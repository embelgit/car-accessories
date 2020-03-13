/**
 * For All Purchase , Sell And Stock
 */
// For Current Stock
function currentStock(){
	
var params= {};
	
	params["methodName"] = "getAllCurrentStock";

	$.post('/SMT/jsp/utility/controller.jsp',params,function(data)
			{
		
		$('#currStock').dataTable().fnClearTable();
		
		var jsonData = $.parseJSON(data);
		/*var doc = $.parseXML("<xml/>")
		var json = $.parseJSON(data);
		var xml = doc.getElementsByTagName("xml")[0]
		var key, elem

		for (key in json) {
		  if (json.hasOwnProperty(key)) {
		    elem = doc.createElement(key)
		    $(elem).text(json[key])
		    xml.appendChild(elem)
		  }
		}

		console.log(xml.outerHTML) */
		var catmap = jsonData.list;
		
		
		
		$(document).ready(function() {
		 var total =   $('#currStock').DataTable( {
			 
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
                    {"data": "catName", "width": "5%", "defaultContent": ""},
		            {"data": "itemName", "width": "5%", "defaultContent": ""},
		            {"data": "quantity", "width": "5%", "defaultContent": ""},
		            {"data": "date", "width": "5%", "defaultContent": ""},
		            
		            
		        ],
		      
		        
		    } );
		} );
		
	var mydata = catmap;
	$('#currStock').dataTable().fnAddData(mydata);
	
		}

	);
	
	
}

// Category Wise Current Stock

function getCategoryWiseStock()
{
	var input = document.getElementById('catId123'),
    list = document.getElementById('catId_drop123'),
    i,catId;
    for (i = 0; i < list.options.length; ++i) {
    if (list.options[i].value === input.value) {
    	catId = list.options[i].getAttribute('data-value');
    
      }
    }
	
	var params = {};
	params["methodName"] = "getCategoryWiseStock";
	params["catId"] = catId;
	

	$.post('/SMT/jsp/utility/controller.jsp',params,function(data)
			{
		$('#catWiseStock').dataTable().fnClearTable();
		var jsonData = $.parseJSON(data);
		
		var catmap = jsonData.list;
			
			$(document).ready(function() {
			    $('#catWiseStock').DataTable( {
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
			
				        },
			    	
			    	
			    	destroy: true,
			        searching: false,
			      
			columns: [  
			          
                      {"data": "catName", "width": "5%", "defaultContent": ""},
                      {"data": "itemName", "width": "5%", "defaultContent": ""},
                      {"data": "quantity", "width": "5%", "defaultContent": ""},
                      {"data": "date", "width": "5%", "defaultContent": ""},
			           
			           
			            
			           
			        ]
			    } );
			} );
			
		var mydata = catmap;
		$('#catWiseStock').dataTable().fnAddData(mydata);
			
			}).error(function(jqXHR, textStatus, errorThrown){
				if(textStatus==="timeout") {
					$(loaderObj).hide();
					$(loaderObj).find('#errorDiv').show();
				
				}
			});
}
// Barcode Wise Current Stock
function barcodewisestock(){
	
    var barcodeNo = $('#barcode').val();
   
    var params = {};
    params["barcodeNo"] = barcodeNo;
	params["methodName"] = "getBarcodeWiseStock";
	
	$.post('/SMT/jsp/utility/controller.jsp',params,function(data)
			{
		$('#barcodeWiseCurrentStock').dataTable().fnClearTable();
		var jsonData = $.parseJSON(data);
		var catmap = jsonData.list;
			
			$(document).ready(function() {
			    $('#barcodeWiseCurrentStock').DataTable( {
			    	
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
			
				        },
			    	
			    	
			    	destroy: true,
			        searching: false,
			      
			columns: [  
			          
                      {"data": "catName", "width": "5%", "defaultContent": ""},
                      {"data": "itemName", "width": "5%", "defaultContent": ""},
                      {"data": "quantity", "width": "5%", "defaultContent": ""},
                      {"data": "buyPrice", "width": "5%", "defaultContent": ""},
                      {"data": "vat", "width": "5%", "defaultContent": ""},
                      {"data": "billNo", "width": "5%", "defaultContent": ""},
                      {"data": "barcodeNo", "width": "5%", "defaultContent": ""},
                      
			           
			        ]
			    } );
			} );
			
		var mydata = catmap;
		$('#barcodeWiseCurrentStock').dataTable().fnAddData(mydata);
			
			}).error(function(jqXHR, textStatus, errorThrown){
				if(textStatus==="timeout") {
					$(loaderObj).hide();
					$(loaderObj).find('#errorDiv').show();
				
				}
			});
	
	
}

// Single Date Sale
function singleDateSaleReport(){
	
	var params= {};
	var fisDate = $("#fDate1").val();
	
	params["fisDate"]= fisDate;
	
		params["methodName"] = "singleDateSaleReport";

		$.post('/SMT/jsp/utility/controller.jsp',params,function(data)
				{
			$('#saleSingleDate').dataTable().fnClearTable();
			var jsonData = $.parseJSON(data);
			var catmap = jsonData.list;
				
				$(document).ready(function() {
				    $('#saleSingleDate').DataTable( {
				    	
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
					            pageTotal = api
					                .column( 7 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					         
					            // Update footer
					            $( api.column( 7 ).footer() ).html(
					              //  'Rs'+' '+pageTotal.toFixed(2)
					            		 str = pageTotal.toFixed(0)
					            );
					            console.log( pageTotal);
					            
					         // Total over this page
					            pageTotal = api
					                .column( 9 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					         
					            // Update footer
					            $( api.column( 9 ).footer() ).html(
					              //  'Rs'+' '+pageTotal.toFixed(2)
					            		 str = pageTotal.toFixed(0)
					            );
					            console.log( pageTotal);
					            
					         // Total over this page
					            pageTotal = api
					                .column( 10 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					         
					            // Update footer
					            $( api.column( 10 ).footer() ).html(
					              //  'Rs'+' '+pageTotal.toFixed(2)
					            		 str = pageTotal.toFixed(0)
					            );
					            console.log( pageTotal);
					            
					         // Total over this page
					            pageTotal = api
					                .column( 11 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					         
					            // Update footer
					            $( api.column( 11 ).footer() ).html(
					              //  'Rs'+' '+pageTotal.toFixed(2)
					            		 str = pageTotal.toFixed(0)
					            );
					            console.log( pageTotal);
				
					        },
				    	
				    	
				    	destroy: true,
				        searching: false,
				      
				columns: [  
                               {"data": "srNo", "width": "5%","defaultContent": ""},
                               {"data": "billNo", "width": "5%","defaultContent": ""},
                               {"data": "barcodeNo", "width": "5%","defaultContent": ""},
                               {"data": "hsnSacNo", "width": "5%","defaultContent": ""},
                               {"data": "itemName", "width": "5%","defaultContent": ""},
                               {"data": "categoryName", "width": "5%","defaultContent": ""},
                               {"data": "quantity", "width": "5%","defaultContent": ""},
                               {"data": "salePrice", "width": "5%","defaultContent": ""},
                               {"data": "totalperItem", "width": "5%","defaultContent": ""},
                               {"data": "gst", "width": "5%","defaultContent": ""},
                               {"data": "taxAmount", "width": "5%","defaultContent": ""},
                               {"data": "totalAmt", "width": "5%","defaultContent": ""},
                              
	                      
				           
				        ],
				        dom: 'Bfrtip',
				         buttons: [
				             'copy', 'csv', 'excel', 'pdf', 'print'
				         ]
					        
				    } );
				} );
				
			var mydata = catmap;
			$('#saleSingleDate').dataTable().fnAddData(mydata);
				
				}).error(function(jqXHR, textStatus, errorThrown){
					if(textStatus==="timeout") {
						$(loaderObj).hide();
						$(loaderObj).find('#errorDiv').show();
					
					}
				});
}


//Two Date Sale
function twoDateSaleReport(){
	
	var params= {};
	var fisDate = $("#fisDate").val();
	var endDate = $("#endDate").val();
	
	params["fisDate"]= fisDate;
	params["endDate"]= endDate;
	
		params["methodName"] = "twoDateSaleReport";

		$.post('/SMT/jsp/utility/controller.jsp',params,function(data)
				{
			$('#saleTwoDate').dataTable().fnClearTable();
			var jsonData = $.parseJSON(data);
			var catmap = jsonData.list;
				
				$(document).ready(function() {
				    $('#saleTwoDate').DataTable( {
				    	
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
					            pageTotal = api
					                .column( 7 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					         
					            // Update footer
					            $( api.column( 7 ).footer() ).html(
					              //  'Rs'+' '+pageTotal.toFixed(2)
					            		 str = pageTotal.toFixed(0)
					            );
					            console.log( pageTotal);
					            
					         // Total over this page
					            pageTotal = api
					                .column( 9 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					         
					            // Update footer
					            $( api.column( 9 ).footer() ).html(
					              //  'Rs'+' '+pageTotal.toFixed(2)
					            		 str = pageTotal.toFixed(0)
					            );
					            console.log( pageTotal);
					            
					         // Total over this page
					            pageTotal = api
					                .column( 10 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					         
					            // Update footer
					            $( api.column( 10 ).footer() ).html(
					              //  'Rs'+' '+pageTotal.toFixed(2)
					            		 str = pageTotal.toFixed(0)
					            );
					            console.log( pageTotal);
					            
					         // Total over this page
					            pageTotal = api
					                .column( 11 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					         
					            // Update footer
					            $( api.column( 11 ).footer() ).html(
					              //  'Rs'+' '+pageTotal.toFixed(2)
					            		 str = pageTotal.toFixed(0)
					            );
					            console.log( pageTotal);
				
					        },
				    	
				    	
				    	destroy: true,
				        searching: false,
				      
				columns: [  
                               {"data": "srNo", "width": "5%","defaultContent": ""},
                               {"data": "billNo", "width": "5%","defaultContent": ""},
                               {"data": "barcodeNo", "width": "5%","defaultContent": ""},
                               {"data": "hsnSacNo", "width": "5%","defaultContent": ""},
                               {"data": "itemName", "width": "5%","defaultContent": ""},
                               {"data": "categoryName", "width": "5%","defaultContent": ""},
                               {"data": "quantity", "width": "5%","defaultContent": ""},
                               {"data": "salePrice", "width": "5%","defaultContent": ""},
                               {"data": "totalperItem", "width": "5%","defaultContent": ""},
                               {"data": "gst", "width": "5%","defaultContent": ""},
                               {"data": "taxAmount", "width": "5%","defaultContent": ""},
                               {"data": "totalAmt", "width": "5%","defaultContent": ""},
                              
	                      
				           
				        ],
				        dom: 'Bfrtip',
				         buttons: [
				             'copy', 'csv', 'excel', 'pdf', 'print'
				         ]
					        
				    } );
				} );
				
			var mydata = catmap;
			$('#saleTwoDate').dataTable().fnAddData(mydata);
				
				}).error(function(jqXHR, textStatus, errorThrown){
					if(textStatus==="timeout") {
						$(loaderObj).hide();
						$(loaderObj).find('#errorDiv').show();
					
					}
				});
}


//Category Wise Sale
function categorySaleWise(){
	
	var params= {};
	var catName= $('#catId').val();
	
	params["catName"]= catName;
	
		params["methodName"] = "categorySaleWise";

		$.post('/SMT/jsp/utility/controller.jsp',params,function(data)
				{
			$('#saleCategoryWise').dataTable().fnClearTable();
			var jsonData = $.parseJSON(data);
			var catmap = jsonData.list;
				
				$(document).ready(function() {
				    $('#saleCategoryWise').DataTable( {
				    	
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
					            pageTotal = api
					                .column( 7 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					         
					            // Update footer
					            $( api.column( 7 ).footer() ).html(
					              //  'Rs'+' '+pageTotal.toFixed(2)
					            		 str = pageTotal.toFixed(0)
					            );
					            console.log( pageTotal);
					            
					         // Total over this page
					            pageTotal = api
					                .column( 9 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					         
					            // Update footer
					            $( api.column( 9 ).footer() ).html(
					              //  'Rs'+' '+pageTotal.toFixed(2)
					            		 str = pageTotal.toFixed(0)
					            );
					            console.log( pageTotal);
					            
					         // Total over this page
					            pageTotal = api
					                .column( 10 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					         
					            // Update footer
					            $( api.column( 10 ).footer() ).html(
					              //  'Rs'+' '+pageTotal.toFixed(2)
					            		 str = pageTotal.toFixed(0)
					            );
					            console.log( pageTotal);
					            
					         // Total over this page
					            pageTotal = api
					                .column( 11 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					         
					            // Update footer
					            $( api.column( 11 ).footer() ).html(
					              //  'Rs'+' '+pageTotal.toFixed(2)
					            		 str = pageTotal.toFixed(0)
					            );
					            console.log( pageTotal);
				
					        },
				    	
				    	
				    	destroy: true,
				        searching: false,
				      
				columns: [  
                               {"data": "srNo", "width": "5%","defaultContent": ""},
                               {"data": "billNo", "width": "5%","defaultContent": ""},
                               {"data": "barcodeNo", "width": "5%","defaultContent": ""},
                               {"data": "hsnSacNo", "width": "5%","defaultContent": ""},
                               {"data": "itemName", "width": "5%","defaultContent": ""},
                               {"data": "categoryName", "width": "5%","defaultContent": ""},
                               {"data": "quantity", "width": "5%","defaultContent": ""},
                               {"data": "salePrice", "width": "5%","defaultContent": ""},
                               {"data": "totalperItem", "width": "5%","defaultContent": ""},
                               {"data": "gst", "width": "5%","defaultContent": ""},
                               {"data": "taxAmount", "width": "5%","defaultContent": ""},
                               {"data": "totalAmt", "width": "5%","defaultContent": ""},
                               
	                      
				           
				        ],
				        dom: 'Bfrtip',
				         buttons: [
				             'copy', 'csv', 'excel', 'pdf', 'print'
				         ]
					        
				    } );
				} );
				
			var mydata = catmap;
			$('#saleCategoryWise').dataTable().fnAddData(mydata);
				
				}).error(function(jqXHR, textStatus, errorThrown){
					if(textStatus==="timeout") {
						$(loaderObj).hide();
						$(loaderObj).find('#errorDiv').show();
					
					}
				});
}

// Single Date Customer Sale Report 
function vehicleSingleDate(){
	
	var params= {};
	var fDate = $("#vsDate").val();
	
	params["fDate"]= fDate;
	
		params["methodName"] = "vehicleSingleDate";

		$.post('/SMT/jsp/utility/controller.jsp',params,function(data)
				{
			$('#vehicleSingleDateReport').dataTable().fnClearTable();
			var jsonData = $.parseJSON(data);
			var catmap = jsonData.list;
				
				$(document).ready(function() {
				    $('#vehicleSingleDateReport').DataTable( {
				    	
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
					                .column( 8 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 8 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					            // Total over this page
					            pageTotal = api
					                .column( 9 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 9 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					            // Total over this page
					            pageTotal = api
					                .column( 10 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 10 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					            // Total over this page
					            pageTotal = api
					                .column( 12 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 12 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					            // Total over this page
					            pageTotal = api
					                .column( 13 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 13 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					            // Total over this page
					            pageTotal = api
					                .column( 14 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 14 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
				
					        },
				    	
				    	
				    	destroy: true,
				        searching: false,
				      
				columns: [  
                          {"data": "srNo", "width": "5%","defaultContent": ""},
	                      {"data": "billNo", "width": "5%","defaultContent": ""},
	                      {"data": "carNo", "width": "5%","defaultContent": ""},
	                      {"data": "ownerName", "width": "5%","defaultContent": ""},
	                      {"data": "contactNo", "width": "5%","defaultContent": ""},
	                      {"data": "barcodeNo", "width": "5%","defaultContent": ""},
	                      {"data": "hsnSacNo", "width": "5%","defaultContent": ""},
	                      {"data": "categoryName", "width": "5%","defaultContent": ""},
	                      {"data": "itemName", "width": "5%","defaultContent": ""},
	                      {"data": "quantity", "width": "5%","defaultContent": ""},
	                      {"data": "salePrice", "width": "5%","defaultContent": ""},
	                      {"data": "totalperItem", "width": "5%","defaultContent": ""},
	                      {"data": "gst", "width": "5%","defaultContent": ""},
	                      {"data": "taxAmount", "width": "5%","defaultContent": ""},
	                      {"data": "totalAmt", "width": "5%","defaultContent": ""},
	                     
	                      
				        ]
				    } );
				} );
				
			var mydata = catmap;
			$('#vehicleSingleDateReport').dataTable().fnAddData(mydata);
				
				}).error(function(jqXHR, textStatus, errorThrown){
					if(textStatus==="timeout") {
						$(loaderObj).hide();
						$(loaderObj).find('#errorDiv').show();
					
					}
				});
	
}

//Between Two Date Customer Sale Report 
function vehicleTwoDate(){
	
	var params= {};
	var fDate = $("#fisDate4").val();
	var eDate = $("#endDate4").val();
	
	params["fDate"]= fDate;
	params["eDate"]= eDate;
	
		params["methodName"] = "vehicleTwoDate";

		$.post('/SMT/jsp/utility/controller.jsp',params,function(data)
				{
			$('#vehicleTwoDateReport').dataTable().fnClearTable();
			var jsonData = $.parseJSON(data);
			var catmap = jsonData.list;
				
				$(document).ready(function() {
				    $('#vehicleTwoDateReport').DataTable( {
				    	
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
					                .column( 8 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 8 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					            // Total over this page
					            pageTotal = api
					                .column( 9 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 9 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					            // Total over this page
					            pageTotal = api
					                .column( 10 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 10 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					            // Total over this page
					            pageTotal = api
					                .column( 12 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 12 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					            // Total over this page
					            pageTotal = api
					                .column( 13 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 13 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					            // Total over this page
					            pageTotal = api
					                .column( 14 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 14 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
				
					        },
				    	
				    	
				    	destroy: true,
				        searching: false,
				      
				columns: [  
                          {"data": "srNo", "width": "5%","defaultContent": ""},
	                      {"data": "billNo", "width": "5%","defaultContent": ""},
	                      {"data": "carNo", "width": "5%","defaultContent": ""},
	                      {"data": "ownerName", "width": "5%","defaultContent": ""},
	                      {"data": "contactNo", "width": "5%","defaultContent": ""},
	                      {"data": "barcodeNo", "width": "5%","defaultContent": ""},
	                      {"data": "hsnSacNo", "width": "5%","defaultContent": ""},
	                      {"data": "categoryName", "width": "5%","defaultContent": ""},
	                      {"data": "itemName", "width": "5%","defaultContent": ""},
	                      {"data": "quantity", "width": "5%","defaultContent": ""},
	                      {"data": "salePrice", "width": "5%","defaultContent": ""},
	                      {"data": "totalperItem", "width": "5%","defaultContent": ""},
	                      {"data": "gst", "width": "5%","defaultContent": ""},
	                      {"data": "taxAmount", "width": "5%","defaultContent": ""},
	                      {"data": "totalAmt", "width": "5%","defaultContent": ""},
	                      
	                      
				        ]
				    } );
				} );
				
			var mydata = catmap;
			$('#vehicleTwoDateReport').dataTable().fnAddData(mydata);
				
				}).error(function(jqXHR, textStatus, errorThrown){
					if(textStatus==="timeout") {
						$(loaderObj).hide();
						$(loaderObj).find('#errorDiv').show();
					
					}
				});
	
}

//Category Wise Customer Sale Report 
function categorySaleWiseCustomer(){
	
	var input = document.getElementById('catId12'),
    list = document.getElementById('catId_drop12'),
    i,catId;
    for (i = 0; i < list.options.length; ++i) {
    if (list.options[i].value === input.value) {
    	catId = list.options[i].getAttribute('data-value');
    
      }
    }
	
	var params = {};
	params["catId"] = catId;
		params["methodName"] = "categorySaleWiseCustomer";

		$.post('/SMT/jsp/utility/controller.jsp',params,function(data)
				{
			$('#categorySaleWiseCustomerReport').dataTable().fnClearTable();
			var jsonData = $.parseJSON(data);
			var catmap = jsonData.list;
				
				$(document).ready(function() {
				    $('#categorySaleWiseCustomerReport').DataTable( {
				    	
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
					                .column( 8 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 8 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					            // Total over this page
					            pageTotal = api
					                .column( 9 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 9 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					            // Total over this page
					            pageTotal = api
					                .column( 10 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 10 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					            // Total over this page
					            pageTotal = api
					                .column( 12 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 12 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					            // Total over this page
					            pageTotal = api
					                .column( 13 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 13 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					            // Total over this page
					            pageTotal = api
					                .column( 14 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 14 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
				
					        },
				    	
				    	
				    	destroy: true,
				        searching: false,
				      
				columns: [  
                          {"data": "srNo", "width": "5%","defaultContent": ""},
	                      {"data": "billNo", "width": "5%","defaultContent": ""},
	                      {"data": "carNo", "width": "5%","defaultContent": ""},
	                      {"data": "ownerName", "width": "5%","defaultContent": ""},
	                      {"data": "contactNo", "width": "5%","defaultContent": ""},
	                      {"data": "barcodeNo", "width": "5%","defaultContent": ""},
	                      {"data": "hsnSacNo", "width": "5%","defaultContent": ""},
	                      {"data": "categoryName", "width": "5%","defaultContent": ""},
	                      {"data": "itemName", "width": "5%","defaultContent": ""},
	                      {"data": "quantity", "width": "5%","defaultContent": ""},
	                      {"data": "salePrice", "width": "5%","defaultContent": ""},
	                      {"data": "totalperItem", "width": "5%","defaultContent": ""},
	                      {"data": "gst", "width": "5%","defaultContent": ""},
	                      {"data": "taxAmount", "width": "5%","defaultContent": ""},
	                      {"data": "totalAmt", "width": "5%","defaultContent": ""},
	                     
	                      
				        ]
				    } );
				} );
				
			var mydata = catmap;
			$('#categorySaleWiseCustomerReport').dataTable().fnAddData(mydata);
				
				}).error(function(jqXHR, textStatus, errorThrown){
					if(textStatus==="timeout") {
						$(loaderObj).hide();
						$(loaderObj).find('#errorDiv').show();
					
					}
				});
	
}


//Bill  No Wise Customer Sale Report 
function billnowiseVehiclesell(){
	
	var input = document.getElementById('BillNocust'),
    list = document.getElementById('seedBillNocust'),
    i,BillNocust;
    for (i = 0; i < list.options.length; ++i) {
    if (list.options[i].value === input.value) {
    	BillNocust = list.options[i].getAttribute('data-value');
    
      }
    }
	
	var params = {};
	params["BillNocust"] = BillNocust;
	params["methodName"] = "billnowiseVehiclesell";

		$.post('/SMT/jsp/utility/controller.jsp',params,function(data)
				{
			$('#billnowiseVehicle').dataTable().fnClearTable();
			var jsonData = $.parseJSON(data);
			var catmap = jsonData.list;
				
				$(document).ready(function() {
				    $('#billnowiseVehicle').DataTable( {
				    	
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
					                .column( 8 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 8 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					            // Total over this page
					            pageTotal = api
					                .column( 9 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 9 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					            // Total over this page
					            pageTotal = api
					                .column( 10 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 10 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					            // Total over this page
					            pageTotal = api
					                .column( 12 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 12 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					            // Total over this page
					            pageTotal = api
					                .column( 13 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 13 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					            // Total over this page
					            pageTotal = api
					                .column( 14 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 14 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
				
					        },
				    	
				    	
				    	destroy: true,
				        searching: false,
				      
				columns: [  
                          {"data": "srNo", "width": "5%","defaultContent": ""},
	                      {"data": "billNo", "width": "5%","defaultContent": ""},
	                      {"data": "carNo", "width": "5%","defaultContent": ""},
	                      {"data": "ownerName", "width": "5%","defaultContent": ""},
	                      {"data": "contactNo", "width": "5%","defaultContent": ""},
	                      {"data": "barcodeNo", "width": "5%","defaultContent": ""},
	                      {"data": "hsnSacNo", "width": "5%","defaultContent": ""},
	                      {"data": "categoryName", "width": "5%","defaultContent": ""},
	                      {"data": "itemName", "width": "5%","defaultContent": ""},
	                      {"data": "quantity", "width": "5%","defaultContent": ""},
	                      {"data": "salePrice", "width": "5%","defaultContent": ""},
	                      {"data": "totalperItem", "width": "5%","defaultContent": ""},
	                      {"data": "gst", "width": "5%","defaultContent": ""},
	                      {"data": "taxAmount", "width": "5%","defaultContent": ""},
	                      {"data": "totalAmt", "width": "5%","defaultContent": ""},
	                     
	                      
				        ]
				    } );
				} );
				
			var mydata = catmap;
			$('#billnowiseVehicle').dataTable().fnAddData(mydata);
				
				}).error(function(jqXHR, textStatus, errorThrown){
					if(textStatus==="timeout") {
						$(loaderObj).hide();
						$(loaderObj).find('#errorDiv').show();
					
					}
				});
	
}


//Barcode  No Wise Customer Sale Report 
function barcodenowiseVehiclesell(){
	
	var barcodeVehicle = $("#barcodeVehicle").val();
	var params = {};
	params["barcodeVehicle"] = barcodeVehicle;
	params["methodName"] = "barcodewiseVehicleSale";

		$.post('/SMT/jsp/utility/controller.jsp',params,function(data)
				{
			$('#barcodewiseVehicle').dataTable().fnClearTable();
			var jsonData = $.parseJSON(data);
			var catmap = jsonData.list;
				
				$(document).ready(function() {
				    $('#barcodewiseVehicle').DataTable( {
				    	
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
					                .column( 8 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 8 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					            // Total over this page
					            pageTotal = api
					                .column( 9 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 9 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					            // Total over this page
					            pageTotal = api
					                .column( 10 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 10 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					            // Total over this page
					            pageTotal = api
					                .column( 12 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 12 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					            // Total over this page
					            pageTotal = api
					                .column( 13 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 13 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					            // Total over this page
					            pageTotal = api
					                .column( 14 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 14 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
				
					        },
				    	
				    	
				    	destroy: true,
				        searching: false,
				      
				columns: [  
                          {"data": "srNo", "width": "5%","defaultContent": ""},
	                      {"data": "billNo", "width": "5%","defaultContent": ""},
	                      {"data": "carNo", "width": "5%","defaultContent": ""},
	                      {"data": "ownerName", "width": "5%","defaultContent": ""},
	                      {"data": "contactNo", "width": "5%","defaultContent": ""},
	                      {"data": "barcodeNo", "width": "5%","defaultContent": ""},
	                      {"data": "hsnSacNo", "width": "5%","defaultContent": ""},
	                      {"data": "categoryName", "width": "5%","defaultContent": ""},
	                      {"data": "itemName", "width": "5%","defaultContent": ""},
	                      {"data": "quantity", "width": "5%","defaultContent": ""},
	                      {"data": "salePrice", "width": "5%","defaultContent": ""},
	                      {"data": "totalperItem", "width": "5%","defaultContent": ""},
	                      {"data": "gst", "width": "5%","defaultContent": ""},
	                      {"data": "taxAmount", "width": "5%","defaultContent": ""},
	                      {"data": "totalAmt", "width": "5%","defaultContent": ""},
	                      
	                      
				        ]
				    } );
				} );
				
			var mydata = catmap;
			$('#barcodewiseVehicle').dataTable().fnAddData(mydata);
				
				}).error(function(jqXHR, textStatus, errorThrown){
					if(textStatus==="timeout") {
						$(loaderObj).hide();
						$(loaderObj).find('#errorDiv').show();
					
					}
				});
	
}


//Single Date Miscellaneos Sale Report 
function miscellaneousSingleDate(){
	
	var params= {};
	var fDate = $("#msDate").val();
	
	params["fDate"]= fDate;
	
		params["methodName"] = "miscellaneousSingleDate";

		$.post('/SMT/jsp/utility/controller.jsp',params,function(data)
				{
			$('#miscellaneousSingleDateReport').dataTable().fnClearTable();
			var jsonData = $.parseJSON(data);
			var catmap = jsonData.list;
				
				$(document).ready(function() {
				    $('#miscellaneousSingleDateReport').DataTable( {
				    	
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
					                .column( 5 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 5 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					            // Total over this page
					            pageTotal = api
					                .column( 6 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 6 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					            // Total over this page
					            pageTotal = api
					                .column( 7 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 7 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					            
					            // Total over this page
					            pageTotal = api
					                .column( 9 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 9 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					         // Total over this page
					            pageTotal = api
					                .column( 10 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 10 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					         // Total over this page
					            pageTotal = api
					                .column( 11 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 11 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
				
					        },
				    	
				    	
				    	destroy: true,
				        searching: false,
				      
				columns: [  
                          {"data": "srNo", "width": "5%","defaultContent": ""},
	                      {"data": "billNo", "width": "5%","defaultContent": ""},
	                      {"data": "barcodeNo", "width": "5%","defaultContent": ""},
	                      {"data": "categoryName", "width": "5%","defaultContent": ""},
	                      {"data": "itemName", "width": "5%","defaultContent": ""},
	                      {"data": "quantity", "width": "5%","defaultContent": ""},
	                      {"data": "salePrice", "width": "5%","defaultContent": ""},
	                      {"data": "totalperItem", "width": "5%","defaultContent": ""},
	                      {"data": "gst", "width": "5%","defaultContent": ""},
	                      {"data": "taxAmount", "width": "5%","defaultContent": ""},
	                      {"data": "totalAmt", "width": "5%","defaultContent": ""},
	                      {"data": "discount", "width": "5%","defaultContent": ""},
	                      
				        ]
				    } );
				} );
				
			var mydata = catmap;
			$('#miscellaneousSingleDateReport').dataTable().fnAddData(mydata);
				
				}).error(function(jqXHR, textStatus, errorThrown){
					if(textStatus==="timeout") {
						$(loaderObj).hide();
						$(loaderObj).find('#errorDiv').show();
					
					}
				});
	
}

//Two Date Miscellaneos Sale Report 
function miscellaneousTwoDate(){
	
	var params= {};
	var fDate = $("#msfisDate").val();
	var eDate = $("#msendDate").val();
	
	params["fDate"]= fDate;
	params["eDate"]= eDate;
	
		params["methodName"] = "miscellaneousTwoDate";

		$.post('/SMT/jsp/utility/controller.jsp',params,function(data)
				{
			$('#miscellaneousTwoDateReport').dataTable().fnClearTable();
			var jsonData = $.parseJSON(data);
			var catmap = jsonData.list;
				
				$(document).ready(function() {
				    $('#miscellaneousTwoDateReport').DataTable( {
				    	
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
					                .column( 5 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 5 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					            // Total over this page
					            pageTotal = api
					                .column( 6 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 6 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					            // Total over this page
					            pageTotal = api
					                .column( 7 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 7 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					            
					            // Total over this page
					            pageTotal = api
					                .column( 9 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 9 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					         // Total over this page
					            pageTotal = api
					                .column( 10 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 10 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					         // Total over this page
					            pageTotal = api
					                .column( 11 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 11 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
				
					        },
				    	
				    	
				    	destroy: true,
				        searching: false,
				      
				columns: [  
                          {"data": "srNo", "width": "5%","defaultContent": ""},
	                      {"data": "billNo", "width": "5%","defaultContent": ""},
	                      {"data": "barcodeNo", "width": "5%","defaultContent": ""},
	                      {"data": "categoryName", "width": "5%","defaultContent": ""},
	                      {"data": "itemName", "width": "5%","defaultContent": ""},
	                      {"data": "quantity", "width": "5%","defaultContent": ""},
	                      {"data": "salePrice", "width": "5%","defaultContent": ""},
	                      {"data": "totalperItem", "width": "5%","defaultContent": ""},
	                      {"data": "gst", "width": "5%","defaultContent": ""},
	                      {"data": "taxAmount", "width": "5%","defaultContent": ""},
	                      {"data": "totalAmt", "width": "5%","defaultContent": ""},
	                      {"data": "discount", "width": "5%","defaultContent": ""},
	                      
				        ]
				    } );
				} );
				
			var mydata = catmap;
			$('#miscellaneousTwoDateReport').dataTable().fnAddData(mydata);
				
				}).error(function(jqXHR, textStatus, errorThrown){
					if(textStatus==="timeout") {
						$(loaderObj).hide();
						$(loaderObj).find('#errorDiv').show();
					
					}
				});
	
}


//Category Wise Miscellaneos Sale Report 
function miscellaneousSaleWiseCustomer(){
	
	var input = document.getElementById('mscatId'),
    list = document.getElementById('mscatId_drop'),
    i,mscatId;
    for (i = 0; i < list.options.length; ++i) {
    if (list.options[i].value === input.value) {
    	mscatId = list.options[i].getAttribute('data-value');
    
      }
    }
	
	var params = {};
	params["mscatId"] = mscatId;
		params["methodName"] = "miscellaneousSaleWiseCustomer";

		$.post('/SMT/jsp/utility/controller.jsp',params,function(data)
				{
			$('#miscellaneousSaleWiseCustomerReport').dataTable().fnClearTable();
			var jsonData = $.parseJSON(data);
			var catmap = jsonData.list;
				
				$(document).ready(function() {
				    $('#miscellaneousSaleWiseCustomerReport').DataTable( {
				    	
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
					                .column( 5 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 5 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					            // Total over this page
					            pageTotal = api
					                .column( 6 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 6 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					            // Total over this page
					            pageTotal = api
					                .column( 7 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 7 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					            
					            // Total over this page
					            pageTotal = api
					                .column( 9 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 9 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					         // Total over this page
					            pageTotal = api
					                .column( 10 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 10 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					         // Total over this page
					            pageTotal = api
					                .column( 11 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 11 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
				
					        },
				    	
				    	
				    	destroy: true,
				        searching: false,
				      
				columns: [  
                          {"data": "srNo", "width": "5%","defaultContent": ""},
	                      {"data": "billNo", "width": "5%","defaultContent": ""},
	                      {"data": "barcodeNo", "width": "5%","defaultContent": ""},
	                      {"data": "categoryName", "width": "5%","defaultContent": ""},
	                      {"data": "itemName", "width": "5%","defaultContent": ""},
	                      {"data": "quantity", "width": "5%","defaultContent": ""},
	                      {"data": "salePrice", "width": "5%","defaultContent": ""},
	                      {"data": "totalperItem", "width": "5%","defaultContent": ""},
	                      {"data": "gst", "width": "5%","defaultContent": ""},
	                      {"data": "taxAmount", "width": "5%","defaultContent": ""},
	                      {"data": "totalAmt", "width": "5%","defaultContent": ""},
	                      {"data": "discount", "width": "5%","defaultContent": ""},
	                      
				        ]
				    } );
				} );
				
			var mydata = catmap;
			$('#miscellaneousSaleWiseCustomerReport').dataTable().fnAddData(mydata);
				
				}).error(function(jqXHR, textStatus, errorThrown){
					if(textStatus==="timeout") {
						$(loaderObj).hide();
						$(loaderObj).find('#errorDiv').show();
					
					}
				});
	
}


//Bill No Wise Miscellaneos Sale Report 
function billnowiseMiscellaneoussell(){
	
	var input = document.getElementById('msBillNocust'),
    list = document.getElementById('msBillNocust_id'),
    i,msBillNocust;
    for (i = 0; i < list.options.length; ++i) {
    if (list.options[i].value === input.value) {
    	msBillNocust = list.options[i].getAttribute('data-value');
    
      }
    }
	
	var params = {};
	params["msBillNocust"] = msBillNocust;
		params["methodName"] = "billnowiseMiscellaneoussell";

		$.post('/SMT/jsp/utility/controller.jsp',params,function(data)
				{
			$('#billnowiseMiscellaneous').dataTable().fnClearTable();
			var jsonData = $.parseJSON(data);
			var catmap = jsonData.list;
				
				$(document).ready(function() {
				    $('#billnowiseMiscellaneous').DataTable( {
				    	
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
					                .column( 5 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 5 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					            // Total over this page
					            pageTotal = api
					                .column( 6 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 6 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					            // Total over this page
					            pageTotal = api
					                .column( 7 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 7 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					            
					            // Total over this page
					            pageTotal = api
					                .column( 9 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 9 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					         // Total over this page
					            pageTotal = api
					                .column( 10 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 10 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					         // Total over this page
					            pageTotal = api
					                .column( 11 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 11 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
				
					        },
				    	
				    	
				    	destroy: true,
				        searching: false,
				      
				columns: [  
                          {"data": "srNo", "width": "5%","defaultContent": ""},
	                      {"data": "billNo", "width": "5%","defaultContent": ""},
	                      {"data": "barcodeNo", "width": "5%","defaultContent": ""},
	                      {"data": "categoryName", "width": "5%","defaultContent": ""},
	                      {"data": "itemName", "width": "5%","defaultContent": ""},
	                      {"data": "quantity", "width": "5%","defaultContent": ""},
	                      {"data": "salePrice", "width": "5%","defaultContent": ""},
	                      {"data": "totalperItem", "width": "5%","defaultContent": ""},
	                      {"data": "gst", "width": "5%","defaultContent": ""},
	                      {"data": "taxAmount", "width": "5%","defaultContent": ""},
	                      {"data": "totalAmt", "width": "5%","defaultContent": ""},
	                      {"data": "discount", "width": "5%","defaultContent": ""},
	                      
				        ]
				    } );
				} );
				
			var mydata = catmap;
			$('#billnowiseMiscellaneous').dataTable().fnAddData(mydata);
				
				}).error(function(jqXHR, textStatus, errorThrown){
					if(textStatus==="timeout") {
						$(loaderObj).hide();
						$(loaderObj).find('#errorDiv').show();
					
					}
				});
	
}


//Barcode No Wise Miscellaneos Sale Report 
function barcodenowiseMiscellaneoussell(){
	
	var params= {};
	var barcodeMiscellaneous = $("#barcodeMiscellaneous").val();
	
	params["barcodeMiscellaneous"]= barcodeMiscellaneous;
	
		params["methodName"] = "barcodenowiseMiscellaneoussell";

		$.post('/SMT/jsp/utility/controller.jsp',params,function(data)
				{
			$('#barcodewiseMiscellaneous').dataTable().fnClearTable();
			var jsonData = $.parseJSON(data);
			var catmap = jsonData.list;
				
				$(document).ready(function() {
				    $('#barcodewiseMiscellaneous').DataTable( {
				    	
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
					                .column( 5 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 5 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					            // Total over this page
					            pageTotal = api
					                .column( 6 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 6 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					            // Total over this page
					            pageTotal = api
					                .column( 7 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 7 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					            
					            // Total over this page
					            pageTotal = api
					                .column( 9 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 9 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					         // Total over this page
					            pageTotal = api
					                .column( 10 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 10 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					         // Total over this page
					            pageTotal = api
					                .column( 11 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 11 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
				
					        },
				    	
				    	
				    	destroy: true,
				        searching: false,
				      
				columns: [  
                          {"data": "srNo", "width": "5%","defaultContent": ""},
	                      {"data": "billNo", "width": "5%","defaultContent": ""},
	                      {"data": "barcodeNo", "width": "5%","defaultContent": ""},
	                      {"data": "categoryName", "width": "5%","defaultContent": ""},
	                      {"data": "itemName", "width": "5%","defaultContent": ""},
	                      {"data": "quantity", "width": "5%","defaultContent": ""},
	                      {"data": "salePrice", "width": "5%","defaultContent": ""},
	                      {"data": "totalperItem", "width": "5%","defaultContent": ""},
	                      {"data": "gst", "width": "5%","defaultContent": ""},
	                      {"data": "taxAmount", "width": "5%","defaultContent": ""},
	                      {"data": "totalAmt", "width": "5%","defaultContent": ""},
	                      {"data": "discount", "width": "5%","defaultContent": ""},
	                      
				        ]
				    } );
				} );
				
			var mydata = catmap;
			$('#barcodewiseMiscellaneous').dataTable().fnAddData(mydata);
				
				}).error(function(jqXHR, textStatus, errorThrown){
					if(textStatus==="timeout") {
						$(loaderObj).hide();
						$(loaderObj).find('#errorDiv').show();
					
					}
				});
	
}

//Single Date Credit Sale Report 
function creditSingleDate(){
	
	var params= {};
	var fDate = $("#csDate").val();
	
	params["fDate"]= fDate;
	
		params["methodName"] = "creditSingleDate";

		$.post('/SMT/jsp/utility/controller.jsp',params,function(data)
				{
			$('#creditSingleDateReport').dataTable().fnClearTable();
			var jsonData = $.parseJSON(data);
			var catmap = jsonData.list;
				
				$(document).ready(function() {
				    $('#creditSingleDateReport').DataTable( {
				    	
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
					                .column( 6 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 6 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					            // Total over this page
					            pageTotal = api
					                .column( 7 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 7 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					            // Total over this page
					            pageTotal = api
					                .column( 8 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 8 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					            
					            // Total over this page
					            pageTotal = api
					                .column( 10 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 10 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					         // Total over this page
					            pageTotal = api
					                .column( 11 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 11 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					         // Total over this page
					            pageTotal = api
					                .column( 12 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 12 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
				
					        },
				    	
				    	
				    	destroy: true,
				        searching: false,
				      
				columns: [  
                          {"data": "srNo", "width": "5%","defaultContent": ""},
	                      {"data": "billNo", "width": "5%","defaultContent": ""},
	                      {"data": "firstName", "width": "5%","defaultContent": ""},
	                      {"data": "barcodeNo", "width": "5%","defaultContent": ""},
	                      {"data": "categoryName", "width": "5%","defaultContent": ""},
	                      {"data": "itemName", "width": "5%","defaultContent": ""},
	                      {"data": "quantity", "width": "5%","defaultContent": ""},
	                      {"data": "salePrice", "width": "5%","defaultContent": ""},
	                      {"data": "totalperItem", "width": "5%","defaultContent": ""},
	                      {"data": "gst", "width": "5%","defaultContent": ""},
	                      {"data": "taxAmount", "width": "5%","defaultContent": ""},
	                      {"data": "totalAmt", "width": "5%","defaultContent": ""},
	                      {"data": "discount", "width": "5%","defaultContent": ""},
	                      
				        ]
				    } );
				} );
				
			var mydata = catmap;
			$('#creditSingleDateReport').dataTable().fnAddData(mydata);
				
				}).error(function(jqXHR, textStatus, errorThrown){
					if(textStatus==="timeout") {
						$(loaderObj).hide();
						$(loaderObj).find('#errorDiv').show();
					
					}
				});
	
}


//Two Date Credit Sale Report 
function creditTwoDate(){
	
	var params= {};
	var fDate = $("#csfisDate").val();
	var eDate = $("#csendDate").val();
	
	params["fDate"]= fDate;
	params["eDate"]= eDate;
	
		params["methodName"] = "creditTwoDate";

		$.post('/SMT/jsp/utility/controller.jsp',params,function(data)
				{
			$('#creditTwoDateReport').dataTable().fnClearTable();
			var jsonData = $.parseJSON(data);
			var catmap = jsonData.list;
				
				$(document).ready(function() {
				    $('#creditTwoDateReport').DataTable( {
				    	
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
					                .column( 6 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 6 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					            // Total over this page
					            pageTotal = api
					                .column( 7 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 7 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					            // Total over this page
					            pageTotal = api
					                .column( 8 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 8 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					            
					            // Total over this page
					            pageTotal = api
					                .column( 10 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 10 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					         // Total over this page
					            pageTotal = api
					                .column( 11 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 11 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					         // Total over this page
					            pageTotal = api
					                .column( 12 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 12 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
				
					        },
				    	
				    	
				    	destroy: true,
				        searching: false,
				      
				columns: [  
                          {"data": "srNo", "width": "5%","defaultContent": ""},
	                      {"data": "billNo", "width": "5%","defaultContent": ""},
	                      {"data": "firstName", "width": "5%","defaultContent": ""},
	                      {"data": "barcodeNo", "width": "5%","defaultContent": ""},
	                      {"data": "categoryName", "width": "5%","defaultContent": ""},
	                      {"data": "itemName", "width": "5%","defaultContent": ""},
	                      {"data": "quantity", "width": "5%","defaultContent": ""},
	                      {"data": "salePrice", "width": "5%","defaultContent": ""},
	                      {"data": "totalperItem", "width": "5%","defaultContent": ""},
	                      {"data": "gst", "width": "5%","defaultContent": ""},
	                      {"data": "taxAmount", "width": "5%","defaultContent": ""},
	                      {"data": "totalAmt", "width": "5%","defaultContent": ""},
	                      {"data": "discount", "width": "5%","defaultContent": ""},
	                      
				        ]
				    } );
				} );
				
			var mydata = catmap;
			$('#creditTwoDateReport').dataTable().fnAddData(mydata);
				
				}).error(function(jqXHR, textStatus, errorThrown){
					if(textStatus==="timeout") {
						$(loaderObj).hide();
						$(loaderObj).find('#errorDiv').show();
					
					}
				});
	
}


//Category Wise Credit Sale Report 
function creditSaleWiseCustomer(){
	
	var input = document.getElementById('cscatId'),
    list = document.getElementById('cscatId_drop'),
    i,cscatId;
    for (i = 0; i < list.options.length; ++i) {
    if (list.options[i].value === input.value) {
    	cscatId = list.options[i].getAttribute('data-value');
    
      }
    }
	
	var params = {};
	params["cscatId"] = cscatId;
	
	
		params["methodName"] = "creditSaleWiseCustomer";

		$.post('/SMT/jsp/utility/controller.jsp',params,function(data)
				{
			$('#creditSaleWiseCustomerReport').dataTable().fnClearTable();
			var jsonData = $.parseJSON(data);
			var catmap = jsonData.list;
				
				$(document).ready(function() {
				    $('#creditSaleWiseCustomerReport').DataTable( {
				    	
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
					                .column( 6 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 6 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					            // Total over this page
					            pageTotal = api
					                .column( 7 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 7 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					            // Total over this page
					            pageTotal = api
					                .column( 8 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 8 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					            
					            // Total over this page
					            pageTotal = api
					                .column( 10 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 10 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					         // Total over this page
					            pageTotal = api
					                .column( 11 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 11 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					         // Total over this page
					            pageTotal = api
					                .column( 12 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 12 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
				
					        },
				    	
				    	
				    	destroy: true,
				        searching: false,
				      
				columns: [  
                          {"data": "srNo", "width": "5%","defaultContent": ""},
	                      {"data": "billNo", "width": "5%","defaultContent": ""},
	                      {"data": "firstName", "width": "5%","defaultContent": ""},
	                      {"data": "barcodeNo", "width": "5%","defaultContent": ""},
	                      {"data": "categoryName", "width": "5%","defaultContent": ""},
	                      {"data": "itemName", "width": "5%","defaultContent": ""},
	                      {"data": "quantity", "width": "5%","defaultContent": ""},
	                      {"data": "salePrice", "width": "5%","defaultContent": ""},
	                      {"data": "totalperItem", "width": "5%","defaultContent": ""},
	                      {"data": "gst", "width": "5%","defaultContent": ""},
	                      {"data": "taxAmount", "width": "5%","defaultContent": ""},
	                      {"data": "totalAmt", "width": "5%","defaultContent": ""},
	                      {"data": "discount", "width": "5%","defaultContent": ""},
	                      
				        ]
				    } );
				} );
				
			var mydata = catmap;
			$('#creditSaleWiseCustomerReport').dataTable().fnAddData(mydata);
				
				}).error(function(jqXHR, textStatus, errorThrown){
					if(textStatus==="timeout") {
						$(loaderObj).hide();
						$(loaderObj).find('#errorDiv').show();
					
					}
				});
	
}

//Bill No Wise Credit Sale Report 
function billnowiseCreditsell(){
	
	var input = document.getElementById('csBillNocust'),
    list = document.getElementById('csBillNocust_id'),
    i,csBillNocust;
    for (i = 0; i < list.options.length; ++i) {
    if (list.options[i].value === input.value) {
    	csBillNocust = list.options[i].getAttribute('data-value');
    
      }
    }
	
	var params = {};
	params["csBillNocust"] = csBillNocust;
	
	
		params["methodName"] = "billnowiseCreditsell";

		$.post('/SMT/jsp/utility/controller.jsp',params,function(data)
				{
			$('#billnowiseCredit').dataTable().fnClearTable();
			var jsonData = $.parseJSON(data);
			var catmap = jsonData.list;
				
				$(document).ready(function() {
				    $('#billnowiseCredit').DataTable( {
				    	
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
					                .column( 6 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 6 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					            // Total over this page
					            pageTotal = api
					                .column( 7 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 7 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					            // Total over this page
					            pageTotal = api
					                .column( 8 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 8 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					            
					            // Total over this page
					            pageTotal = api
					                .column( 10 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 10 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					         // Total over this page
					            pageTotal = api
					                .column( 11 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 11 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					         // Total over this page
					            pageTotal = api
					                .column( 12 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 12 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
				
					        },
				    	
				    	
				    	destroy: true,
				        searching: false,
				      
				columns: [  
                          {"data": "srNo", "width": "5%","defaultContent": ""},
	                      {"data": "billNo", "width": "5%","defaultContent": ""},
	                      {"data": "firstName", "width": "5%","defaultContent": ""},
	                      {"data": "barcodeNo", "width": "5%","defaultContent": ""},
	                      {"data": "categoryName", "width": "5%","defaultContent": ""},
	                      {"data": "itemName", "width": "5%","defaultContent": ""},
	                      {"data": "quantity", "width": "5%","defaultContent": ""},
	                      {"data": "salePrice", "width": "5%","defaultContent": ""},
	                      {"data": "totalperItem", "width": "5%","defaultContent": ""},
	                      {"data": "gst", "width": "5%","defaultContent": ""},
	                      {"data": "taxAmount", "width": "5%","defaultContent": ""},
	                      {"data": "totalAmt", "width": "5%","defaultContent": ""},
	                      {"data": "discount", "width": "5%","defaultContent": ""},
	                      
				        ]
				    } );
				} );
				
			var mydata = catmap;
			$('#billnowiseCredit').dataTable().fnAddData(mydata);
				
				}).error(function(jqXHR, textStatus, errorThrown){
					if(textStatus==="timeout") {
						$(loaderObj).hide();
						$(loaderObj).find('#errorDiv').show();
					
					}
				});
	
}

//Barcode No Wise Credit Sale Report 
function barcodenowiseCredit(){
	
	var barcodeCredit = $("#barcodeCredit").val();
	
	var params = {};
	params["barcodeCredit"] = barcodeCredit;
	
	
		params["methodName"] = "barcodenowiseCredit";

		$.post('/SMT/jsp/utility/controller.jsp',params,function(data)
				{
			$('#barcodewiseCredit').dataTable().fnClearTable();
			var jsonData = $.parseJSON(data);
			var catmap = jsonData.list;
				
				$(document).ready(function() {
				    $('#barcodewiseCredit').DataTable( {
				    	
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
					                .column( 6 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 6 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					            // Total over this page
					            pageTotal = api
					                .column( 7 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 7 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					            // Total over this page
					            pageTotal = api
					                .column( 8 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 8 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					            
					            // Total over this page
					            pageTotal = api
					                .column( 10 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 10 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					         // Total over this page
					            pageTotal = api
					                .column( 11 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 11 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					         // Total over this page
					            pageTotal = api
					                .column( 12 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 12 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
				
					        },
				    	
				    	
				    	destroy: true,
				        searching: false,
				      
				columns: [  
                          {"data": "srNo", "width": "5%","defaultContent": ""},
	                      {"data": "billNo", "width": "5%","defaultContent": ""},
	                      {"data": "firstName", "width": "5%","defaultContent": ""},
	                      {"data": "barcodeNo", "width": "5%","defaultContent": ""},
	                      {"data": "categoryName", "width": "5%","defaultContent": ""},
	                      {"data": "itemName", "width": "5%","defaultContent": ""},
	                      {"data": "quantity", "width": "5%","defaultContent": ""},
	                      {"data": "salePrice", "width": "5%","defaultContent": ""},
	                      {"data": "totalperItem", "width": "5%","defaultContent": ""},
	                      {"data": "gst", "width": "5%","defaultContent": ""},
	                      {"data": "taxAmount", "width": "5%","defaultContent": ""},
	                      {"data": "totalAmt", "width": "5%","defaultContent": ""},
	                      {"data": "discount", "width": "5%","defaultContent": ""},
	                      
				        ]
				    } );
				} );
				
			var mydata = catmap;
			$('#barcodewiseCredit').dataTable().fnAddData(mydata);
				
				}).error(function(jqXHR, textStatus, errorThrown){
					if(textStatus==="timeout") {
						$(loaderObj).hide();
						$(loaderObj).find('#errorDiv').show();
					
					}
				});
	
}


//Purchase Report Supplier Wise
function supplierAllPurchase(){
	
	var input = document.getElementById('supplier7'),
    list = document.getElementById('sup_drop7'),
    i,supplier;
    for (i = 0; i < list.options.length; ++i) {
    if (list.options[i].value === input.value) {
    	supplier = list.options[i].getAttribute('data-value');
    
      }
    }
	
	var params = {};
	params["supplier"] = supplier;
	
		params["methodName"] = "supplierAllPurchase";

		$.post('/SMT/jsp/utility/controller.jsp',params,function(data)
				{
			$('#supplierNameWiseTable').dataTable().fnClearTable();
			var jsonData = $.parseJSON(data);
			var catmap = jsonData.list;
				
				$(document).ready(function() {
				    $('#supplierNameWiseTable').DataTable( {
				    	
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
					                .column( 8 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 8 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					            // Total over this page
					            pageTotal = api
					                .column( 9 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 9 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					            // Total over this page
					            pageTotal = api
					                .column( 12 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 12 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					            
					            // Total over this page
					            pageTotal = api
					                .column( 14 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 14 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					       /*  // Total over this page
					            pageTotal = api
					                .column( 16 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 16 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					         // Total over this page
					            pageTotal = api
					                .column( 18 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 18 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);*/
				
					        },
				    	
				    	
				    	destroy: true,
				        searching: false,
				      
				columns: [  
                          {"data": "srno", "width": "5%","defaultContent": ""},
	                      {"data": "date", "width": "5%","defaultContent": ""},
	                      {"data": "billNo", "width": "5%","defaultContent": ""},
	                      {"data": "contactPerson", "width": "5%","defaultContent": ""},
	                      {"data": "supplierName", "width": "5%","defaultContent": ""},
	                      {"data": "catName", "width": "5%","defaultContent": ""},
	                      {"data": "itemName", "width": "5%","defaultContent": ""},
	                      {"data": "hsnsacno", "width": "5%","defaultContent": ""},
	                      {"data": "quantity", "width": "5%","defaultContent": ""},
	                      {"data": "buyPrice", "width": "5%","defaultContent": ""},
	                      {"data": "vat", "width": "5%","defaultContent": ""},
	                      {"data": "igst", "width": "5%","defaultContent": ""},
	                      {"data": "taxAmount", "width": "5%","defaultContent": ""},
	                     // {"data": "salePrice", "width": "5%","defaultContent": ""},
	                      {"data": "total", "width": "5%","defaultContent": ""},
	                      {"data": "extraDiscount", "width": "5%","defaultContent": ""},
	                      {"data": "expence", "width": "5%","defaultContent": ""},
	                      {"data": "txPerexpence", "width": "5%","defaultContent": ""},
	                      {"data": "finalExpenses", "width": "5%","defaultContent": ""},
	                      {"data": "grossTotal", "width": "5%","defaultContent": ""},
	                      
				        ]
				    } );
				} );
				
			var mydata = catmap;
			$('#supplierNameWiseTable').dataTable().fnAddData(mydata);
				
				}).error(function(jqXHR, textStatus, errorThrown){
					if(textStatus==="timeout") {
						$(loaderObj).hide();
						$(loaderObj).find('#errorDiv').show();
					
					}
				});
	
}

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


//Purchase Report Supplier Bill No Wise
function supplierBillWisePurchaseReport(){
	
	var input = document.getElementById('supplier'),
    list = document.getElementById('sup_drop'),
    i,supplier;
    for (i = 0; i < list.options.length; ++i) {
    if (list.options[i].value === input.value) {
    	supplier = list.options[i].getAttribute('data-value');
    
      }
    }
    var billNo = $("#billNo").val();
	var params = {};
	params["supplier"] = supplier;
	params["billNo"] = billNo;
	
		params["methodName"] = "supplierBillWisePurchaseReport";

		$.post('/SMT/jsp/utility/controller.jsp',params,function(data)
				{
			$('#supplierBillWiseData').dataTable().fnClearTable();
			var jsonData = $.parseJSON(data);
			var catmap = jsonData.list;
				
				$(document).ready(function() {
				    $('#supplierBillWiseData').DataTable( {
				    	
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
					                .column( 8 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 8 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					            // Total over this page
					            pageTotal = api
					                .column( 9 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 9 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					            // Total over this page
					            pageTotal = api
					                .column( 12 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 12 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					            
					            // Total over this page
					            pageTotal = api
					                .column( 14 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 14 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					       /*  // Total over this page
					            pageTotal = api
					                .column( 16 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 16 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					         // Total over this page
					            pageTotal = api
					                .column( 18 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 18 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);*/
				
					        },
				    	
				    	
				    	destroy: true,
				        searching: false,
				      
				columns: [  
                          {"data": "srno", "width": "5%","defaultContent": ""},
	                      {"data": "date", "width": "5%","defaultContent": ""},
	                      {"data": "billNo", "width": "5%","defaultContent": ""},
	                      {"data": "contactPerson", "width": "5%","defaultContent": ""},
	                      {"data": "supplierName", "width": "5%","defaultContent": ""},
	                      {"data": "catName", "width": "5%","defaultContent": ""},
	                      {"data": "itemName", "width": "5%","defaultContent": ""},
	                      {"data": "hsnsacno", "width": "5%","defaultContent": ""},
	                      {"data": "quantity", "width": "5%","defaultContent": ""},
	                      {"data": "buyPrice", "width": "5%","defaultContent": ""},
	                      {"data": "vat", "width": "5%","defaultContent": ""},
	                      {"data": "igst", "width": "5%","defaultContent": ""},
	                      {"data": "taxAmount", "width": "5%","defaultContent": ""},
	                      //{"data": "salePrice", "width": "5%","defaultContent": ""},
	                      {"data": "total", "width": "5%","defaultContent": ""},
	                      {"data": "extraDiscount", "width": "5%","defaultContent": ""},
	                      {"data": "expence", "width": "5%","defaultContent": ""},
	                      {"data": "txPerexpence", "width": "5%","defaultContent": ""},
	                      {"data": "finalExpenses", "width": "5%","defaultContent": ""},
	                      {"data": "grossTotal", "width": "5%","defaultContent": ""},
	                      
				        ]
				    } );
				} );
				
			var mydata = catmap;
			$('#supplierBillWiseData').dataTable().fnAddData(mydata);
				
				}).error(function(jqXHR, textStatus, errorThrown){
					if(textStatus==="timeout") {
						$(loaderObj).hide();
						$(loaderObj).find('#errorDiv').show();
					
					}
				});
	
}



//Purchase Report Category Wise
function categoryWisePurchaseReport(){
	
	var input = document.getElementById('catId45'),
    list = document.getElementById('catId_drop45'),
    i,catName;
    for (i = 0; i < list.options.length; ++i) {
    if (list.options[i].value === input.value) {
    	catName = list.options[i].getAttribute('data-value');
    
      }
    }
   
	var params = {};
	params["catName"] = catName;
	
		params["methodName"] = "categoryWisePurchaseReport";

		$.post('/SMT/jsp/utility/controller.jsp',params,function(data)
				{
			$('#supplierCategoryWise').dataTable().fnClearTable();
			var jsonData = $.parseJSON(data);
			var catmap = jsonData.list;
				
				$(document).ready(function() {
				    $('#supplierCategoryWise').DataTable( {
				    	
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
					                .column( 7 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 7 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					            // Total over this page
					            pageTotal = api
					                .column( 8 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 8 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					            // Total over this page
					            pageTotal = api
					                .column( 11 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 11 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					            
					            // Total over this page
					            pageTotal = api
					                .column( 12 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 12 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					       /*  // Total over this page
					            pageTotal = api
					                .column( 16 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 16 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					         // Total over this page
					            pageTotal = api
					                .column( 18 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 18 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);*/
				
					        },
				    	
				    	
				    	destroy: true,
				        searching: false,
				      
				columns: [  
                          {"data": "srno", "width": "5%","defaultContent": ""},
	                      {"data": "date", "width": "5%","defaultContent": ""},
	                      {"data": "billNo", "width": "5%","defaultContent": ""},
	                      {"data": "supplierName", "width": "5%","defaultContent": ""},
	                      {"data": "catName", "width": "5%","defaultContent": ""},
	                      {"data": "itemName", "width": "5%","defaultContent": ""},
	                      {"data": "hsnsacno", "width": "5%","defaultContent": ""},
	                      {"data": "quantity", "width": "5%","defaultContent": ""},
	                      {"data": "buyPrice", "width": "5%","defaultContent": ""},
	                      {"data": "vat", "width": "5%","defaultContent": ""},
	                      {"data": "igst", "width": "5%","defaultContent": ""},
	                      {"data": "taxAmount", "width": "5%","defaultContent": ""},
	                      {"data": "total", "width": "5%","defaultContent": ""},
	                      {"data": "barcodeNo", "width": "5%","defaultContent": ""},
	                      
				        ]
				    } );
				} );
				
			var mydata = catmap;
			$('#supplierCategoryWise').dataTable().fnAddData(mydata);
				
				}).error(function(jqXHR, textStatus, errorThrown){
					if(textStatus==="timeout") {
						$(loaderObj).hide();
						$(loaderObj).find('#errorDiv').show();
					
					}
				});
	
}

//Purchase Report  BarCode No Wise
function barcodeWisePurchaseReport(){
	
	var barcodeNoOurchase = $("#barcodeNoOurchase").val();
	var params = {};
	params["barcodeNoOurchase"] = barcodeNoOurchase;
	
		params["methodName"] = "barcodeWisePurchaseReport";

		$.post('/SMT/jsp/utility/controller.jsp',params,function(data)
				{
			$('#barcodeWisePurchaseReport').dataTable().fnClearTable();
			var jsonData = $.parseJSON(data);
			var catmap = jsonData.list;
				
				$(document).ready(function() {
				    $('#barcodeWisePurchaseReport').DataTable( {
				    	
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
					                .column( 7 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 7 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					            // Total over this page
					            pageTotal = api
					                .column( 8 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 8 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					            // Total over this page
					            pageTotal = api
					                .column( 11 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 11 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					            
					            // Total over this page
					            pageTotal = api
					                .column( 12 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 12 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					       /*  // Total over this page
					            pageTotal = api
					                .column( 16 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 16 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					         // Total over this page
					            pageTotal = api
					                .column( 18 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 18 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);*/
				
					        },
				    	
				    	
				    	destroy: true,
				        searching: false,
				      
				columns: [  
                          {"data": "srno", "width": "5%","defaultContent": ""},
	                      {"data": "date", "width": "5%","defaultContent": ""},
	                      {"data": "billNo", "width": "5%","defaultContent": ""},
	                      {"data": "supplierName", "width": "5%","defaultContent": ""},
	                      {"data": "catName", "width": "5%","defaultContent": ""},
	                      {"data": "itemName", "width": "5%","defaultContent": ""},
	                      {"data": "hsnsacno", "width": "5%","defaultContent": ""},
	                      {"data": "quantity", "width": "5%","defaultContent": ""},
	                      {"data": "buyPrice", "width": "5%","defaultContent": ""},
	                      {"data": "vat", "width": "5%","defaultContent": ""},
	                      {"data": "igst", "width": "5%","defaultContent": ""},
	                      {"data": "taxAmount", "width": "5%","defaultContent": ""},
	                      {"data": "total", "width": "5%","defaultContent": ""},
	                      {"data": "barcodeNo", "width": "5%","defaultContent": ""},
	                      
				        ]
				    } );
				} );
				
			var mydata = catmap;
			$('#barcodeWisePurchaseReport').dataTable().fnAddData(mydata);
				
				}).error(function(jqXHR, textStatus, errorThrown){
					if(textStatus==="timeout") {
						$(loaderObj).hide();
						$(loaderObj).find('#errorDiv').show();
					
					}
				});
	
}

//Purchase Report Single Date
function singleDatePurchase45(){
	
	var purDate = $("#purDate").val();
	
	var params = {};
	params["purDate"] = purDate;
	
		params["methodName"] = "singleDatePurchase45";

		$.post('/SMT/jsp/utility/controller.jsp',params,function(data)
				{
			$('#singleDatePurchase50').dataTable().fnClearTable();
			var jsonData = $.parseJSON(data);
			var catmap = jsonData.list;
				
				$(document).ready(function() {
				    $('#singleDatePurchase50').DataTable( {
				    	
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
					                .column( 8 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 8 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					            // Total over this page
					            pageTotal = api
					                .column( 9 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 9 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					            // Total over this page
					            pageTotal = api
					                .column( 12 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 12 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					            
					            // Total over this page
					            pageTotal = api
					                .column( 14 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 14 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					       /*  // Total over this page
					            pageTotal = api
					                .column( 16 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 16 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					         // Total over this page
					            pageTotal = api
					                .column( 18 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 18 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);*/
				
					        },
				    	
				    	
				    	destroy: true,
				        searching: false,
				      
				columns: [  
                          {"data": "srno", "width": "5%","defaultContent": ""},
	                      {"data": "date", "width": "5%","defaultContent": ""},
	                      {"data": "billNo", "width": "5%","defaultContent": ""},
	                      {"data": "contactPerson", "width": "5%","defaultContent": ""},
	                      {"data": "supplierName", "width": "5%","defaultContent": ""},
	                      {"data": "catName", "width": "5%","defaultContent": ""},
	                      {"data": "itemName", "width": "5%","defaultContent": ""},
	                      {"data": "hsnsacno", "width": "5%","defaultContent": ""},
	                      {"data": "quantity", "width": "5%","defaultContent": ""},
	                      {"data": "buyPrice", "width": "5%","defaultContent": ""},
	                      {"data": "vat", "width": "5%","defaultContent": ""},
	                      {"data": "igst", "width": "5%","defaultContent": ""},
	                      {"data": "taxAmount", "width": "5%","defaultContent": ""},
	                     // {"data": "salePrice", "width": "5%","defaultContent": ""},
	                      {"data": "total", "width": "5%","defaultContent": ""},
	                      {"data": "extraDiscount", "width": "5%","defaultContent": ""},
	                      {"data": "expence", "width": "5%","defaultContent": ""},
	                      {"data": "txPerexpence", "width": "5%","defaultContent": ""},
	                      {"data": "finalExpenses", "width": "5%","defaultContent": ""},
	                      {"data": "grossTotal", "width": "5%","defaultContent": ""},
	                      
				        ]
				    } );
				} );
				
			var mydata = catmap;
			$('#singleDatePurchase50').dataTable().fnAddData(mydata);
				
				}).error(function(jqXHR, textStatus, errorThrown){
					if(textStatus==="timeout") {
						$(loaderObj).hide();
						$(loaderObj).find('#errorDiv').show();
					
					}
				});
	
}

//Purchase Report Two Date
function twoDatePurchase45(){
	
	var pFisDate = $("#pFisDate").val();
	var pEndDate = $("#pEndDate").val();
	
	var params = {};
	params["pFisDate"] = pFisDate;
	params["pEndDate"] = pEndDate;
	
		params["methodName"] = "twoDatePurchase45";

		$.post('/SMT/jsp/utility/controller.jsp',params,function(data)
				{
			$('#twoDatePurchase50').dataTable().fnClearTable();
			var jsonData = $.parseJSON(data);
			var catmap = jsonData.list;
				
				$(document).ready(function() {
				    $('#twoDatePurchase50').DataTable( {
				    	
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
					                .column( 8 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 8 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					            // Total over this page
					            pageTotal = api
					                .column( 9 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 9 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					            // Total over this page
					            pageTotal = api
					                .column( 12 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 12 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					            
					            // Total over this page
					            pageTotal = api
					                .column( 14 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 14 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					       /*  // Total over this page
					            pageTotal = api
					                .column( 16 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 16 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);
					            
					         // Total over this page
					            pageTotal = api
					                .column( 18 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					            // Update footer
					            $( api.column( 18 ).footer() ).html(
					            		
					              parseFloat(pageTotal).toFixed(2)
					               
					            );
					            console.log( pageTotal);*/
				
					        },
				    	
				    	
				    	destroy: true,
				        searching: false,
				      
				columns: [  
                          {"data": "srno", "width": "5%","defaultContent": ""},
	                      {"data": "date", "width": "5%","defaultContent": ""},
	                      {"data": "billNo", "width": "5%","defaultContent": ""},
	                      {"data": "contactPerson", "width": "5%","defaultContent": ""},
	                      {"data": "supplierName", "width": "5%","defaultContent": ""},
	                      {"data": "catName", "width": "5%","defaultContent": ""},
	                      {"data": "itemName", "width": "5%","defaultContent": ""},
	                      {"data": "hsnsacno", "width": "5%","defaultContent": ""},
	                      {"data": "quantity", "width": "5%","defaultContent": ""},
	                      {"data": "buyPrice", "width": "5%","defaultContent": ""},
	                      {"data": "vat", "width": "5%","defaultContent": ""},
	                      {"data": "igst", "width": "5%","defaultContent": ""},
	                      {"data": "taxAmount", "width": "5%","defaultContent": ""},
	                     // {"data": "salePrice", "width": "5%","defaultContent": ""},
	                      {"data": "total", "width": "5%","defaultContent": ""},
	                      {"data": "extraDiscount", "width": "5%","defaultContent": ""},
	                      {"data": "expence", "width": "5%","defaultContent": ""},
	                      {"data": "txPerexpence", "width": "5%","defaultContent": ""},
	                      {"data": "finalExpenses", "width": "5%","defaultContent": ""},
	                      {"data": "grossTotal", "width": "5%","defaultContent": ""},
	                      
				        ]
				    } );
				} );
				
			var mydata = catmap;
			$('#twoDatePurchase50').dataTable().fnAddData(mydata);
				
				}).error(function(jqXHR, textStatus, errorThrown){
					if(textStatus==="timeout") {
						$(loaderObj).hide();
						$(loaderObj).find('#errorDiv').show();
					
					}
				});
	
}