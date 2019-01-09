<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<title>风控本金添加</title>
<jsp:include page="nav.jsp"></jsp:include>
			<div class="content">
				<div class="control">
					<form action="##" id="save" method="post" enctype="multipart/form-data">
					<input type="hidden" name="s_id" id="s_id">
					<input type="hidden" name="audit_id" id="audit_id">
					<input type="hidden" name="id" value="${finance.id}" id="id" >
					<input type="hidden" name="types" id="types" value="${finance.types}">
					<input type="hidden" name="type" id="type" value="${finance.type}" >	
						<table class="control_tab">
							<tr>
								<td class="tab_01">分公司</td>
								<td class="width_01">
									<input type="hidden" value="${finance.area}" id="selectArea" name="area">
									<select id ="area" name="area" class="width_01" style="border: 1px solid #dcdcdc;padding-left:10px" onchange="firstSel()">										
										<c:forEach items="${branchOfficeList}" var="list">
											
											<option value="${list.area}">${list.area}</option>									
										</c:forEach>
									</select>
								</td>
								<td width="79">姓名</td>
								<td class="width_01">
									<input type="text" name="name" value="${finance.name}" id="names" placeholder="请输入姓名" maxlength="5">
								</td>
								<td width="84">金额</td>
								<td class="width_01" style="text-align:left"><input type="text" name="money" maxlength="8" id="money" value="${finance.money}" style="width:115
								px" placeholder="请输入金额"></td>
								<td width="79">期限</td>
								<td class="width_01"><input type="text" name="day" value="${finance.day}" id="days" style="width:73px;margin-right:10px;" maxlength="1" onkeyup="this.value=this.value.replace(/[^\d]/g,'');" placeholder="输入期限" >个月</td>
							</tr>
							<tr class="font_color">
								
							</tr>
							<tr>
								<td class="tab_01">业务类型</td>
								<td class="width_01">
									<input type="hidden" name="businessType" value="${finance.businessType}" id="selectBusinessType">
									<select name="businessType" id="businessType">
										
										<option value="1">惠车贷</option>
										<option value="0">惠房贷</option>
									</select>
								</td>
								<td width="79">客户经理</td>
								<td class="width_01">
									<input type="hidden" value="${finance.s_id}" id="selectMan" name="s_id">
									<select id ="salesman" name="s_id" class="width_01" >
										
									</select>							
								</td>
								<td width="79">标号</td>
								<td class="width_01"><input type="text" name="grade" value="${finance.grade}"  placeholder="请输入标号"  maxlength="8" onkeyup="findGrade(this)"  id="grade"></td>
								<td width="79">旧标号</td>
								<td class="width_01"><input type="text" name="formergrade" value="${finance.formergrade}"  placeholder="请输入旧标号"  maxlength="8" id="formergrade"></td>
								
								</tr>
								<tr class="font_color">
									<td colspan="2" ></td>
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
								<td width="79">期数</td>
								<td class="width_01"><input type="text" name="period" value="${finance.period}" id="period" maxlength="4"  placeholder="输入期数" ></td>
							</tr>
							<tr class="font_color">
								
							</tr>
						</table>
						<table id="myTable" class="control_tab controls_table" style="text-align:center;width:865px;"border="1">
							<c:if test="${not empty finance.refinancePrincipal}">
								<tr>
									<td width="116px" class="td_pos">
									续贷还本
									</td>
									<td colspan="3" style="position:relative">
									<input type="text" name="refinancePrincipal" class="amount" value="${finance.refinancePrincipal}" maxlength="12" style="border:none;float:left">
									</td>
								</tr>
							</c:if>
							<c:if test="${not empty finance.maturityPrincipal}">
								<tr>
									<td width="116px" class="td_pos">
									到期还本
									</td>
									<td colspan="3" style="position:relative">
									<input type="text" name="maturityPrincipal" class="amount" value="${finance.maturityPrincipal}" maxlength="12" style="border:none;float:left">
									</td>
								</tr>
							</c:if>
							<c:if test="${not empty finance.overduePrincipal}">
								<tr>
									<td width="116px" class="td_pos">
									逾期还本
									</td>
									<td colspan="3" style="position:relative">
									<input type="text" name="overduePrincipal" class="amount" value="${finance.overduePrincipal}" maxlength="12" style="border:none;float:left">
									</td>
								</tr>
							</c:if>
							<c:if test="${not empty finance.prepaymentPrincipal}">
								<tr>
									<td width="116px" class="td_pos">
									提前还本
									</td>
									<td colspan="3" style="position:relative">
									<input type="text" name="prepaymentPrincipal" class="amount" value="${finance.prepaymentPrincipal}" maxlength="12" style="border:none;float:left">
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
							<input type="button" value="返回" onclick="javascript:history.back(-1);"  class="bgbtn" id="sub_form"/>
							
						</p>
					</form>
					
				</div>
			</div>
			
			
<jsp:include page="footer.jsp"></jsp:include>
	</body>
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
				a.append('<div class="div_dis"><input type="text" class="frame-style" readonly="readonly" value=" " disabled/><img src="images/bg_arr.png" class="bg_arri" /></div><ul class="menue-list"><li id="refinancePrincipal">续贷还本</li><li id="maturityPrincipal">到期还本</li><li id="prepaymentPrincipal">提前还本</li><li id="overduePrincipal">逾期还本</li></ul>');
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
		
			$('.menue-list li').click(function(){
				var Current=$(this).text();
				var tdInterHTML = $(this).parent().parent();
				var InputHTML = $(this).parent().prev().children(":first-child");
				InputHTML.text(Current);
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
				newRow.innerHTML='<td class="td_pos" width="116px" onclick="addText($(this))"></td><td colspan="3" style="position:relative"><input type="text" name=""class="amount" value="" onkeyup="clearNoNum(this)" onblur="clearNoNum(this)" maxlength="12" style="border:none;float:left"><img src="images/tab_det.png" id="deleteRow" onclick=\"delRow(row'+ rowIndex +');\"></td>'
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
	});
	$(function(){
			var a= $("#selectArea").val();
			var b= $("#selectBusinessType").val();
			var c= $("#selectRiskman").val();
			var d = $("#selectMan").val();
		
			$("#area option[value='"+a+"']").attr("selected","selected");	
			$("#businessType option[value='"+b+"']").attr("selected","selected");
			$("#riskman option[value='"+c+"']").attr("selected","selected");
			$("#salesman option[value='"+d+"']").attr("selected","selected");
			$("input").attr("readOnly","true");
			$("select").attr("disabled","true"); 
		});
	
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
		});		
	});
	</script>
</html>
