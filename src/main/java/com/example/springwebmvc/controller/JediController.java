package com.example.springwebmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.springwebmvc.model.Jedi;
import com.example.springwebmvc.repository.JediRepository;

@Controller
public class JediController {
	@Autowired
	private JediRepository repository;
	
	@GetMapping(value = "/jedi")
	public ModelAndView jedi() {
		final ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("jedi");
		
		modelAndView.addObject("allJedi", repository.getAllJedi());
		return modelAndView;
	}
	
	@GetMapping(value = "/new-jedi")
	public ModelAndView newJedi() {
		final ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("new-jedi");
		
		modelAndView.addObject("jedi",  new Jedi());
		return modelAndView;
	}
	
	@PostMapping(value = "/jedi")
	public String createJedi(@ModelAttribute Jedi jedi, RedirectAttributes redirectAttributes) {
		repository.add(jedi);
		redirectAttributes.addFlashAttribute("message", "Jedi cadastrado com sucesso!");
		return "redirect:jedi";
	}

}
