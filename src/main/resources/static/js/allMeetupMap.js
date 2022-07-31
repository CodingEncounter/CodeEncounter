mapboxgl.accessToken = 'pk.eyJ1IjoiYW5hY29kZXVwIiwiYSI6ImNsNXNtc281bTJqMDMzaXJvM3dsYnBvejMifQ.edW3GsZuaC1hr4Jvy_u7XQ';

var center = [-98.7320, 29.7947]
navigator.geolocation.getCurrentPosition(successLocation, errorLocation, {
    enableHighAccuracy: true
});

function successLocation(position) {
    console.log(position);
    center = [position.coords.longitude, position.coords.latitude];
}

function errorLocation() {
}

$(document).ready(function () {
    let map = new mapboxgl.Map({
        container: 'map',
        style: 'mapbox://styles/mapbox/streets-v11',
        center: center,
        zoom: 14
    });

    const geocoder = new MapboxGeocoder({
        // Initialize the geocoder
        accessToken: mapboxgl.accessToken, // Set the access token
        mapboxgl: mapboxgl, // Set the mapbox-gl instance
        marker: false, // Do not use the default marker style
        placeholder: '   Search for places', // Placeholder text for the search bar
    });

    map.on('load', () => {
        $.get('/meetup/all',
            function (data, textStatus) {
                data.forEach(meetup => {
                    new mapboxgl.Marker({
                        draggable: false
                    })
                        .setLngLat([meetup.longitude, meetup.latitude])
                        .addTo(map);
                })
            });
    });


});