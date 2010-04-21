<%@ taglib prefix="s" uri="/struts-tags"%>

<div id="base-middle-white"><br>
Video Type: <select id="vt"><option value="All">All</option><s:iterator value="playListTypes"><option value="<s:property />"><s:property /></option></s:iterator></select> 
Channel: <select name="channel"><option value="All">All</option><s:iterator value="channelList"><option value="<s:property value="id"/>"><s:property value="channelName" /></option></s:iterator></select>
<br><br>
</div>
