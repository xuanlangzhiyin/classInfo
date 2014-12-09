<%@page import="com.wuhei.cms.model.Cmission"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<s:url id="editCourseUrl" action="editCourse" />
<s:url id="importCstudentUrl" action="importCstudent" />
<s:url id="deleteCstudentUrl" action="deleteCstudent" />
<div id="container">  
	<div class="w1200 white bc">
		<div class="w1060 bc">
   
			<div class="main-boxes">
				<div class="main-box">
					<h2 class="boxTitle active" onclick="slideBox(this);">基本信息</h2>
					<div class="boxContext">
						<div class="m30 boxForm f16 " id="viewBox4intro">
							<p>
								<span class="mr20 w80 tr">专业课程：</span><span><s:property
										value="courseDetailView.name" /> </span>
							</p>
							<p>
								<span class="mr20 w80 tr">学年：</span><span><s:property
										value="courseDetailView.year" />学年 </span>
							</p>
							<p>
								<span class="mr20 w80 tr">学期：</span><span><s:if
										test="1==courseDetailView.term">第一学期</s:if> <s:if
										test="2==courseDetailView.term">第二学期</s:if> </span>

							</p>
							<p>
								<span class="mr20 w80 tr">开课老师：</span><span> <s:property
										value="mainCteacher.code" />-<s:property
										value="mainCteacher.name" /> </span>
							</p>
							<span><s:iterator value="cteachers" status="sts">
									<p>
										<span class="mr20 w80 tr"></span><span> <s:property
												value="code" />-<s:property value="name" /> </span>
									</p>
								</s:iterator> </span>


							<div class="tc h50">


								<a
									href="<s:property value="%{editCourseUrl}"/>?courseid=<s:property value="courseDetailView.id"/>"><input
									type="button" value="编辑课程信息" class="btn green"
									onclick="showEditBox4Intro();"> </a>

							</div>
						</div>
					</div>
				</div>
				<div class="main-box">
					<h2 class="boxTitle active" onclick="slideBox(this);">选课学生</h2>
					<div class="boxContext">
						<div class="m30 f16">
							<div class="formTable-context white">
								<div class="listTable w1000 bc cgra">






									<s:if test="cstudentDetailViewList.isEmpty()==false">
										<br>
										<form
											action="<s:property value="%{deleteCstudentUrl}"/>?courseid=<s:property value="courseid"/>"
											method="post">
											<table id="testtable" class="bc f16" cellspacing="0"
												cellpadding="0" width="960">
												<thead>
													<tr>
														<th width="50">&nbsp;</th>
														<th width="40">序号</th>
														<th width="180">学号</th>
														<th width="250">姓名</th>
														<th width="80">性别</th>
														<th width="120">年级</th>
														<th width="150">班级</th>
														<th width="80">总评</th>
														<th width="80">操作</th>
													</tr>
												</thead>
												<tfoot>
													<tr>
														<th width="50">&nbsp;</th>
														<th width="40">序号</th>
														<th width="180">学号</th>
														<th width="250">姓名</th>
														<th width="80">性别</th>
														<th width="120">年级</th>
														<th width="150">班级</th>
														<th width="80">总评</th>
														<th width="80">操作</th>
													</tr>
												</tfoot>
												<tbody>
													<s:iterator value="cstudentDetailViewList" id="coursetable"
														status="sts">

														<tr id="tr-<s:property value="id"/>">

															<td><input type="checkbox" name="cstudentId"
																value="<s:property value="id"/>">
															</td>
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
															<td><a
																href=" <s:property value="%{deleteCstudentUrl}"/>?courseid=<s:property value="courseid"/>&cstudentId=<s:property value="id"/>">
																	删除</a></td>
															</td>
													</s:iterator>


												</tbody>
											</table>

                     <p class="tc f14 fl pt10">
						<input type="button" class="button_green w50 fl  " id="chkAll" value="全选" onclick="checkAll();" >
						<input type="button" class="button_green w50 fl " id="delCheckAll" value="取消" onclick="delCheck();">
						<input type="submit" class="button_blue w60  fl"  value="批量删除">
					</p>
											<BR>
											<div class="pager testtablePager fr ">
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

								</s:if>



									<!-- 结束form -->
									</form>
									
								<br>

								<s:if test="cstudentImportResult==null"></s:if>
								<s:elseif
									test="cstudentImportResult.errorStudents.isEmpty()==false">
									<div class="listTable errorlistTable w1000 bc cgray">
										<table id="errListStudent" class="bc f14 tl w900"
											cellspacing="0" cellpadding="0" width="1000">
											<thead>
												<tr>
													<th class="tc" width="50">序号</th>
													<th width="100">学号</th>
													<th width="100">姓名</th>



												</tr>
											</thead>
											<tbody>
												<s:iterator value="cstudentImportResult.errorStudents"
													id="errStudentTable" status="sts">
													<tr id="tr-<s:property value="%{#sts.count}"/>">
														<td class="tc" name="studentIndex"><s:property
																value="%{#sts.count}" /></td>
														<td><s:property value="code" /></td>
														<td><s:property value="name" /></td>

													</tr>

												</s:iterator>
											</tbody>
										</table>
									</div>
								</s:elseif>

								<s:if test="cstudentDeleteResult==null"></s:if>
								<s:elseif
									test="cstudentDeleteResult.errorCstudentDetailViews.isEmpty()==false">
									<div class="listTable errorlistTable w1000 bc cgray">
										<table id="errListStudent" class="bc f14 tl w900"
											cellspacing="0" cellpadding="0" width="1000">
											<thead>
												<tr>
													<th class="tc" width="50">序号</th>
													<th width="100">学号</th>
													<th width="100">姓名</th>



												</tr>
											</thead>
											<tbody>
												<s:iterator value="cstudentDeleteResult.errorCstudentDetailViews"
													id="errStudentTable" status="sts">
													<tr id="tr-<s:property value="%{#sts.count}"/>">
														<td class="tc" name="studentIndex"><s:property
																value="%{#sts.count}" /></td>
														<td><s:property value="code" /></td>
														<td><s:property value="name" /></td>

													</tr>

												</s:iterator>
											</tbody>
										</table>
									</div>
								</s:elseif>
								
								<br>

							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="clear pb50"></div>
		</div>
	</div>
</div>


<%@ include file="../footer.jsp"%>
<script type="text/javascript" charset="utf-8">
	tablePager("#testtable", '.testtablePager');
</script>
<script src="js/jquery-1.8.3.min.js"></script>
<script src="js/common.js"></script>
