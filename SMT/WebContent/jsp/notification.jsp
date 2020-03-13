
<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@page import="java.util.List"%>
<%@page import="com.smt.hibernate.UserDetail"%>
<%@page import="org.hibernate.Session"%>
<%@page import="com.smt.utility.HibernateUtility"%>
<%@page import="com.smt.dao.StockDao"%>
<%@page import="com.smt.hibernate.Stock"%>

<%
   response.setHeader( "Pragma", "no-cache" );
   response.setHeader( "Cache-Control", "no-cache" );
   response.setDateHeader( "Expires", 0 );
%>
  <% String type1= "";
                     String name1 = "";
		             if (session != null) {
			
			         if (session.getAttribute("user") != null) {
				     name1 = (String) session.getAttribute("user");
				    
			            System.out.println("user got from seession in notitfication.jsp - "+name1);
	            	  
		              HibernateUtility hbu1=HibernateUtility.getInstance();
		              Session session2=hbu1.getHibernateSession();
		   
		              org.hibernate.Query query1 = session2.createQuery("from UserDetail where userName =:usr");
		              query1.setParameter("usr", name1);
		              UserDetail userDetail1 = (UserDetail) query1.uniqueResult();
		              type1 = userDetail1.getTypeId();
		              System.out.println("user type - "+type1);
			         } 
			         else {
							
					     response.sendRedirect("/SMT/jsp/login.jsp");
					     out.println("Please Login ");
				     	System.out.println("if user is null "); 
			         }
			         
		           }
		             else {
							
					     response.sendRedirect("/SMT/jsp/login.jsp");
					     System.out.println("if session is null "); 
					     out.println("Please Login ");
				        }
	           %> 

<html>
<head>
<title>Embel Technologies Pvt Ltd</title>
<link href="/SMT/staticContent/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="/SMT/staticContent/js/jquery.min.js"> </script>
<script type="text/javascript" src="/SMT/staticContent/js/bootstrap.min.js"> </script>
<link rel="stylesheet" href="/SMT/staticContent/css/notofication.css">
</head>

  
  <script type="text/javascript">
/*   var blink_speed = 850; var t = setInterval(function () { var ele = document.getElementById('blinker'); 
  ele.style.visibility = (ele.style.visibility == 'hidden' ? '' : 'hidden');
  }, blink_speed);
 
  var blink_speed = 850; var t = setInterval(function () { var ele = document.getElementById('blinker1'); 
  ele.style.visibility = (ele.style.visibility == 'hidden' ? '' : 'hidden');
  }, blink_speed); */
  </script> 
</head>
<body background="/SMT/staticContent/images/29car_parts_graphic.png"> 

<!-- <img src="/SMT/staticContent/images/29car_parts_graphic.png" alt="Smiley face" align="middle"> -->
   <div class="row header_margin_top">
			    <div align="center">
			  		<h2 style="color:red;" class="form-name style_heading">Welcome</h2>
			  	</div>
    
    <div class="col-md-offset-9" id="report">
		<label style="color:#bdd633; font-size: 20" id="demo"></label>
		<script>
		   var date = new Date();
		   document.getElementById("demo").innerHTML = date.toDateString();;
		</script>
	</div>
	</div>
     <div class="row">
		     <div class="col-sm-offset-1 col-md-10">
				  		<hr style="border-top-color:#green;">
		     </div>	
    </div>
  <div class="container col-sm-offset-2">
 	 <div class="row form-group col-md-offset-2 ">
 		<button style="height: 150px;width: 285px" id = "blinker" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#expiryProducts">Low Stock Item Name<br>click to view</button>
 		<button style="height: 150px;width: 285px" class="btn btn-primary btn-lg" onclick="goToHome1()">Credit Customer Billing</button>
	
	<!-- Modal -->
			<div class="modal fade" id="expiryProducts" tabindex="-1" role="dialog" 
			   aria-labelledby="myModalLabel" aria-hidden="true">
			   <div class="modal-dialog">
			      <div class="modal-content">
			         <!-- Modal Header -->
			         <div class="modal-header" style="background-color:white;">
			            <button type="button" class="close" 
			               data-dismiss="modal">
			            <span aria-hidden="true">×</span>
			            <span class="sr-only">Close</span>
			            </button>
			            <h4 class="modal-title" id="myModalLabel">
			              Products Whose Quantity Less Than 10 
			            </h4>
			         </div>
			         <!-- Modal Body -->
			         <div class="modal-body">
			           
							<%-- <%
						                StockDao pn = new StockDao();
					           			List<Stock> np =pn.getAllProductForNotification();
												
								%>
										
										<table>
											<tr>
											<th>Category Name</th>
											<th>Item Name</th>
											<th>Quantity</th>
											<th>Update Date</th>
											</tr>
										<%
								           for(int i=0;i<np.size();i++){
								        	   Stock gfn =(Stock)np.get(i);
										%>
										<tr>
										<td><%=gfn.getCatName()%></td>
										<td><%=gfn.getItemName() %></td>
										<td><%=gfn.getQuantity() %></td>
										<td><%=gfn.getUpdateDate() %></td>
										<%
										}
							    		%>
							    		
							    		</tr>
							    		</table>
            
			            
			            <div style= "text-align: center" class="modal-footer">
			            <button type="button" class="btn btn-default btn-custom"
			               data-dismiss="modal">
			            Ignore
			            </button>
			           
			         </div> --%>
			      </div>
			   </div>
			</div>
      </div> 

   </div>  

<div class="row form-group col-md-offset-2 ">
 <button style="height: 150px;width: 285px" class="btn btn-primary btn-lg" onclick="goToHome()">Vehicle Billing</button>
 <button style="height: 150px;width: 285px" class="btn btn-primary btn-lg" onclick="goToDashBoard()">DashBoard</button>
</div>

<script type="text/javascript">
					function goToHome(){
					    window.location = '/SMT/jsp/OrderBill.jsp';
					}
					function goToHome1(){
					    window.location = 'Credit_Customer_Bill.jsp';
					}
					function goToDashBoard(){
					    window.location = '/SMT/jsp/index.jsp';
					} 
				
				</script>
</div>
</body>
