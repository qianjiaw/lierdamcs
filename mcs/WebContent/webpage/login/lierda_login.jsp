<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page
	import="org.jeecgframework.core.util.SysThemesUtil,org.jeecgframework.core.enums.SysThemesEnum"%>
<%@include file="/context/mytags.jsp"%>
<%
  session.setAttribute("lang","zh-cn");
  SysThemesEnum sysTheme = SysThemesUtil.getSysTheme(request);
  String lhgdialogTheme = SysThemesUtil.getLhgdialogTheme(sysTheme);
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>智慧园区系统管理平台</title>
<link rel="shortcut icon" href="images/favicon.ico">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

<!-- text fonts -->
<link rel="stylesheet" href="plug-in/ace/css/ace-fonts.css" />
<link rel="stylesheet" href="plug-in/ace/css/jquery-ui.css" />

<!-- ace styles -->
<link rel="stylesheet" href="plug-in/ace/css/lux-ace.css"/>

<!--[if lte IE 9]>
  <link rel="stylesheet" href="plug-in/ace/css/ace-part2.css" class="ace-main-stylesheet" />
  <![endif]-->

<!--[if lte IE 9]>
  <link rel="stylesheet" href="plug-in/ace/css/ace-ie.css" />
  <![endif]-->
<!-- ace settings handler -->
<script src="plug-in/ace/js/ace-extra.js"></script>

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

<!--[if lte IE 8]>
  <script src="plug-in/ace/js/html5shiv.js"></script>
  <script src="plug-in/ace/js/respond.js"></script>
  <![endif]-->

</head>
<body >

	<div class="main-container">
		<div class="header">
			<div class="head-logo">
				<div href="#" class="lux-logo">
					<img alt="" src="plug-in/ace/img/bg/logo.png" class="logoimg" />
				</div>
			</div>
			<div class="head-name">
				<ul >
					<li class="ch-font">智慧园区系统管理平台</li>
					<li class="en-font">Wisdom park system management platform</li>
				</ul>
			</div>
		</div>

		<div class="main-content">
			<div class="login-box">
				<form check="loginController.do?checkuser" role="form"
				action="loginController.do?login" method="post">
					<span class="login-input">
						<i class="icon-username"></i>
						<input type="text" class="login-uername" name="userName" placeholder="请输入用户名" id="userName" value="admin" />
					</span>
					<span class="login-input">
						<i class="icon-password"></i>
						<input type="password" class="login-password" name="password" placeholder="请输入密码" id="password" value="123456" />
					</span>
					<div class="login-boxcenter">
						<input type="checkbox" class="logincheck-remember" id="on_off" name="remember" value="1">记住用户名</input>
						<a class="login-forget" href="#">忘记密码？</a>
					</div>
					<button class="login-in" onclick="checkUser()" id="but_login" name="but_login">登录</button>
				</form>
			</div>
		</div>

		<div class="footer">
			<ul class="foot-text">版权所有@ 2017-2022 <a href="#" class="foot-text">利尔达科技集团股份有限公司</a></ul>
		</div>
	</div>
	

	<script type="text/javascript" src="plug-in/jquery/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="plug-in/jquery/jquery.cookie.js"></script>
	<script type="text/javascript" src="plug-in/mutiLang/en.js"></script>
	<script type="text/javascript" src="plug-in/mutiLang/zh-cn.js"></script>
	<script type="text/javascript" src="plug-in/login/js/jquery.tipsy.js"></script>
	<script type="text/javascript" src="plug-in/login/js/iphone.check.js"></script>
	
	<!-- <script type="text/javascript" src="plug-in/login/js/login.js"></script> -->
	<script type="text/javascript">

		//输入验证码，回车登录
		$(document).keydown(function(e) {
			if (e.keyCode == 13) {
				$("#but_login").click();
			}
		});

		//验证用户信息
		function checkUser() {
			if (!validForm()) {
				return false;
			}else {
				newLogin();
			}
		}
		//表单验证
		function validForm() {
			if ($.trim($("#userName").val()).length == 0) {
				showErrorMsg("请输入用户名");
				return false;
			}

			if ($.trim($("#password").val()).length == 0) {
				showErrorMsg("请输入密码");
				return false;
			}
			return true;
		}

		//登录处理函数
		function newLogin(orgId) {
			setCookie();
			var actionurl = $('form').attr('action');//提交路径
			var checkurl = $('form').attr('check');//验证路径
			var formData = new Object();
			var data = $(":input").each(function() {
				formData[this.name] = $("#" + this.name).val();
			});
			formData['orgId'] = orgId ? orgId : "";
			$.ajax({
					async : false,
					cache : false,
					type : 'POST',
					url : checkurl,// 请求的action路径
					data : formData,
					error : function() {// 请求失败处理函数
					},
					success : function(data) {
						var d = $.parseJSON(data);
						if (d.success) {
							window.location.href = actionurl;
						} else {
							showErrorMsg(d.msg);
						}
					}
				});
		}
		
		//设置cookie
		function setCookie() {
			if ($('#on_off').get(0).checked) {
				$("input[iscookie='true']").each(function() {
					$.cookie(this.name, $("#" + this.name).val(), "/", 24);
					$.cookie("COOKIE_NAME", "true", "/", 24);
				});
			} else {
				$("input[iscookie='true']").each(function() {
					$.cookie(this.name, null);
					$.cookie("COOKIE_NAME", null);
				});
			}
		}
		//读取cookie
		function getCookie() {
			var COOKIE_NAME = $.cookie("COOKIE_NAME");
			if (COOKIE_NAME != null) {
				$("input[iscookie='true']").each(function() {
					$($("#" + this.name).val($.cookie(this.name)));
					if ("admin" == $.cookie(this.name)) {
						$("#randCode").focus();
					} else {
						$("#password").val("");
						$("#password").focus();
					}
				});
				$("#on_off").attr("checked", true);
				$("#on_off").val("1");
			} else {
				$("#on_off").attr("checked", false);
				$("#on_off").val("0");
				$("#randCode").focus();
			}
		}
	</script>
	<%=lhgdialogTheme %>
</body>
</html>