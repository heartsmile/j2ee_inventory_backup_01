<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>

<meta charset="utf-8">

<title>Stock Outward</title>

<!-- Bootstrap Core CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css"
	type="text/css">
<link rel="stylesheet"
	href="<c:url value="/resources/css/StockInward/jquery-ui.css" />"
	type="text/css">
<link rel="stylesheet"
	href="<c:url value="/resources/css/bootstrap-dialog.css" />"
	type="text/css">
<link rel="stylesheet"
	href="<c:url value="/resources/css/StockOutward/stockOutward.css" />"
	type="text/css">

<!-- MetisMenu CSS -->
<link rel="stylesheet"
	href="<c:url value="/resources/css/metisMenu.min.css" />"
	type="text/css">

<!-- Custom CSS -->
<link rel="stylesheet"
	href="<c:url value="/resources/css/sb-admin-2.css" />" type="text/css">

<!-- Custom Fonts -->
<link rel="stylesheet"
	href="<c:url value="/resources/css/font-awesome/css/font-awesome.min.css" />"
	type="text/css">

<!-- JavaScript file -->
<!-- Load JavaScript Libraries -->
<script src="<c:url value="/resources/js/jquery/jquery-1.11.3.min.js" />"
	type="text/javascript"></script>
<!-- Bootstrap Core JavaScript -->
<script src="<c:url value="/resources/js/bootstrap.min.js" />"
	type="text/javascript"></script>
<script src="<c:url value="/resources/js/jquery/jquery-ui.js" />"
	type="text/javascript"></script>
<script src="<c:url value="/resources/js/bootstrap-dialog.min.js" />"
	type="text/javascript"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="<c:url value="/resources/js/metisMenu.min.js" />"
	type="text/javascript"></script>

<!-- Custom Theme JavaScript -->
<script src="../dist/js/sb-admin-2.js"></script>
<script src="<c:url value="/resources/js/sb-admin-2.js" />"
	type="text/javascript"></script>
<script src="<c:url value="/resources/js/StockOutward/StockOutward.js" />"
	type="text/javascript"></script>
</head>
<body>
	<div id="wrapper">
		<!-- Navigation -->
		<jsp:include page="Navbar.jsp"></jsp:include>

		<div id="page-wrapper" style="min-height: 346px;">
			<div class="row">
				<div class="col-lg-12">
					<!-- Include content of this page  -->
					<jsp:include page="pages/StockOutwardContent.jsp"></jsp:include>
					<!-- Include content of this page  -->
				</div>

				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->
			<div class="row">
				<div class="col-lg-12"></div>
			</div>
			<!-- /.row -->
		</div>
		<!-- /#page-wrapper -->

	</div>
	<!-- /#wrapper -->
	<script src="<c:url value="/resources/js/StockOutward/StockOutwardTable.js" />"
		type="text/javascript"></script>
	<script>
		function loadData(count, i) {
			var html = "<tr id='row_" + i + "'>";
			html += "<td><input class='case' type='checkbox'/></td>";
			html += "<td><span id='snum" + i + "'>" + count + ".</span></td>";
			html += "<td><input readonly='readonly'readonly='readonly' type='text' data-type='productID' name='productID[]' id='productID_"+i+"' class='form-control searchID' autocomplete='off'></td>";
			html += "<td><input type='text' data-type='productName' name='productName[]' id='productName_"+i+"' class='form-control searchName' autocomplete='off'></td>";
			html += "<td>" + "<select id='stockID_"+i+"' class='form-control'>"
					+ "<c:forEach var='item' items='${listStock}'>"
					+ "<option value='<c:out value='${item.stockID}'>"
					+ "</c:out>'><c:out value='${item.stockName}'>"
					+ "</c:out></option></c:forEach>" + "</select>" + "</td>";
			html += "<td><input readonly='readonly' type='text' data-type='unitName' name='unitName[]' id='unitName_"+i+"' class='form-control' autocomplete='off'></td>";
			html += "<td><input readonly='readonly' type='text' name='price[]' id='price_"
					+ i
					+ "' class='form-control price' autocomplete='off' onkeypress='return IsNumeric(event);' ondrop='return false;' onpaste='return false;'></td>";
			html += "<td><input type='text' name='quantity[]' id='quantity_"
					+ i
					+ "' class='form-control changesNo' autocomplete='off' onkeypress='return IsNumeric(event);' ondrop='return false;' onpaste='return false;'></td>";
			html += "<td><input readonly='readonly' type='text' name='total[]' id='total_"
					+ i
					+ "' class='form-control totalLinePrice' autocomplete='off' onkeypress='return IsNumeric(event);' ondrop='return false;' onpaste='return false;'></td>";
			html += "</tr>";

			return html;
		}
	</script>
</body>
</html>
