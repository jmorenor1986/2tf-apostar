package com.dostf.apostar.services;

import com.dostf.apostar.common.exceptions.MandatoryDtoMissingException;
import com.dostf.apostar.dtos.sorteos.SorteosDto;

public interface ISorteosService {
    String consultarResultados(SorteosDto sorteosDto);

    String consultarResultadosSemanal(SorteosDto sorteosDto) throws MandatoryDtoMissingException;
}
