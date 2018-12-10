function accountBlur(){
	if(mobileBlur() || emailBlur()){
		
	}
}
function mobileBlur(){
	var phoneNum = $("#account").val();
	var reg = /^1[34578]\d{9}$/g;
	if(!reg.test(phoneNum)){
		$(".errorMobile").show();
		return false;
	}
	else{
		$(".errorMobile").hide();
		return true;
	}
}
function emailBlur(){
	var email = $("#account").val();
	var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/ ;
	if(!reg.test(email)){
		$(".errorEmail").show();
		return false;
	}
	else{
		$(".errorEmail").hide();
		return true;
	}
}
function login(){
	var phoneNum = $("#account").val();
	var pwNum = $("#password").val();
	var flag = false;
	return flag;
}