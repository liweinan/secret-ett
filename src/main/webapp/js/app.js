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

    $scope.reset = function() {
        $scope.status.text = "";
    };

    $scope.tags = [];
    $http.get('/wildbee/rest/statuses/tags').success(function(data, status, headers, config) {
        $scope.tags = data;
    });

    $scope.update = function(status) {
            $http.post('/wildbee/rest/statuses', status).
            success(function(data, status, headers, config) {
                alert("success!");
            });
    };
});

app.controller('ReleaseAppController', function($scope, $http) {

    $scope.reset = function() {
        $scope.status.text = "";
    };


    $scope.update = function(release) {
            $http.post('/wildbee/rest/releases', release).
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
        var hoho = [];
        Object.keys($scope.haha).forEach(function(key) {
            var a = {"currentStatusId": key, "nextStatusesId": []};

            Object.keys($scope.haha[key]).forEach(function(keya) {
                if ($scope.haha[key][keya]) {
                    a.nextStatusesId.push(keya);
                }
            });
            if (a.nextStatusesId.length != 0) {
                hoho.push(a);
            }
        });
        alert(hoho.toSource());
        $http.put('/wildbee/rest/workflowstatuses/update', hoho).success(function(data, status, headers, config) {
            alert(status);
        });
    };
});

app.controller('PackageAppController', function($scope, $http) {


    $scope.selected_tags = [];

    $scope.toggleSelection = function(tag) {
        var idx = $scope.selected_tags.indexOf(tag);

        // is currently selected
        if (idx > -1) {
            $scope.selected_tags.splice(idx, 1);
        }
        // is newly selected
        else {
            $scope.selected_tags.push(tag);
        }
    };

    $http.get('/wildbee/rest/users').success(function(data, status, headers, config) {
        $scope.users = data;
    });

    $http.get('/wildbee/rest/releases').success(function(data, status, headers, config) {
        $scope.releases = data;
    });

    $http.get('/wildbee/rest/statuses').success(function(data, status, headers, config) {
        $scope.statuses = data;
    });

    $http.get('/wildbee/rest/packagetags').success(function(data, status, headers, config) {
        $scope.tags = data;
    });

    $scope.update = function(package) {
            package.tags = $scope.selected_tags;
            $http.post('/wildbee/rest/packages', package).
            success(function(data, status, headers, config) {
                alert("success!");
            });
    };
});

app.controller('PackageTagAppController', function($scope, $http) {

    $scope.reset = function() {
        $scope.status.text = "";
    };


    $scope.update = function(tag) {
            $http.post('/wildbee/rest/packagetags', tag).
            success(function(data, status, headers, config) {
                alert("success!");
            });
    };
});