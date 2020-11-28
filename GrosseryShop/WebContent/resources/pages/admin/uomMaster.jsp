<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>UOM Master</title>
<%@include file="../include.jsp"%>

<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>

<script src="${pageContext.request.contextPath}/resources/js/masterApp.js"></script>
<script
	src="//ajax.googleapis.com/ajax/libs/angularjs/1.6.1/angular-animate.js"></script>

<script
	src="//ajax.googleapis.com/ajax/libs/angularjs/1.6.1/angular-sanitize.js"></script>
	<script src="https://unpkg.com/ag-grid-community/dist/ag-grid-community.min.noStyle.js"></script>
  <link rel="stylesheet" href="https://unpkg.com/ag-grid-community/dist/styles/ag-grid.css">
  <link rel="stylesheet" href="https://unpkg.com/ag-grid-community/dist/styles/ag-theme-alpine.css">
<script type="text/javascript" charset="utf-8">
	app.directive('fileModel', [ '$parse', function($parse) {
		return {
			restrict : 'A',
			link : function(scope, element, attrs) {
				var model = $parse(attrs.fileModel);
				var modelSetter = model.assign;

				element.bind('change', function() {
					scope.$apply(function() {
						modelSetter(scope, element[0].files[0]);
					});
				});
			}
		};
	} ]);
	
	var columnDefs = [
	                  {headerName: "UOM S-name", field: "uom_id"},
	                  {headerName: "UOM NM", field: "uom_nm"},
	                  {headerName: "Created By", field: "created_by"},
	                  {headerName: "Created On", field: "created_on"}
	                ];

	                // let the grid know which columns to use
	                var gridOptions = {
	                  columnDefs: columnDefs
	                };

	              // lookup the container we want the Grid to use
	              var eGridDiv = document.querySelector('#myGrid');

	              // create the grid passing in the div to use together with the columns & data we want to use
	              new agGrid.Grid(eGridDiv, gridOptions);

	              agGrid.simpleHttpRequest({url:'http://localhost:8080/GrosseryShop/getAllUom'}).then(function(data) {
	            	  console.log("data is:"+data)
	                  gridOptions.api.setRowData(data);
	              });
</script>
</head>
<body data-open="click" data-menu="vertical-menu" data-col="2-columns"
	class="vertical-layout vertical-menu 2-columns  fixed-navbar"
	ng-app="MasterManagement" ng-controller="MasterController">
	<%@include file="../header.jsp"%>
	<%@include file="adminmenu.jsp"%>

	<div class="app-content content container-fluid">
		<div class="content-wrapper">
			<div class="content-body">
				<div class="row">

					<div class="col-xs-12">
						<div class="card">
							<div class="card-header">
								<h4 class="card-title">UOM Master</h4>
								<a class="heading-elements-toggle"><i
									class="icon-ellipsis font-medium-3"></i></a>
								<div class="heading-elements">
									<ul class="list-inline mb-0">

									</ul>
								</div>
							</div>
							<div class="card-body collapse in">
								<div class="card-block card-dashboard">
									<!--                     <p>Put ur details here</p> -->
									<ul class="nav nav-tabs">
							<li class="nav-item">
								<a class="nav-link active" id="base-tab1" data-toggle="tab" aria-controls="tab1" href="#tab1" aria-expanded="true">Add Uom</a>
							</li>
							<li class="nav-item">
								<a class="nav-link" id="base-tab2" data-toggle="tab" aria-controls="tab2" href="#tab2" aria-expanded="false">View UOM</a>
							</li>
							
						</ul>
						<div class="tab-content px-1 pt-1">
						<div role="tabpanel" class="tab-pane active" id="tab1" aria-expanded="true" aria-labelledby="base-tab1">
									<form name="uom" method="post" ng-submit="addUom()">
										<div class="row">
											<div class="form-group col-md-12">
												<label class="col-md-3 control-lable" for="usname">Uom Short name</label>
												<div class="col-md-7">
													<input type="text" ng-model="uomForm.uom_id" id="usname"
														class="form-control">
												</div>
											</div>
										</div>
										<div class="row">
											<div class="form-group col-md-12">
												<label class="col-md-3 control-lable" for="uname">Uom Name</label>
												<div class="col-md-7">
													<input type="text" ng-model="uomForm.uom_nm" id="uname"
														class="form-control">
												</div>
											</div>
										</div>
										
										<!--  <input type = "file" fileread="emailForm.pathOfXL"  /> -->

										<div class="form-actions center">
											<input type="submit" value="Add UOM"
												class="btn btn-primary icon-check2 btn-lg">
										</div>

									</form>
									</div>
									<div class="tab-pane" id="tab2" aria-labelledby="base-tab2">
								<div id="myGrid" style="height: 600px;width:500px;" class="ag-theme-alpine"></div>
							</div>
									</div>
								</div>
								
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="modals" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					
					<h4 class="modal-title" id="myModalLabel">Add Uom Status</h4>
				</div>
				<div class="modal-body">
					<p ng-bind-html="finalMSG"></p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal" ng-click="reloadPage()">Close</button>
				</div>
			</div>
		</div>
	</div>

	<%@include file="../footer.jsp"%>





</body>
</html>