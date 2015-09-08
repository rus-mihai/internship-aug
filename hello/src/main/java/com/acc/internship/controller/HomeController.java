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

	@Autowired
	private StationDAO stationDao;
	
	@Autowired
	private RouteDAO routeDao;


	@RequestMapping("/403")
	public String accessDenied(Model model){
		
		return "403";
	}

	@RequestMapping("/")
	public String line(Model model) {
		List<Route> lines = routeDao.list();
		
		List<Station> stations = stationDao.list();

		model.addAttribute("stations", stations);
		model.addAttribute("lines", lines);
		model.addAttribute("title", "Home");	
		return "index";
	}
	
	
	@RequestMapping("/line")
	public String line(@RequestParam("id") Integer id, Model model){
		Route route = routeDao.get(id);
		List<Route> lines = routeDao.list();
		List<Station> stations = stationDao.list();
		
		model.addAttribute("stations", stations);
		model.addAttribute("route", route);
		model.addAttribute("lines", lines);
		model.addAttribute("title", "Line "+id);
		return "line";
	}
	


	@RequestMapping("/driver")
	public String driver(Model model) {

		return "driver";
	}
	


}
