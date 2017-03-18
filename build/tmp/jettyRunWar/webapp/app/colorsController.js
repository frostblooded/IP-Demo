var app = angular.module('restApp', ['infinite-scroll']);

app.controller('ColorsController', ['$scope', '$http', function($scope, $http) {
	$http.get('api/colors').success(function(res) {
		$scope.colors = res;
	});
}]);
