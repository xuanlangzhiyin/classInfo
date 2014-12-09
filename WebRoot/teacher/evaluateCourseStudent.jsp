<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>

<div id="container">
	<div class="white w1200 bc">
		<div class="w1060 bc">
			<div class="main-boxes">
				<div class="main-box">  
					<h2 class="boxTitle active" onclick="slideBox(this);">课程信息</h2>
					<div class="boxContext">
						<div class="m30 boxForm f16">
							<p>
								<span class="mr20 w80">课程编号：</span><span><s:property
										value="courseDetailView.code" /> </span>
  
							</p>
							<p>
								<span class="mr20 w80">课程名称：</span> <span><s:property
										value="courseDetailView.name" /> </span>

							</p>
							<p>
								<span class="mr20 w80">课程类型：</span> <span><s:property
										value="courseDetailView.type" /> </span>

							</p>
							<p>
								<span class="mr20 w80">学年：</span> <span><s:property
										value="courseDetailView.year" /> </span>

							</p>
							<p>
								<span class="mr20 w80">学期：</span> <span><s:property
										value="courseDetailView.term" /> </span>
							<p>
								<span class="mr20 w80 tr">开课老师：</span> <span> <s:property
										value="mainCteacher.code" />-<s:property
										value="mainCteacher.name" /> </span>
							</p>
							<span> <s:iterator value="cteachers" status="sts">
									<p>
										<span class="mr20 w80 tr"></span> <span> <s:property
												value="code" />-<s:property value="name" /> </span>
									</p>
								</s:iterator> </span>

							<p>
								<span class="mr20 w80">课程简介：</span>
							</p>
							<div class="formText descriptionBox f16 t2 ">
								${courseDetailView.description}</div>

						</div>
					</div>
				</div>
				<div class="main-box">
					<h2 class="boxTitle active" onclick="slideBox(this);">学生信息</h2>
					<div class="boxContext">
						<div class="m30 boxForm f16">
							<p>
								<span class="mr20 w80">学生姓名：</span> <span><s:property
										value="cstudentDetailView.name" /> </span>
							</p>
							<p>
								<span class="mr20 w80">学号：</span> <span><s:property
										value="cstudentDetailView.code" /> </span>
							</p>
							<p>
								<span class="mr20 w80">年级：</span> <span><s:property
										value="cstudentDetailView.grade" />级</span>
							</p>
							<p>
								<span class="mr20 w80">行政班：</span> <span><s:property
										value="cstudentDetailView.stuclass" /> </span>
							</p>
						</div>
					</div>
				</div>
				<div class="main-box">
					<h2 class="boxTitle active" onclick="slideBox(this);">任务情况</h2>
					<div class="boxContext">
						<div class="m30 w1000 bc listTable f16">
							<table class="bc cgray" cellspacing="0" cellpadding="0"
								width="960">
								<tbody>
									<tr>
										<th width="200">任务名称</th>
										<th width="100">任务类型</th>
										<th width="100">得分</th>
										<th width="660">备注</th>
									</tr>
									<s:iterator value="cmcreditListView" status="sts">

										<tr>
											<td><s:property value="cmissionName" />
											</td>
											<td><s:property value="cmissionType" />
											</td>
											<td><s:property value="credit" />
											</td>
											<td><s:property value="description" />
											</td>

										</tr>

										<!--
										<span class="mr20 w80 tr"></span>
										<span> <s:property value="code" />-<s:property
												value="name" /> </span>  -->

									</s:iterator>


									<s:iterator value="unMarkedCmissions" status="sts">
										<tr>
											<td><s:property value="name" />
											</td>
											<td><s:property value="mtype" />
											</td>
											<td>未评分</td>
											<td>未备注</td>
										</tr>
									</s:iterator>


								</tbody>
							</table>
						</div>
					</div>
				</div>

				<!-- 课程总评 -->

				<div class="main-box">
					<h2 class="boxTitle active" onclick="slideBox(this);">课程总评</h2>

					<div class="boxContext">




						<!-- saveEvaluationSucess -->
						<div class="m30 w980 bc  f14  ">
							<div id="saveEvaluationSucess" class="message none">
								<div class="clear"></div>
								<!-- error -->
								<div class="messageBox success">
									<span class="fl messageLogo"></span> <span class="close fr"
										onclick="closeMessage(this)"></span>
									<h4>评分成功</h4>
									<!-- <p class="t5">
							</p> -->


								</div>

							</div>
						</div>

						<!-- /saveEvaluationSucess -->
						<div id="onload" class="m30 w1000 bc tc mt100 mb100 hide">
							<img src="<s:url value="images/loading.gif"/>">
						</div>
						<div id="viewBox4index" class="m30 w1000 bc listTable f14 white">
							<div id="viewBox4index-tables">
　　　　　　　　　　　　　　　　　　　　　　<div class="m30 boxForm f16">
	                        <s:if test="cevaluation== null">
								<p>
								<span class="mr20 w80">卷面成绩：</span> 
								<span>未评分</span>
								
							</p>
							<p>
								<span class="mr20 w80">平时考勤：</span> 
								<span>未评分</span>

							</p>
							<p>
								<span class="mr20 w80">课程总评：</span> 
									<span>未评分</span>
							</p>								
							</s:if>
							<s:if test="cevaluation != null">
							<p>
								<span class="mr20 w80">卷面成绩：</span> 
								<span><s:property value='cevaluation.examcredit' /></span>
							</p>
							<p>
								<span class="mr20 w80">平时考勤：</span> 
								<span><s:property value='cevaluation.attendance' /></span>

							</p>
							<p>
								<span class="mr20 w80">课程总评：</span> 
								<span><s:property value='cevaluation.credit' /></span>
							</p>
							</s:if>
　　　　　　　　　　　　　　　　　　　　</div>
					 		
							<div class="tc h50">
								<input type="button" class="btn green" value="修&nbsp改"
									onclick="showEditBox4Index()">
							</div>
						</div>
						</div>
						<s:form action="saveCevaluation" enctype="multipart/form-data"
										method="post">
										<s:token />
										<input type="hidden" name="courseid"
											value="<s:property value="courseid" />" />
										<input type="hidden" name="studentid"
											value="<s:property value="studentid" />" />
										<input type="hidden" name="cstudentid"
											value="<s:property value="cstudentDetailView.id" />" />
										<input type="hidden" name="cevaluation.courseid"
											value="<s:property value="courseid" />" />
										<input type="hidden" name="cevaluation.studentid"
											value="<s:property value="studentid" />" />
						<div id="editBox4index" class="m30 tabBox none">
							
							<div class="tabContext f14">
                          　                                                             <div class="m30 boxForm f17">
                               <p >
										<span class="mr20 w80">卷面成绩： </span> <span><input
											class="w100 " type="text"
											name="cevaluation.examcredit">
										</span>
								</p>
								<p >
										<span class="mr20 w80">考勤成绩 ：</span> <span><input
											class="w100 " type="text"								
											name="cevaluation.attendance">
										</span>
								</p>
								<p >
										<span class="mr20 w80">课程总评： </span> <span><input
											class="w100 " type="text"										
											name="cevaluation.credit">
										</span>
								</p>
						       </div>
								<div class="tc h50">
									<input type="button" class="btn blue" value="取&nbsp消"
										onclick="showViewBox4Index()"> 
								    <input type="submit" class="btn green" value="提&nbsp交">
								</div>
							</div>
						</div>
						</s:form>
                       </div>
					<div class="clear mb50"></div>
					<br>
				</div>
			</div>
		</div>
	</div>
</div>
<%@ include file="../footer.jsp"%>
<script type="text/javascript">
	function showEditBox4Intro() {
		$("#viewBox4intro").fadeOut(500, function() {
			$("#editBox4intro").fadeIn();
		});
	}
	function showEditBox4Index() {
		$("#viewBox4index").fadeOut(500, function() {
			$("#editBox4index").fadeIn();
		});
	}
	
	function showViewBox4Index() {

		$("#editBox4index").fadeOut(500, function() {
			$("#indexError").fadeIn();
		});

		$("#editBox4index").fadeOut(500, function() {
			$("#viewBox4index").fadeIn();
		});

	}

</script>