package com.acc.internship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;

import com.acc.internship.model.User;
import com.acc.internship.repo.UserDAO;
@SpringBootApplication

public class Application {
	
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		
		
		

	}

}
