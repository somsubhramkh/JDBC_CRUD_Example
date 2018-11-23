package com.niit.crudexample.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.niit.crudexample.model.User;
import com.niit.crudexample.utility.DBConnection;

public class UserDAOImpl implements UserDAO {
	
	Connection connection = null;
	
	
	public List<User> listUsers() {
		List<User> users = new ArrayList<User>();
		connection = DBConnection.getConnection();
		String sql = "select * from users";
		PreparedStatement statement = null;
		
		try {
			statement = connection.prepareStatement(sql);
			
			ResultSet resultSet =statement.executeQuery();
			
			while(resultSet.next()) {
				
				User user=new User();
				user.setUserId(resultSet.getInt(1));
				user.setUsername(resultSet.getString(2));
				user.setFirstName(resultSet.getString(3));
				user.setLastName(resultSet.getString(4));
				
				users.add(user);
			}
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return users;
		
	}

	public User getUser(int userId) {
		User user = new User();
		connection = DBConnection.getConnection();
		String sql = "select * from users where userid=?";
		PreparedStatement statement = null;
		
		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1,userId);
			
			ResultSet resultSet =statement.executeQuery();
			
			if(resultSet.next()) {
				
				
				user.setUserId(resultSet.getInt(1));
				user.setUsername(resultSet.getString(2));
				user.setFirstName(resultSet.getString(3));
				user.setLastName(resultSet.getString(4));
				
			}
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return user;
	}

	public boolean addUser(User user) {
		
		connection = DBConnection.getConnection();
		String sql = "insert into Users(userId,username,firstname,lastname,password) values(?,?,?,?,?)";
		PreparedStatement statement = null;
		int rowsUpdated = 0;
		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, user.getUserId());
			statement.setString(2, user.getUsername());
			statement.setString(3, user.getFirstName());
			statement.setString(4, user.getLastName());
			statement.setString(5, user.getPassword());
			
			rowsUpdated = statement.executeUpdate();
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		if(rowsUpdated>0) {
			return true;
		}
		else
			return false;
	}

	public boolean updateUser(User user) {
		connection = DBConnection.getConnection();
		String sql = "update Users set username=?,firstname=?,lastname=?,password=? where userid=?";
		PreparedStatement statement = null;
		int rowsUpdated = 0;
		try {
			statement = connection.prepareStatement(sql);
			
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getFirstName());
			statement.setString(3, user.getLastName());
			statement.setString(4, user.getPassword());
			statement.setInt(5, user.getUserId());
			rowsUpdated = statement.executeUpdate();
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		if(rowsUpdated>0) {
			return true;
		}
		else
			return false;
	}

	public boolean deleteUser(int userId) {
		connection = DBConnection.getConnection();
		String sql = "delete from Users where userid=?";
		PreparedStatement statement = null;
		int rowsUpdated = 0;
		try {
			statement = connection.prepareStatement(sql);
			
			statement.setInt(1, userId);
			rowsUpdated = statement.executeUpdate();
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		if(rowsUpdated>0) {
			return true;
		}
		else
			return false;
	}

	public boolean validate(String username, String password) {
		
		User user = new User();
		connection = DBConnection.getConnection();
		String sql = "select * from users where username=? and password=?";
		PreparedStatement statement = null;
		
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1,username);
			statement.setString(2,password);
			ResultSet resultSet =statement.executeQuery();
			
			if(resultSet.next()) {
				
				return true;
				
			}
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return false;
	}

}
