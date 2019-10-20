package com.dostf.apostar.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class RecaudosProperties {
    private String urlBase;
    private String urlConsultarDepartamentos;
    private String urlConsultarRecaudos;
}
