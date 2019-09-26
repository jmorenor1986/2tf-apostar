package com.dostf.apostar.common.enums;

public enum RequestEnum {
	DATA_IS_MANDATORY_CLIENTE_ID("El Id del cliente es obligatorio"),
	DATA_IS_MANDATORY_CLAVE("La clave es obligatoria"),
	DATA_IS_MANDATORY_EQUIPO_CLIENTE_ID("El Id del equipo del cliente es obligatorio"),
	DATA_IS_MANDATORY_IDENTIFICADOR("El Identificador del  cliente es obligatorio"),
	DATA_IS_MANDATORY_LLAVE("La llave  es obligatoria"),
	DATA_IS_MANDATORY_USUARIO_ID("el usuario es obligatorio"),
	SERVICE_NOT_AVAILABLE("El servicio {URL} no esta disponible");
	
	private final String message;

	private RequestEnum(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
	
	
}
