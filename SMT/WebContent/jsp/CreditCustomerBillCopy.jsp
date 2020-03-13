
<%-- <%@page import="org.apache.batik.script.Window"%> --%>
<%@page import="com.smt.bean.NumToWord"%>
<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="com.itextpdf.text.Rectangle"%>
<%@page import="com.itextpdf.text.BaseColor"%>
<%@page import="com.itextpdf.text.Font"%>
<%@page import="com.itextpdf.text.FontFactory"%>
<%@page import="java.util.Formatter"%>
<%@page import="javax.sound.midi.Soundbank"%>
<%@page import="com.itextpdf.text.pdf.codec.Base64.OutputStream"%>
<%@page import="java.util.Date"%>
<%@page import="java.io.IOException"%>
<%@page import="com.itextpdf.text.DocumentException"%>
<%@page import="com.itextpdf.text.Paragraph"%>
<%@page import="com.itextpdf.text.pdf.PdfWriter"%>
<%@page import="com.itextpdf.text.Document"%>

<%@ page trimDirectiveWhitespaces="true"%>

<%@page import="java.util.Date"%>
<%@page import="java.io.IOException"%>
<%@page import="com.itextpdf.text.DocumentException"%>
<%@page import="com.itextpdf.text.Paragraph"%>
<%@page import="com.itextpdf.text.pdf.PdfWriter"%>
<%@page import="com.itextpdf.text.Document"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>

<%@page import="java.awt.Desktop"%>
<%@page import="java.io.File"%>
<%@page import="com.itextpdf.text.pdf.PdfPCell"%>
<%@page import="com.itextpdf.text.pdf.PdfPTable"%>
<%@page import="com.itextpdf.text.Paragraph"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="com.itextpdf.text.PageSize"%>
<%@page import="com.itextpdf.text.pdf.PdfWriter"%>
<%@page import="java.util.List"%>

<%@ page import="com.itextpdf.text.Element"%>
<%--  <%@page import="com.itextpdf.text.log.SysoLogger"%> --%>
<%@page import="java.util.List"%>

<%@page import="java.util.TimeZone"%>
<%@page import="java.text.SimpleDateFormat"%>

<%@page import="com.itextpdf.text.Phrase"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="com.itextpdf.text.Image"%>
<%@page import="com.itextpdf.text.pdf.PdfContentByte"%>
<%@page import="com.itextpdf.text.Image"%>
<%@page import="com.itextpdf.text.pdf.PdfContentByte"%>
<%@page import="com.itextpdf.text.pdf.PdfGState"%>

<%
	response.setContentType("application/pdf");
	 Long billno = (Long) session.getAttribute("BillNo");
	 String creditCustomerName = (String) session.getAttribute("creditCustomerName"); 
	 String gstTinNo = (String) session.getAttribute("gstTinNo");
 
	 double expenseExtra =0;
		double totalAmount = 0;
		double vatAmount = 0;
		double interGstAmount = 0;
		int itemCount = 0;
		String totAmountStr = "";
		String vatAmountStr = "";
		String extraExpence = "";
		double totalBags = 0;
		double packingOfBag = 0;
		String allItemNames = "";
		String AllInOne = "";
		double TotalOfTotalAmtWithoutVat = 0;
		double TotalOfTotalAmtWithoutVat1 = 0;
		double half = 2;
		double gsttax = 0;
//		String hsnsac1 = "";
//		String HsnSac = "";
		double GrandTotal = 0.0;
		double Total = 0.0;
		double TotalTax = 0.0;
		double stateTaxTotal = 0.0;
		int a = 1;
		
		
		

		try {
			Connection conn = null;
			Connection conn1 = null;
			// step 1
			Document document = new Document();

			// step 2

			PdfWriter.getInstance(document, response.getOutputStream());

			// step 3
			document.open();

			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smt_sc", "root", "root");
			Statement stmt = conn.createStatement();
			//Statement stmt1 = conn1.createStatement();
			
			ResultSet rs = stmt.executeQuery("select c.ItemName, c.Quantity, c.SalePrice, c.Discount, c.TotalAmount, c.GrossTotal, c.Date, c.totalperitem, c.Gst, c.Igst, c.TaxAmount, c.HsnSacNo , s.contact_no, s.first_name from creditcustomerbill c left join customer_details s ON c.fkCrediCustId=s.pk_customer_id where c.BillNo =" + billno);
			
			//ResultSet rs1 = stmt1.executeQuery("select c.ItemName, c.Quantity, c.SalePrice, c.Discount, c.TotalAmount, c.GrossTotal, c.Date, c.totalperitem, c.Gst, c.Igst, c.TaxAmount, c.HsnSacNo , s.contact_no, s.first_name from creditcustomerbill c left join customer_details s ON c.fkCrediCustId=s.pk_customer_id where c.BillNo =" + billno);
		
			
			System.out.println("Query Execute");
			Date d1 = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
			SimpleDateFormat sdf1 = new SimpleDateFormat("E");
			SimpleDateFormat sdf2 = new SimpleDateFormat("hh:mm:ss  ");
			sdf2.setTimeZone(TimeZone.getTimeZone("IST"));

			Date billDate = new Date();
			SimpleDateFormat dateFormater = new SimpleDateFormat("dd-MM-yyyy");
			String StrBillDate = dateFormater.format(billDate);

			String stdver1 = (String) sdf.format(d1);
			String day = sdf1.format(d1.getDate());
			String Time = sdf2.format(d1.getTime());
			String dtfinl = stdver1;

			DecimalFormat df = new DecimalFormat("#.00");

			Font font17Bold = new Font(Font.FontFamily.TIMES_ROMAN, 17, Font.BOLD, BaseColor.BLACK);
			Font font16Bold = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD, BaseColor.BLACK);
			Font font15Bold = new Font(Font.FontFamily.TIMES_ROMAN, 15, Font.BOLD, BaseColor.BLACK);
			Font font14Bold = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD, BaseColor.BLACK);
			Font font13Bold = new Font(Font.FontFamily.TIMES_ROMAN, 13, Font.BOLD, BaseColor.BLACK);
			Font font12Bold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLACK);
			Font font11Bold = new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD, BaseColor.BLACK);
			Font font16 = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD, BaseColor.BLACK);
			Font font14 = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD, BaseColor.BLACK);
			Font font12 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLACK);
			Font font13 = new Font(Font.FontFamily.TIMES_ROMAN, 13, Font.BOLD, BaseColor.BLACK);

			Font Normalfont12 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.BLACK);
			Font Normalfont13 = new Font(Font.FontFamily.TIMES_ROMAN, 13, Font.NORMAL, BaseColor.BLACK);
			Font Normalfont14 = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.NORMAL, BaseColor.BLACK);
			Font ufont14 = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.UNDERLINE, BaseColor.BLACK);

			

			//Header Containt Table

			rs.next();
			PdfPTable infotable1 = new PdfPTable(2);
			infotable1.setWidthPercentage(100);
			
			float[] infoColumnWidths1 = { 0.8f, 0.6f };
			infotable1.setWidths(infoColumnWidths1);
			
			float[] HeadercolumnWidths15 = { 0.9f, 0.5f };
			infotable1.setWidths(HeadercolumnWidths15);

			PdfPCell headerTable_cell15;

			headerTable_cell15 = new PdfPCell(new Phrase("TAX INVOICE\n ", font15Bold));
			headerTable_cell15.setHorizontalAlignment(Element.ALIGN_CENTER);
			headerTable_cell15.setColspan(2);
			headerTable_cell15.setBorder(Rectangle.NO_BORDER | Rectangle.NO_BORDER | Rectangle.NO_BORDER);
			infotable1.addCell(headerTable_cell15);
			
			document.add(infotable1);

			PdfPTable headertable = new PdfPTable(3);
			headertable.setWidthPercentage(100);
		
			float[] HeadercolumnWidths = { 0.9f, 0.5f,0.5f };
			headertable.setWidths(HeadercolumnWidths);

			PdfPCell headerTable_cell;
			
			headerTable_cell = new PdfPCell(new Phrase("SAGAR CAR DECOR\nNanded Road, Latur \nGSTIN/UIN : 27ANBPP6434B1Z8 \nContact No : 8275941042 / 9422468430\nE-Mail : sagarcardecor972@gmail.com", font12Bold));
			headerTable_cell.setRowspan(6);
			headerTable_cell.setBorder( Rectangle.TOP | Rectangle.LEFT | Rectangle.BOTTOM );
			headertable.addCell(headerTable_cell);
			
			headerTable_cell = new PdfPCell(new Phrase("Invoice No :" + billno, font12Bold));
			headerTable_cell.setRowspan(2);
			headerTable_cell.setBorder( Rectangle.TOP | Rectangle.BOTTOM | Rectangle.RIGHT | Rectangle.LEFT);
			headertable.addCell(headerTable_cell);
			
			headerTable_cell = new PdfPCell(new Phrase("Dated :"+ rs.getString("Date"), font12Bold));
			headerTable_cell.setRowspan(2);
			headerTable_cell.setBorder( Rectangle.TOP | Rectangle.RIGHT | Rectangle.BOTTOM |Rectangle.LEFT);
			headertable.addCell(headerTable_cell);
				
				headerTable_cell = new PdfPCell(new Phrase("Delivery Note :"));
				headerTable_cell.setRowspan(2);
				headerTable_cell.setBorder( Rectangle.TOP | Rectangle.BOTTOM |Rectangle.RIGHT | Rectangle.LEFT);
				headertable.addCell(headerTable_cell);
				
				headerTable_cell = new PdfPCell(new Phrase("Mode/Terms of Payment : "));
				headerTable_cell.setRowspan(2);
				headerTable_cell.setBorder( Rectangle.TOP | Rectangle.RIGHT | Rectangle.BOTTOM | Rectangle.LEFT);
				headertable.addCell(headerTable_cell);
				
				headerTable_cell = new PdfPCell(new Phrase("Supplier's Ref :"));
				headerTable_cell.setRowspan(2);
				headerTable_cell.setBorder( Rectangle.TOP | Rectangle.BOTTOM | Rectangle.RIGHT | Rectangle.LEFT);
				headertable.addCell(headerTable_cell);
				
				headerTable_cell = new PdfPCell(new Phrase("Other Reference(s) : "));
				headerTable_cell.setRowspan(2);
				headerTable_cell.setBorder( Rectangle.TOP | Rectangle.RIGHT | Rectangle.BOTTOM | Rectangle.LEFT);
				headertable.addCell(headerTable_cell);

			document.add(headertable);
			
			PdfPTable headertable1 = new PdfPTable(3);
			headertable1.setWidthPercentage(100);

			float[] HeadercolumnWidths1 = { 0.9f, 0.5f, 0.5f};
			headertable1.setWidths(HeadercolumnWidths1);

			PdfPCell headerTable_cell1;
			
			headerTable_cell1 = new PdfPCell(new Phrase("Buyer :\n" +creditCustomerName+ " \nGSTIN/UIN :" +gstTinNo+"\n\n\n\n\n", font12Bold));
			headerTable_cell1.setRowspan(8);
			headerTable_cell1.setBorder( Rectangle.TOP | Rectangle.LEFT | Rectangle.BOTTOM );
			headertable1.addCell(headerTable_cell1);

			
			headerTable_cell1 = new PdfPCell(new Phrase("Buyer's Order No:"));
			headerTable_cell1.setRowspan(2);
			headerTable_cell1.setBorder( Rectangle.TOP | Rectangle.BOTTOM |Rectangle.RIGHT | Rectangle.LEFT);
			headertable1.addCell(headerTable_cell1);

			headerTable_cell1 = new PdfPCell(new Phrase("Dated :"));
			headerTable_cell1.setRowspan(2);
			headerTable_cell1.setBorder( Rectangle.TOP | Rectangle.BOTTOM |Rectangle.RIGHT | Rectangle.LEFT);
			headertable1.addCell(headerTable_cell1);
			
			headerTable_cell1 = new PdfPCell(new Phrase("Despatch Document No : "));
			headerTable_cell1.setRowspan(2);
			headerTable_cell1.setBorder( Rectangle.TOP | Rectangle.BOTTOM |Rectangle.RIGHT | Rectangle.LEFT);
			headertable1.addCell(headerTable_cell1);

			headerTable_cell1 = new PdfPCell(new Phrase("Delivery Note Date : "));
			headerTable_cell1.setRowspan(2);
			headerTable_cell1.setBorder( Rectangle.TOP | Rectangle.BOTTOM |Rectangle.RIGHT | Rectangle.LEFT);
			headertable1.addCell(headerTable_cell1);
			
			headerTable_cell1 = new PdfPCell(new Phrase("Despatched through :  "));
			headerTable_cell1.setRowspan(2);
			headerTable_cell1.setBorder( Rectangle.TOP | Rectangle.BOTTOM |Rectangle.RIGHT | Rectangle.LEFT);
			headertable1.addCell(headerTable_cell1);
			
			headerTable_cell1 = new PdfPCell(new Phrase("Destination :  "));
			headerTable_cell1.setRowspan(2);
			headerTable_cell1.setBorder( Rectangle.TOP | Rectangle.BOTTOM |Rectangle.RIGHT | Rectangle.LEFT);
			headertable1.addCell(headerTable_cell1);
			
			headerTable_cell1 = new PdfPCell(new Phrase("Terms of Delivery : \n"));
			headerTable_cell1.setRowspan(2);
			headerTable_cell1.setBorder( Rectangle.TOP | Rectangle.BOTTOM | Rectangle.LEFT);
			headertable1.addCell(headerTable_cell1);
			
			headerTable_cell1 = new PdfPCell(new Phrase(""));
			headerTable_cell1.setRowspan(2);
			headerTable_cell1.setBorder( Rectangle.TOP | Rectangle.BOTTOM |Rectangle.RIGHT );
			headertable1.addCell(headerTable_cell1);

			document.add(headertable1);
			//end informatin

			//table for particulars
			PdfPTable table = new PdfPTable(8);
			table.setWidthPercentage(100);

			String taxType = String.valueOf(rs.getDouble("Gst"));
			String taxType1 = String.valueOf(rs.getDouble("Igst"));
					
			float[] columnWidths = { 0.1f, 0.8f, 0.4f, 0.2f, 0.3f, 0.4f, 0.4f,0.5f };
			table.setWidths(columnWidths);

			PdfPCell table_cell;
			rs.beforeFirst();
			
			table_cell = new PdfPCell(new Phrase("Sr"));
			table_cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(table_cell);

			table_cell = new PdfPCell(new Phrase("Description of  Goods"));
			table_cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(table_cell);
			
			table_cell = new PdfPCell(new Phrase("HSN/SAC"));
			table_cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(table_cell);
	 
			
			if(taxType.equals("0.0")){
				table_cell = new PdfPCell(new Phrase("GST Rate"));
				table_cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(table_cell);
				
			}else{
				if(taxType1.equals("0.0")){
					table_cell = new PdfPCell(new Phrase("GST Rate"));
					table_cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(table_cell);
				}else{
				table_cell = new PdfPCell(new Phrase("IGST Rate"));
				table_cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(table_cell);
				}
			}
			table_cell = new PdfPCell(new Phrase("Quantity"));
			table_cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(table_cell);

			table_cell = new PdfPCell(new Phrase("Rate"));
			table_cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(table_cell);

			table_cell = new PdfPCell(new Phrase("Tax Amount"));
			table_cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(table_cell);

			table_cell = new PdfPCell(new Phrase("Amount"));
			table_cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(table_cell);
			
			

		while (rs.next()) {

		expenseExtra = rs.getDouble("Discount");
		 
		gsttax =gsttax + rs.getDouble("Gst");
		
		table_cell = new PdfPCell(new Phrase("\n " + a));
		table_cell.setBorder(Rectangle.RIGHT | Rectangle.LEFT);
		table.addCell(table_cell);
		
		String itemName = rs.getString("ItemName");
		table_cell = new PdfPCell(new Phrase("\n " + itemName));
		table_cell.setBorder(Rectangle.RIGHT | Rectangle.LEFT);
		table.addCell(table_cell);

		String HsnSac = rs.getString("HsnSacNo");
		table_cell = new PdfPCell(new Phrase("\n " + HsnSac));
		table_cell.setBorder(Rectangle.RIGHT | Rectangle.LEFT);
		table.addCell(table_cell);
		
		String hsnsac1 = rs.getString("HsnSacNo");
		
		String Gst = String.valueOf(rs.getDouble("Gst"));
		String Igst = String.valueOf(rs.getDouble("Igst"));
		if(Igst.equals("0.0")){
			table_cell = new PdfPCell(new Phrase("\n " + Gst));
			table_cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table_cell.setBorder(Rectangle.RIGHT | Rectangle.LEFT);
			table.addCell(table_cell);
		}
		else{
			table_cell = new PdfPCell(new Phrase("\n " + Igst));
			table_cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table_cell.setBorder(Rectangle.RIGHT | Rectangle.LEFT);
			table.addCell(table_cell);
		}
		
		String packing = String.valueOf(rs.getDouble("Quantity"));
		table_cell = new PdfPCell(new Phrase("\n " + packing));
		table_cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		table_cell.setBorder(Rectangle.RIGHT | Rectangle.LEFT);
		table.addCell(table_cell);

		String totalWeight = String.valueOf(rs.getDouble("SalePrice"));
		table_cell = new PdfPCell(new Phrase("\n " + totalWeight));
		table_cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		table_cell.setBorder(Rectangle.RIGHT | Rectangle.LEFT);
		table.addCell(table_cell);

		String TaxAmount = String.valueOf(rs.getDouble("TaxAmount"));
		table_cell = new PdfPCell(new Phrase("\n " + TaxAmount));
		table_cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		table_cell.setBorder(Rectangle.RIGHT | Rectangle.LEFT);
		table.addCell(table_cell);

		String vatAmt = String.valueOf(rs.getDouble("totalperitem"));
		table_cell = new PdfPCell(new Phrase("\n " + vatAmt));
		table_cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		table_cell.setBorder(Rectangle.RIGHT | Rectangle.LEFT);
		table.addCell(table_cell);
		
		double gst = rs.getDouble("Gst");
		double igst = rs.getDouble("TaxAmount");
		double totalAmtWithoutVat = 0;
		if(igst == 0){
		 totalAmtWithoutVat = rs.getDouble("TaxAmount") - rs.getDouble("Gst");
		}
		else if(igst != 0){
			totalAmtWithoutVat = rs.getDouble("TaxAmount") - rs.getDouble("Gst");
		}
		String strTotalAmtWithoutVat = df.format(totalAmtWithoutVat);

		TotalOfTotalAmtWithoutVat = rs.getDouble("totalperitem");
		Total = Total + TotalOfTotalAmtWithoutVat;
		
		
		TotalOfTotalAmtWithoutVat1 = TotalOfTotalAmtWithoutVat1 + totalAmtWithoutVat ;
		double vAmt = rs.getDouble("Gst");
		vatAmount = vatAmount + vAmt;
		
		double newiGstAmount =  rs.getDouble("TaxAmount");
		interGstAmount = interGstAmount+newiGstAmount;
		
		double totalAmt = rs.getDouble("TaxAmount");
		TotalTax = TotalTax + totalAmt;

		itemCount++;

		//code for total item display on second pdf form of sale bill

		allItemNames = itemName;
		totalBags = rs.getDouble("Quantity");
		packingOfBag = rs.getDouble("Quantity");

		AllInOne += allItemNames + "  " + String.valueOf(totalBags) + "  Quantity  ";
	    a++;
		}
		rs.close();
		stmt.close();
		conn.close();
		
		
			table_cell = new PdfPCell(new Phrase("\n"));
			table_cell.setBorder(Rectangle.RIGHT | Rectangle.LEFT);
			table.addCell(table_cell);

			table_cell = new PdfPCell(new Phrase("\n"));
			table_cell.setBorder(Rectangle.RIGHT | Rectangle.LEFT);
			table.addCell(table_cell);

			table_cell = new PdfPCell(new Phrase("\n"));
			table_cell.setBorder(Rectangle.RIGHT | Rectangle.LEFT);
			table.addCell(table_cell);

			table_cell = new PdfPCell(new Phrase("\n"));
			table_cell.setBorder(Rectangle.RIGHT | Rectangle.LEFT);
			table.addCell(table_cell);

			table_cell = new PdfPCell(new Phrase("\n"));
			table_cell.setBorder(Rectangle.RIGHT | Rectangle.LEFT);
			table.addCell(table_cell);

			table_cell = new PdfPCell(new Phrase("\n"));
			table_cell.setBorder(Rectangle.RIGHT | Rectangle.LEFT);
			table.addCell(table_cell);

			table_cell = new PdfPCell(new Phrase("\n"));
			table_cell.setBorder(Rectangle.RIGHT | Rectangle.LEFT);
			table.addCell(table_cell);

			table_cell = new PdfPCell(new Phrase("\n"));
			table_cell.setBorder(Rectangle.RIGHT | Rectangle.LEFT);
			table.addCell(table_cell);
			
			
			String empty = " ";
			

			table_cell = new PdfPCell(new Phrase("Gross Total"));
			table_cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table_cell.setColspan(2);
			table.addCell(table_cell);

			table_cell = new PdfPCell(new Phrase(empty));
			table_cell.setPadding(0.5f);
			table.addCell(table_cell);
			
			table_cell = new PdfPCell(new Phrase(empty));
			table_cell.setPaddingBottom(1);
			table_cell.setPaddingTop(1);
			table.addCell(table_cell);
			
			table_cell = new PdfPCell(new Phrase(empty));
			table_cell.setPaddingBottom(1);
			table_cell.setPaddingTop(1);
			table.addCell(table_cell);

			vatAmountStr = String.valueOf(vatAmount);
			table_cell = new PdfPCell(new Phrase(empty));
			table_cell.setPaddingBottom(2);
			table_cell.setPaddingTop(1);
			table.addCell(table_cell);
			
			String TotalTaxAmount = df.format(TotalTax);
			table_cell = new PdfPCell(new Phrase(TotalTaxAmount, Normalfont12));
			table_cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table_cell.setPaddingBottom(2);
			table_cell.setPaddingTop(1);
			table.addCell(table_cell);

			String TotalOfTotalAmtWithoutVatStr = df.format(Total);
			table_cell = new PdfPCell(new Phrase(TotalOfTotalAmtWithoutVatStr, font16));
			table_cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table_cell.setPaddingBottom(2);
			table_cell.setPaddingTop(1);
			table.addCell(table_cell);
			
			double cgst = gsttax/half;
			String halfGst = String.valueOf(cgst);

			GrandTotal = Double.parseDouble(TotalOfTotalAmtWithoutVatStr);
			String strTotalWithVat = String.valueOf(df.format(GrandTotal));

			long longAmt = Math.round(GrandTotal);
			String strGrandTotal = String.valueOf(longAmt);
			int intGrandTotal = Integer.parseInt(strGrandTotal);

			NumToWord w = new NumToWord();
			String amtInWord = w.convert(intGrandTotal);

			table_cell = new PdfPCell(new Phrase("Amount Chargeable (in words) :\n INR "
					+ amtInWord + " Only/-\n "));
			table_cell.setColspan(9);
			table.addCell(table_cell);

			document.add(table);
			
			conn1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/smt_sc", "root", "root");
			Statement stmt1 = conn1.createStatement();
			
			ResultSet rs1 = stmt1.executeQuery("select c.ItemName, c.Quantity, c.SalePrice, c.Discount, c.TotalAmount, c.GrossTotal, c.Date, c.totalperitem, c.Gst, c.Igst, c.TaxAmount, c.HsnSacNo , s.contact_no, s.first_name from creditcustomerbill c left join customer_details s ON c.fkCrediCustId=s.pk_customer_id where c.BillNo =" + billno);

		
			PdfPTable footerTable1 = new PdfPTable(6);
			footerTable1.setWidthPercentage(100);

			float[] footerColumnWidths1 = { 0.9f, 0.4f, 0.3f, 0.3f, 0.3f, 0.3f};
			footerTable1.setWidths(footerColumnWidths1);

			rs1.next();
			
			String Gstrs1 = String.valueOf(rs1.getDouble("Gst"));
			String Igstrs1 = String.valueOf(rs1.getDouble("Igst"));
			PdfPCell footerTable_cell1;
			rs1.beforeFirst();	
			footerTable_cell1 = new PdfPCell(new Phrase("HSN/SAC \n"));
			footerTable_cell1.setRowspan(2);
			footerTable_cell1.setBorder( Rectangle.TOP | Rectangle.LEFT | Rectangle.BOTTOM | Rectangle.RIGHT );
			footerTable_cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			footerTable1.addCell(footerTable_cell1);
			
			footerTable_cell1 = new PdfPCell(new Phrase("Taxable Value"));
			footerTable_cell1.setRowspan(2);
			footerTable_cell1.setBorder( Rectangle.TOP | Rectangle.LEFT | Rectangle.BOTTOM | Rectangle.RIGHT );
			footerTable1.addCell(footerTable_cell1);
			
			
			if(Igstrs1.equals("0.0")){
				footerTable_cell1 = new PdfPCell(new Phrase("Central Tax"));
				footerTable_cell1.setColspan(2);
				footerTable_cell1.setRowspan(1);
				footerTable_cell1.setBorder( Rectangle.TOP | Rectangle.LEFT | Rectangle.BOTTOM | Rectangle.RIGHT );
				footerTable_cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
				footerTable1.addCell(footerTable_cell1);
				
				footerTable_cell1 = new PdfPCell(new Phrase("State Tax"));
				footerTable_cell1.setColspan(2);
				footerTable_cell1.setRowspan(1);
				footerTable_cell1.setBorder( Rectangle.TOP | Rectangle.LEFT | Rectangle.BOTTOM | Rectangle.RIGHT );
				footerTable1.addCell(footerTable_cell1);
				
				footerTable_cell1 = new PdfPCell(new Phrase("Rate"));
				footerTable_cell1.setColspan(1);
				footerTable_cell1.setRowspan(1);
				footerTable_cell1.setBorder( Rectangle.TOP | Rectangle.LEFT | Rectangle.BOTTOM | Rectangle.RIGHT );
				footerTable1.addCell(footerTable_cell1);
				
				footerTable_cell1 = new PdfPCell(new Phrase("Amount"));
				footerTable_cell1.setColspan(1);
				footerTable_cell1.setRowspan(1);
				footerTable_cell1.setBorder( Rectangle.TOP | Rectangle.LEFT | Rectangle.BOTTOM | Rectangle.RIGHT);
				footerTable1.addCell(footerTable_cell1);
				
				footerTable_cell1 = new PdfPCell(new Phrase("Rate"));
				footerTable_cell1.setColspan(1);
				footerTable_cell1.setRowspan(1);
				footerTable_cell1.setBorder( Rectangle.TOP | Rectangle.LEFT | Rectangle.BOTTOM | Rectangle.RIGHT );
				footerTable1.addCell(footerTable_cell1);
				
				footerTable_cell1 = new PdfPCell(new Phrase("Amount"));
				footerTable_cell1.setColspan(1);
				footerTable_cell1.setRowspan(1);
				footerTable_cell1.setBorder( Rectangle.TOP | Rectangle.LEFT | Rectangle.BOTTOM | Rectangle.RIGHT );
				footerTable1.addCell(footerTable_cell1);
				
			}
			else{
				footerTable_cell1 = new PdfPCell(new Phrase("IGST Tax"));
				footerTable_cell1.setColspan(4);
				footerTable_cell1.setRowspan(1);
				footerTable_cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
				footerTable_cell1.setBorder( Rectangle.TOP | Rectangle.LEFT | Rectangle.BOTTOM | Rectangle.RIGHT );
				footerTable1.addCell(footerTable_cell1);
				
				footerTable_cell1 = new PdfPCell(new Phrase("Rate"));
				footerTable_cell1.setColspan(2);
				footerTable_cell1.setRowspan(1);
				footerTable_cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
				footerTable_cell1.setBorder( Rectangle.TOP | Rectangle.LEFT | Rectangle.BOTTOM | Rectangle.RIGHT );
				footerTable1.addCell(footerTable_cell1);
				
				footerTable_cell1 = new PdfPCell(new Phrase("Amount"));
				footerTable_cell1.setColspan(2);
				footerTable_cell1.setRowspan(1);
				footerTable_cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
				footerTable_cell1.setBorder( Rectangle.TOP | Rectangle.LEFT | Rectangle.BOTTOM | Rectangle.RIGHT );
				footerTable1.addCell(footerTable_cell1);
			}
			String TotalOfTotalAmtWithoutVatStr1 = "";
			while (rs1.next()) {
			
			String HsnSac1 = rs1.getString("HsnSacNo");
			footerTable_cell1 = new PdfPCell(new Phrase(HsnSac1));
			footerTable_cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			footerTable_cell1.setPaddingBottom(2);
			footerTable_cell1.setPaddingTop(1);
			footerTable1.addCell(footerTable_cell1);

			String TaxAmount1 = String.valueOf(rs1.getDouble("TaxAmount"));
		    TotalOfTotalAmtWithoutVatStr1= df.format(TotalOfTotalAmtWithoutVat1);
			footerTable_cell1 = new PdfPCell(new Phrase(TaxAmount1, font14));
			footerTable_cell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
			footerTable_cell1.setPaddingBottom(2);
			footerTable_cell1.setPaddingTop(1);
			footerTable1.addCell(footerTable_cell1);
			
			String Gst3 = String.valueOf(rs1.getDouble("Gst"));
			String Igst3 = String.valueOf(rs1.getDouble("Igst"));
			
			if(Igst3.equals("0.0")){
				double Gst1 = (rs1.getDouble("Gst"))/2;
				String Gst2 = String.valueOf(Gst1);
				footerTable_cell1 = new PdfPCell(new Phrase(Gst2));
				footerTable_cell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
				footerTable_cell1.setPaddingBottom(2);
				footerTable_cell1.setPaddingTop(1);
				footerTable1.addCell(footerTable_cell1);
				
				double TaxAmount2 = rs1.getDouble("TaxAmount")/2;
				String TaxAmount3 = String.valueOf(TaxAmount2);
				footerTable_cell1 = new PdfPCell(new Phrase(TaxAmount3));
				footerTable_cell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
				footerTable_cell1.setPaddingBottom(2);
				footerTable_cell1.setPaddingTop(1);
				footerTable1.addCell(footerTable_cell1);
				
			
				footerTable_cell1 = new PdfPCell(new Phrase(Gst2));
				footerTable_cell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
				footerTable_cell1.setPaddingBottom(2);
				footerTable_cell1.setPaddingTop(1);
				footerTable1.addCell(footerTable_cell1);
				
				
				footerTable_cell1 = new PdfPCell(new Phrase(TaxAmount3));
				footerTable_cell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
				footerTable_cell1.setPaddingBottom(2);
				footerTable_cell1.setPaddingTop(1);
				footerTable1.addCell(footerTable_cell1);
				
				stateTaxTotal = stateTaxTotal + TaxAmount2;
			}
			
			else{
				
				double Gst1 = (rs1.getDouble("Igst"));
				String Gst2 = String.valueOf(Gst1);
				footerTable_cell1 = new PdfPCell(new Phrase(Gst2));
				footerTable_cell1.setColspan(2);
				footerTable_cell1.setRowspan(1);
				footerTable_cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
				footerTable_cell1.setPaddingBottom(2);
				footerTable_cell1.setPaddingTop(1);
				footerTable1.addCell(footerTable_cell1);
				
				double TaxAmount2 = rs1.getDouble("TaxAmount");
				String TaxAmount3 = String.valueOf(TaxAmount2);
				footerTable_cell1 = new PdfPCell(new Phrase(TaxAmount3));
				footerTable_cell1.setColspan(2);
				footerTable_cell1.setRowspan(1);
				footerTable_cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
				footerTable_cell1.setPaddingBottom(2);
				footerTable_cell1.setPaddingTop(1);
				footerTable1.addCell(footerTable_cell1);
				
			
				/* footerTable_cell1 = new PdfPCell(new Phrase(Gst2));
				footerTable_cell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
				footerTable_cell1.setPaddingBottom(2);
				footerTable_cell1.setPaddingTop(1);
				footerTable1.addCell(footerTable_cell1);
				
				
				footerTable_cell1 = new PdfPCell(new Phrase(TaxAmount3));
				footerTable_cell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
				footerTable_cell1.setPaddingBottom(2);
				footerTable_cell1.setPaddingTop(1);
				footerTable1.addCell(footerTable_cell1); */
				
				stateTaxTotal = stateTaxTotal + TaxAmount2;
				
			}
			
			
			}
			footerTable_cell1 = new PdfPCell(new Phrase("Total"));
			footerTable_cell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
			footerTable_cell1.setRowspan(2);
			footerTable_cell1.setBorder( Rectangle.TOP | Rectangle.LEFT | Rectangle.BOTTOM | Rectangle.RIGHT );
			footerTable1.addCell(footerTable_cell1);
			
			
			footerTable_cell1 = new PdfPCell(new Phrase(TotalTaxAmount, font14));
			footerTable_cell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
			footerTable_cell1.setRowspan(2);
			footerTable_cell1.setBorder( Rectangle.TOP | Rectangle.LEFT | Rectangle.BOTTOM | Rectangle.RIGHT );
			footerTable1.addCell(footerTable_cell1);
			
			if(Igstrs1.equals("0.0")){
				
				footerTable_cell1 = new PdfPCell(new Phrase(""));
				footerTable_cell1.setRowspan(2);
				footerTable_cell1.setBorder( Rectangle.TOP | Rectangle.LEFT | Rectangle.BOTTOM | Rectangle.RIGHT );
				footerTable1.addCell(footerTable_cell1);
				
				String stateTaxTotal1 = df.format(stateTaxTotal);
				footerTable_cell1 = new PdfPCell(new Phrase(stateTaxTotal1));
				footerTable_cell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
				footerTable_cell1.setRowspan(2);
				footerTable_cell1.setBorder( Rectangle.TOP | Rectangle.LEFT | Rectangle.BOTTOM | Rectangle.RIGHT );
				footerTable1.addCell(footerTable_cell1);
				
				footerTable_cell1 = new PdfPCell(new Phrase(""));
				footerTable_cell1.setRowspan(2);
				footerTable_cell1.setBorder( Rectangle.TOP | Rectangle.LEFT | Rectangle.BOTTOM | Rectangle.RIGHT );
				footerTable1.addCell(footerTable_cell1);
				
				footerTable_cell1 = new PdfPCell(new Phrase(stateTaxTotal1));
				footerTable_cell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
				footerTable_cell1.setRowspan(2);
				footerTable_cell1.setBorder( Rectangle.TOP | Rectangle.LEFT | Rectangle.BOTTOM | Rectangle.RIGHT );
				footerTable1.addCell(footerTable_cell1);
				
			}
			
			else{
				
				/* footerTable_cell1 = new PdfPCell(new Phrase(""));
				footerTable_cell1.setRowspan(2);
				footerTable_cell1.setBorder( Rectangle.TOP | Rectangle.LEFT | Rectangle.BOTTOM | Rectangle.RIGHT );
				footerTable1.addCell(footerTable_cell1); */
				
				String stateTaxTotal1 = df.format(stateTaxTotal);
				footerTable_cell1 = new PdfPCell(new Phrase(""));
				footerTable_cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
				footerTable_cell1.setColspan(2);
				footerTable_cell1.setRowspan(2);
				footerTable_cell1.setBorder( Rectangle.TOP | Rectangle.LEFT | Rectangle.BOTTOM | Rectangle.RIGHT );
				footerTable1.addCell(footerTable_cell1);
				
				/* footerTable_cell1 = new PdfPCell(new Phrase(""));
				footerTable_cell1.setRowspan(2);
				footerTable_cell1.setBorder( Rectangle.TOP | Rectangle.LEFT | Rectangle.BOTTOM | Rectangle.RIGHT );
				footerTable1.addCell(footerTable_cell1); */
				
				footerTable_cell1 = new PdfPCell(new Phrase(stateTaxTotal1));
				footerTable_cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
				footerTable_cell1.setColspan(2);
				footerTable_cell1.setRowspan(2);
				footerTable_cell1.setBorder( Rectangle.TOP | Rectangle.LEFT | Rectangle.BOTTOM | Rectangle.RIGHT );
				footerTable1.addCell(footerTable_cell1);
			}
			
			double GrandTotal1 = (TotalTax);
			String strTotalWithVat1 = String.valueOf(df.format(GrandTotal1));
			
			long longAmt1 = Math.round(GrandTotal1);
			String strGrandTotal1 = String.valueOf(longAmt1);
			int intGrandTotal1 = Integer.parseInt(strGrandTotal1);
			
			NumToWord w1 = new NumToWord();
			String amtInWord1 = w1.convert(intGrandTotal1);

			footerTable_cell1 = new PdfPCell(new Phrase("\n Tax Amount (in words) : INR "
					+ amtInWord1 + " Only/-\n "));
			footerTable_cell1.setColspan(6);
			footerTable_cell1.setRowspan(8);
			footerTable1.addCell(footerTable_cell1);
			
			document.add(footerTable1);
			
			//footer table

			PdfPTable footerTable = new PdfPTable(1);
			footerTable.setWidthPercentage(100);

			float[] footerColumnWidths = { 0.9f};
			footerTable.setWidths(footerColumnWidths);

			PdfPCell footerTable_cell;

			footerTable_cell = new PdfPCell(
			new Phrase(
							" \n Declaration :\n We declare that this invoice shows the actual price of the goods described and that all particulars are true and correct"));
			
			footerTable_cell.setRowspan(5);
			footerTable.addCell(footerTable_cell);

			
			document.add(footerTable);
		
					PdfPTable footerTable2 = new PdfPTable(2);
					footerTable2.setWidthPercentage(100);

					float[] footerColumnWidths2 = { 0.7f, 0.9f };
					footerTable2.setWidths(footerColumnWidths2);

					PdfPCell footerTable_cell2;

					footerTable_cell2 = new PdfPCell(new Phrase("Customer's Seal and Signature \n\n\n " ));
					footerTable_cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
					footerTable_cell1.setRowspan(6);
					footerTable2.addCell(footerTable_cell2);

					footerTable_cell2 = new PdfPCell(
					new Phrase("                            For SAGAR CAR DECOR     \n\n\n                                 Authorised Signatory" ));
					footerTable_cell2.setRowspan(6);
					footerTable2.addCell(footerTable_cell2);

					
					document.add(footerTable2);
			
			
			
			// step 5
			rs1.close();
		    stmt1.close();
		    conn1.close();
			document.close();

		} catch (DocumentException de) {
			throw new IOException(de.getMessage());
		}
%>

