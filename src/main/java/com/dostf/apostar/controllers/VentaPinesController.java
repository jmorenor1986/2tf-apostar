package com.dostf.apostar.controllers;

import com.dostf.apostar.dtos.ventapines.ConsultarPinesDto;
import com.dostf.apostar.dtos.ventapines.VenderPinesDto;
import com.dostf.apostar.services.IVentaPinesService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("v1/venta-pines")
public class VentaPinesController {
    private final IVentaPinesService ventaPinesService;

    public VentaPinesController(IVentaPinesService ventaPinesService) {
        this.ventaPinesService = ventaPinesService;
    }

    @PostMapping(value = "consultar-parametros/{transaccion-distribuidor-id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String consultarParametros(@PathVariable("transaccion-distribuidor-id") Long id) {
        return ventaPinesService.consultarParametros(id);
    }

    @PostMapping(value = "consultar-pines", produces = MediaType.APPLICATION_JSON_VALUE)
    public String consultarPines(@RequestBody ConsultarPinesDto dto) {
        return ventaPinesService.consultarPines(dto);
    }

    @PostMapping(value = "vender-pines", produces = MediaType.APPLICATION_JSON_VALUE)
    public String venderPines(@RequestBody VenderPinesDto dto) {
        return ventaPinesService.venderPines(dto);
    }
}
