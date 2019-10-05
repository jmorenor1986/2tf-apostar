package com.dostf.apostar.dtos;

import java.util.Objects;

import com.dostf.apostar.common.enums.RequestEnum;
import com.dostf.apostar.common.exceptions.MandatoryFieldsMissingException;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DistributorDto {
	private String indentificador;
	private Long usuarioId;
	private String clienteId;
	private String equipoClienteId;
	private String llave;
	private String clave;

	/**
	 * validate the mandatory fields of distributor
	 *
	 * @param distributor
	 * @throws MandatoryFieldsMissingException
	 */
	public void checkDistributorMandatoryFields(DistributorDto distributor) throws MandatoryFieldsMissingException {
		if (Objects.isNull(distributor.getClave())) {
			throw new MandatoryFieldsMissingException(RequestEnum.DATA_IS_MANDATORY_CLAVE.getMessage());
		}
		if (Objects.isNull(distributor.getClienteId())) {
			throw new MandatoryFieldsMissingException(RequestEnum.DATA_IS_MANDATORY_CLIENTE_ID.getMessage());
		}
		if (Objects.isNull(distributor.getEquipoClienteId())) {
			throw new MandatoryFieldsMissingException(RequestEnum.DATA_IS_MANDATORY_EQUIPO_CLIENTE_ID.getMessage());
		}
		if (Objects.isNull(distributor.getIndentificador())) {
			throw new MandatoryFieldsMissingException(RequestEnum.DATA_IS_MANDATORY_IDENTIFICADOR.getMessage());
		}
		if (Objects.isNull(distributor.getLlave())) {
			throw new MandatoryFieldsMissingException(RequestEnum.DATA_IS_MANDATORY_LLAVE.getMessage());
		}
		if (Objects.isNull(distributor.getUsuarioId())) {
			throw new MandatoryFieldsMissingException(RequestEnum.DATA_IS_MANDATORY_USUARIO_ID.getMessage());
		}
	}
}
