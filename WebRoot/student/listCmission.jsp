<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.wuhei.cms.model.*"%>
<%@page import="com.opensymphony.xwork2.util.*"%>
<%@ include file="../header.jsp"%>

<s:url id="viewCmreport4StdUrl" action="viewCmreport4Std" />
<s:url id="editCmreport4StdUrl" action="editCmreport4Std" />
<div id="cotainer">  
	<div class="w1200 white bc">
		<div class="w1060 bc pb30">
		  
			<!-- noMission -->
			<s:if test="cmissionList.isEmpty()==true">
				<div class="message">
					<div class="clear"></div>
					<div class="messageBox info">
						<div class="messageTitle">
							<span class="fl messageLogo"></span><span class="close fr"
								onclick="closeMessage(this)"></span>
						</div>
						<div class="messageContent">
							<h4>该课程还没有小组任务</h4>
						</div>
					</div>
				</div>
			</s:if>


			<div class="main-boxes">
				<div class="main-box">
					<h2 onclick="slideBox(this);" class="boxTitle active">任务情况</h2>
					<div class="boxContext">

						<div class="m30 w1000 bc listTable f16 white">

							<s:if test="cmissionList.isEmpty()==false">
								<br>
								<table id="listCmissionTable" cellspacing="0" cellpadding="0"
									width="960" class="bc cgray">
									<thead>
										<tr>
											<th width="40" class="tc">序号</th>
											<th width="160" class="tc">任务名称</th>
											<th width="120" class="tc">任务类型</th>
											<th width="150" class="tc">截止时间</th>
											<th width="130" class="tc">提交/参与</th>
											<th width="300" class="tc">操作</th>
											<th width="150" class="tc">任务状态</th>
										</tr>
									</thead>
									<tbody>
										<s:iterator value="cmissionList" status="sts">
											<tr id="tr-<s:property value="id"/>">
												<td class="tc"><s:property value="id" />
												</td>
												<td class="tc"><s:property value="name" />
												</td>
												<td class="tc"><s:property value="mtype" />
												</td>
												<td class="tc"><s:property value="deadline" />
												</td>
												<td class="tc">
												<s:if test="reportedStudentNumber==null">
												0
												</s:if>
												<s:if test="reportedStudentNumber!=null">
												<s:property	value="reportedStudentNumber" />
												</s:if>
												/<s:property value="scount" />
												</td>
												<td class="tc">
												
												<a href=" <s:property value="%{viewCmreport4StdUrl}"/>?cmissionid=<s:property value="id"/>">
												查看												
												</a>
												</td>
												<td class="tc">
												<%
														String temp = request.getAttribute("isactive").toString();  
														if(temp.equals("可提交"))
														out.println("任务进行中");
														else
														out.println("任务已关闭");
														 %>
												</td>
										
												
											</tr>
										</s:iterator>
									</tbody>
								</table>
								<br>
							</s:if>
						</div>
					</div>
				</div>
			</div>
			<br>
			<div class="clear mb50"></div>
		</div>
	</div>
</div>
<%@ include file="../footer.jsp"%>

<script src="js/jquery-1.8.3.min.js"></script>
<script src="js/common.js"></script>
<script type="text/javascript" charset="utf-8"
	src="<s:url value="js/tablePlugin/jquery.tablesorter.js"/>"></script>
<script type="text/javascript" charset="utf-8"
	src="<s:url value="js/tablePlugin/jquery.tablesorter.pager.js"/>"></script>
<script type="text/javascript" charset="utf-8"
	src="<s:url value="js/tablePlugin/jquery.tablesorter.widgets.js"/>"></script>

<script type="text/javascript" charset="utf-8"
	src="<s:url value="js/teacher/listCourse.js"/>"></script>

<script src="js/jquery.tablesorter.js"></script>
<script src="js/jquery.tablesorter.pager.js"></script>
<script src="js/jquery.tablesorter.widgets.js"></script>


<script id="js">
	$(function() {

		// define pager options
		var pagerOptions = {
			// target the pager markup - see the HTML block below
			container : $(".studentTablePager"),
			// output string - default is '{page}/{totalPages}'; possible variables: {page}, {totalPages}, {startRow}, {endRow} and {totalRows}
			output : '{page}/{totalPages}',
			// if true, the table will remain the same height no matter how many records are displayed. The space is made up by an empty
			// table row set to a height to compensate; default is false
			fixedHeight : true,
			// remove rows from the table to speed up the sort of large tables.
			// setting this to false, only hides the non-visible rows; needed if you plan to add/remove rows with the pager enabled.
			removeRows : false,
			// go to page selector - select dropdown that sets the current page
			cssGoto : '.gotoPage'
		};

		// Initialize tablesorter
		// ***********************
		$("#studentTable").tablesorter({
			theme : 'blue',
			headerTemplate : '{content} {icon}', // new in v2.7. Needed to add the bootstrap icon!
			widthFixed : true,
			widgets : [ 'zebra', 'filter' ]
		})

		// initialize the pager plugin
		// ****************************
		.tablesorterPager(pagerOptions);

		// Destroy pager / Restore pager
		// **************
		$('button:contains(Destroy)').click(function() {
			// Exterminate, annhilate, destroy! http://www.youtube.com/watch?v=LOqn8FxuyFs
			var $t = $(this);
			if (/Destroy/.test($t.text())) {
				$('#studentTable').trigger('destroy.pager');
				$t.text('Restore Pager');
			} else {
				$('#studentTable').tablesorterPager(pagerOptions);
				$t.text('Destroy Pager');
			}
			return false;
		});

		// Disable / Enable
		// **************
		$('.toggle').click(
				function() {
					var mode = /Disable/.test($(this).text());
					$('#studentTable').trigger(
							(mode ? 'disable' : 'enable') + '.pager');
					$(this).text((mode ? 'Enable' : 'Disable') + 'Pager');
					return false;
				});
		$('#studentTable').bind('pagerChange', function() {
			// pager automatically enables when table is sorted.
			$('.toggle').text('Disable');
		});

	});
</script>
<script>
	//切换没成功
	function showIntroEditBox4teacher() {
		$("#viewBoxintro4teacher").fadeOut(500, function() {
			$("#editBoxintro4teacher").fadeIn();
		});
	}
	function showViewBox4Intro() {
		$("#editBoxintro4teacher").fadeOut(500, function() {
			$("#viewBoxintro4teacher").fadeIn();
		});
	}
</script>
</body>
</html>