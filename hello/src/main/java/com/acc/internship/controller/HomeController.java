package com.acc.internship.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.acc.internship.model.Route;
import com.acc.internship.model.Station;
import com.acc.internship.repo.RouteDAO;
import com.acc.internship.repo.StationDAO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	//@Autowired
	//private  UserDAO userDao;
	
	@Autowired
	private StationDAO stationDao;
	
	@Autowired
	private RouteDAO routeDao;
	
	
	@RequestMapping("/hello")
	public String hello(@RequestParam(value="name", required=false, defaultValue="world") String name, Model model){
		model.addAttribute("name", name);
		return "hello";
	}
	
	@RequestMapping("/")
	public String root(Model model){
		//insert satations and list 
		Station s = new Station();
		s.setName("test3");

		stationDao.add(s);
		
		List<Station> list = stationDao.list();
		
		Route r = new Route();
		
		r.setId(35);
		r.setDuration("30");
		r.setStart(list.get(0));
		r.setEnd(list.get(1));
		
		routeDao.add(r);
		return "index";
		
	}

	
	
	@RequestMapping("/admin")
	public String admin(Model model){
		
		return "admin";
	}
	
	@RequestMapping("/driver")
	public String driver(Model model){
		
		return "driver";
	}
	
	
	
	
}
