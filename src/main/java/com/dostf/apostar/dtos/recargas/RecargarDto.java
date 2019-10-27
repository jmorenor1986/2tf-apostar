package com.dostf.apostar.dtos.recargas;

import com.dostf.apostar.common.enums.ErrorEnum;
import com.dostf.apostar.common.exceptions.MandatoryFieldsMissingException;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

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
  @Override
  public void validateMandatoryFields() throws MandatoryFieldsMissingException {
    super.validateMandatoryFields();
    if (Objects.isNull(numero)) {
      throw new MandatoryFieldsMissingException(ErrorEnum.DATA_IS_MANDATORY_NUMERO.getMessage());
    }
    if (Objects.isNull(valor)) {
      throw new MandatoryFieldsMissingException(ErrorEnum.DATA_IS_MANDATORY_VALOR.getMessage());
    }
    if (Objects.isNull(codigoSubproducto)) {
      throw new MandatoryFieldsMissingException(ErrorEnum.DATA_IS_MANDATORY_CODIGO_SUBPRODUCTO.getMessage());
    }
  }
}
