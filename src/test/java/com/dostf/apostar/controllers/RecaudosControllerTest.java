package com.dostf.apostar.controllers;

import com.dostf.apostar.config.properties.DistribuidorProperties;
import com.dostf.apostar.config.properties.OperacionesProperties;
import com.dostf.apostar.dtos.recaudos.RecaudoBaseDto;
import com.dostf.apostar.dtos.recaudos.RecaudoDto;
import com.dostf.apostar.services.IRecaudosService;
import com.dostf.apostar.services.client.RestTemplateService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertNotNull;

public class RecaudosControllerTest {
    private static final String EXPECTED_RESULT = "{\"result\": \"result\"}";
    private static final long TRANSACCION_DISTRIBUIDOR_ID = 0L;
    private RecaudosController recaudosController;

    @Mock
    private IRecaudosService recaudosService;
    @Mock
    private DistribuidorProperties distribuidor;
    @Mock
    private OperacionesProperties properties;
    @Mock
    private RestTemplateService restTemplateService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        recaudosController = new RecaudosController(recaudosService);
    }

    @Test
    public void testConsultarDepartamentosSuccess() {
        when(recaudosService.consultarDepartamentos(eq(TRANSACCION_DISTRIBUIDOR_ID))).thenReturn(EXPECTED_RESULT);
        Object result = recaudosController.consultarDepartamentos(TRANSACCION_DISTRIBUIDOR_ID);
        assertNotNull(result);
    }

    @Test
    public void testConsultarRecaudosSuccess() {
        when(recaudosService.consultarRecaudos(any())).thenReturn(EXPECTED_RESULT);
        Object result = recaudosController.consultarRecaudos(new RecaudoDto());
        assertNotNull(result);
    }
}
