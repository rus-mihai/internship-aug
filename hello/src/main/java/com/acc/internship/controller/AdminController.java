package com.acc.internship.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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

	@RequestMapping(value = "/admin?routes")
	public String adminRoutes(Model model) {
		model.addAttribute("proba", "asta sa apara");

		return "/admin?routes";
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String stationForm(@RequestParam(value="viewuser",required=false) String viewuser,Model model) {
		
		
		List<User> users = userDao.list();

		model.addAttribute("users", users);
		
		Station s = new Station();
		model.addAttribute("station", s);

		User user = new User();
		model.addAttribute("user", user);

		return "admin";
	}

	@RequestMapping(value = "/admin/newstation", method = RequestMethod.POST)
	public String stationSubmit(@ModelAttribute Station station, Model model) {

		stationDao.add(station);
		// model.addAttribute("station", station);
		return "admin";
	}

	@RequestMapping(value = "/admin/adduser", method = RequestMethod.POST)
	public String userSubmit(@ModelAttribute User user, Model model) {

		user.setRole(userRoleDao.list().get(1));
		String pass = new BCryptPasswordEncoder().encode(user.getPassword());
		user.setPassword(pass);
		userDao.add(user);
		// model.addAttribute("station", station);
		return "redirect:/admin?adduser";
	}

	@RequestMapping(value = "/driver", method = RequestMethod.GET)
	public String DriverRequest(Model model) {
		
		
		List<User> users = userDao.list();

		model.addAttribute("users", users);
		
		

		return "driver";
	}
	
}
