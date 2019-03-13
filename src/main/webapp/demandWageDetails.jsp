<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>信息查询-工资表-详情</title>
<jsp:include page="nav.jsp"></jsp:include>
<div class="content">
	<div class="sub-box">
		<div class="sub-right">
			<input type="button" value="返回" class="sub-but" onclick="javascript:history.back(-1);" />
		</div>
	</div>
	<div class="control">
		<form action="##" method="post" enctype="multipart/form-data" id="save">
			<table class="control_tab">
				<tr>
					<td width="140px" class="tab_03"><b>工号</b></td>
					<td class="width_04"><input type="hidden" value="${salaryVo.month}" name="month"> <input type="text" value="${salaryVo.job_number}" name="job_number" id="job_number"></td>
					<td width="140px" class="tab_03"><b>姓名</b></td>
					<td class="width_04"><input type="text" value="${salaryVo.name}" name="name" id="names"></td>
					<td width="140px" class="tab_03"><b>职位</b></td>
					<td class="width_04"><input type="text" value="${salaryVo.post_name}" name="post_id" id="posts"></td>
					<td width="140px" class="tab_03"><b>转正出勤</b></td>
					<td class="width_04"><input type="text" value="${salaryVo.real_attendance}" name="real_attendance" id="zattendance"></td>
				</tr>
				<tr>
					<td colspan="2" class="Worknum font_color"></td>
					<td colspan="2" class="Names font_color"></td>
					<td colspan="2" class="Posts font_color"></td>
					<td colspan="2" class="Attend font_color"></td>
				</tr>
				<tr>
					<td width="140px" class="tab_03"><b>全勤奖</b></td>
					<td class="width_04"><input type="text" value="${salaryVo.attendance_bouns}" name="attendance_bouns" id="fulltimes" maxlength="6"></td>
					<td width="140px" class="tab_03"><b>实习出勤</b></td>
					<td class="width_04"><input type="text" value="${salaryVo.noreal_attendance}" name="noreal_attendance" id="attendance"></td>

					<td width="140px" class="tab_03"><b>实习工资</b></td>
					<td class="width_04"><input type="text" value="${salaryVo.base_pay}" name="base_pay" id="wages"></td>

					<td width="140px" class="tab_03"><b>转正工资</b></td>
					<td class="width_04"><input type="text" value="${salaryVo.zbase_pay}" name="zbase_pay" id="wagess"></td>
				</tr>
				<tr>

					<td colspan="2" class="Days font_color"></td>
					<td colspan="2" class="Wages font_color"></td>
					<td colspan="2" class="Share font_color"></td>
					<td colspan="2" class="Fulltime font_color"></td>
				</tr>
				<tr>
					<td width="140px" class="tab_03"><b>原始股东分红</b></td>
					<td class="width_04"><input type="text" value="${salaryVo.original_dividend}" name="original_dividend" id="shares" maxlength="6" onkeyup="clearNoNum(this)"></td>
					<td width="140px" class="tab_03"><b>保密金</b></td>
					<td class="width_04"><input type="text" value="${salaryVo.confidential_gold}" id="surance" name="confidential_gold" onkeyup="clearNoNum(this)"></td>
					<td width="140px" class="tab_03"><b>工装</b></td>
					<td class="width_04"><input type="text" value="${salaryVo.forck_bouns}" id="forck_bouns" name="forck_bouns" maxlength="6"></td>
					<td width="140px" class="tab_03"><b>餐补</b></td>
					<td class="width_04"><input type="text" value="${salaryVo.meal_bouns}" id="meals" name="meal_bouns"></td>
				</tr>
				<tr>
					<td colspan="2"></td>
					<td colspan="2"></td>
					<td colspan="2"></td>
					<td colspan="2"></td>
				</tr>
				<tr>


					<td width="140px" class="tab_03"><b>业务提成</b></td>
					<td class="width_04"><input type="text" value="${salaryVo1.business_pay}" name="business_pay" id="deduct" maxlength="6"></td>

					<td width="140px" class="tab_03"><b>理财提成</b></td>
					<td class="width_04"><input type="text" value="${json}" id="licai_deduct" maxlength="6"></td>
					<td width="140px" class="tab_03"><b>扣保险</b></td>
					<td class="width_04"><input type="text" value="${salaryVo.insurance_money}" name="insurance_money" id="insurance" maxlength="6" onkeyup="clearNoNum(this)"></td>
					<td width="140px" class="tab_03"><b>罚款</b></td>
					<td class="width_04"><input type="text" value="${salaryVo.penalty_money}" name="penalty_money" id="fine" maxlength="6" onkeyup="clearNoNum(this)"></td>
					<%-- <td width="140px" class="tab_03"><b>逾期扣</b></td>
						<td class="width_04"><input type="text" value="${salaryVo.overdue_money}" id="overdue" name="overdue_money" onkeyup="clearNoNum(this)"></td> --%>
				</tr>
				<tr>
					<td colspan="2" class="ForckBouns font_color"></td>
					<td colspan="2" class="Times font_color"></td>
					<td colspan="2" class="ForckBouns font_color"></td>
					<td colspan="2" class="Times font_color"></td>
				</tr>
				<tr>

					<td width="140px" class="tab_03"><b>事假天数</b></td>
					<td class="width_04"><input type="text" value="${salaryVo.leaves}" name="leaves" id="leaves"></td>
					<td width="140px" class="tab_03"><b>病假天数</b></td>
					<td class="width_04"><input type="text" value="${salaryVo.sick}" id="sick" name="sick" maxlength="6" onkeyup="clearNoNum(this)"></td>
					<td width="140px" class="tab_03"><b>产假天数</b></td>
					<td class="width_04"><input type="text" value="${salaryVo.maternity}" id="maternity " name="maternity" maxlength="6" onkeyup="clearNoNum(this)"></td>
					<td width="140px" class="tab_03"><b>婚假天数</b></td>
					<td class="width_04"><input type="text" value="${salaryVo.marriage }" id="marriage " name="marriage" maxlength="6" onkeyup="clearNoNum(this)"></td>
				</tr>
				<tr>
					<td colspan="2" class="ForckBouns font_color"></td>
					<td colspan="2" class="Times font_color"></td>
					<td colspan="2"></td>
					<td colspan="2"></td>
				</tr>
				<tr>

					<td width="140px" class="tab_03"><b>年假天数</b></td>
					<td class="width_04"><input type="text" value="${salaryVo.annualleave}" id="annualleave" name="annualleave" maxlength="6" onkeyup="clearNoNum(this)"></td>
					<td width="140px" class="tab_03"><b>迟到次数</b></td>
					<td class="width_04"><input type="text" value="${salaryVo.late}" name="late" id="late" maxlength="6" onkeyup="clearNoNum(this)"></td>
					<td width="140px" class="tab_03"><b>未打卡</b></td>
					<td class="width_04"><input type="text" value="${salaryVo.no_clock}" id="Notclock" name="no_clock"></td>
					<td width="140px" class="tab_03"><b>陪产假天数</b></td>
					<td class="width_04"><input type="text" value="${salaryVo.pmaternity}" id="pmaternity " name="pmaternity" maxlength="6" onkeyup="clearNoNum(this)"></td>
				</tr>
				<tr>
					<td colspan="2"></td>
					<td colspan="2"></td>
					<td colspan="2"></td>
					<td colspan="2"></td>
				</tr>
				<tr>

					<td width="140px" class="tab_03"><b>实发工资</b></td>
					<td class="width_04"><input type="text" value="${salaryVo.real_salary}" id="money" name="real_salary"></td>

				</tr>
				<tr>
					<td colspan="2"></td>
					<td colspan="2"></td>
					<td colspan="2"></td>
					<td colspan="2"></td>
				</tr>
				<tr>
					<td width="140px" class="tab_03"><b>备注</b></td>
					<td colspan="5"><input type="text" value="${salaryVo.remark}" name="remark" id="Tooling"></td>
				</tr>
				<tr>
					<td colspan="6"></td>
				</tr>

			</table>
			<p class="control_tab">
				<input type="button" value="编辑" class="bgbtn" id="sub_form" onclick="edit()"> <input type="button" value="保存" class="bgbtn" style="background: #f45b63" onclick="update()">
			</p>

		</form>
	</div>
</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
<script type="text/javascript">
	/*编辑，点击后需修改内容变为可修改，不修改内容变为不可用*/
	function edit() {
		$("#save input").attr("readOnly", false);
		$("#job_number").attr("disabled", true);
		$("#names").attr("disabled", true);
		$("#posts").attr("disabled", true);
		$("#fulltimes").attr("disabled", true);
		$("#forck_bouns").attr("disabled", true);
		$("#meals").attr("disabled", true);
		$("#wages").attr("disabled", true);
		$("#wagess").attr("disabled", true);

		$("#Notclock").attr("disabled", true);

	}
	$(function() {
		$("#save input").attr("readOnly", true);

	});
	function nub(vals) {
		var num;
		if ($('#' + vals).val() == '' || $('#' + vals).val() == undefined) {

			num = 0.0;
		} else {
			num = parseFloat($('#' + vals).val());
		}
		return num;
	}
	function update() {

		var sum = nub('wages') / 30 * nub('attendance') + nub('wagess') / 30 * (nub('zattendance') + nub('maternity') + nub('marriage') + nub('pmaternity') + nub('annualleave'))
				+ nub('shares') + nub('fulltimes') + nub('forck_bouns') + nub('meals') + nub('surance') + nub('deduct') + nub('licai_deduct') - nub('Notclock') - nub('insurance')
				- nub('fine') - nub('late') * 10;
		if (sum >= 5000) {
			$('#money').val(sum - ((sum - 5000) * 0.03));
		} else {
			$('#money').val(sum)
		}
		var id = $("#input_id").val();

		var formObject = {};

		var formArray = $("#save").serializeArray();

		$.each(formArray, function(i, item) {

			formObject[item.name] = item.value;

		});
		var formJson = JSON.stringify(formObject);

		$.ajax({
			type : "POST",
			dataType : "json",
			url : "updateRemark.action",
			data : formJson,
			contentType : 'application/json',
			success : function(result) {
				if (result.code == 0) {

					window.location.href = "demandWage.action";
				}
			},
			error : function(result) {
				window.wxc.xcConfirm("抱歉！出了一点小问题", window.wxc.xcConfirm.typeEnum.error);
			}
		});
	}
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