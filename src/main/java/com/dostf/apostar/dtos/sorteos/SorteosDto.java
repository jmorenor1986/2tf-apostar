package com.dostf.apostar.dtos.sorteos;

import com.dostf.apostar.common.enums.ErrorEnum;
import com.dostf.apostar.common.exceptions.MandatoryDtoMissingException;
import com.dostf.apostar.common.exceptions.MandatoryFieldsMissingException;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@JacksonXmlRootElement(localName = "consulta-resultado-sorteos-input")
public class SorteosDto extends SorteosBaseDto{
    @JacksonXmlProperty(localName = "fecha-sorteo")
    private String fechaSorteo;
    private Long codigo;

    public void validateMandatoryFields() {
        super.validateMandatoryFields();
        if (Objects.isNull(fechaSorteo))
            throw new MandatoryFieldsMissingException(ErrorEnum.DATE_SORTEO_IS_MANDATORY.getMessage());
        if (Objects.isNull(codigo))
            throw new MandatoryDtoMissingException(ErrorEnum.CODIGO_LOTERIA_IS_MANDATORY.getMessage());
    }
}
