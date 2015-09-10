package com.acc.internship.handler;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.acc.internship.controller.RoleSchema;
import com.acc.internship.controller.SchemaFactory;

@Component
public class AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Override
    protected void handle(HttpServletRequest request, 
      HttpServletResponse response, Authentication authentication) throws IOException {
        String targetUrl = getTargetUrl(authentication);
  
        if (response.isCommitted()) {
            return;
        }
  
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }
	
	protected String getTargetUrl(Authentication authentication){
		String url = "";
		@SuppressWarnings("unchecked")
		List<GrantedAuthority> authorities = (List<GrantedAuthority>) authentication.getAuthorities();
	
		if(authorities.size() > 0){
			SchemaFactory schemaFactory = new SchemaFactory();
			RoleSchema schema = schemaFactory.getSchema(authorities.get(0).getAuthority());
			url = "/"+schema.getRoleSchema();
		}
		
		return url;
	}
}
