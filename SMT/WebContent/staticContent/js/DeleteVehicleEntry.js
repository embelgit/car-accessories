
//Delete of vehicle Entry
function delvehicleEntry(){
	
	if(document.delSup.vehicleNo.value == "")
	{
		alert("Please Enter vehicle Number");
		return false;
	}	
	
	deletevehicleEntry();

}

function deletevehicleEntry(){
	
	var input = document.getElementById('vehicleNo'),
    list = document.getElementById('sup_drop'),
    i,vehicleNo;

	for (i = 0; i < list.options.length; ++i) {
    if (list.options[i].value === input.value) {
    	vehicleNo = list.options[i].getAttribute('data-value');
    	}
	}
	
	var params = {};
	
	params["vehicleNo"] =vehicleNo;
	
	params["methodName"] = "deleteVehicle";
	
	$.post('/SMT/jsp/utility/controller.jsp',params,function(data)
 	    	{
 		if(data=="↵↵↵↵↵↵↵"){
 			alert("Not Added");
 		}
 		else{
 			alert(data);
 		}
 			location.reload();

 			}
 	    	).error(function(jqXHR, textStatus, errorThrown){
 	    		if(textStatus==="timeout") {
 	    			$(loaderObj).hide();
 	    			$(loaderObj).find('#errorDiv').show();
 	    		}
 	    	});
	
}