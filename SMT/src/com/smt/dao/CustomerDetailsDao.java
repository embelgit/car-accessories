package com.smt.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jfree.util.Log;

import com.smt.bean.GetCreditCustomerDetails;
import com.smt.hibernate.CustomerDetailsBean;
import com.smt.utility.HibernateUtility;



public class CustomerDetailsDao {

	public void valCustomerDetails(CustomerDetailsBean cdb){
		System.out.println("In DAO");
		
		HibernateUtility hbu=null;
		Session session = null;
		Transaction transaction = null;
		try{
		 hbu = HibernateUtility.getInstance();
		session = hbu.getHibernateSession();
		 System.out.println("got session ");
		 transaction = session.beginTransaction();
	
		 System.out.println("Tx started");
		session.save(cdb);
		transaction.commit();
		System.out.println("Successful");
		}
		catch(RuntimeException e){
			try{
				transaction.rollback();
			}catch(RuntimeException rbe)
			{
				Log.error("Couldn't roll back tranaction",rbe);
			}	
		}finally{
		hbu.closeSession(session);
		}
	/*hbu.closeSession(session);*/
	}
	
	
	
	public List<CustomerDetailsBean> getAllCustomer()
	{
		HibernateUtility hbu = null;
		Session session =  null;
		Query query = null;
		 List<CustomerDetailsBean> list = null;
		 try {
			 hbu = HibernateUtility.getInstance();
			 session = hbu.getHibernateSession();
			query = session.createQuery("from CustomerDetailsBean");
			 /*query = session.createQuery("from CustomerDetailsBean");*/
			 list = query.list(); 
		} catch (Exception e) {
			Log.error("Error in getAllCustomer", e);
		}
		 
		 finally
		 {
			 if (session!=null) {
				hbu.closeSession(session);
			}
		 }
			
				return list;
		
	}
	
	
	
public List getVillageByCustomerName(String creditCustomerId) {
		
		HibernateUtility hbu = null ;
    	 Session session = null;
    	 List list  = null;
    	 try {
    		 hbu = HibernateUtility.getInstance();
    		 session = hbu.getHibernateSession();
 			Query query = session.createSQLQuery("select c.address , c.first_name, c.contact_no, c.aadhar_no from customer_details c where c.pk_customer_id ="+creditCustomerId);
 			list = query.list();
 			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
			
    	 finally
		 {
			 if (session!=null) {
				hbu.closeSession(session);
			}
		 }
			return list;
    	
	}



public List getAllBillByCreditCustomer(String fkCustomerId) {

	
	 HibernateUtility hbu = null ;
	 Session session = null;
	 List list  = null;
	 try {
		 String paytype = "y";
		 hbu = HibernateUtility.getInstance();
		 session = hbu.getHibernateSession();
		Query query = session.createSQLQuery("select s.BillNo, s.Date from creditcustomerbill s where s.PaymentDone ='"+paytype+"' and s.fkCrediCustId = '"+fkCustomerId+"'");
	//	query.setParameter("paytype", paytype);
		list = query.list();
		System.out.println("in getAllBillByCreditCustomer() dao query size - "+query.list().size());
	} catch (Exception e) {
		e.printStackTrace();
		// TODO: handle exception
	}
		
	 finally
	 {
		 if (session!=null) {
			hbu.closeSession(session);
		}
	 }
		return list;


}

public List getAllBillByCreditCustomer1(String fkCustomerId) {

	
	 HibernateUtility hbu = null ;
	 Session session = null;
	 List list  = null;
	 try {
		
		 hbu = HibernateUtility.getInstance();
		 session = hbu.getHibernateSession();
		Query query = session.createSQLQuery("select s.BillNo,s.Date from creditcustomerbill s where s.fkCrediCustId="+fkCustomerId);
		
		list = query.list();
		
	} catch (Exception e) {
		e.printStackTrace();
		// TODO: handle exception
	}
		
	 finally
	 {
		 if (session!=null) {
			hbu.closeSession(session);
		}
	 }
		return list;


}



public List getTotalAmountByBill(String billNo, String creditCustomer) {

	 HibernateUtility hbu = null ;
	 Session session = null;
	 List list  = null;
	 try {
		 hbu = HibernateUtility.getInstance();
		 session = hbu.getHibernateSession();
		Query query = session.createSQLQuery("select GrossTotal , Date from creditcustomerbill  where BillNo='"+billNo+"' AND fkCrediCustId = '"+creditCustomer+"'");
		System.out.println("In customerdetailsdao get total");
		list = query.list();
		System.out.println("List size in DAO of total = == = -  "+query.list().size());
	} catch (Exception e) {
		e.printStackTrace();
		// TODO: handle exception
	}
		
	 finally
	 {
		 if (session!=null) {
			hbu.closeSession(session);
		}
	 }
		return list;
	
}



public List getRemainingBalanceAmountByBill(String billNo, String creditCustomer) {
	
	HibernateUtility hbu = null ;
	 Session session = null;
	 List list  = null;
	 try {
		 hbu = HibernateUtility.getInstance();
		 session = hbu.getHibernateSession();
		 Query query = session.createSQLQuery("SELECT balance ,bill_no from credit_customer_payment WHERE bill_no ='"+billNo+"' AND fk_customer_id='"+creditCustomer+"' ORDER BY  pk_credit_customer_id  DESC LIMIT 1 ;");
		
		list = query.list();
		
	} catch (Exception e) {
		e.printStackTrace();
		// TODO: handle exception
	}
		
	 finally
	 {
		 if (session!=null) {
			hbu.closeSession(session);
		}
	 }
		return list;
}


public Double getTotalAmt(String billNo) {
	
	HibernateUtility hbu = null ;
	 Session session = null;
	 List<Object[]> list  = null;
	 Double totalAmount = null;
	 List<GetCreditCustomerDetails> itemlist = null;
	 try {
		 hbu = HibernateUtility.getInstance();
		 session = hbu.getHibernateSession();
			Query query = session.createSQLQuery("select GrossTotal, Date from creditcustomerbill  where BillNo="+billNo);
			list = query.list();
			 itemlist = new ArrayList<GetCreditCustomerDetails>(0);
			 
			 for (Object[] objects : list) {
				 
				GetCreditCustomerDetails bean = new GetCreditCustomerDetails();
				 
				String newBal = (objects[0].toString());
				totalAmount = Double.valueOf(newBal);
			
				itemlist.add(bean);
				}
			
			
	} catch (Exception e) {
		e.printStackTrace();
		// TODO: handle exception
	}
		
	 finally
	 {
		 if (session!=null) {
			hbu.closeSession(session);
		}
	 }
		return totalAmount;
	
}



public List getCreditCustomerForEdit(Long customerId) {

	System.out.println("into dao Credit Customer : "+customerId);
	HibernateUtility hbu = null;
	Session session =  null;
	Query query = null;
	 List list = null;
	 try {
		 hbu = HibernateUtility.getInstance();
		 session = hbu.getHibernateSession();
		 query = session.createSQLQuery("SELECT c.first_name, c.middle_name, c.last_name,c.email_id, c.address, c.contact_no, c.pin_code, c.aadhar_no FROM customer_details c where c.pk_customer_id="+customerId);
		 list = query.list(); 
	} catch (RuntimeException e) {
		e.printStackTrace();
	}
	 
	 finally
	 {
		 if (session!=null) {
			hbu.closeSession(session);
		}
	 }
	 System.out.println("out of dao - return credit customer List : "+list);
			return list;

}

public List getCreditCustomerList(){
	
	HibernateUtility hbu=null;
	Session session=null;
	List<GetCreditCustomerDetails> custList=null;
try{	

	hbu = HibernateUtility.getInstance();
	session = hbu.getHibernateSession();

	Query query=session.createSQLQuery("SELECT first_name, middle_name, last_name, email_id, address, contact_no, pin_code, aadhar_no FROM customer_details");
	//Query query = session.createQuery("from PurchaseBill2");
	List<Object[]> list = query.list();


	custList= new ArrayList<GetCreditCustomerDetails>(0);


for (Object[] object : list) {	
	GetCreditCustomerDetails reports = new GetCreditCustomerDetails();
	
	reports.setFirstName(object[0].toString());
	reports.setMiddleName(object[1].toString());
	reports.setLastName(object[2].toString());
	reports.setAddress(object[4].toString());
	reports.setEmail(object[3].toString());
	reports.setContactNo((BigInteger)object[5]);
	reports.setZipCode((BigInteger)object[6]);
	reports.setAadhar(object[7].toString());
	
	custList.add(reports);

}}catch(RuntimeException e){	

}
finally{

hbu.closeSession(session);	
}
return custList;
}

	
}
