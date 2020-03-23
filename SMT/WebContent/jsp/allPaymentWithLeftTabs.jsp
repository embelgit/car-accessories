
<%@page import="java.util.List"%>
<%@page import="com.smt.dao.CustomerDetailsDao" %>
<%@page import="com.smt.hibernate.CustomerDetailsBean" %>
<%@page import="com.smt.dao.EmployeeDetailsDao" %>
<%@page import="com.smt.hibernate.EmployeeDetailsBean" %>
<%@page import="com.smt.dao.ExpenditureDetailsDao" %>
<%@page import="com.smt.hibernate.ExpenditureDetailsBean" %>
<%@page import="com.smt.dao.SupplierDetailDao"%> 
<%@page import="com.smt.hibernate.SupplierDetail"%>
<% boolean isHome=false;%>
<%@include file="y_commons/header1.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	
	<link rel="stylesheet" href="/SMT/staticContent/css/tabDemo.css">
	
	<script type="text/javascript" src="/SMT/staticContent/js/cashbankbook.js"></script>

<body  class="master_form_img">
<div class="container-fluid" > 
<div class="row" style="min-height:300px;">
    <div  class="col-md-12">
        <h3>Left Tabs</h3>
        <hr/>
        <div class="col-md-2">
            <!-- required for floating -->
            <!-- Nav tabs -->
            <ul class="nav nav-tabs">
                <li class="active"><a href="#home" data-toggle="tab">Supplier Payment</a></li>
                <li><a href="#profile" data-toggle="tab">Customer Payment</a></li>
                <li><a href="#messages" data-toggle="tab">Employee Payment</a></li>
                <li><a href="#settings" data-toggle="tab">Expenditure Payment</a></li>
            </ul>
        </div>
        <div class="col-xs-9">
            <!-- Tab panes -->
    <div class="tab-content">
    	
    	<div class="tab-pane fade in active" id="home">
       		<form class="form-horizontal" method="post" action="" name="spmt">
         		<div class="row">
			    	<div align="center">
			  			<h2 class="form-name style_heading">Supplier Payment</h2>
			  		</div>
			 	
		     <div class="row">
				     <div class="col-sm-offset-1 col-mxl-12">
						  		<hr style="border-top-color:#c1b1b1;">
				     </div>	
		   		 </div>
		    </div>
        	       <div class="row form-group">
           				 <label class="col-md-3 control-label" for="supplier">Supplier Name<sup>*</sup></label>  
          					  <div class="col-md-3">
								<div class="input-group">
									<span class="input-group-addon">
										<i class="	glyphicon glyphicon-hand-right"></i>
									</span>
									
							
							<%
							    SupplierDetailDao sdd = new SupplierDetailDao();
           						List sList =sdd.getAllSupplier();
							
							%>
							
							<input list="sup_drop" id="supplier" onchange="bill.getAllBills();" class="form-control">
				<datalist id="sup_drop">
							
							<%
					           for(int i=0;i<sList.size();i++){
					        	   SupplierDetail sup =(SupplierDetail)sList.get(i);
							%>
		
							<option data-value="<%=sup.getSupplierId()%>" value="<%=sup.getSupplierName() %>">
							<%
				      			}
				    		%>
						</datalist>           	
					</div>
           		</div>
            
           				    <label class="col-md-2 control-label" for="bill_no"> Bill No<sup>*</sup> </label>  
          					  <div class="col-md-3">
								<div class="input-group">
									<span class="input-group-addon">
										No
									</span>
           		 					
           		 					<select class="form-control input-md" id='billNo'  name="billNo" onchange="bill.getTotalAmtByBills()">
									</select>
           						 </div>
							</div>
						  </div> 
					
					<div class="row form-group">
           				<label class="col-md-2 col-md-offset-6 control-label" for="totalAmt"> Total Amount</label>  
           							 <div class="col-md-3">
										<div class="input-group">
											<span class="input-group-addon">
												Rs
											</span>
           									  <input readonly="readonly" id="totalAmount" name="totalAmount" class="form-control" placeholder="Total Amount">
           							 </div>
								</div>
					</div>
						  
				<div class="row form-group">
						 <label class="col-md-3 control-label" for="personname">Accountant Name<sup>*</sup> </label>  
			           		 <div class="col-md-3">
								<div class="input-group">
									<span class="input-group-addon">
										<i class="glyphicon glyphicon-user"></i>
									</span>
			           		   <input id="personname" name="personname" placeholder="Accountant Name" class="form-control input-md" type="text" >
			           		 </div>
						</div>
				
			
          					<label class="col-md-2 control-label" for="balanceAmt"> Balance Amount</label>  
           							 <div class="col-md-3">
										<div class="input-group">
											<span class="input-group-addon">
												Rs
											</span>
           									  <input type="text" readonly="readonly" id="balanceAmount" name="balanceAmount" class="form-control input-md" placeholder="Balance Amount">
           							 </div>
								</div>
		  						
	</div>
      	  
						  
	 <div class="row form-group">
				
				<label class="col-md-3 control-label" for="paymentMode"> Payment Mode<sup>*</sup></label>  
	           		<div class="col-md-3">
							<div class="input-group">
								<span class="input-group-addon">
										<i class="glyphicon glyphicon-usd"></i>
								</span>
	           					<select class="form-control" id="paymentMode">
										<option value="selected">-Select Type--</option>
										<option value="cash">Cash</option>
										<option value="cheque">Cheque</option>
										<option value="card">Card</option>
										<option value="neft">NEFT</option>
								</select>	
	           				</div>
						</div>
	 
	<script>
		
		$(document).ready(function(){
	  		 $("#paymentMode").change(function(){
	       	$(this).find("option:selected").each(function(){
	           	if($(this).attr("value")=="cheque"){
	           	
	           	$("#cheque_no").show(); 
	           	
	           	$("#neft_acc_no").hide(); 
	           	$("#card_no").hide();
	           	}
	          	 else if($(this).attr("value")=="card"){
	           	
	          		$("#card_no").show(); 	
	          		
	          		$("#neft_acc_no").hide(); 
	        		$("#cheque_no").hide();
	           }
	          	 else if($(this).attr("value")=="neft"){
	                	
	           		$("#neft_acc_no").show(); 	
	           		
	           		$("#card_no").hide(); 
	        		$("#cheque_no").hide();
	            }
	          	 else if($(this).attr("value")=="cash"){
	             	
	            		$("#neft_acc_no").hide(); 
	            		$("#cheque_no").hide();
	            		$("#card_no").hide(); 
	             }
	           	
	          	else if($(this).attr("value")=="selected"){
	             	
	        		$("#neft_acc_no").hide(); 
	        		$("#cheque_no").hide();
	        		$("#card_no").hide(); 
	         }
	          
	       });
	   }).change();
		});	
		</script>
	 </div> 
			          
			     <div class="form-group">
				
				  <label class="col-md-3 control-label" for="paymentMode"> Payment Type<sup>*</sup></label>  
	           		<div class="col-md-3">
							<div class="input-group">
								<span class="input-group-addon">
										<i class="glyphicon glyphicon-usd"></i>
								</span>
	           					<select class="form-control" id="paymentType">
										<option value="selected">-Select Type--</option>
										<option value="credit">Credit</option>
										<option value="debit">Debit</option>
										
								</select>	
	           				</div>
						</div>
						
						
						<div class="col-md-5">
										<div class="input-group">
											<span class="input-group-addon">
												Rs
											</span>
           									  <input  id="supPay" name="supPay" class="form-control" placeholder="Enter Amount">
           							 </div>
						</div>
		  						
			     
			     </div>
			     
			     
			          
		    	<div class="row form-group" >

            					<div id="cheque_no" >
            						
										
									<div class="col-md-3 col-md-offset-3 first">	
										<input class="form-control" type="text" name="chequeNum" id="chequeNum" placeholder="Cheque No." />  
									</div>
								
									<div class="col-md-3 col-md-offset-3 first">	
										<input class="form-control" type="text" name="nameOnCheck" id="nameOnCheck" placeholder="Name On check" />  
									</div>
								</div>
											
											<div id="card_no" class="form-group">
												
												<div class="col-md-3 col-md-offset-3 first">	
													<input class="form-control" type="text" name="cardNum" id="cardNum" placeholder="Card No." />  
												</div>
												
											</div>
											
											<div id="neft_acc_no" class="form-group">
												
												<div class="col-md-3 col-md-offset-3 first">	
													<input class="form-control" type="text" name="accNum" id="accNum" placeholder="Account No." />  
												</div>
												
												<div class="col-md-3 col-md-offset-3 first">	
													<input class="form-control" type="text" name="bankName" id="bankName" placeholder="Name Of Bank" />  
												</div>
												</div>
				</div>
			          
			<div class="form-group row">
			  <div class="col-md-12 text-center">
			  		<input type="button" id="btn1" name="btn1"  class="btn btn-large btn-success button-height-width btn-md button_hw button_margin_right"  onclick="supplierPayment(); return false;" value="Submit">
		            <input type="reset" id="btn1"  class="btn btn-large btn-danger   button-height-width btn-md button_hw button_margin_right" name="btn1" value="Cancel">
			  
			   </div>
	 		</div>       	    
	</form>
  </div>
         
           <!---------Customer Payment --------->
                
    <div class="tab-pane" id="profile">
			<form class="form-horizontal" method="post" action="" name="cust">
	         	<div class="row">
				    <div align="center">
				  		<h2 class="form-name style_heading">Credit Customer Payment</h2>
				  	</div>
				 	
			     <div class="row">
					     <div class="col-sm-offset-1 col-md-10">
							  		<hr style="border-top-color:#c1b1b1;">
					     </div>	
			   		 </div>
			    </div>
         	       <div class="row form-group">
           				 <label class="col-md-3 control-label" for="customerName">Customer Name<sup>*</sup></label>  
          					  <div class="col-md-3">
								<div class="input-group">
									<span class="input-group-addon">
										<i class="glyphicon glyphicon-user"></i>
									</span>
						
							<%
								CustomerDetailsDao cdd = new CustomerDetailsDao();
           						List cList =cdd.getAllCustomer();
							
							%>
									<input list="cust_drop" id="creditCustomer"  class="form-control"  onchange="getBillByCustomer()">
				<datalist id="cust_drop">
							
							<%
					           for(int i=0;i<cList.size();i++){
					        	   CustomerDetailsBean cust =(CustomerDetailsBean)cList.get(i);
							%>
		
						<option data-value="<%=cust.getCustId()%>"><%=cust.getFirstName() %> <%=cust.getLastName() %> </option>
							<%
				      			}
				    		%>
						</datalist> 
				</div>
           </div>
         </div>      
           				  
	 
						  
					 <div class="row form-group">
					 <label class="col-md-3 control-label" for="bill_no"> Bill No<sup>*</sup> </label>  
          					  <div class="col-md-3">
								<div class="input-group">
									<span class="input-group-addon">
										No
									</span>
           		 					
           		 					<select class="form-control input-md" id='billNo1'  name="billNo" onchange="getTotalAmountByBill()">
									</select>
           						 </div>
							</div>
           				<label class="col-md-2  control-label" for="totalAmt"> Total Amount</label>  
           							 <div class="col-md-3">
										<div class="input-group">
											<span class="input-group-addon">
												Rs
											</span>
           									  <input readonly="readonly" id="totalAmount1" name="totalAmount" class="form-control" placeholder="Total Amount">
           							 </div>
								</div>
            					
					 </div>
						  
		<div class="row form-group">
						
						 <label class="col-md-3 control-label" for="personname">Accountant Name <sup>*</sup></label>  
			           		 <div class="col-md-3">
								<div class="input-group">
									<span class="input-group-addon">
										<i class="glyphicon glyphicon-user"></i>
									</span>
			           		   <input id="personname1" name="personname" placeholder="Accountant Name" class="form-control input-md" type="text" >
			           		 </div>
						</div>   
		

          	
          			<label class="col-md-2 control-label" for="balanceAmt"> Balance Amount</label>  
           							 <div class="col-md-3">
										<div class="input-group">
											<span class="input-group-addon">
												Rs
											</span>
           									  <input readonly="readonly" id="balanceAmount1" name="balanceAmount" class="form-control" placeholder="Balance Amount">
           							 </div>
								</div>
          	
					
	</div>
       	<div class="row form-group">
				
				<label class="col-md-3 control-label" for="paymentMode"> Payment Mode<sup>*</sup></label>  
	           		<div class="col-md-3">
							<div class="input-group">
								<span class="input-group-addon">
										<i class="glyphicon glyphicon-usd"></i>
								</span>
	           					<select class="form-control" id="paymentMode1">
										<option value="selected1">-Select Type--</option>
										<option value="cash">Cash</option>
										<option value="cheque">Cheque</option>
										<option value="card">Card</option>
										<option value="neft">NEFT</option>
								</select>	
	           				</div>
						</div>
	 <script>
		
		$(document).ready(function(){
	  		 $("#paymentMode1").change(function(){
	       	$(this).find("option:selected").each(function(){
	           	if($(this).attr("value")=="cheque1"){
	           	
	           	$("#cheque_no1").show(); 
	           	
	           	$("#neft_acc_no1").hide(); 
	           	$("#card_no1").hide();
	           	}
	          	 else if($(this).attr("value")=="card1"){
	           	
	          		$("#card_no1").show(); 	
	          		
	          		$("#neft_acc_no1").hide(); 
	        		$("#cheque_no1").hide();
	           }
	          	 else if($(this).attr("value")=="neft1"){
	                	
	           		$("#neft_acc_no1").show(); 	
	           		
	           		$("#card_no1").hide(); 
	        		$("#cheque_no1").hide();
	            }
	          	 else if($(this).attr("value")=="cash1"){
	             	
	            		$("#neft_acc_no1").hide(); 
	            		$("#cheque_no1").hide();
	            		$("#card_no1").hide(); 
	             }
	           	
	          	else if($(this).attr("value")=="selected1"){
	             	
	        		$("#neft_acc_no1").hide(); 
	        		$("#cheque_no1").hide();
	        		$("#card_no1").hide(); 
	         }
	          
	       });
	   }).change();
		});	
		</script>
						
		</div>	          
			   <div class="row form-group">
				
				  <label class="col-md-3 control-label" for="paymentMode"> Payment Type<sup>*</sup></label>  
	           		<div class="col-md-3">
							<div class="input-group">
								<span class="input-group-addon">
										<i class="glyphicon glyphicon-usd"></i>
								</span>
	           					<select class="form-control" id="paymentType1">
										<option value="selected">-Select Type--</option>
										<option value="credit">Credit</option>
										<option value="debit">Debit</option>
										
								</select>	
	           				</div>
						</div>
						
						
						<div class="col-md-5">
										<div class="input-group">
											<span class="input-group-addon">
												Rs
											</span>
           									  <input  id="custPay" name="custPay" class="form-control" placeholder="Enter Amount">
           							 </div>
						</div>
			     </div> 
			 <div class="row form-group" >

            					<div id="cheque_no1" >
            						
									<div class="col-md-3 col-md-offset-3 first">	
										<input class="form-control" type="text" name="chequeNum" id="chequeNum1" placeholder="Cheque No." />  
									</div>
									<div class="col-md-3 col-md-offset-3 first">	
										<input class="form-control" type="text" name="nameOnCheck" id="nameOnCheck1" placeholder="Name On check" />  
									</div>
								</div>
											<div id="card_no1" class="form-group">
												
												<div class="col-md-3 col-md-offset-3 first">	
													<input class="form-control" type="text" name="cardNum" id="cardNum1" placeholder="Card No." />  
												</div>
												
											</div>
											
											<div id="neft_acc_no1" class="form-group">
												
												<div class="col-md-3 col-md-offset-3 first">	
													<input class="form-control" type="text" name="accNum" id="accNum1" placeholder="Account No." />  
												</div>
												
												<div class="col-md-3 col-md-offset-3 first">	
													<input class="form-control" type="text" name="bankName" id="bankName1" placeholder="Name Of Bank" />  
												</div>
											</div>
								</div>
			          
			          
			     <div class="form-group row">
			            <div class="col-md-12 text-center">
			           	 	
			            <input type="button" id="btn2" name="btn2"  class="btn btn-large btn-success button-height-width btn-md button_hw button_margin_right"  onclick="customerPaymentValidation(); return false;" value="Submit">
		            	<input type="reset" id="btn2"  class="btn btn-large btn-danger   button-height-width btn-md button_hw button_margin_right" name="btn2" value="Cancel">
			            </div>
			      </div>       	    
			 </form>
	</div>  
	
	<!------------ Employee Payment ---------->
	
           <div class="tab-pane" id="messages">
           		<form class="form-horizontal" method="post" action="" name="emp">
   	    	 <div class="row">
			    <div align="center">
			  		<h2 class="form-name style_heading">Employee Payment</h2>
			  	</div>
			 	
		     <div class="row">
				     <div class="col-sm-offset-1 col-md-10">
						  		<hr style="border-top-color:#c1b1b1;">
				     </div>	
		   		 </div>
		    </div>
    	     	 <div class="row form-group">
    	     	
           				 <label class="col-md-3 control-label" for="employeename">Employee Name<sup>*</sup></label>  
          					  <div class="col-md-3">
								<div class="input-group">
									<span class="input-group-addon">
										<i class="glyphicon glyphicon-user"></i>
									</span>
							
							 <%
								EmployeeDetailsDao eedd = new EmployeeDetailsDao();
           						List mList =eedd.getAllMainEmployee();
							
							%>
						<input list="emp_drop" id="employee"  class="form-control">
				<datalist id="emp_drop">
							
							<%
					           for(int i=0;i<mList.size();i++){
					        	   EmployeeDetailsBean detailsBean =(EmployeeDetailsBean)mList.get(i);
							%>
		
						<option data-value="<%=detailsBean.getEmpId()%>" ><%=detailsBean.getFirstName()%> <%=detailsBean.getLastName()%></option>
							<%
				      			}
				    		%>
						</datalist> 
								</div>
           					 </div>
            
           				 <label class="col-md-2 control-label" for="personName">Accountant Name <sup>*</sup></label>  
	          					  <div class="col-md-3">
									<div class="input-group">
										<span class="input-group-addon">
											<i class="glyphicon glyphicon-user"></i>
										</span>
	           		 						  <input id="personName2" name="personName" placeholder="Accountant Name" class="form-control input-md" type="text">
	           						 </div>
								</div>
           				 
				 </div>
         	 
         	  	<div class="row form-group">
	           				  
								
								<label class="col-md-3 control-label" for="reason2">Reason<sup>*</sup></label>  
	          					  <div class="col-md-3">
									<div class="input-group">
										<span class="input-group-addon">
											<i class="glyphicon glyphicon-user"></i>
										</span>
	           		 						  <input id="reason2" name="reason2" placeholder="Reason" class="form-control input-md" type="text">
	           						 </div>
								</div>
								
								
					<label class="col-md-2 control-label" for="paymentMode"> Payment Mode<sup>*</sup></label>  
	           		       <div class="col-md-3">
							<div class="input-group">
								<span class="input-group-addon">
										<i class="glyphicon glyphicon-usd"></i>
								</span>
	           					<select class="form-control" id="paymentMode2">
										<option value="selected2">-Select Type--</option>
										<option value="cash">Cash</option>
										<option value="cheque">Cheque</option>
										<option value="card">Card</option>
										<option value="neft">NEFT</option>
								</select>	
	           				</div>
						</div>
				
			 	</div>
         
        
         
         
            	
	 <script>
		
		$(document).ready(function(){
	  		 $("#paymentMode2").change(function(){
	       	$(this).find("option:selected").each(function(){
	           	if($(this).attr("value")=="cheque2"){
	           	
	           	$("#cheque_no2").show(); 
	           	
	           	$("#neft_acc_no2").hide(); 
	           	$("#card_no2").hide();
	           	}
	          	 else if($(this).attr("value")=="card2"){
	           	
	          		$("#card_no2").show(); 	
	          		
	          		$("#neft_acc_no2").hide(); 
	        		$("#cheque_no2").hide();
	           }
	          	 else if($(this).attr("value")=="neft2"){
	                	
	           		$("#neft_acc_no2").show(); 	
	           		
	           		$("#card_no2").hide(); 
	        		$("#cheque_no2").hide();
	            }
	          	 else if($(this).attr("value")=="cash2"){
	             	
	            		$("#neft_acc_no2").hide(); 
	            		$("#cheque_no2").hide();
	            		$("#card_no2").hide(); 
	             }
	           	
	          	else if($(this).attr("value")=="selected2"){
	             	
	        		$("#neft_acc_no2").hide(); 
	        		$("#cheque_no2").hide();
	        		$("#card_no2").hide(); 
	         }
	          
	       });
	   }).change();
		});	
		</script>
          	
					
	
           					
	  
		<div class="row form-group">
				
				  <label class="col-md-3 control-label" for="paymentMode"> Payment Type<sup>*</sup></label>  
	           		<div class="col-md-3">
							<div class="input-group">
								<span class="input-group-addon">
										<i class="glyphicon glyphicon-usd"></i>
								</span>
	           					<select class="form-control" id="paymentType2">
										<option value="selected">-Select Type--</option>
										<option value="credit">Credit</option>
										<option value="debit">Debit</option>
										
								</select>	
	           				</div>
						</div>
						
						
						<div class="col-md-5">
										<div class="input-group">
											<span class="input-group-addon">
												Rs
											</span>
           									  <input  id="empPay" name="empPay" class="form-control" placeholder="Enter Amount">
           							 </div>
						</div>
		  						
			     
			     </div> 				  

          
          	<div class="row form-group" >

            					<div id="cheque_no2" >
            						
									<div class="col-md-3 col-md-offset-3 first">	
										<input class="form-control" type="text" name="chequeNum" id="chequeNum2" placeholder="Cheque No." />  
									</div>
									
									<div class="col-md-3 col-md-offset-3 first">	
										<input class="form-control" type="text" name="nameOnCheck" id="nameOnCheck2" placeholder="Name On check" />  
									</div>
								</div>
											
											<div id="card_no2" class="form-group">
												
												<div class="col-md-3 col-md-offset-3 first">	
													<input class="form-control" type="text" name="cardNum" id="cardNum2" placeholder="Card No." />  
												</div>
												
											</div>
											
											<div id="neft_acc_no2" class="form-group">
												
												<div class="col-md-3 col-md-offset-3 first">	
													<input class="form-control" type="text" name="accNum" id="accNum2" placeholder="Account No." />  
												</div>
												
												<div class="col-md-3 col-md-offset-3 first">	
													<input class="form-control" type="text" name="bankName" id="bankName2" placeholder="Name Of Bank" />  
												</div>
											</div>
								</div>
         
        
         
         <div class="row form-group ">
            <div class="col-md-12 text-center">
              	
            <input type="button" id="btn3" name="btn3"  class="btn btn-large btn-success button-height-width btn-md button_hw button_margin_right"  onclick="EmployeeValidation(); return false;" value="Submit">
		    <input type="reset" id="btn2"  class="btn btn-large btn-danger   button-height-width btn-md button_hw button_margin_right" name="btn2" value="Cancel">
            </div>
          </div>       	    
 	 </form> 
 </div>
           
           
         <!------------------   Expenditure Payment ------------>
                
       <div class="tab-pane" id="settings">
		<form method="post" action="" name="exp">
			<div class="row">
			    <div align="center">
			  		<h2 class="form-name style_heading">Expenditure Payment</h2>
			  	</div>
			 	
		     <div class="row">
				     <div class="col-sm-offset-1 col-md-10">
						  		<hr style="border-top-color:#c1b1b1;">
				     </div>	
		   		 </div>
		    </div>
			 <div class="row form-group">
           		<label class="col-md-3 control-label" for="expenditureName">Expenditure Name<sup>*</sup></label>  
           	 	      		 <div class="col-md-3">
								<div class="input-group">
									<span class="input-group-addon">
										<i class="	glyphicon glyphicon-hand-right"></i>
									</span>
										
							
							<%
							ExpenditureDetailsDao exdd = new ExpenditureDetailsDao();
           						List exList =exdd.getAllExpenseName();
							
							%>
							
							<input list="exp_drop" id="expenseName" class="form-control">
				<datalist id="exp_drop">
							
							<%
					           for(int i=0;i<exList.size();i++){
					        	   ExpenditureDetailsBean expbean =(ExpenditureDetailsBean)exList.get(i);
							%>
		
							<option data-value="<%=expbean.getPkExpenseDetailsId()%>" value="<%=expbean.getExpenseName() %>">
							<%
				      			}
				    		%>
						</datalist>           	
					</div>
           		</div>
           	 	<label class="col-md-3 control-label" for="serviceProvider">Service Provider<sup>*</sup></label>  
           			 <div class="col-md-3">
						<div class="input-group">
							<span class="input-group-addon">
								<i class="glyphicon glyphicon-user"></i>
							</span>
             				<input id="serviceProvider" name="serviceProvider" placeholder="Service Provider" class="form-control input-md" type="text" >	
           				 </div>
					</div>
			</div>
			
			<div class="row form-group">
					
					<label class="col-md-3 control-label" for="contactNumber">Contact Number<sup>*</sup></label>  
           			 <div class="col-md-3">
						<div class="input-group">
							<span class="input-group-addon">
								<i class="glyphicon glyphicon-earphone"></i>
							</span>
             					 <input id="contactNumber" name="contactNumber" placeholder="Contact Number" class="form-control input-md" type="text" >
           				 </div>
					</div>
					
				<label class="col-md-3 control-label" for="expCredit">Credit Amount<sup>*</sup></label>  
           			 <div class="col-md-3">
						<div class="input-group">
							<span class="input-group-addon">
								Rs
							</span>
             					 <input id="expCredit" name="expCredit" placeholder="Credit Amount" class="form-control input-md" type="text" >
           				 </div>
					</div>
					
				
			</div>
				
			<div class="row form-group">
				
				 <label class="col-md-3 control-label" for="personName">Accountant Name<sup>*</sup> </label>  
	          		 <div class="col-md-3">
						<div class="input-group">
							<span class="input-group-addon">
								<i class="glyphicon glyphicon-user"></i>
							</span>
	           		 			<input id="accountantName" name="personName" placeholder="Accountant Name" class="form-control input-md" type="text">
	           			</div>
					</div>
			
				<label class="col-md-3 control-label" for="expDebit">Debit Amount<sup>*</sup></label>  
           			 <div class="col-md-3">
						<div class="input-group">
							<span class="input-group-addon">
								Rs
							</span>
             					 <input id="expDebit" name="expDebit" placeholder="Debit Amount" class="form-control input-md" type="text" >
           				 </div>
					</div>
			
			</div>	
				
		<div class="form-group row">
            <div class="col-md-12 text-center">
             	
            <input type="button" id="save" name="btn4"  class="btn btn-large btn-success button-height-width btn-md button_hw button_margin_right"  onclick="expensePaymentValidation(); return false;" value="Submit">
		    <input type="reset" id="btn2" s class="btn btn-large btn-danger   button-height-width btn-md button_hw button_margin_right" name="btn4" value="Cancel">
            </div>
        </div> 
			
     </form>
	</div>
           
   </div>
  </div> 
 </div>
</div>

        <div class="row footer_margin_top" align="center">
			<%@include file="y_commons/footer.jsp"%>
		</div>
		
</div>
</body>
</html>		