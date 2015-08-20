package com.acc.internship.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.acc.internship.model.Station;
import com.acc.internship.repo.RouteDAO;
import com.acc.internship.repo.StationDAO;
import com.acc.internship.repo.UserDAO;

@Controller
public class AdminController {

		@Autowired
		private StationDAO stationDao;
		
		@Autowired
		private RouteDAO routeDao;
		
		@Autowired
		private UserDAO userDao;
		
		@RequestMapping(value="/admin?routes")
		public String adminRoutes(Model model){
			model.addAttribute("proba", "asta sa apara");
			
			return "/admin?routes";
		}
		
		@RequestMapping(value="/admin", method=RequestMethod.GET)
		public String stationForm(Model model){
			
			Station s = new Station();
			model.addAttribute("station",s);
			
			return "admin";
		}
		
	   @RequestMapping(value="/admin", method=RequestMethod.POST)
	    public String stationSubmit(@ModelAttribute Station station, Model model) {
	       	stationDao.add(station);
	       	//model.addAttribute("station", station);
	        return "admin";
	    }
	   
	   

	
	
}
