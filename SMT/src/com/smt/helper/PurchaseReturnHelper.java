package com.smt.helper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.smt.bean.BillBean;
import com.smt.dao.GoodReciveDao;
import com.smt.dao.PurchaseReturnDao;
import com.smt.dao.StockDao;
import com.smt.hibernate.GoodReceive;
import com.smt.hibernate.PurchaseReturn;
import com.smt.hibernate.Stock;
import com.smt.utility.HibernateUtility;

public class PurchaseReturnHelper {

	public void returngoodsReceipt(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		PurchaseReturn gd = new PurchaseReturn();
		Integer count = Integer.parseInt(request.getParameter("count"));
		System.out.println("c111111"+count);

		for(int i=0;i<count;i++){
			
			String itemName = request.getParameter("itemName"+i);
			gd.setItemName(itemName);
			
			String catName = request.getParameter("catName"+i);
			gd.setCatName(catName);

			String quantity= request.getParameter("quantity"+i);
			
			String availquantity= request.getParameter("availquantity"+i);
			Long afterReturnQuantity = Long.parseLong(quantity) - Long.parseLong(availquantity);
			gd.setQuantity(afterReturnQuantity);
			
			gd.setOringnalQuantity(Long.parseLong(availquantity));
			
            String buyPrice= request.getParameter("buyPrice"+i);
			gd.setBuyPrice(Double.parseDouble(buyPrice));
			
			String vat = request.getParameter("vat"+i);
			gd.setVat(Double.parseDouble(vat));
            
          
            String total = request.getParameter("total"+i);
            gd.setTotal(Double.parseDouble(total));
            
			String contactPerson = request.getParameter("contactPerson"+i);
			gd.setContactPerson(contactPerson);
			
			String barcodeNo = request.getParameter("barcodeNo"+i);
			gd.setBarcodeNo(Long.parseLong(barcodeNo));
			
			String ondate = request.getParameter("ondate"+i);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			
			Date adate = null;
			try {
			 adate=	format.parse(ondate);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			gd.setDate(adate);
			
			DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
			Date dateobj = new Date();
			System.out.println(df1.format(dateobj));
			String newDate = df1.format(dateobj);
			gd.setOndate(newDate);
		
			String billNo = request.getParameter("billNo");
			gd.setBillNo(Long.parseLong(billNo));
			
			String supplierId = request.getParameter("supplierId");
			gd.setSupplierName(supplierId);
			
			
			String totalAmount = request.getParameter("totalAmount");
			gd.setGrossTotal(Double.parseDouble(totalAmount));
			
		    PurchaseReturnDao dao = new PurchaseReturnDao();
			dao.regGoodReceive(gd);
			
			Long PkGoodRecId = Long.parseLong(request.getParameter("PkGoodRecId"+i));
			System.out.println("item_id" +PkGoodRecId);
			PurchaseReturnDao good = new PurchaseReturnDao();
			good.updateQuantity(PkGoodRecId,availquantity,total);
			
			StockDao dao1 = new StockDao();
	        List stkList2  = dao1.getAllStockEntry();
	        
	        
	        	
	        for(int j=0;j<stkList2.size();j++){
	        	
	        	Stock st = (Stock)stkList2.get(j);
	        	String ItemId = st.getItemName();
	        	String cat = st.getCatName();
	        	Long PkItemId = st.getPkStockId();
	        	
	        	/*If ItemName Is Already Exists In Stock Table */ 
	        	if(ItemId.equals(itemName) && cat.equals(catName)){
	        		 Long qunty = st.getQuantity();
	        		
	        		 Long updatequnty = (long) (qunty - Long.parseLong(availquantity));
	        				 
	        		
	        		 HibernateUtility hbu = HibernateUtility.getInstance();
	        		 Session session = hbu.getHibernateSession();
	        		 Transaction transaction = session.beginTransaction();
	        		 
	        		 DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
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
