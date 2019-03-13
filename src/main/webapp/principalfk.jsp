<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>风控本金添加</title>
<jsp:include page="nav.jsp"></jsp:include>
<div class="content">
	<div class="control">
		<form action="##" id="save" target="nm_iframe" method="post" enctype="multipart/form-data">
			<input type="hidden" name="s_id" id="s_id"> <input type="hidden" name="audit_id" id="audit_id"> <input type="hidden" name="id" value="${finance.id}" id="id"> <input type="hidden" name="types" id="types" value="${finance.types}"> <input type="hidden" name="type" id="type" value="${finance.type}">
			<table class="control_tab">
				<tr>
					<td class="tab_01">分公司</td>
					<td class="width_01"><select id="area" name="area" class="width_01" style="border: 1px solid #dcdcdc; padding-left: 10px" onchange="firstSel()">
							<c:forEach items="${branchOfficeList}" var="list">
								<option disabled hidden selected>选择分公司</option>
								<option value="${list.area}">${list.area}</option>
							</c:forEach>
					</select></td>
					<td width="79">姓名</td>
					<td class="width_01"><input type="text" name="name" value="" id="names" placeholder="请输入姓名" maxlength="5"></td>
					<td width="84">金额</td>
					<td class="width_01" style="text-align: left"><input type="text" name="money" maxlength="8" onkeyup="clearNoNum(this)" onblur="clearNoNum(this)" id="money" value="" style="width: 115 px" placeholder="请输入金额"></td>
					<td width="79">期限</td>
					<td class="width_01"><input type="text" name="day" value="" id="days" style="width: 73px; margin-right: 10px;" maxlength="1" onkeyup="this.value=this.value.replace(/[^\d]/g,'');" placeholder="输入期限">个月</td>
				</tr>
				<tr class="font_color">
					<td colspan="2" class="Area"></td>
					<td colspan="2" class="Names"></td>
					<td colspan="2" class="Money"></td>
					<td colspan="2" class="Days"></td>
				</tr>
				<tr>
					<td class="tab_01">业务类型</td>
					<td class="width_01"><select name="businessType" id="businessType">
							<option disabled selected hidden>选择业务类型</option>
							<option value="1">惠车贷</option>
							<option value="0">惠房贷</option>
					</select></td>
					<td width="79">客户经理</td>
					<td class="width_01"><select id="salesman" name="s_id" class="width_01">
							<option value="" disabled selected hidden>选择客户经理</option>
					</select></td>
					<td width="79">标号</td>
					<td class="width_01"><input type="text" name="grade" value="${finance.grade}" placeholder="请输入标号" maxlength="8" onkeyup="findGrade(this)" id="grade"></td>
					<td width="79">旧标号</td>
					<td class="width_01"><input type="text" name="formergrade" id="old_nums" value="" placeholder="输入旧标号" onkeyup="this.value=this.value.replace(/[^0-9a-zA-Z]/g,'');"></td>

				</tr>
				<tr class="font_color">
					<td colspan="2"></td>
				</tr>

				<tr>
					<td class="tab_01">建表人</td>
					<td class="width_01"><input type="hidden" id="selectRiskman" value="${finance.riskman}" /> <select name="riskman" id="riskman">
							<option value="" disabled selected hidden>选择建表人</option>
							<option value="风控1">风控1</option>
							<option value="风控2">风控2</option>
							<option value="风控3">风控3</option>
					</select></td>
					<td width="79">期数</td>
					<td class="width_01"><input type="text" name="period" value="${finance.period}" id="period" ;margin-right:10px;" maxlength="4" placeholder="输入期数"></td>
				</tr>
				<tr class="font_color">
					<td colspan="2" class="BusinessType"></td>
					<td colspan="2" class="Salesman"></td>
					<td colspan="2" class="Nums"></td>
					<td colspan="2" class="Riskman"></td>
				</tr>
			</table>
			<table id="myTable" class="control_tab controls_table" style="text-align: center; width: 865px;" border="1">
				<tr>
					<td width="143px" class="td_pos" onclick="addText($(this))"></td>
					<td colspan="3" style="position: relative"><img class="tab_img" src="images/tab_add.png" id="addRow"> <img src="images/tab_det.png" id="deleteRow" onclick="delRow('del1');"> <input type="text" name="" class="amount" value="" onkeyup="clearNoNum(this)" onblur="clearNoNum(this)" maxlength="12" style="border: none; float: left"></td>
				</tr>
				<tr>
					<td colspan="2" width="400px"></td>
					<td width="105px" id="sumAdd">合计</td>
					<td width="110px" id="td_Amount"><input type="text" name="sum" value="" readonly style="border: none"></td>
					<!-- <td class="width_01"><input type="hidden" name="types"  id="types" value=""></td> -->
				</tr>
			</table>
			<p class="control_tab">
				<input type="button" value="保存" onclick="add()" class="bgbtn" id="sub_form" /> <input type="button" onclick="add1()" value="提交" style="background: #f5ba31" class="bgbtn" /> <input type="reset" onclick="javascript:history.back(-1);" value="取消" style="background: #f45b63" class="bgbtn" />
			</p>
		</form>
		<iframe id="id_iframe" name="nm_iframe" style="display: none" src="about:blank"></iframe>
	</div>
</div>
<div class="show_next">
	<div class="close" style="height: 40px; padding-top: 12px;">
		<span><a href="#" id="close">关闭</a></span>
	</div>
</div>

<jsp:include page="footer.jsp"></jsp:include>
</body>
<!-- 表格内下拉框 -->
<script type="text/javascript">
	var ahtml;
	var ahtmls;
	function addText(a) {
		if (a.html().length == 534) {
			if (a.children(":first-child").children(":first-child")[0].className == "frame-style") {
				return true;
			} else {
				ahtml = a.html();
			}
		} else {
			ahtml = a.html();
		}

		a.html('');
		if (a.html().trim() == '') {
			a
					.append('<div class="div_dis"><input type="text" class="frame-style" readonly="readonly" value=" " disabled/><img src="images/bg_arr.png" class="bg_arri" /></div><ul class="menue-list"><li id="refinancePrincipal">续贷还本</li><li id="maturityPrincipal">到期还本</li><li id="prepaymentPrincipal">提前还本</li><li id="overduePrincipal">逾期还本</li></ul>');
		} else {
			return false;
		}
		document.onclick = function(e) {
			var target = e.target;
			console.log(a)
			ahtmls = a.html();
			var tc = target.className;
			var tcc = a.children(":first-child").children(":first-child")[0].className;
			if (tc != tcc) {
				if (tc.indexOf(a.context.className) == -1) {
					a.children(":first-child").hide();
					a.children(":last-child").hide();
					console.log(ahtml)
					if (ahtml == ahtmls) {
						a.html(ahtmls);
					} else {
						a.html(ahtml);
					}
				}
			} else {
				ahtml = ahtml;
			}
		}

		$('.menue-list li').click(function() {
			var Current = $(this).text();
			var tdInterHTML = $(this).parent().parent();
			var InputHTML = $(this).parent().prev().children(":first-child");
			InputHTML.text(Current);
			tdInterHTML.text(Current);
			if (tdInterHTML.text() == "续贷还本") {
				tdInterHTML.next().children(".amount").attr('name', 'refinancePrincipal');
			} else if (tdInterHTML.text() == "到期还本") {
				tdInterHTML.next().children(".amount").attr('name', 'maturityPrincipal');
			} else if (tdInterHTML.text() == "提前还本") {
				tdInterHTML.next().children(".amount").attr('name', 'prepaymentPrincipal');
			} else if (tdInterHTML.text() == "逾期还本") {
				tdInterHTML.next().children(".amount").attr('name', 'overduePrincipal');
			}
			$(this).parent().css("display", "none");
			$(this).parent().prev().css("display", "none");
			return false;
		})
	}
</script>
<!-- 点击删除添加 -->
<script type="text/javascript">
	window.onload = function() {
		var btn1 = document.getElementById("addRow");
		btn1.onclick = function() {
			var myTable = document.getElementById("myTable");
			var rowIndex = myTable.rows.length - 1;
			var newRow = myTable.insertRow(rowIndex);
			newRow.id = "row" + rowIndex;
			newRow.className = "pos";
			newRow.innerHTML = '<td class="td_pos" width="116px" onclick="addText($(this))"></td><td colspan="3" style="position:relative"><input type="text" name=""class="amount" value="" onkeyup="clearNoNum(this)" onblur="clearNoNum(this)" maxlength="12" style="border:none;float:left"><img src="images/tab_det.png" id="deleteRow" onclick=\"delRow(row'
					+ rowIndex + ');\"></td>'
		}
	}
	function delRow(idName) {
		option = {
			title : "信息",
			btn : parseInt("0011", 2),
			icon : "0 -96px",
			onOk : function() {
				$(idName).remove();
				sum();
			}
		}
		window.wxc.xcConfirm('确定要删除吗？', "custom", option);
	}
</script>
<script type="text/javascript">
	$(function() {
		navSelected(0);
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
<script>
<!-- 保存 -->
	function add() {
		var area = $('#area').val();
		var names = $("#names").val();
		var money = $("#money").val();
		var days = $("#days").val();
		var businessType = $('#businessType').val();
		var salesman = $("#salesman").val();
		var riskman = $("#riskman").val();
		var nums = $("#grade").val();
		if (area == null) {
			$(".Area").text("请选择分公司");
			return false;
		} else {
			$(".Area").text(" ");
		}
		if (names == "") {
			$(".Names").text("请输入姓名");
			return false;
		} else {
			$(".Names").text(" ");
		}
		if (money == "") {
			$(".Money").text("请输入金额");
			return false;
		} else {
			$(".Money").text(" ");
		}
		if (days == "") {
			$(".Days").text("请输入期限");
			return false;
		} else {
			$(".Days").text(" ");
		}
		if (businessType == null) {
			$(".BusinessType").text("请选择业务类型");
			return false;
		} else {
			$(".BusinessType").text("");
		}

		if (riskman == null) {
			$(".Riskman").text("请选择建表人");
			return false;
		} else {
			$(".Riskman").text(" ");
		}
		if (salesman == null) {
			$(".Salesman").text("请选择客户经理");
			return false;
		} else {
			$(".Salesman").text(" ");
		}
		if (nums == "") {
			$(".Nums").text("请输入标号(不可重复)");
			return false;
		} else {
			$(".Nums").html("");
		}
		//提交或保存
		var a = 0;
		//本金
		var b = 2;
		$("#types").attr("value", a);
		$("#audit_id").attr("value", b);
		$("#save").target = "nm_iframe";
		/* 状态(未审核0  已审核1 ,已作废3，已打回/审核未通过2 */
		$("#type").attr("value", a)
		var formObject = {};
		var formArray = $("#save").serializeArray();
		$.each(formArray, function(i, item) {
			formObject[item.name] = item.value;
		});
		var formJson = JSON.stringify(formObject);
		(formJson)
		$.ajax({
			//几个参数需要注意一下
			type : "POST",//方法类型
			dataType : "json",//预期服务器返回的数据类型
			url : "principalfk.action",
			data : formJson,
			contentType : 'application/json',
			success : function(result) {
				console.log(result.code)
				if (result.code == 0) {

					//保存表单后，输入框内容为只读，不可修改
					console.log("风控本金添加成功！！");
					window.location.href = "toShowAll.action";
				} else {
					console.log(result.msg);
					window.wxc.xcConfirm("抱歉！出了一点小问题", window.wxc.xcConfirm.typeEnum.error);
				}
			},
			error : function(result) {
				window.wxc.xcConfirm("抱歉！出了一点小问题", window.wxc.xcConfirm.typeEnum.error);
			}
		});
	}

	/* 提交 */
	function add1() {
		var area = $('#area').val();
		var names = $("#names").val();
		var money = $("#money").val();
		var days = $("#days").val();
		var businessType = $('#businessType').val();
		var salesman = $("#salesman").val();
		var nums = $("#grade").val();
		if (area == null) {
			$(".Area").text("请选择分公司");
			return false;
		} else {
			$(".Area").text(" ");
		}
		if (names == "") {
			$(".Names").text("请输入姓名");
			return false;
		} else {
			$(".Names").text(" ");
		}
		if (money == "") {
			$(".Money").text("请输入金额");
			return false;
		} else {
			$(".Money").text(" ");
		}
		if (days == "") {
			$(".Days").text("请输入期限");
			return false;
		} else {
			$(".Days").text(" ");
		}
		if (businessType == null) {
			$(".BusinessType").text("请选择业务类型");
			return false;
		} else {
			$(".BusinessType").text(" ");
		}
		if (riskman == null) {
			$(".Riskman").text("请选择建表人");
			return false;
		} else {
			$(".Riskman").text(" ");
		}
		if (salesman == null) {
			$(".Salesman").text("请选择客户经理");
			return false;
		} else {
			$(".Salesman").text(" ");
		}
		if (nums == "") {
			$(".Nums").text("请输入标号(不可重复)");
			return false;
		} else {
			$(".Nums").html("");
			//提交或保存
			var a = 0;
			//本金
			var b = 2;
			$("#types").attr("value", 1);
			$("#audit_id").attr("value", b);
			$("#save").target = "nm_iframe";
			/* 状态(未审核0  已审核1 ,已作废3，已打回/审核未通过2 */
			$("#type").attr("value", a)
			var formObject = {};
			var formArray = $("#save").serializeArray();
			$.each(formArray, function(i, item) {
				formObject[item.name] = item.value;
			});
			var formJson = JSON.stringify(formObject);
			(formJson)
			$.ajax({
				//几个参数需要注意一下
				type : "POST",//方法类型
				dataType : "json",//预期服务器返回的数据类型
				url : "principalfk.action",
				data : formJson,
				contentType : 'application/json',
				success : function(result) {
					console.log(result.code)
					if (result.code == 0) {

						//保存表单后，输入框内容为只读，不可修改
						console.log("新标添加成功！！");
						window.location.href = "toShowAll.action";
					} else {
						console.log(result.msg);
						window.wxc.xcConfirm("抱歉！出了一点小问题", window.wxc.xcConfirm.typeEnum.error);
					}
				},
				error : function(result) {
					window.wxc.xcConfirm("抱歉！出了一点小问题", window.wxc.xcConfirm.typeEnum.error);
				}
			});
		}

	}
	function firstSel() {
		$("#salesman").html("")
		//如果第一个下拉列表的值改变则调用此方法
		var area = $("#area").val();//得到第一个下拉列表的值
		console.log("获取地区成功")
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
