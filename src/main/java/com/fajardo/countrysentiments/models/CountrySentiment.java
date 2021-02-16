package com.fajardo.countrysentiments.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CountrySentiment {
    private String country;
    private Double averageSentiment;
}
