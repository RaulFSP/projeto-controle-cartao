package io.github.app.controller;

import java.io.IOException;
import java.time.LocalDate;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import io.github.app.domain.gasto.Banco;
import io.github.app.domain.recebedor.TipoRecebedor;
import io.github.app.dto.GastoCartaoFormDto;
import io.github.app.service.GastoCartaoService;
import io.github.app.service.PdfGenerator;
import io.github.app.service.RecebedorService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@Controller
@RequestMapping(value = "/gastos")
public class GastoController {

	private final GastoCartaoService gastoCartaoService;
	private final RecebedorService recebedorService;
	private final PdfGenerator pdfGenerator;
	

	public GastoController(GastoCartaoService gastoCartaoService, RecebedorService recebedorService,
			PdfGenerator pdfGenerator) {
		this.gastoCartaoService = gastoCartaoService;
		this.recebedorService = recebedorService;
		this.pdfGenerator = pdfGenerator;
	}

	@ModelAttribute
	public void addAttributes(Model model) {
		model.addAttribute("bancos", Banco.values());
		model.addAttribute("recebedores", recebedorService.findAll(null, null));
	}

	@GetMapping
	public ModelAndView index(@RequestParam(name = "banco", required = false) Banco banco,
			@RequestParam(name = "data_inicio", required = false) LocalDate dataInicio,
			@RequestParam(name = "data_final", required = false) LocalDate dataFinal,
			@RequestParam(name = "nome_recebedor", required = false) String nomeRecebedor,
			@RequestParam(name = "tipo_recebedor", required = false) TipoRecebedor tipoRecebedor) {
		return new ModelAndView("gastos-page").addObject("gastos",
				gastoCartaoService.findAll(banco, dataInicio, dataFinal, nomeRecebedor, tipoRecebedor));
	}

	@GetMapping(value = "/new")
	public ModelAndView showGastoForm(GastoCartaoFormDto gasto) {
		return new ModelAndView("gastos-form").addObject("gasto", gasto);
	}

	@GetMapping(value = "/{id}")
	public ModelAndView showGastoById(@PathVariable Long id) {
		return new ModelAndView("gastos-show").addObject("gasto", gastoCartaoService.queryById(id));
	}

	@GetMapping(value = "/{id}/edit")
	public ModelAndView showGastoEdit(@PathVariable Long id) {

		return new ModelAndView("gastos-form").addObject("gasto", gastoCartaoService.queryByIdDto(id));
	}

	
	//rotas de Crud
	
	@DeleteMapping(value = "/{id}")
	public ModelAndView destroyGasto(@PathVariable Long id) {
		gastoCartaoService.deleteGastoById(id);
		return new ModelAndView("redirect:/gastos");
	}

	@PostMapping(value = "/new")
	public ModelAndView createGastos(@Valid @ModelAttribute(name = "gasto") GastoCartaoFormDto gasto,
			BindingResult result) {

		if (result.hasErrors()) {
			return new ModelAndView("gastos-form").addObject("gasto", gasto);
		}
		gastoCartaoService.saveGastoCartao(gasto);
		return new ModelAndView("redirect:/gastos/new");
	}

	@PutMapping(value = "/{id}")
	public ModelAndView updateGastos(@PathVariable Long id, @Valid @ModelAttribute("gasto") GastoCartaoFormDto gasto,
			BindingResult result) {
		if (result.hasErrors()) {
			return new ModelAndView("gastos-form").addObject("gasto", gasto);
		}
		gastoCartaoService.updateGastoCartao(gasto, id);

		return new ModelAndView("redirect:/gastos/%d/edit".formatted(id));
	}
	
	//Rotas de PDf
	
	@GetMapping(value="/pdf")
	public void gerarPdf(HttpServletResponse resp) throws IOException {
		resp.setContentType("application/pdf");
		resp.setHeader("Content-Disposition", "inline; filename=gastos.pdf");
		pdfGenerator.export(resp.getOutputStream());
	}
}
