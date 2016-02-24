function DefineProductCtrl($scope){
	$scope.setCurrentUrl('define-product');
	$scope.menuValue = true;

	$scope.setCurrentState = function(state){
		$scope.currentState = state;
	}

	$scope.getChildPage = function(){
		switch($scope.currentState){
			case 'add':
				return 'views/partials/define-product-new.html';
			case 'view':
				return 'views/partials/define-product-view.html';
		}
	}

	$scope.$watch('menuValue', function(){
		if($scope.menuValue === false){
			$scope.setCurrentState('view');
		}else{
			$scope.setCurrentState('add');
		}
	});

	$scope.setMenuTitle = function(title){
		$scope.menuTitle = title;
	}

	$scope.setCurrentState('add');
}