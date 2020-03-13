package com.smt.helper;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smt.dao.CategoryDao;
import com.smt.dao.UserDetailDao;
import com.smt.hibernate.UserDetail;

public class UserDetailHelper {

	
	
	// Register User Detail
	public void userLogin(HttpServletRequest request,
			HttpServletResponse response) {
		
		UserDetail userDetail = new UserDetail();
		String typeId = request.getParameter("typeId");
		String password = request.getParameter("password");
		String userName = request.getParameter("userName");
		String repassword = request.getParameter("repassword");
		
		userDetail.setTypeId(typeId);
		userDetail.setPassword(password);
		userDetail.setRepassword(repassword);
		userDetail.setUserName(userName);
		
		UserDetailDao userDetailDao = new UserDetailDao();
		userDetailDao.registerUser(userDetail);
		// TODO Auto-generated method stub
		
	}
	
	public List getAlluserName()
	{
		UserDetailDao dao = new UserDetailDao();
		return dao.getAlluserName();
	}

}
