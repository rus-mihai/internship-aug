package com.acc.internship.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.acc.internship.model.Station;
import com.acc.internship.repo.StationDAO;


@Controller
public class AdminStationController {
	
	@Autowired
	private StationDAO stationDao;
	
	@RequestMapping(value = "/admin/newstation", method = RequestMethod.GET)
	public String newStationGet(Model model) {
		model.addAttribute("station", new Station());
		List<Station> stations = stationDao.list();
		model.addAttribute("stations", stations);
		model.addAttribute("page", "newstation");
		return "admin";

	}

	@RequestMapping(value = "/admin/newstation", method = RequestMethod.POST)
	public String newStationPost(@ModelAttribute Station station, Model model) {
		stationDao.add(station);
		model.addAttribute("stations",stationDao.list());
		model.addAttribute("success","Station added");
		model.addAttribute("page", "newstation");
		return "admin";
	}

	@RequestMapping(value = "/admin/newstation/delete-station", method = RequestMethod.GET)
	public String deleteStation(@RequestParam("id") Integer id, @ModelAttribute Station station,
			BindingResult bindingResult, HttpServletRequest request, Model model) {
		try {
			stationDao.delete(id);
		} catch (DataIntegrityViolationException e) {
			List<Station> stations = stationDao.list();
			model.addAttribute("stations", stations);
			model.addAttribute("used", e.getMessage());
			model.addAttribute("page", "newstation");
			return "admin";
		}

		return "redirect:" + request.getHeader("Referer");
	}
}
