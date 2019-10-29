package com.dostf.apostar.services.impl;

import com.dostf.apostar.config.properties.BetPlayProperties;
import com.dostf.apostar.config.properties.DistribuidorProperties;
import com.dostf.apostar.config.properties.OperacionesProperties;
import com.dostf.apostar.services.IBetPlayService;
import com.dostf.apostar.services.IRestTemplateService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doReturn;

public class BetPlayServiceTest {

    public static final String URI_BASE = "http://172.17.254.17/web-services-seta-apostar/api";
    public static final String URI_BETPLAY = "/betplay";
    public static final String URI_CONSULTAR_SUBPRODUCTOS = "/consultar-subproductos";
    private static final long TRANSACCION_DISTRIBUIDOR_ID = 0L;
    private static final String EXPECTED_RESULT = "{\"result\": \"result\"}";
    private IBetPlayService betPlayService;
    @Mock
    private IRestTemplateService restTemplateService;
    @Mock
    private BetPlayProperties betPlayProperties;
    @Mock
    private OperacionesProperties operacionesProperties;
    @Mock
    private DistribuidorProperties distribuidorProperties;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(operacionesProperties.getBetPlay()).thenReturn(betPlayProperties);
        Mockito.when(operacionesProperties.getUrlBase()).thenReturn(URI_BASE);
        Mockito.when(betPlayProperties.getUrlBase()).thenReturn(BetPlayServiceTest.URI_BETPLAY);
        Mockito.when(betPlayProperties.getUrlConsultaSubProductos()).thenReturn(URI_CONSULTAR_SUBPRODUCTOS);
        betPlayService = new BetPlayService(restTemplateService, operacionesProperties, distribuidorProperties);
    }


    @Test
    public void testConsultarSubproductosSuccess() {
        final String uri = URI_BASE.concat(URI_BETPLAY).concat(URI_CONSULTAR_SUBPRODUCTOS);
        Mockito.when(betPlayProperties.getUrlConsultaSubProductos()).thenReturn(URI_CONSULTAR_SUBPRODUCTOS);
        doReturn(Optional.of(EXPECTED_RESULT)).when(restTemplateService).post(eq(uri), any());
        String result = betPlayService.consultarSubProductos(TRANSACCION_DISTRIBUIDOR_ID);
        Assert.assertNotNull(result);
    }

    @Test(expected = ResponseStatusException.class)
    public void testConsultarSubproductosNotFoundId() {
        final String uri = URI_BASE.concat(URI_BETPLAY).concat(URI_CONSULTAR_SUBPRODUCTOS);
        Mockito.when(betPlayProperties.getUrlConsultaSubProductos()).thenReturn(URI_CONSULTAR_SUBPRODUCTOS);
        doReturn(Optional.empty()).when(restTemplateService).post(eq(uri), notNull());
        String result = betPlayService.consultarSubProductos(TRANSACCION_DISTRIBUIDOR_ID);
        Assert.assertNotNull(result);
    }
}
