package com.dostf.apostar.common.interceptors;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.util.Arrays;


public class RestTemplateLoggerRequest implements ClientHttpRequestInterceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestTemplateLoggerRequest.class);
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        LOGGER.info(new String(body, "UTF-8"));
        request.getHeaders().setAccept(Arrays.asList(MediaType.APPLICATION_XML));
        request.getHeaders().setContentType(MediaType.APPLICATION_XML);
        ClientHttpResponse response = execution.execute(request, body);

        return response;
    }
}
