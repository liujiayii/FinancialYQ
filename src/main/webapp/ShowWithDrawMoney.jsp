<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>财务管理-收入费用-查看详情</title>
<jsp:include page="nav.jsp"></jsp:include>
<div class="content">
	<div class="sub-box">
		<!-- <ul class="sub-nav">
						<li><a href="toStaff.action">员工管理</a></li>
						<li><a href="toIncome.action" class="selected">收入费用</a></li>
						<li><a href="toSpend.action">支出费用</a></li>
						<li><a href="toFinanceManager.action">标的管理</a></li>
						<li><a href="toShowBeenVoid.action">工资核算</a></li>
						<li><a href="toCompany.action">公司管理</a></li>
					</ul> -->
		<div class="sub-right">
			<input type="button" value="返回" class="sub-but" onclick="javascript:history.back(-1);" />
		</div>
	</div>
	<div class="control">
		<form action="##" id="updateState" method="post" enctype="multipart/form-data">
			<input type="hidden" name="id" id="id" value="${withdrawMoney.id}">

		</form>
		<form action="##" id="save" target="nm_iframe" method="post" enctype="multipart/form-data">
			<input type="hidden" name="id" id="id" value="${withdrawMoney.id}">
			<table class="control_tab">
				<tr>
					<td><input type="hidden" id="id1" name="incomeAccount" value="" maxlength="30" style="border: none; float: left" id="number1"> <input type="hidden" id="id2" name="money" value="" maxlength="30" style="border: none; float: left" id="number1"> <input type="hidden" id="id3" name="remark" value="" maxlength="30" style="border: none; float: left" id="number1"></td>
				</tr>

			</table>
			<table id="myTable" class="control_tab controls_table" style="text-align: center; width: 800px;" border="1">
				<tr>
					<td width="180px">收入项目</td>
					<td>摘要</td>
					<td colspan="3" class="amount">金额</td>
				</tr>
				<tr>
					<td><input type="text" name="incomeAccount" value="${withdrawmoney.incomeAccount}" maxlength="30" style="border: none; float: left" id="number1"></td>
					<td><input type="text" name="remark" value="${withdrawmoney.remark}" maxlength="30" style="border: none; float: left" id="digest1"></td>
					<td colspan="3"><input type="text" name="money" value="${withdrawmoney.money}" class="amount" onkeyup="clearNoNum(this)" onblur="clearNoNum(this)" maxlength="12" style="border: none; float: left" id="sum1"></td>
				</tr>
				<tr>
					<td><input type="text" name="income_name1" value="${income.income_name1}" maxlength="30" style="border: none; float: left" id="number2"></td>
					<td><input type="text" name="digest1" value="${income.digest1}" maxlength="30" style="border: none; float: left" id="digest2"></td>
					<td colspan="3"><input type="text" name="money1" id="sum2" value="${income.money1}" class="amount" onkeyup="clearNoNum(this)" onblur="clearNoNum(this)" maxlength="12" style="border: none; float: left"></td>
				</tr>
				<tr>
					<td><input type="text" name="income_name2" value="${income.income_name2}" maxlength="30" style="border: none; float: left" id="number3"></td>
					<td><input type="text" name="digest2" value="${income.digest2}" maxlength="30" style="border: none; float: left" id="digest3"></td>
					<td colspan="3"><input type="text" name="money2" id="sum3" value="${income.money2}" class="amount" onkeyup="clearNoNum(this)" onblur="clearNoNum(this)" maxlength="12" style="border: none; float: left"></td>
				</tr>
				<tr>
					<td><input type="text" name="income_name3" value="${income.income_name3}" maxlength="30" style="border: none; float: left" id="number4"></td>
					<td><input type="text" name="digest3" value="${income.digest3}" maxlength="30" style="border: none; float: left" id="digest4"></td>
					<td colspan="3"><input type="text" name="money3" id="sum4" value="${income.money3}" class="amount" onkeyup="clearNoNum(this)" onblur="clearNoNum(this)" maxlength="12" style="border: none; float: left"></td>
				</tr>
				<tr>
					<td><input type="text" name="income_name4" value="${income.income_name4}" maxlength="30" style="border: none; float: left" id="number5"></td>
					<td><input type="text" name="digest4" value="${income.digest4}" maxlength="30" style="border: none; float: left" id="digest5"></td>
					<td colspan="3"><input type="text" name="money4" id="sum5" value="${income.money4}" class="amount" onkeyup="clearNoNum(this)" onblur="clearNoNum(this)" maxlength="12" style="border: none; float: left"></td>
				</tr>
				<tr>
					<td colspan="2" width="400px"></td>
					<td width="105px" id="sumAdd" name="sum">合计</td>
					<td width="110px" id="td_Amount"><input type="text" value="" name="sum" readonly style="border: none"></td>
				</tr>
			</table>
			<p class="control_tab"></p>
		</form>
	</div>
</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
<script type="text/javascript">
	$("#save input").attr("readOnly", true);
	$("#save select").attr("disabled", true);
</script>
<script type="text/javascript">
	/*一级导航栏选中 */
	$(function() {
		navSelected(4);
		$("#time").next().children(".textbox-text").attr("placeholder", "请输入时间");
		var a = $("#number1").val() + "," + $("#number2").val() + "," + $("#number3").val() + "," + $("#number4").val() + "," + $("#number5").val();

	})
</script>
<!-- 合计 -->
<script type="text/javascript">
	$(function() {
		var tb = document.getElementById('myTable');
		var count = 0;
		for (var i = 0; i < tb.rows.length - 1; i++) {
			var num = $(tb.rows[i]).find('.amount').val().trim();
			if (num == "") {
				num = 0;
			}
			num = parseFloat(num);
			count += num;
		}
		$('#td_Amount input').val(count.toFixed(2));
	})
</script>
<!--合计验证  -->
<script type="text/javascript">
	function clearNoNum(obj) {
		//先把非数字的都替换掉，除了数字和.
		obj.value = obj.value.replace(/[^\d.]/g, "");
		//保证只有出现一个.而没有多个.
		obj.value = obj.value.replace(/\.{2,}/g, ".");
		//必须保证第一个为数字而不是.
		obj.value = obj.value.replace(/^\./g, "");
		//保证.只出现一次，而不能出现两次以上
		obj.value = obj.value.replace(".", "$#$").replace(/\./g, "").replace("$#$", ".");
		//只能输入两个小数
		obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3');
	}
</script>

</html>