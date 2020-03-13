<!DOCTYPE html>
<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@page import="com.smt.hibernate.UserDetail"%>
<%@page import="org.hibernate.Session"%>
<%@page import="com.smt.utility.HibernateUtility"%>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Dash Board</title>
    <!-- Bootstrap Core CSS -->
    <link href="/SMT/staticContent/css/bootstrap.min.css" rel="stylesheet">
    <link href="/SMT/staticContent/css/dashboard.css" rel="stylesheet">
    
    <!-- Custom CSS -->
    <link href="/SMT/staticContent/css/sb-admin-2.css" rel="stylesheet">
    
    <!-- Morris Charts CSS -->
    <link href="/SMT/staticContent/css/morris.css" rel="stylesheet">
    
     <!-- MetisMenu CSS -->
    <link href="/SMT/staticContent/css/metisMenu.min.css" rel="stylesheet">

    <!-- Timeline CSS -->
    <link href="/SMT/staticContent/css/timeline.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="/SMT/staticContent/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    
	<!-- jQuery -->
    <script src="/SMT/staticContent/js/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="/SMT/staticContent/js/bootstrap.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="/SMT/staticContent/js/sb-admin-2.js"></script>
    
    <!-- Metis Menu Plugin JavaScript -->
    <script src="/SMT/staticContent/js/metisMenu.min.js"></script>
    
    <!-- Morris Charts JavaScript -->
    <script src="/SMT/staticContent/js/raphael-min.js"></script>
    <script src="/SMT/staticContent/js/morris.min.js"></script>
    <script src="/SMT/staticContent/js/morris-data.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="/SMT/staticContent/js/sb-admin-2.js"></script>
    <script src="/SMT/staticContent/js/logout.js"></script>
    
    <script src="/SMT/staticContent/js/graph.js"></script>
<script src="/SMT/staticContent/js/jquery-1.12.3.min.js"></script>
<script src="/SMT/staticContent/js/highcharts.js"></script>
<script src="/SMT/staticContent/js/exporting.js"></script>
  
</head>

	<div class="row">
			<div class="form-group" align="right" >
				<div class="col-sm-offset-6 col-md-5 control-label">
					<div id="date" style="padding-right: 10px;">
						<h4 id="demo"></h4>
						<script>
							var date = new Date();
							document.getElementById("demo").innerHTML = date
									.toDateString();
						</script>
					</div>
				</div>
			</div>
		</div>

<body>	
    <div id="wrapper">
   <% String type1= "";
                     String name1 = "";
		             if (session != null) {
			
			         if (session.getAttribute("user") != null) {
				     name1 = (String) session.getAttribute("user");
				    
			            
	            	  
		              HibernateUtility hbu1=HibernateUtility.getInstance();
		              Session session2=hbu1.getHibernateSession();
		   
		              org.hibernate.Query query1 = session2.createQuery("from UserDetail where userName =:usr");
		              query1.setParameter("usr", name1);
		              UserDetail userDetail1 = (UserDetail) query1.uniqueResult();
		              type1 = userDetail1.getTypeId();
		              System.out.println("usertype in index.jsp - "+type1);
			         } 
			         else {
							
					     response.sendRedirect("/SMT/jsp/login.jsp");
					     System.out.println("user is null in index.jsp - ");
					     out.println("Please Login ");
				        }
			         
		           }
		             else {
							
					     response.sendRedirect("/SMT/jsp/login.jsp");
					     System.out.println("session is null in index.jsp - ");
					     out.println("Please Login ");
				        }
	           %>
	
	
	
	
	
        <!-- Navigation -->
        <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
        	<marquee direction="right" onmouseover="this.stop();" onmouseout="this.start();" ><font color="crimson" style="font-size: 25px; font-style: italic; font-weight: bold;"> SAGAR CAR DECOR </font> </marquee>
			<!-- <div class="wrapper">
            	<a href="http://localhost:8080/SMT/jsp/OrderBill.jsp" class="btn btn-lg btn-md btn-success"> <span class="glyphicon glyphicon-home"></span> HOME </a>
            	<button type="button" class="btn  btn-md btn-danger" onclick="Logout()" style="margin-left: 1180px;"> <span class="glyphicon glyphicon-off"></span> Logout </button>
			</div> -->
            <!-- /.navbar-header -->

            <div class="navbar-default sidebar container" role="navigation">
                <div class="sidebar-nav navbar-collapse">
				
					
					<ul class="nav nav-pills nav-stacked">
						<li class="dropdown">
					    	<a class="dropdown-toggle" data-toggle="dropdown" href="#"> Report's <span class="caret"></span></a>
					    	<ul class="dropdown-menu">
					    	<%
    
                  if(type1.equals("admin") ){
    	  
                %>	
								
								<li><a href="DayClosingReport.jsp" ">Day Wise Sale Report</a></li>
								<li><a href="purchaseReports.jsp">Gst Wise Purchase Report</a></li>
								<li><a href="gstSaleReport.jsp">Gst Wise Sale Report</a></li>
								<li><a href="cashBookReports.jsp">Cash Book Report</a></li>
								<li><a href="UnPaidAmountReport.jsp">UnPaid Amount Report</a></li>
								<li><a href="allReports.jsp">ALL Reports</a></li>
								<li><a href="notification.jsp">Notification</a></li>
								<li><a href="TallySaleReport.jsp">Tally Sale Reports</a></li>
								<li><a href="s_category_list.jsp">Category List</a></li>
								<li><a href="s_item_list.jsp">Product List</a></li>
								<li><a href="s_supplier_list.jsp">Supplier List</a></li>
								<li><a href="employeeList.jsp">Employee List</a></li>
							<%
				}
				%>		
							</ul>
					    </li>
					  	

					
					</ul>
               </div>
                <!-- /.sidebar-collapse -->
            </div>
            <!-- /.navbar-static-side -->
        </nav>

        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Dashboard</h1>
                    
                    <div class="form-group-2">	
						<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
							<input type="text" id="shop1" name="shop1" class="form-control" placeholder="Shop 1 Sale">
						</div>
						<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
					    	<input type="button" class="btn list-group list-group-item list-group-item-success"  value="Shop 1 Sale" onclick="stock.shopNetTotalFor1();" >
					    </div>
					</div>
					
					<div class="form-group-2">		
						<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
					         <input type="text"  id='shop2' name="shop2" class="form-control" placeholder="Shop 2 Sale">
						</div>
						<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
					    	<input type="button" class="btn list-group list-group-item list-group-item-danger" value="Shop 2 Sale" onclick="stock.shopNetTotalFor2();" >
						</div>
					</div>
					
					<div class="form-group-2">	
						<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
					         <input type="text"  id='shop3' name="shop3" class="form-control" placeholder="Shop 3 Sale">
						</div>
						<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
					    	<input type="button" class="btn list-group list-group-item list-group-item-info" value="Shop 3 Sale"  onclick="stock.shopNetTotalFor3();" >
						</div>
					</div>	
                </div>
                <!-- /.col-lg-12 -->
            </div>
    
            <div class="row">
                <div class="col-lg-8">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                           <h2 style="color:crimson ">Graph</h2>  
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="flot-chart">
                                <div class="flot-chart">
                                	<input	class="btn  list-group-item-success" type="button" value="D S" onclick="stock.day();" >
	                              	<input	class="btn  list-group-item-danger" type="button" value="W S" onclick="stock.graph();" >
	                              	<input	class="btn  list-group-item-info" type="button" value="M S" onclick="stock.month();" >
	                              	<input	class="btn  list-group-item-success" type="button" value="S D S" onclick="stock.shopday();" >
	                              	<input	class="btn  list-group-item-danger" type="button" value="S W S" onclick="stock.shopweek();" >
	                              	<input	class="btn  list-group-item-info" type="button" value="S M S" onclick="stock.shopmonth();" >
	                              	<input	class="btn  list-group-item-success" type="button" value="S Y S" onclick="stock.shopyear();" >
									<div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
                            	</div>
                             </div>
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                
                <!-- /.col-lg-8 -->
                <div class="col-lg-4">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <i class="fa fa-bell fa-fw"></i> Notifications Panel
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                        <%
    
                  if(type1.equals("admin") ){
    	  
                %>	
                           
                            <a href="http://http://localhost:8080/SMT/jsp/sale_return.jsp" class="btn list-group list-group-item list-group-item-info"> <h4> Sale Return </h4></a>
                       		<a href="http://http://localhost:8080/SMT/jsp/dead_stock.jsp" class="btn list-group list-group-item list-group-item-success"><h4> Dead Stock </h4></a>
                       <%
				}
				%>		
                        </div>
                        
                        <div class="dropdown">
								<button class="dropbtn">Day Close Report</button>
z							<div class="dropdown-content">
							<%
    
                  if(type1.equals("admin") ){
    	  
                %>	
						    	<a href="http://http://localhost:8080/SMT/jsp/day_closing_payments.jsp" >Day Closing Payments</a>
						    	<a href="http://http://localhost:8080/SMT/jsp/AllPayments.jsp" >Day Closing AllPayments</a>
					    	<a href="http://http://localhost:8080/SMT/jsp/getAllDayCloseEmployeePayments.jsp" >Day Closing Employee Payments</a>
						    	
								<a href="http://http://localhost:8080/SMT/jsp/date_wise_commision_for_employee.jsp">Date Wise Commission For Employee</a>
								<a href="http://http://localhost:8080/SMT/jsp/opening_stock.jsp">Opening Stock</a>
								<a href="http://http://localhost:8080/SMT/jsp/closing_stock.jsp">Closing Stock</a>	
						<%
				}
				%>		
							</div>
						</div>
                        
                        <div class="dropdown">
								<button class="dropbtn">Employee</button>
							<div class="dropdown-content"> 
							<%
    
                  if(type1.equals("admin") ){
    	  
                %>	
								 <a href="http://http://localhost:8080/SMT/jsp/day_wise_salesman_commision.jsp">Day Wise Employee Commision</a>
								 <a href="http://http://localhost:8080/SMT/jsp/employeewise_commision.jsp">Employee Wise Commision</a>
								 <a href="http://http://localhost:8080/SMT/jsp/date_wise_commision_for_employee.jsp">Date Wise Commission For Employee</a>
						<%
				}
				%>		
							</div>
						</div>
						
						<div class="dropdown">
								<button class="dropbtn">Supplier</button>
							<div class="dropdown-content"> 		
							<%
    
                  if(type1.equals("admin") ){
    	  
                %>	
								<a href="http://http://localhost:8080/SMT/jsp/supplierwise_sale_report.jsp">Supplier Sale Report</a>
								<a href="http://http://localhost:8080/SMT/jsp/supplierwise_purchase_report.jsp">Supplier Purchase Report</a>											
						<%
				}
				%>		
							</div>
						</div>
                        <!-- /.panel-body -->
                    </div>
                  </div>
            </div>
        </div>
    </div>

</body>



<div style="    margin-top: 180px;">
	<%@include file="commons/shortcut.jsp"%>
</div>
</html>



