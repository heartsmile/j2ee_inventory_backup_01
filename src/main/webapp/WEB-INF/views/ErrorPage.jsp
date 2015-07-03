<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Stock Move</title>

<!-- Bootstrap Core CSS -->
<link rel="stylesheet"
	href="<c:url value="/resources/css/bootstrap.min.css" />"
	type="text/css">

<!-- MetisMenu CSS -->
<link rel="stylesheet"
	href="<c:url value="/resources/css/metisMenu.min.css" />"
	type="text/css">

<!-- Custom CSS -->
<link rel="stylesheet"
	href="<c:url value="/resources/css/sb-admin-2.css" />" type="text/css">

<!-- Custom Fonts -->
<link rel="stylesheet"
	href="<c:url value="/resources/css/font-awesome/css/font-awesome.min.css" />"
	type="text/css">
<link rel="stylesheet"
	href="<c:url value="/resources/css/datepicker3.css" />"
	type="text/css">
	
<link rel="stylesheet"
	href="<c:url value="/resources/wizard-stepbystep/bootstrap-nav-wizard.css" />"
	type="text/css">
</head>
<body>
	<div id="wrapper">
		<!-- Navigation -->
		<nav class="navbar navbar-default navbar-static-top" role="navigation"
			style="margin-bottom: 0">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="index.html">Inventory Management - TSQ</a>
		</div>
		<!-- /.navbar-header -->

		<ul class="nav navbar-top-links navbar-right">
			<!-- /.dropdown -->
			<li class="dropdown"><a class="dropdown-toggle"
				data-toggle="dropdown" href="#" aria-expanded="false"> <i
					class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
			</a>
				<ul class="dropdown-menu dropdown-user">
					<li><a href="#"><i class="fa fa-user fa-fw"></i> Profile</a></li>
					<li><a href="#"><i class="fa fa-gear fa-fw"></i> Setting</a></li>
					<li class="divider"></li>
					<li><a href="#"><i class="fa fa-sign-out fa-fw"></i>
							Logout</a></li>
				</ul> <!-- /.dropdown-user --></li>
			<!-- /.dropdown -->
		</ul>
		<!-- /.navbar-top-links -->

		<div class="navbar-default sidebar" role="navigation">
			<div class="sidebar-nav navbar-collapse">
				<ul class="nav in" id="side-menu">
					<li class="sidebar-search">
						<div class="input-group custom-search-form">
							<input type="text" class="form-control" placeholder="Tìm kiếm...">
							<span class="input-group-btn">
								<button class="btn btn-default" type="button">
									<i class="fa fa-search"></i>
								</button>
							</span>
						</div> <!-- /input-group -->
					</li>
					<li><a href="home"><i class="fa fa-dashboard fa-fw"></i>
							Home</a></li>
					<!-- Stock Management -->
					<li class=""><a href="#"><i
							class="fa fa-bar-chart-o fa-fw"></i> Stock Management<span
							class="fa arrow"></span></a>
						<ul class="nav nav-second-level collapse">
							<li><a href="StockInward">Stock Inward</a></li>
							<li><a href="morris.html">Stock Outward</a></li>
							<li><a href="StockMove">Stock Move</a></li>
							<li><a href="morris.html">Adjustment</a></li>
						</ul> <!-- /.nav-second-level --></li>
					<!-- Category Management -->
					<li class=""><a href="#"><i class="fa fa-edit fa-fw"></i>
							Category Management<span class="fa arrow"></span></a>
						<ul class="nav nav-second-level collapse">
							<li><a href="product">Product</a></li>
							<li><a href="#">Stock</a></li>
							<li><a href="#">Unit</a></li>
							<li><a href="#">Provider</a></li>
							<li><a href="#">Product Group</a></li>

						</ul> <!-- /.nav-second-level --></li>

					<li><a href="#"><i class="fa fa-table fa-fw"></i> Report</a></li>

					<li><a href="#"><i class="fa fa-wrench fa-fw"></i>
							Configuration</a></li>

				</ul>
			</div>
			<!-- /.sidebar-collapse -->
		</div>
		<!-- /.navbar-static-side --> </nav>

		<div id="page-wrapper" style="min-height: 346px;">
			<div class="row">
				<div class="col-lg-12">
					<h3 align="center">Error! Not support this request!</h3>
				</div>

				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->
			<div class="row">
				<div class="col-lg-12"></div>
			</div>
			<!-- /.row -->
		</div>
		<!-- /#page-wrapper -->

	</div>
	<!-- /#wrapper -->

	<!-- jQuery -->
	<script
		src="<c:url value="/resources/js/jquery/jquery-1.11.3.min.js" />"
		type="text/javascript"></script>
	<!-- Bootstrap Core JavaScript -->
	<script src="<c:url value="/resources/js/bootstrap.min.js" />"
		type="text/javascript"></script>

	<script src="<c:url value="/resources/js/moment.min.js" />"
		type="text/javascript"></script>

	<!-- Metis Menu Plugin JavaScript -->
	<script src="<c:url value="/resources/js/metisMenu.min.js" />"
		type="text/javascript"></script>

	<!-- Custom Theme JavaScript -->
	<script src="../dist/js/sb-admin-2.js"></script>
	<script src="<c:url value="/resources/js/sb-admin-2.js" />"
		type="text/javascript"></script>
	<script src="<c:url value="/resources/js/StockMoveContent.js" />"
		type="text/javascript"></script>

	<script
		src="<c:url value="/resources/js/bootstrap-datepicker.js" />"
		type="text/javascript"></script>

</body>
</html>