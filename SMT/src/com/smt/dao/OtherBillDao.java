package com.smt.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.smt.bean.BillBean;
import com.smt.bean.BillCopy;
import com.smt.bean.SaleReport;
import com.smt.hibernate.OtherBill;
import com.smt.utility.HibernateUtility;

public class OtherBillDao {

	public void registerBill(OtherBill cust) {
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
// get Last Bill No 
	public List getLastBillNo() {
		// TODO Auto-generated method stub
		HibernateUtility hbu=null;
		Session session=null;
		List<BillBean> saleList=null;
		try
		{
			hbu = HibernateUtility.getInstance();
		 session = hbu.getHibernateSession();
		 Query query = session.createSQLQuery("SELECT BillNo , pkOtherBillId FROM otherbill ORDER BY BillNo DESC LIMIT 1");
			
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
	
	//get bill no to get Bill copy
	
		public List getBillNo()
		{
			HibernateUtility hbu=null;
			Session session=null;
			
			List<BillCopy> billList=null;
			 List<Object[]> list = null;
			
			try
			{
					hbu = HibernateUtility.getInstance();
					session = hbu.getHibernateSession();
					Query query=session.createSQLQuery("select BillNo, pkOtherBillId from otherbill group by BillNo order by BillNo desc;");
					list = query.list();
					billList = new ArrayList<BillCopy>(0);
					
			 for (Object[] objects : list) {
				 BillCopy bean = new BillCopy();
				
				bean.setBillNo(Long.parseLong(objects[0].toString()));
				billList.add(bean);
				}
			 }
			catch(RuntimeException  e)
			{
					
			}finally
			{if(session!=null){
				hbu.closeSession(session);	
			}
			}
			return billList;
		}
		
		
		// get single date Miscellaneos customer Sale
		public List<SaleReport> miscellaneousSingleDate(Date adate) {
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
			 Query query2 = session.createSQLQuery("select s.BillNo, s.BarcodeNo, s.ItemName, s.CategoryName, s.SalePrice, s.TotalAmount, s.Discount, s.Quantity, s.Gst, s.TaxAmount from otherbill s where s.Date =:adate");
			 query2.setParameter("adate", adate);
		        List<Object[]> list = query2.list();
		        catList= new ArrayList<SaleReport>(0);
				
				
				for (Object[] object : list) {
						
					SaleReport reports = new SaleReport();
					k++;
					reports.setSrNo(k);
					reports.setBillNo(Long.parseLong(object[0].toString()));
					reports.setBarcodeNo(Long.parseLong(object[1].toString()));
					reports.setItemName(object[2].toString());
					reports.setCategoryName(object[3].toString());
					reports.setSalePrice((double) Math.round(Double.parseDouble(object[4].toString())));
					//reports.setTotalAmt(Double.parseDouble(object[5].toString()));
					reports.setDiscount((double) Math.round(Double.parseDouble(object[6].toString())));
					reports.setQuantity(Long.parseLong(object[7].toString()));
					reports.setGst(Double.parseDouble(object[8].toString()));
					reports.setTaxAmount(Double.parseDouble(object[9].toString()));
					
					double quan = Double.parseDouble(object[7].toString());
					double saleP = Double.parseDouble(object[4].toString());
					double taxAmt = Double.parseDouble(object[9].toString());
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
		
		// get Two date Miscellaneos customer Sale
		public List<SaleReport> miscellaneousTwoDate(Date adate, Date bdate) {
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
			 Query query2 = session.createSQLQuery("select s.BillNo, s.BarcodeNo, s.ItemName, s.CategoryName, s.SalePrice, s.TotalAmount, s.Discount, s.Quantity, s.Gst, s.TaxAmount from otherbill s where s.Date BETWEEN :adate AND :bdate");
			 query2.setParameter("adate", adate);
			 query2.setParameter("bdate", bdate);
		        List<Object[]> list = query2.list();
		        catList= new ArrayList<SaleReport>(0);
				
				
				for (Object[] object : list) {
						
					SaleReport reports = new SaleReport();
					k++;
					reports.setSrNo(k);
					reports.setBillNo(Long.parseLong(object[0].toString()));
					reports.setBarcodeNo(Long.parseLong(object[1].toString()));
					reports.setItemName(object[2].toString());
					reports.setCategoryName(object[3].toString());
					reports.setSalePrice((double) Math.round(Double.parseDouble(object[4].toString())));
					//reports.setTotalAmt(Double.parseDouble(object[5].toString()));
					reports.setDiscount((double) Math.round(Double.parseDouble(object[6].toString())));
					reports.setQuantity(Long.parseLong(object[7].toString()));
					reports.setGst(Double.parseDouble(object[8].toString()));
					reports.setTaxAmount(Double.parseDouble(object[9].toString()));
					
					double quan = Double.parseDouble(object[7].toString());
					double saleP = Double.parseDouble(object[4].toString());
					double taxAmt = Double.parseDouble(object[9].toString());
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
		
		// get category wise Miscellaneos customer Sale
		public List<SaleReport> miscellaneousSaleWiseCustomer(String mscatId) {
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
			 Query query2 = session.createSQLQuery("select s.BillNo, s.BarcodeNo, s.ItemName, s.CategoryName, s.SalePrice, s.TotalAmount, s.Discount, s.Quantity, s.Gst, s.TaxAmount from otherbill s where s.CategoryName =:mscatId");
			 query2.setParameter("mscatId", mscatId);
			 
		        List<Object[]> list = query2.list();
		        catList= new ArrayList<SaleReport>(0);
				
				
				for (Object[] object : list) {
						
					SaleReport reports = new SaleReport();
					k++;
					reports.setSrNo(k);
					reports.setBillNo(Long.parseLong(object[0].toString()));
					reports.setBarcodeNo(Long.parseLong(object[1].toString()));
					reports.setItemName(object[2].toString());
					reports.setCategoryName(object[3].toString());
					reports.setSalePrice((double) Math.round(Double.parseDouble(object[4].toString())));
					//reports.setTotalAmt(Double.parseDouble(object[5].toString()));
					reports.setDiscount((double) Math.round(Double.parseDouble(object[6].toString())));
					reports.setQuantity(Long.parseLong(object[7].toString()));
					reports.setGst(Double.parseDouble(object[8].toString()));
					reports.setTaxAmount(Double.parseDouble(object[9].toString()));
					
					double quan = Double.parseDouble(object[7].toString());
					double saleP = Double.parseDouble(object[4].toString());
					double taxAmt = Double.parseDouble(object[9].toString());
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
		
		// get Bill no wise Miscellaneos customer Sale
		public List<SaleReport> billnowiseMiscellaneoussell(long msBillNocust) {
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
			 Query query2 = session.createSQLQuery("select s.BillNo, s.BarcodeNo, s.ItemName, s.CategoryName, s.SalePrice, s.TotalAmount, s.Discount, s.Quantity, s.Gst, s.TaxAmount from otherbill s where s.BillNo =:msBillNocust");
			 query2.setParameter("msBillNocust", msBillNocust);
			 
		        List<Object[]> list = query2.list();
		        catList= new ArrayList<SaleReport>(0);
				
				
				for (Object[] object : list) {
						
					SaleReport reports = new SaleReport();
					k++;
					reports.setSrNo(k);
					reports.setBillNo(Long.parseLong(object[0].toString()));
					reports.setBarcodeNo(Long.parseLong(object[1].toString()));
					reports.setItemName(object[2].toString());
					reports.setCategoryName(object[3].toString());
					reports.setSalePrice((double) Math.round(Double.parseDouble(object[4].toString())));
					//reports.setTotalAmt(Double.parseDouble(object[5].toString()));
					reports.setDiscount((double) Math.round(Double.parseDouble(object[6].toString())));
					reports.setQuantity(Long.parseLong(object[7].toString()));
					reports.setGst(Double.parseDouble(object[8].toString()));
					reports.setTaxAmount(Double.parseDouble(object[9].toString()));
					
					double quan = Double.parseDouble(object[7].toString());
					double saleP = Double.parseDouble(object[4].toString());
					double taxAmt = Double.parseDouble(object[9].toString());
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
		
		//Barcode No Wise Miscellaneos Sale Report
		public List<SaleReport> barcodenowiseMiscellaneoussell(long barcodeMiscellaneous) {
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
			 Query query2 = session.createSQLQuery("select s.BillNo, s.BarcodeNo, s.ItemName, s.CategoryName, s.SalePrice, s.TotalAmount, s.Discount, s.Quantity, s.Gst, s.TaxAmount from otherbill s where s.BarcodeNo =:barcodeMiscellaneous");
			 query2.setParameter("barcodeMiscellaneous", barcodeMiscellaneous);
			 
		        List<Object[]> list = query2.list();
		        catList= new ArrayList<SaleReport>(0);
				
				
				for (Object[] object : list) {
						
					SaleReport reports = new SaleReport();
					k++;
					reports.setSrNo(k);
					reports.setBillNo(Long.parseLong(object[0].toString()));
					reports.setBarcodeNo(Long.parseLong(object[1].toString()));
					reports.setItemName(object[2].toString());
					reports.setCategoryName(object[3].toString());
					reports.setSalePrice((double) Math.round(Double.parseDouble(object[4].toString())));
					//reports.setTotalAmt(Double.parseDouble(object[5].toString()));
					reports.setDiscount((double) Math.round(Double.parseDouble(object[6].toString())));
					reports.setQuantity(Long.parseLong(object[7].toString()));
					reports.setGst(Double.parseDouble(object[8].toString()));
					reports.setTaxAmount(Double.parseDouble(object[9].toString()));
					
					double quan = Double.parseDouble(object[7].toString());
					double saleP = Double.parseDouble(object[4].toString());
					double taxAmt = Double.parseDouble(object[9].toString());
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
			 
			 Query query3 = session.createSQLQuery("SELECT count(BillNo), BillNo from otherbill where date BETWEEN :adate AND :bdate group by BillNo");
			 query3.setParameter("adate", adate);
			 query3.setParameter("bdate", bdate);
			 List<Object[]> list1 = query3.list();
		        catList= new ArrayList<SaleReport>(0);
		        for (Object[] o : list1) {
		        	long billCount = Long.parseLong(o[0].toString());
		        	long billNo1 = Long.parseLong(o[1].toString());
		        	
		        	System.out.println("Bill No : "+billNo1 +"Count Of Bill No : " + billCount);
		        	
		        	Query query4 = session.createSQLQuery("SELECT GrossTotal, Date, Quantity, Gst, Igst, HsnSacNo, BillNo from otherbill where BillNo=:billNo1 group by BillNo");
		        	query4.setParameter("billNo1", billNo1);
	    	        List<Object[]> list2 = query4.list();
	    	      //  catList= new ArrayList<SaleReport>(0);
	    			
	    			for (Object[] object1 : list2) {
	    					
	    				SaleReport reports = new SaleReport();
	    				
	    				
	    				reports.setGrossamt(Double.parseDouble(object1[0].toString()));
	    				reports.setDate(object1[1].toString());
	    				reports.setBillNo(Long.parseLong(object1[6].toString()));
	    				reports.setCustName("other"+billNo1);
	    				reports.setDrCr("Dr");
	    				reports.setVoucherType("Sales");
	    				
	    				catList.add(reports); 
	    		
	    			}
		        	
		        	Query query2 = session.createSQLQuery("SELECT SalePrice, ItemName, Quantity, Gst, Igst, Date, BillNo from otherbill where BillNo="+billNo1);
		    		
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
		    			
		    			Query query5 = session.createSQLQuery("SELECT SalePrice, ItemName, Quantity, Gst, Igst, Date, BillNo, TaxAmount from otherbill where BillNo="+billNo1);
			    		
		    	        List<Object[]> list5 = query5.list();
		    	       // catList= new ArrayList<SaleReport>(0);
		    			
		    			for (Object[] object5 : list5) {
		    				int i =	object5.length;
		    				System.out.println("length Of ARRAy :: " + i);
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
					 
					 Query query3 = session.createSQLQuery("SELECT count(BillNo), BillNo from otherbill where date BETWEEN :adate AND :bdate group by BillNo");
					 query3.setParameter("adate", adate);
					 query3.setParameter("bdate", bdate);
					 List<Object[]> list1 = query3.list();
				        catList= new ArrayList<SaleReport>(0);
				        for (Object[] o : list1) {
				        	long billCount = Long.parseLong(o[0].toString());
				        	long billNo1 = Long.parseLong(o[1].toString());
				        	
				        	System.out.println("Bill No : "+billNo1 +"Count Of Bill No : " + billCount);
				        	
				        	Query query4 = session.createSQLQuery("SELECT GrossTotal, Date, Quantity, Gst, Igst, HsnSacNo, BillNo, SalePrice, TaxAmount, ItemName from otherbill where BillNo=:billNo1");
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
								reports.setPartyType("Other");
								
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
								reports.setItemName(object5[9].toString());
								reports.setQuantity(Long.parseLong(object5[2].toString()));
								reports.setSalePrice(Double.parseDouble(object5[7].toString()));
								
								double quan = Double.parseDouble(object5[2].toString());
								double saleP = Double.parseDouble(object5[7].toString());
								double total = quan * saleP;
								reports.setTotal(total);
								
								reports.setTaxAmt(Double.parseDouble(object5[8].toString()));
								reports.setGrossAmt(Double.parseDouble(object5[0].toString()));
								reports.setNarration("narration");
			    				
			    				catList.add(reports); 
								}
			    			}
				        	
				        	Query query2 = session.createSQLQuery("SELECT GrossTotal, Date, Quantity, Gst, Igst, HsnSacNo, BillNo, SalePrice, TaxAmount, ItemName from otherbill where BillNo=:billNo1");
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
									reports.setItemName(object[9].toString());
									reports.setQuantity(Long.parseLong(object[2].toString()));
									reports.setSalePrice(Double.parseDouble(object[7].toString()));
									
									double quan = Double.parseDouble(object[2].toString());
									double saleP = Double.parseDouble(object[7].toString());
									double total = quan * saleP;
									reports.setTotal(total);
									reports.setTaxAmt(Double.parseDouble(object[8].toString()));
									
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

}
