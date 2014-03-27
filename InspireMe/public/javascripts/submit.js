jQuery(document).ready(function() {
    initialize();

    $('button#submitButton').click( function() {
        var formVals =  $('form#inspireMeSearch').serializeArray();
        var str = "";
        $.each( formVals, function( key, value ) {
            str += "/" + value.value;
        });
        $.get( '/search/holidays' + str);
    });
});