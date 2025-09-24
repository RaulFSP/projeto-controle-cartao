package io.github.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import io.github.app.domain.gasto.GastoCartao;
import io.github.app.dto.BancoTotalDto;

public interface GastoCartaoRepository extends JpaRepository<GastoCartao, Long> {

	@Query(value = "select new io.github.app.dto.BancoTotalDto( g.banco, sum(g.valor)) from GastoCartao g group by g.banco")
	List<BancoTotalDto> queryBancoTotal();
}
