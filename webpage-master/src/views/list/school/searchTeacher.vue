<template>
  <el-table
    :data="tableData3"
    height="800px"
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
</template>

<script>
  import {requestSearchTeacher} from "../../../api/api";

  export default {
    data() {
      return {
        tableData3: []
      }
    },
    methods:{

    },
    mounted:function(){
      let schoolId1 = this.$route.query.schoolId
      let searchTeacherParams = { schoolId: schoolId1};
      requestSearchTeacher(searchTeacherParams).then(data => {
        let code = data.data.code;
        let msg = data.data.msg;
        console.log(msg)
        this.tableData3 = data.data.data;
        if (code !== 200) {
          this.$message({
            message: msg,
          });
        }
      });
    }
  }
</script>
