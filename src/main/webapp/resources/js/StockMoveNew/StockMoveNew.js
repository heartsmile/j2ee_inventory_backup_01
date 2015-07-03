/**
 * 
 */
$(function() {
	$('#sandbox-container input').datepicker({
		format : "yyyy-mm-dd"
	});

	var d = new Date();
	var month = d.getMonth() + 1;
	var day = d.getDate();
	var output = d.getFullYear() + "-" + (month < 10 ? '0' : '') + month + '-' + (day < 10 ? '0' : '') + day;
	if(!$("#sandbox-container input").val()){
		$("#sandbox-container input").val(output);
	}
	
	$("#fromStock").change(function(){

    	var optionID = $(this).find(':selected').data('id').trim();
    	if(optionID == "createStock"){
    		window.location = "createStock";
    	}
    	
    });
	
	$("#exportTransfer").css('display', 'none');
	//check current status of bill --> to show correspond button 
	if($("#curStatus").val() == 1){//new
		
		$('#btnEdit').css('display', 'none');
		$('#btnCreateNew').css('display', 'none');
		$('#btnCheckAvailable').css('display', 'none');
	} else if($("#curStatus").val() == 2){//waiting
		
		$('.input-info').prop("disabled", true);
		$('#btnSave').css('display', 'none');
		$('#btnCreateNew').css('display', 'none');
		$('#btnProcessLater').css('display', 'none');
		$('#btnCheckAvailable').css('display', 'inline-block');
	} else if($("#curStatus").val() == 3){//available
		
		$('.input-info').prop("disabled", true);
		$('#btnSave').css('display', 'none');
		$('#btnCreateNew').css('display', 'none');
		$('#btnProcessLater').css('display', 'none');
		$('#btnCheckAvailable').css('display', 'none');
	} else if($("#curStatus").val() == 4){//done
		
		$('.input-info').prop("disabled", true);
		$('#btnSave').css('display', 'none');
		$('#btnProcessAll').css('display', 'none');
		$('#btnProcessLater').css('display', 'none');
		$('#btnCreateNew').css('display', 'inline-block');
		$('#btnCheckAvailable').css('display', 'none');
		$('#btnCancelMove').css('display', 'none');
		$("#exportTransfer").css('display', 'inline-block');
	}
	
	$("#btnEdit").click(function() {
		
		$('#btnEdit').css('display', 'none');
		$('#btnSave').css('display', 'inline-block');
		if($("#curStatus").val() == 4){
			$('#description').prop( "disabled", false );
			$('#priority').prop( "disabled", false );
		} else {
			$('.input-info').prop("disabled", false);
		}
	});
	
	$("#exportTransfer").click(function() {
		var link = "exportTransfer?currentTransferID=" + $("#curTransferID").val();
		window.location = link;
	});
		
	$("#btnCancelMove").click(function() {
		if(confirm("Are you sure to cancel this moving?")){
			var stockMove = {
					"product" : $("#product").val(),
					"expectedDay" : $("#expectedDay").val(),
					"quantity" : $("#quantity").val(),
					"priority" : $("#priority").val(),
					"fromStock" : $("#fromStock").val(),
					"toStock" : $("#toStock").val(),
					"description" : $("#description").val(),
					"curTransferID" : $("#curTransferID").val()
				};

			var data = {};
			data[0] = JSON.stringify(stockMove);
			
			$.ajax({
				type : "POST",
				url : "cancelMove",
				data : data,
				dataType : "json",
				success : function(data) {
					if(data.result == "1"){
						window.location = "StockMove";
					} else {
						alert("Process failed! Can't cancel this moving.");
					}
				},
				error : function(status) {
					console.log(status);
				}
			});
		} else {
			//do nothing
		}
		
	});
	
	$("#btnProcessLater").click(function() {

		if ($("#quantity").val() < 1) {
			alert("Quantity must more than 0");
		} else {

			var stockMove = {
				"product" : $("#product").val(),
				"expectedDay" : $("#expectedDay").val(),
				"quantity" : $("#quantity").val(),
				"priority" : $("#priority").val(),
				"fromStock" : $("#fromStock").val(),
				"toStock" : $("#toStock").val(),
				"description" : $("#description").val(),
				"curTransferID" : $("#curTransferID").val(),
				"isEdit" : $("#isEdit").val()
			};

			var data = {};
			data[0] = JSON.stringify(stockMove);

			if ($("#fromStock").val() != $("#toStock").val()) {
				
				$.ajax({
					type : "POST",
					url : "processLater",
					data : data,
					dataType : "json",
					success : function(data) {
						if (data.result == "1") {
							
							$("#curTransferID").val(data.curTransferID);
							$("#isEdit").val(1);
							//change status to waiting available
							$("#sttNew").removeClass("active");
							$("#sttWaiting").addClass("active");
							$("#curStatus").val(2);
							//change button
							$("#btnProcessLater").css('display', 'none');
							$('#btnCheckAvailable').css('display', 'inline-block');
							$('.input-info').prop("disabled", true);
							$('#btnSave').css('display', 'none');
							$('#btnEdit').css('display', 'inline-block');
						} else {
							alert("Failed :( !");
						}
					},
					error : function(status) {
						console.log(status);
					}
				});
			} else {
				alert("Invalid Destination Location");
				return false;
			}
		}

	});
	
	$("#btnSave").click(function() {

		if ($("#quantity").val() < 1) {
			alert("Quantity must more than 0");
		} else {

			var stockMove = {
				"product" : $("#product").val(),
				"expectedDay" : $("#expectedDay").val(),
				"quantity" : $("#quantity").val(),
				"priority" : $("#priority").val(),
				"fromStock" : $("#fromStock").val(),
				"toStock" : $("#toStock").val(),
				"description" : $("#description").val(),
				"curTransferID" : $("#curTransferID").val(),
				"isEdit" : $("#isEdit").val(),
				"curStatus" : $("#curStatus").val()
			};

			var data = {};
			data[0] = JSON.stringify(stockMove);

			if ($("#fromStock").val() != $("#toStock").val()) {
				
				$.ajax({
					type : "POST",
					url : "saveNewStockMove",
					data : data,
					dataType : "json",
					success : function(data) {
						if (data.result == "1") {
							$("#curTransferID").val(data.curTransferID);
							$("#isEdit").val(1);
							alert("Create new bill successful!");
							$('.input-info').prop("disabled", true);
							$('#btnSave').css('display', 'none');
							$('#btnEdit').css('display', 'inline-block');
						} else if (data.result == "2") {
							alert("Update successful!");
							$('.input-info').prop("disabled", true);
							$('#btnSave').css('display', 'none');
							$('#btnEdit').css('display', 'inline-block');
						} else {
							alert("Failed :( !");
						}
					},
					error : function(status) {
						console.log(status);
					}
				});
			} else {
				alert("Invalid Destination Location");
				return false;
			}
		}
	});
	
	$("#btnCheckAvailable").click(function() {

		if ($("#quantity").val() < 1) {
			alert("Quantity must more than 0");
		} else {

			var stockMove = {
				"product" : $("#product").val(),
				"quantity" : $("#quantity").val(),
				"fromStock" : $("#fromStock").val(),
				"toStock" : $("#toStock").val(),
				"curTransferID" : $("#curTransferID").val()
			};

			var data = {};
			data[0] = JSON.stringify(stockMove);

			if ($("#fromStock").val() != $("#toStock").val()) {
				
				$.ajax({
					type : "POST",
					url : "checkAvailable",
					data : data,
					dataType : "json",
					success : function(data) {
						if (data.result == "1") {
							
							//yes, this product is available in fromStock
							//update status in database
							$.ajax({
								type : "POST",
								url : "updateToAvailable",
								data : { "curTransferID" : $("#curTransferID").val() },
								dataType : "json",
								success : function(data) {
									if (data.result == "1") {
										//change status to available
										$("#sttWaiting").removeClass("active");
										$("#sttAvailable").addClass("active");
										$("#curStatus").val(3);
										$("#btnCheckAvailable").css('display', 'none');
										$('.input-info').prop("disabled", true);
										$('#btnSave').css('display', 'none');
										$('#btnEdit').css('display', 'inline-block');
									} else {
										alert("Update available failed :( !");
									}
								},
								error : function(status) {
									console.log(status);
								}
							});
							
						} else if(data.result == -1){
							
							//warning lower than min stock
							alert("If move, product in source stock will be lower than min value!");
						} else {
							
							//not available
							alert("Product is not enough in source stock!");
						}
					},
					error : function(status) {
						console.log(status);
					}
				});
			} else {
				alert("Invalid Destination Location");
				return false;
			}
		}
	});
	
	//btn process entirely
	$("#btnProcessAll").click(function() {

		if ($("#quantity").val() < 1) {
			alert("Quantity must more than 0");
		} else {

			var stockMove = {
				"product" : $("#product").val(),
				"expectedDay" : $("#expectedDay").val(),
				"quantity" : $("#quantity").val(),
				"priority" : $("#priority").val(),
				"fromStock" : $("#fromStock").val(),
				"toStock" : $("#toStock").val(),
				"description" : $("#description").val(),
				"curTransferID" : $("#curTransferID").val(),
				"curStatus" : $("#curStatus").val(),
				"isEdit" : $("#isEdit").val()
			};

			var data = {};
			data[0] = JSON.stringify(stockMove);

			if ($("#fromStock").val() != $("#toStock").val()) {
				$.ajax({
					type : "POST",
					url : "processAll",
					data : data,
					dataType : "json",
					success : function(data) {
						if (data.result == "success") {
							//change status to DONE
							$("#sttNew").removeClass("active");
							$("#sttWaiting").removeClass("active");
							$("#sttAvailable").removeClass("active");
							$("#sttDone").addClass("active");
							$("#curStatus").val(4);
							$("#btnCheckAvailable").css('display', 'none');
							$("#btnProcessLater").css('display', 'none');
							$("#btnProcessAll").css('display', 'none');
							$("#btnCancelMove").css('display', 'none');
							$('#btnCreateNew').css('display', 'inline-block');
							$('.input-info').prop("disabled", true);
							$('#btnSave').css('display', 'none');
							$('#btnEdit').css('display', 'inline-block');
							$("#exportTransfer").css('display', 'inline-block');
						} else if (data.result == "lower"){

							//warning lower than min stock
							alert("If move, product in source stock will be lower than min value!");
							
							//change status to DONE
							$("#sttNew").removeClass("active");
							$("#sttWaiting").removeClass("active");
							$("#sttAvailable").removeClass("active");
							$("#sttDone").addClass("active");
							$("#curStatus").val(4);
							$("#btnCheckAvailable").css('display', 'none');
							$("#btnProcessLater").css('display', 'none');
							$("#btnProcessAll").css('display', 'none');
							$("#btnCancelMove").css('display', 'none');
							$('#btnCreateNew').css('display', 'inline-block');
							$('.input-info').prop("disabled", true);
							$('#btnSave').css('display', 'none');
							$('#btnEdit').css('display', 'inline-block');
							$("#exportTransfer").css('display', 'inline-block');
						} else if(data.result == "notAvailable") {
							
							//notAvailable
							$("#sttNew").removeClass("active");
							$("#sttWaiting").addClass("active");
							$("#btnProcessLater").css('display', 'none');
							$("#btnCheckAvailable").css('display', 'inline-block');
							$("#curStatus").val(2);
							alert("Product is not enough in source stock!");
							$('.input-info').prop("disabled", true);
							$('#btnSave').css('display', 'none');
							$('#btnEdit').css('display', 'inline-block');
						} else {
							alert("Process failed :( !");
							console.log(status);
						}
					},
					error : function(status) {
						alert("Failed :( !");
						console.log(status);
					}
				});
			} else {
				alert("Invalid Destination Location");
				return false;
			}
		}
	});
	
});