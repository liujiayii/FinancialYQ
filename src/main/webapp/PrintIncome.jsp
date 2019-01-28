<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<meta charset="utf-8" />
		<title>打印收入</title>
		<link rel="stylesheet" href="css/style.css" type="text/css"/>
		<link rel="stylesheet" href="css/printing.css" type="text/css"/>
		
	<script type="text/javascript" src="js/jquery.min.js"></script>
		<script type="text/javascript" src="js/jquery.easyui.min.js" ></script>
	</head>
	
	<body>
		<!--startprint-->
		
		<div class="table-frame">
			<div class="rptype">收：</div>
			<div class="left">
				<div style="width: 100%; height: 37px;">
					<div class="receipt-title" style="margin-right: 325px;">
						<h1>收据</h1>
						<hr width="100%" color="#000000"/>
						<hr width="100%" color="#000000"/>
					</div>
				</div>
				<div class="receipt-nav">
					<div class="left">
						交款单位：<b>${area}</b>
					</div>
					<div class="right receipt-date">
						${strDate}
					</div>
				</div>
				<table class="reimbursement-table" cellpadding="0" cellspacing="0" width="890px;" border="1">
					<tr>
						<td>报销项目</td><td colspan="4">摘要</td><td colspan="3"><b>金额</b></td>
					</tr>
					<tr>
						<td>${income.income_name}</td><td colspan="4">${income.digest}</td><td colspan="3"><b>${income.money}</b></td>
					</tr>
					<tr>
						<td>${income.income_name1}</td><td colspan="4">${income.digest1}</td><td colspan="3"><b>${income.money1}</b></td>
					</tr>
					<tr>
						<td>${income.income_name2}</td><td colspan="4">${income.digest2}</td><td colspan="3"><b>${income.money2}</b></td>
					</tr>
					<tr>
						<td>${income.income_name3}</td><td colspan="4">${income.digest3}</td><td colspan="3"><b>${income.money3}</b></td>
					</tr>
					<tr>
						<td>${income.income_name4}</td><td colspan="4">${income.digest4}</td><td colspan="3"><b>${income.money4}</b></td>
					</tr>
					<tr>
						<td>
							<div class="total left">
								<div class="alignment">合计</div>
								<div class="alignment1">人民币</div>
							</div>
							<div class="vertical left">︵大写︶</div>
						</td>
						<td colspan="4">
							<b class="ls4">${s}</b>
						</td>
						<td colspan="3" style="font-weight: bold;">
							￥<u>${income.sum }</u>
						</td>
					</tr>
				</table>
				<span class="vertical">单位负责人</span>
				<div class="right">
					<span class="vertical">财务主管</span>
					<span class="vertical" id="spanone"></span>
					<span class="vertical">记&nbsp;&nbsp;&nbsp;&nbsp;账</span>
					<span class="vertical" id="spantwo"></span>
					<span class="vertical">出&nbsp;&nbsp;&nbsp;&nbsp;纳</span>
<!-- 					<span class="vertical">审&nbsp;&nbsp;&nbsp;&nbsp;核</span>
 -->					<span class="vertical" id="spanthree"></span>
					<span class="vertical">经&nbsp;&nbsp;&nbsp;&nbsp;办</span>
					<span class="vertical">${income.name}</span>
				</div>
			</div>
			<div class="RightVertical right">
				白联&nbsp;存根&nbsp;&nbsp;&nbsp;红联&nbsp;记账&nbsp;&nbsp;&nbsp;黄联&nbsp;票据
			</div>
		</div>
		<!--endprint-->
		<div class="table-frame" style="height: 50px"><input type="button"  value="打印" onclick="doPrint()" class="printBtn"/></div>
	</body>
	
	<script type="text/javascript">
	 
	$(function(){
		
		var name="${income.auditor}";
		
		var roletype="${income.role}";
		
	 	if(roletype=='记账'){
			
			
			$("#spantwo").html(name);
		}if(roletype=='主管'){
			$("#spanone").html(name);
			
		}if(roletype=='财务总监'){
			$("#spanthree").html(name);
			
		} 
		
	})
		function doPrint() {
	        bdhtml=window.document.body.innerHTML;
	        sprnstr="<!--startprint-->"; //开始打印标识字符串有17个字符
	        eprnstr="<!--endprint-->"; //结束打印标识字符串
	        prnhtml=bdhtml.substr(bdhtml.indexOf(sprnstr)+17); //从开始打印标识之后的内容
	        prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr)); //截取开始标识和结束标识之间的内容
	        window.document.body.innerHTML=prnhtml; //把需要打印的指定内容赋给body.innerHTML
	        window.print(); //调用浏览器的打印功能打印指定区域
	        window.document.body.innerHTML=bdhtml; // 最后还原页面
    	}
		
	</script>
	
	
	
</html>
