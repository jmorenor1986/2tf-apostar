package com.dostf.apostar.common.enums;

public enum ErrorEnum {

	DATA_IS_MANDATORY_NUMERO("El numero de celular a recargar es obligatorio"),
	DATA_IS_MANDATORY_CODIGO_SUBPRODUCTO("El codigo del subproducto es obligatorio"),
	DATA_IS_MANDATORY_VALOR("El valor a recargar obligatorio"),
	DISTRIBUIDOR_IS_MANDATORY("Datos inválidos del distribuidor"),
	TRANSACCION_DISTRIBUIDOR_ID_IS_MANDATORY("Transaccion Distribuidor Id es obligatorio"),
	DEPARTAMENTO_IS_MANDATORY("Codigo de Departamento es obligatorio"),
	XML_PARSING("Parsing xml to json");

	private final String message;

	private ErrorEnum(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
