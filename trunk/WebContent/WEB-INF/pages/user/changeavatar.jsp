<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="p" uri="/WEB-INF/tlds/photo.tld"%>
<%@ taglib prefix="web.page" uri="/WEB-INF/tlds/path.tld"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Video Share - Change profile photo</title>
</head>
<body>
	
<div class="fluid title" id="lzappContainer">
  <div class="fixed-lg container">
      <div class="gr b ptop">
        <h2 class="cufonable">Upload your profile photo</h2>
        <h4 style="margin-top: 5px;" class="subtag">
          Create a profile picture to customize your Video Share profile and help your contacts find you through Video Share friends.

        </h4>
        <h4 style="margin-top: 5px;" class="subtag">
          Click "Browse" to select an image, and then click on the "upload" button.
        </h4>
      </div>
  </div>
</div>


<div style="text-align: center; margin-bottom: 35px;margin-top:50px;" class="standard tidy">
      
        
        <div style="margin: 1em;">
			<img 
			<s:if test="userAccount.uploadedAvatar">src="<p:photo index="${userAccount.userIndex}" photoType="UserPhoto"/>"</s:if><s:else>src="<web.page:path/>/images/avatar.png"</s:else>/>
        </div>
        <s:actionerror/>
        <form method="post" id="avatar_upload_form" enctype="multipart/form-data" action="<web.page:path/>/user/saveAvatar">
          <div style="margin: 20px;"><input type="file" name="photo" id="photo"></div>
          <div style="margin: 20px auto; width: 350px; font-size: 11px; text-align: left;">
            You can upload a JPG, GIF or PNG file.  For best results, images should be at least 128p wide and 128p high.
            File size limit is 3 MB.  If your upload doesn't work, please try a smaller image.
          </div>
          <div style="margin: 20px auto; width: 350px; font-size: 10px; text-align: left;">
            By uploading an image you ceritfy that you have the right to distribute this image and that it does not violate the 
            <a href="#">Terms of Use</a>.
          </div>
          <input type="submit" name="Upload" value="Upload" />
          <input type="button" name="Cancel" value="Cancel" />
        </form>
     
    </div>
    
    
    
<div style="position: relative;" class="fixed-lg relative container">
	<div  class="avatar" style="width:100px;text-align:center;">
		
	</div>
</div>


</body>
</html>