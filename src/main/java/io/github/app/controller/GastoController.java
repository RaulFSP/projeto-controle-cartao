package io.github.app.controller;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import io.github.app.domain.gasto.Banco;
import io.github.app.domain.recebedor.TipoRecebedor;
import io.github.app.dto.GastoCartaoDto;
import io.github.app.service.GastoCartaoService;
import io.github.app.service.RecebedorService;
import jakarta.validation.Valid;

@Controller
@RequestMapping(value = "/gastos")
public class GastoController {

	private final GastoCartaoService cartaoService;
	private final RecebedorService recebedorService;

	private GastoController(GastoCartaoService cartaoService, RecebedorService recebedorService) {
		this.cartaoService = cartaoService;
		this.recebedorService = recebedorService;
	}

	@ModelAttribute
	public void addAttributes(Model model) {
		model.addAttribute("bancos", Banco.values());
		model.addAttribute("recebedores", recebedorService.findAll(null,null));
	}

	@GetMapping
	public ModelAndView index(@RequestParam(name="banco", required=false) Banco banco, 
			@RequestParam(name="data_inicio",required = false) LocalDate dataInicio,
			@RequestParam(name="data_final",required=false) LocalDate dataFinal,
			@RequestParam(name="nome_recebedor", required=false) String nomeRecebedor,
			@RequestParam(name="tipo_recebedor",required =false) TipoRecebedor tipoRecebedor
			) {
		var mv = new ModelAndView("gastos-page");
		
		mv.addObject("gastos", cartaoService.findAll(banco,dataInicio,dataFinal,nomeRecebedor,tipoRecebedor));
		return mv;
	}

	@GetMapping(value = "/new")
	public ModelAndView showGastoForm(GastoCartaoDto gasto) {
		var mv = new ModelAndView("gastos-form");
		mv.addObject("gasto", gasto);
		return mv;
	}
	
	@GetMapping(value="/{id}")
	public ModelAndView showGastoById(@PathVariable Long id) {
		
		var mv = new ModelAndView("gastos-show");
		return mv;
	}
	
	@DeleteMapping(value="/{id}")
	public ModelAndView destroyGasto(@PathVariable Long id) {
		
		return new ModelAndView("redirect:/gastos");
	}
	

	@PostMapping(value = "/new")
	public ModelAndView createGastos(
			@Valid @ModelAttribute(name = "gasto") GastoCartaoDto gasto,
			BindingResult result) {

		if (result.hasErrors()) {
			return new ModelAndView("gastos-form").addObject("gasto", gasto);
		}
		cartaoService.saveGastoCartao(gasto);
		return new ModelAndView("redirect:/gastos/new");
	}
}
