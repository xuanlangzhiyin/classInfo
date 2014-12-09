<%@page import="com.wuhei.cms.model.Cmission"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>

<div id="container">
	<div class="w1200 white bc">
		<div class="w1060 bc">

			<div class="main-boxes">
				<div class="main-box">
					<h2 class="boxTitle active" onclick="slideBox(this);">基本信息</h2>
					<div class="boxContext">
						<s:form action="saveCourse4Acamgr" method="post">
							<div class="m30 boxForm f16" id="editBox4intro">
								<p>
									<span class="mr20 w80 tr">专业课程：</span><span><s:property
											value="courseDetailView.name" /> </span>
								</p>

								<p>
									<span class="mr20 w80 tr">学年：</span> <select id="selectYear"
										class="f14 pl10" name="course.year">
										<option value="2016-2017"
											<s:if test = "'2016-2017'==courseDetailView.year">selected="seleceted"</s:if>>2016-2017学年</option>
										<option value="2015-2016"
											<s:if test = "'2015-2016'==courseDetailView.year">selected="seleceted"</s:if>>2015-2016学年</option>
										<option value="2014-2015"
											<s:if test = "'2014-2015'==courseDetailView.year">selected="seleceted"</s:if>>2014-2015学年</option>
									</select>
								</p>
								<p style="display:none;">
									<input id="kk" type="text" name="course.id"
										value="<s:property value="courseDetailView.id" />" />
								</p>
								<p style="display:none;">
									<input type="text" name="courseid"
										value="<s:property value="courseDetailView.id" />" />
								</p>
								<p>
									<span class="mr20 w80 tr">学期：</span> <select id="selectTerm"
										class="f14 pl10" name="course.term">
										<option value="1"
											<s:if test = "1==courseDetailView.term">selected="seleceted"</s:if>>第一学期</option>
										<option value="2"
											<s:if test = "2==courseDetailView.term">selected="seleceted"</s:if>>第二学期</option>
									</select>
								</p>
								<p id="teacherp">
									<span class="mr20 w80 tr">开课教师：</span> <span> <select
										id="select4teacher" class="f14 pl10" m='search'
										name="course.teacherid">
											<s:iterator value="teachersList" status="sts">
												<option value="<s:property value="id" />"
													<s:if test = "id==mainCteacher.id">selected="selected"</s:if>>
													<s:property value="code" />
													-
													<s:property value="name" />
												</option>
											</s:iterator>

									</select> </span>
								</p>




								<s:iterator value="cteachers" status="status">
									<p>
										<span class="mr20 w80 tr"></span> <span> <select
											id="select4teacher<s:property value="#status.count-1" />"
											class="f14 pl10" m='search'
											name="teacherIDs[<s:property value="#status.count-1" />]">
												<s:iterator value="teachersList" status="sts">
													<option value="<s:property value="id" />"
														<s:if test = "id==cteachers[#status.count-1].id">selected="selected"</s:if>>
														<s:property value="code" />
														-
														<s:property value="name" />
													</option>
												</s:iterator>
										</select> </span> <span class="mr20 w20 tr"></span> <span
											onclick="deleteATeacher(this)"><img
											src="images/delete.png"> </span>
									</p>
								</s:iterator>


								<p id="moreteacher">
									<input type="button" class="add ml100 f14" value="更多老师"
										onclick="newteacher()"> <br /> <br />

								</p>



								<div class="tc h50 w500 bc">
									<a
										href="/classInfo/acamgr/viewCourse.action?courseid=<s:property value="courseDetailView.id" />"><input
										type="button" class="btn blue" value="返&nbsp回"> </a> <span
										class="w200 fr"> <input id="handin" type="submit"
										class="btn green" value="提&nbsp交"> </span>
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


<script>
	$(document)
			.ready(
					function() {

						//$("#select4teacher").selectseach();
						$("select[id^=select4teacher]").selectseach();

						
						$("#handin")
								.click(
										function() {

											var kkhtml = '\
 <input id="hideinput" type="hidden" value="" name="teacherIDs[0]">\
\
';

											if ($("select").length == 3) {

												$("#kk").after(kkhtml);

												var m = 0;
												$("#hideinput").attr("value",
														parseInt(m));
											}

										});

					});

	function newteacher() {

		if ($("select").length < 12) {
			var inputhtml = '\
	   <p>\
           <span class="mr20 w80 tr"></span>\
           <span></span>\
           <span class="mr20 w20 tr"></span>\
           <span class="pl40" onclick="deleteATeacher(this)"><img src="images/delete.png" </span>\
           </p>\
	   \
	   ';
			$("#moreteacher").before(inputhtml);
			$("#moreteacher").prev().children("span").eq(1).append(
					$("#select4teacher").clone(true));
			$("#moreteacher").prev().children("span").eq(1).children("select")
					.attr("name",
							"teacherIDs[" + ($("select").length - 4) + "]");
			$("#moreteacher").prev().children("span").eq(1).children("select")
					.attr("id",
							"select4teacher" + ($("select").length - 4) + "");
			$("#moreteacher").prev().children("span").eq(1).children("select")
					.children("option").removeAttr("selected");
			$("#moreteacher").prev().children("span").eq(1).children("select")
					.removeAttr("style");
			$("#moreteacher").prev().children("span").eq(1).children("select")
					.selectseach();

		}

	}

	function deleteATeacher(e) {
		$(e).parent().remove();

		var m = ($("select").length - 3);
		for ( var i = 0; i < m; i++) {
			$("select").eq(i + 3).attr("name", "teacherIDs[" + i + "]");

			$("select").eq(i + 3).attr("id", "select4teacher" + i + "");
		}
		$("select[id^=select4teacher]").next("div").remove();
		$("select[id^=select4teacher]").selectseach();
	}
</script>

<script type="text/javascript" charset="utf-8"
	src="<s:url value="js/acamgr/newCourse.js"/>">
	var csettingList = <s:property value="csettingDetailViews"/>;
	var test = "test123";
</script>

