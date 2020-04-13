package com.dostf.apostar.services;

import com.dostf.apostar.dtos.recaudos.ConsultarRecaudoDto;
import com.dostf.apostar.dtos.recaudos.ConsultarValorDto;
import com.dostf.apostar.dtos.recaudos.GuardarDto;

public interface IRecaudosService {
    String consultarDepartamentos(final Long transaccionId);

    String consultarRecaudos(final ConsultarRecaudoDto consultarRecaudoDto);

    String consultarValor(final ConsultarValorDto consultarValorDto);

    String guardar(final GuardarDto dto);

    String consultar(final ConsultarRecaudoDto dto);
}
