package com.dostf.apostar.services.impl;

import com.dostf.apostar.config.properties.DistribuidorProperties;
import com.dostf.apostar.config.properties.OperacionesProperties;
import com.dostf.apostar.config.properties.RecaudosProperties;
import com.dostf.apostar.dtos.recaudos.ConsultarDepartamentoDto;
import com.dostf.apostar.dtos.recaudos.RecaudoDto;
import com.dostf.apostar.services.IRecaudosService;
import com.dostf.apostar.services.client.RestTemplateService;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;

import static java.util.Objects.isNull;

@Service
@Validated
public class RecaudosService implements IRecaudosService {
    RestTemplateService restTemplateService;
    DistribuidorProperties distribuidor;
    RecaudosProperties recaudosProperties;
    private final String uri;

    @Autowired
    public RecaudosService(final RestTemplateService restTemplateService,
                           final OperacionesProperties properties,
                           final DistribuidorProperties distribuidor) {
        this.restTemplateService = restTemplateService;
        this.distribuidor = distribuidor;
        this.recaudosProperties = properties.getRecaudos();
        this.uri = properties.getUrlBase() + recaudosProperties.getUrlBase();
    }

    @Override
    public String consultarDepartamentos(final Long transaccionId) {
        final String requestUri = this.uri.concat(recaudosProperties.getUrlConsultarDepartamentos());
        ConsultarDepartamentoDto dto = new ConsultarDepartamentoDto();
        dto.setDistribuidor(distribuidor);
        dto.setTransaccionDistribuidorId(transaccionId);
        dto.validateMandatoryFields();
        String result =  restTemplateService.post(requestUri, dto).orElseThrow(()
            -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found"));
        return XML.toJSONObject(result).toString();

    }

    @Override
    public String consultarRecaudos(RecaudoDto dto) {
        if (isNull(dto)) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        final String requestUri = this.uri.concat(recaudosProperties.getUrlConsultarRecaudos());
        dto.setDistribuidor(distribuidor);
        dto.validateMandatoryFields();
        String result =  restTemplateService.post(requestUri, dto).orElseThrow(()
            -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found"));
        return XML.toJSONObject(result).toString();
    }
}
