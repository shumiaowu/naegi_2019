package com.neusoft.busmis.factory;

import java.sql.Connection;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

//使用C3P0连接池框架的连接工厂

public class ConnectionFactoryWithC3P0 {
	private static DataSource ds=null;
	
	static {//设置数据库连接
		ComboPooledDataSource cpds = new ComboPooledDataSource();
		try {
			cpds.setDriverClass("com.mysql.cj.jdbc.Driver");  
			cpds.setJdbcUrl("jdbc:mysql://localhost:3306/busmis?serverTimezone=GMT%2B8");  
			cpds.setUser("root");  
			cpds.setPassword("7701");  
	        //设置连接池参数
			cpds.setMinPoolSize(1); //设置最小的连接个数  
			cpds.setAcquireIncrement(1); //设置每次新增的连接数
			cpds.setMaxPoolSize(10); //设置最大的连接个数
			ds=cpds;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//从连接池取得连接
	public static Connection getConnection() throws Exception{
		return ds.getConnection();
	}
	public static DataSource getDataSource() throws Exception{
		return ds;
	}

}
