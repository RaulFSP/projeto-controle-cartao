package io.github.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import io.github.app.dto.GastoCartaoDto;
import io.github.app.service.GastoCartaoService;

@Controller
@RequestMapping(value = "/")
public class HomeController {

	private final GastoCartaoService cartaoService;
	
	
	public HomeController(GastoCartaoService cartaoService) {
		this.cartaoService = cartaoService;
	}


	@GetMapping
	public ModelAndView home(GastoCartaoDto dto) {
		var dtos = cartaoService.queryBancoTotal();
		return new ModelAndView("index").addObject("dtos", dtos);
	}

}
