/* <!--View Top Learners--> */
	$('a[name="topLearners"]').on('click', function() {
		$('#viewTopLearnersModal').modal('show');
	});
/* <!--View Top Learners end--> */
	
	
function load(){
	var fromDateTime = $("#fromDateTime").val();
	var toDateTime = $("#toDateTime").val();
	
	var fromNewDate = fromDateTime.toString().substring(0, 16);
	var toNewDate = toDateTime.toString().substring(0, 16);
	
	document.getElementById("fromDate").value = fromNewDate;
	document.getElementById("toDate").value = toNewDate;
}