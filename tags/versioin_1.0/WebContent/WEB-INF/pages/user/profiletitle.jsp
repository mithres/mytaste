<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="p" uri="/WEB-INF/tlds/photo.tld"%>
<%@ taglib prefix="web.page" uri="/WEB-INF/tlds/path.tld"%>


<div class="avatar"
	style="width: 150px; text-align: center; margin-bottom: 5px;"><a
	href="#"> <img title="Change your profile photo"
	<s:if test="userAccount.uploadedAvatar">src="<p:photo index="${userAccount.userIndex}" photoType="UserPhoto"/>"</s:if>
	<s:else>src="<web.page:path/>/images/avatar.png"</s:else> /> </a></div>
<div class="content">
<h1>Your Profile</h1>
<div style="margin-top: 20px;"><a
	href="<web.page:path/>/user/avatar" style="text-decoration: none;">[
Change your profile photo ]</a></div>
</div>
<div style="float: right; margin-top: 7px; width: 200px;"><span
	style="font-size: 90%;"> <a href="#">[ See how others view
your profile ]</a> </span></div>
