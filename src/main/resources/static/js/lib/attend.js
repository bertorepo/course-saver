function onValidation() {
    var fromDateTimeEmpty = $("fromDateTime").val();
    var toDateTimeEmpty = $("toDateTime").val();

    if (fromDateTimeEmpty != null || toDateTimeEmpty != null) {
        onSubmit();
    }
    if (fromDateTimeEmpty == null) {
        fromDateTimeError = "Please Select Date and Time";
        document.getElementById("fromDateTime_error").innerHTML = fromDateTimeError;
    }
    if (toDateTimeEmpty == null) {
        toDateTimeError = "Please Select Date and Time";
        document.getElementById("toDateTime_error").innerHTML = toDateTimeError;
    }
}
//SUBMIT FROM/TO DATE TIME
function onSubmit() {
    var toAppend = ":00.000+08:00[Asia/Singapore]";
    var fromDateTime = document.getElementById('fromDateTime').value;
    var toDateTime = document.getElementById('toDateTime').value;
    var inpFromDateTime = document.getElementById('inputFromDateTime');
    var inpToDateTime = document.getElementById('inputToDateTime');
    var finalFromDateTime = fromDateTime + toAppend;
    var finalToDateTime = toDateTime + toAppend;
    var fromDateTimeEmpty = inpFromDateTime;
    var toDateTimeEmpty = inpToDateTime;
    if (fromDateTimeEmpty == null) {
        fromDateTimeError = "Please Select Date and Time";
        document.getElementById("fromDateTime_error").innerHTML = fromDateTimeError;
    }
    if (toDateTimeEmpty == null) {
        toDateTimeError = "Please Select Date and Time";
        document.getElementById("toDateTime_error").innerHTML = toDateTimeError;
    }
    fromDateTimeEmpty.value = finalFromDateTime;
    toDateTimeEmpty.value = finalToDateTime;
}
function Back() {
	var callUrl = '/attendance/signin';
	document.location.href = callUrl;
}
/*<![CDATA[*/
var element = document.getElementById('successMessage').innerHTML;
$('#successModal').modal('show');
$('#successModal').on('shown.bs.modal', function() {
	$('#successModal').find('.modal-body').html(message);
});