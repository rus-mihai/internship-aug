package com.acc.internship.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.acc.internship.model.Route;
import com.acc.internship.model.User;
import com.acc.internship.repo.RouteDAO;
import com.acc.internship.repo.StationDAO;
import com.acc.internship.repo.UserDAO;

@Controller
public class DriverController {

	@Autowired
	private UserDAO userDao;

	@Autowired
	private RouteDAO routeDao;

	@Autowired
	private StationDAO stationDao;

	

	@RequestMapping( value = "/driver",method = RequestMethod.GET)
	public String newDriverGet(ModelMap model) {
		

		List<Route> routes = routeDao.list();
		model.addAttribute("routes", routes);

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		String username = auth.getName();

		User user = userDao.findByUsername(username);
		User user2 = userDao.findByUsername(username);

		model.addAttribute("userupdate", user2);
		model.addAttribute("passupdate", user);
		

		return "/driver";

	}

	@RequestMapping(value = {"/driver"},method = RequestMethod.POST)
	@ResponseBody
	public String newDriverPost(
			@ModelAttribute("userupdate") User userupdate,
			@ModelAttribute("passupdate") User passupdate, ModelMap model) {

		passupdate.setPassword(new BCryptPasswordEncoder().encode(passupdate.getPassword()));
		userDao.updatepass(passupdate);
		
		userDao.update(userupdate);
		return "driver";
	}

}
