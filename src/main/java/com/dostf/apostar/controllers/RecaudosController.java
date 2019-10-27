package com.dostf.apostar.controllers;

import com.dostf.apostar.common.exceptions.ServiceNotAvailableException;
import com.dostf.apostar.dtos.recaudos.RecaudoBaseDto;
import com.dostf.apostar.dtos.recaudos.RecaudoDto;
import com.dostf.apostar.services.IRecaudosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/recaudos")
public class RecaudosController {

    private final IRecaudosService recaudosService;

    @Autowired
    public RecaudosController(IRecaudosService recaudosService) {
        this.recaudosService = recaudosService;
    }

    @PostMapping(value = "consultar", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object consultarRecaudos(@RequestBody RecaudoDto dto) throws ServiceNotAvailableException {
        return recaudosService.consultarRecaudos(dto);
    }

    @PostMapping(value = "consultar-departamentos/{transaccion-distribuidor-id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object consultarDepartamentos(@PathVariable("transaccion-distribuidor-id") Long id) throws ServiceNotAvailableException {
        return recaudosService.consultarDepartamentos(id);
    }
}
