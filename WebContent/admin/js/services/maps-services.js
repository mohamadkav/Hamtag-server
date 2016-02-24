servicesModule.service('mapsServices', function(Restangular){
	var restPromise = Restangular.one("maps");

	this.getMaps = function(success, error){
		restPromise.one('all/simple/').get().then(function(response){
			success(response);
		} , function errorCallback() {
			error();
		});
	}

	this.addMap = function(data, success, error){
		restPromise.customPOST(data, 'map/add/', {}, {}).then(function(response){
			success(response);
		} , function errorCallback() {
			error();
		});
	}
})
