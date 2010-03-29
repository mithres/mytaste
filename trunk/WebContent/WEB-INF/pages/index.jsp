<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page
	import="org.springframework.security.ui.AbstractProcessingFilter"%>
<%@ page import="org.springframework.security.AuthenticationException"%>

<%@ taglib prefix="web.page" uri="/WEB-INF/tlds/path.tld"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="maindecorator" />
<title><s:text name="vc.home.title" /></title>
</head>
<body>





<div class="cp flt">
				<div class="cp_box flt">
					<div class="cp_align flt">
						<img src="<web.page:path/>/images/cp_bul.jpg" width="23" height="20" alt="" class="cp_bul flt" />
						<span class="cp_video flt">VIDEO <font color="#CDCDCD">OF THE WEEK</font></span>
					</div>
					<span class="cp_we flt">We put up crazy, sexy funny videos every single day, all day. Make sure to check back to see the newest updates!
Praesent vel quam vel nunc ultrices molestie. </span>
					<s:iterator value="playListsOfWeek">
						<div class="cp_align flt">
							<img src="<web.page:path/>/images/cp_img1.jpg" width="164" height="108" alt=" " class="cp_img1 flt" />
							<div style="float:left; width:410px; margin-left:50px;">
								<span class="cp_acro flt"><s:property value="playListName" /></span>
								<div style="float:left; width:410px;">
									<span class="cp_acro flt">Category:</span>
									<div style="float:left; width:200px;">
										<span class="cp_ext flt"><s:property value="category.categoryName"/>   |</span>
										<img src="<web.page:path/>/images/cp_arr.jpg" width="9" height="13" alt="" class="cp_arr flt" />
										<span class="cp_ext flt">(16)</span>
										<img src="<web.page:path/>/images/cp_arr1.jpg" width="9" height="13" alt="" class="cp_arr flt" />
										<span class="cp_ext flt">(2)     </span>
									</div>										
								</div>
								<div class="cp_acro flt" style="font:11px Arial, Helvetica, sans-serif; width:390px;"><b>Description:</b> &nbsp; <s:property value="description"/></div>
								<span class="cp_acro flt" style="font:11px Arial, Helvetica, sans-serif; width:390px;"><b>Views:</b>&nbsp;  <s:property value="viewCount"/>  |  Added: <s:date name="addedTime" nice="true"/>   </span>
							</div>
						</div>
					</s:iterator>
				</div>
				
				<div class="cp_box1 flt">
					<div class="cp_align flt">
						<img src="<web.page:path/>/images/cp_real.jpg"  width="21" height="21" alt="" class="cp_bul flt" />
						<span class="cp_video flt" style="color:#1F2A02;">NEW <font color="#EA3D00">VIDEOS</font></span>
					</div>
					<div class="cp_align flt">
						<img src="<web.page:path/>/images/cp_left.jpg" width="12" height="17" alt="" class="cp_left flt" />
						<img src="<web.page:path/>/images/cp_bimg1.jpg" width="128" height="77" alt="" class="cp_bimg1 flt" />
						<img src="<web.page:path/>/images/cp_bimg2.jpg" width="128" height="77" alt="" class="cp_bimg1 flt" />
						<img src="<web.page:path/>/images/cp_bimg3.jpg" width="128" height="77" alt="" class="cp_bimg1 flt" />
						<img src="<web.page:path/>/images/cp_bimg4.jpg" width="128" height="77" alt="" class="cp_bimg1 flt" />																		
						<img src="<web.page:path/>/images/cp_right.jpg" width="12" height="19" alt="" class="cp_left flt" />						
					</div>
					<div class="cp_align flt">
						<img src="<web.page:path/>/images/cp_gbul.jpg" width="3" height="5" align="" class="cp_gbul flt" />
						<a href="#" class="cp_nulla flt"><u>Nulla viverra neque</u> From: non tortor.</a>
						<img src="<web.page:path/>/images/cp_gbul.jpg" width="3" height="5" align="" class="cp_gbul flt" />
						<a href="#" class="cp_nulla flt"><u>Nulla viverra neque</u> From: non tortor.</a>
						<img src="<web.page:path/>/images/cp_gbul.jpg" width="3" height="5" align="" class="cp_gbul flt" />
						<a href="#" class="cp_nulla flt"><u>Nulla viverra neque</u> From: non tortor.</a>
						<img src="<web.page:path/>/images/cp_gbul.jpg" width="3" height="5" align="" class="cp_gbul flt" />
						<a href="#" class="cp_nulla flt"><u>Nulla viverra neque</u> From: non tortor.</a>
					</div>
				</div>
				<div class="cp_align flt">
					<div class="cp_tab flt">
						<a href="#" class="cp_fea flt">Featured</a>
						<a href="#" class="cp_feas flt">Most Viewed<img src="<web.page:path/>/images/cp_arrows.jpg" width="10" height="7" alt="" /></a>
						<a href="#" class="cp_feas flt">Most Discussed<img src="<web.page:path/>/images/cp_arrows.jpg" width="10" height="7" alt="" /></a>
						<a href="#" class="cp_feas flt">Top Favorites<img src="<web.page:path/>/images/cp_arrows.jpg" width="10" height="7" alt="" /></a>
						<a href="#" class="cp_feas flt">Top Rated<img src="<web.page:path/>/images/cp_arrows.jpg" width="10" height="7" alt="" /></a>
						<a href="#" class="cp_feas flt">Random<img src="<web.page:path/>/images/cp_arrows.jpg" width="10" height="7" alt="" /></a>
					</div>
				</div>
				
				<s:iterator value="mostViewedPlayLists" var="playList">
					<%@include file="vod/playlistentry.jsp" %>
				</s:iterator>
				
				
</div>
			<div class="rp flt">
				<div class="rp_box flt">
					<div class="rp_top flt">
						<span class="rp_mem flt">MEMBERS LOGIN</span>
					</div>
					<div class="rp_bg flt">
						<div class="rp_align flt">
						<form  action="<web.page:path/>/signIn" method="POST">
							<table width="205">
								<tr>
									<td class="rp_user flt"><s:text name="vc.index.username" /></td>
									<td><input class="rp_txt flt" type='text' name='userName' value="" /></td>
								</tr>
								<tr>
									<td class="rp_user flt"><s:text name="vc.index.pwssword" /></td>
									<td><input class="rp_txt flt" type='password' name='password' AUTOCOMPLETE="off" /></td>
								</tr>
								<tr>
									<td class="rp_user flt"><s:text name="vc.index.input_checkcode" /></td>
									<td><input class="rp_txt flt" type='text' name='ccode' value="" /></td>
								</tr>
								<tr>
									<td colspan="2"><a href="javascript:void(0);" onClick="flushValidateCode();" title='<s:text name="vc.index.reload_checkcode" />'><img id="ccode" src="signUp/captcha" border="0"/></a></td>
								</tr>
								<tr>
									<td colspan='2'>
									<input type="image" src="<web.page:path/>/images/rp_login.jpg"  name='<s:text name="vc.button.submit" />' />									</td>
								</tr>
						  </table>
							
						  </form>
						
						</div>
						
						<span class="rp_not flt">Not Yet a Member? <a href="<web.page:path/>/signUp/signUpIndex" style="color:#000000;">Sign-up</a></span>
						<span class="rp_not flt" style="margin-top:4px;">Forget username or password? <a href="#" style="color:#000000;">Click here</a></span>
					</div>
					<img src="<web.page:path/>/images/rp_bot.jpg" width="264" height="8" alt="" class="rp_bot flt" />
				</div>
				
				<div class="rp_box flt">
					<div class="rp_top1 flt">
						<span class="rp_mem flt">VIDEO CATEGORIES</span>
					</div>
					<div class="rp_bg1 flt">
						<div class="rp_align flt">
							<s:iterator value="categories">
								<img src="<web.page:path/>/images/rp_bul.jpg" width="9" height="5" alt="" class="rp_bul flt" />
								<a href="#" class="rp_funn flt"><s:property value="categoryName"/></a>
							</s:iterator>
						</div>
					</div>
					<img src="<web.page:path/>/images/tp_bot1.jpg" width="264" height="15" alt="" class="rp_bot flt" />
				</div>
				<div class="rp_box flt">
					<div class="rp_top2 flt">
						<span class="rp_mem flt">VIDEO HIGHLIGHTES</span>
					</div>
					<div class="rp_bg2 flt">
						<div class="rp_align flt">
							<img src="<web.page:path/>/images/rp_img1.jpg" width="42" height="43" alt=" " class="rp_img1 flt" />
							<span class="rp_viv flt">Vivamus convallis samet turpis.</span>
							<a href="#"><img src="<web.page:path/>/images/rp_botbul.jpg" width="21" height="20" alt=" " class="rp_botbul flt" /></a>
						</div>
						<div class="rp_align flt">
							<img src="<web.page:path/>/images/rp_img2.jpg" width="42" height="43" alt=" " class="rp_img1 flt" />
							<span class="rp_viv flt">Nulla non lectus vel faucibus facilisis.</span>
							<a href="#"><img src="<web.page:path/>/images/rp_botbul.jpg" width="21" height="20" alt=" " class="rp_botbul flt" /></a>
						</div>
					</div>
					<img src="<web.page:path/>/images/rp_bot2.jpg"  width="264" height="13" alt="" class="rp_bot flt" />
				</div>
				
			</div>		






<%
	if (session
			.getAttribute(AbstractProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY) != null) {
		out
				.println(((AuthenticationException) session
						.getAttribute(AbstractProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY))
						.getMessage());
	}
%>

</body>
</html>