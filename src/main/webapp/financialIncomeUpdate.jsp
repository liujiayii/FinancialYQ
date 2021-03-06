<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<title>财务管理-收入费用-修改</title>
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
			<input type="button" value="返回" class="sub-but" onclick="javascript:history.go(-1);" />
		</div>
	</div>
	<div class="control">
		<form action="##" id="save" target="nm_iframe" method="post" enctype="multipart/form-data">
			<input type="hidden" value="${income.id}" name="id">
			<table class="control_tab">
				<tr>
					<td class="tab_01">分公司</td>
					<td class="width_01"><input type="hidden" name="district_id" value="${income.district_id}" id="selectArea"> <select id="area" name="area" onchange="firstSel()">
							<option value="" disabled selected hidden>选择分公司</option>
							<c:forEach items="${branchOfficeList}" var="list">
								<option value="${list.area_code}">${list.area}</option>
							</c:forEach>
					</select></td>

					<td class="tab_01">姓名</td>
					<td class="width_01">
						<!-- <input type="text" value="" name="name" placeholder="请输入姓名"> --> <input type="text" value="${income.name }" name="name" placeholder="请输入姓名">

					</td>


					<!-- <td class="tab_01">标题</td> -->
					<td>
						<%-- <input type="text" value="${income.title }" name="title" placeholder="请输入标题"> --%> <input type="hidden" id="id1" name="income_name" value="" maxlength="30" style="border: none; float: left"> <input type="hidden" id="id2" name="money" value="" maxlength="30" style="border: none; float: left"> <input type="hidden" id="id3" name="digest" value="" maxlength="30" style="border: none; float: left">
					</td>
				</tr>
				<%-- <fmt:formatDate value="${times }" type="date" pattern="yyyy/MM/dd"/> --%>
			</table>
			<%-- 时间:<input type="date" value="${times }" name="times" class="sub-search" placeholder="请输入日期" id="inputDate"  style="background: none;" /> --%>


			<table id="myTable" class="control_tab controls_table" style="text-align: center; width: 800px;" border="1">
				<tr>
					<td width="180px">收入项目</td>
					<td>摘要</td>
					<td colspan="3" class="amount">金额</td>
				</tr>
				<tr>
					<td><input type="text" name="income_name" value="${income.income_name}" maxlength="30" style="border: none; float: left;width: 100%" id="number1"></td>
					<td><input type="text" name="digest" value="${income.digest}" maxlength="30" style="border: none; float: left;width: 100%" id="digest1"></td>
					<td colspan="3"><input type="text" name="money" value="${income.money}" class="amount" onkeyup="clearNoNum(this)" onblur="clearNoNum(this)" maxlength="12" style="border: none; float: left" id="sum1"></td>
				</tr>
				<tr>
					<td><input type="text" name="income_name1" value="${income.income_name1}" maxlength="30" style="border: none; float: left;width: 100%" id="number2"></td>
					<td><input type="text" name="digest1" value="${income.digest1}" maxlength="30" style="border: none; float: left;width: 100%" id="digest2"></td>
					<td colspan="3"><input type="text" name="money1" id="sum2" value="${income.money1}" class="amount" onkeyup="clearNoNum(this)" onblur="clearNoNum(this)" maxlength="12" style="border: none; float: left"></td>
				</tr>
				<tr>
					<td><input type="text" name="income_name2" value="${income.income_name2}" maxlength="30" style="border: none; float: left;width: 100%" id="number3"></td>
					<td><input type="text" name="digest2" value="${income.digest2}" maxlength="30" style="border: none; float: left;width: 100%" id="digest3"></td>
					<td colspan="3"><input type="text" name="money2" id="sum3" value="${income.money2}" class="amount" onkeyup="clearNoNum(this)" onblur="clearNoNum(this)" maxlength="12" style="border: none; float: left"></td>
				</tr>
				<tr>
					<td><input type="text" name="income_name3" value="${income.income_name3}" maxlength="30" style="border: none; float: left;width: 100%" id="number4"></td>
					<td><input type="text" name="digest3" value="${income.digest3}" maxlength="30" style="border: none; float: left;width: 100%" id="digest4"></td>
					<td colspan="3"><input type="text" name="money3" id="sum4" value="${income.money3}" class="amount" onkeyup="clearNoNum(this)" onblur="clearNoNum(this)" maxlength="12" style="border: none; float: left"></td>
				</tr>
				<tr>
					<td><input type="text" name="income_name4" value="${income.income_name4}" maxlength="30" style="border: none; float: left;width: 100%" id="number5"></td>
					<td><input type="text" name="digest4" value="${income.digest4}" maxlength="30" style="border: none; float: left;width: 100%" id="digest5"></td>
					<td colspan="3"><input type="text" name="money4" id="sum5" value="${income.money4}" class="amount" onkeyup="clearNoNum(this)" onblur="clearNoNum(this)" maxlength="12" style="border: none; float: left"></td>
				</tr>
				<tr>
					<td colspan="2" width="400px"><input type="text" name="remark" value="${income.remark}" style="border: none;width:100%" maxlength="100" placeholder="请输入备注:" /></td>
					<td width="105px" id="sumAdd">合计</td>
					<td width="110px" id="td_Amount"><input type="text" value="" name="sum" readonly style="border: none"></td>
				</tr>
			</table>
			<p class="control_tab">
				<input type="button" value="提交" class="bgbtn" id="sub_form" onclick="update()"> <input type="button" value="取消" class="bgbtn" style="background: #f45b63" onclick="javascript:history.back(-1);">
			</p>
		</form>
	</div>
</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
<script type="text/javascript">
	var selectArea = $("#selectArea").val();
	console.log(selectArea);
	$("#area option[value='" + selectArea + "']").attr("selected", "selected");
	/*一级导航栏选中 */
	$(function() {
		navSelected(4);
		var a = $("#number1").val() + "," + $("#number2").val() + "," + $("#number3").val() + "," + $("#number4").val() + "," + $("#number5").val();

	})
</script>
<!-- 合计 -->
<script type="text/javascript">
	function sum() {
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
		sum();
	}
</script>

<script type="text/javascript">
	/** 提交 */
	function update(id) {

		var formObject = {};

		var formArray = $("#save").serializeArray();

		$.each(formArray, function(i, item) {

			formObject[item.name] = item.value;

		});
		var formJson = JSON.stringify(formObject);

		$.ajax({
			type : "POST",//方法类型
			dataType : "json",//预期服务器返回的数据类型
			url : "updateIncome.action",
			data : formJson,
			contentType : 'application/json',
			success : function(result) {
				if (result.code == 0) {
					alert("提交成功  。。。")
					window.location.href = "toIncome.action";

				} else {
					console.log(result.msg);
				}
			},
			error : function(result) {
				window.wxc.xcConfirm("抱歉！出了一点小问题", window.wxc.xcConfirm.typeEnum.error);
			}
		});

	}

	function firstSel() {
		$("#salesman").html("")
		//如果第一个下拉列表的值改变则调用此方法
		var area = $("#area").val();//得到第一个下拉列表的值

		var url = "getlistsalesman.action?area=" + area;
		$.getJSON(url, function(res) {
			var option;
			for (var i = 0; i < res.length; i++) {
				//循环，i为下标从0开始，n为集合中对应的第i个对象
				option = "<option value='"+res[i].id+"'>" + res[i].name + "</option>"
				$("#salesman").append("<option value='"+res[i].id+"'>" + res[i].name + "</option>");
			}
			;
			//将循环拼接的字符串插入第二个下拉列表
		});
	}
	$(function() {

		var c = "${roletype}";
		if (c == '记账') {
			$('.sub-nav li').slice(0, 1).css("display", "none");
			$('.sub-nav li').slice(4, 5).css("display", "none");
		}
		var area = $("#area").val();//得到第一个下拉列表的值
		var url = "getlistsalesman.action?area=石家庄";
		$.getJSON(url, function(res) {
			var option;
			for (var i = 0; i < res.length; i++) {
				//循环，i为下标从0开始，n为集合中对应的第i个对象
				option = "<option value='"+res[i].id+"'>" + res[i].name + "</option>"
				$("#salesman").append("<option value='"+res[i].id+"'>" + res[i].name + "</option>");
			}
			;
			//将循环拼接的字符串插入第二个下拉列表					
		});
	});
</script>
</html>