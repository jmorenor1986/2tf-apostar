package com.dostf.apostar.controllers;

import com.dostf.apostar.dtos.paquetesMoviles.GuardarPaqueteMovilDto;
import com.dostf.apostar.services.IPaquetesMovilesService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class PaquetesMovilesControllerTest {
    private static final String EXPECTED_RESULT = "{\"result\": \"result\"}";
    private PaquetesMovilesController paquetesMovilesController;
    @Mock
    private IPaquetesMovilesService paquetesMovilesService;
    @Mock
    private GuardarPaqueteMovilDto guardarPaqueteMovilDto;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        paquetesMovilesController = new PaquetesMovilesController(paquetesMovilesService);
    }

    @Test
    public void testConsultaSubProductos() {
        Mockito.when(paquetesMovilesService.consultarSubProductos(null)).thenReturn(EXPECTED_RESULT);
        String result = paquetesMovilesController.consultaSubProductos(null);
        Assert.assertNotNull(result);
    }

    @Test
    public void testConsultarPaquetes() {
        Mockito.when(paquetesMovilesService.consultarPaquetes(1l, "")).thenReturn(EXPECTED_RESULT);
        String result = paquetesMovilesController.consultarPaquetes(1L, "");
        Assert.assertNotNull(result);
    }

    @Test
    public void testGuardarPaquetesMoviles() {
        Mockito.when(paquetesMovilesService.guardarPaquetesMoviles(guardarPaqueteMovilDto)).thenReturn(EXPECTED_RESULT);
        String result = paquetesMovilesController.guardaPaquetesMoviles(guardarPaqueteMovilDto);
        Assert.assertNotNull(result);
    }

}
