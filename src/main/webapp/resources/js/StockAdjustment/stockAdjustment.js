var obj;
var stockID = null;
$(document).ready(function() {
	
	stockID = $( "#select_stockName" ).val();
	
    var date = new Date();
    var month = date.getMonth()+1;
    var day = date.getDate();
    var output = date.getFullYear() + '-' + ((''+month).length<2 ? '0' : '') + month + '-' + ((''+day).length<2 ? '0' : '') + day; 
    
    $('#date').val(output);
	// call function calculation grandTotal
	setGrandTotal();
	setDiffQ();
	// get list product
	var success = function(response) {
		if (response != "") {
			obj = null;
			obj = jQuery.parseJSON(response);
			//load json to table 
			$('#tableInven tbody').html(getTableHTML());

			$('.inputQuantity').change(function(){
				var row = $(this).parent().parent();
				var col = row.find('td');
				
				var stockQ_index = 2;
				var realQ_index = 3;
				var diff_index = 4;
				var price_index = 5;
				var subTotal_index = 6;
				
				// get value
				var stockQuantity = parseInt(col.eq(stockQ_index).html());
				var realQuantity = parseInt($(this).val());
				
				var diff = col.eq(diff_index);
				var sub = col.eq(subTotal_index);
				
				var row_index = row.index()/2;
				
				var diffQuantity = (realQuantity - stockQuantity);
				var subToTal = diffQuantity * (parseInt(col.eq(price_index).html()));
				
				// set value for html
				diff.html(diffQuantity);
				sub.html(subToTal);
				
				// set value for return object
				obj[row_index]['realQuantity'] = realQuantity;
				obj[row_index]['differentQuantity'] = diffQuantity;
				obj[row_index]['subTotal'] = subToTal;
			
				// call function calculation grandTotal
				setGrandTotal();
				setDiffQ();
			});
		} else {
			
		}
	};
	
	// dinh nghia ham error
	var error = function() {
		var dialogError = new BootstrapDialog({
            type: BootstrapDialog.TYPE_DANGER,
            title: 'Danger Message',
            message: 'Have problem! Please try later!',
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
	};
	
	var ajaxObject = {
		url : 'getListStockAdjustment',
		type : 'POST',
		data:{
			"stockID": stockID
		},
		success : success,
		error : error
	};
	
	// calling
	$.ajax(ajaxObject);

	$("#select_stockName").change(function(){
    	// get value of ProductID
    	stockID = $( "#select_stockName" ).val();
    	$('#grandTotal').html(0);
    	$('#diffQuantity').html(0);
	});
	
	$("#searchInven").on('click',function(){
		// calling
		$('table#tableInven > tbody > tr').not(':first').not(':last').remove();
		
		$.ajax({
    		url: "getListStockAdjustment",
    		data: { 
    	        'stockID' : stockID
    	    },
    	    dataType: 'json',
    	    type: 'POST',
    	    success: function(response) {
    	    	if (response != "") {
    	    		obj = null;
    				obj = response;
    				//load json to table 
    				$('#tableInven tbody').html(getTableHTML());

    				$('.inputQuantity').change(function(){
    					var row = $(this).parent().parent();
    					var col = row.find('td');
    					
    					var stockQ_index = 2;
    					var realQ_index = 3;
    					var diff_index = 4;
    					var price_index = 5;
    					var subTotal_index = 6;
    					
    					// get value
    					var stockQuantity = parseInt(col.eq(stockQ_index).html());
    					var realQuantity = parseInt($(this).val());
    					
    					var diff = col.eq(diff_index);
    					var sub = col.eq(subTotal_index);
    					
    					var row_index = row.index()/2;
    					
    					var diffQuantity = (realQuantity - stockQuantity);
    					var subToTal = diffQuantity * (parseInt(col.eq(price_index).html()));
    					
    					// set value for html
    					diff.html(diffQuantity);
    					sub.html(subToTal);
    					
    					// set value for return object
    					obj[row_index]['realQuantity'] = realQuantity;
    					obj[row_index]['differentQuantity'] = diffQuantity;
    					obj[row_index]['subTotal'] = subToTal;
    				
    					// call function calculation grandTotal
    					setGrandTotal();
    					setDiffQ();
    				});
    			}
    	    },
    	    error: function(xhr, status){
				var dialogError = new BootstrapDialog({
	                type: BootstrapDialog.TYPE_DANGER,
	                title: 'Danger Message',
	                message: 'Have problem! Please try later!',
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
    	});
	});
	
	$("#saveAdjust").on('click',function(){
		
		var staffID = "1";
		var date = $('#date').val();
		var totalDiffAmount = $('#grandTotal').html();
		var totalDiffQuantity = $('#diffQuantity').html();
		
		var adjustment = {
				"date" : date,
				"staffID" : staffID,
				"stockID" : stockID,
				"totalDiffAmount" : totalDiffAmount,
				"totalDiffQuantity" : totalDiffQuantity
		};
		
		var data = {};
		data[0] = JSON.stringify(adjustment);
		data[1] = JSON.stringify(obj);
		$.ajax({
        	type: 'POST',
            url: './saveStockAdjustment',
            dataType: 'json',
            data: data,
            success: function(response) {
            	// Check if response is success.
				if(response.ID == 1){
					var dialog = new BootstrapDialog({
		                type: BootstrapDialog.TYPE_SUCCESS,
		                title: 'Successful Message',
		                message: 'SAVE ADJUSTMENT THIS INVENTORY SUCCESSFUL!',
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
		                message: 'Have problem! Please try later!',
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

function setGrandTotal() {
	var grandTotal = 0;
	$('#tableInven .subTotal').each(function() {
		grandTotal += parseInt( $(this).html() );
	});
	
	$('#grandTotal').html(grandTotal);
};

function setDiffQ() {

	var diffQ = 0;
	$('#tableInven .diffQ').each(function() {
		diffQ += parseInt( $(this).html() );
	});
	$('#diffQuantity').html(diffQ);
};

function getTableHTML() {
    var html = "";
    for (i in obj) {
        html += "<tr>";

        for (var j = 0; j < Object.keys(obj[0]).length; j++) {
            switch (j) {
                //Column 0
                case 0:
                    html += "<td>" + obj[i]['productID'] + "</td>";
                    break;
                // Column 1
                case 1:
                    html += "<td>" + obj[i]['productName'] + "</td>";
                    break;
                // Column 2
                case 2:
                    html += "<td>" + obj[i]['stockQuantity'] + "</td>";
                    break;
                // Column 3
                case 3:
                    html += "<td>" + "<input type='number' min='1' value='" + obj[i]['realQuantity'] + "'  class='form-control inputQuantity' />" + "</td>";
                    break;
                // Column 4
                case 4:
                    html += "<td class='diffQ'>" + obj[i]['differentQuantity'] + "</td>";
                    break;
                // Column 5
                case 5:
                    html += "<td>" + obj[i]['price'] + "</td>";
                    break;
                // Column 6
                case 6:
                    html += "<td class='subTotal'>" + obj[i]['subTotal'] + "</td>";
                    break;

                default:
                    break;
            }

        }
        html += "<tr>";

    }
    return html;
};