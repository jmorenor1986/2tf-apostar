package com.dostf.apostar.dtos.sorteos;

import com.dostf.apostar.common.exceptions.MandatoryFieldsMissingException;
import com.dostf.apostar.common.exceptions.SecureDistribuidorException;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Setter
public class SorteosDto extends SorteosBaseDto {
    @JacksonXmlProperty(localName = "fecha-sorteo")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate fechaSorteo;
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
    
    public LocalDate getFechaSorteo() {
        return LocalDate.parse(fechaSorteo.toString(),DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
    
    public Long getCodigo() {
        return codigo;
    }
}
