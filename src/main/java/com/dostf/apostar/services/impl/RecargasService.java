package com.dostf.apostar.services.impl;

import com.dostf.apostar.config.properties.DistribuidorProperties;
import com.dostf.apostar.config.properties.OperacionesProperties;
import com.dostf.apostar.config.properties.RecargasProperties;
import com.dostf.apostar.dtos.recargas.RecargaBaseDto;
import com.dostf.apostar.services.client.IRestTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import com.dostf.apostar.common.exceptions.MandatoryFieldsMissingException;
import com.dostf.apostar.dtos.recargas.RecargarDto;
import com.dostf.apostar.services.IRecargasService;
import org.springframework.web.server.ResponseStatusException;

@Service
public class RecargasService implements IRecargasService {

  private final RecargasProperties recargasProperties;
  private final DistribuidorProperties distribuidor;
  private final IRestTemplateService restTemplateService;

  private final String uri;

  @Autowired
  public RecargasService(OperacionesProperties properties, IRestTemplateService restTemplateService,
                         DistribuidorProperties distribuidor) {
    this.recargasProperties = properties.getRecargas();
    this.uri = properties.getUrlBase() + recargasProperties.getUrlBase();
    this.restTemplateService = restTemplateService;
    this.distribuidor = distribuidor;
  }

  @Override
  public Object recargar(RecargarDto rechargeData) throws MandatoryFieldsMissingException {
    final String uri = this.uri + recargasProperties.getUrlRecargar();
    rechargeData.validateExistDistribuidor();
    rechargeData.setDistribuidor(distribuidor);
    rechargeData.validateDataMandatory();
    return restTemplateService.post(uri, rechargeData).orElseThrow(()
        -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found"));
  }

  @Override
  public Object consultarSubproducto(Long id) {
    final String uri = this.uri + recargasProperties.getUrlConsultarSubproducto();
    RecargaBaseDto consultarSubproducto = new RecargaBaseDto();
    consultarSubproducto.setDistribuidor(distribuidor);
    consultarSubproducto.setTransaccionDistribuidorId(id);
    return restTemplateService.post(uri, consultarSubproducto).orElseThrow(()
        -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found"));
  }
}
