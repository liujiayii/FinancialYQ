<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<title>新标财务审核</title>
<style type="text/css">
	#times:hover{border: 1.5px solid #d3c6ff;}
	.control_tab tr {height: 90px !important;line-height: 90px !important;}
</style>
<jsp:include page="nav.jsp"></jsp:include>
	<div class="content">
		<div class="control">
			<form action="##" id="updateStatus" method="post" enctype="multipart/form-data">
				<input type="hidden" name="id" id="id" value="${finance.id}" >
			</form>
			<form action="##" id="save" target="nm_iframe" method="post" enctype="multipart/form-data">
			<input type="hidden" name="s_id" id="s_id">
			<input type="hidden" name="audit_id" id="audit_id" value="${finance.audit_id}">
			
			<table class="control_tab">
				<tr>
					<td class="tab_01">分公司</td>
					<td class="width_01"><input type="hidden" name="area"
						value="${finance.area}" id="selectArea"> <select id="area"
						name="area" class="width_01"
						style="border: 1px solid #dcdcdc; padding-left: 10px"
						onchange="firstSel()">
							<c:forEach items="${branchOfficeList}" var="list">
								<option value="${list.area}">${list.area}</option>
							</c:forEach>
					</select></td>
					<td width="79">姓名</td>
					<td class="width_01"><input type="text" name="name"
						value="${finance.name}"></td>
					<td width="84">金额</td>
					<td class="width_01" style="text-align: left"><input
						type="text" name="money" value="${finance.money}"
						style="width: 102px"></td>
					<td width="79">期限</td>
					<td class="width_01"><input type="text" name="day"
						value="${finance.day}个月"></td>
				</tr>
				<tr>
					<td class="tab_01">业务类型</td>
					<td class="width_01"><input type="hidden"
						id="selectBusinessType" value="${finance.businessType}" /> <select
						name="businessType" id="businessType">
							<option value="1">惠车贷</option>
							<option value="0">惠房贷</option>
					</select></td>
					<td width="79">客户经理</td>
					<td class="width_01"><input type="hidden" name="s_id"
						value="${finance.s_id}" id="selectMan" /> <select id="salesman"
						name="s_id" class="width_01">
					</select></td>
					<td width="84">时间</td>

					<td width="185" class="input_hove"><input type="text"
						name="time" value="${strDate}"></td>

					<td width="79">标号</td>
					<td class="width_01"><input type="text" name="grade"
						value="${finance.grade}"></td>
						</tr>
						<tr>
					<td class="tab_01">建表人</td>
								<td class="width_01">
								<input type="hidden" id="selectRiskman"  value="${finance.riskman}"/>
									<select name="riskman" id="riskman">
											
										<option value="风控1">风控1</option>
										<option value="风控2">风控2</option>
										<option value="风控3">风控3</option>
									</select>
								</td>
				</tr>
			</table>
			<table id="myTable" class="control_tab controls_table" style="text-align:center;width:865px;"border="1">
					<!-- <tr>
						<td width="143px" class="td_pos" onclick="addText($(this))">
					
						</td>
						
						<td colspan="3" style="position:relative">
							<img src="images/tab_det.png" id="deleteRow"  onclick="delRow('del1');">
							<img class="tab_img" src="images/tab_add.png" id="addRow" >
							<input type="text" name="" class="amount" value="" onkeyup="clearNoNum(this)" onblur="clearNoNum(this)" maxlength="12" style="border:none;float:left">
						</td>
					</tr> -->
					<c:if test="${not empty finance.principal}">
					<tr>
						<td width="143px" class="td_pos" >
						还本金
						</td>
						<td colspan="3" style="position:relative">
							<!-- <img src="images/tab_det.png" id="deleteRow"  onclick="delRow('del1');"> -->
							
							<input type="text" name="principal" class="amount" value="${finance.principal}" onkeyup="clearNoNum(this)" onblur="clearNoNum(this)" maxlength="12" style="border:none;float:left">
						</td>
					</tr>
				</c:if>
					<c:if test="${not empty finance.interest}">
					<tr>
						<td width="143px" class="td_pos" >
						平台服务费+利息
						</td>
						<td colspan="3" style="position:relative">
						<!-- 	<img src="images/tab_det.png" id="deleteRow"  onclick="delRow('del1');"> -->
							
							<input type="text" name="interest" class="amount" value="${finance.interest}" onkeyup="clearNoNum(this)" onblur="clearNoNum(this)" maxlength="12" style="border:none;float:left">
						</td>
					</tr>
				</c:if>
				<c:if test="${not empty finance.serviceMoney}">
					<tr>
						<td width="143px" class="td_pos" >
						点位费
						</td>
						<td colspan="3" style="position:relative">
							
							<!-- <img src="images/tab_det.png" id="deleteRow"  onclick="delRow('del1');"> -->
							
							<input type="text" name="serviceMoney" class="amount" value="${finance.serviceMoney}" onkeyup="clearNoNum(this)" onblur="clearNoNum(this)" maxlength="12" style="border:none;float:left">
						</td>
					</tr>
				</c:if>
				<c:if test="${not empty finance.houseProperty}">
					<tr>
						<td width="116px" class="td_pos" >
						房产费
						</td>
						<td colspan="3" style="position:relative">
							
							<!-- <img src="images/tab_det.png" id="deleteRow"  onclick="delRow('del1');"> -->
							
							<input type="text" name="houseProperty" class="amount" value="${finance.houseProperty}" onkeyup="clearNoNum(this)" onblur="clearNoNum(this)" maxlength="12" style="border:none;float:left">
						</td>
					</tr>
				</c:if>
				<c:if test="${not empty finance.archivesMoney}">
					<tr>
						<td width="116px" class="td_pos" >
						档案费
						</td>
						<td colspan="3" style="position:relative">
							
							<!-- <img src="images/tab_det.png" id="deleteRow"  onclick="delRow('del1');"> -->
							
							<input type="text" name="archivesMoney" class="amount" value="${finance.archivesMoney}" onkeyup="clearNoNum(this)" onblur="clearNoNum(this)" maxlength="12" style="border:none;float:left">
						</td>
					</tr>
				</c:if>
				<c:if test="${not empty finance.capitalMoney}">
					<tr>
						<td width="116px" class="td_pos" >
						垫资费
						</td>
						<td colspan="3" style="position:relative">
							
							<!-- <img src="images/tab_det.png" id="deleteRow"  onclick="delRow('del1');"> -->
							
							<input type="text" name="capitalMoney" class="amount" value="${finance.capitalMoney}" onkeyup="clearNoNum(this)" onblur="clearNoNum(this)" maxlength="12" style="border:none;float:left">
						</td>
					</tr>
				</c:if>
				<c:if test="${not empty finance.account}">
					<tr>
						<td width="116px" class="td_pos" >
						过账
						</td>
						<td colspan="3" style="position:relative">
							
							<!-- <img src="images/tab_det.png" id="deleteRow"  onclick="delRow('del1');"> -->
							
							<input type="text" name="account" class="amount" value="${finance.account}" onkeyup="clearNoNum(this)" onblur="clearNoNum(this)" maxlength="12" style="border:none;float:left">
						</td>
					</tr>
				</c:if>
				<c:if test="${not empty finance.landMoney}">
					<tr>
						<td width="116px" class="td_pos" >
						实地费
						</td>
						<td colspan="3" style="position:relative">
							
							<!-- <img src="images/tab_det.png" id="deleteRow"  onclick="delRow('del1');"> -->
							
							<input type="text" name="landMoney" class="amount" value="${finance.landMoney}" onkeyup="clearNoNum(this)" onblur="clearNoNum(this)" maxlength="12" style="border:none;float:left">
						</td>
					</tr>
				</c:if>
				<c:if test="${not empty finance.gpsMoney}">
					<tr>
						<td width="116px" class="td_pos" >
						GPS安装
						</td>
						<td colspan="3" style="position:relative">
							
							<!-- <img src="images/tab_det.png" id="deleteRow"  onclick="delRow('del1');"> -->
							
							<input type="text" name="gpsMoney" class="amount" value="${finance.gpsMoney}" onkeyup="clearNoNum(this)" onblur="clearNoNum(this)" maxlength="12" style="border:none;float:left">
						</td>
					</tr>
				</c:if>
				<c:if test="${not empty finance.stopMoney}">
					<tr>
						<td width="116px" class="td_pos" >
						停车费
						</td>
						<td colspan="3" style="position:relative">
							
							<!-- <img src="images/tab_det.png" id="deleteRow"  onclick="delRow('del1');"> -->
							
							<input type="text" name="stopMoney" class="amount" value="${finance.stopMoney}" onkeyup="clearNoNum(this)" onblur="clearNoNum(this)" maxlength="12" style="border:none;float:left">
						</td>
					</tr>
				</c:if>
				<c:if test="${not empty finance.jindiMoney}">
					<tr>
						<td width="116px" class="td_pos" >
						进抵费
						</td>
						<td colspan="3" style="position:relative">
							
							<!-- <img src="images/tab_det.png" id="deleteRow"  onclick="delRow('del1');"> -->
							
							<input type="text" name="jindiMoney" class="amount" value="${finance.jindiMoney}" onkeyup="clearNoNum(this)" onblur="clearNoNum(this)" maxlength="12" style="border:none;float:left">
						</td>
					</tr>
				</c:if>
				<c:if test="${not empty finance.fileMoney}">
					<tr>
						<td width="116px" class="td_pos" >
						查档费
						</td>
						<td colspan="3" style="position:relative">
							
							<!-- <img src="images/tab_det.png" id="deleteRow"  onclick="delRow('del1');"> -->
							
							<input type="text" name="fileMoney" class="amount" value="${finance.fileMoney}" onkeyup="clearNoNum(this)" onblur="clearNoNum(this)" maxlength="12" style="border:none;float:left">
						</td>
					</tr>
				</c:if>
				<c:if test="${not empty finance.elseMoney}">
					<tr>
						<td width="116px" class="td_pos" >
						他项费
						</td>
						<td colspan="3" style="position:relative">
							
							<!-- <img src="images/tab_det.png" id="deleteRow"  onclick="delRow('del1');"> -->
							
							<input type="text" name="elseMoney" class="amount" value="${finance.elseMoney}" onkeyup="clearNoNum(this)" onblur="clearNoNum(this)" maxlength="12" style="border:none;float:left">
						</td>
					</tr>
				</c:if>
					<tr>
						<td colspan="2" width="400px"></td>
						<td width="105px" id="sumAdd">合计</td>
						<td width="110px" id="td_Amount"><input type="text" name="sum" value="${finance.sum}" readonly style="border:none"></td>
					</tr>
				</table>
				<p class="control_tab">
				<button class="bgbtn" onclick="pass()" >通过</button>
				<button class="bgbtn" onclick="back()" style="background:#f45b63">打回</button>
				</p>
			</form>
			<iframe id="id_iframe" name="nm_iframe" style="display:none" src="about:blank"></iframe>
		</div>
	</div>
<jsp:include page="footer.jsp"></jsp:include>
	</body>
	<script type="text/javascript">
	$(function(){
		navSelected(0);
	})
	
	</script>
	
<!-- 通过  和 打回-->
<script type="text/javascript">
/* 通过 */
$("#save input").attr("readOnly",true);
$("#save select").attr("disabled",true);
function pass(){
	var pass_id = $("#id").val();
	
	console.log(pass_id);
	/* 状态(未审核0  已审核1 ,已作废3，已打回/审核未通过2 */
	var formObject = {};

	var formArray = $("#updateStatus").serializeArray();

	$.each(formArray,function(i,item){

		formObject[item.name] = item.value;

	});
	var formJson = JSON.stringify(formObject);
	
		$.ajax({		
				//几个参数需要注意一下
                type: "POST",//方法类型
                dataType: "json",//预期服务器返回的数据类型
                url: "updatePass.action?id="+pass_id,
                data:formJson,
                contentType : 'application/json',
                success: function (result) {
                
                    if (result.code == 0) {
                    	//提交成功后，提交到财务可查看审核页面
                    	window.location.href="toShowCheked.action";
             
                   		console.log("成功通过！！");
                    }else{
                    	window.wxc.xcConfirm("抱歉！出了一点小问题", window.wxc.xcConfirm.typeEnum.error);
                    }                   
                },
                error : function(result) {
                	window.wxc.xcConfirm("抱歉！出了一点小问题", window.wxc.xcConfirm.typeEnum.error);
                }          
	 });

		//提交表单后，输入框内容为不可用
		$("#save input").attr("readOnly","true");
}
 /* 打回 */
function back(){
	
	var back_id = $("#id").val();
	
	console.log(back_id);
	/* 状态(未审核0  已审核1 ,已作废3，已打回/审核未通过2 */
	var formObject = {};

	var formArray = $("#updateStatus").serializeArray();

	$.each(formArray,function(i,item){

		formObject[item.name] = item.value;

	});
	var formJson = JSON.stringify(formObject);

		$.ajax({		
				//几个参数需要注意一下
                type: "POST",//方法类型
                dataType: "json",//预期服务器返回的数据类型
                url: "updateBack.action?id="+back_id,
                data: formJson,
                contentType : 'application/json',
                success: function (result) {
                	
                    if (result.code == 0) {
                    	//提交成功后，提交到财务可查看审核页面
                    	window.location.href="toShowBeenBack.action";
             
                   		console.log("打回成功！！");
                    }else{
                    	console.log(result.msg);
                    }                   
                },
                error : function(result) {
                	window.wxc.xcConfirm("抱歉！出了一点小问题", window.wxc.xcConfirm.typeEnum.error);
                }         
	 });
		//提交表单后，输入框内容为不可用
		$("#save input").attr("readOnly","true");
}


</script>
	<script type="text/javascript">
	$(function(){
		var a= $("#selectArea").val();
		var b= $("#selectBusinessType").val();
		var c = $("#selectMan").val();
		var d = $("#selectRiskman").val();
	
		$("#area option[value='"+a+"']").attr("selected","selected");	
		$("#businessType option[value='"+b+"']").attr("selected","selected");
		$("#salesman option[value='"+c+"']").attr("selected","selected");
		$("#riskman option[value='"+d+"']").attr("selected","selected");
	})	
		function genlist(t){
			$(t).parent().siblings().children("a").removeClass("selected");
			$(t).addClass("selected");
		}
	$(function(){
		
		var area = $("#area").val();//得到第一个下拉列表的值
		
		var url = "getlistsalesman.action?area="+area;
		
		$.getJSON(url,function(res){
																
			var option;
			for(var i=0;i<res.length;i++){
				
				var b=$("#selectMan").val();
		
				//循环，i为下标从0开始，n为集合中对应的第i个对象

				if(res[i].id!=b){

					option = "<option value='"+res[i].id+"'>"+res[i].name+"</option>"
	}
				else{
					option = "<option value='"+res[i].id+"'selected='selected'>"+res[i].name+"</option>"
				}
										
				$("#salesman").append(option);
				
			};
			//将循环拼接的字符串插入第二个下拉列表
					
				});
		
	});
	</script>
</html>
