package io.github.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import io.github.app.domain.recebedor.TipoRecebedor;
import io.github.app.dto.RecebedorDto;
import io.github.app.service.RecebedorService;
import jakarta.validation.Valid;

@Controller
@RequestMapping(value = "/recebedores")
public class RecebedorController {

	private final RecebedorService recebedorService;

	public RecebedorController(RecebedorService recebedorService) {
		this.recebedorService = recebedorService;
	}

	@ModelAttribute
	public void addAttributes(Model model) {
		model.addAttribute("recebedores", recebedorService.findAll());
		model.addAttribute("tipoRecebedores",TipoRecebedor.values());
	}
	
	@GetMapping
	public ModelAndView index(
			@RequestParam(name = "nome_recebedor", required = false) String nomeRecebedor,
			@RequestParam(name = "tipo_recebedor", required = false) TipoRecebedor tipoRecebedor) {
		var mv = new ModelAndView("recebedores-page");
		mv.addObject("recebedores", recebedorService.findAll(nomeRecebedor , tipoRecebedor));
		mv.addObject("tipoRecebedorSelecionado",tipoRecebedor);
		return mv;
	}

	@GetMapping(value = "/new")
	public ModelAndView showRecebdorForm(RecebedorDto dto) {
		var mv = new ModelAndView("recebedores-form");
		mv.addObject("recebedor", dto);
		mv.addObject("tipos", TipoRecebedor.values());
		return mv;
	}

	@PostMapping(value = "/new")
	public ModelAndView createRecebedor(@Valid @ModelAttribute(name = "recebedor") RecebedorDto dto,
			BindingResult result) {

		if (result.hasErrors()) {

			return new ModelAndView("recebedores-form").addObject("recebedor", dto).addObject("tipos",
					TipoRecebedor.values());

		}
		recebedorService.createRecebedor(dto);
		return new ModelAndView("redirect:/recebedores/new");
	}
}
