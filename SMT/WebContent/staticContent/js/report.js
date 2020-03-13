/**
 * 
 */
function singleDatePurchase(){
	
	var params= {};
	var fDate = $("#fDate").val();
	
	params["fDate"]= fDate;

	  
		params["methodName"] = "singleDatePurchase";

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
					            // Total over this page
					            pageTotal = api
					                .column( 15 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					            // Update footer
					            $( api.column( 15 ).footer() ).html(
					              parseFloat(pageTotal).toFixed(2)
					            );
					            // Total over this page
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
					                .column( 17 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					            // Update footer
					            $( api.column( 17 ).footer() ).html(
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
					            console.log( pageTotal);
					            
					         // Total over this page
					            pageTotal = api
					                .column( 19 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					            // Update footer
					            $( api.column( 19 ).footer() ).html(
					              parseFloat(pageTotal).toFixed(2)
					            );
					            console.log( pageTotal);
				
					        },
				    	
					        "sPaginationType": "full_numbers",
					    	destroy: true,
					        searching: true,
					        orderable: true,
				      
				columns: [  
                          {data: "serialnumber", "width": "5%" ,"defaultContent": ""},
	                      {data: "fetchDate", "width": "5%" , "defaultContent": ""},
	                      {data: "supplierName", "width": "5%" , "defaultContent": ""},
	                      {data: "billNo", "width": "5%" , "defaultContent": ""},
	                      {data: "GstTinNo", "width": "5%", "defaultContent": ""},
	                      {data: "itemName", "width": "5%", "defaultContent": ""},
	                      {data: "hsnsacno", "width": "5%", "defaultContent": ""},
	                      {data: "buyPrice", "width": "5%", "defaultContent": ""},
	                      {data: "quantity", "width": "5%", "defaultContent": ""},
	                      {data: "total", "width": "5%", "defaultContent": ""},
	                      {data: "fivePercentageGST", "width": "5%", "defaultContent": ""},
	  		              {data: "twelwePercentageGST", "width": "5%", "defaultContent": ""},
	  		              {data: "eighteenPercentageGST" , "width": "5%", "defaultContent": ""},
	  		              {data: "twentyEightPercentageGST", "width": "5%", "defaultContent": ""},
	  		              {data: "iGSTFivePercentage", "width": "5%", "defaultContent": ""},
	  		              {data: "iGSTTwelwePercentage" , "width": "5%", "defaultContent": ""},
	  		              {data: "iGSTEighteenPercentage", "width": "5%", "defaultContent": ""},
	  		              {data: "iGSTTwentyeightPercentage", "width": "5%", "defaultContent": ""},
	  		              {data: "totalTaxAmount" , "width": "5%", "defaultContent": ""},
	  		              {data: "netAmount" , "width": "5%", "defaultContent": ""}
	                      
				           
				        ]
				    } );
				} );
				
			var mydata = catmap;
			$('#example').dataTable().fnAddData(mydata);
				
				});
		
		
}

function purchaseReportBetweenTwoDates(){
	
	var params= {};
	var fisDate = $("#fisDate").val();
	var endDate = $("#endDate").val();
	
	params["fisDate"]= fisDate;
	params["endDate"]= endDate;

	  
		params["methodName"] = "purchaseReportBetweenTwoDates";

		$.post('/SMT/jsp/utility/controller.jsp',params,function(data)
				{
			
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
					            // Total over this page
					            pageTotal = api
					                .column( 15 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					            // Update footer
					            $( api.column( 15 ).footer() ).html(
					              parseFloat(pageTotal).toFixed(2)
					            );
					            // Total over this page
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
					                .column( 17 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					            // Update footer
					            $( api.column( 17 ).footer() ).html(
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
					            console.log( pageTotal);
					            
					            // Total over this page
					            pageTotal = api
					                .column( 19 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					            // Update footer
					            $( api.column( 19 ).footer() ).html(
					              parseFloat(pageTotal).toFixed(2)
					            );
					            console.log( pageTotal);
					            
					         // Total over this page
					            pageTotal = api
					                .column( 20 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					            // Update footer
					            $( api.column( 20 ).footer() ).html(
					              parseFloat(pageTotal).toFixed(2)
					            );
					            console.log( pageTotal);
					            
					         // Total over this page
					            pageTotal = api
					                .column( 21 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					            // Update footer
					            $( api.column( 21 ).footer() ).html(
					              parseFloat(pageTotal).toFixed(2)
					            );
					            console.log( pageTotal);
					            
					         // Total over this page
					            pageTotal = api
					                .column( 22 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					            // Update footer
					            $( api.column( 22 ).footer() ).html(
					              parseFloat(pageTotal).toFixed(2)
					            );
					            console.log( pageTotal);
					            
					         // Total over this page
					            pageTotal = api
					                .column( 23 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					            // Update footer
					            $( api.column( 23 ).footer() ).html(
					              parseFloat(pageTotal).toFixed(2)
					            );
					            console.log( pageTotal);
					            
					         // Total over this page
					            pageTotal = api
					                .column( 24 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					            // Update footer
					            $( api.column( 24 ).footer() ).html(
					              parseFloat(pageTotal).toFixed(2)
					            );
					            console.log( pageTotal);
				
					        },
				    	
				    	
					        "sPaginationType": "full_numbers",
					    	destroy: true,
					        searching: true,
					        orderable: true,
				      
				columns: [  
				          
	                      {"data": "serialnumber", "width": "5%", "defaultContent": ""},
	                      {"data": "fetchDate", "width": "5%", "defaultContent": ""},
	                      {"data": "supplierName", "width": "5%", "defaultContent": ""},
	                      {"data": "billNo", "width": "5%", "defaultContent": ""},
	                      {"data": "GstTinNo", "width": "5%", "defaultContent": ""},
	                      {"data": "itemName", "width": "5%", "defaultContent": ""},
	                      {"data": "hsnsacno", "width": "5%", "defaultContent": ""},
	                      {"data": "buyPrice", "width": "5%", "defaultContent": ""},
	                      {"data": "quantity", "width": "5%", "defaultContent": ""},
	                      {"data": "total", "width": "5%", "defaultContent": ""},
	                      {"data": "additionalCost", "width": "5%", "defaultContent": ""},
	                      {"data": "fivePercentageGST", "width": "5%", "defaultContent": ""},
	                      {"data": "fivePercentageSGST", "width": "5%", "defaultContent": ""},
	  		              {"data": "twelwePercentageGST", "width": "5%", "defaultContent": ""},
	  		              {"data": "twelwePercentageSGST", "width": "5%", "defaultContent": ""},
	  		              {"data": "eighteenPercentageGST" , "width": "5%", "defaultContent": ""},
	  		              {"data": "eighteenPercentageSGST" , "width": "5%", "defaultContent": ""},
	  		              {"data": "twentyEightPercentageGST", "width": "5%", "defaultContent": ""},
	  		              {"data": "twentyEightPercentageSGST", "width": "5%", "defaultContent": ""},
	  		              {"data": "iGSTFivePercentage", "width": "5%", "defaultContent": ""},
	  		              {"data": "iGSTTwelwePercentage" , "width": "5%", "defaultContent": ""},
	  		              {"data": "iGSTEighteenPercentage", "width": "5%", "defaultContent": ""},
	  		              {"data": "iGSTTwentyeightPercentage", "width": "5%", "defaultContent": ""},
	  		              {"data": "totalTaxAmount" , "width": "5%", "defaultContent": ""},
	  		              {"data": "netAmount" , "width": "5%", "defaultContent": ""}
				           
				        ]
				    } );
				} );
				
			var mydata = catmap;
			$('#example1').dataTable().fnAddData(mydata);
				
				});
}

//CA Purchase Report Two Date
function caReportBetweenTwoDates(){
	
	var fisDate1 = $("#fisDate1").val();
	var endDate1 = $("#endDate1").val();
	
	var params = {};
	params["fisDate1"] = fisDate1;
	params["endDate1"] = endDate1;
	
		params["methodName"] = "caReportBetweenTwoDates";

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
                          {"data": "srno", "width": "5%","defaultContent": ""},
	                      {"data": "catName", "width": "5%","defaultContent": ""},
	                      {"data": "itemName", "width": "5%","defaultContent": ""},
	                      {"data": "hsnsacno", "width": "5%","defaultContent": ""},
	                      {"data": "quantity", "width": "5%","defaultContent": ""},
	                      {"data": "vat", "width": "5%","defaultContent": ""},
	                      {"data": "igst", "width": "5%","defaultContent": ""},
	                      
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

//CA Purchase Report Two Date
function tallyReportBetweenTwoDates(){
	
	var fisDatetally = $("#fisDatetally").val();
	var endDatetally = $("#endDatetally").val();
	
	var params = {};
	params["fisDatetally"] = fisDatetally;
	params["endDatetally"] = endDatetally;
	
		params["methodName"] = "tallyReportBetweenTwoDates";

		$.post('/SMT/jsp/utility/controller.jsp',params,function(data)
				{
			$('#tallyreport').dataTable().fnClearTable();
			var jsonData = $.parseJSON(data);
			//var dictstring = JSON.stringify(jsonData);
			//document.getElementById("demo12").innerHTML = dictstring;
			/*var fs = require('fs');
			fs.writeFile("thing.json", dictstring);*/
			var catmap = jsonData.list;
				
				$(document).ready(function() {
				    $('#tallyreport').DataTable( {
				    	
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
				          {"data": "itemName", "width": "5%","defaultContent": ""},
	                      {"data": "catName", "width": "5%","defaultContent": ""},
	                      {"data": "baseunit", "width": "5%","defaultContent": ""},
	                      {"data": "alias", "width": "5%","defaultContent": ""},
	                      {"data": "barcodeNo", "width": "5%","defaultContent": ""},
	                      {"data": "quantity", "width": "5%","defaultContent": ""},
	                      {"data": "buyPrice", "width": "5%","defaultContent": ""},
	                      {"data": "total", "width": "5%","defaultContent": ""},
	                      {"data": "catName", "width": "5%","defaultContent": ""},
	                      {"data": "description", "width": "5%","defaultContent": ""},
	                      {"data": "vatapplicable", "width": "5%","defaultContent": ""},
	                      {"data": "gstapplicable", "width": "5%","defaultContent": ""},
	                      {"data": "typesofsupply", "width": "5%","defaultContent": ""},
	                      {"data": "gstapplicabledate", "width": "5%","defaultContent": ""},
	                      {"data": "hsnsacno", "width": "5%","defaultContent": ""},
	                      {"data": "hsnsacdes", "width": "5%","defaultContent": ""},
	                      {"data": "taxability", "width": "5%","defaultContent": ""},
	                      {"data": "igst", "width": "5%","defaultContent": ""},
	                      {"data": "cgst", "width": "5%","defaultContent": ""},
	                      {"data": "sgst", "width": "5%","defaultContent": ""},
	                      {"data": "rateofcess", "width": "5%","defaultContent": ""},
	                      
				        ]
				    } );
				} );
				
			var mydata = catmap;
			$('#tallyreport').dataTable().fnAddData(mydata);
				
				}).error(function(jqXHR, textStatus, errorThrown){
					if(textStatus==="timeout") {
						$(loaderObj).hide();
						$(loaderObj).find('#errorDiv').show();
					
					}
				});
	
}

function singleDateSale(){
	
	var params= {};
	var fDate = $("#fDate").val();
	
	params["fDate"]= fDate;

	  
		params["methodName"] = "singleDateSale";

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
				
					        },
				    	
				    	
				    	destroy: true,
				        searching: false,
				      
				columns: [  
				          
	                      {"data": "billNo", "width": "5%"},
	                      {"data": "carNo", "width": "5%"},
	                      {"data": "barcodeNo", "width": "5%"},
	                      {"data": "itemName", "width": "5%"},
	                      {"data": "categoryName", "width": "5%"},
	                      {"data": "salePrice", "width": "5%"},
	                      {"data": "ownerName", "width": "5%"},
	                      {"data": "contactNo", "width": "5%"},
	                      {"data": "totalAmt", "width": "5%"},
	                      {"data": "discount", "width": "5%"},
	                      
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

function getSaleDetailsBetweenTwoDates(){
	
	var params= {};
	var fisDate = $("#fisDate").val();
	var endDate = $("#endDate").val();
	
	params["fisDate"]= fisDate;
	params["endDate"]= endDate;

	  
		params["methodName"] = "getSaleDetailsBetweenTwoDates";

		$.post('/SMT/jsp/utility/controller.jsp',params,function(data)
				{
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
				
					        },
				    	
				    	
				    	destroy: true,
				        searching: false,
				      
				columns: [  
				          
                               {"data": "billNo", "width": "5%"},
                               {"data": "carNo", "width": "5%"},
                               {"data": "barcodeNo", "width": "5%"},
                               {"data": "itemName", "width": "5%"},
                               {"data": "categoryName", "width": "5%"},
                               {"data": "salePrice", "width": "5%"},
                               {"data": "ownerName", "width": "5%"},
                               {"data": "contactNo", "width": "5%"},
                               {"data": "totalAmt", "width": "5%"},
                               {"data": "discount", "width": "5%"},
	                      
				           
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

function dayCloseReport(){
	
	var params= {};
	
		params["methodName"] = "dayCloseReport";

		$.post('/SMT/jsp/utility/controller.jsp',params,function(data)
				{
			$('#dayClose').dataTable().fnClearTable();
			var jsonData = $.parseJSON(data);
			var catmap = jsonData.list;
				
				$(document).ready(function() {
				    $('#dayClose').DataTable( {
				    	
				    	/* dom: 'Bfrtip',
				         buttons: [
				             'copy', 'csv', 'excel', 'pdf', 'print'
				         ],*/
				    	
				    	
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
					                .column( 8 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					 
					         
					            // Update footer
					            $( api.column( 8 ).footer() ).html(
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
                               {"data": "itemName", "width": "5%","defaultContent": ""},
                               {"data": "categoryName", "width": "5%","defaultContent": ""},
                               {"data": "salePrice", "width": "5%","defaultContent": ""},
                               {"data": "quantity", "width": "5%","defaultContent": ""},
                               {"data": "totalAmt", "width": "5%","defaultContent": ""},
                               {"data": "discount", "width": "5%","defaultContent": ""},
	                      
				           
				        ],
				        dom: 'Bfrtip',
				         buttons: [
				             'copy', 'csv', 'excel', 'pdf', 'print'
				         ]
					        
				    } );
				} );
				
			var mydata = catmap;
			$('#dayClose').dataTable().fnAddData(mydata);
				
				}).error(function(jqXHR, textStatus, errorThrown){
					if(textStatus==="timeout") {
						$(loaderObj).hide();
						$(loaderObj).find('#errorDiv').show();
					
					}
				});
}


function CategoryWiseSale(){
	
	var params= {};
	var catName= $('#catId').val();
	
	params["catName"]= catName;

		params["methodName"] = "CategoryWiseSaleReport";

		$.post('/SMT/jsp/utility/controller.jsp',params,function(data)
				{
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

				
					        },
				    	
				    	
				    	destroy: true,
				        searching: false,
				      
				columns: [  
				          
	                      {"data": "billNo", "width": "5%"},
	                      {"data": "carNo", "width": "5%"},
	                      {"data": "barcodeNo", "width": "5%"},
	                      {"data": "itemName", "width": "5%"},
	                      {"data": "categoryName", "width": "5%"},
	                      {"data": "salePrice", "width": "5%"},
	                      {"data": "ownerName", "width": "5%"},
	                      {"data": "contactNo", "width": "5%"},
	                      {"data": "totalAmt", "width": "5%"},
	                      {"data": "discount", "width": "5%"},
	                      
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

function gstsaleReportBetweenTwoDates(){
	
	var params= {};
	var fisDate = $("#firstDate").val();
	var endDate = $("#secondDate").val();
	
	params["fisDate"]= fisDate;
	params["endDate"]= endDate;

	  
		params["methodName"] = "gstSaleReportBetweenTwoDates";

		$.post('/SMT/jsp/utility/controller.jsp',params,function(data)
				{
			
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
					            
					            // Total over this page
					            pageTotal = api
					                .column( 15 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					            // Update footer
					            $( api.column( 15 ).footer() ).html(
					              parseFloat(pageTotal).toFixed(2)
					            );
					            console.log( pageTotal);
					            
					         // Total over this page
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
					                .column( 17 )
					                .data()
					                .reduce( function (a, b) {
					                    return intVal(a) + intVal(b);
					                }, 0 );
					            // Update footer
					            $( api.column( 17 ).footer() ).html(
					              parseFloat(pageTotal).toFixed(2)
					            );
					            console.log( pageTotal);
				
					        },
				    	
				    	
					        "sPaginationType": "full_numbers",
					    	destroy: true,
					        searching: true,
					        orderable: true,
				      
				columns: [  
				          
	                      {"data": "serialnumber", "width": "5%", "defaultContent": ""},
	                      {"data": "fetchDate", "width": "5%", "defaultContent": ""},
	                      //{"data": "supplierName", "width": "5%", "defaultContent": ""},
	                      {"data": "billNo", "width": "5%", "defaultContent": ""},
	                      //{"data": "GstTinNo", "width": "5%", "defaultContent": ""},
	                      {"data": "itemName", "width": "5%", "defaultContent": ""},
	                      {"data": "hsnsacno", "width": "5%", "defaultContent": ""},
	                      {"data": "buyPrice", "width": "5%", "defaultContent": ""},
	                      {"data": "quantity", "width": "5%", "defaultContent": ""},
	                      {"data": "total", "width": "5%", "defaultContent": ""},
	                      {"data": "fivePercentageGST", "width": "5%", "defaultContent": ""},
	  		              {"data": "twelwePercentageGST", "width": "5%", "defaultContent": ""},
	  		              {"data": "eighteenPercentageGST" , "width": "5%", "defaultContent": ""},
	  		              {"data": "twentyEightPercentageGST", "width": "5%", "defaultContent": ""},
	  		              {"data": "iGSTFivePercentage", "width": "5%", "defaultContent": ""},
	  		              {"data": "iGSTTwelwePercentage" , "width": "5%", "defaultContent": ""},
	  		              {"data": "iGSTEighteenPercentage", "width": "5%", "defaultContent": ""},
	  		              {"data": "iGSTTwentyeightPercentage", "width": "5%", "defaultContent": ""},
	  		              {"data": "totalTaxAmount" , "width": "5%", "defaultContent": ""},
	  		              {"data": "netAmount" , "width": "5%", "defaultContent": ""}
				           
				        ]
				    } );
				} );
				
			var mydata = catmap;
			$('#example4').dataTable().fnAddData(mydata);
				
				});
}

function BarcodeWiseReport(){
	
	var params= {};
	var catName= $('#barcodeId').val();
	
	params["catName"]= catName;

		params["methodName"] = "BarcodeWiseReport";

		$.post('/SMT/jsp/utility/controller.jsp',params,function(data)
				{
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

				
					        },
				    	
				    	
				    	destroy: true,
				        searching: false,
				      
				columns: [  
				          
	                      {"data": "date", "width": "5%"},
	                      {"data": "itemName", "width": "5%"},
	                      {"data": "categoryName", "width": "5%"},
	                      {"data": "hsnsacNo", "width": "5%"},
	                      {"data": "quantity", "width": "5%"},
	                      {"data": "buyPrice", "width": "5%"},
	                      {"data": "salePrice", "width": "5%"},
	                      {"data": "vat", "width": "5%"},
	                      {"data": "igst", "width": "5%"},
	                      {"data": "total", "width": "5%"},
	                      
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

//CA Sale Report Two Date
function caSaleReportBetweenTwoDates(){
	
	var fisDate1 = $("#fisDate1").val();
	var endDate1 = $("#endDate1").val();
	
	var params = {};
	params["fisDate1"] = fisDate1;
	params["endDate1"] = endDate1;
	
		params["methodName"] = "caSaleReportBetweenTwoDates";

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
                          {"data": "srNo", "width": "5%","defaultContent": ""},
	                      {"data": "categoryName", "width": "5%","defaultContent": ""},
	                      {"data": "itemName", "width": "5%","defaultContent": ""},
	                      {"data": "hsnSacNo", "width": "5%","defaultContent": ""},
	                      {"data": "quantity", "width": "5%","defaultContent": ""},
	                      {"data": "gst", "width": "5%","defaultContent": ""},
	                      {"data": "iGst", "width": "5%","defaultContent": ""},
	                      
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