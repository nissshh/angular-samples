var app = angular.module('myApp', [ 'ui.grid' ]);
app.controller('GoogleCtrl', [ '$scope', '$http',function($scope,$http) {

	function groupClients(myObject) {
		var clients = [];
		for (var i = 0; i < myObject.length; i++) {
		  var group = myObject[i];
		  addToClientGroup(clients,group);
		}
	}

	function addToClientGroup(clients, client) {
		
	  for (var i = 0; i < clients.length; i++) {
	    if(clients[i].client == client.client){
	    		clients[i].data.push({
	       		 type: client.type,
	      		 count: client.count,
	      		 status: client.status	
	        });
	    		return ;
	    	}
	    }
	    
	  	// add a new data for this client
	      clients.push({
	      	client:client.client,
	        data:[{
	        	type: client.type,
	      		count: client.count,
	      		status: client.status	
	        }]
	      });
	}
	
	$scope.refresh = function() {
		$http.get('http://dtcd28244585db1:8981/notifications').then(function(response) {
			$scope.myData = response.data;
		}, function(response) {
			console.log('Some Error'+response.statusText);
		});

	};
	
	$scope.refresh(); 
	
	$scope.gridOptions = {
			enableSorting: false,
			data : 'myData',
	        columnDefs: [
	                     { name:'Type', field: 'type' },
	                     { name:'Status', field: 'status' },
	                     { name:'Count', field: 'count' }
	                   ]
				
		};
}

]);
app.controller('AppleCtrl', [ '$scope', '$http',function($scope,$http) {

	$scope.refresh = function() {
		$http.get('http://dtcd28244585db1:8982/notifications').then(function(response) {
			$scope.myData = response.data;
		}, function(response) {
			console.log('Some Error'+response.statusText);
		});

	};
	
	$scope.refresh(); 
	
	$scope.gridOptions = {
			enableSorting: false,
			data : 'myData',
	        columnDefs: [
	                     { name:'Type', field: 'type' },
	                     { name:'Status', field: 'status' },
	                     { name:'Count', field: 'count' }
	                   ]
				
		};
}

]);
app.controller('TraveleXCtrl', [ '$scope', '$http',function($scope,$http) {

	$scope.refresh = function() {
		$http.get('http://dtcd28244585db1:8983/notifications').then(function(response) {
			$scope.myData = response.data;
		}, function(response) {
			console.log('Some Error'+response.statusText);
		});

	};
	
	$scope.refresh(); 
	
	$scope.gridOptions = {
			enableSorting: false,
			data : 'myData',
	        columnDefs: [
	                     { name:'Type', field: 'type' },
	                     { name:'Status', field: 'status' },
	                     { name:'Count', field: 'count' }
	                   ]
				
		};
}

]);


app.controller('MainCtrl', [ '$scope', '$http',function($scope,$http) {
	
	$scope.gridOptions1 = {};
	$scope.gridOptions2 = {};
	$scope.gridOptions3 = {};
	
	
	$scope.refresh1 = function() {
		$http.get('http://dtcd28244585db1:8981/notifications').then(function(response) {
			$scope.gridOptions1.data = response.data;
		}, function(response) {
			console.log('Some Error'+response.statusText);
		});

	};
	
	$scope.refresh2 = function() {
		$http.get('http://dtcd28244585db1:8982/notifications').then(function(response) {
			$scope.gridOptions2.data = response.data;
		}, function(response) {
			console.log('Some Error'+response.statusText);
		});

	};
	
	$scope.refresh3 = function() {
		$http.get('http://dtcd28244585db1:8983/notifications').then(function(response) {
			$scope.gridOptions3.data = response.data;
		}, function(response) {
			console.log('Some Error'+response.statusText);
		});

	};
	
	$scope.refresh1();
	$scope.refresh2();
	$scope.refresh3();
}
]);
