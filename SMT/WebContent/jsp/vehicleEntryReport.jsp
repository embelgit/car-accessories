
 <%@page import="com.smt.hibernate.CarEntry"%>
<%@page import="com.smt.dao.CarEntryDao"%>
<%@page import="com.smt.bean.CarEntryBean"%>
<%@page import="java.util.List"%>
  
  <% boolean isHome=false;%>
	<%@include file="y_commons/header1.jsp"%>

<html>
	
	
	<head>
	<title>Vehicle Entry Reports</title>
		
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
	

	<script type="text/javascript"> 
		$(document).ready(function () {
			var table=$("#list").dataTable({
				
				 fnRowCallback : function(nRow, aData, iDisplayIndex){
		                $("th:first", nRow).html(iDisplayIndex +1);
		               return nRow;
		            },
				
				 "footerCallback": function ( row, data, start, end, display ) {
			            var api = this.api(), data;
			 
			            // Remove the formatting to get integer data for summation
			            var intVal = function ( i ) {
			                return typeof i === 'string' ?
			                    i.replace(/[\$,]/g, '')*1 :
			                    typeof i === 'number' ?
			                        i : 0;
			            };
			 
			            // Total over all pages
			   		         total = api
			                .column( 6 )
			                .data()
			                .reduce( function (a, b) {
			                    return intVal(a) + intVal(b);
			                }, 0 ); 
			 			console.log(total); 
			 			
			 		
			            // Update footer
			            $( api.column( 6 ).footer() ).html(
			                'Rs'+' '+total
			            );
			            console.log( total);
			            
			         // Total over all pages
			            total = api
			                .column( 7 )
			                .data()
			                .reduce( function (a, b) {
			                    return intVal(a) + intVal(b);
			                }, 0 ); 
			 			console.log(total); 
			 			
			 		
			            // Update footer
			            $( api.column( 7 ).footer() ).html(
			                'Rs'+' '+total
			            );
			            console.log( total);
			            
			         // Total over all pages
			            total = api
			                .column( 8 )
			                .data()
			                .reduce( function (a, b) {
			                    return intVal(a) + intVal(b);
			                }, 0 ); 
			 			console.log(total); 
			 			
			 		
			            // Update footer
			            $( api.column( 8 ).footer() ).html(
			                'Rs'+' '+total
			            );
			            console.log( total);
			            
			             total = api
		                .column( 9 )
		                .data()
		                .reduce( function (a, b) {
		                    return intVal(a) + intVal(b);
		                }, 0 ); 
		 			console.log(total); 
		 			
		 		
		            // Update footer
		            $( api.column( 9 ).footer() ).html(
		                'Rs'+' '+total
		            );
		            console.log( total);
		            
		            total = api
	                .column( 10 )
	                .data()
	                .reduce( function (a, b) {
	                    return intVal(a) + intVal(b);
	                }, 0 ); 
	 			console.log(total);  
	 			
	 		
	            // Update footer
	            $( api.column( 10 ).footer() ).html(
	                'Rs'+' '+total
	            );
	            console.log( total); 
	            
	         
			    }
			
				
			});
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
<div class="container-fluid">
		<h2 align="center" class="form-heading" style="margin-top: 100px; margin-bottom: 45px;">Total Vehicle Entry Report</h2>


	<%
	CarEntryDao dao=new CarEntryDao();
	List list12=dao.getcarDetailReports();
	%>
	
	<div id="date">
		<label id="demo"></label>
		<script>
			var date = new Date();
			document.getElementById("demo").innerHTML = date.toDateString();
		</script>
	</div>
	</div>
		 <div class="row">
		     <div class="col-sm-offset-1 col-md-10">
				  		<hr style="border-top-color:#c1b1b1;">
		     </div>	
    </div>
	<div id="demo_jui">
		<table id="list" class="display" border="1">
			<thead>
				<tr>
					<th>vehicle Number</th>
					<th>owner Name</th>
	                <th>Contact Number</th>
	                <th>Active</th>
					<th>Date</th>
					<th>Kilometer Reading</th>
					<th>Vehicle Color</th>
					
					
					
				</tr>
			</thead>
			<tfoot>
            <tr>
               <!--  <th></th>
                <th></th>
                <th></th>
                <th></th>
                <th></th>
                <th></th>
                <th></th>	 -->
              
               
            </tr>
        </tfoot>
			
			<tbody>
   				<%
					for(int i=0;i<list12.size();i++){
						CarEntryBean sr=(CarEntryBean)list12.get(i);
				%>
				
				<tr>
					<td class="align"><%=sr.getCarNo()%></td>
					<td class="align"><%=sr.getOwnerName()%></td>	
					<td class="align"><%=sr.getContactNo()%></td>
					<td class="align"><%=sr.getActive()%></td>	
					<td class="align"><%=sr.getDate()%></td>
					<td class="align"><%=sr.getKmReader1()%></td>	
					<td class="align"><%=sr.getVehiclecolor()%></td>	
						
					
					
				</tr>
	
				<%
					}
				%>
			</tbody>
		</table>
	</div>
</body>

</html>

	<div class="row footer_margin_top" align="center">
			<%@include file="y_commons/footer.jsp"%>
     </div> 
