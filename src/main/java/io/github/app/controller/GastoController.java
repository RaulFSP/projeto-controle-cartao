package io.github.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import io.github.app.domain.gasto.Banco;
import io.github.app.dto.GastoCartaoDto;
import io.github.app.service.GastoCartaoService;
import io.github.app.service.RecebedorService;

@Controller
@RequestMapping(value = "/gastos")
public class GastoController {

	private final GastoCartaoService cartaoService;
	private final RecebedorService recebedorService;
	

	private GastoController(
			GastoCartaoService cartaoService,
			RecebedorService recebedorService) {
		this.cartaoService = cartaoService;
		this.recebedorService = recebedorService;
	}

	@GetMapping
	public ModelAndView index() {
		var mv = new ModelAndView("gastos-page");
		mv.addObject("gastos", cartaoService.findAll());
		return mv;
	}
	
	@GetMapping(value = "/new")
	public ModelAndView showGastoForm(GastoCartaoDto gasto) {
		var mv = new ModelAndView("gastos-form");
		mv.addObject("gasto", gasto);
		mv.addObject("bancos",Banco.values());
		mv.addObject("recebedores",recebedorService.findAll());
		return mv;
	}

	@PostMapping(value="/new")
	public ModelAndView createGastos(
			@ModelAttribute(name = "gasto") GastoCartaoDto dto) {
		cartaoService.saveGastoCartao(dto);
		var mv = new ModelAndView("redirect:/gastos/new");
		return mv;
	}
}
