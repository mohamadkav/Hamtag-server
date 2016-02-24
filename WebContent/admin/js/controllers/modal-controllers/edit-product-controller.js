function EditProductCtrl ($scope, $modalInstance, product, productsServices) {
	$scope.product = product;
	console.log(product);
	$scope.inEditMode = false;
	$scope.editedItems = new Array();
	$scope.deletedItems = new Array();

	$scope.toggleEditMode = function(){
		$scope.inEditMode = !$scope.inEditMode;
	}

	$scope.deleteItem = function(itemIndex){
		$scope.itemEditMode.splice(itemIndex,1);
		$scope.product.items.splice(itemIndex,1);
	}

	$scope.editItem = function(itemIndex){
		$scope.itemEditMode[itemIndex] = true;
	}

	$scope.updateItem = function(itemIndex){
		$scope.itemEditMode[itemIndex] = false;
	}

	$scope.onUpdateProductFailure = function(){
		
	}

	$scope.onUpdateProductSuccess = function(response){
		if(response.data.success){
			$modalInstance.close();
		}else{

		}
	}

	$scope.updateProduct = function(){
		var query  = $scope.product;
		query.items.forEach(function(item){
			item.is_deleted = false;
		});
		productsServices.updateProduct(query, $scope.onUpdateProductSuccess, $scope.onUpdateProductFailure)
	}

	$scope.init = function(){
		$scope.itemEditMode = new Array();
		for(var i =0 ; i < $scope.product.items.length ; i++){
			$scope.itemEditMode.push(false);
//			$scope.product.items[i].is_deleted = false;
		}
	}

	$scope.init();
}