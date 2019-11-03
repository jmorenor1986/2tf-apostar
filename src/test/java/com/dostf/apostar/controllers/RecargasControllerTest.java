package com.dostf.apostar.controllers;

import com.dostf.apostar.dtos.recargas.ConsultarParametrosDto;
import com.dostf.apostar.dtos.recargas.ConsultarParametrosPorSubproductoDto;
import com.dostf.apostar.dtos.recargas.ConsultarTopesDto;
import com.dostf.apostar.dtos.recargas.RecargarDto;
import com.dostf.apostar.services.IRecargasService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class RecargasControllerTest {
    private static final long TRANSACCION_DISTRIBUIDOR_ID = 0L;
    private static final String EXPECTED_RESULT = "{\"result\": \"result\"}";
    private RecargasController recargasController;

    @Mock
    private IRecargasService recargasService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        recargasController = new RecargasController(recargasService);
    }

    @Test
    public void testRecargarSuccess() {
        when(recargasService.recargar(any(RecargarDto.class))).thenReturn(EXPECTED_RESULT);
        String result = recargasController.recargar(new RecargarDto());
        Assert.assertNotNull(result);
    }

    @Test
    public void testConsultarSubproductoSuccess() {
        when(recargasService.consultarSubproducto(eq(TRANSACCION_DISTRIBUIDOR_ID))).thenReturn(EXPECTED_RESULT);
        String result = recargasController.consultarSubproducto(TRANSACCION_DISTRIBUIDOR_ID);
        Assert.assertNotNull(result);
    }

    @Test
    public void testConsultarParametrosSuccess() {
        when(recargasService.consultarParametros(any(ConsultarParametrosDto.class))).thenReturn(EXPECTED_RESULT);
        String result = recargasController.consultarParametros(new ConsultarParametrosDto());
        Assert.assertNotNull(result);
    }

    @Test
    public void testConsultarTopesSuccess() {
        when(recargasService.consultarTopes(any(ConsultarTopesDto.class))).thenReturn(EXPECTED_RESULT);
        String result = recargasController.consultarTopes(new ConsultarTopesDto());
        Assert.assertNotNull(result);
    }

    @Test
    public void testConsultarParametrosPorSubProductosSuccess() {
        when(recargasService.consultarParametrosPorSubproducto(any(ConsultarParametrosPorSubproductoDto.class))).thenReturn(EXPECTED_RESULT);
        String result = recargasController.consultarParametrosPorSubproducto(new ConsultarParametrosPorSubproductoDto());
        Assert.assertNotNull(result);
    }
}
