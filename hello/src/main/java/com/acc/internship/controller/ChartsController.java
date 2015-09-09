package com.acc.internship.controller;

import java.sql.Time;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.acc.internship.model.TimeMeasuring;
import com.acc.internship.repo.RecordDAO;

@RestController
public class ChartsController {

	@Autowired
	private RecordDAO recordDao;

	private TimeMeasuring timemeasuring;

	@RequestMapping("/chartstour")
	public List<Time> chartsTour(@RequestParam("id") Integer id) {
		timemeasuring = new TimeMeasuring(recordDao);
		List<Time> averageRecords = timemeasuring.getReportTourByHourForRoute(id);
		return averageRecords;
	}

	@RequestMapping("/chartsretour")
	public List<Time> chartsRetour(@RequestParam("id") Integer id) {
		timemeasuring = new TimeMeasuring(recordDao);
		List<Time> averageRecords = timemeasuring.getReportReTourByHourForRoute(id);
		return averageRecords;
	}
}
