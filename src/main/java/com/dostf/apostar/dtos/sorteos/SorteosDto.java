package com.dostf.apostar.dtos.sorteos;

import com.dostf.apostar.common.exceptions.MandatoryFieldsMissingException;
import com.dostf.apostar.common.exceptions.SecureDistribuidorException;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@JacksonXmlRootElement(localName = "consulta-resultado-sorteos-input")
public class SorteosDto extends SorteosBaseDto {
    @JacksonXmlProperty(localName = "fecha-sorteo")
    private String fechaSorteo;
    private Long codigo;
    public void validateExistDistribuidor() {
        if(Objects.nonNull(distribuidor)){
            throw new SecureDistribuidorException("Campos enviados inválidos");
        }
    }

    public void validateDataMandatory() {
        if(Objects.isNull(fechaSorteo))
            throw new MandatoryFieldsMissingException("Fecha sorteo es obligatoria");

    }
}
