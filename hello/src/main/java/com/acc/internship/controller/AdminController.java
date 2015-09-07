package com.acc.internship.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.acc.internship.model.Record;
import com.acc.internship.model.RecordFeeding;
import com.acc.internship.repo.RecordDAO;
import com.acc.internship.repo.RouteDAO;



@Controller
public class AdminController {
	
	
	@Autowired
	private RecordDAO recordDao;
	
	@Autowired
	private RouteDAO routeDao;
	
	
	@PreAuthorize("hasAuthority('admin')")
	@RequestMapping(value = "/admin")
	public String adminDash(Model model) {
		model.addAttribute("page", "routes");
		return "admin";
	}
	
	
	
	@MessageMapping("/admin/messages")
	@SendTo("/admin/topic/message")
	public String messages(Model model){
		
		return null;
	}
	
	@RequestMapping(value = "/admin/messages")
	public String adminMessages(Model model) {
		
		return "messages";
	}
	
	@MessageMapping("/admin/realtime")
	@SendTo("/admin/topic/view")
	public String realtime(Model model){
		
		return null;
	}
	
	@RequestMapping(value = "/admin/realtime")
	public String adminRealTime(Model model) {
		
		return "realtime";
	}
	
	@RequestMapping(value = "/admin/feed")
	public String feedRecords(Model model){
		
		RecordFeeding recordFeeding = new RecordFeeding(routeDao);
		for(int i=0; i<100; i++){
			Record r = recordFeeding.getRecord();
			recordDao.add(r);
		}
		
		model.addAttribute("page", "routes");
		return "admin";
	}
	
	
	
}
