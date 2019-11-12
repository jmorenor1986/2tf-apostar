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
@JacksonXmlRootElement(localName = "guardar-paquete-input")
public class GuardarPaqueteMovilDto extends PaquetesMovilesBaseDto {
    @JacksonXmlProperty(localName = "paquete-movil")
    private PaqueteMovilDto paqueteMovilDto;

    public void validateFieldMandatory() {
        if (Objects.isNull(paqueteMovilDto))
            throw new MandatoryFieldsMissingException(ErrorEnum.PAQUETE_MOVIL_MANDATORY.getMessage());
        paqueteMovilDto.validateFieldMandatory();
        validateMandatoryFields();
    }
}
