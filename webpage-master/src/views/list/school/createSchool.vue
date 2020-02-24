<template>
  <el-form ref="sizeForm" :model="sizeForm" label-width="90px" size="mini">
    <el-form-item label="学校名称">
      <el-input v-model="sizeForm.schoolName" style="width: 300px;"></el-input>
    </el-form-item>
    <el-form-item label="校长">
      <el-input v-model="sizeForm.principal" style="width: 300px;"></el-input>
    </el-form-item>
    <el-form-item label="学校简介">
      <el-input v-model="sizeForm.schoolText" style="width: 300px;"></el-input>
    </el-form-item>
    <el-upload
      class="upload-demo"
      action="/new/school/upload"
      :limit=1
      :auto-upload=false
      :on-change="handleChange"
      :on-remove="handleRemove2"
      :on-success="handleSuccess"
      :file-list="fileList3"
      list-type="picture"
      style="width: 300px;">
      <el-button size="small" type="primary">点击上传学校图片</el-button>
      <!--<div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>-->
    </el-upload>
    <el-form-item size="large" style="padding-top:20px;">
      <el-button type="primary" @click="onSubmit">立即添加</el-button>
      <el-button>取消</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
  import axios from 'axios'
  import {requestCreateSchool} from "../../../api/api";
  export default {
    data() {
      return {
        fileList3: [],
        sizeForm: {
          schoolName: '',
          principal: '',
          schoolText: '',
          delivery: false,
          desc: ''
        },
        param2:{},
        param1:{
          schoolIcon:'',
        }
      };
    },
    methods: {
      handleChange(file, fileList) {
        this.param2 = new FormData();
        this.param2.append('file', file.raw, file.name);
        axios.post("/new/school/upload", this.param2).then(res => res.data)
          .then(data => {
            console.log(data)
            this.param1.schoolIcon = data.data
          })
      },
      handleRemove2(file, filesList) {
        this.param2.delete('file')
      },
      handleSuccess(res, file, fileList) {
        //console.log(1)
      },
      onSubmit() {
        this.$refs.sizeForm.validate((valid) => {
          if (valid) {
            let createSchoolParams = { schoolName: this.sizeForm.schoolName, principal: this.sizeForm.principal,
              schoolText: this.sizeForm.schoolText, schoolIcon: this.param1.schoolIcon};
            requestCreateSchool(createSchoolParams).then(data => {
              console.log(data);
              let code = data.code;
              let msg = data.msg;
              if (code !== 200) {
                this.$message({
                  message: msg,
                });
              }else if(code === 200){
                alert('创建学校成功');
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
  }
</script>
