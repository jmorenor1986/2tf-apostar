package com.dostf.apostar.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dostf.apostar.dtos.RechargeDto;
import com.dostf.apostar.services.IRechargeService;

@RestController
@RequestMapping("v1/recargas")
public class RechargeController {

	@Autowired
	private IRechargeService rechargeService;
	
	@RequestMapping(value = "/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Object rechargePhone(@RequestBody RechargeDto rechargeDto) {
		return rechargeService.recharge(rechargeDto);
	}
	
	
}
