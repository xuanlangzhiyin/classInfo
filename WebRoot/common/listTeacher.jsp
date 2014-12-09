<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.wuhei.cms.model.*"%>
<%@ include file="../header.jsp"%>
<s:url id="deleteTeacherUrl" action="deleteTeacher" />
<s:url id="listTeacherUrl" action="listTeacher" />
<div id="container">
	<div class="white w1200 bc">
		<div class="w1060 bc">
			<div class="formTable-context">
				<div class="listTable w1000 bc cgray">
					<s:form action="listTeacher.action" method="POST">
						<div id="" class="searcher light-blue h50 bc">
							<div class="tc mt10 w1000 pt10">
							<span class="fr">
							  	 
								<input class="textInputBox  w200 m0 h30"
									name="teacherSearchMeta.keyword" placeholder="请输入关键字"
									value="<s:property value="teacherSearchMeta.keyword"/>">
								<input class="button_blue f14 w100  ml10" type="submit"
									value="搜索">
							</span>		
							</div>
						</div>
					</s:form>
					<div class="clear"></div>
                   <form  action="<s:property value="%{deleteTeacherUrl}"/>" method="post">
					<table id="studentList" class="bc f14 tl" cellspacing="0" cellpadding="0"
						width="1000">
						<thead>
							<tr>
								<th width="40" class="filter-false remove sorter-false">&nbsp</th>
								<th width="40">序号</th>
								<th width="120">教工号</th>
								<th width="200">姓名</th>
								<th width="80">性别</th>
								<th width="360">邮箱</th>
								<%if (roleCode.equalsIgnoreCase("ROLE_ACAMGR")) { %>
								<th width="150" class="filter-false remove sorter-false">操作</th>
								<%} %>
							</tr>
						</thead>
				
					<s:if test="teacherPageResult.teachers.size()!=0">
						<tbody>
							<s:iterator value="teacherPageResult.teachers" id="teacherTable"
								status="sts">
								<tr id="tr-<s:property value="id"/>">			

									<td><input type="checkbox" name="teachersId" value="<s:property value="id"/>"></td>
									
																	
									<!-- sts为struts命名对象，保存在ActionContext中，故需用#访问 -->
									<td name="teacherIndex"><s:property
											value="%{#sts.count+(teacherPageResult.currentPage-1)*(@com.wuhei.cms.search.result.PageResult@getPageCount())}" />
									</td>
									<td><s:property value="code" /></td>
									<td><s:property value="name" /></td>
									<td><s:property value="sex" /></td>
									<td><s:property value="email" /></td>
									
									
						<%
						if (roleCode.equalsIgnoreCase("ROLE_ACAMGR")) {
					    %>
									<td >
									
									
										<a href=" <s:property value="%{deleteTeacherUrl}"/>?teachersId=<s:property value="id"/>">
											删除</a>
									</td>
									
									<%} %>

								</tr>
							</s:iterator>
							
						</tbody>
						</s:if>
					</table>
					
					<br>
					<s:elseif test="teacherSearchMeta.keyword != null">
						<div class="w1000 bc f20 cgray h200 mt10 mb10 tc">暂时没有结果</div>
					</s:elseif>
					<s:else>
						<div class="w1000 bc f20 cgray h200 mt10 mb10 tc">暂时没有数据</div>
					</s:else>
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
					
					<div id="kkpager" class="pagerBox fr"></div>
					<br>
					<div class="clear pb20"></div>


					<%
						if (roleCode.equalsIgnoreCase("ROLE_ACAMGR")) {
					%>
					
					
					<!-- 结束form -->
                    </form>
                 

					<%
						}
					%>

				</div>

				<%
					if (roleCode.equalsIgnoreCase("ROLE_ACAMGR")) {
				%>
				<!-- 未成功插入的teacher -->
				<s:if test="teacherImportResult==null"></s:if>
				<s:elseif test="teacherImportResult.errorTeachers.isEmpty()==false">
					<div class="clear pb20"></div>
					<div class="listTable w1000 bc cgray">
						<div id="messageBox"></div>
						<table id="" class="bc f14 tl" cellspacing="0" cellpadding="0"
							width="1000">
							<thead>

								<tr>
									<th width="100">序号</th>
									<th width="120">教工号</th>
									<th width="200">姓名</th>
									<th width="800">性别</th>
									<th width="360">邮箱</th>

								</tr>
							</thead>
							<tbody>
								<s:iterator value="teacherImportResult.errorTeachers"
									id="errTeacherTable" status="sts">
									<tr id="tr-<s:property value="id"/>">
										<!-- sts为struts命名对象，保存在ActionContext中，故需用#访问 -->
										<td name="teacherIndex"><s:property value="%{#sts.count}" /></td>
										<td><s:property value="code" /></td>
										<td><s:property value="name" /></td>
										<td><s:property value="sex" /></td>
										<td><s:property value="email" /></td>

									</tr>
								</s:iterator>
							</tbody>
						</table>
					</div>
				</s:elseif>

				<s:if test="teacherDeleteResult==null"></s:if>
				<s:elseif test="teacherDeleteResult.errTeachers.isEmpty()==false">
					<div class="clear pb20"></div>
					<div class="listTable errorlistTable w1000 bc cgray">
						<div id="messageBox"></div>
						<table id="" class="bc f14 tl" cellspacing="0" cellpadding="0"
							width="1000">
							<thead>

								<tr>
									<th width="40">序号</th>
									<th width="120">教工号</th>
									<th width="200">姓名</th>
									<th width="80">性别</th>
									<th width="360">邮箱</th>

								</tr>
							</thead>
							<tbody>
								<s:iterator value="teacherDeleteResult.errTeachers"
									id="errTeacherTable" status="sts">
									<tr id="tr-<s:property value="id"/>">
										<!-- sts为struts命名对象，保存在ActionContext中，故需用#访问 -->
										<td name="teacherIndex"><s:property value="%{#sts.count}" /></td>
										<td><s:property value="code" /></td>
										<td><s:property value="name" /></td>
										<td><s:property value="sex" /></td>
										<td><s:property value="email" /></td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
					</div>
				</s:elseif>
						
				
				<%
					}
				%>
				<br>
			</div>

		</div>
		<div class="clear mb50"></div>
		<br>
	</div>
</div>




<%@ include file="../footer.jsp"%>

<script src="js/jquery-1.8.3.min.js"></script>
<script src="js/common.js"></script>
<script type="text/javascript">
	//生成分页控件  
	kkpager
			.generPageHtml({
				pno : '<s:property value="teacherPageResult.currentPage"/>', //当前页
				mode : 'link', //可选，默认就是link
				//总页码  
				total : '<s:property value="teacherPageResult.totalPage"/>',
				//总数据条数  
				totalRecords : '<s:property value="teacherPageResult.totalCount"/>',
				//链接前部  
				// hrefFormer : '<s:url value="%{page_url}"/>',
				hrefFormer : '<s:property value="%{listTeacherUrl}"/>?teacherSearchMeta.keyword=<s:property value="teacherSearchMeta.keyword"/>',
				//链接尾部  
				// hrefLatter : '${hrefLatter}',
				//链接算法
				getLink : function(n) {
					//这里是获取I页面的算法

					return this.hrefFormer + '&'
							+ "teacherSearchMeta.currentPage=" + n;
				}
			});
</script>
<script type="text/javascript">
//$("#chk_all").click(function(){

  //   $("input[type='checkbox']").attr("checked","checked");

//});

</script>
<script>

</script>