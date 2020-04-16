package com.dostf.apostar.services.impl;

import com.dostf.apostar.config.properties.DistribuidorProperties;
import com.dostf.apostar.config.properties.OperacionesProperties;
import com.dostf.apostar.config.properties.PaquetesMovilesProperties;
import com.dostf.apostar.dtos.paquetesMoviles.GuardarPaqueteMovilDto;
import com.dostf.apostar.dtos.paquetesMoviles.PaquetesMovilesDto;
import com.dostf.apostar.dtos.paquetesMoviles.SubProductosPaquetesMovilesDto;
import com.dostf.apostar.services.IPaquetesMovilesService;
import com.dostf.apostar.services.IRestTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PaquetesMovilesService implements IPaquetesMovilesService {
    private final IRestTemplateService restTemplateService;
    private final OperacionesProperties operacionesProperties;
    private final DistribuidorProperties distribuidorProperties;
    private final PaquetesMovilesProperties paquetesMovilesProperties;
    private final String uri;

    @Autowired
    public PaquetesMovilesService(IRestTemplateService restTemplateService, OperacionesProperties operacionesProperties, DistribuidorProperties distribuidorProperties) {
        this.restTemplateService = restTemplateService;
        this.operacionesProperties = operacionesProperties;
        this.distribuidorProperties = distribuidorProperties;
        this.paquetesMovilesProperties = operacionesProperties.getPaquetesMoviles();
        this.uri = operacionesProperties.getUrlBase().concat(paquetesMovilesProperties.getUrlBase());
    }

    @Override
    public String consultarSubProductos(Long transactionId) {
        final String requestUri = this.uri.concat(paquetesMovilesProperties.getUrlConsultaSubProductos());
        SubProductosPaquetesMovilesDto subProductosPaquetesMovilesDto = new SubProductosPaquetesMovilesDto();
        subProductosPaquetesMovilesDto.setDistribuidor(distribuidorProperties);
        subProductosPaquetesMovilesDto.setTransaccionDistribuidorId(transactionId);
        subProductosPaquetesMovilesDto.validateMandatoryFields();
        return restTemplateService.post(requestUri, subProductosPaquetesMovilesDto).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found"));
    }

    @Override
    public String consultarPaquetes(Long transactionId, String subproducto) {
        final String requestUri = this.uri.concat(paquetesMovilesProperties.getUrlConsultaPaquetes());
        PaquetesMovilesDto paquetesMovilesDto = new PaquetesMovilesDto();
        paquetesMovilesDto.setCodigoSubproducto(subproducto);
        paquetesMovilesDto.setTransaccionDistribuidorId(transactionId);
        paquetesMovilesDto.setDistribuidor(distribuidorProperties);
        paquetesMovilesDto.validateMandatoryFieldsPaquetes();
        return restTemplateService.post(requestUri, paquetesMovilesDto).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found"));
    }

    @Override
    public String guardarPaquetesMoviles(GuardarPaqueteMovilDto guardarPaqueteMovilDto) {
        final String requestUri = this.uri.concat(paquetesMovilesProperties.getUrlGuardarPaquetesMoviles());
        guardarPaqueteMovilDto.setDistribuidor(distribuidorProperties);
        guardarPaqueteMovilDto.validateFieldMandatory();
        return restTemplateService.post(requestUri, guardarPaqueteMovilDto).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found"));
    }
}
