package com.dostf.apostar.controllers;

import com.dostf.apostar.dtos.paquetesMoviles.SubProductosPaquetesMovilesDto;
import com.dostf.apostar.services.IPaquetesMovilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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
}
