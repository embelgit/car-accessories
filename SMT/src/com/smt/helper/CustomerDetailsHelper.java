package com.smt.helper;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.smt.bean.GetCreditCustomerDetails;
import com.smt.dao.CustomerDetailsDao;
import com.smt.hibernate.CustomerDetailsBean;
import com.smt.utility.HibernateUtility;



public class CustomerDetailsHelper {
	
	public void customerDetails(HttpServletRequest request, HttpServletResponse response ){

		System.out.println("In helper");
		
		String address = request.getParameter("address");
		
		String contactNo = request.getParameter("contactNo");
		
		String emailId = request.getParameter("emailId");
		
		String firstName = request.getParameter("firstName");
		
		String lastName = request.getParameter("lastName");
		
		String middleName = request.getParameter("middleName");
		
		String zipCode = request.getParameter("zipCode");
		
		String aadhar = request.getParameter("aadhar");
		System.out.println(aadhar+"aadhar number");
		
		CustomerDetailsBean cdb = new CustomerDetailsBean();
		
				
				
			if(!"".equals(contactNo)){
					cdb.setContactNo(Long.parseLong(contactNo));
			} else
			{
				cdb.setContactNo(Long.parseLong("00"));
			}
				

				if(!"".equals(zipCode)){
					cdb.setZipCode(Long.parseLong(zipCode));
			} else
			{
				cdb.setZipCode(Long.parseLong("00"));
			}
				
				if(!"".equals(aadhar)){
					cdb.setAadhar((aadhar));
			} else
			{
				cdb.setAadhar("NA");
			}
				if(!"".equals(emailId)){
					cdb.setEmailId(emailId);
			} else
			{
				cdb.setEmailId("N/A");
			}
				cdb.setFirstName(firstName);
				cdb.setMiddleName(middleName);
				cdb.setLastName(lastName);
				cdb.setAddress(address);
				
				CustomerDetailsDao cdo = new CustomerDetailsDao();
				cdo.valCustomerDetails(cdb);
	}
	
	
	
	public Map getVillage(String creditCustomerId) {
		
		CustomerDetailsDao dao = new CustomerDetailsDao();
		List list = dao.getVillageByCustomerName(creditCustomerId);
		Map  map =  new HashMap();
		
		for(int i=0;i<list.size();i++)
		{
			Object[] o = (Object[])list.get(i);
			GetCreditCustomerDetails bean = new GetCreditCustomerDetails();
			bean.setVillage(o[0].toString());
			bean.setFirstName(o[1].toString());
			bean.setContactNo((BigInteger)o[2]);
			bean.setAadhar(o[3].toString());
			System.out.println("***************"+o[0]);
			map.put(bean.getVillage(),bean);
		}
		return map;
	}



	public Map getAllBillByCustomers(String fkCustomerId) {

		CustomerDetailsDao dao = new CustomerDetailsDao();
		List list= dao.getAllBillByCreditCustomer(fkCustomerId);
		Map  map =  new HashMap();
		for(int i=0;i<list.size();i++)
		{
			Object[] o = (Object[])list.get(i);
			GetCreditCustomerDetails bean = new GetCreditCustomerDetails();
			System.out.println("result - "+Arrays.toString(o));
			bean.setBillNo((BigInteger)o[0]);
			bean.setInsertDate(o[1].toString());
			System.out.println("bill - "+bean.getBillNo());
			map.put(bean.getBillNo(),bean);
			
		}
		return map;
	
	}
	
	public Map getAllBillByCustomers1(String fkCustomerId) {

		CustomerDetailsDao dao = new CustomerDetailsDao();
		List list= dao.getAllBillByCreditCustomer1(fkCustomerId);
		Map  map =  new HashMap();
		for(int i=0;i<list.size();i++)
		{
			Object[] o = (Object[])list.get(i);
			GetCreditCustomerDetails bean = new GetCreditCustomerDetails();
			System.out.println(Arrays.toString(o));
			bean.setBillNo((BigInteger)o[0]);
			bean.setInsertDate(o[1].toString());
			System.out.println("***************"+o[0]);
			map.put(bean.getBillNo(),bean);
			
		}
		return map;
	
	}



	public Map getTotalAmtByBillNo(String billNo, String creditCustomer) {

		CustomerDetailsDao dao = new CustomerDetailsDao();
		List list= dao.getTotalAmountByBill(billNo,creditCustomer);
		Map  map =  new HashMap();
		for(int i=0;i<list.size();i++)
		{
			Object[] o = (Object[])list.get(i);
			GetCreditCustomerDetails bean = new GetCreditCustomerDetails();
			System.out.println(Arrays.toString(o));
			String total = o[0].toString();
			Double total1 = Double.valueOf(total);
			System.out.println(total1);
			bean.setTotalAmount(total1);
			//bean.setInsertDate(o[1].toString());
			System.out.println("total amt set - "+bean.getTotalAmount());
			System.out.println("Ih helper method getTotal");
			map.put(bean.getBillNo(),bean);
			
		}
		return map;
	
	
	}



	public Map getBalanceAmtByBillNo(String billNo, String creditCustomer) {

		CustomerDetailsDao dao = new CustomerDetailsDao();
		List list= dao.getRemainingBalanceAmountByBill(billNo,creditCustomer);
		Map  map =  new HashMap();
		System.out.println(list.size()+"LIST SIZE");
		int sic = list.size();
		if(sic==0)
		{
			GetCreditCustomerDetails bean = new GetCreditCustomerDetails();
			Double totalAmt = dao.getTotalAmt(billNo);
			bean.setBalance(totalAmt);
			map.put(bean.getBalance(),bean);
		}
		else {
			for(int i=0;i<list.size();i++)
			{
				Object[] o = (Object[])list.get(i);
				GetCreditCustomerDetails bean = new GetCreditCustomerDetails();
				String newBal = (o[0].toString());
					Double newBal1 = Double.valueOf(newBal);
					System.out.println(newBal1+"NEW BALANCE");
					
						bean.setBalance(newBal1);
					
			
				
				System.out.println("***************"+o[0]);
				map.put(bean.getBalance(),bean);
			}
		}
		
		return map;

	}



	public Map getCreditCustomerDetailsForEdit(Long customerId) {
		
	 	System.out.println("into helper class");
	 	CustomerDetailsDao dao1 = new CustomerDetailsDao();
		List catList = dao1.getCreditCustomerForEdit(customerId);
		
		Map  map =  new HashMap();
		for(int i=0;i<catList.size();i++)
		{
			Object[] o = (Object[])catList.get(i);
			GetCreditCustomerDetails bean = new GetCreditCustomerDetails();
			 bean.setFirstName(o[0].toString());
			 bean.setMiddleName(o[1].toString());
			 bean.setLastName(o[2].toString());
			 bean.setEmail(o[3].toString());
			 bean.setAddress(o[4].toString());
			 bean.setContactNo((BigInteger)o[5]);
			 bean.setZipCode((BigInteger)o[6]);
			 bean.setAadhar(o[7].toString());
			
			
			map.put(bean.getFirstName(),bean);
		}
		System.out.println("out of helper return map : "+map);
		return map;
	
	}



	public void editCreditCustomer(HttpServletRequest request,
			HttpServletResponse response) {

		System.out.println("in EditEmployee helper");
		
		String strcustomerId = request.getParameter("customerId");
		
		String firstName = request.getParameter("firstName");
		String middleName = request.getParameter("middleName");
		String lastName = request.getParameter("lastName");
		
		String address = request.getParameter("address");
		String contactNo = request.getParameter("contactNo");
		String aadharNo = request.getParameter("aadharNo");
		
		String emailId = request.getParameter("emailId");
		String zipCode = request.getParameter("zipCode");
		
		HibernateUtility hbu=null;
		Session session = null;
		Transaction transaction = null;
		
		 hbu = HibernateUtility.getInstance();
		session = hbu.getHibernateSession();
		 transaction = session.beginTransaction();
	
		//long customerId = Long.parseLong(customerId);
		long customerId =Long.parseLong(strcustomerId);
		CustomerDetailsBean det = (CustomerDetailsBean) session.get(CustomerDetailsBean.class, customerId);
		
		det.setFirstName(firstName);
		det.setMiddleName(middleName);
		det.setLastName(lastName);
		
		det.setAddress(address);
		det.setContactNo(Long.parseLong(contactNo));
		det.setAadhar((aadharNo));
		det.setEmailId(emailId);
		det.setZipCode(Long.parseLong(zipCode));
  	
	    session.saveOrUpdate(det);
		transaction.commit();
		
		System.out.println("Record updated successfully.");
	
	
	}




	
	
	


}
