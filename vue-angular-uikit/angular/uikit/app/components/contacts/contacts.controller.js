'use strict';

app.controller('ContactsController', function($scope, contactsService) {
	$scope.message = 'Nombre usuario';
	/* $scope.itemFilter = {}; */
	$scope.itemList = contactsService.getContacts();

	if ($scope.itemList && $scope.itemList.length > 0) {
		$scope.selectedContact = $scope.itemList[0];
	}

	$scope.setSelected = function(item) {
		$scope.selectedContact = item;
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
});