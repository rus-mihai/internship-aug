var marker;
var map;
var x = 1;
function initMap() {
	var directionsService = new google.maps.DirectionsService;
	var directionsDisplay = new google.maps.DirectionsRenderer;
	map = new google.maps.Map(document.getElementById('map'), {
		zoom : 10,
		center : {
			lat : 46.767307,
			lng : 23.603637
		}
	});
	directionsDisplay.setMap(map);

	var geocoder = new google.maps.Geocoder;
	var infowindow = new google.maps.InfoWindow;

	map.addListener('click', function(e) {
		geocodeLatLng(e.latLng, geocoder, map, infowindow);
	});

	var onChangeHandler = function() {
		calculateAndDisplayRoute(directionsService, directionsDisplay);
	};
	document.getElementById('start')
			.addEventListener('change', onChangeHandler);
	document.getElementById('end').addEventListener('change', onChangeHandler);
}

function geocodeLatLng(latLng, geocoder, map, infowindow) {

	if (marker) {
		// if marker already was created change positon
		marker.setPosition(latLng);
		geocoder
				.geocode(
						{
							'location' : latLng
						},
						function(results, status) {
							if (status === google.maps.GeocoderStatus.OK) {
								if (results[1]) {
									map.setZoom(11);
									infowindow
											.setContent(results[1].formatted_address);
									document.getElementById("address1").value = results[1].formatted_address;
									infowindow.open(map, marker);
								} else {
									window.alert('No results found');
								}
							} else {
								window.alert('Geocoder failed due to: '
										+ status);
							}
						});

	} else {

		geocoder.geocode({
			'location' : latLng
		}, function(results, status) {
			if (status === google.maps.GeocoderStatus.OK) {
				if (results[1]) {
					map.setZoom(11);
					marker = new google.maps.Marker({
						position : latLng,
						map : map
					});
					infowindow.setContent(results[1].formatted_address);
					document.getElementById("address1").value = results[1].formatted_address;
					infowindow.open(map, marker);
				} else {
					window.alert('No results found');
				}
			} else {
				window.alert('Geocoder failed due to: ' + status);
			}
		});
	}
}

function calculateAndDisplayRoute(directionsService, directionsDisplay) {
	directionsService.route({
		origin : document.getElementById('start').options[document
				.getElementById('start').selectedIndex].text,
		destination : document.getElementById('end').options[document
				.getElementById('end').selectedIndex].text,
		travelMode : google.maps.TravelMode.DRIVING
	}, function(response, status) {
		if (status === google.maps.DirectionsStatus.OK) {
			directionsDisplay.setDirections(response);
		} else {
			window.alert('Directions request failed due to ' + status);
		}
	});
}