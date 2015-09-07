package com.acc.internship.controller;

import java.sql.Time;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.acc.internship.repo.RecordDAO;
import com.acc.internship.repo.RouteDAO;

@RestController
public class ChartsController {

	@Autowired
	private RecordDAO recordDao;
	
	@Autowired
	private RouteDAO routeDao;
	
	@RequestMapping("/charts")
	public List<Time> charts(@RequestParam("id") Integer id){
		List<Time> averageRecords = recordDao.getReportTourByHourForRoute(id);
		
		return averageRecords;
	}
}
