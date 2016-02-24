function DefineProductNewCtrl($scope, md5, mapsServices, errors, productsServices, $modal, $log){
	$scope.setMenuTitle('اضافه کردن محصول');
	$scope.product = new Object();
    $scope.showResult = false;
    $scope.showError = false;

	$scope.add = function(){
    	var f = document.getElementById('file').files[0];
		var r = new FileReader();
	    r.onloadend = function(e){
	        $scope.data = e.target.result;
	    	var hash = md5.createHash($scope.data);
	    }
	    r.readAsBinaryString(f);
    }

    $scope.onSubmitFailure = function(){
        $scope.showResult = false;
        $scope.showError = true;
        $scope.errorBoxMessage = errors['SERVER_ERROR'];
    }

    $scope.onSubmitSuccess = function(response){
        if(response.data.success){
            $scope.showResult = true;
            $scope.showError = false;
        }else{
            $scope.showResult = false;
            $scope.showError = true;
            $scope.errorBoxMessage = errors[response.data.error];
        }
    }

    $scope.submit = function(){
        if(angular.isUndefined($scope.product.title)||$scope.product.title == ''){
            $scope.showResult = false;
            $scope.showError = true;
            $scope.errorBoxMessage = errors['EMPTY_TITLE_FIELD'];
        }else if(angular.isUndefined($scope.product.sku)||$scope.product.sku == ''){
            $scope.showResult = false;
            $scope.showError = true;
            $scope.errorBoxMessage = errors['EMPTY_SKU_FIELD'];
        }else if(angular.isUndefined($scope.product.price)||$scope.product.price == ''){
            $scope.showResult = false;
            $scope.showError = true;
            $scope.errorBoxMessage = errors['EMPTY_PRICE_FIELD'];
        }else if(angular.isUndefined($scope.product.items)||$scope.product.items.length<1){
            $scope.showResult = false;
            $scope.showError = true;
            $scope.errorBoxMessage = errors['EMPTY_ITEMS_FIELD'];
        }else{
            $scope.showError = false;
            var wants_to_continue = true;
            if(angular.isUndefined($scope.product.desc)||$scope.product.desc == ''){
                var answer = confirm("فیلد توضیحات خالی است. آیا می خواهید ادامه بدهید؟");
                if (!answer)
                    wants_to_continue = false;
            }

            if(wants_to_continue){
            	var queryData = {
            		product_desc : $scope.product.desc,
            		product_price : $scope.product.price,
            		product_sku : $scope.product.sku,
            		product_title : $scope.product.title,
            	// 	product_picture : {
            	// 		is_new : true,
            	// 		id : ,
            	// 		file : 
            	// 	},
            		store_item_list : $scope.product.items
            	};
                productsServices.addProduct(queryData, $scope.onSubmitSuccess, $scope.onSubmitFailure);
            }
        }
    }

    $scope.onGetMapsFailure = function(){
        $scope.showResult = false;
        $scope.showError = true;
        $scope.errorBoxMessage = errors['SERVER_ERROR'];
    }

    $scope.onGetMapsSuccess = function(response){
        if(response.data.success){
            $scope.showError = false;
            $scope.maps = response.data.original_response;
        }else{
            $scope.showResult = false;
            $scope.showError = true;
            $scope.errorBoxMessage = errors[response.data.error];
        }
    }

    $scope.getMaps = function(){
        $scope.showResult = false;
        $scope.showError = false;
        mapsServices.getMaps($scope.onGetMapsSuccess, $scope.onGetMapsFailure)
    }

    $scope.onGetStoreItemsFailure = function(){
        $scope.showResult = false;
        $scope.showError = true;
        $scope.errorBoxMessage = errors['SERVER_ERROR'];
    }

    $scope.onGetStoreItemsSuccess = function(response){
        if(response.data.success){
            $scope.showError = false;
            $scope.storeItems = response.data.original_response;
        }else{
            $scope.showResult = false;
            $scope.showError = true;
            $scope.errorBoxMessage = errors[response.data.error];
        }
    }

    $scope.getStoreItems = function(){
        productsServices.getStoreItems($scope.onGetStoreItemsSuccess, $scope.onGetStoreItemsFailure)
    }

    $scope.openModal = function(size){
        var modalInstance = $modal.open({
            templateUrl: 'views/modals/add-store-item.html',
            controller: AddStoreItemProduct,
            size: size,
            resolve: {
                data: function () {
                    return {
                        storeItems : $scope.storeItems,
                        maps : $scope.maps
                    };
                }
            }
        });

        modalInstance.result.then(function(data){
            console.log(data)
            if($scope.product.items){
                $scope.items_full_details.push(data.additional);
                $scope.product.items.push(data.data);
            }else{
                $scope.product.items = new Array();
                $scope.items_full_details = new Array();
                $scope.items_full_details.push(data.additional);
                $scope.product.items.push(data.data);
            }
        });
    }

    $scope.removeItem = function(index){
        $scope.product.items.splice(index, 1);
        $scope.items_full_details.splice(index, 1);
    }

    $scope.getMaps();
    $scope.getStoreItems();
}