package com.dostf.apostar.services.client;

import com.dostf.apostar.common.interceptors.HttpRequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

@Service
public class RestTemplateService implements IRestTemplateService {

    RestTemplate restTemplate;
    @Autowired
    public RestTemplateService(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder
            .additionalInterceptors(Collections.singletonList(new HttpRequestInterceptor()))
            .build();
    }

    @Override
    public Optional<Object> post(String uri, Object request) {
        HttpEntity<Object> requestXMl = new HttpEntity<>(request, addHeaders());
        return Optional.ofNullable(restTemplate.exchange(uri, HttpMethod.POST, requestXMl, Object.class).getBody());
    }

    private HttpHeaders addHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_XML));
        headers.setContentType(MediaType.APPLICATION_XML);
        return headers;
    }
}
