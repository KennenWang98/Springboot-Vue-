<template>
  <el-form ref="sizeForm" :model="sizeForm" label-width="90px" size="mini">
    <el-form-item label="学科名称">
      <el-input v-model="sizeForm.subjectName" style="width: 300px;"></el-input>
    </el-form-item>
    <el-form-item size="large">
      <el-button type="primary" @click="onSubmit">立即添加</el-button>
      <el-button>取消</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
  import {requestCreateSubject} from "../../../api/api";

  export default {
    data() {
      return {
        sizeForm: {
          subjectName: '',
          delivery: false,
          desc: ''
        }
      };
    },
    methods: {
      onSubmit() {
        this.$refs.sizeForm.validate((valid) => {
          if (valid) {
            let createSubjectParams = { subjectName: this.sizeForm.subjectName };
            requestCreateSubject(createSubjectParams).then(data => {
              console.log(data);
              let code = data.code;
              let msg = data.msg;
              if (code !== 200) {
                this.$message({
                  message: msg,
                });
              }else if(code === 200){
                alert('创建学科成功');
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
