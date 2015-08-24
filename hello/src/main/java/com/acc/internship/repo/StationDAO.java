package com.acc.internship.repo;

import java.util.List;

import com.acc.internship.model.Station;

public interface StationDAO {
	public Station get(int id);
	public List<Station> list();
	public void add(Station r);
	public void delete(int id);
	
}
