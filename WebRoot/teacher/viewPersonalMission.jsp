<%@page import="com.wuhei.cms.model.Cmission"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ include file="../header.jsp"%>

<s:url id="listCmissionUrl" action="listCmission"/>
<s:url id="downloadAttachUrl" action="downloadFile"/>
<s:url id="editPersonalMissionUrl" action="editPersonalMission"/>

<div id="container">
	<div class="white w1200 bc">
		<div class="w1060 bc">

			   
			<div class="main-boxes">
				<div class="main-box">
					<h2 class="boxTitle active" onclick="slideBox(this);">任务简介</h2>
					<div class="boxContext">
						
						<div id="viewBox4info">
							<div class="m30 boxForm f16">
								<p>
									<span class="mr20 w80">任务类型：</span><span id="cmissionmMtypeView"><s:property
											value="cmission.mtype" />
								</p>
								<p>
									<span class="mr20 w80">任务名称：</span><span id="cmissionNameView"><s:property
											value="cmission.name" /> </span>
								</p>
								<p>
									<span class="mr20 w80">截止时间：</span><span
										id="cmissionDeadlineView"><s:property
											value="cmission.deadline" /> </span>
								</p>
								<p>
									<span class="mr20 w80">任务简介：</span>
								</p>
								    <div class="formText descriptionBox f16 t2 ">  
								         ${cmission.requirement}  
								    </div>
								
								<p><span class="mr20 w80">附件：</span>
									
									<a href="<s:property value="%{downloadAttachUrl}"/>?cmissionid=<s:property 
										value="cmissionid"/>"><span><s:property
												value="cmission.oldname" /></span>
												</a>
							</div>
							<div class="tc h50 f16">
								<a href="<s:property value="%{editPersonalMissionUrl}"/>?cmissionid=<s:property value="cmission.id"/>">
								<input type="button" class="btn green" value="修&nbsp改">
								</a>
							</div>
						</div>
					</div>
				</div>
				<div class="main-box">
					<h2 id="involvedStudent" class="boxTitle active"
						onclick="slideBox(this);">参与学生</h2>

					<div id="listBox" class="boxContext cgray">
						<div id="editBox4Studentlist">
							<div class="m30 formTableBox f16">
								<div class="formTable-context white">
									<div class="listTable w1000 bc cGrey">
										<br>
										<h3 class="f20 ml20 mr20">
											<strong>已参与学生列表</strong>
										</h3>
										<div class="uline ml20 mr20"></div>
										<table id="studentList" class="bc f14 cgray" cellspacing="0"
											cellpadding="0" width="960">
											<thead>
												<tr>
													<th width="45" class="filter-false remove sorter-false">&nbsp;</th>
													<th width="80">序号</th>
													<th width="130">学号</th>
													<th width="110">姓名</th>
													<th width="80">性别</th>
													<th width="100">年级</th>
													<th width="150">班级</th>
													<th width="80">总评</th>
													<th width="140" class="filter-false remove sorter-false">参与状态</th>
													<th width="140" class="filter-false remove sorter-false">操作</th>
												</tr>
											</thead>
											<tfoot>
												<tr>
													<th>&nbsp;</th>
													<th>序号</th>
													<th>学号</th>
													<th>姓名</th>
													<th>性别</th>
													<th>年级</th>
													<th>班级</th>
													<th>总评</th>
													<th>参与状态</th>
													<th>操作</th>
												</tr>
											</tfoot>
											<tbody>
												<s:iterator value="involvedCmstudentDetailViewList"
													status="sts">
													<tr id="tr-<s:property value="id"/>">
														<td><input type="checkbox"
															value="<s:property value="id"/>"></td>
														<td class="imStudentIndex"><s:property
																value="#sts.count" /></td>
														<td><s:property value="studentCode" /></td>
														<td id="name"><s:property value="name" /></td>
														<td><s:property value="sex" /></td>
														<td><s:property value="studentGrade" /></td>
														<td><s:property value="stuClass" /></td>
														<td>
															<!-- credit为空，打印“未评分” --> <s:if test="credit==null">
																未评分
															  </s:if> <s:property value="credit" />
														</td>
														<!-- <td><s:property value="credit"/></td> -->
														<td class="removable"><s:property
																value="isinvolvedChinese" /></td>
														<td class="removable"><a href="javascript:void(0);"
															onclick="deleteOneStudent(<s:property value="id"/>,<s:property value="cmissionid"/>,false)">删除参与</a>
														</td>
													</tr>
												</s:iterator>
											</tbody>
										</table>
										
										
										
										<div class="pager  fr pager4studentList">
											<span class="fl">每页显示数量：</span> <select
												class="pagesize  w50 fl">
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
												class="pageButton next fl" alt="Next" title="Next page" />
											</span> <span><img src="<s:url value="images/last.png"/>"
												class="pageButton last fl" alt="Last" title="Last page" />
											</span> <span class="fl">转至: </span><select class="gotoPage w50 fl"></select>
										</div>
										<br> <br>
									</div>
								</div>
								
								<div class="formTable-meta">
									<div id="checkAll" class="btnBS f16 fl"
										onclick="checkAll('#studentList',this)">全选</div>
									<div id="delAll" class="button_blue m10 f14 fl"
										onclick="deleteStudents()">批量删除</div>

								</div>
							</div>


							<div class="m30 formTableBox f16">

								<div class="formTable-context white">
									<div class="listTable w1000 bc cGrey">
										<br>
										<h3 class="f20 ml20 mr20">
											<strong>未参与学生列表</strong>
										</h3>
										<div class="uline ml20 mr20"></div>

										<table id="unSelected"
											class="bc f14 cgray tablesorter-default" cellspacing="0"
											cellpadding="0" width="960">
											<thead>
												<tr>
													<th width="45" class="filter-false remove sorter-false">&nbsp;</th>
													<th width="80">序号</th>
													<th width="130">学号</th>
													<th width="110">姓名</th>
													<th width="80">性别</th>
													<th width="100">年级</th>
													<th width="150">班级</th>
													<th width="80">总评</th>
													<th width="140" class="filter-false remove sorter-false">参与状态</th>
													<th width="140" class="filter-false remove sorter-false">操作</th>
												</tr>
											</thead>
											<tfoot>
												<tr>
													<th>&nbsp;</th>
													<th>序号</th>
													<th>学号</th>
													<th>姓名</th>
													<th>性别</th>
													<th>年级</th>
													<th>班级</th>
													<th>总评</th>
													<th>参与状态</th>
													<th>操作</th>
												</tr>
											</tfoot>
											<tbody>
												<s:iterator value="uninvolvedCmstudentDetailViewList"
													status="sts">
													<tr id="tr-<s:property value="id"/>">
														<td><input type="checkbox"
															value="<s:property value="id"/>"></td>
														<td class="idleStudentIndex"><s:property
																value="#sts.count" /></td>
														<td><s:property value="studentCode" /></td>
														<td id="name"><s:property value="name" /></td>
														<td><s:property value="sex" /></td>
														<td><s:property value="studentGrade" /></td>
														<td><s:property value="stuClass" /></td>
														<td><s:if test="credit==null">
																	未评分
																  </s:if> <s:property value="credit" />
														</td>
														<td><s:property value="isinvolvedChinese" /></td>
														<td><a href="javascript:void(0);"
															onclick="addOneStudentToMission(<s:property value="id"/>,<s:property value="cmissionid"/>,true)">添加到任务</a>
														</td>
													</tr>
												</s:iterator>
											</tbody>
										</table>
										<div class="pager  fr pager4unSelected">
											<span class="fl">每页显示数量：</span> <select
												class="pagesize  w50 fl">
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
												class="pageButton next fl" alt="Next" title="Next page" />
											</span> <span><img src="<s:url value="images/last.png"/>"
												class="pageButton last fl" alt="Last" title="Last page" />
											</span> <span class="fl">转至: </span><select class="gotoPage w50 fl"></select>
										</div>
									</div>
								</div>
								<br> <br>
								
								<div class="formTable-meta light-gray">
									<div id="checkAll" class="btnBS f16 fl"
										onclick="checkAll('#unSelected',this)">全选</div>
									<div id="addAll" class="button_blue m10 f14 fl"
										onclick="addStudents()">批量添加</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="clear mb50"></div>
		<br>
	</div>
</div>
<%@ include file="../footer.jsp"%>

<script type="text/javascript" charset="utf-8"
	src="<s:url value="js/teacher/viewPersonalMission.js"/>"></script>
<script type="text/javascript" charset="utf-8">
	var cmissionID=<s:property value="cmissionid"/>;
	var courseid=<s:property value="courseid"/>;
</script>