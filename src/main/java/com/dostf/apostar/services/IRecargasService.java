package com.dostf.apostar.services;

import com.dostf.apostar.common.exceptions.ServiceNotAvailableException;
import com.dostf.apostar.dtos.RecargarDto;

public interface IRecargasService {

    /**
     * method to recharge a celphone
     *
     * @param rechargeData
     * @return
     * @throws ServiceNotAvailableException
     */
    Object recargar(RecargarDto rechargeData) throws ServiceNotAvailableException;

    Object consultarSubproducto(Long id);
}
