<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<title>财务管理-员工管理-添加</title>
<jsp:include page="nav.jsp"></jsp:include>
			<div class="content">
				<div class="sub-box">
					<ul class="sub-nav">
						<li><a href="toStaff.action" class="selected">员工管理</a></li>
						<li><a href="toIncome.action">收入费用</a></li>
						<li><a href="toSpend.action">支出费用</a></li>
						<li><a href="toFinanceManager.action">标的管理</a></li>
						<li><a href="toShowBeenVoid.action">工资核算</a></li>
						<li><a href="toCompany.action">公司管理</a></li>
					</ul>
					<div class="sub-right">
						<input type="button" value="返回" class="sub-but" onclick="javascript:history.back(-1);"/>
					</div>
				</div>
				<div class="control">
					<form action="##" method="post" enctype="multipart/form-data" id="save">
						<table class="control_tab">
							<tr>
								<td width="140px" class="tab_03"><b>工号</b></td>
								<td class="width_04">
								
									<input type="hidden" value="" name="id" id="staff_id"/>
									<!-- this.value=this.value.replace(/[^\d]/g,''); -->
									<input type="text" value="" name="job_number" id="job_number" placeholder="请输入工号"  onkeyup="findJobNum()"></td>
								<td width="140px" class="tab_03"><b>姓名</b></td>
								<td class="width_04"><input type="text" value="" name="name" id="names" placeholder="请输入姓名"></td>
								<td width="140px" class="tab_03"><b>分公司</b></td>
								<td class="width_04">
									<select name="district_id" id="area" >
										<option value="" disabled selected hidden>请输入分公司</option>
										<c:forEach items="${branchOfficeList}" var="list">
											<option value="${list.id}">${list.area}</option>									
										</c:forEach>
									</select>
								</td>
							</tr>
							<tr>
								<td colspan="2" class="Worknum font_color"></td>
								<td colspan="2" class="Names font_color"></td>
								<td colspan="2" class="Area font_color"></td>
							</tr>
							<tr>
								<td width="140px" class="tab_03"><b>部门</b></td>
								<td class="width_04">
									<select name="branch_id" id="branch">
										<option value="" disabled selected hidden>请输入部门</option>
										<c:forEach items="${departmentList}" var="list">
											<option value="${list.id}">${list.dep_name}</option>
										</c:forEach>
									</select>
								</td>
								<td width="140px" class="tab_03"><b>职位</b></td>
								<td class="width_04">
									<select name="post_id" id="position">
										<option value="" disabled selected hidden>请输入职位</option>
										<c:forEach items="${postList}" var="list">
											
											<option value="${list.id}">${list.post_name}</option>
										</c:forEach>
										
									</select>
								</td>
								<td width="140px" class="tab_03"><b>电话</b></td>
								<!--  onkeyup="this.value=this.value.replace(/[^\d]/g,'');"  -->
								<td class="width_04"><input type="text" value="" name="phone" id="tell" placeholder="请输入电话" onkeyup="toFindStaffByPhone(this)" maxlength="11"></td>
							</tr>
							<tr>
								<td colspan="2" class="Branch font_color"></td>
								<td colspan="2" class="Position font_color"></td>
								<td colspan="2" class="Telphone font_color"></td>
							</tr>
							<tr>
								<td width="140px" class="tab_03"><b>实习工资</b></td>
								<td class="width_04"><input type="text" value="" name="base_pay" id="basePay" placeholder="请输入基本工资" maxlength="6" onkeyup="this.value=this.value.replace(/[^\d]/g,'');"></td>
								<td width="140px" class="tab_03"><b>转正工资</b></td>
								<td class="width_04"><input type="text" value="" name="zbase_pay" id="zbasePay" placeholder="请输入转正工资" maxlength="6" onkeyup="this.value=this.value.replace(/[^\d]/g,'');"></td>
								<td width="140px" class="tab_03"><b>全勤</b></td>
								<td class="width_04"><input type="text" value="" name="attendance_bouns" id="attendance_bouns"  placeholder="请输入全勤奖金" maxlength="6" onkeyup="this.value=this.value.replace(/[^\d]/g,'');"></td>
								<td width="140px" class="tab_03"><b>餐补</b></td>
								<td class="width_04"><input type="text" value="" name="meal_bouns" id="meal_bouns" placeholder="请输入餐补奖金" maxlength="6" onkeyup="this.value=this.value.replace(/[^\d]/g,'');"></td>
							</tr>
							<tr>
								<td colspan="2" class="Basepay font_color"></td>
								<td colspan="2" class="AttendanceBouns font_color"></td>
								<td colspan="2" class="MealBouns font_color"></td>
							</tr>
							<tr>
								<td width="140px" class="tab_03"><b>工装</b></td>
								<td class="width_04"><input type="text" value="" name="forck_bouns" id="forck_bouns" placeholder="请输入着装奖金" maxlength="8" onkeyup="this.value=this.value.replace(/[^\d]/g,'');"></td>
								<td width="140px" class="tab_03"><b>入职时间</b></td>
								<td style="text-align:left">
									 <input class="easyui-datetimebox"  style=" border-color: 1.5px solid #d3c6ff;width:200px;height:40px;z-index:5" editable="false" type="text" value="" placeholder="输入时间" id="times" name="entry_date">
								</td>
								<td width="140px" class="tab_03"><b>是否转正</b></td>
								<td class="width_04">
									<select name="is_become" id="is_become">
										<option value="0">否</option>
										<option value="1">是</option>
										
									</select>
								</td>
								
								<td width="140px" class="tab_03"><b>所属主管工号</b></td>
								<td class="width_04"><input type="text" value="" name="leadersalesid" id="leadersalesid" placeholder="请输入工号" maxlength="6" onkeyup="this.value=this.value.replace(/[^\d]/g,'');"></td>
							</tr>
							<tr>
								<td colspan="2" class="ForckBouns font_color"></td>
								<td colspan="2" class="Times font_color"></td>
								<td colspan="2"></td>
							</tr>
						</table>
						<p class="control_tab">
							<input type="button" value="保存" class="bgbtn" id="sub_form" onclick="add()">
							<input type="button" value="取消" class="bgbtn" style="background:#f45b63"  onclick="window.location.href='toStaff.action'">
						</p>
					</form>
					
				</div>
			</div>
      <jsp:include page="footer.jsp"></jsp:include>
	</body>
	<script type="text/javascript">
		/*一级导航栏选中 */
		navSelected(4);
		$(function(){
			$("#times").next().children(".textbox-text").attr("placeholder","请输入时间");
			
			var c="${roletype}";
			if(c=='行政'){
				$('.sub-nav li').slice(1,5).css("display","none");
			}

		});
		/* 表单验证 */
	</script>
	<script type="text/javascript">
		var iframe = document.getElementById("rfFrame");
		iframe.onload = function(){
			var bodycontent = iframe.contentDocument.body.innerHTML;
			console.log(bodycontent);
		}
		/** 添加 */
		function add(){	
			var job_number = $("#job_number").val();
			var Names = $("#names").val();
			var Area = $("#area").val();
			var Tell = $("#tell").val();
			var Branch = $("#branch").val();
			var Position = $("#position").val();
			var BasePay = $("#basePay").val();
			var AttendanceBouns = $("#attendance_bouns").val();
			var MealBouns = $("#meal_bouns").val();
			var ForckBouns = $("#forck_bouns").val();
			var Times= $("#times").val();
			var reg=/^1[3|4|5|6|7|8|9][0-9]{9}$/;//手机号验证规则
			if(job_number == ""){
				$(".Worknum").text("请输入工号");
				return false;
			}else{
				$.ajax({
					type:"post",
					url:"toFindByJobNum.action",
					dataType:"json",
					async:false,
					data:"job_number="+job_number,
					success:function(result){
						if(result.code==0){
							$(".Worknum").html("工号已存在,请重新输入");
						}else{
							$(".Worknum").html("");
							if(Names ==""){
								$(".Names").text("请输入姓名");
								return false;
							}else{
								$(".Names").text("");
							}
							if(Area == null){
								$(".Area").text("请选择分公司");
								return false;
							}else{
								$(".Area").text("");
							}
							if(Branch == null){
								$(".Branch").text("请选择部门");
								return false;
							}else{
								$(".Branch").text("");
							}
							if(Position == null){
								$(".Position").text("请选择职位");
								return false;
							}else{
								$(".Position").text("");
							}
							if (Tell == "") {
								$(".Telphone").text("请输入手机号")
								return false;
							}else if(!reg.test(Tell)){
								$(".Telphone").text("手机号格式不正确")
								return false;
							}else{
								$.ajax({
									type:"post",
									url:"findStaffByPhone.action",
									dataType:"json",
									data:"phone="+Tell,
									success:function(result){
										if(result.code==0){
											$(".Telphone").html("手机已存在,请重新输入");
										}else{
											$(".Telphone").html("");
											if(BasePay == ""){
												$(".Basepay").text("请输入基本工资");
												return false;
											}else{
												$(".Basepay").text("");
											}
											if(AttendanceBouns == ""){
												$(".AttendanceBouns").text("请输入全勤奖金");
												return false;
											}else{
												$(".AttendanceBouns").text("");
											}
											if(MealBouns == ""){
												$(".MealBouns").text("请输入餐补");
												return false;
											}else{
												$(".MealBouns").text("");
											}
											if(ForckBouns == ""){
												$(".ForckBouns").text("请输入工装");
												return false;
											}else{
												$(".ForckBouns").text("");
											}
											if(Times == ""){
												$(".Times").text("请输入时间");
												return false;
											}else{
												$(".Times").text("");
											}
											
											var id = $("#staff_id").val();
											var formObject = {};

											var formArray =$("#save").serializeArray();

											$.each(formArray,function(i,item){

												formObject[item.name] = item.value;

											});
											var formJson = JSON.stringify(formObject);
											$.ajax({
												 	type: "POST",//方法类型
									                dataType: "json",//预期服务器返回的数据类型
									                url: "toAddStaff.action",
									                data: formJson,
									                contentType : 'application/json',
									                success: function (result) {
									                    if (result.code == 0) {
									                    	window.location.href="toStaff.action";
									                   		console.log("提交成功！！");
									                    }else{
									                    	console.log(result.msg);
									                    }                   
									                },
									                error : function(result) {
									                	console.log("异常！！"+result.msg);
									                }
											});
										}
									},
									error:function(result){
										console.log("异常");
									}
								});
							};
							
						}
					},
					error:function(result){
						console.log("异常");
					}
				});
			}
			
			
		}
		 function findJobNum(){
			var job_number = $("#job_number").val();
			
			if(job_number.trim()==''){
				$(".Worknum").html(" ");
			}else{
			$.ajax({
				type:"post",
				url:"toFindByJobNum.action",
				dataType:"json",
				data:"job_number="+job_number,
				success:function(result){
					if(result.code==0){
						$(".Worknum").html("工号已存在,请重新输入");
					}else{
						$(".Worknum").html("");
					}
				},
				error:function(result){
					console.log("异常");
				}
			});
			}
		} 
		function toFindStaffByPhone(aa){
			aa.value=aa.value.replace(/[^\d]/g,'');
			var phone = $("#tell").val();
			
			if(phone.trim()==''){
				$(".Telphone").html(" ");
			}else{
			$.ajax({
				type:"post",
				url:"findStaffByPhone.action",
				dataType:"json",
				data:"phone="+phone,
				success:function(result){
					if(result.code==0){
						$(".Telphone").html("手机已存在,请重新输入");
					}else{
						$(".Telphone").html(" ");
					}
				},
				error:function(result){
					console.log("异常");
				}
			});
			}
		}
	</script>
</html>