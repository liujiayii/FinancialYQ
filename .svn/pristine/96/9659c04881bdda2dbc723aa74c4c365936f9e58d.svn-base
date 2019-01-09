<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<title>已审核</title>
<jsp:include page="nav.jsp"></jsp:include>
			<div class="content">
			<div>
			<form action="##" id="check" method="post" enctype="multipart/form-data">
				<input type="hidden" name="id" id="id" value="" > 
			</form>
				
			</div>
				<div class="sub-box">
					<ul class="sub-nav">
						<li><a href="toShowAll.action">全部列表</a></li>
						<li><a href="toShowCheked.action" class="selected">已审核</a></li>
						<li><a href="toShowUnchecked.action">未审核</a></li>
						<li><a href="toShowBeenBack.action">已打回</a></li>
						<li><a href="toShowBeenVoid.action">已作废</a></li>
					</ul>
					
					<div class="sub-right" id="tf">
						<input type="text" value="" name="grade" class="sub-search" placeholder="请输入标号" id="inputGrade" onkeyup="findGrade()"/>
						<input type="button" value="新建审核" class="sub-button"/>
					</div>
				</div>
				<table id="cs_table" class="table_tbs" rules=rows>
				</table>
			</div>
			
			<c:if test="${roletype=='风控'}">
			<div class="show_next" style="margin-left:-630px;">
				<div class="new_standard" onclick="window.location.href='addFinances.action'">
					<img src="images/new_norm.png" />
					<h2>新标</h2>
				</div>
				<div class="Renewloans" onclick="window.location.href='addFinance.action'">
					<img src="images/refinance.png" />
					<h2>续贷</h2>
				</div>
				
				<img src="images/4.jpg" width="40" height="40" style="position: absolute;right: 20px;top: 5px;cursor: pointer;" class="show_img"/>
			</div>
			</c:if>
			<c:if test="${roletype=='财务总监' or roletype=='主管'}">
			<div class="show_next" style="margin-left:-630px;">
				<div class="new_standard" onclick="window.location.href='addFinances.action'">
					<img src="images/new_norm.png" />
					<h2>新标</h2>
				</div>
				<div class="Renewloans" onclick="window.location.href='addFinance.action'">
					<img src="images/refinance.png" />
					<h2>续贷</h2>
				</div>
				<div class="new_standard" onclick="window.location.href='toPrincipalcw.action'">
					<img src="images/new_norm.png"  />
					<h2>本息</h2>
				</div>
				
				<img src="images/4.jpg" width="40" height="40" style="position: absolute;right: 20px;top: 5px;cursor: pointer;" class="show_img"/>
			</div>
			</c:if>
			
			<c:if test="${roletype=='记账' }">
			<div class="show_next" style="margin-left:-315px;">
				<div class="new_standard" onclick="window.location.href='toPrincipalcw.action'">
					<img src="images/new_norm.png"  />
					<h2>本息</h2>
				</div>
				
				<img src="images/4.jpg" width="40" height="40" style="position: absolute;right: 20px;top: 5px;cursor: pointer;" class="show_img"/>
			</div>
		</c:if>
<jsp:include page="footer.jsp"></jsp:include>
	</body>
	<script type="text/javascript">
	/*一级导航栏选中 */
	navSelected(0);
		$(function(){
			$('.sub-box input.sub-button').click(function(){
				$('.show_next').show();
			})
		});
		$(".show_img").click(function(){
			$(".show_next").hide();
		});
		$(function(){
			var c="${roletype}";
			if(c=='出纳'){
				$(".sub-button").attr("type","hidden");
			}
		});
	</script>
	<!-- 按标号搜索 -->
	<script type="text/javascript">
		
		function findGrade(){
			var grade = $("#inputGrade").val();
			if ("${roletype}" == '风控') {
				if (grade.trim() == '') {
					url = "toFindChecked.action";
				} else {
					url = "toFindGradeByChecked.action?grade="+grade;
				}
			}else if("${roletype}" == '记账'||c=='财务总监'||c=='主管'){
				if (grade.trim() == '') {
					url = "toFindCheckedcw.action";
				} else {
					url = "toFindGradeByCheckedcw.action?grade="+grade;
				}
			}else{
				url = "toFindCheckedcw.action";
			}
		$.getJSON(url,function(res){
			var data = [];
		  	for(var i=0;i<res.length;i++){
		  		var state;//续贷新标本金利息
				var showDetail;//显示详情链接
				var print; // 打印链接
				if(res[i].audit_id==0){
		  			state="续贷";
		  			print="<b style='color:#0CA1EF;cursor: pointer;'' onclick=\"window.location.href='printXD.action?id="+res[i].id+"'\">打印</b>&nbsp;&nbsp;&nbsp;<b style='color:red; cursor: pointer;'  onclick='nullify("+res[i].id+")'>作废</b>";
		  			showDetail="<b style='color:#0CA1EF;cursor: pointer;' onclick=\"window.location.href='toShowDetails.action?id="+res[i].id+"'\">查看详情</b>"
		  		 }else if(res[i].audit_id==1){
		  			state="新标";
		  			print="<b style='color:#0CA1EF;cursor: pointer;'' onclick=\"window.location.href='printXB.action?id="+res[i].id+"'\">打印</b>&nbsp;&nbsp;&nbsp;<b style='color:red;cursor: pointer;' onclick='nullify("+res[i].id+")'>作废</b>";
		  			showDetail="<b style='color:#0CA1EF;cursor: pointer;'onclick=\"window.location.href='toShowDetails1.action?id="+res[i].id+"'\">查看详情</b>";
		  		 }else if(res[i].audit_id==2){
		  			state="本金";
		  			if(res[i].state==0){ //res[i].state判断是谁填的表 0代表风控 1代表财务
		  				print="<b style='color:#0CA1EF;cursor: pointer;'' onclick=\"window.location.href='Printfkbj.action?id="+res[i].id+"'\">打印</b>&nbsp;&nbsp;&nbsp;<b style='color:red;cursor: pointer;' onclick='nullify("+res[i].id+")'>作废</b>";
		  				showDetail="<b style='color:#0CA1EF;cursor: pointer;'onclick=\"window.location.href='toShowPrincipalfk.action?id="+res[i].id+"'\">查看详情</b>";
		  			}else if(res[i].state==1){
		  				print="<b style='color:#0CA1EF;cursor: pointer;'' onclick=\"window.location.href='Printcwbj.action?id="+res[i].id+"'\">打印</b>&nbsp;&nbsp;&nbsp;<b style='color:red;cursor: pointer;' onclick='nullify("+res[i].id+")'>作废</b>";
		  				showDetail="<b style='color:#0CA1EF;cursor: pointer;'onclick=\"window.location.href='toShowPrincipalcw.action?id="+res[i].id+"'\">查看详情</b>";
		  			}
		  			
		  		 }else if(res[i].audit_id==3){
		  			state="利息";
		  			if(res[i].state==0){ //res[i].state判断是谁填的表 0代表风控 1代表财务
		  				print="<b style='color:#0CA1EF;cursor: pointer;'' onclick=\"window.location.href='Printfklx.action?id="+res[i].id+"'\">打印</b>&nbsp;&nbsp;&nbsp;<b style='color:red;cursor: pointer;' onclick='nullify("+res[i].id+")'>作废</b>";
		  				showDetail="<b style='color:#0CA1EF;cursor: pointer;'onclick=\"window.location.href='toShowInterestfk.action?id="+res[i].id+"'\">查看详情</b>";
		  			}else if(res[i].state==1){
		  				print="<b style='color:#0CA1EF;cursor: pointer;'' onclick=\"window.location.href='Printcwlx.action?id="+res[i].id+"'\">打印</b>&nbsp;&nbsp;&nbsp;<b style='color:red;cursor: pointer;' onclick='nullify("+res[i].id+")'>作废</b>";
		  				showDetail="<b style='color:#0CA1EF;cursor: pointer;'onclick=\"window.location.href='toShowInterestcw.action?id="+res[i].id+"'\">查看详情</b>";
		  			}
		  		 }
		  		data[i] = {序号:res[i].id,
		  	    		  标号:res[i].grade,
		  	    		  姓名:res[i].name,
		  	    		  地区:res[i].area,
		  	    		  金额:res[i].money,
		  	    		 期限:res[i].day, 
		  	    		提前还款:res[i].is_payment==0?"<span style='color:green'>否 </span>":"<span style='color:red'>是</span>",
		  				利息:res[i].interest,
		  				 类型:state,
		  				逾期:res[i].is_overdue==0?"<span style='color:green'>否 </span>":"<span style='color:red'>是</span>",	   
		  				状态:"<img src='images/audited.png'>",
		  				操作:print,
		  				详情: showDetail
		  				};
			}
		  	
		  	console.log(data)
			var cs = new table({
			    "tableId":"cs_table",  //必须
			    "headers":["序号","标号","姓名","地区","金额","期限","提前还款","利息","类型","逾期","状态","操作","详情"],  //必须
			    "data":data,    //必须
			    "displayNum": 5,  //必须  默认 10
			    "groupDataNum":10 //可选  默认 10
			});
		  	if(data.length==0){
				//$('#cs_table').children("tbody").children(":first").append('<td colspan="7" style="background:#ccc;">暂无数据</td>');
				$('#cs_table').append('<img class="nodata" src="images/nodata.png">');
			}
			});
	}
	</script>
	<script type="text/javascript">
	$(function(){
		var c="${roletype}";
		
		if(c=='风控'||c=='出纳'){
			
			

			var url = "toFindChecked.action";
			$.getJSON(url,function(res){
			var data = [];
			
		  	for(var i=0;i<res.length;i++){
		  		var state;//续贷新标本金利息
				var showDetail;//显示详情链接
				if(res[i].audit_id==0){
		  			state="续贷";
		  			showDetail="<b style='color:#0CA1EF;cursor: pointer;' onclick=\"window.location.href='toShowDetails.action?id="+res[i].id+"'\">查看详情</b>"
		  		 }else if(res[i].audit_id==1){
		  			state="新标";
		  			showDetail="<b style='color:#0CA1EF;cursor: pointer;'onclick=\"window.location.href='toShowDetails1.action?id="+res[i].id+"'\">查看详情</b>";
		  		 }else if(res[i].audit_id==2){
		  			state="本息";
		  			/* if(res[i].state==0){ //res[i].state判断是谁填的表 0代表风控 1代表财务
		  				showDetail="<b style='color:#0CA1EF;cursor: pointer;'onclick=\"window.location.href='toShowPrincipalfk.action?id="+res[i].id+"'\">查看详情</b>";
		  			}else if(res[i].state==1){ */
		  				showDetail="<b style='color:#0CA1EF;cursor: pointer;'onclick=\"window.location.href='toShowPrincipalcw.action?id="+res[i].id+"'\">查看详情</b>";
		  		/* 	} */
		  			
		  		 }/* else if(res[i].audit_id==3){
		  			state="利息";
		  			if(res[i].state==0){ //res[i].state判断是谁填的表 0代表风控 1代表财务
		  				showDetail="<b style='color:#0CA1EF;cursor: pointer;'onclick=\"window.location.href='toShowInterestfk.action?id="+res[i].id+"'\">查看详情</b>";
		  			}else if(res[i].state==1){
		  				showDetail="<b style='color:#0CA1EF;cursor: pointer;'onclick=\"window.location.href='toShowInterestcw.action?id="+res[i].id+"'\">查看详情</b>";
		  			}
		  		 } */
		  		data[i] = {序号:res[i].id,
		  	    		  标号:res[i].grade,
		  	    		  姓名:res[i].name,
		  	    		  地区:res[i].area,
		  	    		  金额:res[i].money,
		  	    		 期限:res[i].day, 		  				
		  	    		提前还款:res[i].is_payment==0?"<span style='color:green'>否 </span>":"<span style='color:red'>是</span>",
		  				利息:res[i].interest,
		  				 类型:state,
		  				逾期:res[i].is_overdue==0?"<span style='color:green'>否 </span>":"<span style='color:red'>是</span>",	   
		  				状态:"<img src='images/audited.png'>",		  				
		  				详情:showDetail
		  				};
			}
		  	
		  	console.log(data)
			var cs = new table({
			    "tableId":"cs_table",  //必须
			    "headers":["序号","标号","姓名","地区","金额","期限","提前还款","利息","类型","逾期","状态","详情"],  //必须
			    "data":data,    //必须
			    "displayNum": 5,  //必须  默认 10
			    "groupDataNum":10 //可选  默认 10
			});
		  	if(data.length==0){
				//$('#cs_table').children("tbody").children(":first").append('<td colspan="7" style="background:#ccc;">暂无数据</td>');
				$('#cs_table').append('<img class="nodata" src="images/nodata.png">');
			}
			});
			
		}else{
		
			var url = "toFindCheckedcw.action";
			$.getJSON(url,function(res){
			var data = [];
		  	for(var i=0;i<res.length;i++){
		  		var state;//续贷新标本金利息
				var showDetail;//显示详情链接
				var print; // 打印链接
				if(res[i].audit_id==0){
		  			state="续贷";
		  			print="<b style='color:#0CA1EF;cursor: pointer;'' onclick=\"window.location.href='printXD.action?id="+res[i].id+"'\">打印</b>&nbsp;&nbsp;&nbsp;<b style='color:red; cursor: pointer;'  onclick='nullify("+res[i].id+")'>作废</b>";
		  			showDetail="<b style='color:#0CA1EF;cursor: pointer;' onclick=\"window.location.href='toShowDetails.action?id="+res[i].id+"'\">查看详情</b>"
		  		 }else if(res[i].audit_id==1){
		  			state="新标";
		  			print="<b style='color:#0CA1EF;cursor: pointer;'' onclick=\"window.location.href='printXB.action?id="+res[i].id+"'\">打印</b>&nbsp;&nbsp;&nbsp;<b style='color:red;cursor: pointer;' onclick='nullify("+res[i].id+")'>作废</b>";
		  			showDetail="<b style='color:#0CA1EF;cursor: pointer;'onclick=\"window.location.href='toShowDetails1.action?id="+res[i].id+"'\">查看详情</b>";
		  		 }else if(res[i].audit_id==2){
		  			state="本息";
		  		/* 	if(res[i].state==0){ */ //res[i].state判断是谁填的表 0代表风控 1代表财务
		  				print="<b style='color:#0CA1EF;cursor: pointer;'' onclick=\"window.location.href='Printfkbj.action?id="+res[i].id+"'\">打印</b>&nbsp;&nbsp;&nbsp;<b style='color:red;cursor: pointer;' onclick='nullify("+res[i].id+")'>作废</b>";
		  				showDetail="<b style='color:#0CA1EF;cursor: pointer;'onclick=\"window.location.href='toShowPrincipalfk.action?id="+res[i].id+"'\">查看详情</b>";
		  		/* 	} *//* else if(res[i].state==1){
		  				print="<b style='color:#0CA1EF;cursor: pointer;'' onclick=\"window.location.href='Printcwbj.action?id="+res[i].id+"'\">打印</b>&nbsp;&nbsp;&nbsp;<b style='color:red;cursor: pointer;' onclick='nullify("+res[i].id+")'>作废</b>";
		  				showDetail="<b style='color:#0CA1EF;cursor: pointer;'onclick=\"window.location.href='toShowPrincipalcw.action?id="+res[i].id+"'\">查看详情</b>";
		  			}
		  			 */
		  		 }/* else if(res[i].audit_id==3){
		  			state="利息";
		  			if(res[i].state==0){ //res[i].state判断是谁填的表 0代表风控 1代表财务
		  				print="<b style='color:#0CA1EF;cursor: pointer;'' onclick=\"window.location.href='Printfklx.action?id="+res[i].id+"'\">打印</b>&nbsp;&nbsp;&nbsp;<b style='color:red;cursor: pointer;' onclick='nullify("+res[i].id+")'>作废</b>";
		  				showDetail="<b style='color:#0CA1EF;cursor: pointer;'onclick=\"window.location.href='toShowInterestfk.action?id="+res[i].id+"'\">查看详情</b>";
		  			}else if(res[i].state==1){
		  				print="<b style='color:#0CA1EF;cursor: pointer;'' onclick=\"window.location.href='Printcwlx.action?id="+res[i].id+"'\">打印</b>&nbsp;&nbsp;&nbsp;<b style='color:red;cursor: pointer;' onclick='nullify("+res[i].id+")'>作废</b>";
		  				showDetail="<b style='color:#0CA1EF;cursor: pointer;'onclick=\"window.location.href='toShowInterestcw.action?id="+res[i].id+"'\">查看详情</b>";
		  			}
		  		 } */
		  		data[i] = {序号:res[i].id,
		  	    		  标号:res[i].grade,
		  	    		  姓名:res[i].name,
		  	    		  地区:res[i].area,
		  	    		  金额:res[i].money,
		  	    		 期限:res[i].day, 
		  	    		提前还款:res[i].is_payment==0?"<span style='color:green'>否 </span>":"<span style='color:red'>是</span>",
		  				利息:res[i].interest,
		  				类型:state,
		  				逾期:res[i].is_overdue==0?"<span style='color:green'>否 </span>":"<span style='color:red'>是</span>",	   
		  				状态:"<img src='images/audited.png'>",
		  				操作:print,
		  				详情:showDetail };
			}
		  	
		  	console.log(data)
			var cs = new table({
			    "tableId":"cs_table",  //必须
			    "headers":["序号","标号","姓名","地区","金额","期限","提前还款","利息","类型","逾期","状态","操作","详情"],  //必须
			    "data":data,    //必须
			    "displayNum": 5,  //必须  默认 10
			    "groupDataNum":10 //可选  默认 10
			});
		  	if(data.length==0){
				//$('#cs_table').children("tbody").children(":first").append('<td colspan="7" style="background:#ccc;">暂无数据</td>');
				$('#cs_table').append('<img class="nodata" src="images/nodata.png">');
			}
			});
		}
	});
	
	</script>
	<script type="text/javascript">
	/* $(document).ready(function(){
		
	}); */
	
	//作废

	function nullify(y){
		var option = {
				title: "信息",
				btn: parseInt("0011",2),
				icon: "0 -96px",
				onOk: function(){
					$("#id").val(y);
					
					/* 状态(未审核0  已审核1 ,已作废3，已打回/审核未通过2 */
					var formObject = {};

					var formArray = $("#check").serializeArray();

					$.each(formArray,function(i,item){

						formObject[item.name] = item.value;

					});
					var formJson = JSON.stringify(formObject);
				
						$.ajax({		
								//几个参数需要注意一下
				                type: "POST",//方法类型
				                dataType: "json",//预期服务器返回的数据类型
				                url: "toNullify.action",
				                data:formJson,
				                contentType : 'application/json',
				                success: function (result) {
				                	console.log(result.code)
				                    if (result.code == 0) {
				                    	//提交成功后，提交到财务可查看审核页面
				                    	window.location.href="toShowCheked.action";
				             
				                   		
				                    }else{
				                    	window.wxc.xcConfirm("抱歉！出了一点小问题", window.wxc.xcConfirm.typeEnum.error);
				                    }                   
				                },
				                error : function(result) {
				                	window.wxc.xcConfirm("抱歉！出了一点小问题", window.wxc.xcConfirm.typeEnum.error);
				                }          
					 });
				}
			}
		window.wxc.xcConfirm('确定要作废吗？', "custom", option);	
	}
	</script>
</html>
