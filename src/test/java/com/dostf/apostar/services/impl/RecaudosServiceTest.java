package com.dostf.apostar.services.impl;

import com.dostf.apostar.common.exceptions.MandatoryFieldsMissingException;
import com.dostf.apostar.common.exceptions.SecureDistribuidorException;
import com.dostf.apostar.config.properties.DistribuidorProperties;
import com.dostf.apostar.config.properties.OperacionesProperties;
import com.dostf.apostar.config.properties.RecaudosProperties;
import com.dostf.apostar.dtos.common.DepartamentoDto;
import com.dostf.apostar.dtos.common.RecaudoDto;
import com.dostf.apostar.dtos.common.RecaudoGuardarDto;
import com.dostf.apostar.dtos.recaudos.ConsultarRecaudoDto;
import com.dostf.apostar.dtos.recaudos.ConsultarValorDto;
import com.dostf.apostar.dtos.recaudos.GuardarDto;
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
    private static final String URI_GUARDAR = "/guardar";
    private static final String URI_CONSULTAR_DEPARTAMENTOS = "/consultar-departamentos";
    private static final String URI_CONSULTAR_RECAUDOS = "/consutlar-recaudos";
    private static final String URI_CONSULTAR_VALOR = "/consutlar-valor";
    private static final String URI_RECAUDO = "/recaudo";
    private static final String URI_BASE = "http://172.17.254.17/web-services-seta-apostar/api";
    private static final long TRANSACCION_DISTRIBUIDOR_ID = 0L;
    private static final String CODIGO_SUBPRODUCTO = "CODIGO_SUBPRODUCTO";
    private static final String CODIGO = "CODIGO_DEPARTAMENTO";
    private static final String REFERENCIA = "REFERENCIA_DTO";
    private static final double VALOR = 1000D;
    public static final String URI_CONSULTAR = "/consultar";

    private RecaudosService recaudosService;

    @Mock
    private DistribuidorProperties distribuidor;
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

    // Consultar Recaudo
    @Test(expected = SecureDistribuidorException.class)
    public void testConsultarRecaudosMissingDistribuidor() {
        when(recaudosProperties.getUrlConsultarRecaudos()).thenReturn(URI_CONSULTAR_RECAUDOS);
        IRecaudosService recaudosServiceTestNull = new RecaudosService(restTemplateService, properties, null);
        String result = recaudosServiceTestNull.consultarRecaudos(new ConsultarRecaudoDto());
    }

    @Test
    public void testConsultarRecaudosSuccess() {
        final String uri = URI_BASE_SERVICE + URI_CONSULTAR_RECAUDOS;
        ConsultarRecaudoDto dto = new ConsultarRecaudoDto();
        DepartamentoDto departamentoDto = new DepartamentoDto();
        departamentoDto.setCodigo(CODIGO);
        dto.setTransaccionDistribuidorId(TRANSACCION_DISTRIBUIDOR_ID);
        dto.setDepartamento(departamentoDto);
        when(recaudosProperties.getUrlConsultarRecaudos()).thenReturn(URI_CONSULTAR_RECAUDOS);
        doReturn(Optional.of(EXPECTED_RESULT)).when(restTemplateService).post(eq(uri), notNull());
        String result = recaudosService.consultarRecaudos(dto);
        Assert.assertNotNull(result);
    }

    @Test(expected = ResponseStatusException.class)
    public void testConsultarRecaudosNull() {
        String result = recaudosService.consultarRecaudos(null);
    }

    @Test(expected = MandatoryFieldsMissingException.class)
    public void testConsultarRecaudosMissingTransaccionDistribuidorID() {
        when(recaudosProperties.getUrlConsultarRecaudos()).thenReturn(URI_CONSULTAR_RECAUDOS);
        ConsultarRecaudoDto dto = new ConsultarRecaudoDto();
        String result = recaudosService.consultarRecaudos(dto);
    }

    @Test(expected = MandatoryFieldsMissingException.class)
    public void testConsultarRecaudosWhenDepartamentoIsNull() {
        final String uri = URI_BASE_SERVICE + URI_CONSULTAR_RECAUDOS;
        ConsultarRecaudoDto dto = new ConsultarRecaudoDto();
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
        ConsultarRecaudoDto dto = new ConsultarRecaudoDto();
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
        ConsultarRecaudoDto dto = new ConsultarRecaudoDto();
        DepartamentoDto departamentoDto = new DepartamentoDto();
        departamentoDto.setCodigo(CODIGO);
        dto.setTransaccionDistribuidorId(TRANSACCION_DISTRIBUIDOR_ID);
        dto.setDepartamento(departamentoDto);
        when(recaudosProperties.getUrlConsultarRecaudos()).thenReturn(URI_CONSULTAR_RECAUDOS);
        doReturn(Optional.empty()).when(restTemplateService).post(eq(uri), notNull());
        String result = recaudosService.consultarRecaudos(dto);
        Assert.assertNotNull(result);
        Assert.assertEquals(EXPECTED_RESULT, result);
    }

    // Consultar Valor
    @Test
    public void testConsultarValorSuccess() {
        final String uri = URI_BASE_SERVICE + URI_CONSULTAR_VALOR;
        ConsultarValorDto dto = new ConsultarValorDto();
        DepartamentoDto departamentoDto = new DepartamentoDto();
        RecaudoDto recaudoDto = new RecaudoDto();
        recaudoDto.setCodigo(CODIGO);
        recaudoDto.setReferencia(REFERENCIA);
        dto.setRecaudo(recaudoDto);
        departamentoDto.setCodigo(CODIGO);
        dto.setTransaccionDistribuidorId(TRANSACCION_DISTRIBUIDOR_ID);
        dto.setDepartamento(departamentoDto);
        when(recaudosProperties.getUrlConsultarValor()).thenReturn(URI_CONSULTAR_VALOR);
        doReturn(Optional.of(EXPECTED_RESULT)).when(restTemplateService).post(eq(uri), notNull());
        String result = recaudosService.consultarValor(dto);
        Assert.assertNotNull(result);
    }

    @Test(expected = ResponseStatusException.class)
    public void testConsultarValorNull() {
        String result = recaudosService.consultarValor(null);
    }

    @Test(expected = MandatoryFieldsMissingException.class)
    public void testConsultarValorMissingTransaccionDistribuidorID() {
        when(recaudosProperties.getUrlConsultarValor()).thenReturn(URI_CONSULTAR_VALOR);
        ConsultarValorDto dto = new ConsultarValorDto();
        String result = recaudosService.consultarValor(dto);
    }

    @Test(expected = MandatoryFieldsMissingException.class)
    public void testConsultarValorWhenDepartamentoIsNull() {
        final String uri = URI_BASE_SERVICE + URI_CONSULTAR_VALOR;
        ConsultarValorDto dto = new ConsultarValorDto();
        dto.setTransaccionDistribuidorId(TRANSACCION_DISTRIBUIDOR_ID);
        when(recaudosProperties.getUrlConsultarValor()).thenReturn(URI_CONSULTAR_VALOR);
        doReturn(Optional.empty()).when(restTemplateService).post(eq(uri), notNull());
        String result = recaudosService.consultarValor(dto);
        Assert.assertNotNull(result);
        Assert.assertEquals(EXPECTED_RESULT, result);
    }

    @Test(expected = MandatoryFieldsMissingException.class)
    public void testConsultarValorWhenDepartamentoCodeIsNull() {
        final String uri = URI_BASE_SERVICE + URI_CONSULTAR_VALOR;
        ConsultarValorDto dto = new ConsultarValorDto();
        dto.setTransaccionDistribuidorId(TRANSACCION_DISTRIBUIDOR_ID);
        dto.setDepartamento(new DepartamentoDto());
        when(recaudosProperties.getUrlConsultarValor()).thenReturn(URI_CONSULTAR_VALOR);
        doReturn(Optional.empty()).when(restTemplateService).post(eq(uri), notNull());
        String result = recaudosService.consultarValor(dto);
        Assert.assertNotNull(result);
        Assert.assertEquals(EXPECTED_RESULT, result);
    }

    @Test(expected = ResponseStatusException.class)
    public void testConsultarValorNotFoundID() {
        final String uri = URI_BASE_SERVICE + URI_CONSULTAR_VALOR;
        ConsultarValorDto dto = new ConsultarValorDto();
        DepartamentoDto departamentoDto = new DepartamentoDto();
        RecaudoDto recaudoDto = new RecaudoDto();
        recaudoDto.setCodigo(CODIGO);
        recaudoDto.setReferencia(REFERENCIA);
        dto.setRecaudo(recaudoDto);
        departamentoDto.setCodigo(CODIGO);
        dto.setTransaccionDistribuidorId(TRANSACCION_DISTRIBUIDOR_ID);
        dto.setDepartamento(departamentoDto);
        when(recaudosProperties.getUrlConsultarValor()).thenReturn(URI_CONSULTAR_VALOR);
        doReturn(Optional.empty()).when(restTemplateService).post(eq(uri), notNull());
        String result = recaudosService.consultarValor(dto);
        Assert.assertNotNull(result);
        Assert.assertEquals(EXPECTED_RESULT, result);
    }

    @Test(expected = MandatoryFieldsMissingException.class)
    public void testConsultarValorExceptionRecaudoDtoNull() {
        final String uri = URI_BASE_SERVICE + URI_CONSULTAR_VALOR;
        ConsultarValorDto dto = new ConsultarValorDto();

        DepartamentoDto departamentoDto = new DepartamentoDto();
        departamentoDto.setCodigo(CODIGO);
        dto.setTransaccionDistribuidorId(TRANSACCION_DISTRIBUIDOR_ID);
        dto.setDepartamento(departamentoDto);
        when(recaudosProperties.getUrlConsultarValor()).thenReturn(URI_CONSULTAR_VALOR);
        doReturn(Optional.empty()).when(restTemplateService).post(eq(uri), notNull());
        String result = recaudosService.consultarValor(dto);
        Assert.assertNotNull(result);
        Assert.assertEquals(EXPECTED_RESULT, result);
    }

    @Test(expected = MandatoryFieldsMissingException.class)
    public void testConsultarValorExceptionReferenciaNull() {
        final String uri = URI_BASE_SERVICE + URI_CONSULTAR_VALOR;
        ConsultarValorDto dto = new ConsultarValorDto();
        RecaudoDto recaudoDto = new RecaudoDto();
        recaudoDto.setCodigo(CODIGO);
        DepartamentoDto departamentoDto = new DepartamentoDto();
        departamentoDto.setCodigo(CODIGO);
        dto.setTransaccionDistribuidorId(TRANSACCION_DISTRIBUIDOR_ID);
        dto.setDepartamento(departamentoDto);
        when(recaudosProperties.getUrlConsultarValor()).thenReturn(URI_CONSULTAR_VALOR);
        doReturn(Optional.empty()).when(restTemplateService).post(eq(uri), notNull());
        String result = recaudosService.consultarValor(dto);
        Assert.assertNotNull(result);
        Assert.assertEquals(EXPECTED_RESULT, result);
    }

    @Test(expected = MandatoryFieldsMissingException.class)
    public void testConsultarValorExceptionRecaudoCodigoNull() {
        final String uri = URI_BASE_SERVICE + URI_CONSULTAR_VALOR;
        ConsultarValorDto dto = new ConsultarValorDto();
        RecaudoDto recaudoDto = new RecaudoDto();
        recaudoDto.setReferencia(REFERENCIA);
        DepartamentoDto departamentoDto = new DepartamentoDto();
        departamentoDto.setCodigo(CODIGO);
        dto.setTransaccionDistribuidorId(TRANSACCION_DISTRIBUIDOR_ID);
        dto.setDepartamento(departamentoDto);
        when(recaudosProperties.getUrlConsultarValor()).thenReturn(URI_CONSULTAR_VALOR);
        doReturn(Optional.empty()).when(restTemplateService).post(eq(uri), notNull());
        recaudosService.consultarValor(dto);
    }

    // Guardar
    @Test
    public void testGuardarSuccess() {
        final String uri = URI_BASE_SERVICE + URI_GUARDAR;
        GuardarDto dto = new GuardarDto();
        DepartamentoDto departamentoDto = new DepartamentoDto();
        RecaudoGuardarDto recaudoDto = new RecaudoGuardarDto();
        recaudoDto.setCodigo(CODIGO);
        recaudoDto.setReferencia(REFERENCIA);
        recaudoDto.setValor(VALOR);
        dto.setRecaudo(recaudoDto);
        departamentoDto.setCodigo(CODIGO);
        dto.setTransaccionDistribuidorId(TRANSACCION_DISTRIBUIDOR_ID);
        dto.setDepartamento(departamentoDto);
        when(recaudosProperties.getUrlGuardar()).thenReturn(URI_GUARDAR);
        doReturn(Optional.of(EXPECTED_RESULT)).when(restTemplateService).post(eq(uri), notNull());
        String result = recaudosService.guardar(dto);
        Assert.assertNotNull(result);
    }

    @Test(expected = ResponseStatusException.class)
    public void testGuardarNull() {
        String result = recaudosService.guardar(null);
    }

    @Test(expected = MandatoryFieldsMissingException.class)
    public void testGuardarMissingTransaccionDistribuidorID() {
        when(recaudosProperties.getUrlGuardar()).thenReturn(URI_GUARDAR);
        GuardarDto dto = new GuardarDto();
        String result = recaudosService.guardar(dto);
    }

    @Test(expected = MandatoryFieldsMissingException.class)
    public void testGuardarWhenDepartamentoIsNull() {
        final String uri = URI_BASE_SERVICE + URI_GUARDAR;
        GuardarDto dto = new GuardarDto();
        dto.setTransaccionDistribuidorId(TRANSACCION_DISTRIBUIDOR_ID);
        when(recaudosProperties.getUrlGuardar()).thenReturn(URI_GUARDAR);
        doReturn(Optional.empty()).when(restTemplateService).post(eq(uri), notNull());
        String result = recaudosService.guardar(dto);
        Assert.assertNotNull(result);
        Assert.assertEquals(EXPECTED_RESULT, result);
    }

    @Test(expected = MandatoryFieldsMissingException.class)
    public void testGuardarWhenDepartamentoCodeIsNull() {
        final String uri = URI_BASE_SERVICE + URI_GUARDAR;
        GuardarDto dto = new GuardarDto();
        dto.setTransaccionDistribuidorId(TRANSACCION_DISTRIBUIDOR_ID);
        dto.setDepartamento(new DepartamentoDto());
        when(recaudosProperties.getUrlGuardar()).thenReturn(URI_GUARDAR);
        doReturn(Optional.empty()).when(restTemplateService).post(eq(uri), notNull());
        String result = recaudosService.guardar(dto);
        Assert.assertNotNull(result);
        Assert.assertEquals(EXPECTED_RESULT, result);
    }

    @Test(expected = ResponseStatusException.class)
    public void testGuardarNotFoundID() {
        final String uri = URI_BASE_SERVICE + URI_GUARDAR;
        GuardarDto dto = new GuardarDto();
        DepartamentoDto departamentoDto = new DepartamentoDto();
        RecaudoGuardarDto recaudoDto = new RecaudoGuardarDto();
        recaudoDto.setCodigo(CODIGO);
        recaudoDto.setReferencia(REFERENCIA);
        recaudoDto.setValor(VALOR);
        dto.setRecaudo(recaudoDto);
        departamentoDto.setCodigo(CODIGO);
        dto.setTransaccionDistribuidorId(TRANSACCION_DISTRIBUIDOR_ID);
        dto.setDepartamento(departamentoDto);
        when(recaudosProperties.getUrlGuardar()).thenReturn(URI_GUARDAR);
        doReturn(Optional.empty()).when(restTemplateService).post(eq(uri), notNull());
        String result = recaudosService.guardar(dto);
        Assert.assertNotNull(result);
        Assert.assertEquals(EXPECTED_RESULT, result);
    }

    @Test(expected = MandatoryFieldsMissingException.class)
    public void testGuardarExceptionRecaudoGuardarDtoNull() {
        final String uri = URI_BASE_SERVICE + URI_GUARDAR;
        GuardarDto dto = new GuardarDto();

        DepartamentoDto departamentoDto = new DepartamentoDto();
        departamentoDto.setCodigo(CODIGO);
        dto.setTransaccionDistribuidorId(TRANSACCION_DISTRIBUIDOR_ID);
        dto.setDepartamento(departamentoDto);
        when(recaudosProperties.getUrlGuardar()).thenReturn(URI_GUARDAR);
        doReturn(Optional.empty()).when(restTemplateService).post(eq(uri), notNull());
        String result = recaudosService.guardar(dto);
        Assert.assertNotNull(result);
        Assert.assertEquals(EXPECTED_RESULT, result);
    }

    @Test(expected = MandatoryFieldsMissingException.class)
    public void testGuardarExceptionReferenciaNull() {
        final String uri = URI_BASE_SERVICE + URI_GUARDAR;
        GuardarDto dto = new GuardarDto();
        RecaudoGuardarDto recaudoDto = new RecaudoGuardarDto();
        recaudoDto.setCodigo(CODIGO);
        DepartamentoDto departamentoDto = new DepartamentoDto();
        departamentoDto.setCodigo(CODIGO);
        dto.setTransaccionDistribuidorId(TRANSACCION_DISTRIBUIDOR_ID);
        dto.setDepartamento(departamentoDto);
        when(recaudosProperties.getUrlGuardar()).thenReturn(URI_GUARDAR);
        doReturn(Optional.empty()).when(restTemplateService).post(eq(uri), notNull());
        String result = recaudosService.guardar(dto);
        Assert.assertNotNull(result);
        Assert.assertEquals(EXPECTED_RESULT, result);
    }

    @Test(expected = MandatoryFieldsMissingException.class)
    public void testGuardarExceptionRecaudoCodigoNull() {
        final String uri = URI_BASE_SERVICE + URI_GUARDAR;
        GuardarDto dto = new GuardarDto();
        RecaudoGuardarDto recaudoDto = new RecaudoGuardarDto();
        recaudoDto.setReferencia(REFERENCIA);
        DepartamentoDto departamentoDto = new DepartamentoDto();
        departamentoDto.setCodigo(CODIGO);
        dto.setTransaccionDistribuidorId(TRANSACCION_DISTRIBUIDOR_ID);
        dto.setDepartamento(departamentoDto);
        when(recaudosProperties.getUrlGuardar()).thenReturn(URI_GUARDAR);
        doReturn(Optional.empty()).when(restTemplateService).post(eq(uri), notNull());
        recaudosService.guardar(dto);
    }

    @Test(expected = MandatoryFieldsMissingException.class)
    public void testGuardarExceptionRecaudoValorNull() {
        final String uri = URI_BASE_SERVICE + URI_GUARDAR;
        GuardarDto dto = new GuardarDto();
        RecaudoGuardarDto recaudoDto = new RecaudoGuardarDto();
        recaudoDto.setReferencia(REFERENCIA);
        recaudoDto.setCodigo(CODIGO);
        DepartamentoDto departamentoDto = new DepartamentoDto();
        departamentoDto.setCodigo(CODIGO);
        dto.setTransaccionDistribuidorId(TRANSACCION_DISTRIBUIDOR_ID);
        dto.setDepartamento(departamentoDto);
        when(recaudosProperties.getUrlGuardar()).thenReturn(URI_GUARDAR);
        doReturn(Optional.empty()).when(restTemplateService).post(eq(uri), notNull());
        recaudosService.guardar(dto);
    }

    // Consultar
    @Test(expected = SecureDistribuidorException.class)
    public void testConsultarMissingDistribuidor() {
        when(recaudosProperties.getUrlConsultar()).thenReturn(URI_CONSULTAR);
        IRecaudosService recaudosServiceTestNull = new RecaudosService(restTemplateService, properties, null);
        recaudosServiceTestNull.consultar(new ConsultarRecaudoDto());
    }

    @Test
    public void testConsultarSuccess() {
        final String uri = URI_BASE_SERVICE + URI_CONSULTAR;
        ConsultarRecaudoDto dto = new ConsultarRecaudoDto();
        DepartamentoDto departamentoDto = new DepartamentoDto();
        departamentoDto.setCodigo(CODIGO);
        dto.setTransaccionDistribuidorId(TRANSACCION_DISTRIBUIDOR_ID);
        dto.setDepartamento(departamentoDto);
        when(recaudosProperties.getUrlConsultar()).thenReturn(URI_CONSULTAR);
        doReturn(Optional.of(EXPECTED_RESULT)).when(restTemplateService).post(eq(uri), notNull());
        String result = recaudosService.consultar(dto);
        Assert.assertNotNull(result);
    }

    @Test(expected = ResponseStatusException.class)
    public void testConsultarNull() {
        recaudosService.consultar(null);
    }

    @Test(expected = MandatoryFieldsMissingException.class)
    public void testConsultarMissingTransaccionDistribuidorID() {
        when(recaudosProperties.getUrlConsultar()).thenReturn(URI_CONSULTAR);
        ConsultarRecaudoDto dto = new ConsultarRecaudoDto();
        recaudosService.consultar(dto);
    }

    @Test(expected = MandatoryFieldsMissingException.class)
    public void testConsultarWhenDepartamentoIsNull() {
        final String uri = URI_BASE_SERVICE + URI_CONSULTAR;
        ConsultarRecaudoDto dto = new ConsultarRecaudoDto();
        dto.setTransaccionDistribuidorId(TRANSACCION_DISTRIBUIDOR_ID);
        when(recaudosProperties.getUrlConsultar()).thenReturn(URI_CONSULTAR);
        doReturn(Optional.empty()).when(restTemplateService).post(eq(uri), notNull());
        String result = recaudosService.consultar(dto);
        Assert.assertNotNull(result);
        Assert.assertEquals(EXPECTED_RESULT, result);
    }

    @Test(expected = MandatoryFieldsMissingException.class)
    public void testConsultarWhenDepartamentoCodeIsNull() {
        final String uri = URI_BASE_SERVICE + URI_CONSULTAR;
        ConsultarRecaudoDto dto = new ConsultarRecaudoDto();
        dto.setTransaccionDistribuidorId(TRANSACCION_DISTRIBUIDOR_ID);
        dto.setDepartamento(new DepartamentoDto());
        when(recaudosProperties.getUrlConsultar()).thenReturn(URI_CONSULTAR);
        doReturn(Optional.empty()).when(restTemplateService).post(eq(uri), notNull());
        String result = recaudosService.consultar(dto);
        Assert.assertNotNull(result);
        Assert.assertEquals(EXPECTED_RESULT, result);
    }

    @Test(expected = ResponseStatusException.class)
    public void testConsultarNotFoundID() {
        final String uri = URI_BASE_SERVICE + URI_CONSULTAR;
        ConsultarRecaudoDto dto = new ConsultarRecaudoDto();
        DepartamentoDto departamentoDto = new DepartamentoDto();
        departamentoDto.setCodigo(CODIGO);
        dto.setTransaccionDistribuidorId(TRANSACCION_DISTRIBUIDOR_ID);
        dto.setDepartamento(departamentoDto);
        when(recaudosProperties.getUrlConsultar()).thenReturn(URI_CONSULTAR);
        doReturn(Optional.empty()).when(restTemplateService).post(eq(uri), notNull());
        String result = recaudosService.consultar(dto);
        Assert.assertNotNull(result);
        Assert.assertEquals(EXPECTED_RESULT, result);
    }
}
