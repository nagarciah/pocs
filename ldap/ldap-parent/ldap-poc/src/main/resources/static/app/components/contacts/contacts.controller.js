'use strict';

app.controller('ContactsController', function($scope, $timeout, contactsService) {
	var contactBackup = {};
	
	$scope.itemList = [];
	$scope.editMode = false;
	$scope.actionResult = {
		message: null,
		type: 'info' //info, warn, error
	};
	
	$scope.refreshList = function(){
		contactsService.getContacts()
			.then(function(result){
				$scope.itemList = result;
				if ($scope.itemList && $scope.itemList.length > 0) {
					$scope.setSelected($scope.itemList[0]);
				}
			}, function(error){
				handleError('Error consultando la lista de contactos', error);
			});
	};

	$scope.setSelected = function(item) {
		if($scope.editMode && selectedContactHasChanged()){
			if(confirm("Perder cambios?")){
				angular.copy(contactBackup, $scope.selectedContact);
			}else{
				return;
			}
		}
		$scope.selectedContact = item;
		contactBackup = angular.copy($scope.selectedContact);
	}

	$scope.listFilter = function(item) {
		if (!$scope.query) {
			return true;
		} else {
			var matchString = $scope.query.toLowerCase();
			if (item.name.toLowerCase().indexOf(matchString) != -1
					|| item.role.toLowerCase().indexOf(matchString) != -1
					|| item.email.toLowerCase().indexOf(matchString) != -1) {
				return true;
			}
		}

		return false;
	}
	
	$scope.cancelEdit = function(){
		if(selectedContactHasChanged()){
			if(!confirm("Perder cambios?")){
				return
			}else{
				angular.copy(contactBackup, $scope.selectedContact);
			}
		}
		
		if($scope.selectedContact.id == null && $scope.itemList && $scope.itemList.length>0){
			$scope.setSelected($scope.itemList[0]);
		}
			
		$scope.editMode = false;
	}
	
	$scope.saveContact = function(){
		if( selectedContactHasChanged() ){
			contactsService.saveContact( $scope.selectedContact )
				.then(function(result){
					$scope.editMode = false;
					$scope.refreshList();
					showMessage("Guardado");
				}, function(error){
					handleError('Error guardando contacto', error);
				});
		}else{
			$scope.editMode = false; // No hay cambios, no guarda nada
		}
	}
	
	$scope.removeContact = function(){
		UIkit.modal.confirm("Eliminará este contacto. Desea continuar?", function(){
			contactsService.deleteContact( $scope.selectedContact )
			.then(function(result){
				$scope.refreshList();
				showMessage("El contacto ha sido eliminado");
			}, function(error){
				handleError('Error eliminando contacto', error);
			});		    
		});
	}
	
	$scope.addContact = function(){
		$scope.setSelected({});
	}
	
	var selectedContactHasChanged = function(){
		var copyOfSelectedContact = angular.copy($scope.selectedContact);
		return !_.isEqual(contactBackup, copyOfSelectedContact);
	}
	
	var handleError = function(message, error){
		console.log(message);
		console.log(JSON.stringify(error));
		UIkit.modal.alert(message); // TODO cambiar por modal con apariencia de error
	}
	
	var showMessage = function(text){
		$scope.actionResult.type = "info";
		$scope.actionResult.message = text;
		$timeout(function () {
			$scope.actionResult.message = null;
	    }, 3000);
		//UIkit.modal.alert(text);
	}


	$scope.refreshList();
});