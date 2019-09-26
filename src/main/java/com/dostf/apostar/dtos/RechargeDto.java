package com.dostf.apostar.dtos;

import java.util.Objects;
import java.util.UUID;

import com.dostf.apostar.common.enums.RechargeEnum;
import com.dostf.apostar.common.exceptions.MandatoryFieldsMissingException;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RechargeDto extends DistributorDto {
	private String numero;
	private String codigoSubproducto;
	private Double valor;
	private Long transaccionDistribuidorId;

	/**
	 * validate  field mandatory distributor and recharge
	 * @throws MandatoryFieldsMissingException
	 */
	public void validateDataMandatory() throws MandatoryFieldsMissingException {
		checkDistributorMandatoryFields(this);
		if (Objects.isNull(numero)) {
			throw new MandatoryFieldsMissingException(RechargeEnum.DATA_IS_MANDATORY_NUMERO.getMessage());
		}
		if (Objects.isNull(codigoSubproducto)) {
			throw new MandatoryFieldsMissingException(RechargeEnum.DATA_IS_MANDATORY_CODIGO_SUBPRODUCTO.getMessage());
		}
		if (Objects.isNull(valor)) {
			throw new MandatoryFieldsMissingException(RechargeEnum.DATA_IS_MANDATORY_VALOR.getMessage());
		}
		if (Objects.isNull(transaccionDistribuidorId)) {
			transaccionDistribuidorId=UUID.randomUUID().getLeastSignificantBits();
		}
	}
}
