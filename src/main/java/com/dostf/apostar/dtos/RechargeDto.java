package com.dostf.apostar.dtos;

import java.util.Objects;
import java.util.UUID;

import com.dostf.apostar.common.enums.RechargeEnum;
import com.dostf.apostar.common.exceptions.MandatoryFieldsMissingException;

import com.dostf.apostar.config.properties.DistribuidorProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement
public class RechargeDto {
  private DistribuidorProperties distribuidor;
  private String numero;
  @JsonProperty("codigo-subproducto")
  @XmlElement(name = "codigo-subproducto")
  private String codigoSubproducto;
  private Double valor;
  @JsonProperty("transaction-distribuidor-id")
  @XmlElement(name = "transaction-distribuidor-id")
  private Long transaccionDistribuidorId;

  /**
   * validate  field mandatory distributor and recharge
   *
   * @throws MandatoryFieldsMissingException
   */
  public void validateDataMandatory() throws MandatoryFieldsMissingException {
    distribuidor.checkMandatoryFields(distribuidor);
    if (Objects.isNull(numero)) {
      throw new MandatoryFieldsMissingException(RechargeEnum.DATA_IS_MANDATORY_NUMERO.getMessage());
    }
    if (Objects.isNull(codigoSubproducto)) {
      throw new MandatoryFieldsMissingException(RechargeEnum.DATA_IS_MANDATORY_CODIGO_SUBPRODUCTO.getMessage());
    }
    if (Objects.isNull(valor)) {
      throw new MandatoryFieldsMissingException(RechargeEnum.DATA_IS_MANDATORY_VALOR.getMessage());
    }
    if (Objects.isNull(transaccionDistribuidorId)) {
      transaccionDistribuidorId = UUID.randomUUID().getLeastSignificantBits();
    }
  }
}
