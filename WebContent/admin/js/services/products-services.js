servicesModule.service('productsServices', function(Restangular){
	var restPromise = Restangular.one("products");

	this.addProduct = function(data, success, error){
		restPromise.customPOST(data, 'product/add', {}, {}).then(function(response){
			success(response);
		} , function errorCallback() {
			error();
		});
	}

	this.getProducts = function(success, error){
		restPromise.one('all?special=false&date=10').get().then(function(response){
			success(response);
		} , function errorCallback() {
			error();
		});
	}

	this.getStoreItems = function(success, error){
		restPromise.one('items').get().then(function(response){
			success(response);
		} , function errorCallback() {
			error();
		});
	}

	this.updateProduct = function(data, success, error){
		restPromise.customPOST(data, 'product/update', {}, {}).then(function(response){
			success(response);
		} , function errorCallback() {
			error();
		});
	}

	this.removeProduct = function(productSku, success, error){
		restPromise.one('product/delete/'+productSku).get().then(function(response){
			success(response);
		} , function errorCallback() {
			error();
		});
	}
})
