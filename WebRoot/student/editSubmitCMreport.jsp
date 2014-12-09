<%@page import="com.wuhei.cms.model.Cmission"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<%
	
%>
<s:url id="viewCmreport4StdUrl" action="viewCmreport4Std" />
<s:url id="listCmissionUrl" action="listCmission" />
<s:url id="cancelCmreportUrl" action="cancelCMreport" />
<s:url id="downloadAttachUrl" action="downloadAttach" />
<div id="container">
	<div class="white w1200 bc">
		<div class="w1060 bc">
			  

			<div class="main-boxes">
				<div class="main-box">
					<h2 class="boxTitle active" onclick="slideBox(this);">个人任务</h2>
					<div class="boxContext">
						<div class="m30 boxForm f16">
							<p>
								<span class="mr20 w80">任务类型：</span>
								<s:property value="cmission.mtype" />
							</p>
							<s:if test="cmission.mtype=='小组任务' ">
								<p>
									<span class="mr20 w80">提交方式：</span>
									<s:property value="cmission.stype" />
								</p>
							</s:if>
							<p>
								<span class="mr20 w80">任务名称：</span>
								<s:property value="cmission.name" />
							</p>
							<p>
								<span class="mr20 w80">截止时间：</span>
								<s:property value="cmission.deadline" />
							</p>
							<p>
								<span class="mr20 w80">任务要求：</span><div class=" formText descriptionBox f16 t2">${requestScope.cmission.requirement}</div>
							<p>
								<span class="mr20 w80">任务附件：</span> <a
									href="<s:property value="downloadAttachUrl"/>?cmissionid=<s:property value="cmissionid"/>">
									<s:property value="cmission.oldname" /> </a>
							</p>
							<!-- 如果是小组任务,显示小组情况 -->
							<s:if test="cmission.mtype=='小组任务' ">
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
						<s:form id="saveCMreport" action="saveCMreport"
							enctype="multipart/form-data" method="post">
							<input type="hidden" name="cmcredit.cstudentid"
								value="<s:property value="cstudentid" />" />
							<input type="hidden" name="cmcredit.cmissionid"
								value="<s:property value="cmissionid" />" />
							<input type="hidden" name="cmissionid"
								value="<s:property value="cmissionid" />" />
							<input type="hidden" name="courseid"
								value="<s:property value="courseid" />" />
							<div class="m30 boxForm f16">
								<!-- 判断是否是小组组长,如果是组长,显示提交附件选项 -->
								<s:if test="isSubmitionShowed==true">
									<div class="messageBox warn" style="display:none">
										<div class="messageTitle">
											<span class="fl messageLogo"></span><span class="close fr"></span>
										</div>
										<div class="messageContent">
											<h4></h4>
											<ul>
												<li></li>
											</ul>
										</div>
									</div>  
						
									<p>
										<span class="mr20 w100 ">任务报告：</span>
										<!-- 隐含img标签 For IE浏览器提交文件 -->
										<img id="tempimg" dynsrc="" src="" style="display:none" />
										<!-- 选择文件时检验大小,maxsize=4MB,若文件大于4MB,弹消息提示框并重置附件 -->
										<input id="inputfile1" class="noBorder" type="file"
											name="viewablefile">
									</p>
									<p>
										<span class="mr20 w100"></span>（文件不宜过大，限制文件类型为pdf, doc,
										docx, xls, xlsx, rar, zip）
									</p>
								</s:if>
								
							</div>
							<div class="tc h50 f16">
								<a
									href="<s:property value="listCmissionUrl"/>?courseid=<s:property value="courseid"/>">
									<input type="button" class="btn blue" value="取&nbsp消">
								</a>
								<!-- 判断是否是小组组长,如果是组长,显示提交选项 -->
								<s:if test="isSubmitionShowed==true&&cmreport==null">
									<input type="submit" class="btn green"
										value="确&nbsp认&nbsp提&nbsp交" id="submitReport">
								</s:if>
							</div>
						</s:form>
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
	

		$(".close").click(function() {
			$(this).parent().parent().hide();
		});

		//提交按钮监控,如果第一个附件为空则报错,Form表单提交回滚.
		$("#saveCMreport").submit(function(e) {
			//选中消息框
			var inputMsg = $("#inputfile1").parent().prev("div");
			if ($("#inputfile1").val() == "") {
				if ($("#editor").val() == "")
					//填充消息框内容.
					fillinMsg(inputMsg, "信息提示", "报告说明和任务报告均不能为空");
				//填充消息框内容.
				else
					fillinMsg(inputMsg, "信息提示", "任务报告不能为空");
				$(inputMsg).show();
				//Form表单提交回滚
				e.preventDefault();
				return;
			}
			if ($("#editor").val() == "") {
				//填充消息框内容.
				fillinMsg(inputMsg, "信息提示", "报告说明不能为空");
				$(inputMsg).show();
				//Form表单提交回滚
				e.preventDefault();
				return;
			}
		});
	});

	
	//填充消息框函数
	function fillinMsg(e, title, msg) {
		$(e).children("div .messageContent").children("h4").text(title);
		$(e).children("div .messageContent").children("ul").children()
				.text(msg);
	}
</script>
