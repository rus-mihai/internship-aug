package com.acc.internship.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.acc.internship.handler.AuthenticationSuccessHandler;


@Configuration
@EnableWebMvcSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	@Qualifier("userDetailsService")
	UserDetailsService userDetailsService;
	
	@Autowired
	AuthenticationSuccessHandler authenticationSuccessHandler;
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/js/**/*.{js}").antMatchers("/css/**").antMatchers("/img/**").antMatchers("/images/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
			.authorizeRequests()
				.antMatchers("/admin/**").access("hasRole('admin')")
				.antMatchers("/driver/**").access("hasRole('driver')")				
				.and()
					.formLogin()
					.loginPage("/login")
					.successHandler(authenticationSuccessHandler)
					.failureUrl("/login?error")
					.usernameParameter("username")
					.passwordParameter("password")
				.and()
					.logout()
					.logoutSuccessUrl("/login?logout")
				.and()
					.exceptionHandling()
					.accessDeniedPage("/403")
				.and()
					.csrf();
		
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
		
		//TODO password encode
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
	
}
