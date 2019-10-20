package com.dostf.apostar.services.impl;

import com.dostf.apostar.common.exceptions.MandatoryFieldsMissingException;
import com.dostf.apostar.common.exceptions.SecureDistribuidorException;
import com.dostf.apostar.config.properties.DistribuidorProperties;
import com.dostf.apostar.config.properties.OperacionesProperties;
import com.dostf.apostar.config.properties.RecargasProperties;
import com.dostf.apostar.dtos.recargas.RecargarDto;
import com.dostf.apostar.services.IRecargasService;
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

public class RecargasServiceTest {
    private static final String EXPECTED_RESULT = "{\"result\": \"result\"}";
    private static final String URI_CONSULTAR_SUBPRODUCTOS = "/consultar-subproductos";
    private static final String URI_CONSULTAR_RECARGAS = "/consutlar-recargas";
    private static final String URI_RECARGA = "/recarga";
    private static final String URI_BASE = "http://172.17.254.17/web-services-seta-apostar/api";
    private static final long TRANSACCION_DISTRIBUIDOR_ID = 0L;
    private static final String CODIGO_SUBPRODUCTO = "CODIGO_SUBPRODUCTO";
    private static final String NUMERO_CELULAR = "3004139580";
    private static final double VALOR_A_RECARGAR = 100.0D;
    RecargasService recargasService;

    @Mock
    DistribuidorProperties distribuidor;
    @Mock
    private OperacionesProperties properties;
    @Mock
    private RestTemplateService restTemplateService;
    @Mock
    private RecargasProperties recargasProperties;

    private String URI_BASE_SERVICE = URI_BASE + URI_RECARGA;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        when(properties.getRecargas()).thenReturn(recargasProperties);
        when(properties.getUrlBase()).thenReturn(URI_BASE);
        when(recargasProperties.getUrlBase()).thenReturn(URI_RECARGA);
        recargasService = new RecargasService(restTemplateService, properties, distribuidor);
    }

    @Test
    public void testConsultarSubproductoSuccess() {
        final String uri = URI_BASE_SERVICE + URI_CONSULTAR_SUBPRODUCTOS;
        when(recargasProperties.getUrlConsultarSubproducto()).thenReturn(URI_CONSULTAR_SUBPRODUCTOS);
        doReturn(Optional.of(EXPECTED_RESULT)).when(restTemplateService).post(eq(uri), any());
        String result = recargasService.consultarSubproducto(TRANSACCION_DISTRIBUIDOR_ID);
        Assert.assertNotNull(result);
    }

    @Test(expected = ResponseStatusException.class)
    public void testConsultarSubproductoNotFoundID() {
        final String uri = URI_BASE_SERVICE + URI_CONSULTAR_SUBPRODUCTOS;
        when(recargasProperties.getUrlConsultarSubproducto()).thenReturn(URI_CONSULTAR_SUBPRODUCTOS);
        doReturn(Optional.empty()).when(restTemplateService).post(eq(uri), notNull());
        String result = recargasService.consultarSubproducto(TRANSACCION_DISTRIBUIDOR_ID);
        Assert.assertNotNull(result);
    }

    @Test
    public void testConsultarSubproductoTransactionDistrubidorIdIsNullSuccess() {
        final String uri = URI_BASE_SERVICE + URI_CONSULTAR_SUBPRODUCTOS;
        when(recargasProperties.getUrlConsultarSubproducto()).thenReturn(URI_CONSULTAR_SUBPRODUCTOS);
        doReturn(Optional.of(EXPECTED_RESULT)).when(restTemplateService).post(eq(uri), notNull());
        String result = recargasService.consultarSubproducto(TRANSACCION_DISTRIBUIDOR_ID);
    }

    @Test(expected = SecureDistribuidorException.class)
    public void testConsultarSubproductoMissingDistribuidor() {
        IRecargasService recargasServiceTestNull = new RecargasService(restTemplateService, properties, null);
        String result = recargasServiceTestNull.consultarSubproducto(TRANSACCION_DISTRIBUIDOR_ID);
    }

    @Test(expected = SecureDistribuidorException.class)
    public void testRecargarMissingDistribuidor() {
        RecargasService recargasServiceTestNull = new RecargasService(restTemplateService, properties, null);
        String result = recargasServiceTestNull.recargar(new RecargarDto());
    }

    @Test
    public void testRecargarSuccess() {
        final String uri = URI_BASE_SERVICE + URI_CONSULTAR_RECARGAS;
        RecargarDto dto = new RecargarDto();
        dto.setTransaccionDistribuidorId(TRANSACCION_DISTRIBUIDOR_ID);
        dto.setCodigoSubproducto(CODIGO_SUBPRODUCTO);
        dto.setNumero(NUMERO_CELULAR);
        dto.setValor(VALOR_A_RECARGAR);
        when(recargasProperties.getUrlRecargar()).thenReturn(URI_CONSULTAR_RECARGAS);
        doReturn(Optional.of(EXPECTED_RESULT)).when(restTemplateService).post(eq(uri), notNull());
        String result = recargasService.recargar(dto);
        Assert.assertNotNull(result);
    }

    @Test
    public void testRecargarSuccessWithoutTransaccionDistribuidorId() {
        final String uri = URI_BASE_SERVICE + URI_CONSULTAR_RECARGAS;
        RecargarDto dto = new RecargarDto();
        dto.setCodigoSubproducto(CODIGO_SUBPRODUCTO);
        dto.setNumero(NUMERO_CELULAR);
        dto.setValor(VALOR_A_RECARGAR);
        when(recargasProperties.getUrlRecargar()).thenReturn(URI_CONSULTAR_RECARGAS);
        doReturn(Optional.of(EXPECTED_RESULT)).when(restTemplateService).post(eq(uri), notNull());
        String result = recargasService.recargar(dto);
        Assert.assertNotNull(result);
    }

    @Test(expected = ResponseStatusException.class)
    public void testRecargarWhenDtoIsNull() {
        Object result = recargasService.recargar(null);
    }

    @Test(expected = MandatoryFieldsMissingException.class)
    public void testRecargarMissingNumero() {
        RecargarDto dto = new RecargarDto();
        dto.setCodigoSubproducto(CODIGO_SUBPRODUCTO);
        String result = recargasService.recargar(dto);
    }

    @Test(expected = MandatoryFieldsMissingException.class)
    public void testRecargarMissingValor() {
        RecargarDto dto = new RecargarDto();
        dto.setCodigoSubproducto(CODIGO_SUBPRODUCTO);
        dto.setNumero(NUMERO_CELULAR);
        String result = recargasService.recargar(dto);
    }

    @Test(expected = MandatoryFieldsMissingException.class)
    public void testRecargarMissingCodigoSubproducto() {
        RecargarDto dto = new RecargarDto();
        dto.setNumero(NUMERO_CELULAR);
        dto.setValor(VALOR_A_RECARGAR);
        String result = recargasService.recargar(dto);
    }

    @Test(expected = ResponseStatusException.class)
    public void testRecargarNotFoundID() {
        final String uri = URI_BASE_SERVICE + URI_CONSULTAR_RECARGAS;
        RecargarDto dto = new RecargarDto();
        dto.setTransaccionDistribuidorId(TRANSACCION_DISTRIBUIDOR_ID);
        dto.setCodigoSubproducto(CODIGO_SUBPRODUCTO);
        dto.setNumero(NUMERO_CELULAR);
        dto.setValor(VALOR_A_RECARGAR);
        when(recargasProperties.getUrlRecargar()).thenReturn(URI_CONSULTAR_RECARGAS);
        doReturn(Optional.empty()).when(restTemplateService).post(eq(uri), notNull());
        String result = recargasService.recargar(dto);
        Assert.assertNotNull(result);
    }

}
