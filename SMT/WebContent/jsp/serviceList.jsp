<%@page import="com.smt.dao.ServiceDao"%>
<%@page import="com.smt.bean.ServiceBean"%>
 <%@page import="java.util.List"%>

	<% boolean isHome=false;%>
	<%@include file="y_commons/header1.jsp"%>
	
		<script src="/SMT/staticContent/js/jquery-1.12.3.min.js"></script>
        
         <script src="/SMT/staticContent/shree/jquery.dataTables.min.js" type="text/javascript"></script>
         <script src="/SMT/staticContent/shree/dataTables.buttons.min.js" type="text/javascript"></script>
         <script src="/SMT/staticContent/shree/buttons.flash.min.js" type="text/javascript"></script>
         <script src="/SMT/staticContent/shree/jszip.min.js" type="text/javascript"></script>
         <script src="/SMT/staticContent/shree/pdfmake.min.js" type="text/javascript"></script>
         <script src="/SMT/staticContent/shree/vfs_fonts.js" type="text/javascript"></script>
         <script src="/SMT/staticContent/shree/buttons.html5.min.js" type="text/javascript"></script>
         <script src="/SMT/staticContent/shree/buttons.print.min.js" type="text/javascript"></script>
         <link href="/SMT/staticContent/y_css/jquery.dataTables.min.css" rel="stylesheet" type="text/css" media="all" />
         <link href="/SMT/staticContent/y_css/buttons.dataTables.min.css" rel="stylesheet" type="text/css" media="all">
        
		 <script src="/SMT/staticContent/y_js/productDetail.js"></script>
		
  		<script type="text/javascript">
  			function Back()
  			{
  				window.location = "service.jsp" ;
  			}
  		</script>
  
	<script type="text/javascript"> 
		$(document).ready(function () {
	         var table=$("#list").dataTable();
			 var tableTools = new $.fn.dataTable.TableTools(table, {
				 'sSwfPath':'//cdn.datatables.net/tabletools/2.2.4/swf/copy_csv_xls_pdf.swf',
				 	'aButtons':['copy','print','csv',{
					 'sExtends':'xls',
					 'sFileName':'Data.xls',
					 'sButtonText': 'Save to Excel'
						}
					]
				});
					$(tableTools.fnContainer()).insertBefore('#list_wrapper');
			});
	</script>
</head>
<body id="dt_example">
<div class="container">
<div class="row header_margin_top">
			    <div align="center">
			  		<h2 class="form-name style_heading">Product List</h2>
			  	</div>
			 
    </div>

	</div>
	<%
	ServiceDao dao=new ServiceDao();
	List list12=dao.getAllServiceList();
	%>
	 <div class="row">
		     <div class="col-sm-offset-1 col-md-10">
				  		<hr style="border-top-color:#c1b1b1;">
		     </div>	
    </div>
	<div id="date">
		<label id="demo"></label>
		<script>
			var date = new Date();
			document.getElementById("demo").innerHTML = date.toDateString();
		</script>
	</div>

	<div id="demo_jui">
		<table id="list" class="display" border="1">
			<thead>
				<tr>
					<th>Service Names</th>
					<th>HSN/SAC NO</th>
					
				</tr>
			</thead>
			
			<tbody>
   				<%
					for(int i=0;i<list12.size();i++){
						ServiceBean sr=(ServiceBean)list12.get(i);
				%>
				
				<tr>
		
					<td class="align"><%=sr.getItemName()%></td>
					<td class="align"><%=sr.getHsnsacno()%></td>
					
				</tr>
	
				<%
					}
				%>
			</tbody>
		</table>
	</div>
	
		<div class="ibutton">
	<div class="row" align="center">
		<input type="button"  value="Back"  class="btn btn-lg btn-primary btn-md button_hw button_margin_right" onclick="Back()" /> 
	</div>
	</div>
</body>

</html>
		