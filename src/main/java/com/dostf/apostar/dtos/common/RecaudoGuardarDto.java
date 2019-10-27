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
public class RecaudoGuardarDto extends RecaudoDto {
    protected Double valor;

    @Override
    public void validateMandatoryFields() {
        if(StringUtils.isEmpty(valor)) throw new MandatoryFieldsMissingException(ErrorEnum.DATA_IS_MANDATORY_VALOR.getMessage());
    }
}
