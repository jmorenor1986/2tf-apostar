package com.dostf.apostar.controllers;

import com.dostf.apostar.dtos.paquetesMoviles.GuardarPaqueteMovilDto;
import com.dostf.apostar.services.IPaquetesMovilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("v1/paquetes-moviles")
public class PaquetesMovilesController {
    private final IPaquetesMovilesService paquetesMovilesService;

    @Autowired
    public PaquetesMovilesController(IPaquetesMovilesService paquetesMovilesService) {
        this.paquetesMovilesService = paquetesMovilesService;
    }

    @PostMapping(value = "/consultar-subproductos/{transaccion-distribuidor-id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String consultaSubProductos(@PathVariable("transaccion-distribuidor-id") Long transactionId) {
        return paquetesMovilesService.consultarSubProductos(transactionId);
    }

    @PostMapping(value = "/consultar-paquetes/{transaccion-distribuidor-id}/{codigo-sub-producto}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String consultarPaquetes(@PathVariable("transaccion-distribuidor-id")Long transactionId, @PathVariable("codigo-sub-producto")String subproducto) {
        return paquetesMovilesService.consultarPaquetes(transactionId,subproducto);
    }

    @PostMapping(value = "/guardar-paquete", produces = MediaType.APPLICATION_JSON_VALUE)
    public String guardaPaquetesMoviles(@RequestBody GuardarPaqueteMovilDto guardarPaqueteMovilDto) {
        return paquetesMovilesService.guardarPaquetesMoviles(guardarPaqueteMovilDto);
    }
}
