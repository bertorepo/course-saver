
 $( document ).ready(function() {






//VALIDATION IF NO RESULT FOUND IN COURSE INPUT		
var $block = $('.no-results');
			
$("#inputCourse").keyup(function() {
    var value = $(this).val().toLowerCase();
    var isMatch = false;
    

    $("#tBodySummary tr").filter(function(i) {
        var content = $(this).find("td:eq(1)").text().toLowerCase();
		$(this).toggle($(this).find("td:eq(1)").text().toLowerCase().indexOf(value) > -1)
        if(content.toLowerCase().indexOf(value) == -1) {
           $(this).hide();           
		
        } else {
            isMatch = true;
            $(this).show();
			 
        }
    });
    
    $block.toggle(!isMatch);
});
			
});
//
//
		/**
		Exporting to CSV File
		*/
		function downloadCSV(csv, filename) {
			if(fromDateTime > toDateTime){
				alert("Invalid Date. Please select correct date.");
			}
			else if($('#selectReportType').val() == 0){
				alert("Please select report type.");
			}
			else if(fromDateTime == null){
				alert("Please select start date.");
			}
			else if(toDateTime == null){
				alert("Please select end date.");
			}
			else{
				var csvFile;
				var downloadLink;
				alert("Report exported successfully");
				csvFile = new Blob([csv], {type: "text/csv;charset=utf-8"});
				downloadLink = document.createElement("a");
				downloadLink.download = filename;
				downloadLink.href = window.URL.createObjectURL(csvFile);
				downloadLink.style.display = "none";
				downloadLink.click();
			}
		}
		function exportTableToCSV(filename) {
			var csv = [];
			var rows = document.querySelectorAll("#summaryCoursesTable tr");
			
			for (var i = 0; i < rows.length; i++) {
				var row = [], cols = rows[i].querySelectorAll("td, th");
				
				for (var j = 0; j < cols.length; j++) 
					row.push(cols[j].innerText);
        			csv.push(row.join(","));        
   			}
   			downloadCSV(csv.join("\n"), filename);
		}



		/**
		For Filtering Courses Table
		*/
		var selectedItem = sessionStorage.getItem("SelectedItem");  
		$('#selectReportType').val(selectedItem);
		$('#selectReportType').change(function() { 
			$("#viewButton").removeAttr('disabled'); 
			
			var dropVal = $(this).val();
			sessionStorage.setItem("SelectedItem", dropVal);
		});
		function filterText(){  
			var rex = new RegExp($('#selectCourse').val());
			
			if(rex =="/all/"){clearFilter()}else{
				$('.content').hide();
				$('.content').filter(function() {
				return rex.test($(this).text());
				}).show();
			}
		}
		function clearFilter(){
			$('.selectCourse').val('');
			$('.content').show();
		}

		
	/**
		Exporting to CSV File
		*/
		function downloadCSV(csv, filename) {

			if(rowCount <= 0)
			{
				$("#exportValidation").show();
			}
			else{
				if(fromDateTime > toDateTime){
					document.getElementById("message").innerHTML = dateErrorMessage;
					$('#errorModal').modal('show');
				}
				else if($('#selectReportType').val() == 0){
					document.getElementById("message").innerHTML = reportTypeErrorMessage;
					$('#errorModal').modal('show');
				}
				else if(fromDateTime == null){
					document.getElementById("message").innerHTML = selectStartDateErrorMessage;
					$('#errorModal').modal('show');
				}
				else if(toDateTime == null){
					document.getElementById("message").innerHTML = selectEndDateErrorMessage;
					$('#errorModal').modal('show');
				}
				else{
					var csvFile;
					var downloadLink;
					alert("Report exported successfully");
					csvFile = new Blob([csv], {type: "text/csv;charset=utf-8"});
					downloadLink = document.createElement("a");
					downloadLink.download = filename;
					downloadLink.href = window.URL.createObjectURL(csvFile);
					downloadLink.style.display = "none";
					downloadLink.click();
				}
			}

		}
		function exportTableToCSV(filename) {
			var csv = [];
			var rows = document.querySelectorAll("#exportSummaryCoursesTable tr");
			
			for (var i = 0; i < rows.length; i++) {
				var row = [], cols = rows[i].querySelectorAll("td, th");
				
				for (var j = 0; j < cols.length; j++) 
					row.push(cols[j].innerText);
        			csv.push(row.join(","));        
   			}
   			downloadCSV(csv.join("\n"), filename);
		}

		/**
		Filter Duplicate Courses

		$('#inputCourse text').each(function(){
			  if($.inArray(this.value, optionValues) >-1){
			     $(this).remove()
			  }else{
			     optionValues.push(this.value);

			  }
		});
			*/	
			
			
			
		/**
		Display Modal
		*/
		var dateErrorMessage = "To Date should be greater than or equal to From Date";
		var reportTypeErrorMessage = "Please select report type."
		var selectStartDateErrorMessage = "Please select start date."
		var selectEndDateErrorMessage = "Please select end date."
		
		function viewButtonClick(){
			
			if( $("#startDate").val() >=  $("#endDate").val()){
				document.getElementById("message").innerHTML = dateErrorMessage;
				$('#errorModal').modal('show');
			}
			else if($('#selectReportType').val() == 0){
				document.getElementById("message").innerHTML = reportTypeErrorMessage;
				$('#errorModal').modal('show');
			}
			else if(fromDateTime == null){
				document.getElementById("message").innerHTML = selectStartDateErrorMessage;
				$('#errorModal').modal('show');
			}
			else if(toDateTime == null){
				document.getElementById("message").innerHTML = selectEndDateErrorMessage;
				$('#errorModal').modal('show');
			}
			else{
				document.getElementById("coursesConductedForm").submit();
			}
		}	
		