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
	
	

<div class="fluid title" id="lzappContainer">
  <div class="fixed-lg container">
      <div class="gr b ptop">
        <h2 class="cufonable">Enter play list details and update video file</h2>
       
      </div>
  </div>
</div>


<div style="position: relative;" class="fixed-lg relative container">
  
<div class="signup form-table-wrapper">
		
		<form method="post" action="<web.page:path/>/vod/savePlayList" enctype="multipart/form-data" name="createPlayList" id="createPlayList">
			<input type="hidden" name="playList.id" value="<s:property value="playList.id"/>"/>
			<table class="wwFormTable">
				<tbody>
				
				<tr>
				    <td class="tdLabel">&nbsp;</td>
				    <td class="tdContent"><span style="color:red"><s:actionerror/></span></td>
				</tr>
				
				<tr>
				    <td class="tdLabel">&nbsp;</td>
				    <td class="tdContent">Please note: Items marked with * are required.</td>
				</tr>
				
				<tr>
				    <td class="tdLabel"><label class="label" for="createPlayList_playListName"><s:text name="vc.playlist.name"/>:</label></td>
				    <td class="tdContent"> <input type="text" class="form-input" id="createPlayList_playListName" value="<s:property value="playList.playListName"/>" name="playList.playListName"><b class="required">&nbsp;*</b></td>
				</tr>
	
				<tr>
				    <td class="tdLabel"><label class="label" for="createPlayList_price"><s:text name="vc.playlist.price"/>:</label></td>
				    <td class="tdContent"><input type="text" class="form-input" id="createPlayList_price" value="<s:property value="playList.price"/>" name="price"></td>
				</tr>
				
				<tr>
				    <td class="tdLabel"><label class="label" for="createPlayList_channel">Channel:</label></td>
				    <td class="tdContent"><select class="form-select" id="createPlayList_channel" name="channel">
			    	<s:iterator value="channels" var="state">
			    		<option <s:if test="playList.channel.id.equals(id)">selected='selected'</s:if> value='<s:property value="id"/>'><s:property value="channelName"/></option>
			    		<s:iterator value="childChannels" var="subChannel">
			    			<option <s:if test="playList.channel.id.equals(id)">selected='selected'</s:if> value='<s:property value="id"/>'>&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="channelName"/></option>
						</s:iterator>
			    	</s:iterator>
			    </select>&nbsp;&nbsp;</td>
				</tr>
				
				<tr>
				    <td class="tdLabel"><label class="label" for="createPlayList_playListType"><s:text name="vc.playlist.type"/>:</label></td>
				    <td class="tdContent"><select class="form-select" id="createPlayList_playListType" name="playList.playListType">
			    	<s:iterator value="playListTypes" var="plt">
			    		<option <s:if test="#plt.equals(playList.playListType)">selected='selected'</s:if> value='<s:property/>'><s:property /></option>
			    	</s:iterator>
			    </select>&nbsp;&nbsp;</td>
				</tr>
	
				<tr>
				    <td class="tdLabel"><label class="label" for="createPlayList_fileTypes"><s:text name="vc.playlist.filmtype"/>:</label></td>
				    <td class="tdContent"><select class="form-select" id="createPlayList_fileTypes" name="playList.filmType">
			    	<s:iterator value="fileTypes" var="ft">
			    		<option <s:if test="#ft.equals(playList.filmType)">selected='selected'</s:if> value='<s:property/>'><s:property /></option>
			    	</s:iterator>
			    </select>&nbsp;&nbsp;</td>
				</tr>
				
				<tr>
				    <td class="tdLabel"><label class="label" for="createPlayList_playListTags"><s:text name="vc.playlist.tag"/>:</label></td>
			   		<td class="tdContent"><input type="text" class="form-input" id="createPlayList_playListTags" name="tags" value="<s:iterator value="playList.tags"><s:property value="tag"/> </s:iterator>">&nbsp;&nbsp;</td>
				</tr>
				
				<tr>
				    <td class="tdLabel"><label class="label" for="createPlayList_playListDescrition"><s:text name="vc.playlist.description"/>:</label></td>
			    	<td class="tdContent"><textarea class="form-textarea" id="createPlayList_playListDescrition" name="playList.description"><s:property value="playList.description"/></textarea>&nbsp;&nbsp;</td>
				</tr>
	
				<tr>
				    <td class="tdLabel"><label class="label" for="createPlayList_screenShot"><s:text name="vc.playlist.screenshot"/>:</label></td>
				    <td class="tdContent"><input class="form-file" type="file" id="createPlayList_screenShot" name="screenShot">&nbsp;&nbsp;</td>
				</tr>
	
				<tr>
				    <td class="tdLabel"><label class="label" for="createPlayList_film"><s:text name="vc.playlist.filmfile"/>:</label></td>
			   		<td class="tdContent"><input class="form-file" type="file" id="createPlayList_film" name="film">&nbsp;&nbsp;</td>
				</tr>

				 <tr>
				 	 <td class="tdLabel">&nbsp;</td>
					 <td class="tdContent">
					 	<input type="submit" value="Save" class="ui-button ui-widget ui-state-default ui-corner-all">
					 </td>
				 </tr>
			</tbody>
		</table>
		</form>
</div>


</div>


		
		
</body>
</html>