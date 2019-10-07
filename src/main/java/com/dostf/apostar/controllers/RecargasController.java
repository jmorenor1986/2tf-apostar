package com.dostf.apostar.controllers;

import com.dostf.apostar.common.exceptions.ServiceNotAvailableException;
import com.dostf.apostar.dtos.RecargarDto;
import com.dostf.apostar.services.IRecargasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/recargas")
public class RecargasController {

  @Autowired
  private IRecargasService recargasService;

  @PostMapping("/recargar")
  public Object recargar(@RequestBody RecargarDto recargarDto) throws ServiceNotAvailableException {
    return recargasService.recargar(recargarDto);
  }

  @PostMapping("/{transaccion-distribuidor-id}")
  public Object consultarSubproducto(@PathVariable("transaccion-distribuidor-id") Long id ) throws ServiceNotAvailableException {
    return recargasService.consultarSubproducto(id);
  }
}
