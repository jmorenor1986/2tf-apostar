package com.dostf.apostar.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "operaciones.api")
public class OperacionesProperties {
  private String urlBase;
  private RecargasProperties recargas;

  public String getUrlBase() {
    return urlBase;
  }

  public void setUrlBase(String urlBase) {
    this.urlBase = urlBase;
  }

  public RecargasProperties getRecargas() {
    return recargas;
  }

  public void setRecargas(RecargasProperties recargas) {
    this.recargas = recargas;
  }
}
