app.controller('KanbanCtrl', function ($scope, $http) {
	$scope.api = '/api';
	
	$scope.steps = [];
	$scope.priorities = [
		{'id' : 'yellow', 'value' : 'Baixa'},
		{'id' : 'red', 'value' : 'Alta'}
	]

	$scope.update_kanban=function() {
		$http.get("/api/steps")
		    .success(function(data) { 
		        $scope.steps = data;
			});
	}

//	$scope.socket = io.connect('http://' + document.domain + ':' + location.port + '/update_steps');
//	$scope.socket.on('update_all_clients', function() {
//		$scope.update_kanban();
//	});

	$scope.call_socket=function(){
		$scope.update_kanban();
//		$scope.socket.emit('broadcast update');
	}

	$scope.call_socket();

	$scope.onDropStep=function(data,evt,step){
		data.stepId = step.id;
		$scope.updateTask(data);
	}
	
	$scope.removeTask = function(task) {
		$http.delete($scope.api + '/tasks/' + task.id)
			.success(function(data) {
	            $scope.call_socket();
	    	}).error(function(error) {
	            console.log(error);
	    	});
	}
	
	$scope.saveTask = function(task) {
	    if (task.id) {
	        return $scope.updateTask(task);
	    }

		$http.post($scope.api + '/tasks', task)
			.success(function() {
	            $scope.call_socket();
	            $scope.modalShown = false;
	    	});
	}

	$scope.updateTask = function(task) {
		$http.put($scope.api + '/tasks/' + task.id, task)
			.success(function() {
	            $scope.call_socket();
	            $scope.modalShown = false;
	    	});
	}

	$scope.modalShown = false;
	$scope.addTask = function(step_id) {
		$scope.modalShown = !$scope.modalShown;
		$scope.domain = {};
		$scope.domain.stepId = step_id;
		$scope.domain.id=undefined;
		$scope.domain.color='yellow';
	};
	
	$scope.editTask = function(task) {
		$scope.domain = angular.copy(task);
		$scope.modalShown = true;
	}
});
