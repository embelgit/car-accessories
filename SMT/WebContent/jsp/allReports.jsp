
<%@page import="java.util.List"%>
<%@page import="com.smt.helper.CategoryHelper"%>
<%@page import="com.smt.hibernate.Category"%>
<%@page import="com.smt.hibernate.CustomerDetailsBean"%>
<%@page import="com.smt.bean.CategoryDetails"%>
<%@page import="com.smt.bean.BillCopy"%>
<%@page import="com.smt.dao.CarEntryDao"%>
<%@page import="com.smt.dao.OtherBillDao"%>
<%@page import="com.smt.dao.CreditCustBillDao"%>
<%@page import="com.smt.dao.CustomerDetailsDao"%>
<%@page import="com.smt.hibernate.SupplierDetail"%>
<%@page import="com.smt.dao.SupplierDetailDao"%>

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
	
	<script src="/SMT/staticContent/js/json2xml.js"></script>
	<script src="/SMT/staticContent/js/allReport.js"></script>


<div class="row" style="min-height:300px;">
    <div  class="col-md-12">
        <h3>Left Tabs</h3>
        <hr/>
        <div class="col-md-2">
            <ul class="nav nav-tabs tabs-left">
                <li class="active"><a href="#purchase" data-toggle="tab">Purchase Reports</a></li>
                <li><a href="#sell" data-toggle="tab">Sale Reports</a></li>
                <li><a href="#stock" data-toggle="tab">Stock Reports</a></li>
                <li><a href="#vehicle" data-toggle="tab">Vehicle Bill Reports</a></li>
                <li><a href="#miscellaneous" data-toggle="tab">Miscellaneous Bill Reports</a></li>
                <li><a href="#credit" data-toggle="tab">Credit Customer Bill Reports</a></li>
            </ul>
        </div>
  <div class="col-xs-9">
            <!-- Tab panes -->
    <div class="tab-content">
    	
    <!---------- 	supplier Payment reports -------------->
    
    <div class="tab-pane active" id="purchase">
       		<div class="row">
				    <div align="center">
				  		<h2 class="form-name style_heading">Purchase Reports</h2>
				  	</div>
				 	
			     <div class="row">
					     <div class="col-sm-offset-1 col-md-10">
							  		<hr style="border-top-color:#c1b1b1;">
					     </div>	
			   		 </div>
			    </div>
			    
		<ul class="nav nav-tabs">
		    <li class="active"><a data-toggle="tab" href="#supplierName"><h4 style="color:blue">Supplier Name Wise</h4></a></li>
		    <li ><a data-toggle="tab" href="#categoryWise"><h4 style="color:blue">Category Wise</h4></a></li>
			<li ><a data-toggle="tab" href="#supplierBillWise"><h4 style="color:blue">Bill Number Wise</h4></a></li>
		    <li><a data-toggle="tab" href="#barcodeNoWise"><h4 style="color:blue">Barcode No Wise</h4></a></li>
		    <li ><a data-toggle="tab" href="#singleDatePurchase"><h4 style="color:blue">Single Date</h4></a></li>
		    <li><a data-toggle="tab" href="#twoDatePurchase"><h4 style="color:blue">Range</h4></a></li>
		   
	 	</ul>
         	 
   <div class="tab-content" style="float: left"> 
   
   <!-- Supplier Name Wise -->
     <div id="supplierName" class="tab-pane fade in active">
     	<div class="row">
					<div class="col-sm-offset-1 col-md-10">
						<hr style="border-top-color: #c1b1b1;">
					</div>
				</div>
		<form class="form-horizontal" method="post" action="" name="supReportBill">
			<fieldset>

						<div class="row form-group">
							 <label class="col-md-3 control-label" for="supplier">Supplier Name</label>  
          					  <div class="col-md-3">
								<div class="input-group">
									<span class="input-group-addon">
										<i class="	glyphicon glyphicon-hand-right"></i>
									</span>
									
							
										
							<!-- Following code is to get Supplier from "supplier_details" table of "fertilizer" DB -->
							<!-- getAllSupllier() is implemented in  SupplierDetailsDao with return type List-->
						
							<%
								SupplierDetailDao sdd88 = new SupplierDetailDao();
           						List sList88 =sdd88.getAllSupplier();
							
							%>
							
							<input list="sup_drop7" id="supplier7"  class="form-control">
				<datalist id="sup_drop7">
							
							<%
					           for(int i=0;i<sList88.size();i++){
					        	   SupplierDetail sup88 =(SupplierDetail)sList88.get(i);
							%>
		
							<option data-value="<%=sup88.getSupplierId()%>" value="<%=sup88.getSupplierName() %>">
							<%
				      			}
				    		%>
						</datalist>           	
					</div>
           		</div>
            
							<!-- <div class="col-md-3 "> -->
								<div class="input-group">
									<input type="button" id="btn" name="save"
										class="btn btn-lg btn-success btn-md button_hw button_margin_right"
										onclick="supplierAllPurchase()" value="Search" />
								</div>
							<!-- </div> -->
					</div>	
						<div	class="table-responsive">
						<table class= "table table-bordered table-striped table-condensed cf" id="supplierNameWiseTable" class="display" style=" border: 2px solid black;border-collapse: collapse;">
							<thead>
								<tr>
								                    <th>Sr No</th>
								                    <th>Date</th>
								                    <th>Bill No</th>
								                    <th>Contact Person</th>
													<th>Supplier Name</th>
													<th>Category Number</th>
													<th>Item Name</th>
													<th>HsnSac No</th>
													<th>Quantity</th>
													<th>Buy Price</th>
													<th>Gst</th>
													<th>Igst</th>
													<th>Tax Amount</th>
													<!-- <th>Discount</th> -->
													<th>Total Amount</th>
													<th>Overall Discount</th>
													<th>Expenses</th>
													<th>Expenses Tax</th>
													<th>Total Expenses</th>
													<th>Gross Amount</th>
													
								</tr>
							</thead>
							<tfoot>
								<tr>
									<th colspan="8" style="text-align: right">Total:</th>
									<th></th>
									<th></th>
									<th></th>
									<th></th>
									<th></th>
									<th></th>
									<th></th>
									<th></th>
									<th></th>
									<th></th>
									<th></th>
									<!-- <th></th> -->
				 
				
								</tr>
							</tfoot>
						</table>
					</div>
					</fieldset>
				</form>
     
     
     </div>
    <!-- Bill number wise -->
    <div id="supplierBillWise" class="tab-pane ">
		        <div class="row">
					<div class="col-sm-offset-1 col-md-10">
						<hr style="border-top-color: #c1b1b1;">
					</div>
				</div>
		<form class="form-horizontal" method="post" action="" name="supReportBill">
			<fieldset>

						<div class="row form-group">
							 <label class="col-md-2 control-label" for="supplier">Supplier Name</label>  
          					  <div class="col-md-3">
								<div class="input-group">
									<span class="input-group-addon">
										<i class="	glyphicon glyphicon-hand-right"></i>
									</span>
									
							
										
							<!-- Following code is to get Supplier from "supplier_details" table of "fertilizer" DB -->
							<!-- getAllSupllier() is implemented in  SupplierDetailsDao with return type List-->
						
							<%
								SupplierDetailDao sdd = new SupplierDetailDao();
           						List sList =sdd.getAllSupplier();
							
							%>
							
							<input list="sup_drop" id="supplier" onchange="getAllBills();" class="form-control">
				<datalist id="sup_drop">
							
							<%
					           for(int i=0;i<sList.size();i++){
					        	   SupplierDetail sup =(SupplierDetail)sList.get(i);
							%>
		
							<option data-value="<%=sup.getSupplierId()%>" value="<%=sup.getSupplierName() %>">
							<%
				      			}
				    		%>
						</datalist>           	
					</div>
           		</div>
            
           				    <label class="col-md-1 control-label" for="bill_no"> Bill No </label>  
          					  <div class="col-md-3">
								<div class="input-group">
									<span class="input-group-addon">
										No
									</span>
           		 					
           		 					<select class="form-control input-md" id='billNo'  name="billNo" >
									</select>
           						 </div>
							</div>
				
							<div class="col-md-2">
								<div class="input-group">
									<input type="button" id="btn" name="save"
										class="btn btn-lg btn-success btn-md button_hw button_margin_right"
										onclick="supplierBillWisePurchaseReport()" value="Search" />
								</div>
							</div>
					</div>	
						<div	class="table-responsive">
						<table class= "table table-bordered table-striped table-condensed cf"  id="supplierBillWiseData" class="display" style=" border: 2px solid black;border-collapse: collapse;">
							<thead>
								<tr>
													<th>Sr No</th>
								                    <th>Date</th>
								                    <th>Bill No</th>
								                    <th>Contact Person</th>
													<th>Supplier Name</th>
													<th>Category Number</th>
													<th>Item Name</th>
													<th>HsnSac No</th>
													<th>Quantity</th>
													<th>Buy Price</th>
													<th>Gst</th>
													<th>Igst</th>
													<th>Tax Amount</th>
													<!-- <th>Discount</th> -->
													<th>Total Amount</th>
													<th>Overall Discount</th>
													<th>Expenses</th>
													<th>Expenses Tax</th>
													<th>Total Expenses</th>
													<th>Gross Amount</th>
								</tr>
							</thead>
							<tfoot>
								<tr>
									<th colspan="8" style="text-align: right">Total:</th>
									<th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><!-- <th></th> -->
				
				
								</tr>
							</tfoot>
						</table>
					</div>
					</fieldset>
				</form>
         
       </div>
    
    
    <!--    Category Wise Purchase -->
	<div id="categoryWise" class="tab-pane">
		        <div class="row">
					<div class="col-sm-offset-1 col-md-10">
						<hr style="border-top-color: #c1b1b1;">
					</div>
				</div>
		<form class="form-horizontal" method="post" action="" name="supReport">
			<fieldset>

						<div class="form-group">
								<label class="col-md-3 control-label" for="">
								       Select Category:<sup>*</sup>
							    </label>
									<%	
										CategoryHelper ch45 = new CategoryHelper();
										List list45 = ch45.getCategorys();
									%>
									<div class="col-sm-3">
										<input list="catId_drop45" id="catId45" class="form-control">
					  						<datalist id="catId_drop45" >
					    	
											<%
												for(int i=0;i<list45.size();i++){
													CategoryDetails item45 = (CategoryDetails)list45.get(i);
											%>
												<option data-value="<%=item45.getCatName()%>" value="<%=item45.getCatName()%>">
											<%
												}
											%>
										  </datalist>
									</div>
				
								
							
            	
							<div class="col-md-3 ">
								<div class="input-group">
									<input type="button" id="btn45" name="save"
										class="btn btn-lg btn-success btn-md button_hw button_margin_right"
										onclick="categoryWisePurchaseReport()" value="Search" />
								</div>
							</div>
					</div>
						<div	class="table-responsive">
						<table class= "table table-bordered table-striped table-condensed cf"  id="supplierCategoryWise" class="display" style=" border: 2px solid black;border-collapse: collapse;">
							<thead>
								<tr>
													
													<th>Sr No</th>
								                    <th>Date</th>
								                    <th>Bill No</th>
													<th>Supplier Name</th>
													<th>Category Number</th>
													<th>Item Name</th>
													<th>HsnSac No</th>
													<th>Quantity</th>
													<th>Buy Price</th>
													<th>Gst</th>
													<th>Igst</th>
													<th>Tax Amount</th>
													<th>Total Amount</th>
													<th>Barcode No</th>
								</tr>
							</thead>
							<tfoot>
								<tr>
								<th colspan="7" style="text-align: right">Total:</th>
								<th></th><th></th><th></th><th></th><th></th><th></th><th></th>
				
				
							    </tr>
							</tfoot>
						</table>
					</div>
					</fieldset>
				</form>
         
       </div>
        	
        <!------ 	Barcode No wise Purchase  ----->
        	  <div id="barcodeNoWise" class="tab-pane fade">
				
				     <div class="row">
						     <div class="col-sm-offset-1 col-md-10">
								  		<hr style="border-top-color:#c1b1b1;">
						     </div>	
				    </div>
				
				
				<form class="form-horizontal" method="post" action="" name="supReport1">
					<fieldset>
				         <div class="row form-group">
							<label class="col-md-3 control-label" for="startDate"> Enter Barcode No:<sup>*</sup></label>  
				           			 <div class="col-md-3">
										<div class="input-group">
											<span class="input-group-addon">
												<i class="glyphicon glyphicon-user"></i>
											</span>
				           		 			  <input type="text" id="barcodeNoOurchase" placeholder="Barcode No" class="form-control input-md" type="text" >
				           		 		</div>
									</div>
									
									<div class="col-md-3 ">
								        <div class="input-group">
									         <input type="button" id="btn" name="save"
										        class="btn btn-lg btn-success btn-md button_hw button_margin_right"
										          onclick="barcodeWisePurchaseReport()" value="Search" />
								       </div>
							   </div>
				          </div>
					  
						
						<table class= "table table-bordered table-striped table-condensed cf"  id="barcodeWisePurchaseReport" class="display"  style=" border: 2px solid black;border-collapse: collapse;">
						<thead>
							<tr>
													<th>Sr No</th>
								                    <th>Date</th>
								                    <th>Bill No</th>
													<th>Supplier Name</th>
													<th>Category Number</th>
													<th>Item Name</th>
													<th>HsnSac No</th>
													<th>Quantity</th>
													<th>Buy Price</th>
													<th>Gst</th>
													<th>Igst</th>
													<th>Tax Amount</th>
													<th>Total Amount</th>
													<th>Barcode No</th>
													
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th colspan="7" style="text-align: right">Total:</th>
								<th></th><th></th><th></th><th></th><th></th><th></th><th></th>
				
				
							</tr>
						</tfoot>
					</table>
				</fieldset>
				</form>
        	  	
        	  
        	  </div>
        	  
           <!------ 	Single Date Purchase  ----->
        	  <div id="singleDatePurchase" class="tab-pane fade">
				
				     <div class="row">
						     <div class="col-sm-offset-1 col-md-10">
								  		<hr style="border-top-color:#c1b1b1;">
						     </div>	
				    </div>
				
				
				<form class="form-horizontal" method="post" action="" name="supReport1">
					<fieldset>
				         <div class="row form-group">
							<label class="col-md-2 control-label" for="startDate"> Start Date:<sup>*</sup></label>  
				           			 <div class="col-md-3">
										<div class="input-group">
											<span class="input-group-addon">
												<i class="glyphicon glyphicon-user"></i>
											</span>
				           		 			  <input type="date" id="purDate" placeholder="Start Date" class="form-control input-md" type="text" >
				           		 		</div>
									</div>
									
				               <div class="col-md-3 ">
								  <div class="input-group">
									<input type="button" id="btn" name="save"
										class="btn btn-lg btn-success btn-md button_hw button_margin_right"
										onclick="singleDatePurchase45()" value="Search" />
								  </div>
							   </div>
						</div>
						<table class= "table table-bordered table-striped table-condensed cf"  id="singleDatePurchase50" class="display"  style=" border: 2px solid black;border-collapse: collapse;">
						<thead>
							<tr>
													<th>Sr No</th>
								                    <th>Date</th>
								                    <th>Bill No</th>
								                    <th>Contact Person</th>
													<th>Supplier Name</th>
													<th>Category Number</th>
													<th>Item Name</th>
													<th>HsnSac No</th>
													<th>Quantity</th>
													<th>Buy Price</th>
													<th>Gst</th>
													<th>Igst</th>
													<th>Tax Amount</th>
													<!-- <th>Discount</th> -->
													<th>Total Amount</th>
													<th>Overall Discount</th>
													<th>Expenses</th>
													<th>Expenses Tax</th>
													<th>Total Expenses</th>
													<th>Gross Amount</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
									<th colspan="8" style="text-align: right">Total:</th>
									<th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><!-- <th></th> -->
				
				
								</tr>
						</tfoot>
					</table>
				</fieldset>
				</form>
        	  	
        	  
        	  </div>
        	  
        	  <!------ 	Between Two Dates Purchase ----->
        	  <div id="twoDatePurchase" class="tab-pane fade">
				
				     <div class="row">
						     <div class="col-sm-offset-1 col-md-10">
								  		<hr style="border-top-color:#c1b1b1;">
						     </div>	
				    </div>
				
				
				<form class="form-horizontal" method="post" action="" name="supReport1">
					<fieldset>
				         <div class="row form-group">
							<label class="col-md-1 control-label" for="startDate"> Start Date:</label>  
				           			 <div class="col-md-3">
										<div class="input-group">
											<span class="input-group-addon">
												<i class="glyphicon glyphicon-user"></i>
											</span>
				           		 			  <input type="date" id="pFisDate" placeholder="Start Date" class="form-control input-md" type="text" >
				           		 		</div>
									</div>
				
				           	 <label class="col-md-1 control-label" for="endDate">End Date:</label>
				           	 	<div class="col-md-3">
									<div class="input-group">
										<span class="input-group-addon">
											<i class="glyphicon glyphicon-map-marker"></i>
										</span>
				         	   			  <input type="date" id="pEndDate" placeholder="End Date" class="form-control input-md ac_district"  type="text">
				          		  	</div>
								</div>
								
								<div class="col-md-3 ">
								<div class="input-group">
									<input type="button" id="btn" name="save"
										class="btn btn-lg btn-success btn-md button_hw button_margin_right"
										onclick="twoDatePurchase45()" value="Search" />
								</div>
							</div>
				          </div>
					  
				       
						
						<table class= "table table-bordered table-striped table-condensed cf"  id="twoDatePurchase50" class="display"  style=" border: 2px solid black;border-collapse: collapse;">
						<thead>
							<tr>
													<th>Sr No</th>
								                    <th>Date</th>
								                    <th>Bill No</th>
								                    <th>Contact Person</th>
													<th>Supplier Name</th>
													<th>Category Number</th>
													<th>Item Name</th>
													<th>HsnSac No</th>
													<th>Quantity</th>
													<th>Buy Price</th>
													<th>Gst</th>
													<th>Igst</th>
													<th>Tax Amount</th>
													<!-- <th>Discount</th> -->
													<th>Total Amount</th>
													<th>Overall Discount</th>
													<th>Expenses</th>
													<th>Expenses Tax</th>
													<th>Total Expenses</th>
													<th>Gross Amount</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
									<th colspan="8" style="text-align: right">Total:</th>
									<th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><!-- <th></th> -->
				
				
								</tr>
						</tfoot>
					</table>
				</fieldset>
				</form>
        	  	
        	  
        	  </div> 	  
        </div>	 
         	 
	</div>

         
           <!---------   Sell Reports--------->
                
     <div class="tab-pane" id="sell">
	         	<div class="row">
				    <div align="center">
				  		<h2 class="form-name style_heading">Sale Reports</h2>
				  	</div>
				 	
			     <div class="row">
					     <div class="col-sm-offset-1 col-md-10">
							  		<hr style="border-top-color:#c1b1b1;">
					     </div>	
			   		 </div>
			    </div>
         	 
	  <ul class="nav nav-tabs">
	  		<li class="active"><a data-toggle="tab" href="#categorySaleWise"><h4 style="color:blue">Category wise</h4></a></li>
		    <li ><a data-toggle="tab" href="#singleDateSale"><h4 style="color:blue">Datewise</h4></a></li>
		    <li><a data-toggle="tab" href="#twoDateSale"><h4 style="color:blue">Range</h4></a></li>
		   <!--  <li ><a data-toggle="tab" href="#customerBillWise"><h4 style="color:blue">Bill Number Wise</h4></a></li> -->
		  
	  </ul>
         	 
   <div class="tab-content" style="float: left"> 

<!-- 	Customer Name wise -->    
    <div id="categorySaleWise" class="tab-pane fade in active">
    		        <div class="row">
					<div class="col-sm-offset-1 col-md-10">
						<hr style="border-top-color: #c1b1b1;">
					</div>
				</div>
		<form class="form-horizontal" method="post" action="" name="supReportBill">
			<fieldset>
			    	<div class="form-group">
								<label class="col-md-3 control-label" for="">
								       Select Category:<sup>*</sup>
							    </label>
									<%	
										CategoryHelper ch = new CategoryHelper();
										List list = ch.getCategorys();
									%>
									<div class="col-sm-3">
										<input list="catId_drop" id="catId" class="form-control">
					  						<datalist id="catId_drop" >
					    	
											<%
												for(int i=0;i<list.size();i++){
													CategoryDetails item = (CategoryDetails)list.get(i);
											%>
												<option data-value="<%=item.getCatId()%>" value="<%=item.getCatName()%>">
											<%
												}
											%>
										  </datalist>
									</div>
				
								
							
            	
							<div class="col-md-3 ">
								<div class="input-group">
									<input type="button" id="btn" name="save"
										class="btn btn-lg btn-success btn-md button_hw button_margin_right"
										onclick="categorySaleWise()" value="Search" />
								</div>
							</div>
					</div>
					 <div class="table-responsive">
							<table class= "table table-bordered table-striped table-condensed cf" id="saleCategoryWise" class="display" style=" border: 2px solid black;border-collapse: collapse;">
								<thead>
									<tr>
									        <th>Sr No</th>
										    <th>Bill No</th>
											<th>Barcode No</th>
											 <th>HSN/SAC</th> 
											<th>ItemName</th>
											<th>CategoryName</th>
											<th>Quantity</th>
											<th>SalePrice</th>
											<th>Total Sale</th>
											<th>Tax Percentage</th>
											<th>Tax Amount</th>
											<th>Total Amount</th>
											
										
			
									</tr>
								</thead>
								
								<tfoot>
										<tr>
										    <th colspan="5" style="text-align: right">Total:</th>
											<th></th>
											<th></th> 
											<th></th>
											<th></th>
											<th></th>
											<th></th>
											 <th></th> 
						
										</tr>
									</tfoot>
			
							</table>
							</div>
					</fieldset>
				</form>
    </div>
    
    
    <!-- Customer Bill number wise -->
     <%-- <div id="customerBillWise" class="tab-pane">
		        <div class="row">
					<div class="col-sm-offset-1 col-md-10">
						<hr style="border-top-color: #c1b1b1;">
					</div>
				</div>
		<form class="form-horizontal" method="post" action="" name="supReportBill">
			<fieldset>

						<div class="row form-group">
							<label class="col-md-2 control-label" for="customerName">Customer Name</label>  
          					  <div class="col-md-3">
								<div class="input-group">
									<span class="input-group-addon">
										<i class="glyphicon glyphicon-user"></i>
									</span>
						
							<%
								CustomerDetailsDao cdd = new CustomerDetailsDao();
           						List cList =cdd.getAllCustomer();
							
							%>
									<input list="cust_drop" id="creditCustomer"  class="form-control" onchange="getBillByCustomer1()">
				<datalist id="cust_drop">
							
							<%
					           for(int i=0;i<cList.size();i++){
					        	   CustomerDetailsBean cust =(CustomerDetailsBean)cList.get(i);
							%>
		
						<option data-value="<%=cust.getCustId()%>"><%=cust.getFirstName() %> <%=cust.getLastName() %> </option>
							<%
				      			}
				    		%>
						</datalist> 
				</div>
       </div>
       
            </div>
           				<div class="row form-group">  
           				  <label class="col-md-2 control-label" for="bill_no"> Bill No </label>  
          					  <div class="col-md-3">
								<div class="input-group">
									<span class="input-group-addon">
										No
									</span>
           		 					
           		 					<select class="form-control input-md" id='billNo1'  name="billNo1" >
									</select>
           						 </div>
							</div>
				
				
							<div class="col-md-3 ">
								<div class="input-group">
									<input type="button" id="btn" name="save"
										class="btn btn-lg btn-success btn-md button_hw button_margin_right"
										onclick="getBillWiseCreditReport()" value="Search" />
								</div>
							</div>
					</div>	
						<div	class="table-responsive">
						<table class= "table table-bordered table-striped table-condensed cf"  id="customerNameWiseData" class="display"  style=" border: 2px solid black;border-collapse: collapse;">
							<thead>
								<tr>
													<th>Date</th>
													<th>First Name</th>
													<th>Last Name</th>
													<th>Bill Number</th>
													<th>Payment Type</th>
													<th>Payment Mode</th>
													<th>Total Amount</th>
													<th>Payment Amount</th>
													<th>Balance Amount</th>
								</tr>
							</thead>
							<tfoot>
								<tr>
									<th colspan="6" style="text-align: right">Total:</th>
									<th></th><th></th><th></th>
				
				
								</tr>
							</tfoot>
						</table>
					</div>
					</fieldset>
				</form>
         
       </div> --%>
    
    
    <!--    for single date sale -->
	<div id="singleDateSale" class="tab-pane">
		        <div class="row">
					<div class="col-sm-offset-1 col-md-10">
						<hr style="border-top-color: #c1b1b1;">
					</div>
				</div>
		<form class="form-horizontal" method="post" action="" >
			<fieldset>

						<div class="row form-group">
							<label class="col-md-3 control-label" for="">
								Enter Date:<sup>*</sup>
							</label>
							<div class="col-md-4">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-user"></i>
									</span> <input type="date" id="fDate1" placeholder="Select Date"
										class="form-control input-md" type="text">
								</div>
							</div>
				
				
							<div class="col-md-3">
								<div class="input-group">
									<input type="button" id="btn" name="save"
										class="btn btn-lg btn-success btn-md button_hw button_margin_right"
										onclick="singleDateSaleReport()" value="Search" />
								</div>
							</div>
						</div>
						
						  <div class="table-responsive">
							<table class= "table table-bordered table-striped table-condensed cf" id="saleSingleDate" class="display" style=" border: 2px solid black;border-collapse: collapse;">
								<thead>
									<tr>
									        <th>Sr No</th>
										    <th>Bill No</th>
											<th>Barcode No</th>
											<th>hsnSacNo</th>
											<th>ItemName</th>
											<th>CategoryName</th>
											<th>Quantity</th>
											<th>SalePrice</th>
											<th>Total Sale</th>
											<th>Tax Percentage</th>
											<th>Tax Amount</th>
											<th>Total Amount</th>
											  
										
			
									</tr>
								</thead>
								
								<tfoot>
										<tr>
										    <th colspan="5" style="text-align: right">Total:</th>
											<th></th><th></th> <th></th><th></th><th></th><th></th> <th></th> 
						
										</tr>
									</tfoot>
							</table>
							</div>
						
					</fieldset>
				</form>
         
       </div>
        	
        <!------ 	Between Two Dates Sale  ----->
        	  <div id="twoDateSale" class="tab-pane fade">
				
				     <div class="row">
						     <div class="col-sm-offset-1 col-md-10">
								  		<hr style="border-top-color:#c1b1b1;">
						     </div>	
				    </div>
				
				
				<form class="form-horizontal" method="post" action="" >
					<fieldset>
				         <div class="row form-group">
							<label class="col-md-1 control-label" for="startDate"> Start Date:</label>  
				           			 <div class="col-md-3">
										<div class="input-group">
											<span class="input-group-addon">
												<i class="glyphicon glyphicon-user"></i>
											</span>
				           		 			  <input type="date" id="fisDate" placeholder="Start Date" class="form-control input-md" type="text" >
				           		 		</div>
									</div>
				
				           	 <label class="col-md-1 control-label" for="village">End Date:</label>
				           	 	<div class="col-md-3">
									<div class="input-group">
										<span class="input-group-addon">
											<i class="glyphicon glyphicon-map-marker"></i>
										</span>
				         	   			  <input type="date" id="endDate" placeholder="End Date" class="form-control input-md ac_district"  type="text">
				          		  	</div>
								</div>
								
								<div class="col-md-3">
								<div class="input-group">
									<input type="button" id="btn" name="save"
										class="btn btn-lg btn-success btn-md button_hw button_margin_right"
										onclick="twoDateSaleReport()" value="Search" />
								</div>
							</div>
				          </div>
					  
				      
						
						<div class="table-responsive">
							<table class= "table table-bordered table-striped table-condensed cf" id="saleTwoDate" class="display" style=" border: 2px solid black;border-collapse: collapse;">
								<thead>
									<tr>
									        <th>Sr No</th>
										    <th>Bill No</th>
											<th>Barcode No</th>
											<th>hsnSacNo</th>
											<th>ItemName</th>
											<th>CategoryName</th>
											<th>Quantity</th>
											<th>SalePrice</th>
											<th>Total Sale</th>
											<th>Tax Percentage</th>
											<th>Tax Amount</th>
											<th>Total Amount</th>
											
										
			
									</tr>
								</thead>
								
								<tfoot>
										<tr>
										    <th colspan="5" style="text-align: right">Total:</th>
											<th></th><th></th> <th></th><th></th><th></th><th></th><th></th>
						
										</tr>
									</tfoot>
							</table>
							</div>
				</fieldset>
				</form>
        	  	
        	  
        	  </div>
        </div>	 
         	 
	</div>
	
		<!----- 	Currnt Stock reports ------>
			 <div class="tab-pane" id="stock">
				<div class="row">
				    <div align="center">
				  		<h2 class="form-name style_heading">Stock Reports</h2>
				  	</div>
				 	
			     <div class="row">
					     <div class="col-sm-offset-1 col-md-10">
							  		<hr style="border-top-color:#c1b1b1;">
					     </div>	
			   		 </div>
			    </div>
         	 
				  <ul class="nav nav-tabs">
					    
					    <li class="active"><a data-toggle="tab" href="#categoryWiseStock"><h4 style="color:blue">Category Wise Stock</h4></a></li>
					    <li><a data-toggle="tab" href="#currentStock" onclick="currentStock()"><h4 style="color:blue">Current Stock</h4></a></li>
					    <li><a data-toggle="tab" href="#barcodeNoWiseStock"><h4 style="color:blue">Barcode Wise Stock</h4></a></li>
					  
				  </ul>
			         	 
			   <div class="tab-content" style="float: left"> 
			    
		<!--    for Current Stock -->
				<div id="currentStock" class="tab-pane" >
						 <div class="row">
					<div class="col-sm-offset-1 col-md-10">
						<hr style="border-top-color: #c1b1b1;">
					</div>
				</div>
				<form class="form-horizontal" method="post" action="" >
					<fieldset>
					<div class="row">
						<div	class="table-responsive">
						 <table class= "table table-bordered table-striped table-condensed cf" id="currStock" class="display" style=" border: 2px solid black;border-collapse: collapse;">
					     <thead>
						 <tr>
						   
							<th>Category Name</th>
							<th>Item Name</th>
							<th>Quantity</th>
							<th>Update Date</th>
							

						 </tr>
					   </thead>

				</table>
			</div>
			</div>
			</fieldset>
		 </form>
		</div>
				
				
		<!-------- 	Category Wise Stock	 -------->
			<div id="categoryWiseStock" class="tab-pane fade in active">
				
				<div class="row">
						     <div class="col-sm-offset-1 col-md-10">
								  		<hr style="border-top-color:#c1b1b1;">
						     </div>	
				    </div>
				
				
	<form class="form-horizontal" method="post" action="" >
		<fieldset>
		 <div class="row">
				<div class="form-group">
					<div class="col-sm-2 col-sm-offset-1" align="center">
						<label class="control-label" style="font-family: Times New Roman;">Category Name:</label>
					</div>
					<%
						CategoryHelper helper = new CategoryHelper();
						List mainCategoryList = helper.getAllMainCategories();
					%>
					<div class="col-sm-3">
						<div class="input-group">
							<span class="input-group-addon"> <i
								class="glyphicon glyphicon-user"></i>
							</span> <input list="catId_drop123" id="catId123" class="form-control">
					<datalist id="catId_drop123">
						<%
							for(int i=0;i<mainCategoryList.size();i++){
							Category category = (Category)mainCategoryList.get(i);
						%>
							<option data-value="<%=category.getCategoryName()%>" value="<%=category.getCategoryName() %>">
						<%
							}
						%>
					</datalist>
						</div>
					</div>
					
					<div class="col-md-3 ">
								<div class="input-group">
									<input type="button" id="btn" name="save"
										class="btn btn-lg btn-success btn-md button_hw button_margin_right"
										onclick="getCategoryWiseStock();" value="Search" />
								</div>
				   </div>
			    </div>	
				</div>
				
				
						
					<table id="catWiseStock" class= "table table-bordered table-striped table-condensed cf" class="display" style=" border: 2px solid black;border-collapse: collapse;">
						<thead>
							<tr>
							
								
								<th> Category Name</th>
								<th> Item Name</th>
								<th>Available Quantity</th>
								<th>Update Date</th>
				
							</tr>
						</thead>
						<tfoot>
						<tr>	
						<th></th>
						<th></th>
						<th></th>
						<th></th>
						
						</tr>
						
					</table>
				</fieldset>
				</form>
			</div>
			
		<!--    Barcode Wise Current Stock -->
				<div id="barcodeNoWiseStock" class="tab-pane" >
					 <div class="row">
					    <div class="col-sm-offset-1 col-md-10">
						  <hr style="border-top-color: #c1b1b1;">
					   </div>
			     </div>
				<form class="form-horizontal" method="post" action="" >
					<fieldset>
					
								<div class="row">
								  <div class="row form-group">
										<label class="col-md-3 control-label" for="">
											Barcode No:<sup>*</sup>
										</label>
										<div class="col-md-3">
											<div class="input-group">
												<span class="input-group-addon"> <i
													class="glyphicon glyphicon-user"></i>
												</span> <input type="text" id="barcode" placeholder="Barcode No"
													class="form-control input-md" type="text">
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
							<div class="row">
							  <div class="table-responsive">
					            <table class= "table table-bordered table-striped table-condensed cf" id="barcodeWiseCurrentStock" class="display" style=" border: 2px solid black; border-collapse: collapse;">
						          <thead>
										<tr>
											<th> Category Name</th>
											<th> Item Name</th>
											<th> Available Quantity</th>
											<th> Buy Price</th>
											<th> Tax Percentage</th>
											<th> Bill No</th>
											<th> Barcode No</th>
										</tr>
								</thead>
		
				          </table>
				        </div>
				      </div>
				    </fieldset>
				  </form>
			   </div>	
			
			</div>
		</div>
		
		
		
				
		<!---------- Vehicle Bill Reports ------->
			
			 <div class="tab-pane" id="vehicle">
					<div class="row">
				    <div align="center">
				  		<h2 class="form-name style_heading">Vehicle Bill Reports</h2>
				  	</div>
				 	
			     <div class="row">
					     <div class="col-sm-offset-1 col-md-10">
							  		<hr style="border-top-color:#c1b1b1;">
					     </div>	
			   		 </div>
			    </div>
         	 
				  <ul class="nav nav-tabs">
					    <li class="active"><a data-toggle="tab" href="#vehicleSingleDate"><h4 style="color:blue">Datewise</h4></a></li>
					    <li><a data-toggle="tab" href="#vehicleTwoDate"><h4 style="color:blue">Range</h4></a></li>
					    <li><a data-toggle="tab" href="#vehicleCategoryWise"><h4 style="color:blue">Category Wise</h4></a></li>
					    <li><a data-toggle="tab" href="#vehicleBillNoWise"><h4 style="color:blue">Bill No Wise</h4></a></li>
					    <li><a data-toggle="tab" href="#vehicleBarcodeNoWise"><h4 style="color:blue">Barcode No Wise</h4></a></li>
				  </ul>
			         	 
	 <div class="tab-content" style="float: left"> 
			    
	<!--  Vehicle Sale for single date -->
				
				<div id="vehicleSingleDate" class="tab-pane fade in active">
						<div class="row">
					<div class="col-sm-offset-1 col-md-10">
						<hr style="border-top-color: #c1b1b1;">
					</div>
				</div>
		<form class="form-horizontal" method="post" action="" >
			<fieldset>

						<div class="row form-group">
							<label class="col-md-3 control-label" for="">
								Select Date:<sup>*</sup>
							</label>
							<div class="col-md-4">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-user"></i>
									</span> <input type="date" id="vsDate" placeholder="Select Date"
										class="form-control input-md" type="text">
								</div>
							</div>
				
				
							<div class="col-md-3">
								<div class="input-group">
									<input type="button" id="btn" name="save"
										class="btn btn-lg btn-success btn-md button_hw button_margin_right"
										onclick="vehicleSingleDate()" value="Search" />
								</div>
							</div>
						</div>
						<div	class="table-responsive">
						<table class= "table table-bordered table-striped table-condensed cf"  id="vehicleSingleDateReport" class="display" style=" border: 2px solid black;border-collapse: collapse;">
							<thead>
								<tr>      
								        <th>Sr No</th>         
										<th>Bill No</th>
										<th>Car No</th>
										<th>Owner Name</th>
										<th>Contact No</th>
										<th>Barcode No</th>
										<th>hsnSacNo</th>
										<th>CategoryName</th>
										<th>ItemName</th>
										<th>Quantity</th>
										<th>SalePrice</th>
										<th>Total Sale</th>
										<th>Tax Percentage</th>
										<th>Tax Amount</th>
										<th>Total Amount</th>
												    
								</tr>
							</thead>
							<tfoot>
								<tr>
									<th colspan="8" style="text-align: right">Total:</th>
									<th></th><th></th><th></th><th></th><th></th><th></th><th></th>
				
				
								</tr>
							</tfoot>
						</table>
					</div>
					</fieldset>
				</form>
			
			
			
		</div>
	
	<!-------Vehicle Sale between Two dates ------->
	
				<div id="vehicleTwoDate" class="tab-pane">
						<div class="row">
						     <div class="col-sm-offset-1 col-md-10">
								  		<hr style="border-top-color:#c1b1b1;">
						     </div>	
				    </div>
				
				
				<form class="form-horizontal" method="post" action="" name="expReport1">
					<fieldset>
				         <div class="row form-group">
							<label class="col-md-1 control-label" for="startDate"> Start Date:</label>  
				           			 <div class="col-md-3">
										<div class="input-group">
											<span class="input-group-addon">
												<i class="glyphicon glyphicon-user"></i>
											</span>
				           		 			  <input type="date" id="fisDate4" placeholder="Start Date" class="form-control input-md" type="text" >
				           		 		</div>
									</div>
				
				           	 <label class="col-md-1 control-label" for="endDate">End Date:</label>
				           	 	<div class="col-md-3">
									<div class="input-group">
										<span class="input-group-addon">
											<i class="glyphicon glyphicon-map-marker"></i>
										</span>
				         	   			  <input type="date" id="endDate4" placeholder="End Date" class="form-control input-md ac_district"  type="text">
				          		  	</div>
								</div>
								
								<div class="col-md-3">
								<div class="input-group">
									<input type="button" id="btn" name="save"
										class="btn btn-lg btn-success btn-md button_hw button_margin_right"
										onclick="vehicleTwoDate()" value="Search" />
								</div>
							   </div>
				          </div>
					  
				      <div	class="table-responsive">
						<table class= "table table-bordered table-striped table-condensed cf"  id="vehicleTwoDateReport" class="display" style=" border: 2px solid black;border-collapse: collapse;">
							<thead>
								<tr>      
								        <th>Sr No</th>         
										<th>Bill No</th>
										<th>Car No</th>
										<th>Owner Name</th>
										<th>Contact No</th>
										<th>Barcode No</th>
										<th>hsnSacNo</th>	
										<th>CategoryName</th>
										<th>ItemName</th>
										<th>Quantity</th>
										<th>SalePrice</th>
										<th>Total Sale</th>
										<th>Tax Percentage</th>
										<th>Tax Amount</th>
										<th>Total Amount</th>
											    
								</tr>
							</thead>
							<tfoot>
								<tr>
									<th colspan="8" style="text-align: right">Total:</th>
									<th></th><th></th><th></th><th></th><th></th><th></th><th></th>
				
				
								</tr>
							</tfoot>
						</table>
					</div>
				</fieldset>
				</form>
			
			
			
				</div>
	
    <!-- 	Category Name wise Vehicle Sale Reports -->    
    <div id="vehicleCategoryWise" class="tab-pane">
    		        <div class="row">
					<div class="col-sm-offset-1 col-md-10">
						<hr style="border-top-color: #c1b1b1;">
					</div>
				</div>
		<form class="form-horizontal" method="post" action="" >
			<fieldset>
			    	<div class="form-group">
								<label class="col-md-3 control-label" for="">
								       Select Category:<sup>*</sup>
							    </label>
									<%	
										CategoryHelper ch1 = new CategoryHelper();
										List list1 = ch1.getCategorys();
									%>
									<div class="col-sm-3">
										<input list="catId_drop12" id="catId12" class="form-control">
					  						<datalist id="catId_drop12" >
					    	
											<%
												for(int i=0;i<list1.size();i++){
													CategoryDetails item1 = (CategoryDetails)list1.get(i);
											%>
												<option data-value="<%=item1.getCatName()%>" value="<%=item1.getCatName()%>">
											<%
												}
											%>
										  </datalist>
									</div>
				
								
							
            	
							<div class="col-md-3 ">
								<div class="input-group">
									<input type="button" id="btn" name="save"
										class="btn btn-lg btn-success btn-md button_hw button_margin_right"
										onclick="categorySaleWiseCustomer()" value="Search" />
								</div>
							</div>
					</div>
					 <div	class="table-responsive">
						<table class= "table table-bordered table-striped table-condensed cf"  id="categorySaleWiseCustomerReport" class="display" style=" border: 2px solid black;border-collapse: collapse;">
							<thead>
								<tr>      
								        <th>Sr No</th>         
										<th>Bill No</th>
										<th>Car No</th>
										<th>Owner Name</th>
										<th>Contact No</th>
										<th>Barcode No</th>
										<th>hsnSacNo</th>
										<th>CategoryName</th>
										<th>ItemName</th>
										<th>Quantity</th>
										<th>SalePrice</th>
										<th>Total Sale</th>
										<th>Tax Percentage</th>
										<th>Tax Amount</th>
										<th>Total Amount</th>
												    
								</tr>
							</thead>
							<tfoot>
								<tr>
									<th colspan="8" style="text-align: right">Total:</th>
									<th></th><th></th><th></th><th></th><th></th><th></th><th></th>
				
				
								</tr>
							</tfoot>
						</table>
					  </div>
					</fieldset>
				</form>
    </div>	
			
			
 <!-- 	Bill No wise Vehicle Sale Reports -->    
    <div id="vehicleBillNoWise" class="tab-pane">
    		        <div class="row">
					<div class="col-sm-offset-1 col-md-10">
						<hr style="border-top-color: #c1b1b1;">
					</div>
				</div>
		    <form class="form-horizontal" method="post" action="" >
			 <fieldset>
			    	<div class="form-group">
								<label class="col-md-3 control-label" for="">
								       Select BillNo:<sup>*</sup>
							    </label>
									<%
						            	CarEntryDao fd = new CarEntryDao();
							            List list2 = fd.getBillNoAndNames();
						             %>
									<div class="col-sm-3">
									  <input list="seedBillNocust" id="BillNocust" class="form-control">
									  <datalist id="seedBillNocust">
									   <%
					               		    for(int i=0;i<list2.size();i++){
					               			BillCopy billList=(BillCopy)list2.get(i);
									    %>
									    <option data-value="<%=billList.getBillNo()%>" value="<%=billList.getBillNo()%>          <%=billList.getCustName()%>" >
									   <%
										   }
									    %>
									</datalist>
									</div>
				
								
							
            	
							<div class="col-md-3 ">
								<div class="input-group">
									<input type="button" id="btn" name="save"
										class="btn btn-lg btn-success btn-md button_hw button_margin_right"
										onclick="billnowiseVehiclesell()" value="Search" />
								</div>
							</div>
					</div>
					 <div	class="table-responsive">
						<table class= "table table-bordered table-striped table-condensed cf"  id="billnowiseVehicle" class="display" style=" border: 2px solid black;border-collapse: collapse;">
							<thead>
								<tr>      
								        <th>Sr No</th>         
										<th>Bill No</th>
										<th>Car No</th>
										<th>Owner Name</th>
										<th>Contact No</th>
										<th>Barcode No</th>
										<th>hsnSacNo</th>	
										<th>CategoryName</th>
										<th>ItemName</th>
										<th>Quantity</th>
										<th>SalePrice</th>
										<th>Total Sale</th>
										<th>Tax Percentage</th>
										<th>Tax Amount</th>
										<th>Total Amount</th>
											    
								</tr>
							</thead>
							<tfoot>
								<tr>
									<th colspan="8" style="text-align: right">Total:</th>
									<th></th><th></th><th></th><th></th><th></th><th></th><th></th>
				
				
								</tr>
							</tfoot>
						</table>
					  </div>
					</fieldset>
				</form>
    </div>		
    
   <!-- Barcode No wise Vehicle Sale Reports -->    
    <div id="vehicleBarcodeNoWise" class="tab-pane">
    		        <div class="row">
					<div class="col-sm-offset-1 col-md-10">
						<hr style="border-top-color: #c1b1b1;">
					</div>
				</div>
		    <form class="form-horizontal" method="post" action="" >
			 <fieldset>
			    	<div class="row">
								  <div class="row form-group">
										<label class="col-md-3 control-label" for="">
											Barcode No:<sup>*</sup>
										</label>
										<div class="col-md-3">
											<div class="input-group">
												<span class="input-group-addon"> <i
													class="glyphicon glyphicon-user"></i>
												</span> <input type="text" id="barcodeVehicle" placeholder="Barcode No"
													class="form-control input-md" type="text">
											</div>
										</div>
							
							
										<div class="col-md-3">
											<div class="input-group">
												<input type="button" id="btn" name="save"
													class="btn btn-lg btn-success btn-md button_hw button_margin_right"
													onclick="barcodenowiseVehiclesell()" value="Search" />
											</div>
										</div>
									</div>
					</div>
					 <div	class="table-responsive">
						<table class= "table table-bordered table-striped table-condensed cf"  id="barcodewiseVehicle" class="display" style=" border: 2px solid black;border-collapse: collapse;">
							<thead>
								<tr>      
								        <th>Sr No</th>         
										<th>Bill No</th>
										<th>Car No</th>
										<th>Owner Name</th>
										<th>Contact No</th>
										<th>Barcode No</th>
										<th>hsnSacNo</th>	
										<th>CategoryName</th>
										<th>ItemName</th>
										<th>Quantity</th>
										<th>SalePrice</th>
										<th>Total Sale</th>
										<th>Tax Percentage</th>
										<th>Tax Amount</th>
										<th>Total Amount</th>
											    
								</tr>
							</thead>
							<tfoot>
								<tr>
									<th colspan="8" style="text-align: right">Total:</th>
									<th></th><th></th><th></th><th></th><th></th><th></th><th></th>
				
				
								</tr>
							</tfoot>
						</table>
					  </div>
					</fieldset>
				</form>
             </div>	
				
				
			</div>
		</div>
			
			
	    <!---------- Miscellaneous Bill Reports ------->
			
			 <div class="tab-pane" id="miscellaneous">
					<div class="row">
				    <div align="center">
				  		<h2 class="form-name style_heading">Miscellaneous Bill Reports</h2>
				  	</div>
				 	
			     <div class="row">
					     <div class="col-sm-offset-1 col-md-10">
							  		<hr style="border-top-color:#c1b1b1;">
					     </div>	
			   		 </div>
			    </div>
         	 
				  <ul class="nav nav-tabs">
					    <li class="active"><a data-toggle="tab" href="#miscellaneousSingleDate"><h4 style="color:blue">Datewise</h4></a></li>
					    <li><a data-toggle="tab" href="#miscellaneousTwoDate"><h4 style="color:blue">Range</h4></a></li>
					    <li><a data-toggle="tab" href="#miscellaneousCategoryWise"><h4 style="color:blue">Category Wise</h4></a></li>
					    <li><a data-toggle="tab" href="#miscellaneousBillNoWise"><h4 style="color:blue">Bill No Wise</h4></a></li>
					    <li><a data-toggle="tab" href="#miscellaneousBarcodeNoWise"><h4 style="color:blue">Barcode No Wise</h4></a></li>
				  </ul>
			         	 
	 <div class="tab-content" style="float: left"> 
			    
	<!--  Miscellaneos Sale for single date -->
				
				<div id="miscellaneousSingleDate" class="tab-pane fade in active">
						<div class="row">
					<div class="col-sm-offset-1 col-md-10">
						<hr style="border-top-color: #c1b1b1;">
					</div>
				</div>
		<form class="form-horizontal" method="post" action="" >
			<fieldset>

						<div class="row form-group">
							<label class="col-md-3 control-label" for="">
								Select Date:<sup>*</sup>
							</label>
							<div class="col-md-4">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-user"></i>
									</span> <input type="date" id="msDate" placeholder="Select Date"
										class="form-control input-md" type="text">
								</div>
							</div>
				
				
							<div class="col-md-3">
								<div class="input-group">
									<input type="button" id="btn" name="save"
										class="btn btn-lg btn-success btn-md button_hw button_margin_right"
										onclick="miscellaneousSingleDate()" value="Search" />
								</div>
							</div>
						</div>
						<div	class="table-responsive">
						<table class= "table table-bordered table-striped table-condensed cf"  id="miscellaneousSingleDateReport" class="display" style=" border: 2px solid black;border-collapse: collapse;">
							<thead>
								<tr>      
								        <th>Sr No</th>         
										<th>Bill No</th>
										<th>Barcode No</th>
										<th>CategoryName</th>
										<th>ItemName</th>
										<th>Quantity</th>
										<th>SalePrice</th>
										<th>Total Price</th>
										<th>Tax Percentage</th>
										<th>Tax Amount</th>
										<th>Total Amount</th>
										<th>Discount</th>		    
								</tr>
							</thead>
							<tfoot>
								<tr>
									<th colspan="5" style="text-align: right">Total:</th>
									<th></th><th></th><th></th><th></th><th></th><th></th><th></th>
				
				
								</tr>
							</tfoot>
						</table>
					</div>
					</fieldset>
				</form>
			
			
			
		</div>
	
	<!-------Miscellaneos Sale between Two dates ------->
	
				<div id="miscellaneousTwoDate" class="tab-pane">
						<div class="row">
						     <div class="col-sm-offset-1 col-md-10">
								  		<hr style="border-top-color:#c1b1b1;">
						     </div>	
				    </div>
				
				
				<form class="form-horizontal" method="post" action="" >
					<fieldset>
				         <div class="row form-group">
							<label class="col-md-1 control-label" for="startDate"> Start Date:</label>  
				           			 <div class="col-md-3">
										<div class="input-group">
											<span class="input-group-addon">
												<i class="glyphicon glyphicon-user"></i>
											</span>
				           		 			  <input type="date" id="msfisDate" placeholder="Start Date" class="form-control input-md" type="text" >
				           		 		</div>
									</div>
				
				           	 <label class="col-md-1 control-label" for="endDate">End Date:</label>
				           	 	<div class="col-md-3">
									<div class="input-group">
										<span class="input-group-addon">
											<i class="glyphicon glyphicon-map-marker"></i>
										</span>
				         	   			  <input type="date" id="msendDate" placeholder="End Date" class="form-control input-md ac_district"  type="text">
				          		  	</div>
								</div>
								
								<div class="col-md-3">
								<div class="input-group">
									<input type="button" id="btn" name="save"
										class="btn btn-lg btn-success btn-md button_hw button_margin_right"
										onclick="miscellaneousTwoDate()" value="Search" />
								</div>
							   </div>
				          </div>
					  
				      <div	class="table-responsive">
						<table class= "table table-bordered table-striped table-condensed cf"  id="miscellaneousTwoDateReport" class="display" style=" border: 2px solid black;border-collapse: collapse;">
							<thead>
								<tr>      
								        <th>Sr No</th>         
										<th>Bill No</th>
										<th>Barcode No</th>
										<th>CategoryName</th>
										<th>ItemName</th>
										<th>Quantity</th>
										<th>SalePrice</th>
										<th>Total Price</th>
										<th>Tax Percentage</th>
										<th>Tax Amount</th>
										<th>Total Amount</th>
										<th>Discount</th>		    
								</tr>
							</thead>
							<tfoot>
								<tr>
									<th colspan="5" style="text-align: right">Total:</th>
									<th></th><th></th><th></th><th></th><th></th><th></th><th></th>
				
				
								</tr>
							</tfoot>
						</table>
					</div>
				</fieldset>
				</form>
			
			
			
				</div>
	
    <!-- 	Category Name wise Miscellaneos Sale Reports -->    
    <div id="miscellaneousCategoryWise" class="tab-pane">
    		        <div class="row">
					<div class="col-sm-offset-1 col-md-10">
						<hr style="border-top-color: #c1b1b1;">
					</div>
				</div>
		<form class="form-horizontal" method="post" action="" >
			<fieldset>
			    	<div class="form-group">
								<label class="col-md-3 control-label" for="">
								       Select Category:<sup>*</sup>
							    </label>
									<%	
										CategoryHelper ch2 = new CategoryHelper();
										List lis = ch2.getCategorys();
									%>
									<div class="col-sm-3">
										<input list="mscatId_drop" id="mscatId" class="form-control">
					  						<datalist id="mscatId_drop" >
					    	
											<%
												for(int i=0;i<lis.size();i++){
													CategoryDetails item2 = (CategoryDetails)lis.get(i);
											%>
												<option data-value="<%=item2.getCatName()%>" value="<%=item2.getCatName()%>">
											<%
												}
											%>
										  </datalist>
									</div>
				
								
							
            	
							<div class="col-md-3 ">
								<div class="input-group">
									<input type="button" id="btn" name="save"
										class="btn btn-lg btn-success btn-md button_hw button_margin_right"
										onclick="miscellaneousSaleWiseCustomer()" value="Search" />
								</div>
							</div>
					</div>
					 <div	class="table-responsive">
						<table class= "table table-bordered table-striped table-condensed cf"  id="miscellaneousSaleWiseCustomerReport" class="display" style=" border: 2px solid black;border-collapse: collapse;">
							<thead>
								<tr>      
								        <th>Sr No</th>         
										<th>Bill No</th>
										<th>Barcode No</th>
										<th>CategoryName</th>
										<th>ItemName</th>
										<th>Quantity</th>
										<th>SalePrice</th>
										<th>Total Price</th>
										<th>Tax Percentage</th>
										<th>Tax Amount</th>
										<th>Total Amount</th>
										<th>Discount</th>		    
								</tr>
							</thead>
							<tfoot>
								<tr>
									<th colspan="5" style="text-align: right">Total:</th>
									<th></th><th></th><th></th><th></th><th></th><th></th><th></th>
				
				
								</tr>
							</tfoot>
						</table>
					  </div>
					</fieldset>
				</form>
    </div>	
			
			
 <!-- 	Bill No wise Miscellaneos Sale Reports -->    
    <div id="miscellaneousBillNoWise" class="tab-pane">
    		        <div class="row">
					<div class="col-sm-offset-1 col-md-10">
						<hr style="border-top-color: #c1b1b1;">
					</div>
				</div>
		    <form class="form-horizontal" method="post" action="" >
			 <fieldset>
			    	<div class="form-group">
								<label class="col-md-3 control-label" for="">
								       Select BillNo:<sup>*</sup>
							    </label>
									<%
 						               OtherBillDao ms1 = new OtherBillDao();
							           List lisms = ms1.getBillNo();
						             %>    
									<div class="col-sm-3">
									  <input list="msBillNocust_id" id="msBillNocust" class="form-control">
									  <datalist id="msBillNocust_id">
									   <%
					               		for(int i=0;i<lisms.size();i++){
					               			BillCopy billList1=(BillCopy)lisms.get(i);
									%>
									<option data-value="<%=billList1.getBillNo()%>" value="<%=billList1.getBillNo()%>" >
									<%
										}
									%>
									</datalist>
									</div>
				
								
							
            	
							<div class="col-md-3 ">
								<div class="input-group">
									<input type="button" id="btn" name="save"
										class="btn btn-lg btn-success btn-md button_hw button_margin_right"
										onclick="billnowiseMiscellaneoussell()" value="Search" />
								</div>
							</div>
					</div>
					 <div	class="table-responsive">
						<table class= "table table-bordered table-striped table-condensed cf"  id="billnowiseMiscellaneous" class="display" style=" border: 2px solid black;border-collapse: collapse;">
							<thead>
								<tr>      
								        <th>Sr No</th>         
										<th>Bill No</th>
										<th>Barcode No</th>
										<th>CategoryName</th>
										<th>ItemName</th>
										<th>Quantity</th>
										<th>SalePrice</th>
										<th>Total Price</th>
										<th>Tax Percentage</th>
										<th>Tax Amount</th>
										<th>Total Amount</th>
										<th>Discount</th>		    
								</tr>
							</thead>
							<tfoot>
								<tr>
									<th colspan="5" style="text-align: right">Total:</th>
									<th></th><th></th><th></th><th></th><th></th><th></th><th></th>
				
				
								</tr>
							</tfoot>
						</table>
					  </div>
					</fieldset>
				</form>
    </div>		
    
   <!-- Barcode No wise Miscellaneos Sale Reports -->    
    <div id="miscellaneousBarcodeNoWise" class="tab-pane">
    		        <div class="row">
					<div class="col-sm-offset-1 col-md-10">
						<hr style="border-top-color: #c1b1b1;">
					</div>
				</div>
		    <form class="form-horizontal" method="post" action="" >
			 <fieldset>
			    	<div class="row">
								  <div class="row form-group">
										<label class="col-md-3 control-label" for="">
											Barcode No:<sup>*</sup>
										</label>
										<div class="col-md-3">
											<div class="input-group">
												<span class="input-group-addon"> <i
													class="glyphicon glyphicon-user"></i>
												</span> <input type="text" id="barcodeMiscellaneous" placeholder="Barcode No"
													class="form-control input-md" type="text">
											</div>
										</div>
							
							
										<div class="col-md-3">
											<div class="input-group">
												<input type="button" id="btn" name="save"
													class="btn btn-lg btn-success btn-md button_hw button_margin_right"
													onclick="barcodenowiseMiscellaneoussell()" value="Search" />
											</div>
										</div>
									</div>
					</div>
					 <div	class="table-responsive">
						<table class= "table table-bordered table-striped table-condensed cf"  id="barcodewiseMiscellaneous" class="display" style=" border: 2px solid black;border-collapse: collapse;">
							<thead>
								<tr>      
								        <th>Sr No</th>         
										<th>Bill No</th>
										<th>Barcode No</th>
										<th>CategoryName</th>
										<th>ItemName</th>
										<th>Quantity</th>
										<th>SalePrice</th>
										<th>Total Price</th>
										<th>Tax Percentage</th>
										<th>Tax Amount</th>
										<th>Total Amount</th>
										<th>Discount</th>		    
								</tr>
							</thead>
							<tfoot>
								<tr>
									<th colspan="5" style="text-align: right">Total:</th>
									<th></th><th></th><th></th><th></th><th></th><th></th><th></th>
				
				
								</tr>
							</tfoot>
						</table>
					  </div>
					</fieldset>
				</form>
             </div>	
				
				
			</div>
		</div>		
	
	
	     <!---------- Credit Customer Bill Reports ------->
			
			 <div class="tab-pane" id="credit">
					<div class="row">
				    <div align="center">
				  		<h2 class="form-name style_heading">Credit Customer Bill Reports</h2>
				  	</div>
				 	
			     <div class="row">
					     <div class="col-sm-offset-1 col-md-10">
							  		<hr style="border-top-color:#c1b1b1;">
					     </div>	
			   		 </div>
			    </div>
         	 
				  <ul class="nav nav-tabs">
					    <li class="active"><a data-toggle="tab" href="#creditSingleDate"><h4 style="color:blue">Datewise</h4></a></li>
					    <li><a data-toggle="tab" href="#creditTwoDate"><h4 style="color:blue">Range</h4></a></li>
					    <li><a data-toggle="tab" href="#creditCategoryWise"><h4 style="color:blue">Category Wise</h4></a></li>
					    <li><a data-toggle="tab" href="#creditBillNoWise"><h4 style="color:blue">Bill No Wise</h4></a></li>
					    <li><a data-toggle="tab" href="#creditBarcodeNoWise"><h4 style="color:blue">Barcode No Wise</h4></a></li>
				  </ul>
			         	 
	 <div class="tab-content" style="float: left"> 
			    
	<!--  Credit Customer Sale for single date -->
				
				<div id="creditSingleDate" class="tab-pane fade in active">
						<div class="row">
					<div class="col-sm-offset-1 col-md-10">
						<hr style="border-top-color: #c1b1b1;">
					</div>
				</div>
		<form class="form-horizontal" method="post" action="" >
			<fieldset>

						<div class="row form-group">
							<label class="col-md-3 control-label" for="">
								Select Date:<sup>*</sup>
							</label>
							<div class="col-md-4">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-user"></i>
									</span> <input type="date" id="csDate" placeholder="Select Date"
										class="form-control input-md" type="text">
								</div>
							</div>
				
				
							<div class="col-md-3">
								<div class="input-group">
									<input type="button" id="btn" name="save"
										class="btn btn-lg btn-success btn-md button_hw button_margin_right"
										onclick="creditSingleDate()" value="Search" />
								</div>
							</div>
						</div>
						<div	class="table-responsive">
						<table class= "table table-bordered table-striped table-condensed cf"  id="creditSingleDateReport" class="display" style=" border: 2px solid black;border-collapse: collapse;">
							<thead>
								<tr>      
								        <th>Sr No</th>         
										<th>Bill No</th>
										<th>Customer Name</th>
										<th>Barcode No</th>
										<th>CategoryName</th>
										<th>ItemName</th>
										<th>Quantity</th>
										<th>SalePrice</th>
										<th>Total Price</th>
										<th>Tax Percentage</th>
										<th>Tax Amount</th>
										<th>Total Amount</th>
										<th>Discount</th>		    
								</tr>
							</thead>
							<tfoot>
								<tr>
									<th colspan="6" style="text-align: right">Total:</th>
									<th></th><th></th><th></th><th></th><th></th><th></th><th></th>
				
				
								</tr>
							</tfoot>
						</table>
					</div>
					</fieldset>
				</form>
			
			
			
		</div>
	
	<!-------Credit Customer Sale between Two dates ------->
	
				<div id="creditTwoDate" class="tab-pane">
						<div class="row">
						     <div class="col-sm-offset-1 col-md-10">
								  		<hr style="border-top-color:#c1b1b1;">
						     </div>	
				    </div>
				
				
				<form class="form-horizontal" method="post" action="" >
					<fieldset>
				         <div class="row form-group">
							<label class="col-md-1 control-label" for="startDate"> Start Date:</label>  
				           			 <div class="col-md-3">
										<div class="input-group">
											<span class="input-group-addon">
												<i class="glyphicon glyphicon-user"></i>
											</span>
				           		 			  <input type="date" id="csfisDate" placeholder="Start Date" class="form-control input-md" type="text" >
				           		 		</div>
									</div>
				
				           	 <label class="col-md-1 control-label" for="endDate">End Date:</label>
				           	 	<div class="col-md-3">
									<div class="input-group">
										<span class="input-group-addon">
											<i class="glyphicon glyphicon-map-marker"></i>
										</span>
				         	   			  <input type="date" id="csendDate" placeholder="End Date" class="form-control input-md ac_district"  type="text">
				          		  	</div>
								</div>
								
								<div class="col-md-3">
								<div class="input-group">
									<input type="button" id="btn" name="save"
										class="btn btn-lg btn-success btn-md button_hw button_margin_right"
										onclick="creditTwoDate()" value="Search" />
								</div>
							   </div>
				          </div>
					  
				      <div	class="table-responsive">
						<table class= "table table-bordered table-striped table-condensed cf"  id="creditTwoDateReport" class="display" style=" border: 2px solid black;border-collapse: collapse;">
							<thead>
								<tr>      
								        <th>Sr No</th>         
										<th>Bill No</th>
										<th>Customer Name</th>
										<th>Barcode No</th>
										<th>CategoryName</th>
										<th>ItemName</th>
										<th>Quantity</th>
										<th>SalePrice</th>
										<th>Total Price</th>
										<th>Tax Percentage</th>
										<th>Tax Amount</th>
										<th>Total Amount</th>
										<th>Discount</th>		    
								</tr>
							</thead>
							<tfoot>
								<tr>
									<th colspan="6" style="text-align: right">Total:</th>
									<th></th><th></th><th></th><th></th><th></th><th></th><th></th>
				
				
								</tr>
							</tfoot>
						</table>
					</div>
				</fieldset>
				</form>
			
			
			
				</div>
	
    <!-- 	Category Name wise Credit Customer Sale Reports -->    
    <div id="creditCategoryWise" class="tab-pane">
    		        <div class="row">
					<div class="col-sm-offset-1 col-md-10">
						<hr style="border-top-color: #c1b1b1;">
					</div>
				</div>
		<form class="form-horizontal" method="post" action="" >
			<fieldset>
			    	<div class="form-group">
								<label class="col-md-3 control-label" for="">
								       Select Category:<sup>*</sup>
							    </label>
									<%	
										CategoryHelper ch3 = new CategoryHelper();
										List lis3 = ch3.getCategorys();
									%>
									<div class="col-sm-3">
										<input list="cscatId_drop" id="cscatId" class="form-control">
					  						<datalist id="cscatId_drop" >
					    	
											<%
												for(int i=0;i<lis3.size();i++){
													CategoryDetails item3 = (CategoryDetails)lis3.get(i);
											%>
												<option data-value="<%=item3.getCatName()%>" value="<%=item3.getCatName()%>">
											<%
												}
											%>
										  </datalist>
									</div>
				
								
							
            	
							<div class="col-md-3 ">
								<div class="input-group">
									<input type="button" id="btn" name="save"
										class="btn btn-lg btn-success btn-md button_hw button_margin_right"
										onclick="creditSaleWiseCustomer()" value="Search" />
								</div>
							</div>
					</div>
					 <div	class="table-responsive">
						<table class= "table table-bordered table-striped table-condensed cf"  id="creditSaleWiseCustomerReport" class="display" style=" border: 2px solid black;border-collapse: collapse;">
							<thead>
								<tr>      
								        <th>Sr No</th>         
										<th>Bill No</th>
										<th>Customer Name</th>
										<th>Barcode No</th>
										<th>CategoryName</th>
										<th>ItemName</th>
										<th>Quantity</th>
										<th>SalePrice</th>
										<th>Total Price</th>
										<th>Tax Percentage</th>
										<th>Tax Amount</th>
										<th>Total Amount</th>
										<th>Discount</th>		    
								</tr>
							</thead>
							<tfoot>
								<tr>
									<th colspan="6" style="text-align: right">Total:</th>
									<th></th><th></th><th></th><th></th><th></th><th></th><th></th>
				
				
								</tr>
							</tfoot>
						</table>
					  </div>
					</fieldset>
				</form>
    </div>	
			
			
 <!-- 	Bill No wise Credit Customer Sale Reports -->    
    <div id="creditBillNoWise" class="tab-pane">
    		        <div class="row">
					<div class="col-sm-offset-1 col-md-10">
						<hr style="border-top-color: #c1b1b1;">
					</div>
				</div>
		    <form class="form-horizontal" method="post" action="" >
			 <fieldset>
			    	<div class="form-group">
								<label class="col-md-3 control-label" for="">
								       Select BillNo:<sup>*</sup>
							    </label>
									<%
 						              CreditCustBillDao cs1 = new CreditCustBillDao();
							          List lrst4 = cs1.getBillNo();
						            %>   
									<div class="col-sm-3">
									  <input list="csBillNocust_id" id="csBillNocust" class="form-control">
									  <datalist id="csBillNocust_id">
									   <%
					               		for(int i=0;i<lrst4.size();i++){
					               			BillCopy bli=(BillCopy)lrst4.get(i);
									%>
									<option data-value="<%=bli.getBillNo()%>" value="<%=bli.getBillNo()%>    <%=bli.getCustName()%>" >
									<%
										}
									%>
									</datalist>
									</div>
				
								
							
            	
							<div class="col-md-3 ">
								<div class="input-group">
									<input type="button" id="btn" name="save"
										class="btn btn-lg btn-success btn-md button_hw button_margin_right"
										onclick="billnowiseCreditsell()" value="Search" />
								</div>
							</div>
					</div>
					 <div	class="table-responsive">
						<table class= "table table-bordered table-striped table-condensed cf"  id="billnowiseCredit" class="display" style=" border: 2px solid black;border-collapse: collapse;">
							<thead>
								<tr>      
								        <th>Sr No</th>         
										<th>Bill No</th>
										<th>Customer Name</th>
										<th>Barcode No</th>
										<th>CategoryName</th>
										<th>ItemName</th>
										<th>Quantity</th>
										<th>SalePrice</th>
										<th>Total Price</th>
										<th>Tax Percentage</th>
										<th>Tax Amount</th>
										<th>Total Amount</th>
										<th>Discount</th>		    
								</tr>
							</thead>
							<tfoot>
								<tr>
									<th colspan="6" style="text-align: right">Total:</th>
									<th></th><th></th><th></th><th></th><th></th><th></th><th></th>
				
				
								</tr>
							</tfoot>
						</table>
					  </div>
					</fieldset>
				</form>
    </div>		
    
   <!-- Barcode No wise Credit Customer Sale Reports -->    
    <div id="creditBarcodeNoWise" class="tab-pane">
    		        <div class="row">
					<div class="col-sm-offset-1 col-md-10">
						<hr style="border-top-color: #c1b1b1;">
					</div>
				</div>
		    <form class="form-horizontal" method="post" action="" >
			 <fieldset>
			    	<div class="row">
								  <div class="row form-group">
										<label class="col-md-3 control-label" for="">
											Barcode No:<sup>*</sup>
										</label>
										<div class="col-md-3">
											<div class="input-group">
												<span class="input-group-addon"> <i
													class="glyphicon glyphicon-user"></i>
												</span> <input type="text" id="barcodeCredit" placeholder="Barcode No"
													class="form-control input-md" type="text">
											</div>
										</div>
							
							
										<div class="col-md-3">
											<div class="input-group">
												<input type="button" id="btn" name="save"
													class="btn btn-lg btn-success btn-md button_hw button_margin_right"
													onclick="barcodenowiseCredit()" value="Search" />
											</div>
										</div>
									</div>
					</div>
					 <div	class="table-responsive">
						<table class= "table table-bordered table-striped table-condensed cf"  id="barcodewiseCredit" class="display" style=" border: 2px solid black;border-collapse: collapse;">
							<thead>
								<tr>      
								        <th>Sr No</th>         
										<th>Bill No</th>
										<th>Customer Name</th>
										<th>Barcode No</th>
										<th>CategoryName</th>
										<th>ItemName</th>
										<th>Quantity</th>
										<th>SalePrice</th>
										<th>Total Price</th>
										<th>Tax Percentage</th>
										<th>Tax Amount</th>
										<th>Total Amount</th>
										<th>Discount</th>		    
								</tr>
							</thead>
							<tfoot>
								<tr>
									<th colspan="6" style="text-align: right">Total:</th>
									<th></th><th></th><th></th><th></th><th></th><th></th><th></th>
				
				
								</tr>
							</tfoot>
						</table>
					  </div>
					</fieldset>
				</form>
             </div>	
				
				
			</div>
		</div>
	
	
	</div>
  </div>
 </div>
</div>



<%-- <%@include file="commons/footer.jsp" %> --%>
	
	
	
	
	