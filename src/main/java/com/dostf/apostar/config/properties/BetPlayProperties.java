package com.dostf.apostar.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class BetPlayProperties {
    private String urlBase;
    private String urlConsultaSubProductos;
    private String urlSolicitarPin;
    private String urlRealizarRetiro;

}
