 function validateForm() {
    var x = document.forms["myForm"]["time1"].value;
    var y = document.forms["myForm"]["time2"].value;
    var z = document.forms["myForm"]["time3"].value;
    if (x == null || x == "") {
        alert("Start must be filled out");
        return false;
    }
    if (y == null || y == "") {
        alert("Pause must be filled out");
        return false;
    }
    if (z == null || z == "") {
        alert("Stop must be filled out");
        return false;
    }
    
    confirm("Your time was succesfully submited!");
    
}
 