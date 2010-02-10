function flushValidateCode() {
	// set timeout is workaroud for update captcha in IE
	setTimeout(function() {
	$('#ccode').attr('src','signUp/captcha?_=' + parseInt(Math.random() * 1000 + 1));}, 100);
}