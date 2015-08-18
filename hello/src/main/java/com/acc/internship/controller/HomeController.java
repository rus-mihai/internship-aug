package com.acc.internship.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.acc.internship.model.User;
import com.acc.internship.repo.UserDAO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	@Autowired
	private  UserDAO userDao;
	
	@RequestMapping("/hello")
	public String hello(@RequestParam(value="name", required=false, defaultValue="world") String name, Model model){
		model.addAttribute("name", name);
		return "hello";
	}
	
	@RequestMapping("/")
	public String root(Model model){
		model.addAttribute("fp", "Prima pagina");
		model.addAttribute("hellow", "/hello");
		
		
		return "index";
		
	}

	
	@RequestMapping("/login")
	public String login(Model model){
//		model.addAttribute("fp", "Prima pagina");
//		model.addAttribute("hellow", "/hello");
		return "login";
		
	}
	
	@RequestMapping("/admin")
	public String admin(Model model){
		
		return "admin";
	}
	
}
