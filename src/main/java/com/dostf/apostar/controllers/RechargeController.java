package com.dostf.apostar.controllers;

import com.dostf.apostar.common.exceptions.ServiceNotAvailableException;
import com.dostf.apostar.dtos.RechargeDto;
import com.dostf.apostar.dtos.RechargeDtoResponse;
import com.dostf.apostar.services.IRechargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/recargas")
public class RechargeController {

  @Autowired
  private IRechargeService rechargeService;

  @RequestMapping(value = "/", method = RequestMethod.POST)
  public Object rechargePhone(@RequestBody RechargeDto rechargeDto) throws ServiceNotAvailableException {
    return rechargeService.recharge(rechargeDto).getBody();
  }


}
