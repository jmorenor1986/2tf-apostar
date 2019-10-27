package com.dostf.apostar.common.enums;

public enum ErrorEnum {

	DATA_IS_MANDATORY_NUMERO("El numero de celular a recargar es obligatorio"),
	DATA_IS_MANDATORY_CODIGO_SUBPRODUCTO("El codigo del subproducto es obligatorio"),
	DATA_IS_MANDATORY_VALOR("El valor es obligatorio"),
	DISTRIBUIDOR_IS_MANDATORY("Datos inválidos del distribuidor"),
	TRANSACCION_DISTRIBUIDOR_ID_IS_MANDATORY("Transaccion Distribuidor Id es obligatorio"),
	CODIGO_IS_MANDATORY("Codigo es obligatorio"),
    DEPARTAMENTO_IS_MANDATORY("Codigo de Departamento es obligatorio"),
	NOMBRE_IS_MANDATORY("Nombre es obligatorio"),
	IVA_IS_MANDATORY("Iva es obligatorio"),
	XML_PARSING("Parsing xml to json"),
	DATE_SORTEO_IS_MANDATORY("La fecha de sorteo es obligatoria"),
	CODIGO_LOTERIA_IS_MANDATORY ("El código de la loteria es obligatorio");
	private final String message;

	private ErrorEnum(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
