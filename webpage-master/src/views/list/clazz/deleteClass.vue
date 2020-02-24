<template>
  <div>
    <el-table
      :data="tableData3"
      height="700px"
      border
      style="width: 1017px">
      <el-table-column
        prop="classId"
        label="班级编号"
        width="100">
      </el-table-column>
      <el-table-column
        prop="className"
        label="班级名称"
        width="100">
      </el-table-column>
      <el-table-column
        prop="schoolId"
        label="学校编号"
        width="100">
      </el-table-column>
      <el-table-column
        prop="teaId"
        label="教师编号"
        width="100">
      </el-table-column>
      <el-table-column
        prop="code"
        label="识别码"
        width="200">
      </el-table-column>
      <el-table-column
        prop="classIcon"
        label="班级图片"
        width="100">
        <template  slot-scope="scope" >
          <img :src=scope.row.classIcon min-width="70" height="70" />
        </template>
      </el-table-column>
      <el-table-column
        prop="classQrCode"
        label="班级二维码"
        width="150">
      </el-table-column>
      <el-table-column
        prop="classText"
        label="班级简介"
        width="150">
      </el-table-column>
    </el-table>
    <el-form ref="sizeForm" :model="sizeForm" label-width="180px" size="mini">
      <el-form-item label="请输入要删除的班级编号">
        <el-input v-model="sizeForm.classId" style="width: 300px;"></el-input>
      </el-form-item>
      <el-form-item size="large">
        <el-button type="primary" @click="onSubmit">立即删除</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
  import axios from 'axios'
  import {requestDeleteClass} from "../../../api/api";
  export default {
    data() {
      return {
        tableData3: [],
        sizeForm: {
          bookId: '',
          bookRank:'',
          subjectId:'',
          bookName:'',
          bookIcon:'',
          code:'',
          delivery: false,
          desc: ''
        }
      };
    },
    methods: {
      onSubmit() {
        if (confirm("确定要删除该班级吗？")) {
          this.$refs.sizeForm.validate((valid) => {
            if (valid) {
              let deleteClazzParams = {classId: this.sizeForm.classId};
              requestDeleteClass(deleteClazzParams).then(data => {
                console.log(data);
                let code = data.data.code;
                let msg = data.data.msg;
                if (code !== 200) {
                  this.$message({
                    message: msg,
                  });
                }else if(code === 200){
                  alert('删除成功，请刷新界面');
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
    },
    mounted:function(){
      let _this = this
      axios.get('/new/class/print')
        .then(function(res){
          console.log(res);
          _this.tableData3 = res.data.data
        })
        .catch(function(error) {
          console.log(error);
        })
    }
  }
</script>
