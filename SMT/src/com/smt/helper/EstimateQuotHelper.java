package com.smt.helper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.smt.bean.BillBean;
import com.smt.dao.CreditCustBillDao;
import com.smt.dao.CustomerOrderDao;
import com.smt.dao.CustomerPaymentDao;
import com.smt.dao.EstimateQuotDao;
import com.smt.dao.GoodReciveDao;
import com.smt.dao.StockDao;
import com.smt.hibernate.CreditCustomerBill;
import com.smt.hibernate.EstimateQuotationBill;
import com.smt.hibernate.Stock;
import com.smt.utility.HibernateUtility;

public class EstimateQuotHelper {

	Long BillNo = 1l;

	public void regCreditCustomerBillGrid(HttpServletRequest request, HttpServletResponse response) {

		System.out.println("In estimate quotation Bill");
		HttpSession session3 = request.getSession();
		EstimateQuotDao data = new EstimateQuotDao();
		List stkList = data.getLastBillNo();

		for (int i = 0; i < stkList.size(); i++)

		{

			BillBean st = (BillBean) stkList.get(i);
			BillNo = st.getBillNo();

			BillNo++;

		}

		EstimateQuotationBill cust = new EstimateQuotationBill();
		Integer count = Integer.parseInt(request.getParameter("count"));
		System.out.println("c111111" + count);

		for (int i = 0; i < count; i++) {

			String itemName = request.getParameter("itemName" + i);
			cust.setItemName(itemName);

			String categoryName = request.getParameter("categoryName" + i);
			cust.setCategoryName(categoryName);

			String quantity = request.getParameter("quantity" + i);
			System.out.println("quantity" + quantity);
			cust.setQuantity(Long.parseLong(quantity));

			String salePrice = request.getParameter("salePrice" + i);
			cust.setSalePrice(Double.parseDouble(salePrice));

			String barcodeNo = request.getParameter("barcodeNo" + i);
			System.out.println("unitinMl" + barcodeNo);
			cust.setBarcodeNo(Long.parseLong(barcodeNo));

			String hsnSacNo = request.getParameter("hsnSacNo" + i);
			cust.setHsnSacNo(hsnSacNo);

			String vat = request.getParameter("vat" + i);
			cust.setVat(Double.parseDouble(vat));

			String igst = request.getParameter("igst" + i);
			cust.setIgst(Double.parseDouble(igst));

			String discountGrid = request.getParameter("discountGrid" + i);
			cust.setDiscountGrid(Double.parseDouble(discountGrid));

			String discountAmt = request.getParameter("discountAmt" + i);
			cust.setDiscountAmt(Double.parseDouble(discountAmt));

			String taxAmount = request.getParameter("taxAmount" + i);
			cust.setTaxAmount(Double.parseDouble(taxAmount));

			String totalAmount = request.getParameter("totalAmount");
			cust.setTotalAmt(Double.parseDouble(totalAmount));

			/*
			 * String discount = request.getParameter("discount"); double disAmt
			 * = Double.parseDouble(discount) / count; cust.setDiscount(disAmt);
			 */
			String fkRootCustId = request.getParameter("fkRootCustId");
			cust.setFkEstimCustId(Long.parseLong(fkRootCustId));

			String grossTotal = request.getParameter("grossTotal");
			cust.setGrossamt(Double.parseDouble(grossTotal));

			String paidAmt = request.getParameter("paidAmt");

			/*
			 * if (paidAmt.equals(grossTotal)) { cust.setPaymentDone("n"); }
			 * else { cust.setPaymentDone("y"); }
			 */
			String total = request.getParameter("total" + i);
			cust.setTotalperItem(Double.parseDouble(total));

			DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			Date dateobj = new Date();
			System.out.println(df.format(dateobj));
			String newDate = df.format(dateobj);
			cust.setCurrent_date(dateobj);

			String input = request.getParameter("input1");
			String gstTinNo = request.getParameter("gstTinNo");

			session3.setAttribute("creditCustomerName", input);
			session3.setAttribute("gstTinNo", gstTinNo);
			session3.setAttribute("BillNo", BillNo);
			if (BillNo == null) {
				cust.setBillNo(1l);
			} else {
				cust.setBillNo(BillNo);
			}

			EstimateQuotDao dao = new EstimateQuotDao();
			//dao.regCreditCustomerBill(cust);
			
		}
		
		//SERVICING GRID
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
			 
				
			
			  EstimateQuotDao dao = new EstimateQuotDao();
			  dao.regCreditCustomerBill(cust);
		
		}
		
	}

}
