
<%@page import="com.smt.hibernate.SupplierDetail"%>
<%@page import="com.smt.helper.SupplierDetailHelper"%>
<%@page import="com.smt.helper.ProductDetailHelper"%>
<%@page import="com.smt.hibernate.Category"%>
<%@page import="java.util.List"%>
<%@page import="com.smt.helper.CategoryHelper"%>
<%@page import="com.smt.bean.ProductNameBean"%>
<%@page import="com.smt.dao.GoodReciveDao"%>

<%@page import="com.smt.hibernate.GoodReceive"%>

<% boolean isHome=false;%>
<%@include file="y_commons/header1.jsp"%>
<style>
.btn-mm {
    transition: all 0.2s ease;
    background-color: white ;
    border: 1px solid !important;
   /*  box-shadow: 0 3px  !important;*/
    border-radius: 23px; 
    font-size:19px;
     width: 99px;
      height:27px;
   
     
}
input[type="radio"] {
     position: absolute;
  visibility: hidden;
}
input[type="radio"] + div {
    position: relative;
}
input[type="radio"]:checked + div {
  background-color:#BA0707;
}
input[type="radio"]:checked + div>span {
  color: white;
}
input[type="radio"] + div>span {
position: relatives;
top: 2%;}

@media screen and (max-width: 600px){
.btn-mm {
    transition: all 0.2s ease;
    background-color: white ;
    border: 1px solid !important;
   /*  box-shadow: 0 3px  !important; */
    border-radius: 23px;
    font-size:14px;
     width: 99px;
      height:27px;
   
     
}
input[type="radio"] {
     position: absolute;
  visibility: hidden;
}
input[type="radio"] + div {
    position: relative;
}
input[type="radio"]:checked + div {
  background-color:#f3073ad;
}
input[type="radio"]:checked + div>span {
  color: white;
}
input[type="radio"] + div>span {
position: relative;
top: 2%;
}
}

</style>
	
	<script type="text/javascript">
	
	 function grasstotal1(){
			
			var total = document.getElementById("resolution1").value;           
			var extraDiscount = document.getElementById("extraDiscount").value;
			var disAmount =  (extraDiscount/100)*total;
			var gross = +total - +disAmount;
			document.getElementById("resolution").value = Math.round(gross);
    }


    function grasstotal(){
    var extraDiscount = document.getElementById("extraDiscount").value;
    var total = document.getElementById("resolution1").value;
    if(extraDiscount != ""){
    	var disAmount =  (extraDiscount/100)*total;
		var gross = +total - +disAmount;
		var total = Math.round(gross);
		var expence = document.getElementById("expence").value;
		var gross = +total + +expence;
		
		document.getElementById("resolution").value = gross;
    	
    }else{       
    	var expence = document.getElementById("expence").value;
    	var gross = +total + +expence;
    	
    	document.getElementById("resolution").value = gross;
    }
	
    }
    
    function grasstotalTax(){
    	
    	var extraDiscount = document.getElementById("extraDiscount").value;
        var total = document.getElementById("resolution1").value;
        if(extraDiscount != ""){
        	var disAmount =  (extraDiscount/100)*total;
    		var gross = +total - +disAmount;
    		var total = Math.round(gross);
    		var expence = document.getElementById("expence").value;
    		var expencetx = document.getElementById("txPerexpence").value;
    		var extaxamt = (expencetx/100)*expence;
    		var finalextax = +expence + +extaxamt;
    		var gross = +total + +finalextax;
    		
    		document.getElementById("finalExpenses").value = finalextax;
    		document.getElementById("resolution").value = gross;
        	
        }else{       
        	var expence = document.getElementById("expence").value;
        	var expencetx = document.getElementById("txPerexpence").value;
    		var extaxamt = (expencetx/100)*expence;
    		var finalextax = +expence + +extaxamt;
    		var gross = +total + +finalextax;
    		
    		document.getElementById("finalExpenses").value = finalextax;
    		document.getElementById("resolution").value = gross;
        }
    }

</script>

         <script src="/SMT/staticContent/js/jquery-1.12.3.min.js"></script>
	     <link rel="stylesheet" href="/SMT/staticContent/y_css/ui.jqgrid.min.css">
	     <link rel="stylesheet" href="/SMT/staticContent/y_css/jquery-ui.css">
	     <link rel="stylesheet" href="/SMT/staticContent/y_css/ui.jqgrid.css">
	     <script src="/SMT/staticContent/y_js/jquery-ui.min.js"></script>
	     <script src="/SMT/staticContent/js/jquery-ui.js"></script>
	     <script src="/SMT/staticContent/js/jqueryUi.js"></script>
	     <script src="/SMT/staticContent/y_js/jquery.jqgrid.min.js"></script>
   
	     <script src="/SMT/staticContent/y_js/newgoodsreceived.js"></script>
	
	<body class="purchase_form_img">
	<div class="container-fluid">

		<div class="row header_margin_top">
			<div align="center">
				<h2 class="form-name style_heading">Purchase Goods Received</h2>
			</div>
		</div>

		<div class="row">
			<div class="form-group" align="right">
				<div class="col-sm-offset-6 col-md-5 control-label">
					<div id="date">
						<label id="demo"></label>
						<script>
							var date = new Date();
							document.getElementById("demo").innerHTML = date
									.toDateString();
						</script>
					</div>
				</div>
			</div>
		</div>
		
		
			<div class="row">
					<div class="col-md-6 col-sm-12 col-xs-12 col-xl-4 col-lg-4"></div>
					

					<%
						Long Txid = 1l;
					%>
				 	<%
						GoodReciveDao dao = new GoodReciveDao();
						List listtxid = dao.getSupplierPaymentTxid();

						for (int i = 0; i < listtxid.size(); i++) {

							GoodReceive bean = (GoodReceive) listtxid.get(i);
							Txid = bean.getTxId();
							Txid++;

						}
					%> 

					<div>

						<div align="right">
							<h3 style="color: red;">
								PO Number ::
								<%
								out.println(Txid);
							%>
							</h3>
						</div>

					</div>

				</div>
		

		<div class="row">
			<div class="col-sm-offset-1 col-md-10">
				<hr style="border-top-color: #c1b1b1;">
			</div>
		</div>
		<!---------------------------------++++++++++------Cash and Credit Radio---------++++++++++-----------------------------> 
				
	<div class=" " id="my_styles" >
	<div class="textalign center" align="center">
			
   			 <label>
       		    <input type="radio" checked name="customertype" id="customertype" checked="checked"
					   onclick="openCashCustomerBilling()" > 
                <div class="btn1 btn-mm text-center"><span>Cash</span></div> </label>
                
                 <label><input type="radio" name="customertype" id="customertype"
					   onclick="openCreditCustomerBilling()"> 
                <div class="btn1 btn-mm text-center"><span>Credit</span></div></label>
            
        		
       </div>		
	 </div>
	              	
<!-- --------------------------------------------------end----------------------------------- -->
		<form action="goods" method="post" name="good" class="form-horizontal" style="margin-top:1%" >

			<div class="row">
				<div class="form-group">
					<div class="col-sm-2 col-sm-offset-1" align="center">
						<label class="control-label">Bill No:<sup style="color: red;">*</sup></label>
					</div>
					<div class="col-sm-3">
						<div class="input-group">
							<span class="input-group-addon"> <i
								class="glyphicon glyphicon-hand-right"></i>
							</span> <input type="text" id="billNo" id="jander" name="billNo"
								autofocus="autofocus" class="form-control" placeholder="Bill No" />
						</div>
					</div>

					<div class="col-sm-2" align="center">
						<label class="control-label">Supplier Name:<sup style="color: red;">*</sup> </label>
					</div>
					<div class="col-sm-3">
						<%
							SupplierDetailHelper poHelper = new SupplierDetailHelper();
							List supplierList = poHelper.getAllSuppliers();
						%>

						<div class="input-group">
							<span class="input-group-addon"> <i
								class="glyphicon glyphicon-user"></i>
							</span> 
							
							<input list="supplierId_drop" id="supplierId" class="form-control"  >
						<datalist id="supplierId_drop">
						<%
							for(int i =0 ;i<supplierList.size();i++)
								{
								SupplierDetail supplier = (SupplierDetail)supplierList.get(i);
						%>
							<option data-value="<%=supplier.getSupplierId()%>" value="<%=supplier.getSupplierName()%>"> 
						<%   	
								}	
						%>
						</datalist>
							
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="form-group">
					<div class="col-sm-2 col-sm-offset-1" align="center">
						<label class="control-label">Contact Person:<sup style="color: red;">*</sup></label>
					</div>
					<div class="col-sm-3">
					
					<%
							SupplierDetailHelper poHelper1 = new SupplierDetailHelper();
							List supplierList1 = poHelper1.getAllSuppliers();
						%>
						<div class="input-group">
							<span class="input-group-addon"> <i
								class="glyphicon glyphicon-phone"></i>
							</span> 
							
							<!-- <input type="text" id='contactPerson' name="contactPerson"
								 class="form-control" placeholder="Contact Person" /> -->
								 	<input list="contactId_drop" id="contactPerson" class="form-control"  placeholder="Contact Person">
								 
								 
								 <datalist id="contactId_drop">
						<%
							for(int i =0 ;i<supplierList1.size();i++)
								{
								SupplierDetail supplier1 = (SupplierDetail)supplierList1.get(i);
						%>
							<option data-value="<%=supplier1.getSupplierId()%>" value="<%=supplier1.getContactPerson()%>"> 
						<%   	
								}	
						%>
						</datalist>
						</div>
					</div>



					<div class="col-sm-2" align="center">
						<label class="control-label">Purchase Date:<sup style="color: red;">*</sup></label>
					</div>
					<div class="col-sm-3">
						<div class="input-group">
							<span class="input-group-addon"> <i
								class="glyphicon glyphicon-calendar"></i>
							</span> <input type="date" id='pDate' name="pDate" class="form-control"
								id="jander" placeholder="Purchase Date" />
						</div>
					</div>

                   <!--  <div class="col-sm-2" align="center">
						<label class="control-label">Vat:</label>
					</div>
					<div class="col-sm-3">
						<div class="input-group">
							<span class="input-group-addon"> <i
								class="glyphicon glyphicon-phone"></i>
							</span> <input type="text" id='vat' name="vat"
								 class="form-control" placeholder="Vat" />
						</div>
					</div>
					 -->
				</div>
			</div>

			
			<div class="row">
				<div class="form-group">
					<div class="col-sm-2 col-sm-offset-1" align="center">
						<label class="control-label">Item List:<sup style="color: red;">*</sup></label>
					</div>
					<div class="col-sm-5">
					
					    <%
					    ProductDetailHelper item = new ProductDetailHelper();
						List itemList = item.getAllItemName();
						%>
						<div class="input-group">
							<span class="input-group-addon"> <i
								class="glyphicon glyphicon-hand-right"></i>
								</span>
								<input list="itemId_drop" id="itemName" class="form-control" onchange="getProductList()" >
						        <datalist id="itemId_drop">
						       <%
							      for(int j =0 ;j<itemList.size();j++)
								{
							    	  ProductNameBean itm = (ProductNameBean)itemList.get(j);
						       %>
							      
			             <option data-value="<%=itm.getCaregoryName()%>" value="<%=itm.getCaregoryName()%> =>Itemname=> <%=itm.getItemName()%>  =>ModelName=> <%=itm.getModelName()%>" myvalue="<%=itm.getItemName()%>" myvalue1="<%=itm.getHsnsacno()%>"> 
							    
							    
							    
						      <%   	
						      
								}	
						       %>
						</datalist>
							
						</div>
					</div>

					
				</div>
			</div>

			<div class="row">
					<div class="col-sm-10 col-sm-offset-1">
						<div class="table-responsive">
							<table id="jqGrid"></table>
			                <div id="jqGridPager"></div>
						</div>
					</div>
				</div>
				
			<div class="row row_margin">
				<div class="form-group">
				     <div class="col-sm-1 col-sm-offset-1" align="center">
						<label class="control-label">Discount:</label>
					</div>

					<div class="col-sm-2">
						<div class="input-group">
							<span class="input-group-addon"> <i
								class="glyphicon glyphicon-hand-right"></i>
							</span> <input type="text" id='extraDiscount' name="extraDiscount"
								class="text-border form-control" placeholder="In %"
								autofocus onkeyup="grasstotal1(); return false;"
								style="background-color: rgba(251, 243, 0, 0.27)" id="jander" />
						</div>
					</div>
				
				
					<div class="col-sm-1" align="center">
						<label class="control-label">Expenses:</label>
					</div>

					<div class="col-sm-2">
						<div class="input-group">
							<span class="input-group-addon"> <i
								class="glyphicon glyphicon-hand-right"></i>
							</span> <input type="text" id='expence' name="expence"
								class="text-border form-control" placeholder="Expenses"
								autofocus onchange="grasstotal(); return false;"
								style="background-color: rgba(251, 243, 0, 0.27)" id="jander" />
						</div>
					</div>

					<div class="col-sm-2" align="right">
						<label class="control-label">Tax % On Expenses:</label>
					</div>

					<div class="col-sm-2">
						<div class="input-group">
							<span class="input-group-addon"> <i
								class="glyphicon glyphicon-hand-right"></i>
							</span> <input type="text" id='txPerexpence' name="txPerexpence"
								class="text-border form-control" placeholder="Tax % On Expenses"
								autofocus onchange="grasstotalTax(); return false;"
								style="background-color: rgba(251, 243, 0, 0.27)" id="jander" />
						</div>
					</div>

				</div>
			</div>
			
			
			<div class="row row_margin">
				<div class="form-group">
				     <div class="col-sm-1 col-sm-offset-1" align="center">
						<label class="control-label">Final Expenses:</label>
					</div>

					<div class="col-sm-2">
						<div class="input-group">
							<span class="input-group-addon"> <i
								class="glyphicon glyphicon-hand-right"></i>
							</span> <input type="text" id='finalExpenses' name="finalExpenses" readonly="readonly"
								class="text-border form-control" 
								style="background-color: rgba(251, 243, 0, 0.27)" id="jander" />
						</div>
					</div>
				
				
					

					<div class="col-sm-2" align="right">
						<label class="control-label" style="font-size: 30px">Total:</label>
					</div>
					<div class="col-sm-6" >
						<div class="input-group">
							<input type="text" name="resolution" id="resolution" readonly="readonly"
								class="form-group"
								style="font-size: 30px; float: right; width: 200px; height: 50px; background-color: rgba(251, 243, 0, 0.27);" />
								 <input type="hidden" id='resolution1' name="resolution1" class="form-control"  />
						</div>
					</div>

				</div>
			</div>

			<div class="row buttons_margin_top">
			<div align="center">
    			<input type="button" class="btn btn-lg btn-success btn-md button_hw button_margin_right" name="btnSubmit" id="btnSubmit" onclick="validateRegGoodReceive()" value="Save" /> 
				<input type="reset" value="Cancel" onclick="reset()" class="btn btn-lg btn-danger btn-md button_hw button_margin_right"/>
				<!-- <input type="button" onclick="window.location.href='http://localhost:8080/SMT/jsp/supplierAccountDetails.jsp'" 
				value="Fill Supplier Payement" class="btn btn-lg btn-primary btn-md" />  -->
			
			</div>
		</div>	
				
</form>
	   
		<div class="row footer_margin_top" align="center">
			<%@include file="y_commons/footer.jsp"%>
		</div> 
    
    </div>
   </body>
  </html>
  
	