package com.smt.dao;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jfree.util.Log;

import com.smt.bean.BarcodeReportBean;
import com.smt.bean.GoodreciveBillBean;
import com.smt.bean.PreviousGoodReceive;
import com.smt.bean.PurchaseReport;
import com.smt.bean.PurchaseReportBean;
import com.smt.bean.TallyPurchaseReport;
import com.smt.hibernate.GoodReceive;
import com.smt.utility.HibernateUtility;

public class GoodReciveDao {

	public void regGoodReceive(GoodReceive gd) {
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
	////////
	
	//getbill
	public List getAllbill()
	{
		HibernateUtility hbu=null;
		Session session=null;
		List list=null;
		try{
		 hbu = HibernateUtility.getInstance();
		 session = hbu.getHibernateSession();
		 Query query = session.createQuery("from GoodReceive");
		 list = query.list();
		 System.out.println("in getAllbills() list size - "+query.list().size());
		}
			catch(RuntimeException e){	
				Log.error("Error in getAllMainCategories()", e);
		}
			finally
			{
					if(session!=null){
					hbu.closeSession(session);
				}
			}
		
	return list;
	
	}
	
	
	public List getLastBarcodeNo(){
		
		
		HibernateUtility hbu=null;
		Session session=null;
		List<GoodreciveBillBean> saleList=null;
		try
		{
			hbu = HibernateUtility.getInstance();
		 session = hbu.getHibernateSession();
		 Query query = session.createSQLQuery("SELECT BillNo , BarcodeNo FROM goodreceive ORDER BY BarcodeNo DESC LIMIT 1");
			
			List<Object[]> list = query.list();
			 saleList= new ArrayList<GoodreciveBillBean>(0);
			for (Object[] object : list) {
				System.out.println(Arrays.toString(object));
				GoodreciveBillBean reports = new GoodreciveBillBean();
				reports.setBillNo(object[0].toString());
				reports.setBarcodeNo(Long.parseLong(object[1].toString()));
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



	public void updateQuantity(Long item_id,String quantity) {
		// TODO Auto-generated method stub
		
		HibernateUtility hbu=null;
		Session session=null;
		Transaction transaction =null;

		try
		{
			hbu = HibernateUtility.getInstance();
		    session = hbu.getHibernateSession();
		    transaction = session.beginTransaction();
		    
		    org.hibernate.Query query = session.createQuery("from GoodReceive where PkGoodRecId = :item_id ");
		    query.setParameter("item_id", item_id);
		    
		    GoodReceive uniqueResult = (GoodReceive) query.uniqueResult();
		    Long quant = uniqueResult.getQuantity();
		    
		    Long updatequnty = (long) (quant - Long.parseLong(quantity));
		    

		    GoodReceive updateStock = (GoodReceive) session.get(GoodReceive.class, new Long(item_id));	 
   		
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


    //BarcodeWise Stock
	public List<GoodReceive> getCategoryWiseStock(Long barcodeNo) {
		// TODO Auto-generated method stub
		HibernateUtility hbu=null;
		Session session=null;
		List<GoodReceive> catList=null;
		try
		{
			hbu = HibernateUtility.getInstance();
		 session = hbu.getHibernateSession();
		 Query query2 = session.createQuery("select itemName, catName, quantity, buyPrice, billNo, barcodeNo, vat, igst,total from GoodReceive where barcodeNo=:barcodeNo");
		 query2.setParameter("barcodeNo", barcodeNo);
	        List<Object[]> list = query2.list();
	        catList= new ArrayList<GoodReceive>(0);
			
			
			for (Object[] object : list) {
					
				GoodReceive reports = new GoodReceive();
				System.out.println("result - "+Arrays.toString(object));
				reports.setItemName(object[0].toString());
				reports.setCatName(object[1].toString());
				reports.setQuantity(Long.parseLong(object[2].toString()));
				reports.setBuyPrice(Double.parseDouble(object[3].toString()));
				reports.setBillNo(object[4].toString());
				reports.setBarcodeNo(Long.parseLong(object[5].toString()));
				String gst = object[6].toString();
				String igst = object[7].toString();
				if(gst.equals("0.0")){
					reports.setVat(Double.parseDouble(igst));
				}
				if(igst.equals("0.0")){
					reports.setVat(Double.parseDouble(gst));
				}
				reports.setTotal(Double.parseDouble(object[8].toString()));
				catList.add(reports); 
		
			}}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return catList;
	}

	
	 //SupplierWise stock between two days
		public List<GoodReceive> getSuppWiseStockBetTwoDate(Long suppId,String fDate, String eDate) {
			HibernateUtility hbu=null;
			Session session=null;
			List<GoodReceive> catList=null;
			try
			{
				hbu = HibernateUtility.getInstance();
			 session = hbu.getHibernateSession();
			// select barcodeNo, itemName, CategoryName, quantity, buyPrice, Vat, igst from GoodReceive  UNION ALL SELECT BarcodeNo,ItemName,

			 //CategoryName,TotalLitre,BuyPrice,vat,igst FROM goodreceivebarrel
			 //Query query2 = session.createQuery("select itemName, catName, quantity, buyPrice, salePrice, billNo, barcodeNo from GoodReceive where barcodeNo=:barcodeNo");
			 Query query2 = session.createSQLQuery("select barcodeNo, itemName, CategoryName, quantity, buyPrice, Vat, igst from GoodReceive where FksuppId ="+ suppId +" AND Date between '"+ fDate +"' and '"+ eDate +"';");
			 //query2.setParameter("barcodeNo", barcodeNo);
		        List<Object[]> list = query2.list();
		        catList= new ArrayList<GoodReceive>(0);
				
				
				for (Object[] object : list) {
						
					GoodReceive reports = new GoodReceive();
					
					reports.setBarcodeNo(Long.parseLong(object[0].toString()));
					reports.setItemName(object[1].toString());
					reports.setCatName(object[2].toString());
					reports.setQuantity(Long.parseLong(object[3].toString()));
					reports.setBuyPrice(Double.parseDouble(object[4].toString()));
					String gst = object[5].toString();
					String igst = object[6].toString();
					if(gst.equals("0.0")){
						reports.setVat(Double.parseDouble(igst));
					}
					if(igst.equals("0.0")){
						reports.setVat(Double.parseDouble(gst));
					}
					catList.add(reports); 
			
				}}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			return catList;
		}

//get purchase bill no
	public List getBillNo() {
		
		HibernateUtility hbu=null;
		Session session=null;
		List list=null;
		try{
		 hbu = HibernateUtility.getInstance();
		 session = hbu.getHibernateSession();
		 Query query = session.createQuery("from GoodReceive group by billNo");
		 list = query.list();
		 System.out.println("get bill no - "+query.list().size());
		}
			catch(RuntimeException e){	
				Log.error("Error in getAllMainCategories()", e);
		}
			finally
			{
					if(session!=null){
					hbu.closeSession(session);
				}
			}
		
	return list;
		
	}
//
public List getBarcode() {
		
		HibernateUtility hbu=null;
		Session session=null;
		List list=null;
		try{
		 hbu = HibernateUtility.getInstance();
		 session = hbu.getHibernateSession();
		 Query query = session.createQuery("from GoodReceive group by barcodeNo");
		 list = query.list();
		 System.out.println("get barcodeNo - "+query.list().size());
		}
			catch(RuntimeException e){	
				Log.error("Error in getAllMainCategories()", e);
		}
			finally
			{
					if(session!=null){
					hbu.closeSession(session);
				}
			}
		
	return list;
		
	}

    // bill no wise stock
	public List<GoodReceive> getBillNoWiseStock(String billno) {
		// TODO Auto-generated method stub
		HibernateUtility hbu=null;
		Session session=null;
		List<GoodReceive> catList=null;
		try
		{
			hbu = HibernateUtility.getInstance();
		 session = hbu.getHibernateSession();
//		 Query query2 = session.createQuery("select itemName, catName, quantity, buyPrice, salePrice, billNo, barcodeNo, vat, igst from GoodReceive where billNo=:billno");
		 Query query2 = session.createSQLQuery("select itemName, CategoryName, quantity, buyPrice,  billNo, barcodeNo, vat, igst from GoodReceive where billNo='"+billno+"'");
//		 query2.setParameter("billno", billno);
	        List<Object[]> list = query2.list();
	        catList= new ArrayList<GoodReceive>(0);
			
			
			for (Object[] object : list) {
					
				GoodReceive reports = new GoodReceive();
				System.out.println("result - "+Arrays.toString(object));
				reports.setItemName(object[0].toString());
				reports.setCatName(object[1].toString());
				reports.setQuantity(Long.parseLong(object[2].toString()));
				reports.setBuyPrice(Double.parseDouble(object[3].toString()));
	//			reports.setSalePrice(Double.parseDouble(object[4].toString()));
				reports.setBillNo(object[4].toString());
				reports.setBarcodeNo(Long.parseLong(object[5].toString()));
				String gst = object[6].toString();
				String igst = object[7].toString();
				if(gst.equals("0.0")){
					reports.setVat(Double.parseDouble(igst));
				}
				if(igst.equals("0.0")){
					reports.setVat(Double.parseDouble(gst));
				}
				
				catList.add(reports); 
		
			}}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return catList;
	}



	public List<PurchaseReportBean> singleDatePurchase(Date adate) {
		// TODO Auto-generated method stub
		HibernateUtility hbu=null;
		Session session=null;
		List<PurchaseReportBean> catList=null;
		try
		{
			Long k = 0l;
			hbu = HibernateUtility.getInstance();
		 session = hbu.getHibernateSession();
		 Query query2 = session.createSQLQuery("select g.Date, s.supplier_name, g.BillNo, s.pan_no, g.ItemName, g.BuyPrice, g.OrignalQuantity, g.vat, g.igst, g.HsnSacNo from goodreceive g left join supplier_details s on g.FksuppId = s.supplier_id  where date=:adate");
		 query2.setParameter("adate", adate);
	        List<Object[]> list = query2.list();
	        catList= new ArrayList<PurchaseReportBean>(0);
			
			
			for (Object[] object : list) {
			k++;		
			PurchaseReportBean reports = new PurchaseReportBean();
				reports.setSerialnumber(k);
				reports.setFetchDate(object[0].toString());
				reports.setSupplierName(object[1].toString());
				reports.setBillNo(object[2].toString());
				reports.setGstTinNo(object[3].toString());
				reports.setItemName(object[4].toString());
				reports.setBuyPrice(Double.parseDouble(object[5].toString()));
				reports.setQuantity(Long.parseLong(object[6].toString()));
				reports.setHsnsacno(object[9].toString());
				
				
				Double qunti = Double.parseDouble(object[6].toString());
				Double byPrice = Double.parseDouble(object[5].toString());
				Double total = byPrice * qunti;
				reports.setTotal(total);
				String gsttaxAmt = object[7].toString();
				String igsttaxAmt = object[8].toString();
				System.out.println("gsttaxAmt"+gsttaxAmt);
				System.out.println("igsttaxAmt"+igsttaxAmt);
				
				 DecimalFormat df = new DecimalFormat("0.00");
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
					reports.setTotalTaxAmount((double) Math.round(totalTaxAmount)); 
					reports.setNetAmount((double) Math.round(totalAmount));
					System.out.println("5 % GST Amount"+taxAmt);
				
				}
				
				catList.add(reports); 
		
			}}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return catList;
		
	}

	

	public List<PurchaseReportBean> purchaseReportBetweenTwoDates(Date adate,
			Date bdate) {
		
		HibernateUtility hbu=null;
		Session session=null;
		List<PurchaseReportBean> catList=null;
		try
		{   Long k = 0l;
			hbu = HibernateUtility.getInstance();
		 session = hbu.getHibernateSession();
		 Query query2 = session.createSQLQuery("select g.Date, s.supplier_name, g.BillNo, s.pan_no, g.ItemName, g.BuyPrice, g.OrignalQuantity, g.vat, g.igst, g.HsnSacNo, g.FinalExpense from goodreceive g left join supplier_details s on g.FksuppId = s.supplier_id where date BETWEEN :adate AND :bdate");
		 query2.setParameter("adate", adate);
		 query2.setParameter("bdate", bdate);
		 List<Object[]> list = query2.list();
	        catList= new ArrayList<PurchaseReportBean>(0);
			
			
			for (Object[] object : list) {
			k++;		
			PurchaseReportBean reports = new PurchaseReportBean();
				reports.setSerialnumber(k);
				reports.setFetchDate(object[0].toString());
				reports.setSupplierName(object[1].toString());
				reports.setBillNo(object[2].toString());
				reports.setGstTinNo(object[3].toString());
				reports.setItemName(object[4].toString());
				reports.setBuyPrice(Double.parseDouble(object[5].toString()));
				reports.setQuantity(Long.parseLong(object[6].toString()));
				reports.setHsnsacno(object[9].toString());
				reports.setAdditionalCost(Double.parseDouble(object[10].toString()));
				
				
				Double qunti = Double.parseDouble(object[6].toString());
				Double byPrice = Double.parseDouble(object[5].toString());
				Double total = byPrice * qunti;
				reports.setTotal(total);
				String gsttaxAmt = object[7].toString();
				String igsttaxAmt = object[8].toString();
				System.out.println("gsttaxAmt"+gsttaxAmt);
				System.out.println("igsttaxAmt"+igsttaxAmt);
				
				if(gsttaxAmt.equals("5.0")){
					Double tx = Double.parseDouble(gsttaxAmt);
					Double taxAmt = (tx/100)*(byPrice);
					Double totalTaxAmount = qunti * taxAmt;
					Double finalTaxAmt = totalTaxAmount/2;
					Double newSalePrice = byPrice + taxAmt;
					Double totalAmount = qunti * newSalePrice;
					reports.setFivePercentageGST((double) Math.round(finalTaxAmt));
					reports.setFivePercentageSGST((double) Math.round(finalTaxAmt));
				/*	reports.setTwelwePercentageGST(0.0);
					reports.setEighteenPercentageGST(0.0);
					reports.setTwentyEightPercentageGST(0.0);*/
					reports.setTotalTaxAmount((double) Math.round(totalTaxAmount)); 
					reports.setNetAmount((double) Math.round(totalAmount));
					System.out.println("5 % GST Amount"+taxAmt);
				}
				else if(gsttaxAmt.equals("12.0")){
					Double tx = Double.parseDouble(gsttaxAmt);
					Double taxAmt = (tx/100)*(byPrice);
					Double totalTaxAmount = qunti * taxAmt;
					Double finalTaxAmt = totalTaxAmount/2;
					Double newSalePrice = byPrice + taxAmt;
					Double totalAmount = qunti * newSalePrice;
					/*reports.setFivePercentageGST(0.0);*/
					reports.setTwelwePercentageGST((double) Math.round(finalTaxAmt));
					reports.setTwelwePercentageSGST((double) Math.round(finalTaxAmt));
					/*reports.setEighteenPercentageGST(0.0);
					reports.setTwentyEightPercentageGST(0.0);*/
					reports.setTotalTaxAmount((double) Math.round(totalTaxAmount)); 
					reports.setNetAmount((double) Math.round(totalAmount));
					System.out.println("5 % GST Amount"+taxAmt);
				
				}else if(gsttaxAmt.equals("18.0")){
					Double tx = Double.parseDouble(gsttaxAmt);
					Double taxAmt = (tx/100)*(byPrice);
					Double totalTaxAmount = qunti * taxAmt;
					Double finalTaxAmt = totalTaxAmount/2;
					Double newSalePrice = byPrice + taxAmt;
					Double totalAmount = qunti * newSalePrice;
					/*reports.setFivePercentageGST(0.0);
					reports.setTwelwePercentageGST(0.0);*/
					reports.setEighteenPercentageGST((double) Math.round(finalTaxAmt));
					reports.setEighteenPercentageSGST((double) Math.round(finalTaxAmt));
					/*reports.setTwentyEightPercentageGST(0.0);*/
					reports.setTotalTaxAmount((double) Math.round(totalTaxAmount)); 
					reports.setNetAmount((double) Math.round(totalAmount));
					System.out.println("5 % GST Amount"+taxAmt);
				
				}
				else if(gsttaxAmt.equals("28.0")){
					Double tx = Double.parseDouble(gsttaxAmt);
					Double taxAmt = (tx/100)*(byPrice);
					Double totalTaxAmount = qunti * taxAmt;
					Double finalTaxAmt = totalTaxAmount/2;
					Double newSalePrice = byPrice + taxAmt;
					Double totalAmount = qunti * newSalePrice;
				/*	reports.setFivePercentageGST(0.0);
					reports.setTwelwePercentageGST(0.0);
					reports.setEighteenPercentageGST(0.0);*/
					reports.setTwentyEightPercentageGST((double) Math.round(finalTaxAmt));
					reports.setTwentyEightPercentageSGST((double) Math.round(finalTaxAmt));
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
					/*reports.setiGSTTwelwePercentage(0.0);
					reports.setiGSTEighteenPercentage(0.0);
					reports.setiGSTTwentyeightPercentage(0.0);*/
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
					/*reports.setiGSTFivePercentage(0.0);*/
					reports.setiGSTTwelwePercentage((double) Math.round(taxAmt));
					/*reports.setiGSTEighteenPercentage(0.0);
					reports.setiGSTTwentyeightPercentage(0.0);*/
					reports.setTotalTaxAmount((double) Math.round(totalTaxAmount)); 
					reports.setNetAmount((double) Math.round(totalAmount));
					System.out.println("5 % GST Amount"+taxAmt);
				
				}else if(igsttaxAmt.equals("18.0")){
					Double tx = Double.parseDouble(igsttaxAmt);
					Double taxAmt = (tx/100)*(byPrice);
					Double totalTaxAmount = qunti * taxAmt;
					Double newSalePrice = byPrice + taxAmt;
					Double totalAmount = qunti * newSalePrice;
					/*reports.setiGSTFivePercentage(0.0);
					reports.setiGSTTwelwePercentage(0.0);*/
					reports.setiGSTEighteenPercentage((double) Math.round(taxAmt));
					/*reports.setiGSTTwentyeightPercentage(0.0);*/
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
					/*reports.setiGSTFivePercentage(0.0);
					reports.setiGSTTwelwePercentage(0.0);
					reports.setiGSTEighteenPercentage(0.0);*/
					reports.setiGSTTwentyeightPercentage((double) Math.round(taxAmt));
					reports.setTotalTaxAmount((double) Math.round(totalTaxAmount)); 
					reports.setNetAmount((double) Math.round(totalAmount));
					System.out.println("5 % GST Amount"+taxAmt);
				
				}
				
				catList.add(reports); 
		
			}}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return catList;
		}


	
	 //  get Previous Good Receive
	public List<PreviousGoodReceive> getPreGoodReceive(String billNo) {
		// TODO Auto-generated method stub
		HibernateUtility hbu=null;
		Session session=null;
		List<PreviousGoodReceive> catList=null;
		try
		{
			hbu = HibernateUtility.getInstance();
		    session = hbu.getHibernateSession();
	//	    Query query2 = session.createSQLQuery("select s.supplier_name, g.BillNo, g.CategoryName, g.ItemName, g.BarcodeNo, g.ContactPerson, g.Quantity, g.BuyPrice, g.Vat, g.Total, g.Expences, g.Date from goodreceive g left join supplier_details s on g.FksuppId = s.supplier_id where g.BillNo =:billNo");
		    Query query2 = session.createSQLQuery("select g.BillNo, s.supplier_name,  g.CategoryName, g.ItemName, g.BarcodeNo, g.ContactPerson, g.Quantity, g.BuyPrice, g.Vat,g.igst,  g.Expences, g.Total,g.Date from goodreceive g left join supplier_details s on g.FksuppId = s.supplier_id where g.BillNo =:billNo");
		    query2.setParameter("billNo", billNo);
	        List<Object[]> list = query2.list();
	        catList= new ArrayList<PreviousGoodReceive>(0);
			
			
			for (Object[] object : list) {
					
				PreviousGoodReceive reports = new PreviousGoodReceive();
				reports.setBillNo(object[0].toString());
				reports.setSuppName(object[1].toString());
				reports.setCatName(object[2].toString());
				reports.setItemName(object[3].toString());
				reports.setBarcodeNo(Long.parseLong(object[4].toString()));
				reports.setContactPerson(object[5].toString());
				reports.setQuantity(Long.parseLong(object[6].toString()));
				reports.setBuyPrice(Double.parseDouble(object[7].toString()));
				reports.setVat(Double.parseDouble(object[8].toString()));
				reports.setIgst(Double.parseDouble(object[9].toString()));
				reports.setExpence(Double.parseDouble(object[10].toString()));
				reports.setTotal(Double.parseDouble(object[11].toString()));
				reports.setOndate(object[12].toString());
				//reports.setDate(object[3].toString());
				
				catList.add(reports); 
		
			}}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return catList;
	}


// get all main barcode no
	public List getAllMainBarcodeNo() {
		// TODO Auto-generated method stub
		HibernateUtility hbu=null;
		Session session=null;
		List list=null;
		try{
		 hbu = HibernateUtility.getInstance();
		 session = hbu.getHibernateSession();
		 Query query = session.createQuery("from GoodReceive");
		 list = query.list();
		}
			catch(RuntimeException e){	
				Log.error("Error in getAllMainBarcodeNo()", e);
		}
			finally
			{
					if(session!=null){
					hbu.closeSession(session);
				}
			}
		
	return list;
	}
	
	//Barcode wise report
	public List<BarcodeReportBean> BarcodeWiseReport(Long barcodeId) {
		// TODO Auto-generated method stub
		HibernateUtility hbu=null;
		Session session=null;
		List<BarcodeReportBean> catList=null;
		try
		{
			hbu = HibernateUtility.getInstance();
		 session = hbu.getHibernateSession();
		 //Query query2 = session.createQuery("select billNo, carNo, barcodeNo, itemName, categoryName, salePrice, ownerName, contactNo, totalAmt, discount from CustomerBill where CategoryName=:categoryName");
		 Query query2 = session.createSQLQuery("select Date,ItemName,CategoryName,HsnSacNo,Quantity,BuyPrice,SalePrice,Vat,igst,Total from goodreceive where BarcodeNo =:barcodeId");
		 query2.setParameter("barcodeId", barcodeId);
	        List<Object[]> list = query2.list();
	        catList= new ArrayList<BarcodeReportBean>(0);
			
			
			for (Object[] object : list) {
					
				BarcodeReportBean reports = new BarcodeReportBean();
				reports.setDate(object[0].toString());
				reports.setItemName(object[1].toString());
				reports.setCategoryName(object[2].toString());
				reports.setHsnsacNo(object[3].toString());
				reports.setQuantity(Double.parseDouble(object[4].toString()));
				reports.setBuyPrice(Double.parseDouble(object[5].toString()));
				reports.setSalePrice(Double.parseDouble(object[6].toString()));
				reports.setVat(Double.parseDouble(object[7].toString()));
				reports.setIgst(Double.parseDouble(object[8].toString()));
				reports.setTotal(Double.parseDouble(object[9].toString()));
				
				catList.add(reports); 
		
			}}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return catList;
	}


// Deactive the PaymentMode
	public void DeActivePaymentDone(String billNo, String supplier) {
		// TODO Auto-generated method stub
		
		com.smt.utility.HibernateUtility hbu=null;
		Session session=null;
		Transaction transaction = null;
		
		try{
		 hbu = HibernateUtility.getInstance();
		 session = hbu.getHibernateSession();
		 Long supId = Long.parseLong(supplier);
		 Query query = session.createQuery("select s.PkGoodRecId,s.paymentDone from GoodReceive s where  s.billNo =:billNo AND s.supplierName =:supId");
			query.setParameter("billNo",billNo);
			query.setParameter("supId",supId);
			List<Object[]> list = query.list();
			
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Object[] objects = (Object[]) iterator.next();
				transaction = session.beginTransaction();
				Long PkGoodRecId = Long.parseLong(objects[0].toString());
					//BigDecimal bigDecimal = new BigDecimal(newBal);
				System.out.println("deactivation done of "+PkGoodRecId );
				GoodReceive upPayment = (GoodReceive) session.get(GoodReceive.class,
						new Long(PkGoodRecId));
				upPayment.setPaymentDone("n");
				

				session.saveOrUpdate(upPayment);
				transaction.commit();   
				
			}
			
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


	//Supplier Wise Purchase Report 
	public List<PurchaseReport> supplierAllPurchase(long supplier) {
		// TODO Auto-generated method stub
		HibernateUtility hbu=null;
		Session session=null;
		List<PurchaseReport> catList=null;
		try
		{
		 hbu = HibernateUtility.getInstance();
		 session = hbu.getHibernateSession();
		 
		 Long k = 0l;
		// Query query2 = session.createQuery("select billNo, carNo, barcodeNo, itemName, categoryName, salePrice, ownerName, contactNo, totalAmt, discount from CustomerBill where current_date=:adate");
		 Query query2 = session.createSQLQuery("select s.BillNo, s.ContactPerson, s.CategoryName, s.ItemName, s.HsnSacNo, s.OrignalQuantity, s.BuyPrice, s.Vat, s.igst, s.TaxAmount,s.Total, s.ExtraDiscount, s.Expences, s.ExpenseTax, s.FinalExpense, s.GrossTotal, s.Date, c.supplier_name from goodreceive s left join supplier_details c ON s.FksuppId=c.supplier_id where s.FksuppId =:supplier");
		 query2.setParameter("supplier", supplier);
		
	        List<Object[]> list = query2.list();
	        catList= new ArrayList<PurchaseReport>(0);
			
			
			for (Object[] object : list) {
					
				PurchaseReport reports = new PurchaseReport();
				k++;
				reports.setSrno(k);
				reports.setBillNo(object[0].toString());
				reports.setContactPerson(object[1].toString());
				reports.setCatName(object[2].toString());
				reports.setItemName(object[3].toString());
				reports.setHsnsacno(object[4].toString());
				reports.setQuantity(Long.parseLong(object[5].toString()));
				reports.setBuyPrice(Double.parseDouble(object[6].toString()));
				reports.setVat(Double.parseDouble(object[7].toString()));
				reports.setIgst(Double.parseDouble(object[8].toString()));
				reports.setTaxAmount(Double.parseDouble(object[9].toString()));
				//reports.setSalePrice(Double.parseDouble(object[10].toString()));
				reports.setTotal((double) Math.round(Double.parseDouble(object[10].toString())));
				reports.setExtraDiscount(Double.parseDouble(object[11].toString()));
				reports.setExpence(Double.parseDouble(object[12].toString()));
				reports.setTxPerexpence(Double.parseDouble(object[13].toString()));
				reports.setFinalExpenses(Double.parseDouble(object[14].toString()));
				reports.setGrossTotal((double) Math.round(Double.parseDouble(object[15].toString())));
				reports.setDate(object[16].toString());
				reports.setSupplierName(object[17].toString());
				
				catList.add(reports); 
		
			}}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return catList;
	}


//Purchase Report Supplier Bill No Wise
	public List<PurchaseReport> supplierBillWisePurchaseReport(long supplier, String billNo) {
		// TODO Auto-generated method stub
		HibernateUtility hbu=null;
		Session session=null;
		List<PurchaseReport> catList=null;
		try
		{
		 hbu = HibernateUtility.getInstance();
		 session = hbu.getHibernateSession();
		 
		 Long k = 0l;
		// Query query2 = session.createQuery("select billNo, carNo, barcodeNo, itemName, categoryName, salePrice, ownerName, contactNo, totalAmt, discount from CustomerBill where current_date=:adate");
		 Query query2 = session.createSQLQuery("select s.BillNo, s.ContactPerson, s.CategoryName, s.ItemName, s.HsnSacNo, s.OrignalQuantity, s.BuyPrice, s.Vat, s.igst, s.TaxAmount,s.Total, s.ExtraDiscount, s.Expences, s.ExpenseTax, s.FinalExpense, s.GrossTotal, s.Date, c.supplier_name from goodreceive s left join supplier_details c ON s.FksuppId=c.supplier_id where s.FksuppId =:supplier AND s.BillNo =:billNo");
		 query2.setParameter("supplier", supplier);
		 query2.setParameter("billNo", billNo);
		
	        List<Object[]> list = query2.list();
	        catList= new ArrayList<PurchaseReport>(0);
			
			
			for (Object[] object : list) {
					
				PurchaseReport reports = new PurchaseReport();
				k++;
				reports.setSrno(k);
				reports.setBillNo(object[0].toString());
				reports.setContactPerson(object[1].toString());
				reports.setCatName(object[2].toString());
				reports.setItemName(object[3].toString());
				reports.setHsnsacno(object[4].toString());
				reports.setQuantity(Long.parseLong(object[5].toString()));
				reports.setBuyPrice(Double.parseDouble(object[6].toString()));
				reports.setVat(Double.parseDouble(object[7].toString()));
				reports.setIgst(Double.parseDouble(object[8].toString()));
				reports.setTaxAmount(Double.parseDouble(object[9].toString()));
				//reports.setSalePrice(Double.parseDouble(object[10].toString()));
				reports.setTotal((double) Math.round(Double.parseDouble(object[10].toString())));
				reports.setExtraDiscount(Double.parseDouble(object[11].toString()));
				reports.setExpence(Double.parseDouble(object[12].toString()));
				reports.setTxPerexpence(Double.parseDouble(object[13].toString()));
				reports.setFinalExpenses(Double.parseDouble(object[14].toString()));
				reports.setGrossTotal((double) Math.round(Double.parseDouble(object[15].toString())));
				reports.setDate(object[16].toString());
				reports.setSupplierName(object[17].toString());
				
				catList.add(reports); 
		
			}}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return catList;
	}


	//Purchase Report Category Wise
	public List<PurchaseReport> categoryWisePurchaseReport(String catName) {
		// TODO Auto-generated method stub
		HibernateUtility hbu=null;
		Session session=null;
		List<PurchaseReport> catList=null;
		try
		{
		 hbu = HibernateUtility.getInstance();
		 session = hbu.getHibernateSession();
		 
		 Long k = 0l;
		// Query query2 = session.createQuery("select billNo, carNo, barcodeNo, itemName, categoryName, salePrice, ownerName, contactNo, totalAmt, discount from CustomerBill where current_date=:adate");
		 Query query2 = session.createSQLQuery("select s.BillNo, s.CategoryName, s.ItemName, s.HsnSacNo, s.OrignalQuantity, s.BuyPrice, s.Vat, s.igst, s.TaxAmount, s.Total, s.BarcodeNo, s.Date, c.supplier_name from goodreceive s left join supplier_details c ON s.FksuppId=c.supplier_id where s.CategoryName =:catName");
		 query2.setParameter("catName", catName);
		
	        List<Object[]> list = query2.list();
	        catList= new ArrayList<PurchaseReport>(0);
			
			
			for (Object[] object : list) {
					
				PurchaseReport reports = new PurchaseReport();
				k++;
				reports.setSrno(k);
				reports.setBillNo(object[0].toString());
				reports.setCatName(object[1].toString());
				reports.setItemName(object[2].toString());
				reports.setHsnsacno(object[3].toString());
				reports.setQuantity(Long.parseLong(object[4].toString()));
				reports.setBuyPrice(Double.parseDouble(object[5].toString()));
				reports.setVat(Double.parseDouble(object[6].toString()));
				reports.setIgst(Double.parseDouble(object[7].toString()));
				reports.setTaxAmount(Double.parseDouble(object[8].toString()));
				reports.setTotal((double) Math.round(Double.parseDouble(object[9].toString())));
				reports.setBarcodeNo(Long.parseLong(object[10].toString()));
				reports.setDate(object[11].toString());
				reports.setSupplierName(object[12].toString());
				
				catList.add(reports); 
		
			}}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return catList;
	}


	//Purchase Report Barcode No Wise
	public List<PurchaseReport> barcodeWisePurchaseReport(String barcodeNoOurchase) {
		// TODO Auto-generated method stub
		HibernateUtility hbu=null;
		Session session=null;
		List<PurchaseReport> catList=null;
		try
		{
		 hbu = HibernateUtility.getInstance();
		 session = hbu.getHibernateSession();
		 
		 Long k = 0l;
		// Query query2 = session.createQuery("select billNo, carNo, barcodeNo, itemName, categoryName, salePrice, ownerName, contactNo, totalAmt, discount from CustomerBill where current_date=:adate");
		 Query query2 = session.createSQLQuery("select s.BillNo, s.CategoryName, s.ItemName, s.HsnSacNo, s.OrignalQuantity, s.BuyPrice, s.Vat, s.igst, s.TaxAmount, s.Total, s.BarcodeNo, s.Date, c.supplier_name from goodreceive s left join supplier_details c ON s.FksuppId=c.supplier_id where s.BarcodeNo =:barcodeNoOurchase");
		 query2.setParameter("barcodeNoOurchase", barcodeNoOurchase);
		
	        List<Object[]> list = query2.list();
	        catList= new ArrayList<PurchaseReport>(0);
			
			
			for (Object[] object : list) {
					
				PurchaseReport reports = new PurchaseReport();
				k++;
				reports.setSrno(k);
				reports.setBillNo(object[0].toString());
				reports.setCatName(object[1].toString());
				reports.setItemName(object[2].toString());
				reports.setHsnsacno(object[3].toString());
				reports.setQuantity(Long.parseLong(object[4].toString()));
				reports.setBuyPrice(Double.parseDouble(object[5].toString()));
				reports.setVat(Double.parseDouble(object[6].toString()));
				reports.setIgst(Double.parseDouble(object[7].toString()));
				reports.setTaxAmount(Double.parseDouble(object[8].toString()));
				reports.setTotal((double) Math.round(Double.parseDouble(object[9].toString())));
				reports.setBarcodeNo(Long.parseLong(object[10].toString()));
				reports.setDate(object[11].toString());
				reports.setSupplierName(object[12].toString());
				
				catList.add(reports); 
		
			}}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return catList;
	}

	//Purchase Report Single Date

	public List<PurchaseReport> singleDatePurchase45(Date adate) {
		// TODO Auto-generated method stub
		HibernateUtility hbu=null;
		Session session=null;
		List<PurchaseReport> catList=null;
		try
		{
		 hbu = HibernateUtility.getInstance();
		 session = hbu.getHibernateSession();
		 
		 Long k = 0l;
		// Query query2 = session.createQuery("select billNo, carNo, barcodeNo, itemName, categoryName, salePrice, ownerName, contactNo, totalAmt, discount from CustomerBill where current_date=:adate");
		 Query query2 = session.createSQLQuery("select s.BillNo, s.ContactPerson, s.CategoryName, s.ItemName, s.HsnSacNo, s.OrignalQuantity, s.BuyPrice, s.Vat, s.igst, s.TaxAmount,s.Total, s.ExtraDiscount, s.Expences, s.ExpenseTax, s.FinalExpense, s.GrossTotal, s.Date, c.supplier_name from goodreceive s left join supplier_details c ON s.FksuppId=c.supplier_id where s.Date =:adate");
		 query2.setParameter("adate", adate);
		
	        List<Object[]> list = query2.list();
	        catList= new ArrayList<PurchaseReport>(0);
			
			
			for (Object[] object : list) {
					
				PurchaseReport reports = new PurchaseReport();
				k++;
				reports.setSrno(k);
				reports.setBillNo(object[0].toString());
				reports.setContactPerson(object[1].toString());
				reports.setCatName(object[2].toString());
				reports.setItemName(object[3].toString());
				reports.setHsnsacno(object[4].toString());
				reports.setQuantity(Long.parseLong(object[5].toString()));
				reports.setBuyPrice(Double.parseDouble(object[6].toString()));
				reports.setVat(Double.parseDouble(object[7].toString()));
				reports.setIgst(Double.parseDouble(object[8].toString()));
				reports.setTaxAmount(Double.parseDouble(object[9].toString()));
				//reports.setSalePrice(Double.parseDouble(object[10].toString()));
				reports.setTotal((double) Math.round(Double.parseDouble(object[10].toString())));
				reports.setExtraDiscount(Double.parseDouble(object[11].toString()));
				reports.setExpence(Double.parseDouble(object[12].toString()));
				reports.setTxPerexpence(Double.parseDouble(object[13].toString()));
				reports.setFinalExpenses(Double.parseDouble(object[14].toString()));
				reports.setGrossTotal((double) Math.round(Double.parseDouble(object[15].toString())));
				reports.setDate(object[16].toString());
				reports.setSupplierName(object[17].toString());
				
				catList.add(reports); 
		
			}}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return catList;
	}


	//Purchase Report Two Date
	public List<PurchaseReport> twoDatePurchase45(Date adate, Date bdate) {
		// TODO Auto-generated method stub
		HibernateUtility hbu=null;
		Session session=null;
		List<PurchaseReport> catList=null;
		try
		{
		 hbu = HibernateUtility.getInstance();
		 session = hbu.getHibernateSession();
		 
		 Long k = 0l;
		// Query query2 = session.createQuery("select billNo, carNo, barcodeNo, itemName, categoryName, salePrice, ownerName, contactNo, totalAmt, discount from CustomerBill where current_date=:adate");
		 Query query2 = session.createSQLQuery("select s.BillNo, s.ContactPerson, s.CategoryName, s.ItemName, s.HsnSacNo, s.OrignalQuantity, s.BuyPrice, s.Vat, s.igst, s.TaxAmount,s.Total, s.ExtraDiscount, s.Expences, s.ExpenseTax, s.FinalExpense, s.GrossTotal, s.Date, c.supplier_name from goodreceive s left join supplier_details c ON s.FksuppId=c.supplier_id where s.Date BETWEEN :adate AND :bdate");
		 query2.setParameter("adate", adate);
		 query2.setParameter("bdate", bdate);
	        List<Object[]> list = query2.list();
	        catList= new ArrayList<PurchaseReport>(0);
			
			
			for (Object[] object : list) {
					
				PurchaseReport reports = new PurchaseReport();
				k++;
				reports.setSrno(k);
				reports.setBillNo(object[0].toString());
				reports.setContactPerson(object[1].toString());
				reports.setCatName(object[2].toString());
				reports.setItemName(object[3].toString());
				reports.setHsnsacno(object[4].toString());
				reports.setQuantity(Long.parseLong(object[5].toString()));
				reports.setBuyPrice(Double.parseDouble(object[6].toString()));
				reports.setVat(Double.parseDouble(object[7].toString()));
				reports.setIgst(Double.parseDouble(object[8].toString()));
				reports.setTaxAmount(Double.parseDouble(object[9].toString()));
				//reports.setSalePrice(Double.parseDouble(object[10].toString()));
				reports.setTotal((double) Math.round(Double.parseDouble(object[10].toString())));
				reports.setExtraDiscount(Double.parseDouble(object[11].toString()));
				reports.setExpence(Double.parseDouble(object[12].toString()));
				reports.setTxPerexpence(Double.parseDouble(object[13].toString()));
				reports.setFinalExpenses(Double.parseDouble(object[14].toString()));
				reports.setGrossTotal((double) Math.round(Double.parseDouble(object[15].toString())));
				reports.setDate(object[16].toString());
				reports.setSupplierName(object[17].toString());
				
				catList.add(reports); 
		
			}}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return catList;
	}


	//CA Purchase Report Two Date
	public List<PurchaseReport> caReportBetweenTwoDates(Date adate, Date bdate) {
		// TODO Auto-generated method stub
		HibernateUtility hbu=null;
		Session session=null;
		List<PurchaseReport> catList=null;
		try
		{
		 hbu = HibernateUtility.getInstance();
		 session = hbu.getHibernateSession();
		 
		 Long k = 0l;
		// Query query2 = session.createQuery("select billNo, carNo, barcodeNo, itemName, categoryName, salePrice, ownerName, contactNo, totalAmt, discount from CustomerBill where current_date=:adate");
		 Query query2 = session.createSQLQuery("select CategoryName, ItemName, HsnSacNo, OrignalQuantity, Vat, igst from goodreceive  where Date BETWEEN :adate AND :bdate");
		 query2.setParameter("adate", adate);
		 query2.setParameter("bdate", bdate);
	        List<Object[]> list = query2.list();
	        catList= new ArrayList<PurchaseReport>(0);
			
			
			for (Object[] object : list) {
					
				PurchaseReport reports = new PurchaseReport();
				k++;
				reports.setSrno(k);
				
				reports.setCatName(object[0].toString());
				reports.setItemName(object[1].toString());
				reports.setHsnsacno(object[2].toString());
				reports.setQuantity(Long.parseLong(object[3].toString()));
				reports.setVat(Double.parseDouble(object[4].toString()));
				reports.setIgst(Double.parseDouble(object[5].toString()));
				
				catList.add(reports); 
		
			}}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return catList;
	}


	//TALLY Purchase Report Two Date
	public List<TallyPurchaseReport> tallyReportBetweenTwoDates(Date adate, Date bdate) {
		// TODO Auto-generated method stub
		HibernateUtility hbu=null;
		Session session=null;
		List<TallyPurchaseReport> catList=null;
		try
		{
		 hbu = HibernateUtility.getInstance();
		 session = hbu.getHibernateSession();
		 
		
		// Query query2 = session.createQuery("select billNo, carNo, barcodeNo, itemName, categoryName, salePrice, ownerName, contactNo, totalAmt, discount from CustomerBill where current_date=:adate");
		 Query query2 = session.createSQLQuery("select CategoryName, ItemName, HsnSacNo, OrignalQuantity, Vat, igst, BuyPrice, BarcodeNo from goodreceive  where Date BETWEEN :adate AND :bdate");
		 query2.setParameter("adate", adate);
		 query2.setParameter("bdate", bdate);
	        List<Object[]> list = query2.list();
	        catList= new ArrayList<TallyPurchaseReport>(0);
			
			
			for (Object[] object : list) {
					
				TallyPurchaseReport reports = new TallyPurchaseReport();
				
				
				reports.setCatName(object[0].toString());
				reports.setItemName(object[1].toString());
				reports.setHsnsacno(object[2].toString());
				reports.setQuantity(Long.parseLong(object[3].toString()));
				double quan = Double.parseDouble(object[3].toString());
				double saleP = Double.parseDouble(object[6].toString());
				double gst = Double.parseDouble(object[4].toString());
				double gstRate = gst / 2 ;
				reports.setSgst(gstRate);
				reports.setCgst(gstRate);
				double total =  quan * saleP;
				reports.setTotal(total);
				reports.setIgst(Double.parseDouble(object[5].toString()));
				reports.setBuyPrice(Double.parseDouble(object[6].toString()));
				reports.setBarcodeNo(Long.parseLong(object[7].toString()));
				
				reports.setVatapplicable("Yes");
				reports.setGstapplicable("Yes");
				reports.setTypesofsupply("goods");
				reports.setGstapplicabledate("01-07-2017");
				reports.setTaxability("Taxble");
				
				catList.add(reports); 
		
			}}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return catList;
	}


	// Between Two Dates Tally Reports
	public List<TallyPurchaseReport> tallyPurchaseReportBetweenTwoDates(Date adate, Date bdate) {
		// TODO Auto-generated method stub
		HibernateUtility hbu=null;
		Session session=null;
		List<TallyPurchaseReport> catList=null;
		try
		{
		 hbu = HibernateUtility.getInstance();
		 session = hbu.getHibernateSession();
		 
		
		// Query query2 = session.createQuery("select billNo, carNo, barcodeNo, itemName, categoryName, salePrice, ownerName, contactNo, totalAmt, discount from CustomerBill where current_date=:adate");
		 Query query2 = session.createSQLQuery("SELECT count(BillNo), BillNo, FksuppId from goodreceive  where Date BETWEEN :adate AND :bdate group by BillNo");
		 query2.setParameter("adate", adate);
		 query2.setParameter("bdate", bdate);
	     List<Object[]> list = query2.list();
	     catList= new ArrayList<TallyPurchaseReport>(0);
	     
	     for (Object[] object : list) {
	    	 
	    	 long billCount = Long.parseLong(object[0].toString());
	         String billNo1 = object[1].toString();
	         long suppId = Long.parseLong(object[2].toString());
	        
	         System.out.println("supplier id:: "+suppId);
	         System.out.println("Bill No : "+billNo1 +"   Count Of Bill No : " + billCount);
	         
	         Query query4 = session.createSQLQuery("select CategoryName, ItemName, HsnSacNo, OrignalQuantity, Vat, igst, BuyPrice, BarcodeNo, FinalExpense, BillNo, Date, TaxAmount, GrossTotal, supplier_name from goodreceive left join supplier_details on FksuppId = supplier_id where BillNo=:billNo1 AND FksuppId=:suppId");
	         query4.setParameter("billNo1", billNo1);
	         query4.setParameter("suppId", suppId);
		        List<Object[]> list5 = query4.list();
		       // catList= new ArrayList<TallyPurchaseReport>(0);
				
				for (Object[] object5 : list5) {
						
					//TallyPurchaseReport reports = new TallyPurchaseReport();	
					int n = list5.indexOf(object5);
					System.out.println("Index Of List :: "+ n);
					if(n == 0){
					TallyPurchaseReport reports = new TallyPurchaseReport();	
					reports.setBillNo(object5[9].toString());	
					reports.setVchType("Purchase");
					reports.setDate(object5[10].toString());
					reports.setPartyType(object5[13].toString());
					
					String gst = object5[4].toString();
					String igst = object5[5].toString();
					if(gst.equals("0.0")){
						reports.setPurchaseLedger("Purchase GST "+igst+"%");
						reports.setGstLedger("IGST");
					}
					if(igst.equals("0.0")){
						reports.setPurchaseLedger("Purchase GST "+gst+"%");
						reports.setGstLedger("GST");
					}
					reports.setItemName(object5[1].toString());
					reports.setQuantity(Long.parseLong(object5[3].toString()));
					reports.setBuyPrice(Double.parseDouble(object5[6].toString()));
					
					double quan = Double.parseDouble(object5[3].toString());
					double saleP = Double.parseDouble(object5[6].toString());
					double total = quan * saleP;
					reports.setTotal(total);
					reports.setAdditionalLedger("Transportation Charges");
					reports.setTransportExpenses(Double.parseDouble(object5[8].toString()));
					reports.setTaxAmt(Double.parseDouble(object5[11].toString()));
					reports.setGrossAmt(Double.parseDouble(object5[12].toString()));
					reports.setNarration("narration");
					
					catList.add(reports); 
					}
					
					//catList.add(reports);
				}
				
				Query query5 = session.createSQLQuery("select CategoryName, ItemName, HsnSacNo, OrignalQuantity, Vat, igst, BuyPrice, BarcodeNo, FinalExpense, BillNo, Date, TaxAmount, GrossTotal, supplier_name from goodreceive left join supplier_details on FksuppId = supplier_id where BillNo=:billNo1 AND FksuppId=:suppId");
				query5.setParameter("billNo1", billNo1);
				query5.setParameter("suppId", suppId);
			        List<Object[]> list6 = query5.list();
			       // catList= new ArrayList<TallyPurchaseReport>(0);
					
					for (Object[] object6 : list6) {
							
						//TallyPurchaseReport reports = new TallyPurchaseReport();	
						int a = list6.indexOf(object6);
						System.out.println("Index Of List :: "+ a);
						
						
						if(a > 0){
							TallyPurchaseReport reports = new TallyPurchaseReport();
							reports.setBillNo(object6[9].toString());	
							String gst = object6[4].toString();
							String igst = object6[5].toString();
							if(gst.equals("0.0")){
								reports.setPurchaseLedger("Purchase GST "+igst+"%");
								reports.setGstLedger("IGST");
							}
							if(igst.equals("0.0")){
								reports.setPurchaseLedger("Purchase GST "+gst+"%");
								reports.setGstLedger("GST");
							}
							reports.setItemName(object6[1].toString());
							reports.setQuantity(Long.parseLong(object6[3].toString()));
							reports.setBuyPrice(Double.parseDouble(object6[6].toString()));
							
							double quan = Double.parseDouble(object6[3].toString());
							double saleP = Double.parseDouble(object6[6].toString());
							double total = quan * saleP;
							reports.setTotal(total);
							reports.setTaxAmt(Double.parseDouble(object6[11].toString()));
							
							catList.add(reports); 
							
						}
						//catList.add(reports);
					}
		
			}}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return catList;
	}
	
	
	
	
	//get latest Transaction number in supplier_payment
	public List getSupplierPaymentTxid() {
		HibernateUtility hbu=null;
		Session session=null;
		List<GoodReceive> Txidlist=null;
		try
		{
			hbu = HibernateUtility.getInstance();
			session = hbu.getHibernateSession();
			Query query = session.createSQLQuery("SELECT transaction_id,BillNo FROM goodreceive ORDER BY transaction_id DESC LIMIT 1");

			List<Object[]> list = query.list();
			Txidlist= new ArrayList<GoodReceive>(0);
			for (Object[] object : list) {
				GoodReceive reports = new GoodReceive();
				reports.setTxId(Long.parseLong(object[0].toString()));
				Txidlist.add(reports);	 
			}}
		catch(RuntimeException e)
		{
			Log.error("Error in getCustomerBill()", e);	
		}finally
		{if(session!=null){
			hbu.closeSession(session);	
		}
		}
		return Txidlist;	
	}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
