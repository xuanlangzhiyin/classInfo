/*version 140706
 * author CHEN ZHUO
 * 教师->新增小组页面的js
*/


/**CHEN ZHUO 140704
 * 页面初始化处琄1�7
 */
$(document).ready(function(){
	$("#involvedGroups").removeAttr("onclick");//提交任务前，禁用参与小组的下拉功胄1�7
	//$("#viewBox4Mission, #viewBox4Groups, #editBox4Groups").hide();//隐藏mission的viewBox，Groups的viewBox和editBox
	//$("#btnCancleRevise4Mission").attr("disabled","disabled").removeClass("blue").addClass("gray");//禁用editBox4Mission的取消修改按钄1�7
	var editor = new Simditor({
	   	  textarea: $('#editor')
	   	});
});

function backup(){
	alert("回�1�7�1�7至上丄1�7页面（尚未编写）");
}

/**CHEN ZHUO 140705
 * 新增任务，将任务detail提交至后叄1�7
 * 后台传回该新增任务id,以及该任务下创建的小组list
 * 将小组list写入html DOM中，濄1�7活参与小组下拉功能，显示小组列表
 */
function submitMission(){
	//点击提交按钮后将editBox禁用，防止多次提亄1�7
	$editBox4Mission = $("#editBox4Mission");
	$editBox4Mission.find(".editView").attr("disabled","disabled");
	//显示加载中图桄1�7
	$("#onload").fadeIn();
	//***以下为创建json数据的code***
	var stype;
		//后台数据库的stype存储格式为true/false，此处对stype值进行转捄1�7
	if($editBox4Mission.find("#stype4edit option:selected").val() == 1){
		stype = true;
	}else{
		stype = false;
	}
	var name = $("#name4edit").val();
	var deadlineDate = new Date($("#deadline4edit").val()).toJSON();
	var requirement = $("#requirement4edit").find(".simditor-body").eq(0).html();
	var inputdata = '{"cmission":{"courseid":'+courseid+',';
	//alert("courseid is "+courseid);
	inputdata = inputdata + '"stype":'+ stype + ','+'"name":"'+name+'",'
			+'"deadline":"'+deadlineDate+'",'
				+'"requirement":"'+requirement+'"}}';
	//***以上为创建json数据的code***
	//向后台发送ajax异步请求
	$.ajax({
		cache:true,
		type:"POST",
		url:"/classInfo/tch/saveNewGroupMission.action",//后台action对应该请求的方法的url
		contentType: 'application/json; charset=utf-8',
		data: inputdata,
		//请求失败的处理函敄1�7
		error: function (request, message, ex) {
			alert(request.responseText);
			$("#onload").fadeOut();
			//回�1�7�1�7editBox的禁用处理，让用户可以重新提亄1�7
			$("#editBox4Mission").find(".editView").removeAttr("disabled");
		},
		//请求成功的处理函数（此处成功只代表成功与后台连接，不代表后台数据处理成功
		success: function(data){		
			//如果后台数据处理成功
			if(data.ajaxResult == "success"){
				cmissionid = data.cmgroupinMDetailViews[0].cmissionid;//创建js全局变量保存该任务的id
				//***以下为将editBox的数据copy到viewBox的code***
				var stype4view;
				if($("#stype4edit").val() == 1){
					stype4view = "按小组提亄1�7";
				}else{
					stype4view = "按个人提亄1�7";
				}
				$("#stype4view").text(stype4view);
				$("#stype4view").attr("title",$("#stype4edit").val());
				$("#name4view").text($("#name4edit").val());
				$("#deadline4view").text($("#deadline4edit").val());
				$("#requirement4view").html($("#requirement4edit").find(".simditor-body").eq(0).html());
				alert($("#requirement4edit").find(".simditor-body").eq(0).html());
				//***以上为将editBox的数据copy到viewBox的code***
				//将任务视图切换到viewBox
				showView4Mission();
				//重新设置任务视图的editBox的提交按钮：onclick动作改为更新，显示改为更新（因为提交成功后，以后的修改都是更新操作�1ￄ1�7�不是新增操作）
				$("#btnSubmit4Mission").attr({"onclick":"updateMission()","value":"更新"});
				$("#btnCancleRevise4Mission").attr("onclick","showView4Mission()");
				//将后台传来的groupData写入html DOM
				var groupData = data.cmgroupinMDetailViews;
				writeGroupsData(groupData);
				//濄1�7活小组列表的下拉功能
				$("#involvedGroups").attr("onclick","slideBox(this)");
				//隐藏加载丄1�7
				$("#onload").fadeOut();
				//显示小组列表
				slideBox($("#involvedGroups").get(0));
			}else{
				alert("error in data response");
				$("#onload").fadeOut();
				$("#editBox4Mission").find(".editView").removeAttr("disabled");
				
			}
		}
	});	
}

/**CHEN ZHUO 140705
 * 更新任务
 * 将任务id和更新的任务内容提交到后叄1�7
 * 后台传回ajax request的处理状怄1�7
 */
function updateMission(){
	//禁用editBox,防止多次重复更新
	$editBox4Mission = $("#editBox4Mission");
	$editBox4Mission.find(".editView").attr("disabled","disabled");
	//***以下为创建json数据的code
	var stype;
	if($editBox4Mission.find("#stype4edit option:selected").val() == 1){
		stype = true;
	}else{
		stype = false;
	}
	var name = $("#name4edit").val();
	var deadlineDate = new Date($("#deadline4edit").val()).toJSON();
	var requirement = $("#requirement4edit").find(".simditor-body").eq(0).html();
	//alert(requirement);
	var inputdata = '{"cmission":{"id":'+cmissionid+', "courseid":'+courseid+',';
	//alert("courseid is "+courseid);
	inputdata = inputdata + '"stype":'+ stype + ','+'"name":"'+name+'",'
			+'"deadline":"'+deadlineDate+'",'
				+'"requirement":"'+requirement+'"}}';
	//***以上为创建json数据的code
	alert(inputdata);
	$.ajax({
		cache:true,
		type:"POST",
		url:"/classInfo/tch/updateGroupCmission.action",
		contentType: 'application/json; charset=utf-8',
		data: inputdata,
		//请求失败的处理函敄1�7
		error: function (request, message, ex) {
			alert(request.responseText);
			//回�1�7�1�7对editBox的禁甄1�7
			$("#editBox4Mission"),find(".editView").removeAttr("disabled");
			
		},
		//请求成功的处理函数（此处成功只代表成功与后台连接，不代表后台数据处理成功
		success: function(data){
			if(data.ajaxResult == "success"){
				//alert("success update");
				//***以下为将editBox的数据copy到viewBox的code***
				var stype4view;
				if($("#stype4edit").val() == 1){
					stype4view = "按小组提亄1�7";
				}else{
					stype4view = "按个人提亄1�7";
				}
				$("#stype4view").text(stype4view);
				$("#stype4view").attr("title",$("#stype4edit").val());
				$("#name4view").text($("#name4edit").val());
				$("#deadline4view").text($("#deadline4edit").val());
				$("#requirement4view").html($("#requirement4edit").find(".simditor-body").eq(0).html());
				//***以上为将editBox的数据copy到viewBox的code***
				//切换到viewBox
				showView4Mission();
				/*success end*/
				}else{
					
				}
			}
	});	
	
}

/**CHEN ZHUO 140705 
 * (此函数仅在submitMission成功时调用（根据业务逻辑，所有小组默认为已参劄1�7)
 * 将groupdata写入html DOM （已参与小组列表下） 
 *@param groupData: {"joinGroups":[{},{}],"unJoinGroups:[{},{}]"}
 * 	注：groupsdata仅为grouptitle级别的信息，groupDetailt通过ajax异步请求获取
 * id 组名 name 队长各1�7 leadername 小组人数 count 参与状�1�7�1�7 isinvolved
 */
function writeGroupsData(groupData){
	//alert("loadGroupsData()...");
	//alert(groupData.length);
	addHTML = '';
	for(var i=0;i<groupData.length;i++){
		var thisGroup = groupData[i];
		addHTML += '<div id="group-'+thisGroup.id+'" class="groupBox">\
			<div class="groupTitle active">\
		<span class="w50"><input name="option" type="checkbox"></span>\
		<span class="w300">'+thisGroup.name+'</span>\
		<span class="w200">'+thisGroup.leadername+'</span>\
		<span class="w100">'+thisGroup.count+'</span>\
		<span class="w100 checked">参加任务</span>\
		<span >\
			<a name="option" href="javascript:void(0);" class="checked" onclick="toggleGroup(this)">删除参加</a>\
		</span>\
		<div class="fr mr10 show boxControl" onclick="loadGroupDetailData(this)">╄1�7/div>\
	</div>\
	<div class="groupContext">\
	</div>\
</div><br>';	
	}
	//alert(addHTML);
	//将addHTML写入已参与小组列衄1�7
	$("#checkedGroups").html(addHTML);
	tableGap();
}

/**CHEN ZHUO 140705
 * 加载小组成员信息
 * @param e：击中的小组title里面的1�7"+"div
 *
 */
function loadGroupDetailData(e){
	//获取该小组的groupid "group-#"
	var groupid = $(e).parents().filter("div.groupBox").attr("id");
	//获取传入后台的id "group-#"里面的1�7"#"
	var cmgroupid = groupid.substr(groupid.search('-')+1);
	//alert("groupid is "+groupid);
	//alert("cmgroupid is "+cmgroupid);
	//创建json数据
	var inputdata = '{"cmgroupid":'+cmgroupid+'}';
	$.ajax({
		cache:true,
		type:"POST",
		url:"/classInfo/tch/getCmGroupMemberDetial.action",
		contentType: 'application/json; charset=utf-8',
		data: inputdata,
		//请求失败的处理函敄1�7
		error: function (request, message, ex) {
			alert(request.responseText);
		},
		//请求成功的处理函数（此处成功只代表成功与后台连接，不代表后台数据处理成功
		success: function(data){
			//如果后台数据处理成功
			if(data.ajaxResult == "success"){
				var cmstudentDetailViews = data.cmstudentDetailViews;
				//将后台传回的students信息写入groupid指定的小组下的htmlDOM
				writeStudentsData(e,cmstudentDetailViews);
				//slideTable(e);
			}
		}
	});
}

/**
 * 
 * @param groupid
 * @param cmstudentDetailViews
 */
function writeStudentsData(e,cmstudentDetailViews){
	$thisGroupBox = $(e).parents().filter("div.groupBox");
	$thisGroupContext = $thisGroupBox.find("div.groupContext");
	var addHTML = '<table  class="bc f14" cellspacing="0" cellpadding="0" width="900">\
		<thead>\
	<tr>\
		<th width="50">&nbsp</th>\
		<th width="200">学号</th>\
		<th width="130">姓名</th>\
		<th width="100">性别</th>\
		<th width="120">年级</th>\
		<th width="150">班级</th>\
	</tr>\
		</thead>\
		<tbody>';
	for(var i=0;i<cmstudentDetailViews.length;i++){
		var thisStudent = cmstudentDetailViews[i];
		addHTML += '<tr >\
			<td>&nbsp</td>\
			<td>'+thisStudent.studentCode+'</td>\
			<td>'+thisStudent.name+'</td>\
			<td>'+thisStudent.sex+'</td>\
			<td>'+thisStudent.studentGrade+'</td>\
			<td>'+thisStudent.stuClass+'</td>\
		</tr>';
	}
	addHTML += '</tbody></table>';
	//alert(addHTML);
	$thisGroupContext.html(addHTML);
	tableGap();
	$thisGroupBox.find(".boxControl").attr("onclick","slideTable(this)");
}

function showView4Mission(){
	$("#missionDetail").attr("onclick","slideBox(this);");
	$("#editBox4Mission").fadeOut(500,function(){
		$("#viewBox4Mission").fadeIn();
	});
}

function editView4Mission(){
	$("#stype4edit").find("option [value='"+$("#stype4view").attr("title")+"']").attr("selected","selected");
	$("#name4edit").val($("#name4view").text());
	$("#deadline4edit").val($("#deadline4view").text());
	//alert($("requirement4view").parent().html());
	$("#requirement4edit").find(".simditor-body").eq(0).html($("#requirement4view").text());
	
	$("#editBox4Mission").find(".editView").removeAttr("disabled");
	$("#viewBox4Mission").fadeOut(500,function(){
		$("#editBox4Mission").fadeIn();
	});
	$("#missionDetail").removeAttr("onclick");
}

/**
 * 全�1�7�操佄1�7
 * @param checkFormId: 霄1�7要全选的范围的ID
 * @param e: 全�1�7�的按钮本身(this)
 */
function checkAll(checkFormId,e){
	var $this=$(e);
	if($this.hasClass("active")){
		$(checkFormId).find("[type='checkbox']").attr("checked",false);
		$this.removeClass("active");
	}else{
		
		$(checkFormId).find("[type='checkbox']").attr("checked",true);
		
		$this.addClass("active");
	}
}

/**
 * 全�1�7�操佄1�7(对排序表格操佄1�7)
 * @param checkFormId: 霄1�7要全选的范围的ID
 * @param e: 全�1�7�的按钮本身(this)
 */
function checkAll4table(checkFormId,e){
	var $this=$(e);
	if($this.hasClass("active")){
		$(checkFormId).find("[type='checkbox']").attr("checked",false);
		$this.removeClass("active");
	}else{
		$(checkFormId).find("tr").not(".filtered").find("[type='checkbox']").not(".filtered").attr("checked",true);
		//$(checkFormId).find("[type='checkbox']").attr("checked",true);
		//alert($(checkFormId).find("tr").not(".filtered").find("[type='checkbox']").not(".filtered").length)
		$this.addClass("active");
	}
}

/**CHEN ZHUO 140705
 * toggle the group: if checked then toggle it to unchecked set, vice versa
 * @param groupId (id of toggled group)
 *  
 */
function toggleGroup(e){
	$thisGroup = $(e).parents().filter("div.groupBox");//get the jQeury Object of this group
	var groupid =$thisGroup.attr("id");
	var cmgroupid = groupid.substr(groupid.search('-')+1);
	//发�1�7�至后台的数据：cmgroupid,操作名称
	
	//alert("cmgroupid is "+cmgroupid);
	if($thisGroup.parent().attr("id") == "checkedGroups"){
		var inputdata ='{"cmgroupids":['+cmgroupid+']}';
		//alert(inputdata);
		$.ajax({
			cache:true,
			type:"POST",
			url:"/classInfo/tch/removeCmGroupsFromMission.action",
			contentType: 'application/json; charset=utf-8',
			data: inputdata,
			//请求失败的处理函敄1�7
			error: function (request, message, ex) {
				alert(request.responseText);
				$("#editBox4Mission"),find(".editView:not(#btnCancleRevise4Mission)").removeAttr("disabled");
			},
			//请求成功的处理函数（此处成功只代表成功与后台连接，不代表后台数据处理成功
			success: function(data){
				//if success
				if(data.ajaxResult == "success"){
				deleteGroup(groupid);
				}
			}
		});
		
	}else{
		var inputdata ='{"cmgroupids":['+cmgroupid+']}';
		$.ajax({
			cache:true,
			type:"POST",
			url:"/classInfo/tch/addCmGroupstoMission.action",
			contentType: 'application/json; charset=utf-8',
			data: inputdata,
			//请求失败的处理函敄1�7
			error: function (request, message, ex) {
				alert(request.responseText);
			},
			//请求成功的处理函数（此处成功只代表成功与后台连接，不代表后台数据处理成功
			success: function(data){
				//if success
				if(data.ajaxResult == "success"){
				addGroup(groupid);
				}
			}
		});
		
		
	}
}

function deleteGroup(groupid){
	//alert("delete()..");
	$thisGroup = $("#"+groupid);
	$thisGroup.find("span.checked").text("不参加任劄1�7").removeClass("checked").addClass("unchecked");
	$thisGroup.find("a.checked").text("添加到任劄1�7").removeClass("checked").addClass("unchecked");
	$thisGroup.find("div.groupTitle").removeClass("active");
	$br = $thisGroup.next("br");
	$("#uncheckedGroups").append($thisGroup).append($br);//直接用append方法对jQuery对象进行移动
}

function addGroup(groupid){
	$thisGroup = $("#"+groupid);
	$thisGroup.find("span.unchecked").text("参加任务").removeClass("unchecked").addClass("checked");
	$thisGroup.find("a.unchecked").text("删除参加").removeClass("unchecked").addClass("checked");
	$thisGroup.find("div.groupTitle").addClass("active");
	$br = $thisGroup.next("br");
	$("#checkedGroups").append($thisGroup).append($br);
}

function batchDeleteGroups(){
	//alert($("#checkedGroups").find(":checkbox").filter("[checked='checked']").parent().html());
	$checkedBoxes = $("#checkedGroups").find(":checkbox:checked");//.filter("[checked='checked']");
	//alert($checkedBoxes.parent().html());
	//获取$checkBoxs隶属的groupBox的jQuery对象集合$selectGroups
	$selectGroups = $checkedBoxes.parents().filter("div.groupBox");
	//取消$checkedBoxs的�1�7�中状�1�7�（因为对被选中的对象完成操作后其checkBox应恢复为unchecked的状态）
	$checkedBoxes.attr("checked",false);
	var cmgroupids = new Array();
	//alert("groupsLength:"+$selectGroups.length);
	var inputdata = '{"cmgroupids":[';
	for(var i=0;i<$selectGroups.length;i++){
		if(i != 0){
			inputdata +=',';
		}
		var thisGroupid = $selectGroups.eq(i).attr("id");
		 cmgroupids[i] = thisGroupid.substr(thisGroupid.search('-')+1);
		inputdata += cmgroupids[i];
	}
	inputdata += ']}';
	//alert(inputdata);
	$.ajax({
		cache:true,
		type:"POST",
		url:"/classInfo/tch/removeCmGroupsFromMission.action",
		contentType: 'application/json; charset=utf-8',
		data: inputdata,
		//请求失败的处理函敄1�7
		error: function (request, message, ex) {
			alert(request.responseText);
		},
		//请求成功的处理函数（此处成功只代表成功与后台连接，不代表后台数据处理成功
		success: function(data){
			//if success
			if(data.ajaxResult == "success"){
			for(var i=0; i<cmgroupids.length;i++){
				deleteGroup("group-"+cmgroupids[i]);
			}
			}
		}
	});
}

function batchAddGroups(){
	//alert($("#checkedGroups").find(":checkbox").filter("[checked='checked']").parent().html());
	$checkedBoxes = $("#uncheckedGroups").find(":checkbox:checked");//.filter("[checked='checked']");
	//alert($checkedBoxes.parent().html());
	//获取$checkBoxs隶属的groupBox的jQuery对象集合$selectGroups
	$selectGroups = $checkedBoxes.parents().filter("div.groupBox");
	//取消$checkedBoxs的�1�7�中状�1�7�（因为对被选中的对象完成操作后其checkBox应恢复为unchecked的状态）
	$checkedBoxes.attr("checked",false);
	var cmgroupids = new Array();
	//alert("groupsLength:"+$selectGroups.length);
	var inputdata = '{"cmgroupids":[';
	for(var i=0;i<$selectGroups.length;i++){
		if(i != 0){
			inputdata +=',';
		}
		var thisGroupid = $selectGroups.eq(i).attr("id");
		 cmgroupids[i] = thisGroupid.substr(thisGroupid.search('-')+1);
		inputdata += cmgroupids[i];
	}
	inputdata += ']}';
	//alert(inputdata);
	$.ajax({
		cache:true,
		type:"POST",
		url:"/classInfo/tch/addCmGroupstoMission.action",
		contentType: 'application/json; charset=utf-8',
		data: inputdata,
		//请求失败的处理函敄1�7
		error: function (request, message, ex) {
			alert(request.responseText);
		},
		//请求成功的处理函数（此处成功只代表成功与后台连接，不代表后台数据处理成功
		success: function(data){
			//if success
			if(data.ajaxResult == "success"){
			for(var i=0; i<cmgroupids.length;i++){
				addGroup("group-"+cmgroupids[i]);
			}
			}
		}
	});
}

function confirmGroups(){
	$("involvedGroups").removeAttr("onclick");
	$viewBox4Groups = $("#viewBox4Groups");
	$editBox4groups = $("#editBox4Groups");
	$checkedGroupsBox4view = $viewBox4Groups.find("#checkedGroups");
	$checkedGroupsBox4edit=$editBox4groups.find("#checkedGroups");
	$checkedGroupsBox4view.html($checkedGroupsBox4edit.html());
	$options = $checkedGroupsBox4view.find("[name='option']");
	$options.each(function(){
		$(this).parent().html("&nbsp");
	});
	$editBox4groups.fadeOut(500,function(){
		$viewBox4Groups.fadeIn();
	});
}

function editView4Groups(){
	$("involvedGroups").attr("onclick","slideBox(this);");
	$("#viewBox4Groups").fadeOut(500,function(){
		$("#editBox4Groups").fadeIn();
	});
}

