package com.neusoft.busmis.security.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.naming.java.javaURLContextFactory;

import com.neusoft.busmis.factory.ConnectionFactoryWithC3P0;
import com.neusoft.busmis.factory.ConnectionFactoryWithDBCP;
import com.neusoft.busmis.factory.ConnectionFactoryWithJNDI;
import com.neusoft.busmis.security.dao.IUserDao;
import com.neusoft.busmis.security.model.UserModel;

//用户的DAO实现类
public class UserDaoImpl implements IUserDao {

	@Override
	public void create(UserModel userModel) throws Exception {//创建
		//Class.forName("com.mysql.jdbc.Driver");
		//Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/busmis", "root", "7701");
		//一般不用这种方式[jdbc]
		//Connection cn=ConnectionFactoryWithDBCP.getConnection();//[dbcp]
		//Connection cn=ConnectionFactoryWithC3P0.getConnection();//[c3p0]
		Connection cn=ConnectionFactoryWithJNDI.getConnection();//[jndi]
		
		String sql="insert into userinfo2 values(?,?,?,?,?,?,'N')";//默认为不激活状态
		PreparedStatement ps=cn.prepareStatement(sql);
		ps.setString(1,userModel.getId());
		ps.setString(2,userModel.getPassword());
		ps.setString(3,userModel.getName());
		ps.setString(4,userModel.getRole());
		ps.setInt(5,userModel.getAge());
		ps.setDate(6, new java.sql.Date(userModel.getJoinDate().getTime()));
		//ps.setString(7,userModel.getStatus());
		ps.executeUpdate();
		ps.close();
		cn.close();//将连接变成空闲，返回连接池框架

	}

	@Override
	public void update(UserModel userModel) throws Exception {//修改
		Connection cn=ConnectionFactoryWithJNDI.getConnection();
		String sql="update userinfo2 set UserPASSWORD=?,UserNAME=?,UserROLE=?,Age=?,JoinDate=?,STATUS=? where USERID=?";
		PreparedStatement ps=cn.prepareStatement(sql);
		ps.setString(1,userModel.getPassword());
		ps.setString(2,userModel.getName());
		ps.setString(3,userModel.getRole());
		ps.setInt(4,userModel.getAge());
		ps.setDate(5,new java.sql.Date(userModel.getJoinDate().getTime()));
		ps.setString(6,userModel.getStatus());
		ps.setString(7,userModel.getId());
		ps.executeUpdate();
		ps.close();
		cn.close();//将连接变成空闲，返回连接池框架

	}

	@Override
	public void delete(UserModel userModel) throws Exception {//删除
		Connection cn=ConnectionFactoryWithJNDI.getConnection();
		String sql="delete from userinfo2 where USERID=?";
		PreparedStatement ps=cn.prepareStatement(sql);
		ps.setString(1,userModel.getId());
		ps.executeUpdate();
		ps.close();
		cn.close();//将连接变成空闲，返回连接池框架

	}

	@Override
	public List<UserModel> selectListByAll() throws Exception {//列表查询
		List<UserModel> list=new ArrayList<UserModel>();
		Connection cn=ConnectionFactoryWithJNDI.getConnection();
		String sql="select * from userinfo2";
		PreparedStatement ps=cn.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		while (rs.next()) {
			UserModel um=new UserModel();
			um.setId(rs.getString("USERID"));
			um.setPassword(rs.getString("UserPASSWORD"));
			um.setName(rs.getString("UserNAME"));
			um.setRole(rs.getString("UserROLE"));
			um.setAge(rs.getInt("Age"));
			um.setJoinDate(rs.getDate("JoinDate"));
			um.setStatus(rs.getString("STATUS"));
			list.add(um);
			
		}
		rs.close();
		ps.close();
		cn.close();
		return list;
	}

	@Override
	public UserModel selectById(String id) throws Exception {//单个查询
		Connection cn=ConnectionFactoryWithJNDI.getConnection();
		String sql="select * from userinfo2 where USERID=?";
		PreparedStatement ps=cn.prepareStatement(sql);
		ps.setString(1,id);
		ResultSet rs=ps.executeQuery();
		UserModel um=null;
		while(rs.next()){
			um=new UserModel();
			um.setId(rs.getString("USERID"));
			um.setPassword(rs.getString("UserPASSWORD"));
			um.setName(rs.getString("UserNAME"));
			um.setRole(rs.getString("UserROLE"));
			um.setAge(rs.getInt("Age"));
			um.setJoinDate(rs.getDate("JoinDate"));
			um.setStatus(rs.getString("STATUS"));
		}
		rs.close();
		ps.close();
		cn.close();
		return um;
	}

}
