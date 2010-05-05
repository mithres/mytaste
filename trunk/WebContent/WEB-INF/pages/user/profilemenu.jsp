<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="web.page" uri="/WEB-INF/tlds/path.tld"%>


<ul id="profile-tab-container">
	<li
		class="<s:if test="type.equals('Main')">profile-tab-selected</s:if><s:else>profile-tab-unselected</s:else>">
	<div><s:if test="type.equals('Main')">Main</s:if><s:else>
		<a style="text-decoration: none;" href="<web.page:path/>/user/accountInfo">Main</a>
	</s:else></div>
	</li>
	<li
		class="<s:if test="type.equals('Queue')">profile-tab-selected</s:if><s:else>profile-tab-unselected</s:else>">
	<div><s:if test="type.equals('Queue')">Queue (<s:property value="count"/>)</s:if><s:else>
		<a style="text-decoration: none;" href="<web.page:path/>/user/queue">Queue (<s:property value="count"/>)</a>
	</s:else></div>
	</li>
	<li
		class="<s:if test="type.equals('Rate')">profile-tab-selected</s:if><s:else>profile-tab-unselected</s:else>">
	<div><s:if test="type.equals('Rate')">Tags</s:if><s:else>
		<a style="text-decoration: none;"
			href="#">Tags</a>
	</s:else></div>
	</li>
	<li
		class="<s:if test="type.equals('Payment')">profile-tab-selected</s:if><s:else>profile-tab-unselected</s:else>">
	<div><s:if test="type.equals('Payment')">Payment</s:if><s:else>
		<a style="text-decoration: none;" href="<web.page:path/>/user/payment">Payment</a>
	</s:else></div>
	</li>
	<li
		class="<s:if test="type.equals('Rate')">profile-tab-selected</s:if><s:else>profile-tab-unselected</s:else>">
	<div><s:if test="type.equals('Rate')">Setting</s:if><s:else>
		<a style="text-decoration: none;"
			href="#">Setting</a>
	</s:else></div>
	</li>
	<br style="clear: both;">
</ul>