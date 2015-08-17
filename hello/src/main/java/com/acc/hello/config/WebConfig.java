package com.acc.hello.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.resourceresolver.ClassLoaderResourceResolver;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter{
	
//	@Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry){
//		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
//	}

	
	@Bean
	public ClassLoaderTemplateResolver templateResolver(){
		ClassLoaderTemplateResolver  templateResolver = new ClassLoaderTemplateResolver();
//		ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver();		
		
		templateResolver.setPrefix("templates/");
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode("HTML5");
		templateResolver.setOrder(1);
		
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
