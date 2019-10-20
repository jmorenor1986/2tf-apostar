package com.dostf.apostar.services.impl;

import com.dostf.apostar.common.exceptions.MandatoryFieldsMissingException;
import com.dostf.apostar.common.exceptions.SecureDistribuidorException;
import com.dostf.apostar.config.properties.DistribuidorProperties;
import com.dostf.apostar.config.properties.OperacionesProperties;
import com.dostf.apostar.config.properties.RecaudosProperties;
import com.dostf.apostar.dtos.common.DepartamentoDto;
import com.dostf.apostar.dtos.recaudos.RecaudoDto;
import com.dostf.apostar.services.IRecaudosService;
import com.dostf.apostar.services.client.RestTemplateService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

public class RecaudosServiceTest {
  private static final String EXPECTED_RESULT = "{\"result\": \"result\"}";
  private static final String URI_CONSULTAR_DEPARTAMENTOS = "/consultar-departamentos";
  private static final String URI_CONSULTAR_RECAUDOS = "/consutlar-recaudos";
  private static final String URI_RECAUDO = "/recaudo";
  private static final String URI_BASE = "http://172.17.254.17/web-services-seta-apostar/api";
  private static final long TRANSACCION_DISTRIBUIDOR_ID = 0L;
  private static final String CODIGO_SUBPRODUCTO = "CODIGO_SUBPRODUCTO";
  private static final String CODIGO_DEPARTAMENTO = "CODIGO_DEPARTAMENTO";
  RecaudosService recaudosService;

  @Mock
  DistribuidorProperties distribuidor;
  @Mock
  private OperacionesProperties properties;
  @Mock
  private RestTemplateService restTemplateService;
  @Mock
  private RecaudosProperties recaudosProperties;

  private String URI_BASE_SERVICE = URI_BASE + URI_RECAUDO;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
    when(properties.getRecaudos()).thenReturn(recaudosProperties);
    when(properties.getUrlBase()).thenReturn(URI_BASE);
    when(recaudosProperties.getUrlBase()).thenReturn(URI_RECAUDO);
    recaudosService = new RecaudosService(restTemplateService, properties, distribuidor);
  }

  @Test
  public void testConsultarDepartamentosSuccess() {
    final String uri = URI_BASE_SERVICE + URI_CONSULTAR_DEPARTAMENTOS;
    when(recaudosProperties.getUrlConsultarDepartamentos()).thenReturn(URI_CONSULTAR_DEPARTAMENTOS);
    doReturn(Optional.of(EXPECTED_RESULT)).when(restTemplateService).post(eq(uri), any());
    String result = recaudosService.consultarDepartamentos(TRANSACCION_DISTRIBUIDOR_ID);
    Assert.assertNotNull(result);
  }

  @Test(expected = ResponseStatusException.class)
  public void testConsultarDepartamentosNotFoundID() {
    final String uri = URI_BASE_SERVICE + URI_CONSULTAR_DEPARTAMENTOS;
    when(recaudosProperties.getUrlConsultarDepartamentos()).thenReturn(URI_CONSULTAR_DEPARTAMENTOS);
    doReturn(Optional.empty()).when(restTemplateService).post(eq(uri), notNull());
    String result = recaudosService.consultarDepartamentos(TRANSACCION_DISTRIBUIDOR_ID);
    Assert.assertNotNull(result);
  }

  @Test(expected = MandatoryFieldsMissingException.class)
  public void testConsultarDepartamentosNull() {
    when(recaudosProperties.getUrlConsultarDepartamentos()).thenReturn(URI_CONSULTAR_DEPARTAMENTOS);
    String result = recaudosService.consultarDepartamentos(null);
  }


  @Test(expected = SecureDistribuidorException.class)
  public void testConsultarDepartamentosMissingDistribuidor() {
    when(recaudosProperties.getUrlConsultarDepartamentos()).thenReturn(URI_CONSULTAR_DEPARTAMENTOS);
    IRecaudosService recaudosServiceTestNull = new RecaudosService(restTemplateService, properties, null);
    String result = recaudosServiceTestNull.consultarDepartamentos(TRANSACCION_DISTRIBUIDOR_ID);
  }

  @Test(expected = SecureDistribuidorException.class)
  public void testConsultarRecaudosMissingDistribuidor() {
    when(recaudosProperties.getUrlConsultarRecaudos()).thenReturn(URI_CONSULTAR_RECAUDOS);
    IRecaudosService recaudosServiceTestNull = new RecaudosService(restTemplateService, properties, null);
    String result = recaudosServiceTestNull.consultarRecaudos(new RecaudoDto());
  }

  @Test
  public void testConsultarRecaudosSuccess() {
    final String uri = URI_BASE_SERVICE + URI_CONSULTAR_RECAUDOS;
    RecaudoDto dto = new RecaudoDto();
    DepartamentoDto departamentoDto = new DepartamentoDto();
    departamentoDto.setCodigo(CODIGO_DEPARTAMENTO);
    dto.setTransaccionDistribuidorId(TRANSACCION_DISTRIBUIDOR_ID);
    dto.setDepartamento(departamentoDto);
    when(recaudosProperties.getUrlConsultarRecaudos()).thenReturn(URI_CONSULTAR_RECAUDOS);
    doReturn(Optional.of(EXPECTED_RESULT)).when(restTemplateService).post(eq(uri), notNull());
    String result = recaudosService.consultarRecaudos(dto);
    Assert.assertNotNull(result);
  }

  @Test(expected = ResponseStatusException.class)
  public void testConsultarRecaudosNull() {
    Object result = recaudosService.consultarRecaudos(null);
  }

  @Test(expected = MandatoryFieldsMissingException.class)
  public void testConsultarRecaudosMissingTransaccionDistribuidorID() {
    when(recaudosProperties.getUrlConsultarRecaudos()).thenReturn(URI_CONSULTAR_RECAUDOS);
    RecaudoDto dto = new RecaudoDto();
    String result = recaudosService.consultarRecaudos(dto);
  }

  @Test(expected = MandatoryFieldsMissingException.class)
  public void testConsultarRecaudosWhenDepartamentoIsNull() {
    final String uri = URI_BASE_SERVICE + URI_CONSULTAR_RECAUDOS;
    RecaudoDto dto = new RecaudoDto();
    dto.setTransaccionDistribuidorId(TRANSACCION_DISTRIBUIDOR_ID);
    when(recaudosProperties.getUrlConsultarRecaudos()).thenReturn(URI_CONSULTAR_RECAUDOS);
    doReturn(Optional.empty()).when(restTemplateService).post(eq(uri), notNull());
    String result = recaudosService.consultarRecaudos(dto);
    Assert.assertNotNull(result);
    Assert.assertEquals(EXPECTED_RESULT, result);
  }

  @Test(expected = MandatoryFieldsMissingException.class)
  public void testConsultarRecaudosWhenDepartamentoCodeIsNull() {
    final String uri = URI_BASE_SERVICE + URI_CONSULTAR_RECAUDOS;
    RecaudoDto dto = new RecaudoDto();
    dto.setTransaccionDistribuidorId(TRANSACCION_DISTRIBUIDOR_ID);
    dto.setDepartamento(new DepartamentoDto());
    when(recaudosProperties.getUrlConsultarRecaudos()).thenReturn(URI_CONSULTAR_RECAUDOS);
    doReturn(Optional.empty()).when(restTemplateService).post(eq(uri), notNull());
    String result = recaudosService.consultarRecaudos(dto);
    Assert.assertNotNull(result);
    Assert.assertEquals(EXPECTED_RESULT, result);
  }

  @Test(expected = ResponseStatusException.class)
  public void testConsultarRecaudosNotFoundID() {
    final String uri = URI_BASE_SERVICE + URI_CONSULTAR_RECAUDOS;
    RecaudoDto dto = new RecaudoDto();
    DepartamentoDto departamentoDto = new DepartamentoDto();
    departamentoDto.setCodigo(CODIGO_DEPARTAMENTO);
    dto.setTransaccionDistribuidorId(TRANSACCION_DISTRIBUIDOR_ID);
    dto.setDepartamento(departamentoDto);
    when(recaudosProperties.getUrlConsultarRecaudos()).thenReturn(URI_CONSULTAR_RECAUDOS);
    doReturn(Optional.empty()).when(restTemplateService).post(eq(uri), notNull());
    String result = recaudosService.consultarRecaudos(dto);
    Assert.assertNotNull(result);
    Assert.assertEquals(EXPECTED_RESULT, result);
  }

}
