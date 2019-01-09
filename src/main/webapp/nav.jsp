<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/style.css" type="text/css" />
<link rel="stylesheet" type="text/css" href="css/home_page.css" />
<link rel="stylesheet" type="text/css" media="screen" href="css/easyui.css">
<link rel="stylesheet" type="text/css" href="css/xcConfirm.css" />
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/tablePage.js"></script>
<script type="text/javascript" src="js/xcConfirm.js"></script>
<script type="text/javascript">
	$(function() {

		var c = "${roletype}";
		if (c == '风控') {
			$('.classify_uls li').slice(1, 6).css("display", "none");
			$('.classify_uls li').slice(0, 1).html("风控填写页面");
			$('.classify_uls li').slice(0, 1).css("color", "#8907b9");
			$('.classify_uls li').slice(0, 1).css("font-weight", "Bold");

		}
		if (c == '出纳') {
			$('.classify_uls li').slice(1, 6).css("display", "none");
			$('.classify_uls li').slice(0, 1).html("出纳可仅可查看风控填的表");
			$('.classify_uls li').slice(0, 1).css("color", "#8907b9");
			$('.classify_uls li').slice(0, 1).css("font-weight", "Bold");

		}

		if (c == '记账') {
			$('.classify_uls li').slice(3, 4).css("display", "none");
			$('.classify_uls li').slice(1, 2).css("display", "none");
		}

		if (c == '管理员') {
			$('.classify_uls li').slice(0, 3).css("display", "none");
			$('.classify_uls li').slice(4, 6).css("display", "none");
		}
		if (c == '行政') {
			$('.classify_uls li').slice(0, 2).css("display", "none");
			$('.classify_uls li').slice(3, 4).css("display", "none");
			$('.classify_uls li').slice(5, 6).css("display", "none");
		}
		if (c == '主管') {
			$('.classify_uls li').slice(1, 4).css("display", "none");

		}

	});
</script>
</head>
<body>
	<div class="container">
		<div class="head_box">
			<div class="head">
				<div class="head_left">
					<img src="images/logo.png" style="margin-top: 8px;">
				</div>
				<div class="head_right">
					<b>${username}</b><b>${roletype}</b><a href="toLogin.action"><img src="images/exit.png"></a>
				</div>
				<input type="hidden" value="${roletype}" />
			</div>
			<div class="classify_box">
				<ul class="classify_uls">
					<li><a href="toHomePage.action">首页</a></li>
					<li><a href="toCompanyManagement.action">业务管理</a></li>
					<li><a href="demandCommission.action">信息查询</a></li>
					<li><a href="toAuthManger.action">角色权限</a></li>
					<li><a href="toFinanceManager.action">财务管理</a></li>
					<li><a href="rules.action">业绩规则</a></li>
					<li class="custInfo"><a href="javascript:;">客户信息</a>
						<ul class="classify_uls_clild">
							<li><a href="custPersInfo.jsp">个人信息</a></li>
							<li><a href="custCompInfo.jsp">公司信息</a></li>
						</ul>
					</li>
				</ul>
			</div>
		</div>