function getitemData1(){ 
		var value = document.getElementById("key").value;
		var carNo = $('#carNo').val();
		
		var params= {};
		
		params["key"]=value;
		params["methodName"] ="fetchCust1";
		document.getElementById('key').value = null;
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
		         count = jQuery("#list4").jqGrid('getGridParam', 'records'); 
			     var rowdata =$("#list4").jqGrid('getGridParam','data');
			     var ids = jQuery("#list4").jqGrid('getDataIDs');
			     
				  var prodName,com,bar;
				  for (var j = 0; j < count; j++) 
				  {
					  prodName = rowdata[j].itemName;
					  com = rowdata[j].categoryName;
					  bar = rowdata[j].barcodeNo;
					 var rowId = ids[j];
					 var rowData = jQuery('#list4').jqGrid ('getRowData', rowId);
					
					if (prodName == jsonData.offer.itemName && com == jsonData.offer.categoryName && bar == jsonData.offer.barcodeNo) {
				    	
				    	newrow=false;
						alert("Item Already Inserted !!!");
						var grid = jQuery("#list4");
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
					  $("#list4").addRowData(count,jsonData.offer);
						
					 }
			
			
			$("#list4").jqGrid({
				datatype: "local",
				
				colNames:['pk_temp_id','item_id','BarcodeNO','CatName','ItemName','HSN/SAC','Quantity','SalePrice','Discount','DiscountAmt','GST%','IGST%','Tax Amt','Total Amt'],
				colModel:[ 
				          
				          
	                 {
		                name:'pk_temp_id',
		                hidden:true,
	                 },    
				     {
				    	 name:'item_id',
				    	 hidden:true,
				     },
				    
				 	{
				    	 name:'barcodeNo',
				    	 width:70,				    	
				    	 
				     },
				     {	name:'categoryName',
				    	 width:170,
						
					},
				           
				    {	name:'itemName',
				    	 width:170,
						
					},
					 {	name:'hsnSacNo',
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
					
					{	name:'discountGrid',
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
					{	name:'taxAmount',
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
		        	var rowId =$("#list4").jqGrid('getGridParam','selrow');  
	                var rowData = jQuery("#list4").getRowData(rowId);
	             	var quantity = rowData['quantity'];
	             	var salePrice = rowData['salePrice'];
	             	var discount = rowData['discountGrid'];
	             	var discountAmt = rowData['discountAmt'];
	             	var gst = rowData['vat'];
                	var igst = rowData['igst'];
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
					}
                	tota = quantity * salePrice;
                	totAmt = quantity * salePrice;
                	
                	var totalIncDisc= (tota*(discount/100));
                	var finalTotal= totAmt-totalIncDisc;
                	$("#list4").jqGrid("setCell", rowId, "discountAmt", totalIncDisc);
	             
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
                	
                	$("#list4").jqGrid("setCell", rowId, "taxAmount", calculateVatTotal);
	             	$("#list4").jqGrid("setCell", rowId, "total", totalWithVatAmt);
	             	var Total = 0;
                	var count = jQuery("#list4").jqGrid('getGridParam', 'records');
    				var allRowsInGrid1 = $('#list4').getGridParam('data');
    				var AllRows=JSON.stringify(allRowsInGrid1);
                    for (var k = 0; k < count; k++) {
                	var Total1 = allRowsInGrid1[k].total;
	             	
                	if(Total1 != undefined){
                		Total = +Total + +Total1;
                	}
                    }
                        document.getElementById("totalAmount").value = Math.round(Total);
                        var totAmount = Math.round(Total);
	             	    var dis = document.getElementById("discount").value;
	             	    if(dis != "0"){
	             	    	document.getElementById("grossTotal").value = totAmount;
	             	    }
	             	    else{
	             	    	document.getElementById("grossTotal").value = (+totAmount - +dis);
	             	    }
	             	
		        	},
	           
				pager: "#jqGridPager",
				
				
				
			});
			
		
			//$("#list4").addRowData(i+1,jsonData[i]);
			if(count==0 || count==null)
			{
				 // $("#list4").addRowData(i,jsonData[i]);
				  $("#list4").addRowData(0,jsonData.offer);
			}
			 $('#list4').navGrid('#jqGridPager',
		                
		                { edit: true, add: false, del: true, search: true, refresh: true, view: true, position: "left", cloneToTop: false },
		                
		                {
		                    editCaption: "The Edit Dialog",
		                    afterSubmit: function() {
		                		$('#list4').trigger( 'reloadGrid' );
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
		                		$('#list4').trigger( 'reloadGrid' );
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
		                		var rowId =$("#list4").jqGrid('getGridParam','selrow');  
		    	                var rowData = jQuery("#list4").getRowData(rowId);
		    	             	var quantity = rowData['quantity'];
		    	             	var salePrice = rowData['salePrice'];
		    	             	var discount = rowData['discountGrid'];
		    	             	var discountAmt = rowData['discountAmt'];
		    	             	var gst = rowData['vat'];
		                    	var igst = rowData['igst'];
		                    	var tota = 0;
		                    	var vatAmt = 0;
		                    	var totAmt = 0;
		                    	
		                    	
		                    	/*if(gst != "")
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
		                    	$("#list4").jqGrid("setCell", rowId, "discountAmt", totalIncDisc);
		    	             
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
		                    	
		                    	$("#list4").jqGrid("setCell", rowId, "taxAmount", calculateVatTotal);
		    	             	$("#list4").jqGrid("setCell", rowId, "total", totalWithVatAmt);
		    	             	var Total = 0;
		                    	var count = jQuery("#list4").jqGrid('getGridParam', 'records');
		        				var allRowsInGrid1 = $('#list4').getGridParam('data');
		        				var AllRows=JSON.stringify(allRowsInGrid1);
		                        for (var k = 0; k < count; k++) {
		                    	var Total1 = allRowsInGrid1[k].total;
		    	             	
		                    	if(Total1 != undefined){
		                    		Total = +Total + +Total1;
		                    	}
		                        }
		                            document.getElementById("totalAmount").value = Math.round(Total);
		                            var totAmount = Math.round(Total);
		    	             	    var dis = document.getElementById("discount").value;
		    	             	    if(dis != "0"){
		    	             	    	document.getElementById("grossTotal").value = totAmount;
		    	             	    }
		    	             	    else{
		    	             	    	document.getElementById("grossTotal").value = (+totAmount - +dis);
		    	             	    }
		    	             	
		    		        	},
		                });
			 
			 
				   });
				
 			})
}

function regcreditcustomerbill(){
	
	if(document.custord.creditCustomer.value == "")
	{
		alert("Select Customer Name.");
		return false;
	}	
	regCreditCustomerBill();
	}

function regCreditCustomerBill(){
	document.getElementById("btnSubmit").disabled = true; 
	var params= {};
	var count = jQuery("#list4").jqGrid('getGridParam', 'records');
	var allRowsInGrid1 = $('#list4').getGridParam('data');
	var AllRows=JSON.stringify(allRowsInGrid1);
	for (var i = 0; i < count; i++) {
	
		var pk_temp_id = allRowsInGrid1[i].pk_temp_id;
     	params["pk_temp_id"+i] = pk_temp_id;
		
		var item_id = allRowsInGrid1[i].item_id;
     	params["item_id"+i] = item_id;
     	
		var itemName = allRowsInGrid1[i].itemName;
		params["itemName"+i] = itemName;
		
		var quantity = allRowsInGrid1[i].quantity;
		params["quantity"+i] = quantity;
		
		var barcodeNo = allRowsInGrid1[i].barcodeNo;
		params["barcodeNo"+i] = barcodeNo;
		
		var categoryName = allRowsInGrid1[i].categoryName;
		params["categoryName"+i] = categoryName;

		var salePrice = allRowsInGrid1[i].salePrice;
		params["salePrice"+i] = salePrice;
		
		var total = allRowsInGrid1[i].total;
		params["total"+i] = total;
		
		var hsnSacNo = allRowsInGrid1[i].hsnSacNo;
		params["hsnSacNo"+i] = hsnSacNo;
		
		var vat = allRowsInGrid1[i].vat;
		params["vat"+i] = vat;
		
		var igst = allRowsInGrid1[i].igst;
		params["igst"+i] = igst;
		
		var taxAmount = allRowsInGrid1[i].taxAmount;
		params["taxAmount"+i] = taxAmount;
		
		var discountGrid = allRowsInGrid1[i].discountGrid;
		params["discountGrid"+i] = discountGrid;
		
		var discountAmt = allRowsInGrid1[i].discountAmt;
		params["discountAmt"+i] = discountAmt;
		
		
	}
	
	 var input = document.getElementById('creditCustomer'),
     list = document.getElementById('cust_drop'),
     i,fkRootCustId,gstTinNo;
	 for (i = 0; i < list.options.length; ++i) {
		 if (list.options[i].value === input.value) {
			 fkRootCustId = list.options[i].getAttribute('data-value');
			 gstTinNo = list.options[i].getAttribute('myvalue');
			 }
	     }
	    var input1 = document.getElementById('creditCustomer').value;
	    var totalAmount=$('#totalAmount').val();
	    var discount=$('#discount').val();
	    if(discount == ""){
	    	discount = 0;
	    }
	    
	    var grossTotal=$('#grossTotal').val();
	    var paidAmt=$('#paidAmt').val();
	    if(paidAmt == ""){
	    	paidAmt = 0;
	    }
	    
	    params["input1"] = input1;
	    params["fkRootCustId"] = fkRootCustId;
	    params["gstTinNo"] = gstTinNo;
		params["count"] = count;
		params["totalAmount"] = totalAmount;
		params["discount"] = discount;
		
		params["grossTotal"] = grossTotal;
		params["paidAmt"] = paidAmt;
		
	    params["methodName"] = "regCreditCustomerBill";
	    
		$.post('/SMT/jsp/utility/controller.jsp',params,function(data)
		    	{  
			           alert(data);
					   window.open("CreditCustomerBillPDF.jsp");
					   location.reload(true);
			
				 }
		    	).error(function(jqXHR, textStatus, errorThrown){
		    		if(textStatus==="timeout") {
		    			$(loaderObj).hide();
		    			$(loaderObj).find('#errorDiv').show();
		    		}
		    	});
	
}

function getproductgrid(){
	
	var value = document.getElementById("productId").value;
	var splitText = value.split(" => ");
	var productId1 = splitText[0];
	var carNo = $('#carNo').val();
	
	var params= {};
	
	params["productId"]=productId1;
	params["methodName"] ="fetchprod";
	
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
	         count = jQuery("#list4").jqGrid('getGridParam', 'records'); 
		     var rowdata =$("#list4").jqGrid('getGridParam','data');
		     var ids = jQuery("#list4").jqGrid('getDataIDs');
		     
			  var prodName,com,bar;
			  for (var j = 0; j < count; j++) 
			  {
				  prodName = rowdata[j].itemName;
				  com = rowdata[j].categoryName;
				  bar = rowdata[j].barcodeNo;
				 var rowId = ids[j];
				 var rowData = jQuery('#list4').jqGrid ('getRowData', rowId);
				
				if (prodName == jsonData.offer.itemName && com == jsonData.offer.categoryName && bar == jsonData.offer.barcodeNo) {
			    	
			    	newrow=false;
					alert("Item Already Inserted !!!");
					var grid = jQuery("#list4");
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
				  $("#list4").addRowData(count,jsonData.offer);
					
				 }
		
		
		$("#list4").jqGrid({
			datatype: "local",
			
			colNames:['pk_temp_id','item_id','BarcodeNO','CatName','ItemName','HSN/SAC','Quantity','SalePrice','Discount','DiscountAmt','GST%','IGST%','Tax Amt','Total Amt'],
			colModel:[ 
			          
			          
                 {
	                name:'pk_temp_id',
	                hidden:true,
                 },    
			     {
			    	 name:'item_id',
			    	 hidden:true,
			     },
			    
			 	{
			    	 name:'barcodeNo',
			    	 width:70,				    	
			    	 
			     },
			     {	name:'categoryName',
			    	 width:170,
					
				},
			           
			    {	name:'itemName',
			    	 width:170,
					
				},
				 {	name:'hsnSacNo',
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
				
				{	name:'discountGrid',
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
				{	name:'taxAmount',
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
	        	var rowId =$("#list4").jqGrid('getGridParam','selrow');  
                var rowData = jQuery("#list4").getRowData(rowId);
             	var quantity = rowData['quantity'];
             	var salePrice = rowData['salePrice'];
             	var discount = rowData['discountGrid'];
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
				}
            	
            	tota = quantity * salePrice;
            	totAmt = quantity * salePrice;
            	
            	var totalIncDisc= (tota*(discount/100));
            	var finalTotal= totAmt-totalIncDisc;
            	$("#list4").jqGrid("setCell", rowId, "discountAmt", totalIncDisc);
             
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
            	
            	$("#list4").jqGrid("setCell", rowId, "taxAmount", calculateVatTotal);
             	$("#list4").jqGrid("setCell", rowId, "total", totalWithVatAmt);
             	var Total = 0;
            	var count = jQuery("#list4").jqGrid('getGridParam', 'records');
				var allRowsInGrid1 = $('#list4').getGridParam('data');
				var AllRows=JSON.stringify(allRowsInGrid1);
                for (var k = 0; k < count; k++) {
            	var Total1 = allRowsInGrid1[k].total;
             	
            	if(Total1 != undefined){
            		Total = +Total + +Total1;
            	}
                }
                    document.getElementById("totalAmount").value = Math.round(Total);
                    var totAmount = Math.round(Total);
             	    var dis = document.getElementById("discount").value;
             	    if(dis != "0"){
             	    	document.getElementById("grossTotal").value = totAmount;
             	    }
             	    else{
             	    	document.getElementById("grossTotal").value = (+totAmount - +dis);
             	    }
             	
	        	},
           
			pager: "#jqGridPager",
			
			
			
		});
		
	
		//$("#list4").addRowData(i+1,jsonData[i]);
		if(count==0 || count==null)
		{
			 // $("#list4").addRowData(i,jsonData[i]);
			  $("#list4").addRowData(0,jsonData.offer);
		}
		 $('#list4').navGrid('#jqGridPager',
	                
	                { edit: true, add: false, del: true, search: true, refresh: true, view: true, position: "left", cloneToTop: false },
	                
	                {
	                    editCaption: "The Edit Dialog",
	                    afterSubmit: function() {
	                		$('#list4').trigger( 'reloadGrid' );
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
	                		$('#list4').trigger( 'reloadGrid' );
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
	                		
	                		var rowId =$("#list4").jqGrid('getGridParam','selrow');  
	                        var rowData = jQuery("#list4").getRowData(rowId);
	                     	var quantity = rowData['quantity'];
	                     	var salePrice = rowData['salePrice'];
	                     	var discount = rowData['discountGrid'];
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
	                    	$("#list4").jqGrid("setCell", rowId, "discountAmt", totalIncDisc);
	                     
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
	                    	
	                    	$("#list4").jqGrid("setCell", rowId, "taxAmount", calculateVatTotal);
	                     	$("#list4").jqGrid("setCell", rowId, "total", totalWithVatAmt);
	                     	var Total = 0;
	                    	var count = jQuery("#list4").jqGrid('getGridParam', 'records');
	        				var allRowsInGrid1 = $('#list4').getGridParam('data');
	        				var AllRows=JSON.stringify(allRowsInGrid1);
	                        for (var k = 0; k < count; k++) {
	                    	var Total1 = allRowsInGrid1[k].total;
	                     	
	                    	if(Total1 != undefined){
	                    		Total = +Total + +Total1;
	                    	}
	                        }
	                            document.getElementById("totalAmount").value = Math.round(Total);
	                            var totAmount = Math.round(Total);
	                     	    var dis = document.getElementById("discount").value;
	                     	    if(dis != "0"){
	                     	    	document.getElementById("grossTotal").value = totAmount;
	                     	    }
	                     	    else{
	                     	    	document.getElementById("grossTotal").value = (+totAmount - +dis);
	                     	    }
	                     	
		                    	
							},
	                });
		 
		 
			   });
			
			})
}

