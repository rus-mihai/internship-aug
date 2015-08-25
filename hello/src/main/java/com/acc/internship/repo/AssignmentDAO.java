package com.acc.internship.repo;

import java.util.List;

import com.acc.internship.model.Assignment;

public interface AssignmentDAO {
	public Assignment get(int id);
	public List<Assignment> list();
	public List<Assignment> listRouteForUser(int id);
	public void delete(int id);
	public void update(Assignment assigment);
	public void add(Assignment assigment);
}
