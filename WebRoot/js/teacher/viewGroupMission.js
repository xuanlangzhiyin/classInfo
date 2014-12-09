$(document).ready(function(){
	 submitMission();
});

function backup(){
	alert("....");
}


function submitMission(){

	$editBox4Mission = $("#editBox4Mission");

	$("#onload").fadeIn();
	
	var inputdata = '{"cmissionid":'+cmissionid+"}"; 
	
	$.ajax({
		cache:true,
		type:"POST",
		url:"/classInfo/tch/saveNewGroupMission.action",
		contentType: 'application/json; charset=utf-8',
		data: inputdata,
		
		error: function (request, message, ex) {
			alert(request.responseText);
			$("#onload").fadeOut();
		
			$("#editBox4Mission").find(".editView").removeAttr("disabled");
		},
		
		success: function(data){		
		
			if(data.ajaxResult == "success"){	
			if(data.cmgroupinMDetailViews.length==0)
				{
				$("#errorGroups").removeClass("none");
				$("#editBox4Groups").addClass("none");
				$("involvedGroups").addClass("active");
				
				$("#onload").fadeOut();
				}
			else{
			
				showView4Mission();

				
			
				$("#btnSubmit4Mission").attr({"onclick":"updateMission()","value":"更新"});
				$("#btnCancleRevise4Mission").attr("onclick","showView4Mission()");
			
				
				var groupData = data.cmgroupinMDetailViews;
				
				writeGroupsData(groupData);
			
				$("#involvedGroups").attr("onclick","slideBox(this)");
			
				$("#onload").fadeOut();
				
				slideBox($("#involvedGroups").get(0));
			}
			}else{
				alert("error in data response");
				$("#onload").fadeOut();
				$("#editBox4Mission").find(".editView").removeAttr("disabled");
				
			}
		}
		});	
}


function writeGroupsData(groupData){
	//alert("loadGroupsData()...");
	//alert(groupData.length);
	addHTML = '';
	for(var i=0;i<groupData.length;i++){
		var thisGroup = groupData[i];
		addHTML += '<div id="group-'+thisGroup.id+'" class="groupBox">\
			<div class="groupTitle active">\
		<span class="w50"><input name="option" type="checkbox"></span>\
		<span id="groupname" class="w300">'+thisGroup.name+'</span>\
		<span class="w200">'+thisGroup.leaderName+'</span>\
		<span class="w100">'+thisGroup.count+'</span>\
		<span class="w100 checked">参加任务</span>\
		<div class="fr mr10 show boxControl" onclick="loadGroupDetailData(this)">╋</div>\
	</div>\
	<div class="groupContext">\
	</div>\
</div><br>';	
	}

	$("#checkedGroups").html(addHTML);
	tableGap();
}


function loadGroupDetailData(e){

	var groupid = $(e).parents().filter("div.groupBox").attr("id");

	var cmgroupid = groupid.substr(groupid.search('-')+1);
	
	var inputdata = '{"cmgroupid":'+cmgroupid+'}';
	$.ajax({
		cache:true,
		type:"POST",
		url:"/classInfo/tch/getCmGroupMemberDetial.action",
		contentType: 'application/json; charset=utf-8',
		data: inputdata,
		
		error: function (request, message, ex) {
			alert(request.responseText);
		},
	
		success: function(data){
		
			if(data.ajaxResult == "success"){
				var cmstudentDetailViews = data.cmstudentDetailViews;
			
				writeStudentsData(e,cmstudentDetailViews);
			
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
		<th width="50">&nbsp;</th>\
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



function editView4Groups(){
	$("involvedGroups").attr("onclick","slideBox(this);");
	$("#viewBox4Groups").fadeOut(500,function(){
		$("#editBox4Groups").fadeIn();
	});
}

