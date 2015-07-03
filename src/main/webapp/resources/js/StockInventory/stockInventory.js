$('#tableInventory').dataTable({
	"info" : false,
	"bLengthChange" : false // used to hide the property
});

$("#exportData").click(function(){
	window.location = "exportInventory";
});