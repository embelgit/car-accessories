<%@page import="com.smt.hibernate.UserDetail"%>
<%@page import="org.hibernate.Session"%>
<%@page import="com.smt.utility.HibernateUtility"%>
<%@ page contentType="text/html; charset=utf-8" language="java" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<meta name="description" content="" />

<script src="/SMT/staticContent/js/logout.js"></script>

<%-- <% String type1= "admin";%> --%>
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
			         } 
			         else {
							
					     response.sendRedirect("/SMT/jsp/login.jsp");
					     out.println("Please Login ");
				        }
			         
		           }
		             else {
							
					     response.sendRedirect("/SMT/jsp/login.jsp");
					     out.println("Please Login ");
				        }
	           %>




<% String  contextPath=request.getContextPath(); %>
<% String path=""; if(isHome)path="jsp\\"; %>


<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="/SMT/staticContent/guru/js/jquery.min.js"> </script> 
<link rel="stylesheet"
	href="/SMT/staticContent/y_css/bootstrap.min.css">
<title>Embel Technology</title>
<link rel="stylesheet"
	href="/SMT/staticContent/y_css/bootstrap.min.css"
	>
<%if(isHome){%>
<link rel="stylesheet" href="staticContent/y_css/style.css" />
<%}else{ %>
<link rel="stylesheet" href="../staticContent/y_css/style.css" />
<% } %>
<script
	src="/SMT/staticContent/y_js/bootstrap.min.js"></script>


<%if(isHome){%>
<link rel="stylesheet" href="staticContent/y_css/style.css" />
<%}else{ %>
<link rel="stylesheet" href="../staticContent/y_css/style.css" />
<% } %>


<meta name="viewport" content="width=device-width,initial-scale=1">

<!--  Guru fonts-->
<script type="text/javascript" src="/SMT/staticContent/y_js/jquery-1.12.3.min.js"></script>
<script type="text/javascript" src="/SMT/staticContent/guru/js/jquery.min.js"> </script> 
<script type="text/javascript" src="/SMT/staticContent/guru/js/bootstrap.js" > </script>
<script type="text/javascript" src="/SMT/staticContent/guru/js/bootstrap.min.js"> </script>
<link href="/SMT/staticContent/guru/css/bootstrap.css" rel="stylesheet">
<link href="/SMT/staticContent/guru/css/bootstrap.min.css" rel="stylesheet">
<link href="/SMT/staticContent/guru/css/bootstrap-theme.css" rel="stylesheet">
<link href="/SMT/staticContent/guru/css/bootstrap-theme.min.css" rel="stylesheet">

<!-- Font awesome (social icon) -->
<link href="/SMT/staticContent/guru/css/font-awesome.css" rel="stylesheet">
<link href="/SMT/staticContent/guru/css/font-awesome.min.css" rel="stylesheet">

<script type="text/javascript" src="/SMT/staticContent/guru/js/npm.js"> </script>
<script type="text/javascript" src="/SMT/staticContent/guru/js/tooltip.js"> </script>

<script type="text/javascript" src="/SMT/staticContent/js/shortcut.js"> </script>


 <!-- Fonts -->
<link rel="stylesheet" href="/SMT/staticContent/guru/fonts/fontawesome-webfont.eot"/>
<link rel="stylesheet" href="/SMT/staticContent/guru/fonts/fontawesome-webfont.svg"/>
<link rel="stylesheet" href="/SMT/staticContent/guru/fonts/fontawesome-webfont.ttf"/>
<link rel="stylesheet" href="/SMT/staticContent/guru/fonts/fontawesome-webfont.woff"/>
<link rel="stylesheet" href="/SMT/staticContent/guru/fonts/fontawesome-webfont.woff2"/>
<link rel="stylesheet" href="/SMT/staticContent/guru/fonts/FontAwesome.otf"/>
<link rel="stylesheet" href="/SMT/staticContent/guru/fonts/glyphicons-halflings-regular.eot"/>
<link rel="stylesheet" href="/SMT/staticContent/guru/fonts/glyphicons-halflings-regular.svg"/>
<link rel="stylesheet" href="/SMT/staticContent/guru/fonts/glyphicons-halflings-regular.ttf"/>
<link rel="stylesheet" href="/SMT/staticContent/guru/fonts/glyphicons-halflings-regular.woff"/>
<link rel="stylesheet" href="/SMT/staticContent/guru/fonts/glyphicons-halflings-regular.woff2"/> 
	

 	<link rel="stylesheet" href="/SMT/staticContent/y_css/jquery-ui.min.css">
 
    <link rel="stylesheet" href="/SMT/staticContent/y_css/ui.jqgrid.min.css">
    <!-- <script src="/SMT/staticContent/y_js/jquery.min.js"></script> -->
    <!-- <script src="/SMT/staticContent/y_js/jquery.jqgrid.min.js"></script> -->
    
    
    <!--y_temporaryStockReport.jsp-->
    
<script type="text/javascript" src="/SMT/staticContent/y_js/bootstrap.js"> </script>
<script type="text/javascript" src="/SMT/staticContent/js/bootstrap.min.js"> </script>
<link href="/SMT/staticContent/css/bootstrap.css" rel="stylesheet">
<link href="/SMT/staticContent/css/bootstrap.min.css" rel="stylesheet">
<link href="/SMT/staticContent/y_css/bootstrap-theme.css" rel="stylesheet">
<link href="/SMT/staticContent/css/bootstrap-theme.min.css" rel="stylesheet">

<!-- All shortcut key's code here -->

<script type="text/javascript">

	/* Master Moudule keys */
	
	/* //Category Entry
	shortcut.add("alt+c", function() {
	    window.location.href = "y_category_subcategory.jsp";
		});
	
	//Category list
	shortcut.add("ctrl+alt+c", function() {
	    window.location.href = "s_category_list.jsp";
	});
	
	//product Entry
	shortcut.add("alt+p", function() {
	    window.location.href = "y_product_detail.jsp";
	});
	
	//product list
	shortcut.add("ctrl+alt+p", function() {
	    window.location.href = "s_item_list.jsp";
	});
	
	//Supplier Entry
	shortcut.add("alt+s", function() {
	    window.location.href = "s_supplier_detail.jsp";
	});
	
	//Supplier List
	shortcut.add("ctrl+alt+s", function() {
	    window.location.href = "s_supplier_list.jsp";
	});
	
	//Customer Entry
	shortcut.add("alt+r", function() {
	    window.location.href = "customer_detail.jsp";
	});
	
	//Customer List
	shortcut.add("ctrl+r", function() {
	    window.location.href = "creditCustomerList.jsp";
	}); */
	
	
	
	
	
</script>
    
  
	


		<!-- header start-->
<!-- <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css"> -->
<script src="/SMT/staticContent/y_js/prefixfree.min.js"></script>
<!-- <script src="/SMT/staticContent/y_js/modernizr.min.js"></script> -->
<style>
@import url(http://fonts.googleapis.com/css?family=roboto:400,400italic,600,700,800);

*,
html,
body,
div,
dl,
dt,
dd,
ul,
ol,
li,
h1,
h2,
h3,
h4,
h5,
h6,
pre,
form,
label,
fieldset,
input,
p,
blockquote,
th,
td {
  margin: 0;
  padding: 0;
}

article,
aside,
figure,
footer,
header,
hgroup,
nav,
section { display: block; }

* {
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  box-sizing: border-box;
}

html { -webkit-font-smoothing: antialiased; }

a {
  color: #BA0707;
  text-decoration: none;
}

a:hover { color: #BA0707; }

body {
  
  color: #000000;
  font: 14px "roboto", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  line-height: 1;
  width: 100%;
}

nav {
  display: block;
  background:#8c7674
}

.menu { display: block; }

.menu li {
  display: inline-block;
  position: relative;
  z-index: 100;
}

.menu li:first-child { margin-left: 0; }

.menu li a {
  font-weight: 600;
  text-decoration: none;
  padding: 10px 20px;
  display: block;
  color: #fff;
  transition: all 0.2s ease-in-out 0s;
}

.menu li a:hover,
.menu li:hover>a {
  color: #fff;
  background: cadetblue;
}

.menu ul {
  visibility: hidden;
  opacity: 0;
  margin: 0;
  padding: 0;
  width: 150px;
  position: absolute;
  left: 0px;
  background: #fff;
  z-index: 99;
  transform: translate(0, 20px);
  transition: all 0.2s ease-out;
}

.menu ul:after {
  bottom: 100%;
  left: 20%;
  border: solid transparent;
  content: " ";
  height: 0;
  width: 0;
  position: absolute;
  pointer-events: none;
  border-color: rgba(255, 255, 255, 0);
  border-bottom-color: #fff;
  border-width: 6px;
  margin-left: -6px;
}

.menu ul li {
  display: block;
  float: none;
  background: none;
  margin: 0;
  padding: 0;
}

.menu ul li a {
  font-size: 15px;
  font-weight: bold;
  display: block;
  color: #797979;
  background: #fff;
}

.menu ul li a:hover,
.menu ul li:hover>a {
  background: #FC6D58;
  color: #fff;
}

.menu li:hover>ul {
  visibility: visible;
  opacity: 1;
  transform: translate(0, 0);
}

.menu ul ul {
  left: 150px;
  top: 0px;
  visibility: hidden;
  opacity: 0;
  transform: translate(20px, 20px);
  transition: all 0.2s ease-out;
}

.menu ul ul:after {
  left: -6px;
  top: 10%;
  border: solid transparent;
  content: " ";
  height: 0;
  width: 0;
  position: absolute;
  pointer-events: none;
  border-color: rgba(255, 255, 255, 0);
  border-right-color: #fff;
  border-width: 6px;
  margin-top: -6px;
}

.menu li>ul ul:hover {
  visibility: visible;
  opacity: 1;
  transform: translate(0, 0);
}

.responsive-menu {
  display: none;
  width: 100%;
  padding: 10px 8px;
  background: #E95546;
  color: #fff;
  text-transform: uppercase;
  font-weight: 600;
}

.responsive-menu:hover {
  background: #E95546;
  color: #fff;
  text-decoration: none;
}

a.homer { background: #ab8da0; }
 @media (min-width: 768px) and (max-width: 979px) {

.mainWrap { width: 768px; }

.menu ul { top: 37px; }

.menu li a { font-size: 12px; }

a.homer { background: #E95546; }
}
 @media (max-width: 767px) {

.mainWrap {
  width: auto;
  padding: 30px 20px;
}

.menu { display: none; }

.responsive-menu { display: block; }

nav {
  margin: 0;
  background: none;
}

.menu li {
  display: block;
  margin: 0;
}

.menu li a {
  background: #fff;
  color: #797979;
}

.menu li a:hover,
.menu li:hover>a {
  background: #8c7674;
  color: #fff;
}

.menu ul {
  visibility: hidden;
  opacity: 0;
  top: 0;
  left: 0;
  width: 100%;
  transform: initial;
}

.menu li:hover>ul {
  visibility: visible;
  opacity: 1;
  position: relative;
  transform: initial;
}

.menu ul ul {
  left: 0;
  transform: initial;
}

.menu li>ul ul:hover { transform: initial; }
}
</style>
</head>

<body>
<nav class="navbar navbar-fixed-top"> 
<a id="resp-menu" class="responsive-menu" href="#"><i class="fa fa-reorder"></i> Menu</a>
  <ul class="menu">
    <li><a class="homer" href="/SMT/jsp/OrderBill.jsp"><i class="fa fa-home"></i> HOME</a>
     
    </li>
    <li><a  href="/SMT/jsp/OrderBill.jsp"><i class="fa fa-sitemap"></i> Master</a>
      <ul class="sub-menu">
        <li><a href="<%=path%>y_category_subcategory.jsp">Category</a>
           <%
    			  if(type1.equals("salesman")|| type1.equals("admin")){
    	  
            %>	
        
          <%-- <ul>
            <li><a href="<%=path%>y_category_subcategory.jsp">Add Category</a></li>
			<li><a href="<%=path%>s_category_list.jsp" >Category List</a></li>
			
          </ul> --%>
        </li>
        <li><a href="<%=path%>y_product_detail.jsp">Add Products</a>
          <%-- <ul>
           <li><a href="<%=path%>y_product_detail.jsp">Product Details</a></li>
		   <li><a href="<%=path%>s_item_list.jsp" >Product List</a></li>
          </ul> --%>
        </li>
        
        <%--  <li><a href="<%=path%>vat_Entry.jsp">GST Tax</a></li> --%>
        
        <%}%>	
		  <% if(type1.equals("salesman")|| type1.equals("admin") || type1.equals("account") ){ %>	        
        
        <li><a href="<%=path%>s_supplier_detail.jsp">Supplier Entry</a>
         <%--  <ul>
            <li><a href="<%=path%>s_supplier_detail.jsp">Supplier Details</a></li>
			<li><a href="<%=path%>s_supplier_list.jsp">Supplier List</a></li>
			<li><a href="<%=path%>s_SupplierEdit.jsp">Edit Supplier</a></li>
			
          </ul> --%>
        </li>
        
        <%}%>	
        
        <li><a href="<%=path%>customer_detail.jsp">Credit Customer</a>
         <%--  <ul>
            <li><a href="<%=path%>customer_detail.jsp" >Credit Customer</a></li>
			<li><a href="<%=path%>creditCustomerList.jsp" >Credit Customer List</a></li>
			<li><a href="<%=path%>editCreditCustomerDetails.jsp" >Edit Credit Customer</a></li>
          </ul> --%>
        </li>
        
        <li><a href="<%=path%>employee_detail.jsp">Employee</a>
          <%-- <ul>
            <li><a href="<%=path%>employee_detail.jsp">Employee Entry</a></li>
			<li><a href="<%=path%>employeeList.jsp" >Employee List</a></li>
			<li><a href="<%=path%>editEmployeeDetails.jsp" >Edit Employee</a></li>
			
          </ul> --%>
        </li>
        
        <li><a href="<%=path%>expenditureDetails.jsp">Expenditure Detail</a></li>
        
        <li><a href="<%=path%>barcodeCopy.jsp">Barcode Copy</a></li>
         <li><a href="<%=path%>service.jsp">Service Entry</a></li>
			
        
      </ul>
    </li>
   
  <li><a  href="#"><i class="fa fa-shopping-cart"></i> Purchase</a>
      		<ul class="sub-menu">
            <% if(type1.equals("salesman")|| type1.equals("admin")  ){ %>	
						
						<li><a href="<%=path%>s_po_received.jsp">Purchase Goods Received</a></li>
						<li><a href="<%=path%>s_preGrnReg.jsp">Previous Goods Received</a></li>
						<li><a href="<%=path%>s_purchase_return.jsp">Purchase Return</a></li>
						
						
				<%
					}
				%>		
          </ul>
        </li>
    
    <li><a  href="#"><i class="fa fa-edit"></i> Billing</a>
      <ul class="sub-menu">
      		<%if(type1.equals("account")|| type1.equals("admin")){ %>
			
			        	<li><a href="<%=path%>Car_Entry.jsp" >Vehicle Entry</a></li>
        				<li><a href="<%=path%>OrderBill.jsp" >Customer Bill</a></li>
        				<li><a href="<%=path%>Miscellaneous.jsp" >Miscellaneous Bill</a></li>
        				<li><a href="<%=path%>Credit_Customer_Bill.jsp" >Credit Customer Bill</a></li>
        				<li><a href="<%=path%>EstimateQuotationBill.jsp" >Estimate Quotation Bill</a></li>
        			
						<li><a href="<%=path%>y_sale_return.jsp">Sale Return</a></li>
        <%}%>
        			
      </ul>
    </li>
    
   		<%-- <li><a  href="#"><i class="fa fa-shopping-cart"></i> Purchase</a>
      		<ul class="sub-menu">
            <% if(type1.equals("salesman")|| type1.equals("admin")  ){ %>	
						
						<li><a href="<%=path%>s_po_received.jsp">Purchase Order Received</a></li>
						<li><a href="<%=path%>s_preGrnReg.jsp">Previous Goods Received</a></li>
						<li><a href="<%=path%>s_purchase_return.jsp">Purchase Return</a></li>
						
						
				<%
					}
				%>		
          </ul>
        </li> --%>
        
    
    <%
					if(type1.equals("admin") || type1.equals("account") || type1.equals("salesman"))
					
					{
				
				%>
    
   <li><a  href="#"><i class="fa fa-bar-chart"></i> Stock</a>
      <ul class="sub-menu">
        <li><a href="<%=path%>s_current_stock.jsp">Current Stock </a></li>
								
								<li><a href="<%=path%>s_CategoryWiseStock.jsp">CategoryWise Stock</a></li>
								<li><a href="<%=path%>s_supplierWiseStock.jsp">SupplierWise Stock</a></li>
								<li><a href="<%=path%>BarcodeWiseStock.jsp">Barcode Wise Stock</a></li>
								<li><a href="<%=path%>BillNoWiseStock.jsp">BillNO Wise Stock</a></li>
							
								
								<%
								
								}
								%>
       
      </ul>
    </li>
    
     <li><a  href="<%=path%>allPaymentWithLeftTabs.jsp"><i class="fa fa-edit"></i> Cash Book</a>
    </li>
      	
    <li><a  href="#"><i class="fa fa-file-zip-o"></i> Reports</a>
      <ul class="sub-menu">
	  
				
					            <li><a href="<%=path%>DayClosingReport.jsp">Day Closure Report</a></li>
								<li><a href="<%=path%>purchaseReports.jsp">Gst Wise Purchase Report</a></li>
								<li><a href="<%=path%>gstSaleReport.jsp">Gst Wise Sale Report</a></li>
								<li><a href="<%=path%>cashBookReports.jsp">Cash Book Report</a></li>
								<li><a href="<%=path%>UnPaidAmountReport.jsp">UnPaid Amount Report</a></li>
								<li><a href="<%=path%>allReports.jsp">ALL Reports</a></li>
								
								<li><a href="<%=path%>TallyPurchaseReport.jsp">Tally Purchase Reports</a></li>
								<li><a href="<%=path%>TallySaleReport.jsp">Tally Sale Reports</a></li>
								<li><a href="<%=path%>notification.jsp">Notification</a></li>
								<%-- <li><a href="<%=path%>saleReports.jsp">Sale Reports</a></li> --%>
								<li><a href="<%=path%>vehicleEntryReport.jsp">Vehicle Entry Reports</a></li>
								
							
      </ul>
    </li>
    
     <li><a  href="#"><i class="fa fa-file-zip-o"></i> Bill Copy</a>
      <ul class="sub-menu">
	    
				
					            <li><a href="<%=path%>Bill_Copy.jsp"">Customer Bill Copy</a></li>
								<li><a href="<%=path%>Other_Bill_Copy.jsp">Other Bill Copy</a></li>
								<li><a href="<%=path%>CreditCustCopy.jsp">Credit Cust Copy</a></li>
							
      </ul>
    </li>
   
	            <%
    
                  if(type1.equals("admin")){
    	  
                %>	
    <li><a  href="/SMT/jsp/create_user.jsp"><i class="fa fa-user-o"></i> Create User</a></li>
    <%
				}
				%>
				
				<%
    
                  if(type1.equals("admin") || type1.equals("account") ||type1.equals("salesman")){
    	  
                %>	
    <li><a  onclick="Logout()"><i class="fa fa-power-off"></i> Logout</a></li>
    <li id="demo" style="color: yellow; padding-left: 20px;">
    
		<script>
		   var date = new Date();
		   document.getElementById("demo").innerHTML = date.toDateString();
		</script>
	
	</li>
	<!--  <li><a  href="#"><i class="fa fa-file-zip-o"></i> Motoride</a>
	 </li> -->
    
    <%-- <li style="color: yellow; padding-left: 50px;">User :: <%out.println(name1); %></li> --%>
    <%
    
                  }
    %>
  </ul>
  
</nav>


<script src="/SMT/staticContent/y_js/jquery-2.1.3.min.js"></script> 
<script>
$(document).ready(function(){ 
	var touch 	= $('#resp-menu');
	var menu 	= $('.menu');
 
	$(touch).on('click', function(e) {
		e.preventDefault();
		menu.slideToggle();
	});
	
	$(window).resize(function(){
		var w = $(window).width();
		if(w > 767 && menu.is(':hidden')) {
			menu.removeAttr('style');
		}
	});
	
});
</script>
<script type="text/javascript">

  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-36251023-1']);
  _gaq.push(['_setDomainName', 'jqueryscript.net']);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();

</script>

<!-- header end -->


	
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     