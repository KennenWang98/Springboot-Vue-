<template>
  <div>
    <el-table
      :data="tableData3"
      height="700px"
      border
      style="width: 850px">
      <el-table-column
        prop="schoolId"
        label="学校编号"
        width="100">
      </el-table-column>
      <el-table-column
        prop="schoolName"
        label="学校名称"
        width="100">
      </el-table-column>
      <el-table-column
        prop="principal"
        label="校长"
        width="280">
      </el-table-column>
      <el-table-column
        prop="schoolText"
        label="学校简介"
        width="200">
      </el-table-column>
      <el-table-column
        prop="schoolIcon"
        label="学校图片"
        width="150">
        <template  slot-scope="scope" >
          <img :src=scope.row.schoolIcon min-width="70" height="70" />
        </template>
      </el-table-column>
    </el-table>
    <el-form ref="sizeForm" :model="sizeForm" label-width="180px" size="mini">
      <el-form-item label="请输入要删除的学校编号">
        <el-input v-model="sizeForm.schoolId" style="width: 300px;"></el-input>
      </el-form-item>
      <el-form-item size="large">
        <el-button type="primary" @click="onSubmit">立即删除</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
  import axios from 'axios'
  import {requestDeleteSchool} from "../../../api/api";
  export default {
    data() {
      return {
        tableData3: [],
        sizeForm: {
          schoolId: '',
          schoolName:'',
          principal:'',
          schoolText:'',
          schoolIcon:'',
          delivery: false,
          desc: ''
        }
      };
    },
    methods: {
      onSubmit() {
        if (confirm("确定要删除该学校吗？")) {
          this.$refs.sizeForm.validate((valid) => {
            if (valid) {
              let deleteSchoolParams = {schoolId: this.sizeForm.schoolId};
              requestDeleteSchool(deleteSchoolParams).then(data => {
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
      axios.get('/new/school/print')
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
