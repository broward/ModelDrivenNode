var app = angular.module('myApp', ['ngGrid']);
app.controller('CollectionCtrl', function($scope, $http) {
  
    $scope.url = {
      allSchemas: 'Mdn/AppA/v1/Schemas',
      topSchemas: 'Mdn/AppA/v1/TopSchemas',
      currentSchemas: 'Mdn/AppA/v1/TopSchemas'
    };
    $scope.filterOptions = {
        filterText: "",
        useExternalFilter: true
    }; 
    $scope.totalServerItems = 0;
    $scope.pagingOptions = {
        pageSizes: [250, 500, 1152],
        pageSize: 250,
        currentPage: 1
    };	
    $scope.setPagingData = function(data, page, pageSize){	
        var pagedData = data.slice((page - 1) * pageSize, page * pageSize);
        $scope.myData = pagedData;
        $scope.totalServerItems = data.length;
        if (!$scope.$$phase) {
            $scope.$apply();
        }
    };
    $scope.getPagedDataAsync = function (pageSize, page, searchText) {

        setTimeout(function () {
            var data;
            if (searchText) {
                var ft = searchText.toLowerCase();
                $http.get($scope.url.currentSchemas).success(function (largeLoad) {		
                    data = largeLoad.filter(function(item) {
                        return JSON.stringify(item).toLowerCase().indexOf(ft) != -1;
                    });
                    $scope.setPagingData(data,page,pageSize);
                });            
            } else {
                $http.get($scope.url.currentSchemas).success(function (largeLoad) {
                    $scope.setPagingData(largeLoad,page,pageSize);
                });
            }
        }, 100);

    };
	
    $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage);
	
    $scope.$watch('pagingOptions', function (newVal, oldVal) {
        if (newVal !== oldVal && newVal.currentPage !== oldVal.currentPage) {
          $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage, $scope.filterOptions.filterText);
        }
    }, true);
    $scope.$watch('filterOptions', function (newVal, oldVal) {
        if (newVal !== oldVal) {
          $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage, $scope.filterOptions.filterText);
        }
    }, true);

    $scope.gridOptions = {
        data: 'myData',
        columnDefs: [{ field: 'Collections', displayName: 'MongoDB Collections'}],
        enablePaging: true,
		showFooter: true,
        totalServerItems: 'totalServerItems',
        multiSelect: false,
        pagingOptions: $scope.pagingOptions,
        filterOptions: $scope.filterOptions,
        afterSelectionChange: function (theRow, evt) {                
          $scope.collection.url = "/Mdn/AppA/v1/Schemas/" + theRow.entity.Collections; 
          $scope.getCollectionName(); 
        }
    };

    $scope.showAllSchemas = function() {           
    	if ($scope.url.currentSchemas == $scope.url.topSchemas) {
       $scope.url.currentSchemas = $scope.url.allSchemas;
       document.getElementById('myButton').textContent = "Show Main Schemas";
     } else {
       $scope.url.currentSchemas = $scope.url.topSchemas;
       document.getElementById('myButton').textContent = "Show All Schemas (many lookup tables)";
     }
     
     $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage, $scope.filterOptions.filterText);
    }

});

app.controller('RecordCtrl', function($scope, $http) {

    $scope.collection = {
        url: ''
    };
    $scope.filterOptions = {
        filterText: "",
        useExternalFilter: true
    }; 
    $scope.recordServerItems = 0;
    $scope.pagingOptions = {
        pageSizes: [100, 200],
        pageSize: 100,
        currentPage: 1    
    };	
    $scope.setPagingData = function(data, page, pageSize){	
        var pagedData = data.slice((page - 1) * pageSize, page * pageSize);
        $scope.recordData = pagedData;           
        $scope.totalServerItems = data.length;
        if (!$scope.$$phase) {
            $scope.$apply();
        }
    };
    $scope.getPagedDataAsync = function (pageSize, page, searchText) {

        setTimeout(function () {
            var data;
            if (searchText) {
                var ft = searchText.toLowerCase();
                $http.get($scope.collection.url).success(function (largeLoad) {		
                    data = largeLoad.filter(function(item) {
                        return JSON.stringify(item).toLowerCase().indexOf(ft) != -1;
                    });
                    $scope.setPagingData(data,page,pageSize);
                });            
            } else {
                $http.get($scope.collection.url).success(function (largeLoad) {
                    $scope.setPagingData(largeLoad,page,pageSize);
                });
            }
        }, 100);
    };
	
    $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage);
	
    $scope.$watch('pagingOptions', function (newVal, oldVal) {
        if (newVal !== oldVal && newVal.currentPage !== oldVal.currentPage) {
          $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage, $scope.filterOptions.filterText);
        }
    }, true);
    $scope.$watch('filterOptions', function (newVal, oldVal) {
        if (newVal !== oldVal) {
          $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage, $scope.filterOptions.filterText);
        }
    }, true);
	
    $scope.recordOptions = {
        data: 'recordData',
        columnDefs: [{ field: 'Records', displayName: 'Collection Records'}],
        enablePaging: true,
		showFooter: true,
        totalServerItems: 'data.length',
        multiSelect: false,
        pagingOptions: $scope.pagingOptions,
        filterOptions: $scope.filterOptions,
        afterSelectionChange: function (theRow, evt) {                
           $http.get($scope.collection.url + "/" + theRow.entity.Records).success(function (myRecord) {
             document.getElementById('myTextarea').value = JSON.stringify(myRecord, null, 2);
          });
        }
    };

    $scope.getCollectionName = function() {           
        $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage, $scope.filterOptions.filterText);
    }
});

app.controller('ApiCtrl', function($scope, $http) {
    
    $scope.filterOptions = {
        filterText: "",
        useExternalFilter: true
    }; 
    $scope.totalServerItems = 0;
    $scope.pagingOptions = {
        pageSizes: [100, 200],
        pageSize: 100,
        currentPage: 1
    };	
    $scope.setPagingData = function(data, page, pageSize){	
        var pagedData = data.slice((page - 1) * pageSize, page * pageSize);
        $scope.apiData = pagedData;
        $scope.totalServerItems = data.length;
        if (!$scope.$$phase) {
            $scope.$apply();
        }
    };
    $scope.getPagedDataAsync = function (pageSize, page, searchText) {

        setTimeout(function () {
            var data;
            if (searchText) {
                var ft = searchText.toLowerCase();
                $http.get('/Mdn/AppA/v1/Api').success(function (largeLoad) {		
                    data = largeLoad.filter(function(item) {
                        return JSON.stringify(item).toLowerCase().indexOf(ft) != -1;
                    });
                    $scope.setPagingData(data,page,pageSize);
                });            
            } else {
                $http.get('/Mdn/AppA/v1/Api').success(function (largeLoad) {
                    $scope.setPagingData(largeLoad,page,pageSize);
                });
            }
        }, 100);
    };
	
    $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage);
	
    $scope.$watch('pagingOptions', function (newVal, oldVal) {
        if (newVal !== oldVal && newVal.currentPage !== oldVal.currentPage) {
          $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage, $scope.filterOptions.filterText);
        }
    }, true);
    $scope.$watch('filterOptions', function (newVal, oldVal) {
        if (newVal !== oldVal) {
          $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage, $scope.filterOptions.filterText);
        }
    }, true);
	
    $scope.apiOptions = {
        data: 'apiData',
        columnDefs: [{ field: 'URL', displayName: 'REST API via Introspection'}],
        enablePaging: true,
		showFooter: true,
        totalServerItems: 'totalServerItems',
        multiSelect: false,
        pagingOptions: $scope.pagingOptions,
        filterOptions: $scope.filterOptions,
        afterSelectionChange: function (theRow, evt) {                
          
        }
    };
});
