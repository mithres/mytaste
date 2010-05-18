<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="web.page" uri="/WEB-INF/tlds/path.tld"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="maindecorator" />
<title>Create New Channel</title>
</head>
<body>


<form action="<web.page:path/>/admin/saveChannel">
	Channel Name : <input name="channel.channelName"/><br/>
	Parent Channel : <select name="parentChannel">
		<option value="00">---Parent Channel---</option>
		<s:iterator value="parentChannels">
			<option value="<s:property value="id"/>"><s:property value="channelName"/></option>
		</s:iterator>
	</select><br/>
	<input type="submit" value="save"/>	
</form>

</body>
</html>