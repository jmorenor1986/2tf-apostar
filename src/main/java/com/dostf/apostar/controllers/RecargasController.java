package com.dostf.apostar.controllers;

import com.dostf.apostar.common.exceptions.ServiceNotAvailableException;
import com.dostf.apostar.dtos.recargas.RecargarDto;
import com.dostf.apostar.services.IRecargasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/recargas")
public class RecargasController {
  private IRecargasService recargasService;

  @Autowired
  public RecargasController(IRecargasService recargasService) {
    this.recargasService = recargasService;
  }

  @PostMapping(value = "/recargar", produces = MediaType.APPLICATION_JSON_VALUE)
  public String recargar(@RequestBody RecargarDto recargarDto) throws ServiceNotAvailableException {
    return recargasService.recargar(recargarDto);
  }

  @PostMapping(value = "/{transaccion-distribuidor-id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public String consultarSubproducto(@PathVariable("transaccion-distribuidor-id") Long id ) throws ServiceNotAvailableException {
    return recargasService.consultarSubproducto(id);
  }
}
