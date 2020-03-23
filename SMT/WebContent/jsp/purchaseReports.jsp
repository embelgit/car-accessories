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
        
        <script data-main="scripts/main" src="/SMT/staticContent/js/r.js"></script>
       
		 <script src="/SMT/staticContent/js/report.js"></script>
	
 <body  class="master_form_img">
 <div class="container-fluid" > 
 		
 		<div class="row">
			<div align="center" style="margin-top: 75px">
				  <h2 class="form-name style_heading">Gst Wise Purchase Reports</h2>
			</div>
				 	
			 <div class="row">
				<div class="col-sm-offset-1 col-md-10">
						<hr style="border-top-color:#c1b1b1;">
				</div>	
			  </div>
		</div>

		<ul class="nav nav-tabs">
	    <li class="active"><a data-toggle="tab" href="#home"><h4 style="color:blue">Single Date</h4></a></li>
	    <li><a data-toggle="tab" href="#twoDates"><h4 style="color:blue">Between Two Dates</h4></a></li>
	    <li><a data-toggle="tab" href="#caReports"><h4 style="color:blue">CA Report</h4></a></li>
	    <li><a data-toggle="tab" href="#tallyReports"><h4 style="color:blue">TALLY Report</h4></a></li>
	   
 	 </ul>
 
 	<div class="tab-content" style="float: left">
   
    <!-------- Single Date ---------->
   
    	<div id="home" class="tab-pane fade in active">
    	<div class="row"></div>
 			<form class="form-horizontal" method="post" action="" name="abc">
				<fieldset>
					<div class="row form-group" style="margin-top: 20px">
						<label class="col-md-3 control-label" for="">
							Enter Date:<sup>*</sup>
						</label>
						<div class="col-md-3">
							<div class="input-group">
								<span class="input-group-addon"> <i
									class="glyphicon glyphicon-user"></i>
								</span> <input type="date" id="fDate" placeholder="Start Date"
									class="form-control input-md" type="text">
							</div>
						</div>
			
			
						<div class="col-md-3">
							<div class="input-group">
								<input type="button" id="btn" name="save"
									class="btn btn-lg btn-success btn-md button_hw button_margin_right"
									onclick="return singleDatePurchase();" value="Search" />
							</div>
						</div>
					</div>
					
				<div class="table-responsive">
					<table class= "table table-bordered table-striped table-condensed cf" id="example" class="display" style=" border: 2px solid black;border-collapse: collapse;">
						<thead>
							<tr>
							    <th>Sr No</th>
								<th> Date</th>
				                <th> Supplier Name</th>
				                <th> Bill No</th>
				                <th> GSTTIN No</th>
				                <th> Item Name</th>
				                <th> Hsn/Sac No</th>
				                <th> Item Rate</th>
				                <th> Quantity</th>
				                <th> Amount</th>
				                <th >GST& SGST 5% Amount</th>
								<th >GST& SGST 12% Amount</th>
								<th >GST& SGST 18% Amount</th>
								<th >GST& SGST 28% Amount</th>
								<th >IGST 5% Amount</th>
								<th >IGST 12% Amount</th>
								<th >IGST 18% Amount</th>
								<th >IGST 28% Amount</th>
								<th>Total Tax Amount</th>
								<th>Total Bill Amount</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								 <th colspan="7" style="text-align: right">Total:</th>
								<th></th><th></th> <th></th> <th></th> <th></th> <th></th> <th></th> <th></th> <th></th><th></th> <th></th> <th></th> <th></th>
			
			
							</tr>
						</tfoot>
					</table>
				</div>
				
				</fieldset>
			</form>	
 		</div>
 	
 <!-- ---	Between Two Dates	---- -->
 		<div id="twoDates" class="tab-pane ">
 		<div class="row"></div>
 				<form class="form-horizontal" method="post" action="" name="fertiBill">
					<fieldset>
				         <div class="row form-group" style="margin-top: 20px">
							<label class="col-md-2 control-label" for=""> Start Purchase Date:<sup>*</sup></label>  
				           			 <div class="col-md-2">
										<div class="input-group">
											<span class="input-group-addon">
												<i class="glyphicon glyphicon-user"></i>
											</span>
				           		 			  <input type="date" id="fisDate" placeholder="Start Date" class="form-control input-md" type="text" >
				           		 		</div>
									</div>
				
				           	 <label class="col-md-2 control-label" for="">End Purchase Date:<sup>*</sup></label>
				           	 	<div class="col-md-2">
									<div class="input-group">
										<span class="input-group-addon">
											<i class="glyphicon glyphicon-map-marker"></i>
										</span>
				         	   			  <input type="date" id="endDate" placeholder="End Date" class="form-control input-md ac_district"  type="text">
				          		  	</div>
								</div>
				          </div>
					  
				      		 <div class="row form-group buttons_margin_top ">
									<div  align="center">
												  
										<input type="button"  id="btn" name="save" class="btn btn-lg btn-success btn-md button_hw button_margin_right"onclick="purchaseReportBetweenTwoDates()" value="Search"/>
												     									
									</div>
							</div>	
							
					<div class="table-responsive">		
						<table class= "table table-bordered table-striped table-condensed cf" id="example1" class="display" style=" border: 2px solid black;border-collapse: collapse;">
						<thead>
							<tr>
							    <th>Sr No</th>
								<th> Date</th>
				                <th> Supplier Name</th>
				                <th> Bill No</th>
				                <th> GSTTIN No</th>
				                <th> Item Name</th>
				                <th> Hsn/Sac No</th>
				                <th> Item Rate</th>
				                <th> Quantity</th>
				                <th> Amount</th>
				                <th> Additional Cost</th>
				                <th>CGST 5% Amount</th>
				                <th>SGST 5% Amount</th>
				                <th>CGST 12% Amount</th>
								<th>SGST 12% Amount</th>
								<th>CGST 18% Amount</th>
								<th>SGST 18% Amount</th>
								<th>CGST 28% Amount</th>
								<th>SGST 28% Amount</th>
								<th>IGST 5% Amount</th>
								<th>IGST 12% Amount</th>
								<th>IGST 18% Amount</th>
								<th>IGST 28% Amount</th>
								<th>Total Tax Amount</th>
								<th>Total Bill Amount</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								 <th colspan="7" style="text-align: right">Total:</th>
								<th></th><th></th> <th></th><th></th>  <th></th> <th></th> <th></th> <th></th> <th></th> <th></th><th></th> <th></th> <th></th><th></th> <th></th><th></th> <th></th> <th></th> 
			
			
							</tr>
						</tfoot>
					</table>
					</div>
				
				</fieldset>
				</form>
 		</div>
 		
 		
 		<!-- ---	CA Reports	---- -->
 		<div id="caReports" class="tab-pane ">
 		<div class="row"></div>
 				<form class="form-horizontal" method="post" action="" name="fertiBill">
					<fieldset>
				         <div class="row form-group" style="margin-top: 20px">
							<label class="col-md-2 control-label" for=""> Start Purchase Date:<sup>*</sup></label>  
				           			 <div class="col-md-3">
										<div class="input-group">
											<span class="input-group-addon">
												<i class="glyphicon glyphicon-user"></i>
											</span>
				           		 			  <input type="date" id="fisDate1" placeholder="Start Date" class="form-control input-md" type="text" >
				           		 		</div>
									</div>
				
				           	 <label class="col-md-2 control-label" for="">End Purchase Date:<sup>*</sup></label>
				           	 	<div class="col-md-3">
									<div class="input-group">
										<span class="input-group-addon">
											<i class="glyphicon glyphicon-map-marker"></i>
										</span>
				         	   			  <input type="date" id="endDate1" placeholder="End Date" class="form-control input-md ac_district"  type="text">
				          		  	</div>
								</div>
				          </div>
					  
				      		 <div class="row form-group buttons_margin_top ">
									<div align="center">
												  
										<input type="button"  id="btn" name="save" class="btn btn-lg btn-success btn-md button_hw button_margin_right"onclick="caReportBetweenTwoDates()" value="Search"/>
												     									
									</div>
							</div>	
					<p id="demo12"></p>		
					<div class="table-responsive">		
						<table class= "table table-bordered table-striped table-condensed cf" id="careport" class="display" style=" border: 2px solid black;border-collapse: collapse;">
						<thead>
							<tr>
							    <th> Sr No</th>
							    <th> Category Name</th>
				                <th> Item Name</th>
				                <th> Hsn/Sac No</th>
				                <th> Quantity</th>
				                <th> Gst</th>
								<th> Igst</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th></th><th></th> <th></th><th></th>  <th></th> <th></th> <th></th> 
			
							</tr>
						</tfoot>
					</table>
					</div>
				
				</fieldset>
				</form>
 		</div>
 		
 		
 		<!-- ---	Tally Reports	---- -->
 		<div id="tallyReports" class="tab-pane ">
 		<div class="row"></div>
 				<form class="form-horizontal" method="post" action="">
					<fieldset>
				         <div class="row form-group" style="margin-top: 20px">
							<label class="col-md-2 control-label" for=""> Start Purchase Date:<sup>*</sup></label>  
				           			 <div class="col-md-3">
										<div class="input-group">
											<span class="input-group-addon">
												<i class="glyphicon glyphicon-user"></i>
											</span>
				           		 			  <input type="date" id="fisDatetally" placeholder="Start Date" class="form-control input-md" type="text" >
				           		 		</div>
									</div>
				
				           	 <label class="col-md-2 control-label" for="">End Purchase Date:<sup>*</sup></label>
				           	 	<div class="col-md-3">
									<div class="input-group">
										<span class="input-group-addon">
											<i class="glyphicon glyphicon-map-marker"></i>
										</span>
				         	   			  <input type="date" id="endDatetally" placeholder="End Date" class="form-control input-md ac_district"  type="text">
				          		  	</div>
								</div>
				          </div>
					  
				      		 <div class="row form-group buttons_margin_top ">
									<div align="center">
												  
										<input type="button"  id="btn" name="save" class="btn btn-lg btn-success btn-md button_hw button_margin_right"onclick="tallyReportBetweenTwoDates()" value="Search"/>
												     									
									</div>
							</div>	
					<p id="demo12"></p>		
					<div class="table-responsive">		
						<table class= "table table-bordered table-striped table-condensed cf" id="tallyreport" class="display" style=" border: 2px solid black;border-collapse: collapse;">
						<thead>
							<tr>
							    <th>NAME</th>
							    <th>PARENT</th>
				                <th>BASE UNITS</th>
				                <th>ALIAS</th>
				                <th>PARTNO</th>
				                <th>OPENING QTY</th>
								<th>OPENING RATE</th>
								<th>OPENING VALUE</th>
							    <th>Category</th>
				                <th>Description</th>
				                <th>Vat Applicable</th>
				                <th>GST Applicable</th>
				                <th>Type of Supply</th>
								<th>GST Applicable Date</th>
								<th>HSN Code</th>
				                <th>HSN Desc</th>
				                <th>Taxablity</th>
				                <th>Rate Of IGST</th>
								<th>Rate of CGST</th>
								<th>Rate Of SGST</th>
								<th>Rate Of Cess</th>
							</tr>
						</thead>
						
					</table>
					</div>
				
				</fieldset>
				</form>
 		</div>
 		
 
 		
 	      </div>
 

       <div class="row footer_margin_top" align="center">
			<%@include file="y_commons/footer.jsp"%>
		</div>
		
		</div>
		</body>
</html>