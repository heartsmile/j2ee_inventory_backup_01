/**
 * 
 */
$(document).ready(function() {
   
    // provider Name selector
    $("#select_providerN").change(function(){
    	// get value of providerID from providerName
    	var providerID = $(this).find(':selected').data('id').trim();
    	// set providerID for selector.
    	$("select#select_providerID").val(providerID);
    });
    
    // provider ID selector
    $("#select_providerID").change(function(){
    	// get value of providerID from providerName
    	var providerName = $(this).find(':selected').data('value').trim();
    	// set providerID for selector.
    	$("select#select_providerN").val(providerName);
    });
    
    var date = new Date();
    var month = date.getMonth()+1;
    var day = date.getDate();
    var output = date.getFullYear() + '-' + ((''+month).length<2 ? '0' : '') + month + '-' + ((''+day).length<2 ? '0' : '') + day; 
    
    $('#ngayNhap').val(output);
});
