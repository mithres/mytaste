var playListID="ff80818126750e150126750fa6640001,";

function flushValidateCode() {
	// set timeout is workaroud for update captcha in IE
	setTimeout(function() {
	$('#ccode').attr('src','signUp/captcha?_=' + parseInt(Math.random() * 1000 + 1));}, 100);
}

function vidContents(id){
/**	alert(id);
	alert(id.indexOf(playListID));
	alert(playListID);
	
	if (id.indexOf(playListID)==-1){
		if(playListID==","){
			playListID = id;
		}
		playListID = playListID+","+id;**/
		var vidContent = '#vidContent'+id;
		$('#vid'+id).bt({
			trigger: ['mouseover','click'],
			contentSelector: "$('"+vidContent+"')",
			positions: ['right', 'left'],
			clickAnywhereToClose: true,              
		    closeWhenOthersOpen: true, 
		    shrinkToFit: false,
			fill: '#F4F4F4',
			strokeStyle: '#666666', 
			spikeLength: 20,
			spikeGirth: 10,
			width: 350,
			overlap: 0,
			centerPointY: 1,
			cornerRadius: 0, 
			cssStyles: {
				fontFamily: '"Lucida Grande",Helvetica,Arial,Verdana,sans-serif', 
				fontSize: '12px',
				padding: '10px 14px'
			},
			//showTip: function(box){
			    //$(box).fadeIn(500);
			//},
			//hideTip: function(box, callback){
			    //$(box).animate({opacity: 0}, 500, callback);
			//},
			
			shadow: true,
			shadowColor: 'rgba(0,0,0,.5)',
			shadowBlur: 8,
			shadowOffsetX: 4,
			shadowOffsetY: 4
		});
		/**	}else{
		alert("不加载");
	}**/
}