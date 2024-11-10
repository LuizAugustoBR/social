package com.social.social.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.social.social.models.Social;
import com.social.social.repositories.SocialRepository;

@Controller
@RequestMapping("/social")
public class SocialController {

	@Autowired
	private SocialRepository sr;

	@GetMapping("/form")
	public String form() {
		return "social/formSocial";
	}

	@PostMapping
	public String adicionar(Social social) {

		System.out.println(social);
		sr.save(social);

		return "social/evento-adicionado";
	}

	@GetMapping
	public ModelAndView listar() {
		List<Social> social = sr.findAll();
		ModelAndView mv = new ModelAndView("social/lista");
		mv.addObject("social", social);
		return mv;
	}

	@GetMapping("/{id}")
	public ModelAndView detalhar(@PathVariable Long id) {
		ModelAndView md = new ModelAndView();
		Optional<Social> opt = sr.findById(id);
		if (opt.isEmpty()) {
			md.setViewName("redirect:/social");
			return md;
		}

		md.setViewName("social/detalhes");
		Social social = opt.get();

		md.addObject("social", social);

		return md;
	}

}
