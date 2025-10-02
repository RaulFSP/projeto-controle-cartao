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
				.recebedor(recebedorRepository.findById(dto.recebedor()).orElseThrow(() -> new EntityNotFoundException("Recebedor não encontrado")))
				.valor(dto.valor())
				.build();
	}
	
	public GastoCartaoDtoRead toDtoRead(GastoCartao gasto) {
		RecebedorDto recebedorDto = recebedorMapper.toDtoRead(gasto.getRecebedor()); 
		return new GastoCartaoDtoRead(gasto.getId(), gasto.getBanco(), gasto.getDataOcorrencia(), gasto.getValor(), recebedorDto);
	}

	public GastoCartaoDto toDto(GastoCartao gasto) {
		
		return new GastoCartaoDto(gasto.getId(), gasto.getBanco(), gasto.getDataOcorrencia(), gasto.getValor(), gasto.getRecebedor().getId());
	}
	//TODO fazer método mapper de atualizição que recebe tanto dto quanto o id 
}
