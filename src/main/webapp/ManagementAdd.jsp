<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>业务管理-添加</title>
<jsp:include page="nav.jsp"></jsp:include>
<div class="content"></div>
<form id="ff">
	<div class="form-layer">
		<span>工号</span> <input type="text" class="form-input" name="job_number" id="job_number" maxlength="10" />
		<div class="error" id="job_number-error"></div>
	</div>
	<div class="form-layer">
		<span>姓名</span> <input type="text" class="form-input" name="name" id="name" maxlength="6" /> <input type="hidden" value="${name}" name="district_id"> <input type="hidden" value="0" name="state">
		<div class="error" id="name-error"></div>
	</div>
	<div class="form-layer">
		<span>职位</span> <select name="duty" class="form-select">
			<option value="营业部经理">营业部经理</option>
			<option value="门店经理">门店经理</option>
			<option value="业务员">业务员</option>
		</select>
	</div>
	<div class="form-layer">
		<span>电话</span><input type="text" class="form-input" name="phone" id="phone" maxlength="11" />
		<div class="error" id="phone-error"></div>
	</div>
	<div class="form-layer" style="text-align: center;">
		<input type="button" class="form-sub" onclick="add()" value="保存" /> <input type="button" value="取消" onclick="cancel()" class="form-return" />
	</div>
</form>
<jsp:include page="footer.jsp"></jsp:include>
</body>
<script type="text/javascript">
	$(function() {
		navSelected(1);
	})
	function genlist(t) {
		$(t).parent().siblings().children("a").removeClass("selected");
		$(t).addClass("selected");
	}
</script>
<script type="text/javascript">
	/* 取消按钮 */
	function cancel() {
		window.location.href = "toCompanyManagement.action";
	}
	/*验证*/
	/* 增加按钮 */
	function add() {
		var job_numbers = $("#job_number").val().trim();
		var names = $('#name').val().trim();
		var phone = $('#phone').val().trim();
		var reg = /^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\d{8}$/;
		if (job_numbers == '') {
			$('#job_number-error').text('工号不能为空');
			return false;
		} else {
			$.ajax({
				//几个参数需要注意一下
				type : "POST",//方法类型
				dataType : "json",//预期服务器返回的数据类型
				url : "toFindByJobNums.action",
				data : "job_number=" + job_numbers,
				success : function(result) {
					//打印服务端返回的数据(调试用)
					if (result.code == -1) {
						$('#job_number-error').text(result.msg);
					} else {
						$('#job_number-error').text("");
						if (names == '') {
							$('#name-error').text('姓名不能为空');
							return false;
						} else {
							$('#name-error').text('');
						}
						if (phone == '') {
							$('#phone-error').text('电话不能为空');
							return false;
						} else if (!reg.test(phone)) {
							$('#phone-error').text('手机号格式不正确')
							return false
						} else {
							$('#phone-error').text('');
						}
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
							url : "toAddsalesman.action",
							data : formJson,
							contentType : 'application/json',
							success : function(result) {
								console.log(result);//打印服务端返回的数据(调试用)
								if (result.code == 0) {
									window.location.href = "toCompanyManagement.action";
								}
							},
							error : function(result) {
								window.wxc.xcConfirm("抱歉！出了一点小问题", window.wxc.xcConfirm.typeEnum.error);
							}
						});
					}

				},
				error : function(result) {
					window.wxc.xcConfirm("抱歉！出了一点小问题", window.wxc.xcConfirm.typeEnum.error);

				}
			});
		}

	}
</script>
</html>
