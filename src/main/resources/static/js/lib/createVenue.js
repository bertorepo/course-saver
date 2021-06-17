function validateInput() {
	var hasInvalidInput = false;
	var venueNameValue =  $('#venueName').val().trim();
	var format = /[`!@#$%^&*+\=\[\]{};':"\\|,.<>\/?~]/;

	$('#venueNameError').text('');

	if (venueNameValue == "") {
		$('#venueNameError').text("Please enter a venue name.");
		hasInvalidInput = true;
	}

	if (format.test(venueNameValue))
	{
		$('#venueNameError').text("Venue name contains invalid special characters.");
		hasInvalidInput = true;
	}

	if (checkForDuplicate(venueNameValue)) { //Can be used for checking for no change for now since only one item
		$('#venueNameError').text("Venue name already exists!");
		hasInvalidInput = true;
	}

	if (hasInvalidInput) {
		$('#submitButton').prop('disabled', true);
	} else {
		$('#submitButton').prop('disabled', false);
	}
}

function checkForDuplicate(name) {
	return venueList.some(function(venue) {
		return venue.name.toLowerCase() === name.toLowerCase();
	});
}

$(document).ready(function() {
	if (window.location.href.indexOf('#successModal') != -1) {
		$('#successModal').modal('show');
	}
})
