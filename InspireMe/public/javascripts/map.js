google.load('maps', '3', {
    other_params: 'sensor=false'
});

google.setOnLoadCallback(initialize);

console.log("init...");
initialize();
//console.log(data[0].longitude);


function refreshMap() {

    if (markerClusterer) {
        markerClusterer.clearMarkers();
    }

    data.sort(function(a,b) { return parseFloat(a.price) - parseFloat(b.price) } );

    var markers = [];

    // var markerImage = new google.maps.MarkerImage("./images/icon" + priceIndex);
    // new google.maps.Size(24, 32));
    var numIcons = 3;
    for (var i = 0; i < data.length; ++i) {

        var priceIndex = Math.floor(  i / (data.length / numIcons)) + 1;
        //console.log("priceIndex = " + priceIndex);
        //var priceIndex = data[i].price > 200 ? 3 : 1;
        var markerImage = new google.maps.MarkerImage("./images/icon" + priceIndex + ".png");
        var latLng = new google.maps.LatLng(data[i].latitude,
            data[i].longitude)
        console.log('map = ' + map);

        var marker = new google.maps.Marker({
            position: latLng,
            draggable: false,
            icon: markerImage,
            price: data[i].price,
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

            var priceIndex = Math.floor(  lowest.index / (data.length / numStyles)) + 1;

            return {
                text: lowest.price,
                index: priceIndex
            };
        });
}


function initialize() {

    styles = [[{
        url: './images/icon1.png'
    },
        {
            url: './images/icon2.png'
        },
        {
            url: './images/icon3.png'
        }
    ]];

    console.log("map is null");
    markerClusterer = null;

    var center = new google.maps.LatLng(39.91, 116.38);

    map = new google.maps.Map(document.getElementById('map'), {
        zoom: 2,
        center: center,
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
