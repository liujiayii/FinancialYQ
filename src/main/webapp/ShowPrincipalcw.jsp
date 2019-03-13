<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>本息财务详情页面</title>
<jsp:include page="nav.jsp"></jsp:include>
<div class="content">
	<div class="control">
		<form action="##" id="save" method="post" enctype="multipart/form-data">
			<input type="hidden" name="s_id" id="s_id"> <input type="hidden" name="audit_id" id="audit_id"> <input type="hidden" name="id" value="${finance.id}" id="id"> <input type="hidden" name="types" id="types" value="${finance.types}"> <input type="hidden" name="type" id="type" value="${finance.type}">
			<table class="control_tab">
				<tr>
					<td class="tab_01">分公司</td>
					<td class="width_01"><input type="hidden" value="${finance.area}" id="selectArea" name="area"> <select id="area" name="area" class="width_01" style="border: 1px solid #dcdcdc; padding-left: 10px" onchange="firstSel()">
							<c:forEach items="${branchOfficeList}" var="list">

								<option value="${list.area}">${list.area}</option>
							</c:forEach>
					</select></td>
					<td width="79">姓名</td>
					<td class="width_01"><input type="text" name="name" value="${finance.name}" id="names" placeholder="请输入姓名" maxlength="5"></td>
					<td width="84">金额</td>
					<td class="width_01" style="text-align: left"><input type="text" name="money" maxlength="8" onkeyup="clearNoNum(this)" onblur="clearNoNum(this)" id="money" value="${finance.money}" style="width: 115 px" placeholder="请输入金额"></td>
					<td width="79">期限</td>
					<td class="width_01"><input type="text" name="day" value="${finance.day}" id="days" style="width: 73px; margin-right: 10px;" maxlength="1" onkeyup="this.value=this.value.replace(/[^\d]/g,'');" placeholder="输入期限">个月</td>
				</tr>
				<tr class="font_color">
				</tr>
				<tr>
					<td class="tab_01">业务类型</td>
					<td class="width_01"><input type="hidden" name="businessType" value="${finance.businessType}" id="selectBusinessType"> <select name="businessType" id="businessType">

							<option value="1">惠车贷</option>
							<option value="0">惠房贷</option>
					</select></td>
					<%-- <td width="79">客户经理</td>
								<td>
									<input type="hidden" value="${finance.s_id}" id="selectMan" name="s_id">
									<select id ="salesman" name="s_id" >
									</select>							
								</td> --%>


					<td width="79">标号</td>
					<td class="width_01"><input type="text" name="grade" value="${finance.grade}" placeholder="请输入标号" maxlength="8"></td>

					<td class="tab_01">建表人</td>
					<td class="width_01"><input type="text" value="${finance.riskman}" id="riskman"></td>
				</tr>
				<tr class="font_color">
				</tr>
				<tr>

					<td width="79">期数</td>
					<td class="width_01"><input type="text" name="period" value="${finance.period}" id="period" maxlength="4" placeholder="输入期数"></td>


					<td class="tab_01">类别</td>
					<td class="width_01"><input type="text" id="receiptType" value="${receipttype}" /></td>

					<td width="84">时间</td>
					<td width="185"><input type="text" name="time" value="${strDate}"></td>
				</tr>
				<tr class="font_color">
				</tr>
			</table>
			<table id="myTable" class="control_tab controls_table" style="text-align: center; width: 865px;" border="1">
				<c:if test="${not empty finance.principals}">
					<tr>
						<td width="116px" class="td_pos">本金</td>
						<td colspan="3" style="position: relative"><input type="text" name="principals" class="amount" value="${finance.principals}" maxlength="12" style="border: none; float: left"></td>
					</tr>
				</c:if>
				<c:if test="${not empty finance.accrual}">
					<tr>
						<td width="116px" class="td_pos">利息</td>
						<td colspan="3" style="position: relative"><input type="text" name="accrual" class="amount" value="${finance.accrual}" maxlength="12" style="border: none; float: left"></td>
					</tr>
				</c:if>

				<tr>
					<td colspan="2" width="400px"><input type="text" name="remark" value="${finance.remark}" style="border: none" /></td>
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
<script type="text/javascript">
	/*导航栏选中样式*/
	navSelected(0);
	$(function() {
		var a = $("#selectArea").val();
		var b = $("#selectBusinessType").val();
		var c = $("#selectRiskman").val();
		var d = $("#selectMan").val();

		$("#area option[value='" + a + "']").attr("selected", "selected");
		$("#businessType option[value='" + b + "']").attr("selected", "selected");
		$("#riskman option[value='" + c + "']").attr("selected", "selected");
		$("#salesman option[value='" + d + "']").attr("selected", "selected");
		$("input").attr("readOnly", "true");
		$("select").attr("disabled", "true");
	});
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
		});
	});
</script>
</html>
