<%-- <%@page import="com.rms.hibernate.ItemEntry"%>
<%@page import="com.rms.dao.ItemEntryDao"%> --%>

 <% boolean isHome=false; %>
<%@include file="y_commons/header1.jsp"%>
 <%@page import="com.smt.hibernate.ProductRegister"%>
<%@page import="com.smt.dao.ProductDetailDao"%>
<%@page import="com.smt.helper.CarEntryHelper"%>
<%@page import="com.smt.hibernate.CarEntry"%>
<%@page import="com.smt.helper.OtherBillHelper"%>
<%@page import="com.smt.bean.BillBean"%>
<%@page import="com.smt.dao.OtherBillDao"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Order Bill</title>
	
	     <script src="/SMT/staticContent/js/jquery-1.12.3.min.js"></script>
	     <link rel="stylesheet" href="/SMT/staticContent/css/shree.css">
	     <link rel="stylesheet" href="/SMT/staticContent/y_css/ui.jqgrid.min.css">
	     <link rel="stylesheet" href="/SMT/staticContent/y_css/jquery-ui.css">
	     <link rel="stylesheet" href="/SMT/staticContent/y_css/ui.jqgrid.css">
	     <script src="/SMT/staticContent/y_js/jquery-ui.min.js"></script>
	     <script src="/SMT/staticContent/js/jquery-ui.js"></script>
	     <script src="/SMT/staticContent/js/jqueryUi.js"></script>
	     <script src="/SMT/staticContent/y_js/jquery.jqgrid.min.js"></script>
	       
         <script src="/SMT/staticContent/js/otherOrder.js"></script>
          <script src="/SMT/staticContent/js/creditCustBill.js"></script> 


<style type="text/css">

</style>

<script type="text/javascript">
	
<% 
//String pattern = "yyyy-MM-dd";
String pattern = "dd-MM-yyyy";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			String todayDate = simpleDateFormat.format(new Date());

			System.out.println(todayDate);%>
	
</script>

<script type="text/javascript">


function grasstotal(){
	
	var total = document.getElementById("totalAmount").value;           
	var discount = document.getElementById("discount").value;
	var gross = +total - +discount;
	
	document.getElementById("grossTotal").value = gross;
}

</script>

     <%
        Long BillNo = 1l;
	 %>
	 <%
	    OtherBillDao data = new OtherBillDao();
		List stkList  = data.getLastBillNo();
		
		for(int i=0;i<stkList.size();i++){
			
			BillBean st = (BillBean)stkList.get(i);
			BillNo = st.getBillNo();
			
			BillNo++;
			
		}      
	        
         %> 

</head>
   <body class="purchase_form_img">
	<div class="container-fluid">

		<div class="row header_margin_top">
			<div align="center">
				<h2 class="form-name style_heading">Miscellaneous Bill</h2>
			</div>
		</div>

		<div class="row">
			<%-- <h4 align="right" style="color: red; margin-right: 130px;">Bill No :: <%out.println(BillNo); %></h4> --%>
			<h4 align="right" style="color: red; margin-right: 50px;">A/ <%out.println(todayDate); %>/00<%out.println(BillNo); %></h4>
		</div>

		<div class="row">
			<div class="col-sm-offset-1 col-md-10">
				<hr style="border-top-color: #c1b1b1;">
			</div>
		</div>
</div>
<form class="form-horizontal" action="" method="post"  name ="custord">
	
		<%-- <h2 align="center" class="form-heading style_heading" style="margin-top: 75px;">Miscellaneous Bill</h2>
		  <h3 align="right" style="color: red; margin-right: 20px;">Bill No :: <%out.println(BillNo); %></h3> --%>
				<div class="container">
			      <div class="row">
			        <div class="form-group">
						<div class="col-sm-2 col-sm-offset-1" align="center">
							<label class="control-label" >Barcode no:</label>
						</div>
						
						<div class="col-md-3">
						  <div class="input-group">
							<span class="input-group-addon"> <i
								class="glyphicon glyphicon-hand-right"></i>
							</span> 
						    <input type="text" id="key" class="form-control text-border" onchange="return getitemData1();" autofocus="key" placeholder="Enter Item Barcode" />
					     </div>	
					     </div>
					  
					 
					  <label class="col-md-2 control-label" for="customerName">Product Name<sup>*</sup></label>  
          					  <div class="col-md-3">
								<div class="input-group">
									 <span class="input-group-addon">
										<i class="glyphicon glyphicon-user"></i>
									</span> 
					 	
					 	<%
								ProductDetailDao cdd1 = new ProductDetailDao();
							 List cList1 =cdd1.getProductNames();
							
							%>
						<input list="prod_drop" id="productId"  class="form-control"  onchange="getproductgrid();">
				         <datalist id="prod_drop">
							
							<%
					           for(int i=0;i<cList1.size();i++){
					        	   ProductRegister cust1 =(ProductRegister)cList1.get(i);
							%>
		
						 <option data-value="<%=cust1.getPkProductId()%>" value="<%=cust1.getItemName() %>  =>  <%=cust1.getCategoryName()%>"> 
						
						
							<%
				      			}
				    		%>
						</datalist>    
				    </div>
                </div>
					 
					 </div>
					 
				</div>	
					   
		
				<div class="row" style="margin-top: 10px; ">
				    <div class="form-group">
					<div class="col-md-12">
						<!-- <div class="row"> -->
							<div class="table-responsive">
								<table id="list4"></table>
								<div id="jqGridPager"></div>
							</div>
						</div>
					</div>
					</div>
		
				
						<div class="row form-group" style="margin-top:10px;">
						    <div class="col-md-5" id="calculator" style="margin-left: 20px;">
								<!-- Screen and clear key -->
								<div class="top">
									<span class="clear">C</span>
									<div class="screen"></div>
								</div>
								
								<div class="keys">
									<!-- operators and other keys -->
									<span>7</span>
									<span>8</span>
									<span>9</span>
									<span class="operator">+</span>
									<span>4</span>
									<span>5</span>
									<span>6</span>
									<span class="operator">-</span>
									<span>1</span>
									<span>2</span>
									<span>3</span>
									<span class="operator">÷</span>
									<span>0</span>
									<span>.</span>
									<span class="eval">=</span>
									<span class="operator">x</span>
								</div>
							</div>

						<!-- PrefixFree -->
						<script src="/SMT/staticContent/js/PrefixFree 1.0.7.js" type="text/javascript" type="text/javascript"></script>
						
	                    <script src="/SMT/staticContent/js/calculator.js"></script>
						  <div class="col-md-5">
						  	<div class="row form-group" style="margin-top:10px;">
							<div class="col-md-6" align="right">
								<label class="control-label"  >Total Amount: </label>
							</div>
							<div class="col-md-6">
							  <div class="input-group">
							     <span class="input-group-addon">
							       Rs
						         </span>
								<input type="text" class="form-control" id="totalAmount"  placeholder="Total Amout"   readonly="readonly"/>
							  </div> 
							</div>
						</div>
							<div class="row form-group" style="margin-top:10px;">
							<div class="col-md-6" align="right">
								<label class="control-label"  > Discount: </label>
							</div>
							<div class="col-md-6">
							 <div class="input-group">
							   <span class="input-group-addon">
							     Rs
						       </span>
								<input type="text" class="form-control" id="discount"  placeholder="Discount In Rs" autofocus onkeyup="grasstotal(); return false;" />
							 </div> 
							</div> 
						</div>
							
							<div class="row form-group" style="margin-top:10px;">
							<div class="col-md-6" align="right">
								<label class="control-label"  > Gross Total: </label>
							</div>
							<div class="col-md-6">
							 <div class="input-group">
							    <span class="input-group-addon">
							      Rs
						        </span>
								<input type="text" class="form-control" id="grossTotal" placeholder="Gross Total" readonly="readonly"/>
							 </div> 
							</div> 
						</div>
							
						</div>
				</div>	
			</div>	
				<div class="row" style="margin-top:20px;">
				  <div class="form-group">
					<div align="center" class="margin-top-10">
						<button type='button' class="btn btn-success btn-lg bottomButtons btn-md button_hw button_margin_right" id="btnSubmit"  onclick=" return resotherbill();" style="width:150px;">Print</button>
						<button type='button' class="btn btn-danger btn-lg bottomButtons btn-md button_hw button_margin_right">Cancel</button>
					</div>
				  </div>	
				</div> 
				
		</form>		
				
		</div>
  </body>

        <div class="row footer_margin_top" align="center">
			<%@include file="y_commons/footer.jsp"%>
		</div> 

</html>

