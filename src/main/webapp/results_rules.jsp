<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<title>业绩规则</title>
<style type="text/css">
	.sub-nav{
		width: 96%;
	    overflow: hidden;
	    display: inline-block;
	    border-bottom: 1px solid #eee;
	}
	.sub-right{
		margin-top:8px;
	}
</style>
<jsp:include page="nav.jsp"></jsp:include>
		<div class="content">
			<div class="sub-box">
				<ul class="sub-nav">
					<c:forEach items="${list}" var="item">
						<li><a onclick="getlist(this)">${item.area}</a></li>
					</c:forEach>
				</ul>
				<div class="sub-right" id="tf">
					<img src="images/fill.png" class="switch_img" >
				</div>
			</div>
			
			<div class="results_cont">
			<form action="##" id="ff">
				<div class="result_tents">
					<div class="tents_01"><p style="text-align: right;margin-right: 7px;">任务：</p></div>
					<div class="tents pad_left_01">
						<input type="hidden" id="id" name="id" value="">
						<input type="hidden" id="district_id" name="district_id" value="">
						<p style="margin-bottom:15px"><input type="text"  name="task" value="" id="task">（完成--开始按百分比计算提成）</p>
						<p>任务  &nbsp;&nbsp; * <input type="text" name="completionrate" value="" class="rg_03" id="completionrate" >% = &nbsp;&nbsp;&nbsp;--</p>
					</div>
				</div>
				<div class="result_tents">
					<div class="tents_01"><p>门店经理提成：</p></div>
					<div class="tents pad_left_02">
						<p style="margin-bottom:15px">1、当月业绩&nbsp;&nbsp;&nbsp; *<input type="text" name="rule_one" value="" class="rg_03" id="rule_one">‱&nbsp;&nbsp;&nbsp;*&nbsp;&nbsp;&nbsp;（当月业绩&nbsp;&nbsp;/&nbsp;&nbsp;任务）<span>（注：当月业绩大于--按--计算）</span></p>
						<p style="margin-bottom:15px">2、（当月业绩&nbsp; - 任务） * <input type="text" name="rule_two" value="" class="rg_03" id="rule_two">‱&nbsp;&nbsp;&nbsp; = &nbsp;&nbsp;&nbsp;超额提成<span>（注：结果小于0的，按0计算）</span></p>
						<p class="pad_left_03">当月总业绩提成 = 1 + 2</p>
					</div>
				</div>
				<div class="result_tents">
					<div class="tents_01"><p>营业经理提成：</p></div>
					<div class="tents">
						<p class="pad_left_01" ><input type="text" name="salesManageNumber" value="" class="rg_04" id="salesManageNumber">*&nbsp;&nbsp;&nbsp;（当月业绩/任务）<span>（注：结果大于--按--计算）</span></p>
					</div>
				</div>
				<div class="result_tents">
					<div class="tents_01">
						<p style="padding-left: 18px;margin-bottom: 19px;">业务员提成：</p>
						<p style="padding-left:36px">当月提成：</p>
					</div>
					<div class="tents">
						<p class="pad_left_01" style="margin-bottom:15px">单件额度&nbsp;&nbsp;&nbsp; *<input type="text" name="rule_three" value="" class="rg_03" id="rule_three">‱&nbsp;&nbsp;&nbsp;*&nbsp;&nbsp;&nbsp;月数&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>（注：按月发放，次月起）</span></p>
						<p class="pad_left_01" style="margin-bottom:15px">单件月提成合计数</p>
						<p class="pad_left_03"><span>（注：1、出现逾期后此件不再计提业绩提成、提前还款不再计提<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2、所有业绩只按新件计算）</span></p>
					</div>
				</div>
				</form>
			</div>
			
		</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
<script type="text/javascript">
	$(function(){
		$(".result_tents input").attr("readOnly",true);
	})
	$(function(){
		$(".switch_img").click(function(){
			if($(this).attr('src')=='images/fill.png'){
				$(this).attr('src','images/save.png');
				$(".result_tents input").attr("readOnly",false);
				$(".result_tents input").css("background","#fff");
			}else{
				$(this).attr('src','images/fill.png');
				$(".result_tents input").css("background","#f5f4f4");
				
				var formObject = {};

				var formArray =$("#ff").serializeArray();

				$.each(formArray,function(i,item){

					formObject[item.name] = item.value;

				});
				var formJson = JSON.stringify(formObject);
				var a=$("#id").val()
				
				var url;
				if(a==""){
				
					url="toAddRole.action";
				}else{
				
					url="toUpdateRole.action";
				}
					$.ajax({		
							//几个参数需要注意一下
			                type: "POST",//方法类型
			                dataType: "json",//预期服务器返回的数据类型
			                url: url,
			                data: formJson,
			                contentType : 'application/json',
			                success: function (result) {
			                	
			                    if (result.code == 0) {
			                    
			                    	//保存表单后，输入框内容为只读，不可修改
									
									window.location.href="rules.action";
									
			                   		
			                    }else{
			                    	window.wxc.xcConfirm("抱歉！出了一点小问题", window.wxc.xcConfirm.typeEnum.error);
			                    }                   
			                },
			                error : function(result) {
			                	window.wxc.xcConfirm("抱歉！出了一点小问题", window.wxc.xcConfirm.typeEnum.error);
			                }		           
					 });		
				$(".result_tents input").attr("readOnly",true);
		}
		 });
	});
	/*一级导航栏选中 */
	$(function(){
		navSelected(5);
		$(".sub-nav").children("li:first").find("a").addClass("selected");
	})
	
	function getlist(th){
		$(th).parent().siblings().children("a").removeClass("selected");
		$(th).addClass("selected");
		var c=$(th).text();
		$("#district_id").attr("value",c);
	var url = "getlistrule.action?area="+c;
		$.getJSON(url,function(res){
			
			if(res[0]===undefined){
				
				$("#id").attr("value","");
				$("#district_id").attr("value","");
				$("#task").attr("value","");
				$("#completionrate").attr("value","");
				$("#rule_one").attr("value","");
				$("#rule_two").attr("value","");
				$("#rule_three").attr("value","");
				$("#salesManageNumber").attr("value","");
			
			}else{
			
				var x=res[0].id;
				var a=res[0].district_id;
				var b=res[0].task;
				var c=res[0].completionrate;
				var d=res[0].rule_one;
				var e=res[0].rule_two;
				var f=res[0].rule_three;
				var g=res[0].salesManageNumber;
				$("#id").attr("value",x);
				$("#district_id").attr("value",a);
				$("#task").attr("value",b);
				$("#completionrate").attr("value",c);
				$("#rule_one").attr("value",d);
				$("#rule_two").attr("value",e);
				$("#rule_three").attr("value",f);
				$("#salesManageNumber").attr("value",g);
			}
		});
		
	};
	 $(function(){
		 
		 var url = "getlistrule.action?area=石家庄";
		
			$.getJSON(url,function(res){
				var x=res[0].id;
				var a=res[0].district_id;
				var b=res[0].task;
				var c=res[0].completionrate;
				var d=res[0].rule_one;
				var e=res[0].rule_two;
				var f=res[0].rule_three;
				var g=res[0].salesManageNumber;
				
				$("#id").attr("value",x);
				$("#district_id").attr("value",a);
				$("#task").attr("value",b);
				$("#completionrate").attr("value",c);
				$("#rule_one").attr("value",d);
				$("#rule_two").attr("value",e);
				$("#rule_three").attr("value",f);
				$("#salesManageNumber").attr("value",g);
				
			});
			
	 });
		 
</script>