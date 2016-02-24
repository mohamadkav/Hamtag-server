function DefineProductViewCtrl($scope, productsServices, $modal){
	$scope.setMenuTitle('مشاهده محصولات');

	$scope.seeProductDetails = function(product){
		 var modalInstance = $modal.open({
            templateUrl: 'views/modals/edit-product.html',
            controller: EditProductCtrl,
            windowClass: 'edit-product-modal',
            resolve: {
                product: function () {
                    return product;
                }
            }
        });

        modalInstance.result.then(function(data){
            console.log(data);
        });
	}

	$scope.onRemoveProductSuccess = function(response){
		if(response.data.success){
			$scope.products[$scope.removedProductIndex].is_deleted = true;
		}else{

		}
	}

	$scope.onRemoveProductFailure = function(){

	}

	$scope.removeProduct = function(product,index){
		$scope.removedProductIndex = index;
		productsServices.removeProduct(product.sku, $scope.onRemoveProductSuccess, $scope.onRemoveProductFailure)
	}

	$scope.onGetProductsFailure = function(){

	}

	$scope.onGetProductsSuccess = function(response){
		if(response.data.success){
			$scope.products = response.data.original_response.updated_product_list;
		}else{

		}
	}

	$scope.getProducts = function(){
		productsServices.getProducts($scope.onGetProductsSuccess, $scope.onGetProductsFailure);
	}

	$scope.getProducts();
}