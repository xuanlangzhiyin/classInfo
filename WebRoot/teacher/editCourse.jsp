<%@page import="com.wuhei.cms.model.Cmission"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>

<s:url id="saveCourseUrl" action="saveCourse" />
<s:url id="viewCourseUrl" action="viewCourse" />
<div id="container">
	<div class="w1200 white bc">
		<div class="w1060 bc">
			<div class="cb"></div>

			<div class="main-boxes">
				<div class="main-box">
					<h2 class="boxTitle active" onclick="slideBox(this);">基本信息</h2>
					<div class="boxContext">
					<s:form action="saveCourse" enctype="multipart/form-data" method="post">
						<input type="hidden" name="courseid" value="<s:property value="courseid" />"/>
						<input type="hidden" name="csettingid" value="<s:property value="csettingid" />"/>
					    <input type="hidden" name="course.id" value="<s:property value="courseDetailView.id" />"/>
						<div class="m30 boxForm f16" id="editBox4intro">
						<p>
								<span class="mr20 w80 tr">课程编号：</span><span><s:property
										value="courseDetailView.code" /> </span>
							</p>
						<p>
								<span class="mr20 w80 tr">课程名称：</span><span><s:property
										value="courseDetailView.name" /> </span>
							</p>
							
						<p>
								<span class="mr20 w80 tr">课程类型：</span><span><s:property
										value="courseDetailView.ccategoryname" /> </span>
							</p>
							
						<p>
								<span class="mr20 w80 tr">开课教师：</span><span><s:property
										value="mainCteacher.code" />-</span>
									<span><s:property
										value="mainCteacher.name" /></span>
							</p>
							
								<span><s:iterator value="cteachers" status="sts">
									<p>
										<span class="mr20 w80 tr"></span><span> <s:property
												value="code" />-<s:property value="name" /> </span>
									</p>
									
								</s:iterator> </span>
			                     
						<p>
								<span class="mr20 w80 tl">学年：</span><span><s:property
									value="courseDetailView.year" /> </span> 
							</p>
							
						<p>
								<span class="mr20 w80 tl">学期：</span>
								<s:if test = "courseDetailView.term==1"><span>第一学期</span></s:if>
								<s:if test = "courseDetailView.term==2"><span>·第二学期</span></s:if>
								 
							</p>
						
						<p class="hide">
								<span class="mr20 w80">课程简介：</span>
								
								<span class="formText f16 t2">
										<textarea name="course.description" id="editor" cols="30" rows="10" class="f14 t2 p10 m0">
											<!--<s:property value="courseDetailView.description" />-->
											暂不实现该功能
										</textarea></span>
							</p>
							
						<p>
								<span class="mr20 w100 ">附件：</span><input id="long-noBorder"
									class="noBorder" type="file" name="file">
									
							</p>
							<s:if test = "courseDetailView.oldname !=null">
								<p>
								<span class="mr20 w100">已上传附件：</span><s:property value="courseDetailView.oldname" />
								</p>
							</s:if>
						<div class="tc h50">
				                  <a href="<s:property value="%{viewCourseUrl}"/>?courseid=<s:property value="courseDetailView.id"/>
									&majorid=<s:property value="courseDetailView.majorid"/>&csettingid=<s:property value="courseDetailView.csettingid"/>#cView     "> 
									<input type="button" class="btn blue" value="取&nbsp消" ></a> 
									<input type="submit" class="btn green" value="提&nbsp交">
						</div>
						</div>
						
					</s:form>
					</div>
					
					
				</div>

			</div>
			<div class="clear pb50"></div>
		</div>
	</div>
</div>


<%@ include file="../footer.jsp"%>




