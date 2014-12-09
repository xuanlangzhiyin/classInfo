<%@page import="com.wuhei.cms.model.Student"%>
<%@page import="com.opensymphony.xwork2.util.*"%>
<%@page import="com.wuhei.cms.model.joint.StudentDetailView"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
    
<s:url id="listUserUrl" action="listUser" />
<div id="container">
	<div class="white w1200 bc">
		<div class="w1060 bc">
			
			<div class="formTable-context">
				<!-- Form表单提交,提交所选年级(stdgrade),所选班级(stdclass),关键字(keyword),通过post交给后台对象studentSearchMeta 海志-->
				<form action="<s:url value="acamgr/listUser.action"/>" method="post">
					<div class="listTable w1000 bc cgray">
						<div id="" class="options bc h50 light-blue mb20">
							<div class="clear"></div>
							<p class="tc pt10 fr">

								<!-- 班级选择stdgrade(1-6,卓越版,当stdclass为空时默认为所有班级) 海志-->
								<select class="f14 pl10 w100 fl ml10 mr10"
									name="userSearchMeta.rolecode">


									<option value="ROLE_TCH"
										<s:if test = "'userSearchMeta.rolecode==ROLE_TCH'"> selected="selceted"</s:if>>
										教师</option>


									<option value="ROLE_STD"
										<s:if test = "userSearchMeta.rolecode=='ROLE_STD'"> selected="selceted"</s:if>>
										学生</option>

								</select>


								<!-- 输入关键字keyword,搜索后页面静态刷新,input框保留返回之前输入的查询关键字 海志-->
								<span class=""> <input
									class="textInputBox fl w200 m0 h30"
									name="userSearchMeta.keyword" placeholder="请输入关键字"
									value="<s:property value="userSearchMeta.keyword"/>" /> <input
									class="button_blue f14 w100 fl ml10" type="submit" value="搜索" />
								</span>




							</p>
						</div>
						<div></div>


						<%
							String sroleCode = (String) valueStack
									.findValue("userSearchMeta.rolecode");

							if (StringUtils.isEmpty(sroleCode) || sroleCode.equals("ROLE_TCH")) {
						%>
						<table id="" class="bc f14 tl" cellspacing="0" cellpadding="0"
							width="1000">
							<thead>

								<tr>
									<th width="40" class="filter-false remove sorter-false">&nbsp</th>
									<th width="40">序号</th>
									<th width="120">教工号</th>
									<th width="200">姓名</th>
									<th width="80">性别</th>
									<th width="360">邮箱</th>
									<th width="150" class="filter-false remove sorter-false">操作</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="userPageResult.teachers" id="teacherTable"
									status="sts">
									<tr id="tr-<s:property value="id"/>">
										<td><input type="checkbox"
											value="<s:property value="id"/>"></td>
										<!-- sts为struts命名对象，保存在ActionContext中，故需用#访问 -->
										<td name="acamgrTeacherIndex"><s:property
												value="%{#sts.count+(userPageResult.currentPage-1)*(@com.wuhei.cms.search.result.PageResult@getPageCount())}" />
										</td>
										<td><s:property value="username" /></td>
										<td><s:property value="showname" /></td>
										<td><s:property value="sex" /></td>
										<td><s:property value="email" /></td>
										<s:url id="resetPasswordUrl" action="resetPassword">
											<s:param name="userid" value="id"></s:param>
											<s:param name="userSearchMeta.keyword"
												value="userSearchMeta.keyword"></s:param>
											<s:param name="userSearchMeta.rolecode"
												value="userSearchMeta.rolecode"></s:param>
										</s:url>
										<td>暂未实现</td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
						<%
							} else {
						%>

						<table id="listStudent" class="bc f14 tl" cellspacing="0"
							cellpadding="0" width="1000">
							<thead>
								<tr>
									<th width="40">&nbsp</th>
									<th class="tc" width="60">序号</th>
									<th width="200">学号</th>
									<th width="180">姓名</th>
									<th width="80">性别</th>
									<th width="150">学位</th>
									<th width="150">年级</th>
									<th width="150">班级</th>
									<th width="100">操作</th>
								</tr>
							</thead>

							<tbody>
								<!-- 利用struts迭代器迭代输出studentPageResult.students列表的属性,status属性命名为sts.   海志-->
								<s:iterator value="userPageResult.students" id="studenttable"
									status="sts">
									<tr id="tr-<s:property value="id"/>">
										<!-- 为每一行的tr标签赋id值(tr-id) -->
										<td><input type="checkbox"></td>
										<!-- 输出索引数值,为  sts数值+(当前页面-1)*后台分页的每页数量 -->
										<td class="tc" name="studentIndex"><s:property
												value="%{#sts.count+(userPageResult.currentPage-1)*(@com.wuhei.cms.search.result.PageResult@getPageCount())}" />
										</td>
										<td><s:property value="username" /></td>
										<td><s:property value="showname" /></td>
										<td><s:property value="sex" /></td>
										<td><s:property value="level" /></td>
										<td><s:property value="grade" /></td>
										<td><s:property value="stuclass" /></td>
										<!--  <td class="tc"><s:a href="%{viewDetail}">查看详细信息</s:a></td>
													等学生详细信息页面做好就使用这句跳转-->
										<s:url id="resetPasswordUrl" action="resetPassword">
											<s:param name="userid" value="id"></s:param>
											<s:param name="userSearchMeta.keyword"
												value="userSearchMeta.keyword"></s:param>
											<s:param name="userSearchMeta.rolecode"
												value="userSearchMeta.rolecode"></s:param>
										</s:url>
										<td>暂未实现</td>
									</tr>

								</s:iterator>
							</tbody>



						</table>




						<%
							}
						%>


						<br>
					</div>
				</form>

				<br>







				<!-- 分页容器 使用分页控件kkpaper 详见https://github.com/pgkk/kkpager -->
				<div id="kkpager" class="pagerBox fr"></div>
				<div class="clear pb20"></div>
				<!-- 未成功插入的 -->


			</div>
		</div>
		<div class="clear mb50"></div>
		<br>
	</div>
</div>


<!-- footer部分 -->
<%@ include file="../footer.jsp"%>

<!-- 加入分页控件kkpager -->
<script type="text/javascript"
	src="<s:url value="/js/tablePlugin/kkpager.min.js"/>"></script>
<script type="text/javascript">
	//生成分页控件  
	kkpager
			.generPageHtml({
				pno : '<s:property value="userPageResult.currentPage"/>', //当前页
				mode : 'link', //可选，默认就是link
				//总页码  
				total : '<s:property value="userPageResult.totalPage"/>',
				//总数据条数  
				totalRecords : '<s:property value="userPageResult.totalCount"/>',
				//链接前部  
				// hrefFormer : '<s:url value="%{page_url}"/>',
				hrefFormer : '<s:property value="%{listUserUrl}"/>?userSearchMeta.keyword=<s:property value="userSearchMeta.keyword"/>&userSearchMeta.rolecode=<s:property value="userSearchMeta.rolecode"/>',
				//链接尾部  
				// hrefLatter : '${hrefLatter}',
				//链接算法
				getLink : function(n) {
					//这里是获取I页面的算法

					return this.hrefFormer + '&'
							+ "userSearchMeta.currentPage=" + n;
				}

			});
</script>
