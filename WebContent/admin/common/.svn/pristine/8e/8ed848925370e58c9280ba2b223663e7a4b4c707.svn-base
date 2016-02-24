angular.module('vtResource', ['ngResource']).
	factory('vtResource', ['$resource', function($resource) {
		vtResrource = function(url, paramDefaults, actions){
			angular.extend(actions,{
				'pagedQuery':  {method:'GET', isArray:false},
				'update': {method: 'POST', isArray:false},
			});
			return $resource(url, paramDefaults, actions);
		};
		return vtResrource;
	}]);
