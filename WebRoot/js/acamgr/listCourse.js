var resort = true;
tablePager("#courseTable",'.courseTablePager');

/**
 *listCourse.jsp页面初始化函敄1�7
 */
$(document).ready(function(){
	
});

/**
 * 删除丄1�7门开设课稄1�7
 */
function deleteCourse(id){
	var gBoxId = "#tr-"+id;
	var inputdata = '{"courseid":'+id+'}';
	$.ajax({
		cache : true,
		type:"POST",
		url : "/classInfo/acamgr/deleteCourse.action",//此处不能使用单引号代替双引号
		//dataType : 'json',
		contentType : 'application/json; charset=utf-8',
		data : inputdata,
		error: function (request, message, ex) {
		    alert(request.responseText);
		},
		success : function(data) {
			
			if(data.ajaxResult=="success"){
				resetForm();	
				$(gBoxId).remove();	//在小组列表中移除该小组1�7
				autoIndex();
			   	tableGap();	   
			}
			
		}
		});
}

function autoIndex(){
	var listCourse = $("#listCourse").find("[name='courseIndex']");
	for(var i =0;i<listCourse.length;i++){
		listCourse.eq(i).html(i+1);
	}
	$("#listCourse").trigger("update", [resort]);
}
/**
 * 重置表单
 */
function resetForm(){
	$("[type='text']").attr("value","");
	$("[type='checkbox']").attr("checked",false);
}