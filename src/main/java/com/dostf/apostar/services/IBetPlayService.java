package com.dostf.apostar.services;

import com.dostf.apostar.dtos.betplay.BetPlayPinDto;
import com.dostf.apostar.dtos.betplay.BetPlayRecaudoDto;
import com.dostf.apostar.dtos.betplay.BetPlayRetiroDto;

public interface IBetPlayService {
    String consultarSubProductos(long transactionId);


    String solicitarPin(BetPlayPinDto betPlayPinDto);

    String realizarRetiro(BetPlayRetiroDto betPlayRetiroDto);

    String realizaRecaudo(BetPlayRecaudoDto betPlayRecaudoDto);
}
