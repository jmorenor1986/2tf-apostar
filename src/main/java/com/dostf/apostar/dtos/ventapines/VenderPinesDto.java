package com.dostf.apostar.dtos.ventapines;

import com.dostf.apostar.dtos.common.OperacionBaseDto;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JacksonXmlRootElement(localName = "venta-pin-input")
public class VenderPinesDto extends OperacionBaseDto {
    //TODO Properties

    @Override
    public void validateMandatoryFields() {
        super.validateMandatoryFields();
        //TODO Validate fields
    }
}
