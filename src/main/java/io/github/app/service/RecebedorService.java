package io.github.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import io.github.app.domain.recebedor.Recebedor;
import io.github.app.domain.recebedor.TipoRecebedor;
import io.github.app.dto.RecebedorDto;
import io.github.app.mapper.RecebedorMapper;
import io.github.app.repository.RecebedorRepository;

@Service
public class RecebedorService {

	private final RecebedorRepository recebedorRepository;
	private final RecebedorMapper recebedorMapper;
	private RecebedorService(
			RecebedorRepository recebedorRepository,
			RecebedorMapper recebedorMapper) {
		this.recebedorRepository = recebedorRepository;
		this.recebedorMapper = recebedorMapper;
	}
	public List<RecebedorDto> findAll(){
		return recebedorRepository.queryAll(nome,tipoRecebedor);
	}
	
	public List<RecebedorDto> findAll(String nome, TipoRecebedor tipoRecebedor){
		return recebedorRepository.queryAll(nome,tipoRecebedor);
	}
	
	public void createRecebedor(RecebedorDto dto) {
		Recebedor recebedor = recebedorMapper.fromDto(dto);
		recebedorRepository.save(recebedor);
	}
}
