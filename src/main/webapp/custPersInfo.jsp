<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<title>客户信息-个人信息</title>
<jsp:include page="nav.jsp"></jsp:include>
<div class="content">
	<div class="sub-box">
		<div class="sub-right">
			<input type="text" value="" name="name" class="sub-search" placeholder="请输入电话" id="inputPhone" onkeyup="findPerson()" /> <input type="button" value="添加" class="sub-button" />
		</div>
	</div>
	<table id="cs_table" class="table_tbs" rules=rows>
	</table>
</div>
<!-- 添加窗口 -->
<div class="popup" id="popup">
	<form id="ff">
		<div class="popup-layer">
			<span>姓名</span><input type="text" id="name" name="name" class="popup-input" />
		</div>
		<span class='popup-error' id="name-error"></span>
		<div class="popup-layer">
			<span>电话</span><input type="text" id="phone" name="phone" class=" popup-input" />
		</div>
		<span class='popup-error' id="phone-error"></span>
		<div class="popup-layer" style="text-align: center;">

			<input type="button" class="popup-sub" value="添加" onclick="add()" /> <input type="button" value="取消" class="popup-return" />
		</div>
	</form>
</div>
<!-- 修改窗口 -->
<div class="popup" id="popup-edit">
	<form id="ff-edit">
		<input type="hidden" id="edit_id" name="id">
		<div class="popup-layer">
			<span>姓名</span><input type="text" id="name-edit" name="name" class="popup-input" />
		</div>
		<span class='popup-error' id="name-error-edit"></span>
		<div class="popup-layer">
			<span>电话</span><input type="text" id="phone-edit" name="phone" class=" popup-input" />
		</div>
		<span class='popup-error' id="phone-error-edit"></span>
		<div class="popup-layer" style="text-align: center;">
			<input type="button" class="popup-sub" value="保存" onclick="saveEdit()" /> <input type="button" value="取消" class="popup-return" />
		</div>
	</form>
</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
<script type="text/javascript">
	/*一级导航栏选中 */
	navSelected(6);

	$(function() {

		var url = "toCustPersInfo.action";
		$.getJSON(url, function(res) {

			var data = [];
			for (var i = 0; i < res.length; i++) {
				data[i] = {
					id : res[i].id,
					name : res[i].name,
					phone : res[i].phone,
					操作 : "<a onclick='edit(" + res[i].id + "," + JSON.stringify(res[i].name) + "," + JSON.stringify(res[i].phone) + ")'><img src='images/editors.png'></a>"
							+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href='/toDeletePerson.action?id=" + res[i].id + "'><img src='images/deletes.png'></a>"
				};

			}
			console.log(data)
			var cs = new table({
				"tableId" : "cs_table", //必须
				"headers" : [ "序号", "姓名", "手机号", "操作" ], //必须
				"data" : data, //必须
				"displayNum" : 5, //必须  默认 10
				"groupDataNum" : 10
			//可选  默认 10
			});
			if (data.length == 0) {
				$('#cs_table').append('<img class="nodata" src="images/nodata.png">');
			}
		});
	});

	function findPerson() {
		var phone = $("#inputPhone").val();
		console.log(111);
		var url = '';
		if (phone.trim() == '') {
			url = "toCustPersInfo.action";
		} else {
			url = "toFindPersonByPhone.action?phone=" + phone;
		}
		$.getJSON(url, function(res) {

			var data = [];
			for (var i = 0; i < res.length; i++) {

				data[i] = {
					id : res[i].id,
					name : res[i].name,
					phone : res[i].phone,
					操作 : "<a onclick='edit(" + res[i].id + "," + JSON.stringify(res[i].name) + "," + JSON.stringify(res[i].phone) + ")'><img src='images/editors.png'></a>"
							+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href='/toDeletePerson.action?id=" + res[i].id + "'><img src='images/deletes.png'></a>"
				};

			}
			console.log(data)
			var cs = new table({
				"tableId" : "cs_table", //必须
				"headers" : [ "序号", "姓名", "手机号", "操作" ], //必须
				"data" : data, //必须
				"displayNum" : 5, //必须  默认 10
				"groupDataNum" : 10
			//可选  默认 10
			});
			if (data.length == 0) {
				$('#cs_table').append('<img class="nodata" src="images/nodata.png">');
			}
		});
	}

	/*弹窗 */
	$('.sub-button').click(function() {
		$('#popup').show();
		$('#popup-edit').hide();
	});
	$('.popup-return').click(function() {
		$('#popup').hide();
		$('#popup-edit').hide();
	});
</script>
<script type="text/javascript">
	/*添加*/
	function add() {

		var name = $('#name').val().trim();
		var phone = $('#phone').val().trim();
		if (name == '') {
			$('#name-error').html('姓名不能为空！');
			return false;
		} else {
			$('#name-error').html('');
		}
		;
		if (phone == '') {
			$('#phone-error').html('电话不能为空！');
			return false;
		} else {
			$('#phone-error').html('');
		}
		;

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
			url : "toAddPerson.action",
			data : formJson,
			contentType : 'application/json',
			success : function(result) {

				if (result.code == 0) {
					window.location.href = "custPersInfo.jsp";

				} else {
					window.wxc.xcConfirm("抱歉！", window.wxc.xcConfirm.typeEnum.error);
				}
			},
			error : function(result) {
				window.wxc.xcConfirm("抱歉！出了一点小问题", window.wxc.xcConfirm.typeEnum.error);
			}
		});
	}
	/*修改弹窗*/
	function edit(id, name, phone) {
		$("#edit_id").val(id);
		$('#name-edit').val(name);
		$('#phone-edit').val(phone);
		$('#popup').hide();
		$('#popup-edit').show();
	}
	/*保存修改*/
	function saveEdit(id) {
		var name = $('#name-edit').val().trim();
		var phone = $('#phone-edit').val().trim();
		if (name == '') {
			$('#name-error-edit').html('姓名不能为空！');
			return false;
		} else {
			$('#name-error-edit').html('');
		}
		;
		if (phone == '') {
			$('#phone-error-edit').html('电话不能为空！');
			return false;
		} else {
			$('#phone-error-edit').html('');
		}
		var formObject = {};

		var formArray = $("#ff-edit").serializeArray();

		$.each(formArray, function(i, item) {

			formObject[item.name] = item.value;

		});
		var formJson = JSON.stringify(formObject);

		$.ajax({

			//几个参数需要注意一下
			type : "POST",//方法类型
			dataType : "json",//预期服务器返回的数据类型
			url : "toUpdatePerson.action",
			data : formJson,
			contentType : 'application/json',
			success : function(result) {

				if (result.code == 0) {
					window.location.href = "custPersInfo.jsp";

				} else {

					window.wxc.xcConfirm("抱歉！出了一点小问题", window.wxc.xcConfirm.typeEnum.error);
				}
			},
			error : function(result) {

				window.wxc.xcConfirm("抱歉！出了一点小问题", window.wxc.xcConfirm.typeEnum.error);
			}
		});
	}
</script>
</html>