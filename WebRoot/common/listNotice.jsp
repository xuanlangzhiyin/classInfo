<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.wuhei.cms.model.*"%>
<%@ include file="../header.jsp"%>

<s:url id="listNoticeUrl" action="listNotice" />

<s:url id="saveSuggestionUrl" action="saveSuggestion" />
<div id="container">
	<div class="white w1200 bc">
		<div class="w1060 bc">
			<div class="formTable-context">  
				<div class="listTable w1000 bc cgray">
					  
					<s:form action="listNotice" method="post" class="fr">
					<div class="searcher light-blue h50 bc">
					
						<div class="tc mt10 w1000 pt10">
							<%
								if (roleCode.equalsIgnoreCase("ROLE_ACAMGR")) {
							%>
							<s:url id="newNoticeUrl" action="newNotice">
							</s:url>
							<a href="<s:property value="%{newNoticeUrl}"/>?noticeSearchMeta.keyword=<s:property value="noticeSearchMeta.keyword"/>">
								<input class="fr w100 button_green f14 mb10" value="发布通知"></input>
							</a>
							<%
								}
							%>
							
								<span class="fr"> <input
									class="textInputBox w200 m0 h30 "
									name="noticeSearchMeta.keyword" placeholder="请输入关键字"
									value="<s:property value="noticeSearchMeta.keyword"/>" /> <input
									class="button_blue f14 w100 ml10 " type="submit" value="搜索" />
								</span>
							
						</div>
						
					</div>
					</s:form>
					<table id="listNotice" class="bc f14" cellspacing="0"
						cellpadding="0" width="1000">
						<thead>
							<tr>
								<th width="40" class="filter-false remove sorter-false">&nbsp;</th>
								<th width="40">序号</th>
								<th width="160">发布者</th>
								<th width="400">通知标题</th>
								<th width="180">发布时间</th>
								<th width="100" class="filter-false remove sorter-false">操作</th>
							</tr>
						</thead>
						<s:if test="noticePageResult.notices.size()!=0">
							<tbody>
								<s:iterator value="noticePageResult.notices" id="noticetable"
									status="sts">
									<tr id="tr-<s:property value="id"/>">
										<td><input type="checkbox"
											value="<s:property value="id"/>"></td>
										<!-- sts为struts命名对象，保存在ActionContext中，故需用#访问 -->
										<td name="noticeIndex"><s:property
												value="%{#sts.count+(noticePageResult.currentPage-1)*(@com.wuhei.cms.search.result.PageResult@getPageCount())}" />
										</td>
										<td><s:property value="name" />
										</td>
										<td><s:property value="title" /></td>
										<td><s:date name="releasetime" format="yyyy-MM-dd" /></td>
										<s:url id="downloadUrl" action="downloadNotice">
											<s:param name="noticeid" value="id"></s:param>
											<s:param name="noticeSearchMeta.keyword"
												value="noticeSearchMeta.keyword"></s:param>
											<s:param name="noticeSearchMeta.currentPage"
												value="noticeSearchMeta.currentPage"></s:param>
										</s:url>
										<s:url id="deleteUrl" action="deleteNotice">
								
										</s:url>
										<td><s:a href="%{downloadUrl}">下载</s:a>
										<%  if (roleCode.equalsIgnoreCase("ROLE_ACAMGR")){ %>
										<span>&nbsp;|&nbsp;</span>
			<a href='<s:property value="%{deleteUrl}"/>?noticeid=<s:property value="id"/>&noticeSearchMeta.currentPage=<s:property value="noticeSearchMeta.currentPage"/>'>删除</a>
										<%} %>
										</td>
									</tr>
								</s:iterator>
							</tbody>
						</s:if>
					</table>
					<br>
					<s:elseif test="noticeSearchMeta.keyword != null">
						<div class="w1000 bc f20 cgray h200 mt10 mb10 tc">暂时没有结果</div>
					</s:elseif>
					<s:else>
						<div class="w1000 bc f20 cgray h200 mt10 mb10 tc">暂时没有通知</div>
					</s:else>
					<div id="kkpager" class="pagerBox fr"></div>
					<div class="clear mb20"></div>
				</div>
				<div class="clear"></div>
				<div class="listTable w1000 bc">
					<div id="messageBox"></div>
				</div>
			</div>

		</div>
		<div class="clear mb50"></div>

		<br>
	</div>
</div>
<%@ include file="../footer.jsp"%>

<script type="text/javascript" src="js/common/suggestion.js"
	charset="utf-8"></script>

<script type="text/javascript">
	//生成分页控件  
	kkpager
			.generPageHtml({
				pno : '<s:property value="noticePageResult.currentPage"/>', //当前页
				mode : 'link', //可选，默认就是link
				//总页码  
				total : '<s:property value="noticePageResult.totalPage"/>',
				//总数据条数  
				totalRecords : '<s:property value="noticePageResult.totalCount"/>',
				//链接前部  

				hrefFormer : '<s:property value="%{listNoticeUrl}"/>?noticeSearchMeta.keyword=<s:property value="noticeSearchMeta.keyword"/>',

				//链接尾部  
				// hrefLatter : '${hrefLatter}',
				//链接算法
				getLink : function(n) {
					//这里是获取I页面的算法

					return this.hrefFormer + '&'
							+ "noticeSearchMeta.currentPage=" + n;
				}
			});
</script>
