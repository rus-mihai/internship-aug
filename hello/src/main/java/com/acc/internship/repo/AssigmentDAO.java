package com.acc.internship.repo;

import java.util.List;

import com.acc.internship.model.Assigment;

public interface AssigmentDAO {
	public Assigment get(int id);
	public List<Assigment> list();
	public List<Assigment> listRouteForUser(int id);
	public void delete(int id);
	public void update(Assigment assigment);
	public void add(Assigment assigment);
}
