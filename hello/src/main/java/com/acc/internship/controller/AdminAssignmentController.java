package com.acc.internship.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.acc.internship.model.Assignment;
import com.acc.internship.model.Route;
import com.acc.internship.model.User;
import com.acc.internship.repo.AssignmentDAO;
import com.acc.internship.repo.RouteDAO;
import com.acc.internship.repo.UserDAO;

@Controller
public class AdminAssignmentController {
	
	@Autowired
	private UserDAO userDao;
	
	@Autowired
	private AssignmentDAO assignmentDao;
	
	@Autowired
	private RouteDAO routeDao;
	
	
	@RequestMapping(value = "/admin/assign-route", method = RequestMethod.GET)
	public String assignRouteSelectUser(Model model) {

		List<User> users = userDao.list();

		model.addAttribute("users", users);
		model.addAttribute("page", "assign-route");
		return "admin";
	}

	@RequestMapping(value = "/admin/assign-route/driver", method = RequestMethod.GET)
	public String assignRouteSelectUser(@RequestParam("id") Integer id, Model model) {

		List<Route> routes = routeDao.list();
		List<Assignment> assignments = assignmentDao.listRouteForUser(id);
		User user = userDao.get(id);
		
		HashMap<Integer, Route> routesMap = new HashMap<Integer, Route>();
		
		for(Route r: routes){
			routesMap.put(r.getId(), r);
		}
		
		for(Assignment assignment: assignments){
			routesMap.remove(assignment.getRoute().getId());
		}
		
		routes = new ArrayList<Route>(routesMap.values());
		
		model.addAttribute("user", user);
		model.addAttribute("availableRoutes", routes);
		model.addAttribute("assignments", assignments);
		model.addAttribute("page", "assign-route-driver");
		return "admin";
	}

	@RequestMapping(value = "/admin/assign-route-driver/unasign", method = RequestMethod.GET)
	public String unasignRoute(@RequestParam("id") Integer id, HttpServletRequest request, Model model) {

		assignmentDao.delete(id);
		return "redirect:" + request.getHeader("Referer");
	}

	@RequestMapping(value = "/admin/assign-route-driver/asign", method = RequestMethod.GET)
	public String assignRoute(@RequestParam("id") Integer id, @RequestParam("idDriver") Integer idDriver,
			HttpServletRequest request, Model model) {
		Assignment a = new Assignment();
		Route route = routeDao.get(id);

		User user = userDao.get(idDriver);

		a.setDriver(user);
		a.setRoute(route);
		assignmentDao.add(a);
		return "redirect:" + request.getHeader("Referer");
	}
}
