package com.smt.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jfree.util.Log;

import com.smt.bean.GetSupplierDetails;
import com.smt.bean.SupplierListBean;
import com.smt.bean.SupplierWiseSaleReport;
import com.smt.hibernate.SupplierDetail;
import com.smt.utility.HibernateUtility;

public class SupplierDetailDao {
	
	public void valSupplierDetail( SupplierDetail supplierDetail)
	{
		HibernateUtility hbu = null;
		Session session = null ;
		Transaction transaction = null;
		try {
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			transaction = session.beginTransaction();
			session.save(supplierDetail);
			transaction.commit();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		finally
		{
			if (session!=null) {
				hbu.closeSession(session);
			}
		}
		
	}
	
	public List getAllSupplier()
	{
		HibernateUtility hbu = null;
		Session session =  null;
		Query query = null;
		List list = null;
		try {
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			query = session.createQuery("from SupplierDetail");
			list = query.list();
			
		} catch (RuntimeException e) {
			Log.error("Error in getAllSupplier ", e);
		}
		finally
		{
			if (session!=null) {
				hbu.closeSession(session);
			}
		}
		
		return list;
	}
	
	public List getAllMainSuppliers(){
		
		HibernateUtility hbu = null;
		Session session =  null;
		Query query = null;
		List list = null;
		try {
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			query = session.createQuery("from SupplierDetail");
			list = query.list();
			
		} catch (RuntimeException e) {
			Log.error("Error in getAllSupplier ", e);
		}
		finally
		{
			if (session!=null) {
				hbu.closeSession(session);
			}
		}
		
		return list;
	}
	
		public List getSupplierWiseSaleReport(){
			HibernateUtility hbu = null;
			Session session =  null;
			Query query = null;
			List <Object[]> list = null;
			List<SupplierWiseSaleReport> suppBean = null ;
			try {
				hbu = HibernateUtility.getInstance();
				session = hbu.getHibernateSession();
				query=session.createSQLQuery("select c.customerBill,c.totalAmt,c.quantity,c1.category_name,i.item_name,s.supplier_name,c.price,c.newTotalAmt from customer_order c left join offer_details o ON c.fk_offerrr_id=o.pk_itemoffer_id left join item_details i ON c.fk_item_id=i.pk_item_id left join product_details p ON i.fk_product_id=p.pk_product_id left join supplier_details s ON p.fk_vendor_id=s.supplier_id left join categories c1 ON p.fk_cat_id=c1.pk_category_id where (sale_return) IN ('No')");
				list=query.list();
				suppBean=new ArrayList<SupplierWiseSaleReport>(0);
				for (Object[] object : list) {
				
					SupplierWiseSaleReport sBean=new SupplierWiseSaleReport();
					sBean.setOrderId(Long.parseLong(object[0].toString()));
					sBean.setTotalAmount(Double.parseDouble(object[1].toString()));
					BigDecimal qunt = (BigDecimal) object[2] ;
					sBean.setQuantity(qunt);
					sBean.setCategoryName(object[3].toString());
					sBean.setItemName(object[4].toString());
					sBean.setSupplierName(object[5].toString());
					sBean.setSalePrice(Double.parseDouble(object[6].toString()));
					sBean.setNetAmount(Double.parseDouble(object[7].toString()));
					suppBean.add(sBean);
			}	
			} catch (RuntimeException e) {
				Log.error("Error in Method", e);
			}
		
		return suppBean;		
}

	// get all Information about Supplier on SupplierEdit Form
	public List getEditSupplier1(Long suppilerId) {
		HibernateUtility hbu = null;
		Session session =  null;
		Query query = null;
		 List list = null;
		 try {
			 hbu = HibernateUtility.getInstance();
			 session = hbu.getHibernateSession();
			 query = session.createQuery("select  s.address, s.city, s.contactPerson, s.pin, s.email, s.mobileno, s.panNo, s.supplierName from SupplierDetail s where  supplierId ="+suppilerId);
			 list = query.list(); 
		} catch (RuntimeException e) {
			Log.error("Error in getEditSupplier1", e);
		}
		 
		 finally
		 {
			 if (session!=null) {
				hbu.closeSession(session);
			}
		 }
				return list;
	}

	
	public List getAllSupplierList(){

		HibernateUtility hbu = null;
		Session session =  null;
		Query query = null;
		List <Object[]> list = null;
		List<SupplierListBean> suppBean = null ;
		try {
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			query=session.createSQLQuery("select s.supplier_id, s.supplier_name, s.address, s.bank_name, s.branch_name, s.birthday, s.contact_person, s.product_type, s.email, s.mobileNo, s.pan_no, s.account_no, s.tin_no, s.city, s.credit_limit, s.cst_no, b.FirstName, b.LastName from supplier_details s left join broker b ON b.pk_broker_id=s.Broker_id group by s.supplier_id");
			list=query.list();
			suppBean=new ArrayList<SupplierListBean>(0);
			for (Object[] object : list) {
				SupplierListBean sBean=new SupplierListBean();
				sBean.setSupplierId(Long.parseLong(object[0].toString()));
				sBean.setSupplierName(object[1].toString());
				sBean.setAddress(object[2].toString());
				sBean.setBankName(object[3].toString());
				sBean.setBranchName(object[4].toString());
				sBean.setContactPerson(object[6].toString());
				sBean.setProductType(object[7].toString());
				sBean.setEmail(object[8].toString());
				sBean.setPanNo(Long.parseLong(object[10].toString()));
				sBean.setTinNo(Long.parseLong(object[12].toString()));
				sBean.setCity(object[13].toString());
				sBean.setCreditLimit(Double.parseDouble(object[14].toString()));
				
				
				suppBean.add(sBean);
		}	
		} catch (RuntimeException e) {
		
			Log.error("Error in getAllSupplierList ", e);
		}
		finally
		{
			if (session!=null) {
				hbu.closeSession(session);
			}
		}

		return suppBean;
	}

	
	// get alll supplier name
	public List<SupplierDetail> getAllMAinSupp() {
		// TODO Auto-generated method stub
		HibernateUtility hbu=null;
		Session session=null;
		List<SupplierDetail> catList=null;
		try
		{
			Long k = 0l;
			hbu = HibernateUtility.getInstance();
		 session = hbu.getHibernateSession();
		 Query query2 = session.createQuery("select supplierName, address, city, mobileno, contactPerson, email, panNo, pin  from SupplierDetail");
			
	        List<Object[]> list = query2.list();
	        catList= new ArrayList<SupplierDetail>(0);
			
			
			for (Object[] object : list) {
				k++;	
				SupplierDetail reports = new SupplierDetail();
				reports.setSerialnumber(k);
				reports.setSupplierName(object[0].toString());
				reports.setAddress(object[1].toString());
				reports.setCity(object[2].toString());
				reports.setMobileno(Long.parseLong(object[3].toString()));
				reports.setContactPerson(object[4].toString());
				reports.setEmail(object[5].toString());
				reports.setPanNo(object[6].toString());
				reports.setPin(Long.parseLong(object[7].toString()));
								
				catList.add(reports); 
		
			}}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return catList;
	}
	

public List getSupplierNameByToPay()
{
	
	HibernateUtility hbu=null;
	Session session=null;
	List list=null;
		try
		{
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query = session.createQuery("from SupplierDetail");
			list = query.list();
		}
			catch(RuntimeException e)
			{	
				e.printStackTrace();
		}
		finally	{
					if(session!=null)
					{
						hbu.closeSession(session);
					}
		}
		
		return list;
	
	}

public List getEmployeeNameByToPay()
{
	
	HibernateUtility hbu=null;
	Session session=null;
	List list=null;
		try
		{
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query = session.createQuery("from EmployeeDetailsBean");
			list = query.list();
		}
			catch(RuntimeException e)
			{	
				e.printStackTrace();
		}
		finally	{
					if(session!=null)
					{
						hbu.closeSession(session);
					}
		}
		
		return list;
	
	}

public List getAllBillBySuppliers(String supplierId) {
	// TODO Auto-generated method stub
	 HibernateUtility hbu = null ;
	 Session session = null;
	 List list  = null;
	 try {
		 String paymentdone = "y";
		 hbu = HibernateUtility.getInstance();
		 session = hbu.getHibernateSession();
			Query query = session.createSQLQuery("select s.BillNo,s.FksuppId from GoodReceive s where  s.paymentDone ='"+paymentdone+"' and s.FksuppId="+supplierId);
		//	query.setParameter("paymentdone",paymentdone);
			list = query.list();
			System.out.println("in getAllBillBySuppliers() dao query size - "+query.list().size());
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


public List getAllUnPaidBillAmount(String supplierId) {
	// TODO Auto-generated method stub
	 HibernateUtility hbu = null ;
	 Session session = null;
	 List<GetSupplierDetails> custList=null;
	 try {
		 String paymentdone = "y";
		 Long supId = Long.parseLong(supplierId);
		 hbu = HibernateUtility.getInstance();
		 session = hbu.getHibernateSession();
			Query query = session.createSQLQuery("select s.BillNo,s.GrossTotal from GoodReceive s where  s.paymentDone =:paymentdone and s.FksuppId=:supId GROUP  by s.BillNo");
			query.setParameter("paymentdone",paymentdone);
			query.setParameter("supId",supId);
			 List<Object[]> list = query.list();
			
			 custList= new ArrayList<GetSupplierDetails>(0);
			
			
			for (Object[] o : list) {
				
				GetSupplierDetails reports = new GetSupplierDetails();
					
				reports.setBillNo(o[0].toString());
				reports.setTotalAmt(Double.parseDouble(o[1].toString()));
				custList.add(reports); 
			}
	} catch(Exception e)
		{
		e.printStackTrace();
	}
	return custList;

}

public List getAllBillBySuppliers1(String supplierId) {
	// TODO Auto-generated method stub
	 HibernateUtility hbu = null ;
	 Session session = null;
	 List list  = null;
	 try {
		
		 hbu = HibernateUtility.getInstance();
		 session = hbu.getHibernateSession();
			Query query = session.createSQLQuery("select s.BillNo,s.FksuppId from GoodReceive s where s.FksuppId="+supplierId);
			
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

public List getTotalItemByBillNo(String billNo, String supplierId) {
	// TODO Auto-generated method stub
 HibernateUtility hbu = null ;
	 Session session = null;
	 List list  = null;
	 try {
		 hbu = HibernateUtility.getInstance();
		 session = hbu.getHibernateSession();
		 System.out.println("BillNo :: "+billNo);
		 System.out.println("supplierId :: "+supplierId);
		Query query = session.createSQLQuery("select s.PkGoodRecId,s.CategoryName, s.ItemName, s.OrignalQuantity, s.BuyPrice, s.Vat, s.Total, s.ContactPerson, s.BarcodeNo, s.Date, s.Quantity  from goodreceive s where s.BillNo=:billNo And s.FksuppId=:supplierId");
		query.setParameter("billNo",billNo);
		query.setParameter("supplierId",supplierId);
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

}
