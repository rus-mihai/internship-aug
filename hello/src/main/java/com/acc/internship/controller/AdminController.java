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
		
		
		@RequestMapping(value="/admin", method=RequestMethod.GET)
		public String stationForm(Model model){
			
			Station s = new Station();
			model.addAttribute("station",s);
			
			User user = new User();
			model.addAttribute("user",user);
			
			return "admin";
		}
		
	   @RequestMapping(value="/admin/newstation", method=RequestMethod.POST)
	    public String stationSubmit(@ModelAttribute Station station, Model model) {
		   
	       	stationDao.add(station);
	       	
	       	model.addAttribute("success", true);

	       	//model.addAttribute("station", station);
	        return "redirect:/admin?newstation";
	    }
	   
		
	   @RequestMapping(value="/admin/adduser", method=RequestMethod.POST)
	    public String userSubmit(@ModelAttribute User user, Model model) {

		   	user.setRole(userRoleDao.list().get(1));
		   	String pass = new BCryptPasswordEncoder().encode(user.getPassword());
		   	user.setPassword(pass);
	       	userDao.add(user);
	       	//model.addAttribute("station", station);
	        return "redirect:/admin?adduser";
	    }
	   
	   
	   @RequestMapping(value="/admin/routes", method = RequestMethod.GET)
	   public String getRoutes(Model model){
		   
		   model.addAttribute("routes");
		   return "admin";
	   }
	   
	   
	   @RequestMapping(value="/admin/routes")
	   public String viewRoutes(Model model){
			   List<Route> list  = routeDao.list();
			   
			   
			   for(Route r: list){
				   System.out.println(r.getStart().getName() + " " + r.getEnd().getName());
			   }
			   model.addAttribute("routes", list);

		   
		   return "admin";
	   }
	   
	   

	
	
}
