package com.acc.internship.controller;

import java.sql.Time;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.acc.internship.repo.RecordDAO;


@RestController
public class ChartsController {

	@Autowired
	private RecordDAO recordDao;
	
	
	@RequestMapping("/chartstour")
	public List<Time> chartsTour(@RequestParam("id") Integer id){
		List<Time> averageRecords = recordDao.getReportTourByHourForRoute(id);
		return averageRecords;
	}
	
	@RequestMapping("/chartsretour")
	public List<Time> chartsRetour(@RequestParam("id") Integer id){
		List<Time> averageRecords = recordDao.getReportRetourByHourForRoute(id);
		return averageRecords;
	}
}
