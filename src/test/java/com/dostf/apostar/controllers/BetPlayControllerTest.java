package com.dostf.apostar.controllers;

import com.dostf.apostar.dtos.betplay.BetPlayDto;
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
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        betPlayController = new BetPlayController(betPlayService);
    }

    @Test
    public void consultarSubProductos(){
        Mockito.when(betPlayService.consultarSubProductos(1l)).thenReturn(EXPECTED_RESULT);
        String result = betPlayController.consultarSubProductos(1L);
        assertNotNull(result);
    }
}
