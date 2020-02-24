<template>
  <el-form ref="sizeForm" :model="sizeForm" label-width="100px" size="mini">
    <el-form-item label="班级编号">
      <el-input v-model="sizeForm.classId" style="width: 300px;"></el-input>
    </el-form-item>
    <el-form-item label="班级名称">
      <el-input v-model="sizeForm.className" style="width: 300px;"></el-input>
    </el-form-item>
    <el-form-item label="学校编号">
      <el-input v-model="sizeForm.schoolId" style="width: 300px;"></el-input>
    </el-form-item>
    <el-form-item label="识别码">
      <el-input v-model="sizeForm.code" style="width: 300px;"></el-input>
    </el-form-item>
    <el-form-item label="教师编号">
      <el-input v-model="sizeForm.teaId" style="width: 300px;"></el-input>
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
      <!--<div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>-->
    </el-upload>
    <el-form-item size="large" style="padding-top:20px;">
      <el-button type="primary" @click="onSubmit">点击上传修改</el-button>
      <el-button>取消</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
  import axios from 'axios'
  import {requestChangeClass} from "../../../api/api";

  export default {
    data() {
      return {
        fileList3: [],
        sizeForm: {
          classId:'',
          className: '',
          schoolId: '',
          teaId:'',
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
        //console.log(1)
      },
      onSubmit() {
        this.$refs.sizeForm.validate((valid) => {
          if (valid) {
            let changeClassParams = { classId: this.sizeForm.classId, className: this.sizeForm.className, schoolId: this.sizeForm.schoolId,
              code: this.sizeForm.code, classIcon: this.param1.classIcon,
              classText: this.sizeForm.classText, teaId: this.sizeForm.teaId};
            requestChangeClass(changeClassParams).then(data => {
              console.log(data);
              let code = data.code;
              let msg = data.msg;
              if (code !== 200) {
                this.$message({
                  message: msg,
                });
              }else if(code === 200){
                alert('修改班级信息成功');
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
