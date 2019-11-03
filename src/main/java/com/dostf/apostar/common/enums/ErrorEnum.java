package com.dostf.apostar.common.enums;

public enum ErrorEnum {

	DATA_IS_MANDATORY_NUMERO("El numero de celular a recargar es obligatorio"),
	DATA_IS_MANDATORY_CODIGO_SUBPRODUCTO("El codigo del subproducto es obligatorio"),
	DATA_IS_MANDATORY_VALOR("El valor es obligatorio"),
	DISTRIBUIDOR_IS_MANDATORY("Datos inválidos del distribuidor"),
	TRANSACCION_DISTRIBUIDOR_ID_IS_MANDATORY("Transaccion Distribuidor Id es obligatorio"),
	CODIGO_IS_MANDATORY("Codigo es obligatorio"),
	IVA_IS_MANDATORY("Iva es obligatorio"),
	XML_PARSING("Parsing xml to json"),
	DATE_SORTEO_IS_MANDATORY("La fecha de sorteo es obligatoria"),
	CODIGO_LOTERIA_IS_MANDATORY ("El código de la loteria es obligatorio"),
	ALL_DATA_IS_NULL("La petición no contiente datos"),
	SUBPRODUCTO_IS_MANDATORY("El subproducto es obligatorio"),
	CLIENT_ID_IS_MANDATORY("El cliente id es obligatorio"),
	PING_CLIENT_IS_MANDATORY("El ping cliente es obligatorio"),
	VALOR_IS_MANDATORY("El valor es obligatorio"),
	MONTO_IS_MANDATORY("El monto es obligatorio"),
	REGISTRAR_RETIRO_IS_MANDATORY("Los datos para registrar el retiro son obligatorios"),
	REGISTRAR_RECAUDO_IS_MANDATORY("Los datos para registrar el recaudo son obligatorios");
	private final String message;

	ErrorEnum(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
