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
	    <%-- function ChooseContact(){
			
	    	var catname = document.getElementById('catId').value;
	    	var input = document.getElementById('catId'),
	        list = document.getElementById('catId_drop'),
	        i,catId;
	        for (i = 0; i < list.options.length; ++i) {
	        if (list.options[i].value === input.value) {
	        	catId = list.options[i].getAttribute('data-value');
	        
	        }
	        
	        }
	
	        <%
	        ProductDetailHelper catHelper = new ProductDetailHelper();
		   		List catList = catHelper.getAllItemNameAndCatName();
			%>
	
			<%
				for(int i=0;i<catList.size();i++){
				ProductRegister category = (ProductRegister)catList.get(i);
    		%>

    		    var value ="<%=category.getFkCategoryId()%>";
    		    var value1 ="<%=category.getItemName()%>";
    		    
    		    
				if(document.getElementById("itemName").value == value1 && catId == value)
					{
						document.getElementById("btn").disabled = true;	
						alert( "ItemName  "+value1 +"  Already Exist With In  "+catname +"  Caregory !!!" );
						location.reload();
				        return false;
					}
		    <%
				}
    		%>
    		productdel();
	    }
	     --%>
	   
</script>


<body class="master_form_img">
	<div class="container-fluid">  
	
	<div class="row header_margin_top">
			    <div align="center">
			  		<h2 class="form-name style_heading"> Edit Product Detail</h2>
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
    
    
    
<form action="pro" method="post" name="UpdateProd" class="form-horizontal">  
    <div class="row">
					<div class="form-group">			
						
						<div class="col-sm-3 " align="center">
					    	  <label class="control-label">Product Name:</label>
						</div>
						
						<div class="col-sm-2">
						 			<div class="input-group">
											<span class="input-group-addon">
												<i class="glyphicon glyphicon-hand-right"></i>
											</span>
										<!-- <input type="text" class="form-control  input-sm" id='itemName' name="itemName" placeholder="Product Name" > -->
										
										<%
											ProductDetailDao sdd = new ProductDetailDao();
           									List sList =sdd.getProductNames();
							
										%>
							
										<input list="sup_drop" id="product" onchange="getProductDetails()" class="form-control">
										
										<datalist id="sup_drop">
											<%
									           for(int i=0;i<sList.size();i++){
									        	   ProductRegister sup =(ProductRegister)sList.get(i);
											%>
											<option data-value="<%=sup.getPkProductId()%>" value="<%=sup.getItemName() %>">
											<%
								      			}
								    		%>
										</datalist> 
										
							  		</div>
							  </div>
							  
							   <div class="col-sm-2 " align="center">
					    	  <label class="control-label">New Product Name:<sup style="color: red">*</sup></label>
						</div>
						
						<div class="col-sm-3">
						 			<div class="input-group">
											<span class="input-group-addon">
												<i class="glyphicon glyphicon-hand-right"></i>
											</span>
										<input type="text" class="form-control  input-sm" id='itemName' name="itemName" placeholder=" New Product Name" >
							  		</div>
							  </div>
					</div>
				</div>

   
				
				
				<div class="row">
					<div class="form-group">
					     <div class="col-sm-3 " align="center">
					    	  <label class="control-label">HSN/SAC No :</label>
						</div>
						
						<div class="col-sm-2">
						 			<div class="input-group">
											<span class="input-group-addon">
												<i class="glyphicon glyphicon-hand-right"></i>
											</span>
										<input type="text" class="form-control  input-sm" id='hsnsacno' name="hsnsacno" placeholder="HSN/SAC No">
							  		</div>
						</div>
							  
							   <div class="col-sm-2 " align="center">
					    	  <label class="control-label">Model Name:</label>
						</div>
						
						<div class="col-sm-3">
						 			<div class="input-group">
											<span class="input-group-addon">
												<i class="glyphicon glyphicon-hand-right"></i>
											</span>
										<input type="text" class="form-control  input-sm" id='modelName' name="modelName" placeholder="Model Name" >
							  		</div>
							  </div>
						
					</div>
				</div>
			
				
			
			
			<div class="row buttons_margin_top">
					<div align="center">
					  <input type="button" onclick="updateProduct()" id="btn" value="Save" class="btn btn-lg btn-success btn-md button_hw button_margin_right"/>
					  <input type="reset" value="Reset" class="btn btn-lg btn-danger btn-md button_hw button_margin_right"/>
					</div>
			</div>
			
			
			
			
	</form>			
	
		<div class="row footer_margin_top" align="center">
			<%@include file="y_commons/footer.jsp"%>
		</div> 
    
    </div>
   </body>
  </html>