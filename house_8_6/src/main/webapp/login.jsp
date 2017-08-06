<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>高大上租房网 - 用户登录</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.1.0.js"></script>
<link type="text/css" rel="stylesheet" href="css/style.css" />
</head>
<body>
	<div id="header" class="wrap">
		<div id="logo">
			<img src="images/logo.png" />
		</div>
	</div>
	<div id="regLogin" class="wrap">
		<div class="dialog">
			<div class="box">
				<h4>用户登录</h4>
				<form action="login.do" method="post">
					<div class="infos">
						<table class="field">
							<tr>
								<td>&nbsp;</td>
								<td style="color: red;" id="td1">${hint} ${error}</td>
							</tr>
							<tr>
								<td class="field">用 户 名：</td>
								<td>
									<c:if test="${not empty cookie.uid}">
										<c:set var="currentUid" value="${cookie.uid.value}" />
									</c:if> 
									<c:if test="${not empty username}">
										<c:set var="currentUid" value="${username}" />
									</c:if> 
									<input id="username" type="text" class="text" name="username" value="${currentUid}" onblur="checkName(this.value)">
								</td>
							</tr>
							<tr>
								<td class="field">密 码：</td>
								<td>
									<input id="password" type="password" class="text" name="password">
								</td>
							</tr>
							<tr>
								<td class="field">验 证 码：</td>
								<td>
									<input id="codeStr" type="text" class="text" name="code">
									<img id="code" src="code.do" width="80" height="30">
								</td>
							</tr>
							<tr>
								<td></td>
								<td>
									<input type="checkbox" name="save"> 
									<label>下次自动登录</label>
								</td>
							</tr>
						</table>
						<div class="buttons">
							<input type="submit" value="立即登录">
							<input id="toRegBtn" type="button" value="注册">
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div id="footer" class="wrap">
		<dl>
			<dt>高大上租房网 &copy; 2013 攀峰科技 川ICP证1000001号</dt>
			<dd>关于我们 · 联系方式 · 意见反馈 · 帮助中心</dd>
		</dl>
	</div>
	<!-- <script>
		$('#code').on('click', function() {
			$(this).attr('src', 'code.do?' + Math.random());
		});
		$('#toRegBtn').on('click', function() {
			location.href="register.jsp";
		});
	</script> -->
	<script type="text/javascript">
	function checkName(username){ 
		//直接使用jquery的调用方式
		$.ajax({
			"url" : "${pageContext.request.contextPath}/ajaxname.do",
			"data" : "username="+username,
			"type" : "post",
			"success" : function(data) {
				if(data.flag='true'){
					document.getElementById("td1").innerHTML="该用户名可使用";
				}else {
					document.getElementById("td1").innerHTML="该用户名不可用";
				}
				
			},
			"error" : function() {
				alert("请求失败");
			}
			});  
	}
	</script>
</body>
</html>


