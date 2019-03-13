<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<title>查询</title>
<jsp:include page="nav.jsp"></jsp:include>
<div class="content">
	<div class="sub-box">
		<div class="sel-list">
			<h2>${name}</h2>
		</div>
		<!-- <div class="sub-right">
						<input type="text" id="searchstr" name="searchstr" class="sub-search" size="20">  
                        <input type="button" value="页内查找" onclick="javascript:findIt();" class="sub-button">

					</div> -->
	</div>
	<table id="cs_table" class="table_tbs" rules="rows">
	</table>
	<p class="money_statistical">合计：总金额为${money}</p>
</div>

<jsp:include page="footer.jsp"></jsp:include>
</body>
<script type="text/javascript">
	/*一级导航栏选中 */
	navSelected(2);

	$("span").click(function() {
		$(".show_next").hide();
	});
	$(function() {

		var c = "${id}";
		var d = "${time}";
		var url = "getlistRole.action?id=" + c + "&time=" + d;

		$.getJSON(url, function(res) {

			var data = [];

			for (var i = 0; i < res.length; i++) {

				data[i] = {
					idss : res[i].grade,
					金额 : res[i].money,
					月份 : res[i].interest
				};
			}

			console.log(data)
			var cs = new table({
				"tableId" : "cs_table", //必须
				"headers" : [ "标号", "金额", "期限" ], //必须
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
</script>
</html>
