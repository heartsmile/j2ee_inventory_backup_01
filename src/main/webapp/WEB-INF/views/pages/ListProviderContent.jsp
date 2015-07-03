<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="content-resize page-header">
	<h1>Provider Management</h1>
	<div class="row">
		<div class="col-lg-12">
			<button id="createProvider" class="btn btn-primary">Create New</button>
			<button id="editProvider" class="btn btn-warning">Edit Data</button>
			<button id="deleteProvider" class="btn btn-danger">Delete</button>
			<button id="printProvider" class="btn btn-default" type="button">Print</button>
		</div>
	</div>
</div>
<!-- Begin page content -->
<div class="content-resize" id="table-provider-move">
	<div class="row">
		<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<table id="tableListProvider"
				class="table table-bordered table-striped table-hover">
				<thead>
					<tr>
						<th width="9%">Pro. ID</th>
						<th width="15%">Provider Name</th>
						<th width="20%">Address</th>
						<th width="10%">Telephone</th>
						<th width="15%">Email</th>
						<th width="15%">Website</th>
						<th width="17%">Description</th>
					</tr>
				</thead>
				<tbody>
					<!-- Load all data about provider from database -->
					<c:if test="${!empty listProvider }">
						<c:forEach items="${listProvider}" var="provider">
							<tr class="">
								<td>${provider.providerID}</td>
								<td>${provider.providerName}</td>
								<td>${provider.address}</td>
								<td>${provider.tel}</td>
								<td>${provider.email}</td>
								<td>${provider.website}</td>
								<td>${provider.description}</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
		</div>
	</div>
</div>