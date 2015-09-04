package com.acc.internship.repo;

import java.sql.Time;
import java.util.List;

import com.acc.internship.model.Record;


public interface RecordDAO {
	public Record getRecord(int id);
	public List<Record> list();
	public void delete(int id);
	public void update(Record record);
	public void add(Record record);
	public void getAverageTourForRouteByHour(int idRoute, int hour);
	public List<Time> getReportTourByHourForRoute(int idRoute);
}
