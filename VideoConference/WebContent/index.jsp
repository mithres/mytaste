<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script type="text/javascript" src="js/flashembed.min.js"></script>
	
	<!-- some minimal styling, can be removed -->
	<link rel="stylesheet" type="text/css" href="css/common.css"/>

	<script>
	
	 /*
		use flashembed to place flowplayer into HTML element 
		whose id is "example" (below this script tag)
	 */
	 flashembed("example", 
	
		/* 
			first argument supplies standard Flash parameters. See full list:
			http://kb.adobe.com/selfservice/viewContent.do?externalId=tn_12701
		*/
		{
			src:'FlowPlayerDark.swf',
			width: 400, 
			height: 290
		},
		
		/*
			second argument is Flowplayer specific configuration. See full list:
			http://flowplayer.org/player/configuration.html
		*/
		{config: {   
			autoPlay: false,
			autoBuffering: true,
			controlBarBackgroundColor:'0x2e8860',
			initialScale: 'scale',
			videoFile: 'rtmp://172.0.2.193/vc/terminator.flv'
		}} 
	);
	</script>	


</head>
<body>
<div id="page">
	
	<h1>Simple Flowplayer <em>example</em></h1>

	<p>
		View source the page source to see how Flowplayer is installed.
	</p>
	
	<!-- this DIV is where your Flowplayer will be placed. it can be anywhere -->
	<div id="example"></div>

	<p>
		<button onClick="location.href='index.html'">&laquo; back to examples</button>
	</p>
	
</div>

</body>
</html>