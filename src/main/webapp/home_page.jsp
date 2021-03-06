<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>首页</title>
<jsp:include page="nav.jsp"></jsp:include>
<div class="content">
	<div class="sub-box">
		<!-- <ul class="sub-nav" id='risk'>
						<li><a href="toShowAll.action" class="selected">全部列表</a></li>						
						<li><a href="toShowCheked.action">已审核</a></li>					
						<li><a href="toShowUnchecked.action">未审核</a></li>
						<li><a href="toShowBeenBack.action">已打回</a></li>
						<li><a href="toShowBeenVoid.action">已作废</a></li>
					</ul> -->
		<div class="sub-right" id="tf">
			<input type="text" value="" name="grade" class="sub-search" placeholder="请输入标号" id="inputGrade" onkeyup="findGrade()" /> <input type="button" value="新建审核" class="sub-button" />
		</div>
	</div>
	<table id="cs_table" class="table_tbs" rules=rows>
	</table>
</div>

<c:if test="${roletype=='风控'}">
	<div class="show_next" style="margin-left: -630px;">
		<div class="new_standard" onclick="window.location.href='addFinances.action'">
			<img src="images/new_norm.png" />
			<h2>新标</h2>
		</div>
		<div class="Renewloans" onclick="window.location.href='addFinance.action'">
			<img src="images/refinance.png" />
			<h2>续贷</h2>
		</div>
		<!-- <div class="new_standard" onclick="window.location.href='toPrincipalfk.action'">
						<img src="images/new_norm.png"  />
						<h2>本息</h2>
					</div> -->

		<img src="images/4.jpg" width="40" height="40" style="position: absolute; right: 20px; top: 5px; cursor: pointer;" class="show_img" />
	</div>
</c:if>
<c:if test="${roletype=='财务总监' or roletype=='主管'}">
	<div class="show_next" style="margin-left: -630px;">
		<div class="new_standard" onclick="window.location.href='addFinances.action'">
			<img src="images/new_norm.png" />
			<h2>新标</h2>
		</div>
		<div class="Renewloans" onclick="window.location.href='addFinance.action'">
			<img src="images/refinance.png" />
			<h2>续贷</h2>
		</div>
		<!-- <div class="new_standard" onclick="window.location.href='toPrincipalfk.action'">
						<img src="images/new_norm.png"  />
						<h2>本息</h2>
					</div> -->
		<div class="Renewloans" onclick="window.location.href='toPrincipalcw.action'">
			<img src="images/refinance.png" />
			<h2>本息</h2>
		</div>
		<img src="images/4.jpg" width="40" height="40" style="position: absolute; right: 20px; top: 5px; cursor: pointer;" class="show_img" />
	</div>
</c:if>

<c:if test="${roletype=='记账'}">
	<div class="show_next" style="margin-left: -315px;">
		<div class="new_standard" onclick="window.location.href='toPrincipalcw.action'">
			<img src="images/new_norm.png" />
			<h2>本息</h2>
		</div>

		<img src="images/4.jpg" width="40" height="40" style="position: absolute; right: 20px; top: 5px; cursor: pointer;" class="show_img" />
	</div>
</c:if>




<jsp:include page="footer.jsp"></jsp:include>
</body>
<script type="text/javascript">
	$(function() {
		//获取权限角色
		var c = "${roletype}";

		//如果用户是管理员或者是风控

		$('.sub-box input.sub-button').click(function() {
			//可以点击创建风控表
			$('.show_next').show();
		});
		//如果当前登录的是出纳则只能查看详情
		if (c == '出纳') {
			$(".sub-button").attr("type", "hidden");
		}
	});
	$(".show_img").click(function() {
		$(".show_next").hide();
	})
</script>
<!-- 按标号搜索 -->
<script type="text/javascript">
	function findGrade() {
		var grade = $("#inputGrade").val();
		if ("${roletype}" == '风控') {
			if (grade.trim() == '') {
				url = "financeList.action";
			} else {
				url = "toFindGrades.action?grade=" + grade;
			}
		} else if ("${roletype}" == '记账' || c == '财务总监' || c == '主管') {
			if (grade.trim() == '') {
				url = "financeListByCw.action";
			} else {
				url = "toFindGradescw.action?grade=" + grade;
			}
		} else {
			url = "financeList.action";
		}

		$.getJSON(url, function(res) {

			var types;
			var type;

			var data = [];
			for (var i = 0; i < res.length; i++) {
				types = res[i].type;
				if (types == 1) {
					type = "<img src='images/audited.png'>";
				} else if (types == 0) {
					type = "<img src='images/unchecked.png'>";
				} else if (types == 2) {
					type = "<img src='images/Unapprove.png'>";
				} else if (types == 3) {

					type = "<img src='images/cancellation.png'>";
				}

				var state;//续贷新标本金利息
				var showDetail;//显示详情链接
				if (res[i].audit_id == 0) {
					state = "续贷";
					showDetail = "<b style='color:#0CA1EF;cursor: pointer;' onclick=\"window.location.href='toShowDetails.action?id=" + res[i].id + "'\">查看详情</b>";
				} else if (res[i].audit_id == 1) {
					state = "新标";
					showDetail = "<b style='color:#0CA1EF;cursor: pointer;'onclick=\"window.location.href='toShowDetails1.action?id=" + res[i].id + "'\">查看详情</b>";
				} else if (res[i].audit_id == 2) {
					state = "本息";
					//res[i].state判断是谁填的表 0代表风控 1代表财务
					showDetail = "<b style='color:#0CA1EF;cursor: pointer;'onclick=\"window.location.href='toShowPrincipalfk.action?id=" + res[i].id + "'\">查看详情</b>";

				}
				data[i] = {
					序号 : res[i].id,
					标号 : res[i].grade,
					姓名 : res[i].name,
					地区 : res[i].area,
					金额 : res[i].money,
					期限 : res[i].day,
					提前还款 : res[i].is_payment == 0 ? "<span style='color:green'>否 </span>" : "<span style='color:red'>是</span>",
					利息 : res[i].interest,
					类型 : state,
					逾期 : res[i].is_overdue == 0 ? "<span style='color:green'>否 </span>" : "<span style='color:red'>是</span>",
					状态 : type,
					详情 : showDetail
				};
			}
			console.log(data);
			var cs = new table({
				"tableId" : "cs_table", //必须
				"headers" : [ "序号", "标号", "姓名", "地区", "金额", "期限", "提前还款", "利息", "类型", "逾期", "状态", "详情" ], //必须
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
	}
</script>
<script type="text/javascript">
	$(function() {
		//角色权限
		var role = "${roletype}";
		var url;
		var url2 = "financeListByCw.action";
		var url3 = "financeList.action";
		if (role == '记账' || role == '出纳' || role == '财务总监' || role == '主管') {
			url = url2;
		} else if (role == '风控') {
			url = url3;
		}
		$.getJSON(url, function(res) {

			var types;
			var type;

			var data = [];
			for (var i = 0; i < res.length; i++) {
				types = res[i].type;
				if (types == 1) {
					type = "<img src='images/audited.png'>";
				} else if (types == 0) {
					type = "<img src='images/unchecked.png'>";
				} else if (types == 2) {
					type = "<img src='images/Unapprove.png'>";
				} else if (types == 3) {

					type = "<img src='images/cancellation.png'>";
				}
				var state;//续贷新标本金利息
				var showDetail;//显示详情链接
				if (res[i].audit_id == 0) {
					state = "续贷";
					showDetail = "<b style='color:#0CA1EF;cursor: pointer;' onclick=\"window.location.href='toShowDetails.action?id=" + res[i].id + "'\">查看详情</b>"
				} else if (res[i].audit_id == 1) {
					state = "新标";
					showDetail = "<b style='color:#0CA1EF;cursor: pointer;'onclick=\"window.location.href='toShowDetails1.action?id=" + res[i].id + "'\">查看详情</b>";
				} else if (res[i].audit_id == 2) {
					state = "本息";
					/* if(res[i].state==0){ //res[i].state判断是谁填的表 0代表风控 1代表财务
						showDetail="<b style='color:#0CA1EF;cursor: pointer;'onclick=\"window.location.href='toShowPrincipalfk.action?id="+res[i].id+"'\">查看详情</b>";
					}else if(res[i].state==1){ */
					showDetail = "<b style='color:#0CA1EF;cursor: pointer;'onclick=\"window.location.href='toShowPrincipalcw.action?id=" + res[i].id + "'\">查看详情</b>";
					/* } */

				}/* else if(res[i].audit_id==3){
					  			state="利息";
					  			if(res[i].state==0){ //res[i].state判断是谁填的表 0代表风控 1代表财务
					  				showDetail="<b style='color:#0CA1EF;cursor: pointer;'onclick=\"window.location.href='toShowInterestfk.action?id="+res[i].id+"'\">查看详情</b>";
					  			}else if(res[i].state==1){
					  				showDetail="<b style='color:#0CA1EF;cursor: pointer;'onclick=\"window.location.href='toShowInterestcw.action?id="+res[i].id+"'\">查看详情</b>";
					  			}
					  		 } */

				data[i] = {
					序号 : res[i].id,
					标号 : res[i].grade,
					姓名 : res[i].name,
					地区 : res[i].area,
					金额 : res[i].money,
					期限 : res[i].day,
					提前还款 : res[i].is_payment == 0 ? "<span style='color:green'>否 </span>" : "<span style='color:red'>是</span>",
					利息 : res[i].interest,
					类型 : state,
					逾期 : res[i].is_overdue == 0 ? "<span style='color:green'>否 </span>" : "<span style='color:red'>是</span>",
					状态 : type,
					详情 : showDetail
				};
			}

			console.log(data);
			var cs = new table({
				"tableId" : "cs_table", //必须
				"headers" : [ "序号", "标号", "姓名", "地区", "金额", "期限", "提前还款", "利息", "类型", "逾期", "状态", "详情" ], //必须
				"data" : data, //必须
				"displayNum" : 5, //必须  默认 10
				"groupDataNum" : 10,//可选  默认 10
				"currentPageNum" : 3
			});
			if (data.length == 0) {
				//$('#cs_table').children("tbody").children(":first").append('<td colspan="7" style="background:#ccc;">暂无数据</td>');
				$('#cs_table').append('<img class="nodata" src="images/nodata.png">');
			}
		});
	});
	$(function() {
		navSelected(0);
	})
</script>

</html>
