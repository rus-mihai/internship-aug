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

@Component
public class AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Override
    protected void handle(HttpServletRequest request, 
      HttpServletResponse response, Authentication authentication) throws IOException {
        String targetUrl = getTargetUrl(authentication);
  
        if (response.isCommitted()) {
            System.out.println("Can't redirect");
            return;
        }
  
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }
	
	protected String getTargetUrl(Authentication authentication){
		String url = "";
		List<GrantedAuthority> authorities = (List<GrantedAuthority>) authentication.getAuthorities();
	
		//System.out.println("test");
		if(authorities.size() > 0){
			System.out.println(authorities.get(0).getAuthority());
			if(authorities.get(0).getAuthority().contains("admin")){
				url = "/admin";
			}else{
				url = "/driver";
			}
		}
		
		return url;
	}
}
