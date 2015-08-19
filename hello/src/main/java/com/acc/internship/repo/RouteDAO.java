package com.acc.internship.repo;

import java.util.List;

import com.acc.internship.model.Route;

public interface RouteDAO {
	public Route get(int id);
	public List<Route> list();
	public void add(Route r);
	public void delete(int id);
}
