<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<title>风控利息审核</title>
<jsp:include page="nav.jsp"></jsp:include>
	<div class="content">
			
		<div class="control">
			<form action="##" id="updateStatus" method="post" enctype="multipart/form-data">
				<input type="hidden" name="id" id="id" value="${finance.id}" >
			</form>
			<form action="##" id="save" target="nm_iframe" method="post" enctype="multipart/form-data">
				
				<input type="hidden" name="s_id" id="s_id">
				<input type="hidden" name="audit_id" id="audit_id">
				
				<input type="hidden" name="types" id="types" value="${finance.types}">
				<input type="hidden" name="type" id="type" value="${finance.type}" >
				<table class="control_tab">
					<tr>
						<td class="tab_01">分公司</td>
						<td class="width_01">
							<input type="hidden" name="area" value="${finance.area}" id="selectArea">
							 <select id ="area" name="area" class="width_01" style="border: 1px solid #dcdcdc;padding-left:10px" onchange="firstSel()">										
							 <c:forEach items="${branchOfficeList}" var="list">
								<option value="${list.area}">${list.area}</option>									
							</c:forEach>
							</select>
						</td>
						<td width="79">姓名</td>
						<td class="width_01">
							<input type="text" name="name" id="names" value="${finance.name}">
						</td>
						<td width="84">金额</td>
						<td class="width_01" style="text-align:left"><input type="text" name="money" id="money" value="${finance.money}"></td>
						<td width="90">期限</td>
						<td class="width_01"><input type="text" name="day" value="${finance.day}" id="days" style="width:73px;margin-right:10px;">个月</td>
					</tr>
					<tr class="font_color">
						
					</tr>
					<tr>
						<td class="tab_01">业务类型</td>
						<td class="width_01">
							<input type="hidden" id="selectBusinessType" value="${finance.businessType}"/>
							<select name="businessType" id="businessType">
								
								<option value="0">惠车贷</option>
								<option value="1">惠房贷</option>
							</select>
						</td>
						<td width="79">客户经理</td>
						<td class="width_01">
							<input type="hidden"  name="s_id" value="${finance.s_id}" id="selectMan"/>
							<select id ="salesman" name="s_id" class="width_01" >
								
							</select>
						</td>
						<td width="79">标号</td>
						<td class="width_01"><input type="text" name="grade" id="grade" value="${finance.grade}"></td>
						<td width="79">旧标号</td>
						<td class="width_01"><input type="text" name="formergrade" id="old_nums" value="${finance.formergrade}"></td>
					</tr>
					
					<tr class="font_color">
						
					</tr>
					<tr>
						<td width="79">期数</td>
						<td class="width_01"><input type="text" name="period" value="${finance.period}" id="period"></td>
						<td width="79">建表人</td>
						<td class="width_01">
							<input type="hidden" id="selectRiskman"  value="${finance.riskman}"/>
							<select name="riskman" id="riskman">
								
								<option value="风控1">风控1</option>
								<option value="风控2">风控2</option>
								<option value="风控3">风控3</option>
							</select>
						</td>
						
					</tr>
					<tr class="font_color">
						
					</tr>
				</table>
				<table id="myTable" class="control_tab controls_table" style="text-align:center;width:865px;"border="1">
				<c:if test="${not empty finance.interest}">
					<tr>
						<td width="116px" class="td_pos" >
						平台服务费+利息（风）
						</td>
						<td colspan="3" style="position:relative">												
							<input type="text" name="interest" class="amount" value="${finance.interest}" style="border:none;float:left">
						</td>
					</tr>
				</c:if>
					<tr>
						<td colspan="2" width="400px"></td>
						<td width="105px" id="sumAdd">合计</td>
						<td width="110px" id="td_Amount"><input type="text" name="sum" value="${finance.sum}" readonly style="border:none;"></td>
					</tr>
				</table>
				<p class="control_tab">
					<input class="bgbtn" onclick="pass()" value="通过">
					<input class="bgbtn" onclick="back()" style="background:#f45b63" value="打回">
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
	$(function(){
		var a= $("#selectArea").val();
		var b= $("#selectBusinessType").val();
		
		var d = $("#selectMan").val();
		var e = $("#selectRiskman").val();
		
		$("#area option[value='"+a+"']").attr("selected","selected");	
		$("#businessType option[value='"+b+"']").attr("selected","selected");
		
		$("#salesman option[value='"+d+"']").attr("selected","selected");
		$("#riskman option[value='"+e+"']").attr("selected","selected");
		
	});	
	function firstSel() {
		$("#salesman").html("")
		//如果第一个下拉列表的值改变则调用此方法
		var area = $("#area").val();//得到第一个下拉列表的值
		console.log("获取地区成功!!!")
	 var url = "getlistsalesman.action?area="+area;
	
	$.getJSON(url,function(res){
					var option;
					for(var i=0;i<res.length;i++){						
						//循环，i为下标从0开始，n为集合中对应的第i个对象						
						option = "<option value='"+res[i].id+"'>"+res[i].name+"</option>"						
						$("#salesman").append("<option value='"+res[i].id+"'>"+res[i].name+"</option>");						
					};
					//将循环拼接的字符串插入第二个下拉列表				
			});
	}
	$(function(){
		var area = $("#area").val();//得到第一个下拉列表的值
		
		var url = "getlistsalesman.action?area=石家庄";
		
		$.getJSON(url,function(res){
						var option;
						for(var i=0;i<res.length;i++){	
							//循环，i为下标从0开始，n为集合中对应的第i个对象							
							option = "<option value='"+res[i].id+"'>"+res[i].name+"</option>"						
							$("#salesman").append("<option value='"+res[i].id+"'>"+res[i].name+"</option>");						
						};
						//将循环拼接的字符串插入第二个下拉列表				
				});		
	});	
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
}


</script>
	
</html>