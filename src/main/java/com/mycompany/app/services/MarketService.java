package com.mycompany.app.services;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mycompany.app.entities.Item;
import com.mycompany.app.entities.Price;

/**
 * MarketService
 */
public class MarketService {

    public List<Price> getAllItemPrices(Item item) throws IOException, InterruptedException {
        List<Price> priceList = new ArrayList();
        
        HttpRequest request = HttpRequest.newBuilder()
            .GET()
            .uri(URI.create(buildUri(item)))
            .timeout(Duration.ofSeconds(5))
            .build();

        HttpClient httpClient = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(3))
            .build();

        HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());

        System.out.println(response.body());

        return priceList;
    }

    private String buildUri(Item item) throws UnsupportedEncodingException {
        String url = "https://www.albion-online-data.com";
        String format = ".json";
        
        Map<String, String> paramsMap = new HashMap<String,String>();
        paramsMap.put("quality", item.quality.toString());
        String params = ParameterStringBuilder.getParamsString(paramsMap);
        
        String uri = url + "/api/v2/stats/Prices/" + itemMaketPlaceName(item) + format + params;
        System.out.println(uri);
        
        return uri;
    }

    private String itemMaketPlaceName(Item item) {
        return item.tier.toString() + "_" + item.name;
    }
}
 
class ParameterStringBuilder {
    public static String getParamsString(Map<String, String> params) 
      throws UnsupportedEncodingException{
        StringBuilder result = new StringBuilder();

        for (Map.Entry<String, String> entry : params.entrySet()) {
          result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
          result.append("=");
          result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
          result.append("&");
        }

        String resultString = result.toString();
        if (resultString.length() > 0) {
            return "?".concat(resultString.substring(0, resultString.length() - 1));    
        } 
        return resultString;
    }
}
