package com.dostf.apostar.controllers;

import com.dostf.apostar.dtos.recaudos.ConsultarRecaudoDto;
import com.dostf.apostar.dtos.recaudos.ConsultarValorDto;
import com.dostf.apostar.dtos.recaudos.GuardarDto;
import com.dostf.apostar.services.IRecaudosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/recaudos")
public class RecaudosController {

    private final IRecaudosService recaudosService;

    @Autowired
    public RecaudosController(IRecaudosService recaudosService) {
        this.recaudosService = recaudosService;
    }

    @PostMapping(value = "consultar-recaudos", produces = MediaType.APPLICATION_JSON_VALUE)
    public String consultarRecaudos(@RequestBody ConsultarRecaudoDto dto) {
        return recaudosService.consultarRecaudos(dto);
    }

    @PostMapping(value = "consultar-departamentos/{transaccion-distribuidor-id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String consultarDepartamentos(@PathVariable("transaccion-distribuidor-id") Long id) {
        return recaudosService.consultarDepartamentos(id);
    }

    @PostMapping(value = "consultar-valor", produces = MediaType.APPLICATION_JSON_VALUE)
    public String consultarValor(@RequestBody ConsultarValorDto dto) {
        return recaudosService.consultarValor(dto);
    }

    @PostMapping(value = "guardar", produces = MediaType.APPLICATION_JSON_VALUE)
    public String guardar(@RequestBody GuardarDto guardarDto) {
        return recaudosService.guardar(guardarDto);
    }

    @PostMapping(value = "consultar", produces = MediaType.APPLICATION_JSON_VALUE)
    public String consultar(@RequestBody ConsultarRecaudoDto dto) {
        return recaudosService.consultar(dto);
    }
}
