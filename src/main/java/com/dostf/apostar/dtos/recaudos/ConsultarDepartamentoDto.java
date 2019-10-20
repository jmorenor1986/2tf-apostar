package com.dostf.apostar.dtos.recaudos;

import com.dostf.apostar.common.enums.ErrorEnum;
import com.dostf.apostar.common.exceptions.MandatoryFieldsMissingException;
import com.dostf.apostar.common.exceptions.SecureDistribuidorException;
import com.dostf.apostar.config.properties.DistribuidorProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

import static java.util.Objects.isNull;

@Getter
@Setter
@JacksonXmlRootElement(localName = "recaudo-input")
public class ConsultarDepartamentoDto extends RecaudoBaseDto {
    @JacksonXmlProperty(localName = "transaccion-distribuidor-id")
    @NotNull
    protected Long transaccionDistribuidorId;

    @Override
    public void validateMandatoryFields() {
        super.validateMandatoryFields();
        if(isNull(transaccionDistribuidorId)) {
            throw new MandatoryFieldsMissingException(ErrorEnum.TRANSACCION_DISTRIBUIDOR_ID_IS_MANDATORY.getMessage());
        }
    }
}
