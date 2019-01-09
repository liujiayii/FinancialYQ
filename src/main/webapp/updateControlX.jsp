<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<title>新标风控修改</title>
<style type="text/css">
	#times:hover{border: 1.5px solid #d3c6ff;}
	.control_tab tr {height: 90px !important;line-height: 90px !important;}
</style>
<jsp:include page="nav.jsp"></jsp:include>
			<div class="content">
				<div class="control">
					<form action="##" id="update" target="nm_iframe" method="post" enctype="multipart/form-data">
					<input type="hidden" name="audit_id" id="audit_id" value="${finance.audit_id}">
					<input type="hidden" name="id" id="id" value="${finance.id}" >
					<input type="hidden" name="types" id="types" >
					<input type="hidden" name="type" id="type"  >
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
									<input type="text" name="name" value="${finance.name}">
								</td>
								<td width="84">金额</td>
								<td class="width_01" style="text-align:left"><input type="text" name="money" value="${finance.money}" style="width:102px"></td>
								<td width="79">期限</td>
								<td class="width_01"><input type="text" name="day" value="${finance.day}" style="width: 73px; margin-right: 10px;" onkeyup="this.value=this.value.replace(/[^\d]/g,'');" maxlength="1">个月</td>
							</tr>
							<tr>
								<td class="tab_01">业务类型</td>
								
								<td class="width_01">
									<input type="hidden" id="selectBusinessType"  value="${finance.businessType}"/>
									<select name="businessType" id="businessType" class="form-select">
										<option value="1">惠车贷</option>
										<option value="0">惠房贷</option>
									</select>
								</td>
								<td width="79">客户经理</td>
								<td class="width_01">
								<input type="hidden" name="s_id" value="${finance.s_id}" id="selectMan"/>
								 <select id ="salesman" name="s_id" class="width_01">
								 </select>								
								</td>
								<td width="79">标号</td>
								<td class="width_01"><input type="text" name="grade" value="${finance.grade}" maxlength="7"></td>
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
							<%-- <tr>
								<td width="79">期数</td>
								<td class="width_01"><input type="text" name="period" value="${finance.period}" maxlength="6"></td>
							</tr> --%>
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
						<td width="116px" class="td_pos" >
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
							<!-- <img src="images/tab_det.png" id="deleteRow"  onclick="delRow('del1');"> -->
							
							<input type="text" name="interest" class="amount" value="${finance.interest}" onkeyup="clearNoNum(this)" onblur="clearNoNum(this)" maxlength="12" style="border:none;float:left">
						</td>
					</tr>
				</c:if>
				<c:if test="${not empty finance.serviceMoney}">
					<tr>
						<td width="116px" class="td_pos" >
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
							
						<!-- 	<img src="images/tab_det.png" id="deleteRow"  onclick="delRow('del1');"> -->
							
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
							
						<!-- 	<img src="images/tab_det.png" id="deleteRow"  onclick="delRow('del1');"> -->
							
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
							
						<!-- 	<img src="images/tab_det.png" id="deleteRow"  onclick="delRow('del1');"> -->
							
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
							他项费</td>
						<td colspan="3" style="position: relative">
							<!-- <img src="images/tab_det.png" id="deleteRow"  onclick="delRow('del1');"> -->

							<input type="text" name="elseMoney" class="amount"
							value="${finance.elseMoney}" onkeyup="clearNoNum(this)"
							onblur="clearNoNum(this)" maxlength="12"
							style="border: none; float: left">
						</td>
					</tr>
				</c:if>
				<tr>
								<td colspan="2" width="400px"></td>
								<td width="105px" id="sumAdd">合计</td>
								<td width="110px" id="td_Amount"><input type="text" name="sum" value="${finance.sum}" readonly style="border:none"></td>
								<!-- <td class="width_01"><input type="hidden" name="types"  id="types" value=""></td> -->
							</tr>
						</table>
						<p class="control_tab">
							<input class="bgbtn" onclick="update()" type="button" value="保存">
							<input class="bgbtn" onclick="add1()()" type="button" value="提交" style="background:#f5ba31">
							<input class="bgbtn" onclick="cancel()" type="button" value="取消" style="background:#f45b63">
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
	<script type="text/javascript">
		function getTime(t) {
			var _time = new Date(t);
			var year = _time.getFullYear(); //2017
			var month = _time.getMonth() + 1; //7
			var date = _time.getDate();//10
			var hour = _time.getHours()<10?"0"+_time.getHours():_time.getHours(); 
		//10
			var minute = _time.getMinutes()<10?"0"+_time.getMinutes():_time.getMinutes();//56
			var second = _time.getSeconds()<10?"0"+_time.getSeconds():_time.getSeconds(); //15		
			return year + "-" + month + "-" + date + "- " + hour + ":" + minute
					+ ":" + second;//这里自己按自己需要的格式拼接
		}
	</script>
	<!-- 表格内下拉框 -->
	<script type="text/javascript">
		function addText(a){
			a.html('');
			if(a.html().trim()==''){
				a.append('<div class="div_dis"><input type="text" class="frame-style" readonly="readonly" value=" " disabled/><img src="images/bg_arr.png" class="bg_arri" /></div><ul class="menue-list"><li id="principal">还本金</li><li id="interest">平台服务费+利息</li><li id="serviceMoney">点位费</li><li id="houseProperty">房产</li><li id="archivesMoney">档案管理</li><li id="capitalMoney">垫资费</li><li id="">过账费</li><li id="">实地费</li><li id="GPSMoney">GPS安装</li><li id="stopMoney">停车费</li><li id="jindiMoney">进抵费</li><li id="fileMoney">查档费</li><li id="elseMoney">他项费</li></ul>');
			}else{
				return false;
			}
			$('.menue-list li').click(function(){
				var Current=$(this).text();
				var tdInterHTML = $(this).parent().parent();
				tdInterHTML.text(Current);
				if(tdInterHTML.text() == "还本金"){					
					tdInterHTML.next().children(".amount").attr('name','principal');
				}else if(tdInterHTML.text() == "平台服务费+利息"){
					tdInterHTML.next().children(".amount").attr('name','interest');
				}else if(tdInterHTML.text() == "点位费"){
					tdInterHTML.next().children(".amount").attr('name','serviceMoney');
				}else if(tdInterHTML.text() == "房产"){
					tdInterHTML.next().children(".amount").attr('name','houseProperty');
				}else if(tdInterHTML.text() == "档案管理"){
					tdInterHTML.next().children(".amount").attr('name','archivesMoney');
				}else if(tdInterHTML.text() == "垫资费"){
					tdInterHTML.next().children(".amount").attr('name','capitalMoney');
				}else if(tdInterHTML.text() == "过账费"){
					tdInterHTML.next().children(".amount").attr('name','account');
				}else if(tdInterHTML.text() == "实地费"){
					tdInterHTML.next().children(".amount").attr('name','landMoney');
				}else if(tdInterHTML.text() == "GPS安装"){
					tdInterHTML.next().children(".amount").attr('name','gpsMoney');
				}else if(tdInterHTML.text() == "停车费"){
					tdInterHTML.next().children(".amount").attr('name','stopMoney');
				}else if(tdInterHTML.text() == "进抵费"){
					tdInterHTML.next().children(".amount").attr('name','jindiMoney');
				}else if(tdInterHTML.text() == "查档费"){
					tdInterHTML.next().children(".amount").attr('name','fileMoney');
				}else if(tdInterHTML.text() == "他项费"){
					tdInterHTML.next().children(".amount").attr('name','elseMoney');
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
	
	
	<script type="text/javascript">
		
		$("span").click(function(){
			$(".show_next").hide();
		});
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
	<!--取消  -->
	<script type="text/javascript">
	function cancel(){
		window.location.href="toShowBeenBack.action";
	}
	</script>
	<!--  保存    提交-->
	
	<script type="text/javascript">
		
		function update(){	
			//点击保存修改为0
			var b=0;
			$("#types").attr("value",b)
			var formObject = {};	
			var formArray = $("#update").serializeArray();	
			$.each(formArray,function(i,item){	
				formObject[item.name] = item.value;	
			});
			var formJson = JSON.stringify(formObject);
				$.ajax({		
						//几个参数需要注意一下
		                type: "POST",//方法类型
		                dataType: "json",//预期服务器返回的数据类型
		                url: "toUpdateFinance.action",
		                data: formJson,
		                contentType : 'application/json',
		                success: function (result) {
		                	
		                    if (result.code == 0) {
		                   		console.log("修改成功！！");
		                    	window.location.href="toShowUnchecked.action";
								//保存表单后，输入框内容为只读，不可修改
								$("#update input").attr("readOnly","true");
	
		                    }else{
		                    	console.log(result.msg);
		                    }                   
		                },
		                error : function(result) {
		                	window.wxc.xcConfirm("抱歉！出了一点小问题", window.wxc.xcConfirm.typeEnum.error);
		                }
			 		});
		}
		/*提交*/
		function add1(){
			
			var b=1;
			$("#types").attr("value",b)
			var formObject = {};		
			var formArray = $("#update").serializeArray();	
			$.each(formArray,function(i,item){	
				formObject[item.name] = item.value;
		
			});
			var formJson = JSON.stringify(formObject);
			
				$.ajax({		
						//几个参数需要注意一下
		                type: "POST",//方法类型
		                dataType: "json",//预期服务器返回的数据类型
		                url: "toUpdateFinance.action",
		                data: formJson,
		                contentType : 'application/json',
		                success: function (result) {
		                
		                    if (result.code == 0) {
		                    	//提交成功后，提交到财务可查看审核页面
		                    	window.location.href="toShowUnchecked.action";
		             
		                    }else{
		                    	window.wxc.xcConfirm("抱歉！出了一点小问题", window.wxc.xcConfirm.typeEnum.error);
		                    }                   
		                },
		                error : function(result) {
		                	window.wxc.xcConfirm("抱歉！出了一点小问题", window.wxc.xcConfirm.typeEnum.error);
		                }
		           
			 });
				//提交表单后，输入框内容为不可用
			//	$("#update input").attr("readOnly","true");
		}
	</script>
	<script type="text/javaScript">
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
	$(function(){
			navSelected(0);	
		});
		function genlist(t){
			$(t).parent().siblings().children("a").removeClass("selected");
			$(t).addClass("selected");
		}
	
	function firstSel() {
		$("#salesman").html("")
		//如果第一个下拉列表的值改变则调用此方法
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