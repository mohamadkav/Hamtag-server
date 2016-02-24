var mainApp = angular.module("lbs-panel" , [
	'restangular' ,
	'lbs-panel-services' ,
	'lbs-panel-directives' ,
	'ui.bootstrap' ,
	'ui.multiselect' ,
	'ngRoute',
	'ngAnimate',
	'angular-md5']);

function configApp ( RestangularProvider , $routeProvider , baseRequestConfig , routes) {
	var getBaseUrl = function(){
		return baseRequestConfig.protocol + "://" + baseRequestConfig.host + ":" + 
			baseRequestConfig.port + "/" + baseRequestConfig.basePackage ;
	};
	var initRoutes = function(){
		routes.forEach(function(route){
			$routeProvider.when(route.url , {
				templateUrl : route.templateUrl,
				controller : route.controller
			})
		});

		$routeProvider.otherwise({redirectTo : "define-product"});
	}
	RestangularProvider.setBaseUrl(getBaseUrl());
	RestangularProvider.setFullResponse(true);
	initRoutes();
}

mainApp.config(configApp);
mainApp.run(['$route', function($route)  {
  $route.reload();
}]);

