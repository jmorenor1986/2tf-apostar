package com.dostf.apostar.services.client;

import com.dostf.apostar.common.interceptors.HttpRequestInterceptor;
import com.dostf.apostar.services.IRestTemplateService;
import com.dostf.apostar.services.IXmlApostarMapper;
import com.dostf.apostar.services.mapper.XmlApostarMapper;
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

    private final RestTemplate restTemplate;
    private final IXmlApostarMapper xmlMapper;
    @Autowired
    public RestTemplateService(RestTemplateBuilder restTemplateBuilder,
                               final XmlApostarMapper xmlMapper) {
        restTemplate = restTemplateBuilder
            .additionalInterceptors(Collections.singletonList(new HttpRequestInterceptor()))
            .build();
        this.xmlMapper = xmlMapper;
    }

    @Override
    public Optional<String> post(String uri, Object request) {
        HttpEntity<Object> requestXMl = new HttpEntity<>(request, addHeaders());
        String xml = restTemplate.exchange(uri, HttpMethod.POST, requestXMl, String.class).getBody();
        return xmlMapper.toJsonString(xml);
    }

    private HttpHeaders addHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_XML));
        headers.setContentType(MediaType.APPLICATION_XML);
        return headers;
    }
}
