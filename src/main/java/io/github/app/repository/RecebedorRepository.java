package io.github.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.github.app.domain.recebedor.Recebedor;
import io.github.app.domain.recebedor.TipoRecebedor;
import io.github.app.dto.RecebedorDto;

public interface RecebedorRepository extends JpaRepository<Recebedor, Long> {

	@Query("select new io.github.app.dto.RecebedorDto(r.id, r.nome, r.tipoRecebedor) " + "from Recebedor r "
			+ "where (:nomeRecebedor IS NULL OR LOWER(r.nome) LIKE LOWER(CONCAT('%', :nomeRecebedor, '%'))) "
			+ "and (:tipoRecebedor is null or r.tipoRecebedor = :tipoRecebedor)")
	List<RecebedorDto> queryAll(@Param("nomeRecebedor") String nome, @Param("tipoRecebedor") TipoRecebedor tipoRecebedor);

	@Query("select new io.github.app.dto.RecebedorDto(r.id, r.nome, r.tipoRecebedor)from Recebedor r")
	List<RecebedorDto> queryAll();
	
}
