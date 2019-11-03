package com.dostf.apostar.dtos.recargas;

import com.dostf.apostar.common.enums.ErrorEnum;
import com.dostf.apostar.common.exceptions.MandatoryFieldsMissingException;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import static java.util.Objects.isNull;

@JacksonXmlRootElement(localName = "recarga-input")
public class ConsultarTopesDto extends RecargaBaseDto {
    @Override
    public void validateMandatoryFields() {
        super.validateMandatoryFields();
        if(isNull(codigoSubproducto))
            throw new MandatoryFieldsMissingException(ErrorEnum.DATA_IS_MANDATORY_CODIGO_SUBPRODUCTO.getMessage());
    }
}
