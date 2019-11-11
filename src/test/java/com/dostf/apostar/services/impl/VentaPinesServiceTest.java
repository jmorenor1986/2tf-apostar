package com.dostf.apostar.services.impl;

import com.dostf.apostar.common.exceptions.MandatoryFieldsMissingException;
import com.dostf.apostar.config.properties.DistribuidorProperties;
import com.dostf.apostar.config.properties.OperacionesProperties;
import com.dostf.apostar.config.properties.VentaPinesProperties;
import com.dostf.apostar.dtos.common.ServicioDto;
import com.dostf.apostar.dtos.common.SubproductoPinDto;
import com.dostf.apostar.dtos.common.SubproductoSetaDto;
import com.dostf.apostar.dtos.ventapines.ConsultarPinesDto;
import com.dostf.apostar.dtos.ventapines.VenderPinesDto;
import com.dostf.apostar.services.IVentaPinesService;
import com.dostf.apostar.services.IRestTemplateService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class VentaPinesServiceTest {

    private static final String URI_BASE = "http://172.17.254.17/web-services-seta-apostar/api";
    private static final String URI_VENTA_PINES = "/venta-pines";
    private static final String URI_CONSULTAR_PARAMETROS = "/consultar-parametros";


    private static final String URI_CONSULTAR_PINES = "/consultar-pines";
    private static final long ID_SUBPRODUCTO_PINES = 123L;
    private static final long TRANSACCION_DISTRIBUIDOR_ID = 0L;
    private static final String EXPECTED_RESULT = "{\"result\": \"result\"}";
    private static final String SERVICION_CONSULTAR = "SERVICION_CONSULTAR";
    private static final String URI_VENDER_PIN = "/vender-pin";

    private IVentaPinesService ventaPinesService;
    @Mock
    private IRestTemplateService restTemplateService;
    @Mock
    private VentaPinesProperties ventaPinesProperties;
    @Mock
    private OperacionesProperties operacionesProperties;
    @Mock
    private DistribuidorProperties distribuidorProperties;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(operacionesProperties.getVentaPines()).thenReturn(ventaPinesProperties);
        Mockito.when(operacionesProperties.getUrlBase()).thenReturn(URI_BASE);
        Mockito.when(ventaPinesProperties.getUrlBase()).thenReturn(VentaPinesServiceTest.URI_VENTA_PINES);
        Mockito.when(ventaPinesProperties.getUrlConsultarParametros()).thenReturn(URI_CONSULTAR_PARAMETROS);
        Mockito.when(ventaPinesProperties.getUrlConsultarPines()).thenReturn(URI_CONSULTAR_PINES);
        Mockito.when(ventaPinesProperties.getUrlVenderPin()).thenReturn(URI_VENDER_PIN);
        ventaPinesService = new VentaPinesService(restTemplateService, operacionesProperties, distribuidorProperties);
    }


    @Test
    public void testConsultarParametrosSuccess() {
        final String uri = URI_BASE.concat(URI_VENTA_PINES).concat(URI_CONSULTAR_PARAMETROS);
        doReturn(Optional.of(EXPECTED_RESULT)).when(restTemplateService).post(eq(uri), any());
        String result = ventaPinesService.consultarParametros(TRANSACCION_DISTRIBUIDOR_ID);
        Assert.assertNotNull(result);
    }

    @Test(expected = ResponseStatusException.class)
    public void testConsultarParametrosNotFoundId() {
        final String uri = URI_BASE.concat(URI_VENTA_PINES).concat(URI_CONSULTAR_PARAMETROS);
        doReturn(Optional.empty()).when(restTemplateService).post(eq(uri), notNull());
        ventaPinesService.consultarParametros(TRANSACCION_DISTRIBUIDOR_ID);
    }

    @Test(expected = MandatoryFieldsMissingException.class)
    public void testConsultarParametrosNull() {
        ventaPinesService.consultarParametros(null);
    }

    //Consultar Pin
    @Test
    public void testConsultarPinesSuccess() {
        final String uri = URI_BASE.concat(URI_VENTA_PINES).concat(URI_CONSULTAR_PINES);
        doReturn(Optional.of(EXPECTED_RESULT)).when(restTemplateService).post(eq(uri), any());
        ConsultarPinesDto dto = mock(ConsultarPinesDto.class);
        String result = ventaPinesService.consultarPines(dto);
        Assert.assertNotNull(result);
    }

    @Test(expected = ResponseStatusException.class)
    public void testConsultarPinesNull() {
        ventaPinesService.consultarPines(null);
    }

    @Test(expected = MandatoryFieldsMissingException.class)
    public void testConsultarRecaudosMissingTransaccionDistribuidorID() {
        ConsultarPinesDto dto = new ConsultarPinesDto();
        dto.setSubproductoPin(mock(SubproductoPinDto.class));
        ventaPinesService.consultarPines(dto);
    }

    @Test(expected = MandatoryFieldsMissingException.class)
    public void testConsultarPinesMissingSubproductoPinID() {
        ConsultarPinesDto dto = new ConsultarPinesDto();
        dto.setTransaccionDistribuidorId(TRANSACCION_DISTRIBUIDOR_ID);
        ventaPinesService.consultarPines(dto);
    }

    @Test(expected = MandatoryFieldsMissingException.class)
    public void testConsultarPinesMissingIdSubproductoPin() {
        ConsultarPinesDto dto = new ConsultarPinesDto();
        SubproductoPinDto pinDto = new SubproductoPinDto();
        pinDto.setServicioDto(mock(ServicioDto.class));
        pinDto.setServicioConsultar(SERVICION_CONSULTAR);
        pinDto.setSubproductoSeta(mock(SubproductoSetaDto.class));
        dto.setTransaccionDistribuidorId(TRANSACCION_DISTRIBUIDOR_ID);
        dto.setSubproductoPin(pinDto);
        ventaPinesService.consultarPines(dto);
    }

    @Test(expected = MandatoryFieldsMissingException.class)
    public void testConsultarPinesMissingServicioDtoPin() {
        ConsultarPinesDto dto = new ConsultarPinesDto();
        SubproductoPinDto pinDto = new SubproductoPinDto();
        pinDto.setIdSubproductoPines(ID_SUBPRODUCTO_PINES);
        pinDto.setServicioConsultar(SERVICION_CONSULTAR);
        pinDto.setSubproductoSeta(mock(SubproductoSetaDto.class));
        dto.setTransaccionDistribuidorId(TRANSACCION_DISTRIBUIDOR_ID);
        dto.setSubproductoPin(pinDto);
        ventaPinesService.consultarPines(dto);
    }

    @Test(expected = MandatoryFieldsMissingException.class)
    public void testConsultarPinesMissingServicioConsultarPin() {
        ConsultarPinesDto dto = new ConsultarPinesDto();
        SubproductoPinDto pinDto = new SubproductoPinDto();
        pinDto.setServicioDto(mock(ServicioDto.class));
        pinDto.setIdSubproductoPines(ID_SUBPRODUCTO_PINES);
        pinDto.setSubproductoSeta(mock(SubproductoSetaDto.class));
        dto.setTransaccionDistribuidorId(TRANSACCION_DISTRIBUIDOR_ID);
        dto.setSubproductoPin(pinDto);
        ventaPinesService.consultarPines(dto);
    }

    @Test(expected = MandatoryFieldsMissingException.class)
    public void testConsultarPinesMissingSubproductoSetaDtoPin() {
        ConsultarPinesDto dto = new ConsultarPinesDto();
        SubproductoPinDto pinDto = new SubproductoPinDto();
        pinDto.setServicioDto(mock(ServicioDto.class));
        pinDto.setIdSubproductoPines(ID_SUBPRODUCTO_PINES);
        pinDto.setServicioConsultar(SERVICION_CONSULTAR);
        dto.setTransaccionDistribuidorId(TRANSACCION_DISTRIBUIDOR_ID);
        dto.setSubproductoPin(pinDto);
        ventaPinesService.consultarPines(dto);
    }

    @Test(expected = ResponseStatusException.class)
    public void testConsultarPinesNotFoundID() {
        final String uri = URI_BASE.concat(URI_VENTA_PINES).concat(URI_CONSULTAR_PINES);
        doReturn(Optional.empty()).when(restTemplateService).post(eq(uri), any());
        ConsultarPinesDto dto = mock(ConsultarPinesDto.class);
        ventaPinesService.consultarPines(dto);
    }

    //Vender pin
    @Test
    public void testVenderPinesSuccess() {
        final String uri = URI_BASE.concat(URI_VENTA_PINES).concat(URI_VENDER_PIN);
        doReturn(Optional.of(EXPECTED_RESULT)).when(restTemplateService).post(eq(uri), any());
        VenderPinesDto dto = mock(VenderPinesDto.class);
        String result = ventaPinesService.venderPines(dto);
        Assert.assertNotNull(result);
    }

    @Test(expected = ResponseStatusException.class)
    public void testVenderPinesNotFound() {
        final String uri = URI_BASE.concat(URI_VENTA_PINES).concat(URI_VENDER_PIN);
        doReturn(Optional.empty()).when(restTemplateService).post(eq(uri), any());
        VenderPinesDto dto = mock(VenderPinesDto.class);
        String result = ventaPinesService.venderPines(dto);
        Assert.assertNotNull(result);
    }

    @Test(expected = ResponseStatusException.class)
    public void testVenderPinesNull() {
        ventaPinesService.venderPines(null);
    }

    @Test(expected = MandatoryFieldsMissingException.class)
    public void testVenderRecaudosMissingTransaccionDistribuidorID() {
        VenderPinesDto dto = new VenderPinesDto();
        ventaPinesService.venderPines(dto);
    }
}
