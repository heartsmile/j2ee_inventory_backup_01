<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="providerNewContent content-resize">
	<h1 style="text-align: center;">Provider Detail</h1>
	<div class="row page-header">
		<div class="col-lg-6" style="margin-left: -15px;">
			<button id="btnSave" class="btn btn-success">Save</button>
			<button id="btnEdit" class="btn btn-primary">Edit</button>
			<button id="btnCancel" class="btn btn-danger">Cancel</button>
		</div>
		<input value=${isEdit } id="isEdit" style="display: none;">
		<input value="<c:choose><c:when test="${(!empty providerToEdit) }">${providerToEdit.providerID }</c:when></c:choose>" id="providerIDHidden" style="display: none;">
	</div>
	<div class="row information">
		<div class="col-lg-12">
			<!-- Thông tin nhập -->
			<table class="table no-border striped3n table-resize">
				<tbody>
					<tr class="row_1">
						<td><label>Provider Name *</label></td>
						<td class="td-resize">
							<div class="input-control select info-state">
								<input type="text" id="inputProviderName" class="form-control input-info" 
								placeholder="Provider's name"
								maxlength="250"
								value="<c:choose><c:when test="${(!empty providerToEdit) }">${providerToEdit.providerName }</c:when></c:choose>">
							</div>
						</td>
						<td><label>Telephone *</label></td>
						<td class="td-resize">
							<div class="input-control select info-state">
								<input type="text" id="inputTel" class="form-control input-info" 
								placeholder="123-456-789"
								maxlength="15" onkeypress="return IsNumeric(event);" ondrop="return false;"
								value="<c:choose><c:when test="${(!empty providerToEdit) }">${providerToEdit.tel }</c:when></c:choose>">
							</div>
						</td>
					</tr>
					<tr class="row_1">
						<td><label>Email</label></td>
						<td class="td-resize">
							<div class="input-control select info-state">
								<input type="text" id="inputEmail" class="form-control input-info" 
								placeholder="Email"
								maxlength="100"
								value="<c:choose><c:when test="${(!empty providerToEdit) }">${providerToEdit.email }</c:when></c:choose>">
							</div>
						</td>
						<td><label>Address *</label></td>
						<td class="td-resize">
							<div class="input-control select info-state">
								<input type="text" id="inputAddress" class="form-control input-info" 
								placeholder="Address"
								maxlength="250"
								value="<c:choose><c:when test="${(!empty providerToEdit) }">${providerToEdit.address }</c:when></c:choose>">
							</div>
						</td>
					</tr>
					<tr>
						<td><label>Website</label></td>
						<td class="td-resize">
							<div class="input-control select info-state">
								<div class="input-control select info-state">
								<input type="text" id="inputWebsite" class="form-control input-info" 
								placeholder="Website"
								maxlength="100"
								value="<c:choose><c:when test="${(!empty providerToEdit) }">${providerToEdit.website }</c:when></c:choose>">
							</div>
							</div>
						</td>
						<td><label>Description</label></td>
						<td class="td-resize">
							<div class="input-control select info-state">
								<input type="text" id="inputDescription" class="form-control input-info"
								placeholder="Description" 
								maxlength="250"
								value="<c:choose><c:when test="${(!empty providerToEdit) }">${providerToEdit.description }</c:when></c:choose>">
							</div>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</div>