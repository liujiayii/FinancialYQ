<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>已打回</title>
<jsp:include page="nav.jsp"></jsp:include>
<div class="content">
	<div class="sub-box" id='risk'>
		<!-- <ul class="sub-nav">
						<li><a href="toShowAll.action">全部列表</a></li>
						<li><a href="toShowCheked.action">已审核</a></li>
						<li><a href="toShowUnchecked.action">未审核</a></li>
						<li><a href="toShowBeenBack.action" class="selected">已打回</a></li>
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

		<!-- <div class="Renewloans" onclick="window.location.href='toInterestAddfk.action'">
					<img src="images/refinance.png" />
					<h2>利息</h2>
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
		<div class="new_standard" onclick="window.location.href='toPrincipalcw.action'">
			<img src="images/new_norm.png" />
			<h2>本息</h2>
		</div>
		<!-- <div class="Renewloans" onclick="window.location.href='toInterestAddfk.action'">
					<img src="images/refinance.png" />
					<h2>利息</h2>
				</div> -->
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
	/*一级导航栏选中 */
	navSelected(0);
	$(function() {
		$('.sub-button').click(function() {
			$('.show_next').show();
		});
	});
	$(".show_img").click(function() {
		$(".show_next").hide();
	});
	$(function() {
		var c = "${roletype}";
		if (c == '出纳') {
			$(".sub-button").attr("type", "hidden");
		}
	});
</script>
<script type="text/javascript">
	function findGrade() {
		var c = "${roletype}";
		var grade = $("#inputGrade").val();
		var url;
		if (c == '风控') {
			if (grade.trim() == '') {
				url = "toFindBeenBack.action";
			} else {
				url = "toFindGradeByBeenback.action?grade=" + grade;
			}

		} else if (c == '记账' || c == '财务总监' || c == '主管') {
			if (grade.trim() == '') {
				url = "toFindBeenBackcw.action";
			} else {
				url = "toFindGradeByBeenbackCw.action?grade=" + grade;
			}
		} else {
			url = "toFindBeenBack.action";
		}

		$.getJSON(url, function(res) {

			var data = [];
			for (var i = 0; i < res.length; i++) {
				//续贷修改 
				var xg1 = "<b style='color:#0CA1EF;cursor: pointer;' onclick=\"window.location.href='toUpdates.action?id=" + res[i].id + "'\">修改</b>";
				//新标修改
				var xg2 = "<b style='color:#0CA1EF;cursor: pointer;' onclick=\"window.location.href='toUpdate.action?id=" + res[i].id + "'\">修改</b>";
				//风控本金修改
				var xg3 = "<b style='color:#0CA1EF;cursor: pointer;' onclick=\"window.location.href='toUpdatefkbj.action?id=" + res[i].id + "'\">修改</b>";
				//财务本金修改
				var xg4 = "<b style='color:#0CA1EF;cursor: pointer;' onclick=\"window.location.href='toUpdatecwbj.action?id=" + res[i].id + "'\">修改</b>";
				//风控利息修改
				var xg5 = "<b style='color:#0CA1EF;cursor: pointer;' onclick=\"window.location.href='toUpdatecwlx.action?id=" + res[i].id + "'\">修改</b>";
				//财务利息修改
				var xg6 = "<b style='color:#0CA1EF;cursor: pointer;' onclick=\"window.location.href='toUpdatefklx.action?id=" + res[i].id + "'\">修改</b>";

				var state;//续贷新标本金利息
				var operation; //修改或审核链接  风控账号只有修改
				var showDetail;//显示详情链接
				if (res[i].audit_id == 0) {
					state = "续贷";
					operation = xg1;
					showDetail = "<b style='color:#0CA1EF;cursor: pointer;' onclick=\"window.location.href='toShowDetails.action?id=" + res[i].id + "'\">查看详情</b>";
				} else if (res[i].audit_id == 1) {
					state = "新标";
					operation = xg2;
					showDetail = "<b style='color:#0CA1EF;cursor: pointer;'onclick=\"window.location.href='toShowDetails1.action?id=" + res[i].id + "'\">查看详情</b>";
				} else if (res[i].audit_id == 2) {
					state = "本息";
					/* if(res[i].state==0){ //res[i].state判断是谁填的表 0代表风控 1代表财务
						showDetail="<b style='color:#0CA1EF;cursor: pointer;'onclick=\"window.location.href='toShowPrincipalfk.action?id="+res[i].id+"'\">查看详情</b>";
						operation=xg3;
					}else if(res[i].state==1){ */
					operation = xg4;
					showDetail = "<b style='color:#0CA1EF;cursor: pointer;'onclick=\"window.location.href='toShowPrincipalcw.action?id=" + res[i].id + "'\">查看详情</b>";
					/* } */

				}/* else if(res[i].audit_id==3){
							  			state="利息";
							  			if(res[i].state==0){ //res[i].state判断是谁填的表 0代表风控 1代表财务
							  				operation=xg5;
							  				showDetail="<b style='color:#0CA1EF;cursor: pointer;'onclick=\"window.location.href='toShowInterestfk.action?id="+res[i].id+"'\">查看详情</b>";
							  			}else if(res[i].state==1){
							  				operation=xg6;
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
					状态 : "<img src='images/Unapprove.png'>",
					操作 : operation,
					详情 : showDetail
				};
			}
			console.log(data)
			var cs = new table({
				"tableId" : "cs_table", //必须
				"headers" : [ "序号", "标号", "姓名", "地区", "金额", "期限", "提前还款", "利息", "类型", "逾期", "状态", "操作", "详情" ], //必须
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
</script>
<script type="text/javascript">
	$(function() {
		var c = "${roletype}";
		var url;
		if (c == '风控') {
			url = "toFindBeenBack.action";
		} else if (c == '记账') {
			url = "toFindBeenBackcw.action";
		} else {
			url = "toFindBeenBack.action";
		}
		$.getJSON(url, function(res) {

			var data = [];
			for (var i = 0; i < res.length; i++) {
				//续贷修改 
				var xg1 = "<b style='color:#0CA1EF;cursor: pointer;' onclick=\"window.location.href='toUpdates.action?id=" + res[i].id + "'\">修改</b>";
				//新标修改
				var xg2 = "<b style='color:#0CA1EF;cursor: pointer;' onclick=\"window.location.href='toUpdate.action?id=" + res[i].id + "'\">修改</b>";
				//风控本金修改
				var xg3 = "<b style='color:#0CA1EF;cursor: pointer;' onclick=\"window.location.href='toUpdatefkbj.action?id=" + res[i].id + "'\">修改</b>";
				//财务本金修改
				var xg4 = "<b style='color:#0CA1EF;cursor: pointer;' onclick=\"window.location.href='toUpdatecwbj.action?id=" + res[i].id + "'\">修改</b>";
				//风控利息修改
				var xg5 = "<b style='color:#0CA1EF;cursor: pointer;' onclick=\"window.location.href='toUpdatefklx.action?id=" + res[i].id + "'\">修改</b>";
				//财务利息修改
				var xg6 = "<b style='color:#0CA1EF;cursor: pointer;' onclick=\"window.location.href='toUpdatecwlx.action?id=" + res[i].id + "'\">修改</b>";

				var state;//续贷新标本金利息

				var operation; //修改或审核链接  风控账号只有修改
				var showDetail;//显示详情链接
				if (res[i].audit_id == 0) {
					state = "续贷";
					if (c == '风控') {

						operation = xg1;
					} else if (c == '记账' || c == '财务总监' || c == '主管') {
						operation = '';
					}
					showDetail = "<b style='color:#0CA1EF;cursor: pointer;' onclick=\"window.location.href='toShowDetails.action?id=" + res[i].id + "'\">查看详情</b>";
				} else if (res[i].audit_id == 1) {
					state = "新标";
					if (c == '风控') {
						operation = xg2;

					} else if (c == '记账' || c == '财务总监') {
						operation = '';
					}

					showDetail = "<b style='color:#0CA1EF;cursor: pointer;'onclick=\"window.location.href='toShowDetails1.action?id=" + res[i].id + "'\">查看详情</b>";
				} else if (res[i].audit_id == 2) {
					state = "本息";
					if (res[i].state == 0) { //res[i].state判断是谁填的表 0代表风控 1代表财务
						showDetail = "<b style='color:#0CA1EF;cursor: pointer;'onclick=\"window.location.href='toShowPrincipalfk.action?id=" + res[i].id + "'\">查看详情</b>";

						if (c == '风控') {

							operation = xg3;
						} else if (c == '记账' || c == '财务总监' || c == '主管') {
							operation = '';
						}
					} else if (res[i].state == 1) {
						if (c == '风控') {

							operation = '';
						} else if (c == '记账' || c == '财务总监') {
							operation = xg4;

						}
						showDetail = "<b style='color:#0CA1EF;cursor: pointer;'onclick=\"window.location.href='toShowPrincipalcw.action?id=" + res[i].id + "'\">查看详情</b>";
					}

				}/* else if(res[i].audit_id==3){
						  			state="利息";
						  			if(res[i].state==0){ //res[i].state判断是谁填的表 0代表风控 1代表财务
										if(c=='风控'){
								  			
							  				operation=xg5;
							  			}else if(c=='记账'){
						  					operation='';
							  			}	
						  				showDetail="<b style='color:#0CA1EF;cursor: pointer;'onclick=\"window.location.href='toShowInterestfk.action?id="+res[i].id+"'\">查看详情</b>";
						  			}else if(res[i].state==1){
										if(c=='风控'){
								  			
							  				operation='';
							  			}else if(c=='记账'){
						  					operation=xg6;
							  			}	
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
					状态 : "<img src='images/Unapprove.png'>",
					操作 : operation,

					详情 : showDetail
				};
			}
			console.log(data)
			var cs = new table({
				"tableId" : "cs_table", //必须
				"headers" : [ "序号", "标号", "姓名", "地区", "金额", "期限", "提前还款", "利息", "类型", "逾期", "状态", "操作", "详情" ], //必须
				"data" : data, //必须
				"displayNum" : 5, //必须  默认 10
				"groupDataNum" : 10
			//可选  默认 10
			});
			if (data.length == 0) {
				$('#cs_table').append('<img class="nodata" src="images/nodata.png">');
			}
		});
		if (c == '出纳') {
			var url = "toFindBeenBack.action";
			$.getJSON(url, function(res) {

				var data = [];
				for (var i = 0; i < res.length; i++) {
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
						状态 : "<img src='images/Unapprove.png'>",
						详情 : showDetail
					};
				}
				console.log(data)
				var cs = new table({
					"tableId" : "cs_table", //必须
					"headers" : [ "序号", "标号", "姓名", "地区", "金额", "期限", "提前还款", "利息", "类型", "逾期", "状态", "详情" ], //必须
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
	});
</script>
</html>