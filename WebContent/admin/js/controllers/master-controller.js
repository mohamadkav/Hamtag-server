function MasterCtrl ($scope, $location) {
	
	$scope.currentUrl;


	$scope.returnActiveClass = function(url){
		return $scope.currentUrl === url ? 'active' : '';
	}

	$scope.setCurrentUrl = function(url){
		$scope.currentUrl = url;
	}
}