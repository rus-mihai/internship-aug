package com.acc.internship.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.acc.internship.model.Route;
import com.acc.internship.model.Station;
import com.acc.internship.repo.RouteDAO;
import com.acc.internship.repo.StationDAO;

@Controller
public class AdminRouteController {

	@Autowired
	private RouteDAO routeDao;

	@Autowired
	private StationDAO stationDao;

	@RequestMapping(value = "/admin/routes")
	public String viewRoutes(Model model) {
		model.addAttribute("page", "routes");
		List<Route> list = routeDao.list();
		model.addAttribute("routes", list);
		return "admin";
	}

	@RequestMapping(value = "/admin/newroute", method = RequestMethod.GET)
	public String newRouteGet(Model model) {

		List<Station> stations = stationDao.list();
		Route r = new Route();
		r.setStart(new Station());
		r.setEnd(new Station());

		model.addAttribute("stations", stations);
		model.addAttribute("route", r);
		model.addAttribute("page", "newroute");
		return "admin";
	}

	@RequestMapping(value = "/admin/newroute", method = RequestMethod.POST)
	public String newRoutePost(@ModelAttribute("route") @Valid Route route, BindingResult bindingResult,
			HttpServletRequest request, Model model) {
		try {
			if(route.getStart().getId() != route.getEnd().getId()){
				routeDao.add(route);
			}else{
				bindingResult.rejectValue("start.name", "route.invalid", "Route cannot have same station on start and end!");
			}
		} catch (DataIntegrityViolationException e) {
			bindingResult.rejectValue("id", "route.duplicate", "Route no." + route.getId() + " already exists!");
		}

		if (bindingResult.hasErrors()) {
			List<Station> stations = stationDao.list();
			model.addAttribute("stations", stations);
			model.addAttribute("page", "newroute");
			return "admin";
		}

		model.addAttribute("success", "Route added");
		return "redirect:/admin/routes";
	}

	@RequestMapping(value = "/admin/deleteroute")
	public String deleteRoute(@RequestParam("id") Integer id, Model model) {
		routeDao.delete(id);

		return "redirect:/admin/routes";
	}

	@RequestMapping(value = "/admin/view-edit-route", method = RequestMethod.GET)
	public String viewRoute(@RequestParam("id") Integer id, @RequestParam(value = "edit", required = false) String edit,
			Model model) {
		if (edit != null) {
			model.addAttribute("stations", stationDao.list());
		}

		Route route = routeDao.get(id);
		model.addAttribute("route", route);

		model.addAttribute("page", "view-edit-route");
		return "admin";
	}

	@RequestMapping(value = "/admin/view-edit-route", method = RequestMethod.POST)
	public String updateRoute(@ModelAttribute("route") Route route, HttpServletRequest request, Model model) {

		Station start = stationDao.get(route.getStart().getId());
		route.setStart(start);
		Station end = stationDao.get(route.getEnd().getId());
		route.setEnd(end);

		routeDao.update(route);
		model.addAttribute("success", "Route updated");
		return "redirect:/admin/routes";
	}

}
