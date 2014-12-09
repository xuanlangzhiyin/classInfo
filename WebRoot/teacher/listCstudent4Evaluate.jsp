<%@page import="com.wuhei.cms.model.Cmission"%>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<s:url id="evaluateCourseStudentUrl" action="evaluateCourseStudent" />
<s:url id="listCmissionUrl" action="listCmission" />
<s:url id="viewCourseUrl" action="viewCourse" />
<s:url id="listCourseStudentUrl" action="listCourseStudent"/>

<div id="container">
		<div class="w1200 white bc">
			<div class="w1060 bc pb30">	
				<div class="main-boxes">
					  
					<div class="main-box ">
						<h2 onclick="slideBox(this);" class="boxTitle active">选课学生</h2>
						<div class="boxContext ">
								<div class="m30 f16">
									<div class="formTable-context white">
	
								 	<div class="listTable w1000 bc cgray">

							 			<br>

										<table id="studentTable" class="bc f16" cellspacing="0" cellpadding="0" width="960">
											<thead>

												<tr>
													<th width="40">序号</th>
													<th width="180">学号</th>
													<th width="250">姓名</th>
													<th width="80">性别</th>
													<th width="120">年级</th>
													<th width="150">班级</th>
													<th width="80">总评</th>
													<th width="80">操作</th>
												</tr>
											</thead>
											<tfoot>

												<tr>
												
													<th width="40">序号</th>
													<th width="180">学号</th>
													<th width="250">姓名</th>
													<th width="80">性别</th>
													<th width="120">年级</th>
													<th width="150">班级</th>
													<th width="80">总评</th>
													<th width="80">操作</th>
													
												</tr>
											</tfoot>
											<tbody>
											
											<s:iterator value="cstudentListViews" id="coursetable"
														status="sts">

														<tr id="tr-<s:property value="id"/>">

															<td class="tc" name="cstudentIndex"><s:property
																	value="#sts.count" />
															</td>
															<td><s:property value="code" />
															</td>
															<td><s:property value="name" />
															</td>
															<td><s:property value="sex" />
															</td>
															<td><s:property value="grade" />
															</td>
															<td><s:property value="stuclass" />
															</td>
															<td><s:property value="isEvaluation" />
															</td>
															<td>
															<a href="<s:property value="%{evaluateCourseStudentUrl}"/>?courseid=<s:property value="courseid"/>&cstudentid=<s:property value="id"/>">评分</a>
															</td>
													</s:iterator>

										</tbody>
										</table>
										 
										<div class="pager studentTablePager fr">
											<span class="fl">每页显示数量：</span> <select
													class="pagesize  w50 fl">
													<option selected="selected" value="10">10</option>
													<option value="20">20</option>
													<option value="30">30</option>
													<option value="40">40</option>
												</select> <span><img src="images/first.png"
													class="pageButton first fl" alt="First" title="First page" />
												</span> <span><img src="images/prev.png"
													class="pageButton prev fl" alt="Prev" title="Previous page" />
												</span> <span class="pagedisplay f16 cGray fl"></span>
												<!-- this can be any element, including an input -->
												<span><img src="images/next.png"
													class="pageButton next fl" alt="Next" title="Next page" />
												</span> <span><img src="images/last.png"
													class="pageButton last fl" alt="Last" title="Last page" />
												</span> <span class="fl">跳转: </span><select class="gotoPage w50 fl"></select>
										</div>
											<div class="clear pb10"></div>
										
										</div>


			
									</div>
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

   	<script type="text/javascript">


	$(function(){
	// define pager options
	var pagerOptions = {
		// target the pager markup - see the HTML block below
		container: $(".studentTablePager"),
		// output string - default is '{page}/{totalPages}'; possible variables: {page}, {totalPages}, {startRow}, {endRow} and {totalRows}
		output: '{page}/{totalPages}',
		// if true, the table will remain the same height no matter how many records are displayed. The space is made up by an empty
		// table row set to a height to compensate; default is false
		fixedHeight: true,
		// remove rows from the table to speed up the sort of large tables.
		// setting this to false, only hides the non-visible rows; needed if you plan to add/remove rows with the pager enabled.
		removeRows: false,
		// go to page selector - select dropdown that sets the current page
		cssGoto: '.gotoPage'
	};

	// Initialize tablesorter
	// ***********************
	$("#studentTable")
		.tablesorter({
			theme: 'blue',
			headerTemplate : '{content} {icon}', // new in v2.7. Needed to add the bootstrap icon!
			widthFixed: true,
			widgets: ['zebra', 'filter']
		})

		// initialize the pager plugin
		// ****************************
		.tablesorterPager(pagerOptions);

		// Destroy pager / Restore pager
		// **************
		$('button:contains(Destroy)').click(function(){
			// Exterminate, annhilate, destroy! http://www.youtube.com/watch?v=LOqn8FxuyFs
			var $t = $(this);
			if (/Destroy/.test( $t.text() )){
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
		$('.toggle').click(function(){
			var mode = /Disable/.test( $(this).text() );
			$('#studentTable').trigger( (mode ? 'disable' : 'enable') + '.pager');
			$(this).text( (mode ? 'Enable' : 'Disable') + 'Pager');
			return false;
		});
		$('#studentTable').bind('pagerChange', function(){
			// pager automatically enables when table is sorted.
			$('.toggle').text('Disable');
		});

});</script>
<script>
 $(document).ready(function(){
 	changeTab();
 });
</script>
