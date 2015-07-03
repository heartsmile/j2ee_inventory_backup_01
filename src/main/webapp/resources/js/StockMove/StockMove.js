/**
 * 
 */
$(function() {

	bodytable$ = $("#tableStockTransfer").dataTable({
		'iDisplayLength' : 7,
	});
	// hide some unused
	$('.dataTables_length').hide();
	$('.dataTables_info').hide();

	$("#checkAll").click(function() {
		if ($("#checkAll").is(":checked")) {
			$('.checkTransferBill').prop('checked', true);
		} else {
			$('.checkTransferBill').prop('checked', false);
		}
	});
	
	$("#btnExportAllBill").click(function(e) {
	    window.open('data:application/vnd.ms-excel,' + $('#dataStockMoveTable').html());
	    e.preventDefault();
	});

	var table = $('#tableStockTransfer').DataTable();

	$('#tableStockTransfer tbody tr').click(function() {
		/*
		 * if ($(this).hasClass("selected")) { $(this).removeClass('selected'); }
		 * else { table.$("tr.selected").removeClass("selected");
		 * $(this).addClass("selected"); }
		 */
		var nTds = $('td', this);
		var transferID = $(nTds[1]).text();

		$.ajax({
			type : "POST",
			url : "navigateToEditBill",
			data : {
				"stockTransferID" : transferID
			},
			dataType : "json",
			success : function(data) {
				if (data.result == "1") {
					window.location="stockMoveEdit";
				} else {
					alert("Failed :( !");
				}

			},
			error : function(status) {
				console.log(status);
			}
		});
	});

});