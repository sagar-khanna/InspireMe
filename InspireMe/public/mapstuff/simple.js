// google.setOnLoadCallback(initialize);

//console.log(data[0].longitude);

var map;
function initialize() {
    console.log("initializing map");
    google.load('maps', '3', {
    other_params: 'sensor=false'
    });

console.log("got map");

var mapOptions = {
    zoom: 8,
    center: new google.maps.LatLng(-34.397, 150.644)
    };
    console.log
map = new google.maps.Map(document.getElementById('map'),
mapOptions);
console.log(" map initialized");
}


google.maps.event.addDomListener(window, 'load', initialize);