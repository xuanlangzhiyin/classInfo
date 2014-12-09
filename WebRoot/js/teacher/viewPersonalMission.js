var resort = true;

/**
 * 页面初始化时加载的函敄1�7 隐藏学生列表
 */
$(document).ready(function() {

	tablePager("#studentList4viewBox", '.pager4studentList4viewBox');
	tablePager("#studentList", ".pager4studentList");
	graytablePager("#unSelected", ".pager4unSelected");
});

/**
 * 显示修改页面
 */
function modifyCmission() {
	$("#editor").html($("#cmissionSummaryView").html());
	$("#cmissionName4edit").attr("value", $("#cmissionNameView").html());
	$("#editBox4info").removeClass("hide");
	$("#viewBox4info").addClass("hide");
	var editor = new Simditor({
		textarea : $('#editor')
	});
}

/**
 * 确认学生列表,隐藏editbox,显示viewbox
 */
function submitStulist() {
	$("#studentList4viewBox tbody").html("");
	var addHtml = $("#studentList tbody").html();
	alert(addHtml);
	$("#studentList4viewBox tbody").append(addHtml);
	$("#studentList4viewBox").find(".removable").remove();
	$("#viewBox4Studentlist").removeClass("hide");
	$("#editBox4Studentlist").addClass("hide");
	autoIndex();

}

/**
 * 取消对学生列表的修改
 */
// function cancelStulist() {
// $("#viewBox4Studentlist").addClass("hide");
// $("#editBox4Studentlist").removeClass("hide");
// }
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
 * 切换任务列表的显示状态
 * 
 * @param e
 */
function slideBox(e) {
	var $this = $(e);
	if ($this.hasClass("active")) {
		$this.next(".boxContext").slideToggle();
	}
}

/**
 * 全选
 * 
 * @param checkFormId
 * @param e
 */
function checkAll(checkFormId, e) {
	var $this = $(e);
	if ($this.hasClass("active")) {
		$(checkFormId).find("[type='checkbox']").attr("checked", false);
		$this.removeClass("active");
	} else {
		$(checkFormId).find("tr").not(".filtered").find("[type='checkbox']")
				.not(".filtered").attr("checked", true);
		$this.addClass("active");
	}
}

/**
 * 向后台传递要修改的课程任务信息
 */
function updateCmission() {
	var requirement = $("[name=cmissionSummary]").eq(0).find(".simditor-body")
			.eq(0).html();
	var d = new Date($("[name=deadLine]").eq(0).val());
	var deadlineDate = d.toJSON();
	var inputdata = '{"cmission":{"id":' + cmissionID + ',"name":"'
			+ $("[name=cmissionName]").eq(0).val() + '","courseid":' + courseid
			+ ',"deadLine":"' + deadlineDate + '","requirement":"'
			+ requirement + '"}}';

	$.ajax({
		cache : true,
		type : "POST",
		url : "/classInfo/tch/updatePersonalCmission.action",
		contentType : 'application/json; charset=utf-8',
		data : inputdata,
		error : function(request, message, ex) {
			alert(request.responseText);
		},
		success : function(data) {
			if (data == "success") {
				$("#editBox4info").addClass("hide");
				$("#viewBox4info").removeClass("hide");
				$("#cmissionNameView").html(
						$("[name=cmissionName]").eq(0).val());
				$("#cmissionDeadlineView").html(
						$("[name=deadLine]").eq(0).val());
				$("#cmissionSummaryView").html(requirement);
			}
		}
	});
}

/**
 * 批量删除学生
 */
function deleteStudents() {
	var studentids = new Array();
	var stuId = new Array();
	var count = $("#studentList").find("input:checkbox:checked").length;
	for ( var i = 0; i < count; i++) {
		studentids[i] = $("#studentList").find("input:checkbox:checked").eq(i)
				.val();
	}

	var inputdata = '{"cmstudentList":[';
	for ( var j = 0; j < studentids.length; j++) {
		if (j > 0) {
			inputdata = inputdata + ',';
		}
		inputdata = inputdata + '{"id":' + studentids[j] + ',"isinvolved":'
				+ false + ',"cmissionid":' + cmissionID + "}";
	}
	inputdata = inputdata + '],"courseid":' + courseid + ',"cmissionid":'
			+ cmissionID + "}";
	// ',"isinvolved":'+isinvolved+'}],"courseid":'+courseid+',"cmissionid":'+cmissionid+"}";
	$.ajax({
				cache : true,
				type : "POST",
				url : "/classInfo/tch/updateStudentsIsinvolved.action",// 此处不能使用单引号代替双引号
				dataType : 'json',
				contentType : 'application/json; charset=utf-8',
				data : inputdata,
				error : function(request, message, ex) {
					alert(request.responseText);
				},
				success : function(data) {
					if (data.ajaxResult == "success") {
						$(".message").empty();
						var j = 0;
						for ( var i = 0; i < count; i++) {
							stuId[i] = "#tr-" + studentids[i];
							$(stuId[i]).addClass("hide");
							$(stuId[i]).find(".imStudentIndex").addClass(
									"idleStudentIndex").removeClass(
									"imStudentIndex");
							$(stuId[i]).find(".removable").eq(0).html("不参与任务");
							$(stuId[i]).find('a').html("添加到任务");
							$(stuId[i]).find('a').attr(
									'onclick',
									"addOneStudentToMission(" + studentids[i]
											+ ',' + cmissionID + ',' + true
											+ ")");
							var addHtml = $(stuId[i]).prop("outerHTML");
							$(stuId[i]).remove();
							$("#unSelected tbody").append(addHtml);
							$(stuId[i]).removeClass("hide");
							j = i +1;
						}
						var deleteStudents = '<div class="clear"></div>\
										<div class="messageBox success">\
											<div class="messageTitle">\
												<span class="fl messageLogo"></span>\
												<span class="close fr" onclick="closeMessage(this)"></span>\
											</div>\
											<div class="messageContent">\
												<h4>成功删除'+j+'名学生</h4>\
											</div>\
										</div>';
						$("#deleteStudents").append(deleteStudents);
						autoIndex();
						tableGap();
						alignTablePager();
					}
					graytablePager("#unSelected", ".pager4unSelected");
				}
			});

}

/**
 * 批量添加学生
 */
function addStudents() {
	var studentids = new Array();
	var stuId = new Array();
	var count = $("#unSelected").find("input:checkbox:checked").length;
	for ( var i = 0; i < count; i++) {
		studentids[i] = $("#unSelected").find("input:checkbox:checked").eq(i)
				.val();
	}

	var inputdata = '{"cmstudentList":[';
	for ( var j = 0; j < studentids.length; j++) {
		if (j > 0) {
			inputdata = inputdata + ',';
		}
		inputdata = inputdata + '{"id":' + studentids[j] + ',"isinvolved":'
				+ true + ',"cmissionid":' + cmissionID + "}";
	}
	inputdata = inputdata + '],"courseid":' + courseid + ',"cmissionid":'
			+ cmissionID + "}";
	// ',"isinvolved":'+isinvolved+'}],"courseid":'+courseid+',"cmissionid":'+cmissionid+"}";
	$.ajax({
		cache : true,
		type : "POST",
		url : "/classInfo/tch/updateStudentsIsinvolved.action",// 此处不能使用单引号代替双引号
		dataType : 'json',
		contentType : 'application/json; charset=utf-8',
		data : inputdata,
		error : function(request, message, ex) {
			alert(request.responseText);
		},
		success : function(data) {
			if (data.ajaxResult == "success") {
				$(".message").empty();
				var j = 0;
				for ( var i = 0; i < count; i++) {
					stuId[i] = "#tr-" + studentids[i];
					$(stuId[i]).addClass("hide");
					$(stuId[i]).find(".idleStudentIndex").addClass(
							"imStudentIndex").removeClass("idleStudentIndex");
					$(stuId[i]).find(".removable").eq(0).html("参与任务");
					$(stuId[i]).find('a').html("删除参与");
					$(stuId[i]).find('a').attr(
							'onclick',
							"deleteOneStudent(" + studentids[i] + ','
									+ cmissionID + ',' + false + ")");
					var addHtml = $(stuId[i]).prop("outerHTML");
					$(stuId[i]).remove();
					$("#studentList tbody").append(addHtml);
					$(stuId[i]).removeClass("hide");
					j = i+1;
				}
				var addStudents = '<div class="clear"></div>\
					<div class="messageBox success">\
						<div class="messageTitle">\
							<span class="fl messageLogo"></span>\
							<span class="close fr" onclick="closeMessage(this)"></span>\
						</div>\
						<div class="messageContent">\
							<h4>成功添加'+j+'名学生</h4>\
						</div>\
					</div>';
				$("#addStudents").append(addStudents);
				autoIndex();
				tableGap();
				alignTablePager();
				graytablePager("#unSelected", ".pager4unSelected");
				tablePager("#studentList", ".pager4studentList");
			}
		}
	});

}

/**
 * 将一名学生添加到已参与学生列衄1�7��7
 * 
 * @param data
 */
function addOneInMissionStudent(data, index) {
	
	if (data.credit == null) {
		var addHtml = '<tr id="tr-'
				+ data.id
				+ '">\
		<td><input type="checkbox" value="'
				+ data.id
				+ '">\
		</td>\
		<td class="imStudentIndex">'
				+ index
				+ '</td>\
		<td>'
				+ data.studentCode
				+ '</td>\
		<td>'
				+ data.name
				+ '</td>\
		<td>'
				+ data.sex
				+ '</td>\
		<td>'
				+ data.studentGrade
				+ '</td>\
		<td>'
				+ data.stuClass
				+ '</td>\
		<td>'
				+ '未评分'
				+ '</td>\
		<td class="removable">'
				+ data.isinvolvedChinese
				+ '</td>\
		<td class="removable"><a href="javascript:void(0);" onclick="deleteOneStudent('
				+ data.id + ',' + data.cmissionid + ',' + false
				+ ')">删除参与</a></td>\
		</tr>';
	} else {
		var addHtml = '<tr id="tr-'
				+ data.id
				+ '">\
		<td><input type="checkbox" value="'
				+ data.id
				+ '">\
		</td>\
		<td class="imStudentIndex">'
				+ index
				+ '</td>\
		<td>'
				+ data.studentCode
				+ '</td>\
		<td>'
				+ data.name
				+ '</td>\
		<td>'
				+ data.sex
				+ '</td>\
		<td>'
				+ data.studentGrade
				+ '</td>\
		<td>'
				+ data.stuClass
				+ '</td>\
		<td>'
				+ '已评分'
				+ '</td>\
		<td class="removable">'
				+ data.isinvolvedChinese
				+ '</td>\
		<td class="removable"><a href="javascript:void(0);" onclick="deleteOneStudent('
				+ data.id + ',' + data.cmissionid + ',' + false
				+ ')">删除参与</a></td>\
		</tr>';
	}
	
	$("#studentList tbody").append(addHtml);

}

function addStudentToView(data, index) {
	if (data.credit == null) {
		var addHtml = '<tr id="tr-' + data.id
				+ '">\
		<td><input type="checkbox" value="' + data.id
				+ '">\
		</td>\
		<td name="studentIndex">' + index
				+ '</td>\
		<td>' + data.studentCode + '</td>\
		<td>'
				+ data.name + '</td>\
		<td>' + data.sex + '</td>\
		<td>'
				+ data.studentGrade + '</td>\
		<td>' + data.stuClass
				+ '</td>\
		<td>' + '未评分' + '</td>\
		</tr>';
	} else {
		var addHtml = '<tr id="tr-' + data.id
				+ '">\
		<td><input type="checkbox" value="' + data.id
				+ '">\
		</td>\
		<td name="studentIndex">' + index
				+ '</td>\
		<td>' + data.studentCode + '</td>\
		<td>'
				+ data.name + '</td>\
		<td>' + data.sex + '</td>\
		<td>'
				+ data.studentGrade + '</td>\
		<td>' + data.stuClass
				+ '</td>\
		<td>' + '已评分' + '</td>\
		</tr>';
	}
	$("#studentList4viewBox tbody").append(addHtml);
}

/**
 * 将一名学生添加到未参与学生列衄1�7��7
 * 
 * @param data
 */
function addOneIdleStudent(data, index) {
	if (data.credit == null) {
		var addHtml = '<tr id="tr-'
				+ data.id
				+ '">\
		<td><input type="checkbox" value="'
				+ data.id
				+ '">\
		</td>\
		<td class="idleStudentIndex">'
				+ index
				+ '</td>\
		<td>'
				+ data.studentCode
				+ '</td>\
		<td>'
				+ data.name
				+ '</td>\
		<td>'
				+ data.sex
				+ '</td>\
		<td>'
				+ data.studentGrade
				+ '</td>\
		<td>'
				+ data.stuClass
				+ '</td>\
		<td>'
				+ '未评分'
				+ '</td>\
		<td class="removable">'
				+ data.isinvolvedChinese
				+ '</td>\
		<td class="removable"><a href="javascript:void(0);" onclick="addOneStudentToMission('
				+ data.id + ',' + data.cmissionid + ',' + true
				+ ')">添加到任务</a></td>\
		</tr>';
	} else {
		var addHtml = '<tr id="tr-'
				+ data.id
				+ '">\
		<td><input type="checkbox" value="'
				+ data.id
				+ '">\
		</td>\
		<td class="idleStudentIndex">'
				+ index
				+ '</td>\
		<td>'
				+ data.studentCode
				+ '</td>\
		<td>'
				+ data.name
				+ '</td>\
		<td>'
				+ data.sex
				+ '</td>\
		<td>'
				+ data.studentGrade
				+ '</td>\
		<td>'
				+ data.stuClass
				+ '</td>\
		<td>'
				+ '已评分'
				+ '</td>\
		<td class="removable">'
				+ data.isinvolvedChinese
				+ '</td>\
		<td class="removable"><a href="javascript:void(0);" onclick="addOneStudentToMission('
				+ data.id + ',' + data.cmissionid + ',' + true
				+ ')">添加到任务</a></td>\
		</tr>';
	}
	$("#unSelected tbody").append(addHtml);

}
/**
 * 从已参与学生列表中删除一名学甄1�7��7
 * 
 * @param id
 */
function deleteOneStudent(id, cmissionid, isinvolved) {
	var stuId = "#tr-" + id;
	var inputdata = '{"cmstudentList":[{"id":' + id + ',"isinvolved":'
			+ isinvolved + ',"cmissionid":' + cmissionid + '}],"courseid":'
			+ courseid + ',"cmissionid":' + cmissionid + "}";
	$.ajax({
		cache : true,
		type : "POST",
		url : "/classInfo/tch/updateStudentsIsinvolved.action",// 此处不能使用单引号代替双引号
		dataType : 'json',
		contentType : 'application/json; charset=utf-8',
		data : inputdata,
		error : function(request, message, ex) {
			alert(request.responseText);
		},
		success : function(data) {
			if (data.ajaxResult = "success") {
				$(".message").empty();
				$(stuId).addClass("hide");
				$(stuId).find(".imStudentIndex").addClass("idleStudentIndex")
						.removeClass("imStudentIndex");
				$(stuId).find(".removable").eq(0).html("不参与任务");
				$(stuId).find('a').html("添加到任务");
				$(stuId).find('a').attr(
						'onclick',
						"addOneStudentToMission(" + id + ',' + cmissionid + ','
								+ true + ")");
				var addHtml = $(stuId).prop("outerHTML");
				$(stuId).remove();
				$("#unSelected tbody").append(addHtml);
				$(stuId).removeClass("hide");
				var name = $("#tr-"+id).children("#name").text();
				var deleteStudents = '<div class="clear"></div>\
					<div class="messageBox success">\
						<div class="messageTitle">\
							<span class="fl messageLogo"></span>\
							<span class="close fr" onclick="closeMessage(this)"></span>\
						</div>\
						<div class="messageContent">\
							<h4>成功从任务中删除"'+name+'"同学</h4>\
						</div>\
					</div>';
				$("#deleteStudents").append(deleteStudents);
				autoIndex();
				tableGap();
				alignTablePager();
			}
		}
	});
}

/**
 * 将一名学生添加到任务
 * 
 * @param id
 * @param e
 */
function addOneStudentToMission(id, cmissionid, isinvolved) {
	var stuId = "#tr-" + id;
	var inputdata = '{"cmstudentList":[{"id":' + id + ',"isinvolved":'
			+ isinvolved + ',"cmissionid":' + cmissionid + '}],"courseid":'
			+ courseid + ',"cmissionid":' + cmissionid + "}";
	$.ajax({
		cache : true,
		type : "POST",
		url : "/classInfo/tch/updateStudentsIsinvolved.action",// 此处不能使用单引号代替双引号
		dataType : 'json',
		contentType : 'application/json; charset=utf-8',
		data : inputdata,
		error : function(request, message, ex) {
			alert(request.responseText);
		},
		success : function(data) {
			if (data.ajaxResult = "success") {
				$(".message").empty();
				$(stuId).addClass("hide");
				$(stuId).find(".idleStudentIndex").addClass("imStudentIndex")
						.removeClass("idleStudentIndex");
				$(stuId).find(".removable").eq(0).html("参与任务");
				$(stuId).find('a').html("删除参与");
				$(stuId).find('a').attr(
						'onclick',
						"deleteOneStudent(" + id + ',' + cmissionid + ','
								+ false + ")");
				var addHtml = $(stuId).prop("outerHTML");
				$(stuId).remove();
				$("#studentList tbody").append(addHtml);
				$(stuId).removeClass("hide");
				var name = $("#tr-"+id).children("#name").text();
				var addStudents = '<div class="clear"></div>\
					<div class="messageBox success">\
						<div class="messageTitle">\
							<span class="fl messageLogo"></span>\
							<span class="close fr" onclick="closeMessage(this)"></span>\
						</div>\
						<div class="messageContent">\
							<h4>成功添加"'+name+'"同学至任务</h4>\
						</div>\
					</div>';
				$("#addStudents").append(addStudents);
				autoIndex();
				tableGap();
				alignTablePager();;
				tablePager("#studentList", ".pager4studentList");
			}
		}
	});
}

function ajaxFileUpload() {
	alert(document.getElementById("file").value);
	var inputdata = '{"cmissionid":' + cmissionID + ',"fileName":"'
			+ document.getElementById("file").value + '"}';
	$.ajaxFileUpload({
		url : 'upload/saveFile.action',// 用于文件上传的服务器端请求地址
		secureuri : false,// 一般设置为false
		fileElementId : 'file',// 文件上传空间的id属性 <input type="file" id="file"
								// name="file" />
		dataType : 'json',// 返回值类型 一般设置为json
		data : inputdata,
		success : function(data, status) // 服务器成功响应处理函数
		{
			alert(data.newfilename);// 从服务器返回的json中取出message中的数据,其中message为在struts2中定义的成员变量
			$("#result").attr("value", data.message);
			if (typeof (data.error) != 'undefined') {
				if (data.error != '') {
					alert(data.error);
				} else {
					alert(data.message);
				}
			}
		},
		error : function(data, status, e)// 服务器响应失败处理函数
		{
			alert(e);
		}
	})

	return false;

}

/**
 * 自动标序
 */
function autoIndex() {
	var studentList = $(".imStudentIndex");
	for ( var i = 0; i < studentList.length; i++) {
		studentList.eq(i).html(i + 1);
	}
	var unSelected = $(".idleStudentIndex");
	for ( var i = 0; i < unSelected.length; i++) {
		unSelected.eq(i).html(i + 1);
	}
	var viewList = $(".viewStudentIndex");
	for ( var i = 0; i < viewList.length; i++) {
		viewList.eq(i).html(i + 1);
	}
	$("#studentList4viewBox").trigger("update", [ resort ]);
	$("#studentList").trigger("update", [ resort ]);
	$("#unSelected").trigger("update", [ resort ]);
}

/**
 * 消除空白
 * 解决listTable数据减少后出现的大片空白问题
 * author:GongXiang
 */
function alignTablePager(){
	$(".pagerSavedHeightSpacer").height("0px");
}