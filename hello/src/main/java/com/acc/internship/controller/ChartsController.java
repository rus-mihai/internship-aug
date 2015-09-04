package com.acc.internship.controller;

import java.sql.Time;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acc.internship.repo.RecordDAO;

@RestController
public class ChartsController {

	@Autowired
	private RecordDAO recordDao;
	
	@RequestMapping("/charts")
	public List<Time> charts(){
		List<Time> averageRecords = recordDao.getReportTourByHourForRoute(1);
		
		return averageRecords;
	}
}
