var resort = true;
tablePager("#unGroupedTable",'#unGroupedTablePager');
resetForm();
/**
 *courseGroups.jsp页面初始化函敄1�7
 */
$(document).ready(function(){
	//	隐藏新增小组/添加到组等操作框
   	$(".metaBox").hide();
   	
   	//  切换显示新增小组操作桄1�7
	$("#newGroup").click(function(){
   		$("#addToGroupBox").slideUp(500,function(){
   			$("#newGroupBox").slideToggle();
   		});
		
	});
	
	//	隐藏修改学生小组操作桄1�7
	$(".editFormBox").hide();
	
	//	隐藏小组组员详情桄1�7
	$(".groupContext").hide();
});
/**
 * 显示编辑学生分组操作桄1�7
 * 异步请求现有小组列表
 */
function showEditForm(sid,e){
	var $this=$(e);
	var inputdata = '{"cmissionid":'+cmissionid+'}';
	var editFormId ="#editForm-"+sid+" select";
	$.ajax({
		cache : true,
		type:"POST",
		url : "/classInfo/tch/getCmgroups.action",//此处不能使用单引号代替双引号
		//dataType : 'json',
		contentType : 'application/json; charset=utf-8',
		data : inputdata,
		error: function (request, message, ex) {
		    alert(request.responseText);
		},
		success : function(data) {
			if(data.ajaxResult=="success"){
				$("#editBox4Groups").fadeOut();
				$("#viewBox4Groups").fadeOut();
				$("#modifyGroups").fadeIn();
				resetForm();
				var inputHtml = '';
				for(var i = data.cmgroupNotinMDetailViews.length-1;i>=0;i--){
					inputHtml = inputHtml + '<option value="'+data.cmgroupNotinMDetailViews[i].id+'">'+data.cmgroupNotinMDetailViews[i].name+'</option> ';
				}
				for(var i = data.cmgroupinMDetailViews.length-1;i>=0;i--){
					inputHtml = inputHtml + '<option value="'+data.cmgroupinMDetailViews[i].id+'">'+data.cmgroupinMDetailViews[i].name+'</option> ';
				}
				$(editFormId).html(inputHtml);
			}
		}
		});
	$this.fadeOut(100,function(){
		$this.siblings(".editFormBox").fadeIn(100);
	});
}

/**
 * 隐藏编辑学生分组操作桄1�7
 * @param sid
 * @param e
 */
function hideEditForm(sid,e){
	var $this=$(e);
	$this.parents(".editFormBox").fadeOut(100,function(){
		$this.parents(".editFormBox").siblings(".editFormLink").fadeIn(100);
	});
}

/**
 * 新增丄1�7个个组，将一个或多个未分组学生增加到组中
 */
function newGroup(){
	var groupName = $("#newGroupName").val() ;
	var studentids = new Array();
	for(var i = 0; i<$("#ungroupedStudent").find("input:checkbox:checked").length;i++){
		studentids[i]=$("#ungroupedStudent").find("input:checkbox:checked").eq(i).val();
	}
	var cstudentidsList='{"cmstudentids":[';
	for(var j = 0; j<studentids.length;j++){
		if(j>0){
			cstudentidsList=cstudentidsList+',';
		}
		cstudentidsList = cstudentidsList+studentids[j];
	}
	cstudentidsList =cstudentidsList+'],"cmgroupName":"'+groupName+'","cmissionid":'+cmissionid+'}';
	
	resetForm();
	$.ajax({
		cache : true,
		type:"POST",
		url : "/classInfo/tch/newCmgroup.action",//此处不能使用单引号代替双引号
		//dataType : 'json',
		contentType : 'application/json; charset=utf-8',
		data : cstudentidsList,
		error: function (request, message, ex) {
		    alert(request.responseText);
		},
		success : function(data) {
			if(data.ajaxResult=="success"){
				resetForm();
				writeAIngroupInDom(data.addedCmgroupDetailView);
				for(var j = 0; j<studentids.length;j++){
					var trid="#tr-"+studentids[j];
					$(trid).remove();
				}
				tableGap();
				autoIndex();
			}
		}
		});
}


/**
 * 将一个或多个未分组学生增加到已有组中
 */
function addStudentsToGroup(){
	var groupid= $("#selectGroupId").find("input:radio:checked").val();
	var studentids = new Array();
	for(var i = 0; i<$("#ungroupedStudent").find("input:checkbox:checked").length;i++){
		studentids[i]=$("#ungroupedStudent").find("input:checkbox:checked").eq(i).val();
	}
	var inputdata='{"cmstudentids":[';
	for(var j = 0; j<studentids.length;j++){
		if(j>0){
			inputdata=inputdata+',';
		}
		inputdata = inputdata+studentids[j];
	}
	inputdata =inputdata+'],"cmgroupid":"'+groupid+'"}';
	resetForm();
	$.ajax({
		cache : true,
		type:"POST",
		url : "/classInfo/tch/addToCmgroup.action",//此处不能使用单引号代替双引号
		//dataType : 'json',
		contentType : 'application/json; charset=utf-8',
		data : inputdata,
		error: function (request, message, ex) {
		    alert(request.responseText);
		},
		success : function(data) {
			if(data.ajaxResult=="success"){
				resetForm();
				var groupid = "#groupBox-"+data.addedCmgroupDetailView.id;
				$(groupid).remove();
				if(data.addedCmgroupDetailView.isinvolved){
					writeAIngroupInDom(data.addedCmgroupDetailView);
				}else{
					writeANotIngroupInDom(data.cmstudentDetailViews);
				}
				
				for(var j = 0; j<studentids.length;j++){
					var trid="#tr-"+studentids[j];
					$(trid).remove();	
				}

				tableGap();
				autoIndex();
			}
		}
		});
	
	
}

/**
 * 将一个未分组学生增加到已有组丄1�7
 * @param sid 学生主键
 * @param e this
 * @author msl
 */
function addAstudentToGroup(sid,e){
		var $this=$(e);
		var selectid="#select-"+sid;
		groupid=$(selectid).val();
		var inputdata='{"cmstudentids":['+sid;
		inputdata =inputdata+'],"cmgroupid":"'+groupid+'"}';
	var trid="#tr-"+sid;
	$.ajax({
		cache : true,
		type:"POST",
		url : "/classInfo/tch/addToCmgroup.action",//此处不能使用单引号代替双引号
		//dataType : 'json',
		contentType : 'application/json; charset=utf-8',
		data : inputdata,
		error: function (request, message, ex) {
		    alert(request.responseText);
		},
		success : function(data) {
			if(data.ajaxResult=="success"){
				resetForm();
				var groupid = "#groupBox-"+data.addedCmgroupDetailView.id;
				$(groupid).remove();
				if(data.addedCmgroupDetailView.isinvolved){
					writeAIngroupInDom(data.addedCmgroupDetailView);
				}else{
					writeANotIngroupInDom(data.cmstudentDetailViews);
				}
				
				$(trid).remove();	//在未分组学生列表中移除该组1�7	
				tableGap();
				autoIndex();
			}
		}
		});
	
}

/**
 * 将一个学生设为组镄1�7
 * 
 * @param studentid
 * @param groupid
 * @param e
 */
function setGroupLeader(studentid,groupid,e){
	var $this=$(e);
	var groupleaderid= "#groupLeader-"+groupid;
	var originalGroupleader = $(groupleaderid).parents("tr").attr("id");
	var originalGroupleaderId = originalGroupleader.replace(/tr-/,"");		
	var inputdata = '{"cmgroupid":'+groupid+',"leaderid":'+studentid+'}';
	$.ajax({
		cache : true,
		type:"POST",
		url : "/classInfo//tch/setCmgroupLeader.action",//此处不能使用单引号代替双引号
		//dataType : 'json',
		contentType : 'application/json; charset=utf-8',
		data : inputdata,
		error: function (request, message, ex) {
		    alert(request.responseText);
		},
		success : function(data) {
			resetForm();
			
			console.log();
			$("#groupBox-"+groupid).find("span[name='groupMembers']").eq(0).html(data.cmgroupDetailView.count);
			alert(groupid);
			$(groupleaderid).html('<a href="javascript:void();" onclick="setGroupLeader('+originalGroupleaderId+','+groupid+',this)">设为组长</a>').removeAttr("id");
			$this.parent().attr("id","groupLeader-"+groupid);
			$(groupleaderid).html('组长');
			$("#groupBox-"+groupid).find("span[name='groupLeaderName']").eq(0).html(data.cmgroupDetailView.leaderName);
		}
		});
	
	//alert($("#groupBox-"+groupid).find("[name='groupLeaderName'").html());
}

/**
 * 撤销丄1�7个个学生的分组1�7
 * 
 * @param studentid
 * @param groupid
 */
function cancelGroupedByStudentid(studentid,groupid){
	var trid="#tr-"+studentid;
	var inputdata = '{"cmgroupid":'+groupid+',"cmissionid":'+cmissionid+',';
		inputdata = inputdata + ' "cmstudentids":['+studentid+']}';
	
	$.ajax({
		cache : true,
		type:"POST",
		url : "/classInfo//tch/cancelCmstudentGrouped.action",//此处不能使用单引号代替双引号
		//dataType : 'json',
		contentType : 'application/json; charset=utf-8',
		data : inputdata,
		error: function (request, message, ex) {
		    alert(request.responseText);
		},
		success : function(data) {
			if(data.ajaxResult=="success"){
				resetForm();
				$(trid).remove();
				$("#groupBox-"+groupid).find("span[name='groupMembers']").eq(0).html($("#groupBox-"+groupid).find("span[name='groupMembers']").eq(0).html()*1-1);
				$("#unGroupedTable tbody").html("");
				for(var i =data.ungroupedCmstudentDetailViews.length-1;i>=0;i--){
					addAUngroupedStudent(data.ungroupedCmstudentDetailViews[i]);
				}
				tableGap();
				autoIndex();
			}
		}
		});
	
}

/**
 * 撤销丄1�7到多个分组1�7
 */
function cancleGroups(){
	var groupids = new Array();
	for(var i = 0; i<$("#groups").find("input:checkbox:checked").length;i++){
		groupids[i]=$("#groups").find("input:checkbox:checked").eq(i).val();
	}
	var inputdata = '{"cmgroupids":[';
	for(var h = 0;h<groupids.length;h++){
		if(h>0){
			inputdata = inputdata +",";
		}
		inputdata = inputdata +groupids[h];
	}
	 inputdata = inputdata + '],"cmissionid":'+cmissionid+'}';
	$.ajax({
		cache : true,
		type:"POST",
		url : "/classInfo/tch/cancelCmgroupsGrouped.action",//此处不能使用单引号代替双引号
		//dataType : 'json',
		contentType : 'application/json; charset=utf-8',
		data : inputdata,
		error: function (request, message, ex) {
		    alert(request.responseText);
		},
		success : function(data) {
			if(data.ajaxResult=="success"){
				resetForm();
				$("#unGroupedTable tbody").html("");
				for(var i =data.ungroupedCmstudentDetailViews.length-1;i>=0;i--){
					addAUngroupedStudent(data.ungroupedCmstudentDetailViews[i]);
				}
				for(var j = 0; j<groupids.length;j++){
					var gBoxId ="#groupBox-"+groupids[j];
					$(gBoxId).remove();	//在小组列表中移除该小组1�7
				}
				tableGap();
				autoIndex();
			}
		}
		
		});
	
}
/**
 * 获取小组成员列表
 * @param groupid
 * @param e
 */
function getGroupDetail(groupid,e){
	var $this=$(e);
	var inputdata = '{"cmgroupid":'+groupid+'}';
	if($this.parent().next(".groupContext").css("display") == "none"){
		$.ajax({
			cache : true,
			type:"POST",
			url : "/classInfo/tch/getCmgroupMemberDetial.action",//此处不能使用单引号代替双引号
			//dataType : 'json',
			contentType : 'application/json; charset=utf-8',
			data : inputdata,
			error: function (request, message, ex) {
			    alert(request.responseText);
			},
			success : function(data) {
				if(data.ajaxResult=="success"){
					resetForm();
					var groupTableId= "#groupTable-"+data.cgroupid;
					//alert($(groupTableId).find("tr").filter("[name='tableTitle']").html());
					$(groupTableId).find("tr").not("[name='tableTitle']").remove();
					var tableId='#groupTable-'+groupid+' tbody';
					$(tableId).html("");
					for(var i = data.cmstudentDetailViews.length-1;i>=0;i--){
						addAStudentToGroup(data.cmstudentDetailViews[i],data.leaderid,data.cmgroupid);
					}
					autoIndex();
				   	tableGap();
				}
			}
			});
		$this.html("┄1�7");
	}else{
		$this.html("╄1�7");
	}
	$this.parent().next(".groupContext").slideToggle();
}
/**
 * 
 * @param data
 * @param leaderid
 * @param groupid
 */
function addAStudentToGroup(data,leaderid,groupid){
	var inputdata ='';
	var tableId='#groupTable-'+groupid+' tbody';
	if(data.id==leaderid){
	 inputdata = '\
		<tr id="tr-'+leaderid+'">\
			<td>&nbsp</td>\
			<td>'+data.studentCode+'</td>\
			<td>'+data.name+'</td>\
			<td>'+data.studentGrade+'</td>\
			<td>'+data.stuClass+'</td>\
			<td id="groupLeader-'+groupid+'">组长</td>\
			<td><a href="javascript:void();" onclick="cancelGroupedByStudentid('+leaderid+','+groupid+')">取消分组</a>\
			</td>\
		</tr>\
	';
	 $(tableId).prepend(inputdata) ;
	}else{
		inputdata = '\
			<tr id="tr-'+data.id+'">\
				<td>&nbsp</td>\
				<td>'+data.studentCode+'</td>\
				<td>'+data.name+'</td>\
				<td>'+data.studentGrade+'</td>\
				<td>'+data.stuClass+'</td>\
				<td><a href="javascript:void();" onclick="setGroupLeader('+data.id+','+groupid+',this)">设为组长</a></td>\
				<td><a href="javascript:void();" onclick="cancelGroupedByStudentid('+data.id+','+groupid+')">取消分组</a>\
				</td>\
			</tr>\
			';
		$(tableId).append(inputdata) ;
	}
}
/**
 * 切换显示新增学生到已有组操框
 * 异步请求现有小组列表
 */
function showAddToGroupBox(){
	var inputdata = '{"cmissionid":'+cmissionid+'}';
	$("#newGroupBox").slideUp(500,function(){
			if($("#addToGroupBox").css("display") == "none"){
				$.ajax({
					cache : true,
					type:"POST",
					url : "/classInfo/tch/getCmgroups.action",//此处不能使用单引号代替双引号
					//dataType : 'json',
					contentType : 'application/json; charset=utf-8',
					data : inputdata,
					error: function (request, message, ex) {
					    alert(request.responseText);
					},
					success : function(data) {
						if(data.ajaxResult=="success"){
						
							var inputHtml = '';
							for(var i = data.cmgroupNotinMDetailViews.length-1;i>=0;i--){
												inputHtml = inputHtml
														+ '<label class="ml10" for="g-'
														+ data.cmgroupNotinMDetailViews[i].id
														+ '">'
														+ data.cmgroupNotinMDetailViews[i].name
														+ '</label> <input type="radio" name="groupid" id="g-'
														+ data.cmgroupNotinMDetailViews[i].id+'" checked="checked" value="'+data.cmgroupNotinMDetailViews[i].id+'" /> ';
							}
							for(var i = data.cmgroupinMDetailViews.length-1;i>=0;i--){
								inputHtml = inputHtml
										+ '<label class="ml10" for="g-'
										+ data.cmgroupinMDetailViews[i].id
										+ '">'
										+ data.cmgroupinMDetailViews[i].name
										+ '</label> <input type="radio" name="groupid" id="g-'
										+ data.cmgroupinMDetailViews[i].id+'" checked="checked" value="'+data.cmgroupinMDetailViews[i].id+'" /> ';
			}
							$("#selectGroupId").html(inputHtml);
						}
					}
					});
			}
   			$("#addToGroupBox").slideToggle();
   		});
}

/**
 * 显示修改任务分组情况
 * @author msl
 */
function showReviseGroups(){
	var inputdata = '{"cmissionid":'+cmissionid+'}';
	$.ajax({
		cache : true,
		type:"POST",
		url : "/classInfo/tch/modifyCmgrouped.action",//此处不能使用单引号代替双引号
		//dataType : 'json',
		contentType : 'application/json; charset=utf-8',
		data : inputdata,
		error: function (request, message, ex) {
		    alert(request.responseText);
		},
		success : function(data) {
			if(data.ajaxResult=="success"){
				resetForm();
				$("#editBox4Groups").hide();
				$("#unGroupedTable tbody").html("");
				$("#modifyGroups").show();
				for(var i = data.ungroupedCmstudentDetailViews.length-1;i>=0;i--){
					addAUngroupedStudent(data.ungroupedCmstudentDetailViews[i]);
				}
				for(var i =data.cmgroupNotinMDetailViews.length-1;i>=0;i--){
					writeANotIngroupInDom(data.cmgroupNotinMDetailViews[i]);
				}
				for(var i =data.cmgroupinMDetailViews.length-1;i>=0;i--){
					writeAIngroupInDom(data.cmgroupinMDetailViews[i]);
				}
				tableGap();
				autoIndex();
			}
		}
	});
}

/**
 * 撤销丄1�7个小组1�7
 * 
 * @param groupid
 */
function cancleAGroup(groupid){
	var gBoxId ="#groupBox-"+groupid;
	var inputdata = '{"cmgroupids":['+groupid+'],"cmissionid":'+cmissionid+'}';
	$.ajax({
		cache : true,
		type:"POST",
		url : "/classInfo/tch/cancelCmgroupsGrouped.action",//此处不能使用单引号代替双引号
		//dataType : 'json',
		contentType : 'application/json; charset=utf-8',
		data : inputdata,
		error: function (request, message, ex) {
		    alert(request.responseText);
		},
		success : function(data) {
			if(data.ajaxResult=="success"){
				resetForm();
				$("#unGroupedTable tbody").html("");
				for(var i =data.ungroupedCmstudentDetailViews.length-1;i>=0;i--){
					addAUngroupedStudent(data.ungroupedCmstudentDetailViews[i]);
				}
				
				$(gBoxId).remove();	//在小组列表中移除该小组1�7
				autoIndex();
			   	tableGap();
			   	
			   
			}
			
		}
		});
	
}
/**
 * 在小组列表中写入丄1�7个新未参与组
 * 
 * @param data
 */
function writeANotIngroupInDom(data){
	var groupHtml = '<div id="groupBox-'+data.id+'" class="groupBox">\
	<div class="groupTitle">\
		<span id="groupCheckbox-'+data.id+'" name="groupCheckbox" class="w50 mt5"> <input type="checkbox" value="'+data.id+'"> </span>\
		<span name="groupIndex" class="w100">1</span> \
		<span name="groupName" class="w300">'+data.name+'</span> \
		<span name="groupLeaderName" class="w100">'+data.leadername+'</span>\
		<span name="groupState" class="w100">不参劄1�7/span> \
		<span name="groupMembers" class="w100">'+data.count+'</span> \
		<span name="groupOperation" > <a href="javascript:void();" onclick="cancleAGroup('+data.id+')">撤销组1�7/a></span>\
		<div class="fr mr10 show" onclick="getGroupDetail('+data.id+',this)">╄1�7/div>\
	</div>\
	<!-- tr学生id，g弄1�7头的为groupid，js函数传参，第丄1�7个为学生id，第二个为组id -->\
	<div class="groupContext">\
		<table id="groupTable-'+data.id+'" class="bc f14" cellspacing="0" cellpadding="0" width="900">\
			<thead>\
				<tr name="tableTitle">\
					<th width="50">&nbsp</th>\
					<th width="200">学号</th>\
					<th width="130">姓名</th>\
					<th width="80">年级</th>\
					<th width="150">班级</th>\
					<th width="150">组长标识</th>\
					<th width="150">操作</th>\
				</tr>\
			</thead>\
			<tbody>\
			</tbody>\
		</table>\
	</div>\
	<br/>\
		';
	
	$("#modifyGroups .groupBoxes").eq(0).append(groupHtml);
	$("#modifyGroups .groupContext").hide();
}
/**
 * 在小组列表中写入丄1�7个新未参与组
 * 
 * @param data
 */
function writeAIngroupInDom(data){
	var groupHtml = '<div id="groupBox-'+data.id+'" class="groupBox">\
	<div class="groupTitle active">\
		<span id="groupCheckbox-'+data.id+'" name="groupCheckbox" class="w50 mt5"> <input type="checkbox" value="'+data.id+'"> </span>\
		<span name="groupIndex" class="w100">1</span> \
		<span name="groupName" class="w300">'+data.name+'</span> \
		<span name="groupLeaderName" class="w100">'+data.leadername+'</span>\
		<span name="groupState" class="w100">参加任务</span> \
		<span name="groupMembers" class="w100">'+data.count+'</span> \
		<span name="groupOperation" > <a href="javascript:void();" onclick="cancleAGroup('+data.id+')">撤销组1�7/a></span>\
		<div class="fr mr10 show" onclick="getGroupDetail('+data.id+',this)">╄1�7/div>\
	</div>\
	<!-- tr学生id，g弄1�7头的为groupid，js函数传参，第丄1�7个为学生id，第二个为组id -->\
	<div class="groupContext">\
		<table id="groupTable-'+data.id+'" class="bc f14" cellspacing="0" cellpadding="0" width="900">\
			<thead>\
				<tr name="tableTitle">\
					<th width="50">&nbsp</th>\
					<th width="200">学号</th>\
					<th width="130">姓名</th>\
					<th width="80">年级</th>\
					<th width="150">班级</th>\
					<th width="150">组长标识</th>\
					<th width="150">操作</th>\
				</tr>\
			</thead>\
			<tbody>\
			</tbody>\
		</table>\
	</div>\
	<br/>\
</div>\
		';
	
	$("#modifyGroups .groupBoxes").eq(0).prepend(groupHtml);
	$("#modifyGroups .groupContext").hide();
}
/**
 * 在未分组表格内添加一个学甄1�7
 * @param data 学生对象
 * @author msl
 */
function addAUngroupedStudent(data){
	var addHtml ='<tr id="tr-'+data.id+'">\
					<td><input type="checkbox" value="'+data.id+'">\
					</td>\
					<td name="studentIndex">1</td>\
					<td>'+data.studentCode+'</td>\
					<td>'+data.name+'</td>\
					<td>'+data.studentGrade+'</td>\
					<td>'+data.stuClass+'</td>\
					<td class="tc">\
						<a class="editFormLink" href="javascript:void();" onclick="showEditForm('+data.id+',this)">编辑分组</a>\
						<span id="editForm-'+data.id+'" class="editFormBox hide">\
							<select id="select-'+data.id+'" class="textInputBox w100 h25 f13 m5 vm">\
								<option>未分组1�7/option>\
								<option>1</option>\
								<option>2</option>\
								<option>3</option>\
							</select> \
							<span class="button_blue_s f12 h25 m5 dlb" onclick="addAstudentToGroup('+data.id+',this)">确认</span>\
							<span class="button_gray_s f12 h25 m5 dlb" onclick="hideEditForm('+data.id+',this)">取消</span>\
						</span>\
					</td>\
				</tr>';
	$("#unGroupedTable tbody").append(addHtml) ;
	
}
/**
 * 自动标序
 */
function autoIndex(){
	var studentIndex=$("[name='studentIndex']");
	for(var i =0;i<studentIndex.length;i++){
		studentIndex.eq(i).html(i+1);
	}
	var groupIndex=$("[name='groupIndex']");
	for(var i =0;i<groupIndex.length;i++){
		groupIndex.eq(i).html(i+1);
	}
	 $("#unGroupedTable").trigger("update", [resort]);
}


/**
 * 提交分组情况
 */
function submitMissionGrouped(e){
	var $this=$(e);
	var inputHtml = '\
		<div class="message w1000 bc">\
			<div class="clear"></div>\
			<div class="messageBox err">\
				<div class="messageTitle"><span class="fl messageLogo"></span><span class="close fr" onclick="closeMessage(this)"></span></div>\
				<div class="messageContent">\
					<h4>操作失败＄1�7/h4>\
				<p>你还有学生未进行分组！你可以做如下操作：</p>\
				<p class="t2">1.取消：取消本次提交，继续进行分组操作〄1�7/p>\
				<p class="t2">2.确认提交：所有未分组学生将会分为每人丄1�7组，并默认参与本次任务�1�7�1�7/p>\
				<p class="t2">2.确认提交到课程分组：扄1�7有未分组学生将会分为每人丄1�7组，并默认参与本次任务�1�7�且课程分组模板将被本次分组替代〄1�7/p>\
				</div>\
				<div class="tc h50 f16 mt30">\
					<input id="submitId" type="button" class="btn red" value="确认提交" onclick="setUngroupedStudents2newgroup()">\
					<input type="button" class="btn red" value="提交到课程分组1�7" onclick="">\
					<input type="button" class="btn gray" value="叄1�7&nbsp涄1�7" onclick="closeMessage(this)">\
				</div>\
			</div>\
		</div>\
		\
		';
	if($("#unGroupedTable").find("input[type='checkbox']").length !=0){
		
		$("#modifyGroups").after(inputHtml);
		$("#submitId").focus();
		var studentids = new Array();
		for(var i =0;i<$("#unGroupedTable").find("input[type='checkbox']").length-1;i++){
			studentids[i]=$("#unGroupedTable").find("input[type='checkbox']").eq(i).val();
		}
		//setUngroupedStudents2newgroup(studentids);
	}else{
		
		$("#modifyGroups").hide();
		$("#viewBox4Groups").show();
		
	}
	
}

/**
 * 将未分组学生单独分成丄1�7个组
 * 
 * @param groupid
 */
function setUngroupedStudents2newgroup(){
	var studentids = new Array();
	for(var i =0;i<$("#unGroupedTable").find("input[type='checkbox']").length;i++){
		studentids[i]=$("#unGroupedTable").find("input[type='checkbox']").eq(i).val();
	}
	var inputdata = '{"cmissionid":'+cmissionid+',"cmstudentids":[';
	for(var h = 0;h<studentids.length;h++){
		if(h>0){
			inputdata = inputdata +",";
		}
		inputdata = inputdata +studentids[h];
	}
	 inputdata = inputdata + ']}';
	$.ajax({
		cache : true,
		type:"POST",
		url : "/classInfo/tch/setUngroupedStudents2newCmgroup.action",//此处不能使用单引号代替双引号
		//dataType : 'json',
		contentType : 'application/json; charset=utf-8',
		data : inputdata,
		error: function (request, message, ex) {
		    alert(request.responseText);
		},
		success : function(data) {
			if(data.ajaxResult=="success"){
				resetForm();
				$(".groupBoxes").html("");
				for(var i =data.cmgroupNotinMDetailViews.length-1;i>=0;i--){
					writeANotIngroupInDom(data.cmgroupNotinMDetailViews[i]);
				}
				for(var i =data.cmgroupinMDetailViews.length-1;i>=0;i--){
					writeAIngroupInDom(data.cmgroupinMDetailViews[i]);
				}
				$("#unGroupedTable tbody").html("");
				autoIndex();
			   	tableGap();
			   	
			   
			}
			
		}
		});
	
}
/**
 * 重置表单
 */
function resetForm(){
	$("[type='text']").attr("value","");
	$("[type='checkbox']").attr("checked",false);
	$("#addToGroupBox").slideUp();
			$("#newGroupBox").slideUp();
	
}