package com.acc.internship.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

	
	
	@RequestMapping("/admin")
	public String admin(Model model){
		
		return "admin";
	}
	
	@RequestMapping("/driver")
	public String driver(Model model){
		
		return "driver";
	}
	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String autoRedirect(){
		boolean isLoggedIn = false;
		
		if(SecurityContextHolder.getContext().getAuthentication() != null){
			List<GrantedAuthority> authorities= (List<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
			if(authorities.size() > 0){
				System.out.println(authorities.get(0).getAuthority());
				if(authorities.get(0).getAuthority().contains("admin")){
					return "admin";
				}else if(authorities.get(0).getAuthority().contains("driver")){
					return "driver";
				}
			}
			
			
		}
		
		return "login";
	}
	
}
