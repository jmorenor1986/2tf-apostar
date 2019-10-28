package com.dostf.apostar.controllers;

import com.dostf.apostar.dtos.betplay.BetPlayDto;
import com.dostf.apostar.services.IBetPlayService;
import org.springframework.beans.factory.annotation.Autowired;

public class BetPlayController {

    private final IBetPlayService betPlayService;
    @Autowired
    public BetPlayController(IBetPlayService betPlayService) {
    this.betPlayService = betPlayService;
    }

    public String consultarSubProductos(Object any) {
        return betPlayService.consultarSubProductos(new BetPlayDto());
    }
}
