var resort = true;

/**
 * 鏄剧ず/闅愯棌椤甸潰鍖哄煙
 * @param e
 */
function slideBox(e){
	var $this=$(e);
	if($this.hasClass("active")){
		$this.next(".boxContext").slideToggle();
	}
}

/**
 * 璇剧▼璁剧疆涓嬫媺妗嗗唴瀹规敼鍙樻椂瑙﹀彂
 * 杩斿洖淇敼鐨勮绋嬭缃殑绠�浠�
 */
function switchCsetting(){

	var csettingId=$("#selectCsetting").children().eq($("#selectCsetting").get(0).selectedIndex).attr("csettingid");
	alert(csettingId);
	var inputdata = '{"csettingId":'+csettingId+'}';

	$.ajax({
		cache : true,
		type:"POST",
		url : "/classInfo/acamgr/viewDescription.action",
		contentType : 'application/json; charset=utf-8',
		data : inputdata,
		error: function (request, message, ex) {
			alert(request.responseText);
		},
		success : function(data) {
			if(data.ajaxResult=="success"){
				$("#courseDescription").html(data.description);		
			}
		}
	});
}

/**
 * 鍏ㄩ��
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
 * 鎻愪氦鏂拌绋�
 */
function submitNewCourse() {
	var csettingId=$("#selectCsetting").children().eq($("#selectCsetting").get(0).selectedIndex).attr("csettingid");
	var csettingName=$("#selectCsetting").children().eq($("#selectCsetting").get(0).selectedIndex).attr("csettingname");
	var csettingCode=$("#selectCsetting").children().eq($("#selectCsetting").get(0).selectedIndex).attr("csettingcode");
	var teacherId=$("#selectTeacher").children().eq($("#selectTeacher").get(0).selectedIndex).attr("teacherid");
	
	if($("#selectTerm").val()=="绗竴瀛︽湡"){
	var inputdata = '{"course":{"csettingid":'+csettingId+
	',"name":"'+csettingName+
	'","code":"'+csettingCode+
	'","year":"'+$("#selectYear").val()+
	'","term":'+1+
	',"teacherid":'+teacherId+
	',"description":"'+$("#courseDescription").html()+'"}}'; 
	}
	else{
		var inputdata = '{"course":{"csettingid":'+csettingId+
		',"name":"'+csettingName+
		'","code":"'+csettingCode+
		'","year":"'+$("#selectYear").val()+
		'","term":'+2+
		',"teacherid":'+teacherId+
		',"description":"'+$("#courseDescription").html()+'"}}'; 
		}

	$.ajax({
		cache : true,
		type:"POST",
		url : "/classInfo/acamgr/submitNewCourse.action",
		contentType : 'application/json; charset=utf-8',
		data : inputdata,
		error: function (request, message, ex) {
			alert(request.responseText);
		},
		success : function(data) {
			if(data.ajaxResult=="success"){
				$("#studentListTitle").addClass("active");
				$("#studentList").fadeIn();
				$("#editBox4info").fadeOut();
                $("#courseNameView").html($("#selectCsetting").children().eq($("#selectCsetting").get(0).selectedIndex).html());
                $("#courseYearView").html($("#selectYear").val());
                $("#courseTermView").html($("#selectTerm").val());
                $("#courseDescriptionView").html($("#courseDescription").html());
				$("#viewBox4info").fadeIn();
				autoIndex();				
			}
		}
	});
}

function updateCourse(){
	$("#viewBox4info").fadeOut();
	$("#editBox4info").fadeIn();
}

function downloadExcelModel(){
	window.location.href = "Excel/Cstudent.xls";
}

function showFileImport(){
	$("#fileImportSection").fadeIn();
}

function importStudentsFromFile(){
	$("#InvolvedStudentList").fadeIn();
	//鏈畬鎴�
	//ajax寮傛涓婁紶excel鏂囦欢
}



/**
 * 鎵归噺鍒犻櫎瀛︾敓
 */
function deleteStudents(){
	var studentids = new Array();
	var stuId = new Array();
	var count=$("#studentList").find("input:checkbox:checked").length;
	for(var i = 0; i<count; i++){
		studentids[i]=$("#studentList").find("input:checkbox:checked").eq(i).val();
	}

	var inputdata ='{"cstudentList":[';
	for(var j = 0; j<studentids.length;j++){
		if(j>0){
			inputdata=inputdata+',';
		}
		inputdata = inputdata+'{"id":'+studentids[j]+
		',"isinvolved":'+false+"}";
	}
	inputdata =inputdata+'],"courseid":'+courseid+"}";
	$.ajax({
		cache : true,
		type:"POST",
		url : "/classInfo/acamgr/updateCourseStudents.action",//锟剿达拷锟斤拷锟斤拷使锟矫碉拷锟斤拷糯锟斤拷锟剿拷锟斤拷
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
					$(stuId[i]).find(".icStudentIndex").addClass("idleStudentIndex").removeClass("icStudentIndex");
					$(stuId[i]).find(".removable").eq(0).html("涓嶅弬涓庝换鍔�");
					$(stuId[i]).find('a').html("娣诲姞鍒颁换鍔�");
					$(stuId[i]).find('a').attr('onclick',"addOneStudent("+studentids[i]+','+courseid+','+true+")");
					var addHtml=$(stuId[i]).prop("outerHTML");
					$(stuId[i]).remove();	
					$("#unSelected tbody").append(addHtml);
					$(stuId[i]).removeClass("hide");
				}		
				autoIndex();
			}
		}
	});

}

/**
 * 鎵归噺娣诲姞瀛︾敓
 */
function addStudents(){
	var studentids = new Array();
	var stuId = new Array();
	var count=$("#unSelected").find("input:checkbox:checked").length;
	for(var i = 0; i<count; i++){
		studentids[i]=$("#unSelected").find("input:checkbox:checked").eq(i).val();
	}

	var inputdata ='{"cstudentList":[';
	for(var j = 0; j<studentids.length;j++){
		if(j>0){
			inputdata=inputdata+',';
		}
		inputdata = inputdata+'{"id":'+studentids[j]+
		',"isinvolved":'+true+"}";
	}
	inputdata =inputdata+'],"courseid":'+courseid+"}";
	$.ajax({
		cache : true,
		type:"POST",
		url : "/classInfo/acamgr/updateCourseStudents.action",//锟剿达拷锟斤拷锟斤拷使锟矫碉拷锟斤拷糯锟斤拷锟剿拷锟斤拷
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
					$(stuId[i]).find(".idleStudentIndex").addClass("icStudentIndex").removeClass("idleStudentIndex");
					$(stuId[i]).find(".removable").eq(0).html("鍙備笌浠诲姟");
					$(stuId[i]).find('a').html("鍒犻櫎鍙備笌");
					$(stuId[i]).find('a').attr('onclick',"deleteOneStudent("+studentids[i]+','+courseid+','+false+")");
					var addHtml=$(stuId[i]).prop("outerHTML");
					$(stuId[i]).remove();	
					$("#studentList tbody").append(addHtml);
					$(stuId[i]).removeClass("hide");
				}	
				autoIndex();
			}
		}
	});

}

/**
 * 灏嗕竴鍚嶆湭閫夎瀛︾敓鎻掑叆鍒伴〉闈�
 * @param data
 */
function addOneIdleStuToHtml(data,index){
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
		<td>'+'鏈瘎鍒�'+'</td>\
		<td class="removable">'+data.isinvolvedChinese+'</td>\
		<td class="removable"><a href="javascript:void(0);" onclick="addOneStudent('+data.id+','+data.courseid+','+true+')">锟斤拷拥锟斤拷纬锟�/a></td>\
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
		<td>'+'宸茶瘎鍒�'+'</td>\
		<td class="removable">'+data.isinvolvedChinese+'</td>\
		<td class="removable"><a href="javascript:void(0);" onclick="addOneStudentToMission('+data.id+','+data.courseid+','+true+')">锟斤拷拥锟斤拷纬锟�/a></td>\
		</tr>';
	}
	$("#unSelected tbody").append(addHtml) ;

}

/**
 * 灏嗕竴鍚嶅凡閫夎瀛︾敓鎻掑叆鍒伴〉闈�
 * @param data
 */
function addOneIcStuToHtml(data,index){
	if(data.credit==null){
		var addHtml =
	'<tr id="tr-'+data.id+'">\
		<td><input type="checkbox" value="'+data.id+'">\
		</td>\
		<td class="icStudentIndex">'+index+'</td>\
		<td>'+data.studentCode+'</td>\
		<td>'+data.name+'</td>\
		<td>'+data.sex+'</td>\
		<td>'+data.studentGrade+'</td>\
		<td>'+data.stuClass+'</td>\
		<td>'+'鏈瘎鍒�'+'</td>\
		<td class="removable">'+data.isinvolvedChinese+'</td>\
		<td class="removable"><a href="javascript:void(0);" onclick="deleteOneStudent('+data.id+','+data.courseid+','+false+')">删锟斤拷锟斤拷锟�/a></td>\
	</tr>';
	}
	else{
		var addHtml =
	'<tr id="tr-'+data.id+'">\
		<td><input type="checkbox" value="'+data.id+'">\
		</td>\
		<td class="icStudentIndex">'+index+'</td>\
		<td>'+data.studentCode+'</td>\
		<td>'+data.name+'</td>\
		<td>'+data.sex+'</td>\
		<td>'+data.studentGrade+'</td>\
		<td>'+data.stuClass+'</td>\
		<td>'+'宸茶瘎鍒�'+'</td>\
		<td class="removable">'+data.isinvolvedChinese+'</td>\
		<td class="removable"><a href="javascript:void(0);" onclick="deleteOneStudent('+data.id+','+data.courseid+','+false+')">删锟斤拷锟斤拷锟�/a></td>\
	</tr>';
	}
	$("#studentList tbody").append(addHtml) ;

}

/**
 *鍒犻櫎涓�鍚嶅鐢�
 * @param id
 */
function deleteOneStudent(id,courseid,isinvolved){
	var stuId ="#tr-"+id;
	var inputdata ='{"cstudentList":[{"id":'+id+
	',"isinvolved":'+isinvolved+'}],"courseid":'+courseid+"}";
	$.ajax({
		cache : true,
		type:"POST",
		url : "/classInfo/acamgr/updateCourseStudents.action",//锟剿达拷锟斤拷锟斤拷使锟矫碉拷锟斤拷糯锟斤拷锟剿拷锟斤拷
		dataType : 'json',
		contentType : 'application/json; charset=utf-8',
		data : inputdata,
		error: function (request, message, ex) {
			alert(request.responseText);
		},
		success : function(data) {
			if(data.ajaxResult="success"){
				$(stuId).addClass("hide");
				$(stuId).find(".icStudentIndex").addClass("idleStudentIndex").removeClass("icStudentIndex");
				$(stuId).find(".removable").eq(0).html("涓嶅弬涓庤绋�");
				$(stuId).find('a').html("娣诲姞鍒拌绋�");
				$(stuId).find('a').attr('onclick',"addOneStudent("+id+','+courseid+','+true+")");
				var addHtml=$(stuId).prop("outerHTML");
				$(stuId).remove();	
				$("#unSelected tbody").append(addHtml);
				$(stuId).removeClass("hide");
				autoIndex();
			}
		}
	});
}

/**
 * 娣诲姞涓�鍚嶅鐢�
 * @param id
 * @param e
 */
function addOneStudent(id,courseid,isinvolved){
	var stuId ="#tr-"+id;
	var inputdata ='{"cstudentList":[{"id":'+id+
	',"isinvolved":'+isinvolved+'}],"courseid":'+courseid+"}";
	$.ajax({
		cache : true,
		type:"POST",
		url : "/classInfo/acamgr/updateCourseStudents.action",//锟剿达拷锟斤拷锟斤拷使锟矫碉拷锟斤拷糯锟斤拷锟剿拷锟斤拷
		dataType : 'json',
		contentType : 'application/json; charset=utf-8',
		data : inputdata,
		error: function (request, message, ex) {
			alert(request.responseText);
		},
		success : function(data) {
			if(data.ajaxResult="success"){
				$(stuId).addClass("hide");
				$(stuId).find(".idleStudentIndex").addClass("icStudentIndex").removeClass("idleStudentIndex");
				$(stuId).find(".removable").eq(0).html("鍙備笌璇剧▼");
				$(stuId).find('a').html("鍒犻櫎鍙備笌");
				$(stuId).find('a').attr('onclick',"deleteOneStudent("+id+','+courseid+','+false+")");
				var addHtml=$(stuId).prop("outerHTML");
				$(stuId).remove();	
				$("#studentList tbody").append(addHtml);
				$(stuId).removeClass("hide");
				autoIndex();
			}
		}
	});
}



function batchImport(){
	var inputdata ='{"cstudentList":[{"id":'+id+
	',"isinvolved":'+isinvolved+'}],"courseid":'+courseid+"}";
	$.ajax({
		cache : true,
		type:"POST",
		url : "/classInfo/acamgr/batchImport.action",
		dataType : 'json',
		contentType : 'application/json; charset=utf-8',
		data : inputdata,
		error: function (request, message, ex) {
			alert(request.responseText);
		},
		success : function(data) {
			if(data.ajaxResult="success"){
				for(var i = 0;i<data.involvedCstudentDetailViewList.length;i++){
					addOneIcStuToHtml(data.involvedCmstudentDetailViewList[i],i+1);
				}			
			}
		}
	});
}

/**
 * 鑷姩鎺掑簭
 */
function autoIndex(){
	var studentList=$(".icStudentIndex");
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