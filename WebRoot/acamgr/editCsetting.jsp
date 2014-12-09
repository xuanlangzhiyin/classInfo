<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<s:url id="viewCsettingUrl" action="viewCsetting" />
<div id="container">
	<div class="white w1200 bc">
		<div class="w1060 bc">
		    
			<div class="cb"></div>

			<div class="main-boxes">
				<div class="main-box">
					<h2 class="boxTitle active" onclick="slideBox(this);">基本信息</h2>
					<div class="boxContext">
						<s:form action="saveCsetting" method="post">
							<input type="hidden" name="csettingid"
								value="<s:property value="csettingid"/>" />
							<input type="hidden" name="csetting.id"
								value="<s:property value="csettingid"/>" />
							<div id="editBox4intro" class="m30 boxForm f16 ">
								<p>
									<span class="mr20 w80">课程编号：</span><span><s:property
											value="csettingDetailView.code" />
									</span>
								</p>
								<p>
									<span class="mr20 w80">课程名称：</span><input class="f14 pl10"
										name="csetting.name"
										value="<s:property value="csettingDetailView.name"/>">
								</p>

								<!-- 修改： -->
								<p>
									<span class="mr20 w80">课程性质：</span> <select class="f14 pl10"
										name="csetting.type">

										<!-- 修改 -->
										<option value="必修"
											<s:if test = "csettingDetailView.type=='必修'"> selected="selected"</s:if>>必修</option>
										<option value="选修"
											<s:if test = "csettingDetailView.type=='选修'"> selected="selected"</s:if>>选修</option>



										<!-- 修改 -->

									</select>

								</p>

								<p>
									<span class="mr20 w80">课程类型：</span> <select class="f14 pl10"
										name="csetting.ccategoryid">

										<!-- 修改 -->

										<s:iterator value="ccategories" status="sts">

											<option value='<s:property value="id"/>'
												<s:if test = "id==csettingDetailView.ccategoryid"> selected="selceted"</s:if>>
												<s:property value="name" />
											</option>
										</s:iterator>

										<!-- 修改 -->

									</select>
								</p>
								<input type="hidden"
									value="<s:property value="csettingDetailView.majorid"/>"
									name="majorid" />
								<div class="tc h50">
									<a
										href="<s:property value="%{viewCsettingUrl}"/>?majorid=<s:property value="csettingDetailView.majorid"/>
									&csettingid=<s:property value="csettingDetailView.id"/>">
									<input type="button" class="btn blue" value="取&nbsp消"></a>
									<input type="submit" class="btn green" value="提&nbsp交">
								</div>
							</div>
						</s:form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="clear pb50"></div>
<%@ include file="../footer.jsp"%>
