function currentStock(){
	
var params= {};
	
	params["methodName"] = "getAllCurrentStock";

	$.post('/SMT/jsp/utility/controller.jsp',params,function(data)
			{
		
		$('#currStock').dataTable().fnClearTable();
		
		var jsonData = $.parseJSON(data);
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
		            {"data": "totalLitre", "width": "5%", "defaultContent": ""},
		            
		        ],
		      
		        
		    } );
		} );
		
	var mydata = catmap;
	$('#currStock').dataTable().fnAddData(mydata);
	
		}

	);
	
	
}

function getCategoryWiseStock()
{
	var input = document.getElementById('catId'),
    list = document.getElementById('catId_drop'),
    i,catId;
for (i = 0; i < list.options.length; ++i) {
    if (list.options[i].value === input.value) {
    	catId = list.options[i].getAttribute('data-value');
    
    }
}
   
	
	var catId =catId;
	
	
	
	var params = {};
	params["methodName"] = "getCategoryWiseStock";
	params["catId"] = catId;
	
	


	$.post('/SMT/jsp/utility/controller.jsp',params,function(data)
			{
		$('#example').dataTable().fnClearTable();
		var jsonData = $.parseJSON(data);
		var catmap = jsonData.list;
			
			if(catmap == "" || catmap == null || catmap == undefined){
				alert("No data available");
				return false;
			}
		
			$(document).ready(function() {
			    $('#example').DataTable( {
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
                      {"data": "totalLitre", "width": "5%", "defaultContent": ""},
			           
			            
			           
			        ]
			    } );
			} );
			
		var mydata = catmap;
		$('#example').dataTable().fnAddData(mydata);
			
			}).error(function(jqXHR, textStatus, errorThrown){
				if(textStatus==="timeout") {
					$(loaderObj).hide();
					$(loaderObj).find('#errorDiv').show();
				
				}
			});
}


function barcodewisestock(){
	
    var barcodeNo = $('#barcode').val();
   
    var params = {};
    params["barcodeNo"] = barcodeNo;
	params["methodName"] = "getBarcodeWiseStock";
	
	$.post('/SMT/jsp/utility/controller.jsp',params,function(data)
			{
		$('#example').dataTable().fnClearTable();
		var jsonData = $.parseJSON(data);
		var catmap = jsonData.list;
		if(catmap == null || catmap == "" || catmap == undefined){
			alert("No data available for selected barcode");
			return false;
		}	
		
			$(document).ready(function() {
			    $('#example').DataTable( {
			    	
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
                      {"data": "total", "width": "5%", "defaultContent": ""},
			           
			        ]
			    } );
			} );
			
		var mydata = catmap;
		$('#example').dataTable().fnAddData(mydata);
			
			}).error(function(jqXHR, textStatus, errorThrown){
				if(textStatus==="timeout") {
					$(loaderObj).hide();
					$(loaderObj).find('#errorDiv').show();
				
				}
			});
	
	
}

function billwisestock(){
	
	var input = document.getElementById('catId'),
    list = document.getElementById('catId_drop'),
    i,Billno;
for (i = 0; i < list.options.length; ++i) {
    if (list.options[i].value === input.value) {
    	Billno = list.options[i].getAttribute('data-value');
    
    }
}
   
	
	var Billno =Billno;
	
	   
	   var params = {};
		params["methodName"] = "getBillNoWiseStock";
		params["Billno"] = Billno;
		
		


		$.post('/SMT/jsp/utility/controller.jsp',params,function(data)
				{
			$('#example').dataTable().fnClearTable();
			var jsonData = $.parseJSON(data);
			var catmap = jsonData.list;
				
				$(document).ready(function() {
				    $('#example').DataTable( {
				    	
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
				
					            pageTotal = api
				                .column( 3 )
				                .data()
				                .reduce( function (a, b) {
				                    return intVal(a) + intVal(b);
				                }, 0 );
				 
				               // Update footer
				                $( api.column( 3 ).footer() ).html(
				                ' '+ parseFloat(pageTotal).toFixed(2));
				            
				                console.log( pageTotal);
				                
				                pageTotal = api
				                .column( 4 )
				                .data()
				                .reduce( function (a, b) {
				                    return intVal(a) + intVal(b);
				                }, 0 );
				 
				            // Update footer
				            $( api.column( 4 ).footer() ).html(
				                'Rs'+' '+ parseFloat(pageTotal).toFixed(2));
				            
				            console.log( pageTotal);
				                
					        },
				    	
				    	
				    	destroy: true,
				        searching: false,
				      
				columns: [  
                          
                          {"data": "billNo", "width": "5%", "defaultContent": ""},
	                      {"data": "catName", "width": "5%", "defaultContent": ""},
	                      {"data": "itemName", "width": "5%", "defaultContent": ""},
	                      {"data": "quantity", "width": "5%", "defaultContent": ""},
	                      {"data": "buyPrice", "width": "5%", "defaultContent": ""},
	                      {"data": "vat", "width": "5%", "defaultContent": ""},
	                      {"data": "barcodeNo", "width": "5%", "defaultContent": ""},
				           
				        ]
				    } );
				} );
				
			var mydata = catmap;
			$('#example').dataTable().fnAddData(mydata);
				
				}).error(function(jqXHR, textStatus, errorThrown){
					if(textStatus==="timeout") {
						$(loaderObj).hide();
						$(loaderObj).find('#errorDiv').show();
					
					}
				});
		
}