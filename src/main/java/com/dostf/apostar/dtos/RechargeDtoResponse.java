package com.dostf.apostar.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RechargeDtoResponse {
    @JsonProperty
    private String estado;
}
