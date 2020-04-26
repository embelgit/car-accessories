<%@page import="java.util.List"%>
<%@page import="com.smt.hibernate.CustomerDetailsBean"%>
<%@page import="com.smt.dao.CustomerDetailsDao"%>
<%boolean isHome = false;
%>
<%@include file="y_commons/header1.jsp"%>
 
<head>
<meta charset="utf-8">
<script type="text/javascript" src="/SMT/staticContent/js/customerDetails.js"></script>
<script type="text/javascript">
          		 function Customerlist()
          		 {
          		 window.location = "creditCustomerList.jsp";
          		 }
          		 function editCustomer() {
          			 window.location = "editCreditCustomerDetails.jsp";
				}
          		</script>
</head>
<div class="row header_margin_top">
			    <div align="center">
			  		<h2 class="form-name style_heading"> Credit Customer Details </h2>
			  	</div>
			 
    </div>
     <div class="row">
		     <div class="col-sm-offset-1 col-md-10">
				  		<hr style="border-top-color:#c1b1b1;">
		     </div>	
    </div>
	<div class="container col-sm-offset-1" >
        <form class="form-horizontal" method="post" action="" name="cstd"><!-- Value of 'name' attribute is used in customerDetails.js  -->
          <fieldset>
			<div class="row form-group">
           	 		<div class="col-md-6">
              			
           		 	</div>
			</div>
			 <div class="row form-group">
           		<label class="col-md-2 control-label" for="firstName">First Name:<sup>*</sup></label>  
           	 		<div class="col-md-3">
            			<div class="input-group">
							<span class="input-group-addon">
								<i class="glyphicon glyphicon-user"></i>
							</span>
						<input id="firstName" name="firstName " placeholder="First Name" class="form-control input-md" type="text" >
<%-- 							<%
							CustomerDetailsDao dao1 = new CustomerDetailsDao();
           						List unitList =dao1.getAllCustomer();
							%>
							<input list="firstName_drop" id="firstName"  class="form-control">
				<datalist id="firstName_drop">
							<%
					           for(int i=0;i<unitList.size();i++){
					        	   CustomerDetailsBean bean1 =(CustomerDetailsBean)unitList.get(i);
							%>
		
							<option data-value="<%=bean1.getCustId()%>" value="<%=bean1.getFirstName()%>">
							<%
				      			}
				    		%>
			              	
            			</datalist> --%>
            			</div>
           		 	</div>

           
           	 	<label class="col-md-2 control-label" for="middleName">Middle Name: <sup>*</sup></label>  
           			 <div class="col-md-3">
           				 <div class="input-group">
							<span class="input-group-addon">
								<i class="glyphicon glyphicon-user"></i></span>
					<input id="middleName" name="middleName " placeholder="Middle Name" class="form-control input-md" type="text" >
<%-- 							<%
							CustomerDetailsDao dao2 = new CustomerDetailsDao();
           						List middleList =dao2.getAllCustomer();
							%>
							<input list="middleName_drop" id="middleName"  class="form-control">
				<datalist id="middleName_drop">
							<%
					           for(int i=0;i<middleList.size();i++){
					        	   CustomerDetailsBean bean2 =(CustomerDetailsBean)middleList.get(i);
							%>
		
							<option data-value="<%=bean2.getCustId()%>" value="<%=bean2.getMiddleName()%>">
							<%
				      			}
				    		%>
			              	
            			</datalist> --%>
            			</div>
           				 
					</div>
			</div>
			
			<div class="row form-group">
				<label class="col-md-2 control-label" for="lastName">Last Name:<sup>*</sup></label>  
           	 		<div class="col-md-3">
           				  <div class="input-group">
							<span class="input-group-addon">
								<i class="glyphicon glyphicon-user"></i></span>
					<input id="lastName" name="lastName " placeholder="Last Name" class="form-control input-md" type="text" >					
<%-- 							<%
							CustomerDetailsDao dao3 = new CustomerDetailsDao();
           						List lastList =dao3.getAllCustomer();
							%>
							<input list="lastName_drop" id="lastName"  class="form-control">
				<datalist id="lastName_drop">
							<%
					           for(int i=0;i<lastList.size();i++){
					        	   CustomerDetailsBean bean3 =(CustomerDetailsBean)lastList.get(i);
							%>
		
							<option data-value="<%=bean3.getCustId()%>" value="<%=bean3.getLastName()%>">
							<%
				      			}
				    		%>
			              	
            			</datalist> --%>
            			</div>
           			 </div>
           			 
				<label class="col-md-2 control-label" for="address">Address:<sup>*</sup></label>  
	            	<div class="col-md-3 ">
						<div class="input-group">
							<span class="input-group-addon">
								<i class="glyphicon glyphicon-map-marker"></i>
							</span>
	              				<input id="address" name="address " placeholder="Address" class="form-control input-md" type="text" >
	            		</div>
					</div>
				</div>
			
			<div class="row form-group">
				<label class="col-md-2 control-label" for="contactNo">Contact No.:<sup>*</sup></label>  
           	 		<div class="col-md-3 ">
						<div class="input-group">
							<span class="input-group-addon">
								<i class="glyphicon glyphicon-earphone"></i>
							</span>
              					<input id="contactNo" name="contactNo" maxlength="10" placeholder="10 Digit Contact No." class="form-control input-md" type="text" >
           				 </div>
           			</div>
            	
            	<label class="col-md-2 control-label" for="aadharNo">GSTTIN/UIN No.:<!-- <sup>*</sup> --></label>  
           	 		<div class="col-md-3">
						<div class="input-group">
							<span class="input-group-addon">
								No.
							</span>
              				<input id="aadharNo" name="aadharNo" placeholder="GSTTIN/UIN No:" maxlength="14" class="form-control input-md" type="text" >
            			</div>
            		</div>
            	
			</div>

			<div class="row form-group">
				 <label class="col-md-2 control-label" for="emailId">Email ID :</label>  
            	<div class="col-md-3 ">
					<div class="input-group">
						<span class="input-group-addon">
							<i class="glyphicon glyphicon-envelope"></i>
						</span>
             				 <input id="emailId" name="emailId " placeholder="Email ID" class="form-control input-md" type="text">
            		</div>
				</div>
				
				<label class="col-md-2 control-label" for="zipCode">Zip Code:<sup>*</sup></label>  
           		 <div class="col-md-3">
					<div class="input-group">
						<span class="input-group-addon">
							<i class="	glyphicon glyphicon-envelope"></i>
						</span>
              				<input id="zipCode" name="zipCode" maxlength="6" placeholder="6 Digit Zip code" class="form-control input-md" type="text" >
            		</div>
            	</div>
				
				
			</div>
 		
 			
 		
 		<div class="form-group row">
            <div class="col-md-11 text-center">
            <!--  "customerDetails()" function is implemented in customerDetails.js  -->
				<%-- <button id="save" name="btn" class="btn btn-large btn-success glyphicon glyphicon-save  button-height-width"  onclick="customerDetails()"><h4><%if(abc.equals("marathi")){%><%=PropertiesHelper.marathiProperties.getProperty("submit") %> <%}%> <%if(abc.equals("english")){%>Submit<%}%> </h4></button>
              	<button class="btn btn-large btn-danger  button-height-width" type="reset"  onclick="reset()"><h4><%if(abc.equals("marathi")){%><%=PropertiesHelper.marathiProperties.getProperty("cancel") %> <%}%> <%if(abc.equals("english")){%>Cancel<%}%> </h4> </button> --%>
              	
              	
              	<input type="button" id="save" name="btn"  class="btn btn-lg btn-success button-height-width button_hw button_margin_right" onclick="customerDetails()" value="Submit">
              	<input type="button" id="save" name="btn"  class="btn btn-lg btn-danger button_hw button_margin_right  button-height-width" type="reset"  onclick="reset()" value="Cancel">
              	 
              	<input  type="button" value="Edit" id="listBtn" class="btn btn-lg btn-primary button_hw button_margin_right" onclick="editCustomer()" /> 
              	<input  type="button" value="List" id="listBtn" class="btn btn-lg btn-primary button_hw button_margin_right" onclick="Customerlist()" />
             
            
            </div>
         </div>
		</fieldset>
       </form>
     </div>
     
     
     
<%-- <%@include file="commons/footer.jsp" %> --%>
