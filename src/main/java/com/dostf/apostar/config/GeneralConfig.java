package com.dostf.apostar.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import com.dostf.apostar.common.interceptors.RestTemplateLoggerRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

@Configuration
public class GeneralConfig {
  // config template to consuming service rest
  @Bean
  public RestTemplate restTemplate() {
    RestTemplate restTemplate = new RestTemplate();
    List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
    if (CollectionUtils.isEmpty(interceptors)) {
      interceptors = new ArrayList<>();
    }
    interceptors.add(new RestTemplateLoggerRequest());
    restTemplate.setInterceptors(interceptors);
    return  restTemplate;
  }


  /**
   * config to header to rest consume
   *
   * @return
   */
  @Bean
  public HttpEntity<String> addHeaders() {
    HttpHeaders headers = new HttpHeaders();
    headers.setAccept(Arrays.asList(MediaType.APPLICATION_XML));
    headers.setContentType(MediaType.APPLICATION_XML);
    return new HttpEntity<String>("parameters", headers);
  }

}
