package com.dostf.apostar.services.impl;

import com.dostf.apostar.common.enums.RequestEnum;
import com.dostf.apostar.common.enums.ResponseEnum;
import com.dostf.apostar.common.exceptions.MandatoryDtoMissingException;
import com.dostf.apostar.config.properties.DistribuidorProperties;
import com.dostf.apostar.config.properties.OperacionesProperties;
import com.dostf.apostar.config.properties.SorteosProperties;
import com.dostf.apostar.dtos.sorteos.SorteosDto;
import com.dostf.apostar.services.IRestTemplateService;
import com.dostf.apostar.services.ISorteosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@Service
public class SorteosService implements ISorteosService {
    private final IRestTemplateService restTemplateService;
    private final DistribuidorProperties distribuidorProperties;
    private final SorteosProperties sorteosProperties;
    private final String uri;

    @Autowired
    public SorteosService(IRestTemplateService restTemplateService, DistribuidorProperties distribuidorProperties, OperacionesProperties properties) {
        this.restTemplateService = restTemplateService;
        this.distribuidorProperties = distribuidorProperties;
        this.sorteosProperties = properties.getSorteos();
        this.uri= properties.getUrlBase().concat(sorteosProperties.getUrlBase());
    }
    
    @Override
    public Object consultarResultados(SorteosDto sorteosDto) throws MandatoryDtoMissingException {
        String uri = this.uri.concat(sorteosProperties.getUrlConsultarResultados());
        if(Objects.isNull(sorteosDto))
            throw new MandatoryDtoMissingException(RequestEnum.ALL_DATA_IS_NULL.getMessage());
        sorteosDto.validateExistDistribuidor();
        sorteosDto.validateDataMandatory();
        sorteosDto.setDistribuidor(this.distribuidorProperties);
        return restTemplateService.post(uri, sorteosDto).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, ResponseEnum.NOT_FOUND_SERVICE.getMessage()));
    }
}
