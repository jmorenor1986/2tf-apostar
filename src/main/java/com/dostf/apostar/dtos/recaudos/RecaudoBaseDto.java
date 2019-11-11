package com.dostf.apostar.dtos.recaudos;

import com.dostf.apostar.dtos.common.OperacionBaseDto;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JacksonXmlRootElement(localName = "recaudo-input")
public class RecaudoBaseDto extends OperacionBaseDto {
}

