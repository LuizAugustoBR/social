package com.social.social.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SocialController {

	@RequestMapping("/social/form")
	public String form() {
		return "formCadastro";
	}

}
