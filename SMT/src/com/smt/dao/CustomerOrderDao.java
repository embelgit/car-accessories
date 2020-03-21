package com.smt.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.batik.css.engine.value.StringValue;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jfree.util.Log;

import com.smt.bean.BillBean;
import com.smt.bean.CategoryWisePurchase;
import com.smt.bean.CustomerBean;
import com.smt.bean.DayWiseSale;
import com.smt.bean.GetBill;
import com.smt.bean.GstReportBean;
import com.smt.bean.PurchaseReport;
import com.smt.bean.SaleReport;
import com.smt.bean.SaleReports;
import com.smt.hibernate.TempItemDetail;
import com.smt.utility.HibernateUtility;

public class CustomerOrderDao {
	
	
public void registerBill(com.smt.hibernate.CustomerBill cust) {
		
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
	

//////////////////////////////////////


public void registerBill1(com.smt.hibernate.CustomerBill cust1) {
	
	HibernateUtility hbu = null ;
	Session session  = null;
	Transaction transaction = null;
	
	
	try {
		hbu = HibernateUtility.getInstance();
		session = hbu.getHibernateSession();
		transaction = session.beginTransaction();
		
		session.save(cust1);
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















	
	public void updateTabaleStatus(Long pk_temp_id) {
		// TODO Auto-generated method stub
		
		HibernateUtility hbu=null;
		Session session=null;
		Transaction transaction = null;
	
		try{
		 hbu = HibernateUtility.getInstance();
		 session = hbu.getHibernateSession();
		 transaction = session.beginTransaction();
		
		 TempItemDetail itemDetail = (TempItemDetail) session.get(TempItemDetail.class, new Long(pk_temp_id));
		 itemDetail.setActiveYN('N');
		 session.saveOrUpdate(itemDetail);
		 transaction.commit();
		 
		
		 
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
		
		
	}	

	
	
		public List getAllClosingReport(){
		   java.util.Date date = new java.util.Date();  
		    DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		    String data = (dateFormat.format(date)); //2014/08/06 15:59:4
		    HibernateUtility hbu=null;
		    Session session=null;
		    List<DayWiseSale> saleList=null;
		    try
		    {
				hbu = HibernateUtility.getInstance();
				session = hbu.getHibernateSession();
		 Query query=session.createSQLQuery("select customerBill, item_name , quantity , price ,totalAmt,newTotalAmt from customer_order where is_insert_date="+data);	
			List<Object[]> list = query.list();
			 saleList= new ArrayList<DayWiseSale>(0);	
			for (Object[] object : list) {
				System.out.println(Arrays.toString(object));
				
				DayWiseSale reports = new DayWiseSale();
				reports.setCustomerBill(Long.parseLong(object[0].toString()));
				reports.setItemName(object[1].toString());
				BigDecimal qunt = (BigDecimal) object[2] ;
				reports.setQuantity((qunt));
				reports.setPrice(Double.parseDouble(object[3].toString()));
			
				reports.setTotAmount(Double.parseDouble(object[4].toString()));
				
				Double disTotal = (Double)object[5];
				if (disTotal==null) {
					
					reports.setDicountTotal(Double.parseDouble("0.0"));
				}
				else {    
					reports.setDicountTotal(Double.parseDouble(object[5].toString()));
				}
				saleList.add(reports);		
		}}
		catch(RuntimeException e)
		{
			Log.error("Error in getAllClosingReport()", e);	
		}finally
		{if(session!=null){
			hbu.closeSession(session);	
		}
		}
		return saleList;
	}
		
		//get Data On CustomerBill Using Barcode No amd in TempData Table		
	public List getAllItemDetails(String key,String carNo){
		
			HibernateUtility hbu=null;
			Session session=null;
			List<CategoryWisePurchase> categoryBean=null;
			List<CustomerBean> itemlist=null;
			System.out.println("key & carno = "+key+" & "+carNo);
			try
			{
				
				    System.out.println("shreemant");
					hbu = HibernateUtility.getInstance();
			 session = hbu.getHibernateSession();
			 String sqlQuery = "SELECT ItemName , PkGoodRecId, CategoryName , BarcodeNo, hsnsacno, vat, igst,salePrice FROM GoodReceive WHERE  quantity > 0 AND BarcodeNo ="+key;

			 
			 Query query = session.createSQLQuery(sqlQuery);
			 
				List<Object[]> list = query.list();
		
				/*if(list == null && list.size() == 0 )
				{
					System.exit(0);
				}*/
				
				 itemlist = new ArrayList<CustomerBean>(0);
			     for (Object[] objects : list) {
				 System.out.println(Arrays.toString(objects));
				 CustomerBean bean = new CustomerBean();
				  
				 bean.setItemName(objects[0].toString());
				 bean.setItem_id(Long.parseLong(objects[1].toString()));
				 bean.setCategoryName(objects[2].toString());
				 
				 bean.setBarcodeNo(Long.parseLong(objects[3].toString()));
				 bean.setHsnSacNo(objects[4].toString());
				 bean.setQuantity(0l);
				 //bean.setSalePrice(0d);
				 bean.setSalePrice(Double.parseDouble(objects[7].toString()));
				 bean.setVat(Double.parseDouble(objects[5].toString()));
				 bean.setIgst(Double.parseDouble(objects[6].toString()));
				 bean.setTaxAmount(0d);
				 
				itemlist.add(bean);
				
				
				
				String itemName= (objects[0].toString());
				//Double salePrice= 0d;
				Double salePrice = Double.parseDouble(objects[7].toString());
			   /* Long cat_id= Long.parseLong(objects[3].toString());*/
				Long item_id= Long.parseLong(objects[1].toString());
				Long quantity= 0l;
				Long barcodeNo= Long.parseLong(objects[3].toString());
				String categoryName= (objects[2].toString());
				String hsnSacNo = (objects[4].toString());
				Double vat = Double.parseDouble(objects[5].toString());
				Double igst = Double.parseDouble(objects[6].toString());
				Double taxAmount = 0d;
				
				TempItemDetailDao data = new TempItemDetailDao();
				List stkList  = data.getAllItemEntry();
				
				if(stkList.size() == 0){
					
					com.smt.hibernate.TempItemDetail tid = new com.smt.hibernate.TempItemDetail();
					
					tid.setItemName(itemName);
					
					tid.setSalePrice(salePrice);

					//tid.setCat_id(cat_id);
					
					tid.setItem_id(item_id);
					
					tid.setQuantity(quantity);
					
					tid.setBarcodeNo(barcodeNo);
					tid.setVat(vat);
					tid.setIgst(igst);
					tid.setTaxAmount(taxAmount);
					tid.setHsnSacNo(hsnSacNo);
					
					tid.setCategoryName(categoryName);
					
					tid.setActiveYN('Y');
					
					tid.setCarNo(carNo);
					
					DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
					Date dateobj = new Date();
					System.out.println(df.format(dateobj));
					
					tid.setCurrent_date(dateobj);
					
					TempItemDetailDao dao = new TempItemDetailDao();
					dao.regTempData(tid);
					
					
				}
				else{
					for(int i=0;i<stkList.size();i++){
						
						TempItemDetail st = (TempItemDetail)stkList.get(i);
						Long barcodeNumber = st.getBarcodeNo();
						System.out.println(i+" == "+barcodeNumber);
						String carNumber = st.getCarNo();
						Long PkItemId = st.getPk_temp_id();
						
						if(barcodeNumber.equals(barcodeNo) && carNumber.equals(carNo)){
							
							Long qunty = st.getQuantity();
			        		
			       		    Long updatequnty =  qunty + quantity;
			       				 
			       		    HibernateUtility hbu1 = HibernateUtility.getInstance();
			    		    Session session1 = hbu1.getHibernateSession();
			    		    Transaction transaction = session1.beginTransaction();
			    		    
			    		    TempItemDetail updateStock = (TempItemDetail) session1.get(TempItemDetail.class, new Long(PkItemId));	 
			       		
			       		    updateStock.setQuantity(updatequnty);
			       		 
			       		    session1.saveOrUpdate(updateStock);
			       		    transaction.commit();
			       		    break;
			    		    
						}
						else
						{
			        		/*ItemName is Not Exists */
			        		if(i+1 == stkList.size()){
			        			
			        			TempItemDetail newEntry = new TempItemDetail();
			        			
			        			newEntry.setItemName(itemName);
								
								newEntry.setSalePrice(salePrice);

								//newEntry.setCat_id(cat_id);
								
								newEntry.setItem_id(item_id);
								
								newEntry.setQuantity(quantity);
								
								newEntry.setBarcodeNo(barcodeNo);
								
								newEntry.setVat(vat);
								newEntry.setIgst(igst);
								newEntry.setTaxAmount(taxAmount);
								newEntry.setHsnSacNo(hsnSacNo);
								
								newEntry.setCategoryName(categoryName);
								
								newEntry.setActiveYN('Y');
								
								newEntry.setCarNo(carNo);
								
								DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
								Date dateobj = new Date();
								System.out.println(df.format(dateobj));
								
								newEntry.setCurrent_date(dateobj);
			        			
			        			TempItemDetailDao dao1 = new TempItemDetailDao();
			        			dao1.regTempData(newEntry);
			        			
			        			
			        			
			        			
			        		}
			        	 }
						
						
					  }
					
					}
				
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
	
	
	// car Bill No in regBill
	public List getLastBillNo()
	{
		HibernateUtility hbu=null;
		Session session=null;
		List<BillBean> saleList=null;
		try
		{
			hbu = HibernateUtility.getInstance();
		 session = hbu.getHibernateSession();
		 Query query = session.createSQLQuery("SELECT BillNo, pkBillId FROM customerbill ORDER BY BillNo desc LIMIT 1");
			
			List<Object[]> list = query.list();
			 saleList= new ArrayList<BillBean>(0);
			for (Object[] object : list) {
				System.out.println(Arrays.toString(object));
				BillBean reports = new BillBean();
				reports.setBillNo(Long.parseLong(object[0].toString()));
				saleList.add(reports);	 
		}}
		catch(Exception e)
		{
			e.printStackTrace();	
		}finally
		{if(session!=null){
			session.close();	
		}
		}
		return saleList;
	}
	
	
	
		

	public List getBillPrint(){
		HibernateUtility hbu=null;
	Session session=null;
	List<GetBill> billList=null;
	try
	{
			hbu = HibernateUtility.getInstance();
	 session = hbu.getHibernateSession();
	 Query query = session.createSQLQuery("SELECT customerBill , color FROM customer_order ORDER BY customerBill DESC LIMIT 1");	
		List<Object[]> list = query.list();
		billList= new ArrayList<GetBill>(0);
		for (Object[] object : list) {
			GetBill reports = new GetBill();	
			reports.setCustomerBill(Long.parseLong(object[0].toString()));
			billList.add(reports);
	}}
	catch(RuntimeException e)
	{
		Log.error("Error in getBillPrint()", e);	
	}finally
	{if(session!=null){
		hbu.closeSession(session);	
	}
	}
	return billList;	
	}

	
	
public List getSaleDetailsDateWise(String startDate,String endDate){
	
		HibernateUtility hbu=null;
		Session session=null;
		List<com.smt.bean.SaleReports> saleList=null;
		try
		{
				hbu = HibernateUtility.getInstance();
		 session = hbu.getHibernateSession();
		 Query query2 = session.createSQLQuery("select DATE(c.is_insert_date),c.quantity ,c.newTotalAmt,c.customerBill,c.card,c.cash from customer_order c left join offer_details o ON c.fk_offerrr_id=o.pk_itemoffer_id where c.is_insert_date BETWEEN :stDate AND :edDate AND (sale_return) IN ('No') ");
			query2.setParameter("stDate", startDate);
	        query2.setParameter("edDate", endDate);
	        List<Object[]> list = query2.list();
			 	saleList= new ArrayList<com.smt.bean.SaleReports>(0);
			
			
			for (Object[] object : list) {
				
				com.smt.bean.SaleReports reports = new com.smt.bean.SaleReports();
			
				reports.setSoldDate(object[0].toString());
				System.out.println(Arrays.toString(object));
				BigDecimal qunt = (BigDecimal) object[1] ;
				
				if(!"".equals(qunt))
				{
					reports.setQuantity((qunt));	
				}
				else {
					Double qq= 0.0;
					reports.setQuantity(BigDecimal.valueOf(qq));
				}
				
			
				
				reports.setTotalAmount(Double.parseDouble(object[2].toString()));
			    reports.setCustomerBill(Integer.parseInt(object[3].toString()));
			    
			    Double card=(Double) object[4];
			    
			    if(card==null){
			    	Double nullCard=0.0;
			    	reports.setCardAmt(nullCard);
			    	
			    }
			    else{
			    	
			    	reports.setCardAmt(card);
			    }
			    
			    Double cash=(Double) object[5];
			    
			    if(cash==null){
			    	Double nullcash=0.0;
			    	reports.setCashAmt(nullcash);
			    	
			    }
			    else{
			    	
			    	reports.setCashAmt(cash);
			    }
			    
				saleList.add(reports); 
		}}
		catch(RuntimeException e)
		{
			Log.error("Error in getSaleDetailsDateWise(String startDate,String endDate)", e);	
		}finally
		{if(session!=null){
			hbu.closeSession(session);	
		}
		}
		return saleList;
		
	}
	
	public List getSaleDetailsYearly(String startDate,String endDate){
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		Date stsDAte = null;
		try {
			stsDAte=format.parse(startDate);
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		Date edDate = null ;
		
		try {
			edDate = format.parse(endDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		HibernateUtility hbu=null;
		Session session=null;
		List<com.smt.bean.SaleReports> saleList=null;
		
		try{
		 hbu = HibernateUtility.getInstance();
		 session = hbu.getHibernateSession();
		Query query2 = session.createSQLQuery("select c.customerBill ,DATE(c.is_insert_date) , c.quantity , c.price , c.totalAmt , c.newTotalAmt from customer_order c where c.is_insert_date BETWEEN :stDate AND :edDate ");
		query2.setParameter("stDate", stsDAte);
        query2.setParameter("edDate", edDate);
        List<Object[]> list = query2.list();
		 saleList= new ArrayList<com.smt.bean.SaleReports>(0);
		
		for (Object[] object : list) {	
			System.out.println("result"+Arrays.toString(object));
			com.smt.bean.SaleReports reports = new com.smt.bean.SaleReports();
			reports.setCustomerBill(Integer.parseInt(object[0].toString()));
			reports.setSoldDate(object[1].toString());
			BigDecimal qunt = (BigDecimal) object[2] ;
			reports.setQuantity((qunt));
			reports.setSalePrice(Double.parseDouble(object[3].toString()));
	
			reports.setTotalAmount(Double.parseDouble(object[4].toString()));
		    reports.setNewTotAmt(Double.parseDouble(object[5].toString()));
			saleList.add(reports);
		}}
		catch(RuntimeException e){
			
			Log.error("Error in getSaleDetailsYearly(String startDate,String endDate)", e);
		}
		finally{
			if(session!=null){
				
				hbu.closeSession(session);
			}	
		}
		return saleList;
	}
	
	
	
	
	public List getSaleDetailsBySingalDateWise(String isInsertDate){
	
		HibernateUtility hbu=null;
		Session session=null;
		List<com.smt.bean.SaleReports> saleList=null;
		try
		{
				hbu = HibernateUtility.getInstance();
		 session = hbu.getHibernateSession();
		 Query query = session.createSQLQuery(" select customerBill,  DATE(is_insert_date) , quantity  ,price , totalAmt , newTotalAmt from customer_order where DATE(is_insert_date)=:isInsertDate AND (sale_return) IN ('No')");
			query.setParameter("isInsertDate", isInsertDate);
			List<Object[]> list = query.list();
			 saleList= new ArrayList<com.smt.bean.SaleReports>(0);
			
			
			for (Object[] object : list) {
				
				com.smt.bean.SaleReports reports = new com.smt.bean.SaleReports();
				reports.setCustomerBill(Integer.parseInt(object[0].toString()));
				reports.setSoldDate(object[1].toString());
				BigDecimal qunt = (BigDecimal) object[2] ;
				reports.setQuantity((qunt));
				reports.setSalePrice(Double.parseDouble(object[3].toString()));

				reports.setTotalAmount(Double.parseDouble(object[4].toString()));
				reports.setNetAmount(Double.parseDouble(object[5].toString()));
			  
				saleList.add(reports);
		}}
		catch(RuntimeException e)
		{
			Log.error("Error in getSaleDetailsBySingalDateWise(String isInsertDate)", e);	
		}finally
		{if(session!=null){
			hbu.closeSession(session);	
		}
		}
		return saleList;	
	}
	
public List getSaleDetailsByBillNO(String billNo) {
		
		HibernateUtility hbu=null;
		Session session=null;
		 List<SaleReports> saleReports = null; 
		try{
		 hbu = HibernateUtility.getInstance();
		 session = hbu.getHibernateSession();
			Query query = session.createSQLQuery("select c.customerBill, DATE(c.is_insert_date) , c.quantity  ,c.price , c.totalAmt , c.newTotalAmt ,c.fk_item_id ,c.item_name,c.discount,c.color,e.firstName from customer_order c left join employeedetails e ON c.fk_emp_id=e.id where customerBill="+billNo);
			List<Object[]> list = query.list();
		saleReports = new ArrayList<SaleReports>(0);
		for (Object[] object : list) {
			
			
			System.out.println("eeeeeE"+Arrays.toString(object));
			com.smt.bean.SaleReports reports = new com.smt.bean.SaleReports();
			reports.setCustomerBill(Integer.parseInt(object[0].toString()));
			reports.setSoldDate(object[1].toString());
			BigDecimal qunt = (BigDecimal) object[2] ;
			reports.setQuantity((qunt));
			reports.setSalePrice(Double.parseDouble(object[3].toString()));

			reports.setTotalAmount(Double.parseDouble(object[4].toString()));
			reports.setNetAmount(Double.parseDouble(object[5].toString()));
		    reports.setItemId((BigInteger)object[6]);
		    reports.setItemName(object[7].toString());
		    BigDecimal vl =  (BigDecimal) (object[8]);
		    
		    BigDecimal nullDiss=new BigDecimal(0.0);
		    
		    if(vl==null)
		    {
		    	reports.setDiscountforsalereturn(nullDiss);
		    }
		    else {
		    	reports.setDiscountforsalereturn(vl);	
			}
		    
		   
		    reports.setColor(object[9].toString());
		    
		    String empName = (String) object[10];
		    
		    if(!"".equals(empName)){
		    	
		    	reports.setEmpName(empName);
		    }
		    else{
		    	reports.setEmpName("N/A");
		    	 
		    }
		    
		   
		   
			saleReports.add(reports);
			
		}
		 
		}catch(RuntimeException e){	
			Log.error("Error in getSaleDetailsByBillNO(String billNo)", e);
		}
		finally{
			if(session!=null){
				hbu.closeSession(session);
			}
				
		}
		return saleReports;
		
			
	}

public List getAllClosingReportPayments(){
	   java.util.Date date = new java.util.Date();  
	    DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
	    String data = (dateFormat.format(date)); //2014/08/06 15:59:4
	    HibernateUtility hbu=null;
	    Session session=null;
	    List<DayWiseSale> saleList=null;
	    try
	    {
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
	 Query query=session.createSQLQuery("select card , cash  ,SUM(newTotalAmt) ,customerBill, is_insert_date   from customer_order  where  is_insert_date=:data AND (sale_return) IN ('No') GROUP BY customerBill  " );	
	 query.setParameter("data", data)	;
	 List<Object[]> list = query.list();
		 saleList= new ArrayList<DayWiseSale>(0);	
		for (Object[] object : list) {
			
			DayWiseSale reports = new DayWiseSale();
			reports.setCard(Double.parseDouble(object[0].toString()));
			reports.setCash(Double.parseDouble(object[1].toString()));
			reports.setNewTotalAmt(Double.parseDouble(object[2].toString()));
			reports.setCustomerBill(Long.parseLong(object[3].toString()));
			saleList.add(reports);		
	}}
	catch(RuntimeException e)
	{
		Log.error("error in getAllClosingReportPayments()", e);	
	}finally
	{if(session!=null){
		hbu.closeSession(session);	
	}
	}
	return saleList;
}

public List getDayClosingBetTwoDates(String startDate,String endDate){
	
	HibernateUtility hbu=null;
	Session session=null;
	List<com.smt.bean.DayWiseSale> saleList=null;
	try
	{
			hbu = HibernateUtility.getInstance();
	 session = hbu.getHibernateSession();
	 Query query2 = session.createSQLQuery("select card , cash  ,SUM(newTotalAmt) ,customerBill, is_insert_date   from customer_order c where c.is_insert_date BETWEEN :stDate AND :edDate   GROUP BY customerBill ");
		query2.setParameter("stDate", startDate);
        query2.setParameter("edDate", endDate);
        List<Object[]> list = query2.list();
		 saleList= new ArrayList<DayWiseSale>(0);	
		for (Object[] object : list) {
			System.out.println(Arrays.toString(object));
			
			DayWiseSale reports = new DayWiseSale();
			 Double cash = (Double) object[0];
			 if (cash==null) {
				
				 reports.setCash(0.0);
			}
			 else {
				reports.setCash(cash);
			}
			 
			 Double card = (Double) object[1];
			 if (cash==null) {
					
				 reports.setCard(0.0);
			}
			 else {
				reports.setCard(card);
			}
			reports.setNewTotalAmt(Double.parseDouble(object[2].toString()));
			reports.setCustomerBill(Long.parseLong(object[3].toString()));
			saleList.add(reports);		
	}}
	catch(RuntimeException e)
	{
		Log.error("Error in getDayClosingBetTwoDates(String startDate,String endDate)", e);	
	}finally
	{if(session!=null){
		hbu.closeSession(session);	
	}
	}
	return saleList;
	
}
	
public List getPaymentDetailsBySingalDateWise(String data){
	
	HibernateUtility hbu=null;
	Session session=null;
	List<DayWiseSale> saleList=null;
	try
	{
			hbu = HibernateUtility.getInstance();
	 session = hbu.getHibernateSession();
	 
	 Query query=session.createSQLQuery("select card , cash  ,SUM(newTotalAmt) ,customerBill, is_insert_date   from customer_order  where  is_insert_date=:data GROUP BY customerBill " );	
	 query.setParameter("data", data)	;
		List<Object[]> list = query.list();
		 saleList= new ArrayList<DayWiseSale>(0);
		
		
		for (Object[] object : list) {
			
			
			DayWiseSale reports = new DayWiseSale();
			reports.setCard(Double.parseDouble(object[0].toString()));
			reports.setCash(Double.parseDouble(object[1].toString()));
			reports.setNewTotalAmt(Double.parseDouble(object[2].toString()));
			reports.setCustomerBill(Long.parseLong(object[3].toString()));
			saleList.add(reports);		
	}}
	catch(RuntimeException e)
	{
		Log.error("Error in getPaymentDetailsBySingalDateWise(String data)", e);	
	}finally
	{if(session!=null){
		hbu.closeSession(session);	
	}
	}
	return saleList;	
}


public List<SaleReport> singleDatePurchase(Date adate) {
	// TODO Auto-generated method stub
	HibernateUtility hbu=null;
	Session session=null;
	List<SaleReport> catList=null;
	try
	{
		hbu = HibernateUtility.getInstance();
	 session = hbu.getHibernateSession();
	// Query query2 = session.createQuery("select billNo, carNo, barcodeNo, itemName, categoryName, salePrice, ownerName, contactNo, totalAmt, discount from CustomerBill where current_date=:adate");
	 Query query2 = session.createSQLQuery("select s.BillNo, s.CarNo, s.BarcodeNo, s.ItemName, s.CategoryName, s.SalePrice, s.OwnerName, s.ContactNo, s.TotalAmount, s.Discount from customerbill s where s.Date =:adate UNION select o.BillNo, o.CarNo, o.BarcodeNo, o.ItemName, o.CategoryName, o.SalePrice, o.OwnerName, o.ContactNo, o.TotalAmount, o.Discount from otherbill o where o.Date =:adate");
	 query2.setParameter("adate", adate);
        List<Object[]> list = query2.list();
        catList= new ArrayList<SaleReport>(0);
		
		
		for (Object[] object : list) {
				
			SaleReport reports = new SaleReport();
			
			reports.setBillNo(Long.parseLong(object[0].toString()));
			reports.setCarNo(object[1].toString());
			reports.setBarcodeNo(Long.parseLong(object[2].toString()));
			reports.setItemName(object[3].toString());
			reports.setCategoryName(object[4].toString());
			reports.setSalePrice(Double.parseDouble(object[5].toString()));
			reports.setOwnerName(object[6].toString());
			reports.setContactNo(Long.parseLong(object[7].toString()));
			reports.setTotalAmt(Double.parseDouble(object[8].toString()));
			reports.setDiscount(Double.parseDouble(object[9].toString()));
			
			catList.add(reports); 
	
		}}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return catList;
}


public List<SaleReport> CategoryWiseSaleReport(String categoryName) {
	// TODO Auto-generated method stub
	HibernateUtility hbu=null;
	Session session=null;
	List<SaleReport> catList=null;
	try
	{
		hbu = HibernateUtility.getInstance();
	 session = hbu.getHibernateSession();
	 //Query query2 = session.createQuery("select billNo, carNo, barcodeNo, itemName, categoryName, salePrice, ownerName, contactNo, totalAmt, discount from CustomerBill where CategoryName=:categoryName");
	 Query query2 = session.createSQLQuery("select s.BillNo, s.CarNo, s.BarcodeNo, s.ItemName, s.CategoryName, s.SalePrice, s.OwnerName, s.ContactNo, s.TotalAmount, s.Discount from customerbill s where s.CategoryName =:categoryName UNION select o.BillNo, o.CarNo, o.BarcodeNo, o.ItemName, o.CategoryName, o.SalePrice, o.OwnerName, o.ContactNo, o.TotalAmount, o.Discount from otherbill o where o.CategoryName =:categoryName");
	 query2.setParameter("categoryName", categoryName);
        List<Object[]> list = query2.list();
        catList= new ArrayList<SaleReport>(0);
		
		
		for (Object[] object : list) {
				
			SaleReport reports = new SaleReport();
			
			reports.setBillNo(Long.parseLong(object[0].toString()));
			reports.setCarNo(object[1].toString());
			reports.setBarcodeNo(Long.parseLong(object[2].toString()));
			reports.setItemName(object[3].toString());
			reports.setCategoryName(object[4].toString());
			reports.setSalePrice(Double.parseDouble(object[5].toString()));
			reports.setOwnerName(object[6].toString());
			reports.setContactNo(Long.parseLong(object[7].toString()));
			reports.setTotalAmt(Double.parseDouble(object[8].toString()));
			reports.setDiscount(Double.parseDouble(object[9].toString()));
			
			catList.add(reports); 
	
		}}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return catList;
}



public List<SaleReport> getSaleDetailsBetweenTwoDates(Date adate, Date bdate) {
	// TODO Auto-generated method stub
	HibernateUtility hbu=null;
	Session session=null;
	List<SaleReport> catList=null;
	try
	{
		hbu = HibernateUtility.getInstance();
	 session = hbu.getHibernateSession();
	// Query query2 = session.createQuery("select billNo, carNo, barcodeNo, itemName, categoryName, salePrice, ownerName, contactNo, totalAmt, discount from CustomerBill where current_date BETWEEN :adate AND :bdate");
	 Query query2 = session.createSQLQuery("select s.BillNo, s.CarNo, s.BarcodeNo, s.ItemName, s.CategoryName, s.SalePrice, s.OwnerName, s.ContactNo, s.TotalAmount, s.Discount from customerbill s where s.Date BETWEEN :adate AND :bdate UNION select o.BillNo, o.CarNo, o.BarcodeNo, o.ItemName, o.CategoryName, o.SalePrice, o.OwnerName, o.ContactNo, o.TotalAmount, o.Discount from otherbill o where o.Date BETWEEN :adate AND :bdate");
	 query2.setParameter("adate", adate);
	 query2.setParameter("bdate", bdate);
        List<Object[]> list = query2.list();
        catList= new ArrayList<SaleReport>(0);
		
		
		for (Object[] object : list) {
				
			SaleReport reports = new SaleReport();
			
			reports.setBillNo(Long.parseLong(object[0].toString()));
			reports.setCarNo(object[1].toString());
			reports.setBarcodeNo(Long.parseLong(object[2].toString()));
			reports.setItemName(object[3].toString());
			reports.setCategoryName(object[4].toString());
			reports.setSalePrice(Double.parseDouble(object[5].toString()));
			reports.setOwnerName(object[6].toString());
			reports.setContactNo(Long.parseLong(object[7].toString()));
			reports.setTotalAmt(Double.parseDouble(object[8].toString()));
			reports.setDiscount(Double.parseDouble(object[9].toString()));
			
			catList.add(reports); 
	
		}}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return catList;
}

//day closing report
public List<SaleReport> dayCloseReport() {
	// TODO Auto-generated method stub
	HibernateUtility hbu=null;
	Session session=null;
	List<SaleReport> catList=null;
	try
	{
		hbu = HibernateUtility.getInstance();
	 session = hbu.getHibernateSession();
	 DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date dateobj = new Date();
		System.out.println(df.format(dateobj));
		String newDate = df.format(dateobj);
		 Long k = 0l;
	 Query query2 = session.createSQLQuery("select s.BillNo, s.BarcodeNo, s.ItemName, s.CategoryName, s.SalePrice, s.totalperitem, s.Discount, s.Quantity from customerbill s where s.Date =:newDate UNION select o.BillNo, o.BarcodeNo, o.ItemName, o.CategoryName, o.SalePrice, o.totalperitem, o.Discount, o.Quantity from otherbill o where o.Date =:newDate UNION select c.BillNo, c.BarcodeNo, c.ItemName, c.CategoryName, c.SalePrice, c.totalperitem, c.Discount, c.Quantity from creditcustomerbill c where c.Date =:newDate");
	 query2.setParameter("newDate", newDate);
	
        List<Object[]> list = query2.list();
        catList= new ArrayList<SaleReport>(0);
       
		
		for (Object[] object : list) {
			System.out.println("kjhhv "+Arrays.toString(object));
			SaleReport reports = new SaleReport();
			k++;
			reports.setSrNo(k);
			reports.setBillNo(Long.parseLong(object[0].toString()));
			reports.setBarcodeNo(Long.parseLong(object[1].toString()));
			reports.setItemName(object[2].toString());
			reports.setCategoryName(object[3].toString());
			reports.setSalePrice(Double.parseDouble(object[4].toString()));
			reports.setTotalAmt(Double.parseDouble(object[5].toString()));
			reports.setDiscount(Double.parseDouble(object[6].toString()));
			reports.setQuantity(Long.parseLong(object[7].toString()));
			catList.add(reports); 
	
		}}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return catList;
}

public List getSaleItemByBillNo(String billNo) {
	// TODO Auto-generated method stub
	HibernateUtility hbu = null ;
  	 Session session = null;
  	 List list  = null;
  	 try {
  		 hbu = HibernateUtility.getInstance();
  		 session = hbu.getHibernateSession();
  		 System.out.println("BillNo :: "+billNo);
  		
			Query query = session.createSQLQuery("select s.pkBillId,s.CarNo, s.CategoryName, s.ItemName, s.BarcodeNo, s.Quantity, s.SalePrice, s.ContactNo, s.totalperitem, s.Discount, s.GrossTotal, s.Date  from customerbill s where s.BillNo=:billNo");
			query.setParameter("billNo",billNo);
			
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

// other bill
public List<CustomerBean> getAllItemDetails1(String key) {
	// TODO Auto-generated method stub
	
	HibernateUtility hbu=null;
	Session session=null;
	List<CategoryWisePurchase> categoryBean=null;
	List<CustomerBean> itemlist=null;
	try
	{
		
		    System.out.println("shreemant");
			hbu = HibernateUtility.getInstance();
	 session = hbu.getHibernateSession();
	 String sqlQuery = "SELECT ItemName , PkGoodRecId, CategoryName , BarcodeNo, hsnsacno, vat, igst,salePrice FROM GoodReceive WHERE quantity > 0 AND BarcodeNo ="+key;

	 Query query=session.createSQLQuery(sqlQuery);
		List<Object[]> list = query.list();

		 itemlist = new ArrayList<CustomerBean>(0);
	     for (Object[] objects : list) {
		 System.out.println(Arrays.toString(objects));
		 CustomerBean bean = new CustomerBean();
		  
		 bean.setItemName(objects[0].toString());
		 bean.setItem_id(Long.parseLong(objects[1].toString()));
		 bean.setCategoryName(objects[2].toString());
		 
		 bean.setBarcodeNo(Long.parseLong(objects[3].toString()));
		 bean.setHsnSacNo(objects[4].toString());
		 bean.setQuantity(0l);
		 //bean.setSalePrice(0d);
		 bean.setSalePrice(Double.parseDouble(objects[7].toString()));
		 bean.setDiscountGrid(0d);
		 bean.setDiscountAmt(0d);
		 bean.setVat(Double.parseDouble(objects[5].toString()));
		 bean.setIgst(Double.parseDouble(objects[6].toString()));
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

//get all bill numbers in sale return form
public List getAllBillNumbers() {
	// TODO Auto-generated method stub
	HibernateUtility hbu = null;
	Session session =  null;
	Query query = null;
	List list = null;
	try {
		hbu = HibernateUtility.getInstance();
		session = hbu.getHibernateSession();
		query = session.createQuery("from CustomerBill group by billNo");
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

public List<GstReportBean> gstSaleReportBetweenTwoDates(Date adate,
		Date bdate) {
	
	HibernateUtility hbu=null;
	Session session=null;
	List<GstReportBean> catList=null;
	try
	{   Long k = 0l;
		hbu = HibernateUtility.getInstance();
	 session = hbu.getHibernateSession();
	 Query query2 = session.createSQLQuery("select Date, BillNo, ItemName, SalePrice, Quantity, Gst, Igst, HsnSacNo from creditcustomerbill where date BETWEEN :adate AND :bdate UNION SELECT Date, BillNo, ItemName, SalePrice, Quantity, Gst, Igst, HsnSacNo from customerbill where date BETWEEN :adate AND :bdate UNION SELECT Date, BillNo, ItemName, SalePrice, Quantity, Gst, Igst, HsnSacNo from otherbill where date BETWEEN :adate AND :bdate");
	 query2.setParameter("adate", adate);
	 query2.setParameter("bdate", bdate);
	 List<Object[]> list = query2.list();
        catList= new ArrayList<GstReportBean>(0);
		
		
		for (Object[] object : list) {
		k++;		
		GstReportBean reports = new GstReportBean();
			reports.setSerialnumber(k);
			reports.setFetchDate(object[0].toString());
			//reports.setSupplierName(object[1].toString());
			reports.setBillNo(object[1].toString());
			//reports.setGstTinNo(object[3].toString());
			reports.setItemName(object[2].toString());
			reports.setBuyPrice(Double.parseDouble(object[3].toString()));
			reports.setQuantity(Long.parseLong(object[4].toString()));
			reports.setHsnsacno(object[7].toString());
			
			
			Double qunti = Double.parseDouble(object[4].toString());
			Double byPrice = Double.parseDouble(object[3].toString());
			Double total = byPrice * qunti;
			reports.setTotal(total);
			String gsttaxAmt = object[5].toString();
			String igsttaxAmt = object[6].toString();
			System.out.println("gsttaxAmt"+gsttaxAmt);
			System.out.println("igsttaxAmt"+igsttaxAmt);
			
			if(gsttaxAmt.equals("5.0")){
				Double tx = Double.parseDouble(gsttaxAmt);
				Double taxAmt = (tx/100)*(byPrice);
				Double totalTaxAmount = qunti * taxAmt;
				Double newSalePrice = byPrice + taxAmt;
				Double totalAmount = qunti * newSalePrice;
				reports.setFivePercentageGST((double) Math.round(taxAmt));
				reports.setTwelwePercentageGST(0.0);
				reports.setEighteenPercentageGST(0.0);
				reports.setTwentyEightPercentageGST(0.0);
				reports.setiGSTFivePercentage(0.0);
				reports.setiGSTTwelwePercentage(0.0);
				reports.setiGSTEighteenPercentage(0.0);
				reports.setiGSTTwentyeightPercentage(0.0);
				reports.setTotalTaxAmount((double) Math.round(totalTaxAmount)); 
				reports.setNetAmount((double) Math.round(totalAmount));
				System.out.println("5 % GST Amount"+taxAmt);
			}
			else if(gsttaxAmt.equals("12.0")){
				Double tx = Double.parseDouble(gsttaxAmt);
				Double taxAmt = (tx/100)*(byPrice);
				Double totalTaxAmount = qunti * taxAmt;
				Double newSalePrice = byPrice + taxAmt;
				Double totalAmount = qunti * newSalePrice;
				reports.setFivePercentageGST(0.0);
				reports.setTwelwePercentageGST((double) Math.round(taxAmt));
				reports.setEighteenPercentageGST(0.0);
				reports.setTwentyEightPercentageGST(0.0);
				reports.setiGSTFivePercentage(0.0);
				reports.setiGSTTwelwePercentage(0.0);
				reports.setiGSTEighteenPercentage(0.0);
				reports.setiGSTTwentyeightPercentage(0.0);
				reports.setTotalTaxAmount((double) Math.round(totalTaxAmount)); 
				reports.setNetAmount((double) Math.round(totalAmount));
				System.out.println("5 % GST Amount"+taxAmt);
			
			}else if(gsttaxAmt.equals("18.0")){
				Double tx = Double.parseDouble(gsttaxAmt);
				Double taxAmt = (tx/100)*(byPrice);
				Double totalTaxAmount = qunti * taxAmt;
				Double newSalePrice = byPrice + taxAmt;
				Double totalAmount = qunti * newSalePrice;
				reports.setFivePercentageGST(0.0);
				reports.setTwelwePercentageGST(0.0);
				reports.setEighteenPercentageGST((double) Math.round(taxAmt));
				reports.setTwentyEightPercentageGST(0.0);
				reports.setiGSTFivePercentage(0.0);
				reports.setiGSTTwelwePercentage(0.0);
				reports.setiGSTEighteenPercentage(0.0);
				reports.setiGSTTwentyeightPercentage(0.0);
				reports.setTotalTaxAmount((double) Math.round(totalTaxAmount)); 
				reports.setNetAmount((double) Math.round(totalAmount));
				System.out.println("5 % GST Amount"+taxAmt);
			
			}
			else if(gsttaxAmt.equals("28.0")){
				Double tx = Double.parseDouble(gsttaxAmt);
				Double taxAmt = (tx/100)*(byPrice);
				Double totalTaxAmount = qunti * taxAmt;
				Double newSalePrice = byPrice + taxAmt;
				Double totalAmount = qunti * newSalePrice;
				reports.setFivePercentageGST(0.0);
				reports.setTwelwePercentageGST(0.0);
				reports.setEighteenPercentageGST(0.0);
				reports.setTwentyEightPercentageGST((double) Math.round(taxAmt));
				reports.setiGSTFivePercentage(0.0);
				reports.setiGSTTwelwePercentage(0.0);
				reports.setiGSTEighteenPercentage(0.0);
				reports.setiGSTTwentyeightPercentage(0.0);
				reports.setTotalTaxAmount((double) Math.round(totalTaxAmount)); 
				reports.setNetAmount((double) Math.round(totalAmount));
				System.out.println("5 % GST Amount"+taxAmt);
			
			}
			
			if(igsttaxAmt.equals("5.0")){
				Double tx = Double.parseDouble(igsttaxAmt);
				Double taxAmt = (tx/100)*(byPrice);
				Double totalTaxAmount = qunti * taxAmt;
				Double newSalePrice = byPrice + taxAmt;
				Double totalAmount = qunti * newSalePrice;
				reports.setiGSTFivePercentage((double) Math.round(taxAmt));
				reports.setiGSTTwelwePercentage(0.0);
				reports.setiGSTEighteenPercentage(0.0);
				reports.setiGSTTwentyeightPercentage(0.0);
				reports.setFivePercentageGST(0.0);
				reports.setTwelwePercentageGST(0.0);
				reports.setEighteenPercentageGST(0.0);
				reports.setTwentyEightPercentageGST(0.0);
				reports.setTotalTaxAmount((double) Math.round(totalTaxAmount)); 
				reports.setNetAmount((double) Math.round(totalAmount));
				System.out.println("5 % GST Amount"+taxAmt);
			}
			else if(igsttaxAmt.equals("12.0")){
				Double tx = Double.parseDouble(igsttaxAmt);
				Double taxAmt = (tx/100)*(byPrice);
				Double totalTaxAmount = qunti * taxAmt;
				Double newSalePrice = byPrice + taxAmt;
				Double totalAmount = qunti * newSalePrice;
				reports.setiGSTFivePercentage(0.0);
				reports.setiGSTTwelwePercentage((double) Math.round(taxAmt));
				reports.setiGSTEighteenPercentage(0.0);
				reports.setiGSTTwentyeightPercentage(0.0);
				reports.setFivePercentageGST(0.0);
				reports.setTwelwePercentageGST(0.0);
				reports.setEighteenPercentageGST(0.0);
				reports.setTwentyEightPercentageGST(0.0);
				reports.setTotalTaxAmount((double) Math.round(totalTaxAmount)); 
				reports.setNetAmount((double) Math.round(totalAmount));
				System.out.println("5 % GST Amount"+taxAmt);
			
			}else if(igsttaxAmt.equals("18.0")){
				Double tx = Double.parseDouble(igsttaxAmt);
				Double taxAmt = (tx/100)*(byPrice);
				Double totalTaxAmount = qunti * taxAmt;
				Double newSalePrice = byPrice + taxAmt;
				Double totalAmount = qunti * newSalePrice;
				reports.setiGSTFivePercentage(0.0);
				reports.setiGSTTwelwePercentage(0.0);
				reports.setiGSTEighteenPercentage((double) Math.round(taxAmt));
				reports.setiGSTTwentyeightPercentage(0.0);
				reports.setFivePercentageGST(0.0);
				reports.setTwelwePercentageGST(0.0);
				reports.setEighteenPercentageGST(0.0);
				reports.setTwentyEightPercentageGST(0.0);
				reports.setTotalTaxAmount((double) Math.round(totalTaxAmount)); 
				reports.setNetAmount((double) Math.round(totalAmount));
				System.out.println("5 % GST Amount"+taxAmt);
			
			}
			else if(igsttaxAmt.equals("28.0")){
				Double tx = Double.parseDouble(igsttaxAmt);
				Double taxAmt = (tx/100)*(byPrice);
				Double totalTaxAmount = qunti * taxAmt;
				Double newSalePrice = byPrice + taxAmt;
				Double totalAmount = qunti * newSalePrice;
				reports.setiGSTFivePercentage(0.0);
				reports.setiGSTTwelwePercentage(0.0);
				reports.setiGSTEighteenPercentage(0.0);
				reports.setiGSTTwentyeightPercentage((double) Math.round(taxAmt));
				reports.setFivePercentageGST(0.0);
				reports.setTwelwePercentageGST(0.0);
				reports.setEighteenPercentageGST(0.0);
				reports.setTwentyEightPercentageGST(0.0);
				reports.setTotalTaxAmount((double) Math.round(totalTaxAmount)); 
				reports.setNetAmount((double) Math.round(totalAmount));
				System.out.println("5 % GST Amount"+taxAmt);
			
			}
			if(gsttaxAmt.equals("0.0") && igsttaxAmt.equals("0.0")){
				
				Double totalAmount = qunti * byPrice;
				reports.setiGSTFivePercentage(0.0);
				reports.setiGSTTwelwePercentage(0.0);
				reports.setiGSTEighteenPercentage(0.0);
				reports.setiGSTTwentyeightPercentage(0.0);
				reports.setFivePercentageGST(0.0);
				reports.setTwelwePercentageGST(0.0);
				reports.setEighteenPercentageGST(0.0);
				reports.setTwentyEightPercentageGST(0.0);
				reports.setTotalTaxAmount(0.0); 
				reports.setNetAmount((double) Math.round(totalAmount));
				
			}
			catList.add(reports); 
	
		}}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return catList;
	}

// Single Date Sale Report
public List<SaleReport> singleDateSaleReport(Date adate) {
	// TODO Auto-generated method stub
	HibernateUtility hbu=null;
	Session session=null;
	List<SaleReport> catList=null;
	try
	{
		 hbu = HibernateUtility.getInstance();
		 session = hbu.getHibernateSession();
			
		 Long k = 0l;
		 Query query2 = session.createSQLQuery("select s.BillNo, s.BarcodeNo, s.ItemName, s.CategoryName, s.SalePrice, s.totalperitem, s.HsnSacNo, s.Quantity, s.TaxAmount, s.Gst, s.Igst from customerbill s where s.Date =:adate UNION select o.BillNo, o.BarcodeNo, o.ItemName, o.CategoryName, o.SalePrice, o.totalperitem, o.HsnSacNo, o.Quantity, o.TaxAmount, o.Gst, o.Igst from otherbill o where o.Date =:adate UNION select c.BillNo, c.BarcodeNo, c.ItemName, c.CategoryName, c.SalePrice, c.totalperitem, c.HsnSacNo, c.Quantity, c.TaxAmount, c.Gst, c.Igst from creditcustomerbill c where c.Date =:adate");
		 query2.setParameter("adate", adate);
		
		 
	        List<Object[]> list = query2.list();
	        catList= new ArrayList<SaleReport>(0);
	       
			
			for (Object[] object : list) {
				System.out.println("kjhhv "+Arrays.toString(object));
				SaleReport reports = new SaleReport();
				k++;
				reports.setSrNo(k);
				reports.setBillNo(Long.parseLong(object[0].toString()));
				reports.setBarcodeNo(Long.parseLong(object[1].toString()));
				reports.setItemName(object[2].toString());
				reports.setCategoryName(object[3].toString());
				reports.setSalePrice((double) Math.round(Double.parseDouble(object[4].toString())));
				//reports.setTotalAmt(Double.parseDouble(object[5].toString()));
				//reports.setDiscount((double) Math.round(Double.parseDouble(object[6].toString())));
				reports.setHsnSacNo(object[6].toString());
				reports.setQuantity(Long.parseLong(object[7].toString()));
				reports.setTaxAmount(Double.parseDouble(object[8].toString()));
				String gst = object[9].toString();
				String igst = object[10].toString();
				if(gst.equals("0.0")){
					reports.setGst(Double.parseDouble(igst));
				}
				if(igst.equals("0.0")){
					reports.setGst(Double.parseDouble(gst));
				}
				double quan = Double.parseDouble(object[7].toString());
				double saleP = Double.parseDouble(object[4].toString());
				double taxAmt = Double.parseDouble(object[8].toString());
				double saleTotal = quan * saleP;
				double totalAmt = saleTotal + taxAmt;
				reports.setTotalperItem((double) Math.round(saleTotal));
				reports.setTotalAmt((double) Math.round(totalAmt));
				catList.add(reports);  
	
		}}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return catList;
}

//Two Date Sale Report
public List<SaleReport> twoDateSaleReport(Date adate, Date bdate) {
	// TODO Auto-generated method stub
	HibernateUtility hbu=null;
	Session session=null;
	List<SaleReport> catList=null;
	try
	{
		hbu = HibernateUtility.getInstance();
	 session = hbu.getHibernateSession();
	 DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date dateobj = new Date();
		System.out.println(df.format(dateobj));
		String newDate = df.format(adate);
		String newDate1 = df.format(bdate);
		 Long k = 0l;
	 Query query2 = session.createSQLQuery("select s.BillNo, s.BarcodeNo, s.ItemName, s.CategoryName, s.SalePrice, s.totalperitem, s.HsnSacNo, s.Quantity, s.TaxAmount, s.Gst, s.Igst from customerbill s where s.Date BETWEEN :newDate AND :newDate1 UNION select o.BillNo, o.BarcodeNo, o.ItemName, o.CategoryName, o.SalePrice, o.totalperitem, o.HsnSacNo, o.Quantity, o.TaxAmount, o.Gst, o.Igst from otherbill o where o.Date BETWEEN :newDate AND :newDate1 UNION select c.BillNo, c.BarcodeNo, c.ItemName, c.CategoryName, c.SalePrice, c.totalperitem, c.HsnSacNo, c.Quantity, c.TaxAmount, c.Gst, c.Igst from creditcustomerbill c where c.Date BETWEEN :newDate AND :newDate1");
	 query2.setParameter("newDate", newDate);
	 query2.setParameter("newDate1", newDate1);
	 
        List<Object[]> list = query2.list();
        catList= new ArrayList<SaleReport>(0);
       
		
        for (Object[] object : list) {
			System.out.println("kjhhv "+Arrays.toString(object));
			SaleReport reports = new SaleReport();
			k++;
			reports.setSrNo(k);
			reports.setBillNo(Long.parseLong(object[0].toString()));
			reports.setBarcodeNo(Long.parseLong(object[1].toString()));
			reports.setItemName(object[2].toString());
			reports.setCategoryName(object[3].toString());
			reports.setSalePrice((double) Math.round(Double.parseDouble(object[4].toString())));
			//reports.setTotalAmt(Double.parseDouble(object[5].toString()));
			reports.setHsnSacNo(object[6].toString());
			//reports.setDiscount((double) Math.round(Double.parseDouble(object[6].toString())));
			reports.setQuantity(Long.parseLong(object[7].toString()));
			reports.setTaxAmount(Double.parseDouble(object[8].toString()));
			String gst = object[9].toString();
			String igst = object[10].toString();
			if(gst.equals("0.0")){
				reports.setGst(Double.parseDouble(igst));
			}
			if(igst.equals("0.0")){
				reports.setGst(Double.parseDouble(gst));
			}
			double quan = Double.parseDouble(object[7].toString());
			double saleP = Double.parseDouble(object[4].toString());
			double taxAmt = Double.parseDouble(object[8].toString());
			double saleTotal = quan * saleP;
			double totalAmt = saleTotal + taxAmt;
			reports.setTotalperItem((double) Math.round(saleTotal));
			reports.setTotalAmt((double) Math.round(totalAmt));
			catList.add(reports); 
	
		}}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return catList;
}

// Category Wise Sale Report
public List<SaleReport> categorySaleWise(String catName) {
	// TODO Auto-generated method stub
	HibernateUtility hbu=null;
	Session session=null;
	List<SaleReport> catList=null;
	try
	{
	 hbu = HibernateUtility.getInstance();
	 session = hbu.getHibernateSession();
		
	 Long k = 0l;
	 Query query2 = session.createSQLQuery("select s.BillNo, s.BarcodeNo, s.ItemName, s.CategoryName, s.SalePrice, s.totalperitem, s.HsnSacNo, s.Quantity, s.TaxAmount, s.Gst, s.Igst from customerbill s where s.CategoryName =:catName UNION select o.BillNo, o.BarcodeNo, o.ItemName, o.CategoryName, o.SalePrice, o.totalperitem, o.HsnSacNo, o.Quantity, o.TaxAmount, o.Gst, o.Igst from otherbill o where o.CategoryName =:catName UNION select c.BillNo, c.BarcodeNo, c.ItemName, c.CategoryName, c.SalePrice, c.totalperitem, c.HsnSacNo, c.Quantity, c.TaxAmount, c.Gst, c.Igst from creditcustomerbill c where c.CategoryName =:catName");
	 query2.setParameter("catName", catName);
	
	 
        List<Object[]> list = query2.list();
        catList= new ArrayList<SaleReport>(0);
       
		
		for (Object[] object : list) {
			System.out.println("kjhhv "+Arrays.toString(object));
			SaleReport reports = new SaleReport();
			k++;
			reports.setSrNo(k);
			reports.setBillNo(Long.parseLong(object[0].toString()));
			reports.setBarcodeNo(Long.parseLong(object[1].toString()));
			reports.setItemName(object[2].toString());
			reports.setCategoryName(object[3].toString());
			reports.setSalePrice((double) Math.round(Double.parseDouble(object[4].toString())));
			//reports.setTotalAmt(Double.parseDouble(object[5].toString()));
			//double Disc =Double.parseDouble(object[6].toString());
			
			//reports.setDiscount((double) Math.round(Double.parseDouble(object[6].toString())));
			reports.setHsnSacNo(object[6].toString());
			reports.setQuantity(Long.parseLong(object[7].toString()));
			reports.setTaxAmount(Double.parseDouble(object[8].toString()));
			String gst = object[9].toString();
			String igst = object[10].toString();
			if(gst.equals("0.0")){
				reports.setGst(Double.parseDouble(igst));
			}
			if(igst.equals("0.0")){
				reports.setGst(Double.parseDouble(gst));
			}
			double quan = Double.parseDouble(object[7].toString());
			double saleP = Double.parseDouble(object[4].toString());
			double taxAmt = Double.parseDouble(object[8].toString());
			double saleTotal = quan * saleP;
			double totalAmt = saleTotal + taxAmt;
			reports.setTotalperItem((double) Math.round(saleTotal));
			reports.setTotalAmt((double) Math.round(totalAmt));
			catList.add(reports); 
	
		}}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return catList;
}

// Single Date Vehicle Customer Sale Report
public List<SaleReport> vehicleSingleDate(Date adate) {
	// TODO Auto-generated method stub
	HibernateUtility hbu=null;
	Session session=null;
	List<SaleReport> catList=null;
	try
	{
	 hbu = HibernateUtility.getInstance();
	 session = hbu.getHibernateSession();
	 
	 Long k = 0l;
	// Query query2 = session.createQuery("select billNo, carNo, barcodeNo, itemName, categoryName, salePrice, ownerName, contactNo, totalAmt, discount from CustomerBill where current_date=:adate");
	 Query query2 = session.createSQLQuery("select s.BillNo, s.CarNo, s.BarcodeNo, s.ItemName, s.CategoryName, s.SalePrice, s.OwnerName, s.ContactNo, s.TotalAmount, s.HsnSacNo, s.Quantity, s.Gst, s.TaxAmount from customerbill s where s.Date =:adate");
	 query2.setParameter("adate", adate);
        List<Object[]> list = query2.list();
        catList= new ArrayList<SaleReport>(0);
		
		
		for (Object[] object : list) {
				
			SaleReport reports = new SaleReport();
			k++;
			reports.setSrNo(k);
			reports.setBillNo(Long.parseLong(object[0].toString()));
			reports.setCarNo(object[1].toString());
			reports.setBarcodeNo(Long.parseLong(object[2].toString()));
			reports.setItemName(object[3].toString());
			reports.setCategoryName(object[4].toString());
			reports.setSalePrice((double) Math.round(Double.parseDouble(object[5].toString())));
			reports.setOwnerName(object[6].toString());
			reports.setContactNo(Long.parseLong(object[7].toString()));
			//reports.setTotalAmt(Double.parseDouble(object[8].toString()));
			reports.setHsnSacNo(object[9].toString());
			//reports.setDiscount((double) Math.round(Double.parseDouble(object[9].toString())));
			reports.setQuantity(Long.parseLong(object[10].toString()));
			reports.setGst(Double.parseDouble(object[11].toString()));
			reports.setTaxAmount(Double.parseDouble(object[12].toString()));
			double quan = Double.parseDouble(object[10].toString());
			double saleP = Double.parseDouble(object[5].toString());
			double taxAmt = Double.parseDouble(object[12].toString());
			double saleTotal = quan * saleP;
			double totalAmt = saleTotal + taxAmt;
			reports.setTotalperItem((double) Math.round(saleTotal));
			reports.setTotalAmt((double) Math.round(totalAmt));
			catList.add(reports); 
	
		}}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return catList;
}

//get Two date Vehicle customer Sale
public List<SaleReport> vehicleTwoDate(Date adate, Date bdate) {
	// TODO Auto-generated method stub
	HibernateUtility hbu=null;
	Session session=null;
	List<SaleReport> catList=null;
	try
	{
	 hbu = HibernateUtility.getInstance();
	 session = hbu.getHibernateSession();
	 
	 Long k = 0l;
	// Query query2 = session.createQuery("select billNo, carNo, barcodeNo, itemName, categoryName, salePrice, ownerName, contactNo, totalAmt, discount from CustomerBill where current_date=:adate");
	 Query query2 = session.createSQLQuery("select s.BillNo, s.CarNo, s.BarcodeNo, s.ItemName, s.CategoryName, s.SalePrice, s.OwnerName, s.ContactNo, s.TotalAmount, s.HsnSacNo, s.Quantity, s.Gst, s.TaxAmount from customerbill s where s.Date BETWEEN :adate AND :bdate");
	 query2.setParameter("adate", adate);
	 query2.setParameter("bdate", bdate);
        List<Object[]> list = query2.list();
        catList= new ArrayList<SaleReport>(0);
		
		
        for (Object[] object : list) {
			
			SaleReport reports = new SaleReport();
			k++;
			reports.setSrNo(k);
			reports.setBillNo(Long.parseLong(object[0].toString()));
			reports.setCarNo(object[1].toString());
			reports.setBarcodeNo(Long.parseLong(object[2].toString()));
			reports.setItemName(object[3].toString());
			reports.setCategoryName(object[4].toString());
			reports.setSalePrice((double) Math.round(Double.parseDouble(object[5].toString())));
			reports.setOwnerName(object[6].toString());
			reports.setContactNo(Long.parseLong(object[7].toString()));
			//reports.setTotalAmt(Double.parseDouble(object[8].toString()));
			reports.setHsnSacNo(object[9].toString());
			//reports.setDiscount((double) Math.round(Double.parseDouble(object[9].toString())));
			reports.setQuantity(Long.parseLong(object[10].toString()));
			reports.setGst(Double.parseDouble(object[11].toString()));
			reports.setTaxAmount(Double.parseDouble(object[12].toString()));
			double quan = Double.parseDouble(object[10].toString());
			double saleP = Double.parseDouble(object[5].toString());
			double taxAmt = Double.parseDouble(object[12].toString());
			double saleTotal = quan * saleP;
			double totalAmt = saleTotal + taxAmt;
			reports.setTotalperItem((double) Math.round(saleTotal));
			reports.setTotalAmt((double) Math.round(totalAmt));
			catList.add(reports);  
	
		}}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return catList;
}

//get Category Wise Vehicle customer Sale
public List<SaleReport> categorySaleWiseCustomer(String catId) {
	// TODO Auto-generated method stub
	HibernateUtility hbu=null;
	Session session=null;
	List<SaleReport> catList=null;
	try
	{
	 hbu = HibernateUtility.getInstance();
	 session = hbu.getHibernateSession();
	 
	 Long k = 0l;
	// Query query2 = session.createQuery("select billNo, carNo, barcodeNo, itemName, categoryName, salePrice, ownerName, contactNo, totalAmt, discount from CustomerBill where current_date=:adate");
	 Query query2 = session.createSQLQuery("select s.BillNo, s.CarNo, s.BarcodeNo, s.ItemName, s.CategoryName, s.SalePrice, s.OwnerName, s.ContactNo, s.TotalAmount, s.HsnSacNo, s.Quantity, s.Gst, s.TaxAmount from customerbill s where s.CategoryName =:catId");
	 query2.setParameter("catId", catId);
	 
        List<Object[]> list = query2.list();
        catList= new ArrayList<SaleReport>(0);
		
		
        for (Object[] object : list) {
			
			SaleReport reports = new SaleReport();
			k++;
			reports.setSrNo(k);
			reports.setBillNo(Long.parseLong(object[0].toString()));
			reports.setCarNo(object[1].toString());
			reports.setBarcodeNo(Long.parseLong(object[2].toString()));
			reports.setItemName(object[3].toString());
			reports.setCategoryName(object[4].toString());
			reports.setSalePrice((double) Math.round(Double.parseDouble(object[5].toString())));
			reports.setOwnerName(object[6].toString());
			reports.setContactNo(Long.parseLong(object[7].toString()));
			//reports.setTotalAmt(Double.parseDouble(object[8].toString()));
			reports.setHsnSacNo(object[9].toString());
			//reports.setDiscount((double) Math.round(Double.parseDouble(object[9].toString())));
			reports.setQuantity(Long.parseLong(object[10].toString()));
			reports.setGst(Double.parseDouble(object[11].toString()));
			reports.setTaxAmount(Double.parseDouble(object[12].toString()));
			double quan = Double.parseDouble(object[10].toString());
			double saleP = Double.parseDouble(object[5].toString());
			double taxAmt = Double.parseDouble(object[12].toString());
			double saleTotal = quan * saleP;
			double totalAmt = saleTotal + taxAmt;
			reports.setTotalperItem((double) Math.round(saleTotal));
			reports.setTotalAmt((double) Math.round(totalAmt));
			catList.add(reports);  
	
		}}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return catList;
}

//get Category Wise Vehicle customer Sale
public List<SaleReport> billnowiseVehiclesell(String billNo) {
	// TODO Auto-generated method stub
	HibernateUtility hbu=null;
	Session session=null;
	List<SaleReport> catList=null;
	try
	{
	 hbu = HibernateUtility.getInstance();
	 session = hbu.getHibernateSession();
	 Long billno = Long.parseLong(billNo);
	 Long k = 0l;
	// Query query2 = session.createQuery("select billNo, carNo, barcodeNo, itemName, categoryName, salePrice, ownerName, contactNo, totalAmt, discount from CustomerBill where current_date=:adate");
	 Query query2 = session.createSQLQuery("select s.BillNo, s.CarNo, s.BarcodeNo, s.ItemName, s.CategoryName, s.SalePrice, s.OwnerName, s.ContactNo, s.TotalAmount, s.HsnSacNo, s.Quantity, s.Gst, s.TaxAmount from customerbill s where s.BillNo =:billno");
	 query2.setParameter("billno", billno);
	 
        List<Object[]> list = query2.list();
        catList= new ArrayList<SaleReport>(0);
		
		
        for (Object[] object : list) {
			
			SaleReport reports = new SaleReport();
			k++;
			reports.setSrNo(k);
			reports.setBillNo(Long.parseLong(object[0].toString()));
			reports.setCarNo(object[1].toString());
			reports.setBarcodeNo(Long.parseLong(object[2].toString()));
			reports.setItemName(object[3].toString());
			reports.setCategoryName(object[4].toString());
			reports.setSalePrice((double) Math.round(Double.parseDouble(object[5].toString())));
			reports.setOwnerName(object[6].toString());
			reports.setContactNo(Long.parseLong(object[7].toString()));
			//reports.setTotalAmt(Double.parseDouble(object[8].toString()));
			//reports.setDiscount((double) Math.round(Double.parseDouble(object[9].toString())));
			reports.setHsnSacNo(object[9].toString());
			reports.setQuantity(Long.parseLong(object[10].toString()));
			reports.setGst(Double.parseDouble(object[11].toString()));
			reports.setTaxAmount(Double.parseDouble(object[12].toString()));
			double quan = Double.parseDouble(object[10].toString());
			double saleP = Double.parseDouble(object[5].toString());
			double taxAmt = Double.parseDouble(object[12].toString());
			double saleTotal = quan * saleP;
			double totalAmt = saleTotal + taxAmt;
			reports.setTotalperItem((double) Math.round(saleTotal));
			reports.setTotalAmt((double) Math.round(totalAmt));
			catList.add(reports); 
	
		}}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return catList;
}

//get Barcode Wise Vehicle customer Sale
public List<SaleReport> barcodewiseVehicleSale(String barcodeVehicle) {
	// TODO Auto-generated method stub
	HibernateUtility hbu=null;
	Session session=null;
	List<SaleReport> catList=null;
	try
	{
	 hbu = HibernateUtility.getInstance();
	 session = hbu.getHibernateSession();
	 Long barno = Long.parseLong(barcodeVehicle);
	 Long k = 0l;
	// Query query2 = session.createQuery("select billNo, carNo, barcodeNo, itemName, categoryName, salePrice, ownerName, contactNo, totalAmt, discount from CustomerBill where current_date=:adate");
	 Query query2 = session.createSQLQuery("select s.BillNo, s.CarNo, s.BarcodeNo, s.ItemName, s.CategoryName, s.SalePrice, s.OwnerName, s.ContactNo, s.TotalAmount, s.HsnSacNo, s.Quantity, s.Gst, s.TaxAmount from customerbill s where s.BarcodeNo =:barno");
	 query2.setParameter("barno", barno);
	 
        List<Object[]> list = query2.list();
        catList= new ArrayList<SaleReport>(0);
		
		
        for (Object[] object : list) {
			
			SaleReport reports = new SaleReport();
			k++;
			reports.setSrNo(k);
			reports.setBillNo(Long.parseLong(object[0].toString()));
			reports.setCarNo(object[1].toString());
			reports.setBarcodeNo(Long.parseLong(object[2].toString()));
			reports.setItemName(object[3].toString());
			reports.setCategoryName(object[4].toString());
			reports.setSalePrice((double) Math.round(Double.parseDouble(object[5].toString())));
			reports.setOwnerName(object[6].toString());
			reports.setContactNo(Long.parseLong(object[7].toString()));
			//reports.setTotalAmt(Double.parseDouble(object[8].toString()));
			//reports.setDiscount((double) Math.round(Double.parseDouble(object[9].toString())));
			reports.setHsnSacNo(object[9].toString());
			reports.setQuantity(Long.parseLong(object[10].toString()));
			reports.setGst(Double.parseDouble(object[11].toString()));
			reports.setTaxAmount(Double.parseDouble(object[12].toString()));
			double quan = Double.parseDouble(object[10].toString());
			double saleP = Double.parseDouble(object[5].toString());
			double taxAmt = Double.parseDouble(object[12].toString());
			double saleTotal = quan * saleP;
			double totalAmt = saleTotal + taxAmt;
			reports.setTotalperItem((double) Math.round(saleTotal));
			reports.setTotalAmt((double) Math.round(totalAmt));
			catList.add(reports); 
	
		}}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return catList;
}

//CA Sale Report Two Date
	public List<SaleReport> caSaleReportBetweenTwoDates(Date adate, Date bdate) {
		// TODO Auto-generated method stub
		HibernateUtility hbu=null;
		Session session=null;
		List<SaleReport> catList=null;
		try
		{
		 hbu = HibernateUtility.getInstance();
		 session = hbu.getHibernateSession();
		 
		 Long k = 0l;
		// Query query2 = session.createQuery("select billNo, carNo, barcodeNo, itemName, categoryName, salePrice, ownerName, contactNo, totalAmt, discount from CustomerBill where current_date=:adate");
		 Query query2 = session.createSQLQuery("select CategoryName, ItemName, Quantity, Gst, Igst, HsnSacNo from creditcustomerbill where date BETWEEN :adate AND :bdate UNION SELECT CategoryName, ItemName, Quantity, Gst, Igst, HsnSacNo from customerbill where date BETWEEN :adate AND :bdate UNION SELECT CategoryName, ItemName, Quantity, Gst, Igst, HsnSacNo from otherbill where date BETWEEN :adate AND :bdate");
		 query2.setParameter("adate", adate);
		 query2.setParameter("bdate", bdate);
	        List<Object[]> list = query2.list();
	        catList= new ArrayList<SaleReport>(0);
			
			
			for (Object[] object : list) {
					
				SaleReport reports = new SaleReport();
				k++;
				reports.setSrNo(k);
				
				reports.setCategoryName(object[0].toString());
				reports.setItemName(object[1].toString());
				reports.setHsnSacNo(object[5].toString());
				reports.setQuantity(Long.parseLong(object[2].toString()));
				reports.setGst(Double.parseDouble(object[3].toString()));
				reports.setiGst(Double.parseDouble(object[4].toString()));
				
				catList.add(reports); 
		
			}}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return catList;
	}

	/*//TALLY Sale Report Two Date
	public List<SaleReport> tallySaleReportBetweenTwoDates(Date adate, Date bdate) {
		// TODO Auto-generated method stub
		HibernateUtility hbu=null;
		Session session=null;
		List<SaleReport> catList=null;
		
		try
		{
		 hbu = HibernateUtility.getInstance();
		 session = hbu.getHibernateSession();
		 
		 Query query3 = session.createSQLQuery("SELECT count(BillNo), BillNo from customerbill where date BETWEEN :adate AND :bdate group by BillNo");
		 query3.setParameter("adate", adate);
		 query3.setParameter("bdate", bdate);
		 List<Object[]> list1 = query3.list();
	        catList= new ArrayList<SaleReport>(0);
	        for (Object[] o : list1) {
	        	long billCount = Long.parseLong(o[0].toString());
	        	long billNo1 = Long.parseLong(o[1].toString());
	        	
	        	System.out.println("Bill No : "+billNo1 +"Count Of Bill No : " + billCount);
	        	
	        	Query query4 = session.createSQLQuery("SELECT GrossTotal, Date, Quantity, Gst, Igst, HsnSacNo, BillNo from customerbill where BillNo=:billNo1 group by BillNo");
	        	query4.setParameter("billNo1", billNo1);
    	        List<Object[]> list2 = query4.list();
    	      //  catList= new ArrayList<SaleReport>(0);
    			
    			for (Object[] object1 : list2) {
    					
    				SaleReport reports = new SaleReport();
    				
    				
    				reports.setGrossamt(Double.parseDouble(object1[0].toString()));
    				reports.setDate(object1[1].toString());
    				reports.setBillNo(Long.parseLong(object1[6].toString()));
    				reports.setCustName("customer"+billNo1);
    				reports.setDrCr("Dr");
    				reports.setVoucherType("Sales");
    				
    				catList.add(reports); 
    		
    			}
	        	
	        	Query query2 = session.createSQLQuery("SELECT SalePrice, ItemName, Quantity, Gst, Igst, Date, BillNo from customerbill where BillNo="+billNo1);
	    		
	    	        List<Object[]> list = query2.list();
	    	       // catList= new ArrayList<SaleReport>(0);
	    			
	    			for (Object[] object : list) {
	    					
	    				SaleReport reports = new SaleReport();
	    				
	    				reports.setSalePrice(Double.parseDouble(object[0].toString()));
	    				reports.setItemName(object[1].toString());
	    				long quant = Long.parseLong(object[2].toString());
	    				double salep = Double.parseDouble(object[0].toString());
	    				double amount = quant * salep;
	    				reports.setGrossamt(amount);
	    				reports.setQuantity(Long.parseLong(object[2].toString()));
	    				String gst = object[3].toString();
	    				String igst = object[4].toString();
	    				reports.setDate(object[5].toString());
	    				if(gst.equals("0.0")){
	    					reports.setCustName("Sales @"+igst+"%");
	    				}
	    				if(igst.equals("0.0")){
	    					reports.setCustName("Sales @"+gst+"%");
	    				}
	    				reports.setBillNo(Long.parseLong(object[6].toString()));
	    				reports.setVoucherType("Sales");
	    				reports.setDrCr("Cr");
	    				catList.add(reports); 
	    		
	    			}
	    			
	    			Query query5 = session.createSQLQuery("SELECT SalePrice, ItemName, Quantity, Gst, Igst, Date, BillNo, TaxAmount from customerbill where BillNo="+billNo1);
		    		
	    	        List<Object[]> list5 = query5.list();
	    	       // catList= new ArrayList<SaleReport>(0);
	    			
	    			for (Object[] object5 : list5) {
	    					
	    				SaleReport reports = new SaleReport();
	    				
	    			
	    				reports.setGrossamt(Double.parseDouble((object5[7].toString())));
	    				reports.setQuantity(Long.parseLong(object5[2].toString()));
	    				String gst = object5[3].toString();
	    				String igst = object5[4].toString();
	    				reports.setDate(object5[5].toString());
	    				if(gst.equals("0.0")){
	    					reports.setCustName("Vat "+igst+"%");
	    				}
	    				if(igst.equals("0.0")){
	    					reports.setCustName("Vat "+gst+"%");
	    				}
	    				reports.setBillNo(Long.parseLong(object5[6].toString()));
	    				reports.setVoucherType("Sales");
	    				reports.setDrCr("Cr");
	    				catList.add(reports); 
	    		
	    			}	
	        }
		 
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return catList;
		
	}*/
	
	
	//TALLY Sale Report Two Date
		public List<SaleReport> tallySaleReportBetweenTwoDates(Date adate, Date bdate) {
			// TODO Auto-generated method stub
			HibernateUtility hbu=null;
			Session session=null;
			List<SaleReport> catList=null;
			
			try
			{
			 hbu = HibernateUtility.getInstance();
			 session = hbu.getHibernateSession();
			 
			 Query query3 = session.createSQLQuery("SELECT count(BillNo), BillNo from customerbill where date BETWEEN :adate AND :bdate group by BillNo");
			 query3.setParameter("adate", adate);
			 query3.setParameter("bdate", bdate);
			 List<Object[]> list1 = query3.list();
		        catList= new ArrayList<SaleReport>(0);
		        for (Object[] o : list1) {
		        	long billCount = Long.parseLong(o[0].toString());
		        	long billNo1 = Long.parseLong(o[1].toString());
		        	
		        	System.out.println("Bill No : "+billNo1 +"Count Of Bill No : " + billCount);
		        	
		        	Query query4 = session.createSQLQuery("SELECT GrossTotal, Date, Quantity, Gst, Igst, HsnSacNo, BillNo, OwnerName, SalePrice, LaberCharges, TaxAmount, ItemName from customerbill where BillNo=:billNo1");
		        	query4.setParameter("billNo1", billNo1);
	    	        List<Object[]> list2 = query4.list();
	    	      //  catList= new ArrayList<SaleReport>(0);
	    			
	    			for (Object[] object5 : list2) {
	    				
	    			
	    				int n = list2.indexOf(object5);
						System.out.println("Index Of List :: "+ n);
						if(n == 0){	
	    				SaleReport reports = new SaleReport();
	    				
	    				
	    				reports.setBillNo(Long.parseLong(object5[6].toString()));	
						reports.setVchType("Sales");
						reports.setVchRef("Demo");
						reports.setDate(object5[1].toString());
						reports.setPartyType(object5[7].toString());
						
						String gst = object5[3].toString();
						String igst = object5[4].toString();
						if(gst.equals("0.0")){
							reports.setPurchaseLedger("Sales GST "+igst+"%");
							reports.setGstLedger("IGST");
						}
						if(igst.equals("0.0")){
							reports.setPurchaseLedger("Sales GST "+gst+"%");
							reports.setGstLedger("GST");
						}
						reports.setItemName(object5[11].toString());
						reports.setQuantity(Long.parseLong(object5[2].toString()));
						reports.setSalePrice(Double.parseDouble(object5[8].toString()));
						
						double quan = Double.parseDouble(object5[2].toString());
						double saleP = Double.parseDouble(object5[8].toString());
						double total = quan * saleP;
						reports.setTotal(total);
						reports.setAdditionalLedger("Laber Charges");
						reports.setTransportExpenses(Double.parseDouble(object5[9].toString()));
						reports.setTaxAmt(Double.parseDouble(object5[10].toString()));
						reports.setGrossAmt(Double.parseDouble(object5[0].toString()));
						reports.setNarration("narration");
	    				
	    				catList.add(reports); 
						}
	    			}
		        	
		        	Query query2 = session.createSQLQuery("SELECT GrossTotal, Date, Quantity, Gst, Igst, HsnSacNo, BillNo, OwnerName, SalePrice, LaberCharges, TaxAmount, ItemName from customerbill where BillNo=:billNo1");
		        	query2.setParameter("billNo1", billNo1);
		    	        List<Object[]> list = query2.list();
		    	       // catList= new ArrayList<SaleReport>(0);
		    			
		    			for (Object[] object : list) {
		    				
		    				int a = list.indexOf(object);
							System.out.println("Index Of List :: "+ a);
							
							
							if(a > 0){
		    				SaleReport reports = new SaleReport();
		    				
		    				reports.setBillNo(Long.parseLong(object[6].toString()));
							String gst = object[3].toString();
							String igst = object[4].toString();
							if(gst.equals("0.0")){
								reports.setPurchaseLedger("Sales GST "+igst+"%");
								reports.setGstLedger("IGST");
							}
							if(igst.equals("0.0")){
								reports.setPurchaseLedger("Sales GST "+gst+"%");
								reports.setGstLedger("GST");
							}
							reports.setItemName(object[11].toString());
							reports.setQuantity(Long.parseLong(object[2].toString()));
							reports.setSalePrice(Double.parseDouble(object[8].toString()));
							
							double quan = Double.parseDouble(object[2].toString());
							double saleP = Double.parseDouble(object[8].toString());
							double total = quan * saleP;
							reports.setTotal(total);
							reports.setTaxAmt(Double.parseDouble(object[10].toString()));
							
		    				catList.add(reports); 
							}
		    			}
		    			
		    			
		        }
			 
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			return catList;
			
		}
		
		// other bill
		public List<CustomerBean> getAllItemDetails(String productId) {
			// TODO Auto-generated method stub
			
			HibernateUtility hbu=null;
			Session session=null;
			List<CategoryWisePurchase> categoryBean=null;
			List<CustomerBean> itemlist=null;
			try
			{
				
				    System.out.println("shreemant");
					hbu = HibernateUtility.getInstance();
			 session = hbu.getHibernateSession();
			 //String sqlQuery = "SELECT ItemName , PkGoodRecId, CategoryName , BarcodeNo, hsnsacno, vat, igst FROM GoodReceive WHERE quantity > 0 AND ItemName ="+productId;

			 Query query=session.createSQLQuery("SELECT ItemName , PkGoodRecId, CategoryName , BarcodeNo, hsnsacno, vat, igst,salePrice FROM GoodReceive WHERE ItemName ='"+productId+"' AND quantity > 0");
				List<Object[]> list = query.list();

				 itemlist = new ArrayList<CustomerBean>(0);
			     for (Object[] objects : list) {
				 System.out.println(Arrays.toString(objects));
				 CustomerBean bean = new CustomerBean();
				  
				 bean.setItemName(objects[0].toString());
				 bean.setItem_id(Long.parseLong(objects[1].toString()));
				 bean.setCategoryName(objects[2].toString());
				 
				 bean.setBarcodeNo(Long.parseLong(objects[3].toString()));
				 bean.setHsnSacNo(objects[4].toString());
				 bean.setQuantity(0l);
				 //bean.setSalePrice(0d);
				 bean.setSalePrice(Double.parseDouble(objects[7].toString()));
				 bean.setDiscountGrid(0d);
				 bean.setDiscountAmt(0d);
				 bean.setVat(Double.parseDouble(objects[5].toString()));
				 bean.setIgst(Double.parseDouble(objects[6].toString()));
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
		
		
		//get Data On CustomerBill Using Barcode No amd in TempData Table		
		public List getAllItemDetailGrid(String productId,String carNo){
			
				HibernateUtility hbu=null;
				Session session=null;
				List<CategoryWisePurchase> categoryBean=null;
				List<CustomerBean> itemlist=null;
				System.out.println("key & carno = "+productId+" & "+carNo);
				try
				{
					
					    System.out.println("shreemant");
						hbu = HibernateUtility.getInstance();
				 session = hbu.getHibernateSession();
				 //String sqlQuery = "SELECT ItemName , PkGoodRecId, CategoryName , BarcodeNo, hsnsacno, vat, igst FROM GoodReceive WHERE  quantity > 0 AND BarcodeNo ="+key;

				 
				 Query query = session.createSQLQuery("SELECT ItemName , PkGoodRecId, CategoryName , BarcodeNo, hsnsacno, vat, igst,salePrice FROM GoodReceive WHERE ItemName ='"+productId+"' AND quantity > 0");
				 
					List<Object[]> list = query.list();
			
					/*if(list == null && list.size() == 0 )
					{
						System.exit(0);
					}*/
					
					 itemlist = new ArrayList<CustomerBean>(0);
				     for (Object[] objects : list) {
					 System.out.println(Arrays.toString(objects));
					 CustomerBean bean = new CustomerBean();
					  
					 bean.setItemName(objects[0].toString());
					 bean.setItem_id(Long.parseLong(objects[1].toString()));
					 bean.setCategoryName(objects[2].toString());
					 
					 bean.setBarcodeNo(Long.parseLong(objects[3].toString()));
					 bean.setHsnSacNo(objects[4].toString());
					 bean.setQuantity(0l);
					// bean.setSalePrice(0d);
					 bean.setSalePrice(Double.parseDouble(objects[7].toString()));
					 bean.setVat(Double.parseDouble(objects[5].toString()));
					 bean.setIgst(Double.parseDouble(objects[6].toString()));
					 bean.setTaxAmount(0d);
					 
					itemlist.add(bean);
					
					
					
					String itemName= (objects[0].toString());
					//Double salePrice= 0d;
					Double salePrice = Double.parseDouble(objects[7].toString());
				   /* Long cat_id= Long.parseLong(objects[3].toString());*/
					Long item_id= Long.parseLong(objects[1].toString());
					Long quantity= 0l;
					Long barcodeNo= Long.parseLong(objects[3].toString());
					String categoryName= (objects[2].toString());
					String hsnSacNo = (objects[4].toString());
					Double vat = Double.parseDouble(objects[5].toString());
					Double igst = Double.parseDouble(objects[6].toString());
					Double taxAmount = 0d;
					
					TempItemDetailDao data = new TempItemDetailDao();
					List stkList  = data.getAllItemEntry();
					
					if(stkList.size() == 0){
						
						com.smt.hibernate.TempItemDetail tid = new com.smt.hibernate.TempItemDetail();
						
						tid.setItemName(itemName);
						
						tid.setSalePrice(salePrice);

						//tid.setCat_id(cat_id);
						
						tid.setItem_id(item_id);
						
						tid.setQuantity(quantity);
						
						tid.setBarcodeNo(barcodeNo);
						tid.setVat(vat);
						tid.setIgst(igst);
						tid.setTaxAmount(taxAmount);
						tid.setHsnSacNo(hsnSacNo);
						
						tid.setCategoryName(categoryName);
						
						tid.setActiveYN('Y');
						
						tid.setCarNo(carNo);
						
						DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
						Date dateobj = new Date();
						System.out.println(df.format(dateobj));
						
						tid.setCurrent_date(dateobj);
						
						TempItemDetailDao dao = new TempItemDetailDao();
						dao.regTempData(tid);
						
						
					}
					else{
						for(int i=0;i<stkList.size();i++){
							
							TempItemDetail st = (TempItemDetail)stkList.get(i);
							Long barcodeNumber = st.getBarcodeNo();
							System.out.println(i+" == "+barcodeNumber);
							String carNumber = st.getCarNo();
							Long PkItemId = st.getPk_temp_id();
							
							if(barcodeNumber.equals(barcodeNo) && carNumber.equals(carNo)){
								
								Long qunty = st.getQuantity();
				        		
				       		    Long updatequnty =  qunty + quantity;
				       				 
				       		    HibernateUtility hbu1 = HibernateUtility.getInstance();
				    		    Session session1 = hbu1.getHibernateSession();
				    		    Transaction transaction = session1.beginTransaction();
				    		    
				    		    TempItemDetail updateStock = (TempItemDetail) session1.get(TempItemDetail.class, new Long(PkItemId));	 
				       		
				       		    updateStock.setQuantity(updatequnty);
				       		 
				       		    session1.saveOrUpdate(updateStock);
				       		    transaction.commit();
				       		    break;
				    		    
							}
							else
							{
				        		/*ItemName is Not Exists */
				        		if(i+1 == stkList.size()){
				        			
				        			TempItemDetail newEntry = new TempItemDetail();
				        			
				        			newEntry.setItemName(itemName);
									
									newEntry.setSalePrice(salePrice);

									//newEntry.setCat_id(cat_id);
									
									newEntry.setItem_id(item_id);
									
									newEntry.setQuantity(quantity);
									
									newEntry.setBarcodeNo(barcodeNo);
									
									newEntry.setVat(vat);
									newEntry.setIgst(igst);
									newEntry.setTaxAmount(taxAmount);
									newEntry.setHsnSacNo(hsnSacNo);
									
									newEntry.setCategoryName(categoryName);
									
									newEntry.setActiveYN('Y');
									
									newEntry.setCarNo(carNo);
									
									DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
									Date dateobj = new Date();
									System.out.println(df.format(dateobj));
									
									newEntry.setCurrent_date(dateobj);
				        			
				        			TempItemDetailDao dao1 = new TempItemDetailDao();
				        			dao1.regTempData(newEntry);
				        			
				        			
				        			
				        			
				        		}
				        	 }
							
							
						  }
						
						}
					
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



}


