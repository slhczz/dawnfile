function mobileBlur(){
	var phoneNum = $("#mobile").val();
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
	var email = $("#email").val();
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
function passwordBlur(){
	var pwNum = $("#password").val();
	var reg = /^\S{6,16}$/;
	if(!reg.test(pwNum)){
		$(".errorPassword").show();
		return false;
	}
	else{
		$(".errorPassword").hide();
		return true;
	}
		
}
function password2Blur(){
	var pwNum2 = $("#password-confirmation").val();
	var pwNum = $("#password").val();
	if(pwNum != pwNum2){
		$(".errorPassword2").show();
		return false;
	}
	else{
		$(".errorPassword2").hide();
		return true;
	}
}

function clearAll(){
	$("#mobile").val();
	$("#email").val();
	$("#password").val();
	$("#password-confirmation").val();
}
function register(){
	var flag = false;
	flag = ((mobileBlur()&&emailBlur())&&(passwordBlur()&&password2Blur()));
   if(!document.getElementById("check").checked){
	 	alert("请阅读条款并打勾!");
	 	flag = false;
	 }
	
	if(flag == true){
		flag = true;
	}else{
		flag = false;
	}
	return flag;
}
