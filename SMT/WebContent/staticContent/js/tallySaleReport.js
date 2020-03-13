

//CA Purchase Report Two Date
function tallySaleReportBetweenTwoDates(){
	
	var fisDatetally = $("#fisDatetally").val();
	var endDatetally = $("#endDatetally").val();
	
	var params = {};
	params["fisDatetally"] = fisDatetally;
	params["endDatetally"] = endDatetally;
	
		params["methodName"] = "tallySaleReportBetweenTwoDates";

		$.post('/SMT/jsp/utility/controller.jsp',params,function(data)
				{
			$('#tallySalereport').dataTable().fnClearTable();
			var jsonData = $.parseJSON(data);
			//var dictstring = JSON.stringify(jsonData);
			//document.getElementById("demo12").innerHTML = dictstring;
			/*var fs = require('fs');
			fs.writeFile("thing.json", dictstring);*/
			var catmap = jsonData.list;
				
				$(document).ready(function() {
				    $('#tallySalereport').DataTable( {
				    	
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
				          
	                      {"data": "billNo", "width": "5%","defaultContent": ""},
	                      {"data": "vchType", "width": "5%","defaultContent": ""},
	                      {"data": "vchRef", "width": "5%","defaultContent": ""},
	                      {"data": "date", "width": "5%","defaultContent": ""},
	                      {"data": "partyType", "width": "5%","defaultContent": ""},
	                      {"data": "purchaseLedger", "width": "5%","defaultContent": ""},
	                      {"data": "godown", "width": "5%","defaultContent": ""},
	                      {"data": "itemName", "width": "5%","defaultContent": ""},
	                      {"data": "batch", "width": "5%","defaultContent": ""},
	                      {"data": "unit", "width": "5%","defaultContent": ""},
	                      {"data": "quantity", "width": "5%","defaultContent": ""},
	                      {"data": "salePrice", "width": "5%","defaultContent": ""},
	                      {"data": "total", "width": "5%","defaultContent": ""},
	                      {"data": "additionalLedger", "width": "5%","defaultContent": ""},
	                      {"data": "transportExpenses", "width": "5%","defaultContent": ""},
	                      {"data": "gstLedger", "width": "5%","defaultContent": ""},
	                      {"data": "taxAmt", "width": "5%","defaultContent": ""},
	                      {"data": "grossAmt", "width": "5%","defaultContent": ""},
	                      {"data": "narration", "width": "5%","defaultContent": ""}
	                      
				        ]
				    } );
				} );
				
			var mydata = catmap;
			$('#tallySalereport').dataTable().fnAddData(mydata);
				
				}).error(function(jqXHR, textStatus, errorThrown){
					if(textStatus==="timeout") {
						$(loaderObj).hide();
						$(loaderObj).find('#errorDiv').show();
					
					}
				});
	
}

//CA Purchase Report Two Date
function tallySaleReportBetweenTwoDates1(){
	
	var fisDatetally = $("#fDate").val();
	var endDatetally = $("#eDate").val();
	
	var params = {};
	params["fisDatetally"] = fisDatetally;
	params["endDatetally"] = endDatetally;
	
		params["methodName"] = "tallySaleReportBetweenTwoDates1";

		$.post('/SMT/jsp/utility/controller.jsp',params,function(data)
				{
			$('#example').dataTable().fnClearTable();
			var jsonData = $.parseJSON(data);
			//var dictstring = JSON.stringify(jsonData);
			//document.getElementById("demo12").innerHTML = dictstring;
			/*var fs = require('fs');
			fs.writeFile("thing.json", dictstring);*/
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
					            
					        },
				    	
				    	
				    	destroy: true,
				        searching: false,
				      
				columns: [ 
				          
	                      {"data": "billNo", "width": "5%","defaultContent": ""},
	                      {"data": "vchType", "width": "5%","defaultContent": ""},
	                      {"data": "vchRef", "width": "5%","defaultContent": ""},
	                      {"data": "date", "width": "5%","defaultContent": ""},
	                      {"data": "partyType", "width": "5%","defaultContent": ""},
	                      {"data": "purchaseLedger", "width": "5%","defaultContent": ""},
	                      {"data": "godown", "width": "5%","defaultContent": ""},
	                      {"data": "itemName", "width": "5%","defaultContent": ""},
	                      {"data": "batch", "width": "5%","defaultContent": ""},
	                      {"data": "unit", "width": "5%","defaultContent": ""},
	                      {"data": "quantity", "width": "5%","defaultContent": ""},
	                      {"data": "salePrice", "width": "5%","defaultContent": ""},
	                      {"data": "total", "width": "5%","defaultContent": ""},
	                      {"data": "additionalLedger", "width": "5%","defaultContent": ""},
	                      {"data": "transportExpenses", "width": "5%","defaultContent": ""},
	                      {"data": "gstLedger", "width": "5%","defaultContent": ""},
	                      {"data": "taxAmt", "width": "5%","defaultContent": ""},
	                      {"data": "grossAmt", "width": "5%","defaultContent": ""},
	                      {"data": "narration", "width": "5%","defaultContent": ""}
	                      
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

//CA Purchase Report Two Date
function tallySaleReportBetweenTwoDates2(){
	
	var fisDatetally = $("#fisDate1").val();
	var endDatetally = $("#endDate1").val();
	
	var params = {};
	params["fisDatetally"] = fisDatetally;
	params["endDatetally"] = endDatetally;
	
		params["methodName"] = "tallySaleReportBetweenTwoDates2";

		$.post('/SMT/jsp/utility/controller.jsp',params,function(data)
				{
			$('#careport').dataTable().fnClearTable();
			var jsonData = $.parseJSON(data);
			//var dictstring = JSON.stringify(jsonData);
			//document.getElementById("demo12").innerHTML = dictstring;
			/*var fs = require('fs');
			fs.writeFile("thing.json", dictstring);*/
			var catmap = jsonData.list;
				
				$(document).ready(function() {
				    $('#careport').DataTable( {
				    	
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
				          
	                      {"data": "billNo", "width": "5%","defaultContent": ""},
	                      {"data": "vchType", "width": "5%","defaultContent": ""},
	                      {"data": "vchRef", "width": "5%","defaultContent": ""},
	                      {"data": "date", "width": "5%","defaultContent": ""},
	                      {"data": "partyType", "width": "5%","defaultContent": ""},
	                      {"data": "purchaseLedger", "width": "5%","defaultContent": ""},
	                      {"data": "godown", "width": "5%","defaultContent": ""},
	                      {"data": "itemName", "width": "5%","defaultContent": ""},
	                      {"data": "batch", "width": "5%","defaultContent": ""},
	                      {"data": "unit", "width": "5%","defaultContent": ""},
	                      {"data": "quantity", "width": "5%","defaultContent": ""},
	                      {"data": "salePrice", "width": "5%","defaultContent": ""},
	                      {"data": "total", "width": "5%","defaultContent": ""},
	                      {"data": "additionalLedger", "width": "5%","defaultContent": ""},
	                      {"data": "transportExpenses", "width": "5%","defaultContent": ""},
	                      {"data": "gstLedger", "width": "5%","defaultContent": ""},
	                      {"data": "taxAmt", "width": "5%","defaultContent": ""},
	                      {"data": "grossAmt", "width": "5%","defaultContent": ""},
	                      {"data": "narration", "width": "5%","defaultContent": ""}
	                      
				        ]
				    } );
				} );
				
			var mydata = catmap;
			$('#careport').dataTable().fnAddData(mydata);
				
				}).error(function(jqXHR, textStatus, errorThrown){
					if(textStatus==="timeout") {
						$(loaderObj).hide();
						$(loaderObj).find('#errorDiv').show();
					
					}
				});
	
}