package com.acc.internship.controller;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handle request on login page
 * @author cristian-eugen.groza
 *
 */

@Controller
public class LoginController {
	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String autoRedirect(){
		boolean isLoggedIn = false;
		RoleSchema schema = null;
		
		if(SecurityContextHolder.getContext().getAuthentication() != null){
			List<GrantedAuthority> authorities= (List<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
			if(authorities.size() > 0){
				
				SchemaFactory schemaFactory = new SchemaFactory();
				schema = schemaFactory.getSchema(authorities.get(0).getAuthority());
				
			}
			
			
		}
		
		return schema.getRoleSchema();
	}
}
