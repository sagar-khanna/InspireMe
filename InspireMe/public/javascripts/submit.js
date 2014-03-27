jQuery(document).ready(function() {
    initialize();

    $('button#submitButton').click( function() {
        var formVals =  $('form#inspireMeSearch').serializeArray();
        var str = "";
        $.each( formVals, function( key, value ) {
            str += "/" + value.value;
        });
        var someData = $.get( '/search/holidays' + str, function( data ) {
            refreshMap(data)
        });
        /*for (var key in someData) {
            console.log(key);
        }
        console.log("SomeData = " + someData);
        refreshMap(someData)
        */
    });
});