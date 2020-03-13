
 	
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
 	//alert("category+++++"+category)
 
 	params["itemName"] = itemName;
 	params["hsnsacno"] = hsnsacno;
   
    params["modelName"] = modelName;

    params["catId"] =catId;
    params["categoryName"] =categoryName;
	
	        
	 	   params["methodName"] = "doProductDetail";
	 	    
	 	    
	 	    
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


function modifypRODUCT(){
	
	
	var pkProductId= $('#pkProductId').val();
	alert(pkProductId)
	var itemName= $('#itemName').val();
	var commision=$('#commision').val();
	
	var isVat=$('#isVat').val();
	var vatPercentage=$('#vatPercentage').val();
	var isalternateprod=$('#isalternateprod').val();
	var isItem=$('#isItem').val();

	
	
	var params= {};
	params["pkProductId"] = pkProductId;
    params["itemName"] = itemName;
	params["isVat"] = isVat;
	params["vatPercentage"] = vatPercentage;
	params["isalternateprod"] = isalternateprod;
	params["commision"] = commision;
	params["isItem"] = isItem;

	
	params["methodName"] = "modifyProductDetail";
	
	$.post('/SMT/jsp/utility/controller.jsp',params,function(data)
	    	{
				alert(data);
				location.reload();
				document.getElementById("btn").disabled = false;
				document.prod.reset();
			}
	    	).error(function(jqXHR, textStatus, errorThrown){
	    		if(textStatus==="timeout") {
	    			$(loaderObj).hide();
	    			$(loaderObj).find('#errorDiv').show();
	    		}
	    	});
}

function POHelper()

{

	this.getSubCategories = getSubCategories;
	this.fillItemList = fillItemList;
	//this.getLeafCatName=getLeafCatName;
	//var items="";

	function fillItemList()
	{
		var mainCat = $("#catId").val();
		var subcat = $("#fkSubCatId").val();


	}
	function getSubCategories()
	{
		var input = document.getElementById('catId'),
	    list = document.getElementById('catId_drop'),
	    i,catId;
	for (i = 0; i < list.options.length; ++i) {
	    if (list.options[i].value === input.value) {
	    	catId = list.options[i].getAttribute('data-value');
	    }

		var mainCat = catId;
	}
		$("#subCat").empty();
		$("#fkSubCatId").append($("<option></option>").attr("value","").text("Select subcategory"));
		var params= {};
		params["methodName"] = "getSubCategoriesByRootcategory";
		params["catId"]= mainCat;
		$.post('/SMT/jsp/utility/controller.jsp',params,function(data)
				{
			var jsonData = $.parseJSON(data);
			var catmap = jsonData.list;
			$.each(jsonData,function(i,v)
					{
				$("#fkSubCatId").append($("<option></option>").attr("value",i).text(v.subCatName)); 

					});
				}).error(function(jqXHR, textStatus, errorThrown){
					if(textStatus==="timeout") {

					}
				});

	}

	

}




function disable(){
	
	if(document.itemdel.itemName.value !== "")
	{
		document.itemdel.itemName.disabled = true;
		return false;
	}	
 
}


function reset()
{
	document.itemdel.reset();
}


var pohelper = new POHelper();



function getAllMAinItem(){
	
	
	var params= {};
	
	params["methodName"] = "getAllMAinItem";

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
		        ],
		      
		        
		    } );
		} );
		
	var mydata = catmap;
	$('#itemName').dataTable().fnAddData(mydata);
	
		}

	);
	
}	

//Update product Details

function updateProduct(){
	
var itemName = $('#itemName').val();
	
	if(itemName != null && itemName != "" && itemName != " ")
		{
		updateProduct1();
		}
	else{
			alert("Please Enter Product name");
		}

}



function updateProduct1(){

	
	document.UpdateProd.btn.disabled = true;
	
	var input = document.getElementById('product'),
    list = document.getElementById('sup_drop'),
    	i,fkRootSupId;
	 		for (i = 0; i < list.options.length; ++i) {
			     if (list.options[i].value === input.value) {
			    	 fkRootSupId = list.options[i].getAttribute('data-value');
			     }
	 		}
	
	var itemName = $('#itemName').val();
	
	
	var hsnsacno = $('#hsnsacno').val();
	var modelName = $('#modelName').val();				
	
	var params = {};
	
	params["productId"] = fkRootSupId;
	
	params["itemName"] = itemName;
	params["hsnsacno"] = hsnsacno;
	
	params["modelName"] = modelName;
	
	params["methodName"] = "updateProductDetails";

	$.post('/SMT/jsp/utility/controller.jsp',params,function(data){
			alert(data);
				if(document.UpdateProd)
				{
					document.UpdateProd.reset();
				}	
				document.UpdateProd.btn.disabled =false;
			}
 	    	).error(function(jqXHR, textStatus, errorThrown){
 	    		
 	    		/*alert("Data Added Successfully..");
 	    		location.reload();
 				document.ccd.btn.disabled =false;*/
 	    		
 	    		if(textStatus==="timeout") {
 	    			$(loaderObj).hide();
 	    			$(loaderObj).find('#errorDiv').show();
 	    		}
 	    	});


}

/********* get Product Details ************/
function getProductDetails(){
	var params= {};
	
	var input = document.getElementById('product'),
     list = document.getElementById('sup_drop'),
     	i,fkRootSupId;
	 		for (i = 0; i < list.options.length; ++i) {
			     if (list.options[i].value === input.value) {
			    	 fkRootSupId = list.options[i].getAttribute('data-value');
			     }
	 		}
	
	$("#itemName").append($("<input/>").attr("value","").text());
	
	$("#modelName").append($("<input/>").attr("value","").text());
	$("#hsnsacno").append($("<input/>").attr("value","").text());

	params["productId"]= fkRootSupId;
	params["methodName"] = "getProductDetailsToEdit";
	
	$.post('/SMT/jsp/utility/controller.jsp',params,function(data){
		
		var jsonData = $.parseJSON(data);
		var catmap = jsonData.list;
		$.each(jsonData,function(i,v)
				{
				  document.getElementById("itemName").value = v.ProName;
			      document.getElementById("modelName").value = v.ModelName;
			      document.getElementById("hsnsacno").value = v.hsnsacno;
		      
				});
			}).error(function(jqXHR, textStatus, errorThrown){
				if(textStatus==="timeout") {

				}
			});
 	    	
}

////////////////////////////////////////////////////////////////

function serviceprod(){
	
	var itemName = $('#itemName').val();
	
	if(document.prod.itemName.value != "")
	{
		serviceprod1()
		
		
	}
	
	else
		{
		
		alert(" Please Enter service  Name")
		}
	
}




function serviceprod1(){
	
	//document.podetail.btn.disabled = true;
	document.getElementById("btn").disabled = true;	
	
	var itemName = $('#itemName').val();
	var hsnsacNo = $('#hsnsacNo').val();
	
	var params = {};
	
	params['itemName'] = itemName;
	params['hsnsacNo'] = hsnsacNo;
	
	
	params["methodName"] = "regitemName";
	
	$.post('/SMT/jsp/utility/controller.jsp',params,function(data)
 	    	{
 				alert(data);
 				location.reload();
				document.podetail.btn.disabled = false;
 				
 			}
 	    	).error(function(jqXHR, textStatus, errorThrown){
 	    		if(textStatus==="timeout") {
 	    			$(loaderObj).hide();
 	    			$(loaderObj).find('#errorDiv').show();
 	    		}
 	    	});
}













