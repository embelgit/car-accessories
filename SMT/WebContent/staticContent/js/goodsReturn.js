function purchasereturn(){
	
	if(document.supd.supplierId.value == "")
	{
		alert("Enter Supplier Name.");
		return false;
	}	
	if(document.supd.billNo.value == "")
	{
		alert("Select Bill No.");
		return false;
	}	
	purchaseReturn();
}

function purchaseReturn() {

	var params = {};

	
	var count = jQuery("#jqGrid").jqGrid('getGridParam', 'records');
	var allRowsInGrid = $('#jqGrid').getGridParam('data');

	
    var totalAmount = 0;
	var AllRows = JSON.stringify(allRowsInGrid);
	for (var i = 0; i < count; i++) {

		var PkGoodRecId = allRowsInGrid[i].PkGoodRecId;
		params["PkGoodRecId" + i] = PkGoodRecId;
		
		var catName = allRowsInGrid[i].catName;
		params["catName" + i] = catName;
		
		var itemName = allRowsInGrid[i].itemName;
		params["itemName" + i] = itemName;

		var quantity = allRowsInGrid[i].quantity;
		params["quantity" + i] = quantity;

		var availquantity = allRowsInGrid[i].availquantity;
		params["availquantity"+i] = availquantity;

		var buyPrice = allRowsInGrid[i].buyPrice;
		params["buyPrice" + i] = buyPrice;

		var vat = allRowsInGrid[i].vat;
		params["vat" + i] = vat;

		var total = allRowsInGrid[i].total;
		params["total" + i] = total;

		var contactPerson = allRowsInGrid[i].contactPerson;
		params["contactPerson" + i] = contactPerson;

	    var barcodeNo = allRowsInGrid[i].barcodeNo;
		params["barcodeNo"+i] = barcodeNo;
		
		var ondate = allRowsInGrid[i].ondate;
		params["ondate"+i] = ondate;


		var totalAmount = totalAmount + total ;
	}

	var supplierId = $('#supplierId').val();
	var billNo = $('#billNo').val();
	
	params["totalAmount"] = totalAmount;
	params["billNo"] = billNo;
	params["supplierId"] = supplierId;

	params["count"] = count;

	params["methodName"] = "returngoodsReceipt";
	
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

function getItemDetails()

{

	this.getitems = getitems;

	//var items="";

	/********************************** code for GET ITEM DETAILS BY CATEGORY*******************************************/

	function sumFmatter(cellvalue, options, rowObject) {
		return options.rowData.quantity * options.rowData.unitPrice;

	}

	function getitems() {
		var dd ;
		var Expence = 0;
		$("#jqGrid").jqGrid("clearGridData");
		//$("#jqGrid").jqGrid("clearGridData");
		var billNo = $('#billNo').val();
		
		

		var input = document.getElementById('supplierId'),
		list = document.getElementById('supplierId_drop'),
		i,supplierId;
		for (i = 0; i < list.options.length; ++i) {
			if (list.options[i].value === input.value) {
				supplierId = list.options[i].getAttribute('data-value');
			}

			var supplierId=supplierId;
		}
		alert(supplierId)
		var params = {};
		params["methodName"] = "getItemsByBill";
		params["billNo"] = billNo;
		params["supplierId"] = supplierId;
		
		$.post(
				'/SMT/jsp/utility/controller.jsp',
				params,
				function(data) {

					var jsonData = $.parseJSON(data);
					var catmap = jsonData.list;
					
					var grid = $("#jqGrid"),
				    intervalId = setInterval(
				        function() {
				            grid.trigger("reloadGrid",[{current:true}]);
				        },
				        100); 
					$.each(jsonData, function(i, v) {

						Expence = v.expence;
						
						document.getElementById('expen').value = Expence;
						
						
						
						function sumFmatter (cellvalue, options, rowObject)
						{
							
							var itemparams={};
							var jam=0;
							var tot= (options.rowData.quantity * options.rowData.unitPrice);
							var shree = document.returnGoods.resolution.value;
							
							
							/*var ex = document.returnGoods.expen.value;
							var dec_num = parseInt(ex);*/
							var count = jQuery("#jqGrid").jqGrid('getGridParam', 'records');
							var allRowsInGrid1 = $('#jqGrid').getGridParam('data');
							var AllRows=JSON.stringify(allRowsInGrid1);
							for (var i = 0; i < count; i++) {

								var quantity = allRowsInGrid1[i].quantity;
								itemparams["quantity"+i] = quantity;

								var unitPrice = allRowsInGrid1[i].unitPrice;
								itemparams["unitPrice"+i] = unitPrice;

								
								var totals1=((unitPrice)*(quantity));
								
								jam = jam + totals1;
							}	
							
							
							
							document.getElementById("resolution").value = jam+Expence;
							return tot;

						}
						

						
						$("#jqGrid").jqGrid(
								{

									datatype : "local",

									colNames : [ "itemID", "Item Name",
											"Color", "Quantity", "Price",
											"supplier_id", "Supplier Name",
											"OrderDate", "Total" ],

									colModel : [ {
										name : "itemID",
										hidden : true
									}, {
										name : "productName",
										width : 100,
									//resizable: true,

									},

									{
										name : "color",
										width : 140,
										editable : true
									},

									{
										name : "quantity",
										width : 140,
										editable : true
									// must set editable to true if you want to make the field editable
									},

									{
										name : 'unitPrice',
										width : 140,
										editable : true
									}, {
										name : 'supplier_id',

										hidden : true
									},

									{
										name : 'supplierName',
										width : 140,
										editable : true
									},

									{
										name : 'insDate',
										width : 280,
										editable : true
									},
										
								
									{
										label : 'Total',
										name : "total",
										formatter : sumFmatter,
										width : 150,

									}

									],

									sortorder : 'desc',
									loadonce: false,
									footerrow: true,
									userDataOnFooter: true,		
									idPrefix : "a",
									viewrecords: true,
									width: 1090,
									shrinkToFit: true,
									rownumbers: true,
					                rowNum: 25,
								
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
										
										

										// options for the Delete Dailog
										{
											closeAfterdel : true,
											recreateForm : true,
											errorTextFormat : function(data) {
												return 'Error: '
														+ data.responseText
											},

											onSelectRow : function(id) {
												if (id && id !== lastSel) {
													jQuery("#jqGrid").saveRow(
															lastSel, true,
															'clientArray');
													jQuery("#jqGrid").editRow(
															id, true);
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



function getAllBills()
{
	
	 var input = document.getElementById('supplierId'),
     list = document.getElementById('supplierId_drop'),
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
	
	params["methodName"] = "getAllBillBySuppliers";
	
	params["supplier"]= supplier;
	
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


function getitems() {
	
	
	//$("#jqGrid").jqGrid("clearGridData");
	var billNo = $('#billNo').val();
	
	

	 var input = document.getElementById('supplierId'),
     list = document.getElementById('supplierId_drop'),
	i,supplierId;
	for (i = 0; i < list.options.length; ++i) {
		if (list.options[i].value === input.value) {
			supplierId = list.options[i].getAttribute('data-value');
		}
	}
	
	
	
	var params = {};
	params["methodName"] = "getTotalItemByBillNo";
	params["billNo"] = billNo;
	params["supplierId"] = supplierId;
	
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

								colNames : [ "itemID","CategoryName", "Item Name",
										 "Quantity", "Avail Quant", "BuyPrice",
										"Vat", "Total","ContactPerson",
										"BarcodeNo", "Date" ],

								colModel : [ {
									name : "PkGoodRecId",
									hidden : true
								}, {
									name : "catName",
									width : 120,
								//resizable: true,

								},

								{
									name : "itemName",
									width : 140,
									
								},

								{
									name : "quantity",
									width : 100,
								// must set editable to true if you want to make the field editable
								},
								{
									name : "availquantity",
									width : 100,
									editable : true
								// must set editable to true if you want to make the field editable
								},

								{
									name : 'buyPrice',
									width : 100,
									
								},
								

								{
									name : 'vat',
									width : 70,
									
								},
									
							
								{
									name : "total",
									width : 150,
									formatter: 'integer',
								},
								{
									name : 'contactPerson',
									width : 160,
									
								},
								{
									name : 'barcodeNo',
									width : 100,
									
								},
								{
									name : 'ondate',
									width : 150,
									
								}

								],

								loadonce: false,
								viewrecords: true,
								width: 1150,
								shrinkToFit: true,
				                rowList : [20,30,50],
				                rownumbers: true,
				                rowNum: 10,
				                'cellEdit':true,
				                //"userdata" : {"Total": "2622.99"},
				                afterSaveCell: function () {
				 	        	   // $(this).trigger('reloadGrid');
				 	        	   var rowId =$("#jqGrid").jqGrid('getGridParam','selrow');  
				                   var rowData = jQuery("#jqGrid").getRowData(rowId);
				              	   var quantity = rowData['quantity'];
				              	   var availquantity = rowData['availquantity'];
				              	   var buyPrice = rowData['buyPrice'];
				              	   
				              	   var afterquantity = quantity - availquantity;
				              	   
				              	   var tota = afterquantity * buyPrice;
				              	
				              	   $("#jqGrid").jqGrid("setCell", rowId, "total", tota);
				              	    var parseTotal=  $(this).jqGrid('getCol', 'total', false, 'sum');
				                	$(this).jqGrid('footerData', 'set', { vat: "Total :" });
				                    $(this).jqGrid('footerData', 'set', { total: parseTotal});
				 	        	},
				                footerrow: true, // set a footer row
					
				                gridComplete: function() {
				                	
				                	var parseTotal=  $(this).jqGrid('getCol', 'total', false, 'sum');
				                	$(this).jqGrid('footerData', 'set', { vat: "Total :" });
				                    $(this).jqGrid('footerData', 'set', { total: parseTotal});
				    	        	
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

function initGrid() {
    $(".jqgrow", "#jqGrid").contextMenu('contextMenu', {
        bindings: {
            'edit': function (t) {
                editRow();
            },
            'add': function (t) {
                addRow();
            },
            'del': function (t) {
                delRow();
            }
        },
        onContextMenu: function (event, menu) {
            var rowId = $(event.target).parent("tr").attr("id")
            var grid = $("#jqGrid");
            grid.setSelection(rowId);

            return true;
        }
    });

    function addRow() {
        var grid = $("#jqGrid");
        grid.editGridRow("new", { closeAfterAdd: true});
    }

    function editRow() {
        var grid = $("#jqGrid");
        var rowKey = grid.getGridParam("selrow");
        if (rowKey) {
            grid.editGridRow(rowKey, {closeAfterEdit: true});
        }
        else {
            alert("No rows are selected");
        }
    }

    function delRow() {
        var grid = $("#jqGrid");
        var rowKey = grid.getGridParam("selrow");
        if (rowKey) {
            grid.delGridRow(rowKey);
        }
        else {
            alert("No rows are selected");
        }
    }
}