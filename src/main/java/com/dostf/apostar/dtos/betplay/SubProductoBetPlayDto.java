package com.dostf.apostar.dtos.betplay;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JacksonXmlRootElement(localName = "subproducto")
public class SubProductoBetPlayDto {
    private int id;
}
