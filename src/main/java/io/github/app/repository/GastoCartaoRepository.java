package io.github.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.app.domain.gasto.GastoCartao;

public interface GastoCartaoRepository extends JpaRepository<GastoCartao, Long> {

}
