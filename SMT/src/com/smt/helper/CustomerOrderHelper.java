package com.smt.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.smt.bean.BillBean;
import com.smt.bean.CustomerBean;
import com.smt.bean.DayWiseSalesmanCommision;
import com.smt.bean.GetShopWiseSaleBean;
import com.smt.bean.GetShopWiseSaleBetTwoDatesBean;
import com.smt.bean.GetShopWiseStock;
import com.smt.bean.GetSuppWiseStockBean;
import com.smt.bean.GstReportBean;
import com.smt.bean.PODetailBean;
import com.smt.bean.PurchaseReport;
import com.smt.bean.PurchaseReportBean;
import com.smt.bean.SaleReport;
import com.smt.bean.SaleReports;
import com.smt.bean.SaleReturnBean;
import com.smt.bean.TallyPurchaseReport;
import com.smt.dao.CarEntryDao;
import com.smt.dao.CustomerOrderDao;


import com.smt.dao.GoodReciveDao;
import com.smt.dao.ServiceDao;
import com.smt.dao.StockDao;
import com.smt.dao.SupplierDetailDao;
import com.smt.hibernate.CustomerBill;
import com.smt.hibernate.GoodReceive;
import com.smt.hibernate.Stock;
import com.smt.hibernate.SupplierDetail;
import com.smt.utility.HibernateUtility;

public class CustomerOrderHelper {

	
		//get Data On CustomerBill Using Barcode No
	
		public CustomerBean getDetailsById(HttpServletRequest request,
				HttpServletResponse response) {
			
			String productId=request.getParameter("productId");
			String carNo = request.getParameter("carNo");
			System.out.println(productId+"  - productId");
			Map<Long,CustomerBean> map = new HashMap<Long,CustomerBean>();
			
			CustomerOrderDao dao = new CustomerOrderDao();
			List<CustomerBean> catList = dao.getAllItemDetails(productId,carNo);
			
			CustomerBean cs = null;
			if(catList!= null && catList.size() > 0 )
			{	
				cs = (CustomerBean)catList.get(0); 
			}
			return cs;
		}
	
		public List getSaleDetailsBYDate(HttpServletRequest request, HttpServletResponse response) 
		{
			 String fDate = request.getParameter("fisDate");
	         String tDate = request.getParameter("endDate");
			
	         Map<Long,SaleReports> map = new HashMap<Long,SaleReports>();
	 		
	 		CustomerOrderDao dao = new CustomerOrderDao();
	 		List<SaleReports> saleList = dao.getSaleDetailsDateWise(fDate,tDate);
	 		
	 		
	 		return saleList;
		}
		
		public List getSaleDetailsBYYear(HttpServletRequest request, HttpServletResponse response) 
		{
			 String fDate = request.getParameter("fisDate");
	         String tDate = request.getParameter("endDate");
			
	         Map<Long,SaleReports> map = new HashMap<Long,SaleReports>();
	 		
	 		CustomerOrderDao dao = new CustomerOrderDao();
	 		List<SaleReports> saleList = dao.getSaleDetailsYearly(fDate,tDate);
	 		
	 		
	 		return saleList;
		}
		
		public List getSaleDetailsBYSingalDate(HttpServletRequest request, HttpServletResponse response) 
		{
			 String fDate = request.getParameter("fDate");
	       System.out.println(fDate+"vxvdfvdf");
			
	         Map<Long,SaleReports> map = new HashMap<Long,SaleReports>();
	 		
	 		CustomerOrderDao dao = new CustomerOrderDao();
	 		List<SaleReports> saleList = dao.getSaleDetailsBySingalDateWise(fDate);
	 		
	 		
	 		return saleList;
		}
	
		
		
		
		public List getItemsBYSaleBILL(HttpServletRequest request,
				HttpServletResponse response) {
			String billNo= request.getParameter("customerBill");
		       System.out.println(billNo+"vxvdfvdf");
				
		         Map<Long,SaleReports> map = new HashMap<Long,SaleReports>();
		 		
		 		CustomerOrderDao dao = new CustomerOrderDao();
		 		List<SaleReports> saleList = dao.getSaleDetailsByBillNO(billNo);
		 		
		 		
		 		return saleList;
		}
		
		
		
		
		Long BillNo = 1l;
		
		// car register Bill 
		public void registerBill(HttpServletRequest request,
				HttpServletResponse response) {
			
			HttpSession session3 = request.getSession();
			CustomerOrderDao data = new CustomerOrderDao();
			List stkList  = data.getLastBillNo();
			
			for(int i=0;i<stkList.size();i++){
				
				BillBean st = (BillBean)stkList.get(i);
				BillNo = st.getBillNo();
				
				BillNo++;
				
			}
			
			/*
			 * 
			 * SMS SEND CODE
			 * 
			 * 
			 * 
			String autky= "13447AkJTeLLEbf582453c8";
			String authkey= autky;
			System.out.println("Keyyyy"+autky);
			String senderId = "102234";
			System.out.println("Senderrrrrrr"+senderId);
			String customerFirstName=request.getParameter("customerFirstName");
			System.out.println("custName"+customerFirstName);
			String message = "Thanks For Shopping  " + customerFirstName+" " +"Visit Again" +" " + "SM MALL NANDED";
			String route="default";
			
			String customerMobileNo=request.getParameter("customerMobileNo");
			//cOrder.setCustomerMobileNo(Long.parseLong(customerMobileNo));
		
			 //Prepare Url
	        URLConnection myURLConnection=null;
	        URL myURL=null;
	        BufferedReader reader=null;

	        //encoding message
	        String encoded_message=URLEncoder.encode(message);

	        
	        String mainUrl="http://sms.ssdindia.com/api/sendhttp.php?";
	        System.out.println("##########"+mainUrl);
	        StringBuilder sbPostData= new StringBuilder(mainUrl);
	        sbPostData.append("authkey="+authkey);
	        sbPostData.append("&mobiles="+customerMobileNo);
	        //System.out.println("!!!!!"+customerMobileNo);
	        sbPostData.append("&message="+encoded_message);
	        sbPostData.append("&route="+route);
	        sbPostData.append("&sender="+senderId);

	        
	        mainUrl = sbPostData.toString();
	        System.out.println(mainUrl);
	        try
	        {
	            //prepare connection
	            myURL = new URL(mainUrl);
	            myURLConnection = myURL.openConnection();
	            myURLConnection.connect();
	            reader= new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));
	            //reading response
	            String response1;
	            while ((response1 = reader.readLine()) != null)
	            //print response
	            System.out.println(response1);

	            //finally close connection
	            reader.close();
	        }
	        catch (IOException e)
	        {
	                e.printStackTrace();
	        }
	        */
			
			
			com.smt.hibernate.CustomerBill cust = new com.smt.hibernate.CustomerBill();
			
			Integer count = Integer.parseInt(request.getParameter("count"));
			System.out.println("c111111"+count);

			for(int i=0;i<count;i++){

				String itemName = request.getParameter("itemName"+i);
				cust.setItemName(itemName);
				
				String categoryName = request.getParameter("categoryName"+i);
				cust.setCategoryName(categoryName);

				String quantity= request.getParameter("quantity"+i);
				System.out.println("quantity++++++++++++"+quantity);
	            cust.setQuantity(Long.parseLong(quantity));
	            
	            String salePrice= request.getParameter("salePrice"+i);
				
	            //cust.setSalePrice(Double.parseDouble(salePrice));
	            
	            String barcodeNo= request.getParameter("barcodeNo"+i);
				System.out.println("barcodeNo -  "+barcodeNo);
	            cust.setBarcodeNo(Long.parseLong(barcodeNo));
	            
	            String hsnSacNo = request.getParameter("hsnSacNo"+i);
				cust.setHsnSacNo(hsnSacNo);
				
				String discountGrid = request.getParameter("discountGrid"+i);
				cust.setDiscountGrid(Double.parseDouble(discountGrid));
				
				String discountAmt = request.getParameter("discountAmt"+i);
				cust.setDiscountAmt(Double.parseDouble(discountAmt));
				
				String vat = request.getParameter("vat"+i);
				String igst = request.getParameter("igst"+i);
				System.out.println("vat & igst  - "+vat+" & "+igst);
				if(vat.equals("0")){
					cust.setVat(Double.parseDouble(igst));
					double igstAmt = Double.parseDouble(salePrice) - (Double.parseDouble(salePrice) * (100 / (100 + Double.parseDouble(igst))));
					double netPrice = Double.parseDouble(salePrice) - igstAmt;
					cust.setSalePrice(netPrice);

				}
				else{
					cust.setVat(Double.parseDouble(vat));
					double gstAmt = Double.parseDouble(salePrice) - (Double.parseDouble(salePrice) * (100 / (100 + Double.parseDouble(vat))));
					double netPrice = Double.parseDouble(salePrice) - gstAmt;
					cust.setSalePrice(netPrice);
				}
				cust.setIgst(0d);
				
				String taxAmount = request.getParameter("taxAmount"+i);
				cust.setTaxAmount(Double.parseDouble(taxAmount));
	            
				String contactNo = request.getParameter("contactNo");
				cust.setContactNo(Long.parseLong(contactNo));
				
				String ownerName = request.getParameter("ownerName");
				cust.setOwnerName(ownerName);
				
				String carNo = request.getParameter("carNo");
				cust.setCarNo(carNo);
				
				String totalAmount = request.getParameter("totalAmount");
				cust.setTotalAmt(Double.parseDouble(totalAmount));
				
			
			  //String discount = request.getParameter("discount"); 
			 // double disAmt= Double.parseDouble(discount) / count; 
			  
			 
			  //cust.setDiscount(Double.parseDouble(discount));
			 
			/*
			 * String laberCharges = request.getParameter("laberCharges");
			 * double laber = Double.parseDouble(laberCharges) / count;
			 * cust.setLaberCharges(laber);
			 */
				
				String total = request.getParameter("total"+i);
				cust.setTotalperItem(Double.parseDouble(total));
				
			/*
			 * String grossTotal = request.getParameter("grossTotal");
			 * cust.setGrossamt(Double.parseDouble(grossTotal));
			 */
				
				DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
				Date dateobj = new Date();
				System.out.println(df.format(dateobj));
				String newDate = df.format(dateobj);
				cust.setCurrent_date(dateobj);
				
				session3.setAttribute("BillNo", BillNo);
				
				if(BillNo == null){
					cust.setBillNo(1l);
					}
					else
					{
						cust.setBillNo(BillNo);	
					}
				
				CustomerOrderDao dao=new CustomerOrderDao();
				//dao.registerBill(cust);
				
				
				Long pk_temp_id = Long.parseLong(request.getParameter("pk_temp_id"+i));
				System.out.println("pk_temp_id" +pk_temp_id);
				dao.updateTabaleStatus(pk_temp_id);
				
				
				Long item_id = Long.parseLong(request.getParameter("item_id"+i));
				System.out.println("item_id" +item_id);
				GoodReciveDao good = new GoodReciveDao();
				good.updateQuantity(item_id,quantity);
				
				
				StockDao dao1 = new StockDao();
		        List stkList2  = dao1.getAllStockEntry();
		        
		        for(int j=0;j<stkList2.size();j++){
		        	
		        	Stock st = (Stock)stkList2.get(j);
		        	String ItemId = st.getItemName();
		        	String cat = st.getCatName();
		        	
		        	
		        	/*If ItemName Is Already Exists In Stock Table */ 
		        	if(ItemId.equals(itemName) && cat.equals(categoryName)){
		        		 Long PkItemId = st.getPkStockId();
		        		 Long qunty = st.getQuantity();
		        		
		        		 Long updatequnty = (long) (qunty - Long.parseLong(quantity));
		        				 
		        		
		        		 HibernateUtility hbu = HibernateUtility.getInstance();
		        		 Session session = hbu.getHibernateSession();
		        		 Transaction transaction = session.beginTransaction();
		        		 
		        		 
		        		 Date date = new Date();
		        	
		        	     Stock updateStock = (Stock) session.get(Stock.class, new Long(PkItemId));	 
		        		 updateStock.setUpdateDate(date);
		        		 updateStock.setQuantity(updatequnty);
		        		 
		        		session.saveOrUpdate(updateStock);
		        		transaction.commit();
		        		
		        	}
				
		        }
			}
			
			
			////////////////////////service///////////////
			
			Integer count1 = Integer.parseInt(request.getParameter("count1"));
			System.out.println("c111111"+count1);

			for(int i=0;i<count1;i++)
			
			{

				String itemName = request.getParameter("itemName"+i);
				cust.setService_item(itemName);
				
				String hsnSacNo = request.getParameter("hsnSacNo"+i);
				cust.setService_hsn(hsnSacNo);
				
				String quantity= request.getParameter("quantity"+i);
				System.out.println("quantity"+quantity);
	            cust.setService_quantity(Long.parseLong(quantity));
	            
	            String salePrice= request.getParameter("salePrice"+i);
				cust.setService_saleprice(Double.parseDouble(salePrice));
	            
	            
	            
				String discountGrid = request.getParameter("discountGrid"+i);
				cust.setService_disc_grid(Double.parseDouble(discountGrid));
				
				String discountAmt = request.getParameter("discountAmt"+i);
				cust.setService_discAmt(Double.parseDouble(discountAmt));
				
				String vat = request.getParameter("vat"+i);
				String igst = request.getParameter("igst"+i);
				System.out.println("vat & igst  - "+vat+" & "+igst);
				
				cust.setService_gst(Double.parseDouble(vat));
				cust.setService_igst(Double.parseDouble(igst));
				
				String taxAmount = request.getParameter("taxAmount"+i);
				cust.setTaxAmount(Double.parseDouble(taxAmount));
	            
				String totalAmount = request.getParameter("totalAmount");
				cust.setServiceTotalAmt(Double.parseDouble(totalAmount));
				
				String total = request.getParameter("total"+i);
				cust.setServicetotalPerItem(Double.parseDouble(total));
				
				
				 String grossTotal = request.getParameter("grossTotal");
				  cust.setGrossamt(Double.parseDouble(grossTotal));
				 
					
				
				CustomerOrderDao dao=new CustomerOrderDao();
				dao.registerBill(cust);
				
		       
			}
			
		    
			//Session for PDF
			
			HttpSession billNoInSession = request.getSession();
			billNoInSession.setAttribute("carBillNO", BillNo);
			
			System.out.println("#######################  "+ billNoInSession.getAttribute("carBillNO"));
			
			Long carID = Long.parseLong(request.getParameter("carID"));
			CarEntryDao acrive = new CarEntryDao();
			acrive.updateCarStatus(carID);
		}






      // get current Stock

		public List getAllCurrentStock(HttpServletRequest request,
				HttpServletResponse response) {
			 Map<Long,Stock> map = new HashMap<Long,Stock>();
				
			    StockDao dao = new StockDao();
				List<Stock> exp1List = dao.getAllCurrentStock();
				
				return exp1List;
		}







       // category Wise Stock
		public List getCategoryWiseStock(HttpServletRequest request,
				HttpServletResponse response) {
			// TODO Auto-generated method stub
			String catId = request.getParameter("catId");
			 Map<Long,Stock> map = new HashMap<Long,Stock>();
				
			    StockDao dao = new StockDao();
				List<Stock> exp1List = dao.getCategoryWiseStock(catId);
				
				return exp1List;
			
		}

		// Barcode Wise Stock
		public List getBarcodeWiseStock(HttpServletRequest request,
				HttpServletResponse response) {
			// TODO Auto-generated method stub
			 
			 Long barcodeNo = Long.parseLong(request.getParameter("barcodeNo"));
			 System.out.println("barcode : "+barcodeNo);
			 Map<Long,GoodReceive> map = new HashMap<Long,GoodReceive>();
				
			    GoodReciveDao dao = new GoodReciveDao();
				List<GoodReceive> exp1List = dao.getCategoryWiseStock(barcodeNo);
				
				return exp1List;
		}
		
		// get supplierwise stock between two selected date
				public List getSupWiseStockBetTwoDays(HttpServletRequest request,
						HttpServletResponse response) {

					Long suppId = Long.parseLong(request.getParameter("suppId"));
					String fDate = request.getParameter("fDate");
					String eDate = request.getParameter("eDate");
					 System.out.println("++++++++++++++++++++++ Suppp Id : "+suppId);
					 System.out.println("++++++++++++++++++++++ Start Date : "+ fDate);
					 System.out.println("++++++++++++++++++++++ End Date : "+eDate);
					 Map<Long,GoodReceive> map = new HashMap<Long,GoodReceive>();
						
					GoodReciveDao dao = new GoodReciveDao();
					List<GoodReceive> exp1List = dao.getSuppWiseStockBetTwoDate(suppId,fDate,eDate);
						
					return exp1List;
				}


        // bill no wise stock
		public List getBillNoWiseStock(HttpServletRequest request,
				HttpServletResponse response) {
			// TODO Auto-generated method stub
			 String Billno = (request.getParameter("Billno"));
			 System.out.println("barcode : "+Billno);
			 Map<Long,GoodReceive> map = new HashMap<Long,GoodReceive>();
				
			    GoodReciveDao dao = new GoodReciveDao();
				List<GoodReceive> exp1List = dao.getBillNoWiseStock(Billno);
				
				return exp1List;
		}

     // single date purchase
		public List singleDatePurchase(HttpServletRequest request,
				HttpServletResponse response) {
			// TODO Auto-generated method stub
			String fDate = request.getParameter("fDate");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			
			Date adate = null;
			try {
			 adate=	format.parse(fDate);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 Map<Long,PurchaseReportBean> map = new HashMap<Long,PurchaseReportBean>();
				
			    GoodReciveDao dao = new GoodReciveDao();
				List<PurchaseReportBean> exp1List = dao.singleDatePurchase(adate);
				
				return exp1List;
		}

        // two dates purchase
		public List purchaseReportBetweenTwoDates(HttpServletRequest request,
				HttpServletResponse response) {
			String fisDate = request.getParameter("fisDate");
			String endDate = request.getParameter("endDate");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			
			Date adate = null;
			Date bdate = null;
			
			try {
			 adate=	format.parse(fisDate);
			 bdate=	format.parse(endDate);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 Map<Long,PurchaseReportBean> map = new HashMap<Long,PurchaseReportBean>();
				
			    GoodReciveDao dao = new GoodReciveDao();
				List<PurchaseReportBean> exp1List = dao.purchaseReportBetweenTwoDates(adate,bdate);
				
				return exp1List;
		}
        
		// single date sale
		public List singleDateSale(HttpServletRequest request,
				HttpServletResponse response) {
			// TODO Auto-generated method stub
			String fDate = request.getParameter("fDate");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			
			Date adate = null;
			try {
			 adate=	format.parse(fDate);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 Map<Long,SaleReport> map = new HashMap<Long,SaleReport>();
				
			    CustomerOrderDao dao = new CustomerOrderDao();
				List<SaleReport> exp1List = dao.singleDatePurchase(adate);
				
				return exp1List;
		}


		//get Category Wise sale report
		public List CategoryWiseSaleReport(HttpServletRequest request,
				HttpServletResponse response) {
			// TODO Auto-generated method stub
			String catName = request.getParameter("catName");
		
			 Map<Long,SaleReport> map = new HashMap<Long,SaleReport>();
				
			    CustomerOrderDao dao = new CustomerOrderDao();
				List<SaleReport> exp1List = dao.CategoryWiseSaleReport(catName);
				
				return exp1List;
		}



       //sale between two dates

		public List getSaleDetailsBetweenTwoDates(HttpServletRequest request,
				HttpServletResponse response) {
			// TODO Auto-generated method stub
			String fisDate = request.getParameter("fisDate");
			String endDate = request.getParameter("endDate");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			
			Date adate = null;
			Date bdate = null;
			
			try {
			 adate=	format.parse(fisDate);
			 bdate=	format.parse(endDate);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 Map<Long,SaleReport> map = new HashMap<Long,SaleReport>();
				
			    CustomerOrderDao dao = new CustomerOrderDao();
				List<SaleReport> exp1List = dao.getSaleDetailsBetweenTwoDates(adate,bdate);
				
				return exp1List;
		}
     
		//day closing report
		public List dayCloseReport(HttpServletRequest request,
				HttpServletResponse response) {
			// TODO Auto-generated method stub
			 Map<Long,SaleReport> map = new HashMap<Long,SaleReport>();
				
			    CustomerOrderDao dao = new CustomerOrderDao();
				List<SaleReport> exp1List = dao.dayCloseReport();
				
				return exp1List;
		}	
		
		public Map getSaleItemByBillNo(String billNo) {
			// TODO Auto-generated method stub
			CustomerOrderDao dao = new CustomerOrderDao();
			List list = dao.getSaleItemByBillNo(billNo);
			Map  map =  new HashMap();
			
			for(int i=0;i<list.size();i++)
			{
				Object[] o = (Object[])list.get(i);
				SaleReturnBean bean = new SaleReturnBean();
				bean.setPkBillId(Long.parseLong(o[0].toString()));
				bean.setCarNo(o[1].toString());
				bean.setCategoryName(o[2].toString());
				bean.setItemName(o[3].toString());
				bean.setBarcodeNo(Long.parseLong(o[4].toString()));
				bean.setQuantity(Long.parseLong(o[5].toString()));
				String quanty = o[5].toString();
				String totalAmt = o[8].toString();
				double salePrice = Double.parseDouble(totalAmt) / Double.parseDouble(quanty);
				bean.setSalePrice(salePrice);
				bean.setContactNo(Long.parseLong(o[7].toString()));
				bean.setTotalAmt(Double.parseDouble(o[8].toString()));
				bean.setDiscount(Double.parseDouble(o[9].toString()));
				bean.setGrossamt(Double.parseDouble(o[10].toString()));
				bean.setDate(o[11].toString());
				Long editQuan = 0l;
				bean.setEditQuantity(editQuan);
				/*bean.setTotalAmt(Double.parseDouble(o[0].toString()));*/
				System.out.println("***************"+o[0]);
				map.put(bean.getPkBillId(),bean);
			}
			return map;
		}







// other bill
	public CustomerBean getDetailsById1(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		String key=request.getParameter("key");
		
		System.out.println(key+"barcode");
		Map<Long,CustomerBean> map = new HashMap<Long,CustomerBean>();
		
		CustomerOrderDao dao = new CustomerOrderDao();
		List<CustomerBean> catList = dao.getAllItemDetails1(key);
		
		CustomerBean cs = null;
		if(catList!= null && catList.size() > 0 )
		{	
			cs = (CustomerBean)catList.get(0); 
		}
		return cs;
	}
	
	public List getAllBillNumbers()
	{
		CustomerOrderDao dao = new CustomerOrderDao();
		return dao.getAllBillNumbers();
	}








	/*public List CategoryWiseSaleReport(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}*/
	
	
	// two dates sale gst
			public List gstSaleReportBetweenTwoDates(HttpServletRequest request,
					HttpServletResponse response) {
				String fisDate = request.getParameter("fisDate");
				String endDate = request.getParameter("endDate");
	            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				
				Date adate = null;
				Date bdate = null;
				
				try {
				 adate=	format.parse(fisDate);
				 bdate=	format.parse(endDate);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				 Map<Long,GstReportBean> map = new HashMap<Long,GstReportBean>();
					
				 CustomerOrderDao dao = new CustomerOrderDao();
					List<GstReportBean> exp1List = dao.gstSaleReportBetweenTwoDates(adate,bdate);
					
					return exp1List;
			}


           // Single Date Sale Report
			public List singleDateSaleReport(HttpServletRequest request, HttpServletResponse response) {
				// TODO Auto-generated method stub
				String fisDate = request.getParameter("fisDate");
	            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				
				Date adate = null;
				Date bdate = null;
				
				try {
				 adate=	format.parse(fisDate);
				
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				 Map<Long,SaleReport> map = new HashMap<Long,SaleReport>();
					
				    CustomerOrderDao dao = new CustomerOrderDao();
					List<SaleReport> exp1List = dao.singleDateSaleReport(adate);
					
					return exp1List;
			}

			// Two Date Sale Report
			public List twoDateSaleReport(HttpServletRequest request, HttpServletResponse response) {
				// TODO Auto-generated method stub
				String fisDate = request.getParameter("fisDate");
				String endDate = request.getParameter("endDate");
	            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				
				Date adate = null;
				Date bdate = null;
				
				try {
				 adate=	format.parse(fisDate);
				 bdate=	format.parse(endDate);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				 Map<Long,SaleReport> map = new HashMap<Long,SaleReport>();
					
				    CustomerOrderDao dao = new CustomerOrderDao();
					List<SaleReport> exp1List = dao.twoDateSaleReport(adate,bdate);
					
					return exp1List;
			}

			// Category Wise Sale Report
			public List categorySaleWise(HttpServletRequest request, HttpServletResponse response) {
				// TODO Auto-generated method stub
				String catName = request.getParameter("catName");
				
				 Map<Long,SaleReport> map = new HashMap<Long,SaleReport>();
					
				    CustomerOrderDao dao = new CustomerOrderDao();
					List<SaleReport> exp1List = dao.categorySaleWise(catName);
					
					return exp1List;
			}

          // Single Date Vehicle Customer Sale Report
			public List vehicleSingleDate(HttpServletRequest request, HttpServletResponse response) {
				// TODO Auto-generated method stub
				String fDate = request.getParameter("fDate");
	            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				
				Date adate = null;
				try {
				 adate=	format.parse(fDate);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				 Map<Long,SaleReport> map = new HashMap<Long,SaleReport>();
					
				    CustomerOrderDao dao = new CustomerOrderDao();
					List<SaleReport> exp1List = dao.vehicleSingleDate(adate);
					
					return exp1List;
			}


			// get Two date Vehicle customer Sale
			public List vehicleTwoDate(HttpServletRequest request, HttpServletResponse response) {
				// TODO Auto-generated method stub
				String fDate = request.getParameter("fDate");
				String eDate = request.getParameter("eDate");
	            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				
				Date adate = null;
				Date bdate = null;
				try {
				 adate=	format.parse(fDate);
				 bdate=	format.parse(eDate);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				 Map<Long,SaleReport> map = new HashMap<Long,SaleReport>();
					
				    CustomerOrderDao dao = new CustomerOrderDao();
					List<SaleReport> exp1List = dao.vehicleTwoDate(adate,bdate);
					
					return exp1List;
			}


			// get Category Wise Vehicle customer Sale
			public List categorySaleWiseCustomer(HttpServletRequest request, HttpServletResponse response) {
				// TODO Auto-generated method stub
				String catId = request.getParameter("catId");
				
				 Map<Long,SaleReport> map = new HashMap<Long,SaleReport>();
					
				    CustomerOrderDao dao = new CustomerOrderDao();
					List<SaleReport> exp1List = dao.categorySaleWiseCustomer(catId);
					
					return exp1List;
			}



			// get Category Wise Vehicle customer Sale
			public List billnowiseVehiclesell(HttpServletRequest request, HttpServletResponse response) {
				// TODO Auto-generated method stub
				String BillNo = request.getParameter("BillNocust");
				
				 Map<Long,SaleReport> map = new HashMap<Long,SaleReport>();
					
				    CustomerOrderDao dao = new CustomerOrderDao();
					List<SaleReport> exp1List = dao.billnowiseVehiclesell(BillNo);
					
					return exp1List;
			}

			// get Barcode Wise Vehicle customer Sale
			public List barcodewiseVehicleSale(HttpServletRequest request, HttpServletResponse response) {
				// TODO Auto-generated method stub
				String barcodeVehicle = request.getParameter("barcodeVehicle");
				
				 Map<Long,SaleReport> map = new HashMap<Long,SaleReport>();
					
				    CustomerOrderDao dao = new CustomerOrderDao();
					List<SaleReport> exp1List = dao.barcodewiseVehicleSale(barcodeVehicle);
					
					return exp1List;
			}
			
			//CA Sale Report Two Date
			public List caSaleReportBetweenTwoDates(HttpServletRequest request, HttpServletResponse response) {
				// TODO Auto-generated method stub
				String pFisDate = request.getParameter("fisDate1");
				String pEndDate = request.getParameter("endDate1");
		        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				
				Date adate = null;
				Date bdate = null;
				try {
				 adate=	format.parse(pFisDate);
				 bdate=	format.parse(pEndDate);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				 Map<Long,SaleReport> map = new HashMap<Long,SaleReport>();
					
				 CustomerOrderDao dao = new CustomerOrderDao();
					List<SaleReport> exp1List = dao.caSaleReportBetweenTwoDates(adate,bdate);
					
					return exp1List;
			}







			//TALLY Sale Report Two Date
			public List tallySaleReportBetweenTwoDates(HttpServletRequest request, HttpServletResponse response) {
				// TODO Auto-generated method stub
				String pFisDate = request.getParameter("fisDatetally");
				String pEndDate = request.getParameter("endDatetally");
		        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				
				Date adate = null;
				Date bdate = null;
				try {
				 adate=	format.parse(pFisDate);
				 bdate=	format.parse(pEndDate);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				 Map<Long,SaleReport> map = new HashMap<Long,SaleReport>();
					
				    CustomerOrderDao dao = new CustomerOrderDao();
					List<SaleReport> exp1List = dao.tallySaleReportBetweenTwoDates(adate,bdate);
					
					return exp1List;
			}


			// other bill
				public CustomerBean getDetailsByProd(HttpServletRequest request,
						HttpServletResponse response) {
					// TODO Auto-generated method stub
					String productId=request.getParameter("productId");
					
					System.out.println(productId+"productName");
					Map<Long,CustomerBean> map = new HashMap<Long,CustomerBean>();
					
					CustomerOrderDao dao = new CustomerOrderDao();
					List<CustomerBean> catList = dao.getAllItemDetails(productId);
					
					CustomerBean cs = null;
					if(catList!= null && catList.size() > 0 )
					{	
						cs = (CustomerBean)catList.get(0); 
					}
					return cs;
				}
				
				//get Data On CustomerBill Using Barcode No
				
				public CustomerBean getProdDetailsById(HttpServletRequest request,
						HttpServletResponse response) {
					
					String productId=request.getParameter("productId1");
					String carNo = request.getParameter("carNo");
					System.out.println(productId+"  - productId");
					Map<Long,CustomerBean> map = new HashMap<Long,CustomerBean>();
					
					CustomerOrderDao dao = new CustomerOrderDao();
					List<CustomerBean> catList = dao.getAllItemDetailGrid(productId,carNo);
					
					CustomerBean cs = null;
					if(catList!= null && catList.size() > 0 )
					{	
						cs = (CustomerBean)catList.get(0); 
					}
					return cs;
				}
				
}
