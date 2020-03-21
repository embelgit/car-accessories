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

import com.smt.bean.BarcodeReportBean;
import com.smt.bean.GoodreciveBillBean;
import com.smt.bean.PreviousGoodReceive;
import com.smt.bean.PurchaseReport;
import com.smt.bean.TallyPurchaseReport;
import com.smt.dao.GoodReciveDao;
import com.smt.dao.StockDao;
import com.smt.hibernate.GoodReceive;
import com.smt.hibernate.Stock;
import com.smt.utility.HibernateUtility;

public class GoodReceiveHelper {

	Long barcodeNo;
	Long Txid=0l;

	public void regGoodReceive(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		 GoodReciveDao dao3=new GoodReciveDao(); 
		  List listtxid=dao3.getSupplierPaymentTxid();
		  
		  if(listtxid.size()<=0) 
		  { 
			  Txid=1l;
		  
		  } 
		  else
		  
		  { 
			  for (int i = 0; i <listtxid.size(); i++) 
			  {
		  
				  GoodReceive bean=(GoodReceive) listtxid.get(i);
		          Txid=bean.getTxId(); 
		          Txid++; 
		  } 
			  }
		 

		GoodReceive gd = new GoodReceive();

		Integer count = Integer.parseInt(request.getParameter("count"));
		System.out.println("c111111   - " + count);

		for (int i = 0; i < count; i++) {

			HttpSession session3 = request.getSession();
			GoodReciveDao data = new GoodReciveDao();
			List stkList = data.getLastBarcodeNo();

			for (int j = 0; j < stkList.size(); j++) {

				GoodreciveBillBean st = (GoodreciveBillBean) stkList.get(j);
				barcodeNo = st.getBarcodeNo();

				barcodeNo++;

			}

			String itemName = request.getParameter("itemName" + i);
			gd.setItemName(itemName);

			String catName = request.getParameter("catName" + i);
			gd.setCatName(catName);

			gd.setTxId(Txid);
			String quantity = request.getParameter("quantity" + i);
			gd.setQuantity(Long.parseLong(quantity));
			gd.setOringnalQuantity(Long.parseLong(quantity));

			String buyPrice = request.getParameter("buyPrice" + i);
			gd.setBuyPrice(Double.parseDouble(buyPrice));

			String salePrice = request.getParameter("salePrice" + i);
			gd.setSalePrice(Double.parseDouble(salePrice));
			
			String discount = request.getParameter("discount" + i);
			gd.setDiscount(Double.parseDouble(discount));
			System.out.println("discount set- "+gd.getDiscount());
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
			
			String actualprice = request.getParameter("actualprice" + i);
			System.out.println("actual price - "+actualprice);
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

			double discontTotal = Double.parseDouble(Total);
			double data1 = Double.parseDouble(quantity);
			
			double discontValue = (discontTotal/data1); 
			
			long data5 = (long)discontValue;
			System.out.println("print BuyPrice ::  "+data5);
			String data6 = String.valueOf(data5);
			String xyz = "";
			String adc = "";
			String[] Shreemant;
			if(actualprice.equals("0")){
				Shreemant = data6.split("");	
			}else{
			    Shreemant = actualprice.split("");
			}
			for(int a = 0; a<Shreemant.length; a++){
				System.out.println("shreemant :: " + Shreemant[a]);
				String abc = Shreemant[a];
				if(abc.equals("1")){
			        adc = "N";
				}
				if(abc.equals("2")){
					adc = "A";
				}
				if(abc.equals("3")){
					adc = "G";
				}
				if(abc.equals("4")){
					adc = "P";
				}
				if(abc.equals("5")){
					adc = "U";
				}
				if(abc.equals("6")){
					adc = "R";
				}
				if(abc.equals("7")){
					adc = "C";
				}
				if(abc.equals("8")){
					adc = "I";
				}
				if(abc.equals("9")){
					adc = "T";
				}
				if(abc.equals("0")){
					adc = "Y";
				}
				xyz = xyz.concat(adc);
			}
//			System.out.println(""+gd.get);	
			Date adate = null;
			try {
				adate = format.parse(pDate);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			gd.setDate(adate);

			session3.setAttribute("barcodeNo", barcodeNo);

			if (barcodeNo == null) {
				gd.setBarcodeNo(1000l);
			} else {
				gd.setBarcodeNo(barcodeNo);
			}

			GoodReciveDao dao = new GoodReciveDao();
			dao.regGoodReceive(gd);
			
			// Barcode Code By Meghraj
             
			try {
				
				
					
				
				FileInputStream fstream = new FileInputStream(
						"C:/barcose/input.prn");

				BufferedReader br = new BufferedReader(new InputStreamReader(
						fstream));
				FileWriter fw = new FileWriter("C:/barcose/Output"+i+".prn");

				BufferedWriter bw = new BufferedWriter(fw);
				String strLine;
				String str1;
				while ((strLine = br.readLine()) != null) {

					if (strLine.equals("~itemName")) 
					{
						str1 = br.readLine();
						strLine = str1 +"\"SAGAR CAR DECOR\"";
					}
					else if(strLine.equals("~catName"))
			         {
						str1=br.readLine();
			            strLine = str1 +"\"" +itemName+ "\"";
			               
			         }
					else if(strLine.equals("~qty"))
			         {
						str1=br.readLine();
			            strLine = str1 +"\"" +barcodeNo+ "\"";
			         }
					else if(strLine.equals("~buyPrive"))
			         {
						str1=br.readLine();
			            strLine = str1 + "\"" +catName+ "\"";
			         }
					else if(strLine.equals("~total"))
			         {
						str1=br.readLine();
			            strLine = str1 + "\"" +xyz+ "\"";
			               
			         }
					else if(strLine.equals("~quantityForNumberOfPrint"))
			         {
						str1=br.readLine();
			            strLine = str1 + quantity ;
			               
			         }
					System.out.println(strLine);
					bw.write(strLine + "\r\n");

				}

				bw.close();
				// Close the input stream
				br.close();
				
		        List cmdAndArgs = Arrays.asList("cmd", "/c", "printbatch"+i+".bat");
				File dir = new File("C:/barcose");


				ProcessBuilder pb = new ProcessBuilder(cmdAndArgs);
				pb.directory(dir);
				Process p = pb.start();
				/*}*/
			} catch (Exception e) {

			}
			
			// End Barcode code
			
			StockDao dao1 = new StockDao();
			List stkList2 = dao1.getAllStockEntry();
			
			int quant = Integer.parseInt(quantity);

			/* If Stock Is Empty */
			if (stkList2.size() == 0) {

				Stock newEntry = new Stock();

				newEntry.setItemName(itemName);
				newEntry.setQuantity(Long.parseLong(quantity));
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

					/* If ItemName Is Already Exists In Stock Table */
					if (ItemId.equals(itemName) && cat.equals(catName)) {
						Long qunty = st.getQuantity();

						Long updatequnty = (long) (qunty + Long
								.parseLong(quantity));

						HibernateUtility hbu = HibernateUtility.getInstance();
						Session session = hbu.getHibernateSession();
						Transaction transaction = session.beginTransaction();

						DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
						Date date = new Date();

						Stock updateStock = (Stock) session.get(Stock.class,
								new Long(PkItemId));
						updateStock.setUpdateDate(date);
						updateStock.setQuantity(updatequnty);

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

							StockDao dao2 = new StockDao();
							dao2.registerStock(newEntry);

						}
					}

				}

			}

		}
	}

	// get all purchase bill no
	public List getBillNo() {
		GoodReciveDao dao = new GoodReciveDao();
		return dao.getBillNo();
	}
	
	public List getBarcode() {
		GoodReciveDao dao = new GoodReciveDao();
		return dao.getBillNo();
	}
	//get Previous Good Receive Detail
		public List getPreGoodReceive(HttpServletRequest request,
				HttpServletResponse response) {
			// TODO Auto-generated method stub
			String BillNo = "";
			GoodReciveDao data = new GoodReciveDao();
			List stkList  = data.getLastBarcodeNo();
			
			for(int j=0;j<stkList.size();j++){
				
				GoodreciveBillBean st = (GoodreciveBillBean)stkList.get(j);
				BillNo = st.getBillNo();
				
			}
			
            Map<Long,PreviousGoodReceive> map = new HashMap<Long,PreviousGoodReceive>();
			
			GoodReciveDao dao = new GoodReciveDao();
			System.out.println("BILL NO ::: "+ BillNo);
			List<PreviousGoodReceive> exp1List = dao.getPreGoodReceive(BillNo);
			return exp1List;
		}
		
		// get all main barcode no	
		public List getAllMainBarcodeNo()
		{
			GoodReciveDao dao = new GoodReciveDao();
			return dao.getAllMainBarcodeNo();
		}
       // print barcode
		public void printBarcode(HttpServletRequest request, HttpServletResponse response) {
			// TODO Auto-generated method stub
			String barcodeId = request.getParameter("barcodeId");
			String quantity = request.getParameter("quantity");
			long bar = Long.parseLong(barcodeId);
			
			HibernateUtility hbu=HibernateUtility.getInstance();
			Session session1=hbu.getHibernateSession();
			org.hibernate.Query query = session1.createQuery("from GoodReceive where barcodeNo = :bar");
			query.setParameter("bar", bar);
			System.out.println("query size - "+query.list().size());
			GoodReceive uniqueResult = (GoodReceive) query.uniqueResult();
			String itemName = uniqueResult.getItemName();
			String catName = uniqueResult.getCatName();
			Double buyPrice = uniqueResult.getBuyPrice();
			System.out.println("itemname - catnmame  - buyprice - "+itemName+" - "+catName+" - "+buyPrice);
			String data6 = String.valueOf(buyPrice);
			String xyz = "";
			String adc = "";
			String[] Shreemant = data6.split("");
			for(int a = 0; a<Shreemant.length; a++){
				System.out.println("shreemant :: " + Shreemant[a]);
				String abc = Shreemant[a];
				if(abc.equals("1")){
			        adc = "N";
				}
				if(abc.equals("2")){
					adc = "A";
				}
				if(abc.equals("3")){
					adc = "G";
				}
				if(abc.equals("4")){
					adc = "P";
				}
				if(abc.equals("5")){
					adc = "U";
				}
				if(abc.equals("6")){
					adc = "R";
				}
				if(abc.equals("7")){
					adc = "C";
				}
				if(abc.equals("8")){
					adc = "I";
				}
				if(abc.equals("9")){
					adc = "T";
				}
				if(abc.equals("0")){
					adc = "Y";
				}
				xyz = xyz.concat(adc);
			}
			
            try {
				FileInputStream fstream = new FileInputStream(
						"C:/barcose/input.prn");

				BufferedReader br = new BufferedReader(new InputStreamReader(
						fstream));
				FileWriter fw = new FileWriter("C:/barcose/Output.prn");

				BufferedWriter bw = new BufferedWriter(fw);
				String strLine;
				String str1;
				while ((strLine = br.readLine()) != null) {

					if (strLine.equals("~itemName")) 
					{
						str1 = br.readLine();
						strLine = str1 +"\"SAGAR CAR DECOR\"";
					}
					else if(strLine.equals("~catName"))
			         {
						str1=br.readLine();
			            strLine = str1 +"\"" +itemName+ "\"";
			               
			         }
					else if(strLine.equals("~qty"))
			         {
						str1=br.readLine();
			            strLine = str1 +"\"" +bar+ "\"";
			         }
					else if(strLine.equals("~buyPrive"))
			         {
						str1=br.readLine();
			            strLine = str1 + "\"" +catName+ "\"";
			         }
					else if(strLine.equals("~total"))
			         {
						str1=br.readLine();
			            strLine = str1 + "\"" +xyz+ "\"";
			               
			         }
					else if(strLine.equals("~quantityForNumberOfPrint"))
			         {
						str1=br.readLine();
			            strLine = str1 + quantity ;
			               
			         }
					System.out.println(strLine);
					bw.write(strLine + "\r\n");
				}

				bw.close();
				// Close the input stream
				br.close();
				
		        List cmdAndArgs = Arrays.asList("cmd", "/c", "printbatch.bat");
				File dir = new File("C:/barcose");


				ProcessBuilder pb = new ProcessBuilder(cmdAndArgs);
				pb.directory(dir);
				Process p = pb.start();
				/*}*/
			} catch (Exception e) {

			}
			
		}
		
		//get Barcode Wise report
				public List BarcodeWiseReport(HttpServletRequest request,
						HttpServletResponse response) {
					// TODO Auto-generated method stub
					String catName = request.getParameter("catName");
					Long barcodeId = Long.parseLong(catName);
				
					 Map<Long,BarcodeReportBean> map = new HashMap<Long,BarcodeReportBean>();
						
					    GoodReciveDao dao = new GoodReciveDao();
						List<BarcodeReportBean> exp1List = dao.BarcodeWiseReport(barcodeId);
						
						return exp1List;
				}

				
				//Supplier Wise Purchase Report 
				public List supplierAllPurchase(HttpServletRequest request, HttpServletResponse response) {
					// TODO Auto-generated method stub
					long supplier = Long.parseLong(request.getParameter("supplier"));
					System.out.println("Supplier Id : "+supplier);
					 Map<Long,PurchaseReport> map = new HashMap<Long,PurchaseReport>();
						
					    GoodReciveDao dao = new GoodReciveDao();
						List<PurchaseReport> exp1List = dao.supplierAllPurchase(supplier);
						
						return exp1List;
				}

				//Purchase Report Supplier Bill No Wise
				public List supplierBillWisePurchaseReport(HttpServletRequest request, HttpServletResponse response) {
					// TODO Auto-generated method stub
					long supplier = Long.parseLong(request.getParameter("supplier"));
					String billNo = request.getParameter("billNo");
					 Map<Long,PurchaseReport> map = new HashMap<Long,PurchaseReport>();
						
					    GoodReciveDao dao = new GoodReciveDao();
						List<PurchaseReport> exp1List = dao.supplierBillWisePurchaseReport(supplier,billNo);
						
						return exp1List;
				}

				 //Purchase Report Category Wise
				public List categoryWisePurchaseReport(HttpServletRequest request, HttpServletResponse response) {
					// TODO Auto-generated method stub
					 String catName = request.getParameter("catName");
					 Map<Long,PurchaseReport> map = new HashMap<Long,PurchaseReport>();
						
					    GoodReciveDao dao = new GoodReciveDao();
						List<PurchaseReport> exp1List = dao.categoryWisePurchaseReport(catName);
						
						return exp1List;
				}
				//Purchase Report Barcode No Wise
				public List barcodeWisePurchaseReport(HttpServletRequest request, HttpServletResponse response) {
					// TODO Auto-generated method stub
					 String barcodeNoOurchase = request.getParameter("barcodeNoOurchase");
					 Map<Long,PurchaseReport> map = new HashMap<Long,PurchaseReport>();
						
					    GoodReciveDao dao = new GoodReciveDao();
						List<PurchaseReport> exp1List = dao.barcodeWisePurchaseReport(barcodeNoOurchase);
						
						return exp1List;
				}

				//Purchase Report Single Date
				public List singleDatePurchase45(HttpServletRequest request, HttpServletResponse response) {
					// TODO Auto-generated method stub
					String fDate = request.getParameter("purDate");
			        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					
					Date adate = null;
					try {
					 adate=	format.parse(fDate);
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					 Map<Long,PurchaseReport> map = new HashMap<Long,PurchaseReport>();
						
					    GoodReciveDao dao = new GoodReciveDao();
						List<PurchaseReport> exp1List = dao.singleDatePurchase45(adate);
						
						return exp1List;
				}

				//Purchase Report Two Date
				public List twoDatePurchase45(HttpServletRequest request, HttpServletResponse response) {
					// TODO Auto-generated method stub
					String pFisDate = request.getParameter("pFisDate");
					String pEndDate = request.getParameter("pEndDate");
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
					
					 Map<Long,PurchaseReport> map = new HashMap<Long,PurchaseReport>();
						
					    GoodReciveDao dao = new GoodReciveDao();
						List<PurchaseReport> exp1List = dao.twoDatePurchase45(adate,bdate);
						
						return exp1List;
				}
				
				
				//CA Purchase Report Two Date
				public List caReportBetweenTwoDates(HttpServletRequest request, HttpServletResponse response) {
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
					
					 Map<Long,PurchaseReport> map = new HashMap<Long,PurchaseReport>();
						
					    GoodReciveDao dao = new GoodReciveDao();
						List<PurchaseReport> exp1List = dao.caReportBetweenTwoDates(adate,bdate);
						
						return exp1List;
				}

				//TALLY Purchase Report Two Date
				public List tallyReportBetweenTwoDates(HttpServletRequest request, HttpServletResponse response) {
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
					
					 Map<Long,TallyPurchaseReport> map = new HashMap<Long,TallyPurchaseReport>();
						
					    GoodReciveDao dao = new GoodReciveDao();
						List<TallyPurchaseReport> exp1List = dao.tallyReportBetweenTwoDates(adate,bdate);
						
						return exp1List;
				}
           
				// Between Two Dates Tally Reports
				public List tallyPurchaseReportBetweenTwoDates(HttpServletRequest request, HttpServletResponse response) {
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
					
					 Map<Long,TallyPurchaseReport> map = new HashMap<Long,TallyPurchaseReport>();
						
					    GoodReciveDao dao = new GoodReciveDao();
						List<TallyPurchaseReport> exp1List = dao.tallyPurchaseReportBetweenTwoDates(adate,bdate);
						
						return exp1List;
				}

}
