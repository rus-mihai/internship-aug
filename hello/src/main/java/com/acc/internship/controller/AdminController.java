package com.acc.internship.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
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
	
	@MessageMapping("/admin/realtime")
	@SendTo("/admin/topic/view")
	public String realtime(Model model){
		
		return null;
	}
	
	@RequestMapping(value = "/admin/realtime")
	public String adminRealTime(Model model) {
		
		return "realtime";
	}
	
}
