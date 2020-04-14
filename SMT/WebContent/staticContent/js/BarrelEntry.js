   
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

//////////////////Barrel purchase oil item name grid/////////////////



function getProductList1()
{
	//var itemName = document.getElementById('itemName').value;
	
	var input = document.getElementById('itemName1'),
    list = document.getElementById('itemId_drop1'),
    i,catName,itemName, hsnsacno;
    for (i = 0; i < list.options.length; ++i) {
    if (list.options[i].value === input.value) {
    	catName = list.options[i].getAttribute('data-value');
    	itemName = list.options[i].getAttribute('myvalue');
    	hsnsacno = list.options[i].getAttribute('myvalue1');
    }
   }
	
    itemparams={};
	
	itemparams["itemName"]= itemName;
	itemparams["catName"]= catName;
	itemparams["hsnsacno"]= hsnsacno;
	
	document.getElementById('itemName1').value = null;
	var count=0;
	var newrow;
	var rowId;
	itemparams["methodName"] = "getProductInGrid1";
	$.post('/SMT/jsp/utility/controller.jsp',itemparams,
			function(data)
			{ 
				var jsonData = $.parseJSON(data);
			
			 count = jQuery("#jqGrid1").jqGrid('getGridParam', 'records'); 
		     var rowdata =$("#jqGrid1").jqGrid('getGridParam','data');
		     var ids = jQuery("#jqGrid1").jqGrid('getDataIDs');
			 
			
			  var ori_quantity,offerId;
			  for (var j = 0; j < count; j++) 
			  {
				offerId = rowdata[j].itemName;
				
				 var rowId = ids[j];
				 var rowData = jQuery('#jqGrid1').jqGrid ('getRowData', rowId);
				
				if (offerId==jsonData.offer.itemName) {
			    	ori_quantity = +rowdata[j].quantity+1;
			    	alert("Product already entered !");
			//    	$("#jqGrid").jqGrid("setCell", rowId, "quantity", ori_quantity);
			    	var grid = jQuery("#jqGrid1");
			    	grid.trigger("reloadGrid");
			    	newrow=false;
			    	break;
				}
				else
				{
					newrow = true;
				}
			 }
			  
			  if(newrow== true)
				 {
					
					 $("#jqGrid1").addRowData(count,jsonData.offer);
					
				 }
			 
			 
				$("#jqGrid1").jqGrid({
					datatype:"local",
					editurl: 'clientArray',
					colNames: ["ItemName","Category Name","HSN/SAC","Quantity In Litres","BuyPrice","SalePrice","GST %","IGST %","TAX AMT","Discount %","DisAmt","Total","--S--"],

					colModel: [
					           { 	
					        	   name: "itemName",//PARTICULARS
					        	   width:200,
					        	 
					           },
					          
					           {
					        	   name:  "catName",
					        	   width: 170,
					        	   
					           },
					           {
					        	   name:  "hsnsacno",
					        	   width: 120,
					        	   
					           },
					           
					           {
					        	   name:  "quantity",
					        	   width: 100,
					        	   editable: true,
					        	   required:true
					           },

					           {
					        	   name: "buyPrice",
					        	   width: 150,
					        	   editable: true,
					        	 
					           },
					           
					           {
					        	   name: "SalePrice",
					        	   width: 150,
					        	   editable: true,
					        	 
					           },

					           { 	
			        	           name: "vat",//PARTICULARS cgst
			        	           width: 100,
			        	           editable: true,
			                    },
			                    /*{ 	
				        	           name: "sgst",//PARTICULARS
				        	           width: 100,
				        	           editable: true,
				                },*/
			                    { 	
				        	           name: "igst",//PARTICULARS
				        	           width: 100,
				        	           editable: true,
				                },
			                    { 	
				        	           name: "gstamt",//PARTICULARS
				        	           width: 140,
				        	           //formatter: calculateGst
				                },
					           {
					        	   name: "discount",
					        	   width: 150,
					        	   editable: true,
					        	 
					           },
					           
					           {
					        	   name: "discountAmt",
					        	   width: 150,
					        	   //editable: true,
					        	 
					           },

					           {
					        	   name: "Total",
					        	   width: 200,
								   formatter: 'number',
								  
					        	//   formatter: calculateTotal
					        	
					           },
					           {
					        	   name: "actualprice",
					        	   width: 100,
					        	   editable: true,
					        	 hidden: true
					           }
					           ],


					           sortorder : 'desc',
					           multiselect: false,	
					           loadonce: false,
					           viewrecords: true,
					           width: 1300,
					           shrinkToFit: true,
							   rowheight: 300,
							  /* footerrow: true,
				               userDataOnFooter: true,
				               grouping: true,*/
							   rownumbers:true,
							 //  onSelectRow: editRow,
					           rowNum: 11,
					           
					           'cellEdit':true,
					           afterSaveCell: function () {
					        	   // $(this).trigger('reloadGrid');
					        	   	var rowId =$("#jqGrid1").jqGrid('getGridParam','selrow');  
					        	   	var rowData = jQuery("#jqGrid1").getRowData(rowId);
			                    	var quantity = rowData['quantity'];
			                    	var buyPrice = rowData['buyPrice'];
			                    	var discount = rowData['discount'];
			                    	var gst = rowData['vat'];
			                    	var igst = rowData['igst'];
			                    	var vatAmt = 0;
			                    	var discount1 = 0;
			                    	var tota = 0;
			                    	var totAmt = 0;
			                    	var ctot = 0;
			                    	tota = quantity * buyPrice;
			                    	totAmt = quantity * buyPrice;
			                    	if(discount != "0"){
			                    		discount1 = ((tota*discount)/100);
			                    		tota = +tota - +discount1;
			                    		totAmt = +tota;
			                    	}
			                    	
			                    	if(gst != "0" && igst ==0){
			                    		vatAmt =  ((tota*(gst))/100);
			                    		totAmt = +tota + +vatAmt;
			                    	}
			                    	if(igst != "0" && gst == 0){
			                    		vatAmt =  ((tota*igst)/100);
			                    		totAmt = +tota + +vatAmt;
			                    	}
			                    	if(gst !=0 && igst !=0){
			                    		alert("please enter either gst or igst");
			                    		var abc = 0;
				                    	$("#jqGrid1").jqGrid("setCell", rowId, "vat", abc);
				                    	$("#jqGrid1").jqGrid("setCell", rowId, "igst", abc);
				                    	return false;
			                    	}
			                    	$("#jqGrid1").jqGrid("setCell", rowId, "discountAmt", discount1);
			                    	$("#jqGrid1").jqGrid("setCell", rowId, "gstamt", vatAmt);
			                    	$("#jqGrid1").jqGrid("setCell", rowId, "Total", totAmt);
			                    	var Total = 0;
			                    	var count = jQuery("#jqGrid1").jqGrid('getGridParam', 'records');
			        				var allRowsInGrid1 = $('#jqGrid1').getGridParam('data');
			        				var AllRows=JSON.stringify(allRowsInGrid1);
			                            for (var k = 0; k < count; k++) {
			                    	var Total1 = allRowsInGrid1[k].Total;
			                    	if(Total1 != undefined){
			                    		Total = +Total + +Total1;
			                    	} 
			                        }
			                            document.getElementById("resolutionOil").value = Math.round(Total);
			                            document.getElementById("resolutionOil1").value = Math.round(Total);
			                            var totAmount = Math.round(Total);
			                            
			                            var extraDiscount = document.getElementById("extraDiscount1").value;
			                            if(extraDiscount != "0"){
			    	             	    	document.getElementById("resolutionOil").value = totAmount;
			    	             	    }
			    	             	    else{
			    	             	    	var disAmount =  (extraDiscount/100)*totAmount;
			    	            			var gross = +totAmount - +disAmount;
			    	            			document.getElementById("resolutionOil").value = Math.round(gross);
			    	             	    }
			                            
			    	             	    var expence = document.getElementById("expence1").value;
			    	             	    if(expence != "0"){
			    	             	    	document.getElementById("resolutionOil").value = totAmount;
			    	             	    }
			    	             	    else{
			    	             	    	document.getElementById("resolutionOil").value = (+totAmount + +expence);
			    	             	    }
					        	},
					        	  
					           pager: "#jqGridPager"
			      });
				
				var lastSelection;

	            function editRow(id) {
	                if (id && id !== lastSelection) {
	                    var grid = $("#jqGrid1");
	                    grid.jqGrid('restoreRow',lastSelection);
	                    grid.jqGrid('editRow',id, {keys: true} );
	                    lastSelection = id;
	                }
	            }
					        	

				
				if(count==0 || count==null)
				{
					$("#jqGrid1").addRowData(0,jsonData.offer);
				}
				
				$('#jqGrid1').navGrid('#jqGridPager',
						// the buttons to appear on the toolbar of the grid
						{ edit: true, add:false, del: true, search: true, refresh: true, view: true, position: "left", cloneToTop: false },
						// options for the Edit Dialog
						{
							editCaption: "The Edit Dialog",
							reloadAfterSubmit : true,
							closeAfterEdit: true,
							recreateForm: true,
							
							afterSubmit: function () {
								//$(this).trigger('reloadGrid');
//								var count = jQuery("#jqGrid").jqGrid('getGridParam', 'records');


								
								   var rowId =$("#jqGrid1").jqGrid('getGridParam','selrow');  
			                       var rowData = jQuery("#jqGrid1").getRowData(rowId);
			                    	var quantity = rowData['quantity'];
			                    	var buyPrice = rowData['buyPrice'];
			                    	
			                    	var tota = quantity * buyPrice;

			                    	$("#jqGrid1").jqGrid("setCell", rowId, "Total", tota);
								
			                    	
							},
							errorTextFormat: function (data) {
								return 'Error: ' + data.responseText
							}
						 },
						
						 {},
						
						 {
							closeAfterdel:true,
							checkOnUpdate : true,
							checkOnSubmit : true,
							recreateForm: true,
							afterComplete: function() {
		                		//$('#jqGrid').trigger( 'reloadGrid' );
		                		
		                		
		                		var rowId =$("#jqGrid1").jqGrid('getGridParam','selrow');  
				        	   	var rowData = jQuery("#jqGrid1").getRowData(rowId);
		                    	var quantity = rowData['quantity'];
		                    	var buyPrice = rowData['buyPrice'];
		                    	var discount = rowData['discount'];
		                    	var gst = rowData['vat'];
		                    	var igst = rowData['igst'];
		                    	var vatAmt = 0;
		                    	var discount1 = 0;
		                    	var tota = 0;
		                    	var totAmt = 0;
		                    	var ctot = 0;
		                    	tota = quantity * buyPrice;
		                    	totAmt = quantity * buyPrice;
		                    	if(discount != "0"){
		                    		discount1 = ((tota*discount)/100);
		                    		tota = +tota - +discount1;
		                    		totAmt = +tota;
		                    	}
		                    	
		                    	if(gst != "0" && igst ==0){
		                    		vatAmt =  ((tota*(gst))/100);
		                    		totAmt = +tota + +vatAmt;
		                    	}
		                    	if(igst != "0" && gst == 0){
		                    		vatAmt =  ((tota*igst)/100);
		                    		totAmt = +tota + +vatAmt;
		                    	}
		                    	if(gst !=0 && igst !=0){
		                    		//alert("please enter either gst or igst");
		                    		var abc = 0;
			                    	$("#jqGrid1").jqGrid("setCell", rowId, "vat", abc);
			                    	$("#jqGrid1").jqGrid("setCell", rowId, "igst", abc);
			                    	return false;
		                    	}
		                    	$("#jqGrid1").jqGrid("setCell", rowId, "discountAmt", discount1);
		                    	$("#jqGrid1").jqGrid("setCell", rowId, "gstamt", vatAmt);
		                    	$("#jqGrid1").jqGrid("setCell", rowId, "Total", totAmt);
		                    	var Total = 0;
		                    	var count = jQuery("#jqGrid1").jqGrid('getGridParam', 'records');
		        				var allRowsInGrid1 = $('#jqGrid1').getGridParam('data');
		        				var AllRows=JSON.stringify(allRowsInGrid1);
		                            for (var k = 0; k < count; k++) {
		                    	var Total1 = allRowsInGrid1[k].Total;
		                    	if(Total1 != undefined){
		                    		Total = +Total + +Total1;
		                    	} 
		                        }
		                            document.getElementById("resolutionOil").value = Math.round(Total);
		                            document.getElementById("resolutionOil1").value = Math.round(Total);
		                            var totAmount = Math.round(Total);
		                            
		                            var extraDiscount = document.getElementById("extraDiscount1").value;
		                            if(extraDiscount != "0"){
		    	             	    	document.getElementById("resolutionOil").value = totAmount;
		    	             	    }
		    	             	    else{
		    	             	    	var disAmount =  (extraDiscount/100)*totAmount;
		    	            			var gross = +totAmount - +disAmount;
		    	            			document.getElementById("resolutionOil").value = Math.round(gross);
		    	             	    }
		                            
		    	             	    var expence = document.getElementById("expence1").value;
		    	             	    if(expence != "0"){
		    	             	    	document.getElementById("resolutionOil").value = totAmount;
		    	             	    }
		    	             	    else{
		    	             	    	document.getElementById("resolutionOil").value = (+totAmount + +expence);
		    	             	    }
		                		
		                		
		                		
		                		
							},
							errorTextFormat: function (data) {
								return 'Error: ' + data.responseText
							},
							onSelectRow: function(id) {
								if (id && id !== lastSel) {
									jQuery("#jqGrid1").saveRow(lastSel, true, 'clientArray');
									jQuery("#jqGrid1").editRow(id, true);
									lastSel = id;
									console.log(id);
								}
							}
						});
				
				/*function validatePositive(value, column) {
                    if (value > 4)
                        return [false, "Please enter a positive value"];
                    else
                        return [true, ""];
                }*/
				

			})
 	    	.error(function(jqXHR, textStatus, errorThrown){
 	    		if(textStatus==="timeout") {
 	    			$(loaderObj).hide();
 	    			$(loaderObj).find('#errorDiv').show();
 	    		}
 	    	})
		
	
}

//oil barrel grid details and form storing in database
function validateRegGoodReceiveOil(){
	
		var billNo= $('#billNo1').val();
		var supplierId= $('#supplierId1').val();
		var contactPerson= $('#contactPerson1').val();
		var pDate= $('#pDate1').val();
		var expence= $('#expence1').val();
		var totalAmount= $('#resolutionOil').val();
		
		var contactPersonNamePattern = /^[a-zA-Z ]{2,50}$/;
		var contactPersonNamePatternRes = contactPersonNamePattern.test(contactPerson);
		
		//var billNoPattern = /^[0-9/]+$/;
		//var billNoPatternRes = billNoPattern.test(billNo);
		
		var expencePattern = /^\d+$/;
		var expencePatternRes = expencePattern.test(expence);
		
		 if(billNo != null && billNo != "" && billNo != " ")
		{
		
			 if(supplierId != null && supplierId != "" && supplierId != " ")
				{
					 
				 if(contactPerson != null && contactPerson != "" && contactPerson != " ")
					{
					 if(contactPersonNamePatternRes){
						 
						 if(pDate != null && pDate != "" && pDate != " ")
							{
								
									 if(totalAmount != null && totalAmount != "" && totalAmount != " " )
										{
										 	//If validation is success than controller will go to regGoodReceive()
										 	regGoodReceive();
										}
									 	else{
									 		alert(" Please select item from item list and modify into table by proper way. Total amount is not dispayed !");
										}
							       }
							 	
						 	else{
						 		alert("Please select purchase date !");
							}
					 }
					 else{
						 alert("Enter only Contact person Name without Special Symbols and digit ! name must be in between 2 - 50 character");
					 }
					}
				 	else{
				 		alert("Please Enter Contact person Name !");
					}
				 
				}
			 	else{
			 		alert("Please select supplier name !");
				}
		    }
		
	 	else{
	 		alert("Please Enter Bill Number !");
		}
		
		
	}

function regGoodReceive(){
document.getElementById("btnSubmit").disabled = true; 
var params= {};
var count = jQuery("#jqGrid1").jqGrid('getGridParam', 'records');
var allRowsInGrid1 = $('#jqGrid1').getGridParam('data');
var AllRows=JSON.stringify(allRowsInGrid1);
for (var i = 0; i < count; i++) {

var itemName = allRowsInGrid1[i].itemName;
	params["itemName"+i] = itemName;
	
	var hsnsacno = allRowsInGrid1[i].hsnsacno;
	params["hsnsacno"+i] = hsnsacno;
	
//	var vat = allRowsInGrid1[i].vat * 2;
	var vat = allRowsInGrid1[i].vat;
	params["vat"+i] = vat;
	
	var igst = allRowsInGrid1[i].igst;
	params["igst"+i] = igst;
	
	var gstamt = allRowsInGrid1[i].gstamt;
	params["gstamt"+i] = gstamt;

	var catName = allRowsInGrid1[i].catName;
	params["catName"+i] = catName;
	
var quantity = allRowsInGrid1[i].quantity;
	params["quantity"+i] = quantity;
	
	
var buyPrice = allRowsInGrid1[i].buyPrice;
params["buyPrice"+i] = buyPrice;


var salePrice = allRowsInGrid1[i].SalePrice;
params["salePrice"+i] = salePrice; 

var discount = allRowsInGrid1[i].discount;
params["discount"+i] = discount;

var actualprice = allRowsInGrid1[i].actualprice;

if(actualprice == undefined){
	actualprice = 0;
}
params["actualprice"+i] = actualprice;

var Total = allRowsInGrid1[i].Total;
if(Total == undefined){
	Total = 0;
}
params["Total"+i] = Total;



}


var input = document.getElementById('supplierId1'),
list = document.getElementById('supplierId_drop1'),
i,supplierId;
for (i = 0; i < list.options.length; ++i) {
if (list.options[i].value === input.value) {
	supplierId = list.options[i].getAttribute('data-value');
}
}

var billNo = $('#billNo1').val();
var contactPerson=$('#contactPerson1').val();
var vat=$('#vat1').val();
var pDate = $('#pDate1').val();
var extraDiscount = $('#extraDiscount1').val();
if(extraDiscount == ""){
	extraDiscount = 0;
}
var expence=$('#expence1').val();
if(expence == ""){
	expence = 0;
}
var txPerexpence= $('#txPerexpence1').val();
if(txPerexpence == ""){
	txPerexpence = 0;
}
var finalExpenses= $('#finalExpenses1').val();
if(finalExpenses == ""){
	finalExpenses = 0;
}
var resolution=$('#resolutionOil').val();


params["billNo"] = billNo;
params["contactPerson"] = contactPerson;
params["vat"] = vat;

params["pDate"] = pDate;
params["count"] = count;
params["extraDiscount"] = extraDiscount;
params["expence"] = expence;
params["txPerexpence"] = txPerexpence;
params["finalExpenses"] = finalExpenses;
params["resolution"] = resolution;
params["supplierId"] = supplierId;


params["methodName"] = "regGoodReceiveOil";

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











