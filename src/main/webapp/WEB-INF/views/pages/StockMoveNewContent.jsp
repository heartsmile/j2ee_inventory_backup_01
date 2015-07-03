<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="content-resize page-header">
	<h1>Create A Stock Move</h1>
	<div class="row">
		<div class="col-lg-6">
			<form action="NewStockMoveBill" method="post">
				<a class="btn btn-primary" id="btnEdit">Edit</a> 
				<a class="btn btn-success" id="btnSave">Save</a> 
				<button class="btn btn-success" id="btnCreateNew" type="submit">Create New</button>
				<a class="btn btn-primary" id="btnProcessLater">Process Later</a> 
				<a class="btn btn-primary" id="btnCheckAvailable">Check available</a> 
				<a class="btn btn-primary" id="btnProcessAll">Process Entirely</a> 
				<a class="btn btn-danger" id="btnCancelMove">Cancel Move</a>
				<a class="btn btn-primary" id="exportTransfer">Export</a>
			</form>
		</div>
		<div class="col-xs-6">
				<ul class="nav nav-wizard">
					<c:choose>
						<c:when test="${curStatus == 1 }">
							<li class="active" id="sttNew"><a href="#">New</a></li>
							<li class="" id="sttWaiting"><a href="#">Waiting Availability</a></li>
							<li class="" id="sttAvailable"><a href="#">Available</a></li>
							<li class="" id="sttDone"><a href="#">Done</a></li>
						</c:when>
						<c:when test="${curStatus == 2 }">
							<li class="" id="sttNew"><a href="#">New</a></li>
							<li class="active" id="sttWaiting"><a href="#">Waiting Availability</a></li>
							<li class="" id="sttAvailable"><a href="#">Available</a></li>
							<li class="" id="sttDone"><a href="#">Done</a></li>
						</c:when>
						<c:when test="${curStatus == 3 }">
							<li class="" id="sttNew"><a href="#">New</a></li>
							<li class="" id="sttWaiting"><a href="#">Waiting Availability</a></li>
							<li class="active" id="sttAvailable"><a href="#">Available</a></li>
							<li class="" id="sttDone"><a href="#">Done</a></li>
						</c:when>
						<c:when test="${curStatus == 4 }">
							<li class="" id="sttNew"><a href="#">New</a></li>
							<li class="" id="sttWaiting"><a href="#">Waiting Availability</a></li>
							<li class="" id="sttAvailable"><a href="#">Available</a></li>
							<li class="active" id="sttDone"><a href="#">Done</a></li>
						</c:when>
					</c:choose>
				</ul>
		</div>
		<input value=${curTransferID } id="curTransferID" style="display: none;">
		<input value=${curStatus } id="curStatus" style="display: none;">
		<input value=${isEdit } id="isEdit" style="display: none;">
	</div>
</div>
<jsp:include page="StockMoveInputField.jsp"></jsp:include>
