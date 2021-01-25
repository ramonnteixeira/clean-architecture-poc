var initialHeight = $(window).height();
var fullsize = function($window) {
	return function(scope, element, attrs){
		var reservate = attrs.reservate;
		if (!reservate) {
			reservate = 0;
		}

	    scope.onResize = function(height) {
		    $(element[0]).height(height - reservate);
        }
        
        scope.onResize(initialHeight);

        angular.element($window).bind('resize', function() {
            scope.onResize($(window).height()); 
        })
	}
}

var scroll = function() {
	return function(scope, element, attrs){
	    $(element[0]).mCustomScrollbar({theme:"dark"});    
	}
}

var textAreaAutoSize = function() {
	return function(scope, element, attrs){
	    $(element[0]).textareaAutoSize();    
	}
}

var modalDialog = function() {
  return {
    restrict: 'E',
    scope: { show: '='},
    replace: true,
    transclude: true,
    link: function(scope, element, attrs) {
      scope.dialogStyle = {};
      if (attrs.width)
        scope.dialogStyle.width = attrs.width;
      if (attrs.height)
        scope.dialogStyle.height = attrs.height;
      scope.hideModal = function() {
        scope.show = false;
      };
    },
    template: '<div class="ng-modal" ng-show="show"><div class="ng-modal-overlay" ng-click="hideModal()"></div><div class="ng-modal-dialog" ng-style="dialogStyle"><div class="ng-modal-close" ng-click="hideModal()">X</div><div class="ng-modal-dialog-content" ng-transclude></div></div></div>'
  };
};

app.directive("textAreaAuto", textAreaAutoSize);
app.directive("scroll", scroll);
app.directive("fullsize", ['$window', fullsize]);
app.directive('modalDialog', modalDialog);
