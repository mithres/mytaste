<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="web.page" uri="/WEB-INF/tlds/path.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Create new room</title>
</head>
<body>
	
	<s:form namespace="/conference" action="saveRoom">
	
	<s:textfield label="Room name" name="room.roomName" />
	<s:textfield label="Max People" name="room.maxPeopleCount" />
	<s:select name="room.roomType" label="Room type"
		list="#{'Training':'Training','Conference':'Conference'}" />
	<s:select name="room.roomPrivacy" label="Room status"
		list="#{'Public':'Public','Private':'Private'}" />	
	<s:textfield label="Room password" name="room.password" />
	<s:submit label="Save" />
	</s:form>

</body>
</html>