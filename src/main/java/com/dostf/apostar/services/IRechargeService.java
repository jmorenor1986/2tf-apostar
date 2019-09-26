package com.dostf.apostar.services;

import com.dostf.apostar.common.exceptions.ServiceNotAvailableException;
import com.dostf.apostar.dtos.RechargeDto;

public interface IRechargeService {

	/**
	 * method to recharge a celphone
	 * @param rechargeData
	 * @throws ServiceNotAvailableException
	 */
	Object recharge(RechargeDto rechargeData) throws ServiceNotAvailableException;
}
