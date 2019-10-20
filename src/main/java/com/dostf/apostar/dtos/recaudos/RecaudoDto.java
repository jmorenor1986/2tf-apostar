package com.dostf.apostar.dtos.recaudos;

import com.dostf.apostar.common.enums.ErrorEnum;
import com.dostf.apostar.common.exceptions.MandatoryFieldsMissingException;
import com.dostf.apostar.dtos.common.DepartamentoDto;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

import static java.util.Objects.isNull;


@Getter
@Setter
@JacksonXmlRootElement(localName = "recaudo-input")
public class RecaudoDto extends RecaudoBaseDto {
    private DepartamentoDto departamento;
    @JacksonXmlProperty(localName = "transaccion-distribuidor-id")
    @NotNull
    protected Long transaccionDistribuidorId;

    @Override
    public void validateMandatoryFields() {
        super.validateMandatoryFields();
        if(isNull(departamento)) throw new MandatoryFieldsMissingException(ErrorEnum.DEPARTAMENTO_IS_MANDATORY.getMessage());
        departamento.validateMandatoryFields();
        if(isNull(transaccionDistribuidorId)) {
            throw new MandatoryFieldsMissingException(ErrorEnum.TRANSACCION_DISTRIBUIDOR_ID_IS_MANDATORY.getMessage());
        }
    }
}
