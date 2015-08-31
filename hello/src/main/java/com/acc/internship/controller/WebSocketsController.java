package com.acc.internship.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.acc.internship.model.Station;
import com.acc.internship.repo.StationDAO;

@Controller
public class WebSocketsController {
	
	@Autowired
	private StationDAO stationDao;
	
	@MessageMapping("/admin/realtime")
	@SendTo("/admin/topic/view")
	public Station station(Station station){
		
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Station s = stationDao.list().get(1);
		return s;
	}
}
