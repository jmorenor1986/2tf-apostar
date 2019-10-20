package com.dostf.apostar.services;

import com.dostf.apostar.common.exceptions.ServiceNotAvailableException;
import com.dostf.apostar.dtos.sorteos.SorteosDto;


public interface ISorteosService  {

    public Object consultarResultados(SorteosDto sorteosDto) throws ServiceNotAvailableException;
}
