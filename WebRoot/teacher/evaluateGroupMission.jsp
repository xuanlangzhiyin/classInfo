<%@page import="com.wuhei.cms.model.Cmission"%>

<%@ include file="../header.jsp"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<s:url id="viewGroupMissionScoreUrl" action="viewGroupMissionScore" />
<s:url id="downloadViewable" action="downloadGroupViewable" />
 
<div id="container">  
	<div class="white bc">
		<div class="bc ml20">

			<div id="fullMain" class="m20">
				<div class="reportDes f16">
					<h4>任务描述</h4>
					<div class="reportContext p20 ">${cmission.requirement}</div>
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
									<s:form action="saveGroupScore" enctype="multipart/form-data"
										method="post">
										<s:token />
										<input type="hidden" name="courseid"
											value="<s:property value="courseid" />" />
										<input type="hidden" name="cmgroupid"
											value="<s:property value="cmgroupid" />" />
										<input type="hidden" name="cmissionid"
											value="<s:property value="cmissionid" />" />
											<div class="clear"></div>
											<p class="fl">
											<span class="mr10 w80"><span><s:property
														value="cmgroupleader.name" />:</span><small class="leader">组长</small></span>
											
											<span>
												<input type="text" class="w100"
													id="<s:property value='cmgroupleader.id' />"
													value="<s:property value="cmgroupleader.credit" />"
													name="cmScoreList[0]"> <input type="hidden"
													value="<s:property value="cmgroupleader.id" />"
													name="cmStudentIDList[0]">
											</span>
										</p>
										
										<div class="fr" id="descriptionBox mb10">
												<span class="mr10 w100 fl">备注：</span>
												<textarea class="w800 t2 h50" name="cmDescriptionList[0]" placeholder="请输入90字以内"
													id="editor"><s:property value="cmcredit4leader.description" /></textarea>
										</div>
										<!-- 组员评分 -->

										<s:iterator value="cmstudentListDetailView"
												status="sts">
													<div class="clear"></div>
													<p class="fl mt10">
													<span class="mr10 w80 fl"> <s:property
																value="name" />: </span>
														<input type="text" id="<s:property value="id" />"
															value="<s:property value="credit" />"
															name="cmScoreList[<s:property value="#sts.count" />]">
														<input type="hidden" value="<s:property value="id" />"
															name="cmStudentIDList[<s:property value="#sts.count" />]">
													</p>
													<div class="fr" id="dexcriptionBox">
														<span class="mr10 w80 fl">备注：</span>
														<textarea class="w800 h50 t2" placeholder="请输入90字以内"
															name="cmDescriptionList[<s:property value="#sts.count" />]"
															id="editor">${requestScope.cmcreditListDetailView[sts.count-1].description}</textarea>
													</div>
											</s:iterator> 
										<div id="reportMarkButton" class="h50 f16 tc mt100">
											<div class="clear"></div>
											<a
												href="<s:property value="%{viewGroupMissionScoreUrl}"/>?cmissionid=<s:property value="cmissionid"/>&courseid=<s:property value="courseid"/>">
												<input type="button" class="btn blue" value="取&nbsp消">
											</a> <input type="submit" class="btn green" value="提&nbsp交">
										</div>
									</s:form>
								</div>
							</div>
						</div>
			</div>


		</div>
		<div class="clear mb50"></div>
		<br>
	</div>
</div>

<%@ include file="../footer.jsp"%>
<script src="js/jquery-1.8.3.min.js"></script>
<script src="js/flexpaper/flexpaper.js"></script>
<script src="js/flexpaper/flexpaper_handlers.js"></script>
<script src="js/common.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		var editor = new Simditor({
			textarea : $('#editor')

		});
		editor.sync();
	});
</script>
