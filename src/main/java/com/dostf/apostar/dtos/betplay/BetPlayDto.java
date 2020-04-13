package com.dostf.apostar.dtos.betplay;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JacksonXmlRootElement(localName = "betplay-input")
public class BetPlayDto extends BetPlayBaseDto {
}
