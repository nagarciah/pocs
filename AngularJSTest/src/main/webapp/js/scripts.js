angular.module("MyModule", [])
    .controller("MyController", function($scope) {
        $scope.clickCount = 0;

        $scope.userClick = function() {
            $scope.clickCount++;
        };
        
        if($scope=1){
        	alert("error");
        }
    });
