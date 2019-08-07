package com.neusoft.busmis.factory;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactoryWithJDBC {
	
	public static Connection getConnection() throws Exception{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/busmis?serverTimezone=GMT%2B8", "root", "7701");
		return cn;
	}

}
