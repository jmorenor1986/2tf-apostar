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
@JacksonXmlRootElement(localName = "registra-recaudo")
public class RegisraRecaudoDto {
    @JacksonXmlProperty(localName = "cliente-id")
    private String clienteId;
    @JacksonXmlProperty(localName = "subproducto")
    private SubProductoBetPlayDto subProductoBetPlayDto;
    private Double valor;

    public void validateMandatoryFields(){
        if(isNull(clienteId))
            throw new MandatoryFieldsMissingException(ErrorEnum.CLIENT_ID_IS_MANDATORY.getMessage());
        if(isNull(subProductoBetPlayDto))
            throw  new MandatoryFieldsMissingException(ErrorEnum.SUBPRODUCTO_IS_MANDATORY.getMessage());
        if(isNull(valor))
            throw  new MandatoryFieldsMissingException(ErrorEnum.VALOR_IS_MANDATORY.getMessage());
    }
}
