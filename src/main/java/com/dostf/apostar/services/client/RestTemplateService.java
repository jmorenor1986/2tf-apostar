package com.dostf.apostar.services.client;

import com.dostf.apostar.services.IRestTemplateService;
import com.dostf.apostar.services.IXmlApostarMapper;
import com.dostf.apostar.services.mapper.XmlApostarMapper;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Optional;

@Service
public class RestTemplateService implements IRestTemplateService {

    private final RestTemplate restTemplate;
    private final IXmlApostarMapper xmlMapper;

    @Autowired
    public RestTemplateService(RestTemplateBuilder restTemplateBuilder,
                               final XmlApostarMapper xmlMapper) {
        CloseableHttpClient httpClient = HttpClients.custom().setSSLHostnameVerifier(new NoopHostnameVerifier()).build();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);
        restTemplate = new RestTemplate(requestFactory);
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
