package com.dostf.apostar.dtos.common;

import com.dostf.apostar.common.enums.ErrorEnum;
import com.dostf.apostar.common.exceptions.MandatoryFieldsMissingException;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

@Getter
@Setter
public class GenericDto {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected String nombre;
    protected String codigo;

    public void validateMandatoryFields() {
        if(StringUtils.isEmpty(codigo)) throw new MandatoryFieldsMissingException(ErrorEnum.DEPARTAMENTO_IS_MANDATORY.getMessage());
    }
}
