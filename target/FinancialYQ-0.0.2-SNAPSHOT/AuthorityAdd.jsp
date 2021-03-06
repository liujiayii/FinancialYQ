<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <title>角色权限管理（添加）</title>
<jsp:include page="nav.jsp"></jsp:include>
			<div style="margin-top: 150px;">
			<form action="##" id="ff">
				<div class="form-layer">
        			<span>姓名</span><input type="text" name="name" class="form-input" id='name' />
        			<p class="explain1"></p>
        		</div>
        		<div class="form-layer">
        			<span>权限</span>
        			<select class="form-select" name="role_type">
        				<!-- 所有权限最高管理者 -->
        				<option value="管理员">管理员</option>	
        				<!-- 所有权限，但是不可以修改风控填写的信息 -->
        				<option value="财务总监">财务总监</option>
        				<!-- 所有权限，但是不可以修改风控填写的信息 -->
        				<option value="主管">主管</option>
        				<!-- 审核 风控人员提交的表单，能打回，能作废，能打印-->
        				<option value="记账">记账</option>
        				<!-- 出纳只能查看收入表，支出表，(需确认信息，确认后记账人员查看)查看之后有个记号 -->
        				<option value="出纳">出纳</option>
        				<!-- 风控只能去填写风控续贷和新标的两个单子，保存后可修改，提交后只可查看，财务打回，可修改重新提交 -->
        				<option value="风控">风控</option>
        				
        				<option value="行政">行政</option>
        			</select>
        		</div>
        		<div class="form-layer">
        			<span>电话</span><input type="text" name="phone" class="form-input" id='phone' onkeyup="findByPhone()"/>
        			<p class="explain2"></p>
        		</div>
        		<div class="form-layer">
        			<span>账号</span><input type="text" name="username" placeholder="请输入账号" class="form-input" id="fin_account"/>
        			<p class="explain3"></p>
        		</div>
        		<div class="form-layer">
        			<span>密码</span><input type="password" name="password" placeholder="请输入6--12位数密码" class="form-input"  id="fin_pwd" />
        			<p class="explain4"></p>
        		</div>
        		
        		<div class="form-layer" style="text-align: center;">
        			<input type="button" class="form-sub" onclick="add()" value="保存" />
        			<input type="button" value="取消"  onclick="cancel();" class="form-return"/>
        		</div>
        		</form>
			</div>
<jsp:include page="footer.jsp"></jsp:include>
	</body>
	
	<script type="text/javascript">
	
	
	 var password= $("#fin_pwd").val();

     var reg=/^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,12}$/;

     if(!reg.test(password)||password.length<6||password.length>12){

           $("#msg_setPwd").html("密码必须为6-12位的数字和字母的组合").show(); 
     }

</script>
	<script type="text/javascript">
	/*一级导航栏选中 */
	navSelected(3);
	function cancel(){
		window.location.href="touserList.action";
	}
	function findByPhone(){
		var phone = $("#phone").val();
		if(phone.trim()==''){
			$(".explain2").html("请输入手机号");
		}else{
			//判断电话是否已存在
			$.ajax({
				type:"post",
				dataType:"json",
				url:"findUserByPhone.action",
				data:"phone="+phone,
				success:function(result){
					if(result.code==0){
						$(".explain2").html("手机号已存在,请重新输入");
						$(".explain2").css("color","red");
					}else{
						$(".explain2").html("");
					}
				},
				error:function(){
					console.log("异常");			
				}
			});
		}
	}
	function add(){
		var name = $("#name").val();
		var phone = $("#phone").val();
		var fin_account=$('#fin_account').val();
		var fin_pwd=$('#fin_pwd').val();
		var reg=/^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\d{8}$/;
		if(name==""){
			$(".explain1").html("请输入姓名");
			return false;
		}else{
			$(".explain1").html('');
		}
		if(phone==""){
			$(".explain2").html("请输入手机号");
			return false;
		}else if(!reg.test(phone)){
			$(".explain2").html("手机号格式不对");
			return false;
		}else{
			//判断电话是否已存在
			$.ajax({
				type:"post",
				dataType:"json",
				url:"findUserByPhone.action",
				data:"phone="+phone,
				success:function(result){
					if(result.code==0){
						$(".explain2").html("手机号已存在,请重新输入");
						$(".explain2").css("color","red");
					}else{
						$(".explain2").html("");
						
						if(fin_account==""){
							$(".explain3").html("请输入账号");
							return false;
						}else if(fin_account.length<2){
							$(".explain3").html("请输入2位以上的账号");
							$(".explain3").css("color","blue");
						}else{
							//账号是否存在
							$.ajax({
								type:"post",
								dataType:"json",
								url:"findByUsername.action",
								data:"username="+fin_account,
								success:function(result){
									if(result.code==0){
										$(".explain3").html("账号已存在,请重新输入");
										$(".explain3").css("color","red");
										return false;
									}
									$(".explain3").html('');
									if(fin_pwd==""){
										$(".explain4").html("请输入密码");
										return false;
									}else if(fin_pwd.length<6 || fin_pwd.length>12){
										$(".explain4").html("请输入6--12位数密码");
										return false;
									}else{
										$(".explain4").html('');
									}
									var formObject = {};

									var formArray =$("#ff").serializeArray();

									$.each(formArray,function(i,item){
										formObject[item.name] = item.value;

										});
									var formJson = JSON.stringify(formObject);
								
									 $.ajax({
								            //几个参数需要注意一下
								                type: "POST",//方法类型
								                dataType: "json",//预期服务器返回的数据类型
								                url: "toAddUser.action" ,
								                data: formJson,
								                contentType : 'application/json',
								                success: function (result) {
								                    console.log(result);//打印服务端返回的数据(调试用)
								                    if (result.code == 0) {
								                    	window.location.href="touserList.action";
								                    }
								                    
								                },
								                error : function(result) {
								                   		 console.log(result.msg);
								                	
								                }
								            });
								},
								error:function(result){
									console.log("异常"+result.msg);
								}
							});
							
						}
						
					}
				},
				error:function(result){
					console.log("异常"+result.msg);
				}
			});
		}
		
	}
	</script>
	
</html>
