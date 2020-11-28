<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Send Email</title>
<%@include file="include.jsp"%>

<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>

<script src="${pageContext.request.contextPath}/resources/js/app.js"></script>
<script
	src="//ajax.googleapis.com/ajax/libs/angularjs/1.6.1/angular-animate.js"></script>

<script
	src="//ajax.googleapis.com/ajax/libs/angularjs/1.6.1/angular-sanitize.js"></script>
	
<script type="text/javascript">
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
</script>
</head>
<body data-open="click" data-menu="vertical-menu" data-col="2-columns"
	class="vertical-layout vertical-menu 2-columns  fixed-navbar"
	ng-app="EmailManagement" ng-controller="EmailController">
	<%@include file="header.jsp"%>
	

	<div class="app-content content container-fluid">
		<div class="content-wrapper">
			<div class="content-body">
				<div class="row">

					<div class="col-xs-12">
						<div class="card">
							<div class="card-header">
								<h4 class="card-title">Welcome User</h4>
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
									<form name="sms" method="post" ng-submit="sendSMS()">
										<div class="row">
											<div class="form-group col-md-12">
												<label class="col-md-3 control-lable" for="subject">Message
													Subject</label>
												<div class="col-md-7">
													<input type="text" ng-model="subject" id="subject"
														class="form-control">
												</div>
											</div>
										</div>
										<div class="row">
											<div class="form-group col-md-12">
												<label class="col-md-3 control-lable" for="password">Message
													Body</label>
												<div class="col-md-7">
													<!--  <input type="text"  placeholder="Password" class="form-control">  -->
													<textarea id="password" class="form-control"
														ng-model="body" rows="3"></textarea>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="form-group col-md-12">
												<label class="col-md-3 control-lable" for="files">Select
													File</label>
												<div class="col-md-7">
													<input type="file" id="files" file-model="pathOfXL">
													<span class="file-custom"></span>
												</div>
											</div>
										</div>
										<!--  <input type = "file" fileread="emailForm.pathOfXL"  /> -->

										<div class="form-actions center">
											<input type="submit" value="Send SMS"
												class="btn btn-primary icon-check2 btn-lg">
										</div>

									</form>
								</div>
								<div class="table-responsive">
									<!--                 Put ur tables here -->
 
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
					
					<h4 class="modal-title" id="myModalLabel">Your message sending
						process has been completed</h4>
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

	<%@include file="footer.jsp"%>





</body>
</html>