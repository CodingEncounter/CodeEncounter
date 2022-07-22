"use strict"

mapboxgl.accessToken =  'pk.eyJ1IjoiYW5hY29kZXVwIiwiYSI6ImNsNXNtc281bTJqMDMzaXJvM3dsYnBvejMifQ.edW3GsZuaC1hr4Jvy_u7XQ';

//get user location
navigator.geolocation.getCurrentPosition(successLocation, errorLocation, {
    enableHighAccuracy: true
});
function successLocation(position){
    console.log(position);
    setupMap([position.coords.longitude, position.coords.latitude]);
}
let longitude = -98.7320;
let latitude = 29.7947
function errorLocation(){
    setupMap([longitude, latitude]);
}

function setupMap(center) {
    let map = new mapboxgl.Map({
        container: 'map',
        style: 'mapbox://styles/mapbox/streets-v11',
        center: center,
        zoom: 14
    });
    // const nav = new mapboxgl.NavigationControl();
    map.addControl(new mapboxgl.NavigationControl())
    // nav.addTo(map);
    var directions = new MapboxDirections({
        accessToken: mapboxgl.accessToken,
        unit: 'metric',
        profile: 'mapbox/cycling'
    });

    setTimeout(function(){
        map.addControl(directions, 'top-left');
        },1000);
//    create a marker on double click

    var marker = new mapboxgl.Marker()

    function add_marker (event) {
        var coordinates = event.lngLat;
        console.log('Lng:', coordinates.lng, 'Lat:', coordinates.lat);
        marker.setLngLat(coordinates).addTo(map);
    }

    map.on('dblclick', add_marker);
}
