package com.niit.crudexample.utility;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	public static Connection getConnection() {

		Connection connection = null;

		try {

			Class.forName("com.mysql.jdbc.Driver");
			DriverManager.getConnection(url, user, password);
		} catch (IOException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

}
