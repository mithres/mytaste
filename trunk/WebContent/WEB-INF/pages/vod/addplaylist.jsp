<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="web.page" uri="/WEB-INF/tlds/path.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="main" />
<title><s:text name="vc.playlist.new" /></title>
</head>
<body>
<s:actionerror/>

	<form method="post" action="<web.page:path/>/vod/savePlayList" enctype="multipart/form-data" name="createPlayList" id="createPlayList">
	<input type="hidden" name="playList.id" value="<s:property value="playList.id"/>"/>
		<table class="wwFormTable">
			<tbody>
			<tr>
			    <td class="tdLabel">&nbsp;</td>
			    <td>Please note: Items marked with * are required.</td>
			</tr>
			<tr>
			    <td class="tdLabel"><label class="label" for="createPlayList_playListName"><s:text name="vc.playlist.name"/>:</label></td>
			    <td><input type="text" class="form-input" id="createPlayList_playListName" value="<s:property value="playList.playListName"/>" name="playList.playListName"></td>
			</tr>

			<tr>
			    <td class="tdLabel"><label class="label" for="createPlayList_price"><s:text name="vc.playlist.price"/>:</label></td>
			    <td><input type="text" class="form-input" id="createPlayList_price" value="<s:property value="playList.price"/>" name="playList.price"></td>
			</tr>

			<tr>
			    <td class="tdLabel"><label class="label" for="createPlayList_playListType"><s:text name="vc.playlist.type"/>:</label></td>
			    <td><select id="createPlayList_playListType" name="playList.playListType">
			    	<s:iterator value="playListTypes" var="plt">
			    		<option <s:if test="#plt.equals(playList.playListType)">selected='selected'</s:if> value='<s:property/>'><s:property /></option>
			    	</s:iterator>
			    </select></td>
			</tr>


			<tr>
			    <td class="tdLabel"><label class="label" for="createPlayList_fileTypes"><s:text name="vc.playlist.filmtype"/>:</label></td>
			    <td><select id="createPlayList_fileTypes" name="playList.filmType">
			    	<s:iterator value="fileTypes" var="ft">
			    		<option <s:if test="#ft.equals(playList.filmType)">selected='selected'</s:if> value='<s:property/>'><s:property /></option>
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
			    <td><textarea id="createPlayList_playListDescrition" name="playList.description"><s:property value="playList.description"/></textarea></td>
			</tr>
			
			<tr>
			    <td class="tdLabel"><label class="label" for="createPlayList_playListTags"><s:text name="vc.playlist.tag"/>:</label></td>
			    <td><input type="text" class="form-input" id="createPlayList_playListTags" name="tags" value="<s:iterator value="playList.tags"><s:property value="tag"/> </s:iterator>"></td>
			</tr>
			

			 <tr>
				 <td class="tdLabel">&nbsp;</td>
				 <td>
				 	<div align="left">
				 		<input onmouseover="this.src='<web.page:path/>/images/btn-createanaccount-hover.gif'" onmouseout="this.src='<web.page:path/>/images/btn-createanaccount.gif'" type="image" value="Submit" id="createUser_0" src="<web.page:path/>/images/btn-createanaccount.gif" alt="Submit"> 
				 		
				 	</div>
				 </td>
			 </tr>

		</tbody></table></form>
		
		
</body>
</html>