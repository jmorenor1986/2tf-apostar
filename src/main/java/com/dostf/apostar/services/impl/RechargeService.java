package com.dostf.apostar.services.impl;

import com.dostf.apostar.config.properties.DistribuidorProperties;
import com.dostf.apostar.config.properties.OperacionesProperties;
import com.dostf.apostar.services.client.IRestTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.dostf.apostar.common.enums.RequestEnum;
import com.dostf.apostar.common.exceptions.MandatoryFieldsMissingException;
import com.dostf.apostar.common.exceptions.ServiceNotAvailableException;
import com.dostf.apostar.dtos.RechargeDto;
import com.dostf.apostar.services.IRechargeService;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;

@Service
public class RechargeService implements IRechargeService {

    @Autowired
    private DistribuidorProperties distribuidor;
    private final IRestTemplateService restTemplateService;

    private String uri;

    @Autowired
    public RechargeService( OperacionesProperties properties,IRestTemplateService restTemplateService) {
        this.uri = properties.getUrlBase() + properties.getRecargas().getUrlBase() + properties.getRecargas().getUrlRecargar();
        this.restTemplateService = restTemplateService;
    }

    @Override
    public Object recharge(RechargeDto rechargeData) throws MandatoryFieldsMissingException {
        rechargeData.validateExistDistribuidor();
        rechargeData.setDistribuidor(distribuidor);
        rechargeData.validateDataMandatory();
        return restTemplateService.post(this.uri, rechargeData).orElseThrow(() ->new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found"));
    }

}
