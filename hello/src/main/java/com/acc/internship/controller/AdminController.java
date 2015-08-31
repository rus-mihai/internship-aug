package com.acc.internship.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class AdminController {
	
	@PreAuthorize("hasAuthority('admin')")
	@RequestMapping(value = "/admin")
	public String adminDash(Model model) {
		model.addAttribute("page", "routes");
		return "admin";
	}
}
