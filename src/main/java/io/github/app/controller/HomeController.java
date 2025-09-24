package io.github.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import io.github.app.dto.GastoCartaoDto;

@Controller
@RequestMapping(value = "/")
public class HomeController {

	

	@GetMapping
	public ModelAndView home(GastoCartaoDto dto) {
		var mv = new ModelAndView("index");
		
		return mv;
	}

	
}
