package com.pixel.vna.fhirfilereader.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
@Service
public class FhirService {

    private final FhirServiceClient fhirServiceClient;
    @Value("${input.file.path}")
    private String inputFilePath;

    Path ipPath = Paths.get(inputFilePath);

    public FhirService(FhirServiceClient fhirServiceClient) {
        this.fhirServiceClient = fhirServiceClient;
    }

    public Flux<String> readFile() {
        Flux<String> stringFlux = Flux.using(
                () -> Files.lines(ipPath),
                Flux::fromStream,
                Stream::close
        );

        //stringFlux.subscribe(System.out::println);

        return stringFlux;
    }


    public void  postFhirs() {
        readFile().flatMap(fhirServiceClient::postFhir).subscribe();
    }
}
