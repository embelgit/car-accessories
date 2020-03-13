function saleReturn() {

	var params = {};

	
	var count = jQuery("#jqGrid").jqGrid('getGridParam', 'records');
	var allRowsInGrid = $('#jqGrid').getGridParam('data');

	
    var totalAmount = 0;
	var AllRows = JSON.stringify(allRowsInGrid);
	for (var i = 0; i < count; i++) {

		var pkBillId = allRowsInGrid[i].pkBillId;
		params["pkBillId" + i] = pkBillId;
		
		var carNo = allRowsInGrid[i].carNo;
		params["carNo" + i] = carNo;
		
		var categoryName = allRowsInGrid[i].categoryName;
		params["categoryName" + i] = categoryName;

		var itemName = allRowsInGrid[i].itemName;
		params["itemName" + i] = itemName;

		var barcodeNo = allRowsInGrid[i].barcodeNo;
		params["barcodeNo"+i] = barcodeNo;

		var quantity = allRowsInGrid[i].quantity;
		params["quantity" + i] = quantity;

		var editQuantity = allRowsInGrid[i].editQuantity;
		params["editQuantity" + i] = editQuantity;

		var salePrice = allRowsInGrid[i].salePrice;
		params["salePrice" + i] = salePrice;

		var contactNo = allRowsInGrid[i].contactNo;
		params["contactNo" + i] = contactNo;

	    var totalAmt = allRowsInGrid[i].totalAmt;
		params["totalAmt"+i] = totalAmt;
		
		var discount = allRowsInGrid[i].discount;
		params["discount"+i] = discount;
		
		var grossamt = allRowsInGrid[i].grossamt;
		params["grossamt"+i] = grossamt;
		
		var Date = allRowsInGrid[i].Date;
		params["Date"+i] = Date;

	}

	var billNo = $('#billNo').val();
	params["billNo"] = billNo;
	params["count"] = count;

	params["methodName"] = "returnSale";
	
	$.post('/SMT/jsp/utility/controller.jsp', params, function(data) {
		
		
		alert(data);
		location.reload();
		
		
	}).error(function(jqXHR, textStatus, errorThrown) {
		if (textStatus === "timeout") {
			$(loaderObj).hide();
			$(loaderObj).find('#errorDiv').show();
		}
	});

}






function getSaleItems() {
	
	var billNo = $('#billNo').val();

	var params = {};
	params["methodName"] = "getSaleItemByBillNo";
	params["billNo"] = billNo;

	$.post(
			'/SMT/jsp/utility/controller.jsp',
			params,
			function(data) {
				
				$("#jqGrid").jqGrid("clearGridData");

				var jsonData = $.parseJSON(data);
				var catmap = jsonData.list;
				
				
				$.each(jsonData, function(i, v) {

				
					
					
					$("#jqGrid").jqGrid(
							{

								datatype : "local",

								colNames : [ "itemID","Car Name", "Category Name","Item Name","Barcode No",
										 "Quantity", "Return Quant", "SalePrice",
										"Contact No", "Total","Discount",
										"GrossAmt", "Date" ],

								colModel : [ {
									name : "pkBillId",
									hidden : true
								}, {
									name : "carNo",
									width : 120,
								},
								{
									name : "categoryName",
									width : 140,
								},
								{
									name : "itemName",
									width : 100,
								},
								{
									name : "barcodeNo",
									width : 100,
								},
								{
									name : 'quantity',
									width : 70,
									
								},								
								{
									name : 'editQuantity',
									width : 70,
									editable : true
								},
								{
									name : "salePrice",
									width : 100,
								},
								{
									name : "contactNo",
									width : 120,
								},	
								{
									name : "totalAmt",
									width : 150,
									formatter: 'number',
								},
								{
									name : 'discount',
									width : 50,
								},
								{
									name : 'grossamt',
									width : 140,
									formatter: 'number',
									
								},
								{
									name : 'Date',
									width : 140,
									
								}

								],

								loadonce: false,
								viewrecords: true,
								width: 1150,
								shrinkToFit: true,
				                rowList : [10,20,50],
				                rownumbers: true,
				                rowNum: 10,
				                'cellEdit':true,
				                //"userdata" : {"Total": "2622.99"},
				                afterSaveCell: function () {
				 	        	   // $(this).trigger('reloadGrid');
				 	        	   var rowId =$("#jqGrid").jqGrid('getGridParam','selrow');  
				                   var rowData = jQuery("#jqGrid").getRowData(rowId);
				              	   var quantity = rowData['quantity'];
				              	   var editQuantity = rowData['editQuantity'];
				              	   var salePrice = rowData['salePrice'];
				              	   var discount = rowData['discount'];
				              	   
				              	   var afterquantity = quantity - editQuantity;
				              	   
				              	   var tota = afterquantity * salePrice;
				              	   
				              	   if(tota == 0){
				              		   
				              		 $("#jqGrid").jqGrid("setCell", rowId, "grossamt", tota);
				              	   }
				              	   else{
				              		   var gross = ((discount/100)*tota) + tota;
				              		 $("#jqGrid").jqGrid("setCell", rowId, "grossamt", gross);
				              		   
				              	   }
				              	
				              	   $("#jqGrid").jqGrid("setCell", rowId, "totalAmt", tota);
				              	    var parseTotal=  $(this).jqGrid('getCol', 'totalAmt', false, 'sum');
				              	    var parseTotal1=  $(this).jqGrid('getCol', 'grossamt', false, 'sum');
				                	$(this).jqGrid('footerData', 'set', { contactNo: "Total :" });
				                    $(this).jqGrid('footerData', 'set', { totalAmt: parseTotal});
				                    $(this).jqGrid('footerData', 'set', { discount: "Final Total :" });
				                    $(this).jqGrid('footerData', 'set', { grossamt: parseTotal1});
				 	        	},
				                footerrow: true, // set a footer row
					
				                gridComplete: function() {
				                	
				                	var parseTotal=  $(this).jqGrid('getCol', 'totalAmt', false, 'sum');
				                	var parseTotal1=  $(this).jqGrid('getCol', 'grossamt', false, 'sum');
				                	$(this).jqGrid('footerData', 'set', { contactNo: "Total :" });
				                    $(this).jqGrid('footerData', 'set', { totalAmt: parseTotal});
				                    $(this).jqGrid('footerData', 'set', { discount: "Final Total :" });
				                    $(this).jqGrid('footerData', 'set', { grossamt: parseTotal1});
				                },
							
								pager : "#jqGridPager",
								
							});

					$("#jqGrid").addRowData(i, jsonData[i]);
					
					$('#jqGrid')
							.navGrid(
									'#jqGridPager',
									// the buttons to appear on the toolbar of the grid
									 { edit: true, add: true, del: true, search: true, refresh: true, view: true, position: "left", cloneToTop: false },
									// options for the Edit Dialog
									{
										editCaption : "The Edit Dialog",
										recreateForm : true,
										checkOnUpdate : true,
										checkOnSubmit : true,
										closeAfteredit : true,
										errorTextFormat : function(data) {
											return 'Error: '
													+ data.responseText
										}
									},
									
									{},

									// options for the Delete Dailog
									{
										closeAfterdel : true,
										refreshAfterdel : true,
										recreateForm : true,
										checkOnUpdate : true,
										checkOnSubmit : true,
										
										errorTextFormat : function(data) {
											return 'Error: '
													+ data.responseText
													
													
										},
									});

				});

			}).error(function(jqXHR, textStatus, errorThrown) {
		if (textStatus === "timeout") {

		}
	});

}



function getAllSaleReturn()
{
	
		var params = {};

		var namePresent;
		var count = jQuery("#jqGrid").jqGrid('getGridParam', 'records');
		var allRowsInGrid = $('#jqGrid').getGridParam('data');

		var action = new Array();

		var AllRows = JSON.stringify(allRowsInGrid);
		for (var i = 0; i < count; i++) {

			var idVal = "";
			if (i != 0) {
				idVal = i;
			}
			var itemId = allRowsInGrid[i].itemId;
			params["itemId" + i] = itemId;

			var color = allRowsInGrid[i].color;
			params["color" + i] = color;
			
			var customerBill = allRowsInGrid[i].customerBill;
			params["customerBill" + i] = customerBill;

			/*var size = allRowsInGrid[i].size;
			params["size"+i] = size;*/

			var quantity = allRowsInGrid[i].quantity;
			params["quantity" + i] = quantity;

			var SalePrice = allRowsInGrid[i].SalePrice;
			params["SalePrice" + i] = SalePrice;

			var totalAmount = allRowsInGrid[i].totalAmount;
			params["totalAmount" + i] = totalAmount;
			
			var netAmount = allRowsInGrid[i].netAmount;
			params["netAmount" + i] = netAmount;

			var soldDate = allRowsInGrid[i].soldDate;
			params["soldDate" + i] = soldDate;
			
			var itemName = allRowsInGrid[i].itemName;
			params["itemName" + i] = itemName;

			var color = allRowsInGrid[i].color;
			params["color" + i] = color;

			 var discountforsalereturn = allRowsInGrid[i].discountforsalereturn;
			params["discountforsalereturn"+i] = discountforsalereturn;

			var total = params["unitPrice" + i] * params["quantity" + i];

			params["total" + i] = total;
			for (var int = 0; int < count.length; int++) {

			}
			// params[(params["unitPrice"+i]*params["quantity"+i])]=totalAmount;
		}

		var customerBill = $('#customerBill').val();
		params["customerBill"] = customerBill;

		params["count"] = count;

		params["methodName"] = "saleReturnReceipt";
		/*var dd = Object.keys(params).length;
		console.log(count);*/
		$.post('/SMT/jsp/utility/controller.jsp', params, function(data) {
			alert(data);
		}).error(function(jqXHR, textStatus, errorThrown) {
			if (textStatus === "timeout") {
				$(loaderObj).hide();
				$(loaderObj).find('#errorDiv').show();
			}
		});

	



}









function getItemDetails()

{

	this.getitems = getitems;

	//var items="";

	/********************************** code for GET ITEM DETAILS BY CATEGORY*******************************************/

	function sumFmatter(cellvalue, options, rowObject) {
		return options.rowData.quantity * options.rowData.unitPrice;

	}

	function getitems() {

		$("#jqGrid").jqGrid("clearGridData");
		//$("#jqGrid").jqGrid("clearGridData");
		var customerBill = $('#customerBill').val();

		var params = {};
		params["methodName"] = "getItemsBySaleBill";
		params["customerBill"] = customerBill;
		$.post(
				'/SMT/jsp/utility/controller.jsp',params,function(data) {

					var jsonData = $.parseJSON(data);
					var catmap = jsonData.list;
					
					
					function sumFmatter (cellvalue, options, rowObject)
			        {
			            
			        	
			        	var jam=0;
			        	var jam1="";
			        	var tot= (options.rowData.quantity * options.rowData.SalePrice);
			        	var shree = document.good1.resolution.value;
			     
			        	var count = jQuery("#jqGrid").jqGrid('getGridParam', 'records');
			        	var allRowsInGrid1 = $('#jqGrid').getGridParam('data');
			        	var AllRows=JSON.stringify(allRowsInGrid1);
			        	for (var i = 0; i < count; i++) {
			        		
			            	var quantity = allRowsInGrid1[i].quantity;
			             	params["quantity"+i] = quantity;
			             	
			             	var SalePrice = allRowsInGrid1[i].SalePrice;
			            	params["SalePrice"+i] = SalePrice;
			            	
			            	
			            	var totals1=((SalePrice)*(quantity));
			            	
			            	
			            
			            	jam = jam + totals1;
			            	
			            	
		            	
		        	    }
			        			jam1= jam+tot;
			        		
			        		 document.getElementById("resolution").value = jam1;
			        	
			            	 return tot;

			        }
					
					$.each(jsonData, function(i, v) {

						$("#jqGrid").jqGrid(
								{

									datatype : "local",

									colNames : ["customerBill","ItemID","Employee Name", "Item Name","Color",
											 "Quantity", "Price",
											"Sale DAte", "totalAmount" ,"discount","NETTotal" ],

									colModel : [ 
									 
									            {
									            	name : "customerBill",
									            },
									  {
										name : "itemId",
										hidden : true
									},
									
									{
										name:"empName",
										width : 220,
									},
									
									{
										name : "itemName",
										width : 200,
									

									},

									{
										name:"color" ,
										width : 100,
									},

									{
										name : "quantity",
										width : 140,
										editable : true
		
									},

									{
										name : 'SalePrice',
										width : 120,
										editable : true
									}, 

									

									{
										name : 'soldDate',
										width : 200,
										editable : true
									},

									{
										label : 'Total',
										name : "totalAmount",
										 
										width : 175,

									},
									
									{
										name:"discountforsalereturn",
										width : 150
									},
									
									{
									
										name : "netAmount",
										formatter: sumFmatter,
										width : 150,
										

									}
									

									],

									sortorder : 'desc',

									loadonce : true,
									viewrecords : true,
									width : 1200,
									height : 200,
									//rowNum : 10,

									pager : "#jqGridPager"
									
								});

						$("#jqGrid").addRowData(i, jsonData[i]);

						 $('#jqGrid').navGrid('#jqGridPager',
						            // the buttons to appear on the toolbar of the grid
						            { edit: true, add: true, del: true, search: true, refresh: true, view: true, position: "left", cloneToTop: false },
						            // options for the Edit Dialog
						            {
						                editCaption: "The Edit Dialog",
						                recreateForm: true,
										checkOnUpdate : true,
										checkOnSubmit : true,
						                closeAfterEdit: true,
						                errorTextFormat: function (data) {
						                    return 'Error: ' + data.responseText
						                }
						            },
						            // options for the Add Dialog
						            {
						                closeAfterAdd: true,
						                recreateForm: true,
						                errorTextFormat: function (data) {
						                    return 'Error: ' + data.responseText
						                }
						            },
						            // options for the Delete Dailog
						            {
						            	closeAfterdel:true,
						            	 recreateForm: true,
						            	
						                errorTextFormat: function (data) {
						                    return 'Error: ' + data.responseText
						                },
						            onSelectRow: function(id) {
						                if (id && id !== lastSel) {
						                    jQuery("#jqGrid").saveRow(lastSel, true, 'clientArray');
						                    jQuery("#jqGrid").editRow(id, true);
						                    lastSel = id;
						                    console.log(id);
						                }
						            }
						    	});
						 
			 			});
			 			
				}).error(function(jqXHR, textStatus, errorThrown) {
			if (textStatus === "timeout") {

			}
		});

	}

}

var items = new getItemDetails();