<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>财务管理-收入费用-服务费表</title>
<jsp:include page="nav.jsp"></jsp:include>
<div class="content">
	<div class="sub-box">

		<!--  <ul class="sub-nav">
						<li><a href="toStaff.action">员工管理</a></li>
						<li><a href="toIncome.action" class="selected">收入费用</a></li>
						<li><a href="toSpend.action">支出费用</a></li>
						<li><a href="toFinanceManager.action">标的管理</a></li>
						<li><a href="toCompany.action">公司管理</a></li>
					</ul>-->
		<div class="sub-right">
			<input type="button" value="打印本页" class="sub-button" onclick="dayin()" />

		</div>
	</div>
	<div class="sel-list">
		<a href="toIncome.action">添加收入表</a> <a href="toServiceMoney.action" class="bold">服务费表</a> <a href="toWithDraw.action">提现手续费表</a>
	</div>
	<table id="cs_table" class="table_tbs" rules=rows>
	</table>
</div>
<jsp:include page="footer.jsp"></jsp:include>

</body>
<script type="text/javascript">
	function dayin() {
		var demo = $(".current").text();
		console.log(demo);
		window.location.href = "financialServiceMoneyLimitFive.action?currentpage=" + demo;
	}
</script>
<script type="text/javascript">
	/*一级导航栏选中 */
	navSelected(4);

	$(function() {
		var url = "financialServiceMoney.action";
		$.getJSON(url, function(res) {
			var data = [];

			console.log(res.length + "res.length")
			for (var i = 0; i < res.length; i++) {
				data[i] = {
					序号 : res[i].id,
					标题 : res[i].incomeAccount,
					时间 : getTime(res[i].time),
					状态 : "<img src='images/effect.png'/>",
					操作 : "<a href='ShowServiceMoney.action?id=" + res[i].id + "' ><b style='color:#00a2ff'>详情</b></a>"
				};
			}
			console.log(data)
			var cs = new table({
				"tableId" : "cs_table", //必须
				"headers" : [ "序号", "收入项目标题", "时间", "状态", "操作" ], //必须
				"data" : data, //必须
				"displayNum" : 5, //必须  默认 10
				"groupDataNum" : 10
			//可选  默认 10

			});
			if (data.length == 0) {
				//$('#cs_table').children("tbody").children(":first").append('<td colspan="7" style="background:#ccc;">暂无数据</td>');
				$('#cs_table').append('<img class="nodata" src="images/nodata.png">');
			}
		});
	});
</script>
<script type="text/javascript">
	/*跳转*/
	$('#select_type').change(function() {
		var types = $('#select_type option:selected').text();
		if (types == '添加收入表') {
			window.location.href = "toIncome.action";
		} else if (types == '服务费表') {
			window.location.href = "toServiceMoney.action";
		} else if (types == '提现手续费表') {
			window.location.href = "toWithDraw.action";
		}
	});
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