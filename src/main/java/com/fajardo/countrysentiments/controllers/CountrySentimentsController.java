package com.fajardo.countrysentiments.controllers;

import java.io.File;
import java.io.IOException;

import com.fajardo.countrysentiments.models.CountrySentiment;
import com.fajardo.countrysentiments.models.CountrySentimentRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.pubsub.v1.Subscriber;

import org.springframework.cloud.gcp.pubsub.core.PubSubTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
@AllArgsConstructor
@Controller
public class CountrySentimentsController implements CountrySentiments {

    private final PubSubTemplate pubsubTemplate;

    private final ObjectMapper objectMapper;

    @MessageMapping("country-sentiments")
    @Override
    public Flux<CountrySentiment> streamCountrySentiments(final CountrySentimentRequest request) {
        log.info("Requesting country sentiments from \"topics/{}/subscriptions/{}\"\nExpected projectId is {}",
                System.getenv("GOOGLE_CLOUD_PROJECT"), request.getSubscriptionId(), request.getProjectId());

        return Flux.create(emitter -> {
            Subscriber subscriber = pubsubTemplate.subscribe(request.getSubscriptionId(), (message) -> {
                message.ack();

                log.info("Received message: {}", message.getPubsubMessage().getData().toStringUtf8());
                try {
                    emitter.next(objectMapper.readValue(message.getPubsubMessage().getData().toStringUtf8(),
                            CountrySentiment.class));
                } catch (JsonProcessingException e) {
                    log.error("Error mapping the Pub/Sub message to a CountryMessage: {}", e);
                }
            });

            emitter.onDispose(() -> {
                subscriber.stopAsync();
            });
        });
    }
}
