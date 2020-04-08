   
function calculateTotal()
{
	var NoBarrel=$('#NoBarrel').val();
	var perlitre=$('#perlitre').val();

	var TotalBarrel=+NoBarrel* +perlitre;
	
	document.getElementById('TotalBarrel').value=TotalBarrel;

}



function productdel(){
	document.getElementById("btn").disabled = true;	
	var params= {};
	var itemName= $('#itemName').val();
	
	
	var input = document.getElementById('catId'),
    list = document.getElementById('catId_drop'),
    i,catId;
    for (i = 0; i < list.options.length; ++i) {
    if (list.options[i].value === input.value) {
    	catId = list.options[i].getAttribute('data-value');
    
    }
    
    }

   
    var hsnsacno = $("#hsnsacno").val();
 	var modelName = $("#modelName").val();
 	var categoryName = $("#catId").val();
 	var NoBarrel = $("#NoBarrel").val();
 	var perlitre = $("#perlitre").val();
 	var TotalBarrel = $("#TotalBarrel").val();
 	//alert("category+++++"+category)
 
 	params["itemName"] = itemName;
 	params["hsnsacno"] = hsnsacno;
   
    params["modelName"] = modelName;
    params["NoBarrel"] = hsnsacno;
    params["perlitre"] = perlitre;
    params["TotalBarrel"] = TotalBarrel;

    params["catId"] =catId;
    params["categoryName"] =categoryName;
	
	        
	 	   params["methodName"] = "doBarrelDetail";
	 	    
	 	    
	 	    
	$.post('/SMT/jsp/utility/controller.jsp',params,function(data)
	    	{
				alert(data);
				location.reload();
			}
	    	).error(function(jqXHR, textStatus, errorThrown){
	    		if(textStatus==="timeout") {
	    			$(loaderObj).hide();
	    			$(loaderObj).find('#errorDiv').show();
	    		}
	    	});
}