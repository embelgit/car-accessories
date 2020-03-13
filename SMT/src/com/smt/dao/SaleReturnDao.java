package com.smt.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.smt.hibernate.CustomerBill;
import com.smt.hibernate.GoodReceive;
import com.smt.hibernate.SaleReturn;
import com.smt.utility.HibernateUtility;

public class SaleReturnDao {

	public void registerSaleReturn(SaleReturn cust) {
		// TODO Auto-generated method stub
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

	public void updateQuantity(Long pkBillId,String editQuantity,String totalAmt) {
		// TODO Auto-generated method stub
		
		HibernateUtility hbu=null;
		Session session=null;
		Transaction transaction =null;

		try
		{
			hbu = HibernateUtility.getInstance();
		    session = hbu.getHibernateSession();
		    transaction = session.beginTransaction();
		    
		    org.hibernate.Query query = session.createQuery("from CustomerBill where pkBillId = :pkBillId ");
		    query.setParameter("pkBillId", pkBillId);
		    
		    CustomerBill uniqueResult = (CustomerBill) query.uniqueResult();
		    Long quant = uniqueResult.getQuantity();
		   
		    Long updatequnty = (long) (quant - Long.parseLong(editQuantity));
		    
            
		    CustomerBill updateStock = (CustomerBill) session.get(CustomerBill.class, new Long(pkBillId));	 
   		
   		    updateStock.setQuantity(updatequnty);
   		    updateStock.setTotalperItem(Double.parseDouble(totalAmt));
   		 
   		    session.saveOrUpdate(updateStock);
   		    transaction.commit();
		 
		}
		catch(Exception e)
		{
			e.printStackTrace();	
		}finally
		{if(session!=null){
			session.close();	
		}
		}
		
		
	}

	public void updateBarcodeQuantity(Long barcodeNo, String editQuantity) {
		// TODO Auto-generated method stub
		HibernateUtility hbu=null;
		Session session=null;
		Transaction transaction =null;

		try
		{
			hbu = HibernateUtility.getInstance();
		    session = hbu.getHibernateSession();
		    transaction = session.beginTransaction();
		    
		    org.hibernate.Query query = session.createQuery("from GoodReceive where barcodeNo = :barcodeNo ");
		    query.setParameter("barcodeNo", barcodeNo);
		    
		    GoodReceive uniqueResult = (GoodReceive) query.uniqueResult();
		    Long quant = uniqueResult.getQuantity();
		    Long pkGoodReceiveId = uniqueResult.getPkGoodRecId();
		    
		    Long updatequnty = (long) (quant + Long.parseLong(editQuantity));
		    

		    GoodReceive updateStock = (GoodReceive) session.get(GoodReceive.class, new Long(pkGoodReceiveId));	 
   		
   		    updateStock.setQuantity(updatequnty);
   		 
   		    session.saveOrUpdate(updateStock);
   		    transaction.commit();
		 
		}
		catch(Exception e)
		{
			e.printStackTrace();	
		}finally
		{if(session!=null){
			session.close();	
		}
		}
		
	}

}
