var resort = true;

/**
 * 页面初始化时加载的函敄1�7
 * 隐藏学生列表
 */
$(document).ready(function(){
    //隐藏页面初始化时无需显示的框佄1�7
	$("#listBox").hide();
	$("#searchList").hide();
	tablePager("#studentList4viewBox",'.pager4studentList4viewBox');
	tablePager("#studentList",'.pager4studentList');
	graytablePager("#unSelected",".pager4unSelected");
	var editor = new Simditor({
	   	  textarea: $('#editor')
	   	});
});

/**
 * 将要创建的课程任务信息传给后台，
 * 并显示后台传递来的学生列衄1�7
 */
function showStudentList() {
	//使用日期控件
	var d=new Date($("[name=deadLine]").eq(0).val());
	var deadlineDate = d.toJSON();
	$("#listBox").fadeIn();
	var requirement = $("[name=cmissionSummary]").eq(0).find(".simditor-body").eq(0).html();
	//定义传�1�7�给后台的json格式
	var inputdata = '{"cmission":{"name":"'+$("[name=cmissionName]").eq(0).val()+
	'","courseid":'+courseid+
	',"deadLine":"'+deadlineDate+
	'","requirement":"'+requirement+'"}}';
	//ajax操作
	$.ajax({
		cache : true,
		type:"POST",
		url : "/classInfo/tch/addNewPersonalCmission.action", //ajax请求的action
		contentType : 'application/json; charset=utf-8',
		data : inputdata,
		error: function (request, message, ex) {
			//alert(request.responseText);
		},
		success : function(data) {
			if(data.ajaxResult=="success"){
				cmissionID=data.involvedCmstudentDetailViewList[0].cmissionid; //申明全局变量。整个页面都可以调用
				$("#involvedStudent").addClass("active");
				console.log(data.name);
				$("#studentList tbody").html("");
				for(var i = 0;i<data.involvedCmstudentDetailViewList.length;i++){
					addOneInMissionStudent(data.involvedCmstudentDetailViewList[i],i+1);
				}			
				$("#editBox4info").addClass("hide");//隐藏editbox
				$("#viewBox4info").removeClass("hide");//显示viewbox
				
				//给viewBox里的内容赋�1�7�1�7
				$("#cmissionNameView").html($("[name=cmissionName]").eq(0).val());
				$("#cmissionDeadlineView").html(deadlineDate);
				$("#cmissionSummaryView").html(requirement);
				autoIndex();
				tableGap();
			}
		}
	});

}

/**
 * 显示修改页面
 */
function modifyCmission() {
	$("#editBox4info").removeClass("hide");
	$("#viewBox4info").addClass("hide");
	$("#ChangeA").attr("onclick","cancelUpdateCmission()");
	$("#ChangeB").attr("onclick","updateCmission()");
}

/**
 * 向后台传递要修改的课程任务信恄1�7
 */
function updateCmission() {
	var requirement = $("[name=cmissionSummary]").eq(0).find(".simditor-body").eq(0).html();
	var d=new Date($("[name=deadLine]").eq(0).val());
	var deadlineDate = d.toJSON();
	var inputdata = '{"cmission":{"id":'+cmissionID+
	',"name":"'+$("[name=cmissionName]").eq(0).val()+
	'","courseid":'+courseid+
	',"deadLine":"'+deadlineDate+
	'","requirement":"'+requirement+'"}}';

	$.ajax({
		cache : true,
		type:"POST",
		url : "/classInfo/tch/updatePersonalCmission.action",
		contentType : 'application/json; charset=utf-8',
		data : inputdata,
		error: function (request, message, ex) {
			alert(request.responseText);
		},
		success : function(data) {
			if(data=="success"){
				$("#editBox4info").addClass("hide");
				$("#viewBox4info").removeClass("hide");
				$("#cmissionNameView").html($("[name=cmissionName]").eq(0).val());
				$("#cmissionDeadlineView").html(deadlineDate);
				$("#cmissionSummaryView").html(requirement);
			}
		}
	});
}

/**
 * 确认学生列表,隐藏editbox,显示viewbox
 */
function submitStulist() {
	$("#studentList4viewBox tbody").html("");
	var addHtml=$("#studentList tbody").html(); //取得editbox里的学生列表
	$("#studentList4viewBox tbody").append(addHtml); //将获得的列表复制到viewbox
	$("#studentList4viewBox").find(".removable").remove(); //移除viewbox里无霄1�7显示的列表项
	$("#viewBox4Studentlist").removeClass("hide");//显示viewbox
	$("#editBox4Studentlist").addClass("hide"); //隐藏editbox

}

/**
 * 取消对学生列表的修改
 */
function cancelStulist() {
	$("#viewBox4Studentlist").addClass("hide");		
	$("#editBox4Studentlist").removeClass("hide");
}

/**
 * 取消对任务的修改
 */
function cancelUpdateCmission() {
	$("#viewBox4info").removeClass("hide");		
	$("#editBox4info").addClass("hide");
}

function hideStudentList() {

	$("#listBox").fadeOut();

}




/**
 * 切换任务列表的显示状怄1�7
 * @param e
 */
function slideBox(e){
	var $this=$(e);
	if($this.hasClass("active")){
		$this.next(".boxContext").slideToggle();
	}
}


/**
 * 全�1�7�1�7
 * @param checkFormId
 * @param e
 */
function checkAll(checkFormId,e){
	var $this=$(e);
	if($this.hasClass("active")){
		$(checkFormId).find("[type='checkbox']").attr("checked",false);
		$this.removeClass("active");
	}else{
		$(checkFormId).find("tr").not(".filtered").find("[type='checkbox']").not(".filtered").attr("checked",true);
		$this.addClass("active");
	}
}

/**
 * 批量删除学生
 */
function deleteStudents(){
	var studentids = new Array();
	var stuId = new Array();
	var count=$("#studentList").find("input:checkbox:checked").length;
	for(var i = 0; i<count; i++){
		studentids[i]=$("#studentList").find("input:checkbox:checked").eq(i).val();
	}

	var inputdata ='{"cmstudentList":[';
	for(var j = 0; j<studentids.length;j++){
		if(j>0){
			inputdata=inputdata+',';
		}
		inputdata = inputdata+'{"id":'+studentids[j]+
		',"isinvolved":'+false+"}";
	}
	inputdata =inputdata+'],"courseid":'+courseid+',"cmissionid":'+cmissionID+"}";
	$.ajax({
		cache : true,
		type:"POST",
		url : "/classInfo/tch/updateStudentsIsinvolved.action",//此处不能使用单引号代替双引号
		dataType : 'json',
		contentType : 'application/json; charset=utf-8',
		data : inputdata,
		error: function (request, message, ex) {
			alert(request.responseText);
		},
		success : function(data) {
			//此处重点
			if(data.ajaxResult=="success"){
				for(var i = 0; i<count;i++){
					stuId[i] ="#tr-"+studentids[i]; //学生列表每一项的标题
					$(stuId[i]).addClass("hide");
					$(stuId[i]).find(".imStudentIndex").addClass("idleStudentIndex").removeClass("imStudentIndex"); //修改列表的内宄1�7
					$(stuId[i]).find(".removable").eq(0).html("不参与任劄1�7");
					$(stuId[i]).find('a').html("添加到任劄1�7");
					$(stuId[i]).find('a').attr('onclick',"addOneStudentToMission("+studentids[i]+','+cmissionID+','+true+")");
					var addHtml=$(stuId[i]).prop("outerHTML");
					$(stuId[i]).remove();	
					$("#unSelected tbody").append(addHtml);
					$(stuId[i]).removeClass("hide");
				}		
				autoIndex();
				tableGap();
			}
		}
	});

}

/**
 * 批量添加学生
 */
function addStudents(){
	var studentids = new Array();
	var stuId = new Array();
	var count=$("#unSelected").find("input:checkbox:checked").length;
	for(var i = 0; i<count; i++){
		studentids[i]=$("#unSelected").find("input:checkbox:checked").eq(i).val();
	}

	var inputdata ='{"cmstudentList":[';
	for(var j = 0; j<studentids.length;j++){
		if(j>0){
			inputdata=inputdata+',';
		}
		inputdata = inputdata+'{"id":'+studentids[j]+
		',"isinvolved":'+true+"}";
	}
	inputdata =inputdata+'],"courseid":'+courseid+',"cmissionid":'+cmissionID+"}";
	//',"isinvolved":'+isinvolved+'}],"courseid":'+courseid+',"cmissionid":'+cmissionid+"}";
	$.ajax({
		cache : true,
		type:"POST",
		url : "/classInfo/tch/updateStudentsIsinvolved.action",//此处不能使用单引号代替双引号
		dataType : 'json',
		contentType : 'application/json; charset=utf-8',
		data : inputdata,
		error: function (request, message, ex) {
			alert(request.responseText);
		},
		success : function(data) {
			if(data.ajaxResult=="success"){
				for(var i = 0; i<count;i++){
					stuId[i] ="#tr-"+studentids[i];
					$(stuId[i]).addClass("hide");
					$(stuId[i]).find(".idleStudentIndex").addClass("imStudentIndex").removeClass("idleStudentIndex");
					$(stuId[i]).find(".removable").eq(0).html("参与任务");
					$(stuId[i]).find('a').html("删除参与");
					$(stuId[i]).find('a').attr('onclick',"deleteOneStudent("+studentids[i]+','+cmissionID+','+false+")");
					var addHtml=$(stuId[i]).prop("outerHTML");
					$(stuId[i]).remove();	
					$("#studentList tbody").append(addHtml);
					$(stuId[i]).removeClass("hide");
				}	
				autoIndex();
				tableGap();
			}
		}
	});

}

/**
 * 将一名学生添加到未参与学生列衄1�7
 * @param data
 */
function addOneIdleStudent(data,index){
	if(data.credit==null){
		var addHtml ='<tr id="tr-'+data.id+'">\
		<td><input type="checkbox" value="'+data.id+'">\
		</td>\
		<td class="idleStudentIndex">'+index+'</td>\
		<td>'+data.studentCode+'</td>\
		<td>'+data.name+'</td>\
		<td>'+data.sex+'</td>\
		<td>'+data.studentGrade+'</td>\
		<td>'+data.stuClass+'</td>\
		<td>'+'未评刄1�7'+'</td>\
		<td class="removable">'+data.isinvolvedChinese+'</td>\
		<td class="removable"><a href="javascript:void(0);" onclick="addOneStudentToMission('+data.id+','+data.cmissionid+','+true+')">添加到任劄1�7/a></td>\
		</tr>';
	}
	else{
		var addHtml ='<tr id="tr-'+data.id+'">\
		<td><input type="checkbox" value="'+data.id+'">\
		</td>\
		<td class="idleStudentIndex">'+index+'</td>\
		<td>'+data.studentCode+'</td>\
		<td>'+data.name+'</td>\
		<td>'+data.sex+'</td>\
		<td>'+data.studentGrade+'</td>\
		<td>'+data.stuClass+'</td>\
		<td>'+'已评刄1�7'+'</td>\
		<td class="removable">'+data.isinvolvedChinese+'</td>\
		<td class="removable"><a href="javascript:void(0);" onclick="addOneStudentToMission('+data.id+','+data.cmissionid+','+true+')">添加到任劄1�7/a></td>\
		</tr>';
	}
	$("#unSelected tbody").append(addHtml) ;

}

/**
 * 将一名学生添加到已参与学生列衄1�7
 * @param data
 */
function addOneInMissionStudent(data,index){
	if(data.credit==null){
		var addHtml ='<tr id="tr-'+data.id+'">\
		<td><input type="checkbox" value="'+data.id+'">\
		</td>\
		<td class="imStudentIndex">'+index+'</td>\
		<td>'+data.studentCode+'</td>\
		<td>'+data.name+'</td>\
		<td>'+data.sex+'</td>\
		<td>'+data.studentGrade+'</td>\
		<td>'+data.stuClass+'</td>\
		<td>'+'未评刄1�7'+'</td>\
		<td class="removable">'+data.isinvolvedChinese+'</td>\
		<td class="removable"><a href="javascript:void(0);" onclick="deleteOneStudent('+data.id+','+data.cmissionid+','+false+')">删除参与</a></td>\
		</tr>';
	}
	else{
		var addHtml ='<tr id="tr-'+data.id+'">\
		<td><input type="checkbox" value="'+data.id+'">\
		</td>\
		<td class="imStudentIndex">'+index+'</td>\
		<td>'+data.studentCode+'</td>\
		<td>'+data.name+'</td>\
		<td>'+data.sex+'</td>\
		<td>'+data.studentGrade+'</td>\
		<td>'+data.stuClass+'</td>\
		<td>'+'已评刄1�7'+'</td>\
		<td class="removable">'+data.isinvolvedChinese+'</td>\
		<td class="removable"><a href="javascript:void(0);" onclick="deleteOneStudent('+data.id+','+data.cmissionid+','+false+')">删除参与</a></td>\
		</tr>';
	}
	$("#studentList tbody").append(addHtml) ;

}

/**
 * 从已参与学生列表中删除一名学甄1�7
 * @param id
 */
function deleteOneStudent(id,cmissionid,isinvolved){
	var stuId ="#tr-"+id;
	var inputdata ='{"cmstudentList":[{"id":'+id+
	',"isinvolved":'+isinvolved+'}],"courseid":'+courseid+',"cmissionid":'+cmissionid+"}";
	$.ajax({
		cache : true,
		type:"POST",
		url : "/classInfo/tch/updateStudentsIsinvolved.action",//此处不能使用单引号代替双引号
		dataType : 'json',
		contentType : 'application/json; charset=utf-8',
		data : inputdata,
		error: function (request, message, ex) {
			alert(request.responseText);
		},
		success : function(data) {
			if(data.ajaxResult="success"){
				$(stuId).addClass("hide");
				$(stuId).find(".imStudentIndex").addClass("idleStudentIndex").removeClass("imStudentIndex");
				$(stuId).find(".removable").eq(0).html("不参与任劄1�7");
				$(stuId).find('a').html("添加到任劄1�7");
				$(stuId).find('a').attr('onclick',"addOneStudentToMission("+id+','+cmissionid+','+true+")");
				var addHtml=$(stuId).prop("outerHTML");
				$(stuId).remove();	
				$("#unSelected tbody").append(addHtml);
				$(stuId).removeClass("hide");
				autoIndex();
				tableGap();
			}
		}
	});
}

/**
 * 将一名学生添加到任务
 * @param id
 * @param e
 */
function addOneStudentToMission(id,cmissionid,isinvolved){
	var stuId ="#tr-"+id;
	var inputdata ='{"cmstudentList":[{"id":'+id+
	',"isinvolved":'+isinvolved+'}],"courseid":'+courseid+',"cmissionid":'+cmissionid+"}";
	$.ajax({
		cache : true,
		type:"POST",
		url : "/classInfo/tch/updateStudentsIsinvolved.action",//此处不能使用单引号代替双引号
		dataType : 'json',
		contentType : 'application/json; charset=utf-8',
		data : inputdata,
		error: function (request, message, ex) {
			alert(request.responseText);
		},
		success : function(data) {
			if(data.ajaxResult="success"){
				$(stuId).addClass("hide");
				$(stuId).find(".idleStudentIndex").addClass("imStudentIndex").removeClass("idleStudentIndex");
				$(stuId).find(".removable").eq(0).html("参与任务");
				$(stuId).find('a').html("删除参与");
				$(stuId).find('a').attr('onclick',"deleteOneStudent("+id+','+cmissionid+','+false+")");
				var addHtml=$(stuId).prop("outerHTML");
				$(stuId).remove();	
				$("#studentList tbody").append(addHtml);
				$(stuId).removeClass("hide");
				autoIndex();
				tableGap();
			}
		}
	});
}

/**
 * 自动标序
 */
function autoIndex(){
	var studentList=$(".imStudentIndex");
	for(var i =0;i<studentList.length;i++){
		studentList.eq(i).html(i+1);
	}
	var unSelected=$(".idleStudentIndex");
	for(var i =0;i<unSelected.length;i++){
		unSelected.eq(i).html(i+1);
	}
	var studentList4view=$("#studentList4viewBox tbody").children();
	for(var i =0;i<studentList4view;i++){
		studentList4view.eq(i).children().eq(1).html(i+1);
	}
	$("#studentList4viewBox").trigger("update", [resort]);
	$("#studentList").trigger("update", [resort]);
	$("#unSelected").trigger("update", [resort]);
}
