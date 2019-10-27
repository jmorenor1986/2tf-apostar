package com.dostf.apostar.controllers;

import com.dostf.apostar.dtos.sorteos.SorteosDto;
import com.dostf.apostar.services.ISorteosService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertNotNull;

public class SorteosControllerTest {
  private static final String EXPECTED_RESULT = "{\"result\": \"result\"}";
  private SorteosController sorteosController;
  @Mock
  private ISorteosService sorteosService;
  
  @Before
  public void setup(){
    MockitoAnnotations.initMocks(this);
    this.sorteosController = new SorteosController(this.sorteosService);
  }
  
  @Test
  public void testConsultaSorteosSuccess(){
    Mockito.when(this.sorteosController.consultarSorteos(Mockito.any())).thenReturn(EXPECTED_RESULT);
    String result = this.sorteosController.consultarSorteos(new SorteosDto());
    assertNotNull(result);
  }
  
  @Test
  public void testConsultaSorteosSemanal(){
    Mockito.when(this.sorteosController.consultarSorteosSemanal(Mockito.any())).thenReturn(EXPECTED_RESULT);
    String result = this.sorteosController.consultarSorteosSemanal(new SorteosDto());
    assertNotNull(result);
  }
  
}
