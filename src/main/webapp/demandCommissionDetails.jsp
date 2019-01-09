<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<title>信息查询-提成表-详情</title>
<jsp:include page="nav.jsp"></jsp:include>
			<div class="content">
				<div class="sub-box">
					<div class="sel-list">
						<select class="select_style" id="sid" onchange="getlist(this)">
							<c:forEach items="${branchOfficeList}" var="list">
								<option value="${list.area}">${list.area}</option>
							</c:forEach>
						</select>
						<select class="select_style" id="yearid" onchange="getlistyear(this)">
							<option value="0">2016年</option>
							<option value="1">2017年</option>
							<option value="2">2018年</option>
						</select>
						<select class="select_style" id="monthid" onchange="getlistmonth(this)">
							<option value="0">1月</option>
							<option value="1">2月</option>
							<option value="2">3月</option>
							<option value="3">4月</option>
							<option value="4">5月</option>
							<option value="5">6月</option>
							<option value="6">7月</option>
							<option value="7">8月</option>
							<option value="8">9月</option>
							<option value="9">10月</option>
							<option value="10">11月</option>
							<option value="11">12月</option>
						</select>
						<select  class="select_style" id="select_type">
							<option disabled hidden selected>类型</option>
							<option value="1">提成表</option>
							<option value="2">工资表</option>
						</select>
						<div class='details'>
							<span style='color:black'>></span> 刘大爷
						</div>
					</div>
					<div class="sub-right">
						<input type="button" value="返回" class="sub-button">
					</div>
				</div>
				<table id="cs_table" class="table_tbs" rules=rows>
				</table>
				<p class="money_statistical">合计：总金额为 </p>
			</div>
<jsp:include page="footer.jsp"></jsp:include>
	</body>
	<script type="text/javascript">
	/*跳转*/
	$('#select_type').change(function(){
		var types=$('#select_type option:selected').text();
		if(types=='提成表'){
			window.location.href="demandCommission.action";
		}else if(types=='工资表'){
			window.location.href="demandWage.action";
		}
	});

		$(function(){
			navSelected(2);	
		});
			var sname = $("#find").val();
			var url = "showDeduct.action?name="+sname;
			$(function(){
			$.getJSON(url,function(res){
				var data = [];
				alert(res+"res")
			  	for(var i=0;i<res.length;i++){
				    data[i] = {id:res[i].id,职位:res[i].duty,姓名:res[i].name,电话:res[i].phone,提成:res[i].deduct==null?"无":res[i].deduct,操作:res[i].duty=='业务员'?"<a href='showDetail.action?id="+res[i].id+"&time="+res[i].time+"'>详情</a>":""};
				}
			  	console.log(data)
				var cs = new table({
				    "tableId":"cs_table",  //必须
				    "headers":["标号","月份","金额","提成"],  //必须
				    "data":data,    //必须
				    "displayNum": 5,  //必须  默认 10
				    "groupDataNum":10 //可选  默认 10
				});
			  	if(data.length==0){
					$('#cs_table').append('<img class="nodata" src="images/nodata.png">');
				}
		  	});
			});
	</script>
</html>