

<%@page import="java.util.List"%>
<%@page import="com.smt.bean.BillCopy"%>
<%@page import="com.smt.dao.CarEntryDao"%>
<% boolean isHome=false; %>
<%@include file="y_commons/header1.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Car Entry</title>
        
		<script src="/SMT/staticContent/js/billCopy.js"></script>
	
</head>
<body>



 <div class="container" style="float: "> 
 		
 		<div class="row">
			<div align="center" style="margin-top: 75px">
				  <h2 class="form-name style_heading">Customer Bill Copy</h2>
			</div>
				 	
		</div>
		
		 <div class="row">
		     <div class="col-sm-offset-1 col-md-10">
				  		<hr style="border-top-color:#c1b1b1;">
		     </div>	
    </div>
 	 		
 					<form action="" method="post" name="genIn">
 						<%
							CarEntryDao fd = new CarEntryDao();
							List list = fd.getBillNoAndNames();
						%>
						<div class="row" style="margin-top: 25px;">
							<div class="col-md-offset-4">
								<div class="col-md-3">
									<label class="control-label"> Bill Number:</label> 
								</div>	
								<div class="col-md-4">
									<input list="seedBillNo" id="BillNo" class="form-control">
									<datalist id="seedBillNo">
									<%
					               		for(int i=0;i<list.size();i++){
					               			BillCopy billList=(BillCopy)list.get(i);
									%>
									<option data-value="<%=billList.getBillNo()%>" value="<%=billList.getBillNo()%>          <%=billList.getCustName()%>" >
									<%
										}
									%>
									</datalist>
								 </div>
							</div>
							
								<div class="col-md-12 col-md-offset-" style="margin-top: 25px;" align="center">
									<button type="button" onclick="validateGenerateBillCOPY()" name="btn" class="btn btn-success btn-lg btn-md button_hw button_margin_right" >Print </button>
								</div>
							</div>
						
 					</form>
 				
 				
 	 		
</div>
		
			
	

 