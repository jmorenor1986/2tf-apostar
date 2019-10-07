package com.dostf.apostar.dtos.recargas;

import java.util.Objects;
import java.util.UUID;

import com.dostf.apostar.common.enums.RechargeEnum;
import com.dostf.apostar.common.exceptions.MandatoryFieldsMissingException;

import com.dostf.apostar.common.exceptions.SecureDistribuidorException;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JacksonXmlRootElement(localName = "recarga-input")
public class RecargarDto extends RecargaBaseDto {
  private String numero;
  private Double valor;

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

  public void validateExistDistribuidor() throws SecureDistribuidorException {
    if (Objects.nonNull(distribuidor)) {
      throw new SecureDistribuidorException("Campos enviados inválidos");
    }
  }
}
