<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="web.page" uri="/WEB-INF/tlds/path.tld"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="main" />
<title>Room List</title>
</head>
<body>
	
	<s:iterator value="rooms.records">
			Name :<s:property value="roomName"/>
			Type :<s:property value="roomType"/>
			Status:<s:property value="roomPrivacy"/>
			Max People:<s:property value="maxPeopleCount"/>
			Creator:<s:property value="creator.userName"/>
			Moderator:<s:property value="moderator.userName"/>
			<a href="<web.page:path/>/conference/joinRoom?roomId=<s:property value="roomId"/>">Join</a>
	</s:iterator>

</body>
</html>