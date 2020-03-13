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
import com.smt.dao.CustomerOrderDao;
import com.smt.dao.GoodReciveDao;
import com.smt.dao.OtherBillDao;
import com.smt.dao.StockDao;
import com.smt.hibernate.OtherBill;
import com.smt.hibernate.Stock;
import com.smt.utility.HibernateUtility;

public class OtherBillHelper {

	Long BillNo = 1l;
	public void registerOtherBill(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		HttpSession session3 = request.getSession();
		OtherBillDao data = new OtherBillDao();
		List stkList  = data.getLastBillNo();
		
		for(int i=0;i<stkList.size();i++){
			
			BillBean st = (BillBean)stkList.get(i);
			BillNo = st.getBillNo();
			
			BillNo++;
			
		}
		
		OtherBill cust = new OtherBill();
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
			
            //cust.setSalePrice(Double.parseDouble(salePrice));
            
            String barcodeNo= request.getParameter("barcodeNo"+i);
			System.out.println("unitinMl"+barcodeNo);
            cust.setBarcodeNo(Long.parseLong(barcodeNo));
            
            String hsnSacNo = request.getParameter("hsnSacNo"+i);
			cust.setHsnSacNo(hsnSacNo);
			
			String discountGrid = request.getParameter("discountGrid"+i);
			cust.setDiscountGrid(Double.parseDouble(discountGrid));
			
			String discountAmt = request.getParameter("discountAmt"+i);
			cust.setDiscountAmt(Double.parseDouble(discountAmt));
			
			
			String vat = request.getParameter("vat"+i);
			String igst = request.getParameter("igst"+i);
			
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
			
			String totalAmount = request.getParameter("totalAmount");
			cust.setTotalAmt(Double.parseDouble(totalAmount));
			
			String discount = request.getParameter("discount");
			double disAmt = Double.parseDouble(discount) / count;
			cust.setDiscount(disAmt);
			
		
			cust.setCarNo("NA");
			cust.setContactNo(000l);
			cust.setOwnerName("NA");
			
			/*String custName = request.getParameter("custName");
			cust.setCustName(custName);
			
			String custContactNo = request.getParameter("custContactNo");
			cust.setMono(Long.parseLong(custContactNo));
			*/
			String grossTotal = request.getParameter("grossTotal");
			cust.setGrossamt(Double.parseDouble(grossTotal));
			
			String total = request.getParameter("total"+i);
			cust.setTotalperItem(Double.parseDouble(total));
			
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
			
			
			OtherBillDao dao=new OtherBillDao();
			dao.registerBill(cust);
			
			
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
	
	// pdf copy of other bill
	public void OtherBillCOPY(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String billNo = request.getParameter("billNo");
		System.out.println("----------------Bill No before session create::"+billNo);
		HttpSession session3 = request.getSession();
		Long billNo2 = Long.parseLong(billNo);
		session3.setAttribute("OtherBillNo", billNo2);
	}

	// get single date Miscellaneos customer Sale
	public List miscellaneousSingleDate(HttpServletRequest request, HttpServletResponse response) {
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
			
		    OtherBillDao dao = new OtherBillDao();
			List<SaleReport> exp1List = dao.miscellaneousSingleDate(adate);
			
			return exp1List;
	}

	// get Two date Miscellaneos customer Sale
	public List miscellaneousTwoDate(HttpServletRequest request, HttpServletResponse response) {
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
			
		    OtherBillDao dao = new OtherBillDao();
			List<SaleReport> exp1List = dao.miscellaneousTwoDate(adate,bdate);
			
			return exp1List;
	}
	// get category wise Miscellaneos customer Sale
	public List miscellaneousSaleWiseCustomer(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String mscatId = request.getParameter("mscatId");
		
		 Map<Long,SaleReport> map = new HashMap<Long,SaleReport>();
			
		    OtherBillDao dao = new OtherBillDao();
			List<SaleReport> exp1List = dao.miscellaneousSaleWiseCustomer(mscatId);
			
			return exp1List;
	}

	// get Bill no wise Miscellaneos customer Sale
	public List billnowiseMiscellaneoussell(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		long msBillNocust = Long.parseLong(request.getParameter("msBillNocust"));
		
		 Map<Long,SaleReport> map = new HashMap<Long,SaleReport>();
			
		    OtherBillDao dao = new OtherBillDao();
			List<SaleReport> exp1List = dao.billnowiseMiscellaneoussell(msBillNocust);
			
			return exp1List;
	}
	
	
	//Barcode No Wise Miscellaneos Sale Report 
	public List barcodenowiseMiscellaneoussell(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		long barcodeMiscellaneous = Long.parseLong(request.getParameter("barcodeMiscellaneous"));
		
		 Map<Long,SaleReport> map = new HashMap<Long,SaleReport>();
			
		    OtherBillDao dao = new OtherBillDao();
			List<SaleReport> exp1List = dao.barcodenowiseMiscellaneoussell(barcodeMiscellaneous);
			
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
			
		    OtherBillDao dao = new OtherBillDao();
			List<SaleReport> exp1List = dao.tallySaleReportBetweenTwoDates(adate,bdate);
			
			return exp1List;
		}

}
