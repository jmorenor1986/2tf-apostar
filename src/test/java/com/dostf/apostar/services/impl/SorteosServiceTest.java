package com.dostf.apostar.services.impl;

import com.dostf.apostar.common.exceptions.MandatoryDtoMissingException;
import com.dostf.apostar.common.exceptions.MandatoryFieldsMissingException;
import com.dostf.apostar.common.exceptions.SecureDistribuidorException;
import com.dostf.apostar.config.properties.DistribuidorProperties;
import com.dostf.apostar.config.properties.OperacionesProperties;
import com.dostf.apostar.config.properties.SorteosProperties;
import com.dostf.apostar.dtos.sorteos.SorteosDto;
import com.dostf.apostar.services.IRestTemplateService;
import com.dostf.apostar.services.ISorteosService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

public class SorteosServiceTest {
    private static final String URL_RESULTADO_SORTEOS = "/resultado-sorteos";
    private static final String URL_CONSULTAR_RESULTADO_SORTEOS = "/consultar-resultado-sorteos";
    private static final String URL_BASE = "http://172.17.254.17/web-services/api";
    private static final String EXPECTED_RESULT = "RESULT0";
    public static final String URL_CONSULTAR_RESULTADO_SORTEOS_SEMANAL = "consultar-resultado-sorteos-semanal";
    public static final long CODIGO = 1;
    private ISorteosService sorteoService;
    @Mock
    private IRestTemplateService restTemplateService;
    @Mock
    private DistribuidorProperties distribuidorProperties;
    @Mock
    private OperacionesProperties operacionesProperties;
    @Mock
    private SorteosProperties sorteosProperties;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(operacionesProperties.getUrlBase()).thenReturn(URL_BASE);
        Mockito.when(operacionesProperties.getSorteos()).thenReturn(sorteosProperties);
        Mockito.when(sorteosProperties.getUrlBase()).thenReturn(URL_RESULTADO_SORTEOS);
        Mockito.when(sorteosProperties.getUrlConsultarResultadosSemanal()).thenReturn(URL_CONSULTAR_RESULTADO_SORTEOS_SEMANAL);
        Mockito.when(sorteosProperties.getUrlConsultarResultados()).thenReturn(URL_CONSULTAR_RESULTADO_SORTEOS);
        this.sorteoService = new SorteosService(restTemplateService,distribuidorProperties,operacionesProperties);
    }

    @Test
    public void givenAValidDtoWhenConsultarResultadosThenReturnObjectSucess(){
        final String uri = URL_BASE.concat(URL_RESULTADO_SORTEOS).concat(URL_CONSULTAR_RESULTADO_SORTEOS);
        SorteosDto sorteosDto= Mockito.mock(SorteosDto.class);
        Mockito.when(restTemplateService.post(eq(uri), any(SorteosDto.class))).thenReturn(Optional.of(EXPECTED_RESULT));
        Object result = this.sorteoService.consultarResultados(sorteosDto);
        assertNotNull(result);
    }

    @Test(expected = MandatoryDtoMissingException.class)
    public void givenANullDtoWhenConsultaResultadosThenReturnMandatoryException(){
        String uri = "";
        SorteosDto sorteosDto = null;
        this.sorteoService.consultarResultados(sorteosDto);
    }

    @Test(expected = SecureDistribuidorException.class)
    public void givenADtoWithDistribuidorWhenConsultaResultadosThenReturnMandatoryFieldException(){
        SorteosDto sorteosDto = new SorteosDto();
        sorteosDto.setDistribuidor(new DistribuidorProperties());
        this.sorteoService.consultarResultados(sorteosDto);

    }

    @Test(expected = MandatoryFieldsMissingException.class)
    public void givenADtoWithOutFechaSorteoWhenConsultaResultadosThenMandatoryFieldException(){ ;
        SorteosDto sorteosDto = new SorteosDto();
        sorteosDto.setFechaSorteo(null);
        this.sorteoService.consultarResultados(sorteosDto);
    }
    
    @Test
    public void givenAValidDtoWhenInvokeConsultaResultadosSemanalThenObjectSuccess(){
        final String uri = URL_BASE.concat(URL_RESULTADO_SORTEOS).concat(URL_CONSULTAR_RESULTADO_SORTEOS_SEMANAL);
        SorteosDto sorteosDto = Mockito.mock(SorteosDto.class);
        Mockito.when(restTemplateService.post(eq(uri), any(SorteosDto.class))).thenReturn(Optional.of(EXPECTED_RESULT));
        String result = sorteoService.consultarResultadosSemanal(sorteosDto);
        assertNotNull(result);
    }

    @Test(expected = MandatoryDtoMissingException.class)
    public void givenANullDtoWhenInvokeConsultaResultadosSemanalThenMandatoryDtoException(){
        final String uri = URL_BASE.concat(URL_RESULTADO_SORTEOS).concat(URL_CONSULTAR_RESULTADO_SORTEOS_SEMANAL);
        SorteosDto sorteosDto = null;
        Mockito.when(restTemplateService.post(eq(uri), any(SorteosDto.class))).thenReturn(Optional.of(EXPECTED_RESULT));
        String result = sorteoService.consultarResultadosSemanal(sorteosDto);
    }
    
    @Test(expected = SecureDistribuidorException.class)
    public void givenAIncorrectDistribuidorWhenInvokeConsultaResultadosSemanalThenMandatoryFieldExeption(){
        final String uri = URL_BASE.concat(URL_RESULTADO_SORTEOS).concat(URL_CONSULTAR_RESULTADO_SORTEOS_SEMANAL);
        SorteosDto sorteosDto = new SorteosDto();
        sorteosDto.setDistribuidor(Mockito.mock(DistribuidorProperties.class));
        sorteosDto.setCodigo(CODIGO);
        sorteosDto.setFechaSorteo(null);
        Mockito.when(restTemplateService.post(eq(uri), any(SorteosDto.class))).thenReturn(Optional.of(EXPECTED_RESULT));
        String result = sorteoService.consultarResultadosSemanal(sorteosDto);
    }
    
}
