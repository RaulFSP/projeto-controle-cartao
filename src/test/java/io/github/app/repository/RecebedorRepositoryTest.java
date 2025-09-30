package io.github.app.repository;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import io.github.app.domain.recebedor.TipoRecebedor;

@SpringBootTest
class RecebedorRepositoryTest {

	@Autowired
	private RecebedorRepository recebedorRepository;
	
	

	@Test
	void testQueryAllStringTipoRecebedor() {
		recebedorRepository.queryAll(null,TipoRecebedor.MATERIAL)
		.forEach(System.out::println);
		System.out.println("-----");
		recebedorRepository.queryAll("empresa",null)
		.forEach(System.out::println);
		System.out.println("-----");
		recebedorRepository.queryAll(null,null)
		.forEach(System.out::println);
	}

	

}
