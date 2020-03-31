
<%@page import="com.smt.hibernate.SupplierDetail"%>
<%@page import="com.smt.helper.SupplierDetailHelper"%>
<%@page import="java.util.List"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%
	boolean isHome = false;
%>
<%@include file="y_commons/header1.jsp"%>

 <script src="/SMT/staticContent/js/jquery-1.12.3.min.js"></script>
	     <link rel="stylesheet" href="/SMT/staticContent/y_css/ui.jqgrid.min.css">
	     <link rel="stylesheet" href="/SMT/staticContent/y_css/jquery-ui.css">
	     <link rel="stylesheet" href="/SMT/staticContent/y_css/ui.jqgrid.css">
	     <script src="/SMT/staticContent/y_js/jquery-ui.min.js"></script>
	     <script src="/SMT/staticContent/js/jquery-ui.js"></script>
	     <script src="/SMT/staticContent/js/jqueryUi.js"></script>
	     <script src="/SMT/staticContent/y_js/jquery.jqgrid.min.js"></script>

    <script src="/SMT/staticContent/js/goodsReturn.js"></script>
	
    
     <body class="master_form_img">
	<div class="container-fluid">

		<div class="row header_margin_top">
			<div align="center">
				<h2 class="form-name style_heading">Purchase Return</h2>
			</div>
		</div>


		<div class="row">
			<div class="form-group" align="right">
				<div class="col-sm-offset-6 col-md-5 control-label">
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
			<div class="col-sm-offset-1 col-md-10">
				<hr style="border-top-color: #c1b1b1;">
			</div>
		</div>


		<form action="supplier" name="supd" method="post" class="form-horizontal">

			<div class="row">
				<div class="form-group">
					<div class="col-sm-2 col-sm-offset-1" align="center">
					   <label class="control-label">Supplier Name:</label>
					</div>
					
					<%
							SupplierDetailHelper poHelper = new SupplierDetailHelper();
							List supplierList = poHelper.getAllSuppliers();
					%>
					
					
					<div class="col-sm-3">
						
						<div class="input-group">
							<span class="input-group-addon"> <i
								class="glyphicon glyphicon-hand-right"></i>
							</span> 
							<input list="supplierId_drop" id="supplierId" class="form-control" onchange="getAllBills()">
							<datalist id="supplierId_drop">
								<%
									for (int i = 0; i < supplierList.size(); i++) {
										SupplierDetail supplier = (SupplierDetail) supplierList.get(i);
								%>
								<option data-value="<%=supplier.getSupplierId()%>"
									value="<%=supplier.getSupplierName()%>">
									<%
										}
									%>
								
							</datalist>
						</div>
					</div>
					
					<div class="col-sm-2" align="center">
						<label class="control-label"> Bill No:</label>
					</div>
					<div class="col-sm-3">
						
						<div class="input-group">
							<span class="input-group-addon">
										No
									</span>
           		 					
           		 					<select class="form-control input-md" id='billNo'  name="billNo" onchange="getitems()">
									</select>

						</div>
					</div>

					
					

				</div>
        	</div>
			</form>	
				
		</div>	
<div class="container-fluid" style="margin-left: 90px;">
	<div class="row">
		<div class="col-sm-12">
			<div class="table-responsive">
				<table id="jqGrid" ></table>
			    <div id="jqGridPager"></div>
			</div>
		</div>
	</div>
</div>
	
	

	<div class="row buttons_margin_top">
		<div align="center">
			<input type="button" value="Save"  id="btn" onclick="purchasereturn()" class="btn btn-lg btn-success btn-md button_hw button_margin_right"/> 
			
		</div>
	</div>
	
	 
	
	<div class="row footer_margin_top" align="center">
			<%@include file="y_commons/footer.jsp"%>
		</div> 
  
   </body>
  </html>
  
  

	