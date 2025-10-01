package io.github.app.repository;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.persistence.EntityNotFoundException;

@SpringBootTest
class GastoCartaoRepositoryTest {

	@Autowired
	private GastoCartaoRepository gastoCartaoRepository;
	
	@Test
	void testQueryBancoTotal() {
		fail("Not yet implemented");
	}

	@Test
	void testQueryAll() {
		fail("Not yet implemented");
	}

	@Test
	void testQueryGastoShowPage() {
		var o =gastoCartaoRepository.queryGastoShowPage(2L).orElseThrow(()-> new EntityNotFoundException("não encontrado"));
		System.out.println(o);
		
	}

}
