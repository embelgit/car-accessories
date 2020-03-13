package com.smt.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jfree.util.Log;

import com.smt.bean.SupplierPaymentDetail;
import com.smt.hibernate.SupplierPaymentBean;
import com.smt.utility.HibernateUtility;




public class SupplierPaymentDao {

	public void regSupPayment(SupplierPaymentBean bean) {
		
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
				
				Log.error("Error in regSupPayment", e2);
			}
		}
		finally
		{
			if (session!=null) {
				hbu.closeSession(session);
			}
		}
		
	}

	//Supplier Payment details for single date
	public List<SupplierPaymentDetail> getCreditCustPaymentDetailsForSingleDate(
			String fDate) {
		
		HibernateUtility hbu=null;
		Session session=null;
		List<SupplierPaymentDetail> supplierList = null;
		try
		{
				hbu = HibernateUtility.getInstance();
		 session = hbu.getHibernateSession();
		 Query query = session.createSQLQuery("select supplier_name, bill_no, total_amount, balance, payment, payment_mode,person_name,paymentType FROM supplier_details RIGHT JOIN supplier_payment ON supplier_details.supplier_id = supplier_payment.fk_supplier_id WHERE DATE(insert_date)=:isInsertDate ");
			query.setParameter("isInsertDate", fDate);
			List<Object[]> list = query.list();
			supplierList = new ArrayList<SupplierPaymentDetail>(0);
			
			
			for (Object[] object : list) {
				
				SupplierPaymentDetail reports = new SupplierPaymentDetail();
					
				reports.setSupplierName(object[0].toString());
				reports.setBillNo(object[1].toString());
				reports.setTotalAmount(Double.parseDouble(object[2].toString()));
				reports.setBalanceAmount(Double.parseDouble(object[3].toString()));
				reports.setPaymentAmount(Double.parseDouble(object[4].toString()));
			    reports.setPaymentMode(object[5].toString());
			    reports.setAccountantName(object[6].toString());
				reports.setPaymentType(object[7].toString());
			    supplierList.add(reports); 
		
			}}
		catch(Exception e)
		{
			e.printStackTrace();	
		}
		return supplierList;	
	
	
		
	}

	//Supplier Payment Details between two dates
	public List<SupplierPaymentDetail> getSupplierDetailsDateWise(String fDate,
			String tDate) {


		System.out.println(fDate+"first Date In dao");
		System.out.println(tDate+"Second Date In dao");
		HibernateUtility hbu=null;
		Session session=null;
		List<SupplierPaymentDetail> sup1List=null;
		try
		{
			hbu = HibernateUtility.getInstance();
		 session = hbu.getHibernateSession();
		 Query query2 = session.createSQLQuery("select supplier_name, bill_no, total_amount, balance, payment, payment_mode,person_name,paymentType,insert_date FROM supplier_details RIGHT JOIN supplier_payment ON supplier_details.supplier_id = supplier_payment.fk_supplier_id WHERE insert_date BETWEEN '"+fDate+"' AND '"+tDate+"'");
			/*query2.setParameter("stDate", fDate);
	        query2.setParameter("edDate", tDate);*/
	        List<Object[]> list = query2.list();
	        sup1List= new ArrayList<SupplierPaymentDetail>(0);
			
			
			for (Object[] object : list) {
				
				SupplierPaymentDetail reports = new SupplierPaymentDetail();
					
				reports.setSupplierName(object[0].toString());
				reports.setBillNo(object[1].toString());
				reports.setTotalAmount(Double.parseDouble(object[2].toString()));
				reports.setBalanceAmount(Double.parseDouble(object[3].toString()));
				reports.setPaymentAmount(Double.parseDouble(object[4].toString()));
			    reports.setPaymentMode(object[5].toString());
			    reports.setAccountantName(object[6].toString());
				reports.setPaymentType(object[7].toString());
				reports.setDate(object[8].toString());
			    sup1List.add(reports); 
		
			}}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return sup1List;
		
	
	
	}

	public List<SupplierPaymentDetail> getCreditCustPaymentDetailsAsBill(
			String billNo, String fkSupplierId) {

		
		HibernateUtility hbu=null;
		Session session=null;
		List<SupplierPaymentDetail> supplierList = null;
		try
		{
				hbu = HibernateUtility.getInstance();
		 session = hbu.getHibernateSession();
		 Query query = session.createSQLQuery("select s.supplier_name, p.bill_no, p.total_amount, p.balance, p.payment, p.payment_mode, p.person_name , p.paymentType, p.insert_date FROM supplier_details s RIGHT JOIN supplier_payment p ON s.supplier_id = p.fk_supplier_id WHERE p.bill_no =:billNumber And p.fk_supplier_id=:fkSupplierId");
			query.setParameter("billNumber", billNo);
			query.setParameter("fkSupplierId", fkSupplierId);
			
			
			List<Object[]> list = query.list();
			supplierList = new ArrayList<SupplierPaymentDetail>(0);
			
			
			for (Object[] object : list) {
				
				SupplierPaymentDetail reports = new SupplierPaymentDetail();
					
				reports.setSupplierName(object[0].toString());
				reports.setBillNo(object[1].toString());
				reports.setTotalAmount(Double.parseDouble(object[2].toString()));
				reports.setBalanceAmount(Double.parseDouble(object[3].toString()));
				reports.setPaymentAmount(Double.parseDouble(object[4].toString()));
			    reports.setPaymentMode(object[5].toString());
			    reports.setAccountantName(object[6].toString());
			    reports.setPaymentType(object[7].toString());
			    reports.setDate(object[8].toString());
			    supplierList.add(reports); 
		
			}}
		catch(Exception e)
		{
			e.printStackTrace();	
		}
		return supplierList;	
	
	
		
	
	}

	public List<SupplierPaymentDetail> getCreditCustPaymentDetailsAsBill(
			String fkSupplierId) {

		
		HibernateUtility hbu=null;
		Session session=null;
		List<SupplierPaymentDetail> supplierList = null;
		try
		{
				hbu = HibernateUtility.getInstance();
		 session = hbu.getHibernateSession();
		 Query query = session.createSQLQuery("select supplier_name, bill_no, total_amount, balance, paymentType,payment_mode,person_name, payment, insert_date FROM supplier_details RIGHT JOIN supplier_payment ON supplier_details.supplier_id = supplier_payment.fk_supplier_id WHERE supplier_payment.fk_supplier_id=:fkSupplierId");
			query.setParameter("fkSupplierId", fkSupplierId);
			List<Object[]> list = query.list();
			supplierList = new ArrayList<SupplierPaymentDetail>(0);
			
			
			for (Object[] object : list) {
				
				SupplierPaymentDetail reports = new SupplierPaymentDetail();
					
				reports.setSupplierName(object[0].toString());
				reports.setBillNo(object[1].toString());
				reports.setTotalAmount(Double.parseDouble(object[2].toString()));
				reports.setBalanceAmount(Double.parseDouble(object[3].toString()));
				reports.setPaymentType(object[4].toString());
			    reports.setPaymentMode(object[5].toString());
			    reports.setAccountantName(object[6].toString());
				reports.setPaymentAmount(Double.parseDouble(object[7].toString()));
				reports.setDate(object[8].toString());
			    supplierList.add(reports); 
		
			}}
		catch(Exception e)
		{
			e.printStackTrace();	
		}
		return supplierList;	
	
	
		
	
	}
	
	
	// Supplier payment details for report
		public List getSupplierPaymentDetailForReport(){


		    HibernateUtility hbu=null;
		    Session session=null;
		    List<SupplierPaymentDetail> productList=null;
		    try
		    {
				hbu = HibernateUtility.getInstance();
				session = hbu.getHibernateSession();
				Query query=session.createSQLQuery("SELECT s.supplier_name,p.bill_no,p.total_amount,p.balance,p.paymentType,p.payment,p.payment_mode,p.person_name FROM supplier_payment p LEFT JOIN supplier_details s ON p.fk_supplier_id = s.pk_supplier_id");
				List<Object[]> list = query.list();
				productList= new ArrayList<SupplierPaymentDetail>(0);	
			for (Object[] o : list) {
				System.out.println(Arrays.toString(o));
				
				SupplierPaymentDetail reports = new SupplierPaymentDetail();
				
				reports.setSupplierName(o[0].toString());
				reports.setBillNo(o[1].toString());
				reports.setTotalAmount(Double.parseDouble(o[2].toString()));
				reports.setBalanceAmount(Double.parseDouble(o[3].toString()));
				reports.setPaymentType(o[4].toString());
				reports.setPaymentAmount(Double.parseDouble(o[5].toString()));
				reports.setPaymentMode(o[6].toString());
				reports.setAccountantName(o[7].toString());
				
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
	
}
