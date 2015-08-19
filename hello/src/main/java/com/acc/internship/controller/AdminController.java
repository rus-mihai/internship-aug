package com.acc.internship.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminController {

	@RequestMapping(value="/admin?routes")
	public String adminRoutes(Model model){
		model.addAttribute("proba", "asta sa apara");
		
		return "/admin?routes";
	}
	
	   @RequestMapping(value="/admin?newstation", method=RequestMethod.GET)
	    public String greetingForm(Model model) {
	       // model.addAttribute("fetchroute", new setRoute());
	        return "/admin?newstation";
	    }

	
	
}
