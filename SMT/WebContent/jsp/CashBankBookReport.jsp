
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
        
		<script src="/SMT/staticContent/js/cashBankBookreport.js"></script>
	

 <div class="container" style="float: left"> 
 		
 		<div class="row">
			<div align="center" style="margin-top: 75px">
				  <h2 class="form-name style_heading">Cash Bank Book Reports</h2>
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
	   <!--  <li><a data-toggle="tab" href="#sup"><h4 style="color:blue">Supplier Wise</h4></a></li>
	    <li><a data-toggle="tab" href="#cat"><h4 style="color:blue">Category Wise</h4></a></li> -->
 	 </ul>
 
 	<div class="tab-content" style="float: left">
   
    <!-------- Single Date ---------->
   
    	<div id="home" class="tab-pane fade in active">
    	<div class="row"></div>
 			<form class="form-horizontal" method="post" action="" name="">
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
									onclick="singleDateCashBook()" value="Search" />
							</div>
						</div>
					</div>
					<div	class="table-responsive">
					<table id="example" class="display	">
						<thead>
							<tr>
								<th> Customer Type</th>
				                <th> Customer Name</th>
				                <th> Payment Type</th>
				                <th> Payment By</th>
				                <th> Amount</th>
				                <th> Reason</th>
				                <th> Payment Date</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<!--  <th colspan="7" style="text-align: right">Total:</th>
								<th></th> 
			 -->
			
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
							<label class="col-md-2 control-label" for=""> Start Date:<sup>*</sup></label>  
				           			 <div class="col-md-3">
										<div class="input-group">
											<span class="input-group-addon">
												<i class="glyphicon glyphicon-user"></i>
											</span>
				           		 			  <input type="date" id="fisDate" placeholder="Start Date" class="form-control input-md" type="text" >
				           		 		</div>
									</div>
				
				           	 <label class="col-md-2 control-label" for="">End Date:<sup>*</sup></label>
				           	 	<div class="col-md-3">
									<div class="input-group">
										<span class="input-group-addon">
											<i class="glyphicon glyphicon-map-marker"></i>
										</span>
				         	   			  <input type="date" id="endDate" placeholder="End Date" class="form-control input-md ac_district"  type="text">
				          		  	</div>
								</div>
				          </div>
					  
				      		 <div class="row form-group buttons_margin_top ">
									<div align="center">
												  
										<input type="button"  id="btn" name="save" class="btn btn-lg btn-success btn-md button_hw button_margin_right"onclick="cashBookReportBetweenTwoDates()" value="Search"/>
												     									
									</div>
							</div>	
						<table id="example1" class="display">
						<thead>
							<tr>
								<th> Customer Type</th>
				                <th> Customer Name</th>
				                <th> Payment Type</th>
				                <th> Payment By</th>
				                <th> Amount</th>
				                <th> Reason</th>
				                <th> Payment Date</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<!-- <th colspan="7" style="text-align: right">Total:</th>
								<th></th> -->
							</tr>
						</tfoot>
					</table>
				</fieldset>
				</form>
 		</div>
 		
 </div>


