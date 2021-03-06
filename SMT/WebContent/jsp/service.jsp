<%@page import="com.smt.bean.ItemList"%>
<%@page import="com.smt.bean.ProductDetailBean"%>
<%@page import="com.smt.dao.ProductDetailDao"%>
<%@page import="com.smt.hibernate.Category"%>
<%@page import="com.smt.hibernate.SupplierDetail"%>
<%@page import="java.util.List"%>
<%@page import="com.smt.hibernate.ProductRegister"%>
<%@page import="com.smt.helper.CategoryHelper"%>
<%@page import="com.smt.helper.SupplierDetailHelper"%>
<%@page import="com.smt.helper.ProductDetailHelper"%>
	
<% boolean isHome = false;%>

<%@include file="y_commons/header1.jsp"%>


	     <script src="/SMT/staticContent/js/jquery-1.12.3.min.js"></script>
		
		
		<link rel="stylesheet" href="/SMT/staticContent/css/jquery-ui.min.css">
	    <link rel="stylesheet" href="/SMT/staticContent/css/ui.jqgrid.min.css">
	    <link rel="stylesheet" href="/SMT/staticContent/css/ui.jqgrid.css">
	    <script src="/SMT/staticContent/js/jquery.min.js"></script>
	    <script src="/SMT/staticContent/js/jquery.jqgrid.min.js"></script> 
        <script src="/SMT/staticContent/y_js/productDetail.js"></script>
        
  
 <script type="text/javascript">

 function go()
 {
 	window.location = "serviceList.jsp";
 }
 </script>

<body class="master_form_img">
	<div class="container-fluid">  
	
	<div class="row header_margin_top">
			    <div align="center">
			  		<h2 class="form-name style_heading">Service Name</h2>
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
				  		<hr style="border-top-color:#c1b1b1;">
		     </div>	
    </div>
    
<form action="pro" method="post" name="prod" class="form-horizontal">  
    <div class="row">
					<div class="form-group">
					
					  <div class="col-sm-2 " align="center">
					    	  <label class="control-label">Service Name:</label>
						</div>
						
						<div class="col-sm-3">
						 			<div class="input-group">
											<span class="input-group-addon">
												<i class="glyphicon glyphicon-hand-right"></i>
											</span>
										<input type="text" class="form-control  input-sm" id="itemName" name="itemName" placeholder="Product Name" >
							  		</div>
							  </div>
					
					
								
						 <div class="col-sm-3 " align="center">
					    	  <label class="control-label">HSN/SAC No :</label>
						</div>
						
						<div class="col-sm-2">
						 			<div class="input-group">
											<span class="input-group-addon">
												<i class="glyphicon glyphicon-hand-right"></i>
											</span>
										<input type="text" class="form-control  input-sm" id="hsnsacNo" name="hsnsacno" placeholder="HSN/SAC No">
							  		</div>
						 </div>
				      
						
					</div>
				</div>

			<div class="row buttons_margin_top">
					<div align="center">
					  <input type="button" onclick="return serviceprod()" name="btn" id="btn" value="Save " class="btn btn-lg btn-success btn-md button_hw button_margin_right"/>
					  <input type="reset" value="Cancel" class="btn btn-lg btn-danger btn-md button_hw button_margin_right"/>
					  <!-- <input type="button" onclick="editProduct()" id="btn" value="Edit Product" class="btn btn-lg btn-primary btn-md button_hw button_margin_right"/> -->
					  <input type="button" onclick="go()" name="btn" id="btn" value="List" class="btn btn-lg btn-primary btn-md button_hw button_margin_right"/>
					</div>
			</div>
			
			
			
			
	</form>			
	
		<div class="row footer_margin_top" align="center">
			<%@include file="y_commons/footer.jsp"%>
		</div> 
    
    </div>
   </body>
  </html>