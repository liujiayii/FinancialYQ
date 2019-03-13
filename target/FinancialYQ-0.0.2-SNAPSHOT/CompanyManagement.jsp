<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<title>业务管理</title>
<jsp:include page="nav.jsp"></jsp:include>
			<div class="content">
				<div class="sub-box">
					<ul class="sub-nav">
					<c:forEach items="${list}" var="item">
						<li><a onclick="getlist(this)">${item.area}</a></li>
					</c:forEach>	
					</ul>
					<!-- <img src="images/add.png" id="sub-add"/>-->
					<div class="sub-right" id="tf">
					     <input type="hidden" id="area"> 
						<input type="text" value="" name="name" class="sub-search" placeholder="请输入姓名" id="find" onkeyup="findSale($('#area').val())"/>
						<a id="aid" charset='ISO-8859-1' href="###" class="sub-button">添加</a>
					</div>
				</div>
				<table id="cs_table" class="table_tbs" rules=rows>
				</table>
			</div>
		<jsp:include page="footer.jsp"></jsp:include>
	</body>
	<script type="text/javascript">
	/*一级导航栏选中 */
	$(function(){
		navSelected(1);
		$(".sub-nav").children("li:first").find("a").addClass("selected");
	})
	
	function getlist(th){
		
		$(th).parent().siblings().children("a").removeClass("selected");
		$(th).addClass("selected");
		var c=$(th).text();
		/* $("#area").val(c); */ 
		
		var url = "getlistsalesman.action?area="+c;
       
		
		$.getJSON(url,function(res){
			var data = [];

			console.log(res)
			if(res.length>0){
				var a=res[0].district_id;
				var b="###";
					 var c="${roletype}";
	 
					 if(c == '管理员'||c=='财务总监'){
						 $("#aid").attr("href","toManagementAdd.action?area="+a); 
					 }else{
						 $("#aid").attr("href",b);  
					 }
				
			  	for(var i=0;i<res.length;i++){
			  	   data[i] = {idss:res[i].id,duty:res[i].duty,area:res[i].area,name:res[i].name,age:res[i].phone,address:getTime(res[i].create_time),操作:"<a href='toupdatesalesMan.action?id="+res[i].id+"'><img src='images/editors.png'></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src='images/deletes.png' onclick='deleted("+res[i].id+")'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+updates(res[i].state,res[i].id)};
				}

			}
			var cs = new table({
			    "tableId":"cs_table",  //必须
			    "headers":["序号","职位","地区","姓名","电话","时间","操作"],  //必须
			    "data":data,    //必须
			    "displayNum": 5,  //必须  默认 10
			    "groupDataNum":10 //可选  默认 10
			});
			if(data.length==0){
				$('#cs_table').append('<img class="nodata" src="images/nodata.png">');
			}
		});
		
		
	};
	 $(function(){
		 
		
			var c="石家庄"
		 	var url = "getlistsalesman.action?area="+c
		 	 $("#area").val('石家庄')
			$.getJSON(url,function(res){
				var b="###";
				
				 var c="${roletype}";
				
				 
				 if(c == '管理员'||c=='财务总监'){
					 $("#aid").attr("href","toManagementAdd.action?area=石家庄"); 
				 }else{
					 $("#aid").attr("href",b);  
				 }
				var data = [];
			  	for(var i=0;i<res.length;i++){
				    data[i] = {idss:res[i].id,duty:res[i].duty,area:res[i].area,name:res[i].name,age:res[i].phone,address:getTime(res[i].create_time),操作:"<a href='toupdatesalesMan.action?id="+res[i].id+"'><img src='images/editors.png'></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src='images/deletes.png' onclick='deleted("+res[i].id+")'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+updates(res[i].state,res[i].id)};
				}
			  	console.log(data)
				var cs = new table({
				    "tableId":"cs_table",  //必须
				    "headers":["序号","职位","地区","姓名","电话","时间",""],  //必须
				    
				    "data":data,    //必须
				    "displayNum": 5,  //必须  默认 10
				    "groupDataNum":10 //可选  默认 10
				});
			  	if(data.length==0){
					$('#cs_table').append('<img class="nodata" src="images/nodata.png">');
				}
			});
			
	 });
		 
	function add(){
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
	                url: "toAddbranchoffice.action" ,
	                data: formJson,
	                contentType : 'application/json',
	                success: function (result) {
	                	
	                    if (result.code == 0) {
	                    	window.location.href="toCompanyManagement.action";
	                   		
	                    }else{
	                    	window.wxc.xcConfirm("抱歉！出了一点小问题", window.wxc.xcConfirm.typeEnum.error);
	                    }               
	                },
	                error : function(result) {
	                	console("异常！！"+result.msg);
	                	window.wxc.xcConfirm("抱歉！出了一点小问题", window.wxc.xcConfirm.typeEnum.error);
	                }
	            });
	}
	<!--查询业务员使用onkeyup离开键盘的时候就会查-->
	function findSale(area){
		var url;
		var sname = $("#find").val().trim();
		if(sname==''){
			if(area==''){
				url = "getlistsalesman.action?area=石家庄";
			}else{
				url = "getlistsalesman.action?area="+area+"";
			}
		}else{
			url = "toFindSales.action?name="+sname;
			
		}
		
		 $.getJSON(url,function(res){
			var data = [];
		  	for(var i=0;i<res.length;i++){
		  	
		  	  data[i] = {idss:res[i].id,duty:res[i].duty,area:res[i].area,name:res[i].name,age:res[i].phone,address:getTime(res[i].create_time),操作:"<a href='toupdatesalesMan.action?id="+res[i].id+"'><img src='images/editors.png'></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src='images/deletes.png' onclick='deleted("+res[i].id+")'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+updates(res[i].state,res[i].id)};
			}
		  	console.log(data)
			var cs = new table({
			    "tableId":"cs_table",  //必须
			    "headers":["序号","姓名","地区","职位","电话","时间","操作"],  //必须
			    "data":data,    //必须
			    "displayNum": 5,  //必须  默认 10
			    "groupDataNum":10 //可选  默认 10
			});
		  	if(data.length==0){
				$('#cs_table').append('<img class="nodata" src="images/nodata.png">');
			}
		});
		 
	};
	
	function getTime(t){
	   	var  _time=new Date(t);
	    var  year=_time.getFullYear();//2017
	    var  month=_time.getMonth()+1;//7

	    var  date=_time.getDate()-1;//10
	    var  hour=_time.getHours()<10?"0"+_time.getHours():_time.getHours();//10
	    var  minute=_time.getMinutes()<10?"0"+_time.getMinutes():_time.getMinutes();//56
	    var  second=_time.getSeconds()<10?"0"+_time.getSeconds():_time.getSeconds();//15
	    var result=year+"年"+month+"月"+date+"日   "+hour+":"+minute+":"+second;//这里自己按自己需要的格式拼接
	   
	    return result;
	}	
	function updates(t,y){
		var a;
		if(t==0){
			a="<img src='images/not_lock.png' id='lockId' onclick='updatesd(this,"+y+")'>"
		}else{
			a="<img src='images/lock.png' id='lockId' onclick='updatesd(this,"+y+")'>"
		}
		return a;
	}
	
	function updatesd(lock,y){
		 $.ajax({		

	            //几个参数需要注意一下
	                type: "POST",//方法类型
	                dataType: "json",//预期服务器返回的数据类型
	                url: "toupdatebyId.action" ,
	                data: {id:y},
	                contentType : 'application/json',
	                success: function (result) {
	                
	                    if (result.code == 0) {
	                    	var scrs=$(lock).attr('src');
							if(scrs=='images/not_lock.png'){
								$(lock).attr('src','images/lock.png');
							}else{
								$(lock).attr('src','images/not_lock.png');
							}
	                   		
	                    }else{
	                    	window.wxc.xcConfirm("抱歉！出了一点小问题", window.wxc.xcConfirm.typeEnum.error);
	                    }                   
	                },
	                error : function(result) {
	                	window.wxc.xcConfirm("抱歉！出了一点小问题", window.wxc.xcConfirm.typeEnum.error);
	                }
	            });
	}
	
	 function deleted(y){
		 var option = {
					title: "信息",
					btn: parseInt("0011",2),
					icon: "0 -96px",
					onOk: function(){
		 		var d=$("#area").val()+""; 
				$.ajax({		

	            //几个参数需要注意一下
	                type: "POST",//方法类型
	                dataType: "json",//预期服务器返回的数据类型
	                url: "toDeleteSale.action",
	                data: {id:y},
	                contentType : 'application/json',
	                success: function (result) {
	                
	                    if (result.code == 0) {
	                    	
	                    	
	                    	var url = "getlistsalesman.action?area="+d;
	                		
	                		$.getJSON(url,function(res){
	                			var data = [];

	                			console.log(res)
	                			if(res.length>0){
	                				var a=res[0].district_id;
	                				var b="###";
	                					  var c="${roletype}";
	                	 
	                					 if(c == '管理员'||c=='财务总监'){
	                						 $("#aid").attr("href","toManagementAdd.action?area="+a); 
	                					 }else{
	                						 $("#aid").attr("href",b);  
	                					 }
	                				
	                			  	for(var i=0;i<res.length;i++){
	                			  	   data[i] = {idss:res[i].id,duty:res[i].duty,area:res[i].area,name:res[i].name,age:res[i].phone,address:getTime(res[i].create_time),操作:"<a href='toupdatesalesMan.action?id="+res[i].id+"'><img src='images/editors.png'></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src='images/deletes.png' onclick='deleted("+res[i].id+")'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+updates(res[i].state,res[i].id)};
	                				}

	                			}
	                			var cs = new table({
	                			    "tableId":"cs_table",  //必须
	                			    "headers":["序号","职位","地区","姓名","电话","时间","操作"],  //必须
	                			    "data":data,    //必须
	                			    "displayNum": 5,  //必须  默认 10
	                			    "groupDataNum":10 //可选  默认 10
	                			});
	                			if(data.length==0){
	                				$('#cs_table').append('<img class="nodata" src="images/nodata.png">');
	                			}
	                		});
	                		
	                   		
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
		window.wxc.xcConfirm('确定要删除吗？', "custom", option);
	}
	</script>
</html>
