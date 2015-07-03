<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="content-resize" id="table-stock-move">
	<div class="row">
		<div class="col-lg-6">
			<div class="col-sm-3">
				<label>Product</label>
			</div>
			<div class="col-sm-5">
				<select class="form-control input-info" id="product">
					<c:if test="${!empty listProducts }">
						<c:forEach items="${listProducts}" var="product">
							<c:choose>
								<c:when
									test="${(!empty curStockTransfer) and product.productID == curStockTransfer.productID.productID}">
									<option selected="selected">${product.productID }:${product.productName}</option>
								</c:when>
								<c:otherwise>
									<option>${product.productID }:${product.productName}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</c:if>
				</select>
			</div>
		</div>
		<div class="col-lg-6">
			<div class="col-sm-3">
				<label>Expected Date</label>
			</div>
			<div class='col-sm-5'>
				<div id="sandbox-container">
					<input class="form-control input-info" type="text" id="expectedDay" 
					value="<c:choose><c:when test="${!empty curStockTransfer }">${curStockTransfer.expectedDate }</c:when><c:otherwise></c:otherwise></c:choose>">
				</div>
			</div>
		</div>
	</div>
	<div class="row" style="padding-top: 20px;">
		<div class="col-lg-6">
			<div class="col-sm-3">
				<label>Quantity</label>
			</div>
			<div class="col-sm-5">
				<input type="number" class="form-control input-info" id="quantity"
					value="<c:choose><c:when test="${!empty curStockTransfer and curStockTransfer.quantity != 0 }">${curStockTransfer.quantity }</c:when><c:otherwise>1</c:otherwise></c:choose>">
			</div>
		</div>
		<div class="col-lg-6">
			<div class="col-sm-3">
				<label>Priority</label>
			</div>
			<div class="col-sm-5">
				<select class="form-control input-info" id="priority">					
					<c:choose >
						<c:when
							test="${!empty curStockTransfer and curStockTransfer.priority == 4 }">
							<option selected="selected">Urgent</option>
							<option>High</option>
							<option>Normal</option>
							<option>Low</option>
						</c:when>
						<c:when
							test="${!empty curStockTransfer and curStockTransfer.priority == 3 }">
							<option>Urgent</option>
							<option selected="selected">High</option>
							<option>Normal</option>
							<option>Low</option>
						</c:when>
						<c:when
							test="${!empty curStockTransfer and curStockTransfer.priority == 2 }">
							<option>Urgent</option>
							<option>High</option>
							<option selected="selected">Normal</option>
							<option>Low</option>
						</c:when>
						<c:when
							test="${!empty curStockTransfer and curStockTransfer.priority == 1 }">
							<option>Urgent</option>
							<option>High</option>
							<option>Normal</option>
							<option selected="selected">Low</option>
						</c:when>
						<c:otherwise>
							<option>Urgent</option>
							<option>High</option>
							<option selected="selected">Normal</option>
							<option>Low</option>
						</c:otherwise>
					</c:choose>
				</select>
			</div>
		</div>
	</div>

	<div class="row" style="padding-top: 20px;">
		<div class="col-lg-6">
			<div class="col-sm-3">
				<label>Source Location</label>
			</div>
			<div class="col-sm-5">
				<select class="form-control input-info" id="fromStock">					
					<c:if test="${!empty listStocks }">
						<c:forEach items="${listStocks}" var="stock">
							<c:choose>
								<c:when
									test="${(!empty curStockTransfer) and stock.stockID == curStockTransfer.fromStock.stockID}">
									<option selected="selected">${stock.stockID}: ${stock.stockName}</option>
								</c:when>
								<c:otherwise>
									<option>${stock.stockID}: ${stock.stockName}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</c:if>
					<option data-id="createStock">Create new stock</option>
				</select>
			</div>
		</div>
		<div class="col-lg-6">
			<div class="col-sm-3">
				<label>Destination Location</label>
			</div>
			<div class="col-sm-5">
				<select class="form-control input-info" id="toStock">
					<c:if test="${!empty listStocks }">
						<c:forEach items="${listStocks}" var="stock">
							<c:choose>
								<c:when
									test="${(!empty curStockTransfer) and stock.stockID == curStockTransfer.toStock.stockID}">
									<option selected="selected">${stock.stockID}: ${stock.stockName}</option>
								</c:when>
								<c:otherwise>
									<option>${stock.stockID}: ${stock.stockName}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</c:if>
				</select>
			</div>
		</div>
	</div>
	<div class="row" style="padding-top: 20px;">
		<div class="col-lg-6">
			<div class="col-sm-3">
				<label>Description</label>
			</div>
			<div class="col-sm-9">
				<textarea rows="3" cols="100" class="form-control input-info"
					id="description" maxlength="200" wrap="hard" 
					style="resize: none;"><c:choose><c:when test="${!empty curStockTransfer}">${curStockTransfer.description }</c:when><c:otherwise></c:otherwise></c:choose></textarea>
			</div>
		</div>
	</div>
</div>