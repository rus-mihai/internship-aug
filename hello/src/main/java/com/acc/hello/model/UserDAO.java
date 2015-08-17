package com.acc.hello.model;

import java.util.List;

public interface UserDAO {
	public List<AbstractUser> list();
	public AbstractUser get(int id);
	public void saveOrUpdate(AbstractUser u);
	public void delete(int id);
}
