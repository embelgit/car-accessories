package com.smt.helper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.smt.bean.BillBean;
import com.smt.bean.ServiceBean;
import com.smt.dao.CarEntryDao;
import com.smt.dao.CategoryDao;
import com.smt.dao.CustomerOrderDao;
import com.smt.dao.GoodReciveDao;
import com.smt.dao.ServiceDao;
import com.smt.dao.StockDao;
import com.smt.hibernate.Category;
import com.smt.hibernate.CustomerBill;
import com.smt.hibernate.ServiceDetail;
import com.smt.hibernate.Stock;
import com.smt.utility.HibernateUtility;

public class ServiceHelper {
	

	// other bill
		public ServiceBean getDetailsByProd1(HttpServletRequest request,HttpServletResponse response) {
			// TODO Auto-generated method stub
			String productIdService=request.getParameter("productIdService");
			
			System.out.println(productIdService+"Service Name");
			Map<Long,ServiceBean> map = new HashMap<Long,ServiceBean>();
			
			ServiceDao dao = new ServiceDao();
			List<ServiceBean> catList = dao.getAllItemDetails1(productIdService);
			
			ServiceBean sb = null;
			if(catList!= null && catList.size() > 0 )
			{	
				 sb = (ServiceBean)catList.get(0); 
			}
			return  sb;
		}
		
		
	////////////////////////////////
		
		// car register Bill 
				public void registerServiceBill(HttpServletRequest request,
						HttpServletResponse response) {
					
					
					CustomerBill cust = new CustomerBill();
					
					Integer count = Integer.parseInt(request.getParameter("count"));
					System.out.println("c111111"+count);

					for(int i=0;i<count;i++)
					
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
						
						ServiceDao dao=new ServiceDao();
						dao.registerBill(cust);
						
						
						
				       
					}
				    
					
				}
				

}
