function deleteButton(id, category, detail) {
	document.getElementById("courseCategoryIdInput").value = id;
	document.getElementById("courseCategoryName").innerHTML = category;
	document.getElementById("courseCategoryDetail").innerHTML = detail;
	$('#infoModal').modal('show');
}

function showUpdateModal(id, category, detail) {
	document.getElementById("id").value = id;
	document.getElementById("category").value = category;
	document.getElementById("detail").value = detail;
	$('#updateModal').modal('show');
}

function validateIfEmpty() {
	var category = document.getElementById("category");
	var detail = document.getElementById("detail");
	document.getElementById("updateBtn").disabled = true;
	if (category.value === "" && detail.value != "") {
		document.getElementById("categoryErrorMsg").innerHTML = "*Required";
		document.getElementById("detailErrorMsg").innerHTML = "";
	} else if (category.value != "" && detail.value === "") {
		document.getElementById("categoryErrorMsg").innerHTML = "";
		document.getElementById("detailErrorMsg").innerHTML = "*Required";
	} else if (category.value === "" && detail.value === "") {
		document.getElementById("categoryErrorMsg").innerHTML = "*Required";
		document.getElementById("detailErrorMsg").innerHTML = "*Required";
	} else {
		document.getElementById("categoryErrorMsg").innerHTML = "";
		document.getElementById("detailErrorMsg").innerHTML = "";
		document.getElementById("updateBtn").disabled = false;
	}
}

function refreshCourseCategory() {
	window.location.href = '/courseCategory/load';
}

$(document).ready(function() {
	if (window.location.href.indexOf('#confirmDeleteModal') != -1) {
		$('#confirmDeleteModal').modal('show');
	}

	if (window.location.href.indexOf('#successModal') != -1) {
		$('#successModal').modal('show');
	}

	if (window.location.href.indexOf('#errorModal') != -1) {
		$('#errorModal').modal('show');
	}

	$('#confirmUpdateBtn').click(function() {
		$('#updateModalFormId').submit();
	});

	$('#confirmDeleteModal').on('hidden.bs.modal', function(e) {
		refreshCourseCategory();
	});

	$('#successModal').on('hidden.bs.modal', function(e) {
		refreshCourseCategory();
	});

	$('#confirmUpdateModal').on('hidden.bs.modal', function(e) {
		refreshCourseCategory();
	});

	$('#errorModal').on('hidden.bs.modal', function(e) {
		refreshCourseCategory();
	});
});

var rowsShown = 8;
var rowsTotal = $('#categoryTable tbody tr').length;
var numPages = rowsTotal / rowsShown;
var firstPage = 0;
var lastPage = Math.floor(numPages);
var currPageShown = 0;
$('#pagination').append('<a href="#" rel="<<" > << </a> ');
$('#pagination').append('<a href="#" rel="<" > < </a> ');
for (i = 0; i < numPages; i++) {
	var pageNum = i + 1;
	$('#pagination').append(
			'<a href="#" rel="' + i + '" > ' + pageNum + ' </a> ');
}
$('#pagination').append('<a href="#" rel=">" > > </a> ');
$('#pagination').append('<a href="#" rel=">>" > >> </a> ');
$('#categoryTable tbody tr').hide();
$('#categoryTable tbody tr').slice(0, rowsShown).show();
$('#pagination a:nth-child(3)').addClass('active');
$('#pagination a').bind(
		'click',
		function() {
			$('#pagination a').removeClass('active');
			var currPage = $(this).attr('rel');
			var parent = document.getElementById("pagination").children;
			if (currPage == '<<') {
				parent[firstPage + 2].className = 'active';
				currPageShown = firstPage;
				var startItem = currPageShown * rowsShown;
				var endItem = startItem + rowsShown;
			} else if (currPage == '>>') {
				if ((rowsTotal % rowsShown) == 0) {
					currPageShown = lastPage - 1;					
				} else {
					currPageShown = lastPage;
				}
				parent[currPageShown + 2].className = 'active';
				var startItem = currPageShown * rowsShown;
				var endItem = startItem + rowsShown;
			} else if (currPage == '<') {
				if (currPageShown == firstPage) {
					currPageShown = firstPage;
				} else {
					currPageShown = currPageShown - 1;
				}
				var startItem = currPageShown * rowsShown;
				var endItem = startItem + rowsShown;
				parent[currPageShown + 2].className = 'active';
			} else if (currPage == '>') {
				if (currPageShown == lastPage) {
					currPageShown = lastPage;
				} else {
					currPageShown = currPageShown + 1;
				}
				var startItem = currPageShown * rowsShown;
				var endItem = startItem + rowsShown;
				parent[currPageShown + 2].className = 'active';
			} else {
				$(this).addClass('active'); 
				currPageShown = parseInt(currPage);
				var startItem = currPageShown * rowsShown;
				var endItem = startItem + rowsShown;
			}
			$('#categoryTable tbody tr').css('opacity', '0.0').hide().slice(
					startItem, endItem).css('display', 'table-row').animate({
				opacity : 1
			}, 300);
		});