var getRoutes = function(){
	return [
		{
			url : "/send-notifications" , 
			templateUrl : 'views/partials/send-notifications.html' ,
			controller : 'SendNotificationsCtrl' 
		},
		{
			url : "/send-map" , 
			templateUrl : 'views/partials/send-map.html' ,
			controller : 'SendMapCtrl' 
		},
		{
			url : "/define-product" , 
			templateUrl : 'views/partials/define-product.html' ,
			controller : 'DefineProductCtrl' 
		}
	];
} 

mainApp.constant("routes" , getRoutes());
