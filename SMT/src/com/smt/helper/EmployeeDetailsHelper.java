package com.smt.helper;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.smt.bean.GetEmployeeDetails;
import com.smt.dao.EmployeeDetailsDao;
import com.smt.hibernate.EmployeeDetailsBean;
import com.smt.utility.HibernateUtility;



public class EmployeeDetailsHelper {
	Date newDate = null;
	public boolean employeeDetails(HttpServletRequest request, HttpServletResponse response ){

		System.out.println("In helper");
		
		String address = request.getParameter("address");
		
		String contactNo = request.getParameter("contactNo");
		
		String emailId = request.getParameter("emailId");
		
		String firstName = request.getParameter("firstName");
		
		String joiningDate = request.getParameter("joiningDate");
		
		String lastName = request.getParameter("lastName");
		
		String middleName = request.getParameter("middleName");
		
		String salary = request.getParameter("salary");
		
		String zipCode = request.getParameter("zipCode");
		
		EmployeeDetailsBean edb = new EmployeeDetailsBean();
		
		/*SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");*/

				if(!"".equals(joiningDate)){
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					Date newDate = null;
					try {
						newDate =	format.parse(joiningDate);
						} 
					catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					edb.setJoiningDate(newDate);
					}
					else
					{
						SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
						Date adate = null;
						try {
						 adate=	format.parse("2016-09-20");
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						edb.setJoiningDate(adate);
					}
			
				
				
				if(!"".equals(contactNo)){
					edb.setContactNo(Long.parseLong(contactNo));
			} else
			{
				edb.setContactNo(Long.parseLong("00"));
			}
				

				if(!"".equals(zipCode)){
					edb.setZipCode(Long.parseLong(zipCode));
					
					System.out.println("zip == =  ="+zipCode);
			} else
			{
				edb.setZipCode(Long.parseLong("00"));
				System.out.println("zip == =  = in else "+zipCode);
			}
				
				if(!"".equals(emailId)){
					edb.setEmailId(emailId);
			} else
			{
				edb.setEmailId("N/A");
			}
				
				if(!"".equals(salary)){
					edb.setSalary(Double.parseDouble(salary));
			} else
			{
				edb.setSalary(Double.parseDouble("00"));
			}
				
				edb.setFirstName(firstName);
				edb.setMiddleName(middleName);
				edb.setLastName(lastName);
				edb.setAddress(address);
				
				EmployeeDetailsDao edo = new EmployeeDetailsDao();
				edo.valEmployeeDetails(edb);
				
				return true;
	}

	public Map getEmployeeDetailsForEdit(Long empId) {

		
	 	System.out.println("into helper class");
	 	EmployeeDetailsDao dao1 = new EmployeeDetailsDao();
		List catList = dao1.getAllEmployeeDetailsForEdit(empId);
		
		Map  map =  new HashMap();
		for(int i=0;i<catList.size();i++)
		{
			Object[] o = (Object[])catList.get(i);
		
			GetEmployeeDetails bean = new GetEmployeeDetails();
			
			bean.setFirstName(o[0].toString());
			bean.setMiddleName(o[1].toString());
			bean.setLastName(o[2].toString());
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date newDate = format.parse(o[3].toString());
				bean.setJoiningDate(newDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			};
			
			bean.setEmail(o[4].toString());
			bean.setSalary(Double.parseDouble(o[5].toString()));
			bean.setContactNo((BigInteger)o[6]);
			bean.setAddresst(o[7].toString());
			bean.setZipCode((BigInteger)o[8]);
			map.put(bean.getFirstName(),bean);
		}
		System.out.println("out of helper return map : "+map);
		return map;
	
	
		
	
	}

	public void editEmployeeDetail(HttpServletRequest request,
			HttpServletResponse response) {

		
		String EmployeeId = request.getParameter("EmployeeId");
		
		String firstName = request.getParameter("firstName");
		String middleName = request.getParameter("middleName");
		String lastName = request.getParameter("lastName");
		
		String joiningDate = request.getParameter("joiningDate");
		String emailId = request.getParameter("emailId");
		String salary = request.getParameter("salary");
		
		String contactNo = request.getParameter("contactNo");
		String address = request.getParameter("address");
		String zipCode = request.getParameter("zipCode");
		String oldJoiningDate = request.getParameter("oldJoiningDate");
		
		HibernateUtility hbu=null;
		Session session = null;
		Transaction transaction = null;
		
		 hbu = HibernateUtility.getInstance();
		session = hbu.getHibernateSession();
		 transaction = session.beginTransaction();
	
		//long customerId = Long.parseLong(customerId);
		long EmployeeID =Long.parseLong(EmployeeId);
		EmployeeDetailsBean det = (EmployeeDetailsBean) session.get(EmployeeDetailsBean.class, EmployeeID);
		
		det.setAddress(address);
		det.setFirstName(firstName);
		det.setLastName(lastName);
		det.setLastName(lastName);
		det.setMiddleName(middleName);
		det.setContactNo(Long.parseLong(contactNo));
		det.setEmailId(emailId);
		det.setSalary(Double.parseDouble(salary));
		det.setZipcode(Long.parseLong(zipCode));
		//det.setJoiningDate(Date(joiningDate));

		if(!"".equals(joiningDate)){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date newDate = null;
		try {
			newDate =	format.parse(joiningDate);
			} 
		catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		det.setJoiningDate(newDate);
		System.out.println("Date in helper=  = ="+newDate);
		}
	/*	else{

			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date newDate1 = null;
			try {
				newDate1 =	format.parse(oldJoiningDate);// if user not select new joining date then this will assign existing joining date
				} 
			catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			det.setJoiningDate(newDate1);
			System.out.println("Date in else helper=  = ="+newDate1);
			
		}*/
		
	    session.saveOrUpdate(det);
		transaction.commit();
		
		System.out.println("Record updated successfully.");
	
		
	}
}
	