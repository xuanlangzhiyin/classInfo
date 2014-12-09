<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<div id="container">
	<div class="w1200 white bc">
		<div class="w1060 bc">
	  
           
			<div class="main-boxes pb20">
				<div class="main-box ">
					<h2 class="boxTitle active" onclick="slideBox(this);">发布通知</h2>
					<div class="boxContext">
						<s:form action="saveNotice.action" enctype="multipart/form-data"
							method="post">
							<div class="m30 boxForm f16">

								<p>
									<span class="mr20 w100">通知对象：</span> <select name="readerid"
										id="readerid">
										<option value="0"
											<s:if test="readerid==0">selected="selected"</s:if>>全体</option>
										<option value="1"
											<s:if test="readerid==1">selected="selected"</s:if>>老师</option>
										<option value="2"
											<s:if test="readerid==2">selected="selected"</s:if>>学生</option>
									</select>
								</p>
								<p>
									<span class="mr20 w100">通知标题：</span> <input class="f14"
										type="text" name="notice.title"
										value="<s:property value="notice.title"/>"
										placeholder="请输入通知的标题 ...">
								</p>
                                
								<p>
									<span class="mr20 w100 ">附件：</span><input id="long-noBorder"
										class="noBorder" type="file" name="file" onchange="checkFile(this)">
								</p>
								<p><span class="mr20 w100"></span>（限制附件文件类型为pdf, doc, docx, xls, xlsx, rar, zip）</p>
							</div>
							<div class="tc h50 f16">
								<s:url id="listNoticeUrl" action="listNotice">
									<s:param name="noticeSearchMeta.keyword"
										value="noticeSearchMeta.keyword"></s:param>
									<s:param name="noticeSearchMeta.currentPage"
										value="noticeSearchMeta.currentPage"></s:param>
								</s:url>
								<input type="button" class="btn blue" value="取&nbsp消"
									onclick="location='<s:property value="%{listNoticeUrl}"/>'"> <input
									type="submit" class="btn green" value="提&nbsp交">
							</div>
						</s:form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<%@ include file="../footer.jsp"%>

<script src="js/jquery-1.8.3.min.js"></script>
<script src="js/common.js"></script>
<script type="text/javascript">
$(document).ready(function() {
$(".close").click(function(){
		$(this).parent().parent().hide();
		});
	});

        //填充消息框函数
	function fillinMsg(e,title,msg){
	$(e).children("div .messageContent").children("h4").text(title);
	$(e).children("div .messageContent").children("ul").children().text(msg);
	}
</script>
 
