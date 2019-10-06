package com.dostf.apostar.services;

import com.dostf.apostar.common.exceptions.ServiceNotAvailableException;
import com.dostf.apostar.dtos.RechargeDto;
import org.springframework.http.ResponseEntity;

public interface IRechargeService {

    /**
     * method to recharge a celphone
     *
     * @param rechargeData
     * @return
     * @throws ServiceNotAvailableException
     */
    Object recharge(RechargeDto rechargeData) throws ServiceNotAvailableException;
}
