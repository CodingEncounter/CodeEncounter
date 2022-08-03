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
    var directions = new MapboxDirections({
        accessToken: mapboxgl.accessToken,
        unit: 'metric',
        profile: 'mapbox/cycling'
    });
    map.addControl(directions, 'top-left');
    //
    // const geocoder = new MapboxGeocoder({
    //     // Initialize the geocoder
    //     accessToken: mapboxgl.accessToken, // Set the access token
    //     mapboxgl: mapboxgl, // Set the mapbox-gl instance
    //     marker: false, // Do not use the default marker style
    //     placeholder: '   Search for places', // Placeholder text for the search bar
    // });

    map.on('load', () => {
        $.get('/meetup/all',
            function (data, textStatus) {
                data.forEach(meetup => {
                    let dateTime = new Date(meetup.dateTime).toLocaleString('en-US', {
                        weekday: 'short', // long, short, narrow
                        day: 'numeric', // numeric, 2-digit
                        year: 'numeric', // numeric, 2-digit
                        month: 'long', // numeric, 2-digit, long, short, narrow
                        hour: 'numeric', // numeric, 2-digit
                        minute: 'numeric', // numeric, 2-digit
                        second: 'numeric', // numeric, 2-digit
                    });
                    let organizedBy = meetup.user.firstName + " " + meetup.user.lastName;
                    const popup = new mapboxgl.Popup({ offset: 25 }).setHTML(
                        "<p> Name: " + meetup.name + "</p>" +
                        "<p>" + dateTime + "</p>" +
                        "<p>Description: " + meetup.description + "</p>" +
                        "<p>Organized by: " + organizedBy + "</p>"
                    );
                    new mapboxgl.Marker({
                        draggable: false
                    })
                        .setLngLat([meetup.longitude, meetup.latitude])
                        .setPopup(popup)
                        .addTo(map);

                    let p_name = $("<p class='card' style='text-align: center'>").text("Name: " + meetup.name);
                    // let p_dateTime = $("<p>").text(dateTime);
                    // let p_description = $("<p>").text("Description: " + meetup.description);
                    // let p_organizedBy = $("<p>").text("Organized by: " + organizedBy);
                    let dash = $("<p style='text-align: center'>").text("-----------------------------------")
                    let div = $("<div>").click(e => {
                        map.flyTo({
                            center: [meetup.longitude, meetup.latitude],
                        })
                    }).attr("class", "border-1px-dove-gray mb-2").append(p_name);
                    $("#allMeetups").append(div);
                })
            });
    });


});









