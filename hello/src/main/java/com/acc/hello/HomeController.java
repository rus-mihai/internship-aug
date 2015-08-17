package com.acc.hello;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.acc.hello.model.AbstractUser;
import com.acc.hello.model.UserDAO;

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
		
		AbstractUser au = new AbstractUser(1,"nume", "prenume", "username", "password");
		
		userDao.saveOrUpdate(au);
		
		AbstractUser u1 = userDao.get(3);
		System.out.println("get: " + u1.getName());
		
		System.out.println("lista");
		List<AbstractUser> users = userDao.list();
		for(AbstractUser a: users){
			System.out.println(a.getName());
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
