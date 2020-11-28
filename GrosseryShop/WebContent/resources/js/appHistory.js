
var app = angular.module('EmailManagement', ['720kb.datepicker']);
//angular.module('myApp', []);



// This will check the idle status when the app starts
// angular.module('myApp').run(['Idle', function(Idle) {
//     Idle.watch();
// }]);

app.controller('EmailController', function ($scope,$http,$window) {
     
   

    $scope.emailForm = {
			
			from: "",
			subject: "",
			body:"",
			pathOfXL:""
	
	};
    $scope.records=[];
	$scope.from="";
	$scope.subject="";
	$scope.body="";
	$scope.pathOfXL="";
	$scope.hideTable=true;
	$scope.doAction=function(str){
    	var url = "";
    	if(str=='sms'){
    		url=$scope.contextPath+"/sms";
    	}else{
    		url=$scope.contextPath+"/history";
    	}
    	//alert(url);
//		 $http.get(url).then(function(response) {
//	         
//	      });
    	$window.location.href=url;
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
         }).then( _success, _error );
         //_refreshCountryData();
         //this.window.reload();
        
     };
	
     $scope.logout=function(){
    	var url = $scope.contextPath+"/logout";
    	//alert(url);
//		 $http.get(url).then(function(response) {
//	         
//	      });
    	$window.location.href=url;
	}
     $scope.getHistory=function(){
    	 //alert("hiii");
    	 var formData = new FormData();
    	 var fromdate=$scope.fromdate;
    	 var todate=$scope.todate;
    	// alert(fromdate+"---"+todate);
    	 formData.append('fromdate',$scope.fromdate);
         formData.append('todate',$scope.todate);
    	// var data={'fromdate':$scope.fromdate,'todate':$scope.todate};
         method = "POST";
         url = $scope.contextPath+'/getHistory';
    // alert("heyyyyy------"+url);
     $http.post(url, formData,{
            transformRequest : angular.identity,
            headers : {
                'Content-Type' : undefined
            }})
            .then( _success1, _error1 );
     }
     
     function _success1(response){
    	 $scope.hideTable=false;
    	 $scope.records=response.data;
    	// alert(response.data);
     }
     function _error1 (response){
    	 alert(response.data);
     }
	

	
});

