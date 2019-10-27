package com.dostf.apostar.controllers;

import com.dostf.apostar.dtos.recaudos.ConsultarRecaudoDto;
import com.dostf.apostar.dtos.recaudos.ConsultarValorDto;
import com.dostf.apostar.dtos.recaudos.GuardarDto;
import com.dostf.apostar.services.IRecaudosService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class RecaudosControllerTest {
    private static final String EXPECTED_RESULT = "{\"result\": \"result\"}";
    private static final long TRANSACCION_DISTRIBUIDOR_ID = 0L;
    private RecaudosController recaudosController;

    @Mock
    private IRecaudosService recaudosService;

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
        String result = recaudosController.consultarRecaudos(new ConsultarRecaudoDto());
        assertNotNull(result);
    }

    @Test
    public void testConsultarValorSuccess() {
        when(recaudosService.consultarValor(any())).thenReturn(EXPECTED_RESULT);
        String result = recaudosController.consultarValor(new ConsultarValorDto());
        assertNotNull(result);
    }

    @Test
    public void testGuardarSuccess() {
        when(recaudosService.guardar(any())).thenReturn(EXPECTED_RESULT);
        String result = recaudosController.guardar(new GuardarDto());
        assertNotNull(result);
    }

    @Test
    public void testConsultarSuccess() {
        when(recaudosService.consultarRecaudos(any())).thenReturn(EXPECTED_RESULT);
        String result = recaudosController.consultar(new ConsultarRecaudoDto());
        assertNotNull(result);
    }
}
