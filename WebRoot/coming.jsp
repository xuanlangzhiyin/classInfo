<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.wuhei.cms.model.*"%>
<%@ include file="../header.jsp"%>

<s:url id="saveSuggestionUrl" action="saveSuggestion" />
<div id="container">
	<div class="white w1200 bc"> 
		<div class="w1060 bc">
			  
			<div class="clear"></div>

			<div class="formTable-context">
				<div class="listTable w1000 bc cgray"></div>

				<div class="clear"></div>

				<div id="messageBox">
					<div class="messageBox info">
						<div class="messageTitle">
							<span class="fl messageLogo"></span><span class="close fr"
								onclick="closeMessage(this)"></span>
						</div>
						<div class="messageContent">
							<h4>很抱歉，该功能正在实现中:)</h4>
						</div>
					</div>
				</div>

				<div class="listTable w1000 bc">
					<div id="messageBox"></div>
				</div>
		

			</div>
		</div>
		<div class="clear mb50"></div>

		<br>
	</div>
</div>
<%@ include file="footer.jsp"%>
<script type="text/javascript" src="js/common/suggestion.js"></script>
