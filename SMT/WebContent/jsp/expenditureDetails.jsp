<%@page import="java.util.List"%>
<%@page import="com.smt.hibernate.ExpenditureDetailsBean"%>
<%@page import="com.smt.dao.ExpenditureDetailsDao"%>
<%boolean isHome = false;
%>
<%@include file="y_commons/header1.jsp"%>
 
<head>
<meta charset="utf-8">
<script type="text/javascript" src="/SMT/staticContent/js/expenditureDetails.js"></script>
</head>
<div class="row header_margin_top">
			    <div align="center">
			  		<h2 class="form-name style_heading">Expenditure details</h2>
			  	</div>
			 
    </div>
     <div class="row">
		     <div class="col-sm-offset-1 col-md-10">
				  		<hr style="border-top-color:#c1b1b1;">
		     </div>	
    </div>
	<div class="container col-sm-offset-2" >
        <form class="form-horizontal" method="post" action="" name="expenseDetails"><!-- Value of 'name' attribute is used in customerDetails.js  -->
          <fieldset>
			
			 <div class="row form-group">
           		<label class="col-md-3 control-label" for="expenseName">Expenditure Name<sup>*</sup></label>  
           	 		<div class="col-md-3">
						<div class="input-group">
							<span class="input-group-addon">
								<i class="	glyphicon glyphicon-hand-right"></i>
							</span>
              				
							<!-- Following code is to get categories from "categories" table of "fertilizer" DB -->
							<!-- getAllMainCat() is implemented in  CategoryDetailsDao with return type List-->
							<%
							ExpenditureDetailsDao cdd = new ExpenditureDetailsDao();
           						List cList =cdd.getAllExpenseNames();
							
							%>
							<input list="cat_drop" id="expenseName"  class="form-control">
				<datalist id="cat_drop">
							<%
					           for(int i=0;i<cList.size();i++){
					        	   ExpenditureDetailsBean cat=(ExpenditureDetailsBean)cList.get(i);
							%>
		
							<option data-value="<%=cat.getPkExpenseDetailsId()%>" value="<%=cat.getExpenseName()%>">
							<%
				      			}
				    		%>
			              	
            			</datalist>
            			</div>
           		 	</div>
           		 </div>
           		 <div class="form-group row">
		            <div class="col-md-10 text-center">
		             <input type="button"  id="save" name="btn" class="btn btn-lg btn-success btn-md button_hw button_margin_right"  onclick="addExpenseDetails()" value="Save">
		            <input   id="save" name="btn"  class="btn btn-lg btn-danger btn-md button_hw button_margin_right"  type="reset"  onclick="reset()" value="Cancel">
	           		 	
	           		 	<!-- <button id="save" name="btn" class="btn btn-large btn-success glyphicon glyphicon-save  button-height-width"  onclick="addExpense()"><h4> Submit</h4></button>
              			<button class="btn btn-large btn-danger glyphicon glyphicon-remove-circle  button-height-width" type="reset"  onclick="reset()"><h4> Cancel</h4> </button> -->
	           		 </div>
         		</div>
           	</fieldset>
          </form>
         </div>
         
 
         
       <%-- <%@include file="commons/footer.jsp" %> --%>