

app.config(function($stateProvider, $urlRouterProvider) {
$urlRouterProvider.when("", "/home");
var header = {
       templateUrl: "app/partials/header.html"
    ,
       controller: function($scope) {}
  
  }
     var footer = {
       templateUrl: "app/partials/footer.html",
       controller: function($scope) {}
  
  }

$stateProvider
.state('home', {
    url: "/home",
    views: {
    header: header,
     content: {
        templateUrl: "app/components/homesection/homesection.html"
      },
    footer : footer
    },
  })

.state('whyus', {
    url: "/whyus",
    views: {
       header: header,
     content: {
        templateUrl: "app/components/whyusSection/whyus.html"
      
     },
    footer : footer
    },
  })

});