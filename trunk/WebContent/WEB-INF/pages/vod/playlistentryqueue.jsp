
<div style="float:left;width:100%;margin:2px;">
<div style="float:left;width:145px;height:80px;">
	<img height="80" width="145" alt="<s:property value="playList.playListName"/>" src="<p:photo index="${playListQueue.playList.playListIndex}" photoType="FilmScreenShot"/>">
</div>
<div style="float:left;width:750px;">
	<table class="playListTable">
		<tr>
			<td width="150px;">Type</td>
			<td width="250px;">Channel</td>
			<td width="150px;">Video Title</td>
			<td width="150px;">View</td>
			<td width="150px;">Price</td>
			<td >Delete</td>
		</tr>
		<tr>
			<td class="content"><s:property value="playList.playListType"/></td>
			<td class="content"><s:property value="playList.channel.channelName"/></td>
			<td class="content"><s:property value="playList.fileName"/></td>
			<td class="content"><s:property value="playList.viewCount"/></td>
			<td class="content"><s:property value="playList.price"/></td>
			<td class="content">
				<a title="Remove this video from your queue" href="javascript:void(0);" onclick="removeFromQueue('<s:property value="playList.id"/>',<s:property value="getPageNumber()"/>,<s:property value="getPageCount()"/>);">
					<img src="<web.page:path/>/images/icon-delete-remove.gif" style="cursor:pointer;" onmouseover="this.src='<web.page:path/>/images/icon-delete-remove-hover.gif'" onmouseout="this.src='<web.page:path/>/images/icon-delete-remove.gif'"/>
				</a>	
			</td>
		</tr>
	</table>
</div>
</div>