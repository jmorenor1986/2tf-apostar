package com.dostf.apostar.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class VentaPinesProperties {
    private String urlBase;
    private String urlConsultarParametros;
    private String urlConsultarPines;
    private String urlVenderPin;
}
