package com.social.social.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.social.social.models.Social;

@Controller
public class SocialController {

	@RequestMapping("/social/form")
	public String form() {
		return "formSocial";
	}
	
	@PostMapping("/social")
	public String adicionar(Social social) {
		
		System.out.println(social);
				
		return "evento-adicionado";
	}

}
