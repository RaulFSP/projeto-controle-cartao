package io.github.app.mapper;

import org.springframework.stereotype.Component;

import io.github.app.domain.gasto.GastoCartao;
import io.github.app.dto.GastoCartaoDto;
import io.github.app.dto.GastoCartaoFormDto;
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

	public GastoCartao fromFormPostDto(GastoCartaoFormDto dto) {
		var recebedor = recebedorRepository.findById(dto.recebedor()).orElseThrow(() -> new EntityNotFoundException("Recebedor não encontrado"));
		return GastoCartao.builder()
				.id(null)
				.banco(dto.banco())
				.dataOcorrencia(dto.dataOcorrencia())
				.recebedor(recebedor)
				.valor(dto.valor())
				.build();
	}
	public GastoCartao fromFormPutDto(GastoCartaoFormDto dto, Long id) {
		var recebedor = recebedorRepository.findById(dto.recebedor()).orElseThrow(() -> new EntityNotFoundException("Recebedor não encontrado"));
		return GastoCartao.builder()
				.id(id)
				.banco(dto.banco())
				.dataOcorrencia(dto.dataOcorrencia())
				.recebedor(recebedor)
				.valor(dto.valor())
				.build();
	}
	public GastoCartaoDto toDto(GastoCartao gasto) {
		RecebedorDto recebedorDto = recebedorMapper.toDtoRead(gasto.getRecebedor()); 
		return new GastoCartaoDto(
				gasto.getId(), 
				gasto.getBanco(), 
				gasto.getDataOcorrencia(), 
				gasto.getValor(), 
				recebedorDto);
	}

	public GastoCartaoFormDto toFormDto(GastoCartao gasto) {
		return new GastoCartaoFormDto(
				gasto.getId(), 
				gasto.getBanco(), 
				gasto.getDataOcorrencia(), 
				gasto.getValor(), 
				gasto.getRecebedor().getId());
	}
}
