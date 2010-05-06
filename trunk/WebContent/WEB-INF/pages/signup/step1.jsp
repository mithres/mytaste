<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="web.page" uri="/WEB-INF/tlds/path.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="main" />
<title>Sign Up</title>
</head>
<body>
<div class="fluid title" id="lzappContainer">
  <div class="fixed-lg container">
      <div class="gr b ptop">
        <h2 class="cufonable">Welcome to Vide Share</h2>
        <h4 style="margin-top: 5px;" class="subtag">
          To begin, please complete the registration form below.
        </h4>
        <h4 style="margin-top: 5px;" class="subtag">
          Once your account is created, an email with instructions to verify your email address will be sent.
You may use your new Video Share account immediately, but you must first verify your email address before contributing to Reviews and Discussions.
        </h4>
      </div>
  </div>
</div>

<div class="signup form-table-wrapper">

		<form method="post" action="<web.page:path/>/signUp/createUser" name="createUser" id="createUser">
			<table class="wwFormTable">
				<tbody>
				<tr>
				    <td class="tdLabel">&nbsp;</td>
				    <td>Please note: Items marked with * are required.</td>
				</tr>
				<tr>
				    <td class="tdLabel" width="300px;"><label class="label" for="createUser_user_userName">User Name:</label></td>
				    <td><input type="text" class="form-input" id="createUser_user_userName" value="" name="user.userName"><b class="required">&nbsp;*</b></td>
				</tr>
	
				<tr>
				    <td class="tdLabel"><label class="label" for="createUser_user_password">Password:</label></td>
				    <td><input type="password" class="form-input" id="createUser_user_password" name="user.password"><b class="required">&nbsp;*</b></td>
				</tr>
	
				<tr>
				    <td class="tdLabel"><label class="label" for="createUser_password">Confirm Password:</label></td>
				    <td><input type="password" class="form-input" id="createUser_password" name="password"><b class="required">&nbsp;*</b></td>
				</tr>
	
				<tr>
				    <td class="tdLabel"><label class="label" for="createUser_user_email">User Email:</label></td>
				    <td><input type="text" class="form-input" id="createUser_user_email" value="" name="user.email"><b class="required">&nbsp;*</b></td>
				</tr>
	
				<tr>
				    <td class="tdLabel"><label class="label" for="createUser_user_firstName">First Name:</label></td>
				    <td><input type="text" class="form-input" id="createUser_user_firstName" value="" name="user.firstName">&nbsp;&nbsp;</td>
				</tr>
	
				<tr>
				    <td class="tdLabel"><label class="label" for="createUser_user_lastName">Last Name:</label></td>
				    <td><input type="text" class="form-input" id="createUser_user_lastName" value="" name="user.lastName">&nbsp;&nbsp;</td>
				</tr>
	
				 <tr>
					 <td class="tdLabel">&nbsp;</td>
					 <td>
					 	<div align="left">
					 		<input type="submit" value="Create an account" class="ui-button ui-widget ui-state-default ui-corner-all" role="button" aria-disabled="false">
					 	</div>
					 </td>
				 </tr>
			</tbody>
		</table>
		</form>
</div>



</body>
</html>