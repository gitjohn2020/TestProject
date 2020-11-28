<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login To Mail sender</title>
<%@include file="include.jsp" %>

<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>
<script src="http://code.jquery.com/jquery-1.8.3.js"></script>
<script
	src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
<script type="text/javascript">
	var app = angular.module("LoginManagement", []);
	app.controller("LoginController", function($scope, $http, $window) {

		$scope.loginForm = {

			uname : "",
			password : ""

		};

		$scope.loginUser = function() {

			var method = "";
			var url = "";

			method = "POST";
			url = 'login';

			$http({
				method : method,
				url : url,
				data : angular.toJson($scope.loginForm),
				headers : {
					'Content-Type' : 'application/json'
				}
			}).then(_success, _error);
			//_refreshCountryData();
			//this.window.reload();

		};

		function _success(response) {
			//alert(response.data);
			if (response.data == "success") {
				// window.location.href = 'resources/pages/decofur/common/home.jsp';
				$window.location.href = 'resources/pages/mailsender.jsp';
			} else {
				alert("Wrong user name or password");
			}
			_clearFormData();
		}

		function _error(response) {
			console.log(response.statusText);
		}

		//Clear the form
		function _clearFormData() {
			$scope.loginForm.user_id = "";
			$scope.loginForm.user_nm = "";
			$scope.loginForm.co_id = "";

		}

	});
</script>
</head>
<body data-open="click" data-menu="vertical-menu" data-col="1-column" class="vertical-layout vertical-menu 1-column  blank-page blank-page" ng-app="LoginManagement" ng-controller="LoginController">
	<div class="app-content content container-fluid">
      <div class="content-wrapper">
        <div class="content-header row">
        </div>
        <div class="content-body"><section class="flexbox-container">
    <div class="col-md-4 offset-md-4 col-xs-10 offset-xs-1  box-shadow-2 p-0">
        <div class="card border-grey border-lighten-3 m-0">
            <div class="card-header no-border">
                <div class="card-title text-xs-center">
                    <div class="p-1"><img src="" alt="branding logo"></div>
                </div>
                <h6 class="card-subtitle line-on-side text-muted text-xs-center font-small-3 pt-2"><span>Login To SMS Sending APP</span></h6>
            </div>
            <div class="card-body collapse in">
                <div class="card-block">
					<form name="login" method="post" action="login"
						class="form-horizontal form-simple">
						<c:if test="${param.error != null}">
							<div class="alert alert-danger">
								<p>Invalid username and password.</p>
							</div>
						</c:if>
						<c:if test="${param.logout != null}">
							<div class="alert alert-success">
								<p>You have been logged out successfully.</p>
							</div>
						</c:if>
						<fieldset class="form-group position-relative has-icon-left mb-0">
						 <input type="text"	ng-model="loginForm.uname" name="uname" id="username" class="form-control form-control-lg input-lg" placeholder="User Name">
	                     <div class="form-control-position"><i class="icon-head"></i></div>
                        </fieldset>
						<fieldset class="form-group position-relative has-icon-left">
						<input type="password"
								ng-model="loginForm.password" id="password" name="password"
								placeholder="Password" class="form-control form-control-lg input-lg" >
					<div class="form-control-position">
                                <i class="icon-key3"></i>
                            </div>
                        </fieldset>
						
							<input type="submit"
								class="btn btn-primary btn-lg btn-block" value="Submit"
								>

						
					</form>
			</div>
            </div>
            <div class="card-footer">
                <div class="">
                    <p class="float-sm-left text-xs-center m-0"><a href="recover-password.html" class="card-link">Recover password</a></p>
                    <p class="float-sm-right text-xs-center m-0">New to Robust? <a href="registerPage" class="card-link">Sign Up</a></p>
                </div>
            </div>
        </div>
    </div>
</section>

        </div>
      </div>
    </div>

</body>
</html>