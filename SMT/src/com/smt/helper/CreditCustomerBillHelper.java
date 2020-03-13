package com.smt.helper;

import java.text.DateFormat;
import java.text.ParseException;
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
import com.smt.bean.SaleReport;
import com.smt.dao.CreditCustBillDao;
import com.smt.dao.CustomerPaymentDao;
import com.smt.dao.GoodReciveDao;
import com.smt.dao.OtherBillDao;
import com.smt.dao.StockDao;
import com.smt.hibernate.CreditCustomerBill;
import com.smt.hibernate.Stock;
import com.smt.utility.HibernateUtility;

public class CreditCustomerBillHelper {

	Long BillNo = 1l;
	public void regCreditCustomerBill(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		HttpSession session3 = request.getSession();
		CreditCustBillDao data = new CreditCustBillDao();
		List stkList  = data.getLastBillNo();
		
		for(int i=0;i<stkList.size();i++){
			
			BillBean st = (BillBean)stkList.get(i);
			BillNo = st.getBillNo();
			
			BillNo++;
			
		}
		
		CreditCustomerBill cust = new CreditCustomerBill();
		Integer count = Integer.parseInt(request.getParameter("count"));
		System.out.println("c111111"+count);

		for(int i=0;i<count;i++){

			String itemName = request.getParameter("itemName"+i);
			cust.setItemName(itemName);
			
			String categoryName = request.getParameter("categoryName"+i);
			cust.setCategoryName(categoryName);

			String quantity= request.getParameter("quantity"+i);
			System.out.println("quantity"+quantity);
            cust.setQuantity(Long.parseLong(quantity));
            
            String salePrice= request.getParameter("salePrice"+i);
			
            cust.setSalePrice(Double.parseDouble(salePrice));
            
            String barcodeNo= request.getParameter("barcodeNo"+i);
			System.out.println("unitinMl"+barcodeNo);
            cust.setBarcodeNo(Long.parseLong(barcodeNo));
            
            String hsnSacNo = request.getParameter("hsnSacNo"+i);
			cust.setHsnSacNo(hsnSacNo);
			
			String vat = request.getParameter("vat"+i);
			cust.setVat(Double.parseDouble(vat));
			
			
			String igst = request.getParameter("igst"+i);
			cust.setIgst(Double.parseDouble(igst));
			
			String discountGrid = request.getParameter("discountGrid"+i);
			cust.setDiscountGrid(Double.parseDouble(discountGrid));
			
			String discountAmt = request.getParameter("discountAmt"+i);
			cust.setDiscountAmt(Double.parseDouble(discountAmt));
			
			
			String taxAmount = request.getParameter("taxAmount"+i);
			cust.setTaxAmount(Double.parseDouble(taxAmount));
			
			String totalAmount = request.getParameter("totalAmount");
			cust.setTotalAmt(Double.parseDouble(totalAmount));
			
			String discount = request.getParameter("discount");
			double disAmt = Double.parseDouble(discount) / count;
			cust.setDiscount(disAmt);
			
			String fkRootCustId = request.getParameter("fkRootCustId");
			cust.setFkRootCustId(Long.parseLong(fkRootCustId));
			
			
			/*String custName = request.getParameter("custName");
			cust.setCustName(custName);
			
			String custContactNo = request.getParameter("custContactNo");
			cust.setMono(Long.parseLong(custContactNo));
			*/
			String grossTotal = request.getParameter("grossTotal");
			cust.setGrossamt(Double.parseDouble(grossTotal));
			
			String paidAmt = request.getParameter("paidAmt");
			
			  if(paidAmt.equals(grossTotal)){ cust.setPaymentDone("n"); } else{
			  cust.setPaymentDone("y"); }
			 
			
			String total = request.getParameter("total"+i);
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
			if(BillNo == null){
				cust.setBillNo(1l);
				}
				else
				{
					cust.setBillNo(BillNo);	
				}
			
			
			CreditCustBillDao dao=new CreditCustBillDao();
			dao.regCreditCustomerBill(cust);
			if(i == 0){
			CustomerPaymentDao pay=new CustomerPaymentDao();
			pay.regCreditCustomerPayment(BillNo,grossTotal,paidAmt,fkRootCustId);
			}
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
	}
	
	// copy for credit bill customer
	public void BillCOPYForCreditBill(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String billNo = request.getParameter("billNo");
		String custName = request.getParameter("custName");
		String gstTinNo = request.getParameter("gstTinNo");
		System.out.println("----------------Bill No before session create::"+billNo);
		HttpSession session3 = request.getSession();
		Long billNo2 = Long.parseLong(billNo);
		session3.setAttribute("BillNo", billNo2);
		session3.setAttribute("creditCustomerName", custName);
		session3.setAttribute("gstTinNo", gstTinNo);
	}

	//Single Date Credit Sale Report 
	public List creditSingleDate(HttpServletRequest request, HttpServletResponse response) {
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
			
		    CreditCustBillDao dao = new CreditCustBillDao();
			List<SaleReport> exp1List = dao.creditSingleDate(adate);
			
			return exp1List;
	}

	//Two Date Credit Sale Report 
	public List creditTwoDate(HttpServletRequest request, HttpServletResponse response) {
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
			
		    CreditCustBillDao dao = new CreditCustBillDao();
			List<SaleReport> exp1List = dao.creditTwoDate(adate,bdate);
			
			return exp1List;
	}

	//Category Wise Credit Sale Report 
	public List creditSaleWiseCustomer(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String cscatId = request.getParameter("cscatId");
		
		 Map<Long,SaleReport> map = new HashMap<Long,SaleReport>();
			
		    CreditCustBillDao dao = new CreditCustBillDao();
			List<SaleReport> exp1List = dao.creditSaleWiseCustomer(cscatId);
			
			return exp1List;
	}

	//Bill No Wise Credit Sale Report
	public List billnowiseCreditsell(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		long csBillNocust = Long.parseLong(request.getParameter("csBillNocust"));
		
		 Map<Long,SaleReport> map = new HashMap<Long,SaleReport>();
			
		    CreditCustBillDao dao = new CreditCustBillDao();
			List<SaleReport> exp1List = dao.billnowiseCreditsell(csBillNocust);
			
			return exp1List;
	}

	//Barcode No Wise Credit Sale Report
	public List barcodenowiseCredit(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		long barcodeCredit = Long.parseLong(request.getParameter("barcodeCredit"));
		
		 Map<Long,SaleReport> map = new HashMap<Long,SaleReport>();
			
		    CreditCustBillDao dao = new CreditCustBillDao();
			List<SaleReport> exp1List = dao.barcodenowiseCredit(barcodeCredit);
			
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
			
		    CreditCustBillDao dao = new CreditCustBillDao();
			List<SaleReport> exp1List = dao.tallySaleReportBetweenTwoDates(adate,bdate);
			
			return exp1List;
	}

}
