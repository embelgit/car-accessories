
   <% boolean isHome=false; %>
<%@include file="y_commons/header1.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Car Entry</title>
	
      <link href="/SMT/staticContent/css/bootstrap.min.css" rel="stylesheet">
      <script src="/SMT/staticContent/y_js/jquery.min.js"></script>
      <script src="/SMT/staticContent/js/bootstrap.min.js"></script>
      <script src="/SMT/staticContent/y_js/jquery-1.12.3.min.js"></script>
      
      <script src="/SMT/staticContent/y_js/vat_entry.js"></script>
	
</head>
<body>

	<div class="container-fluid">
		<h2 align="center" class="form-heading" style="margin-top: 100px; margin-bottom: 45px;">Vat Entry</h2>
			<form class="form-horizontal" action="" method="post">
			
			
				<div class="row margin-top-50">
					<div class="col-md-offset-2 col-sm-2">
						<label class="control-label">Tax Name :<sup style="color: red;">*</sup></label>
					</div>
					<div class="col-sm-2">
						<input type="text" class="form-control" id="vatName" name="vatName" placeholder="Tax Name" autofocus />
					</div>
					
					<div class="col-sm-2">
						<label class="control-label">Tax Percentage :<sup style="color: red;">*</sup></label>
					</div>
					<div class="col-sm-2">
						<input type="text" class="form-control" id="vatPer" name="vatPer" placeholder="Tax Percentage" />
					</div>
					
				</div>
				
			   <div align="center" class="margin-top-50" style="margin-top: 25px;">
			   		<button class="btn btn-success unicButton" style="text-align: center;" name="createbtn" id="createbtn" onclick="vatValidation()">Submit</button>
					<button type="reset"  class="btn btn-danger unicButton" style="text-align: center;">Reset</button>
				</div>
			
			</form>			
	</div>
	
	<div class="row footer_margin_top" align="center">
			<%@include file="y_commons/footer.jsp"%>
     </div> 

</body>
</html>

