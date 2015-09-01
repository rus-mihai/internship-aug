package com.acc.internship.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.acc.internship.model.Record;
import com.acc.internship.model.Route;
import com.acc.internship.repo.RecordDAO;

@Controller
public class ActionController {
	
	@Autowired
	private RecordDAO recordDao;
	
	@RequestMapping(value = "/driver/actions",method = RequestMethod.GET)
	public String root(@RequestParam("id") Integer id, Model model) {
		Route r=new Route();
		r.setId(id);
		Record rec=new Record();
		rec.setRouterecord(r);
		model.addAttribute("report",rec);
		return "actions";

	}
	
	@RequestMapping(value = "/driver/actions/submitReport",method = RequestMethod.POST)
	public String timeReport(@ModelAttribute("report") Record report, BindingResult bindingResult, Model model) {
		report.startFormat();
		report.pauseFormat();
		report.stopFormat();
		
		recordDao.add(report);
		return "redirect:/driver";

	}
}
