package com.fajardo.countrysentiments.controllers;

import com.fajardo.countrysentiments.models.CountrySentiment;
import com.fajardo.countrysentiments.models.CountrySentimentRequest;

import reactor.core.publisher.Flux;

public interface CountrySentiments {

    Flux<CountrySentiment> streamCountrySentiments(final CountrySentimentRequest request);
}
