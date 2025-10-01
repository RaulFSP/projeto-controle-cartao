package io.github.app.dto.query;

import java.math.BigDecimal;
import java.time.LocalDate;

import io.github.app.domain.gasto.Banco;
import io.github.app.domain.recebedor.TipoRecebedor;

public record GastoShowPageDto(Long id,LocalDate dataOcorrencia, String nomeRecebedor, BigDecimal valor, Banco banco, TipoRecebedor tipoRecebedor) {

}
