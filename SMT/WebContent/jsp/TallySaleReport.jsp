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
       
		 <script src="/SMT/staticContent/js/tallySaleReport.js"></script>
	
 <body  class="master_form_img">
 <div class="container-fluid" > 
 		
 		<div class="row">
			<div align="center" style="margin-top: 75px">
				  <h2 class="form-name style_heading">Tally Sale Reports</h2>
			</div>
				 	
			 <div class="row">
				<div class="col-sm-offset-1 col-md-10">
						<hr style="border-top-color:#c1b1b1;">
				</div>	
			  </div>
		</div>

		<ul class="nav nav-tabs">
		 <li class="active"><a data-toggle="tab" href="#tallyReports"><h4 style="color:blue">Vehicle TALLY Report</h4></a></li>
	    <li><a data-toggle="tab" href="#home"><h4 style="color:blue">Miscellaneous TALLY Report</h4></a></li>
	    <li><a data-toggle="tab" href="#caReports"><h4 style="color:blue">Credit TALLY Report</h4></a></li>
	   
	   
 	 </ul>
 
 	<div class="tab-content" style="float: left">
   
    
         <!-- ---	Tally Reports	---- -->
 		<div id="tallyReports" class="tab-pane fade in active">
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
												  
										<input type="button"  id="btn" name="save" class="btn btn-lg btn-success btn-md button_hw button_margin_right"onclick="tallySaleReportBetweenTwoDates()" value="Search"/>
												     									
									</div>
							</div>	
					<p id="demo12"></p>		
					<div class="table-responsive">		
						<table class= "table table-bordered table-striped table-condensed cf" id="tallySalereport" class="display" style=" border: 2px solid black;border-collapse: collapse;">
						<thead>
							<tr>
							    <th>Vch No.</th>
							    <th>Vch Type</th>
							    <th>Vch Ref</th>
				                <th>Date</th>
				                <th>Party Name</th>
				                <th>Sales Ledger</th>
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
 		
    <!--  other Bill -->
    	<div id="home" class="tab-pane">
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
						
						 <label class="col-md-2 control-label" for="">End Purchase Date:<sup>*</sup></label>
				           	 	<div class="col-md-3">
									<div class="input-group">
										<span class="input-group-addon">
											<i class="glyphicon glyphicon-map-marker"></i>
										</span>
				         	   			  <input type="date" id="eDate" placeholder="End Date" class="form-control input-md ac_district"  type="text">
				          		  	</div>
						</div>
					</div>
					
					
				    <div class="row form-group buttons_margin_top ">
						<div align="center">
												  
							<input type="button"  id="btn" name="save" class="btn btn-lg btn-success btn-md button_hw button_margin_right"onclick="tallySaleReportBetweenTwoDates1()" value="Search"/>
												     									
						</div>
				   </div>	
				<div class="table-responsive">
					<table class= "table table-bordered table-striped table-condensed cf" id="example" class="display" style=" border: 2px solid black;border-collapse: collapse;">
						<thead>
							<tr>
							    <th>Vch No.</th>
							    <th>Vch Type</th>
							    <th>Vch Ref</th>
				                <th>Date</th>
				                <th>Party Name</th>
				                <th>Sales Ledger</th>
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
												  
										<input type="button"  id="btn" name="save" class="btn btn-lg btn-success btn-md button_hw button_margin_right"onclick="tallySaleReportBetweenTwoDates2()" value="Search"/>
												     									
									</div>
							</div>	
					<p id="demo12"></p>		
					<div class="table-responsive">		
						<table class= "table table-bordered table-striped table-condensed cf" id="careport" class="display" style=" border: 2px solid black;border-collapse: collapse;">
						<thead>
							<tr>
							    <th>Vch No.</th>
							    <th>Vch Type</th>
							    <th>Vch Ref</th>
				                <th>Date</th>
				                <th>Party Name</th>
				                <th>Sales Ledger</th>
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
 

       <div class="row footer_margin_top" align="center">
			<%@include file="y_commons/footer.jsp"%>
		</div>
		
		</div>
		</body>
</html>