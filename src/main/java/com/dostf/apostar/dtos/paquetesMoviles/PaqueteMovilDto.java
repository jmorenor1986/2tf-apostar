package com.dostf.apostar.dtos.paquetesMoviles;

import com.dostf.apostar.common.enums.ErrorEnum;
import com.dostf.apostar.common.exceptions.MandatoryFieldsMissingException;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@JacksonXmlRootElement(localName = "paquete-movil")
public class PaqueteMovilDto {
    @JacksonXmlProperty(localName = "codigo-subproducto")
    private String codigoSubproducto;
    private Long valor;
    private Long numero;
    private Long id;

    public void validateFieldMandatory() {
        if (Objects.isNull(codigoSubproducto))
            throw new MandatoryFieldsMissingException(ErrorEnum.SUBPRODUCTO_IS_MANDATORY.getMessage());
        if (Objects.isNull(valor))
            throw new MandatoryFieldsMissingException(ErrorEnum.VALOR_IS_MANDATORY.getMessage());
        if (Objects.isNull(numero))
            throw new MandatoryFieldsMissingException(ErrorEnum.NUMERO_IS_MANDATORY.getMessage());
        if (Objects.isNull(id))
            throw new MandatoryFieldsMissingException(ErrorEnum.ID_PAQUETE_IS_MANDATORY.getMessage());
    }
}
