package com.dostf.apostar.services.impl;

import com.dostf.apostar.config.properties.DistribuidorProperties;
import com.dostf.apostar.config.properties.OperacionesProperties;
import com.dostf.apostar.config.properties.RecaudosProperties;
import com.dostf.apostar.dtos.recaudos.ConsultarDepartamentoDto;
import com.dostf.apostar.dtos.recaudos.ConsultarRecaudoDto;
import com.dostf.apostar.dtos.recaudos.ConsultarValorDto;
import com.dostf.apostar.dtos.recaudos.GuardarDto;
import com.dostf.apostar.services.IRecaudosService;
import com.dostf.apostar.services.client.RestTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;

import static java.util.Objects.isNull;

@Service
@Validated
public class RecaudosService implements IRecaudosService {
    private final RestTemplateService restTemplateService;
    private final DistribuidorProperties distribuidor;
    private final RecaudosProperties recaudosProperties;
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
        return  restTemplateService.post(requestUri, dto).orElseThrow(()
            -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found"));
    }

    @Override
    public String consultarRecaudos(final ConsultarRecaudoDto dto) {
        if (isNull(dto)) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        final String requestUri = this.uri.concat(recaudosProperties.getUrlConsultarRecaudos());
        dto.setDistribuidor(distribuidor);
        dto.validateMandatoryFields();
        return restTemplateService.post(requestUri, dto).orElseThrow(()
            -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found"));
    }

    @Override
    public String consultarValor(final ConsultarValorDto dto) {
        if (isNull(dto)) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        final String requestUri = this.uri.concat(recaudosProperties.getUrlConsultarValor());
        dto.setDistribuidor(distribuidor);
        dto.validateMandatoryFields();
        return restTemplateService.post(requestUri, dto).orElseThrow(()
            -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found"));
    }

    @Override
    public String guardar(final GuardarDto dto) {
        if (isNull(dto)) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        final String requestUri = this.uri.concat(recaudosProperties.getUrlGuardar());
        dto.setDistribuidor(distribuidor);
        dto.validateMandatoryFields();
        return restTemplateService.post(requestUri, dto).orElseThrow(()
            -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found"));
    }

    public String consultar(final ConsultarRecaudoDto dto) {
        if (isNull(dto)) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        final String requestUri = this.uri.concat(recaudosProperties.getUrlConsultar());
        dto.setDistribuidor(distribuidor);
        dto.validateMandatoryFields();
        return restTemplateService.post(requestUri, dto).orElseThrow(()
            -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found"));
    }
}
