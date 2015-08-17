package com.acc.internship.repo;

import java.util.List;

import com.acc.internship.model.AbstractUser;

public interface UserDAO {
	public List<AbstractUser> list();
	public AbstractUser login(String username, String password);
	public AbstractUser get(int id);
	public void add(AbstractUser u);
	public void delete(int id);
}
