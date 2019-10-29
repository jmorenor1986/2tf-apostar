package com.dostf.apostar.controllers;

import com.dostf.apostar.dtos.betplay.BetPlayDto;
import com.dostf.apostar.services.IBetPlayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("v1/betplay")
public class BetPlayController {

    private final IBetPlayService betPlayService;
    @Autowired
    public BetPlayController(IBetPlayService betPlayService) {
    this.betPlayService = betPlayService;
    }

    @PostMapping("/consultar-subproductos/{transaccion-distribuidor-id}")
    public String consultarSubProductos(@PathVariable("transaccion-distribuidor-id") long transactionId) {
        return betPlayService.consultarSubProductos(transactionId);
    }
}
