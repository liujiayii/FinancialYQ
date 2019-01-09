<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<title>风控本金审核</title>
<style type="text/css">
	#times:hover{border: 1.5px solid #d3c6ff;}
	.control_tab tr {height: 90px !important;line-height: 90px !important;}
</style>
<jsp:include page="nav.jsp"></jsp:include>
			<div class="content">
				<div class="control">
					<form action="##" id="updateStatus" target="nm_iframe" method="post" enctype="multipart/form-data">
					
					<input type="hidden" name="audit_id" id="audit_id" >
					<input type="hidden" name="id" id="id" value="${finance.id}" >
					<input type="hidden" name="types" id="types" value="${finance.types}">
					<input type="hidden" name="type" id="type" value="${finance.type}">
						<table class="control_tab">
							<tr>
							<td class="tab_01" >分公司</td>
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
									<input type="text" name="name" value="${finance.name}" >
								</td>
								<td width="84">金额</td>
								<td class="width_01" style="text-align:left"><input type="text" name="money" value="${finance.money}" style="width:102px"></td>
								<td width="79">期限</td>
								<td class="width_01"><input type="text" name="day" value="${finance.day}" style="width: 73px; margin-right: 10px;"onkeyup="this.value=this.value.replace(/[^\d]/g,'');" maxlength="1">个月</td>
							</tr>
							<tr>
								<td class="tab_01">业务类型</td>
								<td class="width_01">
									<input type="hidden" id="selectBusinessType" value="${finance.businessType}"/>
									<select name="businessType" id="businessType" class="form-select">
										<option value="1">惠车贷</option>
										<option value="0">惠房贷</option>
									</select>
								</td>
								<td width="79">客户经理</td>
								<td class="width_01">
								<input type="hidden"  name="s_id" value="${finance.s_id}" id="selectMan"/>
								 <select id ="salesman" name="s_id" class="width_01" >
								 </select>								
								</td>
								<td width="79">标号</td>
								<td class="width_01"><input type="text" name="grade" value="${finance.grade}"></td>
								<td width="79">旧标号</td>
								<td class="width_01"><input type="text" name="formergrade" value="${finance.formergrade}" maxlength="6"></td>
							
							</tr>
							
								<tr>
								
								<td width="79">期数</td>
								<td class="width_01"><input type="text" name="period" value="${finance.period}" id="period" ;margin-right:10px;" maxlength="4"   ></td>
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
				<c:if test="${not empty finance.refinancePrincipal}">
					<tr>
						<td width="116px" class="td_pos" >
						续贷还本
						</td>
						<td colspan="3" style="position:relative">						
							<input type="text" name="refinancePrincipal" class="amount" value="${finance.refinancePrincipal}" onkeyup="clearNoNum(this)" onblur="clearNoNum(this)"  maxlength="12" style="border:none;float:left">
						</td>
					</tr>
					</c:if>
				<c:if test="${not empty finance.maturityPrincipal}">
					<tr>
						<td width="143px" class="td_pos" >
						到期还本
						</td>
						<td colspan="3" style="position:relative">							
							<input type="text" name="maturityPrincipal" class="amount" value="${finance.maturityPrincipal}" onkeyup="clearNoNum(this)" onblur="clearNoNum(this)" maxlength="12" style="border:none;float:left">
						</td>
					</tr>
				</c:if>
				
				<c:if test="${not empty finance.prepaymentPrincipal}">
					<tr>
						<td width="116px" class="td_pos" >
						提前还本
						</td>
						<td colspan="3" style="position:relative">												
							<input type="text" name="prepaymentPrincipal" class="amount" value="${finance.prepaymentPrincipal}" onkeyup="clearNoNum(this)" onblur="clearNoNum(this)" maxlength="12" style="border:none;float:left">
						</td>
					</tr>
				</c:if>
				<c:if test="${not empty finance.overduePrincipal}">
					<tr>
						<td width="116px" class="td_pos" >
						逾期还本
						</td>
						<td colspan="3" style="position:relative">						
							<input type="text" name="overduePlatform" class="amount" value="${finance.overduePrincipal}" onkeyup="clearNoNum(this)" onblur="clearNoNum(this)" maxlength="12" style="border:none;float:left">
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
		$("#updateStatus input").attr("readOnly",true);
		$("#updateStatus select").attr("disabled",true);
		$("span").click(function(){
			$(".show_next").hide();
		});
	</script>
	<!-- 通过  和 打回-->
<script type="text/javascript">
/* 通过 */

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
		$("#updateStatus input").attr("readOnly","true");
}
 /* 打回 */
function back(){
	
	var back_id = $("#id").val();
	//$("#types").attr("value",0)
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
		$("#updateStatus input").attr("readOnly","true");
}


</script>
	
	

	<!-- 表格内下拉框 -->
	<script type="text/javascript">
		function addText(a){
			a.html('');
			if(a.html().trim()==''){
				a.append('<div class="div_dis"><input type="text" class="frame-style" readonly="readonly" value=" " disabled/><img src="images/bg_arr.png" class="bg_arri" /></div><ul class="menue-list"><li id="refinancePrincipal">续贷还本</li><li id="maturityPrincipal">到期还本</li><li id="prepaymentPrincipal">提前还本</li><li id="overduePrincipal">逾期还本</li></ul>');
			}else{
				return false;
			}
			$('.menue-list li').click(function(){
				var Current=$(this).text();
				var tdInterHTML = $(this).parent().parent();
				tdInterHTML.text(Current);
				if(tdInterHTML.text() == "续贷还本"){					
					tdInterHTML.next().children(".amount").attr('name','refinancePrincipal');
				}else if(tdInterHTML.text() == "到期还本"){
					tdInterHTML.next().children(".amount").attr('name','maturityPrincipal');
				}else if(tdInterHTML.text() == "提前还本"){
					tdInterHTML.next().children(".amount").attr('name','prepaymentPrincipal');
				}else if(tdInterHTML.text() == "逾期还本"){
					tdInterHTML.next().children(".amount").attr('name','overduePrincipal');
				}
				$(this).parent().css("display","none");
				$(this).parent().prev().css("display","none");
				return false;
			})
	 	}
		
	
	</script>
	<!-- 合计 -->
	<script type="text/javascript">
	function sum(){
		var tb = document.getElementById('myTable');
	    var count = 0;
	    for (var i = 0; i < tb.rows.length - 1; i++) {
	    	var num = $(tb.rows[i]).find('.amount').val().trim();
	    	if(num == ""){
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
			obj.value = obj.value.replace(/[^\d.]/g,"");
			//保证只有出现一个.而没有多个.
			obj.value = obj.value.replace(/\.{2,}/g,".");
			//必须保证第一个为数字而不是.
			obj.value = obj.value.replace(/^\./g,"");
			//保证.只出现一次，而不能出现两次以上
			obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
			//只能输入两个小数
			obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3');
			sum();
		}
	</script>
	
	<script type="text/javaScript">
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
		$(function(){
			navSelected(0);	
		});
		function genlist(t){
			$(t).parent().siblings().children("a").removeClass("selected");
			$(t).addClass("selected");
		}
	</script>
	<script type="text/javascript">
		function cancel(){
			window.location.href="toShowBeenBack.action";
		}
	</script>
	<script type="text/javascript">
	
	function firstSel() {
		$("#salesman").html("")
		//如果第一个下拉列表的值改变则调用此方法		
		var area = $("#area").val();//得到第一个下拉列表的值
	console.log("获取地区成功!!!")
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
						
					};					//将循环拼接的字符串插入第二个下拉列表
				
			});	
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
