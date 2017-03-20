"use strict";

/**
 * response: data – {string|Object} – The response body transformed with the
 * transform functions. status – {number} – HTTP status code of the response.
 * headers – {function([headerName])} – Header getter function. config –
 * {Object} – The configuration object that was used to generate the request.
 * statusText – {string} – HTTP status text of the response.
 */
app.service('contactsService', [ "$http", "$q", function($http, $q) {
	var apiUrl = '/contact';
	var noServerResponseMessage = "No hay respuesta del servidor. Verifique su conexi[on a internet y consulte con el administrador si el servicio esta disponible";

	this.getContacts = function() {
		return $http.get(apiUrl, {}).then(function successCallback(response) {
			return response.data;
		}, function errorCallback(response) {
			if(response==null || response.data==null){
				response = noServerResponseMessage;
			}
			console.log("Error en GET a " + apiUrl);
			console.log(JSON.stringify(response));
			return $q.reject(response);
		});
	}
	
	this.saveContact = function(contact) {
		return $http.post(apiUrl, contact, {}).then(
		
		function successCallback(response) {
			return response.data;
		}, 
		
		function errorCallback(response) {
			if(response==null || response.data==null){
				response = noServerResponseMessage;
			}
			
			console.log("Error en POST a " + apiUrl + " con datos: " + JSON.stringify(contact));
			console.log(JSON.stringify(response));
			return $q.reject(response);
		});
	}
	
	this.deleteContact = function(contact){
		return $http.delete(apiUrl + "/" + contact.id, {}).then(
				
		function successCallback(response) {
			return response.data;
		}, 
		
		function errorCallback(response) {
			if(response==null || response.data==null){
				response = noServerResponseMessage;
			}
			
			console.log("Error en DELETE a " + apiUrl + " con datos: " + JSON.stringify(contact));
			console.log(JSON.stringify(response));
			return $q.reject(response);
		});
	}
	
}]);