var app = angular.module('EmailManagement', [ 'ngSanitize' ]);
// angular.module('myApp', []);

// This will check the idle status when the app starts
// angular.module('myApp').run(['Idle', function(Idle) {
// Idle.watch();
// }]);

app
		.controller(
				'EmailController',
				function($scope, $http, $window) {

					$scope.started = false;

					$scope.emailForm = {

						from : "",
						subject : "",
						body : "",
						pathOfXL : ""

					};

					$scope.registerForm = {

						fname : "",
						password : "",
						lname : "",
						flag : "",
						phone : "",
						email : "",
						address : "",
						user_type : ""
					};
					$scope.finalMSG = "";
					$scope.from = "";
					$scope.subject = "";
					$scope.body = "";
					$scope.pathOfXL = "";
					$scope.otpvalue = "";
					$scope.otpstatus = true;
					$scope.doAction = function(str) {
						var url = "";
						if (str == 'sms') {
							url = $scope.contextPath + "/sms";
						} else if (str == 'login') {
							url = $scope.contextPath + "/login";
						} else {
							url = $scope.contextPath + "/history";
						}
						// alert(url);
						// $http.get(url).then(function(response) {
						//	         
						// });
						$window.location.href = url;
					}
					$scope.sendEmail = function() {

						var method = "";
						var url = "";

						method = "POST";
						url = '../sendEmail';

						$http({
							method : method,
							url : url,
							data : angular.toJson($scope.loginForm),
							headers : {
								'Content-Type' : 'application/json'
							}
						}).then(_success, _error);
						// _refreshCountryData();
						// this.window.reload();

					};
					$scope.sendSMS = function() {

						var method = "";
						var url = "";
						var formData = new FormData();
						formData.append('pathOfXL', $scope.pathOfXL);
						formData.append('subject', $scope.subject);
						formData.append('body', $scope.body);
						var data = {
							'subject' : $scope.subject,
							'body' : $scope.body,
							'pathOfXL' : $scope.pathOfXL
						};
						method = "POST";
						url = $scope.contextPath + '/sendSMS';

						$http.post(url, formData, {
							transformRequest : angular.identity,
							headers : {
								'Content-Type' : undefined
							}
						}).then(_success, _error);

					};

					$scope.registerUser = function() {

						var formData = new FormData();
						formData.append('key', $scope.registerForm.phone);
						formData.append('email', $scope.registerForm.email);
						var url = $scope.contextPath + '/generateOtp';
						$http.post(url, formData, {
							transformRequest : angular.identity,
							headers : {
								'Content-Type' : undefined
							}
						}).then(_successGenerate, _error);

					};
					function _successGenerate(response) {
						angular.element($('#modals').modal({
							backdrop : 'static',
							keyboard : false
						}));
					}

					$scope.validateOtp = function() {

						var formData = new FormData();
						formData.append('key', $scope.registerForm.phone);
						formData.append('otpnum', $scope.otpvalue);
						var url = $scope.contextPath + '/validateOtp';
						$http.post(url, formData, {
							transformRequest : angular.identity,
							headers : {
								'Content-Type' : undefined
							}
						}).then(_successValidation, _error);

					};

					function _successValidation(response) {

						var url = $scope.contextPath + '/register';
						var method = "POST";
						if (response.data == "success") {

							$http({
								method : method,
								url : url,
								data : angular.toJson($scope.registerForm),
								headers : {
									'Content-Type' : 'application/json'
								}
							}).then(_successRegistration, _errorRegsitration);
						} else {
							$scope.otpstatus = false;
						}
					}
					function _successRegistration(response) {
						angular.element($('#modals').modal('hide'));

						if (response.data) {

							$scope.finalMSG = "You have successfully regsiterted. Please Login with your credentials";
						} else {

							$scope.finalMSG = "Your regsitration failed. Please try again after some times";
						}
						angular.element($('#modalSuccess').modal({
							backdrop : 'static',
							keyboard : false
						}));
					}
					$scope.logout = function() {
						var url = $scope.contextPath + "/logout";

						$window.location.href = url;
					}
					$scope.reloadPage = function() {
						$window.location.reload();
					}
					$scope.getHistory = function() {
						// alert("hiii");
						var formData = new FormData();
						var fromdate = $scope.fromdate;
						var todate = $scope.todate;
						alert(fromdate + "---" + todate);
						formData.append('fromdate', $scope.fromdate);
						formData.append('todate', $scope.todate);
						// var
						// data={'fromdate':$scope.fromdate,'todate':$scope.todate};
						method = "POST";
						url = $scope.contextPath + '/getHistory';
						// alert("heyyyyy------"+url);
						$http.post(url, formData, {
							transformRequest : angular.identity,
							headers : {
								'Content-Type' : undefined
							}
						}).then(_success1, _error1);
					}

					function _success1(response) {
						alert(response.data);
					}
					function _error1(response) {
						alert(response.data);
					}
					function _success(response) {
						// alert(response.data);
						// if(response.data == "success"){
						// // window.location.href =
						// 'resources/pages/decofur/common/home.jsp';
						// //$window.location.href ='../pages/mailsender.jsp';
						// }else {
						// alert("Wrong user name or password");
						// }
						// _clearFormData();
						console.log("Done");
						$scope.finalMSG = response.data;
						angular.element($('#modals').modal({
							backdrop : 'static',
							keyboard : false
						}));
					}

					function _error(response) {
						console.log(response.statusText);
					}
					function _errorRegsitration(response) {
						console.log(response.statusText);
					}
					// Clear the form
					function _clearFormData() {
						$scope.loginForm.user_id = "";
						$scope.loginForm.user_nm = "";
						$scope.loginForm.co_id = "";

					}

				});
