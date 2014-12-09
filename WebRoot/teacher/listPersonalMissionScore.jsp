<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<s:url id="listCmissionUrl" action="listCmission" />
<s:url id="evaluatePersonalMissionUrl" action="evaluatePersonalMission" />
<div id="container">
	<div class="white w1200 bc">  
		<div class="w1060 bc">    
  
			<div class="clear"></div>  
			<div class="context">
				<div class="m30 formTableBox f16">
					
					<div class="formTable-context white">
						<div id="personalMissionStudent" class="listTable w960 bc cGrey">
							<br>
							

							<table id="personalMissionTable" class="bc f14" cellspacing="0"
								cellpadding="0" width="1000">
								<thead>
									<tr>
										<th width="40">&nbsp</th>
										<th width="50">序号</th>
										<th width="180">学号</th>
										<th width="100">姓名</th>
										<th width="80">性别</th>
										<th width="120">年级</th>
										<th width="150">班级</th>
										<th width="80">评分</th>
										<th width="80">报告</th>
										<th width="150">操作</th>
									</tr>
								</thead>
								<tfoot>
									<tr>
										<th width="40">&nbsp</th>
										<th width="50">序号</th>
										<th width="180">学号</th>
										<th width="100">姓名</th>
										<th width="80">性别</th>
										<th width="120">年级</th>
										<th width="150">班级</th>
										<th width="80">评分</th>
										<th width="80">报告</th>
										<th width="150">操作</th>
									</tr>
								</tfoot>

								<tbody>
									<!-- 行id 为 tr-学生id tr内部组合id均为学生id，传的参数也都是学生id-->
									<s:iterator value="cmstudentListDetailView" status="sts">
										<tr id="tr-<s:property value="id"/>">
											<td><input type="checkbox"
												value="<s:property value="id"/>"></td>
											<td name="studentIndex"><s:property value="#sts.count" />
											</td>
											<td><s:property value="studentCode" />
											</td>
											<td><s:property value="name" />
											</td>
											<td><s:property value="sex" />
											</td>
											<td><s:property value="studentGrade" />
											</td>
											<td><s:property value="stuClass" />
											</td>
											
											<s:if test="credit==null " >
												<td>未评分</td>
											</s:if>
											<s:if test="credit!=null " >
												<td><s:property value="credit" />
												</td>
											</s:if>
											<td><s:property value="isSubmitChinese" />
											</td>
											<td class="tc"><a href="<s:property value="%{evaluatePersonalMissionUrl}"/>?courseid=<s:property value="courseid"/>&cmissionid=<s:property 
										value="cmissionid"/>&cstudentid=<s:property 
										value="cstudentid"/>&cmstudentid=<s:property 
										value="id"/>"
												>添加评分</a>
												</td>
										</tr>
									</s:iterator>


								</tbody>

							</table>
							<br>
							<div class="pager fr mt10 mb10 personalMissionTablePager ">
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
									class="pageButton next fl" alt="Next" title="Next page" /> </span> <span><img
									src="<s:url value="images/last.png"/>"
									class="pageButton last fl" alt="Last" title="Last page" /> </span> <span
									class="fl">转至: </span><select class="gotoPage w50 fl"></select>
								<div class="clear"></div>
							</div>
							<div class="clear"></div>
						</div>
					</div>

				</div>
		
				<div class="cb"></div>
				<div id="" class="tc h50 f16">
					<a
						href="<s:property value="%{listCmissionUrl}"/>?courseid=<s:property value="courseid"/>&cmissionid=<s:property 
										value="cmissionid"/>">
						<input type="button" class="btn green" value="返&nbsp回"> </a>
				</div>

			</div>
			<div class="clear mb50"></div>
			<br>
		</div>
	</div>
	</div>
	<%@ include file="../footer.jsp"%>
	<script type="text/javascript">
	tablePager("#personalMissionTable",'.personalMissionTablePager');
	var courseid=<s:property value="courseid"/>;
	var cmissionid=<s:property value="cmissionid"/>;
	</script>