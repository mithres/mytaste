<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="/WEB-INF/tlds/c.tld"%>

<div id="base-middle-white"><br />
<div class="conditionDiv">Video Type: <select
	id="vt"
	onchange="searchPlayListByCondition('<s:property value="action"/>','<s:property value="timeFrame"/>');">
	<option value="All">All</option>
	<s:iterator value="playListTypes" var="plt">
		<option <s:if test="#plt.equals(vt)">selected='selected'</s:if>
			value='<s:property/>'><s:property /></option>
	</s:iterator>
</select></div>
<div class="conditionDiv">Channel: <select
	id="channel" onchange="searchPlayListByCondition('<s:property value="action"/>','<s:property value="timeFrame"/>');">
	<option value="All">All</option>
	<s:iterator value="channelList">
		<option value="<s:property value="id"/>" <s:if test="id.equals(channel)">selected='selected'</s:if>><s:property value="channelName" /></option>
	</s:iterator>
</select></div>


<div id="subChannelDiv" class="conditionDiv"  <s:if test="subChannelList.size() > 0">style="opacity : 100;"</s:if><s:else>style="opacity : 0;"</s:else>>
SubChannel:<select id="subChannels" onchange="searchPlayListByCondition('<s:property value="action"/>','<s:property value="timeFrame"/>');">
<option value="All">All</option>
<s:iterator value="subChannelList">
	<option value="<s:property value="id"/>" <s:if test="id.equals(subChannel)">selected='selected'</s:if>><s:property value="channelName" /></option>
</s:iterator>
</select>
</div>
	
<br>
<br>
</div>
