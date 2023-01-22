package com.mycompany.app.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

import com.mycompany.app.entities.Item;
import com.mycompany.app.entities.Price;

/**
 * MarketService
 */
public class MarketService {

    public List<Price> getAllItemPrices(Item item) throws IOException {
        List<Price> priceList = new ArrayList();
        
        URL url = new URL(urlName(item));
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        Map<String, String> params = new HashMap<String, String>();
        params.put("quality", item.quality.toString());

        // con.setDoOutput(true);
        // DataOutputStream out = new DataOutputStream(con.getOutputStream());
        // String paramsString = ParameterStringBuilder.getParamsString(params);
        // System.out.println(paramsString);
        // out.writeBytes(paramsString);
        // out.flush();
        // out.close();

        
        int status = con.getResponseCode();

        Reader streamReader = null;

        if (status > 299) {
            streamReader = new InputStreamReader(con.getErrorStream());
        } else {
            streamReader = new InputStreamReader(con.getInputStream());
        }

        BufferedReader bufferedReader = new BufferedReader(streamReader);
        StringBuffer response = new StringBuffer();
        String line;
        while((line = bufferedReader.readLine()) != null) {
            response.append(line);
        }
        bufferedReader.close();

        JSONObject data = new JSONObject(response.toString());
        System.out.println(data);

        return priceList;
    }

    private String urlName(Item item) {
        String uri = "https://www.albion-online-data.com";
        String format = ".json";
        String url = uri + "/api/v2/stats/Prices/" + itemMaketPlaceName(item) + format;
        
        System.out.println(url);
        
        return url;
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
        return resultString.length() > 0
          ? resultString.substring(0, resultString.length() - 1)
          : resultString;
    }
}
