package com.dostf.apostar.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class RecargasProperties {
  private String urlBase;
  private String urlRecargar;
  private String urlConsultarSubproducto;
  private String urlConsultarParametros;
  private String urlConsultarTopes;
  private String urlConsultarParametrosPorSubproducto;
}
