package com.acc.internship.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.acc.internship.model.Route;
import com.acc.internship.model.Station;
import com.acc.internship.model.User;
import com.acc.internship.repo.RouteDAO;
import com.acc.internship.repo.StationDAO;
import com.acc.internship.repo.UserDAO;
import com.acc.internship.repo.UserRoleDAO;

@Controller
public class AdminController {

	@Autowired
	private StationDAO stationDao;

	@Autowired
	private RouteDAO routeDao;

	@Autowired
	private UserDAO userDao;

	@Autowired
	private UserRoleDAO userRoleDao;	

	
	//NEW
	
	@RequestMapping(value = "/admin")
	public String adminDash(Model model) {
		model.addAttribute("page", "routes");
		return "admin";
	}
	
	@RequestMapping(value = "admin/routes")
	public String adminViewRoutes(Model model){
		model.addAttribute("page", "routes");
		List<Route> list = routeDao.list();
		model.addAttribute("routes", list);
		return "admin";
	}
	
	@RequestMapping(value = "/admin/newstation", method = RequestMethod.GET)
	public String newStationGet(Model model) {
		model.addAttribute("station", new Station());
		model.addAttribute("page","newstation");
		return "admin";
		
	}
	
	@RequestMapping(value = "/admin/newstation", method = RequestMethod.POST)
	public String newStationPost(@ModelAttribute Station station, Model model){
		stationDao.add(station);
		model.addAttribute("page", "newstation");		
		return "admin";
	}
	
	@RequestMapping(value = "/admin/adduser", method = RequestMethod.GET)
	public String newDriverGet(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("page","newdriver");
		return "admin";
		
	}
	
	@RequestMapping(value = "/admin/adduser", method = RequestMethod.POST)
	public String newDriverPost(@ModelAttribute User user, Model model){
		//set role to driver
		user.setRole(userRoleDao.list().get(1));
		userDao.add(user);
		model.addAttribute("page", "newdriver");		
		return "admin";
	}
	
	

}
