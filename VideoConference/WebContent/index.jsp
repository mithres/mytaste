<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page
	import="org.springframework.security.ui.AbstractProcessingFilter"%>
<%@ page import="org.springframework.security.AuthenticationException"%>

<%@ taglib prefix="web.page" uri="/WEB-INF/tlds/path.tld"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="maindecorator" />
<title>Home Page</title>
</head>
<body>

<%
	if (session
			.getAttribute(AbstractProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY) != null) {
		out
				.println(((AuthenticationException) session
						.getAttribute(AbstractProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY))
						.getMessage());
	}
%>
<form action="signIn" method="POST">
<table>
	<tr>
		<td>User:</td>
		<td><input type='text' name='userName' value="" /></td>
	</tr>
	<tr>
		<td>Password:</td>
		<td><input type='password' name='password' AUTOCOMPLETE="off" /></td>
	</tr>
	<tr>
		<td>Enter letters below:</td>
		<td><input type='text' name='ccode' value="" /></td>
	</tr>
	<tr>
		<td>Check Code:</td>
		<td><img id="ccode" src="signUp/captcha" /> <a
			href="javascript:void(0);" onclick=
	flushValidateCode();;
>Reload</a></td>
	</tr>

	<tr>
		<td colspan='2'><input name="submit" type="submit"></td>
	</tr>
	<tr>
		<td colspan='2'><input name="reset" type="reset"></td>
	</tr>
</table>

</form>

</body>
</html>