"use strict";

app.directive('pager', function(){
	return {
		restrict: 'E',
		replace: true,
		templateUrl: 'app/components/contacts/pager.template.html',
		scope: {
			itemList: '=',
			sortBy: '=',
			textFilter: '&',
			filterBy: '&',
			onItemClicked: '&'
		},
		link: function(scope, element, attrs){
			element.on('select.uk.pagination', function(e, pageIndex, pagination){
			    //alert('You have selected page: ' + (pageIndex+1));
				var size = 20;
				var start = (pageIndex-1) * size;
				var end = start + size -1;
			    scope.itemList = scope.itemList.slice(start, end);
			});
		}
	};
});

app.filter('lf', function() {
	return function(list, txt, filterBy) {
		var filtered = [];

		if (list && txt) {
			var filters = filterBy;

			for (var i = 0; i < list.length; i++) {
				var matchString = txt.toLowerCase();
				var item = list[i];
				for ( var key in item) {
					if (filters.indexOf(key) != -1
							&& item[key]
							&& (typeof item[key] === 'string')
							&& item[key].toLowerCase().indexOf(matchString) != -1) 
					{
						if(filtered.indexOf(item)==-1){ // Evita duplicados por haber ya encontrado coincidencia en otro campo
							filtered.push(item);
						}
					}
				}
			}
		} else {
			return list;
		}

		return filtered;
	}

});