<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>分公司地区</title>
<jsp:include page="nav.jsp"></jsp:include>
<div class="content">
	<div class="sub-box">
		<div class="sel-list">
			<select class="select_style" id="sid" onchange="getlist()">
				<c:forEach items="${branchOfficeList}" var="list">
					<option value="${list.area}">${list.area}</option>
				</c:forEach>
			</select> <select class="select_style" id="yearid" onchange="getlist()">
				<option value="2016">2016年</option>
				<option value="2017">2017年</option>
				<option value="2018">2018年</option>
			</select> <select class="select_style" id="monthid" onchange="getlist()">
				<option value="0">1月</option>
				<option value="1">2月</option>
				<option value="2">3月</option>
				<option value="3">4月</option>
				<option value="4">5月</option>
				<option value="5">6月</option>
				<option value="6">7月</option>
				<option value="7">8月</option>
				<option value="8">9月</option>
				<option value="9">10月</option>
				<option value="10">11月</option>
				<option value="11">12月</option>
			</select> <select class="select_style" id="select_type">
				<option value="1" selected>提成表</option>
				<option value="2">工资表</option>
			</select>
		</div>
		<div class="sub-right">
			<input type="text" value="" name="name" class="sub-search" placeholder="请输入姓名" id="find" onkeyup="findDeduct()" />
		</div>
	</div>
	<table id="cs_table" class="table_tbs" rules=rows>
	</table>
	<p class="money_statistical">
		合计：新标总金额为<span id="one"></span>元 续贷总金额为<span id="two"></span>元
	</p>
</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
<script type="text/javascript">
	$(function() {
		navSelected(2);
	});
	/*跳转*/
	$('#select_type').change(function() {
		var types = $('#select_type option:selected').text();
		if (types == '提成表') {
			window.location.href = "showArea.action";
		} else if (types == '工资表') {
			window.location.href = "demandWage.action";
		}
	});
	function findDeduct() {
		var sname = $("#find").val();
		var url = "showDeduct.action?name=" + sname;
		$.getJSON(url, function(res) {
			var data = [];

			for (var i = 0; i < res.length; i++) {
				data[i] = {
					id : res[i].id,
					职位 : res[i].duty,
					姓名 : res[i].name,
					电话 : res[i].phone,
					提成 : res[i].deduct == null ? 0 : res[i].deduct,
					操作 : res[i].duty == '业务员' ? "<a href='showDetail.action?id=" + res[i].id + "&time=" + res[i].time + "'>详情</a>" : ""
				};
			}
			console.log(data)
			var cs = new table({
				"tableId" : "cs_table", //必须
				"headers" : [ "序号", "职位", "姓名", "电话", "提成", "操作" ], //必须
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

	function getlist() {
		var c = $('#sid option:selected').text();
		var d = $('#yearid option:selected').text();
		var e = $('#monthid option:selected').text();
		var url = "getdemands.action?area=" + c + "&year=" + d + "&month=" + e;
		var urls = "getdemandsum.action?area=" + c + "&year=" + d + "&month=" + e;
		$.getJSON(urls, function(res) {

			var a = res[0].sumXb

			var b = res[0].sumXd
			$("#one").text(a);
			$("#two").text(b);
		})

		$.getJSON(url, function(res) {
			var data = [];
			for (var i = 0; i < res.length; i++) {
				data[i] = {
					idss : res[i].id,
					name : res[i].duty,
					gender : res[i].name,
					age : res[i].phone,
					address : res[i].extract,
					操作 : res[i].duty == '业务员' ? "<a href='showDetail.action?id=" + res[i].id + "&time=" + res[i].time + "'>详情</a>" : ""
				};

			}
			var cs = new table({
				"tableId" : "cs_table", //必须
				"headers" : [ "序号", "职位", "姓名", "电话", "提成", "操作" ], //必须
				"data" : data, //必须
				"displayNum" : 5, //必须  默认 10
				"groupDataNum" : 10
			//可选  默认 10
			});
			if (data.length == 0) {
				$('#cs_table').append('<img class="nodata" src="images/nodata.png">');
			}
		});

	};
	$(function() {
		var myDate = new Date();
		console.log(myDate.getFullYear()); //获取完整的年份(4位,1970-????)
		console.log(myDate.getMonth()); //获取当前月份(0-11,0代表1月)
		$('#yearid').val(myDate.getFullYear());
		$('#monthid').val(myDate.getMonth() - 1);
		var c = $('#sid option:selected').text();
		var d = myDate.getFullYear() + '年';
		var e = myDate.getMonth() + '月';
		var url = "getdemands.action?area=" + c + "&year=" + d + "&month=" + e;

		var urls = "getdemandsum.action?area=" + c + "&year=" + d + "&month=" + e;
		$.getJSON(urls, function(res) {

			var a = res[0].sumXb
			var b = res[0].sumXd
			$("#one").text(a);
			$("#two").text(b);

		})
		$.getJSON(url, function(res) {

			var data = [];
			if (res.length > 0) {
				for (var i = 0; i < res.length; i++) {

					data[i] = {
						id : res[i].id,
						职位 : res[i].duty,
						姓名 : res[i].name,
						电话 : res[i].phone,
						提成 : res[i].extract,
						操作 : res[i].duty == '业务员' ? "<a href='showDetail.action?id=" + res[i].id + "&time=" + res[i].time + "'>详情</a>" : ""
					};
				}
			}
			console.log(data)
			var cs = new table({
				"tableId" : "cs_table", //必须
				"headers" : [ "序号", "职位", "姓名", "电话", "提成", "操作" ], //必须
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
</html>