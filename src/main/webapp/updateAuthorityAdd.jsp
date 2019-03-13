<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<title>角色权限管理（修改）</title>
<jsp:include page="nav.jsp"></jsp:include>
<div style="margin-top: 150px;">
	<form action="##" id="ff">
		<input type="hidden" name="id" value="${user.id}" class="form-input" id="id" />

		<div class="form-layer">
			<span>姓名</span><input type="text" name="name" class="form-input" value="${user.name}" readOnly />
		</div>
		<div class="form-layer">
			<span>权限</span> <input type="hidden" id="selectRole_type" value="${user.role_type}"> <select class="form-select" name="role_type" id="role_type">
				<option value="管理员">管理员</option>
				<option value="财务总监">财务总监</option>
				<option value="主管">主管</option>
				<option value="出纳">出纳</option>
				<option value="记账">记账</option>
				<option value="风控">风控</option>
				<option value="行政">行政</option>

			</select>
		</div>
		<div class="form-layer">
			<span>电话</span><input type="text" name="phone" class="form-input" value="${user.phone}" onkeyup="findByPhone()" />
			<p class="explain2"></p>
		</div>
		<div class="form-layer">
			<span>账号</span><input type="text" name="username" class="form-input" value="${user.username}" readOnly />
			<p class="explain3"></p>
		</div>
		<div class="form-layer">
			<span>密码</span><input type="password" name="password" class="form-input" value="" id="password" placeholder="请输入密码" />
			<p class="explain4"></p>
		</div>

		<div class="form-layer" style="text-align: center;">
			<input type="button" class="form-sub" onclick="update()" value="保存" /> <input type="button" value="取消" onclick="cancel();" class="form-return" />
		</div>
	</form>
</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>

<script type="text/javascript">
	function vilidata() {
		var pwd = document.getElementById("password").value

		if (pwd.length<6 || pwd.length>12) {
			window.wxc.xcConfirm("请输入6--12位数密码", window.wxc.xcConfirm.typeEnum.warning);

			return false;
		}
	}
</script>


<script type="text/javascript">
	/*一级导航栏选中 */
	navSelected(3);
	function cancel() {
		window.location.href = "touserList.action";
	}
	//判断电话是否已存在
	function findByPhone() {
		//电话
		var phone = $("#phone").val();
		if (phone.trim() == '') {
			$(".explain2").html("请输入手机号");
		} else {
			$.ajax({
				type : "post",
				dataType : "json",
				url : "findUserByPhone.action",
				data : "phone=" + phone,
				success : function(result) {
					if (result.code == 0) {
						$(".explain2").html("手机号已存在,请重新输入");
						$(".explain2").css("color", "red");
					} else {
						$(".explain2").html(" ");

					}
				},
				error : function(result) {
					console.log("异常" + result.msg);
				}
			});
		}

	}
	function update() {

		var formObject = {};

		var formArray = $("#ff").serializeArray();

		$.each(formArray, function(i, item) {
			formObject[item.name] = item.value;

		});
		var formJson = JSON.stringify(formObject);

		$.ajax({
			//几个参数需要注意一下
			type : "POST",//方法类型
			dataType : "json",//预期服务器返回的数据类型
			url : "updateUser.action",
			data : formJson,
			contentType : 'application/json',
			success : function(result) {
				console.log(result);//打印服务端返回的数据(调试用)
				if (result.code == 0) {
					window.location.href = "touserList.action";
				}

			},
			error : function(result) {
				window.wxc.xcConfirm("抱歉！出了一点小问题", window.wxc.xcConfirm.typeEnum.error);
			}
		});
	}
</script>
<script type="text/javascript">
	$(document).ready(function() {

		var role_type = $("#selectRole_type").val();
		$("#role_type option[value='" + role_type + "']").attr("selected", "selected");
	});
</script>
</html>
