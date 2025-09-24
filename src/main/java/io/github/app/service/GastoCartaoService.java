package io.github.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import io.github.app.dto.GastoCartaoDto;
import io.github.app.dto.GastoCartaoDtoRead;
import io.github.app.mapper.GastoCartaoMapper;
import io.github.app.repository.GastoCartaoRepository;

@Service
public class GastoCartaoService {

	private final GastoCartaoRepository cartaoRepository;
	private final GastoCartaoMapper cartaoMapper;

	public GastoCartaoService(GastoCartaoRepository cartaoRepository, GastoCartaoMapper cartaoMapper) {
		this.cartaoRepository = cartaoRepository;
		this.cartaoMapper = cartaoMapper;
	}

	public void saveGastoCartao(GastoCartaoDto dto) {
		var gasto = cartaoMapper.fromDto(dto);
		cartaoRepository.save(gasto);
	}
	
	public List<GastoCartaoDtoRead> findAll(){
		var dtos = cartaoRepository.findAll().parallelStream().map(m->cartaoMapper.toDtoRead(m)).toList();
		return dtos;
	}
}
