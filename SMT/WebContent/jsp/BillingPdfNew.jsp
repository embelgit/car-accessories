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



<%@page import="javax.activation.DataSource"%>
<%@page import="javax.mail.util.ByteArrayDataSource"%>
<%@page import="javax.activation.DataHandler"%>
<%@page import="javax.activation.FileDataSource"%>
<%@page import="javax.mail.Multipart"%>
<%@page import="javax.mail.internet.MimeMultipart"%>
<%@page import="javax.mail.internet.MimeBodyPart"%>
<%@page import="javax.mail.Address"%>
<%@page import="com.sun.mail.smtp.SMTPTransport"%>
<%@page import="javax.mail.URLName"%>
<%@page import="javax.mail.internet.InternetAddress"%>
<%@page import="javax.mail.internet.MimeMessage"%>
<%@page import="javax.mail.Transport"%>
<%@page import="javax.mail.Session"%>


<%@ page import="java.io.*, com.itextpdf.text.*, com.itextpdf.text.pdf.*" %>


<%
	response.setContentType("application/pdf");
	 Long billno = (Long) session.getAttribute("carBillNO");

	
	int itemCount = 0;
	int quantCount = 0;
	double finalTotAmountWithoutDis = 0;
	double finalTotAmountWithDis = 0;
	double finalDiscountAmt = 0;
	double finalgross = 0;
	double finalDis = 0;
	double laberCharges = 0;
	double finalLaberCharges = 0;
	double subtotal=0;
	double totalperitem=0;
	double service_totalGrid=0;
	double subtotal1=0;

	DecimalFormat df = new DecimalFormat("#.00");

	Connection conn = null;

	try {
		// step 1
		Document document = new Document(PageSize.A4);
		
		// step 2
		
		PdfWriter.getInstance(document, response.getOutputStream());
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    PdfWriter.getInstance(document, baos);
	    
	 // step 3
		document.open();

		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smt_sc", "root", "root");
		Statement stmt = conn.createStatement();

		ResultSet rs = stmt.executeQuery("select CarNo, ItemName, CategoryName, Quantity, SalePrice, ContactNo, OwnerName, TotalAmount, Discount, GrossTotal ,Date ,totalperitem, TaxAmount,LaberCharges,discountAmt,discountGrid,Gst,HsnSacNo,service_item,service_hsn,service_quantity,service_saleprice,service_disc_grid,service_discAmt,service_gst,service_igst,service_totalGrid,service_totalAmt from customerbill where BillNo =" + billno);

		Font font17Bold = new Font(Font.FontFamily.TIMES_ROMAN, 17, Font.BOLD, BaseColor.BLACK);
		Font font16Bold = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD, BaseColor.BLACK);
		Font font14 = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD, BaseColor.BLACK);
		Font font12 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLACK);
		Font font13 = new Font(Font.FontFamily.TIMES_ROMAN, 13, Font.BOLD, BaseColor.BLACK);

		Font Normalfont12 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.BLACK);
		Font Normalfont13 = new Font(Font.FontFamily.TIMES_ROMAN, 13, Font.NORMAL, BaseColor.BLACK);
		Font Normalfont14 = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.NORMAL, BaseColor.BLACK);
		Font ufont14 = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.UNDERLINE, BaseColor.BLACK);
		Font font16BoldUnderline = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD | Font.UNDERLINE, BaseColor.RED);
		Font font25Bold = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD , BaseColor.RED);
		Font font13Bold = new Font(Font.FontFamily.TIMES_ROMAN, 13, Font.BOLD, BaseColor.BLACK);

		     // Shop Logo start
		
				Image image1 = Image.getInstance("C:/carAccessoriesLOGONew.png");
				image1.scaleToFit(150f, 150f);
				Image imageCenter = Image.getInstance(image1);
				imageCenter.setAlignment(Image.ALIGN_LEFT);
				document.add(imageCenter);
				// End	logo 
		     
		
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

		String name = rs.getString("OwnerName");
		String mono = rs.getString("ContactNo");
		String carno = rs.getString("CarNo");
		String saleDate = rs.getString("Date");
		String grossTotal123 = rs.getString("GrossTotal");
		//Header Containt Table

		PdfPTable headertable = new PdfPTable(2);
		headertable.setWidthPercentage(100);

		float[] HeadercolumnWidths = { 0.9f, 0.5f };
		headertable.setWidths(HeadercolumnWidths);

		PdfPCell headerTable_cell;
		
		
		headerTable_cell = new PdfPCell(new Phrase("Bill No : A/"+saleDate+"/00" +billno+"\n\n"));
		headerTable_cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		headerTable_cell.setColspan(2);
		headerTable_cell.setBorder(Rectangle.NO_BORDER | Rectangle.NO_BORDER);
		headertable.addCell(headerTable_cell);



		headerTable_cell = new PdfPCell(new Phrase("Date: "+ saleDate));
		headerTable_cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		headerTable_cell.setColspan(2);
		headerTable_cell.setBorder(Rectangle.NO_BORDER | Rectangle.NO_BORDER);
		headertable.addCell(headerTable_cell);

		
		headerTable_cell = new PdfPCell(new Phrase("SPEED AUTOMOBILE", font25Bold));
		headerTable_cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		headerTable_cell.setColspan(2);
		headerTable_cell.setBorder(Rectangle.NO_BORDER | Rectangle.NO_BORDER | Rectangle.NO_BORDER);
		headertable.addCell(headerTable_cell);
		
		headerTable_cell = new PdfPCell(new Phrase("\n", font25Bold));
		headerTable_cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		headerTable_cell.setColspan(2);
		headerTable_cell.setBorder(Rectangle.BOTTOM);
		headertable.addCell(headerTable_cell);
		
		headerTable_cell = new PdfPCell(new Phrase("Plot No. 101,Royal Plaza, C.D.C., Chinchwad,Purna Nagar,Pune 411 019.|+919960814821", Normalfont12));
		headerTable_cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		headerTable_cell.setColspan(2);
		headerTable_cell.setBorder(Rectangle.NO_BORDER | Rectangle.NO_BORDER | Rectangle.NO_BORDER);
		headertable.addCell(headerTable_cell);
		
		
		document.add(headertable);
		

		//information table

		PdfPTable infotable = new PdfPTable(2);
		infotable.setWidthPercentage(100);

		float[] infoColumnWidths = { 0.7f, 0.8f };
		infotable.setWidths(infoColumnWidths);

		PdfPCell InfoTable_cell;
		InfoTable_cell = new PdfPCell(new Phrase("\n\nJob Card No #206"));
		InfoTable_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		InfoTable_cell.setBorder(Rectangle.NO_BORDER);
		infotable.addCell(InfoTable_cell);


		InfoTable_cell = new PdfPCell(new Phrase("\n\nName: "+ name));
		InfoTable_cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		InfoTable_cell.setBorder(Rectangle.NO_BORDER);
		infotable.addCell(InfoTable_cell);

		InfoTable_cell = new PdfPCell(new Phrase("Car No: "+ carno));
		InfoTable_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		InfoTable_cell.setBorder(Rectangle.NO_BORDER);
		infotable.addCell(InfoTable_cell);
		
		InfoTable_cell = new PdfPCell(new Phrase("Mob No: "+ mono));
		InfoTable_cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		InfoTable_cell.setBorder(Rectangle.NO_BORDER);
		infotable.addCell(InfoTable_cell);

		/* InfoTable_cell = new PdfPCell(new Phrase("KM Reading"));
		InfoTable_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		InfoTable_cell.setBorder(Rectangle.NO_BORDER);
		infotable.addCell(InfoTable_cell); 
		
 */
		document.add(infotable);
 
    PdfPTable infotable1 = new PdfPTable(1);
	infotable1.setWidthPercentage(100);
	
	float[] infoColumnWidths1 = { 0.8f};
	infotable1.setWidths(infoColumnWidths1);
	
	PdfPCell InfoTable_cell1;

	InfoTable_cell1 = new PdfPCell(new Phrase("\n\nService Spares",font12));
	InfoTable_cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
	InfoTable_cell1.setBorder(Rectangle.BOTTOM);
	infotable1.addCell(InfoTable_cell1);
	
	
	document.add(infotable1);
		
		
		//table for particulars
		PdfPTable table = new PdfPTable(9);
		table.setWidthPercentage(100);

		float[] columnWidths = {0.3f,0.5f,0.6f,0.6f, 0.6f,0.4f,0.5f,0.4f,0.5f };
		table.setWidths(columnWidths);

		PdfPCell table_cell;
		rs.beforeFirst();
		

		table_cell = new PdfPCell(new Phrase("Sr.No",font12));
		table_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table_cell.setBorder(Rectangle.BOTTOM|Rectangle.LEFT | Rectangle.RIGHT);
		table.addCell(table_cell);
		
		
		table_cell = new PdfPCell(new Phrase("Item Name",font12));
		table_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table_cell.setBorder(Rectangle.BOTTOM|Rectangle.LEFT | Rectangle.RIGHT);
		table.addCell(table_cell);
		
		table_cell = new PdfPCell(new Phrase("HSN/SAC",font12));
		table_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table_cell.setBorder(Rectangle.BOTTOM|Rectangle.LEFT | Rectangle.RIGHT);
		table.addCell(table_cell);

		table_cell = new PdfPCell(new Phrase("Rate",font12));
		table_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table_cell.setBorder(Rectangle.BOTTOM|Rectangle.LEFT | Rectangle.RIGHT);
		table.addCell(table_cell);
		
		table_cell = new PdfPCell(new Phrase("Qty",font12));
		table_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table_cell.setBorder(Rectangle.BOTTOM|Rectangle.LEFT | Rectangle.RIGHT);
		table.addCell(table_cell);


		table_cell = new PdfPCell(new Phrase("Disc%",font12));
		table_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table_cell.setBorder(Rectangle.BOTTOM|Rectangle.LEFT | Rectangle.RIGHT);
		table.addCell(table_cell);

		table_cell = new PdfPCell(new Phrase("Disc Amt",font12));
		table_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table_cell.setBorder(Rectangle.BOTTOM|Rectangle.LEFT | Rectangle.RIGHT);
		table.addCell(table_cell);

		table_cell = new PdfPCell(new Phrase("GST%",font12));
		table_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table_cell.setBorder(Rectangle.BOTTOM|Rectangle.LEFT | Rectangle.RIGHT);
		table.addCell(table_cell);
		
		table_cell = new PdfPCell(new Phrase("Amount",font12));
		table_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table_cell.setBorder(Rectangle.BOTTOM|Rectangle.LEFT | Rectangle.RIGHT);
		table.addCell(table_cell);

		
		int srno=1;
		while (rs.next()) {
			
			table_cell = new PdfPCell(new Phrase(""+srno, Normalfont12));
			table_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			table_cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
			table.addCell(table_cell);
			
			srno++;	

			String ItemName = rs.getString("ItemName");
			table_cell = new PdfPCell(new Phrase("\n " + ItemName));
			table_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			table_cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
			table.addCell(table_cell);

			String HsnSacNo = rs.getString("HsnSacNo");
			table_cell = new PdfPCell(new Phrase("\n " + HsnSacNo));
			table_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			table_cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
			table.addCell(table_cell);

			String SalePrice = String.valueOf(rs.getDouble("SalePrice"));
			table_cell = new PdfPCell(new Phrase("\n " + SalePrice));
			table_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			table_cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
			table.addCell(table_cell);

			
			
			String Quantity = String.valueOf(rs.getString("Quantity"));
			table_cell = new PdfPCell(new Phrase("\n " + Quantity));
			table_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			table_cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
			table.addCell(table_cell);

			
			String discountGrid = String.valueOf(rs.getDouble("discountGrid"));
			table_cell = new PdfPCell(new Phrase("\n " + discountGrid));
			table_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			table_cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
			table.addCell(table_cell);

			String discountAmt = String.valueOf(rs.getDouble("discountAmt"));
			table_cell = new PdfPCell(new Phrase("\n " + discountAmt));
			table_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			table_cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
			table.addCell(table_cell);

			String Gst = String.valueOf(rs.getDouble("Gst"));
			table_cell = new PdfPCell(new Phrase("\n " + Gst));
			table_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			table_cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
			table.addCell(table_cell);

			totalperitem = rs.getDouble("totalperitem");
			subtotal=subtotal+totalperitem;
			//roudoffsubtotal=Math.round(subtotal * 100) / 100;
			
			table_cell = new PdfPCell(new Phrase(String.valueOf(totalperitem), Normalfont12));
			table_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			table_cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
			table.addCell(table_cell);
			
		}

		document.add(table);
		//footer of service spares
		    PdfPTable table5 = new PdfPTable(2);
			table5.setWidthPercentage(100);
			
			
			float[] columnWidths5 = {2.0f,0.5f};
			table5.setWidths(columnWidths5);

			PdfPCell table_cell5;
			
			table_cell5 = new PdfPCell(new Phrase("Spare Total"));
			table_cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
			//table_cell5.setBorder(Rectangle.RIGHT|Rectangle.TOP);
			table5.addCell(table_cell5);
			
			
			table_cell5 = new PdfPCell(new Phrase(String.valueOf(subtotal), Normalfont12));
			table_cell5.setHorizontalAlignment(Element.ALIGN_RIGHT);
			//table_cell5.setBorder(Rectangle.TOP);
			table5.addCell(table_cell5);
			
			
			document.add(table5);
			
	///////service tasks///////		
			
			 PdfPTable infotable2 = new PdfPTable(1);
	         infotable2.setWidthPercentage(100);
	
	float[] infoColumnWidths2 = { 0.8f};
	infotable2.setWidths(infoColumnWidths2);
	
	PdfPCell InfoTable_cell2;

	InfoTable_cell2 = new PdfPCell(new Phrase("\n\nService Spares",font12));
	InfoTable_cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
	InfoTable_cell2.setBorder(Rectangle.BOTTOM);
	infotable2.addCell(InfoTable_cell2);
	
	
	document.add(infotable2);
			
	//
	 	//table for particulars
		PdfPTable table1 = new PdfPTable(8);
		table1.setWidthPercentage(100);

		float[] columnWidths1 = {0.5f,0.6f,0.6f, 0.6f,0.4f,0.5f,0.4f,0.5f };
		table1.setWidths(columnWidths1);

		PdfPCell table_cell1;
		rs.beforeFirst();
		

		/* table_cell1 = new PdfPCell(new Phrase("Sr.No",font12));
		table_cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
		table_cell1.setBorder(Rectangle.BOTTOM|Rectangle.LEFT | Rectangle.RIGHT);
		table1.addCell(table_cell1);
		 */
		
		table_cell1 = new PdfPCell(new Phrase("Item Name",font12));
		table_cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
		table_cell1.setBorder(Rectangle.BOTTOM|Rectangle.LEFT | Rectangle.RIGHT);
		table1.addCell(table_cell1);
		
		table_cell1 = new PdfPCell(new Phrase("HSN/SAC",font12));
		table_cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
		table_cell1.setBorder(Rectangle.BOTTOM|Rectangle.LEFT | Rectangle.RIGHT);
		table1.addCell(table_cell1);

		table_cell1 = new PdfPCell(new Phrase("Rate",font12));
		table_cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
		table_cell1.setBorder(Rectangle.BOTTOM|Rectangle.LEFT | Rectangle.RIGHT);
		table1.addCell(table_cell1);
		
		table_cell1 = new PdfPCell(new Phrase("Qty",font12));
		table_cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
		table_cell1.setBorder(Rectangle.BOTTOM|Rectangle.LEFT | Rectangle.RIGHT);
		table1.addCell(table_cell1);


		table_cell1 = new PdfPCell(new Phrase("Disc%",font12));
		table_cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
		table_cell1.setBorder(Rectangle.BOTTOM|Rectangle.LEFT | Rectangle.RIGHT);
		table1.addCell(table_cell1);

		table_cell1 = new PdfPCell(new Phrase("Disc Amt",font12));
		table_cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
		table_cell1.setBorder(Rectangle.BOTTOM|Rectangle.LEFT | Rectangle.RIGHT);
		table1.addCell(table_cell1);

		table_cell1 = new PdfPCell(new Phrase("GST%",font12));
		table_cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
		table_cell1.setBorder(Rectangle.BOTTOM|Rectangle.LEFT | Rectangle.RIGHT);
		table1.addCell(table_cell1);
		
		table_cell1 = new PdfPCell(new Phrase("Amount",font12));
		table_cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
		table_cell1.setBorder(Rectangle.BOTTOM|Rectangle.LEFT | Rectangle.RIGHT);
		table1.addCell(table_cell1); 

		
		 //int srno1=1;
		while (rs.next()) {
			
			/* table_cell1 = new PdfPCell(new Phrase(""+srno1, Normalfont12));
			table_cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table_cell1.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
			table1.addCell(table_cell1);
			
			srno1++; */	

			String service_item = rs.getString("service_item");
			table_cell1 = new PdfPCell(new Phrase("\n " + service_item));
			table_cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table_cell1.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
			table1.addCell(table_cell1);

			String service_hsn = rs.getString("service_hsn");
			table_cell1 = new PdfPCell(new Phrase("\n " + service_hsn));
			table_cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table_cell1.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
			table1.addCell(table_cell1);

			String service_saleprice = String.valueOf(rs.getDouble("service_saleprice"));
			table_cell1 = new PdfPCell(new Phrase("\n " + service_saleprice));
			table_cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table_cell1.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
			table1.addCell(table_cell1);

			
			
			String service_quantity = String.valueOf(rs.getString("service_quantity"));
			table_cell1 = new PdfPCell(new Phrase("\n " + service_quantity));
			table_cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table_cell1.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
			table1.addCell(table_cell1);

			
			String service_disc_grid = String.valueOf(rs.getDouble("service_disc_grid"));
			table_cell1 = new PdfPCell(new Phrase("\n " + service_disc_grid));
			table_cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table_cell1.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
			table1.addCell(table_cell1);

			String service_discAmt = String.valueOf(rs.getDouble("service_discAmt"));
			table_cell1 = new PdfPCell(new Phrase("\n " + service_discAmt));
			table_cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table_cell1.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
			table1.addCell(table_cell1);

			String service_gst = String.valueOf(rs.getDouble("service_gst"));
			table_cell1 = new PdfPCell(new Phrase("\n " + service_gst));
			table_cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table_cell1.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
			table1.addCell(table_cell1);

			service_totalGrid = rs.getDouble("service_totalGrid");
			subtotal1=subtotal1+service_totalGrid;
			//roudoffsubtotal=Math.round(subtotal * 100) / 100;
			
			table_cell1 = new PdfPCell(new Phrase(String.valueOf("\n"+service_totalGrid), Normalfont12));
			table_cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table_cell1.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
			table1.addCell(table_cell1);
			
		} 

		document.add(table1);
			
		//footer of service task
	    PdfPTable table6 = new PdfPTable(2);
		table6.setWidthPercentage(100);
		
		
		float[] columnWidths6 = {2.0f,0.6f};
		table6.setWidths(columnWidths6);

		PdfPCell table_cell6;
		
		table_cell6 = new PdfPCell(new Phrase("Service Tasks"));
		table_cell6.setHorizontalAlignment(Element.ALIGN_LEFT);
		//table_cell6.setBorder(Rectangle.RIGHT|Rectangle.TOP);
		table6.addCell(table_cell6);
		
		
		table_cell6 = new PdfPCell(new Phrase(String.valueOf(subtotal1), Normalfont12));
		table_cell6.setHorizontalAlignment(Element.ALIGN_RIGHT);
		//table_cell6.setBorder(Rectangle.TOP);
		table6.addCell(table_cell6);
		
		document.add(table6);	
			
	///////gross total///////
	 PdfPTable infotable3 = new PdfPTable(2);
	infotable3.setWidthPercentage(100);
	
	float[] infoColumnWidths3 = { 2.0f,0.5f};
	infotable3.setWidths(infoColumnWidths3);
	
	PdfPCell InfoTable_cell3;

	InfoTable_cell3 = new PdfPCell(new Phrase("\nGross Total",font14));
	InfoTable_cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
	InfoTable_cell3.setBorder(Rectangle.NO_BORDER);
	infotable3.addCell(InfoTable_cell3);
	
	 
	InfoTable_cell3 = new PdfPCell(new Phrase("\n"+grossTotal123,font14));
	InfoTable_cell3.setHorizontalAlignment(Element.ALIGN_RIGHT);
	InfoTable_cell3.setBorder(Rectangle.NO_BORDER);
	infotable3.addCell(InfoTable_cell3);
	 
	
	long longGrandTotalAmt = Math.round(Double.valueOf(grossTotal123));
	String strGrandTotalAmt = String.valueOf(longGrandTotalAmt);
	int intGrandTotalAmt = Integer.parseInt(strGrandTotalAmt);
	NumToWord w = new NumToWord();
	String amtInWord = w.convert(intGrandTotalAmt);

	InfoTable_cell3 = new PdfPCell(new Phrase("\n Rupees in words : " + amtInWord + " Only/- "));
	InfoTable_cell3.setHorizontalAlignment(Element.ALIGN_RIGHT);
	InfoTable_cell3.setBorder(Rectangle.NO_BORDER);
	InfoTable_cell3.setColspan(7);
	infotable3.addCell(InfoTable_cell3); 
	
	
	document.add(infotable3);
		
	////footer///
	
	
		PdfPTable headertable1 = new PdfPTable(4);
		headertable1.setWidthPercentage(100);

		float[] HeadercolumnWidths1 = { 0.4f, 0.3f,0.4f,0.4f };
		headertable1.setWidths(HeadercolumnWidths1);

		PdfPCell headerTable_cell1;
		
		
		headerTable_cell1 = new PdfPCell(new Phrase("\nspeed Automobile \n +919960814821 \n vkamthe757@gmail.com\nPlot No.101,Royal Plaza, C.D.C.,\n Chinchwad,Purna Nagar,Pune 411 019",Normalfont12));
		headerTable_cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
		//headerTable_cell1.setColspan(2);
		headerTable_cell1.setBorder(Rectangle.TOP | Rectangle.BOTTOM| Rectangle.LEFT|Rectangle.RIGHT);
		headertable1.addCell(headerTable_cell1);


		headerTable_cell1 = new PdfPCell(new Phrase("\n\n\n\n\n\nCustomer's Signature",Normalfont12));
		//headerTable_cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
		headerTable_cell1.setColspan(2);
		headerTable_cell1.setBorder(Rectangle.TOP | Rectangle.BOTTOM| Rectangle.LEFT|Rectangle.RIGHT);
		headertable1.addCell(headerTable_cell1);

		headerTable_cell1 = new PdfPCell(new Phrase("\n\n\n\n\n\nAuthorised Signatory.\n Speed Automobile"));
		//headerTable_cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
		headerTable_cell1.setColspan(2);
		headerTable_cell1.setBorder(Rectangle.TOP | Rectangle.BOTTOM| Rectangle.LEFT|Rectangle.RIGHT);
		headertable1.addCell(headerTable_cell1);

	
	document.add(headertable1);
		
	
			

		// step 5
		rs.close();
		stmt.close();
		conn.close();
		document.close();
		
		////////email functionality/////// 
		try{
	          Session mailSession = Session.getInstance(System.getProperties());
	          Transport transport = new SMTPTransport(mailSession,new URLName("smtp.gmail.com"));
	          transport = mailSession.getTransport("smtps");
	          transport.connect("smtp.gmail.com", 465 ,"embelmessanger@gmail.com","embel@123");
	          
	          MimeMessage m = new MimeMessage(mailSession); 
	          m.setFrom(new InternetAddress("embelbackup@gmail.com"));
	          Address[] toAddr = new InternetAddress[] {
	          new InternetAddress("embelbackup@gmail.com")
	          };
	          m.setRecipients(javax.mail.Message.RecipientType.TO, toAddr );
	          m.setHeader("Content-Type", "multipart/mixed");
	          m.setSubject("customer Bill");
	          m.setSentDate(new java.util.Date());

	          MimeBodyPart messageBodyPart = new MimeBodyPart();
	          messageBodyPart.setText("");
	          Multipart multipart = new MimeMultipart();
	          multipart.addBodyPart(messageBodyPart);

	          
	          messageBodyPart = new MimeBodyPart();
	          DataSource source = new ByteArrayDataSource(baos.toByteArray(), "application/pdf");
	          messageBodyPart.setDataHandler(new DataHandler(source));
	          messageBodyPart.setFileName("car_bill__Pdf.pdf");
	          multipart.addBodyPart(messageBodyPart);
				
	       
	          m.setContent(multipart);

	          transport.sendMessage(m,m.getAllRecipients());
	          transport.close();
	          out.println("Thanks for sending mail!");
	        }
	        catch(Exception e){
	          out.println(e.getMessage());
	          e.printStackTrace();
	        }

		

	} catch (DocumentException de) {
		throw new IOException(de.getMessage());
	}
%>

		