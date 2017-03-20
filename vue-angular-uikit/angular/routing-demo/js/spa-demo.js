//'use strict';
var app = angular.module('myApp', [ 'ngRoute' ]);

app.config(function($routeProvider) {
	$routeProvider

	.when('/', {
		templateUrl : 'pages/home.html',
		controller : 'HomeController'
	})

	.when('/blog', {
		templateUrl : 'pages/blog.html',
		controller : 'BlogController'
	})

	.when('/about', {
		templateUrl : 'pages/about.html',
		controller : 'AboutController'
	})

	.when('/directive-demo', {
		templateUrl : 'pages/directive-demo.html',
		controller : 'DirectiveController'
	})
	
	.otherwise({
		redirectTo : '/'
	});
});

app.controller('HomeController', function($scope) {
	$scope.message = 'Hello from HomeController';
});

app.controller('BlogController', function($scope) {
	$scope.message = 'Hello from BlogController';
});

app.controller('AboutController', function($scope) {
	$scope.message = 'Hello from AboutController';
});

app.controller('DirectiveController', function($scope) {
	$scope.message = 'Hello from DirectiveController';
	$scope.person = {
		name: 'Donald Trump',
		role: 'Apocalypse Lead'
	};
});