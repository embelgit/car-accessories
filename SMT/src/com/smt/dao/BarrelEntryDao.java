package com.smt.dao;

import org.hibernate.Session;
import org.jfree.util.Log;

import com.smt.hibernate.BarrelEntryHibernate;
import com.smt.hibernate.Category;
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


}
