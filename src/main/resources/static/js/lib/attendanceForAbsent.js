function choices() {
	var id = $('#selectedCourse').val();
	var courseName = $('#selectedCourse :selected').text();
	var message = 'You have signed in to ' + courseName;
	if (id != "") {
		$('#successModal').modal('show');
		$('#successModal').on('shown.bs.modal', function() {
			$('#successModal').find('.modal-body').html(message);
		});
	}
}
function ValidateDropDown(input) {
	var button = document.getElementById(".btn-success")
	if (input.value == '') {
		document.getElementById('button').className = "btn btn-large btn-success disabled";
		buttoninput.disabled = true;
	} else {
		document.getElementById('button').className = "btn btn-large btn-success";
		button.disabled = false;
	}
}
function successUrl() {
	var id = document.getElementById("selectedCourse").value;
	var callUrl = '/attendance/generate/' + id + '/absent';
	document.location.href = callUrl;
}
$(function() {
	var select = $('.selectedCourse');
	select.html(select.find('option').sort(function(x, y) {
		return $(x).text() > $(y).text() ? 1 : -1;
	}));
});
