<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<title>财务管理-支出费用-查看详情</title>
<jsp:include page="nav.jsp"></jsp:include>
			<div class="content">
				<div class="sub-box">
					<ul class="sub-nav">
						<li><a href="toStaff.action">员工管理</a></li>
						<li><a href="toIncome.action">收入费用</a></li>
						<li><a href="toSpend.action" class="selected">支出费用</a></li>
						<li><a href="toFinanceManager.action">标的管理</a></li>
						<li><a href="toShowBeenVoid.action">工资核算</a></li>
						<li><a href="toCompany.action">公司管理</a></li>
					</ul>
					<div class="sub-right">
						<input type="button" value="返回" class="sub-but"  onclick="javascript:history.back(-1);"/>
					</div>
				</div>
				<div class="control">
					<form action="##" id="updateState" method="post" enctype="multipart/form-data">
						<input type="hidden" name="id" id="id" value="${spend.id}" >
						<input type="hidden" name="state" id="state" value="${spend.state}"> 
					</form>
					<form action="##" id="save" target="nm_iframe" method="post" enctype="multipart/form-data">
						<input type="hidden" name="id" id="id" value="${spend.id}" >
						<table class="control_tab">
							<tr>
								<td class="tab_01">分公司</td>
								<td class="width_01">
								<input type="hidden" name="district_id" value="${spend.district_id}" id="selectArea">
									 <select id ="area" name="district_id">	
																	
									 <c:forEach items="${branchOfficeList}" var="list">		
										<option value="${list.id}">${list.area}</option>									
									</c:forEach>
									</select>
								</td>
								<td class="width_01"></td>
								<td class="tab_01">姓名</td>
								<td class="width_01">
									<input type="text" value="${spend.name}" name="name" >
									
								</td>
								
								<td class="width_01"></td>
								<td class="tab_01">标题</td>
							<td><input type="text" value="${spend.title}" name="title" >
								<input type="hidden" id="id1" name="income_name" value=""  maxlength="30" style="border:none;float:left" id="number1">
								<input type="hidden" id="id2" name="money" value=""  maxlength="30" style="border:none;float:left" id="number1">
								<input type="hidden" id="id3" name="digest" value=""  maxlength="30" style="border:none;float:left" id="number1">
							</td>
							</tr>
							
						</table>
							时间:<input type="text" value="${times}"  class="sub-search" style="background: none;"/>
						<table id="myTable" class="control_tab controls_table" style="text-align:center;width:800px;"border="1">
							<tr>
								<td width="180px">
									报销项目
								</td>
								<td>摘要</td>
								<td colspan="3" class="amount" >
									金额
								</td>
							</tr>
							<tr>
								<td>
									<input type="text" name="spend_name" value="${spend.spend_name}"  maxlength="30" style="border:none;float:left" id="number1">
								</td>
								<td>
									<input type="text" name="digest" value="${spend.digest}"  maxlength="30" style="border:none;float:left" id="digest1">
								</td>
								<td colspan="3">
									<input type="text" name="money"  value="${spend.money}" class="amount"  onkeyup="clearNoNum(this)" onblur="clearNoNum(this)" maxlength="12" style="border:none;float:left" id="sum1">
								</td>
							</tr>
							<tr>
								<td>
									<input type="text" name="spend_name1" value="${spend.spend_name1}"  maxlength="30" style="border:none;float:left" id="number2">
								</td>
								<td>
									<input type="text" name="digest1" value="${spend.digest1}"  maxlength="30" style="border:none;float:left" id="digest2">
								</td>
								<td colspan="3">
									<input type="text" name="money1" id="sum2" value="${spend.money1}" class="amount"  onkeyup="clearNoNum(this)" onblur="clearNoNum(this)" maxlength="12" style="border:none;float:left">
								</td>
							</tr>
							<tr>
								<td>
									<input type="text" name="spend_name2" value="${spend.spend_name2}"  maxlength="30" style="border:none;float:left" id="number3">
								</td>
								<td>
									<input type="text" name="digest2" value="${spend.digest2}"  maxlength="30" style="border:none;float:left" id="digest3">
								</td>
								<td colspan="3">
									<input type="text" name="money2" id="sum3" value="${spend.money2}" class="amount"  onkeyup="clearNoNum(this)" onblur="clearNoNum(this)" maxlength="12" style="border:none;float:left">
								</td>
							</tr>
							<tr>
								<td>
									<input type="text" name="spend_name3" value="${spend.spend_name3}"  maxlength="30" style="border:none;float:left" id="number4">
								</td>
								<td>
									<input type="text" name="digest3" value="${spend.digest3}"  maxlength="30" style="border:none;float:left" id="digest4">
								</td>
								<td colspan="3">
									<input type="text" name="money3" id="sum4" value="${spend.money3}" class="amount"  onkeyup="clearNoNum(this)" onblur="clearNoNum(this)" maxlength="12" style="border:none;float:left">
								</td>
							</tr>
							<tr>
								<td>
									<input type="text" name="spend_name4" value=""${spend.spend_name4}  maxlength="30" style="border:none;float:left" id="number5">
								</td>
								<td>
									<input type="text" name="digest4" value="${spend.digest4}"  maxlength="30" style="border:none;float:left" id="digest5">
								</td>
								<td colspan="3">
									<input type="text" name="money4" id="sum5" value="${spend.money4}" class="amount"  onkeyup="clearNoNum(this)" onblur="clearNoNum(this)" maxlength="12" style="border:none;float:left">
								</td>
							</tr>
							<tr>
								<td colspan="2" width="400px"></td>
								<td width="105px" id="sumAdd" name="sum">合计</td>
								<td width="110px" id="td_Amount"><input type="text" value="${spend.sum }" name="sum" readonly style="border:none"></td>
							</tr>
						</table>
						<p class="control_tab">
							<input type="button" value="通过" class="bgbtn" id="sub_form" onclick="pass()">
							<!-- <input type="submit" value="取消" class="bgbtn" style="background:#f45b63"  onclick="javascript:history.back(-1);"> -->
						</p>
					</form>
				</div>
			</div>
<jsp:include page="footer.jsp"></jsp:include>
	</body>
	<script type="text/javascript">
		/*一级导航栏选中 */
		$(function(){
			navSelected(4);
			$("#time").next().children(".textbox-text").attr("placeholder","请输入时间");
			var a=$("#number1").val()+","+$("#number2").val()+","+$("#number3").val()+","+$("#number4").val()+","+$("#number5").val();
		
		})
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
	<!-- 获取地区 -->
	<script type="text/javascript">
		var selectArea = $("#selectArea").val();
		console.log(selectArea);
		$("#area option[value='"+selectArea+"']").attr("selected","selected");
	
		function genlist(t){
			$(t).parent().siblings().children("a").removeClass("selected");
			$(t).addClass("selected");
		}
	function firstSel() {
		$("#name").html("")
		//如果第一个下拉列表的值改变则调用此方法
		var area = $("#area").val();//得到第一个下拉列表的值
		console.log("获取地区成功!!!")
	// var url = "getlistsalesman.action?area="+area;
	
	$.getJSON(url,function(res){
					var option;
					for(var i=0;i<res.length;i++){						
						//循环，i为下标从0开始，n为集合中对应的第i个对象						
						option = "<option value='"+res[i].id+"'>"+res[i].name+"</option>"						
						$("#name").append("<option value='"+res[i].id+"'>"+res[i].name+"</option>");						
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
							$("#name").append("<option value='"+res[i].id+"'>"+res[i].name+"</option>");						
						};
						//将循环拼接的字符串插入第二个下拉列表				
				});		
	});		
	</script>
	<script type="text/javascript">
		/** 提交 */
		function add(){	
			/* $("#time").next().children(".textbox-text").attr("placeholder","请输入时间"); */
			var a=$("#number1").val()+","+$("#number2").val()+","+$("#number3").val()+","+$("#number4").val()+","+$("#number5").val();
		
			var b=$("#sum1").val()+","+$("#sum2").val()+","+$("#sum3").val()+","+$("#sum4").val()+","+$("#sum5").val();
			var c=$("#digest1").val()+","+$("#digest2").val()+","+$("#digest3").val()+","+$("#digest4").val()+","+$("#digest5").val();
			$("#id1").val(a)
			$("#id2").val(b)
			$("#id3").val(c)
			
			var formObject = {};

			var formArray =$("#save").serializeArray();

			$.each(formArray,function(i,item){

				formObject[item.name] = item.value;

			});
			var formJson = JSON.stringify(formObject);
			console.log(formJson+"formJson")
			$.ajax({
				 	type: "POST",//方法类型
	                dataType: "json",//预期服务器返回的数据类型
	                url: "toAddIncome.action",
	                data: formJson,
	                contentType : 'application/json',
	                success: function (result) {
	                    if (result.code == 0) {
	                    	
	                    	window.location.href="toIncome.action";
	                   		console.log("提交成功！！");
	                    }else{
	                    	console.log(result.msg);
	                    }                   
	                },
	                error : function(result) {
	                	console.log("异常！！"+result.msg);
	                }
			});
			
		}
		
	</script>
	<!-- 通过-->
<script type="text/javascript">
/* 通过 */
$("#save input").attr("readOnly",true);
$("#save select").attr("disabled",true);
function pass(){
	
	var pass_id = $("#id").val();
	
	var formObject = {};

	var formArray = $("#updateState").serializeArray();

	$.each(formArray,function(i,item){

		formObject[item.name] = item.value;

	});
	var formJson = JSON.stringify(formObject);
	
	$.ajax({		
			//几个参数需要注意一下
             type: "POST",//方法类型
             dataType: "json",//预期服务器返回的数据类型
             url: "updatePasszc.action?id="+pass_id,
             data:formJson,
             contentType : 'application/json',
             success: function (result) {
	           
	             if (result.code == 0) {
	              //提交成功后，提交到财务可查看审核页面
	            	  window.location.href="toSpend.action";
	             	 
	              }                   
         	  },
                error : function(result) {
                	window.wxc.xcConfirm("抱歉！出了一点小问题", window.wxc.xcConfirm.typeEnum.error);
                }          
	 });
		
		//提交表单后，输入框内容为不可用
		$("#save input").attr("readOnly","true");
}
	//如果状态是1的话通过按钮不显示
	var state = $("#state").val();
	if(state==1){
	
    	$("#sub_form").attr("type","hidden");
	}

</script>
</html>