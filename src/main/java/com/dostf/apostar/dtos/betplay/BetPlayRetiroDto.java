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
@JacksonXmlRootElement(localName = "betplay-input")
public class BetPlayRetiroDto extends BetPlayBaseDto {

    @JacksonXmlProperty(localName = "registra-retiro")
    private RegistraRetiroDto registraRetiroDto;

    public void validateMandatoryFields() {
        if(isNull(registraRetiroDto))
            throw  new MandatoryFieldsMissingException(ErrorEnum.REGISTRAR_RETIRO_IS_MANDATORY.getMessage());
        registraRetiroDto.validateMandatoryFields();
        distribuidor.checkMandatoryFields(distribuidor);
    }
}
