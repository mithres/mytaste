var playListIDs = "";

function showMessage(message){
	alert(message);
}

$(document).ready(function() {
	if ($("#lzappContainer").length > 0) {
		$("#contentpanel").css("width", "100%");
	}
});
function flushValidateCode() {
	// set timeout is workaroud for update captcha in IE
	setTimeout(function() {
		$('#ccode').attr('src',	'signUp/captcha?_=' + parseInt(Math.random() * 1000 + 1));
	}, 100);
}


function findUserQueueCount(){
	
	var url = webPath+"/user/findQueueCount";
	
	$.ajax( {
		url : url,
		type : 'get',
		dataType : 'json',
		error : function(xml) {
			showMessage("Find queue count error.");
		},
		success : function(xml) {
			$('#queueCount').html("("+xml.count+")"); 
		}
	});
}

function addToQueue(pid){
	
	var param = "id="+pid;
	var url = webPath+"/vod/addToQueue";
	
	$.ajax( {
		url : url,
		data: param,
		type : 'post',
		dataType : 'json',
		error : function(xml) {
			showMessage("Add to queue error.");
		},
		success : function(xml) {
			$('#loading').animate({opacity:0});
			if(xml.success){
				//showMessage(xml.messages);
				var img = "<img title='You have queued this video.' style='display: block' src='"+webPath+"/images/icon-check.gif'>";
				$('#queueState'+pid).html(img);
			}else{
				$('#errorMessage').html(xml.errors); 
				flushValidateCode();
			}
		}
	});
	
}

function vidContents(id) {

	if (playListIDs.indexOf(id) == -1) {
		playListIDs = playListIDs + "," + id;
		var vidContent = '#vidContent' + id;
		$('#vid' + id)
				.bt(
						{
							trigger : ['hover'],
							contentSelector : "$('" + vidContent + "')",
							positions : [ 'right', 'left' ],
							clickAnywhereToClose : false,
							closeWhenOthersOpen : true,
							shrinkToFit : false,
							fill : '#F4F4F4',
							strokeStyle : '#666666',
							spikeLength : 20,
							spikeGirth : 10,
							width : 350,
							overlap : 0,
							centerPointY : 1,
							cornerRadius : 0,
							cssStyles : {
								fontFamily : '"Lucida Grande",Helvetica,Arial,Verdana,sans-serif',
								fontSize : '12px',
								padding : '10px 14px'
							},

							shadow : true,
							shadowColor : 'rgba(0,0,0,.5)',
							shadowBlur : 8,
							shadowOffsetX : 4,
							shadowOffsetY : 4
						});
	} else {
		// showMessage("不加载");
	}
}
