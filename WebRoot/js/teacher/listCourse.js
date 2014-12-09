var resort = true;
tablePager("#courseTable",'.courseTablePager');

/**
 *listCourse.jsp页面初始化函敄1�7
 */
$(document).ready(function(){
	
});



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