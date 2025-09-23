package io.github.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.app.domain.recebedor.Recebedor;

public interface RecebedorRepository extends JpaRepository<Recebedor,Long> {

}
