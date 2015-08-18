package com.acc.internship.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
		
		//AbstractUser au = new AbstractUser(1,"nume", "prenume", "username", "password");
		
		User au = userDao.login("username", "password");
		
		//userDao.add(au);
		
		if(au != null){
			System.out.println("ok");
		}else{
			System.out.println("xxxxx, fail login");
		}
		 
		return "fp";
		
	}

	
	@RequestMapping("/login")
	public String login(Model model){
//		model.addAttribute("fp", "Prima pagina");
//		model.addAttribute("hellow", "/hello");
		return "login";
		
	}
	
}
