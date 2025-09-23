package io.github.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import io.github.app.domain.gasto.Banco;
import io.github.app.domain.recebedor.TipoRecebedor;
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
		var mv = new ModelAndView("index");
		mv.addObject("gasto", dto);
		mv.addObject("bancos", Banco.values());
		mv.addObject("tipos", TipoRecebedor.values());
		return mv;
	}

	@PostMapping
	public ModelAndView createGastos(@ModelAttribute(name = "gasto") GastoCartaoDto dto ) {
		System.out.println(dto);
		cartaoService.saveGastoCartao(dto);
		var mv = new ModelAndView("redirect:/");
		return mv;
	}
}
