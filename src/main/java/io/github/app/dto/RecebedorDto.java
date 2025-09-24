package io.github.app.dto;

import org.hibernate.validator.constraints.Length;

import io.github.app.domain.recebedor.TipoRecebedor;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record RecebedorDto(Long id, 
		@NotEmpty(message = "o nome do recebedor não pode estar vazio")
		@Length(min = 2, max=150, message="o nome do recebedor deve ter entre 2 a 150 letras")
		String nome, 
		@NotNull
		TipoRecebedor tipoRecebedor) {

}
