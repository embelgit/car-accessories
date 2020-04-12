package com.smt.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jfree.util.Log;

import com.smt.bean.BarrelEntryBean;
import com.smt.hibernate.BarrelEntryHibernate;
import com.smt.hibernate.Category;
import com.smt.hibernate.GoodReceive;
import com.smt.utility.HibernateUtility;

public class BarrelEntryDao {
	
	
	public void doProductRegister(BarrelEntryHibernate pdreg) {
		
		
		HibernateUtility hbu = null;
		Session session = null;
		org.hibernate.Transaction transaction = null;
		
		Category category = null;
		Long fkCategoryId = null;
		
		try {
			
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			transaction = session.beginTransaction();
			
			
			  fkCategoryId = pdreg.getFkCategoryId(); 
			  Category supdetail =(Category) session.load(Category.class, fkCategoryId);
			   pdreg.setCategory(supdetail);
			 
			 
			
			session.save(pdreg);
			System.out.println("Data in dao sucessfully saved");
			
			
			transaction.commit();
		} catch (RuntimeException e) {
			
			try {
				transaction.rollback();
			} catch (RuntimeException e2) {
				Log.error("Error in PRODUCTDETAIL Add FORM", e2);
			}
		}
		
		finally
		{
			if(session!=null)
			{
				hbu.closeSession(session);
			}
		}
		
		
	   }
	
/////////////
    public List getAllItemNameOil(){
	
	HibernateUtility hbu = null;
	Session session =  null;
	Query query = null;
	List<Object[]> list = null;
	List<BarrelEntryBean> itemList = null;
	try {
		hbu = HibernateUtility.getInstance();
		session = hbu.getHibernateSession();
		query = session.createSQLQuery("select p.ProductName, c.category_name, p.HsnSacNo,p.modelName from barrelentry p left join categories c ON p.FkCatId = c.pk_category_id");
		list = query.list();
		
		itemList = new ArrayList<BarrelEntryBean>(0);
		   
		   for (Object[] objects : list) {
				 
			   BarrelEntryBean item  = new BarrelEntryBean();
			     item.setItemName(objects[0].toString());
			     System.out.println("ItemName++++++++++++"+objects[0].toString());
			     item.setCategoryName(objects[1].toString());
			     item.setHsnsacno(objects[2].toString());
			     item.setModelName(objects[3].toString());
			     
			     itemList.add(item);
			    	
				}
		
	} catch (RuntimeException e) {
		Log.error("Error in getAllSupplier ", e);
	}
	finally
	{
		if (session!=null) {
			hbu.closeSession(session);
		}
	}
	
	return itemList;
  }

    public void regGoodReceive(BarrelEntryHibernate gd) {
		// TODO Auto-generated method stub
		
		
		HibernateUtility hbu = null ;
		Session session  = null;
		Transaction transaction = null;
		
		
		try {
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			transaction = session.beginTransaction();
//			System.out.println(Arrays.toString(gd));
			session.save(gd);
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
}
