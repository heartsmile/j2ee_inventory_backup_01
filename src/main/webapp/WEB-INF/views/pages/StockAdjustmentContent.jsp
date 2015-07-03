<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="packing" >
	<div class="adjustmentContaint">
	<h1 class="page-header">Stock Adjustment</h1>
	
	<form class="form-inline">
		<div class="row">
			<button id="saveAdjust" class="btn btn-success" type="button">Save Data</button>
			<button id="cancelAdjust" class="btn btn-danger" type="button">Cancel</button>
			<button id="printAdjust" class="btn btn-default" type="button">Print</button>
		</div>
		
		<div class="row" style="margin-top: 20px; margin-left: -30px">
			<div class="col-md-6">
				<select id="select_stockName" class="form-control">
	               <c:forEach var="item" items="${listStock}">
	                  <option
	                  value="<c:out value="${item.stockID}"></c:out>">
	                  <c:out value="${item.stockName}">
	                  </c:out>
	                  </option>
	               </c:forEach>
	            </select>
				<button id="searchInven" class="btn btn-primary" type="button">Search</button>
			</div>
			<div class="col-md-6">
	            <label>Date</label>
	            <input id="date" type="text" class="form-control" disabled="disabled">
			</div>
		</div>
		
		<div class="row" style="margin-top: 15px">
			<table id="tableInven" class="table table-striped table-bordered">
               <thead>
                  <tr>
                     <th width="10%">ID</th>
                     <th width="20%">Product Name</th>
                     <th width="15%">Stock Quantity</th>
                     <th width="20%">Real Quantity</th>
                     <th width="5%">Different</th>
                     <th width="15%">Unit Price</th>
                     <th width="20%">Sub Total</th>
                  </tr>
               </thead>
               <tbody>
               </tbody>
               <tfoot>
			   	<tr>
			      <th colspan="3">Grand Total</th>
			      <th></th>
			      <th id="diffQuantity"></th>
			      <th></th>
			      <th id="grandTotal"></th>
			    </tr>
			  </tfoot>
            </table>
		</div>
	</form>
</div>
</div>