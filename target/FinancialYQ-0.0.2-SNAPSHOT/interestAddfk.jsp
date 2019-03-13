<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<title>风控利息添加</title>
<jsp:include page="nav.jsp"></jsp:include>
	<div class="content">
		<div class="control">
			<form action="##" id="save" target="nm_iframe" method="post" enctype="multipart/form-data">
				<input type="hidden" name="s_id" id="s_id">
				<input type="hidden" name="audit_id" id="audit_id">
				<input type="hidden" name="id" value="${finance.id}" id="id" >
				<input type="hidden" name="types" id="types" value="${finance.types}">
				<input type="hidden" name="type" id="type" value="${finance.type}" >
				<table class="control_tab">
					<tr>
						<td class="tab_01">分公司</td>
						<td class="width_01">
							 <select id ="area" name="area" class="width_01" style="border: 1px solid #dcdcdc;padding-left:10px" onchange="firstSel()">										
								<option value="" disabled selected hidden>选择分公司</option>
							 <c:forEach items="${branchOfficeList}" var="list">
								<option value="${list.area}">${list.area}</option>									
							</c:forEach>
							</select>
						</td>
						<td width="79">姓名</td>
						<td class="width_01">
							<input type="text" name="name" id="names" value="" placeholder="输入姓名">
						</td>
						<td width="84">金额</td>
						<td class="width_01" style="text-align:left"><input type="text" name="money" onkeyup="clearNoNum(this)" onblur="clearNoNum(this)"  id="money" value="" placeholder="输入金额"></td>
						<td width="90">期限</td>
						<td class="width_01"><input type="text" name="day" value="" id="days" style="width:73px;margin-right:10px;" onkeyup="this.value=this.value.replace(/[^\d]/g,'');" maxlength="1" placeholder="输入期限" >个月</td>
					</tr>
					<tr class="font_color">
						<td colspan="2" class="Area"></td>
						<td colspan="2" class="Names"></td>
						<td colspan="2" class="Money"></td>
						<td colspan="2" class="Days"></td>
					</tr>
					<tr>
						<td class="tab_01">业务类型</td>
						<td class="width_01">
							<select name="businessType" id="businessType">
								<option value="" disabled selected hidden>选择业务类型</option>
								<option value="0">惠车贷</option>
								<option value="1">惠房贷</option>
							</select>
						</td>
						<td width="79">客户经理</td>
						<td class="width_01">
							<select id ="salesman" name="s_id" class="width_01" >
								<option value="" disabled selected hidden>选择客户经理</option>
							</select>
						</td>
						<td width="79">标号</td>
						<td class="width_01"><input type="text" name="grade" id="grade" value="" placeholder="输入标号" onkeyup="findGrade(this)"></td>
						<td width="79">旧标号</td>
						<td class="width_01"><input type="text" name="formergrade" id="old_nums" value="" placeholder="输入旧标号"  onkeyup="this.value=this.value.replace(/[^0-9a-zA-Z]/g,'');"></td>
					</tr>
					
					<tr class="font_color">
						<td colspan="2" class="OldNums"></td>
					</tr>
					<tr>
						<td width="79">建表人</td>
						<td class="width_01">
							<select name="riskman" id="riskman">
								<option value="" disabled selected hidden>选择建表人</option>
								<option value="风控1">风控1</option>
								<option value="风控2">风控2</option>
								<option value="风控3">风控3</option>
							</select>
						</td>
						<td width="79">期数</td>
								<td class="width_01"><input type="text" name="period" value="${finance.period}" id="period" ;margin-right:10px;" maxlength="4"  placeholder="输入期数" ></td>
					</tr>
					<tr class="font_color">
						<td colspan="2" class="Riskman"></td>
					</tr>
				</table>
				<table id="myTable" class="control_tab controls_table" style="text-align:center;width:865px;"border="1">
					<tr>
						<td width="143px" class="td_pos" onclick="addText($(this))">
						</td>
						<td colspan="3" style="position:relative">
							<img class="tab_img" src="images/tab_add.png" id="addRow" >
							<img src="images/tab_det.png" id="deleteRow"  onclick="delRow('del1');">
							<input type="text" name="" value="" class="amount"  onkeyup="clearNoNum(this)" onblur="clearNoNum(this)" maxlength="12" style="border:none;float:left">
						</td>
					</tr>
					<tr>
						<td colspan="2" width="400px"></td>
						<td width="105px" id="sumAdd">合计</td>
						<td width="110px" id="td_Amount"><input type="text" name="sum" value="${finance.sum}" readonly style="border:none;"></td>
					</tr>
				</table>
				<p class="control_tab">
					<input type="button" value="保存" onclick="add()"  class="bgbtn" id="sub_form"/>
					<input type="button" onclick="add1()"  value="提交" style="background:#f5ba31" class="bgbtn"/>
					<input type="reset" onclick="javascript:history.back(-1);" value="取消" style="background:#f45b63" class="bgbtn"/>
				</p>
			</form>
			<iframe id="id_iframe" name="nm_iframe" style="display:none" src="about:blank"></iframe>
		</div>
	</div>
	<div class="show_next">
		<div class="close" style="height:40px;padding-top:12px;">
			<span><a href="#" id="close">关闭</a></span>
		</div>
	</div>
<jsp:include page="footer.jsp"></jsp:include>
	</body>
	<!-- 验证表单 -->
	<script type="text/javascript">
		$(function(){
			$("#times").next().children(".textbox-text").attr("placeholder","请输入时间");
		});
	</script>
	<!-- 表格内下拉框 -->
	<script type="text/javascript">
		var ahtml;
		var ahtmls;
		function addText(a){
			if(a.html().length==534){
				if(a.children(":first-child").children(":first-child")[0].className=="frame-style"){
					return true;
				}else{
					ahtml=a.html();
				}
			}else{
				ahtml=a.html();
			}
			
			a.html('');
			if(a.html().trim()==''){
				a.append('<div class="div_dis"><input type="text" class="frame-style" readonly="readonly" value=" " disabled/><img src="images/bg_arr.png" class="bg_arri" /></div><ul class="menue-list"><li id="interest">平台服务费+利息（风）</li></ul>');
			}else{
				return false;
			}
			  document.onclick =function(e){
				    var target = e.target;
				    console.log(a)
				    ahtmls=a.html();
				    var tc = target.className;
				    var tcc=a.children(":first-child").children(":first-child")[0].className;
				    if(tc!=tcc){
				    	if(tc.indexOf(a.context.className) == -1){
					    	a.children(":first-child").hide();
					    	a.children(":last-child").hide();
					    	console.log(ahtml)
					    	if(ahtml==ahtmls){
					    		a.html(ahtmls);
					    	}else{
					    		a.html(ahtml);
					    	}
					    	
					    }
				    }else{
				    	ahtml=ahtml;
				    }
				}
				/* document.onclick = function(e){
					var target = e.target;
				    var tc = target.className;
				    if(tc.indexOf('frame-style') == -1){
				    	var qqq=$(".menue-list").css('display');
				    	if(qqq=="block"){
				    	$(".menue-list").css("display","none");
				    	$(".frame-style").css("display","none");
				    	}
				    }
				} */
			$('.menue-list li').click(function(){
				var Current=$(this).text();
				var tdInterHTML = $(this).parent().parent();
				tdInterHTML.text(Current);
				if(tdInterHTML.text() == "平台服务费+利息（风）"){
					tdInterHTML.next().children(".amount").attr('name','interest');
				}
				$(this).parent().css("display","none");
				$(this).parent().prev().css("display","none");
				return false;
			})
		}
		/* $('.div_dis').click(function(){
			var menu=$(this).next();
			menu.css("display","block");
			return false;
		}) */
	</script>

	<!-- 点击删除添加 -->
	<script type="text/javascript">
		window.onload = function(){
			var btn1 = document.getElementById("addRow");
			btn1.onclick = function(){
				var myTable = document.getElementById("myTable");
				var rowIndex = myTable.rows.length - 1;
				var newRow = myTable.insertRow(rowIndex);
				newRow.id = "row" + rowIndex;
				newRow.className = "pos";
				newRow.innerHTML='<td class="td_pos" width="116px" onclick="addText($(this))"></td><td colspan="3" style="position:relative"><input type="text" maxlength="12" class="amount" name="" value=""  onkeyup="clearNoNum(this)" onblur="clearNoNum(this)" style="border:none;float:left"><img src="images/tab_det.png" id="deleteRow" onclick=\"delRow(row'+ rowIndex +');\"></td>'
			}
		}
		function delRow(idName){
			option = {
					title: "信息",
					btn: parseInt("0011",2),
					icon: "0 -96px",
					onOk: function(){
						$(idName).remove();
						sum();
					}
			}
			window.wxc.xcConfirm('确定要删除吗？', "custom", option);
		}
	</script>
	<script type="text/javascript">
		$(function(){
			navSelected(0);	
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
	<!-- 保存 -->
	<script>
	
		function add(){
			var area = $('#area').val();
			var names = $("#names").val();
			var money = $("#money").val();
			var days = $("#days").val();
			var businessType = $('#businessType').val();
			var salesman = $("#salesman").val();
			var nums = $("#grade").val();
			
			var riskman = $("#riskman").val();
			
			if(area == null){
				$(".Area").text("请选择分公司");
				return false;
			}else{
				$(".Area").text(" ");
			}
			if(names == ""){
				$(".Names").text("请输入姓名");
				return false;
			}else{
				$(".Names").text(" ");
			}
			if(money == ""){
				$(".Money").text("请输入金额");
				return false;
			}else{
				$(".Money").text(" ");
			}
			if(days == ""){
				$(".Days").text("请输入期限");
				return false;
			}else{
				$(".Days").text(" ");
			}
			if(businessType == null){
				$(".BusinessType").text("请选择业务类型");
				return false;
			}else{
				$(".BusinessType").text(" ");
			}
			if(salesman == null){
				$(".Salesman").text("请选择客户经理");
				return false;
			}else{
				$(".Salesman").text(" ");
			}
			if(nums == ""){
				$(".Nums").text("请输入标号");
				return false;
			}else{
				$(".Nums").text("");
			}
			if(riskman == null){
				$(".Riskman").text("请选择建表人");
				return false;
			}else{
				$(".Riskman").text(" ");
			}
			var a=0;
			$("#types").attr("value",a);	
			$("#audit_id").attr("value",3);
			$("#save").target = "nm_iframe";
			/* 状态(未审核0  已审核1 ,已作废3，已打回/审核未通过2 */
			$("#type").attr("value",a)
			var formObject = {};
			var formArray =$("#save").serializeArray();
			$.each(formArray,function(i,item){
				formObject[item.name] = item.value;
			});
			var formJson = JSON.stringify(formObject);
		
				$.ajax({		
						//几个参数需要注意一下
		                type: "POST",//方法类型
		                dataType: "json",//预期服务器返回的数据类型
		                url: "interestAddfk.action",
		                data: formJson,
		                contentType : 'application/json',
		                success: function (result) {
		                /* 	alert(result.code) */
		                    if (result.code == 0) {
		                    	
		                    	//保存表单后，输入框内容为只读，不可修改								
								window.location.href="toShowAll.action";								
		                   		
		                   		
		                    }else{
		                    	console.log(result.msg);
		                    }                   
		                },
		                error : function(result) {
		                //	console.log("异常！！"+result.msg);
		                	window.wxc.xcConfirm("抱歉！出了一点小问题", window.wxc.xcConfirm.typeEnum.error);
		                }		           
			 });			
	}

	/* 提交 */
	function add1(){
		var area = $('#area').val();
		var names = $("#names").val();
		var money = $("#money").val();
		var days = $("#days").val();
		var businessType = $('#businessType').val();
		var salesman = $("#salesman").val();
		var nums = $("#grade").val();
		
		var riskman = $("#riskman").val();
		
		if(area == null){
			$(".Area").text("请选择分公司");
			return false;
		}else{
			$(".Area").text(" ");
		}
		if(names == ""){
			$(".Names").text("请输入姓名");
			return false;
		}else{
			$(".Names").text(" ");
		}
		if(money == ""){
			$(".Money").text("请输入金额");
			return false;
		}else{
			$(".Money").text(" ");
		}
		if(days == ""){
			$(".Days").text("请输入期限");
			return false;
		}else{
			$(".Days").text(" ");
		}
		if(businessType == null){
			$(".BusinessType").text("请选择业务类型");
			return false;
		}else{
			$(".BusinessType").text(" ");
		}
		if(salesman == null){
			$(".Salesman").text("请选择客户经理");
			return false;
		}else{
			$(".Salesman").text(" ");
		}
		if(nums == ""){
			$(".Nums").text("请输入标号(不可重复)");
			return false;
		}else{
	         $(".Nums").html("");
		}
		if(riskman == null){
			$(".Riskman").text("请选择建表人");
			return false;
		}else{
			$(".Riskman").text(" ");
		}
		var a=0;
		var b=1;
		$("#types").attr("value",1)
		
		$("#audit_id").attr("value",3)
		$("#save").target = "nm_iframe";
		var formObject = {};

		var formArray =$("#save").serializeArray();

		$.each(formArray,function(i,item){

			formObject[item.name] = item.value;

		});
		var formJson = JSON.stringify(formObject);
			$.ajax({		
					//几个参数需要注意一下
	                type: "POST",//方法类型
	                dataType: "json",//预期服务器返回的数据类型
	                url: "interestAddfk.action",
	                data: formJson,
	                contentType : 'application/json',
	                success: function (result) {
	                
	                    if (result.code == 0) {
	                    
	                   		//提交表单后，输入框内容为不可用
	            			$("#save input").attr("disabled","true");
	                    	//提交成功后，提交到财务可查看审核页面
	                    	window.location.href="toShowAll.action";
	                   		
	                    }else{
	                    	window.wxc.xcConfirm("抱歉！出了一点小问题", window.wxc.xcConfirm.typeEnum.error);
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
</html>