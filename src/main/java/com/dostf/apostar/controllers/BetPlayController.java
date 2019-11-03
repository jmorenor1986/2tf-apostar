package com.dostf.apostar.controllers;

import com.dostf.apostar.dtos.betplay.BetPlayDto;
import com.dostf.apostar.dtos.betplay.BetPlayPinDto;
import com.dostf.apostar.dtos.betplay.BetPlayRetiroDto;
import com.dostf.apostar.services.IBetPlayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("v1/betplay")
public class BetPlayController {

    private final IBetPlayService betPlayService;

    @Autowired
    public BetPlayController(IBetPlayService betPlayService) {
        this.betPlayService = betPlayService;
    }

    @PostMapping(value = "/consultar-subproductos/{transaccion-distribuidor-id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String consultarSubProductos(@PathVariable("transaccion-distribuidor-id") long transactionId) {
        return betPlayService.consultarSubProductos(transactionId);
    }

    @PostMapping(value = "/solicitar-pin", produces = MediaType.APPLICATION_JSON_VALUE)
    public String solicitarPin(@RequestBody BetPlayPinDto betPlayPinDto) {
        return betPlayService.solicitarPin(betPlayPinDto);
    }

    @PostMapping(value = "/realizar-retiro", produces = MediaType.APPLICATION_JSON_VALUE)
    public String realizarRetiro(@RequestBody BetPlayRetiroDto betPlayRetiroDto) {
        return betPlayService.realizarRetiro(betPlayRetiroDto);
    }
}
