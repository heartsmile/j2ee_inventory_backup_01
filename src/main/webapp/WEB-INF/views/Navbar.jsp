<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
			<a class="navbar-brand" href="index.html">Inventory Management -
				TSQ</a>
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
							<input type="text" class="form-control" placeholder="Search...">
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
							<li><a href="StockOutward">Stock Outward</a></li>
							<li><a href="StockMove">Stock Move</a></li>
							<li><a href="StockInventory">Stock Inventory</a></li>
							<li><a href="StockAdjustment">Inventory Adjustments</a></li>
						</ul> <!-- /.nav-second-level --></li>
					<!-- Category Management -->
					<li class=""><a href="#"><i class="fa fa-edit fa-fw"></i>
							Category Management<span class="fa arrow"></span></a>
						<ul class="nav nav-second-level collapse">
							<li><a href="product">Product</a></li>
							<li><a href="build">Assembly Product</a></li>
							<li><a href="listStock">Stock</a></li>
							<li><a href="listProvider">Provider</a></li>
						</ul> <!-- /.nav-second-level --></li>

					<li><a href="#"><i class="fa fa-table fa-fw"></i> Report</a></li>

					<li><a href="#"><i class="fa fa-wrench fa-fw"></i>
							Configuration</a></li>

				</ul>
			</div>
			<!-- /.sidebar-collapse -->
		</div>
		<!-- End navbar-static-side --> 
		</nav>