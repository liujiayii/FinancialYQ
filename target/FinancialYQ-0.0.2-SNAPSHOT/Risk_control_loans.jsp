<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<title>财务审核</title>

<jsp:include page="nav.jsp"></jsp:include>
			<div class="content">
				<div class="control">
					<form action="###" method="post">
						<table class="control_tab">
							<tr>
								<td class="tab_01">分公司</td>
								<td class="width_01"><input type="text" name="money" value=""></td>
								<td width="79">姓名</td>
								<td class="width_01"><input type="text" name="money" value=""></td>
								<td width="84">金额</td>
								<td class="width_01"><input type="text" name="money" value=""></td>
								<td width="140">月数/垫资天数</td>
								<td class="width_01"><input type="text" name="day" value=""></td>
							</tr>
							<tr>
								<td class="tab_01">业务类型</td>
								<td class="width_01"><input type="text" name="money" value=""></td>
								<td width="79">建表人</td>
								<td class="width_01"><input type="text" name="money" value=""></td>
								<td width="84">时间</td>
								<td colspan="2" class="input_hove"><input class="easyui-datetimebox" style="width:250px;height:40px;z-index:10"editable="false" type="text" value=""></td>
							</tr>
						</table>
						<table class="control_tab controls_table" style="text-align:center;width:800px;position: relative;"border="1">
							<tr>
								<td width="116px"><img class="tab_img" src="images/tab_add.png"></td>
								<td colspan="3"><img class="tab_imgs" src="images/tab_det.png"></td>
							</tr>
							<tr>
								<td colspan="2"></td>
								<td width="105px">合计</td>
								<td width="170px">精确到分</td>
							</tr>
						</table>
							<p class="control_tab">
								<button class="bgbtn">保存</button>
								<button class="bgbtn" style="background:#f5ba31">提交</button>
								<button class="bgbtn" style="background:#f45b63">打回</button>
							</p>
					</form>
				</div>
			</div>
			<div class="show_next">
				<div class="close" style="height:40px;padding-top:12px;">
					<span><a href="#" id="close">关闭</a></span>
				</div>
				<div class="new_standard">
					<img src="images/new_norm.png" />
					<h2>新标</h2>
				</div>
				<div class="Renewloans">
					<img src="images/refinance.png" />
					<h2>续贷</h2>
				</div>
			</div>
<jsp:include page="footer.jsp"></jsp:include>
	</body>
	<script type="text/javascript">
		$(function(){
			navSelected(0);	
		})
		$("span").click(function(){
			$(".show_next").hide();
		});
	</script>
</html>
