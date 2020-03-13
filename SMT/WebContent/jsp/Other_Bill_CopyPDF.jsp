
<%-- <%@page import="org.apache.batik.script.Window"%> --%>
<%@page import="com.smt.utility.PropertiesHelper"%>
<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@page import="com.smt.bean.NumToWord"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.itextpdf.text.Rectangle"%>
<%@page import="com.itextpdf.text.pdf.PdfEncodings"%>
<%@page import="com.itextpdf.text.pdf.BaseFont"%>
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
<%@page import="com.itextpdf.text.Font"%>
<%@page import="com.itextpdf.text.FontFactory"%>
<%@page import="com.itextpdf.text.pdf.BaseFont"%>








<%
	response.setContentType("application/pdf");
	 Long billno = (Long) session.getAttribute("OtherBillNo");
	 final String FONT ;
	 FONT = "C:/Users/sumeet/Documents/Devanagari/";
	 
	 Font marFont = FontFactory.getFont("Lohit-Devanagari.ttf",BaseFont.IDENTITY_H,true);

     // Lets write a big header
    
	 int itemCount = 0;
		int quantCount = 0;
		double finalTotAmountWithoutDis = 0;
		double finalTotAmountWithDis = 0;
		double finalDiscountAmt = 0;
		double finalgross = 0;
		double finalDis = 0;

		DecimalFormat df = new DecimalFormat("#.00");

		Connection conn = null;

		try {
	      
			Document document = new Document(PageSize.A6, 20f,20f, 0f, 10f);
			PdfWriter.getInstance(document, response.getOutputStream());
			document.open(); 
			/* 
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			PrintStream ps = new PrintStream(baos);
			Document document = new Document();
			PdfWriter.getInstance(document, ps);
			document.open(); */
			
			
			
			/* ByteArrayOutputStream baos = new ByteArrayOutputStream();
	       // PrintStream ps = new PrintStream(baos);
	        Document document = new Document();
	        PdfWriter.getInstance(document,baos);
	        document.setPageSize(PageSize.A6);
	        document.open(); */
	 
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smt_sc", "root", "root");
			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery("select ItemName, CategoryName, Quantity, SalePrice, ContactNo, OwnerName, TotalAmount, Discount, GrossTotal, Date, totalperitem, TaxAmount from otherbill where BillNo =" + billno);

			Font font17Bold = new Font(Font.FontFamily.TIMES_ROMAN, 17, Font.BOLD, BaseColor.BLACK);
			Font font16Bold = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD, BaseColor.BLACK);
			Font font14 = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD, BaseColor.BLACK);
			Font font12 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLACK);
			Font font13 = new Font(Font.FontFamily.TIMES_ROMAN, 13, Font.BOLD, BaseColor.BLACK);

			Font Normalfont13 = new Font(Font.FontFamily.TIMES_ROMAN, 13, Font.NORMAL, BaseColor.BLACK);
			Font Normalfont14 = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.NORMAL, BaseColor.BLACK);
			Font ufont14 = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.UNDERLINE, BaseColor.BLACK);

			System.out.println("Query Execute");
			Date d1 = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
			SimpleDateFormat sdf1 = new SimpleDateFormat("E");
			SimpleDateFormat sdf2 = new SimpleDateFormat("hh:mm:ss  ");
			sdf2.setTimeZone(TimeZone.getTimeZone("IST"));

			String stdver1 = (String) sdf.format(d1);
			String day = sdf1.format(d1.getDate());
			String Time = sdf2.format(d1.getTime());
			String dtfinl = stdver1;

			rs.next();

			String name = "N/A";
			String mono = "N/A";
			//String carno = rs.getString("CarNo");
			String saleDate = rs.getString("Date");
			String grossTotal123 = rs.getString("GrossTotal");
			//Header Containt Table

			PdfPTable headertable = new PdfPTable(2);
			headertable.setWidthPercentage(100);

			float[] HeadercolumnWidths = { 0.9f, 0.5f };
			headertable.setWidths(HeadercolumnWidths);

			PdfPCell headerTable_cell;

			
			
			
			headerTable_cell = new PdfPCell(new Phrase("TAX INVOICE\n ", font17Bold));
			headerTable_cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			headerTable_cell.setColspan(2);
			headerTable_cell.setBorder(Rectangle.NO_BORDER | Rectangle.NO_BORDER | Rectangle.NO_BORDER);
			headertable.addCell(headerTable_cell);

			
			headerTable_cell = new PdfPCell(new Phrase(PropertiesHelper.marathiProperties.getProperty("productDetails") , font17Bold));
			headerTable_cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			headerTable_cell.setColspan(2);
			headerTable_cell.setBorder(Rectangle.NO_BORDER | Rectangle.NO_BORDER);
			headertable.addCell(headerTable_cell);
			/* 
			headerTable_cell = new PdfPCell(new Phrase("Sagar Car Decor", font17Bold));
			headerTable_cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			headerTable_cell.setColspan(2);
			headerTable_cell.setBorder(Rectangle.NO_BORDER | Rectangle.NO_BORDER);
			headertable.addCell(headerTable_cell);
 */
			headerTable_cell = new PdfPCell(new Phrase("Nanded Road Latur"));
			headerTable_cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			headerTable_cell.setColspan(2);
			headerTable_cell.setBorder(Rectangle.NO_BORDER | Rectangle.NO_BORDER);
			headertable.addCell(headerTable_cell);
			
			document.add(headertable);
			
			PdfPTable infotable1 = new PdfPTable(2);
			infotable1.setWidthPercentage(100);
			
			float[] infoColumnWidths1 = { 0.8f, 0.6f };
			infotable1.setWidths(infoColumnWidths1);
			
			PdfPCell InfoTable_cell1;
			//PdfFont f = PdfFontFactory.createFont(FONT, PdfEncodings.IDENTITY_H);
			 
			/* InfoTable_cell1 = new PdfPCell(new Phrase("Mobile No : 8275941042")); */
			InfoTable_cell1 = new PdfPCell(new Phrase('\u0915'));
			InfoTable_cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			InfoTable_cell1.setBorder(Rectangle.NO_BORDER | Rectangle.NO_BORDER | Rectangle.NO_BORDER);
			infotable1.addCell(InfoTable_cell1);
			
			InfoTable_cell1 = new PdfPCell(new Phrase("Bill No : "+ billno+"\n\n"));
			InfoTable_cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			InfoTable_cell1.setBorder(Rectangle.NO_BORDER | Rectangle.NO_BORDER | Rectangle.NO_BORDER);
			infotable1.addCell(InfoTable_cell1);

			document.add(infotable1);

			//information table

			PdfPTable infotable = new PdfPTable(1);
			infotable.setWidthPercentage(100);

			float[] infoColumnWidths = {0.8f };
			infotable.setWidths(infoColumnWidths);

			PdfPCell InfoTable_cell;

			/* InfoTable_cell = new PdfPCell(new Phrase("Name: "+ name));
			InfoTable_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			InfoTable_cell.setBorder(Rectangle.NO_BORDER);
			infotable.addCell(InfoTable_cell); */

			/* InfoTable_cell = new PdfPCell(new Phrase("Car No: "+ carno));
			InfoTable_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			InfoTable_cell.setBorder(Rectangle.NO_BORDER);
			infotable.addCell(InfoTable_cell); */

			/* InfoTable_cell = new PdfPCell(new Phrase("Mo No: "+ mono));
			InfoTable_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			InfoTable_cell.setBorder(Rectangle.NO_BORDER);
			infotable.addCell(InfoTable_cell); */

			InfoTable_cell = new PdfPCell(new Phrase("Date: "+ saleDate +"\n\n"));
			InfoTable_cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			InfoTable_cell.setBorder(Rectangle.NO_BORDER);
			infotable.addCell(InfoTable_cell);

			document.add(infotable);

			//table for particulars
			PdfPTable table = new PdfPTable(4);
			table.setWidthPercentage(100);

			float[] columnWidths = { 0.9f, 0.3f, 0.6f, 0.6f };
			table.setWidths(columnWidths);

			PdfPCell table_cell;
			rs.beforeFirst();
			
			
			table_cell = new PdfPCell(new Phrase("Item Name",font12));
			table_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			table_cell.setBorder(Rectangle.BOTTOM);
			table.addCell(table_cell);

			table_cell = new PdfPCell(new Phrase("Qty",font12));
			table_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			table_cell.setBorder(Rectangle.BOTTOM);
			table.addCell(table_cell);

			table_cell = new PdfPCell(new Phrase("Sale Price",font12));
			table_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			table_cell.setBorder(Rectangle.BOTTOM);
			table.addCell(table_cell);

			table_cell = new PdfPCell(new Phrase("Amount",font12));
			table_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			table_cell.setBorder(Rectangle.BOTTOM);
			table.addCell(table_cell);
			
			
			while (rs.next()) {
				

				String ItemName = rs.getString("ItemName");
				table_cell = new PdfPCell(new Phrase("\n " + ItemName));
				table_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				table_cell.setBorder(Rectangle.NO_BORDER | Rectangle.NO_BORDER);
				table.addCell(table_cell);

				String Quantity = String.valueOf(rs.getString("Quantity"));
				table_cell = new PdfPCell(new Phrase("\n " + Quantity));
				table_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				table_cell.setBorder(Rectangle.NO_BORDER | Rectangle.NO_BORDER);
				table.addCell(table_cell);

				double price = rs.getDouble("totalperitem");
				long quant = rs.getLong("Quantity");
				double tax = rs.getDouble("TaxAmount");
				double taxperitem = tax / quant;
				double saletotal = price / quant;
				String SalePrice = String.valueOf(saletotal);
				table_cell = new PdfPCell(new Phrase("\n " + SalePrice));
				table_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				table_cell.setBorder(Rectangle.NO_BORDER | Rectangle.NO_BORDER);
				table.addCell(table_cell);

			    
			    double amt = saletotal * quant;
				String totAmountWithoutDis = String.valueOf(rs.getDouble("SalePrice"));
				String strTotAmountWithoutDis = df.format(Double.parseDouble(totAmountWithoutDis));

				table_cell = new PdfPCell(new Phrase("\n " + amt));
				table_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				table_cell.setBorder(Rectangle.NO_BORDER | Rectangle.NO_BORDER);
				table.addCell(table_cell);
				
				String grossTotal = String.valueOf(rs.getDouble("GrossTotal"));
				String strGrossTotal = df.format(Double.parseDouble(grossTotal));
				
				finalDiscountAmt = rs.getDouble("Discount");
				finalTotAmountWithoutDis = finalTotAmountWithoutDis + amt ;
				finalTotAmountWithDis = finalTotAmountWithDis + Double.parseDouble(strGrossTotal);
				double discount = Double.parseDouble(totAmountWithoutDis) - Double.parseDouble(grossTotal);
				String totAmountWithoutDis1 = String.valueOf(rs.getDouble("TotalAmount"));
				itemCount++;
				finalDis = finalDis + finalDiscountAmt;
				finalgross = finalTotAmountWithoutDis - finalDis;

			}
			
			
			table_cell = new PdfPCell(new Phrase("\n"));
			table_cell.setBorder(Rectangle.NO_BORDER | Rectangle.NO_BORDER);
			table_cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(table_cell);

			table_cell = new PdfPCell(new Phrase("\n"));
			table_cell.setBorder(Rectangle.NO_BORDER | Rectangle.NO_BORDER);
			table_cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(table_cell);

			table_cell = new PdfPCell(new Phrase("\n"));
			table_cell.setBorder(Rectangle.NO_BORDER | Rectangle.NO_BORDER);
			table_cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(table_cell);

			table_cell = new PdfPCell(new Phrase("\n"));
			table_cell.setBorder(Rectangle.NO_BORDER | Rectangle.NO_BORDER);
			table_cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(table_cell);
			
			String empty = " ";

			
			
			String strItemCount = String.valueOf(itemCount);
			table_cell = new PdfPCell(new Phrase(" #Items (" + strItemCount + ")"));
			table_cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(table_cell);

			table_cell = new PdfPCell(new Phrase(empty));
			table_cell.setPaddingBottom(1);
			table_cell.setBorder(Rectangle.NO_BORDER);
			table_cell.setPaddingTop(1);
			table.addCell(table_cell);

			/* table_cell = new PdfPCell(new Phrase(empty));
			table_cell.setPaddingBottom(1);
			table_cell.setPaddingTop(1);
			table.addCell(table_cell); */

			table_cell = new PdfPCell(new Phrase("Total :", font14));
			table_cell.setPaddingBottom(1);
			table_cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table_cell.setBorder(Rectangle.NO_BORDER);
			table_cell.setColspan(1);
			table_cell.setPaddingTop(1);
			table.addCell(table_cell);

			String AmtWithoutDis = df.format(finalTotAmountWithoutDis);
			table_cell = new PdfPCell(new Phrase(AmtWithoutDis, font14));
			table_cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table_cell.setBorder(Rectangle.NO_BORDER);
			table_cell.setPaddingBottom(1);
			table_cell.setPaddingTop(1);
			table.addCell(table_cell);
			
			/* table_cell = new PdfPCell(new Phrase(" "));
			table_cell.setColspan(5);
			table_cell.setRowspan(2);
			table.addCell(table_cell); */

			table_cell = new PdfPCell(new Phrase("Discount :", font14));
			table_cell.setPaddingBottom(1);
			table_cell.setColspan(3);
			table_cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table_cell.setBorder(Rectangle.NO_BORDER);
			table_cell.setPaddingTop(1);
			table.addCell(table_cell);
			
			String fDisAmt;
			if(finalDis > 0){
				fDisAmt = df.format(finalDis);
			}else{
				fDisAmt = "0.00";
			}
				
			table_cell = new PdfPCell(new Phrase(fDisAmt, font14));
			table_cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table_cell.setBorder(Rectangle.NO_BORDER);
			table_cell.setPaddingBottom(1);
			table_cell.setPaddingTop(1);
			table.addCell(table_cell);
			
			table_cell = new PdfPCell(new Phrase("G.Total :", font14));
			table_cell.setColspan(3);
			table_cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table_cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(table_cell);
			
			table_cell = new PdfPCell(new Phrase(df.format(finalgross), font14));
			table_cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table_cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(table_cell);
			 
			long longGrandTotalAmt = Math.round(Double.valueOf(finalgross));
			String strGrandTotalAmt = String.valueOf(longGrandTotalAmt);
			int intGrandTotalAmt = Integer.parseInt(strGrandTotalAmt);
			NumToWord w = new NumToWord();
			String amtInWord = w.convert(intGrandTotalAmt);

			table_cell = new PdfPCell(new Phrase("\n Rupees in words : " + amtInWord + " Only/- "));
			table_cell.setBorder(Rectangle.NO_BORDER);
			table_cell.setColspan(7);
			table.addCell(table_cell); 
			
			table_cell = new PdfPCell(new Phrase("--Thank You--"));
			table_cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table_cell.setBorder(Rectangle.NO_BORDER);
			table_cell.setColspan(4);
			table.addCell(table_cell); 

			document.add(table);


			// step 5
			rs.close();
			stmt.close();
			conn.close();
			document.close();
			/* PrintService ps1 = PrintServiceLookup.lookupDefaultPrintService();
			byte[] pdfbyte = baos.toByteArray(); */
	        //System.out.println(pdf);
	        /* FileInputStream fis = new FileInputStream("D:/26.pdf");
			Doc pdfDoc = new SimpleDoc(fis, DocFlavor.INPUT_STREAM.AUTOSENSE, null);
			DocPrintJob printJob = ps1.createPrintJob();
			printJob.print(pdfDoc, new HashPrintRequestAttributeSet());
			fis.close(); */
	        
	      /*   InputStream bis = new ByteArrayInputStream(pdfbyte);
	        SimpleDoc pdfp = new SimpleDoc(bis, DocFlavor.INPUT_STREAM.AUTOSENSE, null);
	        DocPrintJob printjob= ps1.createPrintJob();
	        printjob.print(pdfp, new HashPrintRequestAttributeSet());
	        bis.close(); */

		} catch (DocumentException de) {
			throw new IOException(de.getMessage());
		}
	%>
		