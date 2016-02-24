function SendNotificationsCtrl ($scope , notificationServices , errors) {
	$scope.setCurrentUrl('send-notifications');
	$scope.notValidUrl = false;
  	$scope.dateOptions = {
    	formatYear: 'yy',
    	startingDay: 1
  	}
  	$scope.format = 'dd-MMMM-yyyy';

	$scope.isValidUrl = function(url)
	{
		if(url){
		    if(!url.match(/^(ht|f)tps?:\/\/[a-z0-9-\.]+\.[a-z]{2,4}\/?([^\s<>\#%"\,\{\}\\|\\\^\[\]`]+)?$/))
		    	$scope.notValidUrl = true;
		    else
		    	$scope.notValidUrl = false;
		}else{
			$scope.notValidUrl = true;
		}
	}

	$scope.refresh = function(){
		$scope.notValidUrl = false;
		$scope.typedUrl = '';
		$scope.typedDate = '';
		$scope.selectedType = '';
	}


	/******** Date Picker Methods ***********/

	$scope.today = function() {
	    $scope.typedDate = new Date();
	}

	$scope.clear = function () {
    	$scope.typedDate = null;
  	}

  	// Disable weekend selection
  	$scope.disabled = function(date, mode) {
    	return ( mode === 'day' && ( date.getDay() === 0 || date.getDay() === 6 ) );
  	}

  	$scope.open = function($event) {
	    $event.preventDefault();
	    $event.stopPropagation();
	    $scope.opened = true;
  	}

  	$scope.opn = function($event) {
	    $event.preventDefault();
	    $event.stopPropagation();
	    $scope.opnd = true;
  	}


  	$scope.toggleMin = function() {
	    $scope.minDate = $scope.minDate ? null : new Date();
	};

	$scope.toggleMin();
	$scope.dt = new Date();

	/******************************************/

	$scope.onGetBrandsSuccess = function(response){
		if(response.data.success){
			if(response.data.original_response.distinct_names)
				$scope.brands = response.data.original_response.distinct_names;
		}
	}

	$scope.getBrands = function(){
		var queryData = {
			table_name : 'devices',
			column_name : 'device_brand'
		}
		notificationServices.getColumns(queryData,$scope.onGetBrandsSuccess);
	}

	$scope.onGetOSNamesSuccess = function(response){
		if(response.data.success){
			if(response.data.original_response.distinct_names)
				$scope.OSs = response.data.original_response.distinct_names;
		}
	}

	$scope.getOSNames = function(){
		var queryData = {
			table_name : 'operating_systems',
			column_name : 'name'
		}
		notificationServices.getColumns(queryData,$scope.onGetOSNamesSuccess);
	}

	$scope.onGetOSVersionsByNamesSuccess = function(response){
		if(response.data.success){
			if(response.data.original_response.distinct_names)
				$scope.selectedOsVersions = response.data.original_response.distinct_names;
		}
	}

	$scope.getOSVersionsByNames = function(){
		var queryData = {
			os_names : $scope.selectedOSs
		}
		notificationServices.getVersionsByOSNames(queryData,$scope.onGetOSVersionsByNamesSuccess);
	}

	$scope.onGetSoftwareVersionsSuccess = function(response){
		if(response.data.success){
			if(response.data.original_response.distinct_names)
				$scope.softwareVersions = response.data.original_response.distinct_names;
		}
	}

	$scope.getSoftwareVersions = function(){
		var queryData = {
			table_name : 'softwares',
			column_name : 'software_version'
		}
		notificationServices.getColumns(queryData,$scope.onGetSoftwareVersionsSuccess);
	}

	$scope.onGetModelsSuccess = function(response){
		if(response.data.success){
			if(response.data.original_response.models)
				$scope.models = response.data.original_response.models;
		}
	}

	$scope.getModels = function(){
		var queryData = {
			brands : $scope.selectedBrands
		}
		notificationServices.getModelsByBrands(queryData,$scope.onGetModelsSuccess);
	}
	
	$scope.onGenrateNotificationSuccess = function(response){
		if(response.data.success){
			$scope.numberOfGeneratedNotifications = response.data.original_response.devices_found;
			$scope.showResultCount = true;
			$scope.error = null;
		}else{
			$scope.showResultCount = false;
			$scope.error = errors[response.data.error];
		}
	}

	$scope.genrateNotification = function(){
		//if($scope.selectedNotificationType === "ad_type" ){ // || ! $scope.notValidUrl ){
			if($scope.dt){
				$scope.selectedDataIsValid = true;
			}else{
				$scope.error = errors["EMPTY_TRIGGER_TIME"];
				return;
			}
			var date;
			if($scope.typedDate)
				date = $scope.typedDate.getTime();
			var query = {
				brands : $scope.selectedBrands,
				models : $scope.selectedModels,
				os_names : $scope.selectedOSs,
				os_versions : $scope.selectedOsVersions,
				software_versions : $scope.selectedSoftwareVersions,
				notification_type : $scope.selectedNotificationType,
				content : $scope.typedText,
				url : $scope.typedUrl,
				software_expiration_date : date,
				type : $scope.selectedType,
				notification_trigger_time : $scope.dt.getTime()
			}
			notificationServices.genrateNotification(query,$scope.onGenrateNotificationSuccess);
		/*}else{
			$scope.selectedDataIsValid = false;
			$scope.error = errors["INVALID_URL"];
		}*/
	}

	$scope.$watch('selectedBrands',function(newValue, oldValue){
		if(newValue != oldValue)
			$scope.getModels();
	},true);

	$scope.$watch('selectedOSs',function(newValue, oldValue){
		if(newValue != oldValue)
			$scope.getOSVersionsByNames();
	},true);

	$scope.getBrands();
	$scope.getSoftwareVersions();
	$scope.getOSNames();
}