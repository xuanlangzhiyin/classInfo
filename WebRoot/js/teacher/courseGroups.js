//$("#unGroupedTable").tablesorter();
var resort = true;
tablePager("#unGroupedTable",'#unGroupedTablePager');
resetForm();
	/**
	 *courseGroups.jsp页面初始化函数
	 */
	$(document).ready(function(){
		//	隐藏新增小组/添加到组等操作框
	   	$(".metaBox").hide();
	   	
	   	//  切换显示新增小组操作框
		$("#newGroup").click(function(){
	   		$("#addToGroupBox").slideUp(500,function(){
	   			$("#newGroupBox").slideToggle();
	   		});
			
		});
		
		//	隐藏修改学生小组操作框
		$(".editFormBox").hide();
		
		//	隐藏小组组员详情框
		$(".groupContext").hide();
	});
	
	
	/**
	 * 全选操作
	 * @param checkFormId 全选的范围
	 * @param e 对象本身
	 * @author msl
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
	
	function checkAll4table(checkFormId,e){
		var $this=$(e);
		if($this.hasClass("active")){
			$(checkFormId).find(":checkbox:visible").attr("checked",false);
			$this.removeClass("active");
		}else{
			$(checkFormId).find(":checkbox:visible").attr("checked",true);
			$this.addClass("active");
		}
	}

	/**
	 * 在未分组表格内添加一个学生
	 * @param data 学生对象
	 * @author msl
	 */
	function addAUngroupedStudent(data){
		var addHtml ='<tr id="tr-'+data.id+'">\
						<td><input type="checkbox" value="'+data.id+'">\
						</td>\
						<td name="studentIndex">1</td>\
						<td>'+data.code+'</td>\
						<td>'+data.name+'</td>\
						<td>'+data.grade+'</td>\
						<td>'+data.stuclass+'</td>\
						<td class="tc">\
							<a class="editFormLink" href="javascript:void();" onclick="showEditForm('+data.id+',this)">编辑分组</a>\
							<span id="editForm-'+data.id+'" class="editFormBox hide">\
								<select id="select-'+data.id+'" class="textInputBox w100 h25 f13 m5 vm">\
									<option>未分组/option>\
									<option>1</option>\
									<option>2</option>\
									<option>3</option>\
								</select> \
								<span class="button_bule_s f12 h25 m5 dlb" onclick="addAstudentToGroup('+data.id+',this)">确认</span>\
								<span class="button_gray_s f12 h25 m5 dlb" onclick="hideEditForm('+data.id+',this)">取消</span>\
							</span>\
						</td>\
					</tr>';
		$("#unGroupedTable tbody").append(addHtml) ;
		
	}
	

	/**
	 * 将一个未分组学生增加到已有组中
	 * @param sid 学生主键
	 * @param e this
	 * @author msl
	 */
	function addAstudentToGroup(sid,e){
			var $this=$(e);
			var selectid="#select-"+sid;
			groupid=$(selectid).val();
			var inputdata='{"cstudentids":['+sid;
			inputdata =inputdata+'],"cgroupid":"'+groupid+'","courseid":'+courseid+'}';
		var trid="#tr-"+sid;
		$.ajax({
			cache : true,
			type:"POST",
			url : "/classInfo/tch/addToGroup.action",//此处不能使用单引号代替双引号
			//dataType : 'json',
			contentType : 'application/json; charset=utf-8',
			data : inputdata,
			error: function (request, message, ex) {
			    alert(request.responseText);
			},
			success : function(data) {
				if(data.ajaxResult=="success"){
					resetForm();
					var groupid = "#groupBox-"+data.addedCgroupDetailView.id;
					$(groupid).remove();
					writeAgroupInDom(data.addedCgroupDetailView);
					
					$(trid).remove();	//在未分组学生列表中移除该组	
					tableGap();
					autoIndex();
					alert("成功添加到分组！");
				}
			}
			});
		
	}
	

	/**
	 * 将一个或多个未分组学生增加到已有组中
	 */
	function addStudentsToGroup(){
		if ($("#ungroupedStudent").find("input:checkbox:checked").length == 0 ||$("#selectGroupId").find("input:radio:checked").val()==null){
			alert("尚未选择分组学生或者小组！");
		}
		else{
		var groupid= $("#selectGroupId").find("input:radio:checked").val();
		var studentids = new Array();
		for(var i = 0; i<$("#ungroupedStudent").find("input:checkbox:checked").length;i++){
			studentids[i]=$("#ungroupedStudent").find("input:checkbox:checked").eq(i).val();
		}
		var inputdata='{"cstudentids":[';
		for(var j = 0; j<studentids.length;j++){
			if(j>0){
				inputdata=inputdata+',';
			}
			inputdata = inputdata+studentids[j];
		}
		inputdata =inputdata+'],"cgroupid":"'+groupid+'","courseid":'+courseid+'}';
		resetForm();
		$.ajax({
			cache : true,
			type:"POST",
			url : "/classInfo/tch/addToGroup.action",//此处不能使用单引号代替双引号
			//dataType : 'json',
			contentType : 'application/json; charset=utf-8',
			data : inputdata,
			error: function (request, message, ex) {
			    alert(request.responseText);
			},
			success : function(data) {
				if(data.ajaxResult=="success"){
					resetForm();
					var groupid = "#groupBox-"+data.addedCgroupDetailView.id;
					$(groupid).remove();
					writeAgroupInDom(data.addedCgroupDetailView);
					
					tableGap();
					autoIndex();
				}
				for(var j = 0; j<studentids.length;j++){
					var trid="#tr-"+studentids[j];
					$(trid).remove();	
				}
				
			}
			});
		
		}
	}
	
	/**
	 * 显示编辑学生分组操作框
	 * 异步请求现有小组列表
	 */
	function showEditForm(sid,e){
		var $this=$(e);
		var inputdata = '{"courseid":'+courseid+'}';
		var editFormId ="#editForm-"+sid+" select";
		$.ajax({
			cache : true,
			type:"POST",
			url : "/classInfo/tch/getGroups.action",//此处不能使用单引号代替双引号
			//dataType : 'json',
			contentType : 'application/json; charset=utf-8',
			data : inputdata,
			error: function (request, message, ex) {
			    alert(request.responseText);
			},
			success : function(data) {
				if(data.ajaxResult=="success"){
					resetForm();
					var inputHtml = '';
					for(var i = data.cgroupDetailViews.length-1;i>=0;i--){
						inputHtml = inputHtml + '<option value="'+data.cgroupDetailViews[i].id+'">'+data.cgroupDetailViews[i].name+'</option> ';
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
	 * 隐藏编辑学生分组操作框
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
	 * 新增一个组，将一个或多个未分组学生增加到组中
	 */
	function newGroup(){
		var groupName = $("#newGroupName").val() ;
		var studentids = new Array();
		if ($("#ungroupedStudent").find("input:checkbox:checked").length == 0 ){
			alert("请选择学生进行分组");
		}
		else{
		for(var i = 0; i<$("#ungroupedStudent").find("input:checkbox:checked").length;i++){
			studentids[i]=$("#ungroupedStudent").find("input:checkbox:checked").eq(i).val();
		}
		var cstudentidsList='{"cstudentids":[';
		for(var j = 0; j<studentids.length;j++){
			if(j>0){
				cstudentidsList=cstudentidsList+',';
			}
			cstudentidsList = cstudentidsList+studentids[j];
		}
		cstudentidsList =cstudentidsList+'],"cgroupName":"'+groupName+'","courseid":'+courseid+'}';
		resetForm();
		$.ajax({
			cache : true,
			type:"POST",
			url : "/classInfo/tch/newGroup.action",//此处不能使用单引号代替双引号
			//dataType : 'json',
			contentType : 'application/json; charset=utf-8',
			data : cstudentidsList,
			error: function (request, message, ex) {
				 alert(request.responseText);
			    
			},
			success : function(data) {
				if(data.ajaxResult=="success"){
					resetForm();
					writeAgroupInDom(data.addedCgroupDetailView);
					for(var j = 0; j<studentids.length;j++){
						var trid="#tr-"+studentids[j];
						$(trid).remove();
					}
					$(".message").empty();
					
					tableGap();
					autoIndex();
				}
			}
			});
		}
	}
	
	/**
	 * 切换显示新增学生到已有组操框
	 * 异步请求现有小组列表
	 */
	function showAddToGroupBox(){
		var inputdata = '{"courseid":'+courseid+'}';
		$("#newGroupBox").slideUp(500,function(){
				if($("#addToGroupBox").css("display") == "none"){
					$.ajax({
						cache : true,
						type:"POST",
						url : "/classInfo/tch/getGroups.action",//此处不能使用单引号代替双引号
						//dataType : 'json',
						contentType : 'application/json; charset=utf-8',
						data : inputdata,
						error: function (request, message, ex) {
						    alert(request.responseText);
						},
						success : function(data) {
							if(data.ajaxResult=="success"){
								var inputHtml = '';
								for(var i = data.cgroupDetailViews.length-1;i>=0;i--){
													inputHtml = inputHtml
															+ '<label class="ml10" for="g-'
															+ data.cgroupDetailViews[i].id
															+ '">'
															+ data.cgroupDetailViews[i].name
															+ '</label> <input type="radio" name="groupid" id="g-'
															+ data.cgroupDetailViews[i].id+'" checked="checked" value="'+data.cgroupDetailViews[i].id+'" /> ';
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
	 * 撤销一到多个分组
	 */
	function cancleGroups(){
		if ($("#groups").find("input:checkbox:checked").length == 0 ){
			alert("请选择所要撤销的分组");
		}
		else{
			$("#groups").hide();
			$("#onloading").show();
			
		var groupids = new Array();
		for(var i = 0; i<$("#groups").find("input:checkbox:checked").length;i++){
			groupids[i]=$("#groups").find("input:checkbox:checked").eq(i).val();
		}
		var inputdata = '{"cgroupids":[';
		for(var h = 0;h<groupids.length;h++){
			if(h>0){
				inputdata = inputdata +",";
			}
			inputdata = inputdata +groupids[h];
		}
		 inputdata = inputdata + '],"courseid":'+courseid+'}';
		$.ajax({
			cache : true,
			type:"POST",
			url : "/classInfo/tch/cancelcGroupsGrouped.action",//此处不能使用单引号代替双引号
			//dataType : 'json',
			contentType : 'application/json; charset=utf-8',
			data : inputdata,
			error: function (request, message, ex) {
			    alert(request.responseText);
			},
			success : function(data) {
				if(data.ajaxResult=="success"){
					resetForm();
					$("#onloading").hide();
					$("#groups").show();
					$("#unGroupedTable tbody").html("");
					
					for(var i=0;i<=data.ungroupedCstudentDetailViews.length-1;i++){
						addAUngroupedStudent(data.ungroupedCstudentDetailViews[i]);
					}
					
					for(var j = 0; j<groupids.length;j++){
						var gBoxId ="#groupBox-"+groupids[j];
						$(gBoxId).remove();	//在小组列表中移除该小组
					}
					$(".message").empty();
					
					tablePager("#unGroupedTable",'#unGroupedTablePager');
					tableGap();
					autoIndex();
				}
			}
			
			});
		}
		
	}
	
	/**
	 * 撤销一个小组
	 * 
	 * @param groupid
	 */
	function cancleAGroup(groupid){
		var gBoxId ="#groupBox-"+groupid;
		var inputdata = '{"cgroupid":'+groupid+',"courseid":'+courseid+'}';
		$.ajax({
			cache : true,
			type:"POST",
			url : "/classInfo/tch/cancelcGroupGrouped.action",//此处不能使用单引号代替双引号
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

					for(var i=0;i<=data.ungroupedCstudentDetailViews.length-1;i++){
						addAUngroupedStudent(data.ungroupedCstudentDetailViews[i]);
					}
					$(gBoxId).remove();	//在小组列表中移除该小组
					$(".message").empty();
					autoIndex();
				   	tableGap();
				   	
				   
				}
				
			}
			});
		
	}
	
	/**
	 * 在小组列表中写入一个新组
	 * 
	 * @param data
	 */
	function writeAgroupInDom(data){
		var groupHtml = '<div id="groupBox-'+data.id+'" class="groupBox">\
		<div class="groupTitle active">\
			<span id="groupCheckbox-'+data.id+'" name="groupCheckbox" class="w50 mt5"> <input type="checkbox" value="'+data.id+'"> </span>\
			<span name="groupIndex" class="w100">1</span> \
			<span name="groupName" class="w400">'+data.name+'</span> \
			<span name="groupLeaderName" class="w100">'+data.leaderName+'</span>\
			<span name="groupMembers" class="w100">'+data.count+'</span> \
			<span name="groupOperation" > <a href="javascript:void();" onclick="cancleAGroup('+data.id+')">撤销组</a></span>\
			<div class="fr mr10 show" onclick="getGroupDetail('+data.id+',this)">╋</div>\
		</div>\
		<!-- tr学生id，g开头的为groupid，js函数传参，第一个为学生id，第二个为组id -->\
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
		
		$(".groupBoxes").eq(0).prepend(groupHtml);
		$(".groupContext").hide();
	}
	
	/**
	 * 撤销一个学生的分组
	 * 
	 * @param studentid
	 * @param groupid
	 */
	function cancelGroupedByStudentid(studentid,groupid){
		var trid="#tr-"+studentid;
		var inputdata = '{"cgroupid":'+groupid+',"courseid":'+courseid+',';
			inputdata = inputdata + ' "cstudentids":['+studentid+']}';
		
		$.ajax({
			cache : true,
			type:"POST",
			url : "/classInfo/tch/cancelStudentGrouped.action",//此处不能使用单引号代替双引号
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

					for(var i=0;i<=data.ungroupedCstudentDetailViews.length-1;i++){
						addAUngroupedStudent(data.ungroupedCstudentDetailViews[i]);
					}
					tableGap();
					autoIndex();
					alert("取消分组成功！");
				}
			}
			});
		
	}
	
	/**
	 * 将一个学生设为组长
	 * 
	 * @param studentid
	 * @param groupid
	 * @param e
	 */
	function setGroupLeader(studentid,groupid,e){
		var $this=$(e);
		var groupleaderid= "#groupLeader-"+groupid;
		console.log(groupleaderid);
		var originalGroupleader = $(groupleaderid).parents("tr").attr("id");
		console.log(originalGroupleader);
		var originalGroupleaderId = originalGroupleader.replace(/tr-/,"");		
		var inputdata = '{"cgroupid":'+groupid+',"cstudentid":'+studentid+'}';
		$.ajax({
			cache : true,
			type:"POST",
			url : "/classInfo//tch/setGroupLeader.action",//此处不能使用单引号代替双引号
			//dataType : 'json',
			contentType : 'application/json; charset=utf-8',
			data : inputdata,
			error: function (request, message, ex) {
			    alert(request.responseText);
			},
			success : function(data) {
				resetForm();
				
				console.log();
				$("#groupBox-"+groupid).find("span[name='groupMembers']").eq(0).html(data.cgroupDetailView.count);
				$(groupleaderid).next().html('<a onclick="cancelGroupedByStudentid('+originalGroupleaderId+','+groupid+')" href="javascript:void();">取消分组</a>');
				$(groupleaderid).html('<a href="javascript:void();" onclick="setGroupLeader('+originalGroupleaderId+','+groupid+',this)">设为组长</a>').removeAttr("id");
				$this.parent().attr("id","groupLeader-"+groupid);
				$(groupleaderid).html('组长');
				$(groupleaderid).next().html('');
				$("#groupBox-"+groupid).find("span[name='groupLeaderName']").eq(0).html(data.cgroupDetailView.leaderName);
				alert("设置组长成功！");
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
		var inputdata = '{"cgroupid":'+groupid+'}';
		if($this.parent().next(".groupContext").css("display") == "none"){
			$.ajax({
				cache : true,
				type:"POST",
				url : "/classInfo/tch/getGroupMemberDetial.action",//此处不能使用单引号代替双引号
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
							
						for(var i = data.cstudentDetailViews.length-1;i>=0;i--){
							addAStudentToGroup(data.cstudentDetailViews[i],data.leaderid,data.cgroupid);
						}
						autoIndex();
					   	tableGap();
					}
				}
				});
			$this.html("━");
		}else{
			$this.html("╋");
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
				<td>'+data.code+'</td>\
				<td>'+data.name+'</td>\
				<td>'+data.grade+'</td>\
				<td>'+data.stuclass+'</td>\
				<td id="groupLeader-'+groupid+'">组长</td>\
				<td>\
				</td>\
			</tr>\
		';
		 $(tableId).prepend(inputdata) ;
		}else{
			inputdata = '\
				<tr id="tr-'+data.id+'">\
					<td>&nbsp</td>\
					<td>'+data.code+'</td>\
					<td>'+data.name+'</td>\
					<td>'+data.grade+'</td>\
					<td>'+data.stuclass+'</td>\
					<td><a href="javascript:void();" onclick="setGroupLeader('+data.id+','+groupid+',this)">设为组长</a></td>\
					<td><a href="javascript:void();" onclick="cancelGroupedByStudentid('+data.id+','+groupid+')">取消分组</a>\
					</td>\
				</tr>\
				';
			$(tableId).append(inputdata) ;
		}
	}
	


	
	/**
	 * 将未分组学生单独分成一个组
	 * 
	 * @param groupid
	 */
	function setUngroupedStudents2newgroup(){
		
		$("#ungroupedStudent").hide();
		$("#onload").show();
		
		var studentids = new Array();
		for(var i =0;i<$("#ungroupedStudent").find("input[type='checkbox']").length;i++){
			studentids[i]=$("#ungroupedStudent").find("input[type='checkbox']").eq(i).val();
		}
		var inputdata = '{"courseid":'+courseid+',"cstudentids":[';
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
			url : "/classInfo/tch/setUngroupedStudents2newGroup.action",//此处不能使用单引号代替双引号
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
					for(var i =data.cgroupDetailViews.length-1;i>=0;i--){
						writeAgroupInDom(data.cgroupDetailViews[i]);
					}
					$("#onload").hide();
					$("#ungroupedStudent").show();
					$("#ungroupedStudent tbody").html("");
					autoIndex();
				   	tableGap();
				   	
				   	for(var j = 0; j<studentids.length;j++){
						var sBoxId ="#tr-"+studentids[j];
						$(sBoxId).remove();	//在小组列表中移除该小组
					}
				  
				   
				}
				alignTablePager();
			}
			
			});
		
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
	 * 重置表单
	 */
	function resetForm(){
		$("[type='text']").attr("value","");
		$("[type='checkbox']").attr("checked",false);
		$("#addToGroupBox").slideUp();
   			$("#newGroupBox").slideUp();
		
	}
	
	/**
	 * 消除空白
	 * 解决listTable数据减少后出现的大片空白问题
	 * author:GongXiang
	 */
	function alignTablePager(){
		$(".pagerSavedHeightSpacer").height("0px");
	}