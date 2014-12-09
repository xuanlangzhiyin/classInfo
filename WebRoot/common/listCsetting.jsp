<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.wuhei.cms.model.*"%>
<%@ include file="../header.jsp"%>
  
<s:url id="listCsettingUrl" action="listCsetting" />
<s:url id="viewCsettingUrl" action="viewCsetting" />
<s:url id="deleteCsettingUrl" action="deleteCsetting" />
<div id="container">
	<div class="white w1200 bc">
		<div class="w1060 bc">
		  
			<div class="formTable-context">
				<div class="listTable w1000 bc cgray">
					<!-- form表单静态提交keyword关键字给后台的csettingSearchMeta后,调用listCsetting.action刷新页面  海志-->
					<s:form action="listCsetting" method="post">
						<div id="" class="search bc h50 light-blue mb20 ">
							<p class="tc f14 fr pt10">
								<input type="hidden" name="majorid"
									value="<s:property value='majorid'/>"> <input
									class="textInputBox fl w200 m0 h30"
									name="csettingSearchMeta.keyword" placeholder="请输入关键字"
									value="<s:property value="csettingSearchMeta.keyword"/>" /> <input
									class="button_blue f14 w100 fl ml10" type="submit" value="搜索">
							</p>
						</div>
					</s:form>
					
						<table id="" class="tc bc f14" cellspacing="0" cellpadding="0"
							width="1000">
							<thead>

								<tr>
									<th width="50">&nbsp</th>
									<th width="40">序号</th>
									<th width="80">课程编号</th>
									<th width="120">课程类别</th>
									<th width="560">课程名称</th>
							<% if (roleCode.equalsIgnoreCase("ROLE_ACAMGR")){%>		
									<th width="150">操作</th>
							<%} %>		
								</tr>
							</thead>
							<s:if test="csettingPageResult.csettings.size() !=0">
							<tbody>
								<!-- 利用struts迭代器,对csettingPageResult.csettings对象进行迭代,status名为sts 海志-->
								<s:iterator value="csettingPageResult.csettings"
									id="Csettingtable" status="sts">
									<tr id="tr-<s:property value="id"/>">
										<td><input type="checkbox" name="csettingId"
											value="<s:property value="id"/>">
										</td>
										<!-- 输出索引数值,为  sts数值+(当前页面-1)*后台分页的每页数量 -->
										<td class="tc" name="CsettingIndex"><s:property
												value="%{#sts.count+(csettingPageResult.currentPage-1)*(@com.wuhei.cms.search.result.PageResult@getPageCount())}" />
										</td>
										<td><s:property value="code" />
										</td>
										<td><s:property value="ccategoryname" />
										</td>
										<td><s:property value="name" />
										</td>
										<% if (roleCode.equalsIgnoreCase("ROLE_ACAMGR")){%>		
										<td><a
											href='<s:property value="%{viewCsettingUrl}"/>?majorid=<s:property value="majorid"/>&csettingid=<s:property value="id"/>'>编辑</a><span
											class="m10">|</span><a
											href="<s:property value="%{deleteCsettingUrl}" />?csettingId=<s:property value="id"/>&majorid=<s:property value="majorid"/>">删除</a>
										</td>
										<%} %>
									</tr>
								</s:iterator>
							</tbody>
							</s:if>
						</table>
						<br>
					<s:elseif test="csettingSearchMeta.keyword != null">
						<div class="w1000 bc f20 cgray h200 mt10 mb10 tc">暂时没有结果</div>
					</s:elseif>
					<s:else>
						<div class="w1000 bc f20 cgray h200 mt10 mb10 tc">暂时没有通知</div>
					</s:else>
						<div class="clear"></div>
						<!-- 全选 -->
						<%
						     if (roleCode.equalsIgnoreCase("ROLE_ACAMGR")){
						 %>
						<p class="tc f14 fl pt10">
							<input type="button" class="button_green w40 fl  " id="chkAll"
								value="全选" onclick="checkAll();"> <input type="button"
								class="button_green w40 fl none " id="delCheckAll" value="取消"
								onclick="delCheck();"> <input type="submit"
								class="button_blue w50  fl" value="批量删除">
						</p>
						<%
						     }
						 %>
						<!-- 分页容器 使用分页控件kkpaper 详见https://github.com/pgkk/kkpager -->
						<div id="kkpager" class="pagerBox fr"></div>
						<div class="clear mb20"></div>
						<br>

						<%
							if (roleCode.equalsIgnoreCase("ROLE_ACAMGR")) {
						%>
						
					
				</div>
				<s:if test="csettingDeleteResult==null"></s:if>
				<s:elseif test="csettingDeleteResult.errorCsetting.isEmpty()==false">
					<div class="errorlistTable w1000 bc cgray">
						<table id="errorListCsetting" class="bc f14 tl w900"
							cellspacing="0" cellpadding="0" width="1000">
							<thead>
								<tr>
									<th class="tc" width="60">序号</th>
									<th width="240">代码</th>
									<th width="180">名称</th>
									<th width="180">课程类型</th>

									<th width="180">层级</th>

								</tr>
							</thead>
							<tbody>
								<s:iterator value="csettingDeleteResult.errorCsetting"
									id="errorCsettingTable" status="sts">
									<tr id="tr-<s:property value="%{#sts.count}"/>">
										<td class="tc" name="csettingIndex"><s:property
												value="%{#sts.count}" />
										</td>
										<td><s:property value="code" />
										</td>
										<td><s:property value="name" />
										</td>
										<td><s:property value="type" />
										</td>

										<td><s:property value="level" />
										</td>
									</tr>

								</s:iterator>
							</tbody>
						</table>
					</div>
				</s:elseif>
				<s:if test="csettingImportResult==null"></s:if>
				<s:elseif
					test="csettingImportResult.errorCsettingDetailView.isEmpty()==false">
					<div class="errorlistTable w1000 bc cgray">
						<table id="errListCsetting" class="bc f14 tl w900" cellspacing="0"
							cellpadding="0" width="1000">
							<thead>
								<tr>
									<th class="tc" width="60">序号</th>
									<th width="240">代码</th>
									<th width="180">名称</th>
									<th width="180">课程类型</th>
									<th width="180">课程分类</th>
									<th width="180">层级</th>

								</tr>
							</thead>
							<tbody>
								<s:iterator value="csettingImportResult.errorCsettingDetailView"
									id="errCsettingTable" status="sts">
									<tr id="tr-<s:property value="%{#sts.count}"/>">
										<td class="tc" name="csettingIndex"><s:property
												value="%{#sts.count}" />
										</td>
										<td><s:property value="code" />
										</td>
										<td><s:property value="name" />
										</td>
										<td><s:property value="type" />
										</td>
										<td><s:property value="ccategoryname" />
										</td>
										<td><s:property value="level" />
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
	</div>
	<div class="clear mb50"></div>
	<br>
</div>

<!-- footer部分 -->
<%@ include file="../footer.jsp"%>


<script type="text/javascript">
	//生成分页控件  
	kkpager
			.generPageHtml({
				pno : '<s:property value="csettingPageResult.currentPage"/>', //当前页
				mode : 'link', //可选，默认就是link
				//总页码  
				total : '<s:property value="csettingPageResult.totalPage"/>',
				//总数据条数  
				totalRecords : '<s:property value="csettingPageResult.totalCount"/>',
				//链接前部  
				// hrefFormer : '<s:url value="%{page_url}"/>',
				hrefFormer : '<s:property value="%{listCsettingUrl}"/>?majorid=<s:property value="majorid"/>&csettingSearchMeta.keyword=<s:property value="csettingSearchMeta.keyword"/>',
				//链接尾部  
				// hrefLatter : '${hrefLatter}',
				//链接算法
				getLink : function(n) {
					//这里是获取I页面的算法

					return this.hrefFormer + '&'
							+ "csettingSearchMeta.currentPage=" + n;
				}

			});
</script>
