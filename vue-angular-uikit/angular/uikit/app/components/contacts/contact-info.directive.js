app.directive('contactInfo', function(){
	return {
		restrict: 'E',
		replace: true,
		templateUrl: 'app/components/contacts/contact-info.template.html',
		scope: {
			contact: '=',
			onContactClicked: '&'
		}
	};
});