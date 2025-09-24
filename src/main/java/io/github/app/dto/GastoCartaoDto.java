package io.github.app.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import io.github.app.domain.gasto.Banco;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

public record GastoCartaoDto(Long id, 
		@NotNull
		Banco banco, 
		@NotNull
		@PastOrPresent
		LocalDate dataOcorrencia, 
		@NotNull
		BigDecimal valor, 
		@NotNull
		Long recebedorId) {

}
