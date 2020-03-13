  <% boolean isHome=false; %>
<%@include file="y_commons/header1.jsp"%>

 <%@page import="java.util.List"%>
<%@page import="com.smt.hibernate.CashBook"%>
<%@page import="com.smt.helper.CashBookHelper"%>
<%@page import="com.smt.dao.CashBookDao"%>  

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cash Book</title>
    <script src="/SMT/staticContent/y_js/jquery-1.12.3.min.js"></script>  
	<link href="/SMT/staticContent/css/bootstrap.min.css" rel="stylesheet">
	<script src="/SMT/staticContent/y_js/jquery.min.js"></script>
	<script src="/SMT/staticContent/js/bootstrap.min.js"></script>
	<script src="/SMT/staticContent/y_js/jquery-1.12.3.min.js"></script>
	<link href="/SMT/staticContent/css/style1.css" rel="stylesheet">
	<link href="/SMT/staticContent/css/style3.css" rel="stylesheet">
	
	<script type="text/javascript" src="/SMT/staticContent/js/cash_book.js"></script>

<script>
	function defaultHideElement() {

		document.getElementById("neft_acc_no").style.display = "none";
		document.getElementById("cheque_no").style.display = "none";
		document.getElementById("card_no").style.display = "none";
	}

	$(document).ready(function() {
		$("#paymebtById").change(function() {
			$(this).find("option:selected").each(function() {
				if ($(this).attr("value") == "cheque") {

					$("#cheque_no").show();

					$("#neft_acc_no").hide();
					$("#card_no").hide();
				} else if ($(this).attr("value") == "card") {

					$("#card_no").show();

					$("#neft_acc_no").hide();
					$("#cheque_no").hide();
				} else if ($(this).attr("value") == "neft") {

					$("#neft_acc_no").show();

					$("#card_no").hide();
					$("#cheque_no").hide();
				} else if ($(this).attr("value") == "cash") {

					$("#neft_acc_no").hide();
					$("#cheque_no").hide();
					$("#card_no").hide();
				}

			});
		}).change();
	});
</script>
</head>
<body onload="defaultHideElement()">

	<div class="container"> 
		<h2 class="form-name" style="margin-top:20px;">Cash Book</h2>
	</div>

	<form action="cashbook" method="post" name="cashbook">
		<div class="col-lg-12">

			<div class="form-group-2">
				<div class="col-lg-12">
					<div class="col-lg-2 col-md-1 col-sm-1 col-xs-6">
						<label>Payment To:</label>
					</div>
					<div class="col-lg-2 col-md-1 col-sm-1 col-xs-6">
						<select id="paymenttonameid" onchange="cheak()" class="form-control">
							<option id="0" value="" selected="selected">Select To Pay</option>
							<option id="0" value="supplier">Supplier</option>
							<option id="1" value="employee">Employee</option>
							<!-- <option id="2" value="employee">Other</option> -->
						</select>					
					</div>
				</div>
			</div>
			
			<div class="form-group-2">
				<div class="col-lg-12">
					<div class="col-lg-2 col-md-1 col-sm-1 col-xs-6">
						<label>Name:</label>
					</div>
					
						<%-- <%
						employeeDetailHelper helper = new employeeDetailHelper();
						List empNameList = helper.getAllEmployeeNames();
						%>
					
					<div class="col-lg-2 col-md-1 col-sm-1 col-xs-6">
						<input list="empId_drop" id="empId" class="form-control">
					    <datalist id="empId_drop">
						<%
							for(int i=0;i<empNameList.size();i++){
							EmployeeDetail emp = (EmployeeDetail)empNameList.get(i);
						%>
							<option data-value="<%=emp.getPkEmpId()%>" value="<%=emp.getEmpName()%>">
						<%
							}
						%>
						</datalist> --%>
						
					<div class="col-lg-2 col-md-1 col-sm-1 col-xs-6">
						<select class="form-control" id="itemNames" name="itemNames"  >
						</select>
					</div>
						
					</div>
				</div>
				
				
				<!-- <div class="form-group-2">
				<div class="col-lg-12">
					<div class="col-lg-2 col-md-1 col-sm-1 col-xs-6">
						<label>Date:</label>
					</div>
					<div class="col-lg-2 col-md-1 col-sm-1 col-xs-6">
						<input class="form-control" type="date" name="" id="paymentDate" placeholder="Payment Date" />
					</div>
				</div>
			</div> -->
				
			

			<div class="form-group-2">
				<div class="col-lg-12">
					<div class="col-lg-2 col-md-1 col-sm-1 col-xs-6">
						<label>Payment Type:</label>
					</div>
					<div class="col-lg-2 col-md-1 col-sm-1 col-xs-6">
						<select class="form-control" name="selectType" id="paymentTypeId">
							<option value="">--Select Type--</option>
							<option value="credit">Credit</option>
							<option value="debit">Debit</option>
						</select>
					</div>
				</div>
			</div>
			
			
			
	<div class="col-lg-12" id="drop_cust_name">
		<div class="form-group-2">
			<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
				<label>Payment By:</label>
			</div>	
			<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6 first">	
				<select class="form-control" id="paymebtById">
					<option value="">-Select Type--</option>
					<option value="cash">Cash</option>
					<option value="cheque">Cheque</option>
					<option value="card">Card</option>
					<option value="neft">NEFT</option>
				</select>	  
			</div>
			
			<div id="cheque_no">
				<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
					<label>Cheque No:</label>
				</div>
				<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6 first">	
					<input class="form-control" type="text" name="" id="chequeNoId" placeholder="Cheque No." />  
				</div>
				<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
					<label>Date:</label>
				</div>
				<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6 first">	
					<input class="form-control" type="date" name="" id="chequeDateId" placeholder="Cheque date" />  
				</div>
			</div>
			
			<div id="card_no">
				<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
					<label>Card No.:</label>
				</div>
				<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6 first">	
					<input class="form-control" type="text" name="" id="cardNoId" placeholder="Card No." />  
				</div>
				
			</div>
			
			<div id="neft_acc_no">
				<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
					<label>NEFT Acc No.:</label>
				</div>
				<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6 first">	
					<input class="form-control" type="text" name="" id="neftAccNoId" placeholder="Account No." />  
				</div>
				
			</div>
			
		</div>
	</div>

			<div class="form-group-2">
				<div class="col-lg-12">
					<br><div class="col-lg-2 col-md-1 col-sm-1 col-xs-6">
						<label>Amount:</label>
					</div>
					<div class="col-lg-2 col-md-1 col-sm-1 col-xs-6">
						<input type="text" class="form-control" name="amount" id="paymentAmountId"
							placeholder="Paying Amount">
					</div>
				</div>
			</div>

			<div class="form-group-2">
				<div class="col-lg-12">
					<br><div class="col-lg-2 col-md-1 col-sm-1 col-xs-6">
						<label>Reason:</label>
					</div>
					<div class="col-lg-2 col-md-1 col-sm-1 col-xs-6">
						<input type="text" class="form-control" name="reason" id="paymentReasonId"
							placeholder="Payment Reason">
					</div>
				</div>
			</div>

			<div class="wrapper">
				<input type="button" id="buttonCSS" value="Save" name="saveButton" class="btn btn-success" onclick="validateCashBook()" />
				 <input type="reset" id="buttonCSS" value="Reset" name="reset" class="btn btn-danger" /><br>
				 
			</div>

		</div>
	</form>
</body>
</html>