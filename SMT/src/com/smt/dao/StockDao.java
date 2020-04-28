package com.smt.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.smt.bean.StocktemNameBean;
import com.smt.hibernate.Stock;
import com.smt.utility.HibernateUtility;





public class StockDao {
	
	/*To Fetch ItemName From Stock Table */
	public List getAllStockEntry()
	{
		com.smt.utility.HibernateUtility hbu=null;
		Session session=null;
		List list=null;
		try{
		 hbu = HibernateUtility.getInstance();
		 session = hbu.getHibernateSession();
		 Query query = session.createQuery("from Stock");
		 list = query.list();
		}
			catch(Exception e){	
				e.printStackTrace();
		}
			finally
			{
					if(session!=null){
					hbu.closeSession(session);
				}
			}
		
	return list;
	}
    /*To register New ItemName In Stock Table*/
	public void registerStock(Stock newEntry) {
		
		HibernateUtility hbu=null;
		Session session=null;
		Transaction transaction=null;
		
		try{
		 hbu = HibernateUtility.getInstance();
		 session = hbu.getHibernateSession();
		 transaction = session.beginTransaction();
		 
		 DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		 Date date = new Date();
		
		 newEntry.setUpdateDate(date);
		
		session.save(newEntry);
		transaction.commit();
		}
		
		catch(RuntimeException e){
			try{
				transaction.rollback();
			}catch(Exception rbe)
			{
				rbe.printStackTrace();
			}	
		}finally{
		hbu.closeSession(session);
		}
		
	}



/* To Fetch ItemName And Quantity from Stack Table  */ 

public List getItemNameAndQuantity(){
	
	HibernateUtility hbu =null;
	Session session = null ;
	Query query = null ;
	List <Object[]> list =null;
	List<StocktemNameBean> sbean = null ;
	
	try {
		hbu = HibernateUtility.getInstance();
		session = hbu.getHibernateSession();
		query=session.createSQLQuery("select ItemName, Quntity, PkStockId , CategoryName from stock_details");
		
		list=query.list();
		sbean=new ArrayList<StocktemNameBean>(0);
		for (Object[] object : list) {
		
		StocktemNameBean scBean=new StocktemNameBean();
		scBean.setItemName(object[0].toString());
		scBean.setQuantity(Long.parseLong(object[1].toString()));
		scBean.setPkStockId(Long.parseLong(object[2].toString()));
		scBean.setCatName(object[3].toString());
	
		sbean.add(scBean);
		
		}

	} catch (Exception e) {
		e.printStackTrace();
	}
	finally
	{
		if (session!=null) {
			
			hbu.closeSession(session);
		}
	}
		
	return sbean;
  }

// get current Stock
public List<Stock> getAllCurrentStock() {
	// TODO Auto-generated method stub
	HibernateUtility hbu=null;
	Session session=null;
	List<Stock> catList=null;
	try
	{
		hbu = HibernateUtility.getInstance();
	 session = hbu.getHibernateSession();
	 Query query2 = session.createQuery("select itemName, catName, quantity, UpdateDate,totalLitre from Stock");
		
        List<Object[]> list = query2.list();
        catList= new ArrayList<Stock>(0);
		
		
		for (Object[] object : list) {
				
			Stock reports = new Stock();
			
			reports.setItemName(object[0].toString());
			reports.setCatName(object[1].toString());
			reports.setQuantity(Long.parseLong(object[2].toString()));
			reports.setDate(object[3].toString());
			reports.setTotalLitre(Double.parseDouble(object[4].toString()));
			
			catList.add(reports); 
	
		}}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return catList;
}

//category wise stock
public List<Stock> getCategoryWiseStock(String catId) {
	HibernateUtility hbu=null;
	Session session=null;
	List<Stock> catList=null;
	try
	{
		hbu = HibernateUtility.getInstance();
	 session = hbu.getHibernateSession();
	 Query query2 = session.createQuery("select itemName, catName, quantity, UpdateDate,totalLitre from Stock where catName=:catId");
	 query2.setParameter("catId", catId);
        List<Object[]> list = query2.list();
        catList= new ArrayList<Stock>(0);
		
		
		for (Object[] object : list) {
				
			Stock reports = new Stock();
			
			reports.setItemName(object[0].toString());
			reports.setCatName(object[1].toString());
			reports.setQuantity(Long.parseLong(object[2].toString()));
			reports.setDate(object[3].toString());
			reports.setTotalLitre(Double.parseDouble(object[4].toString()));
			
			catList.add(reports); 
	
		}}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return catList;
	
}


/*To Fetch ItemName From Stock Table */
public List getAllProductForNotification()
{
	com.smt.utility.HibernateUtility hbu=null;
	Session session=null;
	List list=null;
	try{
	 hbu = HibernateUtility.getInstance();
	 session = hbu.getHibernateSession();
	 Query query = session.createQuery("SELECT * from smt_sc.stock_details WHERE Quantity < 10");
	 list = query.list();
	}
		catch(Exception e){	
			e.printStackTrace();
	}
		finally
		{
				if(session!=null){
				hbu.closeSession(session);
			}
		}
	
return list;
}



/*
 * Name: Meghraj Menkudle
 * Date: 11-3-17
 * method:getTotalStockReport()
 * Reason: Get the report of all Stock
 */
	
/*public List getTotalStockReport(){
	
	HibernateUtility hbu=null;
	Session session=null;
	List<StockReport> saleList=null;
try{	

	hbu = HibernateUtility.getInstance();
	session = hbu.getHibernateSession();

	Query query=session.createSQLQuery("SELECT productname.ItemName, stock.Quntity FROM stock INNER JOIN productname ON stock.Fk_ItemName_Id = productname.PkProNameId;");
	//Query query = session.createQuery("from PurchaseBill2");
	List<Object[]> list = query.list();


	saleList= new ArrayList<StockReport>(0);


for (Object[] object : list) {	
	StockReport reports = new StockReport();
	
	System.out.println("RAJJ"+Arrays.toString(object));
	
	
	reports.setItemName(object[0].toString());		
	reports.setQuantity(Double.parseDouble(object[1].toString()));
	
	saleList.add(reports);

}}catch(RuntimeException e){	

}
finally{

hbu.closeSession(session);	
}
return saleList;
}*/

}
