<%@page import="com.wuhei.cms.model.Student"%>
<%@page import="com.opensymphony.xwork2.util.*"%>
<%@page import="com.wuhei.cms.model.joint.StudentDetailView"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>

<s:url id="listStudentUrl" action="listStudent" />
<s:url id="deleteStudentUrl" action="deleteStudent" />
<div id="container">  
	<div class="white w1200 bc">
		<div class="w1060 bc">
			<div class="formTable-context">
				<div class="listTable w1000 bc cgray">
					<!-- Form表单提交,提交所选年级(stdgrade),所选班级(stdclass),关键字(keyword),通过post交给后台对象studentSearchMeta 海志-->
					<s:form action="listStudent" method="post">
						<div id="" class="search bc h50 light-blue mb20 ">
							<p class="tc f14 fr  pt10">
								<select class="f14 pl10 w100 fl ml10"
									name="studentSearchMeta.majorid">
									<option value="" selected="selceted">所有专业</option>
									<s:iterator value="majors" status="sts">
										<option value="<s:property value="id"/>"
											<s:if test = "studentSearchMeta.majorid==id"> selected="selceted"</s:if>>
											<s:property value="name" />
										</option>
									</s:iterator>
								</select>
								<!-- 年级选择stdgrade(2010-2014,当stdgrade为空时默认为所有年级) 海志-->
								<select class="f14 pl10 w100 fl ml10"
									name="studentSearchMeta.stdgrade">
									<option value=""
										<s:if test = "studentSearchMeta.stdgrade==null"> selected="selceted"</s:if>>
										所有年级</option>
									<option value="2014"
										<s:if test = "studentSearchMeta.stdgrade==2014"> selected="selceted"</s:if>>
										2014级</option>
									<option value="2013"
										<s:if test = "studentSearchMeta.stdgrade==2013"> selected="selceted"</s:if>>
										2013级</option>
									<option value="2012"
										<s:if test = "studentSearchMeta.stdgrade==2012"> selected="selceted"</s:if>>
										2012级</option>
									<option value="2011"
										<s:if test = "studentSearchMeta.stdgrade==2011"> selected="selceted"</s:if>>
										2011级</option>
									<option value="2010"
										<s:if test = "studentSearchMeta.stdgrade==2010"> selected="selceted"</s:if>>
										2010级</option>
								</select>
								<!-- 班级选择stdgrade(1-6,卓越版,当stdclass为空时默认为所有班级) 海志-->
								<select class="f14 pl10 w100 fl ml10 mr10"
									name="studentSearchMeta.stdclass">
									<option value=""
										<s:if test = "studentSearchMeta.stdclass==null"> selected="selceted"</s:if>>
										所有班级</option>
									<option value="卓越班"
										<s:if test = "studentSearchMeta.stdclass=='卓越班' "> selected="selceted"</s:if>>
										卓越班</option>
									<option value="6"
										<s:if test = "studentSearchMeta.stdclass=='6' "> selected="selceted"</s:if>>
										6</option>
									<option value="5"
										<s:if test = "studentSearchMeta.stdclass=='5' "> selected="selceted"</s:if>>
										5</option>
									<option value="4"
										<s:if test = "studentSearchMeta.stdclass=='4' "> selected="selceted"</s:if>>
										4</option>
									<option value="3"
										<s:if test = "studentSearchMeta.stdclass=='3' "> selected="selceted"</s:if>>
										3</option>
									<option value="2"
										<s:if test = "studentSearchMeta.stdclass=='2' "> selected="selceted"</s:if>>
										2</option>
									<option value="1"
										<s:if test = "studentSearchMeta.stdclass=='1' "> selected="selceted"</s:if>>
										1</option>
								</select>
								<!-- 输入关键字keyword,搜索后页面静态刷新,input框保留返回之前输入的查询关键字 海志-->
								<span class="fr"> <input
									class="textInputBox fl w200 m0 h30"
									name="studentSearchMeta.keyword" placeholder="请输入关键字"
									value="<s:property value="studentSearchMeta.keyword"/>" /> <input
									class="button_blue f14 w100 fl ml10" type="submit" value="搜索" />
								</span>
								
							</p>
						</div>
					</s:form>

					<div class="clear"></div>


					<!-- 批量删除表单  author:GongXiang -->
					
						<!-- 无结果/数据样式 author：Long -->
					
					<form action="<s:property value="%{deleteStudentUrl}"/>">
						<table id="listStudent" class="bc f14 tl" cellspacing="0"
							cellpadding="0" width="1000">
							<thead>
								<tr>
									<th width="40">&nbsp;</th>
									<th class="tc" width="60">序号</th>
									<th width="200">学号</th>
									<th width="180">姓名</th>
									<th width="80">性别</th>
									<th width="150">学位</th>
									<th width="150">年级</th>
									<th width="150">班级</th>
									<%
						     if (roleCode.equalsIgnoreCase("ROLE_ACAMGR")){
						 %>
									<th width="100">操作</th>
									<%} %>
								</tr>
							</thead>
							<s:if test="studentPageResult.students.size()!=0">
							<tbody>
								<!-- 利用struts迭代器迭代输出studentPageResult.students列表的属性,status属性命名为sts.   海志-->
								<s:iterator value="studentPageResult.students" id="studenttable"
									status="sts">
									<tr id="tr-<s:property value="id"/>">
										<!-- 为每一行的tr标签赋id值(tr-id) -->
										<td><input type="checkbox" name="studentsId"
											value="<s:property value="id"/>">
										</td>
										<!-- 输出索引数值,为  sts数值+(当前页面-1)*后台分页的每页数量 -->
										<td class="tc" name="studentIndex"><s:property
												value="%{#sts.count+(studentPageResult.currentPage-1)*(@com.wuhei.cms.search.result.PageResult@getPageCount())}" />
										</td>
										<td><s:property value="code" />
										</td>
										<td><s:property value="name" />
										</td>
										<td><s:property value="sex" />
										</td>
										<td><s:property value="level" />
										</td>
										<td><s:property value="grade" />
										</td>
										<td><s:property value="stuclass" />
										</td>


										<%
											if (roleCode.equalsIgnoreCase("ROLE_ACAMGR")) {
										%>
										<td><a
											href=" <s:property value="%{deleteStudentUrl}"/>?studentsId=<s:property value="id"/>">
												删除</a>
										</td>

										<%
											}
										%>
									</tr>

								</s:iterator>
							</tbody>
						</s:if>	
						</table>
						
						<br>
					<s:elseif test="studentSearchMeta.keyword != null">
						<div class="w1000 bc f20 cgray h200 mt10 mb10 tc">暂时没有结果</div>
					</s:elseif>
					<s:else>
						<div class="w1000 bc f20 cgray h200 mt10 mb10 tc">暂时没有数据</div>
					</s:else>
					<br>
						<%
						     if (roleCode.equalsIgnoreCase("ROLE_ACAMGR")){
						 %>
						<p class="tc f14 fl pt10">
						<input type="button" class="button_green w40 fl  " id="chkAll" value="全选" onclick="checkAll();" >
						<input type="button" class="button_green w40 fl none " id="delCheckAll" value="取消" onclick="delCheck();">
						<input type="submit" class="button_blue w50 fl"  value="批量删除">
					    </p>
					    <%
					           }
					     %>
						<br>


						<!-- 分页容器 使用分页控件kkpaper 详见https://github.com/pgkk/kkpager -->
						<div id="kkpager" class="pagerBox fr "></div>
						<div class="clear pb20"></div>

						<%
							if (roleCode.equalsIgnoreCase("ROLE_ACAMGR")) {
						%>
						
							
					</form>
					
	                   <div id="onload" class="m30 w1000 bc tc mt100 mb100 hide">
							<img src="<s:url value="images/loading.gif"/>">
						</div>
						
					

				<%
					}
				%>
			</div>

			<%
				if (roleCode.equalsIgnoreCase("ROLE_ACAMGR")) {
			%>
			<s:if test="studentImportResult==null"></s:if>
			<s:elseif test="studentImportResult.errorStudents.isEmpty()==false">
				<div class="listTable w1000 bc cgray">
					<table id="errListStudent" class="bc f14 tl w900" cellspacing="0"
						cellpadding="0" width="1000">
						<thead>
							<tr>
								<th class="tc" width="60">序号</th>
								<th width="240">学号</th>
								<th width="180">姓名</th>
								<th width="80">性别</th>
								<th width="100">学位</th>
								<th width="150">年级</th>
								<th width="150">班级</th>

							</tr>
						</thead>
						<tbody>
							<s:iterator value="studentImportResult.errorStudents"
								id="errStudentTable" status="sts">
								<tr id="tr-<s:property value="%{#sts.count}"/>">
									<td class="tc" name="studentIndex"><s:property
											value="%{#sts.count}" />
									</td>
									<td><s:property value="code" />
									</td>
									<td><s:property value="name" />
									</td>
									<td><s:property value="sex" />
									</td>
									<td><s:property value="level" />
									</td>
									<td><s:property value="grade" />
									</td>
									<td><s:property value="stuclass" />
									</td>
								</tr>

							</s:iterator>
						</tbody>
					</table>
				</div>
			</s:elseif>

			<!-- 未成功删除的学生 author:GongXiang -->
			<s:if test="studentDeleteResult==null"></s:if>
			<s:elseif test="studentDeleteResult.errStudents.isEmpty()==false">
				<div class="listTable w1000 bc cgray">
					<table id="errListStudent" class="bc f14 tl w900" cellspacing="0"
						cellpadding="0" width="1000">
						<thead>
							<tr>
								<th class="tc" width="60">序号</th>
								<th width="240">学号</th>
								<th width="180">姓名</th>
								<th width="80">性别</th>
								<th width="100">学位</th>
								<th width="150">年级</th>
								<th width="150">班级</th>

							</tr>
						</thead>
						<tbody>
							<s:iterator value="studentDeleteResult.errStudents"
								id="errStudentTable" status="sts">
								<tr id="tr-<s:property value="%{#sts.count}"/>">
									<td class="tc" name="studentIndex"><s:property
											value="%{#sts.count}" />
									</td>
									<td><s:property value="code" />
									</td>
									<td><s:property value="name" />
									</td>
									<td><s:property value="sex" />
									</td>
									<td><s:property value="level" />
									</td>
									<td><s:property value="grade" />
									</td>
									<td><s:property value="stuclass" />
									</td>
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


<!-- footer部分 -->
<%@ include file="../footer.jsp"%>


<script type="text/javascript">
    function showOnload()
    {
   
      $("#onload").show();
    }

	//生成分页控件  
	kkpager
			.generPageHtml({
				pno : '<s:property value="studentPageResult.currentPage"/>', //当前页
				mode : 'link', //可选，默认就是link
				//总页码  
				total : '<s:property value="studentPageResult.totalPage"/>',
				//总数据条数  
				totalRecords : '<s:property value="studentPageResult.totalCount"/>',
				//链接前部  
				// hrefFormer : '<s:url value="%{page_url}"/>',
				hrefFormer : '<s:property value="%{listStudentUrl}"/>?studentSearchMeta.keyword=<s:property value="studentSearchMeta.keyword"/>&studentSearchMeta.stdgrade=<s:property value="studentSearchMeta.stdgrade"/>&studentSearchMeta.stdclass=<s:property value="studentSearchMeta.stdclass"/>',
				//链接尾部  
				// hrefLatter : '${hrefLatter}',
				//链接算法
				getLink : function(n) {
					//这里是获取I页面的算法

					return this.hrefFormer + '&'
							+ "studentSearchMeta.currentPage=" + n;
				}

			});
</script>
<script>

</script>
