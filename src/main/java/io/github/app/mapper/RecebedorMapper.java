package io.github.app.mapper;

import org.springframework.stereotype.Component;

import io.github.app.domain.recebedor.Recebedor;
import io.github.app.dto.RecebedorDto;

@Component
public class RecebedorMapper {

	public Recebedor fromDto(RecebedorDto dto) {
		return Recebedor.builder()
				.id(null)
				.nome(dto.nome())
				.tipoRecebedor(dto.tipoRecebedor())
				.build();
	}
	
	public RecebedorDto toDtoRead(Recebedor recebedor) {
		return new RecebedorDto(recebedor.getId(), recebedor.getNome()	, recebedor.getTipoRecebedor());
	}
}
