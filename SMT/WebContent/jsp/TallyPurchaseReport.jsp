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
       
		 <script src="/SMT/staticContent/js/tallyPurchaseReport.js"></script>
	
 <body  class="master_form_img">
 <div class="container-fluid" > 
 		
 		<div class="row">
			<div align="center" style="margin-top: 75px">
				  <h2 class="form-name style_heading">Tally Purchase Reports</h2>
			</div>
				 	
			 <div class="row">
				<div class="col-sm-offset-1 col-md-10">
						<hr style="border-top-color:#c1b1b1;">
				</div>	
			  </div>
		</div>

		
 
 	
 		<div class="row">
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
												  
										<input type="button"  id="btn" name="save" class="btn btn-lg btn-success btn-md button_hw button_margin_right" onclick="tallyPurchaseReportBetweenTwoDates()" value="Search"/>
												     									
									</div>
							</div>	
					<p id="demo12"></p>		
					<div class="table-responsive">		
						<table class= "table table-bordered table-striped table-condensed cf" id="tallyPurchasereport" class="display" style=" border: 2px solid black;border-collapse: collapse;">
						<thead>
							<tr>
							    <th>Vch No.</th>
							    <th>Vch Type</th>
				                <th>Date</th>
				                <th>Party Name</th>
				                <th>Purchase Ledger</th>
								<th>Godown</th>
								<th>Item Name</th>
								<th>Batch</th>
								<th>Unit</th>
							    <th>Qty</th>
				                <th>RATE</th>
				                <th>Amt</th>
				                <th>Additional Ledger</th>
				                <th>Amount</th>
								<th>GST Ledger</th>
								<th>GST AMT</th>
								<th>Total</th>
				                <th>Narrtation</th>
				               
							</tr>
						</thead>
						
					</table>
					</div>
				
				</fieldset>
				</form>
			</div>	
 		</div>
   
		</body>
</html>