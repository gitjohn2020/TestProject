var app = angular.module('MasterManagement', [ 'ngSanitize' ]);
// angular.module('myApp', []);

// This will check the idle status when the app starts
// angular.module('myApp').run(['Idle', function(Idle) {
// Idle.watch();
// }]);

app
		.controller(
				'MasterController',
				function($scope, $http, $window) {

					

					$scope.uomForm = {

							uom_id:"",
							uom_nm:"",
							created_by:"",
							created_on:"",
							modified_by:"",
							modified_on:"",
							active:""

					};

					
					$scope.finalMSG = "";
					
					
					$scope.addUom = function() {

						var method = "";
						var url = "";

						method = "POST";
						url = $scope.contextPath +'/addUom';

						$http({
							method : method,
							url : url,
							data : angular.toJson($scope.uomForm),
							headers : {
								'Content-Type' : 'application/json'
							}
						}).then(_successUom, _errorUom);
						// _refreshCountryData();
						// this.window.reload();

					};
					
					
					$scope.logout = function() {
						var url = $scope.contextPath + "/logout";

						$window.location.href = url;
					}
					$scope.reloadPage = function() {
						$window.location.reload();
					}
					

					
					function _successUom(response) {
						
						console.log("Done");
						if(response.data){
							$scope.finalMSG ="Your data has been saved successfully " ;	
						}else{
							$scope.finalMSG ="Data saving failed.Please try again later " ;	
						}
						
						angular.element($('#modals').modal({
							backdrop : 'static',
							keyboard : false
						}));
					}

					function _errorUom(response) {
						$scope.finalMSG ="Data saving failed.Please try again later " ;	
						angular.element($('#modals').modal({
							backdrop : 'static',
							keyboard : false
						}));
					}
					
				});
