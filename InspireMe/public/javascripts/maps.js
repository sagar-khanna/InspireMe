
google.load('maps', '3', {
    other_params: 'sensor=false'
});

google.setOnLoadCallback(initialize);

//console.log(foundData[0].lang);

var markerClusterer = null;
var map = null;
console.log("map is null");
var imageUrl = './images/icon3.png';

function refreshMap(foundData) {
    console.log("found foundData " + foundData);

    if (foundData == undefined) {
        foundData = [];
    } else {
        foundData = $.parseJSON(String(foundData));
    }
    if (markerClusterer) {
        markerClusterer.clearMarkers();
    }

    console.log(foundData[0]);

    for (var key in foundData) {
        console.log(key);
    }


    foundData.sort(function(a,b) { return parseFloat(a.price) - parseFloat(b.price) } );

    var markers = [];

    // var markerImage = new google.maps.MarkerImage("./images/icon" + priceIndex);
    // new google.maps.Size(24, 32));
    var numIcons = 3;
    for (var i = 0; i < foundData.length; ++i) {

        var priceIndex = Math.floor(  i / (foundData.length / numIcons)) + 1;
        //console.log("priceIndex = " + priceIndex);
        //var priceIndex = foundData[i].price > 200 ? 3 : 1;
        var markerImage = images[priceIndex - 1];//new google.maps.MarkerImage("./images/icon" + priceIndex + ".png");
        console.log("markerImage = " + markerImage + " index = " + priceIndex);
        var latLng = new google.maps.LatLng(foundData[i].lat,
            foundData[i].lang)
        console.log('map = ' + map);

        var marker = new google.maps.Marker({
            position: latLng,
            draggable: false,
            icon: markerImage,
            price: foundData[i].price,
            index: i
        });

        markers.push(marker);
        //  console.log("pushed " + latLng +"with image " + imageUrl);
    }

    markerClusterer = new MarkerClusterer(map, markers, {
        maxZoom: 7,
        gridSize: 40,
        styles: styles[null]
    });

    markerClusterer.setCalculator(
        function(markers, numStyles) {
            //var count = markers.length;
            //var index = Math.min(count, numStyles) - 1;

            var lowest = {"price":Number.POSITIVE_INFINITY};
            var tmp;
            for (var i=markers.length-1; i>=0; i--) {
                tmp = markers[i];
                if (tmp.price < lowest.price) lowest = tmp;
            }

            var priceIndex = Math.floor(  lowest.index / (foundData.length / numStyles));

            return {
                text: lowest.price,
                index: priceIndex
            };
        });
}


function initialize() {
    map = new google.maps.Map(document.getElementById('map'), {
        zoom: 2,
        center: new google.maps.LatLng(39.91, 116.38),
        mapTypeId: google.maps.MapTypeId.ROADMAP
    });

    console.log("map is initialized");

    //var refresh = document.getElementById('refresh');
    // google.maps.event.addDomListener(refresh, 'click', refreshMap);

    //  var clear = document.getElementById('clear');
    //   google.maps.event.addDomListener(clear, 'click', clearClusters);

    refreshMap();
}

function clearClusters(e) {
    e.preventDefault();
    e.stopPropagation();
    markerClusterer.clearMarkers();
}

