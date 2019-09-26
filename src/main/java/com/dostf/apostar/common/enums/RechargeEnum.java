package com.dostf.apostar.common.enums;

public enum RechargeEnum {

	DATA_IS_MANDATORY_NUMERO("El numero de celular a recargar es obligatorio"),
	DATA_IS_MANDATORY_CODIGO_SUBPRODUCTO("El codigo del subproducto es obligatorio"),
	DATA_IS_MANDATORY_VALOR("El valor a recargar obligatorio");
	private final String message;

	private RechargeEnum(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
