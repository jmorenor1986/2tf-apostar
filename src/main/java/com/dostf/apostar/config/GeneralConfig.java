package com.dostf.apostar.config;

import java.util.Arrays;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

@Configuration
public class GeneralConfig {
  // config template to consuming service rest
  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
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
