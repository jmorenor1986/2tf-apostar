package com.dostf.apostar.dtos.betplay;

import com.dostf.apostar.common.enums.ErrorEnum;
import com.dostf.apostar.common.exceptions.MandatoryFieldsMissingException;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;
import lombok.Setter;

import static java.util.Objects.isNull;

@Getter
@Setter
@JacksonXmlRootElement(localName = "solicitar-pin")

public class SolicitarPinDto {
    @JacksonXmlProperty(localName = "monto-pin")
    private Double monto;
    @JacksonXmlProperty(localName = "subproducto")
    private SubProductoBetPlayDto subProductoBetPlayDto;


    public void validateMandatoryFields() {
        if (isNull(subProductoBetPlayDto)) {
            throw new MandatoryFieldsMissingException(ErrorEnum.SUBPRODUCTO_IS_MANDATORY.getMessage());
        }
        if (isNull(monto)) {
            throw new MandatoryFieldsMissingException(ErrorEnum.MONTO_IS_MANDATORY.getMessage());
        }
    }
}
