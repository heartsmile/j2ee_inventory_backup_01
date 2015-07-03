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
		window.location = "listProvider";

	});

	$("#btnSave").click(function() {

		// validate input data
		if ($("#inputProviderName").val() == "") {
			alert("Please insert value for Provider Name field!");
			return;
		}
		if ($("#inputAddress").val() == "") {
			alert("Please insert value for Address field!");
			return;
		}
		if ($("#inputTel").val() == "") {
			alert("Please insert value for Telephone field!");
			return;
		}

		var provider = {
			"providerID" : $("#providerIDHidden").val(),
			"providerName" : $("#inputProviderName").val(),
			"tel" : $("#inputTel").val(),
			"email" : $("#inputEmail").val(),
			"address" : $("#inputAddress").val(),
			"website" : $("#inputWebsite").val(),
			"description" : $("#inputDescription").val(),
			"isEdit" : $("#isEdit").val()
		};
		var data = {};
		data[0] = JSON.stringify(provider);

		$.ajax({
			type : "POST",
			url : "saveProvider",
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
					
					//navigate to provider list
					window.location = "listProvider";
				} else if(data.result == "2"){
					alert("Updated successful!");
					
					// disable all input
					$('#btnEdit').css('display', 'inline-block');
					$('#btnSave').css('display', 'none');
					$('.input-info').prop("disabled", true);
					
					//navigate to provider list
					window.location = "listProvider";
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