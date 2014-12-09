<%@page import="com.wuhei.cms.model.Cmission"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<s:url id="editCourseUrl" action="editCourse" />
<s:url id="downloadAttachUrl" action="downloadAttach" />
<s:url id="importCstudentUrl" action="importCstudent" />
<s:url id="listCmissionUrl" action="listCmission" />
<s:url id="viewCourseUrl" action="viewCourse" />
<s:url id="listCourseStudentUrl" action="listCourseStudent"/>
<div id="container">
		<div class="w1200 white bc">
			<div class="w1060 bc pb30">
				<div class="main-boxes">
  
					<div class="main-box" >
						<h2 class="boxTitle "id="cView" onclick="slideBox(this);">课程信息</h2>
						<div class="boxContext none">
							<div class="m30 boxForm f16 viewBox4intro" >
									<p>
									<span class="mr20 w80">课程编号：</span><span><s:property
										value="courseDetailView.code" /></span>
									
									</p>
									<p><span class="mr20 w80">课程名称：</span>
									   <span><s:property value="courseDetailView.name" /></span>
									
									</p>
									<p><span class="mr20 w80">课程类型：</span>
									   <span><s:property value="courseDetailView.type" /></span>
									
									</p>
									<p><span class="mr20 w80">学年：</span>
									   <span><s:property value="courseDetailView.year" /></span>
									
									</p>
									<p><span class="mr20 w80">学期：</span>
									   <span><s:property value="courseDetailView.term" /></span>
									
									</p>
						         <p>
								<span class="mr20 w80 tr">开课老师：</span><span> <s:property
										value="mainCteacher.code" />-<s:property
										value="mainCteacher.name" />
							   </span>
							    </p>
							    <span><s:iterator value="cteachers" status="sts">
									<p>
										<span class="mr20 w80 tr"></span><span> <s:property
												value="code" />-<s:property value="name" /> </span>
									</p>
								</s:iterator> </span>
									

									<p><span class="mr20 w80">课程简介：</span>
									
									</p>
									<div class="formText descriptionBox f16 t2 ">								
									${courseDetailView.description}
									</div>
									<p><span class="mr20 w80">附件：</span>
									
									<a href="<s:property value="%{downloadAttachUrl}"/>?courseid=<s:property value="courseid"/>&csettingid=<s:property 
										value="csettingid"/>"><span><s:property
												value="courseDetailView.oldname" /></span>
												</a>
												
										
									<div class="tc h50">
									<a href="<s:property value="%{editCourseUrl}"/>?courseid=<s:property value="courseid"/>&csettingid=<s:property value="csettingid"/>">
									
									<input type="button" class="btn green" value="编&nbsp辑" />
									
									</a> 
									</div>
								</div>
							
						</div>
					</div>
					
					
					
					<div class="main-box " >
						<h2 class="boxTitle " onclick="slideBox(this);" id="cStudent">选课学生</h2>
						<div class="boxContext none">
								<div class="m30 f16">
									<div class="formTable-context white">
									
									<div class="listTable w1000 bc cgray">

							 			<br>

										<table id="studentTable" class="bc f16" cellspacing="0" cellpadding="0" width="960">
											<thead>

												<tr>
													
													<th width="40">序号</th>
													<th width="180">学号</th>
													<th width="250">姓名</th>
													<th width="80">性别</th>
													<th width="120">年级</th>
													<th width="150">班级</th>
													<th width="80">总评</th>
													
												</tr>
											</thead>
											<tfoot>

												<tr>
												
													<th width="40">序号</th>
													<th width="180">学号</th>
													<th width="250">姓名</th>
													<th width="80">性别</th>
													<th width="120">年级</th>
													<th width="150">班级</th>
													<th width="80">总评</th>
													
												</tr>
											</tfoot>
											<tbody>
											
											<s:iterator value="cstudentDetailViewList" id="coursetable"
														status="sts">

														<tr id="tr-<s:property value="id"/>">

															
															<td class="tc" name="cstudentIndex"><s:property
																	value="#sts.count" />
															</td>
															<td><s:property value="code" />
															</td>
															<td><s:property value="name" />
															</td>
															<td><s:property value="sex" />
															</td>
															<td><s:property value="grade" />
															</td>
															<td><s:property value="stuclass" />
															</td>
															<td><s:property value="isEvaluation" />
															</td>
															
													</s:iterator>

										</tbody>
										</table>
										 
										<div id="studentTablePager" class="pager studentTablePager fr">
											<span class="fl">每页显示数量：</span> <select
													class="pagesize  w50 fl">
													<option selected="selected" value="10">10</option>
													<option value="20">20</option>
													<option value="30">30</option>
													<option value="40">40</option>
												</select> <span><img src="images/first.png"
													class="pageButton first fl" alt="First" title="First page" />
												</span> <span><img src="images/prev.png"
													class="pageButton prev fl" alt="Prev" title="Previous page" />
												</span> <span class="pagedisplay f16 cGray fl"></span>
												<!-- this can be any element, including an input -->
												<span><img src="images/next.png"
													class="pageButton next fl" alt="Next" title="Next page" />
												</span> <span><img src="images/last.png"
													class="pageButton last fl" alt="Last" title="Last page" />
												</span> <span class="fl">跳转: </span><select class="gotoPage w50 fl"></select>
										</div>
											<div class="clear pb10"></div>
										
										</div>
										<br>
										<br>
										
									</div>
								 </div>
								</div>
						
					</div>
					<div class="main-box" >
						<h2 class="boxTitle " onclick="slideBox(this);"id="cGroup">课程分组</h2>
						<div class="boxContext none">
						<div class="m30 formTableBox f16">
							<div class="formTable-meta">
								<a href="tch/listUngroupedStudents.action?courseid=${requestScope.courseid}&csettingid=${requestScope.csettingid}"><div  class="button_blue m10 f14 fr" >编辑分组</div></a>
							</div>
							<div id="groups" class="formTable-context white">
								<div class="listTable w1000 bc cgray">
									<br>
									<h3 class="f20 ml20 mr20"> <strong>小组列表</strong></h3>
									<div class="uline ml20 mr20"></div>
									<div class="groupTable m20 bc">
										<h4 class="groupMeta">
											<span class="w50">&nbsp</span>
											<span class="w100">小组编号</span>
											<span class="w400">小组名</span>
											<span
										class="w100">队长名</span>
											<span class="w100">小组人数</span>
											
										</h4>
										<div class="cb"></div>
													<div class="groupBoxes">
								<s:iterator value="cgroupDetailViews" status="sts">
									<div id="groupBox-<s:property value="id"/>" class="groupBox">
										<!-- groupTitle内部参数和组合id均为小组id -->
										<div class="groupTitle active">
											<span class="w50 mt5"></span>
											<span name="groupIndex" class="w100"><s:property value="#sts.count"/></span> 
											<span name="groupName" class="w400"><s:property value="name"/></span> 
											<span name="groupLeaderName" class="w100"><s:property value="leaderName"/></span>
											<span name="groupMembers" class="w100"><s:property value="count"/></span> 
											<span name="groupOperation" ></span>
											<div class="fr mr10 show" onclick="getGroupDetail(<s:property value="id"/>,this)">╋</div>
										</div>
										<!-- tr学生id，g开头的为groupid，js函数传参，第一个为学生id，第二个为组id -->
										<div class="groupContext">
											<table id="groupTable-<s:property value="id"/>" class="bc f14" cellspacing="0" cellpadding="0" width="900">
												<thead>
													<tr name="tableTitle">
														<th width="50">&nbsp</th>
														<th width="230">学号</th>
														<th width="160">姓名</th>
														<th width="110">年级</th>
														<th width="180">班级</th>
														<th width="180">组长标识</th>
														<th width="0"></th>
													</tr>
												</thead>
												<tbody>
								
												</tbody>
											</table>
										</div>
										<br>
									</div>
									
								</s:iterator>
									
							</div>
								</div>
							</div>
						</div>
						</div>
						</div>
					</div>
					
				</div>
				<br>
				<div class="clear mb50"></div>
			</div>
		</div>
	</div>
<%@ include file="../footer.jsp"%>

<script type="text/javascript" charset="utf-8"
		src="<s:url value="js/teacher/courseGroups.js"/>"></script>
   	<script type="text/javascript">
   
tablePager("#studentTable",'#studentTablePager');   	
   	function showIndex(e)
   	{
   	   slideBox(e);
   	}
   	
  </script>
 <script type="text/javascript">
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
					<td>组员</td>\
					<td>\
					</td>\
				</tr>\
				';
			$(tableId).append(inputdata) ;
		}
	}
	

	</script>
<script>
//切换没成功
		function showIntroEditBox4teacher() {
		$("#viewBoxintro4teacher").fadeOut(500,function(){
			$("#editBoxintro4teacher").fadeIn();
		});
	}
		function showViewBox4Intro() {
		$("#editBoxintro4teacher").fadeOut(500,function(){
			$("#viewBoxintro4teacher").fadeIn();
		});
	}
</script>
<script>
/*
 * @pagm 锚节点ID 
 */
	$(document).ready(function(){
		var mao = window.location.hash;
		//$("html,body").animate({scrollTop:$(mao).offset().top},1000);
		$(mao).addClass("active");
		$(mao).next().show();
		if (mao == ""){
			$("#cView").addClass("active");
			$("#cView").next().show();
		}
	});
</script>
<script>
$(document).ready(function(){
/*删除多余P标签*/
	 delP();
  /*切换标签按钮*/
	changeTab();
	});
 /*切换标题名字*/
 $("#cView").click(function(){
 if ($("#cView").text() == "课程信息"){
   		$("#cView").text("<s:property value="courseDetailView.name" />");
   }
   else {
   	$("#cView").text("课程信息");
   }	
   });
   
</script>