package com.pixel.vna.fhirfilereader.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class FhirServiceClient {

    private final WebClient webClient;

    public FhirServiceClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<String> postFhir(String s) {
        return webClient
                .post()
                .uri("localhost:8080/post")
                .body(Mono.just(s), String.class)
                .retrieve()
                .bodyToMono(String.class);
    }
}
