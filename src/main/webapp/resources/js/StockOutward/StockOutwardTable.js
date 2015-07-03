/**
 * @author: thaint
 */

var currentOutwardID;

$(document).ready(function() {
	
	//get current stock outward id
	currentOutwardID = $("#billNo").val();
	
	//disable btn export
	$("#exportOutward").prop("disabled", true);
	
	$("#exportOutward").click(function(){
		window.location = "exportOutward?currentOutwardID=" + currentOutwardID;
	});
	
	var i = $('#tbData tr').length;
	calculateTotal();
	//deletes the selected table rows
	$(".delete").on('click', function() {
		i --;
		$('.case:checkbox:checked').parents("tr").remove();
		$('#check_all').prop("checked", false); 
		calculateTotal();
	});

	////to check all checkboxes
	$(document).on('change','#check_all',function(){
		$('input[class=case]:checkbox').prop("checked", $(this).is(':checked'));
	});
	
	////price change
	$(document).on('change keyup blur','.changesNo',function(){
		id_arr = $(this).attr('id');
		id = id_arr.split("_");
		quantity = $('#quantity_'+elementId).val();
		price = $('#price_'+elementId).val();
		if( quantity!='' && price !='' ) $('#total_'+ elementId).val( (parseFloat(price)*parseFloat(quantity)).toFixed(2) );	
		calculateTotal();
	});
	
	//adds extra table rows
	$(".addmore").on('click',function(){
		
		count = $('#tbData tr').length;
		var productID = 'productID_' + (count - 1); 
		var productIDValue = $('#' + productID).val();
		
		if (count <= 1 || productIDValue) {
			//
			var html =loadData(count, i);
			$('#tbData').append(html);
			i++;
		}

	});
	
    var array;
    function getData() {

    	//var ID = request;
        $.ajax({
        	type: 'POST',
            url: './getProduct',
            dataType: 'json',
            data: {},
            success: function(response) {
                array = $.map(response, function(item) {
                    var code = item.split("|");
                    return {
                        label: code[autoTypeNo],
                        value: code[autoTypeNo],
                        data: item
                    }
                });
            },
            error : function(xhr, status){
                console.log(status);
            }
            
        });
        return false;
    }
    
    //auto-complete script
    $(document).on('focus','.searchName',function(){
    	type = $(this).data('type');

    	if(type =='productID' )autoTypeNo=0;
    	if(type =='productName' )autoTypeNo=1; 	

        $(this).autocomplete({
            minLength: 0,
            source: function(request, response) {
            	
            	// check request
            	if (request != null || request != "") {
                    getData();
                    //call the filter here
                    if (array != null) {
                        response($.ui.autocomplete.filter(array, request.term));
    				}
				}
            	
            },
            focus: function() {
                // prevent value inserted on focus
                return false;
            },
    		select: function( event, ui ) {
    			var names = ui.item.data.split("|");						
    			id_arr = $(this).attr('id');
    	  		id = id_arr.split("_");
    			elementId = id[id.length - 1];
    			$('#productID_'+elementId).val(names[0]);
    			$('#productName_'+elementId).val(names[1]);
    			$('#unitName_'+elementId).val(names[2]);
    			$('#price_'+elementId).val(names[3]);
    			$('#total_'+elementId).val( 1*names[3] );
    			calculateTotal();
    		}	
        });

        $.ui.autocomplete.filter = function(array, term) {
            var matcher = new RegExp("^" + $.ui.autocomplete.escapeRegex(term), "i");
            return $.grep(array, function(value) {
                return matcher.test(value.label || value.value || value);
            });
        };
    });
    
	//save stock-inward
	$("#saveData").on('click',function(){
		
		var customerID = $('#select_customerID').val();
		var staffID = "1";
		var date = $('#ngayNhap').val();
		var reason = $('#reason').val();
		var totalAmount = $('#totalNumber').val();
		var totalMoney = $('#totalMoney').val();
		var note = $('#note').val();
		
		var stockInward = {
			"customerID" : customerID,
			"staffID" : staffID,
			"date" : date,
			"reason" : reason,
			"totalNumber" : totalAmount,
			"totalMoney" : totalMoney,
			"note" : note
		};
		
		var TableData = new Array();
		var idx = 1;
		$('#tbData tr').each(function(row, tr){
			var productID = "#productID_"+ idx;
			var stockID = "#stockID_" + idx;
			var quantity = "#quantity_" + idx;
			TableData[row]={
		        "productID" : $(productID).val()
		        , "stockID" : $(stockID).val()
		        , "quantity" : $(quantity).val()
		    }
			idx++;
		});
		
		// call ajax to save data
		var data = {};
		data[0] = JSON.stringify(stockInward);
		data[1] = JSON.stringify(TableData);
		$.ajax({
        	type: 'POST',
            url: './saveStockOutward',
            dataType: 'json',
            data: data,
            success: function(response) {
            	// Check if response is success.
				if(response.ID == 1){
					var dialog = new BootstrapDialog({
		                type: BootstrapDialog.TYPE_SUCCESS,
		                title: 'Successful Message',
		                message: 'Save bill successful!',
		                buttons: [{
		                    id: 'btn-ok',
		                    label: 'OK'
		                }]
		            });     
					dialog.realize();
					var btnOk = dialog.getButton('btn-ok');
					btnOk.click(function(event){
						//location.reload(true);
						dialog.close();
						//enable btn export
						$( "#exportOutward" ).prop( "disabled", false );
			        });
					dialog.open();
					
					//enable btn export
					$( "#exportOutward" ).prop( "disabled", false );
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
	
	$('#cancel').on('click',function(){
		BootstrapDialog.alert('I want money!');
	});
	
	$('#createNew').on('click',function(){
		location.reload(true);
	});
	
});
//total price calculation 
function calculateTotal(){
	totalMoney = 0 ; totalNumber = 0; 
	$('.totalLinePrice').each(function(){
		if($(this).val() != '' )totalMoney += parseFloat( $(this).val() );
	});
	$('#totalMoney').val( totalMoney.toFixed(3) );
	
	$('.changesNo').each(function(){
		if($(this).val() != '' )totalNumber += parseFloat( $(this).val() );
	});
	$('#totalNumber').val( totalNumber );
}
//It restrict the non-numbers
var specialKeys = new Array();
specialKeys.push(8,46); //Backspace
function IsNumeric(e) {
    var keyCode = e.which ? e.which : e.keyCode;
    var ret = ((keyCode >= 48 && keyCode <= 57) || specialKeys.indexOf(keyCode) != -1);
    return ret;
}

