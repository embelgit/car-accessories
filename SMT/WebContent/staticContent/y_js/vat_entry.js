
function vatValidation(){
	
	var vatName= $('#vatName').val();
	var vatPer= $('#vatPer').val();
	
	var vatPerPattern = /\d+(\.\d+)?/;
	var vatPerPatternRes = vatPerPattern.test(vatPer);
	
	
	
	if(vatName != null && vatName != "" && vatName != " ")
		{
		if(vatPer != null && vatPer != "" && vatPer != " ")
		{
		 if(vatPerPatternRes){
			 vatEntry();
		 }
		 else{
			 
			 alert("Enter only Number here in Vat percentage !");
		 }
		}
	else{
		alert("Please Enter Vat Percentage Amount");
	}
		}
	else{
		alert("Please Enter Vat name");
	}
}

function vatEntry(){
	
	document.getElementById("createbtn").disabled =true;
	
	var vatName = $('#vatName').val();
	var vatPer = $('#vatPer').val();
	
	var params = {};
	
	params["vatName"] = vatName;
	params["vatPer"] = vatPer;
	
	params["methodName"] = "vatEntry";
	
	$.post('/SMT/jsp/utility/controller.jsp',params,function(data)
 	    	{
 				alert(data);
 				location.reload();
 				document.getElementById("createbtn").disabled =false;
 			}
 	    	).error(function(jqXHR, textStatus, errorThrown){
 	    		if(textStatus==="timeout") {
 	    			$(loaderObj).hide();
 	    			$(loaderObj).find('#errorDiv').show();
 	    		}
 	    	});
}
