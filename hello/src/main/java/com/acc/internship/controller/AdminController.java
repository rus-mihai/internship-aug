package com.acc.internship.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.acc.internship.model.Assignment;
import com.acc.internship.model.Route;
import com.acc.internship.model.Station;
import com.acc.internship.model.User;
import com.acc.internship.repo.AssignmentDAO;
import com.acc.internship.repo.RouteDAO;
import com.acc.internship.repo.StationDAO;
import com.acc.internship.repo.UserDAO;
import com.acc.internship.repo.UserRoleDAO;

@Controller
public class AdminController {
	
	@RequestMapping(value = "/admin")
	public String adminDash(Model model) {
		model.addAttribute("page", "routes");
		return "admin";
	}
}
