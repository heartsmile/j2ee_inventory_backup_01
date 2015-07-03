/**
 * 
 */
//It restrict the non-numbers
var specialKeys = new Array();
specialKeys.push(8,46); //Backspace
function IsNumeric(e) {
    var keyCode = e.which ? e.which : e.keyCode;
    var ret = ((keyCode >= 48 && keyCode <= 57) || specialKeys.indexOf(keyCode) != -1);
    return ret;
}

$(function() {

	// on load
	$('#btnEdit').css('display', 'none');

	// event on page
	$("#btnEdit").click(function() {

		$('#btnEdit').css('display', 'none');
		$('#btnSave').css('display', 'inline-block');
		$('.input-info').prop("disabled", false);
	});

	$("#btnCancel").click(function() {
		window.location = "listStock";

	});

	$("#btnSave").click(function() {

		// validate input data
		if ($("#inputStockName").val() == "") {
			alert("Please insert value for Stock Name field!");
			return;
		}
		if ($("#inputAddress").val() == "") {
			alert("Please insert value for Address field!");
			return;
		}
		if ($("#inputSize").val() <= 0) {
			alert("Please insert value more than 0 for Size field!");
			return;
		}

		var stock = {
			"stockID" : $("#stockIDHidden").val(),
			"stockName" : $("#inputStockName").val(),
			"managerID" : $("#listStaff option:selected").attr('data-id').trim(),
			"address" : $("#inputAddress").val(),
			"size" : $("#inputSize").val(),
			"description" : $("#inputDescription").val(),
			"isEdit" : $("#isEdit").val()
		};
		var data = {};
		data[0] = JSON.stringify(stock);

		$.ajax({
			type : "POST",
			url : "saveStock",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.result == "1") {
					
					//set is further process is edit
					$("#isEdit").val(1);
					alert("Save successful!");
					
					// disable all input
					$('#btnEdit').css('display', 'inline-block');
					$('#btnSave').css('display', 'none');
					$('.input-info').prop("disabled", true);
					
					//navigate to stock list
					window.location = "listStock";
				} else if(data.result == "2"){
					alert("Updated successful!");
					
					// disable all input
					$('#btnEdit').css('display', 'inline-block');
					$('#btnSave').css('display', 'none');
					$('.input-info').prop("disabled", true);
					
					//navigate to stock list
					window.location = "listStock";
				} else {
					alert("Save Fail!");
				}
			},
			error : function(status) {
				console.log(status);
			}
		});
	});

});