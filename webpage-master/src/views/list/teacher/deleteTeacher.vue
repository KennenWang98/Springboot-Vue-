<template>
  <div>
    <el-table
      :data="tableData3"
      height="700px"
      border
      style="width: 651px">
      <el-table-column
        prop="teaId"
        label="教师编号"
        width="100">
      </el-table-column>
      <el-table-column
        prop="teaName"
        label="教师名"
        width="100">
      </el-table-column>
      <el-table-column
        prop="phoneNumber"
        label="联系方式"
        width="200">
      </el-table-column>
      <el-table-column
        prop="gender"
        label="教师性别"
        width="100">
      </el-table-column>
      <el-table-column
        prop="teaIcon"
        label="教师照片"
        width="150">
        <template  slot-scope="scope" >
          <img :src=scope.row.teaIcon min-width="70" height="70" />
        </template>
      </el-table-column>
    </el-table>
    <el-form ref="sizeForm" :model="sizeForm" label-width="180px" size="mini">
      <el-form-item label="请输入要删除的教师编号">
        <el-input v-model="sizeForm.teaId" style="width: 300px;"></el-input>
      </el-form-item>
      <el-form-item size="large">
        <el-button type="primary" @click="onSubmit">立即删除</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
  import axios from 'axios'
  import {requestDeleteTeacher} from "../../../api/api";
  export default {
    data() {
      return {
        tableData3: [],
        sizeForm: {
          teaId: '',
          teaName:'',
          phoneNumber:'',
          gender:'',
          teaIcon:'',
          delivery: false,
          desc: ''
        }
      };
    },
    methods: {
      onSubmit() {
        if (confirm("确定要删除该教师吗？")) {
          this.$refs.sizeForm.validate((valid) => {
            if (valid) {
              let deleteTeacherParams = {teaId: this.sizeForm.teaId};
              requestDeleteTeacher(deleteTeacherParams).then(data => {
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
      axios.get('/new/teacher/print')
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
