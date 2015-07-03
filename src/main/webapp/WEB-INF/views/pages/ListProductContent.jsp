<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="packing" >
	<div class=contain>
	<h1>Product &amp; Service</h1>
	
	<form class="form-inline">
		<div class="row">
			<button id="createProduct" class="btn btn-primary" type="button">Create New</button>
			<button id="editProduct" class="btn btn-warning" type="button">Edit Data</button>
			<button id="deleteProduct" class="btn btn-danger" type="button">Delete</button>
			<button id="createComponent" class="btn btn-success" type="button" data-toggle="modal"
					data-target="#component">Create &amp; Edit Component</button>
			<button id="exportProduct" class="btn btn-default" type="button">Export</button>
		</div>
		
		<div class="row" style="margin-top: 15px">
			<table id="tableProduct" class="table table-striped table-bordered">
               <thead>
                  <tr>
                     <th>Product ID</th>
                     <th>Product Name</th>
                     <th>Unit Type</th>
                     <th>Manufacturer</th>
                     <th>Sale Price</th>
                     <th>Original Price</th>
                     <th>Product Type</th>
                     <th>Min Quantity</th>
                     <th>Max Quantity</th>
                  </tr>
               </thead>
               <tbody>
               </tbody>
            </table>
		</div>
	</form>
	<!-- Contact Lookup -->
	<div class="modal fade" id="component" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">

		<!-- Add content for popup modal -->
		<div class="content-resize">
			<div class="componentInfor">
			   <h2>Create component for Product</h2>
			   <div class="row">
			      <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			         <table id="tbData" class="table table-bordered table-hover">
			            <thead>
			               <tr>
			                  <th width="2%">
			                  <input id="check_all" class="formcontrol" type="checkbox"></th>
			                  <th>S. No</th>
			                  <th width="20%">Component ID</th>
			                  <th width="45%">Component Name</th>
			                  <th width="20%">Quantity</th>
			               </tr>
			            </thead>
			            <tbody>
			            </tbody>
			         </table>
			      </div>
			   </div>
			   <div class="row">
			      <div class="col-xs-6 col-md-6">
			         <button class="btn btn-danger delete" type="button">-Delete</button>
			         <button class="btn btn-success addmore" type="button">+ Add More</button>
			         <button id="saveProductComponent" class="btn btn-primary" type="submit">Save Data</button>
			      </div>
			   </div>
			</div>
			
		<script>
		function loadData(count, i) {
			var html = "<tr id='row_" + i + "'>";
			html += "<td><input class='case' type='checkbox'/></td>";
			html += "<td><span id='snum" + i + "'>" + count + ".</span></td>";
			html += "<td><input readonly='readonly' type='text' data-type='componentID' name='componentID[]' id='componentID_"+i+"' class='form-control searchID' autocomplete='off'></td>";
			html += "<td><input type='text' data-type='componentName' name='componentName[]' id='componentName_"+i+"' class='form-control searchName' autocomplete='off'></td>";
			html += "<td><input type='number' name='quantity[]' id='quantity_"
				+ i
				+ "' class='form-control changesNo' autocomplete='off' onkeypress='return IsNumeric(event);' ondrop='return false;' onpaste='return false;' min='1' max='20'></td>";
			html += "</tr>";
		
			return html;
		}
		</script>
      </div>
	</div>
</div>
</div>