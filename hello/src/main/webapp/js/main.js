function confirmDelete(){
	var r = confirm("Are you sure that you want to delete this route?");
	
	if(!r){
		return false;
	}else{
		return true;
	}
}