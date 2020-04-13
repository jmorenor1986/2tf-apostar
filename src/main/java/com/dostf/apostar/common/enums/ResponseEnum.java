package com.dostf.apostar.common.enums;

public enum ResponseEnum {
  NOT_FOUND_SERVICE("No se encuentra el servicio");
  private final String message;
  
  ResponseEnum(String message) {
    this.message = message;
  }
  
  public String getMessage() {
    return message;
  }
}
