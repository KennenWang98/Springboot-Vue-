<template>
  <el-form ref="sizeForm" :model="sizeForm" label-width="100px" size="mini">
    <el-form-item label="班级名称">
      <el-input v-model="sizeForm.className" style="width: 300px;"></el-input>
    </el-form-item>
    <el-form-item label="学校名称">
      <el-input v-model="sizeForm.schoolName" style="width: 300px;"></el-input>
    </el-form-item>
    <el-form-item label="识别码">
      <el-input v-model="sizeForm.code" style="width: 300px;"></el-input>
    </el-form-item>
    <el-form-item label="教师联系方式">
      <el-input v-model="sizeForm.phoneNumber" style="width: 300px;"></el-input>
    </el-form-item>
    <el-form-item label="班级简介">
      <el-input v-model="sizeForm.classText" style="width: 300px;"></el-input>
    </el-form-item>
    <el-upload
      class="upload-demo"
      action="/new/class/upload"
      :limit=1
      :auto-upload=false
      :on-change="handleChange"
      :on-remove="handleRemove2"
      :on-success="handleSuccess"
      :file-list="fileList3"
      list-type="picture"
      style="width: 300px;">
      <el-button size="small" type="primary">点击上传班级图片</el-button>
    </el-upload>
    <el-form-item size="large" style="padding-top:20px;">
      <el-button type="primary" @click="onSubmit">立即添加</el-button>
      <el-button>取消</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
  import axios from 'axios'
  import { requestCreateClass} from "../../../api/api";
  export default {
    data() {
      return {
        fileList3: [],
        sizeForm: {
          className: '',
          phoneNumber:'',
          schoolName: '',
          code: '',
          classText:'',
          delivery: false,
          desc: ''
        },
        param2:{},
        param1:{
          classIcon:'',
        }
      };
    },
    methods: {
      handleChange(file, fileList) {
        this.param2 = new FormData();
        this.param2.append('file', file.raw, file.name);
        axios.post("/new/class/upload", this.param2).then(res => res.data)
          .then(data => {
            console.log(data)
            this.param1.classIcon = data.data
            console.log(this.param1.classIcon)
            console.log(1)
          })
      },
      handleRemove2(file, filesList) {
        this.param2.delete('file')
      },
      handleSuccess(res, file, fileList) {
      },
      onSubmit() {
        this.$refs.sizeForm.validate((valid) => {
          if (valid) {
            let createClassParams = { className: this.sizeForm.className, schoolName: this.sizeForm.schoolName,
              code: this.sizeForm.code, classIcon: this.param1.classIcon,
              classText: this.sizeForm.classText, phoneNumber: this.sizeForm.phoneNumber}
            requestCreateClass(createClassParams).then(data => {
              console.log(data);
              let code = data.data.code;
              let msg = data.data.msg;
              if (code !== 200) {
                this.$message({
                  message: msg,
                });
              }else if(code === 200){
                alert('创建班级成功');
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




