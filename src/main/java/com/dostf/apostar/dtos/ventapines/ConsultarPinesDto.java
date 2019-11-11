package com.dostf.apostar.dtos.ventapines;

import com.dostf.apostar.common.enums.ErrorEnum;
import com.dostf.apostar.common.exceptions.MandatoryDtoMissingException;
import com.dostf.apostar.common.exceptions.MandatoryFieldsMissingException;
import com.dostf.apostar.dtos.common.OperacionBaseDto;
import com.dostf.apostar.dtos.common.SubproductoPinDto;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;
import lombok.Setter;

import static java.util.Objects.isNull;

@Getter
@Setter
@JacksonXmlRootElement(localName = "consulta-pin-input")
public class ConsultarPinesDto extends OperacionBaseDto {
    @JacksonXmlProperty(localName = "subproducto-pin")
    private SubproductoPinDto subproductoPin;

    @Override
    public void validateMandatoryFields() {
        super.validateMandatoryFields();
        if(isNull(subproductoPin))
            throw new MandatoryFieldsMissingException(ErrorEnum.SUBPRODUCTO_PIN_IS_MANDATORY.getMessage());
        subproductoPin.validateMandatoryFields();

    }
}
