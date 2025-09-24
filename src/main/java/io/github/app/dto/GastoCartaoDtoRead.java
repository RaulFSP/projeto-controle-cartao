package io.github.app.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import io.github.app.domain.gasto.Banco;

public record GastoCartaoDtoRead(
		Long id,
		Banco banco,
		LocalDate dataOcorrencia,
		BigDecimal valor,
		RecebedorDto recebedor) {

}
