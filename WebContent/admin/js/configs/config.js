/*
* This file include server configuration for service calls
* protocol : http protocol used to connect server
* host : server IP
* port : server port
* resourceAddress : server serviceses base package . always should start and end with /
*/

mainApp.constant('baseRequestConfig', {
	'protocol' : 'http',
	'host' : 'localhost' ,
	'port' : '8080' , 
	'basePackage' : 'LBS/api'
});