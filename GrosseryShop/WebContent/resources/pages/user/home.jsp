<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
<%@include file="../include.jsp" %>
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
		$scope.logout=function(){
	    	var url = $scope.contextPath+"/logout";
	    	
	    	$window.location.href=url;
		}
		 $scope.doAction=function(str){
		    	var url = "";
		    	if(str=='sms'){
		    		url=$scope.contextPath+"/sms";
		    	}else{
		    		url=$scope.contextPath+"/history";
		    	}
		    	//alert(url);
//				 $http.get(url).then(function(response) {
//			         
//			      });
		    	$window.location.href=url;
			}
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
<body data-open="click" data-menu="vertical-menu" data-col="2-columns" class="vertical-layout vertical-menu 2-columns  fixed-navbar" ng-app="LoginManagement" ng-controller="LoginController">
	<%@include file="../header.jsp" %>
	<%@include file="usermenu.jsp" %>
	
      <!-- /main menu content-->
      <!-- main menu footer-->
      <!-- include includes/menu-footer-->
      <!-- main menu footer-->
   
    <!-- / main menu-->
    <div class="app-content content container-fluid">
      <div class="content-wrapper">
      <div class="content-body">
      <div class="row">
    <div class="col-xs-12">
        <div class="card">
            <div class="card-header">
                <h4 class="card-title">Welcome User</h4>
                <a class="heading-elements-toggle"><i class="icon-ellipsis font-medium-3"></i></a>
                <div class="heading-elements">
                    <ul class="list-inline mb-0">
                        
                    </ul>
                </div>
            </div>
            <div class="card-body collapse in">
                <div class="card-block card-dashboard">
<!--                     <p>Put ur details here</p> -->
                </div>
                <div class="table-responsive">
<!--                 Put ur tables here -->
                </div>
                </div>
                </div></div></div>
      </div>
      </div>
      </div>
      
					
      <%@include file="../footer.jsp" %>
	
</body>
</html>