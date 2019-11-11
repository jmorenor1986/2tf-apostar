package com.dostf.apostar.dtos.common;

import com.dostf.apostar.common.enums.ErrorEnum;
import com.dostf.apostar.common.exceptions.MandatoryFieldsMissingException;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@JacksonXmlRootElement(localName = "subproducto-pin")
public class SubproductoPinDto {
    @JacksonXmlProperty(localName = "id-subproducto-pines")
    private Long idSubproductoPines;
    @JacksonXmlProperty(localName = "subproducto-seta")
    private SubproductoSetaDto subproductoSeta;
    @JacksonXmlProperty(localName = "servicio-consultar")
    private String servicioConsultar;
    @JacksonXmlProperty(localName = "servicio")
    private ServicioDto servicioDto;

    public void validateMandatoryFields() {
        if(Objects.isNull(idSubproductoPines))
            throw new MandatoryFieldsMissingException(ErrorEnum.SUBPRODUCTO_IS_MANDATORY.getMessage());
        if(Objects.isNull(servicioConsultar))
            throw new MandatoryFieldsMissingException(ErrorEnum.SERVICIO_CONSULTAR_IS_MANDATORY.getMessage());
        if(Objects.isNull(subproductoSeta))
            throw new MandatoryFieldsMissingException(ErrorEnum.SUBPRODUCTO_SETA_IS_MANDATORY.getMessage());
        if(Objects.isNull(servicioDto))
            throw new MandatoryFieldsMissingException(ErrorEnum.SERVICIO_IS_MANDATORY.getMessage());
    }
}
