var app = angular.module("app", []);

// Singleton para almacenar las credenciales
app.factory('oauth', function () {
    return {details: null, name: null, token: null};
});

app.run(['$http', '$rootScope', 'oauth', function ($http, $rootScope, oauth) {

    $http.get("/user").success(function (data) {

        oauth.details = data.userAuthentication.details;
        oauth.name = oauth.details.name;
        oauth.token = data.details.tokenValue;

        // Guarda token para invocaciones posteriores. El primer llamado sin token debe fallar
        $http.defaults.headers.common['Authorization'] = 'bearer ' + oauth.token;

        // Publica evento de autenticacion a los interesados
        $rootScope.$broadcast('auth-event', oauth.token);
    })
    .error(function (e) {
    	 console.log('error en run!: ' + JSON.stringify(e));
    	 console.dir(e);
    });;
}]);

app.controller("home", function ($http, $rootScope, oauth) {

    var self = this;

    self.authenticated = false;

    // <4>
    $rootScope.$on('auth-event', function (evt, ctx) {
        self.user = oauth.details.name;
        self.token = oauth.token;
        self.authenticated = true;

        var name = window.prompt('A quien desea saludar?');

        // Invocacion sin CORS, solo debe funcionar para localhost
        $http.get('http://localhost:8082' + '/greet/' + name)
            .success(function (greetingData) {
                self.greetingFromLocahost = greetingData.greeting;
            })
            .error(function (e) {
                console.log('oops!' + JSON.stringify(e));
            });

        // Invocacion usando ruta Zuul
        $http.get('/greetings-service/greet/' + name)
            .success(function (greetingData) {
                self.greetingFromZuulRoute = greetingData.greeting;
            })
            .error(function (e) {
                console.log('oops!' + JSON.stringify(e));
            });

        /*
        // Invocacion usando cliente Feign
        $http.get('/lets/greet/' + name)
            .success(function (greetingData) {
                self.greetingFromEdgeService = greetingData.greeting;
            })
            .error(function (e) {
                console.log('oops!' + JSON.stringify(e));
            });
        */
    });
});
