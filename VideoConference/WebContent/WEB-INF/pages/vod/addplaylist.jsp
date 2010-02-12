<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="maindecorator" />
<title>Add New PlayList</title>
</head>
<body>

<s:form namespace="/vod" action="savePlayList" enctype="multipart/form-data">
	<s:textfield label="PlayListName" name="playList.playListName" />
	<s:textfield label="Price" name="playList.price" />
	<s:file label="ScreenShot" name="screenShot" />
	<s:file label="Film File" name="film" />
	<s:select name="playList.playListType" label="Type"
		list="#{'Movie':'Movie','News':'News','Sport':'Sport','No Type':'NoType'}" />
	<s:select name="playList.filmType" label="FilmType"
		list="#{'Normal':'Normal','HD':'HD','Audio Only':'Audio'}" />
	<s:textarea label="Description" cols="40" rows="6"
		name="playList.description" />
	<s:submit label="Save" />

</s:form>

</body>
</html>