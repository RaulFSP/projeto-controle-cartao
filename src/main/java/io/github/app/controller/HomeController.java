package io.github.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import io.github.app.domain.gasto.Banco;
import io.github.app.service.GastoCartaoService;

@Controller
@RequestMapping(value = "/")
public class HomeController {

	private final GastoCartaoService cartaoService;
	
	
	public HomeController(GastoCartaoService cartaoService) {
		this.cartaoService = cartaoService;
	}
	@ModelAttribute
	public void addAttributes(Model model) {
		model.addAttribute("bancos", Banco.values());
		
	}

	@GetMapping
	public ModelAndView home(
			@RequestParam(name="banco", required=false) Banco banco
			) {
		var dtos = cartaoService.queryBancoTotal(banco);
		return new ModelAndView("index").addObject("dtos", dtos);
	}

}
