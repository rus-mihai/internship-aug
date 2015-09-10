package com.acc.internship.repo;

import java.util.List;
import com.acc.internship.model.Record;
import com.acc.internship.model.Route;


public interface RecordDAO {
	public Record getRecord(int id);
	public List<Record> list();
	public void add(Record record);
	public List<Integer> listTour(Route route,int forHou);
	public List<Integer> listReTour(Route route,int forHou);
	
}
