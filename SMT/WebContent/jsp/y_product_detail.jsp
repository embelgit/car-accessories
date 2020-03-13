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
 
 		function validateProductDetail(){
 			
 			var catId= $('#catId').val();
 			var itemName= $('#itemName').val();
 		    var hsnsacno = $('#hsnsacno').val();
 			
 			if(catId != null && catId != "" && catId != " ")
 			{
 				
 				if(itemName != null && itemName != "" && itemName != " ")
 				{
 					
 				if(hsnsacno != null && hsnsacno != "" && hsnsacno != " ")
 						{
 						ChooseContact();
 	 	 			}else{
 	 	 				alert("Please Enter HSN/SAC No ! ");
 	 	 			}
 					
 	 			}
 	 			else{
 	 				alert("Please Enter Product name");
 	 			}
 			}
 			else{
 				alert("Please Select Category name");
 			}
 			
 		}
	    function ChooseContact(){
			
	    	var catname = document.getElementById('catId').value;
	    	var input = document.getElementById('catId'),
	        list = document.getElementById('catId_drop'),
	        i,catId;
	        for (i = 0; i < list.options.length; ++i) {
	        if (list.options[i].value === input.value) {
	        	catId = list.options[i].getAttribute('data-value');
	        
	        }
	        
	        }
	        
	
	        if(catId == null){
	 			alert("Selected Category is not exits! please select available Category");
	 			return false;
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
						alert( "ItemName  "+value1 +"  Already Exist With In  "+catname +"  Category !!!" );
						location.reload();
				        return false;
					}
		    <%
				}
    		%>
    		productdel();
    		
	    }
	    
	    function editProduct()
	    {
	    	window.location = "Edit_y_product_detail.jsp";
	    }
</script>
 <script type="text/javascript">
 function editProduct()
 {
 	window.location = "Edit_y_product_detail.jsp";
 }
 </script>

<body class="master_form_img">
	<div class="container-fluid">  
	
	<div class="row header_margin_top">
			    <div align="center">
			  		<h2 class="form-name style_heading">Product Detail</h2>
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
						<div class="col-sm-3"  align="center">
						  	<label class="control-label">Category:<sup style="color: red">*</sup></label>
						 </div>
						 <%
						    CategoryHelper helper = new CategoryHelper();
						    List mainCategoryList = helper.getAllMainCategories();
					    %>
						 
						<div class="col-sm-2">
							<div class="input-group">
									<span class="input-group-addon">
										<i class="glyphicon glyphicon-triangle-right"></i>
									</span>
									<input list="catId_drop" id="catId" class="form-control">
								<datalist id="catId_drop">
									<%
										for(int i=0;i<mainCategoryList.size();i++){
										Category category = (Category)mainCategoryList.get(i);
									%>
										<option data-value="<%=category.getPkCategoryId()%>" value="<%=category.getCategoryName() %>">
									<%
										}
									%>
								</datalist>
							</div>
						</div>
						  <div class="col-sm-2 " align="center">
					    	  <label class="control-label">Product Name:<sup style="color: red">*</sup></label>
						</div>
						
						<div class="col-sm-3">
						 			<div class="input-group">
											<span class="input-group-addon">
												<i class="glyphicon glyphicon-hand-right"></i>
											</span>
										<input type="text" class="form-control  input-sm" id='itemName' name="itemName" placeholder="Product Name" >
							  		</div>
							  </div>
					</div>
				</div>

   
				
				
				<div class="row">
					<div class="form-group">
					    <div class="col-sm-3 " align="center">
					    	  <label class="control-label">HSN/SAC No :<sup style="color: red">*</sup></label>
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
					  <input type="button" onclick="return validateProductDetail()" name="btn" id="btn" value="Save Product" class="btn btn-lg btn-success btn-md button_hw button_margin_right"/>
					  <input type="reset" value="Cancel" class="btn btn-lg btn-danger btn-md button_hw button_margin_right"/>
					  <input type="button" onclick="editProduct()" id="btn" value="Edit Product" class="btn btn-lg btn-primary btn-md button_hw button_margin_right"/>
					</div>
			</div>
			
			
			
			
	</form>			
	
		<div class="row footer_margin_top" align="center">
			<%@include file="y_commons/footer.jsp"%>
		</div> 
    
    </div>
   </body>
  </html>