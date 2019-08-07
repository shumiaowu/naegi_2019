package com.neusoft.busmis.factory;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

//通过JNDI API查找命名服务中注册的连接池对象

public class ConnectionFactoryWithJNDI {
	private static DataSource ds=null;
	static {
		try {
			Context ctx=new InitialContext();
			//ds=(DataSource) ctx.lookup("c3p0ds");//通过监听
			//ds=(DataSource) ctx.lookup("java:comp/env/mysql3306busmis");//通过DBCP连接池
			ds=(DataSource) ctx.lookup("java:comp/env/mysql3306busmis_c3p0");//通过C3P0连接池
			ctx.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//从连接池取得连接
	public static Connection getConnection() throws Exception{
		return ds.getConnection();
	}
}
