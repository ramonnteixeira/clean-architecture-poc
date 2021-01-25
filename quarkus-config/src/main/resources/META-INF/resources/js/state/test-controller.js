var app = angular.module('TestApp', []);

app.controller('TestCtrl', function ($scope, $http) {
	$scope.steps = [];

	console.log('iniciou');
	$http.get("/api/steps")
    .success(function(data, status, headers, config) {
        $scope.steps = data.steps;
		console.log($scope.steps);
	});

});
