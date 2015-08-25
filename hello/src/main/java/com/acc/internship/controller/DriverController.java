package com.acc.internship.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import com.acc.internship.model.PasswordVerify;
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

<<<<<<< HEAD
	

	@RequestMapping( value = "/driver",method = RequestMethod.GET)
	public String newDriverGet(ModelMap model) {
		
=======
	@RequestMapping(value = "/driver", method = RequestMethod.GET)
	public String newDriverGet(Model model) {
>>>>>>> weekk3

		List<Route> routes = routeDao.list();
		model.addAttribute("routes", routes);

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		String username = auth.getName();

		User user = userDao.findByUsername(username);
<<<<<<< HEAD
		User user2 = userDao.findByUsername(username);

		model.addAttribute("userupdate", user2);
		model.addAttribute("passupdate", user);
		
=======

		PasswordVerify pass = new PasswordVerify();
		pass.setId(user.getId());

		model.addAttribute("userupdate", user);
		model.addAttribute("passupdateoptions", pass);
>>>>>>> weekk3

		return "driver";

	}

<<<<<<< HEAD
	@RequestMapping(value = {"/driver"},method = RequestMethod.POST)
	public String updateDriverPost(User userupdate, BindingResult result ) {
		
		userDao.update(userupdate);
		return "redirect:/driver";
	}
	
	@RequestMapping(value = {"/driver/updatepass"},method = RequestMethod.POST)
	public String updatePassPost(User passupdate, BindingResult result, Model model) {

		passupdate.setPassword(new BCryptPasswordEncoder().encode(passupdate.getPassword()));
		userDao.updatepass(passupdate);
//		model.addAttribute("userupdate", passupdate);
//		model.addAttribute("passupdate", passupdate);
		return "redirect:/driver";
	}
=======
	@RequestMapping(value = { "/driver" }, method = RequestMethod.POST)
	public String updateDriverPost(User userupdate, BindingResult result) {

		userDao.update(userupdate);
		return "redirect:/driver";
	}

	@RequestMapping(value = { "/driver/updatepass" }, method = RequestMethod.POST)
	public String updatePassPost(PasswordVerify passupdateoptions, BindingResult result, Model model) {

		String old = passupdateoptions.getPasswordold();
		String nPass = passupdateoptions.getPasswordnew();
		String confirm = passupdateoptions.getConfirm();
		
		User user = userDao.get(passupdateoptions.getId());
		
		if(new BCryptPasswordEncoder().matches(old, user.getPassword())){
			if(nPass.equals(confirm)){
				user.setPassword(new BCryptPasswordEncoder().encode(nPass));
				userDao.updatepass(user);
			}
		}
		return "redirect:/driver";
	}
>>>>>>> weekk3

}
