package com.dostf.apostar.dtos.common;

import com.dostf.apostar.common.enums.ErrorEnum;
import com.dostf.apostar.common.exceptions.MandatoryFieldsMissingException;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

@Getter
@Setter
@JacksonXmlRootElement(localName = "recaudo")
public class RecaudoDto {
    protected String codigo;
    protected String referencia;

    public void validateMandatoryFields() {
        if(StringUtils.isEmpty(referencia)) throw new MandatoryFieldsMissingException(ErrorEnum.IVA_IS_MANDATORY.getMessage());
    }
}
