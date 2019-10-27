package com.dostf.apostar.services;

import com.dostf.apostar.dtos.recaudos.RecaudoDto;

public interface IRecaudosService {
    String consultarDepartamentos(final Long transaccionId);
    String consultarRecaudos(final RecaudoDto recaudoDto);
}
