$(document).ready(function(){
});


function submitScores(){
	var cmissionid = $("#reportMark").attr("cmissionid");
	var cmreportid = $("#reportMark").attr("cmreportid");
	$cmcreditBoxes = $("#editBox4mark").find(".cmcreditBox");
	var inputdata ='{"cmreportid":'+cmreportid+',"cmcredits":[';
	var idSet = new Array(), cstudentidSet = new Array(), creditSet=new Array(),descriptionSet = new Array();
	var insertNum =0;
	for(var i=0;i<$cmcreditBoxes.length;i++){
		$thisCmcreditBox = $cmcreditBoxes.eq(i);
		var id = $thisCmcreditBox.attr("id");
		idSet[i] = id.substr(id.search('-')+1);
		cstudentidSet[i] = $thisCmcreditBox.attr("sid");
		creditSet[i] = $thisCmcreditBox.find(":input.score").val();
		descriptionSet[i] = $thisCmcreditBox.find("textarea.reportNote").val();
		//idSet[i]
	if(creditSet[i] !="" && creditSet[i] != null){
		
		if(insertNum >0){
			inputdata += ',';
		}
		inputdata += '{';
		if(idSet[i] != "" && idSet[i] != null){
			inputdata += '"id":'+idSet[i]+',';
		}
		inputdata += '"cstudentid":'+cstudentidSet[i]+',"credit":'+creditSet[i]+',"description":"'+descriptionSet[i]+'","cmissionid":'+cmissionid+'}';
		insertNum += 1;
	}else{
		if(idSet[i] != "" && idSet[i] != null){
			alert("无法为已评分学生赋空值！");
			showViewBox4mark();
			return;
		}
	}
	}
	inputdata += ']}';
	alert(inputdata);
	$.ajax({
		cache:true,
		type:"POST",
		url:"/classInfo/tch/saveCmcredits.action",
		contentType: 'application/json; charset=utf-8',
		data: inputdata,
		//请求失败的处理函敄1�7
		error: function (request, message, ex) {
			alert(request.responseText);
			
		},
		//请求成功的处理函数（此处成功只代表成功与后台连接，不代表后台数据处理成功
		success: function(data){
			if(data.ajaxResult == "success"){
				//*copy to view start
				addHtml = '';
				for(var i=0;i<$cmcreditBoxes.length;i++){
					//$thisCmcreditBox = $cmcreditBoxs.eq(i);
					if(creditSet[i] != "" && creditSet[i] != null){
					addHtml +='<div sid="'+cstudentidSet[i]+'" class="cmcreditBox">\
						<div class="reportMark-line h30">\
					<div class="clear"></div>\
					<span class="mr10 w100 fl" name="stuName">'+$cmcreditBoxes.eq(i).find("span[name='stuName']").html()+'</span>\
					<div class="pBox-input">分数:<span name="credit">'+creditSet[i]+'</span></div>\
				</div>\
				<div>备注:<span name="description">'+descriptionSet[i]+'</span></div>\
			</div>';
				//	alert(addHtml);
				}
				}
				addHtml += '<div class="clear"></div>\
					<div class="tc h50 f16">\
				<input type="button" class="btn blue" value="硄1�7&nbsp讄1�7">\
				<input type="button" class="btn green" value="俄1�7&nbsp攄1�7" onclick="showEditBox4mark()">\
			</div>';
				$("#viewBox4mark").html(addHtml);
				//*copy to view end
				showViewBox4mark();
				
				var cmCreditDetailViews = data.cmcreditDetailViews;
				for(var i=0;i<cmCreditDetailViews.length;i++){
					var sidstr = "[sid='"+cmCreditDetailViews[i].cstudentid+"']";
					var idstr = "cmcredit-"+cmCreditDetailViews[i].id;
					//alert("test");
					//alert($(sidstr).parent().html());
					$(sidstr).attr("id",idstr);
				}
				
				$("#btnCancleSubmitMark").attr("onclick","showViewBox4mark();");
				}else{
					alert("评分保存出现错误＄1�7");
				}
			}
	});	
	
}

function showEditBox4mark(){
	$cmcreditBoxes4View = $("#viewBox4mark").find(".cmcreditBox");
	$cmcreditBoxes4Edit = $("#editBox4mark").find(".cmcreditBox");
	//*copy view to edit start
	for(var i=0;i<$cmcreditBoxes4View.length;i++){
		$thisCmcreditBox4Edit = $cmcreditBoxes4Edit.eq(0);
		$thisCmcreditBox4View = $cmcreditBoxes4View.eq(0);
		$thisCmcreditBox4Edit.find("span[name='stuName']").html($thisCmcreditBox4View.find("span[name='stuName']").html());
		$thisCmcreditBox4Edit.find(":input.score").val($thisCmcreditBox4View.find("span[name='credit']").text());
		$thisCmcreditBox4Edit.find("textarea.reportNote").val($thisCmcreditBox4View.find("span[name='description']").text());
	}
	//*copy view to edit end
	$("#viewBox4mark").fadeOut(500,function(){
		$("#editBox4mark").fadeIn();
	});
}

function showViewBox4mark(){
	$("#editBox4mark").fadeOut(500,function(){
		$("#viewBox4mark").fadeIn();
	});
}


function slideDownNote(e){
	$(e).text("收起修改").attr("onclick","slideUpNote(this);");
	$thisCmcredit = $(e).parents().filter(".cmcreditBox");
	$thisCmcredit.find("textarea.reportNote").slideDown();
	
}

function slideUpNote(e){
	$(e).text("编辑备注").attr("onclick","slideDownNote(this);");
	$thisCmcredit = $(e).parents().filter(".cmcreditBox");
	$thisCmcredit.find("textarea.reportNote").slideUp();

	}

function showFile(e){
	$("[name='fileShowLink']").removeClass("fb");
	$(e).addClass("fb");
	var swfPath = $(e).attr("swfPath");
	if(swfPath == "" || swfPath == null){
		//if swfPath == null
		inputdata ='{"cmreportItemid":'+$(e).attr("itemid")+'}';
		$.ajax({
			cache:true,
			type:"POST",
			url:"/classInfo/tch/viewReportItemOnline.action",
			contentType: 'application/json; charset=utf-8',
			data: inputdata,
			//请求失败的处理函敄1�7
			error: function (request, message, ex) {
				alert(request.responseText);
				
			},
			//请求成功的处理函数（此处成功只代表成功与后台连接，不代表后台数据处理成功
			success: function(data){
				if(data.ajaxResult == "success"){
					swfPath = data.swflocation;
					alert(swfPath);
					flexPaperUtil(swfPath);
					$(e).attr("swfPath",swfPath);
					}else{
						alert("文件加载出现错误＄1�7");
					}
				}
		});	

	}
	else{
		flexPaperUtil(swfPath);
	}//}
}

function flexPaperUtil(swfPath){
	$("#documentViewer").FlexPaperViewer({
		config : {

			SWFFile : swfPath,
			jsDirectory: 'js/flexpaper/',
			Scale : 0.6,
			ZoomTransition : 'easeOut',
			ZoomTime : 0.5,
			ZoomInterval : 0.2,
			FitPageOnLoad : false,
			FitWidthOnLoad : true,
			FullScreenAsMaxWindow : false,
			ProgressiveLoading : false,
			MinZoomSize : 0.2,
			MaxZoomSize : 5,
			SearchMatchAll : false,
			InitViewMode : 'Portrait',
			RenderingOrder : 'flash',
			StartAtPage : '',

			ViewModeToolsVisible : true,
			ZoomToolsVisible : true,
			NavToolsVisible : true,
			CursorToolsVisible : true,
			SearchToolsVisible : true,
			WMode : 'window',
			localeChain : 'zh_CN'
		}
	});
}