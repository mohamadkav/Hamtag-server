servicesModule.service('notificationServices', function(Restangular){
	var restPromiseTable = Restangular.one("tables");
	var restPromiseDevices = Restangular.one("devices");

	this.getColumns = function(data , success){
		restPromiseTable.customPOST(data , 'column/' , {} , {}).then(function(response){
			success(response);
		} , function errorCallback() {
			console.log('error in getColumns');
		});
	}

	this.genrateNotification = function(data , success){
		restPromiseDevices.customPOST(data , 'notifications/generate/' , {} , {}).then(function(response){
			success(response);
		} , function errorCallback() {
			console.log('error in genrateNotification');
		});
	}

	this.getModelsByBrands = function(data , success){
		restPromiseTable.customPOST(data , 'column/brand/' , {} , {}).then(function(response){
			success(response);
		} , function errorCallback() {
			console.log('error in getModelsByBrands');
		});
	}

	this.getVersionsByOSNames = function(data , success){
		restPromiseTable.customPOST(data , 'column/os/' , {} , {}).then(function(response){
			success(response);
		} , function errorCallback() {
			console.log('error in getVersionsByOSNames');
		});
	}
})