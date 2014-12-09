<%@page import="com.wuhei.cms.model.Cmission"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<s:url id="viewPersonalMissionScoreUrl"
	action="viewPersonalMissionScore" />
<s:url id="downloadViewable" action="downloadPersonalViewable" />
<div id="container">
	<div class="white bc">
		<div class="bc ml20">  
  
			<div id="fullMain" class="m20">

				<div class="reportDes f16">
					<h4>任务描述</h4>
					<div class="reportContext p20 ">
						${cmission.requirement}
					</div>
					<br> <br>

				</div>


				<div class="mark mr20">
				
						<h4>任务报告</h4>
						<div class="fileList">
							<ul class="f16 m10">

								<s:if test="%{cmreport == null}">
									<span>暂无报告</span>
								</s:if>

								<s:if test="%{cmreport.viewablefilename != null}">
									<span><s:property value="cmreport.oldfilename" />
									</span>
									<a
										href="<s:property value="%{downloadViewable}"/>?courseid=<s:property value="courseid"/>
							&cmissionid=<s:property value="cmissionid"/>
							&cmgroupid=<s:property value="cmgroupid"/>
							&cmreport.id=<s:property value="cmreport.id"/>
							">下载
									</a>
								</s:if>
							</ul>
						</div>
				</div>
			<div class="cb"></div>


			<h4>评分</h4>

						<div id="evaluateBox">
							<div class="">
								<div class="m10 boxForm f16">
									<s:form action="savePersonalScore"
										enctype="multipart/form-data" method="post">
										<input type="hidden" name="cmstudent.id"
											value="<s:property value="cpersonalmstudentDetailView.id" />" />
										<input type="hidden" name="cmstudent.cmissionid"
											value="<s:property value="cmissionid" />" />
										<input type="hidden" name="cmcredit.cstudentid"
											value="<s:property value="cstudentid" />" />
										<input type="hidden" name="cmcredit.cmissionid"
											value="<s:property value="cmissionid" />" />
										<input type="hidden" name="cmissionid"
											value="<s:property value="cmissionid" />" />
										<input type="hidden" name="courseid"
											value="<s:property value="courseid" />" />
										<input type="hidden" name="cmstudentid"
											value="<s:property value="cpersonalmstudentDetailView.id" />" />
										<input type="hidden" name="cstudentid"
											value="<s:property value="cstudentid" />" />
										<div class=" h50">
											<div class="clear"></div>
											<p class="fl">
												<span class="mr10 w80 "><s:property
														value="cpersonalmstudentDetailView.name" />
												</span>

												<span><input class="w100 " type="text"
													id="<s:property value='cpersonalmstudentDetailView.id' />"
													value="<s:property value='cpersonalmstudentDetailView.credit' />"
													name="cmcredit.credit"></span>
											</p>
											<div class=" fr">
												<span class="mr10 w100 fl">备注：</span>
												<textarea class="w800 h50 t2" name="cmcredit.description" placeholder="请输入90字以内"><s:property value="cmcredit.description" /></textarea>
											</div>
											
											
										</div>
										  	

										

										<div id="reportMarkButton" class="h50 f16 tc mt100">
											<div class="clear"></div>

											<a
												href="<s:property value="%{viewPersonalMissionScoreUrl}"/>?cmissionid=<s:property value="cmissionid"/>&courseid=<s:property value="courseid"/>">
												<input type="button" class="btn blue" value="取&nbsp消">
											</a> <input type="submit" class="btn green" value="提&nbsp交">
										</div>
									</s:form>
								</div>
							</div>
						</div>
							</div>	
		<div class="clear mb50"></div>
		<br>
	</div>
</div>
<footer>
	<div id="footer">
		<p class="w1000 bc mt50 tc f14 cgray">2014 Copyright ©华南理工大学软件学院</p>
	</div>
</footer>
</div>
<script src="js/jquery-1.8.3.min.js"></script>
<script src="js/flexpaper/flexpaper.js"></script>
<script src="js/flexpaper/flexpaper_handlers.js"></script>
<script src="js/common.js"></script>
