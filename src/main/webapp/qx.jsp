<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<title>权限管理</title>
<jsp:include page="nav.jsp"></jsp:include>
<!-- Form -->
<div id="a" style="margin-top:130px">
<el-card shadow="hover">
  <el-container class="main access">
    <el-form :model="ruleForm" ref="ruleForm" label-width="100px" class="demo-ruleForm">
      <el-form-item label="风控管理：">
        <el-checkbox-group v-model="ruleForm.popedoms" class="checkbox-group">
          <el-checkbox label="1001">全部列表</el-checkbox>
          <el-checkbox label="1002">已审核</el-checkbox>
          <el-checkbox label="1003">未审核</el-checkbox>
          <el-checkbox label="1004">已打回</el-checkbox>
          <el-checkbox label="1005">已作废</el-checkbox>
          <el-checkbox label="1006">审核</el-checkbox>
          <el-checkbox label="1007">打印</el-checkbox>
          <el-checkbox label="1008">作废</el-checkbox>
        </el-checkbox-group>
      </el-form-item>
      <el-form-item label="角色权限：">
        <el-checkbox-group v-model="ruleForm.popedoms" class="checkbox-group">
          <el-checkbox label="1009">角色权限</el-checkbox>
        
        </el-checkbox-group>
      </el-form-item>
      <el-form-item label="财务管理：">
        <el-checkbox-group v-model="ruleForm.popedoms" class="checkbox-group">
          <el-checkbox label="1010">员工管理</el-checkbox>
          <el-checkbox label="1011">收入管理</el-checkbox>
          <el-checkbox label="1012">支出管理</el-checkbox>
          <el-checkbox label="1013">标的管理</el-checkbox>
          <el-checkbox label="1014">公司管理</el-checkbox>
          <el-checkbox label="1015">收入通过按钮</el-checkbox>
          <el-checkbox label="1016">支出通过按钮</el-checkbox>
        </el-checkbox-group>
      </el-form-item>
      <el-form-item label="客户信息:">
        <el-checkbox-group v-model="ruleForm.popedoms" class="checkbox-group">
          <el-checkbox label="1017">个人信息</el-checkbox>
          <el-checkbox label="1018">公司信息</el-checkbox>
        </el-checkbox-group>
      </el-form-item>
      <el-form-item>
        <el-button type="info" @click="submitForm('ruleForm')">确定</el-button>
      </el-form-item>
    </el-form>
  </el-container>
</el-card>

</div>

<jsp:include page="footer.jsp"></jsp:include>
<script>
	/*一级导航栏选中 */
	//navSelected(1);
  let a = new Vue({
    el: '#a',
    data: function () {
      return {
        ruleForm: {
        	id:'${id}',
          popedoms: []
        }
      }
    },
     methods: {
      submitForm(formName) {
    	  //alert("789")
        this.$refs[formName].validate((valid) => {
          if (valid) {
            $.ajax({
              traditional: true,
              type: 'post',
              url: '/popedom/updatePopedom.action',
              data: this.ruleForm,
              traditional: true,
              dataType: 'json',
              success: (res) => {
                if (res.code == 1) {
                	alert(res.msg)
                  //this.notifySuc(res.msg, 'accessManage.jsp')
                } else {
                	alert(res.msg)
                  //this.notifyError(res.msg)
                }
              },
              error: (res) => {
            	  alert(res.msg)
                //this.notifyError(res.msg)
              }
            })
          } else {
            return false;
          }
        })
      }
    },
    created() {
    	var id="${id}";
    	//alert("ruleForm"+id)
      if (id) {
    	  
        $.ajax({
          type: 'post',
          url: '/popedom/findPopedomByUserId.action',
          data: {id:id},
          dataType: 'json',
          success: (res) => {
        	  //alert("4556")
            if (res.code == 1) {
            	console.log(res.data)
              this.ruleForm.popedoms = res.data
            } else {
              this.notifyError(res.msg)
            }
          },
          error: (res) => {
            this.notifyError(res.msg)
          }
        })
      }
    } 
  })
</script>
</html>
