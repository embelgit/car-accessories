<%@page import="com.smt.bean.CarEntryBean"%>
<%@page import="com.smt.dao.CarEntryDao"%>
<%@page import="java.util.List"%>
<%boolean isHome = false;
%>
<%@include file="y_commons/header1.jsp"%>
 
<head>

<meta charset="utf-8">
  <script src="/SMT/staticContent/js/DeleteVehicleEntry.js"></script>
<script type="text/javascript">
function Back(){
	
	window.location ="Car_Entry.jsp";
}

</script>
</head>
		 	<div class="row header_margin_top">
			    <div align="center">
			  		<h2 class="form-name style_heading">Vehicle Entry</h2>
			  	</div>
			 
    </div>
     <div class="row">
		     <div class="col-sm-offset-1 col-md-10">
				  		<hr style="border-top-color:#c1b1b1;">
		     </div>	
    </div>
	<div class="container col-sm-offset-2" >
        <form class="form-horizontal" method="post" action="" name="delSup"> 
          <fieldset>
			
			 <div class="row form-group">
           		 
           		
           	<label class="col-md-3 control-label" for="expenseName">Vehicle Number<sup>*</sup></label>  
           	 		<div class="col-md-3">
            			<div class="input-group">
							<span class="input-group-addon">
								<i class="	glyphicon glyphicon-hand-right"></i>
							</span>
              				
							 <%
							 CarEntryDao sdd = new CarEntryDao();
           						List sList =sdd.getVehicleNumber();
							
							%> 
							
							<input list="sup_drop" id="vehicleNo"  class="form-control">
				            <datalist id="sup_drop">
							
							<%
					           for(int i=0;i<sList.size();i++){
					        	   CarEntryBean sup =(CarEntryBean)sList.get(i);
							%>
		
							<option data-value="<%=sup.getPkCarEntryId()%>" value="<%=sup.getCarNo() %> => <%=sup.getOwnerName()%>">
							<%
				      			}
				    		%>
						</datalist>        
            			</div>
           		 	</div>
				</div>
				
				<div class="form-group row">
		            <div class="col-md-10 text-center">
		          
       <input type="button"  id="btn" name="btn" style="font-size: 25" class="btn btn-large btn-success glyphicon glyphicon-save  button-height-width"  onclick="delvehicleEntry()" value="Submit">
       <input  id="save" name="btn" style="font-size: 25" class="btn btn-large btn-danger glyphicon glyphicon-remove-circle  button-height-width" type="reset"  onclick="reset()" value="Cancel">
       <input type="button" value="Back" style="font-size: 25" id="listBtn" class="btn btn-primary  btn-large  button-height-width" onclick="Back()" />
              	
              	

		            </div>
         </div>
		</fieldset>
       </form>
     </div>
     
     <div class="row footer_margin_top" align="center">
			<%@include file="y_commons/footer.jsp"%>
     </div> 
