package com.dostf.apostar.services.impl;

import com.dostf.apostar.common.exceptions.MandatoryDtoMissingException;
import com.dostf.apostar.common.exceptions.MandatoryFieldsMissingException;
import com.dostf.apostar.common.exceptions.SecureDistribuidorException;
import com.dostf.apostar.config.properties.DistribuidorProperties;
import com.dostf.apostar.dtos.recargas.RecargarDto;
import com.dostf.apostar.dtos.sorteos.SorteosDto;
import com.dostf.apostar.services.ISorteosService;
import com.dostf.apostar.services.client.IRestTemplateService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class SorteosServiceTest {
    private ISorteosService sorteoService;
    @Mock
    private  IRestTemplateService restTemplateService;
    @Mock
    private DistribuidorProperties distribuidor;



    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.sorteoService = new SorteosService(restTemplateService);
    }

    @Test
    public void givenAValidDtoWhenConsultarResultadosThenReturnObjectSucess(){
        String uri = " http://172.17.254.17/web-services/api/resultado-sorteos/consultar-resultado-sorteos";
        SorteosDto sorteosDto= new SorteosDto();
        Mockito.when(restTemplateService.post(uri,sorteosDto)).thenReturn(Optional.of(new Object()));
        Object result = this.sorteoService.consultarResultados(sorteosDto);
        assertNotNull(result);
    }

    @Test(expected = MandatoryDtoMissingException.class)
    public void givenANullDtoWhenConsultaResultadosThenReturnMandatoryException(){
        String uri = Mockito.any();
        SorteosDto sorteosDto = null;
        this.sorteoService.consultarResultados(sorteosDto);
    }

    @Test(expected = SecureDistribuidorException.class)
    public void givenADtoWithDistribuidorWhenConsultaResultadosThenReturnMandatoryFieldException(){
        String uri = Mockito.any();
        SorteosDto sorteosDto = new SorteosDto();
        sorteosDto.setDistribuidor(new DistribuidorProperties());
        this.sorteoService.consultarResultados(sorteosDto);

    }

    @Test(expected = MandatoryFieldsMissingException.class)
    public void givenADtoWithOutFechaSorteoWhenConsultaResultadosThenMandatoryFieldException(){
        String uri = Mockito.any();
        SorteosDto sorteosDto = new SorteosDto();
        sorteosDto.setFechaSorteo(null);
        this.sorteoService.consultarResultados(sorteosDto);
    }

}
