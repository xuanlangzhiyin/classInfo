<%@page import="com.wuhei.cms.model.Cmission"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<%
	
%>
<s:url id="savePersonalMissionUrl" action="savePersonalMission" />
<s:url id="listCMissionUrl" action="listCmission" />
<div id="container">
	<div class="w1200 white bc">
		<div class="w1060 bc">
			<div class="cb"></div>

  
			<div class="main-boxes">
				<div class="main-box">
					<h2 class="boxTitle active" onclick="slideBox(this);">任务简介</h2>
					<s:form id="saveMission" action="savePersonalMission" enctype="multipart/form-data"
							method="post">
					<div class="boxContext">
						
							<input type="hidden" name="courseid"
								value="<s:property value="courseid" />" />
							<div class="m30 boxForm f16" id="editBox4intro">
								<p>
									<span class="mr20 w80">课程名称：</span><span></span>
								</p>
								<p>
									<span class="mr20 w80">任务类型：</span><span>个人任务</span>
								</p>

								<p>
									<span class="mr20 w80">任务名称：</span> <input class="f14 pl10"
										name="cmission.name" type="text" id="cmissionname">
								</p>

								<p>
									<span class="mr20 w80">截止时间：</span> <input class="f14 pl10"
										name="cmission.deadline" type="text" value="2 010年11月31日"
										onClick="WdatePicker()" onfocus="WdatePicker()">
								</p>

								<p class="hide">
									<span class="mr20 w80">任务简介：</span> <span
										class="formText f16 t2"> <textarea
											name="cmission.requirement" id="editor" cols="30" rows="10"
											class="f14 t2 p10 m0">
											暂不实现该功能
										</textarea>
									</span>
								</p>
								
								<div class="messageBox warn" id="messageBox">
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
								<div class="message">
									<div class="clear"></div>
									<div class="messageBox info">
								    <div class="messageTitle">
								      <span class="fl messageLogo"></span><span class="close fr"
									onclick="closeMessage(this)"></span>
								    </div>
								    <div class="messageContent">
								    <p class="t2 ">附件信息：</p>
								    </div>
								   </div>	
								 </div>
								<p>
									<span class="mr20 w100 ">附件：</span>
									<!-- 选择文件时检验大小,maxsize=4MB,若文件大于4MB,弹消息提示框并重置附件 -->
									<input class="noBorder" type="file" name="file"
										onchange=checkFile(this,4*1024*1024)>
								</p>
								<div class="tc h50">
									<a
										href="<s:property value="%{listCMissionUrl}"/>?courseid=<s:property value="courseid"/>">
										<input type="button" class="btn blue" value="取&nbsp消">
									</a> <input type="submit" class="btn green" value="提&nbsp交">
								</div>
							</div>

						
					</div>
					</s:form>


				</div>

			</div>
			<div class="clear pb50"></div>
		</div>
	</div>
</div>

<script type="text/javascript" charset="utf-8"
	src="<s:url value="js/My97DatePicker/WdatePicker.js"/>"></script>

<%@ include file="../footer.jsp"%>
	<script type="text/javascript">
		$(document).ready(function() {
//			var editor = new Simditor({
//				textarea : $('#editor')
//			});
		
		$("#messageBox").hide();

		$(".close").click(function(){
		$(this).parent().parent().hide();
		});
			
		//提交按钮监控,如果第一个附件为空则报错,Form表单提交回滚.
		$("#saveMission").submit(function(e){
     
            if($("#cmissionname").val()=="")
            {
                //选中消息框
                var inputMsg=$("#messageBox");
                //填充消息框内容.
                fillinMsg(inputMsg,"信息提示","任务名称不能为空");
                $(inputMsg).show();
                //Form表单提交回滚
                e.preventDefault();
                    
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


