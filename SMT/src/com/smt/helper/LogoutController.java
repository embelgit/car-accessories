package com.smt.helper;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.smt.utility.MyJob;

/**
 * Servlet implementation class LogoutSession
 */
public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	

	public void logoutUser(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("thanq you!!, Your session was destroyed successfully!!");
	//	LogoutController.main1();
	//	MyJob job = new MyJob();
	//	job.execute();
		HttpSession session = request.getSession(false);
		response.sendRedirect("/SMT/jsp/login.jsp");
		session.setAttribute("user", null);
	
		
		session.removeAttribute("user");
		//session.getMaxInactiveInterval();
		// TODO Auto-generated method stub
		
	}
	
/*	public boolean tbBackup(String dbName,String dbUserName, String dbPassword, String path) {

		String executeCmd = "";
	    executeCmd = "C:/Program Files/MySQL/MySQL Server 5.7/bin/mysqldump -u " + dbUserName + " -p" + dbPassword + " --add-drop-database -B " + dbName + " -r " + path;
	    Process runtimeProcess;
	        try
	        {
	            System.out.println(executeCmd);//this out put works in mysql shell
	            runtimeProcess = Runtime.getRuntime().exec(executeCmd);
	           // runtimeProcess = Runtime.getRuntime().exec(new String[] { "cmd.exe", "/c cd Program Files cd MySQL cd MySQL Server 5.7 cd bin", executeCmd });
	            int processComplete = runtimeProcess.waitFor();

	                if (processComplete == 0)
	                {
	                    System.out.println("Backup created successfully");
	                    return true;
	                }
	                else
	                {
	                    System.out.println("Could not create the backup");
	                }
	        } catch (Exception ex)
	        {
	            ex.printStackTrace();
	        }
	return false;
	}

	public static void main1(){

		    LogoutController bb = new LogoutController();
		    Date date = new Date();
		    
	        bb.tbBackup("smt_sc","root","root","E:/Embel/dumpbackup/smt_sc.sql");

	}   */
}
