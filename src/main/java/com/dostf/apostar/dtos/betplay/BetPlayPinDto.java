package com.dostf.apostar.dtos.betplay;

import com.dostf.apostar.common.enums.ErrorEnum;
import com.dostf.apostar.common.exceptions.MandatoryFieldsMissingException;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

import static java.util.Objects.isNull;

@Getter
@Setter
@JacksonXmlRootElement(localName = "betplay-input")
public class BetPlayPinDto extends BetPlayBaseDto {
    @JacksonXmlProperty(localName = "solicitar-pin")
    private SolicitarPinDto solicitarPinDto;
    public void validateMandatoryFields() {
        solicitarPinDto.validateMandatoryFields();
        distribuidor.checkMandatoryFields(distribuidor);
    }


}
