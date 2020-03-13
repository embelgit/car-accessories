package com.smt.helper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.smt.dao.SaleReturnDao;
import com.smt.dao.StockDao;
import com.smt.hibernate.SaleReturn;
import com.smt.hibernate.Stock;
import com.smt.utility.HibernateUtility;

public class SaleReturnHelper {

	public void returnSale(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		SaleReturn cust = new SaleReturn();
		
		Integer count = Integer.parseInt(request.getParameter("count"));
		System.out.println("c111111"+count);

		for(int i=0;i<count;i++){

			String itemName = request.getParameter("itemName"+i);
			cust.setItemName(itemName);
			
			String categoryName = request.getParameter("categoryName"+i);
			cust.setCategoryName(categoryName);

			String quantity= request.getParameter("quantity"+i);
			String editQuantity= request.getParameter("editQuantity"+i);
			Long afterReturnQuantity = Long.parseLong(quantity) - Long.parseLong(editQuantity);
			System.out.println("quantity"+quantity);
            cust.setQuantity(afterReturnQuantity);
            cust.setAfterQuantity(afterReturnQuantity);
            cust.setEditQuantity(Long.parseLong(editQuantity));
            
            String salePrice= request.getParameter("salePrice"+i);
			
            cust.setSalePrice(Double.parseDouble(salePrice));
            
            String barcodeNo= request.getParameter("barcodeNo"+i);
			System.out.println("unitinMl"+barcodeNo);
            cust.setBarcodeNo(Long.parseLong(barcodeNo));
            
			
			String contactNo = request.getParameter("contactNo"+i);
			cust.setContactNo(Long.parseLong(contactNo));
			
			
			String carNo = request.getParameter("carNo");
			cust.setCarNo(carNo);
			
			String totalAmt = request.getParameter("totalAmt"+i);
			cust.setTotalAmt(Double.parseDouble(totalAmt));
			
			String discount = request.getParameter("discount"+i);
			cust.setDiscount(Double.parseDouble(discount));
			
			String grossamt = request.getParameter("grossamt"+i);
			cust.setGrossamt(Double.parseDouble(grossamt));
			
			String Date = request.getParameter("Date"+i);
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			
			Date adate = null;
			try {
			 adate=	format.parse(Date);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			cust.setCurrent_date(adate);
			
			DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			Date dateobj = new Date();
			System.out.println(df.format(dateobj));
			String newDate = df.format(dateobj);
			cust.setDate(newDate);
			
			String billNo = request.getParameter("billNo");
		    cust.setBillNo(Long.parseLong(billNo));
				
			
		    SaleReturnDao dao=new SaleReturnDao();
			dao.registerSaleReturn(cust);
			
			
			//update sellbill quantity minus 
			Long pkBillId = Long.parseLong(request.getParameter("pkBillId"+i));
			//System.out.println("item_id" +item_id);
			SaleReturnDao good = new SaleReturnDao();
			good.updateQuantity(pkBillId,editQuantity,totalAmt);
			
			//update Barcodewise quantity in good receive plus 
			Long barcodeNo1 = Long.parseLong(request.getParameter("barcodeNo"+i));
			//System.out.println("item_id" +item_id);
			SaleReturnDao good1 = new SaleReturnDao();
			good1.updateBarcodeQuantity(barcodeNo1,editQuantity);
			
			
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
	        		
	        		 Long updatequnty = (long) (qunty + Long.parseLong(editQuantity));
	        				 
	        		
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
	}

}
