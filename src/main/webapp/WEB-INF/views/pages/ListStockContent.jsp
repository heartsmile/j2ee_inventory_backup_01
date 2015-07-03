<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="content-resize page-header">
	<h1>Stock Management</h1>
	<div class="row">
		<div class="col-lg-12">
			<button id="createStock" class="btn btn-primary">Create New</button>
			<button id="editStock" class="btn btn-warning">Edit Data</button>
			<button id="deleteStock" class="btn btn-danger">Delete</button>
			<button id="printStock" class="btn btn-default" type="button">Print</button>
		</div>
	</div>
</div>
<!-- Begin page content -->
<div class="content-resize" id="table-stock-move">
	<div class="row">
		<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<table id="tableListStock"
				class="table table-bordered table-striped table-hover">
				<thead>
					<tr>
						<th width="9%">Stock ID</th>
						<th width="15%">Stock Name</th>
						<th width="25%">Address</th>
						<th width="10%">Size</th>
						<th width="15%">Manager</th>
						<th width="23%">Description</th>
					</tr>
				</thead>
				<tbody>
					<!-- Load all data about stock from database -->
					<c:if test="${!empty listStock }">
						<c:forEach items="${listStock}" var="stock">
							<tr class="">
								<td>${stock.stockID}</td>
								<td>${stock.stockName}</td>
								<td>${stock.address}</td>
								<td>${stock.size}</td>
								<td>${stock.managerID.staffName}</td>
								<td>${stock.desciption}</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
		</div>
	</div>
</div>