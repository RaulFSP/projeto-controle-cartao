package io.github.app.service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import io.github.app.domain.gasto.Banco;
import io.github.app.domain.recebedor.TipoRecebedor;
import io.github.app.dto.GastoCartaoDto;
import io.github.app.dto.GastoCartaoFormDto;
import io.github.app.dto.query.BancoTotalDto;
import io.github.app.dto.query.GastoShowPageDto;
import io.github.app.mapper.GastoCartaoMapper;
import io.github.app.repository.GastoCartaoRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class GastoCartaoService {

	private final GastoCartaoRepository gastoCartaoRepository;
	private final GastoCartaoMapper gastoCartaoMapper;

	public GastoCartaoService(GastoCartaoRepository cartaoRepository, GastoCartaoMapper cartaoMapper) {
		this.gastoCartaoRepository = cartaoRepository;
		this.gastoCartaoMapper = cartaoMapper;
	}

	public void deleteGastoById(Long id) {
		gastoCartaoRepository.deleteById(id);
	}

	public void saveGastoCartao(GastoCartaoFormDto dto) {
		var gasto = gastoCartaoMapper.fromFormPostDto(dto);
		gastoCartaoRepository.save(gasto);
	}

	public void updateGastoCartao(GastoCartaoFormDto dto, Long id) {
		try {
			if (!gastoCartaoRepository.existsById(id)) {
				throw new EntityNotFoundException("O gasto de ID %d não existe".formatted(id));
			}
			var gasto = gastoCartaoMapper.fromFormPutDto(dto, id);
			gastoCartaoRepository.save(gasto);
		} catch(IllegalArgumentException | EntityNotFoundException e) {
			System.err.println(e.getMessage());
		}
		
	}
	
	public List<GastoCartaoDto> findAll(
			Banco banco, 
			LocalDate dataInicio, 
			LocalDate dataFinal,
			String nomeRecebedor, 
			TipoRecebedor tipoRecebedor) {
		return gastoCartaoRepository.queryAll(banco, dataInicio, dataFinal, nomeRecebedor, tipoRecebedor).parallelStream()
				.map(m -> gastoCartaoMapper.toDto(m))
				.sorted(Comparator.comparing(GastoCartaoDto::dataOcorrencia).reversed()).toList();
	}

	public List<BancoTotalDto> queryBancoTotal(Banco banco) {
		return gastoCartaoRepository.queryBancoTotal(banco);
	}
	
	public GastoShowPageDto queryById(Long id) {
		return gastoCartaoRepository.queryGastoShowPage(id).orElseThrow(()-> new EntityNotFoundException("Gasto não encontrado"));
	}
	public GastoCartaoFormDto queryByIdDto(Long id) {
		var gasto = gastoCartaoRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Gasto não encontrado"));
		return gastoCartaoMapper.toFormDto(gasto);
		
	}
	
}
