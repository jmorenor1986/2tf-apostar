package com.dostf.apostar.dtos.paquetesMoviles;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JacksonXmlRootElement(localName = "subproductos-paquetes-input")
public class SubProductosPaquetesMovilesDto extends PaquetesMovilesBaseDto{

}
