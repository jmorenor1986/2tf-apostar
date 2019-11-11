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
@JacksonXmlRootElement(localName = "paquetes-input")
public class PaquetesMovilesDto extends PaquetesMovilesBaseDto {
    @JacksonXmlProperty(localName = "codigo-subproducto")
    private String codigoSubproducto;

    public void validateMandatoryFieldsPaquetes() {
        if (Objects.isNull(codigoSubproducto))
            throw new MandatoryFieldsMissingException(ErrorEnum.SUBPRODUCTO_IS_MANDATORY.getMessage());
        validateMandatoryFields();
    }
}
