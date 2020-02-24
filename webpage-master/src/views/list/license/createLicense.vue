<template>
  <el-form ref="sizeForm" :model="sizeForm" label-width="90px" size="mini">
    <el-form-item label="识别码数量">
      <el-input v-model="sizeForm.codeNum" style="width: 300px;"></el-input>
    </el-form-item>
    <el-form-item label="教材编号">
      <el-input v-model="sizeForm.bookId" style="width: 300px;"></el-input>
    </el-form-item>
    <el-form-item size="large">
      <el-button type="primary" @click="onSubmit">立即添加</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
  import {requestCreateLicense} from "../../../api/api";
  export default {
    data() {
      return {
        sizeForm: {
          codeNum: '',
          bookId:'',
          delivery: false,
          desc: ''
        }
      };
    },
    methods: {
      onSubmit() {
        this.$refs.sizeForm.validate((valid) => {
          if (valid) {
            let createLicenseParams = { codeNum: this.sizeForm.codeNum, bookId: this.sizeForm.bookId };
            requestCreateLicense(createLicenseParams).then(data => {
              console.log(data);
              let code = data.data.code;
              let msg = data.data.msg;
              if (code !== 200) {
                this.$message({
                  message: msg,
                });
              }else if(code === 200){
                alert('创建许可码成功');
              }
            });
          } else {
            console.log('error submit!!');
            return false;
          }
        });
        console.log('submit!');
      }
    }
  };
</script>
