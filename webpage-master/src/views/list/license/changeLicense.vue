<template>
  <el-form ref="sizeForm" :model="sizeForm" label-width="90px" size="mini">
    <el-form-item label="识别码编号">
      <el-input v-model="sizeForm.licenseId" style="width: 300px;"></el-input>
    </el-form-item>
    <el-form-item label="识别码">
      <el-input v-model="sizeForm.code" style="width: 300px;"></el-input>
    </el-form-item>
    <el-form-item label="使用状态">
      <el-select v-model="sizeForm.isUsed" placeholder="请选择使用状态">
        <el-option label="0" value="0"></el-option>
        <el-option label="1" value="1"></el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="激活日期">
      <el-col :span="11">
        <el-date-picker type="date" placeholder="选择日期" v-model="sizeForm.activeTime" style="width: 200px;"></el-date-picker>
      </el-col>
    </el-form-item>
    <el-form-item label="到期日期">
      <el-col :span="11">
        <el-date-picker type="date" placeholder="选择日期" v-model="sizeForm.remainTime" style="width: 200px;"></el-date-picker>
      </el-col>
    </el-form-item>
    <el-form-item label="教材编号">
      <el-input v-model="sizeForm.bookId" style="width: 300px;"></el-input>
    </el-form-item>
    <el-form-item size="large">
      <el-button type="primary" @click="onSubmit">点击上传修改</el-button>
      <el-button>取消</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
  import {requestChangeLicense} from "../../../api/api";

  export default {
    data() {
      return {
        sizeForm: {
          licenseId:'',
          code: '',
          isUsed: '',
          activeTime: '',
          remainTime: '',
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
            let changeLicenseParams = { licenseId: this.sizeForm.licenseId
              , code: this.sizeForm.code
              , isUsed: this.sizeForm.isUsed
              , activeTime: this.sizeForm.activeTime
              , remainTime: this.sizeForm.remainTime
              , bookId: this.sizeForm.bookId };
            requestChangeLicense(changeLicenseParams).then(data => {
              console.log(data);
              let code = data.code;
              let msg = data.msg;
              if (code !== 200) {
                this.$message({
                  message: msg,
                });
              }else if(code === 200){
                alert('修改许可码信息成功');
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

