   
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
 	//var perlitre = $("#perlitre").val();
 	var TotalBarrel = $("#TotalBarrel").val();
 	//alert("category+++++"+category)
 
 	params["itemName"] = itemName;
 	params["hsnsacno"] = hsnsacno;
   
    params["modelName"] = modelName;
    params["NoBarrel"] = NoBarrel;
    //params["perlitre"] = perlitre;
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
	
	/*var input = document.getElementById('itemName1'),
    list = document.getElementById('itemId_drop1'),
    i,catName,itemName, hsnsacno;
    for (i = 0; i < list.options.length; ++i) {
    if (list.options[i].value === input.value) {
    	itemName = list.options[i].getAttribute('data-value');
    	//catName = list.options[i].getAttribute('data-value');
    	//itemName = list.options[i].getAttribute('myvalue');
    	//hsnsacno = list.options[i].getAttribute('myvalue1');
    }
   }*/
	
	var input = document.getElementById('itemName1'),
    list = document.getElementById('itemId_drop1'),
    i,itemName;
    for (i = 0; i < list.options.length; ++i) {
    if (list.options[i].value === input.value) {
    	itemName = list.options[i].getAttribute('data-value');
    	
    }
   }
	
    itemparams={};
	//alert("ITEMID"+itemName)
	itemparams["itemName"]= itemName;
	//itemparams["catName"]= catName;
	//itemparams["hsnsacno"]= hsnsacno;
	
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
					colNames: ["ItemName","Category Name","HSN/SAC","No.of.barrel","oil per litre","Total Litre","Qty In Litres","BuyPrice","SalePrice","GST %","IGST %","TAX AMT","Discount %","DisAmt","Total","--S--"],

					colModel: [
					           { 	
					        	   name: "itemName",//PARTICULARS
					        	   width:200,
					        	 
					           },
					          
					           {
					        	   name:  "categoryName",
					        	   width: 170,
					        	   
					           },
					           {
					        	   name:  "hsnsacno",
					        	   width: 120,
					        	   
					           },
					           {
					        	   name:  "NumberofBarrel",
					        	   width: 120,
					        	   editable: true,
					        	  
					        	   
					           },
					           
					           {
					        	   name:  "oilperlitre",
					        	   width: 120,
					        	   hidden: true
					        	   
					           },
					           
					           {
					        	   name:  "TotalLitre",
					        	   width: 120,
					        	   
					           },
					           
					           
					           {
					        	   name:  "quantity",
					        	   width: 100,
					        	   //editable: true,
					        	   //required:true
					        	   hidden: true
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
			                    	var quantity = rowData['NumberofBarrel'];
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
										 	regGoodReceiveOil();
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

function regGoodReceiveOil(){
document.getElementById("btnSubmit1").disabled = true; 
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
	
	if(igst == undefined){
		igst = 0;
	}
	params["igst"+i] = igst;
	
	var gstamt = allRowsInGrid1[i].gstamt;
	params["gstamt"+i] = gstamt;

	var catName = allRowsInGrid1[i].categoryName;
	params["catName"+i] = catName;
	
    var quantity = allRowsInGrid1[i].quantity;
	params["quantity"+i] = quantity;
	
	
var buyPrice = allRowsInGrid1[i].buyPrice;
params["buyPrice"+i] = buyPrice;


var salePrice = allRowsInGrid1[i].SalePrice;
params["salePrice"+i] = salePrice; 

var NumberofBarrel = allRowsInGrid1[i].NumberofBarrel;
params["NumberofBarrel"+i] = NumberofBarrel; 

var TotalLitre = allRowsInGrid1[i].TotalLitre;
params["TotalLitre"+i] = TotalLitre; 

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
list = document.getElementById('supplierId_drop'),
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

////billing oil module grid///////////

function getProductInGridBillingOil()
{
	
	var value = document.getElementById("itemName1").value;
	var splitText = value.split(" =>");
	var productId1 = splitText[0];
	
	//var carNo = $('#carNo').val();
	
	var params= {};
	
	params["productId"]=productId1;
	params["methodName"] ="getProductInGridBillingOil";
	
	document.getElementById('productId').value = null;
	var count=0;
	var newrow;
	var rowId;
	$.post('/SMT/jsp/utility/controller.jsp',params,function(data)
	    	{
		 var jsonData = $.parseJSON(data);
		 var result = data.length;

		 if(result <= "20"){
			 alert("Stock NOT AVAILABLE !!!");
			 return true;
		 }
		 
	     $.each(jsonData,function(i,v)
			{
	         count = jQuery("#listOil").jqGrid('getGridParam', 'records'); 
		     var rowdata =$("#listOil").jqGrid('getGridParam','data');
		     var ids = jQuery("#listOil").jqGrid('getDataIDs');
		     
			  var prodName,com,bar;
			  for (var j = 0; j < count; j++) 
			  {
				  prodName = rowdata[j].itemName;
				  com = rowdata[j].categoryName;
				  //bar = rowdata[j].barcodeNo;
				 var rowId = ids[j];
				 var rowData = jQuery('#listOil').jqGrid ('getRowData', rowId);
				
				if (prodName == jsonData.offer.itemName && com == jsonData.offer.categoryName) {
			    	
			    	newrow=false;
					alert("Item Already Inserted !!!");
					var grid = jQuery("#listOil");
				    grid.trigger("reloadGrid");
			    	break;
				}
				else
				{
					newrow = true;
				}
			 }
			  
			  if(newrow == true)
				 {
					
				  //$("#list4").addRowData(i,jsonData[i]);
				  $("#listOil").addRowData(count,jsonData.offer);
					
				 }
		
		
		$("#listOil").jqGrid({
			datatype: "local",
			
			colNames:['pk_temp_id','item_id','CatName','ItemName','HSN/SAC','No.of.barrels','Total litres','Quantity','SalePrice','Discount','DiscountAmt','GST%','IGST%','Tax Amt','Total Amt'],
			colModel:[ 
			          
			          
                 {
	                name:'pk_temp_id',
	                hidden:true,
                 },    
			     {
			    	 name:'pkProductId',
			    	 hidden:true,
			     },
			    
			 	/*{
			    	 name:'barcodeNo',
			    	 width:70,				    	
			    	 
			     },*/
			     {	name:'categoryName',
			    	 width:170,
					
				},
			           
			    {	name:'itemName',
			    	 width:170,
					
				},
				 {	name:'hsnsacno',
			    	 width:100,
					
				},
				 {	name:'NumberofBarrel',
			    	 width:100,
					
				},
				 {	name:'TotalLitre',
			    	 width:100,
					
				},
				
				{	name:'quantity',
					width:70,
					editable: true
					
				},
				
				{	name:'salePrice',
					width:100,
					editable: true
					
				},
				/*{	name:'SalePriceExTax',
					width:100,
					editable: true
					
				},*/
				
				{	name:'discount',
					width:100,
					editable: true
					
				},
				
				{	name:'discountAmt',
					width:100,
					
					
				},
				
				{	name:'vat',
					width:80,
					editable: true
				},
				{	name:'igst',
					width:80,
					editable: true
				},
				{	name:'taxAmt',
					width:150,
					formatter: 'number',
				},
				{	name:'total',
					width:150,
					formatter: 'number',
				//	formatter: sumFmatter
					
				},
				
			],
				
			
			sortorder : 'desc',
			loadonce: false,
			viewrecords: true,
			width: 1250,
           // height: 200,
           // rowheight: 300,
			shrinkToFit: true,
            hoverrows: true,
	        rownumbers: true,
            rowNum: 10,
            'cellEdit':true,
	           afterSaveCell: function () {
	        	   // $(this).trigger('reloadGrid');
	        	var rowId =$("#listOil").jqGrid('getGridParam','selrow');  
                var rowData = jQuery("#listOil").getRowData(rowId);
             	var quantity = rowData['quantity'];
             	var salePrice = rowData['salePrice'];
             	var discount = rowData['discount'];
             	var discountAmt = rowData['discountAmt'];
             	var gst = rowData['vat'];
            	var igst = rowData['igst'];
            	var SalePriceExTax = rowData['SalePriceExTax'];
            	var tota = 0;
            	var vatAmt = 0;
            	var totAmt = 0;
            	
            	
            	if(gst != "")
				{
					var IDecs = /^[0-9]+$/;
					if(gst.match(IDecs))
					{
						(gst > Number(0))
						{
						
						}

					}
				else{
					var abc ="0";
					var pqr ="0"
					alert(" Please Enter GST Number OR IGST Number ");
					$("#listOil").jqGrid("setCell",rowId, "igst", abc);

					$("#listOil").jqGrid("setCell",rowId, "gst", abc);
					$("#listOil").jqGrid("setCell",rowId, "taxAmt", pqr);
					return false;
					}
				
				}
            	
            	if(igst != "")
				{
					var IDecs1 = /^[0-9]+$/;
					if(igst.match(IDecs1))
					{
						(igst > Number(0))
						{
						
						}

					}
				else{
					var abc ="0";
					var pqr ="0"
					alert(" Please Enter GST Number OR IGST Number ");
					$("#listOil").jqGrid("setCell",rowId, "igst", abc);

					$("#listOil").jqGrid("setCell",rowId, "gst", abc);
					$("#listOil").jqGrid("setCell",rowId, "taxAmt", pqr);
					return false;
					}
				
				}
            	
            	if(igst >0 && gst > 0 )
				{
				var abc ="0";
				alert(" Please Enter GST Number OR IGST Number");
				$("#listOil").jqGrid("setCell",rowId, "igst", abc);

				$("#listOil").jqGrid("setCell",rowId, "gst", abc);
				return false;
				}
            	
            	tota = quantity * salePrice;
            	totAmt = quantity * salePrice;
            	
            	var totalIncDisc= (tota*(discount/100));
            	var finalTotal= totAmt-totalIncDisc;
            	$("#listOil").jqGrid("setCell", rowId, "discountAmt", totalIncDisc);
             
            	/*if(gst != "0"){
            		//vatAmt =  ((totalIncDisc*gst)/100);
            		vatAmt = (gst / 100)*totalIncDisc;
            		totAmt = +totalIncDisc + +vatAmt;
            	}
            	if(igst != "0"){
            		vatAmt =  ((totalIncDisc*igst)/100);
            		totAmt = +totalIncDisc + +vatAmt;
            	}*/
            	
            	if(igst ==null || igst==0 || igst==""){
            		
                	
                	var calculateVatTotal = (gst / 100)*finalTotal;
                	var totalWithVatAmt = Number(finalTotal)+Number(calculateVatTotal)
                	//var totalWithVatAmtTot= Math.round(totalWithVatAmt * 100.0) / 100.0;
                	}
                	else if(igst !=null || igst!=0|| igst!=""){
                		
                		var calculateVatTotal = (igst / 100)*finalTotal;
                    	var totalWithVatAmt = Number(finalTotal)+Number(calculateVatTotal)
                    	//var totalWithVatAmtTot= Math.round(totalWithVatAmt * 100.0) / 100.0;
                	}
            	
            	$("#listOil").jqGrid("setCell", rowId, "taxAmt", calculateVatTotal);
             	$("#listOil").jqGrid("setCell", rowId, "total", totalWithVatAmt);
             	var Total = 0;
            	var count = jQuery("#listOil").jqGrid('getGridParam', 'records');
				var allRowsInGrid1 = $('#listOil').getGridParam('data');
				var AllRows=JSON.stringify(allRowsInGrid1);
                for (var k = 0; k < count; k++) {
            	var Total1 = allRowsInGrid1[k].total;
             	
            	if(Total1 != undefined){
            		Total = +Total + +Total1;
            	}
                }
                    document.getElementById("totalAmountOil").value = Math.round(Total);
                    var totAmount = Math.round(Total);
             	    var dis = document.getElementById("discountOil").value;
             	    if(dis != "0"){
             	    	document.getElementById("grossTotalOil").value = totAmount;
             	    }
             	    else{
             	    	document.getElementById("grossTotalOil").value = (+totAmount - +dis);
             	    }
             	
	        	},
           
			pager: "#jqGridPager",
			
			
			
		});
		
	
		
		if(count==0 || count==null)
		{
			 // $("#list4").addRowData(i,jsonData[i]);
			  $("#listOil").addRowData(0,jsonData.offer);
		}
		 $('#listOil').navGrid('#jqGridPager',
	                
	                { edit: true, add: false, del: true, search: true, refresh: true, view: true, position: "left", cloneToTop: false },
	                
	                {
	                    editCaption: "The Edit Dialog",
	                    afterSubmit: function() {
	                		$('#listOil').trigger( 'reloadGrid' );
						},
						 recreateForm: true,
						 checkOnUpdate : true,
						 checkOnSubmit : true,
		                 closeAfterEdit: true,
						
	                    errorTextFormat: function (data) {
	                        return 'Error: ' + data.responseText
	                    }
	                },
	                
	                {
	                	afterSubmit: function() {
	                		$('#listOil').trigger( 'reloadGrid' );
						},
	                    closeAfterAdd: true,
	                    recreateForm: true,
	                    errorTextFormat: function (data) {
	                        return 'Error: ' + data.responseText
	                    }
	                },
	                
	                {
	                	closeAfterdel:true,
	                	checkOnUpdate : true,
						checkOnSubmit : true,
						recreateForm: true,
	                	afterComplete: function () {
	                		
	                		var rowId =$("#listOil").jqGrid('getGridParam','selrow');  
	                        var rowData = jQuery("#listOil").getRowData(rowId);
	                     	var quantity = rowData['quantity'];
	                     	var salePrice = rowData['salePrice'];
	                     	var discount = rowData['discount'];
	                     	var discountAmt = rowData['discountAmt'];
	                     	var gst = rowData['vat'];
	                    	var igst = rowData['igst'];
	                    	var SalePriceExTax = rowData['SalePriceExTax'];
	                    	var tota = 0;
	                    	var vatAmt = 0;
	                    	var totAmt = 0;
	                    	
	                    /*	
	                    	if(gst != "")
	        				{
	        					var IDecs = /^[0-9]+$/;
	        					if(gst.match(IDecs))
	        					{
	        						(gst > Number(0))
	        						{
	        						
	        						}

	        					}
	        				else{
	        					var abc ="0";
	        					var pqr ="0"
	        					alert(" Please Enter GST Number OR IGST Number ");
	        					$("#list4").jqGrid("setCell",rowId, "igst", abc);

	        					$("#list4").jqGrid("setCell",rowId, "gst", abc);
	        					$("#list4").jqGrid("setCell",rowId, "taxAmount", pqr);
	        					return false;
	        					}
	        				
	        				}
	                    	
	                    	if(igst != "")
	        				{
	        					var IDecs1 = /^[0-9]+$/;
	        					if(igst.match(IDecs1))
	        					{
	        						(igst > Number(0))
	        						{
	        						
	        						}

	        					}
	        				else{
	        					var abc ="0";
	        					var pqr ="0"
	        					alert(" Please Enter GST Number OR IGST Number ");
	        					$("#list4").jqGrid("setCell",rowId, "igst", abc);

	        					$("#list4").jqGrid("setCell",rowId, "gst", abc);
	        					$("#list4").jqGrid("setCell",rowId, "taxAmount", pqr);
	        					return false;
	        					}
	        				
	        				}
	                    	
	                    	if(igst >0 && gst > 0 )
	        				{
	        				var abc ="0";
	        				alert(" Please Enter GST Number OR IGST Number");
	        				$("#list4").jqGrid("setCell",rowId, "igst", abc);

	        				$("#list4").jqGrid("setCell",rowId, "gst", abc);
	        				return false;
	        				}*/
	                    	
	                    	tota = quantity * salePrice;
	                    	totAmt = quantity * salePrice;
	                    	
	                    	var totalIncDisc= (tota*(discount/100));
	                    	var finalTotal= totAmt-totalIncDisc;
	                    	$("#listOil").jqGrid("setCell", rowId, "discountAmt", totalIncDisc);
	                     
	                    	/*if(gst != "0"){
	                    		//vatAmt =  ((totalIncDisc*gst)/100);
	                    		vatAmt = (gst / 100)*totalIncDisc;
	                    		totAmt = +totalIncDisc + +vatAmt;
	                    	}
	                    	if(igst != "0"){
	                    		vatAmt =  ((totalIncDisc*igst)/100);
	                    		totAmt = +totalIncDisc + +vatAmt;
	                    	}*/
	                    	
	                    	if(igst ==null || igst==0 || igst==""){
	                    		
	                        	
	                        	var calculateVatTotal = (gst / 100)*finalTotal;
	                        	var totalWithVatAmt = Number(finalTotal)+Number(calculateVatTotal)
	                        	//var totalWithVatAmtTot= Math.round(totalWithVatAmt * 100.0) / 100.0;
	                        	}
	                        	else if(igst !=null || igst!=0|| igst!=""){
	                        		
	                        		var calculateVatTotal = (igst / 100)*finalTotal;
	                            	var totalWithVatAmt = Number(finalTotal)+Number(calculateVatTotal)
	                            	//var totalWithVatAmtTot= Math.round(totalWithVatAmt * 100.0) / 100.0;
	                        	}
	                    	
	                    	$("#listOil").jqGrid("setCell", rowId, "taxAmt", calculateVatTotal);
	                     	$("#listOil").jqGrid("setCell", rowId, "total", totalWithVatAmt);
	                     	var Total = 0;
	                    	var count = jQuery("#listOil").jqGrid('getGridParam', 'records');
	        				var allRowsInGrid1 = $('#listOil').getGridParam('data');
	        				var AllRows=JSON.stringify(allRowsInGrid1);
	                        for (var k = 0; k < count; k++) {
	                    	var Total1 = allRowsInGrid1[k].total;
	                     	
	                    	if(Total1 != undefined){
	                    		Total = +Total + +Total1;
	                    	}
	                        }
	                            document.getElementById("totalAmountOil").value = Math.round(Total);
	                            var totAmount = Math.round(Total);
	                     	    var dis = document.getElementById("discountOil").value;
	                     	    if(dis != "0"){
	                     	    	document.getElementById("grossTotalOil").value = totAmount;
	                     	    }
	                     	    else{
	                     	    	document.getElementById("grossTotalOil").value = (+totAmount - +dis);
	                     	    }
	                     	
		                    	
							},
	                });
		 
		 
			   });
			
			})
}

//register oil  in database
function resotherbill(){

	resOtherBill();
	}
function resOtherBill(){
	document.getElementById("btnSubmit").disabled = true; 
	var params= {};
	var count = jQuery("#listOil").jqGrid('getGridParam', 'records');
	var allRowsInGrid1 = $('#listOil').getGridParam('data');
	var AllRows=JSON.stringify(allRowsInGrid1);
	for (var i = 0; i < count; i++) {
	
		var pk_temp_id = allRowsInGrid1[i].pk_temp_id;
     	params["pk_temp_id"+i] = pk_temp_id;
		
		var item_id = allRowsInGrid1[i].pkProductId;
     	params["item_id"+i] = item_id;
     	
		var itemName = allRowsInGrid1[i].itemName;
		params["itemName"+i] = itemName;
		
		var quantity = allRowsInGrid1[i].quantity;
		params["quantity"+i] = quantity;
		
		/*var barcodeNo = allRowsInGrid1[i].barcodeNo;
		params["barcodeNo"+i] = barcodeNo;
		*/
		var categoryName = allRowsInGrid1[i].categoryName;
		params["categoryName"+i] = categoryName;

		var salePrice = allRowsInGrid1[i].salePrice;
		params["salePrice"+i] = salePrice;
		
		var total = allRowsInGrid1[i].total;
		params["total"+i] = total;
		
		var hsnSacNo = allRowsInGrid1[i].hsnsacno;
		params["hsnSacNo"+i] = hsnSacNo;
		
		var vat = allRowsInGrid1[i].vat;
		params["vat"+i] = vat;
		
		var igst = allRowsInGrid1[i].igst;
		params["igst"+i] = igst;
		
		var taxAmount = allRowsInGrid1[i].taxAmt;
		params["taxAmount"+i] = taxAmount;
		
		var discountGrid = allRowsInGrid1[i].discount;
		params["discountGrid"+i] = discountGrid;
		
		var discountAmt = allRowsInGrid1[i].discountAmt;
		params["discountAmt"+i] = discountAmt;
		
		var NumberofBarrel = allRowsInGrid1[i].NumberofBarrel;
		params["NumberofBarrel"+i] = NumberofBarrel;
		
		var TotalLitre = allRowsInGrid1[i].TotalLitre;
		params["TotalLitre"+i] = TotalLitre;
		
	}
	
	    var totalAmount=$('#totalAmountOil').val();
	    var discount=$('#discountOil').val();
	    if(discount == ""){
	    	discount = 0;
	    }
	    var grossTotal=$('#grossTotalOil').val();
	    
		params["count"] = count;
		params["totalAmount"] = totalAmount;
		params["discount"] = discount;
		params["grossTotal"] = grossTotal;
		
	    params["methodName"] = "registerOtherBill1";
	    
		$.post('/SMT/jsp/utility/controller.jsp',params,function(data)
		    	{  
			           alert(data);
					   window.open("Other_Bill_PDF.jsp");
					   location.reload(true);
			
				 }
		    	).error(function(jqXHR, textStatus, errorThrown){
		    		if(textStatus==="timeout") {
		    			$(loaderObj).hide();
		    			$(loaderObj).find('#errorDiv').show();
		    		}
		    	});
	
}

////List of barrel entry form//////

function getAllMAinItem(){
	
	
	var params= {};
	
	params["methodName"] = "getAllMAinItemOil";

	$.post('/SMT/jsp/utility/controller.jsp',params,function(data)
			{
		
		$('#itemName').dataTable().fnClearTable();
		
		var jsonData = $.parseJSON(data);
		var catmap = jsonData.list;
		
		
		
		$(document).ready(function() {
		 var total =   $('#itemName').DataTable( {
			 
			 dom: 'Bfrtip',
	         buttons: [
	             'copy', 'csv', 'excel', 'pdf', 'print'
	         ],
			 
			 fnRowCallback : function(nRow, aData, iDisplayIndex){
	                $("th:first", nRow).html(iDisplayIndex +1);
	               return nRow;
	            },
	            
			    
	            "sPaginationType": "full_numbers",
		    	destroy: true,
		        searching: true,
		        orderable: true,
		        
		      
		columns: [
		          	{"data": "serialnumber", "width": "5%", "defaultContent": ""},
                    {"data": "categoryName", "width": "5%", "defaultContent": ""},
		            {"data": "item_name", "width": "5%", "defaultContent": ""},
		            {"data": "hsnsacno", "width": "5%", "defaultContent": ""},
		            {"data": "modelName", "width": "5%", "defaultContent": ""},
		            {"data": "NumberofBarrel", "width": "5%", "defaultContent": ""},
		            {"data": "TotalLitre", "width": "5%", "defaultContent": ""},
		            
		            
		        ],
		      
		        
		    } );
		} );
		
	var mydata = catmap;
	$('#itemName').dataTable().fnAddData(mydata);
	
		}

	);
	
}	