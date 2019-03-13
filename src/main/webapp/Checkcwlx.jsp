<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>财务利息审核</title>
<style type="text/css">
#times:hover {
	border: 1.5px solid #d3c6ff;
}

.control_tab tr {
	height: 90px !important;
	line-height: 90px !important;
}
</style>
<jsp:include page="nav.jsp"></jsp:include>
<div class="content">
	<div class="control">
		<form action="##" id="updateStatus" method="post" enctype="multipart/form-data">
			<input type="hidden" name="id" id="id" value="${finance.id}">
		</form>
		<form action="##" id="save" target="nm_iframe" method="post" enctype="multipart/form-data">

			<input type="hidden" name="audit_id" id="audit_id"> <input type="hidden" name="types" id="types" value="${finance.types}"> <input type="hidden" name="type" id="type" value="${finance.type}">
			<table class="control_tab">
				<tr>
					<td class="tab_01">分公司</td>
					<td class="width_01"><input type="hidden" name="area" value="${finance.area}" id="selectArea"> <select id="area" name="area" class="width_01" style="border: 1px solid #dcdcdc; padding-left: 10px" onchange="firstSel()">
							<c:forEach items="${branchOfficeList}" var="list">
								<option value="${list.area}">${list.area}</option>
							</c:forEach>
					</select></td>
					<td width="79">姓名</td>
					<td class="width_01"><input type="text" name="name" value="${finance.name}"></td>
					<td width="84">金额</td>
					<td class="width_01" style="text-align: left"><input type="text" name="money" value="${finance.money}" style="width: 102px"></td>
					<td width="79">期限</td>
					<td class="width_01"><input type="text" name="day" value="${finance.day}" style="width: 73px; margin-right: 10px;" onkeyup="this.value=this.value.replace(/[^\d]/g,'');" maxlength="1">个月</td>
				</tr>
				<tr>
					<td class="tab_01">业务类型</td>
					<td class="width_01"><input type="hidden" id="selectBusinessType" value="${finance.businessType}" /> <select name="businessType" id="businessType" class="form-select">
							<option value="1">惠车贷</option>
							<option value="0">惠房贷</option>
					</select></td>
					<td width="79">客户经理</td>
					<td class="width_01"><input type="hidden" name="s_id" value="${finance.s_id}" id="selectMan" /> <select id="salesman" name="s_id" class="width_01">
					</select></td>
					<td width="79">标号</td>
					<td class="width_01"><input type="text" name="grade" value="${finance.grade}"></td>
					<td width="79">旧标号</td>
					<td class="width_01"><input type="text" name="formergrade" value="${finance.formergrade}" maxlength="6"></td>

				</tr>

				<tr>

					<td width="79">期数</td>
					<td class="width_01"><input type="text" name="period" value="${finance.period}" id="period" maxlength="4" placeholder="输入期数"></td>
					<td class="tab_01">建表人</td>
					<td class="width_01"><input type="hidden" id="selectRiskman" value="${finance.riskman}" /> <select name="riskman" id="riskman">
							<option value="财务1">财务1</option>
							<option value="财务2">财务2</option>
							<option value="财务3">财务3</option>
					</select></td>
				</tr>
			</table>
			<table id="myTable" class="control_tab controls_table" style="text-align: center; width: 865px;" border="1">
				<c:if test="${not empty finance.interests}">
					<tr>
						<td width="116px" class="td_pos">平台服务费+利息（财务）</td>
						<td colspan="3" style="position: relative"><input type="text" name="interests" class="amount" value="${finance.interests}" onkeyup="clearNoNum(this)" onblur="clearNoNum(this)" maxlength="12" style="border: none; float: left"></td>
					</tr>
				</c:if>

				<tr>
					<td colspan="2" width="400px"></td>
					<td width="105px" id="sumAdd">合计</td>
					<td width="110px" id="td_Amount"><input type="text" name="sum" value="${finance.sum}" readonly style="border: none"></td>

				</tr>
			</table>

			<p class="control_tab">
				<input class="bgbtn" onclick="pass()" value="通过"> <input class="bgbtn" onclick="back()" value="打回" style="background: #f45b63">
			</p>
		</form>
		<iframe id="id_iframe" name="nm_iframe" style="display: none" src="about:blank"></iframe>
	</div>
</div>

<jsp:include page="footer.jsp"></jsp:include>
</body>

<!-- 表格内下拉框 -->
<script type="text/javascript">
	function addText(a) {
		a.html('');
		if (a.html().trim() == '') {
			a
					.append('<div class="div_dis"><input type="text" class="frame-style" readonly="readonly" value=" " disabled/><img src="images/bg_arr.png" class="bg_arri" /></div><ul class="menue-list"><li id="refinancePlatform">续贷还平台</li><li id="maturityPlatform">到期还平台</li><li id="overduePlatform">逾期还平台</li><li id="prepaymentPlatform">提前还平台</li></ul>');
		} else {
			return false;
		}
		$('.menue-list li').click(function() {
			var Current = $(this).text();
			var tdInterHTML = $(this).parent().parent();
			tdInterHTML.text(Current);
			if (tdInterHTML.text() == "平台服务费+利息（财务）") {
				tdInterHTML.next().children(".amount").attr('name', 'interests');
			}
			$(this).parent().css("display", "none");
			$(this).parent().prev().css("display", "none");
			return false;
		})
	}
</script>
<script type="text/javascript">
	$("span").click(function() {
		$(".show_next").hide();
	});
</script>

<script type="text/javaScript">
	$(function() {
		var a = $("#selectArea").val();
		var b = $("#selectBusinessType").val();

		var d = $("#selectMan").val();
		var e = $("#selectRiskman").val();

		$("#area option[value='" + a + "']").attr("selected", "selected");
		$("#businessType option[value='" + b + "']").attr("selected", "selected");

		$("#salesman option[value='" + d + "']").attr("selected", "selected");
		$("#riskman option[value='" + e + "']").attr("selected", "selected");

	});
	$(function() {
		navSelected(0);
	});
	function genlist(t) {
		$(t).parent().siblings().children("a").removeClass("selected");
		$(t).addClass("selected");
	}
</script>
<script type="text/javascript">
	/* 通过 */
	$("#save input").attr("readOnly", true);
	$("#save select").attr("disabled", true);
	function pass() {
		var pass_id = $("#id").val();

		console.log(pass_id);
		/* 状态(未审核0  已审核1 ,已作废3，已打回/审核未通过2 */
		var formObject = {};

		var formArray = $("#updateStatus").serializeArray();

		$.each(formArray, function(i, item) {

			formObject[item.name] = item.value;

		});
		var formJson = JSON.stringify(formObject);

		$.ajax({
			//几个参数需要注意一下
			type : "POST",//方法类型
			dataType : "json",//预期服务器返回的数据类型
			url : "updatePass.action?id=" + pass_id,
			data : formJson,
			contentType : 'application/json',
			success : function(result) {

				if (result.code == 0) {
					//提交成功后，提交到财务可查看审核页面
					window.location.href = "toShowCheked.action";

					console.log("成功通过！！");
				} else {
					window.wxc.xcConfirm("抱歉！出了一点小问题", window.wxc.xcConfirm.typeEnum.error);
				}
			},
			error : function(result) {
				window.wxc.xcConfirm("抱歉！出了一点小问题", window.wxc.xcConfirm.typeEnum.error);
			}
		});
	}
	/* 打回 */
	function back() {

		var back_id = $("#id").val();

		console.log(back_id);
		/* 状态(未审核0  已审核1 ,已作废3，已打回/审核未通过2 */
		var formObject = {};

		var formArray = $("#updateStatus").serializeArray();

		$.each(formArray, function(i, item) {

			formObject[item.name] = item.value;

		});
		var formJson = JSON.stringify(formObject);

		$.ajax({
			//几个参数需要注意一下
			type : "POST",//方法类型
			dataType : "json",//预期服务器返回的数据类型
			url : "updateBack.action?id=" + back_id,
			data : formJson,
			contentType : 'application/json',
			success : function(result) {

				if (result.code == 0) {
					//提交成功后，提交到财务可查看审核页面
					window.location.href = "toShowBeenBack.action";

					console.log("打回成功！！");
				} else {
					console.log(result.msg);
				}
			},
			error : function(result) {
				window.wxc.xcConfirm("抱歉！出了一点小问题", window.wxc.xcConfirm.typeEnum.error);
			}
		});

	}
</script>

<script type="text/javascript">
	function firstSel() {
		$("#salesman").html("")
		//如果第一个下拉列表的值改变则调用此方法

		var area = $("#area").val();//得到第一个下拉列表的值
		console.log("获取地区成功!!!")
		var url = "getlistsalesman.action?area=" + area;

		$.getJSON(url, function(res) {

			var option;
			for (var i = 0; i < res.length; i++) {
				var b = $("#selectMan").val();
				//循环，i为下标从0开始，n为集合中对应的第i个对象
				if (res[i].id != b) {
					option = "<option value='"+res[i].id+"'>" + res[i].name + "</option>"

				} else {
					option = "<option value='"+res[i].id+"'selected='selected'>" + res[i].name + "</option>"
				}

				$("#salesman").append(option);

			}
			;
			//将循环拼接的字符串插入第二个下拉列表

		});

	}
	$(function() {
		var area = $("#area").val();//得到第一个下拉列表的值
		var url = "getlistsalesman.action?area=" + area;
		$.getJSON(url, function(res) {
			var option;
			for (var i = 0; i < res.length; i++) {

				var b = $("#selectMan").val();

				//循环，i为下标从0开始，n为集合中对应的第i个对象
				if (res[i].id != b) {
					option = "<option value='"+res[i].id+"'>" + res[i].name + "</option>"

				} else {
					option = "<option value='"+res[i].id+"'selected='selected'>" + res[i].name + "</option>"
				}

				$("#salesman").append(option);

			}
			;
			//将循环拼接的字符串插入第二个下拉列表					
		});
	});
</script>
</html>
