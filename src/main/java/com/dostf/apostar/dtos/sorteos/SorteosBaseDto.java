package com.dostf.apostar.dtos.sorteos;

import com.dostf.apostar.common.enums.ErrorEnum;
import com.dostf.apostar.common.exceptions.SecureDistribuidorException;
import com.dostf.apostar.config.properties.DistribuidorProperties;
import com.dostf.apostar.dtos.common.IValidateFields;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Objects;

import static java.util.Objects.isNull;

@Getter
@Setter
@JacksonXmlRootElement(localName = "consulta-resultado-sorteos-input")
public class SorteosBaseDto implements IValidateFields {

    @ApiModelProperty(hidden = true)
    @NotNull
    protected DistribuidorProperties distribuidor;
    
    @Override
    public void validateMandatoryFields() {
        if(isNull(distribuidor)) {
            throw new SecureDistribuidorException(ErrorEnum.DISTRIBUIDOR_IS_MANDATORY.getMessage());
        }
        distribuidor.checkMandatoryFields(distribuidor);
    }
}
