<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<s:url id="listCmissionUrl" action="listCmission" />
<div id="container">
	<div class="white w1200 bc">
		<div class="w1060 bc">
			<div class="clear"></div>  
			<div class="context">
					
     
				<div class="m30 formTableBox f16">
					
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
									<span class="w150">队长名</span>
									<span class="w100">小组人数</span>
									<span class="w100">小组评分</span>
								
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
												<span name="groupLeaderName" class="w150">
														<s:property value="leadername" /> 
												</span>
												<span name="groupMembers" class="w100">
													<s:property value="count" /> 
												</span> 
												<span name="ismarked" class="w100">
													<%
														int temp = request.getAttribute("ismarked").hashCode();  
														if(temp==1)
														out.println("已评分");
														else
														out.println("未评分");
														 %>
												</span>
												
												<div class="fr mr10 show"
													onclick="getGroupDetail(<s:property value="id"/>,<s:property value="courseid"/>,<s:property value="cmissionid"/>,this)">╋</div>
											</div>
											<!-- tr学生id，g开头的为groupid，js函数传参，第一个为学生id，第二个为组id -->
											<div class="groupContext">
												<table class="" id="groupTable-<s:property value="id"/>"
													class="bc f14" cellspacing="0" cellpadding="0" width="900">
													<thead>
														<tr name="tableTitle">
															<th width="50">&nbsp</th>
															<th width="200">学号</th>
															<th width="130">姓名</th>
															<th width="80">年级</th>
															<th width="100">班级</th>
															<th width="100">组长标识</th>
															<th width="150">成绩</th>
															<th width="100">报告</th>
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
							</div>
						</div>
					</div>
				</div>
				<div class="cb"></div>
				
				<div id="" class="tc h50 f16">
					<a
						href="<s:property value="%{listCmissionUrl}"/>?courseid=<s:property value="courseid"/>">
						<input type="button" class="btn green" value="返&nbsp回"> </a>
				</div>

			</div>
			<div class="clear mb50"></div>
			<br>
		</div>
	</div>
</div>
	<%@ include file="../footer.jsp"%>
	<script type="text/javascript" charset="utf-8"
		src="<s:url value="js/teacher/listGroupMission4personalScore.js"/>"></script>
	<script type="text/javascript">
	var courseid=<s:property value="courseid"/>;
	var cmissionid=<s:property value="cmissionid"/>;
	</script>