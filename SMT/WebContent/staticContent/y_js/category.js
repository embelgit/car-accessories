  
 

/*require(['ojs/ojcore', 'knockout', 'jquery', 'ojs/ojknockout', 'ojs/ojinputtext'], 
    
    function (oj, ko, $)
    {
      
      function SimpleModel()
      {
        this.value = ko.observable("Green");
      };

      $(document).ready(function ()
      {
        ko.applyBindings(new SimpleModel(), document.getElementById('div1'));
      });
      
    });*/
  
function valAddMainCat()
{
	var categoryName= $('#categoryName').val();
	
	//var patt = /^[ a-zA-Z ]+$/;
	var patt = /^[a-zA-Z0-9]+$/;
	var t = patt.test(categoryName);
	
	if(categoryName != null && categoryName != "" && categoryName != " ")
		{
		 if(t){
			 categoryd();
		    }
		 else{
			 
			 alert("Enter only Alphabet");
			 //document.getElementById("eoalfa").style.display = "initial";
		 }
		}
	else{
		alert("Please Enter Category name");
	}
}

function categoryd(){
	
	category();
	}
	 		function category(){
	 
	 
	        document.cat.btn.disabled = true;
	        
	        
	       
			var categoryName= $('#categoryName').val();
			var activeYn = $('#activeYn').val();
			var isleafCat=$('#isleafCat').val();
		    var isrootCat=$('#isrootCat').val();
		   
				
			var params= {};
			
			params ["categoryName"] = categoryName;
			params ["activeYn"] = activeYn;
			params ["isleafCat"] = isleafCat;
			params ["isrootCat"] = isrootCat;
			
			
			
			params["methodName"] = "regCategory";
	    	
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
	    
	 		function catkHelper()
	 		{
	 			var offerList="";
	 			this.getAllCategory = getAllCategory;
	 			this.fillCategoryList = fillCategoryList;

	 			function fillCategoryList()
	 			{
	 				var categoryName = $("#categoryName").val();
	 			}

	 			function getAllCategory()
	 			
	 			{
	 				var params= {};
	 				
	 				params["methodName"] = "getAllCategoryReport";
	 				
	 				$.post('/SMT/jsp/utility/controller.jsp',params,function(data)
	 						{
	 					alert(data)
	 					var jsonData = $.parseJSON(data);
	 					var catmap = jsonData.list;
	 					$.each(jsonData,function(i,v)

	 							{
	 						
	 						var categoryName =v.categoryName;
	 						

	 						jsonData[jsonData]={categoryName:""};
	 						$("#list4").jqGrid({
	 							datatype: "local",
	 							//editurl: 'clientArray',
	 							height: 100,
	 							colNames:['categoryName', ],
	 							colModel:[
	 							          
	 							          {name:'categoryName',
	 							        	width:140  
	 							          }
	 							          
	 							           ],

	 							          sortname: 'ID',
	 							          sortorder : 'desc',
	 							          loadonce: true,
	 							          viewrecords: true,


	 							          caption: "Category table",
	 						});

	 						$("#list4").addRowData(i,jsonData[i]);
	 						


	 							});
	 						}).error(function(jqXHR, textStatus, errorThrown){
	 							if(textStatus==="timeout") {
	 								$(loaderObj).hide();
	 								$(loaderObj).find('#errorDiv').show();
	 							}
	 						});
	 			}

	 		}

	 		var category11 = new catkHelper();

	 		
function getAllMAinCaregory(){
	
	
	var params= {};
	
	params["methodName"] = "getAllMAinCaregory";

	$.post('/SMT/jsp/utility/controller.jsp',params,function(data)
			{
		
		$('#catName').dataTable().fnClearTable();
		
		var jsonData = $.parseJSON(data);
		var catmap = jsonData.list;
		
		
		
		$(document).ready(function() {
		 var total =   $('#catName').DataTable( {
			 
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
		            
		        ],
		      
		        
		    } );
		} );
		
	var mydata = catmap;
	$('#catName').dataTable().fnAddData(mydata);
	
		}

	);
	
	
	
}	


function reNamecategory(){
	 
	 
    document.getElementById("btn").disabled = true;
    
    var input = document.getElementById('catId'),
    list = document.getElementById('catId_drop'),
    i,pk_cat_id;
    for (i = 0; i < list.options.length; ++i) {
    if (list.options[i].value === input.value) {
    	pk_cat_id = list.options[i].getAttribute('data-value');
    }
    }
   
	var reNameCat= $('#reNameCat').val();
	var activeYn = $('#activeYn').val();
	
	var params= {};
	
	params ["reNameCat"] = reNameCat;
	params ["pk_cat_id"] = pk_cat_id;
	
	
	
	params["methodName"] = "reNameCategory";
	
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
	 		
			