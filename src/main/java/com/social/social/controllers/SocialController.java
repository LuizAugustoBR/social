package com.social.social.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.social.social.models.Social;
import com.social.social.models.Voluntarios;
import com.social.social.repositories.SocialRepository;
import com.social.social.repositories.VoluntarioRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/social")
public class SocialController {

	@Autowired
	private SocialRepository sr;
	@Autowired
	private VoluntarioRepository vr;

	@GetMapping("/form")
	public String form(Social social) {
		return "social/formSocial";
	}

	@PostMapping
	public String salvar(@Valid Social social, BindingResult result, RedirectAttributes attributes) {
		
		if(result.hasErrors()) {
			return form(social);
		}

		System.out.println(social);
		sr.save(social);
		attributes.addFlashAttribute("mensagem", "Projeto salvo com sucesso!");

		return "redirect:/social";
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
		
		List<Voluntarios> voluntarios = vr.findBySocial(social);
		md.addObject("voluntarios", voluntarios);

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
	@GetMapping("/{id}/remover")
	public String apagarEvento(@PathVariable Long id, RedirectAttributes attributes) {
		
		Optional<Social> opt = sr.findById(id);
		
		if(!opt.isEmpty()) {
			Social social = opt.get();
			
			List<Voluntarios> Voluntarios = vr.findBySocial(social);
			
			vr.deleteAll(Voluntarios);
			sr.delete(social);
			attributes.addFlashAttribute("mensagem", "Projeto removido com sucesso");
		}
		
		return "redirect:/social";
	}
	
	@GetMapping("/{id}/selecionar")
	public ModelAndView selecionarSocial(@PathVariable Long id) {
		ModelAndView md = new ModelAndView();
		Optional<Social> opt = sr.findById(id);
		if(opt.isEmpty()) {
			md.setViewName("redirect:/social");
			return md;
		}
		
		Social social = opt.get();
		md.setViewName("social/formSocial");
		md.addObject("social", social);
		
		return md;
		
	}
	
}
