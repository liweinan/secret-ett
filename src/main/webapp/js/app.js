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

app.controller('StatusAppController', function($scope, $http) {
    $scope.status = {};
    $scope.status.name = "";
    $scope.reset = function() {
        $scope.status.text = "";
    };

    $scope.update = function() {
            $http.post('/wildbee/rest/statuses', $scope.status).
            success(function(data, status, headers, config) {
                alert("success!");
            });
    };
});

app.controller('WorkflowAppController', function($scope, $http) {
    $scope.statuses = [];
    $scope.haha = {};
    $http.get('/wildbee/rest/statuses').success(function(data, status, headers, config) {
        $scope.statuses = data;
    });
    $scope.update = function() {
        alert($scope.haha['Open']['Open']);
    };
});