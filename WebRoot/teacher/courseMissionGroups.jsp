<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<s:url id="viewGroupMissionUrl" action="viewGroupMission" />
<div id="container">
	<div class="white w1200 bc">
		<div class="w1060 bc">  
			<div class="context">  
				<div class="m30 formTableBox f16">
				</div>  
					<div class="formTable-meta hauto">
						<div id="" class="btnBS f16 fl"
							onclick="checkAll4table('#ungroupedStudent',this)">全选</div>
						<div id="newGroup" class="button_blue w100 m10 f14 fl">新建分组</div>
						<div id="" class="button_blue w100 m10 f14 fl"
							onclick="showAddToGroupBox()">添加到组</div>
						<div id="" class="button_blue  m10 f14 fl"
							onclick="setUngroupedStudents2newgroup()">将未分组学生独自分组</div>


						<!-- 
						<input class="textInputBox fl" type="text"> 
						<input class="button_green w100 m10 f14 fl vm" value="查&nbsp询" type="button">
						 -->
						<div class="clear"></div>
						<div id="newGroupBox" class="metaBox">
							<div class="uline cBlueL"></div>
							<p class="tc">
								<span class=" c333">请输入组名：</span><input id="newGroupName"
									class="textInputBox " type="text"><span
									class="w100 button_green m10 f14 dlb" onclick="newGroup()">确认</span>
							</p>
							<div class="clear"></div>
						</div>
						<div id="addToGroupBox" class="metaBox">
							<div class="uline cBlueL"></div>
							<p class="tc">
								<span class=" c333">请选择小组：</span> <span class=" c333 cp"
									id="selectGroupId"> <!-- for input.id input.value 一一对应  -->
									<label class="ml10" for="g-1">1</label> <input type="radio"
									name="groupid" id="g-1" checked="checked" value="1" /> <label
									class="ml10" for="g-2">2</label> <input type="radio"
									name="groupid" id="g-2" value="2" /> <label class="ml10"
									for="g-3">3</label> <input type="radio" name="groupid" id="g-3"
									value="3" /> <label class="ml10" for="g-4">4</label> <input
									type="radio" name="groupid" id="g-4" value="4" /> </span> <span
									class="w100 button_green m10 f14 dlb"
									onclick="addStudentsToGroup()">确认</span>
							</p>
							<div class="clear"></div>
						</div>


					</div>
					<div id="onload" class="m30 w1000 bc tc mt100 mb100 hide">
						<img src="<s:url value="images/loading.gif"/>">
					</div>
					<div class="formTable-context white">
						<div id="ungroupedStudent" class="listTable w960 bc cGrey">
							<br>
							<h3 class="f20 ">
								<strong>未分组学生列表</strong>
							</h3>
							<div class="uline "></div>

							<table id="unGroupedTable" class="bc f14" cellspacing="0"
								cellpadding="0" width="960">
								<thead>
									<tr>
										<th class="filter-false remove sorter-false" width="50">&nbsp</th>
										<th width="80">序号</th>
										<th width="150">学号</th>
										<th width="130">姓名</th>
										<th width="120">年级</th>
										<th width="100">班级</th>
										<th width="290" class="tc filter-false remove sorter-false">操作</th>
									</tr>
								</thead>
								<tfoot>
									<tr>
										<th width="50">&nbsp</th>
										<th width="80">序号</th>
										<th width="150">学号</th>
										<th width="130">姓名</th>
										<th width="120">年级</th>
										<th width="100">班级</th>
										<th width="290" class="tc">操作</th>
									</tr>
								</tfoot>

								<tbody>
									<!-- 行id 为 tr-学生id tr内部组合id均为学生id，传的参数也都是学生id-->
									<s:iterator value="ungroupedCmstudentDetailViews" status="sts">
										<tr id="tr-<s:property value="id"/>">
											<td><input type="checkbox"
												value="<s:property value="id"/>"></td>
											<td name="studentIndex"><s:property value="#sts.count" />
											</td>
											<td><s:property value="studentCode" />
											</td>
											<td><s:property value="name" />
											</td>
											<td><s:property value="studentGrade" />
											</td>
											<td><s:property value="stuClass" />
											</td>
											<td class="tc"><a class="editFormLink"
												href="javascript:void();"
												onclick="showEditForm(<s:property value="id"/>,this)">编辑分组</a>
												<span id="editForm-<s:property value="id"/>"
												class="editFormBox hide"> <select
													id="select-<s:property value="id"/>"
													class="textInputBox w100 h25 f13 m5 vm">
														<option value="">未分组</option>
														<option value="">1</option>
														<option value="">2</option>
														<option value="" selected="selected">3</option>
												</select> <span class="button_gray_s f12 h25 m5 dlb"
													onclick="addAstudentToGroup(<s:property value="id"/>,this)">确认</span>
													<span class="button_gray_s f12 h25 m5 dlb"
													onclick="hideEditForm(<s:property value="id"/>,this)">取消</span>
											</span></td>
										</tr>
									</s:iterator>


								</tbody>

							</table>
							<br>
							<div id="unGroupedTablePager" class="pager fr mt10 mb10">
								<span class="fl">每页显示数量：</span> <select class="pagesize  w50 fl">
									<option selected="selected" value="10">10</option>
									<option value="20">20</option>
									<option value="30">30</option>
									<option value="40">40</option>
								</select> <span><img src="<s:url value="images/first.png"/>"
									class=" first fl" alt="First" title="First page" /> </span> <span><img
									src="<s:url value="images/prev.png"/>"
									class="pageButton prev fl" alt="Prev" title="Previous page" />
								</span> <span class="pagedisplay f16 cGray fl"></span>
								<!-- this can be any element, including an input -->
								<span><img src="<s:url value="images/next.png"/>"
									class="pageButton next fl" alt="Next" title="Next page" /> </span> <span><img
									src="<s:url value="images/last.png"/>"
									class="pageButton last fl" alt="Last" title="Last page" /> </span> <span
									class="fl">转至: </span><select class="gotoPage w50 fl"></select>
								<div class="clear"></div>
							</div>
							<div class="clear"></div>
						</div>
					</div>

				</div>
				<div class="m30 formTableBox f16">

					
					<div id="onloading" class="m30 w1000 bc tc mt100 mb100 hide">
						<img src="<s:url value="images/loading.gif"/>">
					</div>
					
					<div id="groups" class="formTable-context white">
						<div class="listTable w1000 bc cGrey">
							<br>
							<h3 class="f20 ml20 mr20">
								<strong>小组列表</strong>
							</h3>
							<div class="uline ml20 mr20"></div>
							<div class="groupTable m20 bc">
								<h4 class="groupMeta">
									<span class="w50">&nbsp</span>
									<span class="w100">小组编号</span> 
									<span class="w300">小组名</span>
									<span class="w150">参与状态</span>
									<span class="w100">队长名</span>
									<span class="w100">小组人数</span>
									<span>操作</span>
								
								</h4>
								<div class="cb"></div>
								<div class="groupBoxes">
									<s:iterator value="cmgroupDetailViews" status="sts">
										<div id="groupBox-<s:property value="id"/>" class="groupBox">
											<!-- groupTitle内部参数和组合id均为小组id -->
											<div class="groupTitle active">
												<span id="groupCheckbox-<s:property value="id"/>" name="groupCheckbox" class="w50 mt5">
												  <input type="checkbox" value="<s:property value="id"/>"> 
												</span>
												<span name="groupIndex" class="w100">
													<s:property value="#sts.count" /> 
												</span> 
												<span name="groupName" class="w300">
													<s:property value="name" /> 
												</span> 
												<span name="isinvolved" class="w150">
														<%
														String temp = request.getAttribute("isinvolved").toString();  
														if(temp=="true")
														out.println("参与");
														else
														out.println("不参与");
														 %>
												</span>
												<span name="groupLeaderName" class="w100">
													<s:property value="leadername" /> 
												</span> 
												<span name="groupMembers" class="w100">
													<s:property value="count" /> 
												</span> 
												<span name="groupOperation"> <a href="javascript:void();"
													onclick="cancleAGroup(<s:property value="id"/>)">撤销组</a> 
												</span>
												<div class="fr mr10 show"
													onclick="getGroupDetail(<s:property value="id"/>,this)">╋</div>
											</div>
											<!-- tr学生id，g开头的为groupid，js函数传参，第一个为学生id，第二个为组id -->
											<div class="groupContext">
												<table id="groupTable-<s:property value="id"/>"
													class="bc f14" cellspacing="0" cellpadding="0" width="900">
													<thead>
														<tr name="tableTitle">
															<th width="50">&nbsp</th>
															<th width="200">学号</th>
															<th width="130">姓名</th>
															<th width="80">年级</th>
															<th width="150">班级</th>
															<th width="150">组长标识</th>
															<th width="150">操作</th>
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
													<div class="formTable-meta">
						<div id="" class="btnBS f16 fl" onclick="checkAll('#groups',this)">全选</div>
						<div id="addAll" class="button_blue m10 f14 fl"
							onclick="cancleGroups()">批量撤销分组</div>
					</div>
								
							</div>
						</div>
					</div>
				</div>
				<div class="cb"></div>
				<div id="" class="tc h50 f16">
					<a
						href="<s:property value="%{viewGroupMissionUrl}"/>?courseid=<s:property value="courseid"/>&cmissionid=<s:property 
										value="cmissionid"/>">
						<input type="button" class="btn green" value="返&nbsp回"> </a>
				</div>

			</div>
			<div class="clear mb50"></div>
			<br>
		</div>
	</div>

	<%@ include file="../footer.jsp"%>
	<script type="text/javascript" charset="utf-8"
		src="<s:url value="js/teacher/courseMissionGroups.js"/>"></script>
	<script type="text/javascript">
	var courseid=<s:property value="courseid"/>;
	var cmissionid=<s:property value="cmissionid"/>;
	</script>