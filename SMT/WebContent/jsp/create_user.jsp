<%@page import="org.hibernate.Session"%>
<%@page import="com.smt.utility.HibernateUtility"%>
<%@page import="java.util.List"%>
<%@page import="com.smt.helper.UserDetailHelper"%>
<%@page import="com.smt.hibernate.UserDetail"%>


<!DOCTYPE html>

<%
         String name = "";
		if (session != null) {
			
			if (session.getAttribute("user") != null) {
			    name = (String) session.getAttribute("user");
				out.print("Hello, " + name );
				
				
			} else {
				
				response.sendRedirect("/SMT/jsp/login.jsp");
				out.println("Please Login ");
			}
		}
	%>
	<%
	
	   HibernateUtility hbu=HibernateUtility.getInstance();
	   Session session1=hbu.getHibernateSession();
	   
	   org.hibernate.Query query = session1.createQuery("from UserDetail where userName =:name");
	   query.setParameter("name", name);
	   UserDetail userDetail = (UserDetail) query.uniqueResult();
	   String type = userDetail.getTypeId();
	   
	   
	%>
	
    
	
	<%
    
      if(type.equals("admin")){
    	  
     %>	
<html lang="en">

<% boolean isHome=false;%>
<%@include file="y_commons/header1.jsp"%>


	
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
  
      <script src="/SMT/staticContent/js/userlogin.js"></script>

    <script type="text/javascript">
	    function ChooseContact(){
			//document.getElementById("buyPrice")(data.options[data.selectedIndex].getAttribute("myid"));
			//document.cat.categoryName.value == (data.options[data.selectedIndex].getAttribute("value"));
	
	        <%
	        UserDetailHelper usrHelper = new UserDetailHelper();
		   		List usrList = usrHelper.getAlluserName();
			%>
	
			<%
				for(int i=0;i<usrList.size();i++){
					UserDetail usr = (UserDetail)usrList.get(i);
    		%>

    		var value ="<%=usr.getUserName()%>";
				if(document.getElementById("userName").value == value)
					{
						document.createusr.btn.disabled = true;	
						alert("User Name Not Available..!!!");
				if(document.createusr) 
					{
	  					document.createusr.reset();
    				}
						document.createusr.btn.disabled = false;
						return false;
					}
   			<%
				}
    		%>
		}
	</script>
    

</head>

<body>


	<div class="container-fluid">
		<h2 align="center" class="form-name form-heading" style="margin-top: 40px; margin-bottom: 45px;">Create User</h2>
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="login-panel panel panel-default">
                    
                    <div class="panel-body">
                        <form action="" method="post" name="createusr" name="createuser" >
                            
                            	<div class="form-group-2" style="margin-top: 10px;">
									<label>Type:</label>
                                    <select class="form-control" id="typeId">
										<option value="">--Select Type--</option>
										<option value="admin">Admin</option>
										<option value="account">Account</option>
										<option value="salesman">Salesman</option>
									</select>
                                </div>
                                <div class="form-group-2" style="margin-top: 10px;">
									<label>Username:</label>
                                    <input type="text" class="form-control" id="userName" placeholder="Username" autofocus onchange="return ChooseContact();"  >
                                </div>
                                <div class="form-group-2" style="margin-top: 10px;">
									<label>Password:</label>
                                    <input type="password" class="form-control" id="password"  placeholder="Password"   >
                                </div>
                                <div class="form-group-2" style="margin-top: 10px;">
									<label>Re-Password:</label>
                                    <input type="password" class="form-control" id="repassword" placeholder="Re-Password" autofocus="autofocus" >
                                </div>
                              <div class="wrapper" style="margin-top: 20px;">
                                <input type="button" name="btn" value="Submit" class="btn btn-md btn-lg btn-success btn-md button_hw button_margin_right"  onclick="validataionUserLogin()"/>
                                <input type="reset" value="Reset" class="btn btn-md btn-lg btn-danger btn-md button_hw button_margin_right" />
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
   
</body>
</html>
    <%
      }else
    
            {
    	 
    	  out.println("<span class=\"myspan\">You Can Not view This Page</span>");
    	
          }
    
    	%>
    
    

