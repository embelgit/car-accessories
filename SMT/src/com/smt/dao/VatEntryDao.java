package com.smt.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.smt.hibernate.VatEntry;

public class VatEntryDao {
/*Register Car Entry...  */
	
	public void registerVatEntry(VatEntry vat) {
		// TODO Auto-generated method stub
		
		com.smt.utility.HibernateUtility hbu=null;
		Session session=null;
		Transaction transaction=null;
		try{
		hbu = com.smt.utility.HibernateUtility.getInstance();
		session = hbu.getHibernateSession();
		transaction = session.beginTransaction();
	
		
		session.save(vat);
		transaction.commit();
		
		}
		
		/*catch(RuntimeException e){
			try{
				transaction.rollback();
			}catch(RuntimeException rbe)
			{
				Log.error("Couldn't roll back tranaction",rbe);
			}	
		}*/finally{
		hbu.closeSession(session);
		}
		
	}
}
