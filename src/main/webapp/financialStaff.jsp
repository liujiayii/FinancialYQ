<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<title>财务管理-员工管理</title>
<jsp:include page="nav.jsp"></jsp:include>
<div class="content">
	<div class="sub-box" id="fin">
		<!-- <ul class="sub-nav">
						<li><a href="toStaff.action" class="selected">员工管理</a></li>
						<li><a href="toIncome.action">收入费用</a></li>
						<li><a href="toSpend.action">支出费用</a></li>
						<li><a href="toFinanceManager.action">标的管理</a></li>
						<li><a href="toCompany.action">公司管理</a></li>
					</ul> -->
		<div class="sub-right">
			<input type="text" value="" name="name" class="sub-search" placeholder="请输入电话" id="inputPhone" onkeyup="findStaff()" /> <input type="button" value="添加" class="sub-button" onclick="window.location.href='toShowArea.action'" />
		</div>
	</div>
	<table id="cs_table" class="table_tbs" rules=rows>
	</table>
</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
<script type="text/javascript" src="js/jquery1.11.3-jquery.min.js"></script>
<script type="text/javascript">
	/*一级导航栏选中 */
	navSelected(4);

	$(function() {
		var url = "toShowStaffInfo.action";
		$.getJSON(url, function(res) {

			var data = [];
			for (var i = 0; i < res.length; i++) {

				data[i] = {
					id : res[i].id,
					job_number : res[i].job_number,
					name : res[i].name,
					area : res[i].area,
					dep : res[i].dep_name,
					post : res[i].post_name,
					phone : res[i].phone,
					entry_date : getTime(res[i].entry_date),
					操作 : "<a href='toUpdateStaff.action?id=" + res[i].id + "'><img src='images/editors.png'></a>&nbsp;&nbsp;&nbsp;&nbsp;" + toLock(res[i].type, res[i].id)
				};

			}
			console.log(data)
			var cs = new table({
				"tableId" : "cs_table", //必须
				"headers" : [ "序号", "工号", "姓名", "地区", "部门", "职位", "电话", "时间", "操作" ], //必须
				"data" : data, //必须
				"displayNum" : 8, //必须  默认 10
				"groupDataNum" : 10
			//可选  默认 10
			});
			if (data.length == 0) {
				$('#cs_table').append('<img class="nodata" src="images/nodata.png">');
			}
		});

		var c = "${roletype}";
		if (c == '行政') {
			$('.sub-nav li').slice(1, 4).css("display", "none");
		}

	});
	function toLock(t, y) {
		var lock;
		if (t == 0) {
			lock = "<img src='images/not_lock.png' onclick='lock(this," + y + ")'>";
		} else {
			lock = "<img src='images/lock.png' onclick='lock(this," + y + ")'>";
		}
		return lock;

	}
	function lock(a, y) {

		$.ajax({
			type : "POST",
			dataType : "json",
			url : "toLockStaff.action?id=" + y,
			data : {
				"id" : y
			},
			contentType : 'application/json',
			success : function(result) {

				if (result.code == 0) {
					/* 点击锁定时，换图片 */
					var scrs = $(a).attr('src');
					if (scrs == 'images/not_lock.png') {
						$(a).attr('src', 'images/lock.png');
					} else {
						$(a).attr('src', 'images/not_lock.png');
					}

				}
			},
			error : function(result) {
				window.wxc.xcConfirm("抱歉！出了一点小问题", window.wxc.xcConfirm.typeEnum.error);

			}
		});
	};
	function findStaff() {
		var phone = $("#inputPhone").val();

		var url = '';
		if (phone.trim() == '') {
			url = "toShowStaffInfo.action";
		} else {
			url = "toFindStaffByPhone.action?phone=" + phone;
		}
		$.getJSON(url, function(res) {

			var data = [];
			for (var i = 0; i < res.length; i++) {

				data[i] = {
					id : res[i].id,
					job_number : res[i].job_number,
					name : res[i].name,
					area : res[i].area,
					dep : res[i].dep_name,
					post : res[i].post_name,
					phone : res[i].phone,
					entry_date : getTime(res[i].entry_date),
					操作 : "<a href='toUpdateStaff.action?id=" + res[i].id + "'><img src='images/editors.png'></a>&nbsp;&nbsp;&nbsp;&nbsp;" + toLock(res[i].type, res[i].id)
				};

			}
			console.log(data)
			var cs = new table({
				"tableId" : "cs_table", //必须
				"headers" : [ "序号", "工号", "姓名", "地区", "部门", "职位", "电话", "时间", "操作" ], //必须
				"data" : data, //必须
				"displayNum" : 8, //必须  默认 10
				"groupDataNum" : 10
			//可选  默认 10
			});
			if (data.length == 0) {
				$('#cs_table').append('<img class="nodata" src="images/nodata.png">');
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