<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="packing" >
	<div class=contain>
	<h1>Stock Inventory</h1>
	
	<form class="form-inline">
		<div class="row">
			<button id="exportData" class="btn btn-primary" type="button">Export Data</button>
			<button id="printData" class="btn btn-default" type="button">Print</button>
		</div>
		
		<div class="row" style="margin-top: 15px">
			<table id="tableInventory" class="table table-striped table-bordered">
               <thead>
                  <tr>
                     <th>Date</th>
                     <th>Product ID</th>
                     <th>Product Name</th>
                     <th>Unit Type</th>
                     <th>Quantity</th>
                     <th>Original Price</th>
                     <th>Sub Total</th>
                     <th>Stock</th>
                  </tr>
               </thead>
               	<tbody>
					<c:forEach items="${listSInven}" var="current">
						<tr>
							<td>
								${current.toStringDateWeb()}
							</td>

							<td>
								<c:out value="${current.productID.productID}" />
							</td>

							<td>
								<c:out value="${current.productID.productName}" />
							</td>

							<td>
								<c:out value="${current.productID.unitID.unitName}" />
							</td>
							
							<td>
								<c:out value="${current.quantity}" />
							</td>

							<td>
								<c:out value="${current.price}" />
							</td>
							
							<td>
								<c:out value="${current.amount}" />
							</td>
							
							<td>
								<c:out value="${current.stockID.stockName}" />
							</td>
						</tr>
					</c:forEach>
				</tbody>
            </table>
		</div>
	</form>
</div>
</div>