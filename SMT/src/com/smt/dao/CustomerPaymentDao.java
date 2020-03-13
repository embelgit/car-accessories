package com.smt.dao;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jfree.util.Log;

import com.smt.bean.CreditCustPaymentDetail;
import com.smt.hibernate.CustomerPaymentBean;
import com.smt.utility.HibernateUtility;

public class CustomerPaymentDao {

	public void regCustomerPayment(CustomerPaymentBean bean) {


		
		HibernateUtility hbu   = null;
		Session session =null;
		Transaction transaction = null;
		try {
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			transaction = session.beginTransaction();
			 session.save(bean);
			 transaction.commit();
			 System.out.println("Successful");
		} catch (RuntimeException e) {
			
			try {
				transaction.rollback();
			} catch (RuntimeException e2) {
				
				Log.error("Error in regCustomerPayment", e2);
			}
		}
		finally
		{
			if (session!=null) {
				hbu.closeSession(session);
			}
		}
		
	
		
	}

	
	public List getCreditCustPaymentDetailsForReport(){


		    HibernateUtility hbu=null;
		    Session session=null;
		    List<CreditCustPaymentDetail> creditCustPaymentList=null;
		    try
		    {
				hbu = HibernateUtility.getInstance();
				session = hbu.getHibernateSession();
				Query query=session.createSQLQuery("select first_name, last_name, bill_no, total_amount, balance, credit, payment_mode FROM customer_details RIGHT JOIN credit_customer_payment ON customer_details.pk_customer_id = credit_customer_payment.fk_customer_id WHERE insert_date BETWEEN :stDate AND :edDate;");
				List<Object[]> list = query.list();
			creditCustPaymentList= new ArrayList<CreditCustPaymentDetail>(0);	
			for (Object[] object : list) {
				System.out.println(Arrays.toString(object));
				
				CreditCustPaymentDetail custreports = new CreditCustPaymentDetail();
				custreports.setCreditCustomerFirstName(object[0].toString());
				custreports.setCreditCustomerLastName(object[1].toString());
				custreports.setBillNo((BigInteger)object[2]);
				custreports.setTotalAmount(Double.parseDouble(object[3].toString()));
				custreports.setBalanceAmount(Double.parseDouble(object[4].toString()));
				custreports.setCredit(Double.parseDouble(object[5].toString()));
				custreports.setPaymentMode(object[6].toString());
				
				creditCustPaymentList.add(custreports);		
		}}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		 finally
		{
		
		if(session!=null){
			hbu.closeSession(session);	
		}
		}
		return creditCustPaymentList;
	}


/*To get Details of credit customer Payment between two dates	*/
	
	public List<CreditCustPaymentDetail> getCreditCustomerDetailsDateWise(
			String fDate, String tDate) {

		System.out.println(fDate+"first Date In dao");
		System.out.println(tDate+"Second Date In dao");
		HibernateUtility hbu=null;
		Session session=null;
		List<CreditCustPaymentDetail> custList=null;
		try
		{
			hbu = HibernateUtility.getInstance();
		 session = hbu.getHibernateSession();
		 Query query2 = session.createSQLQuery("select first_name, last_name, bill_no, total_amount, balance, payment, payment_mode, paymentType, insert_date FROM customer_details RIGHT JOIN credit_customer_payment ON customer_details.pk_customer_id = credit_customer_payment.fk_customer_id WHERE insert_date BETWEEN '"+fDate+"' AND '"+tDate+"'");
			/*query2.setParameter("stDate", fDate);
	        query2.setParameter("edDate", tDate);*/
	        List<Object[]> list = query2.list();
	        custList= new ArrayList<CreditCustPaymentDetail>(0);
			
			
			for (Object[] object : list) {
				
				CreditCustPaymentDetail reports = new CreditCustPaymentDetail();
					
				reports.setCreditCustomerFirstName(object[0].toString());
				reports.setCreditCustomerLastName(object[1].toString());
				reports.setBillNo((BigInteger)object[2]);
				reports.setTotalAmount(Double.parseDouble(object[3].toString()));
				reports.setBalanceAmount(Double.parseDouble(object[4].toString()));
				reports.setPaymentAmount(Double.parseDouble(object[5].toString()));
			    reports.setPaymentMode(object[6].toString());
			    reports.setPaymentType(object[7].toString());
			    reports.setDate(object[8].toString());
				custList.add(reports); 
		
			}}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return custList;
		
	
	}


	//for single date
	public List<CreditCustPaymentDetail> getCreditCustPaymentDetailsForSingleDate(
			String fDate) {

		
		HibernateUtility hbu=null;
		Session session=null;
		List<CreditCustPaymentDetail> customerList = null;
		try
		{
				hbu = HibernateUtility.getInstance();
		 session = hbu.getHibernateSession();
		 Query query = session.createSQLQuery("select first_name, last_name, bill_no, total_amount, balance, payment, payment_mode,paymentType FROM customer_details RIGHT JOIN credit_customer_payment ON customer_details.pk_customer_id = credit_customer_payment.fk_customer_id WHERE DATE(insert_date)=:isInsertDate ");
			query.setParameter("isInsertDate", fDate);
			List<Object[]> list = query.list();
			customerList = new ArrayList<CreditCustPaymentDetail>(0);
			
			
			for (Object[] object : list) {
				
				CreditCustPaymentDetail reports = new CreditCustPaymentDetail();
					
				reports.setCreditCustomerFirstName(object[0].toString());
				reports.setCreditCustomerLastName(object[1].toString());
				reports.setBillNo((BigInteger)object[2]);
				reports.setTotalAmount(Double.parseDouble(object[3].toString()));
				reports.setBalanceAmount(Double.parseDouble(object[4].toString()));
				reports.setPaymentAmount(Double.parseDouble(object[5].toString()));
			    reports.setPaymentMode(object[6].toString());	
				reports.setPaymentType(object[7].toString());
			    customerList.add(reports); 
		
			}}
		catch(Exception e)
		{
			e.printStackTrace();	
		}
		return customerList;	
	
	}


	public List<CreditCustPaymentDetail> getCreditCustPaymentDetailsAsPerBillNum(
			String billNo, String fkCustomerId) {


		
		HibernateUtility hbu=null;
		Session session=null;
		List<CreditCustPaymentDetail> customerList = null;
		try
		{
				hbu = HibernateUtility.getInstance();
		 session = hbu.getHibernateSession();
		 Query query = session.createSQLQuery("select c.first_name,c.last_name, p.bill_no, p.total_amount, p.balance, p.payment,p.payment_mode,p.paymentType,p.insert_date from credit_customer_payment p LEFT JOIN customer_details c ON c.pk_customer_id=p.fk_customer_id where p.bill_no =:billNo AND p.fk_customer_id =:fkCustomerId");
			query.setParameter("billNo", billNo);
			query.setParameter("fkCustomerId", fkCustomerId);
			List<Object[]> list = query.list();
			customerList = new ArrayList<CreditCustPaymentDetail>(0);
			
			
			for (Object[] object : list) {
				
				CreditCustPaymentDetail reports = new CreditCustPaymentDetail();
					
				reports.setCreditCustomerFirstName(object[0].toString());
				reports.setCreditCustomerLastName(object[1].toString());
				reports.setBillNo((BigInteger)object[2]);
				reports.setTotalAmount(Double.parseDouble(object[3].toString()));
				reports.setBalanceAmount(Double.parseDouble(object[4].toString()));
				reports.setPaymentAmount(Double.parseDouble(object[5].toString()));
			    reports.setPaymentMode(object[6].toString());	
				reports.setPaymentType(object[7].toString());
				reports.setDate(object[8].toString());
			    customerList.add(reports); 
		
			}}
		catch(Exception e)
		{
			e.printStackTrace();	
		}
		return customerList;	
	
	
	}


	public List<CreditCustPaymentDetail> getCreditCustPaymentDetailsAsPerName(
			String fkCustomerId) {


		
		HibernateUtility hbu=null;
		Session session=null;
		List<CreditCustPaymentDetail> customerList = null;
		try
		{
				hbu = HibernateUtility.getInstance();
		 session = hbu.getHibernateSession();
		 Query query = session.createSQLQuery("select first_name, last_name, bill_no, total_amount, balance, payment, payment_mode, paymentType, insert_date FROM customer_details RIGHT JOIN credit_customer_payment ON customer_details.pk_customer_id = credit_customer_payment.fk_customer_id WHERE credit_customer_payment.fk_customer_id=:customer");
			query.setParameter("customer", fkCustomerId);
			List<Object[]> list = query.list();
			customerList = new ArrayList<CreditCustPaymentDetail>(0);
			
			
			for (Object[] object : list) {
				
				CreditCustPaymentDetail reports = new CreditCustPaymentDetail();
					
				reports.setCreditCustomerFirstName(object[0].toString());
				reports.setCreditCustomerLastName(object[1].toString());
				reports.setBillNo((BigInteger)object[2]);
				reports.setTotalAmount(Double.parseDouble(object[3].toString()));
				reports.setBalanceAmount(Double.parseDouble(object[4].toString()));
				reports.setPaymentAmount(Double.parseDouble(object[5].toString()));
			    reports.setPaymentMode(object[6].toString());	
				reports.setPaymentType(object[7].toString());
				reports.setDate(object[8].toString());
				
			    customerList.add(reports); 
		
			}}
		catch(Exception e)
		{
			e.printStackTrace();	
		}
		return customerList;	
	
	
		
	}

// Credit customer payment details for report
	public List getCreditCustomerPaymentDetailForReport(){


	    HibernateUtility hbu=null;
	    Session session=null;
	    List<CreditCustPaymentDetail> productList=null;
	    try
	    {
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query=session.createSQLQuery("SELECT c.first_name,c.last_name,cat.cat_name,p.bill_no,p.total_amount,p.balance,p.paymentType,p.payment,p.payment_mode,p.person_name From credit_customer_payment p LEFT JOIN customer_details c on p.fk_customer_id=c.pk_customer_id LEFT JOIN categories cat on p.cat_id=cat.pk_cat_id");
			List<Object[]> list = query.list();
			productList= new ArrayList<CreditCustPaymentDetail>(0);	
		for (Object[] o : list) {
			System.out.println(Arrays.toString(o));
			
			CreditCustPaymentDetail reports = new CreditCustPaymentDetail();
			
			reports.setCreditCustomerFirstName(o[0].toString());
			reports.setCreditCustomerLastName(o[1].toString());
			reports.setCatName(o[2].toString());
			reports.setBillNo((BigInteger)o[3]);
			reports.setTotalAmount(Double.parseDouble(o[4].toString()));
			reports.setBalanceAmount(Double.parseDouble(o[5].toString()));
			reports.setPaymentType(o[6].toString());
			reports.setPaymentAmount(Double.parseDouble(o[7].toString()));
			reports.setPaymentMode(o[8].toString());
			reports.setAccountantName(o[9].toString());
			
			productList.add(reports);		
	}}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	 finally
	{
	
	if(session!=null){
		hbu.closeSession(session);	
	}
	}
	return productList;
}

// run when credit customer bill have done first time
	public void regCreditCustomerPayment(Long billNo, String grossTotal, String paidAmt, String fkRootCustId) {
		// TODO Auto-generated method stub
		CustomerPaymentBean bean = new CustomerPaymentBean();
		
		bean.setCustomer(Long.parseLong(fkRootCustId));
		
		bean.setBillNo(billNo);
		
		bean.setPersonname("At Time Of Bill");
		
		bean.setPaymentMode("N/A");
		
		bean.setChequeNum("N/A");
		
		bean.setNameOnCheck("N/A");
		
		bean.setCardNum(null);
		
		bean.setBankName("N/A");
		
		bean.setAccNum(null);
		
		SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
		Date dateobj = new Date();
		
		bean.setInsertDate(dateobj);
		
		bean.setTotalAmount(Double.parseDouble(grossTotal));
		
		double grossAmt = Double.parseDouble(grossTotal);
		double paidRs = Double.parseDouble(paidAmt);
		
		double balance = grossAmt - paidRs;
		
		bean.setBalance(balance);
		
		bean.setPaymentType("credit");
		
		bean.setCredit(Double.parseDouble(paidAmt));
		
		CustomerPaymentDao dao = new CustomerPaymentDao();
		dao.regCustomerPayment(bean);
	}
	
	
}
