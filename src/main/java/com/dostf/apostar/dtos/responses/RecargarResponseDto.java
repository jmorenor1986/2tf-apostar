package com.dostf.apostar.dtos.responses;

import com.dostf.apostar.dtos.common.EmpresaDto;
import com.dostf.apostar.dtos.common.JerarquiaDto;
import com.dostf.apostar.dtos.common.LineasDto;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RecargarResponseDto {
  private Boolean estado;
  private EmpresaDto empresa;
  @JsonProperty("transaction-id")
  private String transactionId;
  @JsonProperty("numero-autorizacion")
  private Long numeroAutorizacion;
  private JerarquiaDto jerarquia;
  @JsonProperty("lineas-pie-recarga")
  private LineasDto lineasPieRecarga;


}
