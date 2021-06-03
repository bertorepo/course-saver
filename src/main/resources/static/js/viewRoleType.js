function deleteButton(id, roleName, roleDesc) {
	//document.getElementById("roleIdInput").value = id;
	document.getElementById("roleName").innerHTML = roleName;
	document.getElementById("roleDesc").innerHTML = roleDesc;
	
	var form  = document.getElementById("infoModalFormId");
	form.addEventListener('submit', function(){
		this.action = "/roletype/" + id + "/delete";
	});

	$('#infoModal').modal('show');
}

$(document).ready(function() {
	if (window.location.href.indexOf('#confirmModal') != -1) {
		$('#confirmModal').modal('show');
	}
});

$(document).ready(function() {
	if (window.location.href.indexOf('#successModal') != -1) {
		$('#successModal').modal('show');
	}
});


$(function() {
  $("[autofocus]").on("focus", function() {
    if (this.setSelectionRange) {
      var len = this.value.length * 2;
      this.setSelectionRange(len, len);
    } else {
      this.value = this.value;
    }
    this.scrollTop = 999999;
  }).focus();
});