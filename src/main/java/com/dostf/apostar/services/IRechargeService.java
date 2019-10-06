package com.dostf.apostar.services;

import com.dostf.apostar.common.exceptions.ServiceNotAvailableException;
import com.dostf.apostar.dtos.RechargeDto;
import com.dostf.apostar.dtos.RechargeDtoResponse;
import org.springframework.http.ResponseEntity;

public interface IRechargeService {

	/**
	 * method to recharge a celphone
	 * @param rechargeData
	 * @throws ServiceNotAvailableException
	 * @return
	 */
	ResponseEntity<Object> recharge(RechargeDto rechargeData) throws ServiceNotAvailableException;
}
