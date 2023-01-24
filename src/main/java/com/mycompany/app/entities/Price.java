package com.mycompany.app.entities;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mycompany.app.entities.enums.City;

/**
 * Price
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Price {
    public Item item;
    public City marketLocation;
    public Double sellPriceMin;
    public Double sellPriceMax;
    public Date sellPriceMinDate;
    public Date sellPriceMaxDate;
    public Double buyPriceMin;
    public Double buyPriceMax;
    public Date buyPriceMinDate;
    public Date buyPriceMaxDate;
}
