package com.dostf.apostar.services.impl;

import com.dostf.apostar.common.exceptions.MandatoryDtoMissingException;
import com.dostf.apostar.dtos.sorteos.SorteosDto;
import com.dostf.apostar.services.ISorteosService;
import com.dostf.apostar.services.client.IRestTemplateService;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

public class SorteosService implements ISorteosService {
    private final IRestTemplateService restTemplateService;
    public SorteosService(IRestTemplateService restTemplateService) {
        this.restTemplateService = restTemplateService;
    }

    @Override
    public Object consultarResultados(SorteosDto sorteosDto) throws MandatoryDtoMissingException {
        sorteosDto.validateExistDistribuidor();
        sorteosDto.validateDataMandatory();
        String uri = " http://172.17.254.17/web-services/api/resultado-sorteos/consultar-resultado-sorteos";
        if(Objects.isNull(sorteosDto))
            throw new MandatoryDtoMissingException("SorteoDto is missing");
        return restTemplateService.post(uri, sorteosDto).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found"));
    }
}
