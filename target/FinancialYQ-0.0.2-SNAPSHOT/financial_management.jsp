<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<title>财务管理</title>
<jsp:include page="nav.jsp"></jsp:include>
			<div class="content">
				<div class="sub-box">
					<ul class="sub-nav">
						<li><a href="toStaff.action">员工管理</a></li>
						<li><a href="toIncome.action">收入费用</a></li>
						<li><a href="toSpend.action">支出费用</a></li>
						<li><a href="toFinanceManager.action" class="selected">标的管理</a></li>
						<li><a href="toCompany.action">公司管理</a></li>
					</ul>
					<input type="button" value="添加" class="sub-button" onclick="popup()"/>
				</div>
				<table id="cs_table" class="table_tbs" rules=rows>
				</table>
			</div>
      <jsp:include page="footer.jsp"></jsp:include>
      	<!--
        	作者：157144230@qq.com
        	时间：2018-07-05
        	描述：弹窗
        -->
        <div class="popup">
        	<form id="ff">
        		<div class="popup-layer">
        			<input type="hidden" name="id" value=""/>
        			<span>标号</span><input type="text"  name="grade" id="area" class="popup-input" onkeyup="findGread()"/>
        			
        		</div>
        		<p class="popup-error" id="area-error"></p>	
        		<div class="popup-layer">
        			<span>逾期</span>
        			<select class="popups-select" name="is_overdue" id="is_overdue">
        				<option value="0">否</option>
        				<option value="1">是</option>
        			</select>       		
        		</div>
        		<div class="popup-layer">
        			<span>提前还款</span>
        			<select class="popups-select" name="is_payment" id="is_payment">
        				<option value="0">否</option>
        				<option value="1">是</option>
        			</select>       
        		</div>
        		<p class="popup-error" id="error"></p>	
        		<div class="popup-layer" style="text-align: center;">
        			<input type="button" class="popup-sub"  value="保存" onclick="update()"/>
        			<input type="button" value="取消" class="popup-return"/>
        		</div>       		
        	</form>
        </div>
	</body>
	
	<script type="text/javascript">
	function update(){
		var grade = $("#area").val();
		var formObject = {};
		var formArray = $("#ff").serializeArray();
		$.each(formArray,function(i,item){
			formObject[item.name] = item.value;
		});
		if($('#area').val().trim()==''){
			$('#area-error').text('标号不能为空');
			return false;
		}else{
			$('#area-error').text('');
		}
		if($('#is_overdue').val()=='1' && $('#is_payment').val()=='1'){
			$('#error').text('逾期和提前还款不能同时为是');
			return false;
		}else{
			$('#error').text('');
		}
		
		var formJson = JSON.stringify(formObject);
		$.ajax({
			type:"POST",
			dataType:"json",
			url:"toUpdateFinanceStatus.action?grade="+grade,
			data:formJson,
			contentType:"application/json",
			success:function(result){
				if(result.code ==0){
					
					window.location.href="toFinanceManager.action";
					
				}
			},
			error:function(result){
				console.log("异常！"+result.msg);
			}
		});
	}
	
	</script>
	<script type="text/javascript">
	/*一级导航栏选中 */
	navSelected(4);
	
	$(function(){
		var c="${roletype}";
		if(c=='行政'){
			$('.sub-nav li').slice(1,4).css("display","none");
		}
		if(c=='记账'){
			$('.sub-nav li').slice(0,1).css("display","none");
			$('.sub-nav li').slice(4,5).css("display","none");
		}
		
		var url= "toFindAllType.action";
		if(c!='行政'){
		$.getJSON(url,function(res){
			
			var data = [];
			for(var i=0;i<res.length;i++){
			   data[i] = {id:res[i].id,grade:res[i].grade,name:res[i].name,money:res[i].money,day:res[i].day,is_payment:res[i].is_payment==0?'<span style="color:green">否</span>':'<span style="color:red">是</span>',is_overdue:res[i].is_overdue==0?'<span style="color:green">否</span>':'<span style="color:red">是</span>',interest:res[i].interest};
			
			}
		  	console.log(data)
			var cs = new table({
			    "tableId":"cs_table",  //必须
			    "headers":["序号","标号","姓名","金额","月数","提前还款","逾期","利息"],  //必须
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
	
	
	function popup(res,id){
		$('.popup').show();
	}

	$('.popup-return').click(function(){
			$('.popup').hide();
		});		 
	</script>
	<script type="text/javascript">
		$(function(){
			 var a="toAddList.action";
			 var b="###";
			 var c="${roletype}";
			
			 if(c == '管理员'){
				 $("#aid").attr("href",a); 
			 }else{
				 $("#aid").attr("href",b);  
			 }
			
		})	
	
	</script>
</html>