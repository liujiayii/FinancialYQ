<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>财务利息查看详情</title>
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

		<form action="##" id="save" method="post" enctype="multipart/form-data">

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
					<td class="width_01"><input type="text" name="day" value="${finance.day}" style="width: 73px; margin-right: 10px;" maxlength="2">个月</td>
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
					<td class="width_01"><input type="text" name="period" value="${finance.period}" id="period" style="width: 73px; margin-right: 10px;" maxlength="4" placeholder="输入期数"></td>
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
				<input type="button" value="返回" onclick="javascript:history.back(-1);" class="bgbtn" id="sub_form" />
			</p>
		</form>

	</div>
</div>

<jsp:include page="footer.jsp"></jsp:include>
</body>
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
