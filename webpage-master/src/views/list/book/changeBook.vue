<template>
  <el-form ref="sizeForm" :model="sizeForm" label-width="90px" size="mini">
    <el-form-item label="教材编号">
      <el-input v-model="sizeForm.bookId" style="width: 300px;"></el-input>
    </el-form-item>
    <el-form-item label="教材名称">
      <el-input v-model="sizeForm.bookName" style="width: 300px;"></el-input>
    </el-form-item>
    <el-form-item label="教材等级">
      <el-input v-model="sizeForm.bookRank" style="width: 300px;"></el-input>
    </el-form-item>
    <el-form-item label="学科编号">
      <el-input v-model="sizeForm.subjectId" style="width: 300px;"></el-input>
    </el-form-item>
    <el-upload
      class="upload-demo"
      action="/new/book/upload"
      :limit=1
      :auto-upload=false
      :on-change="handleChange"
      :on-remove="handleRemove2"
      :on-success="handleSuccess"
      :file-list="fileList3"
      list-type="picture"
      style="width: 300px;">
      <el-button size="small" type="primary">点击上传教材图片</el-button>
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
  import {requestChangeBook} from "../../../api/api";
  export default {
    data() {
      return {
        fileList3: [],
        sizeForm: {
          bookName: '',
          bookId:'',
          bookRank: '',
          subjectId: '',
          bookIcon:'',
          delivery: false,
          desc: ''
        },
        param2:{},
        param1:{
          bookIcon:'',
        }
      };
    },
    methods: {
      handleChange(file, fileList) {
        this.param2 = new FormData();
        this.param2.append('file', file.raw, file.name);
        axios.post("/new/book/upload", this.param2).then(res => res.data)
          .then(data => {
            console.log(data)
            this.param1.bookIcon = data.data
            console.log(this.param1.bookIcon)
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
              let changeBookParams = {
                bookId: this.sizeForm.bookId, bookName: this.sizeForm.bookName, bookRank: this.sizeForm.bookRank,
                subjectId: this.sizeForm.subjectId, bookIcon: this.param1.bookIcon
              };
              requestChangeBook(changeBookParams).then(data => {
                console.log(data);
                let code = data.code;
                let msg = data.msg;
                if (code !== 200) {
                  this.$message({
                    message: msg,
                  });
                }else if(code === 200){
                  alert('修改教材信息成功');
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
