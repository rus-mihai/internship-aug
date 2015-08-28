package com.acc.internship.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ActionsController {

	
	@RequestMapping(value = "/driver/actions")
	public String root(Model model) {
		return "actions";

	}
}
