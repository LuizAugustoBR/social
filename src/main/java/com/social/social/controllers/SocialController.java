package com.social.social.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.social.social.models.Social;
import com.social.social.repositories.SocialRepository;

@Controller
public class SocialController {
	
	@Autowired
	private SocialRepository sr;

	@RequestMapping("/social/form")
	public String form() {
		return "social/formSocial";
	}
	
	@PostMapping("/social")
	public String adicionar(Social social) {
		
		System.out.println(social);
		sr.save(social);
				
		return "social/evento-adicionado";
	}

}
