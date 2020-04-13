package com.dostf.apostar.controllers;

import com.dostf.apostar.common.exceptions.ServiceNotAvailableException;
import com.dostf.apostar.dtos.sorteos.SorteosDto;
import com.dostf.apostar.services.ISorteosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/sorteos")
public class SorteosController {
  
  private ISorteosService sorteosService;
  
  @Autowired
  public SorteosController(ISorteosService sorteosService) {
    this.sorteosService = sorteosService;
  }
  
  @PostMapping(value = "/consultar", produces = MediaType.APPLICATION_JSON_VALUE)
  public String consultarSorteos(@RequestBody SorteosDto sorteosDto) throws ServiceNotAvailableException {
    return  sorteosService.consultarResultados(sorteosDto);
  }
  
  @PostMapping(value = "/consultarSorteosSemanal", produces = MediaType.APPLICATION_JSON_VALUE)
  public String consultarSorteosSemanal(@RequestBody  SorteosDto sorteosDto) throws ServiceNotAvailableException {
    return sorteosService.consultarResultadosSemanal(sorteosDto);
  }
}
