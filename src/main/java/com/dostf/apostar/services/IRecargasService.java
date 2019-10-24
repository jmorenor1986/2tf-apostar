package com.dostf.apostar.services;

import com.dostf.apostar.common.exceptions.ServiceNotAvailableException;
import com.dostf.apostar.dtos.recargas.RecargarDto;


public interface IRecargasService {

    /**
     * method to recharge a celphone
     *
     * @param rechargeData
     * @return
     * @throws ServiceNotAvailableException
     */
    String recargar(RecargarDto rechargeData) throws ServiceNotAvailableException;

    String consultarSubproducto(Long id);
}
