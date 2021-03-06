<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>信息查询-工资表</title>
<jsp:include page="nav.jsp"></jsp:include>
<div class="content">
	<div class="sub-box">
		<div class="sel-list">
			<%-- 	<select class="select_style" id="sid" onchange="getlist(this)">
							<c:forEach items="${branchOfficeList}" var="list">
							<option value="${list.area}">${list.area}</option>
						</c:forEach>
						</select> --%>

			<select class="select_style" id="yearid" onchange="getlist()">
				<option value="2019">2019</option>
				<option value="2020">2020</option>
				<option value="2021">2021</option>
			</select> <select class="select_style" id="monthid" onchange="getlist(this)">
				<option value="0">1</option>
				<option value="1">2</option>
				<option value="2">3</option>
				<option value="3">4</option>
				<option value="4">5</option>
				<option value="5">6</option>
				<option value="6">7</option>
				<option value="7">8</option>
				<option value="8">9</option>
				<option value="9">10</option>
				<option value="10">11</option>
				<option value="11">12</option>
			</select>
			<!-- <select  class="select_style" id="select_type">
							<option value="1">提成表</option>
							<option value="2" selected>工资表</option>
						</select> -->
		</div>
		<div class="sub-right">
			<input type="text" value="" name="name" class="sub-search" placeholder="请输入工号" id="input_name" onkeyup="findSalaryByName()" />
		</div>
	</div>
	<table id="cs_table" class="table_tbs" rules=rows>
	</table>
	<p class="money_statistical control_tab">
		<button style="float: right" id="export" class="bgbtn">导出</button>
	</p>
</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
<script type="text/javascript">
	/*跳转*/
	/*$('#select_type').change(function(){
		var c="${roletype}";
		var types=$('#select_type option:selected').text();
		if(types=='提成表'){
			if(c=='行政'){
				window.location.href="demandWage.action";
			}else{
				window.location.href="demandCommission.action";
			}
			
		}else if(types=='工资表'){
			window.location.href="demandWage.action";
		}
	}); */

	$(function() {
		navSelected(2);
	});
	function findSalaryByName() {

		var myDate = new Date();
		console.log(myDate.getFullYear()); //获取完整的年份(4位,1970-????)
		console.log(myDate.getMonth()); //获取当前月份(0-11,0代表1月)

		var c = $('#sid option:selected').text();
		var d = $('#yearid option:selected').text();
		var e = $('#monthid option:selected').text();

		var name = $("#input_name").val();
		var url = '';
		if (name.trim() == '') {

			url = "showSalary.action?area=" + c + "&year=" + d + "&month=" + e;

		} else {

			url = "showSalaryByName.action?name=" + name + "&year=" + d + "&month=" + e;

		}
		$.getJSON(url, function(res) {
			var data = [];

			for (var i = 0; i < res.length; i++) {
				data[i] = {
					id : res[i].id,
					职位 : res[i].post_name,
					姓名 : res[i].name,
					工号 : res[i].job_number,
					实发工资 : res[i].real_salary,
					操作 : "<a href='toShowSalaryDetail.action?id=" + res[i].id + "&time=" + time + "'>详情</a>"
				};

			}
			console.log(data)
			var cs = new table({
				"tableId" : "cs_table", //必须
				"headers" : [ "序号", "职位", "姓名", "工号", "实发工资", "操作" ], //必须
				"data" : data, //必须
				"displayNum" : 5, //必须  默认 10
				"groupDataNum" : 10
			//可选  默认 10
			});
			if (data.length == 0) {
				$('#cs_table').append('<img class="nodata" src="images/nodata.png">');
			}
		});
	}
	$(function() {
		var myDate = new Date();
		console.log(myDate.getFullYear()); //获取完整的年份(4位,1970-????)
		console.log(myDate.getMonth()); //获取当前月份(0-11,0代表1月)
		$('#yearid').val(myDate.getFullYear());
		$('#monthid').val(myDate.getMonth());
		var c = $('#sid option:selected').text();
		var d = myDate.getFullYear();
		var e = myDate.getMonth() + 1;

		var time;
		if (e < 10) {

			time = d + '-0' + e;
		} else {
			time = d + '-' + e;
		}

		var url = "showAttendance.action?year=" + d + "&month=" + e;

		$("#export").click(function() {
			window.location.href = "export.action?year=" + d + "&month=" + e;
		})

		$.getJSON(url, function(res) {
			var data = [];

			for (var i = 0; i < res.length; i++) {
				data[i] = {
					id : res[i].id,
					职位 : res[i].post_name,
					姓名 : res[i].name,
					工号 : res[i].job_number,
					转正后出勤 : res[i].real_attendance,
					未转正出勤 : res[i].noreal_attendance,
					操作 : "<a href='toShowSalaryDetail.action?id=" + res[i].id + "&time=" + time + "'>详情</a>"
				};
			}
			console.log(data)
			var cs = new table({
				"tableId" : "cs_table", //必须
				"headers" : [ "序号", "职位", "姓名", "工号", "转正出勤", "未转正出勤", "操作" ], //必须
				"data" : data, //必须
				"displayNum" : 5, //必须  默认 10
				"groupDataNum" : 10
			//可选  默认 10
			});
			if (data.length == 0) {
				$('#cs_table').append('<img class="nodata" src="images/nodata.png">');
			}
		});
	});

	function getlist() {
		var c = $('#sid option:selected').text();
		var d = $('#yearid option:selected').text();
		var e = $('#monthid option:selected').text();
		if (e < 10) {
			time = d + '-0' + e;
		} else {
			time = d + '-' + e;
		}
		var url = "showSalary.action?area=" + c + "&year=" + d + "&month=" + e;

		$("#export").click(function() {
			window.location.href = "export.action?area=" + c + "&year=" + d + "&month=" + e;
		})

		$.getJSON(url, function(res) {
			var data = [];
			for (var i = 0; i < res.length; i++) {
				data[i] = {
					id : res[i].id,
					职位 : res[i].post_name,
					姓名 : res[i].name,
					基本工资 : res[i].job_number,
					实发工资 : res[i].real_salary,
					操作 : "<a href='toShowSalaryDetail.action?id=" + res[i].id + "&time=" + time + "'>详情</a>"
				};

			}
			var cs = new table({
				"tableId" : "cs_table", //必须
				"headers" : [ "序号", "职位", "姓名", "工号", "实发工资", "操作" ], //必须
				"data" : data, //必须
				"displayNum" : 5, //必须  默认 10
				"groupDataNum" : 10
			//可选  默认 10
			});
			if (data.length == 0) {
				$('#cs_table').append('<img class="nodata" src="images/nodata.png">');
			}
		});

	};
</script>
</html>