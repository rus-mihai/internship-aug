package com.acc.internship.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
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
		//Crypt password
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		userDao.add(user);
		model.addAttribute("page", "newdriver");		
		return "admin";
	}
	
	@RequestMapping(value = "/admin/newroute", method = RequestMethod.GET)
	public String newRouteGet(Model model){
		
		List<Station> stations = stationDao.list();
		Route r=  new Route();
		r.setStart(new Station());
		r.setEnd(new Station());
		
		model.addAttribute("stations", stations);
		model.addAttribute("route", r);
		model.addAttribute("page", "newroute");
		return "admin";
	}
	
	
	@RequestMapping(value="/admin/newroute", method = RequestMethod.POST)
	public String newRoutePost(@ModelAttribute("route") @Valid Route route,BindingResult bindingResult, Model model){
		if(bindingResult.hasErrors()){
			List<ObjectError> error = bindingResult.getAllErrors();
			for(ObjectError e: error){
				System.out.println(e.toString());
			}
		}else{
			
			routeDao.add(route);
		}
		
		//after adding prepare a new add
		
		List<Station> stations = stationDao.list();
		System.out.println(stations.get(0));
		Route r=  new Route();
		r.setStart(new Station());
		r.setEnd(new Station());
		
		model.addAttribute("stations", stations);
		model.addAttribute("route", r);
		model.addAttribute("page", "newroute");
		
		return "admin";
	}
	
	@RequestMapping(value = "/admin/deleteroute")
	public String deleteRoute(@RequestParam("id") Integer id, Model model){
		
		System.out.println(id);
		routeDao.delete(id);
		
		
		return "redirect:/admin/routes";
	}
	
	@RequestMapping(value = "/admin/view-edit-route", method = RequestMethod.GET)
	public String viewEditRoute(@RequestParam("id") Integer id, @RequestParam(value = "edit", required = false) String edit, Model model){
		if(edit != null){
			model.addAttribute("stations", stationDao.list());
		}
		
		Route route = routeDao.get(id);
		model.addAttribute("route", route);
		
		model.addAttribute("page", "view-edit-route");
		return "admin";
	}
	
	@RequestMapping(value = "/admin/view-edit-route", method = RequestMethod.POST)
	public String updateRoute(@ModelAttribute("route") Route route, Model model){
		
		System.out.println(route.getId() + route.getDuration());
		routeDao.update(route);
		model.addAttribute("page", "routes");
		return "admin";
	}
	
	@RequestMapping(value = "/admin/userview", method = RequestMethod.GET)
	public String userView(Model model) {
		List<User> users = userDao.list();
		model.addAttribute("users", users);
		model.addAttribute("page", "userview");
		return "admin";

	}
	

}
