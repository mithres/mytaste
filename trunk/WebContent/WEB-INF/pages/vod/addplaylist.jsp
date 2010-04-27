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

	<form method="post" action="<web.page:path/>/vod/savePlayList" enctype="multipart/form-data" name="createPlayList" id="createPlayList">
		<table class="wwFormTable">
			<tbody>
			<tr>
			    <td class="tdLabel">&nbsp;</td>
			    <td>Please note: Items marked with * are required.</td>
			</tr>
			<tr>
			    <td class="tdLabel"><label class="label" for="createPlayList_playListName"><s:text name="vc.playlist.name"/>:</label></td>
			    <td><input type="text" class="form-input" id="createPlayList_playListName" value="" name="playList.playListName"></td>
			</tr>

			<tr>
			    <td class="tdLabel"><label class="label" for="createPlayList_price"><s:text name="vc.playlist.price"/>:</label></td>
			    <td><input type="password" class="form-input" id="createPlayList_price" name="playList.price"></td>
			</tr>

			<tr>
			    <td class="tdLabel"><label class="label" for="createPlayList_playListType"><s:text name="vc.playlist.type"/>:</label></td>
			    <td><select id="createPlayList_playListType" name="playList.playListType">
			    	<s:iterator value="playListTypes">
			    		<s:property/>
			    	</s:iterator>
			    </select></td>
			</tr>

			<tr>
			    <td class="tdLabel"><label class="label" for="createPlayList_fileTypes"><s:text name="vc.playlist.filmtype"/>:</label></td>
			    <td><select id="createPlayList_fileTypes" name="playList.filmType">
			    	<s:iterator value="fileTypes">
			    		<s:property/>
			    	</s:iterator>
			    </select></td>
			</tr>
			
			<tr>
			    <td class="tdLabel"><label class="label" for="createPlayList_screenShot"><s:text name="vc.playlist.screenshot"/>:</label></td>
			    <td><input type="file" id="createPlayList_screenShot" name="screenShot"></td>
			</tr>

			<tr>
			    <td class="tdLabel"><label class="label" for="createPlayList_film"><s:text name="vc.playlist.filmfile"/>:</label></td>
			    <td><input type="file" id="createPlayList_film" name="film"></td>
			</tr>
			
			<tr>
			    <td class="tdLabel"><label class="label" for="createPlayList_playListDescrition"><s:text name="vc.playlist.description"/>:</label></td>
			    <td><textarea id="createPlayList_playListDescrition" name="playList.description"></textarea></td>
			</tr>
			
			<tr>
			    <td class="tdLabel"><label class="label" for="createPlayList_playListTags"><s:text name="vc.playlist.tag"/>:</label></td>
			    <td><input type="text" class="form-input" id="createPlayList_playListTags" name="tags"></td>
			</tr>
			

			 <tr>
				 <td class="tdLabel">&nbsp;</td>
				 <td><div align="left"><input onmouseover="this.src='<web.page:path/>/images/btn-createanaccount-hover.gif'" onmouseout="this.src='<web.page:path/>/images/btn-createanaccount.gif'" type="image" value="Submit" id="createUser_0" src="<web.page:path/>/images/btn-createanaccount.gif" alt="Submit">
				 </div></td>
			 </tr>

		</tbody></table></form>
		
		
</body>
</html>