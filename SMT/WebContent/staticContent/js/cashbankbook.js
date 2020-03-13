
//Adding supplier Payment
    function supplierPayment(){
    	
    	
    	var balanceAmount = $("#balanceAmount").val();
    	var supPay = $("#supPay").val();
    	var paymentType = $('#paymentType').val();
    	var paymentMode = $('#paymentMode').val();
    	
    	if(+supPay > +balanceAmount ){
    		alert("Supplier Payment Amount Should Be Less Than Balance Amount");
    		return false;
    	}
	
    	if(document.spmt.supplier.value == "")
    	{
    		alert("Please select Supplier Name");
    		return false;
    	}	

    	if(paymentMode == "selected" && paymentMode == null && paymentType == " " && paymentType == "selected")
    	{
    		alert("Please select Payment Mode");
    		return false;
    	}
    	
    	
    	if(document.spmt.billNo.value == "")
    	{
    		alert("Please select Bill number");
    		return false;
    	}
    	if(document.spmt.supPay.value == "")
    	{
    		alert("Please Enter Supplier Payment  amount");
    		return false;
    	}
    	var letterNumber = /^[0-9]+([.][0-9]+)?$/;
			 if(document.spmt.supPay.value.match(letterNumber))
			 { 
				
							 if(document.spmt.personname.value == "")
						    	{
						    		alert("Please enter Accountant name");
						    		return false; 
						    	} 
								 var letterNumber = /^[a-zA-Z]+$/;
									if(document.spmt.personname.value.match(letterNumber))
									{
										sup();
									}
									
					   	        	 else
										{
											alert("Enter only alphabates in Accountant name field..!!");
											return false;
										}
									}
				
		 else
		 {
			alert("Enter only numbers in credit field..!!");
			return false;
		 }
	}
		
   
  function	sup(){
		
	  document.getElementById("btn1").disabled = true;
	  	
	  			var billNo = $('#billNo').val();
	  			var totalAmount = $('#totalAmount').val();
	  			var paymentType = $('#paymentType').val();
	  			var paymentMode = $('#paymentMode').val();
	  			var chequeNum = $('#chequeNum').val();
	  			var nameOnCheck = $('#nameOnCheck').val();
	  			var bankName = $('#bankName').val();
	  			var cardNum = $('#cardNum').val();
	  			var accNum = $('#accNum').val();
	  			var personname = $('#personname').val();
	  			var supPay = $('#supPay').val();
	  			
	  			
	  		
				
				 var input = document.getElementById('supplier'),
				     list = document.getElementById('sup_drop'),
				     	i,fkRootsupId;
					 		for (i = 0; i < list.options.length; ++i) {
							     if (list.options[i].value === input.value) {
							    	 fkRootsupId = list.options[i].getAttribute('data-value');
							     }
					 		}
		 
					 	
		
			var params= {};
			
			params ["supplier"] = fkRootsupId;
			params ["billNo"] = billNo;
			params ["totalAmount"] = totalAmount;
			params ["paymentType"] = paymentType;
			params ["paymentMode"] = paymentMode;
			params ["chequeNum"] = chequeNum;
			params ["nameOnCheck"] = nameOnCheck;
			
			params ["bankName"] = bankName;
			params ["cardNum"] = cardNum;
			params ["accNum"] = accNum;
			params ["personname"] = personname;
			params ["supPay"] = supPay;
			
			
			params["methodName"] = "regSupCashBook";
			
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

  /*
   * Name : Meghraj Menkudle Date : 21/05/2017 Method Name :
   * customerPaymentValidation() Reason : Credit Customer Validation in cashBook
   */

  function customerPaymentValidation() {
  	var creditCustomer = $("#creditCustomer").val();
  	var creditCustBillNo = $("#billNo1").val();
  	var creditCustAccName = $("#personname1").val();
  	var creditCustPaymentMode = $("#paymentMode1").val();
  	
  	var creditCustPaymentType = $("#paymentType1").val();

  	var creditCustBalanceAmt = $("#balanceAmount1").val();
  	var creditCustCreditAmt = $("#custPay").val();

  	if (creditCustomer != null && creditCustomer != "") {
  		if (creditCustBillNo != null && creditCustBillNo != "") {// &&
  																	// creditCustBillNo
  																	// != "none"
  			var onlyAlfabet = /^[a-zA-Z ]*$/;
  			if (creditCustAccName.match(onlyAlfabet) && creditCustAccName != ""
  					&& creditCustAccName != " " && creditCustAccName != null) {

  				var creditAmtRegExp = /^[0-9]+([.][0-9]+)?$/;
  				if (creditCustCreditAmt != null && creditCustCreditAmt != ""
  						&& creditCustCreditAmt != " ") {

  					if (creditCustCreditAmt.match(creditAmtRegExp)) {

  						if (Number(creditCustCreditAmt) <= Number(creditCustBalanceAmt)) {

  							if (Number(creditCustCreditAmt) > 0) {
  								if (creditCustPaymentMode != "selected1" && creditCustPaymentMode != "" && creditCustPaymentMode != " ") {
  									if (creditCustPaymentType != "selected" && creditCustPaymentType != "" && creditCustPaymentType != " ") {
  	  	  								customerPayment();
  	  	  							} else {
  	  	  								alert("Please select Payment Type");
  	  	  							}
  								} else {
  	  								alert("Please select Payment Mode");
  	  							}
  							} else {
  								alert("Credit Amount should be grater than 1");
  							}

  						} else {
  							alert("Credit Amount should not grater than Balance Amount");
  						}

  					} else {
  						alert("Please Enter valid Credit Amount");
  					}

  				} else {
  					alert("Please Enter Credit Amount");
  				}

  			} else {
  				alert("Enter Account Hoder Name without Any Number and Special symbols");
  			}
  		} else {
  			alert("please Select Bill Number");
  		}
  	} else {
  		alert("please Select Customer Name");
  	}

  }
  

  //Adding Credit Customer Payment
  function customerPayment(){
	//validation of balance amount with credit amount
	  var balanceAmount = $("#balanceAmount1").val();
		var credit = $("#credit1").val();
		if(credit >balanceAmount ){
			alert("Credit Amount Should Be Less Than Balance Amount")
		}
		
	  document.getElementById("btn2").disabled = true;
	  	
	  			var billNo = $('#billNo1').val();
	  			var totalAmount = $('#totalAmount1').val();
	  			var custPay = $('#custPay').val();
	  			var paymentMode = $('#paymentMode1').val();
	  			var chequeNum = $('#chequeNum1').val();
	  			var nameOnCheck = $('#nameOnCheck1').val();
	  			var bankName = $('#bankName1').val();
	  			var cardNum = $('#cardNum1').val();
	  			var accNum = $('#accNum1').val();
	  			var personname = $('#personname1').val();
	  			var paymentType = $('#paymentType1').val();
	  		
				
				     var input = document.getElementById('creditCustomer'),
				     list = document.getElementById('cust_drop'),
				     	i,fkRootCustId;
					 		for (i = 0; i < list.options.length; ++i) {
							     if (list.options[i].value === input.value) {
							    	 fkRootCustId = list.options[i].getAttribute('data-value');
							     }
					 		}
		
			var params= {};
			
			params ["customer"] = fkRootCustId;
			
			params ["billNo"] = billNo;
			params ["totalAmount"] = totalAmount;
			params ["custPay"] = custPay;
			params ["paymentMode"] = paymentMode;
			params ["chequeNum"] = chequeNum;
			params ["nameOnCheck"] = nameOnCheck;
			
			params ["bankName"] = bankName;
			params ["cardNum"] = cardNum;
			params ["accNum"] = accNum;
			params ["personname"] = personname;
			params ["paymentType"] = paymentType;
			
			params["methodName"] = "regCreditCustCashBook";
			
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
  
  /*
   * Name : Meghraj Menkudle Date : 21/05/2017 Method Name : EmployeeValidation()
   * Reason : Employee Details Validation in cashBook
   */

  function EmployeeValidation() {

  	var empName = $("#employee").val();
  	var empCreditAmt = $("#empPay").val();
  	var empAccName = $("#personName2").val();
  	var empPaymentReason = $("#reason2").val();
  	var empPaymentMode = $("#paymentMode2").val();
  	var paymentType2 = $("#paymentType2").val();
  	

  	if (empName != null && empName != "") {

  		var creditAmtRegExp = /^[0-9]+([.][0-9]+)?$/;
  		if (empCreditAmt != null && empCreditAmt != "" && empCreditAmt != " ") {
  			if (empCreditAmt.match(creditAmtRegExp)) {
  				if (Number(empCreditAmt) > 0) {
  					var onlyAlfabet = /^[a-zA-Z ]*$/;
  					if (empAccName.match(onlyAlfabet) && empAccName != "" && empAccName != " " && empAccName != null) {
  						if (empPaymentReason != "" && empPaymentReason != " " && empPaymentReason != null) {
  							if (empPaymentMode != "selected2" && empPaymentMode != "" && empPaymentMode != " ") {
  								if (paymentType2 != "selected2" && paymentType2 != "" && paymentType2 != " " && paymentType2 != "selected") {
  	  								emplyeePayment();
  	  							} else {
  	  								alert("Select Payment Type Credti or debit");
  	  							}
  							} else {
  								alert("Select Payment Mode");
  							}
  							
  						} else {
  							alert("Enter Payment Reason.");
  						}
  					} else {
  						alert("Enter Account Hoder Name without Any Number");
  					}
  				} else {
  					alert("Credit Amount should be grater than 1");
  				}
  			} else {
  				alert("Please Enter valid Credit Amount");
  			}
  		} else {
  			alert("Please Enter Credit Amount");
  		}
  	} else {
  		alert("Select Employee Name");
  	}
  }
  
  //Adding Employee Payment
  function emplyeePayment(){
		 
	  document.getElementById("btn3").disabled = true;

	  	    var empPay= $('#empPay').val();
			var personName= $('#personName2').val();
			var reason= $('#reason2').val();
			var paymentMode = $('#paymentMode2').val();
  			var chequeNum = $('#chequeNum2').val();
  			var nameOnCheck = $('#nameOnCheck2').val();
  			var bankName = $('#bankName2').val();
  			var cardNum = $('#cardNum2').val();
  			var accNum = $('#accNum2').val();
  			var paymentType = $('#paymentType2').val();

  			var input = document.getElementById('employee'),
		     list = document.getElementById('emp_drop'),
		     	i,fkRootempId;
			 		for (i = 0; i < list.options.length; ++i) {
					     if (list.options[i].value === input.value) {
					    	 fkRootempId = list.options[i].getAttribute('data-value');
					     }
			 		}
			 		
			 		
			 		var params= {};
					
					params ["employee"] = fkRootempId;
					params ["empPay"] = empPay;
					params ["personName"] = personName;
					params ["reason"] = reason;
					
					params ["paymentMode"] = paymentMode;
					params ["chequeNum"] = chequeNum;
					params ["nameOnCheck"] = nameOnCheck;
					params ["bankName"] = bankName;
					params ["cardNum"] = cardNum;
					params ["accNum"] = accNum;
					params ["paymentType"] = paymentType;
					
					params["methodName"] = "regEmpCashBook";
					
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
  
  function expensePaymentValidation() {

		var expenseName = $("#expenseName").val();
		var serviceProvider = $("#serviceProvider").val();
		var contactNumber = $("#contactNumber").val();
		var expCredit = $("#expCredit").val();
		var expDebit = $("#expDebit").val();
		var accountantName = $("#accountantName").val();
		
		if (expenseName != null && expenseName != "" && expenseName != " ") {
			if (serviceProvider != null && serviceProvider != ""
					&& serviceProvider != " ") {
				if (contactNumber != null && contactNumber != ""
						&& contactNumber != " ") {
					var phoneno = /^\d{10}$/;
					if (contactNumber.match(phoneno)) {
						if (accountantName != null && accountantName != ""
								&& accountantName != " ") {
							var onlyAlfabet = /^[a-zA-Z ]*$/;
							if (accountantName.match(onlyAlfabet)) {
								if (accountantName.match(onlyAlfabet)) {
									addExpense();
								} else {
									alert("Enter Accountant Name without Any Number and Special symbols");
								}
							} else {
								alert("Enter Accountant Name without Any Number and Special symbols");
							}
						} else {
							alert("Enter Accountant Name.");
						}

					} else {
						alert("Contact No Must be 10 digit.");
					}
				} else {
					alert("Enter Contact Number");
				}
			} else {
				alert("Enter Service Provider");
			}
		} else {
			alert("please Select Expenditure Name");
		}
	}

  
 
/********************Adding Expenditure Payment****************/

  
function addExpense(){
	
	  document.exp.btn4.disabled = true;
	  	
	  			var serviceProvider = $('#serviceProvider').val();
	  			var expCredit = $('#expCredit').val();
	  			var expDebit = $('#expDebit').val();
	  			var contactNumber = $('#contactNumber').val();
	  			var accountantName = $('#accountantName').val();
	  			
	  		
				
				 var input = document.getElementById('expenseName'),
				     list = document.getElementById('exp_drop'),
				     	i,fkRootexpId;
					 		for (i = 0; i < list.options.length; ++i) {
							     if (list.options[i].value === input.value) {
							    	 fkRootexpId = list.options[i].getAttribute('data-value');
							     }
					 		}
		 
					 	
		
			var params= {};
			
			params ["expenseName"] = fkRootexpId;
			params ["serviceProvider"] = serviceProvider;
			params ["expCredit"] = expCredit;
			params ["expDebit"] = expDebit;
			params ["contactNumber"] = contactNumber;
			params ["accountantName"] = accountantName;
			
			params["methodName"] = "regExpenseCashBook";
			
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
  
  
/*Following BillHelper() function is to get Bill no , total amount and balamce amount after selecting supplier in Supplier Payment*/
var bill = new BillHelper();
function BillHelper()

{
	
	
	this.getAllBills =getAllBills;
	this.getTotalAmtByBills=getTotalAmtByBills;
	this.getRemainingBalaneByBills=getRemainingBalaneByBills;
	
	function getAllBills()
	{
		
		 var input = document.getElementById('supplier'),
	     list = document.getElementById('sup_drop'),
	     i,supplier;
	
		for (i = 0; i < list.options.length; ++i) {
	     if (list.options[i].value === input.value) {
	    	 supplier = list.options[i].getAttribute('data-value');
	     }
	 }
		
	 var supplier = supplier;
		$("#billNo").empty();
		$("#billNo").append($("<option></option>").attr("value","").text("Select bill"));
		var params= {};
		params["supplier"]= supplier;
		params["methodName"] = "getAllBillBySuppliers";
		
		
		
		$.post('/SMT/jsp/utility/controller.jsp',params,function(data)
				{
			var jsonData = $.parseJSON(data);
			//var jsonData = jsonData.list;
			$.each(jsonData,function(i,v)
					{
				$("#billNo").append($("<option></option>").attr("value",i).text(v.billNo)); 
				
					});
				})

	}
	
	
	function getTotalAmtByBills()
	{
		 var input = document.getElementById('supplier'),
	     list = document.getElementById('sup_drop'),
	     i,supplier;
	
		for (i = 0; i < list.options.length; ++i) {
	     if (list.options[i].value === input.value) {
	    	 supplier = list.options[i].getAttribute('data-value');
	     }
	    }
		
		var billNo = $("#billNo").val();
		$("#totalAmount").empty();
		$("#totalAmount").append($("<input/>").attr("value","").text());
		var params= {};
		params["billNo"]= billNo;
		params["supplier"]= supplier;
		params["methodName"] = "getTotalAmtByBillNo";
		
		$.post('/SMT/jsp/utility/controller.jsp',params,function(data)
				{
			var jsonData = $.parseJSON(data);
			//var jsonData = jsonData.list;
			$.each(jsonData,function(i,v)
					{
				document.getElementById("totalAmount").value = v.totalAmt;
				
					});
				})
			
		
		getRemainingBalaneByBills()
	}
	
	
	
	function getRemainingBalaneByBills()
	{
		 var input = document.getElementById('supplier'),
	     list = document.getElementById('sup_drop'),
	     i,supplier;
	
		for (i = 0; i < list.options.length; ++i) {
	     if (list.options[i].value === input.value) {
	    	 supplier = list.options[i].getAttribute('data-value');
	     }
	    }
		
		
		var billNo = $("#billNo").val();
		var totalAmount = $("totalAmount").val();
		
		$("#balanceAmount").empty();
		$("#balanceAmount").append($("<input/>").attr("value","").text());
		
		var params= {};
		params["billNo"]= billNo;
		params["supplier"]= supplier;
		params["totalAmount"]= totalAmount;
		
		params["methodName"] = "getBalanceAmtByBillNo";
		
		
		
		$.post('/SMT/jsp/utility/controller.jsp',params,function(data)
				{
			var jsonData = $.parseJSON(data);
			//var jsonData = jsonData.list;
			
	
			$.each(jsonData,function(i,v)
					{
			
					document.getElementById("balanceAmount").value = v.balance;
					});
				})
	}
	
	
}



function getBillByCustomer(){
	 var input = document.getElementById('creditCustomer'),
    list = document.getElementById('cust_drop'),
    i,creditCustomer;

	for (i = 0; i < list.options.length; ++i) {
    if (list.options[i].value === input.value) {
   	 creditCustomer = list.options[i].getAttribute('data-value');
    }
}
	
	
	
var creditCustomer = creditCustomer;
	$("#billNo1").empty();
	$("#billNo1").append($("<option></option>").attr("value","").text("Select bill"));
	var params= {};
	params["creditCustomer"]= creditCustomer;
	params["methodName"] = "getAllBillByCustomer";
	
	
	
	
	$.post('/SMT/jsp/utility/controller.jsp',params,function(data)
			{
		var jsonData = $.parseJSON(data);
		//var jsonData = jsonData.list;
		$.each(jsonData,function(i,v)
				{
			$("#billNo1").append($("<option></option>").attr("value",i).text(v.billNo)); 
			
				});
			})
}

function getBillByCustomer1(){
	 var input = document.getElementById('creditCustomer'),
   list = document.getElementById('cust_drop'),
   i,creditCustomer;

	for (i = 0; i < list.options.length; ++i) {
   if (list.options[i].value === input.value) {
  	 creditCustomer = list.options[i].getAttribute('data-value');
   }
}
	
	
	
var creditCustomer = creditCustomer;
	$("#billNo1").empty();
	$("#billNo1").append($("<option></option>").attr("value","").text("Select bill"));
	var params= {};
	params["creditCustomer"]= creditCustomer;
	params["methodName"] = "getAllBillByCustomer1";
	
	
	
	
	$.post('/SMT/jsp/utility/controller.jsp',params,function(data)
			{
		var jsonData = $.parseJSON(data);
		//var jsonData = jsonData.list;
		$.each(jsonData,function(i,v)
				{
			$("#billNo1").append($("<option></option>").attr("value",i).text(v.billNo)); 
			
				});
			})
}


function getBillByCustomerForReport(){
	 var input = document.getElementById('creditCustomer'),
   list = document.getElementById('cust_drop'),
   i,creditCustomer;

	for (i = 0; i < list.options.length; ++i) {
   if (list.options[i].value === input.value) {
  	 creditCustomer = list.options[i].getAttribute('data-value');
   }
}
	
	 var input = document.getElementById('fk_cat_id0'),
	    list = document.getElementById('cat_drop0'),
	    i,cat;

		for (i = 0; i < list.options.length; ++i) {
	    if (list.options[i].value === input.value) {
	    	cat = list.options[i].getAttribute('data-value');
	    }
	}
	
var creditCustomer = creditCustomer;
	$("#billNo1").empty();
	$("#billNo1").append($("<option></option>").attr("value","").text("Select bill"));
	var params= {};
	
	params["methodName"] = "getAllBillByCustomer";
	
	params["creditCustomer"]= creditCustomer;
	params["cat"]= cat;
	
	$.post('/SMT/jsp/utility/controller.jsp',params,function(data)
			{
		var jsonData = $.parseJSON(data);
		//var jsonData = jsonData.list;
		$.each(jsonData,function(i,v)
				{
			$("#billNo1").append($("<option></option>").attr("value",i).text(v.billNo)); 
			
				});
			})
}

  

 
 function getCreditCustomerBillDetails(){
	 
	 this.getBillByCustomer = getBillByCustomer;
	 this.getTotalAmountByBill = getTotalAmountByBill;
	 
	 
	
	
}
	
	 
 function getTotalAmountByBill(){
	 var input = document.getElementById('creditCustomer'),
	    list = document.getElementById('cust_drop'),
	    i,creditCustomer;

		for (i = 0; i < list.options.length; ++i) {
	    if (list.options[i].value === input.value) {
	   	 creditCustomer = list.options[i].getAttribute('data-value');
	    }
	}
			var billNo1 = $("#billNo1").val();
			
			$("#totalAmount1").empty();
			$("#totalAmount1").append($("<input/>").attr("value","").text());
			
			var params= {};
			
			params["methodName"] = "getTotalAmtByBillNoForCreditCustomer";
			
			params["billNo1"]= billNo1;
			
			params["creditCustomer"]= creditCustomer;
			
			$.post('/SMT/jsp/utility/controller.jsp',params,function(data)
					{
				var jsonData = $.parseJSON(data);
				//var jsonData = jsonData.list;
				$.each(jsonData,function(i,v)
						{
					document.getElementById("totalAmount1").value = v.totalAmount;
					
						});
					})
					
				balaceAmountbyBillNoForCreditPayment();
}
	 
	
	 function balaceAmountbyBillNoForCreditPayment(){
		 var input = document.getElementById('creditCustomer'),
		    list = document.getElementById('cust_drop'),
		    i,creditCustomer;

			for (i = 0; i < list.options.length; ++i) {
		    if (list.options[i].value === input.value) {
		   	 creditCustomer = list.options[i].getAttribute('data-value');
		    }
		}
			
			
					
					var billNo1 = $("#billNo1").val();
					$("#balanceAmount1").empty();
					$("#balanceAmount1").append($("<input/>").attr("value","").text());
					
					var params= {};
					
					params["methodName"] = "getBalanceAmtByBillNoForCreditCustomer";
					
					params["billNo1"]= billNo1;
				
					params["creditCustomer"]= creditCustomer;
					
					$.post('/SMT/jsp/utility/controller.jsp',params,function(data)
							{
						var jsonData = $.parseJSON(data);
						//var jsonData = jsonData.list;
						
				
						$.each(jsonData,function(i,v)
								{
						
								document.getElementById("balanceAmount1").value = v.balance;
								});
							})
				
		
	}
 
 var custBill = new getCreditCustomerBillDetails();
 
 
 
 /*************** For Reports *********************/
 
 /**************** Supplier Payment *************/
/************ for single date **************/
 function supplierReportForSingleDate(){

		
		var params= {};
		var fDate = $("#fDate11").val();
		
		
		params["fDate"]= fDate;
		
		params["methodName"] = "getSupplierPaymentDetailsForSingleDate";

		$.post('/SMT/jsp/utility/controller.jsp',params,function(data)
				{
			//To clear table
			$('#supplierSingleDatetable').dataTable().fnClearTable();
			
			var jsonData = $.parseJSON(data);
			var catmap = jsonData.list;
			
			
			
			$(document).ready(function() {
			 var total =   $('#supplierSingleDatetable').DataTable( {
				 
				 dom: 'Bfrtip',
		         buttons: [
		             'copy', 'csv', 'excel', 'pdf', 'print'
		         ],
		    	
				 
				 fnRowCallback : function(nRow, aData, iDisplayIndex){
		                $("th:first", nRow).html(iDisplayIndex +1);
		               return nRow;
		            },
				
				 "footerCallback": function ( row, data, start, end, display ) {
			            var api = this.api(), data;
			 
			            // Remove the formatting to get integer data for summation
			            var intVal = function ( i ) {
			                return typeof i === 'string' ?
			                    i.replace(/[\$,]/g, '')*1 :
			                    typeof i === 'number' ?
			                        i : 0;
			            };
			 
			            // Total over all pages
			            /* total = api
			                .column( 6 )
			                .data()
			                .reduce( function (a, b) {
			                    return intVal(a) + intVal(b);
			                }, 0 ); 
			 			console.log(total); */
			            // Total over this page
			            pageTotal = api
			                .column( 5 )
			                .data()
			                .reduce( function (a, b) {
			                    return intVal(a) + intVal(b);
			                }, 0 );
			 
			         
			            // Update footer
			            $( api.column( 5 ).footer() ).html(
			              //  'Rs'+' '+pageTotal.toFixed(2)
			            		 str = pageTotal.toFixed(0)
			            );
			            console.log( pageTotal);
			            
			            // Total over this page       
			         		         
			            
			            
			        },
			    	
			    	
			    	destroy: true,
			        searching: false,
			        
			      
			columns: [
						{"data": "supplierName", "width": "5%" ,"defaultContent": ""},
						{"data": "billNo", "width": "5%" ,"defaultContent": ""},
						{"data": "paymentType" , "width": "5%" ,"defaultContent": ""},
						{"data": "paymentMode" , "width": "5%" ,"defaultContent": ""},
						{"data": "totalAmount" , "width": "5%" ,"defaultContent": ""},
						{"data": "paymentAmount" , "width": "5%" ,"defaultContent": ""},
						{"data": "balanceAmount" , "width": "5%" ,"defaultContent": ""},
						{"data": "accountantName" , "width": "5%" ,"defaultContent": ""},
			        ],
			      
			        
			    } );
			} );
			
		var mydata = catmap;
		$('#supplierSingleDatetable').dataTable().fnAddData(mydata);
		
			}
		);
 }
 
/********* Between two dates for supplier ***************/
 function getSupplierDetailsBetweenTwoDates(){

		var params= {};
		var startDate = $("#fisDate1").val();
		var endDate = $("#endDate1").val();


		params["fisDate"]= startDate;
		params["endDate"]= endDate;
		params["methodName"] = "getSupplierPaymentReportBetweenTwoDates";

		$.post('/SMT/jsp/utility/controller.jsp',params,function(data)
				{
			//To clear table
			$('#supplierBetweenTwoDatestable').dataTable().fnClearTable();
			
			var jsonData = $.parseJSON(data);
			var catmap = jsonData.list;
		
				
				$(document).ready(function() {
					
					
				    $('#supplierBetweenTwoDatestable').DataTable( {
				    	
				    	dom: 'Bfrtip',
				         buttons: [
				             'copy', 'csv', 'excel', 'pdf', 'print'
				         ],
				    	
				    	fnRowCallback : function(nRow, aData, iDisplayIndex){
			                $("th:first", nRow).html(iDisplayIndex +1);
			               return nRow;
			            },
					
					 "footerCallback": function ( row, data, start, end, display ) {
				            var api = this.api(), data;
				 
				            // Remove the formatting to get integer data for summation
				            var intVal = function ( i ) {
				                return typeof i === 'string' ?
				                    i.replace(/[\$,]/g, '')*1 :
				                    typeof i === 'number' ?
				                        i : 0;
				            };
				 
				            // Total over all pages
				            /* total = api
				                .column( 6 )
				                .data()
				                .reduce( function (a, b) {
				                    return intVal(a) + intVal(b);
				                }, 0 ); 
				 			console.log(total); */
				            // Total over this page
				            pageTotal = api
				                .column( 5 )
				                .data()
				                .reduce( function (a, b) {
				                    return intVal(a) + intVal(b);
				                }, 0 );
				 
				            // Update footer
				            $( api.column( 5 ).footer() ).html(
				            		
				              'Rs'+' '+parseFloat(pageTotal).toFixed(2)
				               
				            );
				            console.log( pageTotal);
				            
				           
				            
				            
				        },
				    	
				    	destroy: true,
				        searching: false,
				      
				columns: [
				          
							{"data": "date" , "width": "5%" ,"defaultContent": ""},
							{"data": "supplierName", "width": "5%" ,"defaultContent": ""},
							{"data": "billNo", "width": "5%" ,"defaultContent": ""},
							{"data": "paymentType" , "width": "5%" ,"defaultContent": ""},
							{"data": "paymentMode" , "width": "5%" ,"defaultContent": ""},
							{"data": "totalAmount" , "width": "5%" ,"defaultContent": ""},
							{"data": "paymentAmount" , "width": "5%" ,"defaultContent": ""},
							{"data": "balanceAmount" , "width": "5%" ,"defaultContent": ""},
							{"data": "accountantName" , "width": "5%" ,"defaultContent": ""},
				           
				        ]
				    } );
				} );
				
			var mydata = catmap;
			$('#supplierBetweenTwoDatestable').dataTable().fnAddData(mydata);
				
				}).error(function(jqXHR, textStatus, errorThrown){
					if(textStatus==="timeout") {
						$(loaderObj).hide();
						$(loaderObj).find('#errorDiv').show();
					
					}
				});
	
 }
 
 
 
 /*Bill number Wise supplier payment report*/
 function getBillWiseReport(){
	
	 var input = document.getElementById('supplier'),
     list = document.getElementById('sup_drop'),
     	i,supplier;
	 		for (i = 0; i < list.options.length; ++i) {
			     if (list.options[i].value === input.value) {
			    	 supplier = list.options[i].getAttribute('data-value');
			     }
	 		}
		var fkSupplierId = supplier;

		
		var params= {};
		var billNo = $("#billNo").val();
		
		params["fkSupplierId"]= fkSupplierId;
		params["billNo"]= billNo;
		
		params["methodName"] = "getSupplierPaymentDetailsAsPerBillNumber";

		$.post('/SMT/jsp/utility/controller.jsp',params,function(data)
				{

			//To clear table
			$('#supplierBillWiseData').dataTable().fnClearTable();
			
			var jsonData = $.parseJSON(data);
			var catmap = jsonData.list;
			
			
			
			$(document).ready(function() {
			 var total =   $('#supplierBillWiseData').DataTable( {
				 
				 dom: 'Bfrtip',
		         buttons: [
		             'copy', 'csv', 'excel', 'pdf', 'print'
		         ],
		    	
				 
				 fnRowCallback : function(nRow, aData, iDisplayIndex){
		                $("th:first", nRow).html(iDisplayIndex +1);
		               return nRow;
		            },
				
				 "footerCallback": function ( row, data, start, end, display ) {
			            var api = this.api(), data;
			 
			            // Remove the formatting to get integer data for summation
			            var intVal = function ( i ) {
			                return typeof i === 'string' ?
			                    i.replace(/[\$,]/g, '')*1 :
			                    typeof i === 'number' ?
			                        i : 0;
			            };
			 
			            // Total over all pages
			            /* total = api
			                .column( 6 )
			                .data()
			                .reduce( function (a, b) {
			                    return intVal(a) + intVal(b);
			                }, 0 ); 
			 			console.log(total); */
			            // Total over this page
			            pageTotal = api
			                .column( 5 )
			                .data()
			                .reduce( function (a, b) {
			                    return intVal(a) + intVal(b);
			                }, 0 );
			 
			         
			            // Update footer
			            $( api.column( 5 ).footer() ).html(
			              //  'Rs'+' '+pageTotal.toFixed(2)
			            		 str = pageTotal.toFixed(0)
			            );
			            console.log( pageTotal);
			            
			            // Total over this page       
			         		         
			            
			            
			        },
			    	
			    	
			    	destroy: true,
			        searching: false,
			        
			      
			columns: [
						{"data": "date" , "width": "5%" ,"defaultContent": ""},
						{"data": "supplierName", "width": "5%" ,"defaultContent": ""},
						{"data": "billNo", "width": "5%" ,"defaultContent": ""},
						{"data": "paymentType" , "width": "5%" ,"defaultContent": ""},
						{"data": "paymentMode" , "width": "5%" ,"defaultContent": ""},
						{"data": "totalAmount" , "width": "5%" ,"defaultContent": ""},
						{"data": "paymentAmount" , "width": "5%" ,"defaultContent": ""},
						{"data": "balanceAmount" , "width": "5%" ,"defaultContent": ""},
						{"data": "accountantName" , "width": "5%" ,"defaultContent": ""},
			        ],
			      
			        
			    } );
			} );
			
		var mydata = catmap;
		$('#supplierBillWiseData').dataTable().fnAddData(mydata);
		
			
			
			
			
				}
		);

	 
 }
 
 /*Supplier Name wise*/
 function getSupNameWiseReport(){
		
	 var input = document.getElementById('supplier7'),
     list = document.getElementById('sup_drop7'),
     	i,supplier;
	 		for (i = 0; i < list.options.length; ++i) {
			     if (list.options[i].value === input.value) {
			    	 supplier = list.options[i].getAttribute('data-value');
			     }
	 		}
		var fkSupplierId = supplier;

		
		var params= {};
	
		
		params["fkSupplierId"]= fkSupplierId;
		
		
		params["methodName"] = "getSupplierPaymentDetailsAsPerName";

		$.post('/SMT/jsp/utility/controller.jsp',params,function(data)
				{

			//To clear table
			$('#supplierNameWiseTable').dataTable().fnClearTable();
			
			var jsonData = $.parseJSON(data);
			var catmap = jsonData.list;
			
			
			
			$(document).ready(function() {
			 var total =   $('#supplierNameWiseTable').DataTable( {
				 
				 dom: 'Bfrtip',
		         buttons: [
		             'copy', 'csv', 'excel', 'pdf', 'print'
		         ],
		    	
				 
				 fnRowCallback : function(nRow, aData, iDisplayIndex){
		                $("th:first", nRow).html(iDisplayIndex +1);
		               return nRow;
		            },
				
				 "footerCallback": function ( row, data, start, end, display ) {
			            var api = this.api(), data;
			 
			            // Remove the formatting to get integer data for summation
			            var intVal = function ( i ) {
			                return typeof i === 'string' ?
			                    i.replace(/[\$,]/g, '')*1 :
			                    typeof i === 'number' ?
			                        i : 0;
			            };
			 
			            // Total over all pages
			            /* total = api
			                .column( 6 )
			                .data()
			                .reduce( function (a, b) {
			                    return intVal(a) + intVal(b);
			                }, 0 ); 
			 			console.log(total); */
			            
			         // Total over this page
			            pageTotal = api
			                .column( 5 )
			                .data()
			                .reduce( function (a, b) {
			                    return intVal(a) + intVal(b);
			                }, 0 );
			 
			         
			            // Update footer
			            $( api.column( 5 ).footer() ).html(
			              //  'Rs'+' '+pageTotal.toFixed(2)
			            		 str = pageTotal.toFixed(0)
			            );
			            console.log( pageTotal);
			            // Total over this page       
			         		         
			            
			            
			        },
			    	
			    	
			    	destroy: true,
			        searching: false,
			        
			      
			columns: [
			          
                    {"data": "date" , "width": "5%" ,"defaultContent": ""},
					{"data": "supplierName", "width": "5%" ,"defaultContent": ""},
					{"data": "billNo", "width": "5%" ,"defaultContent": ""},
					{"data": "paymentType" , "width": "5%" ,"defaultContent": ""},
					{"data": "paymentMode" , "width": "5%" ,"defaultContent": ""},
					{"data": "totalAmount" , "width": "5%" ,"defaultContent": ""},
					{"data": "paymentAmount" , "width": "5%" ,"defaultContent": ""},
					{"data": "balanceAmount" , "width": "5%" ,"defaultContent": ""},
					{"data": "accountantName" , "width": "5%" ,"defaultContent": ""},
					
			        ],
			      
			        
			    } );
			} );
			
		var mydata = catmap;
		$('#supplierNameWiseTable').dataTable().fnAddData(mydata);
		
			
			
			
			
				}
		);

	 
 
 }

 
 /********** Credit Customer Reports *********************/
 
/*++++++ Customer Name wise +++++*/
 function getCreditCustomerReportNameWise(){

		
	 var input = document.getElementById('creditCustomer5'),
    list = document.getElementById('cust_drop5'),
    	i,customer;
	 		for (i = 0; i < list.options.length; ++i) {
			     if (list.options[i].value === input.value) {
			    	 customer = list.options[i].getAttribute('data-value');
			     }
	 		}
		var fkCustomerId = customer;

		var params= {};
		
		params["fkCustomerId"]= fkCustomerId;
		
		params["methodName"] = "getCustomerReportByName";

		$.post('/SMT/jsp/utility/controller.jsp',params,function(data)
				{
			

			//To clear table
			$('#customerNameWiseData2').dataTable().fnClearTable();
			
			var jsonData = $.parseJSON(data);
			var catmap = jsonData.list;
			
			
			
			$(document).ready(function() {
			 var total =   $('#customerNameWiseData2').DataTable( {
				 
				 dom: 'Bfrtip',
		         buttons: [
		             'copy', 'csv', 'excel', 'pdf', 'print'
		         ],
		    	
				 
				 fnRowCallback : function(nRow, aData, iDisplayIndex){
		                $("th:first", nRow).html(iDisplayIndex +1);
		               return nRow;
		            },
				
				 "footerCallback": function ( row, data, start, end, display ) {
			            var api = this.api(), data;
			 
			            // Remove the formatting to get integer data for summation
			            var intVal = function ( i ) {
			                return typeof i === 'string' ?
			                    i.replace(/[\$,]/g, '')*1 :
			                    typeof i === 'number' ?
			                        i : 0;
			            };
			 
			            // Total over all pages
			            /* total = api
			                .column( 6 )
			                .data()
			                .reduce( function (a, b) {
			                    return intVal(a) + intVal(b);
			                }, 0 ); 
			 			console.log(total); */
			            // Total over this page
			            pageTotal = api
			                .column( 6 )
			                .data()
			                .reduce( function (a, b) {
			                    return intVal(a) + intVal(b);
			                }, 0 );
			 
			         
			            // Update footer
			            $( api.column( 6 ).footer() ).html(
			              //  'Rs'+' '+pageTotal.toFixed(2)
			            		 str = pageTotal.toFixed(0)
			            );
			            console.log( pageTotal);
			            
			            // Total over this page       
			         		         
			            
			            
			        },
			    	
			    	
			    	destroy: true,
			        searching: false,
			        
			      
			columns: [
							{"data": "date" , "width": "5%" ,"defaultContent": ""},
							{"data": "creditCustomerFirstName", "width": "5%" ,"defaultContent": ""},
							{"data": "creditCustomerLastName", "width": "5%" ,"defaultContent": ""},
							{"data": "billNo" , "width": "5%" ,"defaultContent": ""},
							{"data": "PaymentType" , "width": "5%" ,"defaultContent": ""},
							{"data": "paymentMode" , "width": "5%" ,"defaultContent": ""},
							{"data": "totalAmount" , "width": "5%" ,"defaultContent": ""},
							{"data": "paymentAmount" , "width": "5%" ,"defaultContent": ""},
							{"data": "balanceAmount" , "width": "5%" ,"defaultContent": ""},
			        ],
			      
			        
			    } );
			} );
			
		var mydata = catmap;
		$('#customerNameWiseData2').dataTable().fnAddData(mydata);
		
			
			
			
				}
		);

	 

}
 
 
/* Bill Wise */
 function getBillWiseCreditReport(){

	 var input = document.getElementById('creditCustomer'),
     list = document.getElementById('cust_drop'),
     	i,creditCustomer;
	 		for (i = 0; i < list.options.length; ++i) {
			     if (list.options[i].value === input.value) {
			    	 creditCustomer = list.options[i].getAttribute('data-value');
			     }
	 		}
	 		
	 	/* var input = document.getElementById('fk_cat_id0'),
	 	    list = document.getElementById('cat_drop0'),
	 	    i,cat;

	 		for (i = 0; i < list.options.length; ++i) {
	 	    if (list.options[i].value === input.value) {
	 	    	cat = list.options[i].getAttribute('data-value');
	 	    }
	 	}*/
	 		
		var fkCustomerId = creditCustomer;
		var params= {};
		var billNo = $("#billNo1").val();
		
		
		params["billNo"]= billNo;
		params["fkCustomerId"]= fkCustomerId;
		//params["cat"]= cat;
		params["methodName"] = "getCreditCustPaymentDetailsForBillNo";

		$.post('/SMT/jsp/utility/controller.jsp',params,function(data)
				{
			//To clear table
			$('#customerNameWiseData').dataTable().fnClearTable();
			
			var jsonData = $.parseJSON(data);
			var catmap = jsonData.list;
			
			
			
			$(document).ready(function() {
			 var total =   $('#customerNameWiseData').DataTable( {
				 
				 dom: 'Bfrtip',
		         buttons: [
		             'copy', 'csv', 'excel', 'pdf', 'print'
		         ],
		    	
				 
				 fnRowCallback : function(nRow, aData, iDisplayIndex){
		                $("th:first", nRow).html(iDisplayIndex +1);
		               return nRow;
		            },
				
				 "footerCallback": function ( row, data, start, end, display ) {
			            var api = this.api(), data;
			 
			            // Remove the formatting to get integer data for summation
			            var intVal = function ( i ) {
			                return typeof i === 'string' ?
			                    i.replace(/[\$,]/g, '')*1 :
			                    typeof i === 'number' ?
			                        i : 0;
			            };
			 
			            // Total over all pages
			            /* total = api
			                .column( 6 )
			                .data()
			                .reduce( function (a, b) {
			                    return intVal(a) + intVal(b);
			                }, 0 ); 
			 			console.log(total); */
			            // Total over this page
			            pageTotal = api
			                .column( 6 )
			                .data()
			                .reduce( function (a, b) {
			                    return intVal(a) + intVal(b);
			                }, 0 );
			 
			         
			            // Update footer
			            $( api.column( 6 ).footer() ).html(
			              //  'Rs'+' '+pageTotal.toFixed(2)
			            		 str = pageTotal.toFixed(0)
			            );
			            console.log( pageTotal);
			            
			            // Total over this page       
			         		         
			            
			            
			        },
			    	
			    	
			    	destroy: true,
			        searching: false,
			        
			      
			columns: [
	                    {"data": "date" , "width": "5%" ,"defaultContent": ""},
						{"data": "creditCustomerFirstName", "width": "5%" ,"defaultContent": ""},
						{"data": "creditCustomerLastName", "width": "5%" ,"defaultContent": ""},
						{"data": "billNo" , "width": "5%" ,"defaultContent": ""},
						{"data": "PaymentType" , "width": "5%" ,"defaultContent": ""},
						{"data": "paymentMode" , "width": "5%" ,"defaultContent": ""},
						{"data": "totalAmount" , "width": "5%" ,"defaultContent": ""},
						{"data": "paymentAmount" , "width": "5%" ,"defaultContent": ""},
						{"data": "balanceAmount" , "width": "5%" ,"defaultContent": ""},
					
			        ],
			      
			        
			    } );
			} );
			
		var mydata = catmap;
		$('#customerNameWiseData').dataTable().fnAddData(mydata);
		
			}
		
		
		);
	

 }
 
 
/********** for single date *********/
 function creditCustReportForSingleDate(){
		
		var params= {};
		var fDate = $("#fDate1").val();
		
		
		params["fDate"]= fDate;
		
		params["methodName"] = "getCreditCustPaymentDetailsForSingleDate";

		$.post('/SMT/jsp/utility/controller.jsp',params,function(data)
				{
			//To clear table
			$('#customerSingleDatetable').dataTable().fnClearTable();
			
			var jsonData = $.parseJSON(data);
			var catmap = jsonData.list;
			
			
			
			$(document).ready(function() {
			 var total =   $('#customerSingleDatetable').DataTable( {
				 
				 dom: 'Bfrtip',
		         buttons: [
		             'copy', 'csv', 'excel', 'pdf', 'print'
		         ],
		    	
				 
				 fnRowCallback : function(nRow, aData, iDisplayIndex){
		                $("th:first", nRow).html(iDisplayIndex +1);
		               return nRow;
		            },
				
				 "footerCallback": function ( row, data, start, end, display ) {
			            var api = this.api(), data;
			 
			            // Remove the formatting to get integer data for summation
			            var intVal = function ( i ) {
			                return typeof i === 'string' ?
			                    i.replace(/[\$,]/g, '')*1 :
			                    typeof i === 'number' ?
			                        i : 0;
			            };
			 
			            // Total over all pages
			            /* total = api
			                .column( 6 )
			                .data()
			                .reduce( function (a, b) {
			                    return intVal(a) + intVal(b);
			                }, 0 ); 
			 			console.log(total); */
			            // Total over this page
			            pageTotal = api
			                .column( 6 )
			                .data()
			                .reduce( function (a, b) {
			                    return intVal(a) + intVal(b);
			                }, 0 );
			 
			         
			            // Update footer
			            $( api.column( 6 ).footer() ).html(
			              //  'Rs'+' '+pageTotal.toFixed(2)
			            		 str = pageTotal.toFixed(0)
			            );
			            console.log( pageTotal);
			            
			            // Total over this page       
			         		         
			            
			            
			        },
			    	
			    	
			    	destroy: true,
			        searching: false,
			        
			      
			columns: [
							{"data": "creditCustomerFirstName", "width": "5%" ,"defaultContent": ""},
							{"data": "creditCustomerLastName", "width": "5%" ,"defaultContent": ""},
							{"data": "billNo" , "width": "5%" ,"defaultContent": ""},
							{"data": "PaymentType" , "width": "5%" ,"defaultContent": ""},
							{"data": "paymentMode" , "width": "5%" ,"defaultContent": ""},
							{"data": "totalAmount" , "width": "5%" ,"defaultContent": ""},
							{"data": "paymentAmount" , "width": "5%" ,"defaultContent": ""},
							{"data": "balanceAmount" , "width": "5%" ,"defaultContent": ""},
			        ],
			      
			        
			    } );
			} );
			
		var mydata = catmap;
		$('#customerSingleDatetable').dataTable().fnAddData(mydata);
		
			}
		
		
		);
	
 }
 
 
/***************** Between two dates *************/
 
 function getCreditCustomerDetailsBetweenTwoDates()
	{
		var params= {};
		var startDate = $("#fisDate").val();
		var endDate = $("#endDate").val();


		params["fisDate"]= startDate;
		params["endDate"]= endDate;
		params["methodName"] = "getCustomerReportBetweenTwoDates";

		$.post('/SMT/jsp/utility/controller.jsp',params,function(data)
				{
			//To clear table
			$('#customerBetweenTwoDates').dataTable().fnClearTable();
			
			var jsonData = $.parseJSON(data);
			var catmap = jsonData.list;
		
				
				$(document).ready(function() {
				    $('#customerBetweenTwoDates').DataTable( {
				    	
				    	dom: 'Bfrtip',
				         buttons: [
				             'copy', 'csv', 'excel', 'pdf', 'print'
				         ],
				    	
				    	fnRowCallback : function(nRow, aData, iDisplayIndex){
			                $("th:first", nRow).html(iDisplayIndex +1);
			               return nRow;
			            },
					
					 "footerCallback": function ( row, data, start, end, display ) {
				            var api = this.api(), data;
				 
				            // Remove the formatting to get integer data for summation
				            var intVal = function ( i ) {
				                return typeof i === 'string' ?
				                    i.replace(/[\$,]/g, '')*1 :
				                    typeof i === 'number' ?
				                        i : 0;
				            };
				 
				            // Total over all pages
				            /* total = api
				                .column( 6 )
				                .data()
				                .reduce( function (a, b) {
				                    return intVal(a) + intVal(b);
				                }, 0 ); 
				 			console.log(total); */
				            // Total over this page
				            pageTotal = api
				                .column( 7 )
				                .data()
				                .reduce( function (a, b) {
				                    return intVal(a) + intVal(b);
				                }, 0 );
				 
				            // Update footer
				            $( api.column( 7 ).footer() ).html(
				            		
				              'Rs'+' '+parseFloat(pageTotal).toFixed(2)
				               
				            );
				            console.log( pageTotal);
				            
				           
				            
				            
				        },
				    	
				    	destroy: true,
				        searching: false,
				      
				columns: [
								{"data": "date" , "width": "5%" ,"defaultContent": ""},
								{"data": "creditCustomerFirstName", "width": "5%" ,"defaultContent": ""},
								{"data": "creditCustomerLastName", "width": "5%" ,"defaultContent": ""},
								{"data": "billNo" , "width": "5%" ,"defaultContent": ""},
								{"data": "PaymentType" , "width": "5%" ,"defaultContent": ""},
								{"data": "paymentMode" , "width": "5%" ,"defaultContent": ""},
								{"data": "totalAmount" , "width": "5%" ,"defaultContent": ""},
								{"data": "paymentAmount" , "width": "5%" ,"defaultContent": ""},
								{"data": "balanceAmount" , "width": "5%" ,"defaultContent": ""},
				           
				        ]
				    } );
				} );
				
			var mydata = catmap;
			$('#customerBetweenTwoDates').dataTable().fnAddData(mydata);
				
				}).error(function(jqXHR, textStatus, errorThrown){
					if(textStatus==="timeout") {
						$(loaderObj).hide();
						$(loaderObj).find('#errorDiv').show();
					
					}
				});
	}
 
/************ Employee Payment Report **************/
 /*********** For single Date *********/
 
function employeePaymentReportForSingleDate(){

	
	var params= {};
	var fDate = $("#fDate2").val();
	
	
	params["fDate"]= fDate;
	
	params["methodName"] = "getEmpPaymentDetailsForSingleDate";

	$.post('/SMT/jsp/utility/controller.jsp',params,function(data)
			{
		//To clear table
		$('#employeeSingleDatetable').dataTable().fnClearTable();
		
		var jsonData = $.parseJSON(data);
		var catmap = jsonData.list;
		
		
		
		$(document).ready(function() {
		 var total =   $('#employeeSingleDatetable').DataTable( {
			 
			 dom: 'Bfrtip',
	         buttons: [
	             'copy', 'csv', 'excel', 'pdf', 'print'
	         ],
	    	
			 
			 fnRowCallback : function(nRow, aData, iDisplayIndex){
	                $("th:first", nRow).html(iDisplayIndex +1);
	               return nRow;
	            },
			
			 "footerCallback": function ( row, data, start, end, display ) {
		            var api = this.api(), data;
		 
		            // Remove the formatting to get integer data for summation
		            var intVal = function ( i ) {
		                return typeof i === 'string' ?
		                    i.replace(/[\$,]/g, '')*1 :
		                    typeof i === 'number' ?
		                        i : 0;
		            };
		 
		            // Total over all pages
		            /* total = api
		                .column( 6 )
		                .data()
		                .reduce( function (a, b) {
		                    return intVal(a) + intVal(b);
		                }, 0 ); 
		 			console.log(total); */
		            // Total over this page
		            pageTotal = api
		                .column( 3 )
		                .data()
		                .reduce( function (a, b) {
		                    return intVal(a) + intVal(b);
		                }, 0 );
		 
		         
		            // Update footer
		            $( api.column( 3 ).footer() ).html(
		              //  'Rs'+' '+pageTotal.toFixed(2)
		            		 str = pageTotal.toFixed(0)
		            );
		            console.log( pageTotal);
		            
		            // Total over this page       
		         		         
		            
		            
		        },
		    	
		    	
		    	destroy: true,
		        searching: false,
		        
		      
		columns: [
					{"data": "insertDate" , "width": "5%" ,"defaultContent": ""},
					{"data": "firstName", "width": "5%" ,"defaultContent": ""},
					{"data": "lastName", "width": "5%" ,"defaultContent": ""},
					{"data": "paymentType", "width": "5%" ,"defaultContent": ""},
					{"data": "paymentMode" , "width": "5%" ,"defaultContent": ""},
					{"data": "paymentAmount" , "width": "5%" ,"defaultContent": ""},
					{"data": "accountantName" , "width": "5%" ,"defaultContent": ""},
					{"data": "reason" , "width": "5%" ,"defaultContent": ""},
		        ],
		      
		        
		    } );
		} );
		
	var mydata = catmap;
	$('#employeeSingleDatetable').dataTable().fnAddData(mydata);
	
		}
	
	
	);

}

/****** Between Two dates **********/

function getEmpPaymentDetailsBetTwoDays(){
	

	var params= {};
	var startDate = $("#fisDate2").val();
	var endDate = $("#endDate2").val();
	
	var input = document.getElementById('employee'),
    list = document.getElementById('emp_drop'),
    	i,fkRootempId;
	for (i = 0; i < list.options.length; ++i) {
			    if (list.options[i].value === input.value) {
			    	 fkRootempId = list.options[i].getAttribute('data-value');
			  }
	 }

	params["fkRootempId"]= fkRootempId;
	params["fisDate"]= startDate;
	params["endDate"]= endDate;
	params["methodName"] = "getEmployeeReportBetweenTwoDates";

	$.post('/SMT/jsp/utility/controller.jsp',params,function(data)
			{
		//To clear table
		$('#empBetweenTwoDates').dataTable().fnClearTable();
		
		var jsonData = $.parseJSON(data);
		var catmap = jsonData.list;
	
			
			$(document).ready(function() {
			    $('#empBetweenTwoDates').DataTable( {
			    	
			    	dom: 'Bfrtip',
			         buttons: [
			             'copy', 'csv', 'excel', 'pdf', 'print'
			         ],
			    	
			    	fnRowCallback : function(nRow, aData, iDisplayIndex){
		                $("th:first", nRow).html(iDisplayIndex +1);
		               return nRow;
		            },
				
				 "footerCallback": function ( row, data, start, end, display ) {
			            var api = this.api(), data;
			 
			            // Remove the formatting to get integer data for summation
			            var intVal = function ( i ) {
			                return typeof i === 'string' ?
			                    i.replace(/[\$,]/g, '')*1 :
			                    typeof i === 'number' ?
			                        i : 0;
			            };
			 
			            // Total over all pages
			            /* total = api
			                .column( 6 )
			                .data()
			                .reduce( function (a, b) {
			                    return intVal(a) + intVal(b);
			                }, 0 ); 
			 			console.log(total); */
			            // Total over this page
			            pageTotal = api
			                .column( 3 )
			                .data()
			                .reduce( function (a, b) {
			                    return intVal(a) + intVal(b);
			                }, 0 );
			 
			            // Update footer
			            $( api.column( 3 ).footer() ).html(
			            		
			              'Rs'+' '+parseFloat(pageTotal).toFixed(2)
			               
			            );
			            console.log( pageTotal);
			            
			           
			            
			            
			        },
			    	
			    	destroy: true,
			        searching: false,
			      
			columns: [
                        {"data": "insertDate" , "width": "5%" ,"defaultContent": ""},
						{"data": "firstName", "width": "5%" ,"defaultContent": ""},
						{"data": "lastName", "width": "5%" ,"defaultContent": ""},
						{"data": "paymentType", "width": "5%" ,"defaultContent": ""},
						{"data": "paymentMode" , "width": "5%" ,"defaultContent": ""},
						{"data": "paymentAmount" , "width": "5%" ,"defaultContent": ""},
						{"data": "accountantName" , "width": "5%" ,"defaultContent": ""},
						{"data": "reason" , "width": "5%" ,"defaultContent": ""},
			           
			        ]
			    } );
			} );
			
		var mydata = catmap;
		$('#empBetweenTwoDates').dataTable().fnAddData(mydata);
			
			}).error(function(jqXHR, textStatus, errorThrown){
				if(textStatus==="timeout") {
					$(loaderObj).hide();
					$(loaderObj).find('#errorDiv').show();
				
				}
			});
	
}

/*********** Expense Payment Reports *************/
/******** For single date *********/

function expensePaymentReportForSingleDate(){

	var params= {};
	var fDate = $("#fDate4").val();
	
	
	params["fDate"]= fDate;
	
	params["methodName"] = "getExpensePaymentDetailsForSingleDate";

	$.post('/SMT/jsp/utility/controller.jsp',params,function(data)
			{
		//To clear table
		$('#expenseSingleDatetable').dataTable().fnClearTable();
		
		var jsonData = $.parseJSON(data);
		var catmap = jsonData.list;
		
		
		
		$(document).ready(function() {
		 var total =   $('#expenseSingleDatetable').DataTable( {
			 
			 dom: 'Bfrtip',
	         buttons: [
	             'copy', 'csv', 'excel', 'pdf', 'print'
	         ],
	    	
			 
			 fnRowCallback : function(nRow, aData, iDisplayIndex){
	                $("th:first", nRow).html(iDisplayIndex +1);
	               return nRow;
	            },
			
			 "footerCallback": function ( row, data, start, end, display ) {
		            var api = this.api(), data;
		 
		            // Remove the formatting to get integer data for summation
		            var intVal = function ( i ) {
		                return typeof i === 'string' ?
		                    i.replace(/[\$,]/g, '')*1 :
		                    typeof i === 'number' ?
		                        i : 0;
		            };
		 
		         // Total over this page
		            pageTotal = api
		                .column( 3 )
		                .data()
		                .reduce( function (a, b) {
		                    return intVal(a) + intVal(b);
		                }, 0 );
		 
		            // Update footer
		            $( api.column( 3 ).footer() ).html(
		            		
		              parseFloat(pageTotal).toFixed(2)
		               
		            );
		            console.log( pageTotal);	
		            
		         // Total over this page
		            pageTotal1 = api
		                .column( 4 )
		                .data()
		                .reduce( function (a, b) {
		                    return intVal(a) + intVal(b);
		                }, 0 );
		 
		            // Update footer
		            $( api.column( 4 ).footer() ).html(
		            		
		              parseFloat(pageTotal1).toFixed(2)
		               
		            );
		            console.log( pageTotal);
		             var creditAmt =  parseFloat(pageTotal).toFixed(2);
		             var debitAmt =  parseFloat(pageTotal1).toFixed(2);
		             var remainAmt = +creditAmt - +debitAmt;
		             
		          // Update footer
			            $( api.column( 5 ).footer() ).html(
			            		
			              parseFloat(remainAmt).toFixed(2)
			               
			            );
		            
		        },
		    	
		    	
		    	destroy: true,
		        searching: false,
		        
		      
		columns: [
						{"data": "insertDate" , "width": "5%" ,"defaultContent": ""}, 
						{"data": "expenseName", "width": "5%" ,"defaultContent": ""},
						{"data": "serviceProviderName", "width": "5%" ,"defaultContent": ""},
						{"data": "credit" , "width": "5%" ,"defaultContent": ""},
						{"data": "debit" , "width": "5%" ,"defaultContent": ""},
						{"data": "accountantName" , "width": "5%" ,"defaultContent": ""},
		        ],
		      
		        
		    } );
		} );
		
	var mydata = catmap;
	$('#expenseSingleDatetable').dataTable().fnAddData(mydata);
	
		}
	
	
	);


}


/************ Between Two Dates *************/
function getExpensePaymentDetailsBetTwoDays(){

	

	var params= {};
	var startDate = $("#fisDate4").val();
	var endDate = $("#endDate4").val();

	 var input = document.getElementById('expenseName'),
     list = document.getElementById('exp_drop'),
     	i,fkRootexpId;
	 for (i = 0; i < list.options.length; ++i) {
			     if (list.options[i].value === input.value) {
			    	 fkRootexpId = list.options[i].getAttribute('data-value');
			  }
	 }

    params["fkRootexpId"]= fkRootexpId; 
	params["fisDate"]= startDate;
	params["endDate"]= endDate;
	params["methodName"] = "getExpenditurePaymentReportBetweenTwoDates";

	$.post('/SMT/jsp/utility/controller.jsp',params,function(data)
			{
		//To clear Table
		$('#expenseBetweenTwoDates').dataTable().fnClearTable();
		
		var jsonData = $.parseJSON(data);
		var catmap = jsonData.list;
	
			
			$(document).ready(function() {
			    $('#expenseBetweenTwoDates').DataTable( {
			    	
			    	dom: 'Bfrtip',
			         buttons: [
			             'copy', 'csv', 'excel', 'pdf', 'print'
			         ],
			    	
			    	fnRowCallback : function(nRow, aData, iDisplayIndex){
		                $("th:first", nRow).html(iDisplayIndex +1);
		               return nRow;
		            },
				
				 "footerCallback": function ( row, data, start, end, display ) {
			            var api = this.api(), data;
			 
			            // Remove the formatting to get integer data for summation
			            var intVal = function ( i ) {
			                return typeof i === 'string' ?
			                    i.replace(/[\$,]/g, '')*1 :
			                    typeof i === 'number' ?
			                        i : 0;
			            };
			 
			         // Total over this page
			            pageTotal = api
			                .column( 3 )
			                .data()
			                .reduce( function (a, b) {
			                    return intVal(a) + intVal(b);
			                }, 0 );
			 
			            // Update footer
			            $( api.column( 3 ).footer() ).html(
			            		
			              parseFloat(pageTotal).toFixed(2)
			               
			            );
			            console.log( pageTotal);	
			            
			         // Total over this page
			            pageTotal1 = api
			                .column( 4 )
			                .data()
			                .reduce( function (a, b) {
			                    return intVal(a) + intVal(b);
			                }, 0 );
			 
			            // Update footer
			            $( api.column( 4 ).footer() ).html(
			            		
			              parseFloat(pageTotal1).toFixed(2)
			               
			            );
			            console.log( pageTotal);
			             var creditAmt =  parseFloat(pageTotal).toFixed(2);
			             var debitAmt =  parseFloat(pageTotal1).toFixed(2);
			             var remainAmt = +creditAmt - +debitAmt;
			             
			          // Update footer
				            $( api.column( 5 ).footer() ).html(
				            		
				              parseFloat(remainAmt).toFixed(2)
				               
				            );
			            
			            
			        },
			    	
			    	destroy: true,
			        searching: false,
			      
			columns: [
			        
	                    {"data": "insertDate" , "width": "5%" ,"defaultContent": ""}, 
						{"data": "expenseName", "width": "5%" ,"defaultContent": ""},
						{"data": "serviceProviderName", "width": "5%" ,"defaultContent": ""},
						{"data": "credit" , "width": "5%" ,"defaultContent": ""},
						{"data": "debit" , "width": "5%" ,"defaultContent": ""},
						{"data": "accountantName" , "width": "5%" ,"defaultContent": ""},
			           
			        ]
			    } );
			} );
			
		var mydata = catmap;
		$('#expenseBetweenTwoDates').dataTable().fnAddData(mydata);
			
			}).error(function(jqXHR, textStatus, errorThrown){
				if(textStatus==="timeout") {
					$(loaderObj).hide();
					$(loaderObj).find('#errorDiv').show();
				
				}
			});
	
}

/*get taday credit and debit report*/
function getTodayCreditDebitReport(){
	
	    var params= {};
		
		params["methodName"] = "getTodayCreditDebitReport";
		
		

		$.post('/SMT/jsp/utility/controller.jsp',params,function(data)
				{
			//To clear Table
			$('#example1').dataTable().fnClearTable();
			
			var jsonData = $.parseJSON(data);
			var catmap = jsonData.list;
		
				
				$(document).ready(function() {
				    $('#example1').DataTable( {
				    	
				    	dom: 'Bfrtip',
				         buttons: [
				             'copy', 'csv', 'excel', 'pdf', 'print'
				         ],
				    	
				    	fnRowCallback : function(nRow, aData, iDisplayIndex){
			                $("th:first", nRow).html(iDisplayIndex +1);
			               return nRow;
			            },
					
					 "footerCallback": function ( row, data, start, end, display ) {
				            var api = this.api(), data;
				 
				            // Remove the formatting to get integer data for summation
				            var intVal = function ( i ) {
				                return typeof i === 'string' ?
				                    i.replace(/[\$,]/g, '')*1 :
				                    typeof i === 'number' ?
				                        i : 0;
				            };
				 
				            // Total over all pages
				            /* total = api
				                .column( 6 )
				                .data()
				                .reduce( function (a, b) {
				                    return intVal(a) + intVal(b);
				                }, 0 ); 
				 			console.log(total); */
				            // Total over this page
				            pageTotal = api
				                .column( 1 )
				                .data()
				                .reduce( function (a, b) {
				                    return intVal(a) + intVal(b);
				                }, 0 );
				 
				            // Update footer
				            $( api.column( 1 ).footer() ).html(
				            		
				              'Rs'+' '+parseFloat(pageTotal).toFixed(2)
				               
				            );
				            console.log( pageTotal);
				            
				            var abc = pageTotal;
							
							document.getElementById('creAmt').value = abc;
				            
				            
				        },
				    	
				    	destroy: true,
				        searching: false,
				      
				columns: [
				          
						{"data": "name", "width": "5%" ,"defaultContent": ""},
						{"data": "credit", "width": "5%" ,"defaultContent": ""},
					
						
				        ]
				    } );
				} );
				
			var mydata = catmap;
			$('#example1').dataTable().fnAddData(mydata);
				
				}).error(function(jqXHR, textStatus, errorThrown){
					if(textStatus==="timeout") {
						$(loaderObj).hide();
						$(loaderObj).find('#errorDiv').show();
					
					}
				});
	 
}

/*get taday credit and debit report*/
function getTodayCreditDebitReport1(){
	
	    var params= {};
		
		
		params["methodName"] = "getTodayCreditDebitReport1";
		

		$.post('/SMT/jsp/utility/controller.jsp',params,function(data)
				{
			//To clear Table
			$('#example2').dataTable().fnClearTable();
			
			var jsonData = $.parseJSON(data);
			var catmap = jsonData.list;
		
				
				$(document).ready(function() {
				    $('#example2').DataTable( {
				    	
				    	dom: 'Bfrtip',
				         buttons: [
				             'copy', 'csv', 'excel', 'pdf', 'print'
				         ],
				    	
				    	fnRowCallback : function(nRow, aData, iDisplayIndex){
			                $("th:first", nRow).html(iDisplayIndex +1);
			               return nRow;
			            },
					
					 "footerCallback": function ( row, data, start, end, display ) {
				            var api = this.api(), data;
				 
				            // Remove the formatting to get integer data for summation
				            var intVal = function ( i ) {
				                return typeof i === 'string' ?
				                    i.replace(/[\$,]/g, '')*1 :
				                    typeof i === 'number' ?
				                        i : 0;
				            };
				 
				            // Total over all pages
				            /* total = api
				                .column( 6 )
				                .data()
				                .reduce( function (a, b) {
				                    return intVal(a) + intVal(b);
				                }, 0 ); 
				 			console.log(total); */
				            // Total over this page
				            pageTotal = api
				                .column( 1 )
				                .data()
				                .reduce( function (a, b) {
				                    return intVal(a) + intVal(b);
				                }, 0 );
				 
				            // Update footer
				            $( api.column( 1 ).footer() ).html(
				            		
				              'Rs'+' '+parseFloat(pageTotal).toFixed(2)
				               
				            );
				            console.log( pageTotal);
				            
                            var xyz = pageTotal;
							
							document.getElementById('debAmt').value = xyz;
				            
				            
				            
				        },
				    	
				    	destroy: true,
				        searching: false,
				      
				columns: [
                          {"data": "name", "width": "5%" ,"defaultContent": ""}, 
						  {"data": "debit", "width": "5%" ,"defaultContent": ""},
					
						
				        ]
				    } );
				} );
				
			var mydata = catmap;
			$('#example2').dataTable().fnAddData(mydata);
				
				}).error(function(jqXHR, textStatus, errorThrown){
					if(textStatus==="timeout") {
						$(loaderObj).hide();
						$(loaderObj).find('#errorDiv').show();
					
					}
				});
	 
}

/*get taday credit and debit report*/
function creditdebitForsingleDate(){
	
	    var params= {};
	    var fDate = $("#fDate").val();
	    
	    params["fDate"]= fDate;
		params["methodName"] = "creditdebitForsingleDate";
		

		$.post('/SMT/jsp/utility/controller.jsp',params,function(data)
				{
			//To clear Table
			$('#example3').dataTable().fnClearTable();
			
			var jsonData = $.parseJSON(data);
			var catmap = jsonData.list;
		
				
				$(document).ready(function() {
				    $('#example3').DataTable( {
				    	
				    	dom: 'Bfrtip',
				         buttons: [
				             'copy', 'csv', 'excel', 'pdf', 'print'
				         ],
				    	
				    	fnRowCallback : function(nRow, aData, iDisplayIndex){
			                $("th:first", nRow).html(iDisplayIndex +1);
			               return nRow;
			            },
					
					 "footerCallback": function ( row, data, start, end, display ) {
				            var api = this.api(), data;
				 
				            // Remove the formatting to get integer data for summation
				            var intVal = function ( i ) {
				                return typeof i === 'string' ?
				                    i.replace(/[\$,]/g, '')*1 :
				                    typeof i === 'number' ?
				                        i : 0;
				            };
				 
				            // Total over all pages
				            /* total = api
				                .column( 6 )
				                .data()
				                .reduce( function (a, b) {
				                    return intVal(a) + intVal(b);
				                }, 0 ); 
				 			console.log(total); */
				            // Total over this page
				            pageTotal = api
				                .column( 1 )
				                .data()
				                .reduce( function (a, b) {
				                    return intVal(a) + intVal(b);
				                }, 0 );
				 
				            // Update footer
				            $( api.column( 1 ).footer() ).html(
				            		
				              'Rs'+' '+parseFloat(pageTotal).toFixed(2)
				               
				            );
				            console.log( pageTotal);
				            
				           
				            
				            
				        },
				    	
				    	destroy: true,
				        searching: false,
				      
				columns: [
				          
						{"data": "name", "width": "5%" ,"defaultContent": ""},
						{"data": "credit", "width": "5%" ,"defaultContent": ""},
					
						
				        ]
				    } );
				} );
				
			var mydata = catmap;
			$('#example3').dataTable().fnAddData(mydata);
				
				}).error(function(jqXHR, textStatus, errorThrown){
					if(textStatus==="timeout") {
						$(loaderObj).hide();
						$(loaderObj).find('#errorDiv').show();
					
					}
				});
	 
}


/*get taday credit and debit report*/
function creditdebitForsingleDate1(){
	
	    var params= {};
	    var fDate = $("#fDate").val();
	    
	    params["fDate"]= fDate;
		params["methodName"] = "creditdebitForsingleDate1";
		

		$.post('/SMT/jsp/utility/controller.jsp',params,function(data)
				{
			//To clear Table
			$('#example4').dataTable().fnClearTable();
			
			var jsonData = $.parseJSON(data);
			var catmap = jsonData.list;
		
				
				$(document).ready(function() {
				    $('#example4').DataTable( {
				    	
				    	dom: 'Bfrtip',
				         buttons: [
				             'copy', 'csv', 'excel', 'pdf', 'print'
				         ],
				    	
				    	fnRowCallback : function(nRow, aData, iDisplayIndex){
			                $("th:first", nRow).html(iDisplayIndex +1);
			               return nRow;
			            },
					
					 "footerCallback": function ( row, data, start, end, display ) {
				            var api = this.api(), data;
				 
				            // Remove the formatting to get integer data for summation
				            var intVal = function ( i ) {
				                return typeof i === 'string' ?
				                    i.replace(/[\$,]/g, '')*1 :
				                    typeof i === 'number' ?
				                        i : 0;
				            };
				 
				            // Total over all pages
				            /* total = api
				                .column( 6 )
				                .data()
				                .reduce( function (a, b) {
				                    return intVal(a) + intVal(b);
				                }, 0 ); 
				 			console.log(total); */
				            // Total over this page
				            pageTotal = api
				                .column( 1 )
				                .data()
				                .reduce( function (a, b) {
				                    return intVal(a) + intVal(b);
				                }, 0 );
				 
				            // Update footer
				            $( api.column( 1 ).footer() ).html(
				            		
				              'Rs'+' '+parseFloat(pageTotal).toFixed(2)
				               
				            );
				            console.log( pageTotal);
				            
				           
				            
				            
				        },
				    	
				    	destroy: true,
				        searching: false,
				      
				columns: [
				          
						{"data": "name", "width": "5%" ,"defaultContent": ""},
						{"data": "debit", "width": "5%" ,"defaultContent": ""},
					
						
				        ]
				    } );
				} );
				
			var mydata = catmap;
			$('#example4').dataTable().fnAddData(mydata);
				
				}).error(function(jqXHR, textStatus, errorThrown){
					if(textStatus==="timeout") {
						$(loaderObj).hide();
						$(loaderObj).find('#errorDiv').show();
					
					}
				});
	 
}



function creditdebitForBetTowDate(){
	
    var params= {};
    var fisDate = $("#fisDate").val();
    var endDate = $("#endDate").val();
    
    params["fisDate"]= fisDate;
    params["endDate"]= endDate;
	params["methodName"] = "creditdebitForBetTowDate";
	

	$.post('/SMT/jsp/utility/controller.jsp',params,function(data)
			{
		//To clear Table
		$('#example5').dataTable().fnClearTable();
		
		var jsonData = $.parseJSON(data);
		var catmap = jsonData.list;
	
			
			$(document).ready(function() {
			    $('#example5').DataTable( {
			    	
			    	dom: 'Bfrtip',
			         buttons: [
			             'copy', 'csv', 'excel', 'pdf', 'print'
			         ],
			    	
			    	fnRowCallback : function(nRow, aData, iDisplayIndex){
		                $("th:first", nRow).html(iDisplayIndex +1);
		               return nRow;
		            },
				
				 "footerCallback": function ( row, data, start, end, display ) {
			            var api = this.api(), data;
			 
			            // Remove the formatting to get integer data for summation
			            var intVal = function ( i ) {
			                return typeof i === 'string' ?
			                    i.replace(/[\$,]/g, '')*1 :
			                    typeof i === 'number' ?
			                        i : 0;
			            };
			 
			            // Total over all pages
			            /* total = api
			                .column( 6 )
			                .data()
			                .reduce( function (a, b) {
			                    return intVal(a) + intVal(b);
			                }, 0 ); 
			 			console.log(total); */
			            // Total over this page
			            pageTotal = api
			                .column( 1 )
			                .data()
			                .reduce( function (a, b) {
			                    return intVal(a) + intVal(b);
			                }, 0 );
			 
			            // Update footer
			            $( api.column( 1 ).footer() ).html(
			            		
			              'Rs'+' '+parseFloat(pageTotal).toFixed(2)
			               
			            );
			            console.log( pageTotal);
			            
			           
			            
			            
			        },
			    	
			    	destroy: true,
			        searching: false,
			      
			columns: [
			        
                       {"data": "name", "width": "5%", "defaultContent": ""},
					   {"data": "credit", "width": "5%", "defaultContent": ""},
				
					
			        ]
			    } );
			} );
			
		var mydata = catmap;
		$('#example5').dataTable().fnAddData(mydata);
			
			}).error(function(jqXHR, textStatus, errorThrown){
				if(textStatus==="timeout") {
					$(loaderObj).hide();
					$(loaderObj).find('#errorDiv').show();
				
				}
			});
 
}


/*get taday credit and debit report*/
function creditdebitForBetTowDate1(){

    var params= {};
    var fisDate = $("#fisDate").val();
    var endDate = $("#endDate").val();
    
    params["fisDate"]= fisDate;
    params["endDate"]= endDate;
	params["methodName"] = "creditdebitForBetTowDate1";
	

	$.post('/SMT/jsp/utility/controller.jsp',params,function(data)
			{
		//To clear Table
		$('#example6').dataTable().fnClearTable();
		
		var jsonData = $.parseJSON(data);
		var catmap = jsonData.list;
	
			
			$(document).ready(function() {
			    $('#example6').DataTable( {
			    	
			    	dom: 'Bfrtip',
			         buttons: [
			             'copy', 'csv', 'excel', 'pdf', 'print'
			         ],
			    	
			    	fnRowCallback : function(nRow, aData, iDisplayIndex){
		                $("th:first", nRow).html(iDisplayIndex +1);
		               return nRow;
		            },
				
				 "footerCallback": function ( row, data, start, end, display ) {
			            var api = this.api(), data;
			 
			            // Remove the formatting to get integer data for summation
			            var intVal = function ( i ) {
			                return typeof i === 'string' ?
			                    i.replace(/[\$,]/g, '')*1 :
			                    typeof i === 'number' ?
			                        i : 0;
			            };
			 
			            // Total over all pages
			            /* total = api
			                .column( 6 )
			                .data()
			                .reduce( function (a, b) {
			                    return intVal(a) + intVal(b);
			                }, 0 ); 
			 			console.log(total); */
			            // Total over this page
			            pageTotal = api
			                .column( 1 )
			                .data()
			                .reduce( function (a, b) {
			                    return intVal(a) + intVal(b);
			                }, 0 );
			 
			            // Update footer
			            $( api.column( 1 ).footer() ).html(
			            		
			              'Rs'+' '+parseFloat(pageTotal).toFixed(2)
			               
			            );
			            console.log( pageTotal);
			            
			           
			            
			            
			        },
			    	
			    	destroy: true,
			        searching: false,
			      
			columns: [
			          
                       {"data": "name", "width": "5%" ,"defaultContent": ""},
					   {"data": "debit", "width": "5%" ,"defaultContent": ""},
				
					
			        ]
			    } );
			} );
			
		var mydata = catmap;
		$('#example6').dataTable().fnAddData(mydata);
			
			}).error(function(jqXHR, textStatus, errorThrown){
				if(textStatus==="timeout") {
					$(loaderObj).hide();
					$(loaderObj).find('#errorDiv').show();
				
				}
			});
 
}


function getYesterdarTotalAmount(){
	
	var params= {};
	
	params["methodName"] = "getYesterdarTotalAmount";
	
	
	$("#yesAmt").append($("<input/>").attr("value","").text());
	$.post('/SMT/jsp/utility/controller.jsp',params,function(data)
			{
		var jsonData = $.parseJSON(data);
		//var jsonData = jsonData.list;
		$.each(jsonData,function(i,v)
				{
			document.getElementById("yesAmt").value =parseFloat(v.totalAmount).toFixed(2);
			
				});
			})
	
	
}

function getTodaySaleTotalAmount(){
	
	var params= {};
	
	params["methodName"] = "getTodaySaleTotalAmount";
	
	
	$("#dupsaletotal").append($("<input/>").attr("value","").text());
	$.post('/SMT/jsp/utility/controller.jsp',params,function(data)
			{
		var jsonData = $.parseJSON(data);
		//var jsonData = jsonData.list;
		$.each(jsonData,function(i,v)
				{
			document.getElementById("dupsaletotal").value = v.totalAmount;
			getTodaySaleTotalAmount1();
			
				});
			})
	
	
}

function getTodaySaleTotalAmount1(){
	
	var params= {};
	
	params["methodName"] = "getTodaySaleTotalAmount1";
	
	
	$("#dupsaletotal1").append($("<input/>").attr("value","").text());
	$.post('/SMT/jsp/utility/controller.jsp',params,function(data)
			{
		var jsonData = $.parseJSON(data);
		//var jsonData = jsonData.list;
		$.each(jsonData,function(i,v)
				{
			document.getElementById("dupsaletotal1").value = v.totalAmount;
			var asd = document.getElementById("dupsaletotal").value;
			var grossSaleTotal = +asd + +v.totalAmount;
			document.getElementById("totsaleAmt").value = grossSaleTotal;
			
			shreeDemo();
			
				});
			})
	
	
}

function getAllBills1()
{
	
	 var input = document.getElementById('supplier'),
     list = document.getElementById('sup_drop'),
     i,supplier;

	for (i = 0; i < list.options.length; ++i) {
     if (list.options[i].value === input.value) {
    	 supplier = list.options[i].getAttribute('data-value');
     }
 }
	
 var supplier = supplier;
	$("#billNo").empty();
	$("#billNo").append($("<option></option>").attr("value","").text("Select bill"));
	var params= {};
	params["supplier"]= supplier;
	params["methodName"] = "getAllBillBySuppliers1";
	
	
	
	$.post('/SMT/jsp/utility/controller.jsp',params,function(data)
			{
		var jsonData = $.parseJSON(data);
		//var jsonData = jsonData.list;
		$.each(jsonData,function(i,v)
				{
			$("#billNo").append($("<option></option>").attr("value",i).text(v.billNo)); 
			
				});
			})

}

function getUnBillnoTotalAmount(){
	
	var input = document.getElementById('supplier'),
    list = document.getElementById('sup_drop'),
    i,supplier;

	for (i = 0; i < list.options.length; ++i) {
    if (list.options[i].value === input.value) {
   	 supplier = list.options[i].getAttribute('data-value');
    }
   }
	var params= {};
	params["supplier"]= supplier;
	params["methodName"] = "getAllUnPaidBillAmount";
	
	
	

	$.post('/SMT/jsp/utility/controller.jsp',params,function(data)
			{
		//To clear Table
		$('#unPaidAmt').dataTable().fnClearTable();
		
		var jsonData = $.parseJSON(data);
		var catmap = jsonData.list;
	
			
			$(document).ready(function() {
			    $('#unPaidAmt').DataTable( {
			    	
			    	dom: 'Bfrtip',
			         buttons: [
			             'copy', 'csv', 'excel', 'pdf', 'print'
			         ],
			    	
			    	fnRowCallback : function(nRow, aData, iDisplayIndex){
		                $("th:first", nRow).html(iDisplayIndex +1);
		               return nRow;
		            },
				
				 "footerCallback": function ( row, data, start, end, display ) {
			            var api = this.api(), data;
			 
			            // Remove the formatting to get integer data for summation
			            var intVal = function ( i ) {
			                return typeof i === 'string' ?
			                    i.replace(/[\$,]/g, '')*1 :
			                    typeof i === 'number' ?
			                        i : 0;
			            };
			 
			            
			            // Total over this page
			            pageTotal = api
			                .column( 1 )
			                .data()
			                .reduce( function (a, b) {
			                    return intVal(a) + intVal(b);
			                }, 0 );
			 
			            // Update footer
			            $( api.column( 1 ).footer() ).html(
			            		
			              'Rs'+' '+parseFloat(pageTotal).toFixed(2)
			               
			            );
			            console.log( pageTotal);
			            
			           
			            
			            
			        },
			    	
			    	destroy: true,
			        searching: false,
			      
			columns: [
			          
                       {"data": "billNo", "width": "5%" ,"defaultContent": ""},
					   {"data": "totalAmt", "width": "5%" ,"defaultContent": ""},
				
					
			        ]
			    } );
			} );
			
		var mydata = catmap;
		$('#unPaidAmt').dataTable().fnAddData(mydata);
			
			}).error(function(jqXHR, textStatus, errorThrown){
				if(textStatus==="timeout") {
					$(loaderObj).hide();
					$(loaderObj).find('#errorDiv').show();
				
				}
			});
 
}

function shreeDemo(){
	
	var yesterdayAmt = document.getElementById("yesAmt").value;
	var todayCreditAmt = document.getElementById("creAmt").value;
	var todayDebitAmt = document.getElementById("debAmt").value;
	var todaySaleAmt = document.getElementById("totsaleAmt").value;
	
	
	var grossSaleTotal = (+yesterdayAmt + +todayDebitAmt + +todaySaleAmt) - (+todayCreditAmt);
	
	
	
	document.getElementById("remAmt").value = grossSaleTotal;
}