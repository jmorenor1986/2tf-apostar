package com.dostf.apostar.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "operaciones.api")
public class OperacionesProperties {
  private String urlBase;
  private RecargasProperties recargas;
  private RecaudosProperties recaudos;
}
