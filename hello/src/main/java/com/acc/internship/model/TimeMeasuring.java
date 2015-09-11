package com.acc.internship.model;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import com.acc.internship.repo.RecordDAO;

public class TimeMeasuring {

	private RecordDAO recordDao;
	
	public TimeMeasuring(RecordDAO d) {
		recordDao = d;
	}

	public Time getAverageTourForRouteByHour(int forHou, Route route) {
		int sum = 0;
		List<Integer> li = recordDao.listTour(route, forHou);
		if (li != null) {
			for (Integer i : li) {	
				sum = sum + i;
			}
			if (sum != 0) {
				int result = (sum / (li.size())) * 1000;
				Time time = new Time(result);
				return time;
			} else {
				return new Time(0);
			}
		} else {
			return new Time(0);
		}

	}

	public Time getAverageReTourForRouteByHour(int forHou, Route route) {
		int sum = 0;
		List<Integer> li = recordDao.listReTour(route, forHou);
		if (li != null) {
			for (Integer i : li) {
				sum = sum + i;
			}
			if (sum != 0) {
				int result = (sum / (li.size())) * 1000;
				Time time = new Time(result);
				return time;
			} else {
				return new Time(0);
			}
		} else {
			return new Time(0);
		}

	}

	public List<Time> getReportTourByHourForRoute(int idRoute) {
		ArrayList<Time> durations = new ArrayList<Time>();

		for (int i = 0; i < 24; i++) {
			Route r = new Route();
			r.setId(idRoute);
			Time s = getAverageTourForRouteByHour(i, r);
			if (s != null) {
				durations.add(s);
			} else {
				durations.add(new Time(0));
			}
		}
		return durations;
	}

	public List<Time> getReportReTourByHourForRoute(int idRoute) {
		ArrayList<Time> durations = new ArrayList<Time>();

		for (int i = 0; i < 24; i++) {
			Route r = new Route();
			r.setId(idRoute);
			Time s = getAverageReTourForRouteByHour(i, r);
			if (s != null) {
				durations.add(s);
			} else {
				durations.add(new Time(0));
			}
		}
		return durations;
	}

}
