<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/style.css" type="text/css" />
<link rel="stylesheet" type="text/css" href="css/home_page.css" />
<link rel="stylesheet" type="text/css" media="screen" href="css/easyui.css">
<link rel="stylesheet" type="text/css" href="css/xcConfirm.css" />
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/tablePage.js"></script>
<script type="text/javascript" src="js/xcConfirm.js"></script>
<script src="//lib.baomitu.com/vue/2.6.8/vue.js"></script>
<script src="//lib.baomitu.com/element-ui/2.6.1/index.js"></script>
<script src="https://cdn.90so.net/layui/2.4.5/layui.js"></script>
<link rel="stylesheet" type="text/css" media="screen" href="https://cdn.90so.net/layui/2.4.5/css/layui.css">
<link href="https://lib.baomitu.com/element-ui/2.6.1/theme-chalk/index.css" rel="stylesheet">

<script type="text/javascript">
    var Menu = [
      {
        name: '风控管理',
        show: false,
        child: [{path: 'toShowAll.action', name: '全部列表'},
          {path: 'toShowCheked.action', name: '已审核'},
          {path: 'toShowUnchecked.action', name: '未审核'},
          {path: 'toShowBeenBack.action', name: '已打回'},
          {path: 'toShowBeenVoid.action', name: '已作废'}]
      },
      {name: '角色权限', path: 'toAuthManger.action', show: false},
      {
        name: '财务管理',
        show: false,
        child: [{path: 'toStaff.action', name: '员工管理'},
          {path: 'toIncome.action', name: '收入费用'},
          {path: 'toSpend.action', name: '支出费用'},
          {path: 'toFinanceManager.action', name: '标的管理'},
          {path: 'toCompany.action', name: '公司管理'}]
      },
      {
        name: '客户信息',
        show: false,
        child: [{path: 'custCompInfo.jsp', name: '个人信息'},
          {path: 'custPersInfo.jsp', name: '公司信息'}]
      }]
    $.ajax({
      type: 'post',
      url: '/popedom/findPopedomByUserId.action',
      data: {id: '${currUser.id}'},
      async: false,
      dataType: 'json',
      success: (res) => {
        if (res.code == 1) {
          console.log('')
          console.log(res.data)
          for (let i = 0; i < res.data.length; i++) {
            if (res.data[i] == "1001") {
              Menu[0].child[0].show = true
              Menu[0].show = true
            } else if (res.data[i] == "1002") {
              Menu[0].child[1].show = true
              Menu[0].show = true
            } else if (res.data[i] == "1003") {
              Menu[0].child[2].show = true
              Menu[0].show = true
            } else if (res.data[i] == "1004") {
              Menu[0].child[3].show = true
              Menu[0].show = true
            } else if (res.data[i] == "1005") {
              Menu[0].child[4].show = true
              Menu[0].show = true
            } else if (res.data[i] == "1009") {
              Menu[1].show = true
            } else if (res.data[i] == "1010") {
              Menu[2].child[0].show = true
              Menu[2].show = true
            } else if (res.data[i] == "1011") {
              Menu[2].child[1].show = true
              Menu[2].show = true
            } else if (res.data[i] == "1012") {
              Menu[2].child[2].show = true
              Menu[2].show = true
            } else if (res.data[i] == "1013") {
              Menu[2].child[3].show = true
              Menu[2].show = true
            } else if (res.data[i] == "1014") {
              Menu[2].child[4].show = true
              Menu[2].show = true
            } else if (res.data[i] == "1017") {
              Menu[3].child[0].show = true
              Menu[3].show = true
            } else if (res.data[i] == "1018") {
              Menu[3].show = true
              Menu[3].child[1].show = true
            } else if (res.data[i] == "1006") {
            	//setTimeout(()=>{
            		$('.sh').css('display','inline-block');
            		//console.log('----')
            	//},1000)
             }
            else if (res.data[i] == "1007") {
            	//setTimeout(()=>{
            		$('.dy').css('display','inline-block');
            	//	console.log('----')
            //	},1000)
             }
             else if (res.data[i] == "1008") {
            //	setTimeout(()=>{
            		$('.zf').css('display','inline-block');
            	//	console.log('----')
            //	},1000)
             }
             else if (res.data[i] == "1015") {
             	setTimeout(()=>{
             		/* $('.bgbtn').css('display','block')
             		$('.bgbtn').css('margin','0,auto') */
             		$('#shouru').css('display','inline-block');
             		//document.getElementById("shouru").style.display="block";
             		console.log('----')
             	},1000)
              }
            else if (res.data[i] == "1016") {
              setTimeout(()=>{
            	  $('#sub_form').css('display','inline-block')
              		//document.getElementById("sub_form").style.display="block";
              	//	console.log('----')
             	},1000)
               } 
          } 
        } else {

        }
      },
      error: (res) => {

      }
    })

  </script>
</head>
<body>
	<div class="container">
		<div class="head_box">
			<div class="head">
				<div class="head_left">
					<img src="images/logo.png" style="margin-top: 8px;">
				</div>
				<div class="head_right">
					<b>${username}</b><b>${roletype}</b><a href="logout.action"><img src="images/exit.png"></a>
				</div>
				<input type="hidden" value="${roletype}" />
			</div>
			<div class="classify_box">
				<div id="app">
					<el-menu mode="horizontal"> <template v-for="(menu_a,index) in menu"> <el-submenu v-if="menu_a.child" :key="index" :index="index+''"> <template slot="title">{{ menu_a.name }}</template> <el-menu-item v-for="(menu_c,index_c) in menu_a.child" :index="index+'-'+(index_c+1)">
					<a :href="menu_c.path" :key="index_c" v-show="menu_c.show">{{ menu_c.name }}</a></el-menu-item> </el-submenu> <el-menu-item v-else :key="index" :index="index+''" v-show="menu_a.show">
					<a :href="menu_a.path">{{ menu_a.name }}</a></el-menu-item> </template> </el-menu>
				</div>
			</div>
		</div>