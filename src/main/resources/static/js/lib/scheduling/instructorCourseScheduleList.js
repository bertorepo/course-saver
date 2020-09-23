/* <!--View Top Learners--> */
	$('a[name="topLearners"]').on('click', function() {
		$('#viewTopLearnersModal').modal('show');
	});
/* <!--View Top Learners end--> */
	
	
function load(){
	var fromDateTime = $("#fromDateTime").val();
	var toDateTime = $("#toDateTime").val();
	
	var fromNewDate = fromDateTime.toString().slice(0,10);
	var toNewDate = toDateTime.toString().slice(0,10);
	
	document.getElementById("fromDate").value = fromNewDate;
	document.getElementById("toDate").value = toNewDate;
}