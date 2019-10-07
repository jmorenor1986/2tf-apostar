package com.dostf.apostar.dtos.recargas;

import com.dostf.apostar.config.properties.DistribuidorProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JacksonXmlRootElement(localName = "recarga-input")
public class RecargaBaseDto {
  @ApiModelProperty(hidden = true)
  protected DistribuidorProperties distribuidor;
  @JacksonXmlProperty(localName = "codigo-subproducto")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  protected String codigoSubproducto;
  @JacksonXmlProperty(localName = "transaccion-distribuidor-id")
  protected Long transaccionDistribuidorId;
}

