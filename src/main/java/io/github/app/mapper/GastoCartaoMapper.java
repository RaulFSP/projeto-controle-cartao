package io.github.app.mapper;

import org.springframework.stereotype.Component;

import io.github.app.domain.gasto.GastoCartao;
import io.github.app.dto.GastoCartaoDto;
import io.github.app.dto.GastoCartaoDtoRead;
import io.github.app.dto.RecebedorDto;
import io.github.app.repository.RecebedorRepository;
import jakarta.persistence.EntityNotFoundException;

@Component
public class GastoCartaoMapper {

	private final RecebedorRepository recebedorRepository;
	private final RecebedorMapper recebedorMapper;
	

	private GastoCartaoMapper(
			RecebedorRepository recebedorRepository,
			RecebedorMapper recebedorMapper) {
		this.recebedorRepository = recebedorRepository;
		this.recebedorMapper = recebedorMapper;
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
	
	public GastoCartaoDtoRead toDtoRead(GastoCartao gastos) {
		RecebedorDto recebedorDto = recebedorMapper.toDtoRead(gastos.getRecebedor()); 
		return new GastoCartaoDtoRead(gastos.getId(), gastos.getBanco(), gastos.getDataOcorrencia(), gastos.getValor(), recebedorDto);
	}

}
