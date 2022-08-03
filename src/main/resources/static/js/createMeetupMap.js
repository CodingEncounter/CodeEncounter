mapboxgl.accessToken = 'pk.eyJ1IjoiYW5hY29kZXVwIiwiYSI6ImNsNXNtc281bTJqMDMzaXJvM3dsYnBvejMifQ.edW3GsZuaC1hr4Jvy_u7XQ';


let longitude = -98.7320;
let latitude = 29.7947


function setUpCreateMeetupMap(center) {
    let map = new mapboxgl.Map({
        container: 'createMeetupMap',
        style: 'mapbox://styles/mapbox/streets-v11',
        center: center,
        zoom: 14
    });
    //Search bar
    const geocoder = new MapboxGeocoder({
        // Initialize the geocoder
        accessToken: mapboxgl.accessToken, // Set the access token
        mapboxgl: mapboxgl, // Set the mapbox-gl instance
        // marker: false, // Do not use the default marker style
        placeholder: '   Search for places', // Placeholder text for the search bar
    });

    geocoder.on('result', (event) => {
        let lon = event.result.geometry.coordinates[0];
        let lat = event.result.geometry.coordinates[1];
        document.getElementById("longitude").value=lon;
        document.getElementById("latitude").value=lat;
    });

    map.addControl(new mapboxgl.NavigationControl())
    // nav.addTo(map);
    // var directions = new MapboxDirections({
    //     accessToken: mapboxgl.accessToken,
    //     unit: 'metric',
    //     profile: 'mapbox/cycling'
    // });
    // map.addControl(directions, 'top-left');
    map.addControl(geocoder);
}

setUpCreateMeetupMap([longitude, latitude])