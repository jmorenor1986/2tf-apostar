package com.dostf.apostar.services.impl;

import com.dostf.apostar.common.exceptions.MandatoryFieldsMissingException;
import com.dostf.apostar.config.properties.DistribuidorProperties;
import com.dostf.apostar.config.properties.OperacionesProperties;
import com.dostf.apostar.config.properties.PaquetesMovilesProperties;
import com.dostf.apostar.dtos.paquetesMoviles.SubProductosPaquetesMovilesDto;
import com.dostf.apostar.services.IPaquetesMovilesService;
import com.dostf.apostar.services.IRestTemplateService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.eq;

public class PaquetesMovilesServiceTest {

    public static final String URI_BASE = "http://172.17.254.17/web-services-seta-apostar/api";
    public static final String URI_PAQUETES_MOVILES = "/paquetes-moviles";
    public static final String URI_CONSULTAR_SUBPRODUCTOS_PAQUETES = "/consultar-subproductos-paquetes ";
    public static final String EXPECTED_RESULT = "{\"result\": \"result\"}";
    private IPaquetesMovilesService paquetesMovilesService;
    @Mock
    private IRestTemplateService restTemplateService;
    @Mock
    private PaquetesMovilesProperties paquetesMovilesProperties;
    @Mock
    private OperacionesProperties operacionesProperties;
    @Mock
    private DistribuidorProperties distribuidorProperties;
    private String URI_BASE_SERVICE = URI_BASE + URI_PAQUETES_MOVILES;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(operacionesProperties.getPaquetesMoviles()).thenReturn(paquetesMovilesProperties);
        Mockito.when(operacionesProperties.getUrlBase()).thenReturn(URI_BASE);
        Mockito.when(paquetesMovilesProperties.getUrlBase()).thenReturn(URI_PAQUETES_MOVILES);
        Mockito.when(paquetesMovilesProperties.getUrlConsultaSubProductos()).thenReturn(URI_CONSULTAR_SUBPRODUCTOS_PAQUETES);
        paquetesMovilesService = new PaquetesMovilesService(restTemplateService, operacionesProperties, distribuidorProperties);
    }

    @Test
    public void testConsultarSubproductosPaquetesSuccess() {
        final String uri = URI_BASE_SERVICE + URI_CONSULTAR_SUBPRODUCTOS_PAQUETES;
        Mockito.doReturn(Optional.of(EXPECTED_RESULT)).when(restTemplateService).post(eq(uri), Mockito.any());
        String result = paquetesMovilesService.consultarSubProductos(1l);
        Assert.assertNotNull(result);
    }

    @Test(expected = MandatoryFieldsMissingException.class)
    public void testConsultarSubproductosPaquetesTransactionIdIsNull() {
        final String uri = URI_BASE_SERVICE + URI_CONSULTAR_SUBPRODUCTOS_PAQUETES;
        Mockito.doReturn(Optional.of(EXPECTED_RESULT)).when(restTemplateService).post(eq(uri), Mockito.any());
        String result = paquetesMovilesService.consultarSubProductos(null);
    }
}
