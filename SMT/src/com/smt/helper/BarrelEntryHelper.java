package com.smt.helper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.smt.bean.BarrelEntryBean;
import com.smt.bean.BillBean;
import com.smt.bean.CustomerBean;
import com.smt.bean.GoodReceiveItemBean;
import com.smt.bean.GoodreciveBillBean;
import com.smt.dao.BarrelEntryDao;
import com.smt.dao.CustomerOrderDao;
import com.smt.dao.GoodReciveDao;
import com.smt.dao.OtherBillDao;
import com.smt.dao.ProductDetailDao;
import com.smt.dao.StockDao;
import com.smt.hibernate.BarrelBillingHibernate;
import com.smt.hibernate.BarrelEntryHibernate;
import com.smt.hibernate.GoodReceive;
import com.smt.hibernate.GoodsReceiveBarrelHibernate;
import com.smt.hibernate.OtherBill;
import com.smt.hibernate.ProductRegister;
import com.smt.hibernate.Stock;
import com.smt.utility.HibernateUtility;

public class BarrelEntryHelper {

	Long barcodeNo;
	Long Txid = 0l;

	public void doProductRegister(HttpServletRequest request, HttpServletResponse response) {

		String itemName = request.getParameter("itemName");
		String catId = request.getParameter("catId");
		String modelName = request.getParameter("modelName");
		String hsnsacno = request.getParameter("hsnsacno");
		String categoryName = request.getParameter("categoryName");
		String NoBarrel = request.getParameter("NoBarrel");
		// String perlitre = request.getParameter("perlitre");
		String TotalBarrel = request.getParameter("TotalBarrel");

		BarrelEntryHibernate pdreg = new BarrelEntryHibernate();

		pdreg.setItemName(itemName);
		pdreg.setCategoryName(categoryName);
		pdreg.setFkCategoryId(Long.parseLong(catId));

		if (!"".equals(modelName)) {
			pdreg.setModelName(modelName);
		} else {
			pdreg.setModelName("N/A");
		}

		pdreg.setVat(0.0);
		pdreg.setHsnsacno(hsnsacno);
		pdreg.setNumberofBarrel(Double.parseDouble(NoBarrel));
		pdreg.setOilperlitre(0.0);
		pdreg.setTotalLitre(Double.parseDouble(TotalBarrel));

		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		Date dateobj = new Date();

		String newDate = df.format(dateobj);
		pdreg.setIsInsertDate(dateobj);

		BarrelEntryDao reg = new BarrelEntryDao();
		reg.doProductRegister(pdreg);

	}

	// to get all Item Name on oil goods receive form
	public List getAllItemName1() {
		BarrelEntryDao dow1 = new BarrelEntryDao();
		return dow1.getAllItemNameOil();
	}

	// Item Name Grid oil barrel

	public BarrelEntryBean getDetailsById1(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		// String itemName = request.getParameter("itemName");
		// String categoryName = request.getParameter("catName");
		// String hsnsacno = request.getParameter("hsnsacno");

		/*
		 * BarrelEntryBean bean = new BarrelEntryBean();
		 * 
		 * bean.setItemName(itemName); bean.setCategoryName(categoryName);
		 * bean.setHsnsacno(hsnsacno); bean.setIgst(0d); //bean.setQuantity(0);
		 * //bean.setBuyPrice(0d); //bean.setSalePrice(0d);
		 * 
		 * return bean;
		 */

		String key = request.getParameter("itemName");

		Map<Long, BarrelEntryBean> map = new HashMap<Long, BarrelEntryBean>();

		BarrelEntryDao dao = new BarrelEntryDao();
		List<BarrelEntryBean> catList = dao.getAllItemDetails1(key);

		BarrelEntryBean cs = null;
		if (catList != null && catList.size() > 0) {
			cs = (BarrelEntryBean) catList.get(0);
		}
		return cs;

	}

	//// register in goods receive oil barrel
	@SuppressWarnings("unused")
	public void regGoodReceiveOil(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		/*
		 * GoodReciveDao dao3=new GoodReciveDao(); List
		 * listtxid=dao3.getSupplierPaymentTxid();
		 * 
		 * if(listtxid.size()<=0) { Txid=1l;
		 * 
		 * } else
		 * 
		 * { for (int i = 0; i <listtxid.size(); i++) {
		 * 
		 * GoodReceive bean=(GoodReceive) listtxid.get(i); Txid=bean.getTxId();
		 * Txid++; } }
		 */

		GoodsReceiveBarrelHibernate gd = new GoodsReceiveBarrelHibernate();

		Integer count = Integer.parseInt(request.getParameter("count"));
		System.out.println("c111111   - " + count);

		for (int i = 0; i < count; i++) {

			/*
			 * HttpSession session3 = request.getSession(); GoodReciveDao data =
			 * new GoodReciveDao(); List stkList = data.getLastBarcodeNo();
			 * 
			 * for (int j = 0; j < stkList.size(); j++) {
			 * 
			 * BarrelEntryBean st = (BarrelEntryBean) stkList.get(j); barcodeNo
			 * = st.getBarcodeNo();
			 * 
			 * barcodeNo++;
			 * 
			 * }
			 */
			String itemName = request.getParameter("itemName" + i);
			gd.setItemName(itemName);

			String catName = request.getParameter("catName" + i);
			gd.setCategoryName(catName);

			// gd.setTxId(Txid);

			String quantity = request.getParameter("quantity" + i);
			gd.setQuantity(Long.parseLong(quantity));
			gd.setOringnalQuantity(Long.parseLong(quantity));

			String buyPrice = request.getParameter("buyPrice" + i);
			gd.setBuyPrice(Double.parseDouble(buyPrice));

			String salePrice = request.getParameter("salePrice" + i);
			gd.setSalePrice(Double.parseDouble(salePrice));

			String discount = request.getParameter("discount" + i);
			gd.setDiscount(Double.parseDouble(discount));
			System.out.println("discount set- " + gd.getDiscount());

			String hsnsacno = request.getParameter("hsnsacno" + i);
			gd.setHsnsacno(hsnsacno);

			String Total = request.getParameter("Total" + i);
			gd.setTotal(Double.parseDouble(Total));

			String billNo = request.getParameter("billNo");
			gd.setBillNo(billNo);

			String contactPerson = request.getParameter("contactPerson");
			gd.setContactPerson(contactPerson);

			String vat = request.getParameter("vat" + i);
			gd.setVat(Double.parseDouble(vat));

			String igst = request.getParameter("igst" + i);
			gd.setIgst(Double.parseDouble(igst));

			String gstamt = request.getParameter("gstamt" + i);
			gd.setTaxAmount(Double.parseDouble(gstamt));

			String NumberofBarrel = request.getParameter("NumberofBarrel" + i);
			gd.setNumberofBarrel(Double.parseDouble(NumberofBarrel));

			String TotalLitre = request.getParameter("TotalLitre" + i);
			gd.setTotalLitre(Double.parseDouble(TotalLitre));

			String actualprice = request.getParameter("actualprice" + i);
			System.out.println("actual price - " + actualprice);

			String extraDiscount = request.getParameter("extraDiscount");
			gd.setExtraDiscount(Double.parseDouble(extraDiscount));

			String expence = request.getParameter("expence");
			gd.setExpence(Double.parseDouble(expence));

			String txPerexpence = request.getParameter("txPerexpence");
			gd.setTxPerexpence(Double.parseDouble(txPerexpence));

			String finalExpenses = request.getParameter("finalExpenses");
			gd.setFinalExpenses(Double.parseDouble(finalExpenses));

			gd.setPaymentDone("y");

			String resolution = request.getParameter("resolution");
			gd.setGrossTotal(Double.parseDouble(resolution));

			String supplierId = request.getParameter("supplierId");
			gd.setSupplierName(Long.parseLong(supplierId));

			String pDate = request.getParameter("pDate");
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

			Date adate = null;
			try {
				adate = format.parse(pDate);
			}

			catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			gd.setDate(adate);

			// session3.setAttribute("barcodeNo", barcodeNo);

			/*
			 * if (barcodeNo == null) { gd.setBarcodeNo(1000l); } else {
			 * gd.setBarcodeNo(barcodeNo); }
			 */

			BarrelEntryDao dao = new BarrelEntryDao();
			dao.regGoodReceive(gd);

			////////// stock code//////////
			StockDao dao1 = new StockDao();
			List stkList2 = dao1.getAllStockEntry();
			System.out.println("Stock++++++" + stkList2);

			int quant = Integer.parseInt(quantity);
			//double TotalLitre1 = Double.parseDouble(TotalLitre);

			/* If Stock Is Empty */
			if (stkList2.size() == 0) {

				Stock newEntry = new Stock();

				newEntry.setItemName(itemName);
				newEntry.setQuantity(Long.parseLong(quantity));
				newEntry.setTotalLitre(Double.parseDouble(TotalLitre));
				newEntry.setCatName(catName);

				StockDao dao2 = new StockDao();
				dao2.registerStock(newEntry);

			} else/* To Update Stock Table If It is Not Empty */
			{

				for (int j = 0; j < stkList2.size(); j++) {

					Stock st = (Stock) stkList2.get(j);
					String ItemId = st.getItemName();
					String cat = st.getCatName();
					Long PkItemId = st.getPkStockId();
					Double totalLitre = st.getTotalLitre();
					

					/* If ItemName Is Already Exists In Stock Table */
					if (ItemId.equals(itemName) && cat.equals(catName)) {
						Long qunty = st.getQuantity();
                        double totalLitreStock=st.getTotalLitre();
                        double updateTotalLitre=(double) (totalLitreStock + Long.parseLong(TotalLitre));
						Long updatequnty = (long) (qunty + Long.parseLong(quantity));

						HibernateUtility hbu = HibernateUtility.getInstance();
						Session session = hbu.getHibernateSession();
						Transaction transaction = session.beginTransaction();

						DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
						Date date = new Date();

						Stock updateStock = (Stock) session.get(Stock.class, new Long(PkItemId));
						updateStock.setUpdateDate(date);
						updateStock.setQuantity(updatequnty);
						updateStock.setTotalLitre(updateTotalLitre);

						session.saveOrUpdate(updateStock);
						transaction.commit();
						break;
					} else {
						/* ItemName is Not Exists */
						if (j + 1 == stkList2.size()) {

							Stock newEntry = new Stock();

							newEntry.setItemName(itemName);
							newEntry.setQuantity(Long.parseLong(quantity));
							newEntry.setCatName(catName);
							newEntry.setTotalLitre(Double.parseDouble(TotalLitre));

							StockDao dao2 = new StockDao();
							dao2.registerStock(newEntry);

						}
					}

				}

			}

		}
	}
	///////////// end//////////

	////////// Billing oil module////////grid///////

	public BarrelEntryBean getProductInGridBillingOil(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String productId = request.getParameter("productId");

		System.out.println(productId + "productName");
		Map<Long, BarrelEntryBean> map = new HashMap<Long, BarrelEntryBean>();

		BarrelEntryDao dao = new BarrelEntryDao();
		List<BarrelEntryBean> catList = dao.getProductInGridBillingOil(productId);

		BarrelEntryBean cs = null;
		if (catList != null && catList.size() > 0) {
			cs = (BarrelEntryBean) catList.get(0);
		}
		return cs;
	}

	/////////////////// register oil in database////////////

	Long BillNo = 1l;

	public void registerOtherBill(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		HttpSession session3 = request.getSession();
		/*
		 * OtherBillDao data = new OtherBillDao(); List stkList =
		 * data.getLastBillNo();
		 */
		/*
		 * for (int i = 0; i < stkList.size(); i++) {
		 * 
		 * BillBean st = (BillBean) stkList.get(i); BillNo = st.getBillNo();
		 * 
		 * BillNo++;
		 * 
		 * }
		 */

		BarrelBillingHibernate cust = new BarrelBillingHibernate();
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

			// cust.setSalePrice(Double.parseDouble(salePrice));

			/*
			 * String barcodeNo = request.getParameter("barcodeNo" + i);
			 * System.out.println("unitinMl" + barcodeNo);
			 * cust.setBarcodeNo(Long.parseLong(barcodeNo));
			 * 
			 * 
			 */
			
			String NumberofBarrel = request.getParameter("NumberofBarrel" + i);
			cust.setNumberofBarrel(Double.parseDouble(NumberofBarrel));

			String TotalLitre = request.getParameter("TotalLitre" + i);
			cust.setTotalLitre(Double.parseDouble(TotalLitre));

			String hsnSacNo = request.getParameter("hsnSacNo" + i);
			cust.setHsnSacNo(hsnSacNo);

			String discountGrid = request.getParameter("discountGrid" + i);
			cust.setDiscountGrid(Double.parseDouble(discountGrid));

			String discountAmt = request.getParameter("discountAmt" + i);
			cust.setDiscountAmt(Double.parseDouble(discountAmt));

			String vat = request.getParameter("vat" + i);
			String igst = request.getParameter("igst" + i);

			if (vat.equals("0")) {
				cust.setVat(Double.parseDouble(igst));
				double igstAmt = Double.parseDouble(salePrice) - (Double.parseDouble(salePrice) * (100 / (100 + Double.parseDouble(igst))));
				double netPrice = Double.parseDouble(salePrice) - igstAmt;
				cust.setSalePrice(netPrice);
			} else {
				cust.setVat(Double.parseDouble(vat));
				double gstAmt = Double.parseDouble(salePrice) - (Double.parseDouble(salePrice) * (100 / (100 + Double.parseDouble(vat))));
				double netPrice = Double.parseDouble(salePrice) - gstAmt;
				cust.setSalePrice(netPrice);
			}
			cust.setIgst(0d);

			String taxAmount = request.getParameter("taxAmount" + i);
			cust.setTaxAmount(Double.parseDouble(taxAmount));

			String totalAmount = request.getParameter("totalAmount");
			cust.setTotalAmt(Double.parseDouble(totalAmount));

			String discount = request.getParameter("discount");
			double disAmt = Double.parseDouble(discount) / count;
			cust.setDiscount(disAmt);

			cust.setCarNo("NA");
			cust.setContactNo(000l);
			cust.setOwnerName("NA");

			
			String grossTotal = request.getParameter("grossTotal");
			cust.setGrossamt(Double.parseDouble(grossTotal));

			String total = request.getParameter("total" + i);
			cust.setTotalperItem(Double.parseDouble(total));

			DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			Date dateobj = new Date();
			System.out.println(df.format(dateobj));
			String newDate = df.format(dateobj);
			cust.setCurrent_date(dateobj);

			session3.setAttribute("BillNo", BillNo);
			if (BillNo == null) {
				cust.setBillNo(1l);
			} else {
				cust.setBillNo(BillNo);
			}

			BarrelEntryDao dao = new BarrelEntryDao();
			dao.registerBill(cust);

			//stock code
			  Long item_id = Long.parseLong(request.getParameter("item_id"+i));
			  System.out.println("item_id" +item_id); 
			  GoodReciveDao good = new GoodReciveDao(); 
			  good.updateQuantity(item_id,quantity);
			  
			  
			  StockDao dao1 = new StockDao(); 
			  List stkList2 =dao1.getAllStockEntry();
			  
			  for(int j=0;j<stkList2.size();j++){
			  
			  Stock st = (Stock)stkList2.get(j); 
			  String ItemId =st.getItemName(); 
			  String cat = st.getCatName();
			  //Double TotalLitre1=st.getTotalLitre();
			   
			  //If ItemName Is Already Exists In Stock Table
			  if(ItemId.equals(itemName) && cat.equals(categoryName))
			  { 
				  Long PkItemId = st.getPkStockId(); 
				  //Long qunty = st.getQuantity();
				  Double totalLitreStock=st.getTotalLitre();
			  
				  Double updatequnty = (Double) (totalLitreStock - Long.parseLong(quantity));
			  //double updateTotalLitre=(double) (totalLitreStock - Long.parseLong(TotalLitre));
			  
			  HibernateUtility hbu = HibernateUtility.getInstance(); 
			  Session session = hbu.getHibernateSession(); Transaction transaction =
			  session.beginTransaction();
			  
			  
			  Date date = new Date();
			  
			  Stock updateStock = (Stock) session.get(Stock.class, new Long(PkItemId)); 
			  updateStock.setUpdateDate(date); 
			  //updateStock.setQuantity(updatequnty);
			  updateStock.setTotalLitre(updatequnty);
			  
			  session.saveOrUpdate(updateStock); 
			  transaction.commit();
			
			  }
			  
			  }
			 
		}

	}

}
