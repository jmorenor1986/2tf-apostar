package com.dostf.apostar.services;

import com.dostf.apostar.dtos.ventapines.ConsultarPinesDto;
import com.dostf.apostar.dtos.ventapines.VenderPinesDto;

public interface IVentaPinesService {
    String consultarParametros(final Long eq);

    String consultarPines(final ConsultarPinesDto dto);

    String venderPines(final VenderPinesDto dto);
}
