<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="stockNewContent content-resize">
	<h1 style="text-align: center;">Stock Detail</h1>
	<div class="row page-header">
		<div class="col-lg-6" style="margin-left: -15px;">
			<button id="btnSave" class="btn btn-success">Save</button>
			<button id="btnEdit" class="btn btn-primary">Edit</button>
			<button id="btnCancel" class="btn btn-danger">Cancel</button>
		</div>
		<input value=${isEdit } id="isEdit" style="display: none;">
		<input value="<c:choose><c:when test="${(!empty stockToEdit) }">${stockToEdit.stockID }</c:when></c:choose>" id="stockIDHidden" style="display: none;">
	</div>
	<div class="row information">
		<div class="col-lg-12">
			<!-- Thông tin nhập -->
			<table class="table no-border striped3n table-resize">
				<tbody>
					<tr class="row_1">
						<td><label>Stock Name</label></td>
						<td class="td-resize">
							<div class="input-control select info-state">
								<input type="text" id="inputStockName" class="form-control input-info" 
								placeholder="Stock's name"
								maxlength="250"
								value="<c:choose><c:when test="${(!empty stockToEdit) }">${stockToEdit.stockName }</c:when></c:choose>">
							</div>
						</td>
						<td><label>Manager</label></td>
						<td class="td-resize">
							<div class="input-control select info-state">
								<select id="listStaff" class="form-control input-info">
									<c:forEach var="staff" items="${listStaff}">
										<c:choose>
											<c:when
												test="${staff.staffID == stockToEdit.managerID.staffID}">
												<option selected="selected" data-id="${staff.staffID}">
													${staff.staffName}
												</option>
											</c:when>
											<c:otherwise>
												<option data-id="${staff.staffID}">
													${staff.staffName}
												</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select>
							</div>
						</td>
					</tr>
					<tr class="row_1">
						<td><label>Address</label></td>
						<td class="td-resize">
							<div class="input-control select info-state">
								<input type="text" id="inputAddress" class="form-control input-info" 
								placeholder="Address"
								maxlength="250"
								value="<c:choose><c:when test="${(!empty stockToEdit) }">${stockToEdit.address }</c:when></c:choose>">
							</div>
						</td>
						<td><label>Size</label></td>
						<td class="td-resize">
							<div class="input-control select info-state">
								<input type="number" id="inputSize" class="form-control input-info" 
								maxlength="10" onkeypress="return IsNumeric(event);" ondrop="return false;"
								value="<c:choose><c:when test="${(!empty stockToEdit) }">${stockToEdit.size }</c:when></c:choose>">
							</div>
						</td>
					</tr>
					<tr>
						<td><label>Description</label></td>
						<td class="td-resize">
							<div class="input-control select info-state">
								<input type="text" id="inputDescription" class="form-control input-info"
								placeholder="Description" 
								maxlength="250"
								value="<c:choose><c:when test="${(!empty stockToEdit) }">${stockToEdit.desciption }</c:when></c:choose>">
							</div>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</div>