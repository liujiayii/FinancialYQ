<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
	<head>
		<meta charset="utf-8" />
		<title>打印服务费</title>
		<link rel="stylesheet" href="css/style.css" type="text/css"/>
		<link rel="stylesheet" href="css/printing.css" type="text/css"/>
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
						交款单位：<b></b>
					</div>
					<div class="right receipt-date">
					<jsp:useBean id="now" class="java.util.Date" scope="page"/>
					<fmt:formatDate value="${now}" pattern="yyyy年MM月dd日" />
					</div>
				</div>
				<table class="reimbursement-table" cellpadding="0" cellspacing="0" width="890px;" border="1">
					<tr>
						<td width="140px"><b>收入项目</b></td><td><b>摘要</b></td><td width="200px"><b>金额</b></td><td><b>时间</b></td>
					</tr>
					<c:forEach items="${servicemoney }" var="service">
					<tr>
						<td>${service.incomeAccount}</td><td>${service.remark}</td><td>${service.money}</td><td>${strDate}</td>
					</tr>
					</c:forEach>
				
					<tr>
						<td>
							<div class="total left">
								<div class="alignment">合计</div>
								<div class="alignment1">人民币</div>
							</div>
							<div class="vertical left">︵大写︶</div>
						</td>
						<td colspan="2">
							<b class="ls4">${s}</b>
						</td>
						<td style="font-weight: bold;" id="td_Amount">
							￥<u id="td_Amount">${moneys} </u>
							<%-- <fmt:formatNumber type="number" value="${member.loginBonusAmount } " maxFractionDigits="2"/> --%>
						</td>
					</tr>
				</table>
				<span class="vertical">单位负责人</span>
				<div class="right">
					<span class="vertical">财务主管</span>
					<span class="vertical">记&nbsp;&nbsp;&nbsp;&nbsp;账</span>
					<span class="vertical">出&nbsp;&nbsp;&nbsp;&nbsp;纳</span>
					<span class="vertical">审&nbsp;&nbsp;&nbsp;&nbsp;核</span>
					<span class="vertical">经&nbsp;&nbsp;&nbsp;&nbsp;办</span>
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
	<script type="text/javascript">
	$(function(){
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
		})
	</script>
</html>
