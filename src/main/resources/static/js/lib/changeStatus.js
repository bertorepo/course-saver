//changeStatusDropDown
function myFunction(index) {
	var x = document.getElementById("statusDropDown" + index).value;
	document.getElementById("status" + index).value = x;

	if (x == 'P') {
		var a = document.getElementById("id");
		var b = a.options[a.selectedIndex].text;
		document.getElementById("scheduledTime" + index).value = b;
		document.getElementById("scheduledEndTime" + index).value = b;

		var getScheduledTime = document.getElementById("scheduledTime" + index).value;
		var getScheduledEndTime = document.getElementById("scheduledEndTime"
				+ index).value;

		var d = moment.utc(getScheduledTime, 'YYYY-MM-DD HH:mm').format(
				'YYYY-MM-DD');
		var dEnd = moment.utc(getScheduledEndTime, 'YYYY-MM-DD HH:mm').format(
				'YYYY-MM-DD');

		document.getElementById("getDate" + index).value = d;
		document.getElementById("getEndDate" + index).value = dEnd;

		document.getElementById("startTime" + index).disabled = false;
		document.getElementById("endTime" + index).disabled = false;

		var start = document.getElementById("startDateTimeBackUp" + index).value;
		document.getElementById("startDateTime" + index).value = start;

		var end = document.getElementById("endDateTimeBackUp" + index).value;
		document.getElementById("endDateTime" + index).value = end;

		var startBackUp = document.getElementById("momentTimeAbsent" + index).value;
		var endBackUp = document.getElementById("momentEndTimeAbsent" + index).value;

		document.getElementById("startTime" + index).value = startBackUp;
		document.getElementById("endTime" + index).value = endBackUp;
	}

	else if (x == 'A') {
		document.getElementById("startTime" + index).disabled = true;
		document.getElementById("endTime" + index).disabled = true;
		document.getElementById("startTime" + index).value = "";
		document.getElementById("endTime" + index).value = "";

		document.getElementById("startDateTime" + index).value = null;
		document.getElementById("endDateTime" + index).value = null;
	}
}
$(document).ready(function() {
	$(".auto").click();
});

$(function() {
	// choose target dropdown
	var select = $('.courseSelect');
	select.html(select.find('option').sort(function(x, y) {
		// to change to descending order switch "<" for ">"
		return $(x).text() > $(y).text() ? 1 : -1;
	}));
});

//onload
function momentofDate(index) {
	var x = document.getElementById("statusDropDown" + index).value;

	var transfer = document.getElementById("startDateTime" + index).value;
	var transferEnd = document.getElementById("endDateTime" + index).value;

	var t = moment.utc(transfer, 'YYYY-MM-DD HH:mm').format('HH:mm');
	var d = moment.utc(transfer, 'YYYY-MM-DD HH:mm').format('YYYY-MM-DD');

	var tEnd = moment.utc(transferEnd, 'YYYY-MM-DD HH:mm').format('HH:mm');
	var dEnd = moment.utc(transferEnd, 'YYYY-MM-DD HH:mm').format('YYYY-MM-DD');

	document.getElementById("startTime" + index).value = t;
	document.getElementById("momentDate" + index).value = d;

	document.getElementById("endTime" + index).value = tEnd;
	document.getElementById("momentEndDate" + index).value = dEnd;

	var time = document.getElementById("startTime" + index).value;
	var timeEnd = document.getElementById("endTime" + index).value;
	document.getElementById("momentTime" + index).value = time;
	document.getElementById("momentEndTime" + index).value = timeEnd;

	var postDate = document.getElementById("momentDate" + index).value;
	var postTime = document.getElementById("momentTime" + index).value;

	var postDateEnd = document.getElementById("momentEndDate" + index).value;
	var postTimeEnd = document.getElementById("momentEndTime" + index).value;

	var postDateTime = moment.utc(postDate + ' ' + postTime);
	var finalPostDateTime = postDateTime.utc().format(
			'YYYY-MM-DDTHH:mm:ss.SS+08:00');

	var postDateTimeEnd = moment.utc(postDateEnd + ' ' + postTimeEnd);
	var finalPostDateTimeEnd = postDateTimeEnd.utc().format(
			'YYYY-MM-DDTHH:mm:ss.SS+08:00');

	var momTime = document.getElementById("momentTime" + index).value;
	var momTimeEnd = document.getElementById("momentEndTime" + index).value;

	//For regex time
	var a = document.getElementById("id");
	var b = a.options[a.selectedIndex].text;
	document.getElementById("scheduledTime" + index).value = b;
	document.getElementById("scheduledEndTime" + index).value = b;

	var getScheduledTime = document.getElementById("scheduledTime" + index).value;
	var getScheduledEndTime = document.getElementById("scheduledEndTime"
			+ index).value;

	var startTime = getScheduledTime.replace(/[a-zA-Z | ]+ /g, '');
	var trimmedStartTime = startTime.replace(/--- [a-zA-Z \W 0-9 ]+/g, '');
	var endTime = getScheduledEndTime.replace(/[a-zA-Z | \W 0-9]+ ---/g, '');

	document.getElementById("getEndTime" + index).value = endTime;
	var regexEndTime = document.getElementById("getEndTime" + index).value;

	var d1 = moment.utc(getScheduledTime, 'YYYY-MM-DD HH:mm').format(
			'YYYY-MM-DD');
	var dTime1 = moment.utc(trimmedStartTime, 'YYYY-MM-DD HH:mm').format(
			'HH:mm');
	var dEnd1 = moment.utc(endTime, 'YYYY-MM-DD HH:mm').format('YYYY-MM-DD');
	var dEndRegex1 = moment.utc(regexEndTime, 'YYYY-MM-DD HH:mm').format(
			'HH:mm');

	if (x == 'A') {
		document.getElementById("startTime" + index).disabled = true;
		document.getElementById("endTime" + index).disabled = true;
		document.getElementById("startTime" + index).value = "";
		document.getElementById("endTime" + index).value = "";

		document.getElementById("getDate" + index).value = d1;
		document.getElementById("getEndDate" + index).value = dEnd1;
		document.getElementById("getTime" + index).value = dTime1;
		document.getElementById("getEndTime" + index).value = dEndRegex1;
		document.getElementById("startDateTime" + index).value = null;
		document.getElementById("endDateTime" + index).value = null;

	}

	else if (x == 'P' && momTime != "" && momTimeEnd != "") {
		document.getElementById("getDate" + index).value = d1;
		document.getElementById("getEndDate" + index).value = dEnd1;
		document.getElementById("getTime" + index).value = dTime1;
		document.getElementById("getEndTime" + index).value = dEndRegex1;

		document.getElementById("momentTimeAbsent" + index).value = time;
		document.getElementById("momentEndTimeAbsent" + index).value = timeEnd;
		document.getElementById("startDateTime" + index).value = finalPostDateTime;
		document.getElementById("startDateTimeBackUp" + index).value = finalPostDateTime;

		document.getElementById("endDateTime" + index).value = finalPostDateTimeEnd;
		document.getElementById("endDateTimeBackUp" + index).value = finalPostDateTimeEnd;
	}
}

//changeTime
function momentofTime(index) {
	var x = document.getElementById("statusDropDown" + index).value;

	var time = document.getElementById("startTime" + index).value;
	var timeEnd = document.getElementById("endTime" + index).value;

	document.getElementById("momentTime" + index).value = time;
	document.getElementById("momentEndTime" + index).value = timeEnd;

	var postDate = document.getElementById("momentDate" + index).value;
	var postTime = document.getElementById("momentTime" + index).value;

	var postDateEnd = document.getElementById("momentEndDate" + index).value;
	var postTimeEnd = document.getElementById("momentEndTime" + index).value;

	if (x == 'A') {
		document.getElementById("startTime" + index).value = "";
		document.getElementById("endTime" + index).value = "";
		document.getElementById("momentTimeAbsent" + index).value = "";
	}

	else if ((x == 'P' && postDate == "" && postDateEnd == "")
			|| (x == 'P' && postDate.length != 0 && postDateEnd.length != 0)) {
		var getScheduledTime = document.getElementById("scheduledTime" + index).value;
		var getScheduledEndTime = document.getElementById("scheduledEndTime"
				+ index).value;

		var d = moment.utc(getScheduledTime, 'YYYY-MM-DD HH:mm').format(
				'YYYY-MM-DD');
		var dEnd = moment.utc(getScheduledEndTime, 'YYYY-MM-DD HH:mm').format(
				'YYYY-MM-DD');
		document.getElementById("getDate" + index).value = d;
		document.getElementById("getEndDate" + index).value = dEnd;

		var postDate1 = document.getElementById("getDate" + index).value;
		var postD = document.getElementById("momentDate" + index).value = postDate1;

		var postDateTime = moment.utc(postD + ' ' + postTime);
		var finalPostDateTime = postDateTime.utc().format(
				'YYYY-MM-DDTHH:mm:ss.SS+08:00');

		document.getElementById("startDateTime" + index).value = finalPostDateTime;

		var postDate1End = document.getElementById("getEndDate" + index).value;
		var postDEnd = document.getElementById("momentEndDate" + index).value = postDate1End;

		var postDateTimeEnd = moment.utc(postDEnd + ' ' + postTimeEnd);
		var finalPostDateTimeEnd = postDateTimeEnd.utc().format(
				'YYYY-MM-DDTHH:mm:ss.SS+08:00');

		document.getElementById("endDateTime" + index).value = finalPostDateTimeEnd;
	}

	else if (x == 'P' && postDate != "" && postDateEnd != "") {
		var postDateTime1 = moment.utc(postDate + ' ' + postTime);
		var finalPostDateTime1 = postDateTime1.utc().format(
				'YYYY-MM-DDTHH:mm:ss.SS+08:00');
		document.getElementById("startDateTime" + index).value = finalPostDateTime1;

		var postDateTime1End = moment.utc(postDateEnd + ' ' + postTimeEnd);
		var finalPostDateTime1End = postDateTime1End.utc().format(
				'YYYY-MM-DDTHH:mm:ss.SS+08:00');
		document.getElementById("endDateTime" + index).value = finalPostDateTime1End;
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

function choices() {
	var id = document.getElementById("id").value;
	if (id != "") {
		var callUrl = '/attendance/' + id + '/participants';
		document.location.href = callUrl;
	}
}

function goBack() {
	var callUrl = '/attendance/0/participants';
	document.location.href = callUrl;
}

function validations() {
	//Empty Fields Validation for Start and End date
	var errorCount = 0;
	var startTimeCount = 0;
	var endTimeCount = 0;
	var participantDetails = document.getElementById("data");
	var length = participantDetails.rows.length;

	for (var i = 1; i <= length; i++) {
		var status = $("#statusDropDown" + i).val();
		var startTime = $("#startTime" + i).val();
		var endTime = $("#endTime" + i).val();
		var regexEndTime = $("#getEndTime" + i).val();
		var regexStartTime = $("#getTime" + i).val();

		if (startTime !== "") {
			startTimeCount++;
		}

		if (endTime !== "") {
			endTimeCount++;
		}

		if (status == 'P') {

			if (startTime > endTime && endTime != "") {
				errorCount++;
				startTimeError = "Time In Shouldn't be greater than Time Out";
				document.getElementById("startTime" + i + "_error").innerHTML = startTimeError;
			}

			if (startTime == endTime) {
				errorCount++;
				startTimeError = "Time In Shouldn't be equal to Time Out";
				document.getElementById("startTime" + i + "_error").innerHTML = startTimeError;
			}

			if (startTime == "") {
				errorCount++;
				startTimeError = "Please fill up Time In";
				document.getElementById("startTime" + i + "_error").innerHTML = startTimeError;
			}

			if (endTime == "") {
				errorCount++;
				endTimeError = "Please fill up Time Out";
				document.getElementById("endTime" + i + "_error").innerHTML = endTimeError;
			}

			if (startTime < regexStartTime && startTime != "") {
				errorCount++;
				startTimeError = "Time In should be in bound of scheduled time";
				document.getElementById("startTime" + i + "_error").innerHTML = startTimeError;
			}

			if (endTime > regexEndTime && endTime != "") {
				errorCount++;
				endTimeError = "Time Out should be in bound scheduled time";
				document.getElementById("endTime" + i + "_error").innerHTML = endTimeError;
			}
		}
	}

	for (i = 1; i <= length; i++) {
		var status = document.getElementById("statusDropDown" + i).value;
		var startTime = document.getElementById("startTime" + i).value;
		var endTime = document.getElementById("endTime" + i).value;
		var regexEndTime = document.getElementById("getEndTime" + i).value;
		var regexStartTime = document.getElementById("getTime" + i).value;

		function removeWarning() {
			var fieldId = this.id;
			var data = document.getElementById(fieldId).value;

			if (data !== "") {
				document.getElementById(fieldId + "_error").innerHTML = "";
			}
		}

		document.getElementById("startTime" + i).onmousemove = removeWarning;
		document.getElementById("endTime" + i).onmousemove = removeWarning;

		if (errorCount <= 0) {
			$('#confirmModal').modal('show');
		}
	}
}
//Success Modal Validation
/*<![CDATA[*/
var element = document.getElementById('successMessage').innerHTML;
if (successMessage == element) {
	$('#successModal').modal('show');
	$('#successModal').on('shown.bs.modal', function() {
		$('#successModal').find('.modal-body').html(message);
	});
}