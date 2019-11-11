package com.dostf.apostar.dtos.common;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JacksonXmlRootElement(localName = "subproducto-seta")
public class SubproductoSetaDto {
    @JacksonXmlProperty(localName = "id-subproducto")
    private Long idSubproducto;
}
