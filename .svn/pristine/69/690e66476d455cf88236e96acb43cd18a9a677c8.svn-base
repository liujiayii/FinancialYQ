<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>打印续贷</title>
		<link rel="stylesheet" href="css/style.css" type="text/css"/>
		<link rel="stylesheet" href="css/printing.css" type="text/css"/>
		<script type="text/javascript" src="js/jquery.min.js"></script>
		
	</head>
	<body>
	<!--startprint-->
		<div class="table-frame">
			<div class="rptype">收：</div>
				<form action="" id="ff" class="case-sub">
						<input type="hidden" name="number" id="inputidone"> 
				</form>
			<div class="left">
				<table cellpadding="0" cellspacing="0" width="890px;" border="1" class="Printing">

				<caption>${finance.area}分公司 续贷 <span id="bar"></span><div class="table-date"><span>时间: </span><input type="text" value="${strDate}"></div></caption>
					<tr>
						<td width="125px">姓名</td><td><b>${finance.name}</b></td><td>新标号</td><td><b>${finance.grade}</b></td><td>旧标号</td><td><b>${finance.formergrade}</b></td><td>金额</td><td><b>${finance.money/10000}万元</b></td><td>期限</td><td><b>${finance.day}个月</b></td>
					</tr>
					<tr>
						<td>还本金</td>
					<c:choose>
						<c:when test="${empty finance.principal}">
							<td colspan="9">0.00</td>
						</c:when>
						<c:otherwise>
							<td colspan="9">${finance.principal}</td>
						</c:otherwise>
					</c:choose>
				</tr>
					
					<tr>
						<td>平台服务费+利息</td>
						
						<c:choose>
						<c:when test="${empty finance.interest}">
							<td colspan="9">0.00</td>
						</c:when>
						<c:otherwise>
							<td colspan="9">${finance.interest}</td>
						</c:otherwise>
					</c:choose>
					</tr>
					<tr>
						<td>垫资费</td>

					<c:choose>
						<c:when test="${empty finance.capitalMoney}">
							<td colspan="9">0.00</td>
						</c:when>
						<c:otherwise>
							<td colspan="9">${finance.capitalMoney}</td>
						</c:otherwise>
					</c:choose>
				</tr>
					<tr>
						<td>档案费</td>
					<c:choose>
						<c:when test="${empty finance.archivesMoney}">
							<td colspan="9">0.00</td>
						</c:when>
						<c:otherwise>
							<td colspan="9">${finance.archivesMoney}</td>
						</c:otherwise>
					</c:choose>
				
					</tr>
					<tr>
						<td>点位费</td>
					
						<c:choose>
						<c:when test="${empty finance.serviceMoney}">
							<td colspan="9">0.00</td>
						</c:when>
						<c:otherwise>
							<td colspan="9">${finance.serviceMoney}</td>
						</c:otherwise>
					</c:choose>
					</tr>
					<tr>
						<td>实地费</td>
						
						<c:choose>
						<c:when test="${empty finance.landMoney}">
							<td colspan="9">0.00</td>
						</c:when>
						<c:otherwise>
							<td colspan="9">${finance.landMoney}</td>
						</c:otherwise>
					</c:choose>
					</tr>
					<tr>
						<td>他项费</td>
						
						<c:choose>
						<c:when test="${empty finance.elseMoney}">
							<td colspan="9">0.00</td>
						</c:when>
						<c:otherwise>
							<td colspan="9">${finance.elseMoney}</td>
						</c:otherwise>
					</c:choose>
					</tr>
					<tr>
						<td colspan="7"></td><td colspan="1">合计</td><td colspan="2"><b>${finance.sum}</b></td>
					</tr>
				</table>
				<div class="table-footer">
				<span>制表人</span><input  type="text" value="${saleName}"/>
			</div>
			</div>
			<div class="RightVertical right">
				白联&nbsp;财务&nbsp;&nbsp;&nbsp;红联&nbsp;风控&nbsp;&nbsp;&nbsp;黄联&nbsp;风控
			</div>
		</div>
		<div class="table-frame pdt20">
			<div class="rptype">收：</div>
			<div class="left">
				<div style="width: 100%; height: 37px;">
					<div class="number">
						N<u>o</u><span id="one"></span>
					</div>
					<div class="receipt-title">
						<h1>收据</h1>
						<hr width="100%" color="#000000"/>
						<hr width="100%" color="#000000"/>
					</div>
				</div>
				<div class="receipt-nav">
					<div class="left">
						交款单位：<b><!-- 技术部 --></b>
					</div>
					<div class="right receipt-date">
					${strDate}
					</div>
				</div>
				<table class="receipt-table" cellpadding="0" cellspacing="0" width="890px;" border="1">
					<tr>
						<td width="125px">客户姓名</td><td><b>${finance.name}</b></td><td>新标号</td><td><b>${finance.grade}</b></td><td>旧标号</td><td><b>${finance.formergrade}</b></td><td>金额</td><td><b>${finance.money/10000}万元</b></td><td>期限</td><td><b>${finance.day}个月</b></td>
					</tr>
					<tr>
						<td>平台服务费+利息</td>
					<c:choose>
						<c:when test="${empty finance.interest}">
							<td colspan="9" id="text1" onkeyup="jisuan();">0.00</td>
						</c:when>
						<c:otherwise>
							<td colspan="9" id="text1" onkeyup="jisuan();">${finance.interest}</td>
						</c:otherwise>
					</c:choose>
					</tr>
					
					<tr>
						<td>点位费</td>
						<c:choose>
						<c:when test="${ empty finance.serviceMoney}">
							<td colspan="9" id="text2" onkeyup="jisuan();">0.00</td>
						</c:when>
						<c:otherwise>
							<td colspan="9" id="text2" onkeyup="jisuan();">${finance.serviceMoney}</td>
						</c:otherwise>
					</c:choose>
				</tr>
					
					<tr>
						<td>档案管理费</td>
						<c:choose>
						<c:when test="${ empty finance.archivesMoney}">
							<td colspan="9" id="text3" onkeyup="jisuan();">0.00</td>
						</c:when>
						<c:otherwise>
							<td colspan="9" id="text3" onkeyup="jisuan();">${finance.archivesMoney}</td>
						</c:otherwise>
					</c:choose>
					</tr>
					<tr>
						<td>
							<div class="total left">
								<div class="alignment">合计</div>
								<div class="alignment1">人民币</div>
							</div>
							<div class="vertical left">︵大写︶</div>
						</td>
						<td colspan="7">
							<b class="ls4">${two}</b>
						</td>
						<td colspan="2" style="font-weight: bold;">
							￥<u id="text4"></u>
						</td>
					</tr>
				</table>
				<span class="vertical">单位盖章</span>
				<div class="right">
					<span class="vertical">财务主管</span>
					<span class="vertical">记&nbsp;&nbsp;&nbsp;&nbsp;账</span>
					<span class="vertical">出&nbsp;&nbsp;&nbsp;&nbsp;纳</span>
					<span class="vertical">审&nbsp;&nbsp;&nbsp;&nbsp;核</span>
					<span class="vertical">经&nbsp;&nbsp;&nbsp;&nbsp;办</span>
				</div>
			</div>
			<div class="RightVertical">
				白联&nbsp;&nbsp;存根&nbsp;&nbsp;&nbsp;红联&nbsp;&nbsp;记账&nbsp;&nbsp;&nbsp;黄联&nbsp;&nbsp;客户
			</div>
		</div>
		
		
		<!--endprint-->
		<div class="table-frame" style="height: 50px"><input type="button"  value="打印" onclick="doPrint()" class="printBtn"/></div>
	</body>
	<script type="text/javascript">
		<!--动态显示业务类型-->	
		var businessType = "${finance.businessType}"==0?"惠房贷":"惠车贷";
		$("#bar").html(businessType);			
	
		
	</script>
	<script type="text/javascript">
		function doPrint() {	               
	        var formObject = {};
			var formArray = $("#ff").serializeArray();			
			$.each(formArray, function(i, item) {
				formObject[item.name] = item.value;
			});
		
			var formJson = JSON.stringify(formObject);
			console.log(formJson+"666")
			$.ajax({

				//几个参数需要注意一下
				type : "POST",//方法类型
				dataType : "json",//预期服务器返回的数据类型
				url : "togetNumber.action",
				data : formJson,
				contentType : 'application/json',
				success : function(result) {
					console.log(result);//打印服务端返回的数据(调试用)
					if (result.code == 0) {
						 bdhtml=window.document.body.innerHTML;
					        sprnstr="<!--startprint-->"; //开始打印标识字符串有17个字符
					        eprnstr="<!--endprint-->"; //结束打印标识字符串
					        prnhtml=bdhtml.substr(bdhtml.indexOf(sprnstr)+17); //从开始打印标识之后的内容
					        prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr)); //截取开始标识和结束标识之间的内容
					        window.document.body.innerHTML=prnhtml; //把需要打印的指定内容赋给body.innerHTML
					        window.print(); //调用浏览器的打印功能打印指定区域
					        window.document.body.innerHTML=bdhtml; // 最后还原页面
					} else {
						console.log(result.msg);
					}

				},
				error : function() {
					console.log("异常！");
				}
			});
    	}
	</script>
	<!-- 合计 -->
	<script type="text/javascript">
	$(function(){
		var text1=$("#text1").text();
		console.log(text1);
		var text2=$("#text2").text();
		var text3=$("#text3").text();		
		var text4 = parseFloat(text1)+parseFloat(text2)+parseFloat(text3);
		$("#text4").text(text4.toFixed(2));
		
	});
	</script>
	<!-- 编号 -->
	<script>
	$(function(){
	var one=${number}+1;
	/*补0 */
	var one1=${number}+1;
	for(var i=0;i<7-one1.toString().length;i++){
		one='0'+one;
	}
	$("#one").html(one)		
	document.getElementById("inputidone").value = one;	
	});
	</script>
</html>
