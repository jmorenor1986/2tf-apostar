package com.dostf.apostar.common.interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class HttpRequestInterceptor implements ClientHttpRequestInterceptor {
  private static final Logger LOGGER = LoggerFactory.getLogger(HttpRequestInterceptor.class);
  @Override
  public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
    LOGGER.warn(new String(body, "UTF-8"));
    ClientHttpResponse response = execution.execute(request, body);

    return response;
  }
}
