package com.smt.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jfree.util.Log;

import com.smt.bean.CategoryWisePurchase;
import com.smt.bean.CustomerBean;
import com.smt.bean.ServiceBean;
import com.smt.hibernate.Category;
import com.smt.hibernate.CustomerBill;
import com.smt.hibernate.ServiceDetail;
import com.smt.utility.HibernateUtility;

public class ServiceDao {
	
	// other bill
			public List<ServiceBean> getAllItemDetails1(String productIdService) {
				
				
				HibernateUtility hbu=null;
				Session session=null;
			    List<ServiceBean> itemlist=null;
				try
				{
					
					    System.out.println("shreemant");
						hbu = HibernateUtility.getInstance();
				 session = hbu.getHibernateSession();
				 //String sqlQuery = "SELECT ItemName , PkGoodRecId, CategoryName , BarcodeNo, hsnsacno, vat, igst FROM GoodReceive WHERE quantity > 0 AND ItemName ="+productId;

				 Query query=session.createSQLQuery("SELECT ItemName,hsnsacNo from service_detail WHERE ItemName ='"+productIdService+"'");
					List<Object[]> list = query.list();

					 itemlist = new ArrayList<ServiceBean>(0);
				     for (Object[] objects : list) 
				     {
					 System.out.println(Arrays.toString(objects));
					 ServiceBean bean = new ServiceBean();
					  
					 bean.setItemName(objects[0].toString());
					bean.setHsnSacNo(objects[1].toString());
					 bean.setQuantity(1l);
					 bean.setSalePrice(0d);
					 bean.setDiscountGrid(0d);
					 bean.setDiscountAmt(0d);
					 bean.setVat(0d);
					 bean.setIgst(0d);
					 bean.setTaxAmount(0d);
					 
					 itemlist.add(bean);
				     }
				     }
						catch(RuntimeException e)
						{
							Log.error("Error in getAllItemDetails(String key)", e);	
						}finally
						{if(session!=null){
							hbu.closeSession(session);	
						}
						}
				return itemlist;
				}
			

	//////////////////////////register service bill/////////////
			
			public void registerBill(CustomerBill cust) {
				
				HibernateUtility hbu = null ;
				Session session  = null;
				Transaction transaction = null;
				
				
				try {
					hbu = HibernateUtility.getInstance();
					session = hbu.getHibernateSession();
					transaction = session.beginTransaction();
					
					session.save(cust);
					transaction.commit();
					
				
				} catch (Exception e) {
					try {
						transaction.rollback();
					} catch (RuntimeException ede) {
					     
					//	Log.error("Error in transaction", ede);
					}
				}
				
				finally
				{
					if (session!=null) {
						hbu.closeSession(session);
						
					}
				}
		}
			
		//////////////////List of service/////////////
			
			
			public List getAllServiceList(){
				
				HibernateUtility hbu=null;
				Session session=null;
				List<ServiceBean> saleList=null;
			try{	

				hbu = HibernateUtility.getInstance();
				session = hbu.getHibernateSession();

				Query query=session.createSQLQuery("SELECT * FROM service_detail;");
				
				List<Object[]> list = query.list();


				saleList= new ArrayList<ServiceBean>(0);


			for (Object[] object : list) {	
				ServiceBean reports = new ServiceBean();
				
				System.out.println("priya "+Arrays.toString(object));
				
				reports.setPkProductId(Long.parseLong(object[0].toString()));
				reports.setItemName(object[1].toString());
				reports.setHsnsacno(object[2].toString());
				
				
				saleList.add(reports);

			}}catch(RuntimeException e){	

			}
			finally{

			hbu.closeSession(session);	
			}
			return saleList;
			}
			
			
			
			
}
