/**
 * 
 */
var oTable;
$(function() {

	// oTable = $('#tableListStock').dataTable();

	// on load
	oTable = $("#tableListStock").dataTable({
		'iDisplayLength' : 10,
	});

	// event
	$("#createStock").click(function() {
		window.location = "createStock";
	});

	$("#editStock").click(function() {
		var anSelected = fnGetSelected(oTable);
		if (anSelected.length > 0) {
			var stockID = oTable.fnGetData(anSelected[0], 0);
			window.location = "editStock?stockID=" + stockID;
		}
	});

	$("#deleteStock").click(function() {

		if (!confirm("Are you sure to delete this stock?")) {
			return;
		}

		var anSelected = fnGetSelected(oTable);
		if (anSelected.length > 0) {
			var stockID = oTable.fnGetData(anSelected[0], 0);

			var stock = {
				"stockID" : stockID
			};

			var data = {};
			data[0] = JSON.stringify(stock);

			$.ajax({
				type : "POST",
				url : "deleteStock",
				data : data,
				dataType : "json",
				success : function(data) {
					if (data.result == "1") {
						alert("Delete successful!");
						window.location = "listStock";
					} else {
						alert("Can't delete product because foreign key!");
					}
				},
				error : function(status) {
					console.log(status);
				}
			});
		}
	});

	// select row function
	$('body').on("click", '#tableListStock tbody tr', function() {
		if ($(this).hasClass('selected')) {
			$(this).removeClass('selected');
		} else {
			$(this).siblings('.selected').removeClass('selected');
			$(this).addClass('selected');
		}
	});
});

// It restrict the non-numbers
var specialKeys = new Array();
specialKeys.push(8, 46); // Backspace
function IsNumeric(e) {
	var keyCode = e.which ? e.which : e.keyCode;
	var ret = ((keyCode >= 48 && keyCode <= 57) || specialKeys.indexOf(keyCode) != -1);
	return ret;
}
/* Get the rows which are currently selected */
function fnGetSelected(oTableLocal) {
	return oTableLocal.$('tr.selected');
}