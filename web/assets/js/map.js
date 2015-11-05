function createMarker(position, map) {
    var marker = L.marker([position.lat, position.lng]).addTo(map);
    var message = "<p><strong>Taxi ID: </strong>"+ position.id +"</p>";
    message += "<p><strong>Time: </strong>"+ position.observ +"</p>";
    marker.bindPopup(message).openPopup();
    return marker;
}

$(function() {

    var map = L.map('my-map').setView([40.02133 , 116.75389], 9);

    var osmUrl='http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png';
    var osmAttrib='Map data © <a href="http://openstreetmap.org">OpenStreetMap</a> contributors';
    var osm = new L.TileLayer(osmUrl, {minZoom: 8, maxZoom: 20, attribution: osmAttrib});
    map.addLayer(osm);


    var myMarkers = [];
    $.get($.url("/taxis/randomtraj"), function (positions) {
        $.each(positions, function (idx, position) {
            myMarkers.concat(createMarker(position, map));
        });
    });
});