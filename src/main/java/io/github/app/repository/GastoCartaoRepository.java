package io.github.app.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.github.app.domain.gasto.Banco;
import io.github.app.domain.gasto.GastoCartao;
import io.github.app.domain.recebedor.TipoRecebedor;
import io.github.app.dto.BancoTotalDto;
import io.github.app.dto.query.GastoShowPageDto;

public interface GastoCartaoRepository extends JpaRepository<GastoCartao, Long> {

	@Query(value = "select new io.github.app.dto.BancoTotalDto( g.banco, sum(g.valor)) from GastoCartao g "
			+ "where (:banco is null  or g.banco=:banco)"
			+ "group by g.banco"
			)
	List<BancoTotalDto> queryBancoTotal(@Param("banco") Banco banco);
	
	@Query("SELECT g FROM GastoCartao g " +
		       "WHERE (:banco IS NULL OR g.banco = :banco) " +
		       "AND (:dataInicio IS NULL OR g.dataOcorrencia >= :dataInicio) " +
		       "AND (:dataFinal IS NULL OR g.dataOcorrencia <= :dataFinal) " +
		       "AND (:nomeRecebedor IS NULL OR LOWER(g.recebedor.nome) LIKE LOWER(CONCAT('%', :nomeRecebedor, '%')))"
		       + "AND (:tipoRecebedor IS NULL OR g.recebedor.tipoRecebedor = :tipoRecebedor) ")
		List<GastoCartao> queryAll(
		    @Param("banco") Banco banco,
		    @Param("dataInicio") LocalDate dataInicio,
		    @Param("dataFinal") LocalDate dataFinal,
		    @Param("nomeRecebedor") String nomeRecebedor,
		    @Param("tipoRecebedor") TipoRecebedor tipoRecebedor
		);

	@Query(value="select g.id,g.dataOcorrencia, r.nome, g.valor, g.banco,r.tipoRecebedor from GastoCartao g inner join g.recebedor r where g.id = :id")
	Optional<GastoShowPageDto> queryGastoShowPage(@Param("id")Long id);
}
