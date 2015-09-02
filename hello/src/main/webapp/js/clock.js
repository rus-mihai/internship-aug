var timeStr, dateStr;

function myFunction(x) {   
	now = new Date();
	timer="time"+x;
	hours = now.getHours();
	minutes = now.getMinutes();
	seconds = now.getSeconds();
	
	  date= now.getDate();
      month= now.getMonth()+1;
      year= now.getFullYear();
    dateStr= "" +year+"-"+month+"-"+date;
	timeStr = "" + hours + ":" + minutes + ":" + seconds;
	
	timeStamp=""+dateStr+" "+timeStr;
	
	document.getElementById(timer).value=timeStamp;
	

}