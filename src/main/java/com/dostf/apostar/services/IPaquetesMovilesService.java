package com.dostf.apostar.services;

import com.dostf.apostar.dtos.paquetesMoviles.GuardarPaqueteMovilDto;

public interface IPaquetesMovilesService {
    String consultarSubProductos(Long transactionId);

    String consultarPaquetes(Long transactionId, String subproducto);

    String guardarPaquetesMoviles(GuardarPaqueteMovilDto guardarPaqueteMovilDto);
}
