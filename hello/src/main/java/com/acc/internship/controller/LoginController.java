package com.acc.internship.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.acc.internship.model.User;
import com.acc.internship.repo.UserDAO;

/**
 * Handle request on login page
 * @author cristian-eugen.groza
 *
 */

@Controller
public class LoginController {
	
	private UserDAO userDao;

//	@RequestMapping(value = "/login", method = RequestMethod.GET)
//    public String login() {
//    	
//    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//
//		String username = auth.getName();
//
//		User user = userDao.findByUsername(username);
//		
//        return user.getId()==1 ? "redirect:/driver" : "redirect:/admin";
//    }
	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String autoRedirect(Model model){
		boolean isLoggedIn = false;
		RoleSchema schema = null;
		
		if(SecurityContextHolder.getContext().getAuthentication() != null){
			List<GrantedAuthority> authorities= (List<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
			if(authorities.size() > 0){
				
				SchemaFactory schemaFactory = new SchemaFactory();
				schema = schemaFactory.getSchema(authorities.get(0).getAuthority());
				
			}
			
			
		}
		model.addAttribute("page", schema.getBasePage());
		return schema.getRoleSchema();
	}
}
