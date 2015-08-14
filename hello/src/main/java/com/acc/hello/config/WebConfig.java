package com.acc.hello.config;

import javax.servlet.ServletContext;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

@Configuration
@EnableWebMvc
public class WebConfig {
	@Bean
	public ServletContextTemplateResolver templateResolver(){
		ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver();		
		
		
		templateResolver.setPrefix("/WEB-INF/templates");
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode("HTML5");
		
		return templateResolver;
	}
	
	@Bean
	public SpringTemplateEngine template(){
		SpringTemplateEngine template = new SpringTemplateEngine();
		template.setTemplateResolver(templateResolver());
		return template;
	}
	
	@Bean
	public ThymeleafViewResolver resolver(){
		ThymeleafViewResolver view = new ThymeleafViewResolver();
		view.setTemplateEngine(template());
		return view;
	}
	
	
	
 
	
}
