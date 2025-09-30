package io.github.app.service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import io.github.app.domain.gasto.Banco;
import io.github.app.domain.recebedor.TipoRecebedor;
import io.github.app.dto.BancoTotalDto;
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

	public void deleteGastoById(Long id) {
		cartaoRepository.deleteById(id);
	}

	public void saveGastoCartao(GastoCartaoDto dto) {
		var gasto = cartaoMapper.fromDto(dto);
		cartaoRepository.save(gasto);
	}

	public List<GastoCartaoDtoRead> findAll(Banco banco, LocalDate dataInicio, LocalDate dataFinal,
			String nomeRecebedor, TipoRecebedor tipoRecebedor) {
		return cartaoRepository.queryAll(banco, dataInicio, dataFinal, nomeRecebedor, tipoRecebedor).parallelStream()
				.map(m -> cartaoMapper.toDtoRead(m))
				.sorted(Comparator.comparing(GastoCartaoDtoRead::dataOcorrencia).reversed()).toList();
	}

	public List<BancoTotalDto> queryBancoTotal(Banco banco) {
		return cartaoRepository.queryBancoTotal(banco);
	}
}
