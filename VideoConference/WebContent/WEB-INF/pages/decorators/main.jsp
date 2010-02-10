<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="webpage" uri="/WEB-INF/tlds/pageview.tld"%>
<%@ taglib prefix="webpath" uri="/WEB-INF/tlds/path.tld"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"	prefix="decorator"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title><decorator:title/></title>

<link href="<webpath:path/>/css/common.css" rel="stylesheet" type="text/css"/>

<script type="text/javascript" src="<webpath:path/>/js/common.js"></script>
<script type="text/javascript" src="<webpath:path/>/js/jquery.js"></script>
 
<decorator:head />
</head>

<body id='<decorator:getProperty property="body.id"/>' <decorator:getProperty property="body.onload" writeEntireProperty="true" />>

<div>This is header</div>

<decorator:body />

<div>This is footer</div>



</body>
</html>

