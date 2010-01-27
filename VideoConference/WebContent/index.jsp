<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="maindecorator" />
<title>Home Page</title>
</head>
<body>

<form action="signIn" method="POST">
<table>
	<tr>
		<td>User:</td>
		<td><input type='text' name='j_username' value=""/></td>
	</tr>
	<tr>
		<td>Password:</td>
		<td><input type='password' name='j_password' /></td>
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