package com.dostf.apostar.services;

import com.dostf.apostar.dtos.betplay.BetPlayDto;
import com.dostf.apostar.dtos.betplay.BetPlayPinDto;

public interface IBetPlayService {
    String consultarSubProductos(long transactionId);


    String solicitarPin(BetPlayPinDto betPlayPinDto);
}
