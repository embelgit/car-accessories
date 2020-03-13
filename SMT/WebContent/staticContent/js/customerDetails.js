function customerDetails(){

	
	if(document.cstd.firstName.value == "")
	{
		alert("Enter Customer First Name.");
		return false;
	}	
	var letterNumber = /^[a-zA-Z, ]+$/;
	if(document.cstd.firstName.value.match(letterNumber))
	{
		if(document.cstd.middleName.value == "")
		{
			alert("Enter Customer Middle Name.");
			return false;
		}	
		var letterNumber = /^[a-zA-Z, ]+$/;
		if(document.cstd.middleName.value.match(letterNumber))
		{
			if(document.cstd.lastName.value == "")
			{
				alert("Enter Customer Last Name.");
				return false;
			}	
			var letterNumber = /^[a-zA-Z, ]+$/;
			if(document.cstd.lastName.value.match(letterNumber))
			{
				           if(document.cstd.address.value == "")
   				           {
   					          alert("Please Enter Employee Address.");
   					          return false;
   				           }	
   				           var letterNumber = /^[a-zA-Z0-9, ]+$/;
   				           if(document.cstd.address.value.match(letterNumber))
   				           {
				           
	   	        			 if ( document.cstd.contactNo.value == "" )
	   	        			 {
					  	       alert("Please Enter Contact Number");
					  	       return false;
	   	        			 }
	   	        			 var letterNumber = /^[0-9]{10}$/;
	   	        			 if(document.cstd.contactNo.value.match(letterNumber) && document.cstd.contactNo.value != "0000000000")
	   	        			 {
	   	        				
	   	        					if ( document.cstd.aadharNo.value == "" || document.cstd.aadharNo.value == null)
								    {
								         
								  	      alert("Please Enter GSTTIN/UIN  Number");
								          return false;
								    }
									
	   	        					if ( document.cstd.zipCode.value == "" )
								    {
								         
								  	      alert("Please Enter Zip Code");
								          return false;
								    }
									var letterNumber = /^[0-9]{6}$/;
									if(document.cstd.zipCode.value.match(letterNumber))
									{
											
										var letterNumber = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
						   	        	 if(document.cstd.emailId.value.match(letterNumber) || document.cstd.emailId.value == null ||  document.cstd.emailId.value == "")
						   	        	 {

						   	        		 custDetails();
										}
									
						   	        	 else
											{
												alert("Enter a Valid email address..!!");
												return false;
											}
										}
									 else
										{
												alert("Enter 6 digit Numbers Only in zip code field..!!");
												return false;
											}
										}
										
	   	        			      else
								{
									alert("Enter 10 digits Only in contact number field And only numbers..!!");
									return false;
									}	
								}
   				           
   				        else
						{
							alert("Enter Alphabates Only in address field..!!");
							return false;
						}	
					}
																
			else
				{
					alert("Enter Alphabets Only in last name field..!!");
					return false;
				}
			}
																
		else
			{
				alert("Enter Alphabets Only in middle name field..!!");
				return false;
			}
		}
												
	else
		{
			alert("Enter Alphabets Only in first name field..!!");
			return false;
		}

	}	



function custDetails(){
	
	document.cstd.btn.disabled = true;
				var firstName = $('#firstName').val();
				var middleName = $('#middleName').val();
				var lastName = $('#lastName').val();
				var address = $('#address').val();
				var contactNo  = $('#contactNo').val();
				var emailId = $('#emailId').val();
				var zipCode = $('#zipCode').val();
				var aadhar = $('#aadharNo').val();
				var params = {};
				
				params["firstName"] =firstName;
				params["middleName"] =middleName;
				params["lastName"] =lastName;
				params["address"] = address;
				params["contactNo"] =contactNo;
				params["emailId"] = emailId;
				params["zipCode"] = zipCode;
				params["aadhar"] = aadhar;
				params["methodName"] = "customerDetails";
				
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


function reset()
{
   document.cstd.reset();	

}




/*************** Edit Credit Customer Details **********/
function getCustomerDetails(){
	var params= {};
	
	var input = document.getElementById('creditCustomer'),
     list = document.getElementById('cust_drop'),
     	i,fkRootCustId;
	 		for (i = 0; i < list.options.length; ++i) {
			     if (list.options[i].value === input.value) {
			    	 fkRootCustId = list.options[i].getAttribute('data-value');
			     }
	 		}
	
	$("#firstName").append($("<input/>").attr("value","").text());
	$("#middleName").append($("<input/>").attr("value","").text());
	$("#lastName").append($("<input/>").attr("value","").text());
	$("#address").append($("<input/>").attr("value","").text());
	$("#contactNo").append($("<input/>").attr("value","").text());
	$("#aadharNo").append($("<input/>").attr("value","").text());
	$("#emailId").append($("<input/>").attr("value","").text());
	$("#zipCode").append($("<input/>").attr("value","").text());
	
	
	
	params["creditCustomer"]= fkRootCustId;
	params["methodName"] = "getCreditCustomerDetailsToEdit";
	
	$.post('/SMT/jsp/utility/controller.jsp',params,function(data){
		
		var jsonData = $.parseJSON(data);
		var catmap = jsonData.list;
		$.each(jsonData,function(i,v)
				{
				  document.getElementById("firstName").value = v.firstName;
			      document.getElementById("middleName").value = v.middleName;
			      document.getElementById("lastName").value = v.lastName;
			      document.getElementById("address").value = v.address;
			      document.getElementById("contactNo").value = v.contactNo;
			      document.getElementById("aadharNo").value = v.aadhar;
			      document.getElementById("emailId").value = v.email;
			      document.getElementById("zipCode").value = v.zipCode;
			   
		      
				});
			}).error(function(jqXHR, textStatus, errorThrown){
				if(textStatus==="timeout") {

				}
			});
 	    	
}


function updateCustomerDetails(){

	
	document.cstd1.btn.disabled = true;
	
	var input = document.getElementById('creditCustomer'),
    list = document.getElementById('cust_drop'),
    	i,fkRootCustId;
	 		for (i = 0; i < list.options.length; ++i) {
			     if (list.options[i].value === input.value) {
			    	 fkRootCustId = list.options[i].getAttribute('data-value');
			     }
	 		}
	
	//var customerId = document.getElementById("customerId").value;
	
	var firstName = $('#firstName').val();
	var middleName = $('#middleName').val();
	var lastName = $('#lastName').val();				
	var address = $('#address').val();
	var contactNo = $('#contactNo').val();
	var aadharNo = $('#aadharNo').val();
	var emailId = $('#emailId').val();
	var zipCode = $('#zipCode').val();
	

	
	
	var params = {};
	
	params["customerId"] = fkRootCustId;
	
	params["firstName"] = firstName;	
	params["middleName"] = middleName;
	params["lastName"] = lastName;
	params["address"] = address;
	params["contactNo"] =contactNo;
	params["aadharNo"] = aadharNo;
	params["emailId"] = emailId;
	params["zipCode"] = zipCode;
	
	
	
	params["methodName"] = "updateCreditCustomerDetails";

	$.post('/SMT/jsp/utility/controller.jsp',params,function(data){
			   alert(data);
		       location.reload();		
			}
 	    	).error(function(jqXHR, textStatus, errorThrown){
 	    		
 	    		/*alert("Data Added Successfully..");
 	    		location.reload();
 				document.ccd.btn.disabled =false;*/
 	    		
 	    		if(textStatus==="timeout") {
 	    			$(loaderObj).hide();
 	    			$(loaderObj).find('#errorDiv').show();
 	    		}
 	    	});


}

