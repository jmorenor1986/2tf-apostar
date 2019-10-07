package com.dostf.apostar.dtos;

import com.dostf.apostar.config.properties.DistribuidorProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JacksonXmlRootElement(localName = "recarga-input")
public class ConsultarSubproductoDto {
  @ApiModelProperty(hidden = true)
  private DistribuidorProperties distribuidor;
  @JacksonXmlProperty(localName = "transaccion-distribuidor-id")
  protected Long transaccionDistribuidorId;

}
