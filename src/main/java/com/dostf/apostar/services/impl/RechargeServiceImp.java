package com.dostf.apostar.services.impl;

import com.dostf.apostar.config.OperacionesProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dostf.apostar.common.enums.RequestEnum;
import com.dostf.apostar.common.exceptions.MandatoryFieldsMissingException;
import com.dostf.apostar.common.exceptions.ServiceNotAvailableException;
import com.dostf.apostar.dtos.RechargeDto;
import com.dostf.apostar.services.IRechargeService;

@Service
public class RechargeServiceImp implements IRechargeService {

	private final RestTemplate restTemplate;
	private final HttpEntity<String> addHeaders;

	private String uri;

	@Autowired
	public RechargeServiceImp(RestTemplate restTemplate, HttpEntity<String> addHeaders, OperacionesProperties properties) {
		this.uri = properties.getUrlBase() + properties.getRecargas().getUrlBase() + properties.getRecargas().getUrlRecargar();
		this.restTemplate = restTemplate;
		this.addHeaders = addHeaders;

	}

	@Override
	public Object recharge(RechargeDto rechargeData) throws MandatoryFieldsMissingException {
		rechargeData.validateDataMandatory();
		try {
			return restTemplate.exchange(uri, HttpMethod.POST, addHeaders, Object.class);
		} catch (Exception e) {
			String mensaje = RequestEnum.SERVICE_NOT_AVAILABLE.getMessage().replace("{URL}", uri);
			throw new ServiceNotAvailableException(mensaje);
		}

	}

}
