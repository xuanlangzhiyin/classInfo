
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.wuhei.cms.model.*"%>
<%@page import="com.opensymphony.xwork2.util.*"%>
<%@ include file="../header.jsp"%>
<%String year =CmsWebContext.getCurrentYear(); 
  String term=CmsWebContext.getCurrentTerm();
  List<String>years=CmsWebContext.getCurrentYearList();
  String searchYear=(String) valueStack.findValue("courseSearchMeta.year");
  String searchTerm=(String) valueStack.findValue("courseSearchMeta.term");
  Integer majorid = (Integer) valueStack.findValue("majorid");
    
%>  
<s:url id="listCourseUrl" action="listCourse" />
<s:url id="deleteCourseUrl" action="deleteCourse" />
<s:url id="viewCourseUrl" action="viewCourse" />
<s:url id="listUngroupedStudentsUrl" action="listUngroupedStudents" />
<s:url id="listCmissionUrl" action="listCmission" />
<s:url id="listCourseStudentUrl" action="listCourseStudent" />
<div id="container">
	<div class="white w1200 bc">
		<div class="w1060 bc">

			<div class="formTable-context">
				<div class="listTable w1000 bc cgray">
					<div id="" class="options bc h50 light-blue mt10 pt10">

						<s:form action="listCourse" method="POST">

							<div>

								<input type="submit" class="button_blue pl10 fr ml10 f14"
									value="搜索"> <select class="f14 pl10 w100 fr ml10"
									name="courseSearchMeta.term">

									<option value="2"
										<s:if test = "courseSearchMeta.term==\"2\""> selected="selceted"</s:if>>
										第二学期</option>

									<option value="1"
										<s:if test = "courseSearchMeta.term==\"1\""> selected="selceted"</s:if>>
										第一学期</option>
								</select> <select class="f14 pl10 w200 fr ml10"
									name="courseSearchMeta.year">


									<s:iterator value="years" var="tempYear">
										<option value="<s:property value="tempYear"/>"
											<s:if test = "courseSearchMeta.year==#tempYear"> selected="selceted"</s:if>>
											<s:property value="tempYear" />
											学年
										</option>
									</s:iterator>




								</select>
							</div>
						</s:form>
					</div>

					<form action="<s:property value="%{deleteCourseUrl}"/>"
						method="post">
						<table id="courseTable" class="bc f14" cellspacing="0"
							cellpadding="0" width="1000">
							<thead>

								<tr>
									<th width="1" class="filter-false remove sorter-false">&nbsp;</th>
									<th width="40">序号</th>
									<th width="180">课程名称</th>
									<th width="100">授课老师</th>
									<th width="80">班级</th>
									<th width="120">选课人数</th>
									<th width="120" class="filter-false remove sorter-false">操作</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="courses" id="coursetable" status="sts">

									<tr id="tr-<s:property value="id"/>">
										<td><input type="checkbox" name="coursesId"
											value="<s:property value="id"/>"></td>
										<td class="tc" name="courseIndex"><s:property
												value="#sts.count" /></td>

										<td title="<s:property value="code" />"><s:property
												value="name" /></td>
										<td><s:property value="allTeacherName" /></td>
										<td><s:property value="cclass" /></td>
										<td><s:property value="snum" /></td>

										<!-- 教师的listcourse页面的操作 -->
										<%
											if (roleCode.equals("ROLE_TCH")) {
										%>

										<td class="tc"><a
											href="<s:property value="%{viewCourseUrl}"/>?courseid=<s:property value="id"/>&csettingid=<s:property value="csettingid"/>">管理</a>
											<span class="m5">|</span> <a
											href="<s:property value="%{listCmissionUrl}"/>?courseid=<s:property value="id"/>">任务</a><span
											class="m5">|</span> <a
											href="<s:property value="%{listCourseStudentUrl}"/>?courseid=<s:property value="id"/>">总评</a>
										</td>

										<!--教务员的listcourse页面的操作  -->
										<%
											}

												if (roleCode.equals("ROLE_ACAMGR")) {
										%>
										<td class="tc"><a
											href="<s:property value="%{viewCourseUrl}"/>?courseid=<s:property value="id"/>">编辑</a>
											<span class="m5">|</span> <a
											href=" <s:property value="%{deleteCourseUrl}"/>?coursesId=<s:property value="id"/>">
												删除</a>
										</td>
										<!-- 学生的listcourse页面的操作 -->
										<%
											}
												if (roleCode.equals("ROLE_STD")) {
										%>

										<td class="filter-false remove sorter-false"><a
											href="<s:property value="%{listCmissionUrl}"/>?courseid=<s:property value="id"/>">查看</a>

										</td>
										<%
											}
										%>
									</tr>

								</s:iterator>
							</tbody>


						</table>
						<br>
						<%
						     if (roleCode.equalsIgnoreCase("ROLE_ACAMGR")){
						 %>
						<p class="tc f14 fl pt10">
							<input type="button" class="button_green w50 fl  " id="chkAll"
								value="全选" onclick="checkAll();"> <input type="button"
								class="button_green w50 fl " id="delCheckAll" value="取消"
								onclick="delCheck();"> <input type="submit"
								class="button_blue w60  fl" value="批量删除">
						</p>
						<%
						      }
						 %>
						<div class="pager courseTablePager fr">
							<span class="fl">每页显示数量：</span> <select class="pagesize  w50 fl">
								<option selected="selected" value="10">10</option>
								<option value="20">20</option>
								<option value="30">30</option>
								<option value="40">40</option>
							</select> <span><img src="images/first.png"
								class="pageButton first fl" alt="First" title="First page" /> </span>
							<span><img src="images/prev.png"
								class="pageButton prev fl" alt="Prev" title="Previous page" />
							</span> <span class="pagedisplay f16 cGray fl"></span>
							<!-- this can be any element, including an input -->
							<span><img src="images/next.png"
								class="pageButton next fl" alt="Next" title="Next page" /> </span> <span><img
								src="images/last.png" class="pageButton last fl" alt="Last"
								title="Last page" /> </span> <span class="fl">跳转: </span><select
								class="gotoPage w50 fl"></select>
						</div>
						<br>
						<div class="clear mb20"></div>
						<%
							if (roleCode.equalsIgnoreCase("ROLE_ACAMGR")) {
						%>

					</form>

				</div>
				<s:if test="courseImportResult==null"></s:if>
				<s:elseif
					test="courseImportResult.errCourseDetailViews.isEmpty()==false">
					<div class="errorlistTable w1000 bc cgray">
						<table id="errListCourse" class="bc f14 tl w900" cellspacing="0"
							cellpadding="0" width="1000">
							<thead>
								<tr>
									<th class="tc" width="60">序号</th>
									<th width="250">课程名称</th>
									<th width="80">课程性质</th>
									<th width="120">课程类别</th>
									<th width="300">选课课号</th>
									<th width="180">开课学院</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="courseImportResult.errCourseDetailViews"
									id="errCourseTable" status="sts">
									<tr id="tr-<s:property value="%{#sts.count}"/>">
										<td class="tc" name="studentIndex"><s:property
												value="%{#sts.count}" /></td>
										<td><s:property value="name" /></td>
										<td><s:property value="type" /></td>
										<td><s:property value="ccategoryname" /></td>
										<td><s:property value="code" /></td>
										<td><s:property value="courseDepartmentname" />
									</tr>

								</s:iterator>
							</tbody>
						</table>
					</div>
				</s:elseif>

				<s:if test="courseDeleteResult==null"></s:if>
				<s:elseif test="courseDeleteResult.errorCourses.isEmpty()==false">
					<div class="errorlistTable listTable  w1000 bc cgray">
						<table id="errListCourse" class="bc f14 tl w900" cellspacing="0"
							cellpadding="0" width="1000">
							<thead>
								<tr>
									<th class="tc" width="60">序号</th>
									<th width="400">课程编号</th>
									<th width="300">课程名称</th>
									<th width="120">授课老师</th>
									<th width="300">选课人数</th>

								</tr>
							</thead>
							<tbody>
								<s:iterator value="courseDeleteResult.errorCourses"
									id="errCourseTable" status="sts">
									<tr id="tr-<s:property value="%{#sts.count}"/>">
										<td class="tc" name="studentIndex"><s:property
												value="%{#sts.count}" /></td>
										<td><s:property value="code" /></td>
										<td><s:property value="name" /></td>
										<td><s:property value="teachername" /></td>
										<td><s:property value="snum" /></td>
									</tr>

								</s:iterator>
							</tbody>
						</table>
					</div>
				</s:elseif>
				<%
					}
				%>

			</div>


		</div>
		<div class="clear mb50"></div>
		<br>
	</div>
</div>

<%@ include file="../footer.jsp"%>

<script src="js/jquery-1.8.3.min.js"></script>
<script src="js/common.js"></script>
<script type="text/javascript" charset="utf-8"
	src="<s:url value="js/tablePlugin/jquery.tablesorter.js"/>"></script>
<script type="text/javascript" charset="utf-8"
	src="<s:url value="js/tablePlugin/jquery.tablesorter.pager.js"/>"></script>
<script type="text/javascript" charset="utf-8"
	src="<s:url value="js/tablePlugin/jquery.tablesorter.widgets.js"/>"></script>
<%
	if (roleCode.equalsIgnoreCase("ROLE_ACAMGR")) {
%>
<script type="text/javascript" charset="utf-8"
	src="<s:url value="js/acamgr/listCourse.js"/>"></script>
<%
	}
%>
<%
	if (roleCode.equalsIgnoreCase("ROLE_TCH")) {
%>
<script type="text/javascript" charset="utf-8"
	src="<s:url value="js/teacher/listCourse.js"/>"></script>
<%
	}
%>

