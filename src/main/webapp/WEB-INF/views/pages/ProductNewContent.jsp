<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="productNewContent content-resize">
	<h1 style="text-align: center;">New Product</h1>
	<div class="row page-header">
		<div class="col-lg-6" style="margin-left: -15px;">
			<button id="btnSave" class="btn btn-success">Save</button>
			<button id="btnEdit" class="btn btn-primary">Edit</button>
			<button id="btnCancel" class="btn btn-danger">Cancel</button>
		</div>
		<input value=${isEdit } id="isEdit" style="display: none;">
		<input value="<c:choose><c:when test="${(!empty productToEdit) }">${productToEdit.productID }</c:when></c:choose>" id="productIDHidden" style="display: none;">
	</div>
	<div class="row information">
		<div class="col-lg-3">
			<input type="file" id="input-4" value="">
			<br>
				<c:choose>
					<c:when test="${(!empty productPhoto) }">
						<img src="data:image/(jpg|png);base64, ${productPhoto }" width="200" id="productImage"/>
					</c:when>
					<c:otherwise>
						<img src="" width="200" id="productImage"/>
					</c:otherwise>
				</c:choose>
        	<br>
			<!-- <img class="img-thumbnail" id="productPic" src="resources/images/1.jpg"
				alt="some_text"> <input id="input-4" type="file"> -->
		</div>
		<div class="col-lg-9">
			<!-- Thông tin nhập -->
			<table class="table no-border striped3n table-resize">
				<tbody>
					<tr class="row_1">
						<td><label>Product</label></td>
						<td class="td-resize">
							<div class="input-control select info-state">
								<input type="text" id="inputProduct" class="form-control input-info" 
								placeholder="Product's name"
								maxlength="250"
								value="<c:choose><c:when test="${(!empty productToEdit) }">${productToEdit.productName }</c:when></c:choose>">
							</div>
						</td>
						<td><label>Origin Price</label></td>
						<td colspan="1">
							<div class="input-control text  info-state">
								<input class="form-control input-info" id="inputOrgPrice" 
									onkeypress="return IsNumeric(event);" ondrop="return false;"
									type="number"
									value="<c:choose><c:when test="${(!empty productToEdit) }">${productToEdit.orgPrice }</c:when></c:choose>">
							</div>
						</td>
					</tr>
					<tr class="row_1">
						<td><label>Product Type</label></td>
						<td class="td-resize">
							<div class="input-control select info-state">
								<select id="listProductType" class="form-control input-info">
									<c:forEach var="productType" items="${listProductType}">
										<%-- <option data-id="${productType.typeID}">
											${productType.typeName}
										</option> --%>
										
										<c:choose>
											<c:when
												test="${productType.typeID == productToEdit.typeID.typeID}">
												<option selected="selected" data-id="${productType.typeID}">
													${productType.typeName}
												</option>
											</c:when>
											<c:otherwise>
												<option data-id="${productType.typeID}">
													${productType.typeName}
												</option>
											</c:otherwise>
										</c:choose>
										
									</c:forEach>
								</select>
							</div>
						</td>
						<td><label>Product Unit</label></td>
						<td class="td-resize">
							<div class="input-control select info-state">
								<select id="listProductUnit" class="form-control input-info">
									<c:forEach var="productUnit" items="${listProductUnit}">
										<%-- <option data-id="${productUnit.unitID}">
											${productUnit.unitName}
										</option> --%>
										
										<c:choose>
											<c:when
												test="${productUnit.unitID == productToEdit.unitID.unitID}">
												<option selected="selected" data-id="${productUnit.unitID}">
													${productUnit.unitName}
												</option>
											</c:when>
											<c:otherwise>
												<option data-id="${productUnit.unitID}">
													${productUnit.unitName}
												</option>
											</c:otherwise>
										</c:choose>
										
									</c:forEach>
								</select>
							</div>
						</td>
					</tr>
					<tr>
						<td><label>Manufacturer</label></td>
						<td class="td-resize">
							<div class="input-control select info-state">
								<select id="listManufacture" class="form-control input-info">
									<c:forEach var="manufacture" items="${listManufacture}">
										<%-- <option data-id="${manufacture.manufactureID}">
											${manufacture.name}
										</option> --%>
										
										<c:choose>
											<c:when
												test="${manufacture.manufactureID == productToEdit.manufactureID.manufactureID}">
												<option selected="selected" data-id="${manufacture.manufactureID}">
													${manufacture.name}
												</option>
											</c:when>
											<c:otherwise>
												<option data-id="${manufacture.manufactureID}">
													${manufacture.name}
												</option>
											</c:otherwise>
										</c:choose>
										
									</c:forEach>
								</select>
							</div>
						</td>
						<td><label>Provider</label></td>
						<td class="td-resize">
							<div class="input-control select info-state">
								<select id="listProvider" class="form-control input-info">
									<c:forEach var="provider" items="${listProvider}">
										<%-- <option data-id="${provider.providerID}">
											${provider.providerName}
										</option> --%>
										
										<c:choose>
											<c:when
												test="${provider.providerID == productToEdit.providerID.providerID}">
												<option selected="selected" data-id="${provider.providerID}">
													${provider.providerName}
												</option>
											</c:when>
											<c:otherwise>
												<option data-id="${provider.providerID}">
													${provider.providerName}
												</option>
											</c:otherwise>
										</c:choose>
										
									</c:forEach>
								</select>
							</div>
						</td>
					</tr>
					<tr>
						<td><label>Min Stock</label></td>
						<td>
							<div class="input-control text  info-state">
								<input class="form-control input-info" id="inputMinStock"
									onkeypress="return IsNumeric(event);" ondrop="return false;"
									type="number"
									value="<c:choose><c:when test="${(!empty productToEdit) }">${productToEdit.minStock }</c:when><c:otherwise></c:otherwise></c:choose>">
							</div>
						</td>
						<td><label>Max Stock</label></td>
						<td><input class="form-control input-info" id="inputMaxStock" 
							onkeypress="return IsNumeric(event);" ondrop="return false;"
							type="number"
							value="<c:choose><c:when test="${(!empty productToEdit) }">${productToEdit.maxStock }</c:when><c:otherwise></c:otherwise></c:choose>"></td>
					</tr>
					<tr>
						<td><label>Description</label></td>
						<td colspan="3">
							<div class="input-control text  info-state">
								<textarea class="form-control input-info" rows="5" id="inputDescription"
									placeholder="Description" maxlength="100" style="resize: none;"><c:choose><c:when test="${(!empty productToEdit) }">${productToEdit.description }</c:when><c:otherwise></c:otherwise></c:choose></textarea>
							</div>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</div>