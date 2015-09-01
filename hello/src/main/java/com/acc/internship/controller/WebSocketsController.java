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

}
