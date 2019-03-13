<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>业务管理-修改</title>
<jsp:include page="nav.jsp"></jsp:include>
<div class="content">
	<div class="sub-box">
		<ul class="sub-nav">
			<c:forEach items="${list}" var="item">
				<li><a onclick="genlist(this)">${item.area}</a></li>
			</c:forEach>
		</ul>
	</div>
</div>
<jsp:include page="footer.jsp"></jsp:include>
<form id="ff">
	<div class="form-layer">
		<span>工号</span><input type="text" class="form-input" value="${listId.get(0).job_number}" name="job_number" readonly="readonly" />
	</div>
	<div class="form-layer">
		<input type="hidden" name="id" value="${listId.get(0).id}"> <span>地区</span> <select name="district_id" class="form-select" id="district_id">
			<c:forEach items="${list}" var="list">
				<option value="${list.area}">${list.area}</option>
			</c:forEach>
		</select>

	</div>
	<div class="form-layer">
		<span>姓名</span><input type="text" class="form-input" value="${listId.get(0).name}" name="name" readonly="readonly" />
	</div>

	<div class="form-layer">
		<span>职位</span> <input type="hidden" id="selectDuty" value="${listId.get(0).duty}" /> <select name="duty" class="form-select" id="duty">
			<option value="营业部经理">营业部经理</option>
			<option value="门店经理">门店经理</option>
			<option value="业务员">业务员</option>
		</select>
	</div>
	<div class="form-layer">
		<span>电话</span><input type="text" class="form-input" value="${listId.get(0).phone}" name="phone" id="phone" maxlength="11" />
		<div class="error" id="phone-error"></div>
	</div>
	<div class="form-layer" style="text-align: center;">
		<input type="button" class="form-sub" onclick="add()" value="保存" /> <input type="button" value="取消" onclick="cancel()" class="form-return" />
	</div>
</form>
</body>
<script type="text/javascript">
	$(function() {
		var a = "${listId.get(0).district_id}";
		var b = $("#selectDuty").val();
		$("#district_id option[value='" + a + "']").attr("selected", "selected");
		$("#duty option[value='" + b + "']").attr("selected", "selected");

	})
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
	/* 增加按钮 */
	function add() {
		var phone = $('#phone').val().trim();
		var reg = /^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\d{8}$/;
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
			url : "toupdatesalesman.action",
			data : formJson,
			contentType : 'application/json',
			success : function(result) {
				console.log(result);//打印服务端返回的数据(调试用)
				if (result.code == 0) {
					window.location.href = "toCompanyManagement.action";
				}
			},
			error : function(result) {
				alert("异常！" + result.msg);
			}
		});
	}
</script>
</html>
