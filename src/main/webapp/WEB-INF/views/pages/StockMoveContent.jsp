<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="content-resize page-header">
	<h1>Stock Move</h1>
	<div class="row">
		<div class="col-lg-12">
			<form action="NewStockMoveBill" method="post">
				<button class="btn btn-success" type="submit">Create New</button>
				<a class="btn btn-primary" id="btnExportAllBill">Export</a>
			</form>
		</div>
	</div>
</div>
<!-- Begin page content -->
<div class="content-resize" id="table-stock-move">
	<div class="row">
		<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" id="dataStockMoveTable">
			<table id="tableStockTransfer" class="table table-bordered table-striped table-hover">
				<thead>
					<tr>
						<th width="2%"><input id="checkAll" class="formcontrol"
							type="checkbox"></th>
						<!-- <th width="4%">No.</th> -->
						<th width="7%">Bill No</th>
						<th width="15%">Product</th>
						<th width="7%">Quantity</th>
						<th width="20%">Source</th>
						<th width="20%">Destination</th>
						<!-- <th width="15%">Description</th> -->
						<th width="8%">Status</th>
					</tr>
				</thead>
				<tbody>
					<!-- Load all data about stock move from database -->
					<c:forEach items="${listStockTrans}" var="stockTrans">
						<tr class="">
							<td><input class="formcontrol checkTransferBill"
							type="checkbox"></td>
							<%-- <td><%=i++ %></td> --%>
							<td>${stockTrans.transferID}</td>
							<td>${stockTrans.productID.productID}: ${stockTrans.productID.productName}</td>
							<td>${stockTrans.quantity}</td>
							<td>${stockTrans.fromStock.stockID}: ${stockTrans.fromStock.stockName}</td>
							<td>${stockTrans.toStock.stockID}: ${stockTrans.toStock.stockName}</td>
							<%-- <td>${stockTrans.description}</td> --%>
							<td>
								<c:choose>
							    	<c:when test="${stockTrans.statusID == 1}">New</c:when>
							    	<c:when test="${stockTrans.statusID == 2}">Waiting</c:when>
							    	<c:when test="${stockTrans.statusID == 3}">Available</c:when>
							    	<c:when test="${stockTrans.statusID == 4}">Done</c:when>
							    	<c:otherwise>...</c:otherwise>
							    </c:choose>
							</td>
						</tr>
					</c:forEach>

				</tbody>
			</table>
		</div>
	</div>
</div>


