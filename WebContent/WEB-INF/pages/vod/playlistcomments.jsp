<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="web.page" uri="/WEB-INF/tlds/path.tld"%>
<%@ taglib prefix="paginator" uri="/WEB-INF/tlds/pageview.tld"%>


<div id="reviewDiv" style="display: none;">
	<span style="font-weight: bold; font-size: 16px;">My Review</span>

	<div class="review-title">Tell the Video Share community what you really think about this video. But while we encourage you to share your
	opinion, please treat other users with respect. Avoid spoilers,profanity, off-topic subjects and hostile remarks. It may take a few minutes for your review to appear.</div>

	<form id="reviewForm" method="post" action="<web.page:path/>/vod/addReview">
		<input type="hidden" name="playListId" value="<s:property value="playList.id"/>"/>
		<table class="reviewFormTable">
			<tbody>
				<tr>
					<td class="tdLabel"><label class="label" for="title">Title of review:</label></td>
					<td><input type="text" class="form-input" id="title"
						name="title"></td>
				</tr>
		
				<tr>
					<td class="tdLabel"><label class="label" for="message">Review message:</label></td>
					<td><textarea class="form-textarea" id="message" name="message"></textarea></td>
				</tr>
		
				<tr>
					<td class="tdLabel">Tags (Optional):</td>
					<td>Click on the tags you think are most appropriate for this video, or add your own in the field below.</td>
				</tr>
		
				<tr>
					<td class="tdLabel">&nbsp;</td>
					<td>
						<s:iterator value="playList.tags">
							<input type="checkbox" name="tags" value="<s:property value="tag"/>" /><s:property value="tag"/>
						</s:iterator>
					</td>
				</tr>
		
				<tr>
					<td class="tdLabel">Add Tags:</td>
					<td> <input type="text" class="form-input" id="tag" name="userTags" /></td>
				</tr>
		
				<tr>
					<td class="tdLabel">&nbsp;</td>
					<td>
						<div align="left">
							<input type="button" name="create" value="create" onclick="addReview('reviewForm','<s:property value="playList.id"/>');"/> <input type="button" name="cancel" value="cancel" onclick="hideReview();"/>
						</div>
					</td>
				</tr>
				
			</tbody>
		</table>
	</form>
</div>
<div id="reviews" class="comments">
	<s:iterator value="videoComments.records">
		<div class="comment">
			<div class="bar">
				<a name="" id="" target="_blank" href="#"><s:property value="author.firstName" /> <s:property value="author.lastName" /></a>&nbsp;&nbsp;<s:date name="createdTime" nice="true" />
			</div>
			<div class="content">
				<span><s:property value="content" /><br></span>
			</div>
		</div>
	</s:iterator>
	
	<paginator:page totalCount="${videoComments.recordTotal}"
	pageCount="${pageCount}" currentPage="${pageNumber}" theme="ajax"
	render="reviews" action="/vod/showComments" className="pageable-div pagination"
	innerStyle="margin-top: 50px; float: left;" />
</div>