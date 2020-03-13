package com.smt.helper;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;

import org.hibernate.Query;
//import org.hibernate.Session;

import com.smt.hibernate.UserDetail;
import com.smt.utility.HibernateUtility;

/**
 * Servlet implementation class Session
 */
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	

	public void loginUser(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String un = request.getParameter("uname");
		String pwd = request.getParameter("pass");
		System.out.println("username -  "+un);
		System.out.println("pass -  "+pwd);
	//	HibernateUtility hbu=HibernateUtility.getInstance();
	//	Session session1=hbu.getHibernateSession();
		
		HibernateUtility hbu=HibernateUtility.getInstance();
		Session session1=hbu.getHibernateSession();
//		org.hibernate.Query query = session1.createQuery("from UserDetail where userName = :username AND password = :pwd ");
		org.hibernate.Query query = session1.createQuery("from UserDetail where userName = '"+un+"' AND password = '"+pwd+"'");
	//	query.setParameter("username", un);
	//	query.setParameter("pwd", pwd);
		System.out.println("query list size initially - "+query.list().size());
		UserDetail uniqueResult = (UserDetail) query.uniqueResult();
		
		String userName = uniqueResult.getUserName();
		String password = uniqueResult.getPassword();
		String type = uniqueResult.getTypeId();
		System.out.println(userName);
		
		if(un.equals(userName) && pwd.equals(password)){
		    
			
		
			out.print("Welcome, " + un);
			HttpSession session = request.getSession(true); // reuse existing
															// session if exist
			response.sendRedirect("/SMT/jsp/index.jsp");												// or create one
			session.setAttribute("user", un);
			session.setAttribute(type, type);
			System.out.println("when usrname pass is correct !");
			 // 30 seconds
			
		} else {
			response.sendRedirect("/SMT/jsp/login.jsp");
			System.out.println("when wrong credential entered ");
	//		response.sendRedirect("/SMT/jsp/index.jsp");
//			HttpSession session = request.getSession(true);
//			session.setAttribute("user", un);
			//RequestDispatcher rd = request.getRequestDispatcher("/SMT/jsp/login.jsp");
			out.println("<font color=red>Either user name or password is wrong.</font>");
		
	      
	}
 }
}