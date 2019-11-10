package com.dostf.apostar.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class PaquetesMovilesProperties {
    private String urlBase;
    private String urlConsultaSubProductos;
    private String urlConsultaPaquetes;

}
