<%@page import="com.wuhei.cms.model.Cmission"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<%
	
%>
<s:url id="editCmreport4StdUrl" action="editCmreport4Std" />
<s:url id="listCmissionUrl" action="listCmission" />
<s:url id="cancelCmreportUrl" action="cancelCMreport" />
<s:url id="downloadAttachUrl" action="downloadAttach"/>
<s:url id="downloadReportUrl" action="downloadReport"/>
<s:url id="downloadUnviewableUrl" action="downloadUnviewable"/>
<div id="container">
	<div class="white w1200 bc">
		<div class="w1060 bc">
		
			  
			<div class="main-boxes">
				<div class="main-box">
					<h2 class="boxTitle active" onclick="slideBox(this);">课程任务</h2>
					<div class="boxContext">
						<div class="m30 boxForm f16">
							<p>
								<span class="mr20 w80">任务类型：</span><s:property value="cmission.mtype"/>
							</p>
							<s:if test="cmission.mtype=='小组任务' " >
							<p>
								<span class="mr20 w80">提交方式：</span><s:property value="cmission.stype"/>
							</p>
							</s:if>
							<p>
								<span class="mr20 w80">任务名称：</span><s:property value="cmission.name"/>
							</p>
							<p>
								<span class="mr20 w80">截止时间：</span><s:property value="cmission.deadline"/>
							</p>
							<p>
								<span class="mr20 w80">任务要求：</span><div class="formText descriptionBox f16 t2 ">${requestScope.cmission.requirement}</div>
							<p>
								<span class="mr20 w80">任务附件：</span>
								<a href="<s:property value="downloadAttachUrl"/>?cmissionid=<s:property value="cmissionid"/>">
								<s:property value="cmission.oldname"/></a>
							</p>
							
							<s:if test="cmission.mtype=='小组任务' " >
							<p>
								<span class="mr20 w80">小组情况：</span>组名：
								<s:property value="cmgroupName" />
								&nbsp;&nbsp;组长：
								<s:property value="cmgroupLeader" />
								&nbsp;&nbsp;组员：
								<s:property value="cmgroupMembers" />
							</p>
							</s:if>	
						</div>
						<div class="m30 boxForm f16">
								<span class="mr20 w80">任务报告：</span><span>
								<a href="<s:property value="downloadReportUrl"/>?cmreportid=<s:property value="cmreport.id"/>">
								<s:property value="cmreport.oldfilename" /></a></span>
						</div>
						<div class="tc h50 f16">
							<a href="<s:property value="listCmissionUrl"/>?courseid=<s:property value="courseid"/>">
							<input type="button" class="btn blue" value="取&nbsp消"> </a>
							<!-- 判断是否是小组组长,如果是组长,显示提交选项 -->
							<s:if test="isSubmitionShowed==true&&cmreport==null&&cmission.isactive=='可提交'">
							<a href="<s:property value="%{editCmreport4StdUrl}"/>?cmissionid=<s:property value="cmissionid"/>">
							<input type="submit" class="btn green" value="提&nbsp交&nbsp报&nbsp告" id="submitReport">
							</a>
							</s:if>
							
							<s:if test="isSubmitionShowed==true&&cmreport!=null">
						<a href="<s:property value="cancelCmreportUrl"/>?cmreportid=<s:property value="cmreportid"/>&cmissionid=<s:property value="cmissionid"/>&courseid=<s:property value="courseid"/>">
							<input type="button" class="btn red" value="撤&nbsp回"> 
							</a>
						</s:if>
						</div>
						
					</div>
				</div>
			</div>
			<div class="cb"></div>


		</div>
		<div class="clear mb50"></div>
		<br>
	</div>
</div>

<script type="text/javascript" charset="utf-8"
	src="<s:url value="js/My97DatePicker/WdatePicker.js"/>"></script>

<%@ include file="../footer.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {
		var editor = new Simditor({
			textarea : $('#editor')
		});
	});
</script>
