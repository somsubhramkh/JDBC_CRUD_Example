package com.niit.crudexample.dao;

import java.util.List;

import com.niit.crudexample.model.User;

public interface UserDAO {

	public List<User> listUsers();
	
	public User getUser(int userId);
	
	public boolean addUser(User user);
	
	public boolean updateUser(User user);
	
	public boolean deleteUser(int userId);
	
	public boolean validate(String username, String password);
	
}
