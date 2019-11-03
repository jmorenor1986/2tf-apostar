package com.dostf.apostar.services.impl;

import com.dostf.apostar.config.properties.BetPlayProperties;
import com.dostf.apostar.config.properties.DistribuidorProperties;
import com.dostf.apostar.config.properties.OperacionesProperties;
import com.dostf.apostar.dtos.betplay.BetPlayDto;
import com.dostf.apostar.dtos.betplay.BetPlayPinDto;
import com.dostf.apostar.dtos.betplay.BetPlayRecaudoDto;
import com.dostf.apostar.dtos.betplay.BetPlayRetiroDto;
import com.dostf.apostar.services.IBetPlayService;
import com.dostf.apostar.services.IRestTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class BetPlayService implements IBetPlayService {

    private final IRestTemplateService restTemplateService;
    private final OperacionesProperties operacionesProperties;
    private final DistribuidorProperties distribuidorProperties;
    private final BetPlayProperties betPlayProperties;
    private final String uri;

    @Autowired
    public BetPlayService(IRestTemplateService restTemplateService, OperacionesProperties operacionesProperties, DistribuidorProperties distribuidorProperties) {
        this.restTemplateService = restTemplateService;
        this.operacionesProperties = operacionesProperties;
        this.distribuidorProperties = distribuidorProperties;
        this.betPlayProperties = operacionesProperties.getBetPlay();
        this.uri = operacionesProperties.getUrlBase().concat(betPlayProperties.getUrlBase());
    }

    @Override
    public String consultarSubProductos(final long transactionId) {
        final String requestUri = this.uri.concat(betPlayProperties.getUrlConsultaSubProductos());
        BetPlayDto betPlayDto = new BetPlayDto();
        betPlayDto.setDistribuidor(distribuidorProperties);
        betPlayDto.setTransaccionDistribuidorId(transactionId);
        return restTemplateService.post(requestUri, betPlayDto).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found"));
    }

    @Override
    public String solicitarPin(BetPlayPinDto betPlayPinDto) {
        final String requestUri = this.uri.concat(betPlayProperties.getUrlSolicitarPin());
        betPlayPinDto.setDistribuidor(distribuidorProperties);
        betPlayPinDto.validateMandatoryFields();
        return restTemplateService.post(requestUri,betPlayPinDto).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Not found"));
    }

    @Override
    public String realizarRetiro(BetPlayRetiroDto betPlayRetiroDto) {
        final String requestUri = this.uri.concat(betPlayProperties.getUrlRealizarRetiro());
        betPlayRetiroDto.setDistribuidor(distribuidorProperties);
        betPlayRetiroDto.validateMandatoryFields();
        return restTemplateService.post(requestUri,betPlayRetiroDto).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Not found"));
    }

    @Override
    public String realizaRecaudo(BetPlayRecaudoDto betPlayRecaudoDto) {
        final String requestUri = this.uri.concat(betPlayProperties.getUrlRealizarRecaudo());
        betPlayRecaudoDto.setDistribuidor(distribuidorProperties);
        betPlayRecaudoDto.validateMandatoryFields();
        return restTemplateService.post(requestUri,betPlayRecaudoDto).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Not found"));
    }


}
