$(document).ready(function() {
	
    var date = new Date();
    var month = date.getMonth()+1;
    var day = date.getDate();
    var output = date.getFullYear() + '-' + ((''+month).length<2 ? '0' : '') + month + '-' + ((''+day).length<2 ? '0' : '') + day;
    $('#buildDate').val(output);
	
	// variable datatable
	var oTable = $("#tableComponent").dataTable({
		"info":     false,
        "bLengthChange": false
	});
	var objJson = null;
	var tableData = null;
	var productID = null;
	// Product select change
    $("#select_productN").change(function(){
    	// get value of ProductID
    	productID = $(this).find(':selected').data('value').trim();
    	
    	// reset quantity value
    	$("#quantity").val('1');
    	
    	// calling Ajax
    	var success = function(response) {
    		if (response != "") {
				var i = 0;
				
    			objJson = jQuery.parseJSON(response);
    			tableData = jQuery.parseJSON(response);
    			$('#tableComponent').dataTable().fnDestroy();
    			$('#tableComponent').dataTable({
    		        "info":     false,
    		        "bLengthChange": false, //used to hide the property 
        			"aaData" : objJson,
        			"aoColumns" : [ {
        				"mData" : "componentID.productID"
        			}, {
        				"mData" : "componentID.productName"
        			}, {
        				"mData" : "quantity"
        			}, {
        				"mData" : "unitPrice"
        			}, {
        				"mData" : "total"
        			} ]
    			
        		});
    		} else {
    			oTable.fnClearTable();
        		$('#tableComponent').dataTable({
        			"info":     false,
    		        "bLengthChange": false
        		}).fnDestroy();
    		}
    	};

    	// define function error
    	var error = function() {
    		alert("Can't get list Product Component!");
    	};
    	var ajaxObject = {
    		url : 'getProductComponent',
    		data: { 
    	        'productID' : productID
    	    },
    		type : 'POST',
    		success : success,
    		error : error
    	};
    	// Thuc thi ajax
    	$.ajax(ajaxObject);
    	
    	// get Product information
    	$.ajax({
    		url: "getProductInfor",
    		data: { 
    	        'productID' : productID
    	    },
    	    dataType: 'json',
    	    type: 'POST',
    	    success: function(response) {
    	    	//var data = jQuery.parseJSON(response);
    			$('#unitPrice').val(response['salePrice']);
    			var price = $('#unitPrice').val();
    			var quantity = $('#quantity').val();
    			if( quantity!='' && price !='' ) $('#subTotal').val( (parseFloat(price)*parseFloat(quantity)) );
    		}
    	});
    });
    
    $("#quantity").bind('keyup mouseup', function () {
		var price = $('#unitPrice').val();
		var quantity = $('#quantity').val();
		if( quantity!='' && price !='' ) $('#subTotal').val( (parseFloat(price)*parseFloat(quantity)) );
		
		var rowCount = oTable.fnSettings().fnRecordsTotal();
		for(var i = 0; i < rowCount; i++){
			
			var quantityCompo = tableData[i].quantity;
			var priceCompo = tableData[i].unitPrice;
			
			oTable.fnUpdate((parseInt(quantityCompo)*parseInt(quantity)), parseInt(i), 2);

			var afterQuatity = oTable.fnGetData( i, 2 );
			oTable.fnUpdate((parseInt(afterQuatity)*parseFloat(priceCompo)), parseInt(i), 4);
		}
    });
    
    // save product build
    $('#saveProBuild').click(function() {
    	
    	// get Stock Build data
    	var staffID = "1";
    	var totalQuantity = $('#quantity').val(); 
    	var totalAmount = $('#subTotal').val();
    	var note = $('#note').val();
    	var buildDate = output;
    	var stockID = $('#select_stockID').val();
    	
		var stockBuild = {
			"productID" : productID,
			"staffID" : staffID,
			"buildDate" : buildDate,
			"note" : note,
			"totalQuantity" : totalQuantity,
			"totalAmount" : totalAmount,
			"stockID" : stockID
		};
    	
		var TableData = new Array();
    	// get row number
    	var rowCount = oTable.fnSettings().fnRecordsTotal();
		for(var i = 0; i < rowCount; i++){
			var compoID = oTable.fnGetData( i, 0 );
			var quantityCompo = oTable.fnGetData( i, 2 );
			var priceCompo = oTable.fnGetData( i, 3 );
			var subTotal = oTable.fnGetData( i, 4 );
			TableData[i]={
		        "compoID" : compoID
		        , "quantityCompo" : quantityCompo
		        , "priceCompo" : priceCompo
		        , "subTotal" : subTotal
			}
		}
		
		// call ajax to save data
		var data = {};
		data[0] = JSON.stringify(stockBuild);
		data[1] = JSON.stringify(TableData);
		$.ajax({
        	type: 'POST',
            url: './saveStockBuild',
            dataType: 'json',
            data: data,
            success: function(response) {
            	// Check if response is success.
				if(response.ID == 1){
					var dialog = new BootstrapDialog({
		                type: BootstrapDialog.TYPE_SUCCESS,
		                title: 'Successful Message',
		                message: 'Assembly This Product Successful!',
		                buttons: [{
		                    id: 'btn-ok',
		                    label: 'OK'
		                }]
		            });     
					dialog.realize();
					var btnOk = dialog.getButton('btn-ok');
					btnOk.click(function(event){
						location.reload(true);
			        });
					dialog.open();
				}
				else {
					var dialogError = new BootstrapDialog({
		                type: BootstrapDialog.TYPE_DANGER,
		                title: 'Danger Message',
		                message: response.MGS,
		                buttons: [{
		                    id: 'btn-cancel',
		                    label: 'CANCEL'
		                }]
		            });   
					dialogError.realize();
					var btnCancel = dialogError.getButton('btn-cancel');
					btnCancel.click(function(event){
						location.reload(true);
			        });
					dialogError.open();
				}
            },
            error : function(xhr, status){
                console.log(status);
            }
            
        });
	});
});
//It restrict the non-numbers
var specialKeys = new Array();
specialKeys.push(8,46); //Backspace
function IsNumeric(e) {
    var keyCode = e.which ? e.which : e.keyCode;
    var ret = ((keyCode >= 48 && keyCode <= 57) || specialKeys.indexOf(keyCode) != -1);
    return ret;
}