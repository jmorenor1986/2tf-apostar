package com.dostf.apostar.services;

import com.dostf.apostar.dtos.paquetesMoviles.SubProductosPaquetesMovilesDto;

public interface IPaquetesMovilesService {
    String consultarSubProductos(Long transactionId);

    String consultarPaquetes(Long transactionId, Long subproducto);
}
