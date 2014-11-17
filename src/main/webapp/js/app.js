var app = angular.module('UserApp', []);

app.controller('UserAppController', function($scope, $http) {
    $scope.user = {};
    $scope.user.name = "";
    $scope.user.email = "";
    $scope.users = function() {
        $http.get('/wildbee/rest/users')
    };
    $scope.reset = function() {
        $scope.user.name = "";
        $scope.user.email = "";
    };

    $scope.update = function() {
        console.log($scope.user.name);
        console.log($scope.user.email);
        $http.post('/wildbee/rest/users', $scope.user).
            success(function(data, status, headers, config) {
                alert("success!");
            });
    };
});
