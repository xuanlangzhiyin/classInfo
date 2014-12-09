
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<s:url id="editCsettingUrl" action="editCsetting" />
<s:url id="viewCeindexUrl" action="viewCeindex" />

<div id="container">
	<div class="white w1200 bc">
		<div class="w1060 bc">
			<div class="cb"></div>
  
			<div class="main-boxes">				
				<div class="main-box">

					<h2 class="boxTitle active" onclick="slideBox(this);">基本信息</h2>
					<div class="boxContext">
						<div id="viewBox4intro" class="m30 boxForm f16">
							<p>
								<span class="mr20 w80">课程编号：</span>
								<s:property value="csettingDetailView.code" />
							</p>
							<p>
								<span class="mr20 w80">课程名称：</span>
								<s:property value="csettingDetailView.name" />
							</p>

							<p>
								<span class="mr20 w80">课程性质：</span>
								<s:property value="csettingDetailView.type" />
							</p>

							<p>
								<span class="mr20 w80">课程类型：</span>
								<s:property value="csettingDetailView.ccategoryname" />
							</p>

							<!-- <p>
								<span class="mr20 w80">课程简介：</span><span
									class="formText f16 t2"><s:property value="csettingDetailView.description"/></span>
							</p>
							<p>
								<span class="mr20 w80">附件：</span><a href=""><s:property value="csettingDetailView.attachname"/></a>
							</p> -->

									
							</div>
						</div>
					</div>
				</div>
				<div class="main-box">
					<h2 class="boxTitle active" onclick="slideBox(this);">课程指标</h2>
					<div class="boxContext">
						<div id="onload" class="m30 w1000 bc tc mt100 mb100 hide">
							<img src="<s:url value="images/loading.gif"/>">
						</div>				
<div id=indexError class="m30 w1000 bc none f14  ">
						
						
						<div class="message">
						<div class="clear"></div>
						<!-- error -->
						
						
						<div class="messageBox warn">
							<span class="fl messageLogo"></span> <span class="close fr"
								onclick="closeCeindexMessage(this)"></span>
							<h4>该课程暂无指标，点击增加以添加指标</h4>
						<!-- <p class="t5">
							</p> -->
							
						
						</div>
                        </div>
                        
                            <div class="tc h50">
								<input type="button" class="btn green" value="增&nbsp加"
									onclick="showEditBox4AddIndex()">
							</div>
                        
  </div>				
						<div id="viewBox4index" class="m30 w1000 bc listTable f14 white">
							<div id="viewBox4index-tables">

								<br>
								<div class="abox cGrey p20">
									<h3 class="f20">
										<strong>知识架构 Knowledge Requirement </strong>
									</h3>
									<div class="uline"></div>
									<table class="bc" cellspacing="0" cellpadding="0" width="960">
										<tbody>
											<tr>
												<th width="900">K.知识架构</th>
												<th width="100">相关系数</th>
											</tr>
											<tr>
												<td>KD.掌握扎实的软件基础理论、软件技术和软件项目管理知识，了解软件专业的前沿发展现状和趋势</td>
												<td>5</td>
											</tr>
											<tr>
												<td>KH.掌握扎实的软件基础理论、软件技术和软件项目管理知识</td>
												<td>5</td>
											</tr>
										</tbody>
									</table>
								</div>
								<br>

								<div class="abox cGrey p20">
									<h3 class="f20">
										<strong>能力要求 Capability Requirement</strong>
									</h3>
									<div class="uline"></div>
									<table class="bc" cellspacing="0" cellpadding="0" width="960">
										<tbody>
											<tr>
												<th width="900">CA.自然科学基础与问题求解能力</th>
												<th width="100">相关系数</th>
											</tr>
											<tr>
												<td>CA3.客观问题分析与求解能力</td>
												<td>5</td>
											</tr>
										</tbody>
									</table>
									<br>
									<table class="bc" cellspacing="0" cellpadding="0" width="960">
										<tbody>
											<tr>
												<th width="900">CA.自然科学基础与问题求解能力</th>
												<th width="100">相关系数</th>
											</tr>
											<tr>
												<td>CA3.客观问题分析与求解能力</td>
												<td>5</td>
											</tr>
										</tbody>
									</table>
									<br>
									<table class="bc" cellspacing="0" cellpadding="0" width="960">
										<tbody>
											<tr>
												<th width="900">CA.自然科学基础与问题求解能力</th>
												<th width="100">相关系数</th>
											</tr>
											<tr>
												<td>CA3.客观问题分析与求解能力</td>
												<td>5</td>
											</tr>
										</tbody>
									</table>
								</div>
								<br>
								<div class="abox cGrey p20">
									<h3 class="f20">
										<strong>素质要求 Quality Requirement</strong>
									</h3>
									<div class="uline"></div>
									<table class="bc" cellspacing="0" cellpadding="0" width="960">
										<tbody>
											<tr>
												<th width="900">Q.素质要求</th>
												<th width="100">相关系数</th>
											</tr>
											<tr>
												<td>QD.掌握扎实的软件基础理论、软件技术和软件项目管理知识，了解软件专业的前沿发展现状和趋势</td>
												<td>5</td>
											</tr>
											<tr>
												<td>QH.掌握扎实的软件基础理论、软件技术和软件项目管理知识</td>
												<td>5</td>
											</tr>
										</tbody>
									</table>
								</div>
								<br>


							</div>

						</div>

					</div>
				</div>

			</div>
			<div class="clear pb50"></div>
		</div>
		<br>
	</div>

<%@ include file="../footer.jsp"%>

<script type="text/javascript">

	loadIndexTree();

	function showViewBox4Intro() {
		$("#editBox4intro").fadeOut(500,function(){
			$("#viewBox4intro").fadeIn();
		});
	}
	function showViewBox4Index() {
		$("#editBox4index").fadeOut(500,function(){
			$("#viewBox4index").fadeIn();
		});
	}
	
	function loadIndexTree() {
	
		var csettingid="<s:property value="csettingDetailView.id"/>";
		var major="<s:property value="majorid"/>";
		var inputdata='{"csettingid" : '+csettingid + ',"majorid":'+major+'}';

		
		$.ajax({
					cache : true,
					type : "POST",
					url : "<s:url value="/tch/viewCeindex.action"/>",//此处不能使用单引号代替双引号
					contentType : 'application/json; charset=utf-8',
					data : inputdata,
					error : function(request) {
						alert("Ajax 出错");
					},

					success : function(results) {
					     if(results==null)
					     {
					      $("#indexError").removeClass("none");
					      $("#viewBox4index").addClass("none");     					           
					     }
					     else{
						console.log(results);
						writeTreeToDom(results);
						}
					}
				});
	}
	
		
	/*
	 *	将指标树写入DOM并更新指标表格中的分值
	 *  参数 results ： 指标树的JSON
	 */
	function writeTreeToDom(results){
							
		var htmlContext = '';
		for ( var i = 0; i < results.result.length; i++) {

			if (results.result[i].children[0].isfinal == 1) {
				//alert(results.result[i]["name"]);//0级指标ID
				htmlContext = htmlContext+ '\
				<br>\
				<div class="abox cGrey p20">\
					<h3 class="f20">\
						<strong>'+results.result[i].wholename+'</strong>\
					</h3>\
					<div class="uline"></div>\
					<table class="bc" cellspacing="0" cellpadding="0" width="960">\
						<tbody>\
							<tr>\
								<th width="900">'+results.result[i].wholename+'</th>\
								<th width="100">相关系数</th>\
							</tr>';
				for ( var j = 0; j < results.result[i].children.length; j++) {
					//一级指标ID
					var formId=".markForm[fid='"+results.result[i].children[j].name+"']";
					var inputV="input[value='"+results.result[i].children[j].value+"']";
					var ceid =results.result[i].children[j].ceid;
					$(formId).eq(0).children(inputV).eq(0).attr("checked","checked");
					$(formId).eq(0).attr("ceid",ceid);
					htmlContext = htmlContext+ '\
						<tr>\
							<td class="t2 f14">'+results.result[i].children[j].wholename+'</td>\
							<td>'+results.result[i].children[j].value+'</td>\
						</tr>';
				}
				htmlContext = htmlContext+ '</tbody></table></div>';
				
			} else {
				
				//alert(results.result[i]["name"]);
				htmlContext = htmlContext+ '\
				<br>\
				<div class="abox cGrey p20">\
					<h3 class="f20">\
						<strong>'+results.result[i].wholename+'</strong>\
					</h3>\
					<div class="uline"></div>';
				for ( var j = 0; j < results.result[i].children.length; j++) {
				htmlContext = htmlContext+ '\<table class="bc" cellspacing="0" cellpadding="0" width="960">\
						<tbody>\
							<tr>\
								<th width="900">'+results.result[i].children[j].wholename+'</th>\
								<th width="100">相关系数</th>\
							</tr>';
					//一级指标ID
					if (results.result[i].children[j].children instanceof Array) {
						for ( var h = 0; h < results.result[i].children[j].children.length; h++) {

							//二级指标ID
							var formId=".markForm[sid='"+results.result[i].children[j].children[h].name+"']";
							var inputV="input[value='"+results.result[i].children[j].children[h].value+"']";
							var ceid =results.result[i].children[j].children[h].ceid;
							$(formId).eq(0).children(inputV).eq(0).attr("checked","checked");
							$(formId).eq(0).attr("ceid",ceid);
							htmlContext = htmlContext+ '\
						<tr>\
							<td class="t2 f14">'+results.result[i].children[j].children[h].wholename+'</td>\
							<td>'+results.result[i].children[j].children[h].value+'</td>\
						</tr>';
						}
					} else {
						//2级指标ID
						var formId=".markForm[sid='"+results.result[i].children[j].children.name+"']";
						var inputV="input[value='"+results.result[i].children[j].children.value+"']";
						var ceid =results.result[i].children[j].ceid;
						$(formId).eq(0).children(inputV).eq(0).attr("checked","checked");
						$(formId).eq(0).attr("ceid",ceid);
						htmlContext = htmlContext+ '\
						<tr>\
							<td class="t2 f14">'+results.result[i].children[j].children.wholename+'</td>\
							<td>'+results.result[i].children[j].children.value+'</td>\
						</tr>';
					}
					htmlContext = htmlContext+ '</tbody></table><br/>';

				};
				htmlContext = htmlContext+ '</div>';
			}

		};
		$("#viewBox4index-tables").html("");
		$("#viewBox4index-tables").html(htmlContext);
		tableGap();
		//getIndexList();
	}
	
	
</script>