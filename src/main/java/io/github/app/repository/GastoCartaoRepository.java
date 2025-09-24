package io.github.app.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.github.app.domain.gasto.Banco;
import io.github.app.domain.gasto.GastoCartao;
import io.github.app.dto.BancoTotalDto;

public interface GastoCartaoRepository extends JpaRepository<GastoCartao, Long> {

	@Query(value = "select new io.github.app.dto.BancoTotalDto( g.banco, sum(g.valor)) from GastoCartao g group by g.banco")
	List<BancoTotalDto> queryBancoTotal();
	
	@Query("select g from GastoCartao g "
			+ "where (:banco is null or g.banco = :banco)"
			+ "and (:dataInit is null or :dataFinal is null or g.dataOcorrencia between :dataInit and :dataFinal)")
	List<GastoCartao> queryAll(
			@Param("banco") Banco banco,
			@Param("dataInit") LocalDate dataInit,
			@Param("dataFinal") LocalDate dataFinal
			);
}
