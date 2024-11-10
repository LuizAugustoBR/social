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
import com.social.social.models.Voluntarios;
import com.social.social.repositories.SocialRepository;
import com.social.social.repositories.VoluntarioRepository;

@Controller
@RequestMapping("/social")
public class SocialController {

	@Autowired
	private SocialRepository sr;
	@Autowired
	private VoluntarioRepository vr;

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
	
	@PostMapping("/{idSocial}")
	public String salvarVoluntario(@PathVariable Long idSocial, Voluntarios voluntario) {
		
		System.out.println("Id do evento: " + idSocial);
		System.out.println(voluntario);
		
		Optional<Social> opt = sr.findById(idSocial);
		if(opt.isEmpty()) {
			return "redirect:/social";
		}
		
		Social social = opt.get();
		voluntario.setSocial(social);
		
		vr.save(voluntario);
		
		return "redirect:/social/{idSocial}";
	}

}
