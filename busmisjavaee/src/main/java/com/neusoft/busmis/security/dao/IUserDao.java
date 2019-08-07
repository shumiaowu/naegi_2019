package com.neusoft.busmis.security.dao;

import java.util.List;

import com.neusoft.busmis.security.model.UserModel;

//用户的DAO层接口

public interface IUserDao {
	//C方法
	public void create(UserModel userModel) throws Exception;
	//U方法
	public void update(UserModel userModel) throws Exception;
	//D方法
	public void delete(UserModel userModel) throws Exception;
	//R方法-获取列表
	public List<UserModel> selectListByAll() throws Exception;
	//R方法-查询返回单个对象，一般根据表的主键字段值获取
	public UserModel selectById(String id) throws Exception;
}
