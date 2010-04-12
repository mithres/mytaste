<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="maindecorator" />
<title><s:text name="vc.playlist.new" /></title>
</head>
<body>
<s:actionerror/>
<s:form namespace="/vod" action="savePlayList" enctype="multipart/form-data">
	<s:textfield label="%{getText('vc.playlist.name')}" name="playList.playListName" />
	<s:textfield label="%{getText('vc.playlist.price')}" name="playList.price" />
	<s:file label="%{getText('vc.playlist.screenshot')}" name="screenShot" />
	<s:file label="%{getText('vc.playlist.filmfile')}" name="film" />
	<s:select name="playList.playListType" label="%{getText('vc.playlist.type')}"
		list="playListTypes" />
	<s:select name="playList.filmType" label="%{getText('vc.playlist.filmtype')}"
		list="fileTypes" />
	<s:textarea label="%{getText('vc.playlist.description')}" cols="40" rows="6"
		name="playList.description" />
		
	<s:textfield label="%{getText('vc.playlist.tag')}" name="tags"/><s:text name="vc.playlist.tagmessage"/>
	<s:submit label="%{getText('vc.button.save')}" />
	
</s:form>

</body>
</html>