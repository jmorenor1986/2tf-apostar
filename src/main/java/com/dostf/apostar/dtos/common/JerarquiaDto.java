package com.dostf.apostar.dtos.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class JerarquiaDto {
  @JsonProperty("empresa-id")
  private String empresaId;
}
