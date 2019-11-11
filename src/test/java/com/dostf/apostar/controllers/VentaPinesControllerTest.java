package com.dostf.apostar.controllers;

import com.dostf.apostar.dtos.ventapines.ConsultarPinesDto;
import com.dostf.apostar.dtos.ventapines.VenderPinesDto;
import com.dostf.apostar.services.IVentaPinesService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class VentaPinesControllerTest {
    private static final String EXPECTED_RESULT = "{\"result\": \"result\"}";
    private static final long TRANSACCION_DISTRIBUIDOR_ID = 0L;
    private VentaPinesController ventaPinesController;

    @Mock
    private IVentaPinesService ventaPinesService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ventaPinesController = new VentaPinesController(ventaPinesService);
    }

    @Test
    public void testConsultarParametrosSuccess() {
        when(ventaPinesService.consultarParametros(eq(TRANSACCION_DISTRIBUIDOR_ID))).thenReturn(EXPECTED_RESULT);
        String result = ventaPinesController.consultarParametros(TRANSACCION_DISTRIBUIDOR_ID);
        assertNotNull(result);
    }

    @Test
    public void testConsultarPinesSuccess() {
        when(ventaPinesService.consultarPines(any(ConsultarPinesDto.class))).thenReturn(EXPECTED_RESULT);
        String result = ventaPinesController.consultarPines(new ConsultarPinesDto());
        assertNotNull(result);
    }

    @Test
    public void testVenderPinesSuccess() {
        when(ventaPinesService.venderPines(any(VenderPinesDto.class))).thenReturn(EXPECTED_RESULT);
        String result = ventaPinesController.consultarPines(new ConsultarPinesDto());
        assertNotNull(result);
    }


}
