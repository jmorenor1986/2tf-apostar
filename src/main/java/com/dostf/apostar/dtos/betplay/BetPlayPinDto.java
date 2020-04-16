package com.dostf.apostar.dtos.betplay;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;
import lombok.Setter;

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
