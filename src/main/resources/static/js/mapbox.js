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

// ADD A MAP
function setupMap(center) {
    let map = new mapboxgl.Map({
        container: 'map',
        style: 'mapbox://styles/mapbox/streets-v11',
        center: center,
        zoom: 14
    });
    // function onMapLoaded(event) {
    //
    //     event.map.resize();
    //
    // }

    //Search bar
    const geocoder = new MapboxGeocoder({
        // Initialize the geocoder
        accessToken: mapboxgl.accessToken, // Set the access token
        mapboxgl: mapboxgl, // Set the mapbox-gl instance
        marker: false, // Do not use the default marker style
        placeholder: '   Search for places', // Placeholder text for the search bar
    });
    function geocode(search, token) {
        var baseUrl = 'https://api.mapbox.com';
        var endPoint = '/geocoding/v5/mapbox.places/';
        return fetch(baseUrl + endPoint + encodeURIComponent(search) + '.json' + "?" + 'access_token=' + token)
            .then(function(res) {
                return res.json();
                // to get all the data from the request, comment out the following three lines...
            }).then(function(data) {
                return data.features[0].center;
            });
    }


    // const nav = new mapboxgl.NavigationControl();
    map.addControl(new mapboxgl.NavigationControl())
    // nav.addTo(map);
    var directions = new MapboxDirections({
        accessToken: mapboxgl.accessToken,
        unit: 'metric',
        profile: 'mapbox/cycling'
    });
        map.addControl(directions, 'top-left');

    var marker = new mapboxgl.Marker({
        draggable: true
    })
        .setLngLat([longitude, latitude])
        .addTo(map);

    //    create a marker on double click
    // function add_marker (event) {
    //     var coordinates = event.lngLat;
    //     console.log('Lng:', coordinates.lng, 'Lat:', coordinates.lat);
    //     marker.setLngLat(coordinates).addTo(map);
    // }
    //
    // map.on('dblclick', add_marker);

    var marker = new mapboxgl.Marker()

// Adding functionality to draggable marker
    function onDragEnd() {
        var lngLat = marker.getLngLat();
        longitude = lngLat.lng;
        latitude = lngLat.lat;
    }
    marker.on('dragend', onDragEnd);


    // Add the geocoder to the map
    map.addControl(geocoder);
}