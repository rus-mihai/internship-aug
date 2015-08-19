package com.acc.internship.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {

	@RequestMapping(value="/admin?routes")
	public String adminRoutes(Model model){
		model.addAttribute("proba", "asta sa apara");
		
		return "/admin?routes";
	}
	
}
