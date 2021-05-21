package com.pixel.vna.fhirfilereader.controller;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.pixel.vna.fhirfilereader.service.FhirService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class TestController {

    private final FhirService fhirService;

    public TestController(FhirService fhirService) {
        this.fhirService = fhirService;
    }

    @GetMapping("read")
    public Mono<String> initializeReading(){
      fhirService.postFhirs();
      return Mono.just("ok");
    }

    @PostMapping("post")
    public Mono<String> testPosting(@RequestBody String s){
        DocumentContext context = JsonPath.parse(s);
        String id= context.read("$.id");
        System.out.println("posting fhir id: " + id);
        return Mono.just("ok");
    }
}
