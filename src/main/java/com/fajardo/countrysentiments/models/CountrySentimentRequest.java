package com.fajardo.countrysentiments.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CountrySentimentRequest {
    private String subscriptionId;
    private String projectId;
}
