<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div id="packing">
   <div class=contain>
      <h1>Assembly Product</h1>
      <div class="bill-detail">
         <h2>Assembly Information</h2>
         <form class="form-inline" id="buildDetail">
            <div class="row">
               <div class="form-group">
                  <label for="dateBuildProduct">Date</label> 
                  <input id="buildDate" readonly="readonly" class="form-control" type="text">
                  <label for="buildProductID">Build ID</label>
                  <input type="text" class="form-control" id="buildProductID" placeholder="Build ID">
                  <label for="staffName">Staff ID</label>
                  <input type="text" disabled="disabled" class="form-control" id="staffID" placeholder="staffID">
               </div>
            </div>
            <div class="row" id="row2">
               <div class="form-group">
                  <label for="note">Note</label>
                  <input type="text" class="form-control" id="note">
               </div>
            </div>
         </form>
      </div>
      <div class="packing-detail">
         <div class="col-md-4" id="productInfor">
            <h2>Product Information</h2>
            <table class="table">
               <tr>
                  <td>Product Name</td>
                  <td>
                     <select class="form-control" id="select_productN">
                        <c:forEach var="item" items="${listProduct}">
                           <option data-value="
                           <c:out value="${item.productID}">
                           </c:out>
                           ">
                           <c:out value="${item.productName}">
                           </c:out>
                           </option>
                        </c:forEach>
                     </select>
                  </td>
               </tr>
               <tr>
                  <td>Quantity</td>
                  <td><input type="number" onkeypress="return IsNumeric(event);" class="form-control" id="quantity" min="1" max="20" step="1"></td>
               </tr>
               <tr>
                  <td>Unit Price</td>
                  <td><input type="number" class="form-control" id="unitPrice" disabled="disabled"></td>
               </tr>
               <tr>
                  <td>SubTotal</td>
                  <td><input type="number" class="form-control" id="subTotal" disabled="disabled"></td>
               </tr>
               <tr>
                  <td>To Stock</td>
                  <td>
                     <select id="select_stockID" class="form-control">
                        <c:forEach var="item" items="${listStock}">
                           <option value="<c:out value="${item.stockID}"></c:out>">
                           <c:out value="${item.stockName}">
                           </c:out>
                           </option>
                        </c:forEach>
                     </select>
                  </td>
               </tr>
            </table>
         </div>
         <div class="col-md-8">
            <table id="tableComponent" class="table table-striped table-bordered">
               <thead>
                  <tr>
                     <th>Product ID</th>
                     <th>Product Name</th>
                     <th>Quantity</th>
                     <th>Unit Price</th>
                     <th>Sub Total</th>
                  </tr>
               </thead>
               <tbody>
               </tbody>
            </table>
         </div>
      </div>
      <div class="process-info">
         <button type="button" id="newProBuild" class="btn btn-primary">Create New</button> 
         <button type="button" id="saveProBuild" class="btn btn-success">Save Data</button>
         <button type="button" id="cancel" class="btn btn-warning">Cancel</button>
         <button type="button" class="btn btn-danger">Close</button>
      </div>
   </div>
</div>
