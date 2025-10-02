package io.github.app.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class GastoCartaoServiceTest {

	
	@Autowired
	private GastoCartaoService cartaoService;
	
	@Test
	void testGastoCartaoService() {
		fail("Not yet implemented");
	}

	@Test
	void testDeleteGastoById() {
		cartaoService.deleteGastoById(1L);
	}

	@Test
	void testSaveGastoCartao() {
		fail("Not yet implemented");
	}

	@Test
	void testFindAll() {
		fail("Not yet implemented");
	}

	@Test
	void testQueryBancoTotal() {
		fail("Not yet implemented");
	}

}
