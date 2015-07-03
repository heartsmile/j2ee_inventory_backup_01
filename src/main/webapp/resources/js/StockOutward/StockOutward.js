/**
 * 
 */
$(document).ready(function() {
   
    // customer Name selector
    $("#select_customerN").change(function(){
    	// get value of customerID from customerName
    	var customerID = $(this).find(':selected').data('id').trim();
    	// set customerID for selector.
    	$("select#select_customerID").val(customerID);
    });
    
    // customer ID selector
    $("#select_customerID").change(function(){
    	// get value of customerID from customerName
    	var customerName = $(this).find(':selected').data('value').trim();
    	// set customerID for selector.
    	$("select#select_customerN").val(customerName);
    });
    
    var date = new Date();
    var month = date.getMonth()+1;
    var day = date.getDate();
    var output = date.getFullYear() + '-' + ((''+month).length<2 ? '0' : '') + month + '-' + ((''+day).length<2 ? '0' : '') + day; 
    
    $('#ngayNhap').val(output);
});
