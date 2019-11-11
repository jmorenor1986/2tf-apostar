package com.dostf.apostar.services.impl;

import com.dostf.apostar.config.properties.DistribuidorProperties;
import com.dostf.apostar.config.properties.OperacionesProperties;
import com.dostf.apostar.config.properties.VentaPinesProperties;
import com.dostf.apostar.dtos.ventapines.ConsultarPinesDto;
import com.dostf.apostar.dtos.ventapines.ParametrosPinInputDto;
import com.dostf.apostar.dtos.ventapines.VenderPinesDto;
import com.dostf.apostar.services.IVentaPinesService;
import com.dostf.apostar.services.IRestTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;

import static java.util.Objects.isNull;

@Service
@Validated
public class VentaPinesService implements IVentaPinesService {
    private final IRestTemplateService restTemplateService;
    private final VentaPinesProperties ventaPinesProperties;
    private final DistribuidorProperties distribuidor;
    private final String uri;

    @Autowired
    public VentaPinesService(IRestTemplateService restTemplateService,
                             OperacionesProperties properties,
                             DistribuidorProperties distribuidor) {
        this.restTemplateService = restTemplateService;
        this.distribuidor = distribuidor;
        this.ventaPinesProperties = properties.getVentaPines();
        this.uri = properties.getUrlBase() + ventaPinesProperties.getUrlBase();
    }

    @Override
    public String consultarParametros(final Long transaccionId) {
        final String requestUri = this.uri.concat(ventaPinesProperties.getUrlConsultarParametros());
        ParametrosPinInputDto dto = new ParametrosPinInputDto();
        dto.setDistribuidor(distribuidor);
        dto.setTransaccionDistribuidorId(transaccionId);
        dto.validateMandatoryFields();
        return restTemplateService.post(requestUri, dto).orElseThrow(()
            -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found"));
    }

    @Override
    public String consultarPines(final ConsultarPinesDto dto) {
        if (isNull(dto)) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        final String requestUri = this.uri.concat(ventaPinesProperties.getUrlConsultarPines());
        dto.setDistribuidor(distribuidor);
        dto.validateMandatoryFields();
        return restTemplateService.post(requestUri, dto).orElseThrow(()
            -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found"));
    }

    @Override
    public String venderPines(final VenderPinesDto dto) {
        if (isNull(dto)) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        final String requestUri = this.uri.concat(ventaPinesProperties.getUrlVenderPin());
        dto.setDistribuidor(distribuidor);
        dto.validateMandatoryFields();
        return restTemplateService.post(requestUri, dto).orElseThrow(()
            -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found"));
    }
}
