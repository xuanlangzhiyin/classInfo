<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<s:url id="listUngroupedCmstudentsUrl" action="listUngroupedCmstudents" />
<s:url id="downloadAttachUrl" action="downloadFile" />
<s:url id="editGroupMissionUrl" action="editGroupMission" />
<!-- author: CHEN ZHUO 140703 -->
<div id="container" >
			<div class="white w1200 bc">
				<div class="w1060 bc">   
					<div class="main-boxes">
						<div class="main-box">
							<h2 id="missionDetail" class="boxTitle active">任务简介</h2>
								<div id="viewBox4Mission" class="boxContext ">
								<div class="m30 boxForm f16"><!--CHEN ZHUO: 为保持统一命名风格，此处id命名采用简化格式，id的详细意义请见后台model：com.wuhei.cms.model/Cmission.java -->
									<p><span class="mr20 w80">任务类型：</span><span ><s:property value="cmission.mtype"/></span></p>
									<p><span class="mr20 w80" >提交方式：</span><span ><s:property value="cmission.stype"/></span></p>
									<p><span class="mr20 w80">任务名称：</span><span ><s:property value="cmission.name"/></span></p>
									<p><span class="mr20 w80">截止时间：</span><span ><s:property value="cmission.deadline"/></span></p>
									<p><span class="mr20 w80">任务要求：</span></p> <div class="formText descriptionBox f16 t2 ">  ${cmission.requirement}  </div>
									<p><span class="mr20 w80">附件：</span>
									
									<a href="<s:property value="%{downloadAttachUrl}"/>?cmissionid=<s:property 
										value="cmissionid"/>"><span><s:property
												value="cmission.oldname" /></span>
												</a>
												
												
								</div>
								<div class="tc h50 f16 buttonArea"><!--CHEN ZHUO: 添加了buttunArea -->
								<a href="<s:property value="%{editGroupMissionUrl}"/>?courseid=<s:property value="courseid"/>&cmissionid=<s:property value="cmission.id"/>"> 
									<input type="button" class="btn green browseView btnRevise" value="修&nbsp改"></a>
								</div>
							</div>		
						</div>
						<div class="main-box">
							<h2 id="involvedGroups" class="boxTitle" onclick="slideBox(this);">参与小组</h2>
							<div class="boxContext none">
							
							<div id="editBox4Groups">
								<div class="m30 formTableBox f16">
									<div class="formTable-meta">
										<a href="<s:property value="%{listUngroupedCmstudentsUrl}"/>?courseid=<s:property value="courseid"/>&cmissionid=<s:property 
										value="cmissionid"/>"><input class="button_green m10 f14 fr vm" value="修改分组情况" type="button" ></a>
										
									</div>
									<div class="formTable-context white">
										<div class="listTable w1000 bc cGrey">
											<br>
											<h3 class="f20 ml20 mr20"> <strong>已参与小组列表</strong></h3>
											<div class="uline ml20 mr20"></div>
											<div class="groupTable m20 bc">
												<h4 class="groupMeta">
													<span class="w50">&nbsp;</span>
													<span class="w300">小组名</span>
													<span class="w200">队长名</span>
													<span class="w100">小组人数</span>
													<span class="w100">参与状态</span>
												</h4>
												<div class="cb"></div>
												<div id="checkedGroups" class="groupBoxes">
													<div id="group-1" title="1" class="groupBox">
														<div class="groupTitle active">
															<span class="w50"><input name="option" type="checkbox"></span>
															<span class="w300">即时通讯组</span>
															<span class="w200">廖添丁</span>
															<span class="w100">6</span>
															<span class="w100 checked">参加评分</span>
															<div class="fr mr10 show" onclick="slideTable(this)">╋</div>
														</div>
														<div class="groupContext">
															<table  class="bc f14" cellspacing="0" cellpadding="0" width="900">
																<tbody>
																	<tr>
																		<th width="50">&nbsp</th>
																		<th width="200">学号</th>
																		<th width="130">姓名</th>
																		<th width="100">性别</th>
																		<th width="120">年级</th>
																		<th width="150">班级</th>
																	</tr>
																	<tr >
																		<td>&nbsp</td>
																		<td>201230675086</td>
																		<td>朱承荣</td>
																		<td>男</td>
																		<td>2012</td>
																		<td>4</td>
																	</tr>
																</tbody>
															</table>
														</div>
													</div>
													<br>
													</div>
											</div><!-- /div groupTable -->
										</div><!-- /div listTable -->
					
									</div><!-- /div formTable-context -->
								</div>
								
							</div><!--div editBox4Groups  -->
							

										
					    <!-- errorGroups -->
						<div id=errorGroups class="m30 w1000 bc none f14  ">
						<div class="message">
						<div class="clear"></div>
						<!-- error -->
						<div class="messageBox warn">
							<span class="fl messageLogo"></span> <span class="close fr"
								onclick="closeCeindexMessage(this)"></span>
							<h4>该课程暂无课程分组，请先编辑课程分组</h4>
						<!-- <p class="t5">
							</p> -->
							
						</div>
                        
                        </div>
                        </div><!-- /errorGroups -->
							
							
							<div id="viewBox4Groups" class="boxContext hide">
								<div class="m30 formTableBox f16">
									<div class="formTable-context white">
										<div class="listTable w1000 bc cGrey">
											<br>
											<h3 class="f20 ml20 mr20"> <strong>已参与小组列表</strong></h3>
											<div class="uline ml20 mr20"></div>
											<div class="groupTable m20 bc">
												<h4 class="groupMeta">
													<!--  <span class="w50">&nbsp</span>-->
													<span class="w50">&nbsp</span>
													<span class="w300">小组名</span>
													<span class="w200">队长名</span>
													<span class="w100">小组人数</span>
													<span class="w100">参与状态</span>
												</h4>
												<div class="cb"></div>
												<div id="checkedGroups" class="groupBoxes">
													<div id="group-1" class="groupBox">
														<div class="groupTitle active">
															<span class="w50">&nbsp</span><!-- replace the checkbox with &nbsp -->
															<span class="w300">即时通讯组</span>
															<span class="w200">廖添丁</span>
															<span class="w100">6</span>
															<span class="w100 checked">参加评分</span>
															<span ></span><!-- replace the a with space -->
															<div class="fr mr10 show" onclick="slideTable(this)">╋</div>
														</div>
														<div class="groupContext">
															<table  class="bc f14" cellspacing="0" cellpadding="0" width="900">
																<tbody>
																	<tr>
																		<th width="50">&nbsp</th>
																		<th width="200">学号</th>
																		<th width="130">姓名</th>
																		<th width="100">性别</th>
																		<th width="120">年级</th>
																		<th width="150">班级</th>
																	</tr>
																	<tr >
																		<td>&nbsp</td>
																		<td>201230675086</td>
																		<td>朱承荣</td>
																		<td>男</td>
																		<td>2012</td>
																		<td>4</td>
																	</tr>
																</tbody>
															</table>
														</div>
													</div>
													<br>
													</div><!-- /div checkedGroups -->
											</div><!-- /div groupTable -->
										</div><!-- /div listTable -->
									</div><!-- /div formTable-context -->
								</div><!-- /div formTableBox -->
								<div class="tc h50 f16">
									<input type="button" class="btn blue" value="修&nbsp改" onclick="editView4Groups()">
									<!-- <input type="button" class="btn green" value="确&nbsp认"> -->
								</div>
							</div><!-- /div viewBox4Groups -->
							<div id="onload" class="m30 w1000 bc tc mt100 mb100 hide"><img src="<s:url value="images/loading.gif"/>"></div>
							</div>
						</div><!-- /div mainBox -->
					</div><!-- /div mainBoxes -->
				</div>
				<div class="clear mb50"></div>
				<br>
			</div>
		</div>
<%@ include file="../footer.jsp"%>

<script type="text/javascript" charset="utf-8" src="<s:url value="js/teacher/viewGroupMission.js"/>"></script>
<script type="text/javascript" charset="utf-8" src="<s:url value="js/teacher/addGroupMissionReviseGroups.js"/>"></script>
<script type="text/javascript">
var courseid=<s:property value="courseid"/>;
var cmissionid=<s:property value="cmissionid"/>;
	 </script>

