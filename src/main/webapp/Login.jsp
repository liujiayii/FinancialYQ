<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登录</title>
<link rel="stylesheet" href="./css/invoice_login.css" />
<link rel="stylesheet" href="./css/style.css">
<script type="text/javascript" src="./js/jquery.min.js"></script>
</head>
<body>
	<div class="main">
		<div class="article_main">
			<h3>
				<img src="images/s_logo.png">财务发票系统
			</h3>
			<div class="matter">
				<form action="login.action" method="post" id="fin_form">
					<div class="details">
						<input type="text" maxlength="11" name="username" placeholder="请输入账号" onkeyup="findUsername()" class="inputs verify" id="fin_account" autocomplete="off" /> <img src="images/account.png" class="img_01" />
						<p class="explain1"></p>
					</div>
					<div class="details">
						<input type="password" name="password" placeholder="请输入密码" onblur="findPass()" id="fin_pwd" class="inputs verify" /> <img src="images/password.png" class="img_01" />
						<p class="explain2"></p>
					</div>
					<div class="details">
						<input type="hidden" value="${result}"> <input type="text" name="auth_code" placeholder="请输入验证码" value="" maxlength="2" autocomplete="off" id="fin_code" class="validation verify" /> <img id="img" class="auth_code" src="showAuthCode.action">
						<div class="clear" style="clear: both;"></div>
						<p class="explain3"></p>
					</div>
					<!--
	                    	作者：offline
	                    	时间：2018-07-030
	                    	描述：验证码
	                    -->
					<input type="button" value="登录" style="width: 185px; height: 40px;" class="refer" />
				</form>
			</div>
			<p class="pp">copyright：云阙网络科技有限公司</p>
		</div>
	</div>
</body>
<script type="text/javascript">
	/*验证当前是否为谷歌浏览器*/
	var ua = navigator.userAgent.toLocaleLowerCase();
	if (ua.match(/chrome/) != null) {
		var is360 = _mime("type", "application/vnd.chromium.remoting-viewer");
		function _mime(option, value) {
			var mimeTypes = navigator.mimeTypes;
			for ( var mt in mimeTypes) {
				if (mimeTypes[mt][option] == value) {
					return true;
				}
			}
			return false;
		}
		if (is360) {
			alert('请用谷歌浏览器登录');
			window.location.href = "https://www.google.cn/chrome";
		}
	} else {
		alert('请用谷歌浏览器登录');
		window.location.href = "https://www.google.cn/chrome";
	}
	//验证账号密码
	$(".refer").click(function() {
		var cell = $("#fin_account").val();//账号
		var pwd = $("#fin_pwd").val();//密码
		var code = $("#fin_code").val();//验证码

		if (cell == "") {
			$(".explain1").html("请输入账号")
			$("#img").attr("src", "showAuthCode.action?t=" + new Date().getTime());
			$("#fin_code").val("");
			return false;
		} else {
			$(".explain1").html('');
		}

		if (pwd == "") {
			$(".explain2").html("请输入密码")
			$("#img").attr("src", "showAuthCode.action?t=" + new Date().getTime());
			return false;
		} else if (pwd.length<6 || pwd.length>12) {
			$(".explain2").html("请输入6--12位数密码")
			$("#img").attr("src", "showAuthCode.action?t=" + new Date().getTime());
			return false;
		} else {
			$(".explain2").html('');
		}

		if (code == "") {
			$(".explain3").html("请输入图文验证码")
			$("#img").attr("src", "showAuthCode.action?t=" + new Date().getTime());
			return false;
		} else {
			$(".explain3").html('')
		}
		;
		/*验证图文验证码*/
		$.ajax({
			type : "post",
			url : "loginTwo.action",
			data : {
				"auth_code" : code,

			},
			success : function(result) {

				if (result.code == 0) {
					$.ajax({
						type : "post",
						dataType : "json",
						url : "findByPass.action",
						data : {
							"username" : cell,
							"password" : pwd
						},
						success : function(result) {
							if (result.code == 0) {
								$("#fin_form").submit();
							} else {
								$(".explain2").html(result.msg);
								$(".explain2").css("color", "red");

								$("#img").attr("src", "showAuthCode.action?t=" + new Date().getTime());
								$("#fin_code").val("");
							}
						},
						error : function(result) {
							console.log("异常" + result.msg);
						}
					});
				} else {
					$(".explain3").html("验证码不正确")
					$("#img").attr("src", "showAuthCode.action?t=" + new Date().getTime());
					$("#fin_code").val("");
				}
			},
			error : function() {
				window.wxc.xcConfirm("抱歉！出了一点小问题", window.wxc.xcConfirm.typeEnum.error);
			}
		});
	});
	$("body").keydown(function(event) {
		if (event.keyCode == "13") {//keyCode=13是回车键
			$(".refer").click();
		}
	});
	$("#img").click(function() {
		$("#img").attr("src", "showAuthCode.action?t=" + new Date().getTime());
	});
	//验证登录
	function findUsername() {
		var username = $("#fin_account").val();
		if (username.trim() == '') {
			$(".explain1").html("请输入账号");
		} else if (username.length < 2) {
			$(".explain1").html("正在查询");
			$(".explain1").css("color", "blue");
		} else {
			$.ajax({
				type : "post",
				dataType : "json",
				url : "findByUsername.action",
				data : "username=" + username,
				success : function(result) {
					if (result.code == 0) {
						$(".explain1").html("用户名正确");
						$(".explain1").css("color", "green");
					} else {
						$(".explain1").html("用户名不正确");
						$(".explain1").css("color", "red");
					}
				},
				error : function(result) {
					console.log("异常" + result.msg);
				}
			});
		}

	}
	function findPass() {
		var password = $("#fin_pwd").val();
		var username = $("#fin_account").val();
		if (password.trim() == '') {
			$(".explain2").html(" ");
		} else {

		}

	}
</script>

</html>