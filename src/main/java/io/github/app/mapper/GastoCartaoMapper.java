package io.github.app.mapper;

import org.springframework.stereotype.Component;

import io.github.app.domain.gasto.GastoCartao;
import io.github.app.dto.GastoCartaoDto;
import io.github.app.repository.RecebedorRepository;
import jakarta.persistence.EntityNotFoundException;

@Component
public class GastoCartaoMapper {

	private final RecebedorRepository recebedorRepository;

	public GastoCartaoMapper(RecebedorRepository recebedorRepository) {
		this.recebedorRepository = recebedorRepository;
	}

	public GastoCartao fromDto(GastoCartaoDto dto) {
		return GastoCartao.builder()
				.banco(dto.banco())
				.dataOcorrencia(dto.dataOcorrencia())
				.id(null)
				.recebedor(recebedorRepository.findById(dto.recebedorId()).orElseThrow(() -> new EntityNotFoundException("Recebedor não encontrado")))
				.valor(dto.valor())
				.build();
	}

}
