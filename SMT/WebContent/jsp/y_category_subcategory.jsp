

<%@page import="java.util.List"%>
<%@page import="com.smt.helper.CategoryHelper"%>
<%@page import="com.smt.hibernate.Category"%>

<% boolean isHome=false;%>
<%@include file="y_commons/header1.jsp"%>


<script src="/SMT/staticContent/y_js/jquery-1.12.3.min.js"></script>
<link href="/SMT/staticContent/y_js/bootstrap-select.min.css"/>
	
     <script src="/SMT/staticContent/y_css/1.11.4-jquery-ui.min.css">
    <script src="/SMT/staticContent/y_js/jquery-1.12.3.min.js"></script>
    <script src="/SMT/staticContent/y_js/1.12.4-jquery.min.js"></script>
    <script src="/SMT/staticContent/y_js/jquery.jqgrid.min.js"></script>
    
   
		<link href="/SMT/staticContent/y_css/adi.css" rel="stylesheet"/>		
		 <link href="/SMT/staticContent/y_css/4.13.3-ui.jqgrid.min.css"/>
		<script src="/SMT/staticContent/y_js/uppercase.js"></script>
	<script src="/SMT/staticContent/y_js/category.js"></script>

<style>
a:link {
	text-decoration: none;
}

a:visited {
	text-decoration: none;
}

a:hover {
	text-decoration: none;
}

a:active {
	text-decoration: none;
}
</style>

<script type="text/javascript">
	    function cheakForAvailableCat(){
			
	        <%
				CategoryHelper catHelper = new CategoryHelper();
		   		List catList = catHelper.getAllMainCategories();
			%>
			var catName = $('#categoryName').val();
    		var UpCatName = catName.toUpperCase();
    		var duplicate;
    		//alert(catList.size());
			<%
				for(int i=0;i<catList.size();i++){
				Category category = (Category)catList.get(i);
    		%>
    		
    		    var value ="<%=category.getCategoryName()%>";
    		    var UpValue = value.toUpperCase();
				if(UpCatName == UpValue)
					{
							duplicate = "found";
							/* document.cat.btn.disabled = true;	
							alert("Category Name Already Exist..!!!");
			 				document.cat.reset();
		 					document.cat.btn.disabled = false;
							return false; */
					}
				
		    <%
				}
    		%>
    		
    		
    		
    		if(duplicate == "found"){
    			document.cat.btn.disabled = true;	
				alert("Category Name Already Exist..!!!");
 			//	document.cat.reset();
			//	document.cat.btn.disabled = false;
    			location.reload();
				return false;
    		}
    		/* else{
    			valAddMainCat();
    		} */
    		//categoryd();
		}
	</script>
	
	
	
	

<body class="master_form_img">
	<div class="container-fluid">  
	
	<!-- <div class="row header_margin_top">
			    <div align="center">
			  		<h2 class="form-name style_heading">Add Category </h2>
			  	</div>
			 
    </div> -->
    
         <div class="row" style="margin-top: 10px; margin-bottom: 45px;" align="right">
			<div class="form-group" >
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
          <h2 align="center" class="form-heading" style="margin-top: 10px; margin-bottom: 40px;">Add Category</h2>
		<!-- <div class="row"  style="margin-top: 100px; margin-bottom: 45px;">
			<div class="col-sm-10 col-sm-offset-1">	
				<div class="row margin-t-13">
					<div class="">			
						<ul class="nav nav-tabs">
						    <li class="active"><a data-toggle="tab" href="#home">Add Category</a></li>
						   		   				    
					    </ul>
					</div>
				</div>
			</div>
		</div> -->
				<div class="tab-content margin-t-13">
				
				    <div id="home" class="tab-pane fade in active">
				    
				    	<form action="cate" method="post" name="cat" class="form-horizontal">
						      	<div class="row">
											<div class="form-group">
											    <div class="col-sm-2 col-sm-offset-3" align="center">
											    	  <label class="control-label">Category:</label>
												</div>
												<div class="col-sm-3">
												 			<div class="input-group">
																	<span class="input-group-addon">
																		<i class="glyphicon glyphicon-user"></i>
																	</span>
 <input type="text" name="categoryName" id="categoryName" class="form-control" placeholder="Category Name" onchange="cheakForAvailableCat()">
															<%-- 	<input list="catId_drop" id="categoryName" name="categoryName" class="form-control" placeholder="Category Name" >
						                                            <datalist id="catId_drop">
						                                            <%
							                                            for(int i =0 ;i<catList.size();i++)
								                                        {
							                                            	Category cat = (Category)catList.get(i);
						                                            %>
							                                        <option data-value="<%=cat.getPkCategoryId()%>" value="<%=cat.getCategoryName()%>"> 
						                                            <%   	
								                                        }	
						                                            %>
						                                            </datalist> --%>
													  		</div>
													  		    <p style="font-size:15;color:#ff8000;">*Enter Alphabets only.</p>
												</div>
												<!-- <div class="col-md-3">
													<button type="button" style="width:190;" name="btn" onclick="goEditCatagoryFrom();" value=""  class="btn btn-primary btn-md button_hw"/><a href="EditCategory.jsp"><h4 style="color:white;">Edit Category Name</h4></a></button>
												</div> -->
									        </div>
									        
									         
								  </div>	
						
									<div class="row buttons_margin_top">
										 	<div align="center">
											    <input type="button" name="btn" onclick="valAddMainCat();" value="Save"  class="btn btn-success btn-md button_hw"/> 
												<input type="reset" value="Cancel" class="btn btn-danger btn-md button_hw"/>
												<button type="button" " name="btn" onclick="goEditCatagoryFrom();" value=""  class="btn btn-primary btn-md button_hw"/><a href="EditCategory.jsp"><h4 style="color:white;">Edit</h4></a></button>
										  </div>
									</div>		
						</form>
				    </div>
				    
				  
				    			    
				 </div>	
			
<%-- 

 		<div class="row margin_shortcut">
					 <div class="col-sm-12" >
					  <%@include file="y_commons/shortcut.jsp"%>  
				    </div>
		</div> --%>
	 
		 <div class="row footer_margin_top" align="center">
			<%@include file="y_commons/footer.jsp"%>
		</div>  
    
    </div>
   </body>
  </html>

</body>
</html>