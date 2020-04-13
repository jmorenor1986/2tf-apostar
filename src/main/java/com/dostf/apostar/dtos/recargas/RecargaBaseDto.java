package com.dostf.apostar.dtos.recargas;

import com.dostf.apostar.common.enums.ErrorEnum;
import com.dostf.apostar.common.exceptions.MandatoryFieldsMissingException;
import com.dostf.apostar.common.exceptions.SecureDistribuidorException;
import com.dostf.apostar.config.properties.DistribuidorProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.UUID;

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
  @NotNull
  protected Long transaccionDistribuidorId;

  public void validateMandatoryFields() {
    if(Objects.isNull(distribuidor)) {
      throw new SecureDistribuidorException(ErrorEnum.DISTRIBUIDOR_IS_MANDATORY.getMessage());
    }
    distribuidor.checkMandatoryFields(distribuidor);
    if (Objects.isNull(transaccionDistribuidorId)) {
      throw new MandatoryFieldsMissingException(ErrorEnum.TRANSACCION_DISTRIBUIDOR_ID_IS_MANDATORY.getMessage());
    }
  }
}

