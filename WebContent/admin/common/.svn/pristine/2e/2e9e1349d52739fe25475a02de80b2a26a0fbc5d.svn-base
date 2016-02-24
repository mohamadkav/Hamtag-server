// This service replace the existing vtResource and change it to mock standard
angular.module('vtResource', ['ngResource']).
	factory('vtResource', ['$resource', function($resource) {
		vtResrource = function(url, paramDefaults, actions){
			var replacedUrl = url.replace(":", "_");
			angular.extend(actions,{
				'get':    {method:'GET', url:replacedUrl + "/get.json"},
				'save':   {method:'POST', url:replacedUrl + "/add.json"},
				'update': {method: 'POST', isArray:false, url:replacedUrl + "/update.json"},
				'query':  {method:'GET', isArray:true, url:replacedUrl + "/list.json"},
				'pagedQuery':  {method:'GET', isArray:false, url:replacedUrl + "/pagedList.json"},
				'remove': {method:'DELETE', url:replacedUrl + "/delete.json"},
				'delete': {method:'DELETE', url:replacedUrl + "/delete.json"}
			});
			return $resource(url, paramDefaults, actions);
		};
		return vtResrource;
	}]);
