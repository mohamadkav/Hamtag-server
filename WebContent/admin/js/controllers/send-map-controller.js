function SendMapCtrl ($scope, mapsServices, errors) {
	$scope.setCurrentUrl('send-map');
	$scope.mapInputs = new Object();
	$scope.showResult = false;
	$scope.showError = false;
	$scope.menuValue = true;

	$scope.onAddMapFailure = function(){
		$scope.showError = true;
		$scope.errorMessage = errors['SERVER_ERROR'];
	}

	$scope.onAddMapSuccess = function(response){
		if(response.data.success){
			$scope.showResult = true;
			$scope.showError = false;
		}else{
			$scope.showError = true;
			$scope.errorMessage = errors[response.data.error];
		}
	}

	$scope.addMap = function(){
		$scope.showResult = false;
		$scope.showError = false;

		var queryData = {
			map_name : $scope.mapInputs.mapName,
  			map_code : $scope.mapInputs.mapCode,
  			map_nw : $scope.mapInputs.nw,
		  	map_se : $scope.mapInputs.se,
		  	map_center : $scope.mapInputs.center
		}

		mapsServices.addMap(queryData, $scope.onAddMapSuccess, $scope.onAddMapFailure);
	}

	$scope.onGetMapsFailure = function(){
    }

    $scope.onGetMapsSuccess = function(response){
        if(response.data.success){
            $scope.maps = response.data.original_response;
        }else{
        }
    }

    $scope.getMaps = function(){
        mapsServices.getMaps($scope.onGetMapsSuccess, $scope.onGetMapsFailure)
    }

    $scope.getMaps();

}