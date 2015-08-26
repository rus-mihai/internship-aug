package com.acc.internship.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.acc.internship.model.Assignment;
import com.acc.internship.model.Route;
import com.acc.internship.model.Station;
import com.acc.internship.model.User;
import com.acc.internship.repo.AssignmentDAO;
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
	
	@Autowired
	private AssignmentDAO assignmentDao;
	
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
		List<Station> stations = stationDao.list();
		model.addAttribute("stations", stations);
		model.addAttribute("page","newstation");
		return "admin";
		
	}
	
	@RequestMapping(value = "/admin/newstation", method = RequestMethod.POST)
	public String newStationPost(@ModelAttribute Station station, Model model){
		stationDao.add(station);

		model.addAttribute("page", "newstation");		
		return "admin";
	}
	
	@RequestMapping(value = "/admin/newstation/delete-station", method = RequestMethod.GET)
	public String deleteStation(@RequestParam("id") Integer id, HttpServletRequest request, Model model){
		stationDao.delete(id);
		return "redirect:"+request.getHeader("Referer");
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
		user.setUserRole(userRoleDao.list().get(1));
		//Crypt password
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		try{
			userDao.add(user);
		}catch(DuplicateKeyException e){
			model.addAttribute("exists", "User already exists");
		}

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
				
		Station start = stationDao.get(route.getStart().getId());
		route.setStart(start);
		Station end = stationDao.get(route.getEnd().getId());
		route.setEnd(end);
		
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
	
	@RequestMapping(value="/admin/userview/deleteuser", method= RequestMethod.GET)
	public String deleteUser(@RequestParam("id") Integer id, HttpServletRequest request, Model model){
		userDao.delete(id);
		return "redirect:"+request.getHeader("Referer");
	}
	
	@RequestMapping(value="/admin/userview/edituser", method= RequestMethod.GET)
	public String editUser(@RequestParam("id") Integer id, HttpServletRequest request, Model model){
		User user = userDao.get(id);
		
		model.addAttribute("user", user);
		model.addAttribute("page", "edituser");
		return "admin";
	}
	
	@RequestMapping(value = "/admin/userview/edituser", method = RequestMethod.POST)
	public String commitEditUser(@ModelAttribute User user, Model model, HttpServletRequest request){
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		userDao.update(user);
		userDao.updatepass(user);
		
		return "redirect:"+request.getHeader("Referer");
	}
	
	@RequestMapping(value = "/admin/assign-route", method = RequestMethod.GET)
	public String assignRouteSelectUser(Model model){
		
		List<User> users = userDao.list();
		
		model.addAttribute("users", users);
		model.addAttribute("page", "assign-route");
		return "admin";
	}
	
	@RequestMapping(value = "/admin/assign-route/driver", method = RequestMethod.GET)
	public String assignRouteSelectUser(@RequestParam("id") Integer id, Model model){
		
		List<Route> routes = routeDao.list();
		List<Assignment> assignments = assignmentDao.listRouteForUser(id);
		User user = userDao.get(id);
		
		model.addAttribute("user", user);
		model.addAttribute("availableRoutes", routes);
		model.addAttribute("assignments", assignments);
		model.addAttribute("page", "assign-route-driver");
		return "admin";
	}
	
	@RequestMapping(value = "/admin/assign-route-driver/unasign", method = RequestMethod.GET)
	public String assignRouteSelectUserDelete(@RequestParam("id") Integer id,HttpServletRequest request, Model model){
		
		assignmentDao.delete(id);
		return "redirect:"+request.getHeader("Referer");
	}
	
	@RequestMapping(value = "/admin/assign-route-driver/asign", method = RequestMethod.GET)
	public String assignRouteSelectUserAdd(@RequestParam("id") Integer id, @RequestParam("idDriver") Integer idDriver, HttpServletRequest request, Model model){
		Assignment a = new Assignment();
		Route route = routeDao.get(id);
		
		User user = userDao.get(idDriver);
		
		a.setDriver(user);
		a.setRoute(route);
		assignmentDao.add(a);
		return "redirect:"+request.getHeader("Referer");
	}
	
	
	
	
	
	
	
	

}
