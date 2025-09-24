package io.github.app.dto;

import java.math.BigDecimal;

import io.github.app.domain.gasto.Banco;

public record BancoTotalDto(Banco banco, BigDecimal total) {

}
