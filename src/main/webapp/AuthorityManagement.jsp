<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<title>角色权限管理</title>
<jsp:include page="nav.jsp"></jsp:include>
<div class="content">
	<div class="sub-box">
		<div class="sub-right">
			<a href="" class="sub-button" id="aid">添加管理</a>
		</div>
	</div>
	<table id="cs_table" class="table_tbs" rules=rows>
	</table>
</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
<script type="text/javascript">
	function lock(a, y) {

		var url = "toLockUser.action";

		$.ajax({
			"url" : url,
			type : "post",
			dataType : "json",
			contentType : 'application/json',
			data : {
				"id" : y
			},
			success : function(result) {
				if (result.code == 0) {
					var scrs = $(a).attr('src');
					if (scrs == 'images/not_lock.png') {
						$(a).attr('src', 'images/lock.png');
					} else {
						$(a).attr('src', 'images/not_lock.png');
					}
					/* window.location.href="touserList.action"; */
				}
			},
			error : function(result) {
				console.log("error");
			}
		});
	};
	/*一级导航栏选中 */
	navSelected(3);
	$(function() {

		var url = "userList.action";
		$.getJSON(url, function(res) {

			var data = [];
			for (var i = 0; i < res.length; i++) {
				//			var lock = "<a href='toLockUser.action?id="+res[i].id+"'><img src='images/lock.png' onclick='lock()'></a>";
				var lock = "<img src='images/lock.png' onclick='lock(this," + res[i].id + ")'>";

				//			var not_lock = "<a href='toLockUser.action?id="+res[i]/.id+"'><img src='images/not_lock.png'></a>";
				var not_lock = "<img src='images/not_lock.png' onclick='lock(this," + res[i].id + ")'>";
				var toLock = res[i].status == 1 ? lock : not_lock;
				var toDelete = "<a href='toDeleteUser.action?id=" + res[i].id + "'><img src='images/deletes.png'></a>";
				var toEdit = "<a href='toupdateUserByid.action?id=" + res[i].id + "'><img src='images/editors.png'></a>";
				var qx = "<a href='toqxByid.action?id=" + res[i].id + "'>权限</a>";
				//data[i] = {id:res[i].id,name:res[i].role_type,gender:res[i].name,age:res[i].phone,操作:"<a href='toupdateUserByid.action?id="+res[i].id+"'><img src='images/editors.png'></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href='toDeleteUser.action?id="+res[i].id+"'><img src='images/deletes.png'></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+toLock};
				data[i] = {
					id : res[i].id,
					role_type : res[i].role_type,
					name : res[i].name,
					username : res[i].username,
					phone : res[i].phone,
					创建时间 : getTime(res[i].create_time),
					修改时间 : getTime(res[i].update_time),
					操作 : toEdit + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + toDelete + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + toLock + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + qx
				};
			}

			var cs = new table({
				"currentPageNum" : 2,
				"tableId" : "cs_table", //必须
				"headers" : [ "序号", "权限", "姓名", "账号", "手机号", "创建时间", "修改时间", "操作" ],
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
</script>
<script type="text/javascript">
	$(function() {
		var a = "toAddList.action";
		var b = "###";
		var c = "${roletype}";

		if (c == '管理员' || c == '财务总监') {
			$("#aid").attr("href", a);
		} else {
			$("#aid").attr("href", b);
		}
	})
</script>
<script type="text/javascript">
	function getTime(t) {
		var _time = new Date(t);
		var year = _time.getFullYear(); //2017
		var month = _time.getMonth() + 1; //7
		var date = _time.getDate();//10
		var hour = _time.getHours() < 10 ? "0" + _time.getHours() : _time.getHours(); //10
		var minute = _time.getMinutes() < 10 ? "0" + _time.getMinutes() : _time.getMinutes();//56
		var second = _time.getSeconds() < 10 ? "0" + _time.getSeconds() : _time.getSeconds(); //15

		return year + "年" + month + "月" + date + "日   " + hour + ":" + minute + ":" + second;//这里自己按自己需要的格式拼接

	}
</script>
</html>