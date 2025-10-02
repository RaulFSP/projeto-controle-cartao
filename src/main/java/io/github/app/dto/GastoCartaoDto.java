package io.github.app.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import io.github.app.domain.gasto.Banco;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;

public record GastoCartaoDto(
		Long id, 
		@NotNull
		Banco banco, 
		@NotNull
		@PastOrPresent
		LocalDate dataOcorrencia, 
		@NotNull
		@PositiveOrZero
		BigDecimal valor, 
		@NotNull
		Long recebedor) {

}
