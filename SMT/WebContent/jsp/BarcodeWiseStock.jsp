<%@page import="com.smt.hibernate.Category"%>
<%@page import="com.smt.helper.CategoryHelper"%>
<%@page import="com.smt.hibernate.SupplierDetail"%>
<%@page import="com.smt.helper.SupplierDetailHelper"%>
<%@page import="java.util.List"%>

<%@page import="com.smt.hibernate.GoodReceive"%>
<%@page import="com.smt.helper.GoodReceiveHelper"%>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<% boolean isHome=false;%>
<%@include file="y_commons/header1.jsp"%>

	     <script src="/SMT/staticContent/js/jquery-1.12.3.min.js"></script>
         <script src="/SMT/staticContent/shree/jquery.dataTables.min.js" type="text/javascript"></script>
         <script src="/SMT/staticContent/shree/dataTables.buttons.min.js" type="text/javascript"></script>
         <script src="/SMT/staticContent/shree/buttons.flash.min.js" type="text/javascript"></script>
         <script src="/SMT/staticContent/shree/jszip.min.js" type="text/javascript"></script>
         <script src="/SMT/staticContent/shree/pdfmake.min.js" type="text/javascript"></script>
         <script src="/SMT/staticContent/shree/vfs_fonts.js" type="text/javascript"></script>
         <script src="/SMT/staticContent/shree/buttons.html5.min.js" type="text/javascript"></script>
         <script src="/SMT/staticContent/shree/buttons.print.min.js" type="text/javascript"></script>
         <link href="/SMT/staticContent/y_css/jquery.dataTables.min.css" rel="stylesheet" type="text/css" media="all" />
         <link href="/SMT/staticContent/y_css/buttons.dataTables.min.css" rel="stylesheet" type="text/css" media="all">
        
        
        
		<script src="/SMT/staticContent/js/currentStock.js"></script>
	
       
	
	 <body class="stock_form_img">
	<div class="container-fluid">
	
		<div class="row header_margin_top">
			<div align="center">
				<h2 class="form-name style_heading">BarcodeWise Stock</h2>
			</div>
		</div>
		
		<div class="row">
			<div class="form-group" align="right">
				<div class="col-sm-offset-8 col-md-4 control-label">
					<div id="date">
						<label id="demo"></label>
						<script>
							   var date = new Date();
							   document.getElementById("demo").innerHTML = date.toDateString();
							</script>
					</div>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-md-12">
				<hr style="border-top-color: #c1b1b1;">
			</div>
		</div>
		
		
<form  method="post" class="form-horizontal">

			<div class="row">
				<div class="row form-group" style="margin-top: 20px">
						<label class="col-md-3 control-label" for="">
							Barcode No:<sup>*</sup>
						</label>
						<%
					    GoodReceiveHelper helper = new GoodReceiveHelper();
						List mainCategoryList = helper.getBarcode();
					%>
						<div class="col-md-3">
							<div class="input-group">
								<span class="input-group-addon"> <i
									class="glyphicon glyphicon-user"></i>
								</span> <!-- <input type="text" id="barcode" placeholder="Barcode No"
									class="form-control input-md" type="text"> -->
									
									<input list="barcode_drop" id="barcode" class="form-control">
					<datalist id="barcode_drop">
						<%
							for(int i=0;i<mainCategoryList.size();i++){
								GoodReceive category = (GoodReceive)mainCategoryList.get(i);
						%>
							<option data-value="<%=category.getBarcodeNo()%>" value="<%=category.getBarcodeNo()%>">
						<%
							}
						%>
					</datalist>
							</div>
						</div>
			
			
						<div class="col-md-3">
							<div class="input-group">
								<input type="button" id="btn" name="save"
									class="btn btn-lg btn-success btn-md button_hw button_margin_right"
									onclick="barcodewisestock()" value="Search" />
							</div>
						</div>
					</div>
			</div>
		
		
			
			<br>
	<br> <br>
	<br>

<div class="row">
			  <div class="table-responsive">
	<table class= "table table-bordered table-striped table-condensed cf" id="example" class="display" style=" border: 2px solid black; border-collapse: collapse;">
		<thead>
			<tr>
				<th> Category Name</th>
				<th> Item Name</th>
				<th> Available Quantity</th>
				<th> Buy Price</th>
				<th> Tax Percentage</th>
				<th> Bill No</th>
				<th> Barcode No</th>
				<th>Total</th>
			</tr>
		</thead>
		
	</table>
	</div></div>

</form>

		<div class="row footer_margin_top" align="center">
			<%@include file="y_commons/footer.jsp"%>
		</div>

	</div>
   </body>
  </html>