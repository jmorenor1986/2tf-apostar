package com.dostf.apostar.config;

import org.springframework.stereotype.Component;

@Component
public class RecargasProperties {
  private String urlBase;
  private String urlRecargar;

  public String getUrlBase() {
    return urlBase;
  }

  public void setUrlBase(String urlBase) {
    this.urlBase = urlBase;
  }

  public String getUrlRecargar() {
    return urlRecargar;
  }

  public void setUrlRecargar(String urlRecargar) {
    this.urlRecargar = urlRecargar;
  }
}
