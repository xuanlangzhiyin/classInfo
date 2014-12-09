<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.wuhei.cms.model.*"%>
<%@ include file="../header.jsp"%>

<s:url id="listMajorUrl" action="listMajor" />  
<div id="container">
	<div class="white w1200 bc">
		<div class="w1060 bc">
			<div class="formTable-context">
				<div class="listTable w1000 bc cgray">

					<s:form  action="listMajor" method="post">
					<div class=" pt10 w1000 bc">
						<div id="" class="search bc h50 light-blue mb20 ">
							<p class="tc f14 fr pt10">
								<input class="textInputBox fl w200 m0 h30"
									name="majorSearchMeta.keyword" placeholder="请输入关键字" value="">
								<input class="button_blue f14 w100 fl ml10" type="submit"
									value="搜索">
							</p>
						</div>
					</div>	   
					</s:form>

					<div class="clear"></div>

					<table id="" class="bc f14" cellspacing="0" cellpadding="0"
						width="1000">
						<thead>

							<tr>
								<th width="40" class="filter-false remove sorter-false">&nbsp</th>
								<th width="50">序号</th>
								<th width="80">专业编号</th>
								<th width="640">专业名称</th>
								<th width="150" class="filter-false remove sorter-false">操作</th>
							</tr>
						</thead>

						<s:if test="majorPageResult.majors.size() !=0">
						<tbody>

							<s:iterator value="majorPageResult.majors" status="sts">
								<!-- 迭代mojors 此表使用前台分页 -->
								<tr majorid="<s:property value="id"/>">
									<!--<td><input type="checkbox" majorid="<s:property value="id"/>" ></td>-->
									<td><input type="checkbox"></td>
									<td><s:property value="#sts.count" /></td>
									<td><s:property value="code" /></td>
									<td><s:property value="name" /></td>
									<s:url id="listCsettingUrl" action="listCsetting">
										<s:param name="majorid" value="id"></s:param>
									</s:url>
									<td><s:a href="%{listCsettingUrl}">课程</s:a></td>
								</tr>
							</s:iterator>
						</tbody>
						</s:if>
					</table>
					<br>
					<s:elseif test="majorSearchMeta.keyword != null">
						<div class="w1000 bc f20 cgray h200 mt10 mb10 tc">暂时没有结果</div>
					</s:elseif>
					<s:else>
						<div class="w1000 bc f20 cgray h200 mt10 mb10 tc">暂时没有数据</div>
					</s:else>
					<div class="clear"></div>
					<!-- 后台分页控件容器 -->
					<div id="kkpager" class="pagerBox fr"></div>
					<div class="clear mb20"></div>
					<br>
					<%
						if (roleCode.equalsIgnoreCase("ROLE_ACAMGR")) {
					%>
					

					<s:if test="majorImportResult==null"></s:if>
					<s:elseif test="majorImportResult.errorMajors.isEmpty()==false">
						<div class="errorlistTable w1000 bc cgray">
							<table id="errListStudent" class="bc f14 tl w900" cellspacing="0"
								cellpadding="0" width="1000">
								<thead>
									<tr>
										<th class="tc" width="60">序号</th>
										<th width="240">代码</th>
										<th width="180">名称</th>
									</tr>
								</thead>
								<tbody>
									<s:iterator value="majorImportResult.errorMajors"
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
					<%
						}
					%>
				</div>
			</div>


		</div>
		<div class="clear mb50"></div>
		<br>
	</div>
</div>
<%@ include file="../footer.jsp"%>

<script type="text/javascript">
	//生成分页控件  
	kkpager
			.generPageHtml({
				pno : '<s:property value="majorPageResult.currentPage"/>', //当前页
				mode : 'link', //可选，默认就是link
				//总页码  
				total : '<s:property value="majorPageResult.totalPage"/>',
				//总数据条数  
				totalRecords : '<s:property value="majorPageResult.totalCount"/>',
				//链接前部  
				// hrefFormer : '<s:url value="%{page_url}"/>',
				hrefFormer : '<s:property value="%{listMajorUrl}"/>?majorSearchMeta.keyword=<s:property value="majorSearchMeta.keyword"/>',
				//链接尾部  
				// hrefLatter : '${hrefLatter}',
				//链接算法
				getLink : function(n) {
					//这里是获取I页面的算法

					return this.hrefFormer + '&'
							+ "majorSearchMeta.currentPage=" + n;
				}
			});
</script>
<script>

</script>