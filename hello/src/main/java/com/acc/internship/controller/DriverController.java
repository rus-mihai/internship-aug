package com.acc.internship.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

	@RequestMapping(value = "/driver/driverview", method = RequestMethod.GET)
	public String driverView(Model model) {

		List<Route> routes = routeDao.list();
		model.addAttribute("routes", routes);

		return "driver";

	}
	
	@RequestMapping(value = "/driver", method = RequestMethod.GET)
	public String newDriverGet(Model model) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		String username = auth.getName();
		
		User user = userDao.findByUsername(username);
		
		model.addAttribute("userupdate", user);
		
		return "driver";

	}

	@RequestMapping(value = "/driver", method = RequestMethod.POST)
	public String newDriverPost(@ModelAttribute("userupdate") User userupdate , Model model) {

		userDao.update(userupdate);
		System.out.println(userupdate.getId());
		return "driver";
	}


}
