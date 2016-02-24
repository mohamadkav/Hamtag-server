function AddStoreItemProduct($scope, $modalInstance, data, errors){
	$scope.item = new Object();
	$scope.storeItems = data.storeItems;
  console.log($scope.storeItems)
	$scope.maps = data.maps;
	$scope.choice = 'new';
  $scope.showError = false;
	$scope.choices = [
		{
			value: 'new',
			text: 'تعریف آیتم جدید'
		},
		{
			value: 'old',
			text: 'انتخاب از آیتم های قبلی'
		}
	];

  $scope.validateINputs = function(state){
    if(state === 'old'){
      if(angular.isUndefined($scope.item.storeItem)||$scope.item.storeItem == ''){
        $scope.showResult = false;
        $scope.showError = true;
        $scope.errorBoxMessage = errors['NOTHING_SELECTED'];
        return false;
      }else{
        return true;
      }
    }else{
      if(angular.isUndefined($scope.item.map)||$scope.item.map == ''){
          $scope.showResult = false;
          $scope.showError = true;
          $scope.errorBoxMessage = errors['EMPTY_MAP_FIELD'];
          return false;
      }else if(angular.isUndefined($scope.item.mapzoom_title)||$scope.item.mapzoom_title == ''){
          $scope.showResult = false;
          $scope.showError = true;
          $scope.errorBoxMessage = errors['EMPTY_MAPZOOM_TITLE_FIELD'];
          return false;
      }else if(angular.isUndefined($scope.item.mapzoom_level)||$scope.item.mapzoom_level == ''){
          $scope.showResult = false;
          $scope.showError = true;
          $scope.errorBoxMessage = errors['EMPTY_MAPZOOM_LEVEL_FIELD'];
          return false;
      }else if(angular.isUndefined($scope.item.file_checksum)||$scope.item.file_checksum == ''){
          $scope.showResult = false;
          $scope.showError = true;
          $scope.errorBoxMessage = errors['EMPTY_FILE_CHECKSUM_FIELD'];
          return false;
      }else if(angular.isUndefined($scope.item.file_size)||$scope.item.file_size == ''){
          $scope.showResult = false;
          $scope.showError = true;
          $scope.errorBoxMessage = errors['EMPTY_FILE_SIZE_FIELD'];
          return false;
      }else if(angular.isUndefined($scope.item.file_address)||$scope.item.file_address == ''){
          $scope.showResult = false;
          $scope.showError = true;
          $scope.errorBoxMessage = errors['EMPTY_FILE_ADDRESS_FIELD'];
          return false;
      }else{
        return true;
      }
    }
  }

	$scope.ok = function () {
		var new_item;
    var data = new Object();

		if($scope.choice === 'old'){
      if(!$scope.validateINputs('old'))
        return;
			new_item = {
        item_type: 'map_zoom',
				gid: $scope.item.storeItem.gid,
  		}
      var index;
      for(var i =0 ; i < $scope.storeItems.length ; i++){
        if($scope.item.storeItem.gid === $scope.storeItems[i].gid){
          index = i;
          break;
        }
      }

      console.log($scope.storeItems[index])
      data.additional = {
        map_name: '',
        mapzoom_title: $scope.storeItems[index].title,
        mapzoom_desc: $scope.storeItems[index].description,
        mapzoom_level: $scope.storeItems[index].zoom,
        file_checksum: $scope.storeItems[index].checksum,
        file_size: $scope.storeItems[index].file_size,
        file_address: '-'
      }
      for(var i =0 ; i < $scope.maps.length ; i++){
        if($scope.storeItems[index].map_gid === $scope.maps[i].map_gid){
          data.additional.map_name = $scope.maps[i].map_name;
          break;
        }
      }
		}else{
      if(!$scope.validateINputs('new'))
        return;
			new_item = {
        item_type: 'map_zoom',
				gid: '',
				title: $scope.item.mapzoom_title,
				description: $scope.item.mapzoom_desc,
				map_gid: $scope.item.map.map_gid,
				zoom_level: $scope.item.mapzoom_level,
				file_checksum: $scope.item.file_checksum,
				file_size: $scope.item.file_size,
				file_address: $scope.item.file_address
			}
      data.additional = {
        map_name: $scope.item.map.map_name,
        mapzoom_title: $scope.item.mapzoom_title,
        mapzoom_desc: $scope.item.mapzoom_desc,
        mapzoom_level: $scope.item.mapzoom_level,
        file_checksum: $scope.item.file_checksum,
        file_size: $scope.item.file_size,
        file_address: $scope.item.file_address
      }
		}

    data.data = new_item;
  	$modalInstance.close(data);
	};

	$scope.cancel = function () {
  	$modalInstance.dismiss('cancel');
	};
}