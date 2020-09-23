
//Format Date Inputs
Date.prototype.toISO = function() {
	 return this.getUTCFullYear() +
        '-' + String(this.getUTCMonth() + 1).padStart(2, "0") +
        '-' + String(this.getUTCDate()).padStart(2, "0") +
        'T' + String(this.getUTCHours()).padStart(2, "0") +
        ':' + String(this.getUTCMinutes()).padStart(2, "0") +
        ':' + String(this.getUTCSeconds()).padStart(2, "0") +
        '.' + (this.getUTCMilliseconds() / 1000).toFixed(3).slice(2, 5) +
        'Z';
};

//Checks if Date is Today
function isLessThanToday(date){
	var dateEntry = new Date(date);
	var today = new Date((new Date).setHours(0, 0, 0, 0));
	return dateEntry < today;
}  

//Formats the text to only accept numbers on keypress
function numOnly(id)
{
	 var number = document.getElementById(id);
	 var regex = /[^0-9]/gi;
	 
	 number.value = number.value.replace(regex, "");
}

//Formats the time to only accept numbers add Semicolon at 3rd index 
function timeOnly(id)
{
	var time = document.getElementById(id);
	var regexWithColon = /[^0-9:]/gi;
	var regexWithoutColon = /[^0-9]/gi;
	
	if (time.value.length == 2 && !(time.value.includes(":"))){
		
		time.value = time.value.replace(regexWithColon, "") + ":";
	} 
	
	if(time.value.includes(":")) {
		
		var hour = time.value.substring(0, time.value.indexOf(':')).replace(regexWithoutColon, "");
		var minute = time.value.substring(time.value.indexOf(':') + 1).replace(regexWithoutColon, "");
		var intHour = parseInt(hour);
		var intMinute = parseInt(minute);
		
		time.value = hour + ":" + minute;
		
		if (hour.length == 1 && (time.value.length == 4 || time.value.length == 3)){
			time.value = hour.padStart(2, "0") + ":" + minute.padEnd(2,"0");
			
			if (intMinute > 5) {	
				time.value = hour.padStart(2, "0") + ":00";	
			}
		}
		
		if (intHour > 24) {
			time.value = "00:" + minute;
		}
		
		if (intMinute > 59 || intHour == 24) {	
			time.value = hour.padStart(2, "0") + ":00";	
		}
		
	}
	
}