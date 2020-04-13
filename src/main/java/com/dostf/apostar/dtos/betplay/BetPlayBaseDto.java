package com.dostf.apostar.dtos.betplay;

import com.dostf.apostar.common.enums.ErrorEnum;
import com.dostf.apostar.common.exceptions.MandatoryFieldsMissingException;
import com.dostf.apostar.common.exceptions.SecureDistribuidorException;
import com.dostf.apostar.config.properties.DistribuidorProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

import static java.util.Objects.isNull;

@Getter
@Setter
@JacksonXmlRootElement(localName = "betplay-input")
public class BetPlayBaseDto {
    @ApiModelProperty(hidden = true)
    @NotNull
    protected DistribuidorProperties distribuidor;
    @JacksonXmlProperty(localName = "transaccion-distribuidor-id")
    @NotNull
    protected Long transaccionDistribuidorId;


    public void validateMandatoryFields() {
        if (isNull(distribuidor)) {
            throw new SecureDistribuidorException(ErrorEnum.DISTRIBUIDOR_IS_MANDATORY.getMessage());
        }
        if (isNull(transaccionDistribuidorId)) {
            throw new MandatoryFieldsMissingException(ErrorEnum.TRANSACCION_DISTRIBUIDOR_ID_IS_MANDATORY.getMessage());
        }
        distribuidor.checkMandatoryFields(distribuidor);
    }
}
