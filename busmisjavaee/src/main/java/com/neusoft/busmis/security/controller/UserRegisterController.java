package com.neusoft.busmis.security.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.busmis.security.factory.ServiceFactory;
import com.neusoft.busmis.security.model.UserModel;
import com.neusoft.busmis.security.service.IUserService;

/**
 * Servlet implementation class UserRegisterController
 */
@WebServlet("/user/register.do")
public class UserRegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id=request.getParameter("id");
		String password=request.getParameter("password");
		String name=request.getParameter("name");
		String role=request.getParameter("role");
		String age=request.getParameter("age");
		String sjoinDate=request.getParameter("joinDate");
		String status=request.getParameter("status");
		
		//取得业务接口对象
		IUserService us=ServiceFactory.createUserService();
		try {
			//类型转换
			DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
			UserModel um=new UserModel();
			um.setId(id);
			um.setPassword(password);
			um.setName(name);
			um.setRole(role);
			um.setAge(Integer.parseInt(age));
			um.setJoinDate(df.parse(sjoinDate));
			um.setStatus(status);
			us.register(um);//注册新用户
			response.sendRedirect("tolist.do");//重定向到列表前分发控制器
			} catch (Exception e) {
					
				e.printStackTrace();
				response.sendRedirect("../error/error.jsp");
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
