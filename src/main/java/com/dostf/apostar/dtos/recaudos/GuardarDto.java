package com.dostf.apostar.dtos.recaudos;

import com.dostf.apostar.common.enums.ErrorEnum;
import com.dostf.apostar.common.exceptions.MandatoryFieldsMissingException;
import com.dostf.apostar.dtos.common.RecaudoGuardarDto;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;
import lombok.Setter;

import static java.util.Objects.isNull;

@Getter
@Setter
@JacksonXmlRootElement(localName = "recaudo-input")
public class GuardarDto extends ConsultarRecaudoDto {
    protected RecaudoGuardarDto recaudo;
    @Override
    public void validateMandatoryFields() {
        super.validateMandatoryFields();
        if(isNull(recaudo)) throw new MandatoryFieldsMissingException(ErrorEnum.CODIGO_IS_MANDATORY.getMessage());
        recaudo.validateMandatoryFields();
    }
}
