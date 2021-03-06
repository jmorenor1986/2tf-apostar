package com.dostf.apostar.services.impl;

import com.dostf.apostar.common.enums.ErrorEnum;
import com.dostf.apostar.common.exceptions.MandatoryDtoMissingException;
import com.dostf.apostar.config.properties.DistribuidorProperties;
import com.dostf.apostar.config.properties.OperacionesProperties;
import com.dostf.apostar.config.properties.RecargasProperties;
import com.dostf.apostar.dtos.recargas.*;
import com.dostf.apostar.services.IRecargasService;
import com.dostf.apostar.services.IRestTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static java.util.Objects.isNull;

@Service
public class RecargasService implements IRecargasService {

    private final RecargasProperties recargasProperties;
    private final DistribuidorProperties distribuidor;
    private final IRestTemplateService restTemplateService;

    private final String uri;

    @Autowired
    public RecargasService(IRestTemplateService restTemplateService, OperacionesProperties properties,
                           DistribuidorProperties distribuidor) {
        this.recargasProperties = properties.getRecargas();
        this.uri = properties.getUrlBase() + recargasProperties.getUrlBase();
        this.restTemplateService = restTemplateService;
        this.distribuidor = distribuidor;
    }

    @Override
    public String recargar(final RecargarDto dto) {
        if (isNull(dto)) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        final String requestUri = this.uri + recargasProperties.getUrlRecargar();
        dto.setDistribuidor(distribuidor);
        dto.validateMandatoryFields();
        return restTemplateService.post(requestUri, dto).orElseThrow(()
            -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found"));
    }

    @Override
    public String consultarSubproducto(final Long id) {
        final String requestUri = this.uri + recargasProperties.getUrlConsultarSubproducto();
        RecargaBaseDto dto = new RecargaBaseDto();
        dto.setDistribuidor(distribuidor);
        dto.setTransaccionDistribuidorId(id);
        dto.validateMandatoryFields();
        return restTemplateService.post(requestUri, dto).orElseThrow(()
            -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found"));
    }

    @Override
    public String consultarParametros(final ConsultarParametrosDto dto) {
        final String requestUri = this.uri + recargasProperties.getUrlConsultarParametros();
        if(isNull(dto)) throw new MandatoryDtoMissingException(ErrorEnum.ALL_DATA_IS_NULL.getMessage());
        dto.setDistribuidor(distribuidor);
        dto.validateMandatoryFields();
        return restTemplateService.post(requestUri, dto).orElseThrow(()
            -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found"));
    }

    @Override
    public String consultarTopes(ConsultarTopesDto dto) {
        final String requestUri = this.uri + recargasProperties.getUrlConsultarTopes();
        if(isNull(dto)) throw new MandatoryDtoMissingException(ErrorEnum.ALL_DATA_IS_NULL.getMessage());
        dto.setDistribuidor(distribuidor);
        dto.validateMandatoryFields();
        return restTemplateService.post(requestUri, dto).orElseThrow(()
            -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found"));
    }

    @Override
    public String consultarParametrosPorSubproducto(final ConsultarParametrosPorSubproductoDto dto) {
        final String requestUri = this.uri + recargasProperties.getUrlConsultarParametrosPorSubproducto();
        if(isNull(dto)) throw new MandatoryDtoMissingException(ErrorEnum.ALL_DATA_IS_NULL.getMessage());
        dto.setDistribuidor(distribuidor);
        dto.validateMandatoryFields();
        return restTemplateService.post(requestUri, dto).orElseThrow(()
            -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found"));
    }
}
