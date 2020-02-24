<template>
    <div class="login-container">
        <el-form :model="ruleForm2" :rules="rules2"
         status-icon
         ref="ruleForm2"
         label-position="left"
         label-width="0px"
         class="demo-ruleForm login-page">
            <h3 class="title">登录管理平台</h3>
            <el-form-item prop="username">
                <el-input type="text"
                    v-model="ruleForm2.username"
                    auto-complete="off"
                    placeholder="用户名"></el-input>
            </el-form-item>
            <el-form-item prop="password">
                <el-input type="password"
                    v-model="ruleForm2.password"
                    auto-complete="off"
                    placeholder="密码"></el-input>
            </el-form-item>
            <el-form-item style="width:100%;">
                <el-button type="primary" style="width:100%;" @click="handleSubmit" :loading="logining">登录</el-button>
            </el-form-item>
            <el-form-item >
                <el-button type="text" @click="forgetPassword">忘记密码</el-button>
            </el-form-item>

        </el-form>
    </div>

</template>

<script>
  import { requestLogin } from '../api/api';
  export default {
    data() {
      return {
        logining: false,
        ruleForm2: {
        },
        rules2: {
          account: [
            { required: true, message: '请输入账号', trigger: 'blur' },
          ],
          checkPass: [
            { required: true, message: '请输入密码', trigger: 'blur' },
          ]
        },
        checked: true
      };
    },
    methods: {
      handleReset2() {
        this.$refs.ruleForm2.resetFields();
      },
      handleSubmit(ev) {
        this.$refs.ruleForm2.validate((valid) => {
          if (valid) {
            this.logining = true;
            let loginParams = { username: this.ruleForm2.username, password: this.ruleForm2.password, identifyCode: this.ruleForm2.identifyCode };
            requestLogin(loginParams).then(data => {
              console.log(data);
              this.logining = false;
              //let { msg, code, user } = data;
              let msg = data.msg;
              let code = data.code;
              let user = data.data;
              if (code !== 200) {
                this.$message({
                  message: msg,
                  type: 'error'
                });
              } else {
                if (user.type === "admin"){
                    sessionStorage.setItem('user', JSON.stringify(user));
                    this.$router.push({ path: '/homepage' });
                }
              }
            });
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      forgetPassword(){
          this.$alert('请联系管理员找回密码，管理员电话：178********', '提示', {
          confirmButtonText: '确定',
          type: 'warning'
        })
      }
    }
  }
</script>

<style scoped>
    label.el-button.forget {
        margin: 0;
        padding: 0;
        border: 1px solid transparent;
        outline: none;
    }
</style>
