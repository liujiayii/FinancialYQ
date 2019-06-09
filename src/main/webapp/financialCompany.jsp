<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<title>财务管理-公司管理</title>
<jsp:include page="nav.jsp"></jsp:include>
<div class="content">
	<div class="sub-box" id="fin">
		<!-- <ul class="sub-nav">
						<li><a href="toStaff.action">员工管理</a></li>
						<li><a href="toIncome.action">收入费用</a></li>
						<li><a href="toSpend.action">支出费用</a></li>
						<li><a href="toFinanceManager.action">标的管理</a></li>
						<li><a href="toCompany.action" class="selected">公司管理</a></li>
					</ul> -->
		<div class="sub-right">
			<input type="button" value="添加" class="sub-button" />
		</div>
	</div>
	<table id="cs_table" class="table_tbs" rules=rows>
	</table>
</div>
<!-- 添加窗口 -->
<div class="popup" id="popup">
	<form id="ff">
		<div class="popup-layer">
			<span>名称</span><input type="text" id="area" name="area" class="popup-input" />
		</div>
		<span class='popup-error' id="name-error"></span>
		<div class="popup-layer">
			<span>区域代码</span><input type="text" id="area_code" name="area_code" class=" popup-input" />
		</div>
		<span class='popup-error' id="area-error"></span>
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
			<span>名称</span><input type="text" id="area_edit" name="area" class="popup-input" />
		</div>
		<span class='popup-error' id="area_edit-error"></span>
		<div class="popup-layer">
			<span>区域代码</span><input type="text" id="area_code_edit" name="area_code" class="popup-input" />
		</div>
		<span class='popup-error' id="area_code_edit-error"></span>
		<div class="popup-layer" style="text-align: center;">
			<input type="button" class="popup-sub" value="保存" onclick="saveEdit()" /> <input type="button" value="取消" class="popup-return" />
		</div>
	</form>
</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
<script type="text/javascript">
	/*一级导航栏选中 */
	navSelected(4);

	$(function() {

		var c = "${roletype}";
		if (c == '行政') {
			$('.sub-nav li').slice(1, 4).css("display", "none");
		}

		var url = "branchofficeList.action";
		$.getJSON(url, function(res) {

			var data = [];
			for (var i = 0; i < res.length; i++) {
				data[i] = {
					id : res[i].id,
					area : res[i].area,
					area_code : res[i].area_code,
					time : getTime(res[i].create_time),
					操作 : "<a onclick='edit(" + res[i].id + "," + JSON.stringify(res[i].area) + "," + JSON.stringify(res[i].area_code) + ")'><img src='images/editors.png'></a>"
				};

			}
			console.log(data)
			var cs = new table({
				"tableId" : "cs_table", //必须
				"headers" : [ "序号", "名称", "区划代码", "时间", "操作" ], //必须
				"data" : data, //必须
				"displayNum" : 8, //必须  默认 10
				"groupDataNum" : 10
			//可选  默认 10
			});
			if (data.length == 0) {
				$('#cs_table').append('<img class="nodata" src="images/nodata.png">');
			}
		});
	});
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

		var area = $('#area').val().trim();
		var area_code = $('#area_code').val().trim();
		if (area == '') {
			$('#name-error').html('名称不能为空！');
			return false;
		} else {
			$('#name-error').html('');
		}
		;
		if (area_code == '') {
			$('#area-error').html('区域代码不能为空！');
			return false;
		} else {
			$('#area-error').html('');
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
			url : "toAddbranchoffice.action",
			data : formJson,
			contentType : 'application/json',
			success : function(result) {

				if (result.code == 0) {
					window.location.href = "toCompany.action";

				} else {
					window.wxc.xcConfirm("抱歉！公司名或区划代码重复", window.wxc.xcConfirm.typeEnum.error);
				}
			},
			error : function(result) {
				window.wxc.xcConfirm("抱歉！公司名或区划代码重复", window.wxc.xcConfirm.typeEnum.error);
			}
		});
	}
	/*修改弹窗*/
	function edit(id, area, code) {
		$("#edit_id").val(id);
		$('#area_edit').val(area);
		$('#area_code_edit').val(code);
		$('#popup').hide();
		$('#popup-edit').show();
	}
	/*保存修改*/
	function saveEdit(id) {
		var area = $('#area_edit').val().trim();
		var area_code = $('#area_code_edit').val().trim();
		if (area == '') {
			$('#area_edit-error').html('名称不能为空！');
			return false;
		} else {
			$('#area_edit-error').html('');
		}
		;
		if (area_code == '') {
			$('#area_code_edit-error').html('区域代码不能为空！');
			return false;
		} else {
			$('#area_code_edit-error').html('');
		}
		;
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
			url : "toUpdatebranchoffice.action?id=" + id,
			data : formJson,
			contentType : 'application/json',
			success : function(result) {

				if (result.code == 0) {
					window.location.href = "toCompany.action";

				} else {
					window.wxc.xcConfirm("抱歉！公司名或区划代码重复", window.wxc.xcConfirm.typeEnum.error);
				}
			},
			error : function(result) {
				window.wxc.xcConfirm("抱歉！公司名或区划代码重复", window.wxc.xcConfirm.typeEnum.error);
			}
		});
	}
</script>
<script type="text/javascript">
	function getTime(t) {
		var _time = new Date(t);
		var year = _time.getFullYear(); //2017
		var month = _time.getMonth() + 1; //7
		var date = _time.getDate();//10
		return year + "年" + month + "月" + date + "日   ";
	}
</script>
</html>