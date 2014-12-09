<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<s:url id="editCsettingUrl" action="editCsetting" />
   
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

							<div class="tc h50">
								<a
									href="<s:property value="%{editCsettingUrl}"/>?csettingid=<s:property value="csettingDetailView.id"/>"><input
									type="button" value="编辑专业课程" class="btn green"
									onclick="showEditBox4Intro();">
								</a> 
									
							</div>
						</div>
					</div>
				</div>
				

			</div>
		</div>
		<br>
	</div>
</div>
<%@ include file="../footer.jsp"%>

<script type="text/javascript">
    //mark用来标记该开设课程是否有添加指标
    var mark=1;
	loadIndexTree();
	function showEditBox4Intro() {
		$("#viewBox4intro").fadeOut(500,function(){
			$("#editBox4intro").fadeIn();
		});
	}
	function showEditBox4Index() {
		$("#viewBox4index").fadeOut(500,function(){
			$("#editBox4index").fadeIn();
		});
	}
	function showViewBox4Intro() {
		$("#editBox4intro").fadeOut(500,function(){
			$("#viewBox4intro").fadeIn();
		});
	}
	function showViewBox4Index() {
	    
	    if (mark==0)
	    {
	    $("#editBox4index").fadeOut(500,function(){
			$("#indexError").fadeIn();
		});
	    }
	    else{
		$("#editBox4index").fadeOut(500,function(){
			$("#viewBox4index").fadeIn();
		});
		}
	}
	
	function showEditBox4AddIndex()
	{
	   $("#indexError").fadeOut(500,showEditBox4Index());
	   
	}
	
	
</script>