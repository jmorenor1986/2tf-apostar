package com.dostf.apostar.dtos.sorteos;

import com.dostf.apostar.common.exceptions.MandatoryFieldsMissingException;
import com.dostf.apostar.common.exceptions.SecureDistribuidorException;
import com.dostf.apostar.config.properties.DistribuidorProperties;
import com.dostf.apostar.dtos.recargas.RecargaBaseDto;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Objects;

@Getter
@Setter
public class SorteosDto extends SorteosBaseDto {
    @JacksonXmlProperty(localName = "fecha-sorteo")
    private Date fechaSorteo;
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
