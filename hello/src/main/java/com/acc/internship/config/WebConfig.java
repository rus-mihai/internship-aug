package com.acc.internship.config;

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
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/js/**").addResourceLocations("/js/");
		registry.addResourceHandler("/css/**").addResourceLocations("/css/");
		registry.addResourceHandler("/img/**").addResourceLocations("/img/");
		registry.addResourceHandler("/images/**").addResourceLocations("/images/");
		registry.addResourceHandler("/SOURCE FILES/**").addResourceLocations("/SOURCE FILES/");
		registry.addResourceHandler("/**").addResourceLocations("/");
	
	}
	
	@Bean
	public ClassLoaderTemplateResolver templateResolver(){
		ClassLoaderTemplateResolver  templateResolver = new ClassLoaderTemplateResolver();
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
