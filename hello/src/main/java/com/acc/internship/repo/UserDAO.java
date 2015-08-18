package com.acc.internship.repo;

import java.util.List;

import com.acc.internship.model.User;

public interface UserDAO {
	public User findByUsername(String username);
	public List<User> list();
	public User login(String username, String password);
	public User get(int id);
	public void add(User u);
	public void delete(int id);
}
