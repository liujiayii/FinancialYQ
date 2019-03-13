<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>财务管理-收入费用</title>
<jsp:include page="nav.jsp"></jsp:include>
<div class="content">
	<div class="sub-box" id="fin">

		<!-- <ul class="sub-nav">
			<li><a href="toStaff.action">员工管理</a></li>
			<li><a href="toIncome.action" class="selected">收入费用</a></li>
			<li><a href="toSpend.action">支出费用</a></li>
			<li><a href="toFinanceManager.action">标的管理</a></li>
			<li><a href="toCompany.action">公司管理</a></li>
		</ul> -->
		<div class="sub-right">
			<label>分公司：</label> <select id="area" name="area" class="sub-search" style="width: 110px;">
				<option value="">选择分公司</option>
				<option value="">-- 空 --</option>
				<c:forEach items="${branchOfficeList}" var="list">
					<option value="${list.id}">${list.area}</option>
				</c:forEach>
			</select> <label>查询选项：</label> <select id="searchField" name="searchField" class="sub-search" style="width: 130px;">
				<option value="">选择查询选项</option>
				<option value="">-- 空 --</option>
				<option value="1">摘要</option>
				<option value="2">总金额</option>
			</select>→ <input type="text" id="searchFieldVal" class="sub-search" style="width: 160px;" /> <input type="date" value="" name="name" class="sub-search" placeholder="请输入日期" id="inputDate" style="background: none; width: 150px;" /> <input type="button" value="添加" class="sub-button" onclick="window.location.href='toShowAddIncome.action'" /> <input type="button" value="查询" class="sub-button" onclick="search()" />
		</div>
	</div>
	<div class="sel-list">
		<a href="toIncome.action" class="bold">添加收入表</a> <a href="toServiceMoney.action">服务费表</a> <a href="toWithDraw.action">提现手续费表</a>
	</div>
	<table id="cs_table" class="table_tbs" rules=rows>
	</table>
</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
<script type="text/javascript">
	/*一级导航栏选中 */
	navSelected(4);

	$(function() {

		var c = "${roletype}";
		if (c == '记账') {
			$('.sub-nav li').slice(0, 1).css("display", "none");
			$('.sub-nav li').slice(4, 5).css("display", "none");
		}
		var url = "financialIncome.action";
		$.getJSON(url, function(res) {
			var data = [];
			/* var b="<a href='printIncome.action?id="+res[i].id+"'><b style='color:#00a2ff'>打印</b></a>&nbsp;&nbsp;&nbsp;<a href='toShowfinancialIncome.action?id="+res[i].id+"' ><b style='color:#00a2ff'>详情</b></a>&nbsp;&nbsp;&nbsp;<a href='todeleatefinancialIncome.action?id="+res[i].id+"' ><b style='color:#00a2ff'>作废</b></a>";
			
			var c="<a href='toShowfinancialIncome.action?id="+res[i].id+"' ><b style='color:#00a2ff'>详情</b></a>&nbsp;&nbsp;&nbsp;<a href='todeleatefinancialIncome.action?id="+res[i].id+"' ><b style='color:#00a2ff'>作废</b></a>"; */
			for (var i = 0; i < res.length; i++) {
				data[i] = {
					序号 : res[i].id,

					摘要 : res[i].digest,
					总金额 : res[i].sum,
					时间 : getTime(res[i].creat_time),
					状态 : res[i].state == 0 ? "<img src='images/notEffect.png'/>" : "<img src='images/effect.png'/>",
					操作 : res[i].state == 0 ? "<a href='toShowfinancialIncome.action?id=" + res[i].id
							+ "' ><b style='color:#00a2ff'>详情</b></a>&nbsp;&nbsp;&nbsp;<a href='todeleatefinancialIncome.action?id=" + res[i].id
							+ "' ><b style='color:#00a2ff'>作废</b></a>&nbsp;&nbsp;&nbsp;<a href='toUpdateFinancialIncome.action?id=" + res[i].id
							+ "' ><b style='color:#00a2ff'>修改</b></a>" : "<a href='printIncome.action?id=" + res[i].id
							+ "'><b style='color:#00a2ff'>打印</b></a>&nbsp;&nbsp;&nbsp;<a href='toShowfinancialIncome.action?id=" + res[i].id
							+ "' ><b style='color:#00a2ff'>详情</b></a>&nbsp;&nbsp;&nbsp;<a href='todeleatefinancialIncome.action?id=" + res[i].id
							+ "' ><b style='color:#00a2ff'>作废</b></a>"
				};
			}
			console.log(data)
			var cs = new table({
				"tableId" : "cs_table", //必须
				"headers" : [ "序号", "摘要", "总金额", "时间", "状态", "操作" ], //必须
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

	//搜索日期
	$('#inputDate').changes(
			function() {
				var inputDate = $("#inputDate").val().toString();

				var url = "financialIncomebytime.action?time=" + inputDate;
				$.getJSON(url, function(res) {
					var data = [];
					/* var b="<a href='printIncome.action?id="+res[i].id+"'><b style='color:#00a2ff'>打印</b></a>&nbsp;&nbsp;&nbsp;<a href='toShowfinancialIncome.action?id="+res[i].id+"' ><b style='color:#00a2ff'>详情</b></a>&nbsp;&nbsp;&nbsp;<a href='todeleatefinancialIncome.action?id="+res[i].id+"' ><b style='color:#00a2ff'>作废</b></a>";
					
					var c="<a href='toShowfinancialIncome.action?id="+res[i].id+"' ><b style='color:#00a2ff'>详情</b></a>&nbsp;&nbsp;&nbsp;<a href='todeleatefinancialIncome.action?id="+res[i].id+"' ><b style='color:#00a2ff'>作废</b></a>"; */
					for (var i = 0; i < res.length; i++) {
						data[i] = {
							序号 : res[i].id,
							摘要 : res[i].digest,
							总金额 : res[i].sum,
							时间 : getTime(res[i].creat_time),
							状态 : res[i].state == 0 ? "<img src='images/notEffect.png'/>" : "<img src='images/effect.png'/>",
							操作 : res[i].state == 0 ? "<a href='toShowfinancialIncome.action?id=" + res[i].id
									+ "' ><b style='color:#00a2ff'>详情</b></a>&nbsp;&nbsp;&nbsp;<a href='todeleatefinancialIncome.action?id=" + res[i].id
									+ "' ><b style='color:#00a2ff'>作废</b></a>" : "<a href='printIncome.action?id=" + res[i].id
									+ "'><b style='color:#00a2ff'>打印</b></a>&nbsp;&nbsp;&nbsp;<a href='toShowfinancialIncome.action?id=" + res[i].id
									+ "' ><b style='color:#00a2ff'>详情</b></a>&nbsp;&nbsp;&nbsp;<a href='todeleatefinancialIncome.action?id=" + res[i].id
									+ "' ><b style='color:#00a2ff'>作废</b></a>"
						};
					}
					console.log(data)
					var cs = new table({
						"tableId" : "cs_table", //必须
						"headers" : [ "序号", "摘要", "总金额", "时间", "状态", "操作" ], //必须
						"data" : data, //必须
						"displayNum" : 5, //必须  默认 10
						"groupDataNum" : 10
					//可选  默认 10

					});

				});
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

	function firstSel() {

		var area = $("#area").val();//得到第一个下拉列表的值

		var url = "financialIncomebyarea.action?area=" + area;
		$.getJSON(url, function(res) {
			var data = [];
			/* var b="<a href='printIncome.action?id="+res[i].id+"'><b style='color:#00a2ff'>打印</b></a>&nbsp;&nbsp;&nbsp;<a href='toShowfinancialIncome.action?id="+res[i].id+"' ><b style='color:#00a2ff'>详情</b></a>&nbsp;&nbsp;&nbsp;<a href='todeleatefinancialIncome.action?id="+res[i].id+"' ><b style='color:#00a2ff'>作废</b></a>";
			
			var c="<a href='toShowfinancialIncome.action?id="+res[i].id+"' ><b style='color:#00a2ff'>详情</b></a>&nbsp;&nbsp;&nbsp;<a href='todeleatefinancialIncome.action?id="+res[i].id+"' ><b style='color:#00a2ff'>作废</b></a>"; */
			for (var i = 0; i < res.length; i++) {
				data[i] = {
					序号 : res[i].id,

					摘要 : res[i].digest,
					总金额 : res[i].sum,
					时间 : getTime(res[i].times),
					状态 : res[i].state == 0 ? "<img src='images/notEffect.png'/>" : "<img src='images/effect.png'/>",
					操作 : res[i].state == 0 ? "<a href='toShowfinancialIncome.action?id=" + res[i].id
							+ "' ><b style='color:#00a2ff'>详情</b></a>&nbsp;&nbsp;&nbsp;<a href='todeleatefinancialIncome.action?id=" + res[i].id
							+ "' ><b style='color:#00a2ff'>作废</b></a>" : "<a href='printIncome.action?id=" + res[i].id
							+ "'><b style='color:#00a2ff'>打印</b></a>&nbsp;&nbsp;&nbsp;<a href='toShowfinancialIncome.action?id=" + res[i].id
							+ "' ><b style='color:#00a2ff'>详情</b></a>&nbsp;&nbsp;&nbsp;<a href='todeleatefinancialIncome.action?id=" + res[i].id
							+ "' ><b style='color:#00a2ff'>作废</b></a>"
				};
			}
			console.log(data)
			var cs = new table({
				"tableId" : "cs_table", //必须
				"headers" : [ "序号", "摘要", "总金额", "时间", "状态", "操作" ], //必须
				"data" : data, //必须
				"displayNum" : 5, //必须  默认 10
				"groupDataNum" : 10
			//可选  默认 10

			});

		});
	}
</script>
<!-- 搜索 -->
<script type="text/javascript">
	var url = "/searchIncome.action";

	function search() {
		//地区
		var district_id = $("#area").val();
		//查询选项
		var searchField = $("#searchField").val();
		//摘要
		var digest;
		//总金额
		var sum;

		if (searchField == 1) {
			digest = $("#searchFieldVal").val();
		} else if (searchField == 2) {
			sum = $("#searchFieldVal").val();
		}

		//查询日期
		var inputDate = $("#inputDate").val();
		var obj = {
			"district_id" : district_id,
			"digest" : digest,
			"sum" : sum,
			"creat_time" : inputDate
		};
		$.ajax({
			url : url,
			data : JSON.stringify(obj),
			type : 'post',
			contentType : 'application/json;charset=utf-8',
			dataType : 'json',
			success : function(res) {
				var data = [];
				for (var i = 0; i < res.length; i++) {
					data[i] = {
						序号 : res[i].id,

						摘要 : res[i].digest,
						总金额 : res[i].sum,
						时间 : getTime(res[i].creat_time),
						状态 : res[i].state == 0 ? "<img src='images/notEffect.png'/>" : "<img src='images/effect.png'/>",
						操作 : res[i].state == 0 ? "<a href='toShowfinancialIncome.action?id=" + res[i].id
								+ "' ><b style='color:#00a2ff'>详情</b></a>&nbsp;&nbsp;&nbsp;<a href='todeleatefinancialIncome.action?id=" + res[i].id
								+ "' ><b style='color:#00a2ff'>作废</b></a>" : "<a href='printIncome.action?id=" + res[i].id
								+ "'><b style='color:#00a2ff'>打印</b></a>&nbsp;&nbsp;&nbsp;<a href='toShowfinancialIncome.action?id=" + res[i].id
								+ "' ><b style='color:#00a2ff'>详情</b></a>&nbsp;&nbsp;&nbsp;<a href='todeleatefinancialIncome.action?id=" + res[i].id
								+ "' ><b style='color:#00a2ff'>作废</b></a>"
					};
				}
				console.log(data)
				var cs = new table({
					"tableId" : "cs_table", //必须
					"headers" : [ "序号", "摘要", "总金额", "时间", "状态", "操作" ], //必须
					"data" : data, //必须
					"displayNum" : 5, //必须  默认 10
					"groupDataNum" : 10
				//可选  默认 10

				});
			}
		});

	}
</script>

</html>