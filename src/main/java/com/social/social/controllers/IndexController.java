package com.social.social.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	@RequestMapping("/")
	public String home() {
		System.out.println("Chamou o método home");
		return "redirect:/social";
	}

}
