package com.dostf.apostar.controllers;

import com.dostf.apostar.services.IBetPlayService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertNotNull;

public class BetPlayControllerTest {
    private static final String EXPECTED_RESULT = "{\"result\": \"result\"}";
    @Mock
    private IBetPlayService betPlayService;
    private BetPlayController betPlayController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        betPlayController = new BetPlayController(betPlayService);
    }

    @Test
    public void consultarSubProductos() {
        Mockito.when(betPlayService.consultarSubProductos(1l)).thenReturn(EXPECTED_RESULT);
        String result = betPlayController.consultarSubProductos(1L);
        assertNotNull(result);
    }

    @Test
    public void solicitarPin() {
        Mockito.when(betPlayService.solicitarPin(null)).thenReturn(EXPECTED_RESULT);
        String result = betPlayController.solicitarPin(null);
        assertNotNull(result);
    }

    @Test
    public void realizarRetiro(){
        Mockito.when(betPlayService.realizarRetiro(null)).thenReturn(EXPECTED_RESULT);
        String result = betPlayController.realizarRetiro(null);
        assertNotNull(result);
    }

    @Test
    public void realizarRecaudo(){
        Mockito.when(betPlayService.realizaRecaudo(null)).thenReturn(EXPECTED_RESULT);
        String result = betPlayController.realizaRecaudo(null);
        assertNotNull(result);
    }


}
