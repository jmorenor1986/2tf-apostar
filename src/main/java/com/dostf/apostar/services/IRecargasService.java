package com.dostf.apostar.services;

import com.dostf.apostar.common.exceptions.ServiceNotAvailableException;
import com.dostf.apostar.dtos.recargas.ConsultarParametrosDto;
import com.dostf.apostar.dtos.recargas.ConsultarParametrosPorSubproductoDto;
import com.dostf.apostar.dtos.recargas.ConsultarTopesDto;
import com.dostf.apostar.dtos.recargas.RecargarDto;


public interface IRecargasService {

    /**
     * method to recharge a celphone
     *
     * @param rechargeData
     * @return
     * @throws ServiceNotAvailableException
     */
    String recargar(final RecargarDto rechargeData);

    String consultarSubproducto(final Long id);

    String consultarParametros(final ConsultarParametrosDto consultarParametrosDto);

    String consultarTopes(final ConsultarTopesDto dto);

    String consultarParametrosPorSubproducto(final ConsultarParametrosPorSubproductoDto dto);
}
